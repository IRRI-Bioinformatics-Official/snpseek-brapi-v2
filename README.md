# SNPseek BrAPI v2.1

A [BrAPI v2.1](https://brapi.org/specification) implementation for the [SNP-Seek](https://snp-seek.irri.org) rice genomics platform at IRRI. Exposes SNP variant metadata from PostgreSQL and genotype data from HDF5 files via a standard BrAPI REST API.

Built with **Java 17**, **Spring Boot 3.2**, and **Maven**. API documentation is served interactively via **Swagger UI**.

---

## Table of Contents

- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Configuration Profiles](#configuration-profiles)
- [Swagger UI](#swagger-ui)
- [API Reference](#api-reference)
- [Use Cases](#use-cases)
- [BrAPI v2.1 Full Use Case Reference](#brapi-v21-full-use-case-reference)
  - [Core](#core)
  - [Genotyping](#genotyping)
  - [Germplasm](#germplasm-1)
  - [Phenotyping](#phenotyping)
- [Development](#development)
- [Project Structure](#project-structure)
- [Links](#links)

---

## Architecture

```
┌─────────────────────────────────────────────────┐
│  Spring Boot  (port 8081)                       │
│  - BrAPI v2.1 REST endpoints                    │
│  - Swagger UI at /swagger-ui.html               │
│  - JWT validation via Keycloak                  │
│  - Reads variant metadata from PostgreSQL       │
│  - Reads genotype matrix from HDF5 files        │
└────────┬───────────────────────┬────────────────┘
         │                       │
    PostgreSQL              HDF5 files
  (existing SNPseek         (local directory)
    schema, read-only)
```

**Stack**

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.2.4 |
| Build tool | Maven |
| Database | PostgreSQL (existing SNP-Seek schema, read-only) |
| ORM | Spring Data JPA / Hibernate |
| Genotype storage | HDF5 via [jhdf](https://github.com/jamesmudd/jhdf) |
| Auth | Keycloak OAuth2 / JWT |
| API docs | Springdoc OpenAPI 2.3 (Swagger UI) |

---

## Prerequisites

- **Java 17** (Temurin or OpenJDK)
- **Maven 3.8+**
- Access to a PostgreSQL SNP-Seek database (direct or via SSH tunnel)
- HDF5 genotype files accessible on the host

---

## Quick Start

**1. Clone the repository**

```bash
git clone <repo-url>
cd snpseek-brapi-v2
```

**2. Set environment variables**

```bash
export DB_USERNAME=snpseek
export DB_PASSWORD=your_password
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/snpseek
export HDF5_DATA_DIR=/path/to/hdf5/files
export KEYCLOAK_ISSUER_URI=https://your-keycloak/auth/realms/snpseek_realm
```

**3. Run in development mode**

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

**4. Verify**

| Resource | URL |
|---|---|
| Swagger UI | http://localhost:8081/swagger-ui.html |
| OpenAPI spec (JSON) | http://localhost:8081/v3/api-docs |
| Server info | http://localhost:8081/brapi/v2/serverinfo |

---

## Configuration Profiles

The application uses three YAML files:

| File | Purpose |
|---|---|
| `application.yml` | Common settings shared across all profiles (JPA, server port, Swagger) |
| `application-dev.yml` | Development: verbose logging, lenient DB defaults, local HDF5 path |
| `application-prod.yml` | Production: strict env-var requirements, INFO-level logging |

### Activating a profile

**Via command line:**

```bash
# Development
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Production (all env vars must be set)
java -jar target/snpseek-brapi-2.1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

**Via environment variable:**

```bash
export SPRING_PROFILES_ACTIVE=prod
java -jar target/snpseek-brapi-2.1.0-SNAPSHOT.jar
```

### Environment variables

| Variable | Profile | Required | Description |
|---|---|---|---|
| `SPRING_DATASOURCE_URL` | dev (optional), prod | prod: yes | Full JDBC URL |
| `DB_USERNAME` | both | prod: yes | Database username |
| `DB_PASSWORD` | both | prod: yes | Database password |
| `KEYCLOAK_ISSUER_URI` | both | prod: yes | Keycloak realm issuer URI |
| `HDF5_DATA_DIR` | both | prod: yes | Directory containing `.h5` genotype files |

---

## Swagger UI

Navigate to **http://localhost:8081/swagger-ui.html** after starting the application.

To call protected endpoints directly from the UI:

1. Obtain a Keycloak JWT token (see [Get a token](#5-get-a-keycloak-token-for-scripting) below)
2. Click the **Authorize** button (lock icon) in the top-right of the Swagger UI
3. Enter: `Bearer <your-token>`
4. All subsequent requests in the UI will include the token

The raw OpenAPI 3.0 specification is available at `/v3/api-docs`.

---

## API Reference

### Public endpoints (no authentication required)

#### `GET /brapi/v2/serverinfo`

Returns server metadata and the list of supported BrAPI calls.

```bash
curl http://localhost:8081/brapi/v2/serverinfo
```

<details>
<summary>Example response</summary>

```json
{
  "metadata": { "pagination": null, "status": [], "datafiles": [] },
  "result": {
    "serverName": "SNP-Seek BrAPI Server",
    "organizationName": "International Rice Research Institute (IRRI)",
    "location": "Los Baños, Philippines",
    "contactEmail": "l.h.barboza@cgiar.org",
    "documentationURL": "https://snp-seek.irri.org",
    "organizationURL": "https://www.irri.org",
    "serverDescription": "SNP-Seek BrAPI v2.1 service for rice genomic variant data",
    "calls": [
      { "service": "serverinfo",      "methods": ["GET"],         "versions": ["2.1"] },
      { "service": "search/variants", "methods": ["POST", "GET"], "versions": ["2.1"] }
    ]
  }
}
```
</details>

---

### Protected endpoints (require `Authorization: Bearer <token>`)

All search endpoints require a valid Keycloak JWT with the `BRAPI_USER` realm role.

#### `POST /brapi/v2/search/variants`

Submit a variant search. Returns results directly (`200`) if the query is fast, or a `searchResultsDbId` (`202`) for async retrieval.

**Request body**

| Field | Type | Description |
|---|---|---|
| `variantDbIds` | `string[]` | Filter by specific variant IDs (`snp_feature_id`) |
| `variantSetDbIds` | `string[]` | Filter by variant set ID |
| `referenceNames` | `string[]` | Filter by chromosome number (e.g. `["1", "2"]`) |
| `start` | `integer` | Start position (inclusive) |
| `end` | `integer` | End position (exclusive) |
| `page` | `integer` | 0-based page number (default: `0`) |
| `pageSize` | `integer` | Results per page (default: `1000`) |

All fields are optional. Omitting all fields returns the first page of all variants.

```bash
curl -X POST http://localhost:8081/brapi/v2/search/variants \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "variantSetDbIds": ["1"],
    "referenceNames": ["1"],
    "start": 1000,
    "end": 500000,
    "pageSize": 100
  }'
```

<details>
<summary>Example 200 response</summary>

```json
{
  "metadata": {
    "pagination": {
      "currentPage": 0,
      "pageSize": 100,
      "totalCount": 4821,
      "totalPages": 49
    },
    "status": [],
    "datafiles": []
  },
  "result": {
    "data": [
      {
        "variantDbId": "10042",
        "referenceName": "1",
        "start": 1024,
        "end": 1025,
        "referenceBases": "A",
        "alternateBases": ["T"],
        "variantSetDbIds": ["1"]
      }
    ]
  }
}
```
</details>

<details>
<summary>Example 202 response (async)</summary>

```json
{
  "metadata": { "pagination": null, "status": [], "datafiles": [] },
  "result": {
    "searchResultsDbId": "3f8a2b1c-4d5e-6f7a-8b9c-0d1e2f3a4b5c"
  }
}
```
</details>

---

#### `GET /brapi/v2/search/variants/{searchResultsDbId}`

Retrieve results of a previously submitted async search.

- `200` — results ready
- `202` — still processing (retry after a moment)
- `404` — unknown `searchResultsDbId`

```bash
curl http://localhost:8081/brapi/v2/search/variants/3f8a2b1c-4d5e-6f7a-8b9c-0d1e2f3a4b5c \
  -H "Authorization: Bearer $TOKEN"
```

---

## Use Cases

### 1. Find all variants on chromosome 3

```bash
curl -X POST http://localhost:8081/brapi/v2/search/variants \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{ "referenceNames": ["3"], "pageSize": 500 }'
```

### 2. Find variants in a genomic region

```bash
curl -X POST http://localhost:8081/brapi/v2/search/variants \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "referenceNames": ["1"],
    "start": 2700000,
    "end": 2800000
  }'
```

### 3. Look up specific variants by ID

```bash
curl -X POST http://localhost:8081/brapi/v2/search/variants \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{ "variantDbIds": ["10042", "10043", "10050"] }'
```

### 4. Paginate through a large result set

```bash
# Page 0
curl -X POST http://localhost:8081/brapi/v2/search/variants \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{ "variantSetDbIds": ["1"], "page": 0, "pageSize": 1000 }'

# Page 1
curl -X POST http://localhost:8081/brapi/v2/search/variants \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{ "variantSetDbIds": ["1"], "page": 1, "pageSize": 1000 }'
```

### 5. Get a Keycloak token (for scripting)

```bash
TOKEN=$(curl -s -X POST \
  https://brs-snpseek.duckdns.org/auth/realms/snpseek_realm/protocol/openid-connect/token \
  -d "grant_type=password" \
  -d "client_id=brapi-client" \
  -d "username=YOUR_USER" \
  -d "password=YOUR_PASS" \
  | jq -r '.access_token')
```

---

## BrAPI v2.1 Full Use Case Reference

BrAPI v2.1 is divided into four modules. Each endpoint below lists its purpose, available filters, what it returns, and its implementation status in this project.

**Legend**

| Symbol | Meaning |
|---|---|
| ✅ | Implemented in this project |
| ⬜ | Not yet implemented |

---

### Core

The Core module covers server discovery, breeding programs, studies, trials, locations, and shared infrastructure.

---

#### `GET /brapi/v2/serverinfo` ✅

Returns the server name, organization, contact details, and a list of all BrAPI endpoints the server supports (with HTTP methods and BrAPI versions). Used by clients to auto-discover what the server can do before making any data request.

**No filters.** Returns a single `ServerInfo` object.

---

#### `GET /brapi/v2/commoncropnames` ⬜

Returns the list of crop species the server holds data for (e.g. `Oryza sativa`). Clients use this as a first step to know which crops are available.

**No filters.** Returns a list of crop name strings.

---

#### `GET /brapi/v2/programs` ⬜
#### `GET /brapi/v2/programs/{programDbId}` ⬜
#### `POST /brapi/v2/search/programs` ⬜

A breeding **program** is the top-level organizational unit (e.g. "IRRI Rice Improvement"). Programs own trials, which own studies, which own observation units.

**Filters:** `programName`, `abbreviation`, `commonCropName`, `leadPersonDbId`, `externalReferenceId`.

Returns program name, abbreviation, lead person, objective, and crop.

---

#### `GET /brapi/v2/trials` ⬜
#### `GET /brapi/v2/trials/{trialDbId}` ⬜
#### `POST /brapi/v2/search/trials` ⬜

A **trial** groups one or more studies conducted under a common research objective (e.g. multi-environment yield trial). Trials are owned by a program.

**Filters:** `programDbId`, `locationDbId`, `commonCropName`, `active`, `sortBy`, `sortOrder`.

Returns trial name, program, start/end dates, publications, and list of associated study IDs.

---

#### `GET /brapi/v2/studies` ⬜
#### `GET /brapi/v2/studies/{studyDbId}` ⬜
#### `GET /brapi/v2/studies/{studyDbId}/germplasm` ⬜
#### `GET /brapi/v2/studies/{studyDbId}/observationunits` ⬜
#### `GET /brapi/v2/studies/{studyDbId}/observations` ⬜
#### `POST /brapi/v2/search/studies` ⬜

A **study** is a single experiment at one location in one season. Studies contain observation units (plots or plants) with phenotypic measurements.

**Filters:** `trialDbId`, `locationDbId`, `seasonDbId`, `germplasmDbId`, `observationVariableDbId`, `active`, `sortBy`.

Returns study name, location, season, trial, protocols, observation levels, and list of variables measured.

The sub-endpoints return germplasm entries enrolled in the study, observation units (plots/plants), and raw phenotype observations respectively.

---

#### `GET /brapi/v2/seasons` ⬜
#### `GET /brapi/v2/seasons/{seasonDbId}` ⬜

A **season** represents a growing period identified by year and season name (e.g. "Dry Season 2023"). Used to filter studies by time.

**Filters:** `year`, `seasonName`.

Returns year, season name, and description.

---

#### `GET /brapi/v2/locations` ⬜
#### `GET /brapi/v2/locations/{locationDbId}` ⬜
#### `POST /brapi/v2/search/locations` ⬜

A **location** is a named geographic site where field studies are conducted. Stores coordinates, country, and site details.

**Filters:** `locationType`, `countryCode`, `countryName`, `externalReferenceId`.

Returns location name, type (field, greenhouse, etc.), coordinates (lat/lon/altitude), country, institute, and abbreviation.

---

#### `GET /brapi/v2/people` ⬜
#### `GET /brapi/v2/people/{personDbId}` ⬜
#### `POST /brapi/v2/search/people` ⬜

A **person** is a researcher or contact associated with programs, studies, or trials.

**Filters:** `firstName`, `lastName`, `userID`, `externalReferenceId`.

Returns name, email, phone, affiliation, roles, and ORCID.

---

#### `GET /brapi/v2/lists` ⬜
#### `GET /brapi/v2/lists/{listDbId}` ⬜
#### `POST /brapi/v2/lists` ⬜
#### `PUT /brapi/v2/lists/{listDbId}` ⬜
#### `POST /brapi/v2/lists/{listDbId}/items` ⬜
#### `POST /brapi/v2/search/lists` ⬜

A **list** is a named, typed collection of IDs (germplasm, variants, studies, etc.). Useful for saving and sharing a curated set of items between tools or sessions.

**Filters:** `listType`, `listName`, `listOwnerName`, `dateCreated`.

Returns list name, type, owner, date, description, and the array of item IDs it contains.

---

### Genotyping

The Genotyping module covers the full stack from reference genome assemblies down to individual genotype calls.

---

#### `GET /brapi/v2/referencesets` ⬜
#### `GET /brapi/v2/referencesets/{referenceSetDbId}` ⬜
#### `POST /brapi/v2/search/referencesets` ⬜

A **reference set** is a genome assembly (e.g. IRGSP-1.0, Nipponbare Os-Nipponbare-Reference-IRGSP-1.0). All variant positions are anchored to a reference set.

**Filters:** `assemblyPUI`, `md5checksum`, `organism`, `commonCropName`, `externalReferenceId`.

Returns assembly name, organism, source URL, MD5, and list of reference sequence IDs it contains.

---

#### `GET /brapi/v2/references` ⬜
#### `GET /brapi/v2/references/{referenceDbId}` ⬜
#### `POST /brapi/v2/search/references` ⬜

A **reference** is a single chromosome or contig within a reference set (e.g. chromosome 1 of IRGSP-1.0).

**Filters:** `referenceSetDbId`, `md5checksum`, `isDerived`, `minLength`, `maxLength`, `externalReferenceId`.

Returns chromosome/contig name, length in bases, MD5 of sequence, source accession, and parent reference set.

---

#### `GET /brapi/v2/variantsets` ✅
#### `GET /brapi/v2/variantsets/{variantSetDbId}` ✅
#### `GET /brapi/v2/variantsets/{variantSetDbId}/callsets` ✅
#### `GET /brapi/v2/variantsets/{variantSetDbId}/variants` ⬜

A **variant set** is a defined collection of variants from a single genotyping experiment or chip panel (e.g. the 3K RGP 700K chip). It links variants (the SNP sites) to call sets (the samples genotyped).

**Filters (list):** `variantSetDbId`, `studyDbId`, `studyName`, `callSetDbId`.

Returns variant set name, study, reference set, available formats, variant count, and call set count.

`/callsets` returns the paginated list of samples (call sets) genotyped in this variant set.

`/variants` ⬜ returns the paginated list of SNP sites belonging to this variant set — equivalent to `GET /variants?variantSetDbId=X` but scoped to the set.

---

#### `GET /brapi/v2/variants` ⬜
#### `GET /brapi/v2/variants/{variantDbId}` ⬜
#### `POST /brapi/v2/search/variants` ✅
#### `GET /brapi/v2/search/variants/{searchResultsDbId}` ✅

A **variant** is a single polymorphic site (SNP, indel, etc.) with a position on the reference genome.

`GET /variants` ⬜ returns all variants in a paginated list with query-parameter filters.

`GET /variants/{variantDbId}` ⬜ returns a single variant by its ID including reference/alternate bases, position, filters, and quality score.

`POST /search/variants` ✅ submits an async or synchronous search. Supported filters:

| Filter | Description |
|---|---|
| `variantDbIds` | Exact variant IDs |
| `variantSetDbIds` | All variants in a set |
| `referenceNames` | Chromosome numbers |
| `start` | Start position (inclusive) |
| `end` | End position (exclusive) |
| `page` / `pageSize` | Pagination |

Returns variant ID, chromosome, position, reference bases, alternate bases, and variant set membership.

---

#### `GET /brapi/v2/callsets` ✅
#### `GET /brapi/v2/callsets/{callSetDbId}` ✅
#### `GET /brapi/v2/callsets/{callSetDbId}/calls` ⬜
#### `POST /brapi/v2/search/callsets` ⬜
#### `GET /brapi/v2/search/callsets/{searchResultsDbId}` ⬜

A **call set** is a single sample's complete set of genotype calls across all variants in a variant set. In SNPseek terms, one call set = one accession × one chip panel.

`GET /callsets` ✅ lists call sets with optional filters: `variantSetDbId`, `germplasmDbId`, `callSetDbId`.

`GET /callsets/{callSetDbId}` ✅ returns a single call set: sample name, germplasm reference, variant set, creation date, and available formats.

`GET /callsets/{callSetDbId}/calls` ⬜ streams all individual genotype calls (one per variant) for this call set. High-volume endpoint; typically paginated or chunked.

`POST /search/callsets` ⬜ async search with filters: `callSetDbIds`, `germplasmDbIds`, `variantSetDbIds`, `sampleDbIds`.

---

#### `GET /brapi/v2/calls` ⬜
#### `POST /brapi/v2/search/calls` ⬜
#### `GET /brapi/v2/search/calls/{searchResultsDbId}` ⬜

A **call** is the genotype value for one sample at one variant (e.g. `0/1`, `A/T`). This is the finest-grained genotyping endpoint.

**Filters:** `callSetDbId`, `variantDbId`, `variantSetDbId`, `expandHomozygotes`, `unknownString`, `sepPhased`, `sepUnphased`.

Returns an array of `{callSetDbId, variantDbId, genotype, phaseSet}` tuples. Output can be very large; always paginate.

---

#### `GET /brapi/v2/allelematrix` ✅
#### `POST /brapi/v2/search/allelematrix` ✅
#### `GET /brapi/v2/search/allelematrix/{searchResultsDbId}` ✅

The **allele matrix** is a 2D genotype table: rows = variants, columns = call sets (samples). It is the most efficient way to retrieve bulk genotype data for a region or set of samples.

Supported filters:

| Filter | Description |
|---|---|
| `variantSetDbIds` | The chip/panel to query |
| `variantDbIds` | Limit to specific variants |
| `callSetDbIds` | Limit to specific samples |
| `referenceName` | Chromosome |
| `start` / `end` | Genomic range |
| `dimensionVariantPage` / `dimensionVariantPageSize` | Paginate the variant (row) axis |
| `dimensionCallSetPage` / `dimensionCallSetPageSize` | Paginate the call set (column) axis |

Returns a `dataMatrices` array containing the genotype matrix (`GT` format), plus dimension pagination metadata for both axes. Data is sourced from HDF5 files.

---

#### `GET /brapi/v2/samples` ⬜
#### `GET /brapi/v2/samples/{sampleDbId}` ⬜
#### `POST /brapi/v2/samples` ⬜
#### `PUT /brapi/v2/samples/{sampleDbId}` ⬜
#### `POST /brapi/v2/search/samples` ⬜

A **sample** is a physical biological specimen collected from a germplasm accession (tissue type, collection date, storage method). Samples are linked to call sets when sequencing or genotyping is performed.

**Filters:** `germplasmDbId`, `observationUnitDbId`, `plateDbId`, `sampleDbId`, `studyDbId`, `externalReferenceId`.

Returns sample name, germplasm, observation unit, plate, well, tissue type, collection date, and storage.

---

### Germplasm

The Germplasm module covers accession identity, passport data, pedigree, seed inventory, and attribute values.

---

#### `GET /brapi/v2/germplasm` ✅
#### `GET /brapi/v2/germplasm/{germplasmDbId}` ✅
#### `POST /brapi/v2/search/germplasm` ✅
#### `GET /brapi/v2/search/germplasm/{searchResultsDbId}` ✅

A **germplasm** record identifies a unique plant accession — a rice variety, breeding line, or wild relative.

`GET /germplasm` ✅ and `POST /search/germplasm` ✅ support the following filters:

| Filter | Description |
|---|---|
| `germplasmDbIds` | Exact accession IDs (`stock_id`) |
| `germplasmNames` | Accession names |
| `accessionNumbers` | Genebank accession numbers |
| `countries` | Country of origin |
| `subpopulations` | Population group (e.g. indica, japonica) |
| `variantSetDbIds` | Accessions belonging to a chip panel |

Returns accession name, synonyms, accession number, country of origin, subpopulation, genus, species, and type of germplasm storage.

---

#### `GET /brapi/v2/germplasm/{germplasmDbId}/mcpd` ⬜

Returns the full **Multi-Crop Passport Descriptor (MCPD)** data for an accession. MCPD is the FAO/IPGRI international standard for genebank passport data.

Returns fields such as: `INSTCODE`, `ACCENUMB`, `COLLDATE`, `ORIGCTY`, `COLLSITE`, `LATITUDE`, `LONGITUDE`, `ELEVATION`, `SAMPSTAT` (biological status), `ANCEST` (ancestry), `DUPLSITE` (duplicate held at).

---

#### `GET /brapi/v2/germplasm/{germplasmDbId}/pedigree` ⬜
#### `GET /brapi/v2/pedigree` ⬜
#### `POST /brapi/v2/search/pedigree` ⬜

Returns the **pedigree** of an accession: parent 1, parent 2, cross type, and generation. The `GET /pedigree` endpoint can return a full multi-generation tree.

**Filters:** `germplasmDbId`, `programDbId`, `trialDbId`, `studyDbId`, `includeParents`, `includeProgeny`, `includeFullTree`.

---

#### `GET /brapi/v2/germplasm/{germplasmDbId}/progeny` ⬜

Returns the list of direct offspring (progeny) of an accession, with cross type and the other parent.

---

#### `GET /brapi/v2/germplasm/{germplasmDbId}/attributes` ⬜

Returns the attribute values assigned to a specific germplasm accession (see `/attributevalues`).

---

#### `GET /brapi/v2/germplasm/{germplasmDbId}/calls` ⬜

Returns all genotype calls recorded for an accession, across all variant sets. Equivalent to fetching every call set belonging to this germplasm and returning their calls.

---

#### `GET /brapi/v2/germplasm/{germplasmDbId}/callsets` ⬜

Returns all call sets (sample × chip combinations) associated with a germplasm accession.

---

#### `GET /brapi/v2/attributes` ⬜
#### `GET /brapi/v2/attributes/{attributeDbId}` ⬜
#### `POST /brapi/v2/search/attributes` ⬜

A germplasm **attribute** is a defined descriptor beyond standard passport fields — e.g. grain color, drought tolerance score, flowering date range.

**Filters:** `attributeCategory`, `attributeDbId`, `attributeName`, `germplasmDbId`, `externalReferenceId`.

Returns attribute name, category, method, scale, and associated ontology term.

---

#### `GET /brapi/v2/attributevalues` ⬜
#### `GET /brapi/v2/attributevalues/{attributeValueDbId}` ⬜
#### `POST /brapi/v2/search/attributevalues` ⬜

Returns the actual **values** of germplasm attributes per accession (the pairing of accession + attribute + measured value).

**Filters:** `attributeDbId`, `attributeName`, `germplasmDbId`, `externalReferenceId`.

Returns germplasm reference, attribute reference, determined date, and the value (string or numeric).

---

#### `GET /brapi/v2/crosses` ⬜
#### `POST /brapi/v2/crosses` ⬜
#### `PUT /brapi/v2/crosses` ⬜

A **cross** records an actual hybridization event between two parent accessions. Used to track breeding history.

Returns cross type (biparental, open pollinated, etc.), parent 1, parent 2, crossing project, and pollinationDate.

---

#### `GET /brapi/v2/plannedcrosses` ⬜
#### `POST /brapi/v2/plannedcrosses` ⬜

A **planned cross** is a crossing scheduled for a future season — the intentions before the actual cross is made.

---

#### `GET /brapi/v2/crossingprojects` ⬜
#### `GET /brapi/v2/crossingprojects/{crossingProjectDbId}` ⬜

A **crossing project** groups related planned and actual crosses under a named breeding objective.

---

#### `GET /brapi/v2/seedlots` ⬜
#### `GET /brapi/v2/seedlots/{seedLotDbId}` ⬜
#### `POST /brapi/v2/seedlots` ⬜
#### `GET /brapi/v2/seedlots/transactions` ⬜
#### `POST /brapi/v2/seedlots/transactions` ⬜
#### `POST /brapi/v2/search/seedlots` ⬜

A **seed lot** tracks the physical inventory of seeds for an accession: quantity, storage location, and lot-level metadata. **Transactions** record movements (deposits and withdrawals) between lots.

**Filters:** `germplasmDbId`, `locationDbId`, `programDbId`, `seedLotDbId`.

Returns lot name, germplasm, location, amount, units, last updated, and transaction history.

---

### Phenotyping

The Phenotyping module covers trait definitions, measurement protocols, field observations, and images. Most endpoints are not relevant to a pure genotyping server like SNPseek but are part of the full BrAPI v2.1 spec.

---

#### `GET /brapi/v2/variables` ⬜
#### `GET /brapi/v2/variables/{observationVariableDbId}` ⬜
#### `POST /brapi/v2/search/variables` ⬜

An **observation variable** is the complete definition of a measured trait: the combination of a Trait + Method + Scale. E.g. "Plant Height measured with a ruler in cm".

**Filters:** `programDbId`, `trialDbId`, `studyDbId`, `ontologyDbId`, `methodDbId`, `scaleDbId`, `traitDbId`, `externalReferenceId`.

Returns variable name, abbreviation, ontology reference, trait, method, scale, and default value.

---

#### `GET /brapi/v2/traits` ⬜
#### `GET /brapi/v2/traits/{traitDbId}` ⬜

A **trait** is a biological characteristic being observed (e.g. "Plant Height", "Days to Heading"). Traits are independent of measurement method or scale.

Returns trait name, class, description, synonyms, ontology term, and associated variables.

---

#### `GET /brapi/v2/methods` ⬜
#### `GET /brapi/v2/methods/{methodDbId}` ⬜

A **method** describes how a trait is measured (e.g. "Visual scoring 1–9", "Digital caliper measurement"). One trait can have multiple methods.

Returns method name, class, description, formula, bibliographic reference, and ontology term.

---

#### `GET /brapi/v2/scales` ⬜
#### `GET /brapi/v2/scales/{scaleDbId}` ⬜

A **scale** defines the data type and range of values for a measurement (e.g. numeric 0–100, categorical 1/3/5/7/9, date, text).

Returns scale name, data type, decimal places, valid value range, and categorical categories with labels.

---

#### `GET /brapi/v2/ontologies` ⬜

Returns the list of ontologies used by the server's observation variables (e.g. CO_321 for Rice, TO for Trait Ontology, PATO).

Returns ontology name, version, description, author, and link.

---

#### `GET /brapi/v2/observations` ⬜
#### `GET /brapi/v2/observations/{observationDbId}` ⬜
#### `POST /brapi/v2/observations` ⬜
#### `PUT /brapi/v2/observations` ⬜
#### `GET /brapi/v2/observations/table` ⬜
#### `POST /brapi/v2/observations/table` ⬜
#### `POST /brapi/v2/search/observations` ⬜

An **observation** is a single recorded measurement: one observation variable at one observation unit on one date.

**Filters:** `germplasmDbId`, `observationVariableDbId`, `studyDbId`, `locationDbId`, `observationUnitDbId`, `observationTimeStampRangeStart/End`, `seasonDbId`.

The `/table` variant returns observations as a 2D matrix (rows = observation units, columns = variables) for bulk export or upload.

Returns germplasm, observation unit, variable, value, timestamp, collector, and study.

---

#### `GET /brapi/v2/observationunits` ⬜
#### `GET /brapi/v2/observationunits/{observationUnitDbId}` ⬜
#### `POST /brapi/v2/observationunits` ⬜
#### `PUT /brapi/v2/observationunits` ⬜
#### `POST /brapi/v2/observationunits/table` ⬜
#### `POST /brapi/v2/search/observationunits` ⬜

An **observation unit** is the physical entity being measured — a plot, plant, or sub-sample in a field study. It links a germplasm to a study position.

**Filters:** `germplasmDbId`, `studyDbId`, `trialDbId`, `locationDbId`, `programDbId`, `observationUnitLevelName` (plot/plant/subplot).

Returns germplasm, study, location, position (row/column/block/rep), and observation unit level hierarchy.

---

#### `GET /brapi/v2/images` ⬜
#### `GET /brapi/v2/images/{imageDbId}` ⬜
#### `POST /brapi/v2/images` ⬜
#### `PUT /brapi/v2/images/{imageDbId}` ⬜
#### `PUT /brapi/v2/images/{imageDbId}/imagecontent` ⬜
#### `POST /brapi/v2/search/images` ⬜

An **image** is a photo or scan associated with an observation unit or germplasm (e.g. canopy photo, leaf scan).

**Filters:** `imageDbId`, `observationUnitDbId`, `descriptiveOntologyTerm`, `mimeType`, `studyDbId`.

Metadata returns image name, description, timestamp, coordinates, MIME type, and linked observation unit. The `imagecontent` PUT uploads the binary image data.

---

#### `GET /brapi/v2/events` ⬜
#### `POST /brapi/v2/events` ⬜

An **event** records a field operation applied to observation units in a study: irrigation, fertilizer application, pest treatment, harvest, etc.

**Filters:** `studyDbId`.

Returns event type, date, description, parameters applied, and the set of observation units affected.

---

## Development

### Running locally (dev profile)

The `dev` profile ships with sensible defaults so you can start without setting every environment variable. Override only what differs in your environment.

**1. Configure the database connection**

Edit `src/main/resources/application-dev.yml` or export environment variables:

```bash
# Required only if your local DB differs from the defaults below
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/snpseekv3
export DB_USERNAME=snpseek
export DB_PASSWORD=snpseek
```

Default values when the variables are not set:

| Variable | Default |
|---|---|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5433/snpseekv3` |
| `DB_USERNAME` | `snpseek` |
| `DB_PASSWORD` | `snpseek` |
| `HDF5_DATA_DIR` | `/home/lhbarboza/data` |
| `KEYCLOAK_ISSUER_URI` | `https://brs-snpseek.duckdns.org/auth/realms/snpseek_realm` |

> The app will start even if the database is unreachable. Connection errors surface only when an endpoint that queries the DB is called.

**2. Run**

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

**3. Verify**

| Resource | URL |
|---|---|
| Swagger UI | http://localhost:8081/swagger-ui.html |
| OpenAPI spec | http://localhost:8081/v3/api-docs |
| Server info | http://localhost:8081/brapi/v2/serverinfo |

---

### Running tests

```bash
mvn test
```

### Building a production JAR

```bash
mvn clean package -DskipTests
java -jar target/snpseek-brapi-2.1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

### Deploying to a server

Copy the JAR and start it with the `prod` profile and required environment variables:

```bash
export SPRING_PROFILES_ACTIVE=prod
export SPRING_DATASOURCE_URL=jdbc:postgresql://db-host:5432/snpseek
export DB_USERNAME=snpseek
export DB_PASSWORD=secret
export KEYCLOAK_ISSUER_URI=https://auth.example.com/auth/realms/snpseek_realm
export HDF5_DATA_DIR=/data/hdf5

java -Xms256m -Xmx1g -jar snpseek-brapi-2.1.0-SNAPSHOT.jar
```

To run as a background service, create a `systemd` unit file or use a process manager such as `supervisord`.

---

## Project Structure

```
snpseek-brapi-v2/
├── pom.xml                           # Maven build configuration
├── src/
│   ├── main/
│   │   ├── java/org/irri/snpseek/brapi/
│   │   │   ├── BrapiApplication.java       # Spring Boot entry point
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java      # Swagger / OpenAPI bean
│   │   │   ├── controller/
│   │   │   │   ├── ServerInfoController.java
│   │   │   │   └── VariantSearchController.java
│   │   │   ├── domain/                     # JPA entities (read-only)
│   │   │   │   ├── SnpMetadata.java
│   │   │   │   ├── VariantSet.java
│   │   │   │   ├── Platform.java
│   │   │   │   └── GenotypeRun.java
│   │   │   ├── dto/                        # BrAPI request/response records
│   │   │   │   ├── VariantSearchRequest.java
│   │   │   │   ├── Variant.java
│   │   │   │   ├── BrapiResponse.java
│   │   │   │   ├── BrapiListResponse.java
│   │   │   │   ├── BrapiSearchResponse.java
│   │   │   │   ├── ServerInfo.java
│   │   │   │   ├── BrapiMetadata.java
│   │   │   │   └── BrapiPagination.java
│   │   │   ├── repository/
│   │   │   │   ├── SnpMetadataRepository.java
│   │   │   │   └── VariantSetRepository.java
│   │   │   ├── security/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── KeycloakRoleConverter.java
│   │   │   └── service/
│   │   │       ├── VariantSearchService.java
│   │   │       ├── GenotypeStorageService.java
│   │   │       └── impl/
│   │   │           └── Hdf5GenotypeStorageService.java
│   │   └── resources/
│   │       ├── application.yml             # Common / base configuration
│   │       ├── application-dev.yml         # Development profile
│   │       └── application-prod.yml        # Production profile
│   └── test/
│       └── java/org/irri/snpseek/brapi/
│           └── service/
│               └── VariantSearchServiceTest.java
├── docs/
│   └── schema/
│       └── db_spec.md                      # Database schema reference
└── .github/
    └── workflows/
        └── ci.yml                          # CI: build and test on every push
```

---

## Links

| Resource | URL |
|---|---|
| BrAPI v2.1 specification | https://brapi.org/specification |
| BrAPI endpoint documentation | https://app.swaggerhub.com/apis/PlantBreedingAPI/BrAPI-Genotyping/2.1 |
| SNP-Seek platform | https://snp-seek.irri.org |
| IRRI | https://www.irri.org |
| jhdf (HDF5 library) | https://github.com/jamesmudd/jhdf |
| Springdoc OpenAPI | https://springdoc.org |
| Keycloak | https://www.keycloak.org |
