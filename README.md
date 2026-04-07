# SNPseek BrAPI v2.1

A [BrAPI v2.1](https://brapi.org/specification) implementation for the [SNP-Seek](https://snp-seek.irri.org) rice genomics platform at IRRI. Exposes SNP variant metadata from PostgreSQL and genotype data from HDF5 files via a standard BrAPI REST API.

---

## Table of Contents

- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Configuration](#configuration)
- [API Reference](#api-reference)
- [Use Cases](#use-cases)
- [Web Portal](#web-portal)
- [Development](#development)
- [Links](#links)

---

## Architecture

```
┌─────────────────────────────────────────────────┐
│  snp-ui  (React + nginx)  :3000                 │
│  - Variant search UI                            │
│  - Proxies /brapi/* → snp-api                   │
└────────────────────┬────────────────────────────┘
                     │ internal Docker network
┌────────────────────▼────────────────────────────┐
│  snp-api  (Spring Boot)  :8081                  │
│  - BrAPI v2.1 REST endpoints                    │
│  - JWT validation via Keycloak                  │
│  - Reads variant metadata from PostgreSQL       │
│  - Reads genotype matrix from HDF5 files        │
└────────┬───────────────────────┬────────────────┘
         │                       │
    PostgreSQL              HDF5 files
  (remote / tunnel)        (/data mount)
```

**Stack**

| Layer | Technology |
|---|---|
| API server | Spring Boot 3.2.4, Java 17 |
| Database | PostgreSQL (existing SNP-Seek schema, read-only) |
| Genotype storage | HDF5 via [jhdf](https://github.com/jamesmudd/jhdf) |
| Auth | Keycloak OAuth2 / JWT |
| Frontend | React 18, Vite 6, Tailwind CSS 4, AG-Grid |
| Deployment | Docker, Docker Compose |

---

## Prerequisites

- Docker and Docker Compose
- Access to a remote PostgreSQL SNP-Seek database (direct or via SSH tunnel)
- HDF5 genotype files accessible on the host at `/home/lhbarboza/data` (or configure a different path)

---

## Quick Start

**1. Clone and configure**

```bash
git clone <repo-url>
cd snpseek-brapi-v2/infrastructure/docker
cp .env.example .env
```

Edit `.env` with your database credentials:

```dotenv
DB_HOST=host.docker.internal   # use this if connecting via SSH tunnel on localhost
DB_PORT=5433                   # tunnel port (default Postgres is 5432)
DB_NAME=snpseekv3
DB_USER=iricadmin
DB_PASSWORD=your_password
```

> **SSH tunnel tip:** If your Postgres is behind a jump host, bind the tunnel to all interfaces so Docker can reach it:
> ```bash
> ssh -L 0.0.0.0:5433:db-host:5432 user@jump-host -N &
> ```
> Then use `DB_HOST=host.docker.internal` in `.env`.

**2. Start the stack**

```bash
docker compose up --build -d
```

**3. Verify**

```bash
# Public endpoint — no auth required
curl http://localhost:8081/brapi/v2/serverinfo
```

| Service | URL |
|---|---|
| BrAPI API | http://localhost:8081 |
| Web portal | http://localhost:3000 |

---

## Configuration

All configuration is injected via environment variables. See `infrastructure/docker/.env.example` for the full list.

| Variable | Description | Default |
|---|---|---|
| `DB_HOST` | PostgreSQL hostname or IP | — |
| `DB_PORT` | PostgreSQL port | `5432` |
| `DB_NAME` | Database name | `snpseek` |
| `DB_USER` | Database user | — |
| `DB_PASSWORD` | Database password | — |

Additional settings in `apps/api-server/src/main/resources/application.yml`:

| Property | Description | Default |
|---|---|---|
| `brapi.hdf5.data-dir` | Host path where HDF5 files are mounted | `/data` |
| `brapi.hdf5.snp-chunk-size` | Rows per HDF5 hyperslab read | `10000` |
| `SERVER_PORT` | API server port inside the container | `8081` |

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

All genotyping and search endpoints require a valid Keycloak JWT with the `BRAPI_USER` realm role.

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

## Web Portal

Open **http://localhost:3000** in your browser.

### Variant Search tab

1. Paste your Keycloak Bearer token in the token field
2. Fill in optional filters (variant set, chromosome, position range, page size)
3. Click **Search Variants**
4. Results appear in the AG-Grid table — sortable, filterable, and exportable to CSV

### Server Info tab

Displays server metadata and the list of supported BrAPI calls fetched live from `/brapi/v2/serverinfo`. No authentication required.

---

## Development

### Running the API locally (without Docker)

```bash
cd apps/api-server
export DB_HOST=localhost
export DB_PORT=5433
export DB_USER=iricadmin
export DB_PASSWORD=your_password
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/snpseekv3
export HDF5_DATA_DIR=/home/lhbarboza/data
mvn spring-boot:run
```

### Running the frontend locally

```bash
cd apps/web-portal
npm install
npm run dev        # starts at http://localhost:5173, proxies /brapi/* to localhost:8081
```

### Running tests

```bash
cd apps/api-server
mvn test
```

### Project structure

```
snpseek-brapi-v2/
├── apps/
│   ├── api-server/               # Spring Boot BrAPI server
│   │   ├── src/main/java/.../
│   │   │   ├── controller/       # REST controllers
│   │   │   ├── domain/           # JPA entities (read-only)
│   │   │   ├── dto/              # BrAPI response/request records
│   │   │   ├── repository/       # Spring Data repositories
│   │   │   ├── security/         # Keycloak JWT config
│   │   │   └── service/          # Search logic, HDF5 reader
│   │   └── src/main/resources/
│   │       └── application.yml
│   └── web-portal/               # React SPA
│       └── src/
│           ├── App.jsx
│           └── components/
├── docs/
│   └── schema/db_spec.md         # Database schema reference
└── infrastructure/
    └── docker/
        ├── docker-compose.yml
        └── .env.example
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
| Keycloak | https://www.keycloak.org |
