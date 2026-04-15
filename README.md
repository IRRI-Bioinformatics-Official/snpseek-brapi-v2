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

## Development

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
