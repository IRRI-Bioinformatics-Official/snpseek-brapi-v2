# BrAPI v2.1 Implementation Roadmap

This document maps every BrAPI v2.1 endpoint to the SNPseek CHADO schema and tracks implementation status.
Use it to plan, prioritize, and communicate what is built, what is feasible, and what is out of scope.

**Last updated:** 2026-04-20

---

## Status Legend

| Symbol | Meaning |
|---|---|
| ✅ | Implemented and available |
| 🟡 | Not yet implemented — data exists in CHADO schema, feasible to build |
| 🔴 | Out of scope — data not available in schema |

---

## CHADO Tables / Views Reference

| View / Table | Java Domain Model | Description |
|---|---|---|
| `V_SNP_REFPOSINDEX_V2` | `VSnpRefposindex` / `SnpMetadata` | SNP positions, ref/alt alleles, HDF5 row index (`allele_index`) |
| `V_ALLSTOCK_BASICPROP` | `VAllstockBasicprop` / `Germplasm` | Accession basic info + HDF5 column index (`stock_sample_id`) |
| `V_ALLSAMPLE_BASICPROP` | `VAllsampleBasicprop` | Sample-level view (parallel to allstock) |
| `variantset` | `VariantSet` | Chip panels / SNP sets |
| `platform` | `Platform` | Genotyping platform linked to a variant set |
| `genotype_run` / `v_genotype_run` | `VGenotypeRun` | Genotyping run — links platform to HDF5 file path |
| `V_STOCK_PASSPORT` | `VIricstockPassport` | Key-value store of MCPD passport properties per accession |
| `V_STOCK_PHENOTYPE` | `VIricstockPhenotype` | Observed phenotype values per stock (quan + qual) |
| `V_ORGANISM` | `Organism` | Organism: genus, species, common name |
| `organism` | — | CHADO core organism table |
| `cvterm` / `cv` | `CvTerm` / `Cv` | Controlled vocabulary — variant types, ontologies, trait terms |
| `feature` | `Feature` | CHADO core: genes, chromosomes as features |
| `V_IRICSTOCK_PASSPORT_VALUES` | `VIricstockPassportValues` | Passport values with full cvterm detail |
| `V_CV_PASSPORT` | `VCvPassport` | Passport field cvterm definitions |
| `V_CV_PHENOTYPE` | `VCvPhenotype` | Phenotype / trait cvterm definitions |
| `V_INDEL_REFPOSINDEX` | `VIndelRefposindex` | InDel positions (parallel to SNP view) |

---

## Module 1 — Genotyping

### VariantSets

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| ✅ | GET | `/variantsets` | `variantset` | `variantset_id` → `variantSetDbId`, `name`, `description`, `organism_id` → organism |
| ✅ | GET | `/variantsets/{variantSetDbId}` | `variantset` + counts | `COUNT(V_SNP_REFPOSINDEX_V2 WHERE variantset=name)` → variantCount; `COUNT(V_ALLSTOCK_BASICPROP WHERE dataset=name)` → callSetCount |
| ✅ | GET | `/variantsets/{variantSetDbId}/callsets` | `V_ALLSTOCK_BASICPROP` | `WHERE dataset = variantset.name`; `stock_sample_id` → `callSetDbId`, `stock_id` → `germplasmDbId` |
| 🟡 | GET | `/variantsets/{variantSetDbId}/variants` | `V_SNP_REFPOSINDEX_V2` | `WHERE variantset = name`; `snp_feature_id` → `variantDbId`, `chromosome` → `referenceName`, `position` → `start`, `refcall` → `referenceBases`, `altcall` → `alternateBases` |
| 🟡 | POST | `/search/variantsets` | `variantset` | Filter by `variantset_id`; join `V_ORGANISM` via `organism_id` for `commonCropName` |
| 🟡 | GET | `/search/variantsets/{searchResultsDbId}` | — | Async poll of above |
| 🔴 | PUT | `/variantsets/{variantSetDbId}` | `variantset` | Schema is read-only |

