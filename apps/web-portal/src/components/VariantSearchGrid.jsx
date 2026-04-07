import { useState, useCallback, useRef } from 'react'
import { AgGridReact } from 'ag-grid-react'

const COL_DEFS = [
  { field: 'variantDbId',    headerName: 'Variant ID',   width: 110, filter: 'agNumberColumnFilter', sort: 'asc' },
  { field: 'referenceName',  headerName: 'Chromosome',   width: 120, filter: 'agTextColumnFilter' },
  { field: 'start',          headerName: 'Start',        width: 120, filter: 'agNumberColumnFilter', type: 'numericColumn', valueFormatter: p => p.value?.toLocaleString() ?? '—' },
  { field: 'end',            headerName: 'End',          width: 120, filter: 'agNumberColumnFilter', type: 'numericColumn', valueFormatter: p => p.value?.toLocaleString() ?? '—' },
  { field: 'referenceBases', headerName: 'Ref',          width: 70,  filter: 'agTextColumnFilter' },
  { field: 'alternateBases', headerName: 'Alt',          width: 100, filter: 'agTextColumnFilter', valueFormatter: p => Array.isArray(p.value) ? p.value.join('/') : (p.value ?? '—') },
  { field: 'variantSetDbIds',headerName: 'Variant Set',  flex: 1,    filter: 'agTextColumnFilter', valueFormatter: p => Array.isArray(p.value) ? p.value.join(', ') : (p.value ?? '—') },
]

const DEFAULT_COL_DEF = { resizable: true, sortable: true, minWidth: 70 }

const POLL_INTERVAL_MS = 2000
const POLL_MAX_ATTEMPTS = 15

