import { useState, useCallback, useRef } from 'react'
import { AgGridReact } from 'ag-grid-react'

/**
 * BrAPI /variantsets column definitions.
 * Field names match the BrAPI v2.1 VariantSet response object.
 */
const COL_DEFS = [
  {
    field: 'variantSetDbId',
    headerName: 'ID',
    width: 90,
    filter: 'agNumberColumnFilter',
    sort: 'asc',
  },
  {
    field: 'variantSetName',
    headerName: 'Name',
    flex: 2,
    filter: 'agTextColumnFilter',
  },
  {
    field: 'referenceSetDbId',
    headerName: 'Reference Set',
    width: 140,
    filter: 'agTextColumnFilter',
  },
  {
    field: 'variantCount',
    headerName: 'Variants',
    width: 110,
    filter: 'agNumberColumnFilter',
    type: 'numericColumn',
    valueFormatter: p => p.value?.toLocaleString() ?? '—',
  },
  {
    field: 'callSetCount',
    headerName: 'Samples',
    width: 110,
    filter: 'agNumberColumnFilter',
    type: 'numericColumn',
    valueFormatter: p => p.value?.toLocaleString() ?? '—',
  },
  {
    field: 'studyDbId',
    headerName: 'Study',
    width: 100,
    filter: 'agTextColumnFilter',
  },
]

const DEFAULT_COL_DEF = {
  resizable: true,
  sortable: true,
  minWidth: 80,
}

export default function VariantSetGrid() {
  const [rowData, setRowData]     = useState([])
  const [loading, setLoading]     = useState(false)
  const [error, setError]         = useState(null)
  const [nextPageToken, setNextPageToken] = useState(null)
  const gridRef = useRef(null)

  /**
   * Fetch from BrAPI /variantsets.
   * @param {boolean} reset  true = clear existing rows and start from page 0
   */
  const load = useCallback(async (reset = false) => {
    setLoading(true)
    setError(null)
    const token = reset ? null : nextPageToken

    try {
      const params = new URLSearchParams({ pageSize: 200 })
      if (token) params.set('pageToken', token)

      const res = await fetch(`/brapi/v2/variantsets?${params}`, {
        headers: { Accept: 'application/json' },
      })
      if (!res.ok) throw new Error(`${res.status} ${res.statusText}`)

      const json = await res.json()
      const data     = json?.result?.data             ?? []
      const nextToken = json?.metadata?.pagination?.nextPageToken ?? null

      setRowData(prev => reset ? data : [...prev, ...data])
      setNextPageToken(nextToken)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }, [nextPageToken])

  const exportCsv = useCallback(() => {
    gridRef.current?.api.exportDataAsCsv({ fileName: 'variantsets.csv' })
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
          {loading ? 'Loading…' : 'Load Variant Sets'}
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
              {rowData.length} variant set{rowData.length !== 1 ? 's' : ''}
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
          overlayNoRowsTemplate='<span class="text-gray-400 text-sm">Click "Load Variant Sets" to fetch from the BrAPI server.</span>'
        />
      </div>
    </div>
  )
}