### Variants

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| 🟡 | GET | `/variants` | `V_SNP_REFPOSINDEX_V2` | `snp_feature_id` → `variantDbId`, `variantset` → `variantSetDbId`, `chromosome` → `referenceName`, `position` → `start`, `refcall` → `referenceBases`, `altcall` → `alternateBases`, `type_id` → variant type via `cvterm` |
| 🟡 | GET | `/variants/{variantDbId}` | `V_SNP_REFPOSINDEX_V2` | `WHERE snp_feature_id = ?` |
| ✅ | POST | `/search/variants` | `V_SNP_REFPOSINDEX_V2` | `snp_feature_id IN (?)`, `variantset IN (?)`, `chromosome IN (?)`, `position >= start`, `position < end` |
| ✅ | GET | `/search/variants/{searchResultsDbId}` | — | Async poll cache |

### CallSets

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| ✅ | GET | `/callsets` | `V_ALLSTOCK_BASICPROP` | `stock_sample_id` → `callSetDbId`, `stock_id` → `germplasmDbId`, `name` → `callSetName`, `dataset` → variantSet lookup |
| ✅ | GET | `/callsets/{callSetDbId}` | `V_ALLSTOCK_BASICPROP` | `WHERE stock_sample_id = ?` |
| 🟡 | GET | `/callsets/{callSetDbId}/calls` | `V_SNP_REFPOSINDEX_V2` + HDF5 | SNP list from view; genotype per row at HDF5 column `stock_sample_id`; `snp_feature_id` → `variantDbId`, decoded byte → `genotype` |
| 🟡 | POST | `/search/callsets` | `V_ALLSTOCK_BASICPROP` | Same filters: `stock_sample_id`, `stock_id`, `dataset` |
| 🟡 | GET | `/search/callsets/{searchResultsDbId}` | — | Async poll of above |

### Calls

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| 🟡 | GET | `/calls` | `V_SNP_REFPOSINDEX_V2` + `V_ALLSTOCK_BASICPROP` + HDF5 | Cross-product of SNPs × samples for a variant set; genotype value from HDF5; `snp_feature_id` → `variantDbId`, `stock_sample_id` → `callSetDbId` |
| 🟡 | POST | `/search/calls` | `V_SNP_REFPOSINDEX_V2` + HDF5 | Filter by `stock_sample_id`, `snp_feature_id`, `variantset` |
| 🟡 | GET | `/search/calls/{searchResultsDbId}` | — | Async poll of above |
| 🔴 | PUT | `/calls` | HDF5 | HDF5 files and schema are read-only |

### AlleleMatrix

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| ✅ | GET | `/allelematrix` | `variantset` + `V_SNP_REFPOSINDEX_V2` + `V_ALLSTOCK_BASICPROP` + HDF5 | `variantset_id` → name → filter SNPs and samples; `allele_index` → HDF5 row; `stock_sample_id` → HDF5 column |
| ✅ | POST | `/search/allelematrix` | same as GET | same as GET |
| ✅ | GET | `/search/allelematrix/{searchResultsDbId}` | — | Async poll cache |

### References

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| 🟡 | GET | `/references` | `V_SNP_REFPOSINDEX_V2` (derived) | `DISTINCT chromosome` → `referenceDbId` / `referenceName`; `MAX(position)` → length; link to `V_ORGANISM` for source organism |
| 🟡 | GET | `/references/{referenceDbId}` | `V_SNP_REFPOSINDEX_V2` | Chromosome number as reference ID |
| 🟡 | POST | `/search/references` | `V_SNP_REFPOSINDEX_V2` | Filter by `referenceSetDbId` (organism), chromosome |
| 🟡 | GET | `/search/references/{searchResultsDbId}` | — | Async poll of above |
| 🟡 | GET | `/referencesets` | `V_ORGANISM` + `variantset` | `organism_id` → `referenceSetDbId`; `genus + species` → `scientificName`; `common_name` → `commonCropName` |
| 🟡 | GET | `/referencesets/{referenceSetDbId}` | `V_ORGANISM` | `WHERE organism_id = ?` |
| 🟡 | POST | `/search/referencesets` | `V_ORGANISM` | Filter by `commonCropName`, `scientificName` |
| 🟡 | GET | `/search/referencesets/{searchResultsDbId}` | — | Async poll of above |

