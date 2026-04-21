# Use Case: GET /brapi/v2/allelematrix

Returns a rectangular slice of the genotype matrix — rows are SNP variants, columns are samples (call sets).

**Example input:** `variantSetDbId=14`, `start=1`, `end=5000`, `referenceName` not provided (defaults to `1`)

---

## Step 1 — Resolve `variantSetDbId=14` from two sources

The two dimensions of the matrix (SNPs and samples) use **different name systems** and must be resolved separately.

**Query 1 — SNP dimension:** Get `variantset.name` (used to filter `v_snp_refposindex_v2`)
```sql
SELECT variantset_id, name FROM public.variantset WHERE variantset_id = 14
```
Result: `name = "3kfiltered"` *(Filtered SNPs from 3k RGP)*

**Query 2 — CallSet dimension:** Get `db.name` via `platform → db` join (used to filter `v_allstock_basicprop`)
```sql
SELECT d.name FROM public.platform p
JOIN public.db d ON d.db_id = p.db_id
WHERE p.variantset_id = 14
```
Result: `["3k", "gq92"]`

> `v_snp_refposindex_v2.variantset` stores `variantset.name`; `v_allstock_basicprop.dataset` stores `db.name`. These are different columns in different tables — they must not be mixed.

---

## Step 2 — Query SNP page from `v_snp_refposindex_v2`

Chromosome defaults to `1` when `referenceName` is not provided.

**Count query:**
```sql
SELECT COUNT(*) FROM public.v_snp_refposindex_v2
WHERE variantset = '3kfiltered'
  AND chromosome = 1
  AND position >= 1
  AND position < 5000
```
Result: **20 SNPs** (positions 1151 → 4990, `allele_index` 0–19)

**Page query** (page=0, pageSize=1000, sorted by `allele_index`):
```sql
SELECT * FROM public.v_snp_refposindex_v2
WHERE variantset = '3kfiltered'
  AND chromosome = 1
  AND position >= 1
  AND position < 5000
ORDER BY allele_index
LIMIT 1000 OFFSET 0
```

Sample results:

| snp_feature_id | chromosome | position | refcall | allele_index |
|---|---|---|---|---|
| 32 | 1 | 1151 | C | 0 |
| 36 | 1 | 1173 | C | 1 |
| 39 | 1 | 1178 | G | 2 |
| 42 | 1 | 1203 | T | 3 |
| 47 | 1 | 1248 | G | 4 |
| … | … | … | … | … |
| 76 | 1 | 4990 | A | 19 |

---

## Step 3 — Query CallSet page from `v_allstock_basicprop`

No `callSetDbId` filter provided — all samples are included.

**Count query:**
```sql
SELECT COUNT(*) FROM public.v_allstock_basicprop
WHERE dataset IN ('3k', 'gq92')
```
Result: **3,116 samples**

**Page query** (page=0, pageSize=1000, sorted by `stock_sample_id`):
```sql
SELECT * FROM public.v_allstock_basicprop
WHERE dataset IN ('3k', 'gq92')
ORDER BY stock_sample_id
LIMIT 1000 OFFSET 0
```

---

## Step 4 — Determine HDF5 row range

From the 20 SNPs returned on the page:

- `snpStart = min(allele_index) = 0`
- `snpEnd   = max(allele_index) + 1 = 20`

HDF5 row slice: **rows 0–19** (contiguous, 20 rows).

Variety index list: `stock_sample_id` values of the 1,000 samples on this callset page.

---

## Step 5 — Resolve HDF5 file path

Executes a single JPQL query with two `LEFT JOIN FETCH` to load the full graph:

```sql
SELECT DISTINCT vs FROM VariantSet vs
LEFT JOIN FETCH vs.platforms p
LEFT JOIN FETCH p.genotypeRuns
WHERE vs.variantSetId = 14
```

Walks the graph: `variantset(14) → platform(2) → genotype_run → data_location`

- Picks the first visible `genotype_run` with a non-blank `data_location`
- Result: `data_location = "SNPuni_geno_NB_3k.h5"`
- Absolute path: `{brapi.hdf5.data-dir}/SNPuni_geno_NB_3k.h5`

The open `HdfFile` is cached in memory (`readerCache`) and reused across requests.

---

## Step 6 — Fetch sub-matrix from HDF5

```
fetchSubMatrix(variantSetDbId=14, snpStart=0, snpEnd=20, varietyIndexes=[...1000 indexes...])
```

- Reads HDF5 dataset `"matrix"` at hyperslab offset `[0, 0]`, shape `[20, totalColumns]`
- Extracts only the 1,000 requested column indexes
- Decodes raw bytes per SNPseek convention:

| Byte | GT string |
|---|---|
| 0 | `0/0` (homozygous reference) |
| 1 | `0/1` (heterozygous) |
| 2 | `1/1` (homozygous alternate) |
| 3 | `./.` (missing / no call) |

Result: `decoded[20][1000]` — 20 rows × 1,000 columns of GT strings.

---

## Step 7 — Assemble BrAPI response

```json
{
  "callSetDbIds": ["1", "2", "3", "...", "1000"],
  "variantDbIds": ["32", "36", "39", "...", "76"],
  "variantSetDbIds": ["14"],
  "dataMatrices": [{
    "dataType": "GT",
    "dataMatrix": [
      ["0/0", "0/1", "0/0", "..."],
      ["1/1", "0/0", "./.", "..."],
      "..."
    ]
  }],
  "sepUnphased": "/",
  "unknownString": "./.",
  "pagination": {
    "dimensionVariantPage": 0,
    "dimensionVariantPageSize": 1000,
    "dimensionVariantTotal": 20,
    "dimensionCallSetPage": 0,
    "dimensionCallSetPageSize": 1000,
    "dimensionCallSetTotal": 3116
  }
}
```

`dataMatrix[i][j]` = genotype call for `variantDbIds[i]` × `callSetDbIds[j]`.

Since `dimensionVariantTotal=20 < pageSize=1000`, all SNPs fit on one page. The 3,116 samples require **4 pages** (`dimensionCallSetPage` 0–3) to retrieve in full.

---

## Name System Summary

| Dimension | View | Filter column | Value source |
|---|---|---|---|
| SNPs | `v_snp_refposindex_v2` | `variantset` | `variantset.name` = `"3kfiltered"` |
| CallSets | `v_allstock_basicprop` | `dataset` | `db.name` via `platform → db` = `["3k", "gq92"]` |
| HDF5 file | `genotype_run` | `data_location` | `variantset → platform → genotype_run` graph |
