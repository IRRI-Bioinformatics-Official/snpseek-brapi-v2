import { useState, useCallback, useRef } from 'react'
import { AgGridReact } from 'ag-grid-react'

/**
 * Columns reflect the genotype_run table columns surfaced via BrAPI,
 * plus the parent platform/variantset identifiers for navigation.
 * Adjust field names to match your BrAPI /genotyperuns response shape.
 */
const COL_DEFS = [
  {
    field: 'genotypeRunDbId',
    headerName: 'Run ID',
    width: 100,
    filter: 'agNumberColumnFilter',
    sort: 'asc',
  },
  {
    field: 'variantSetDbId',
    headerName: 'Variant Set',
    width: 120,
    filter: 'agNumberColumnFilter',
  },
  {
    field: 'platformDbId',
    headerName: 'Platform',
    width: 110,
    filter: 'agNumberColumnFilter',
  },
  {
    field: 'datePerformed',
    headerName: 'Date',
    width: 130,
    filter: 'agDateColumnFilter',
  },
  {
    field: 'dataLocation',
    headerName: 'HDF5 File',
    flex: 2,
    filter: 'agTextColumnFilter',
  },
  {
    field: 'visible',
    headerName: 'Visible',
    width: 90,
    cellRenderer: p => (
      <span className={p.value ? 'text-green-600 font-medium' : 'text-gray-400'}>
        {p.value ? 'Yes' : 'No'}
      </span>
    ),
  },
]

const DEFAULT_COL_DEF = {
  resizable: true,
  sortable: true,
  minWidth: 80,
}

export default function GenotypeRunGrid() {
  const [rowData, setRowData]       = useState([])
  const [loading, setLoading]       = useState(false)
  const [error, setError]           = useState(null)
  const [nextPageToken, setNextPageToken] = useState(null)
  const gridRef = useRef(null)

  const load = useCallback(async (reset = false) => {
    setLoading(true)
    setError(null)
    const token = reset ? null : nextPageToken

    try {
      const params = new URLSearchParams({ pageSize: 200 })
      if (token) params.set('pageToken', token)

      const res = await fetch(`/brapi/v2/genotyperuns?${params}`, {
        headers: { Accept: 'application/json' },
      })
      if (!res.ok) throw new Error(`${res.status} ${res.statusText}`)

      const json    = await res.json()
      const data    = json?.result?.data              ?? []
      const nextTok = json?.metadata?.pagination?.nextPageToken ?? null

      setRowData(prev => reset ? data : [...prev, ...data])
      setNextPageToken(nextTok)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }, [nextPageToken])

  const exportCsv = useCallback(() => {
    gridRef.current?.api.exportDataAsCsv({ fileName: 'genotyperuns.csv' })
  }, [])

  return (
    <div className="flex flex-col h-full gap-3">
      {/* ── Toolbar ──────────────────────────────────────────── */}
      <div className="flex items-center gap-2 flex-wrap">
        <button
          onClick={() => load(true)}
          disabled={loading}
          className="px-4 py-1.5 bg-green-700 hover:bg-green-800 text-white text-sm font-medium rounded disabled:opacity-50 transition-colors"
        >
          {loading ? 'Loading…' : 'Load Genotype Runs'}
        </button>

        {nextPageToken && !loading && (
          <button
            onClick={() => load(false)}
            className="px-4 py-1.5 bg-gray-100 hover:bg-gray-200 text-gray-700 text-sm rounded transition-colors"
          >
            Load more
          </button>
        )}

        {rowData.length > 0 && (
          <>
            <span className="text-sm text-gray-500">
              {rowData.length} run{rowData.length !== 1 ? 's' : ''}
            </span>
            <button
              onClick={exportCsv}
              className="ml-auto px-3 py-1.5 text-sm text-gray-600 hover:text-gray-800 border border-gray-300 rounded hover:bg-gray-50 transition-colors"
            >
              Export CSV
            </button>
          </>
        )}

        {error && (
          <span className="text-sm text-red-600 ml-2">Error: {error}</span>
        )}
      </div>

      {/* ── Grid ─────────────────────────────────────────────── */}
      <div className="ag-theme-quartz flex-1 rounded border border-gray-200 shadow-sm overflow-hidden">
        <AgGridReact
          ref={gridRef}
          rowData={rowData}
          columnDefs={COL_DEFS}
          defaultColDef={DEFAULT_COL_DEF}
          pagination
          paginationPageSize={50}
          rowSelection="multiple"
          animateRows
          overlayNoRowsTemplate='<span class="text-gray-400 text-sm">Click "Load Genotype Runs" to fetch from the BrAPI server.</span>'
        />
      </div>
    </div>
  )
}