### Genome Maps

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| 🔴 | GET | `/maps` | — | No genetic/physical map table in schema |
| 🔴 | GET | `/maps/{mapDbId}` | — | Not available |
| 🔴 | GET | `/maps/{mapDbId}/linkagegroups` | — | Not available |
| 🟡 | GET | `/markerpositions` | `V_SNP_REFPOSINDEX_V2` | Physical positions only: `snp_feature_id` → `variantDbId`, `chromosome` → `linkageGroupName`, `position` → map position (bp) |
| 🟡 | POST | `/search/markerpositions` | `V_SNP_REFPOSINDEX_V2` | Filter by chromosome, position range |
| 🟡 | GET | `/search/markerpositions/{searchResultsDbId}` | — | Async poll of above |

### Samples & Plates

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| 🔴 | GET | `/samples` | `V_ALLSTOCK_BASICPROP` (partial) | `stock_sample_id` is an HDF5 index, not a lab sample record; tissue type, plate, collection date not available |
| 🔴 | POST | `/samples` | — | Read-only schema |
| 🔴 | PUT | `/samples` | — | Read-only schema |
| 🔴 | GET | `/samples/{sampleDbId}` | — | Not available |
| 🔴 | POST | `/search/samples` | — | Not available |
| 🔴 | GET | `/plates` | — | No plate/well data in schema |
| 🔴 | POST | `/plates` | — | Read-only schema |
| 🔴 | GET | `/plates/{plateDbId}` | — | Not available |
| 🔴 | POST | `/search/plates` | — | Not available |

---

## Module 2 — Germplasm

### Germplasm

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| ✅ | GET | `/germplasm` | `V_ALLSTOCK_BASICPROP` | `stock_id` → `germplasmDbId`, `name` → `germplasmName`, `gs_accession` → `accessionNumber`, `ori_country` → `countryOfOriginCode`, `subpopulation` → population group, `dataset` → variantSet membership |
| ✅ | GET | `/germplasm/{germplasmDbId}` | `V_ALLSTOCK_BASICPROP` | `WHERE stock_id = ?` |
| ✅ | POST | `/search/germplasm` | `V_ALLSTOCK_BASICPROP` | `stock_id IN (?)`, `name IN (?)`, `gs_accession IN (?)`, `ori_country IN (?)`, `subpopulation IN (?)`, `dataset IN (?)` |
| ✅ | GET | `/search/germplasm/{searchResultsDbId}` | — | Async poll cache |
| 🟡 | GET | `/germplasm/{germplasmDbId}/mcpd` | `V_STOCK_PASSPORT` | `WHERE stock_id = ?`; each row = one MCPD field (`name` → field key e.g. `ORIGCTY`, `COLLDATE`, `SAMPSTAT`; `value` → field value); pivot rows into MCPD object |
| 🟡 | GET | `/germplasm/{germplasmDbId}/callsets` | `V_ALLSTOCK_BASICPROP` | `WHERE stock_id = ?` — one row per dataset = one call set per variant set |
| 🟡 | GET | `/germplasm/{germplasmDbId}/calls` | `V_ALLSTOCK_BASICPROP` + `V_SNP_REFPOSINDEX_V2` + HDF5 | Collect all `stock_sample_id` for this `stock_id` across datasets; stream HDF5 calls |
| 🔴 | POST | `/germplasm` | `stock` | Read-only schema |
| 🔴 | PUT | `/germplasm/{germplasmDbId}` | `stock` | Read-only schema |
| 🔴 | GET | `/germplasm/{germplasmDbId}/pedigree` | — | No pedigree relationship data in schema |
| 🔴 | GET | `/germplasm/{germplasmDbId}/progeny` | — | No progeny data in schema |

