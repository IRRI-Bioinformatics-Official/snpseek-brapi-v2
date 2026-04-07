# SNP-Seek Database Specification: Variant Sets and Genotype Runs

This document describes the database schema related to variant sets, platforms, and genotype runs. This schema is used to manage the storage and access of genotype data, particularly HDF5-based storage.

## 1. Variant Set Table (`variantset`)

The `variantset` table defines a collection of variants, typically grouped by organism and variant type.

| Column | Data Type | Nullable | Description |
| :--- | :--- | :--- | :--- |
| `variantset_id` | `integer` | NO | Primary Key. |
| `name` | `character varying` | YES | Name of the variant set. |
| `description` | `text` | YES | Detailed description of the variant set. |
| `variant_type_id` | `integer` | NO | Foreign Key to `cvterm.cvterm_id` (e.g., SNP, InDel). |
| `organism_id` | `integer` | YES | Foreign Key to `organism.organism_id`. |

---

## 2. Platform Table (`platform`)

The `platform` table describes the genotyping platform or technology used for a set of genotype runs.

| Column | Data Type | Nullable | Description |
| :--- | :--- | :--- | :--- |
| `platform_id` | `integer` | NO | Primary Key. |
| `variantset_id` | `integer` | YES | Foreign Key to `variantset.variantset_id`. |
| `db_id` | `integer` | YES | Foreign Key to `db.db_id`. |
| `genotyping_method_id` | `integer` | YES | Foreign Key to `cvterm.cvterm_id`. |

---

## 3. Genotype Run Table (`genotype_run`)

The `genotype_run` table tracks specific genotyping experiments or datasets. This table links the logical platform to the physical data storage.

| Column | Data Type | Nullable | Description |
| :--- | :--- | :--- | :--- |
| `genotype_run_id` | `integer` | NO | Primary Key. |
| `platform_id` | `integer` | YES | ID of the platform (implicitly links to `platform.platform_id`). |
| `date_performed` | `date` | YES | Date when the genotyping was performed. |
| `data_location` | `character varying` | YES | Path to the HDF5 data file. |
| `visible` | `boolean` | YES | Flag indicating if the run is visible to users. |

### HDF5 Data Storage (`data_location`)

The `data_location` column stores the file names or relative paths of the HDF5 files that contain the actual genotype data (e.g., `SNPuni_geno_NB_3k.h5`, `filtered_3kv1.h5`). These files are typically accessed by the `GenotypeStorageService` to retrieve genotype calls for specific samples and variants.

---

## 4. Supporting Tables

- **`organism`**: Stores organism information (genus, species, abbreviation).
- **`cvterm`**: Controlled Vocabulary terms for variant types and genotyping methods.
- **`db`**: External database references.

## 5. Relationships Diagram (Logical)

- `variantset` (1) --- (N) `platform`
- `platform` (1) --- (N) `genotype_run`
- `cvterm` (1) --- (N) `variantset` (via `variant_type_id`)
- `organism` (1) --- (N) `variantset` (via `organism_id`)