export default function VariantSearchGrid() {
  const [token,      setToken]      = useState('')
  const [form,       setForm]       = useState({ variantSetDbIds: '', referenceNames: '', start: '', end: '', pageSize: '1000' })
  const [rowData,    setRowData]    = useState([])
  const [pagination, setPagination] = useState(null)
  const [loading,    setLoading]    = useState(false)
  const [status,     setStatus]     = useState(null)   // info message
  const [error,      setError]      = useState(null)
  const gridRef = useRef(null)

  const headers = useCallback(() => {
    const h = { 'Content-Type': 'application/json', Accept: 'application/json' }
    if (token.trim()) h['Authorization'] = `Bearer ${token.trim()}`
    return h
  }, [token])

  const handleField = e => setForm(f => ({ ...f, [e.target.name]: e.target.value }))

  const buildBody = () => {
    const body = { pageSize: parseInt(form.pageSize) || 1000 }
    if (form.variantSetDbIds.trim()) body.variantSetDbIds = form.variantSetDbIds.split(',').map(s => s.trim()).filter(Boolean)
    if (form.referenceNames.trim()) body.referenceNames  = form.referenceNames.split(',').map(s => s.trim()).filter(Boolean)
    if (form.start !== '') body.start = parseInt(form.start)
    if (form.end   !== '') body.end   = parseInt(form.end)
    return body
  }

  const applyResults = json => {
    const data = json?.result?.data ?? []
    const meta = json?.metadata?.pagination ?? null
    setRowData(data)
    setPagination(meta)
    setStatus(`${data.length.toLocaleString()} variant${data.length !== 1 ? 's' : ''} loaded`)
  }

  const pollForResult = useCallback(async (searchResultsDbId, attempt = 0) => {
    if (attempt >= POLL_MAX_ATTEMPTS) {
      setError('Search timed out — try again.')
      setLoading(false)
      return
    }
    await new Promise(r => setTimeout(r, POLL_INTERVAL_MS))
    const res = await fetch(`/brapi/v2/search/variants/${searchResultsDbId}`, { headers: headers() })
    if (res.status === 202) {
      setStatus(`Waiting for results… (${attempt + 1})`)
      return pollForResult(searchResultsDbId, attempt + 1)
    }
    if (!res.ok) throw new Error(`${res.status} ${res.statusText}`)
    applyResults(await res.json())
    setLoading(false)
  }, [headers])

  const search = useCallback(async () => {
    setLoading(true)
    setError(null)
    setStatus('Submitting search…')
    setRowData([])

    try {
      const res = await fetch('/brapi/v2/search/variants', {
        method: 'POST',
        headers: headers(),
        body: JSON.stringify(buildBody()),
      })

      if (res.status === 401 || res.status === 403) {
        throw new Error(`${res.status} — check your Bearer token`)
      }
      if (!res.ok) throw new Error(`${res.status} ${res.statusText}`)

      if (res.status === 200) {
        applyResults(await res.json())
        setLoading(false)
      } else {
        // 202 — async: poll for result
        const json = await res.json()
        const id   = json?.result?.searchResultsDbId
        setStatus(`Search queued (${id}) — polling…`)
        await pollForResult(id)
      }
    } catch (err) {
      setError(err.message)
      setLoading(false)
    }
  }, [headers, form, pollForResult])

  const exportCsv = useCallback(() => {
    gridRef.current?.api.exportDataAsCsv({ fileName: 'variants.csv' })
  }, [])

  return (
    <div className="flex flex-col h-full gap-3">

      {/* ── Search form ──────────────────────────────────────── */}
      <div className="bg-white border border-gray-200 rounded p-4 shadow-sm space-y-3">

        {/* Bearer token */}
        <div className="flex items-center gap-3">
          <label className="text-xs font-medium text-gray-500 w-28 shrink-0">Bearer Token</label>
          <input
            type="password"
            placeholder="Paste Keycloak access token…"
            value={token}
            onChange={e => setToken(e.target.value)}
            className="flex-1 text-xs font-mono border border-gray-300 rounded px-2 py-1.5 focus:outline-none focus:ring-1 focus:ring-green-600"
          />
        </div>

        {/* Search fields */}
        <div className="grid grid-cols-2 md:grid-cols-3 gap-3">
          {[
            { name: 'variantSetDbIds', label: 'Variant Set IDs', placeholder: '1, 2, 3' },
            { name: 'referenceNames',  label: 'Chromosomes',     placeholder: '1, 2, 3' },
            { name: 'start',           label: 'Start position',  placeholder: '0' },
            { name: 'end',             label: 'End position',    placeholder: '1000000' },
            { name: 'pageSize',        label: 'Page size',       placeholder: '1000' },
          ].map(({ name, label, placeholder }) => (
            <div key={name} className="flex flex-col gap-1">
              <label className="text-xs font-medium text-gray-500">{label}</label>
              <input
                name={name}
                value={form[name]}
                onChange={handleField}
                placeholder={placeholder}
                className="text-sm border border-gray-300 rounded px-2 py-1.5 focus:outline-none focus:ring-1 focus:ring-green-600"
              />
            </div>
          ))}
        </div>

        {/* Actions */}
        <div className="flex items-center gap-3 pt-1">
          <button
            onClick={search}
            disabled={loading}
            className="px-5 py-1.5 bg-green-700 hover:bg-green-800 text-white text-sm font-medium rounded disabled:opacity-50 transition-colors"
          >
            {loading ? 'Searching…' : 'Search Variants'}
          </button>

          {rowData.length > 0 && (
            <button
              onClick={exportCsv}
              className="px-3 py-1.5 text-sm text-gray-600 hover:text-gray-800 border border-gray-300 rounded hover:bg-gray-50 transition-colors"
            >
              Export CSV
            </button>
          )}

          {status && !error && <span className="text-sm text-gray-500">{status}</span>}
          {error  && <span className="text-sm text-red-600">Error: {error}</span>}

          {pagination && (
            <span className="ml-auto text-xs text-gray-400">
              Page {pagination.currentPage + 1} / {pagination.totalPages} &nbsp;·&nbsp; {pagination.totalCount?.toLocaleString()} total
            </span>
          )}
        </div>
      </div>

      {/* ── Results grid ─────────────────────────────────────── */}
      <div className="ag-theme-quartz flex-1 rounded border border-gray-200 shadow-sm overflow-hidden">
        <AgGridReact
          ref={gridRef}
          rowData={rowData}
          columnDefs={COL_DEFS}
          defaultColDef={DEFAULT_COL_DEF}
          pagination
          paginationPageSize={100}
          rowSelection="multiple"
          animateRows
          overlayNoRowsTemplate='<span class="text-gray-400 text-sm">Fill in the search form above and click Search Variants.</span>'
        />
      </div>
    </div>
  )
}