### Germplasm Attributes

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| 🟡 | GET | `/attributes` | `V_CV_PASSPORT` | `cvterm_id` → `attributeDbId`, `name` → `attributeName`, `definition` → description; these are passport field definitions |
| 🟡 | GET | `/attributes/{attributeDbId}` | `V_CV_PASSPORT` | `WHERE cvterm_id = ?` |
| 🟡 | POST | `/search/attributes` | `V_CV_PASSPORT` | Filter by `name`, `definition` |
| 🟡 | GET | `/search/attributes/{searchResultsDbId}` | — | Async poll of above |
| 🟡 | GET | `/attributevalues` | `V_STOCK_PASSPORT` | `stockprop_id` → `attributeValueDbId`, `stock_id` → `germplasmDbId`, `name` → attribute name (MCPD field), `value` → attribute value |
| 🟡 | GET | `/attributevalues/{attributeValueDbId}` | `V_STOCK_PASSPORT` | `WHERE stockprop_id = ?` |
| 🟡 | POST | `/search/attributevalues` | `V_STOCK_PASSPORT` | Filter by `stock_id`, `name`, `value` |
| 🟡 | GET | `/search/attributevalues/{searchResultsDbId}` | — | Async poll of above |
| 🔴 | POST | `/attributes` | `cvterm` | Read-only schema |
| 🔴 | PUT | `/attributes/{attributeDbId}` | `cvterm` | Read-only schema |
| 🔴 | POST | `/attributevalues` | `stockprop` | Read-only schema |
| 🔴 | PUT | `/attributevalues/{attributeValueDbId}` | `stockprop` | Read-only schema |

### Breeding Methods, Crosses, Pedigree, Seed Lots

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| 🔴 | GET | `/breedingmethods` | — | No breeding method records in schema |
| 🔴 | GET | `/breedingmethods/{breedingMethodDbId}` | — | Not available |
| 🔴 | GET | `/crosses` | — | No cross records in schema |
| 🔴 | POST | `/crosses` | — | Read-only schema |
| 🔴 | PUT | `/crosses` | — | Read-only schema |
| 🔴 | GET | `/crossingprojects` | — | Not available |
| 🔴 | GET | `/plannedcrosses` | — | Not available |
| 🔴 | GET | `/pedigree` | — | No pedigree tree in schema |
| 🔴 | POST | `/search/pedigree` | — | Not available |
| 🔴 | GET | `/seedlots` | — | No seed lot inventory in schema |
| 🔴 | GET | `/seedlots/transactions` | — | Not available |

---

## Module 3 — Phenotyping

### Observation Variables, Traits, Scales, Methods, Ontologies

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| 🟡 | GET | `/variables` | `V_CV_PHENOTYPE` + `cvterm` + `cv` | `cvterm_id` → `observationVariableDbId`, `name` → variable name, `definition` → description; trait + method + scale derived from cvterm properties |
| 🟡 | GET | `/variables/{observationVariableDbId}` | `V_CV_PHENOTYPE` | `WHERE cvterm_id = ?` |
| 🟡 | POST | `/search/variables` | `V_CV_PHENOTYPE` | Filter by `name`, `cv.name` (ontology) |
| 🟡 | GET | `/search/variables/{searchResultsDbId}` | — | Async poll of above |
| 🟡 | GET | `/traits` | `V_CV_PHENOTYPE` | `cvterm_id` → `traitDbId`, `name` → `traitName`, `definition` → description, `cv.name` → ontology reference |
| 🟡 | GET | `/traits/{traitDbId}` | `V_CV_PHENOTYPE` | `WHERE cvterm_id = ?` |
| 🟡 | GET | `/ontologies` | `cv` | `cv_id` → `ontologyDbId`, `name` → `ontologyName`, `definition` → description |
| 🔴 | GET | `/methods` | — | Method definitions not stored as separate cvterms in schema |
| 🔴 | GET | `/methods/{methodDbId}` | — | Not available |
| 🔴 | POST | `/methods` | — | Read-only schema |
| 🔴 | GET | `/scales` | — | Scale definitions not available separately in schema |
| 🔴 | GET | `/scales/{scaleDbId}` | — | Not available |
| 🔴 | POST | `/scales` | — | Read-only schema |
| 🔴 | POST | `/variables` | `cvterm` | Read-only schema |
| 🔴 | PUT | `/variables/{observationVariableDbId}` | `cvterm` | Read-only schema |
| 🔴 | POST | `/traits` | `cvterm` | Read-only schema |
| 🔴 | PUT | `/traits/{traitDbId}` | `cvterm` | Read-only schema |

### Observations & Observation Units

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| 🟡 | GET | `/observations` | `V_STOCK_PHENOTYPE` | `stock_phenotype2_id` → `observationDbId`, `stock_id` → `germplasmDbId`, `phenotype_id` → `observationVariableDbId`, `name` → variable name, `quan_value` → numeric value, `qual_value` → categorical value, `dataset` → study context |
| 🟡 | GET | `/observations/{observationDbId}` | `V_STOCK_PHENOTYPE` | `WHERE stock_phenotype2_id = ?` |
| 🟡 | GET | `/observations/table` | `V_STOCK_PHENOTYPE` | Pivot `stock_id` × `phenotype_id` → 2D matrix; rows = germplasm, columns = trait names |
| 🟡 | POST | `/search/observations` | `V_STOCK_PHENOTYPE` | Filter by `stock_id`, `phenotype_id`, `dataset` |
| 🟡 | GET | `/search/observations/{searchResultsDbId}` | — | Async poll of above |
| 🔴 | POST | `/observations` | `phenotype` | Read-only schema |
| 🔴 | PUT | `/observations` | `phenotype` | Read-only schema |
| 🔴 | POST | `/observations/table` | `phenotype` | Read-only schema |
| 🔴 | GET | `/observationunits` | — | No plot/plant observation unit layout in schema; `V_STOCK_PHENOTYPE` links stock directly to trait values without spatial layout |
| 🔴 | GET | `/observationunits/{observationUnitDbId}` | — | Not available |
| 🔴 | POST | `/search/observationunits` | — | Not available |
| 🔴 | GET | `/images` | — | No image metadata in schema |
| 🔴 | GET | `/events` | — | No field event records in schema |

---

## Module 4 — Core

| Status | Method | Endpoint | CHADO Source | Column Mapping |
|---|---|---|---|---|
| ✅ | GET | `/serverinfo` | Static config | Server name, org, contact, supported calls |
| 🟡 | GET | `/commoncropnames` | `V_ORGANISM` | `DISTINCT common_name` (e.g. `Rice`) |
| 🟡 | GET | `/studies` | `v_genotype_run` (loose mapping) | `genotype_run_id` → `studyDbId`, `dataset` → `studyName`, `date_performed` → `startDate`, `ds_description` → description; a genotyping run loosely maps to a study |
| 🟡 | GET | `/studies/{studyDbId}` | `v_genotype_run` | `WHERE genotype_run_id = ?` |
| 🟡 | GET | `/studies/{studyDbId}/germplasm` | `V_ALLSTOCK_BASICPROP` | `WHERE dataset = genotype_run.dataset` |
| 🟡 | GET | `/studies/{studyDbId}/observations` | `V_STOCK_PHENOTYPE` | `WHERE dataset = genotype_run.dataset` |
| 🔴 | GET | `/programs` | — | No institutional program metadata in schema |
| 🔴 | GET | `/trials` | — | No trial records in schema |
| 🔴 | GET | `/seasons` | — | No season/year data in schema |
| 🔴 | GET | `/locations` | — | No field location records in schema |
| 🔴 | GET | `/people` | — | No people/contacts in schema |
| 🔴 | GET | `/lists` | — | No named list functionality in schema |

---

## Implementation Summary

| Module | Total | ✅ Implemented | 🟡 Feasible | 🔴 Out of Scope |
|---|---|---|---|---|
| Genotyping | 50 | 15 | 22 | 13 |
| Germplasm | 47 | 6 | 14 | 27 |
| Phenotyping | 45 | 0 | 12 | 33 |
| Core | 46 | 1 | 5 | 40 |
| **Total** | **188** | **22** | **53** | **113** |

---

## Recommended Implementation Order

### Phase 1 — Low effort, high value (extend existing endpoints)

| Endpoint | Effort | CHADO Source |
|---|---|---|
| `GET /variants` + `GET /variants/{id}` | Very Low | `V_SNP_REFPOSINDEX_V2` — same query as `search/variants` |
| `GET /variantsets/{id}/variants` | Very Low | `V_SNP_REFPOSINDEX_V2` — scoped list |
| `GET /germplasm/{id}/callsets` | Very Low | `V_ALLSTOCK_BASICPROP WHERE stock_id = ?` |
| `POST /search/callsets` | Very Low | `V_ALLSTOCK_BASICPROP` — async pattern already exists |
| `GET /commoncropnames` | Very Low | `DISTINCT common_name` from `V_ORGANISM` |
| `POST /search/variantsets` | Low | `variantset` — async pattern already exists |

### Phase 2 — Medium effort, fills key gaps

| Endpoint | Effort | CHADO Source |
|---|---|---|
| `GET /referencesets` + `GET /references` | Low | Derive from `V_ORGANISM` + `DISTINCT chromosome` in `V_SNP_REFPOSINDEX_V2` |
| `GET /germplasm/{id}/mcpd` | Medium | `V_STOCK_PASSPORT` — EAV pivot into MCPD object |
| `GET /attributes` + `GET /attributevalues` | Low | `V_CV_PASSPORT` + `V_STOCK_PASSPORT` |
| `GET /callsets/{id}/calls` | Medium | `V_SNP_REFPOSINDEX_V2` + HDF5 streaming |
| `GET /markerpositions` | Low | Physical positions from `V_SNP_REFPOSINDEX_V2` |

### Phase 3 — Phenotyping layer (new domain)

| Endpoint | Effort | CHADO Source |
|---|---|---|
| `GET /ontologies` | Very Low | `cv` table |
| `GET /traits` + `GET /variables` | Low | `V_CV_PHENOTYPE` |
| `GET /observations` + `POST /search/observations` | Medium | `V_STOCK_PHENOTYPE` |
| `GET /observations/table` | Medium | `V_STOCK_PHENOTYPE` pivot |
| `GET /studies` (loose mapping) | Medium | `v_genotype_run` |

### Out of Scope (data not in CHADO)

The following BrAPI domains have no corresponding data in the SNPseek CHADO schema and would require new data sources or external integration to implement:

- **Breeding management:** Programs, Trials, Seasons, Locations, People, Lists
- **Crossing & Pedigree:** Crosses, PlannedCrosses, CrossingProjects, Pedigree, BreedingMethods
- **Seed inventory:** SeedLots, Transactions
- **Lab tracking:** Samples, Plates
- **Media:** Images
- **Field events:** Events
- **Observation units:** ObservationUnits (plot/plant spatial layout)
- **Measurement protocols:** Methods, Scales (no separate cvterm records)
- **Write operations:** All POST/PUT endpoints that modify data (schema is read-only)
