import { useState, useEffect } from 'react'

export default function ServerInfoPanel() {
  const [info,    setInfo]    = useState(null)
  const [loading, setLoading] = useState(true)
  const [error,   setError]   = useState(null)

  useEffect(() => {
    fetch('/brapi/v2/serverinfo', { headers: { Accept: 'application/json' } })
      .then(r => { if (!r.ok) throw new Error(`${r.status} ${r.statusText}`); return r.json() })
      .then(json => setInfo(json?.result))
      .catch(e => setError(e.message))
      .finally(() => setLoading(false))
  }, [])

  if (loading) return <div className="text-sm text-gray-400 p-4">Loading server info…</div>
  if (error)   return <div className="text-sm text-red-500 p-4">Error: {error}</div>
  if (!info)   return null

  return (
    <div className="space-y-5 max-w-3xl">

      {/* Server details */}
      <div className="bg-white border border-gray-200 rounded shadow-sm p-5">
        <h2 className="text-base font-semibold text-gray-800 mb-4">{info.serverName}</h2>
        <dl className="grid grid-cols-1 sm:grid-cols-2 gap-x-8 gap-y-3 text-sm">
          {[
            ['Organization', info.organizationName],
            ['Location',     info.location],
            ['Contact',      info.contactEmail],
            ['Docs',         info.documentationURL],
            ['Website',      info.organizationURL],
            ['Description',  info.serverDescription],
          ].map(([label, value]) => value && (
            <div key={label} className="flex flex-col gap-0.5">
              <dt className="text-xs font-medium text-gray-400 uppercase tracking-wide">{label}</dt>
              <dd className="text-gray-700 break-all">{value}</dd>
            </div>
          ))}
        </dl>
      </div>

      {/* Supported calls */}
      <div className="bg-white border border-gray-200 rounded shadow-sm p-5">
        <h3 className="text-sm font-semibold text-gray-700 mb-3">Supported BrAPI Calls</h3>
        <table className="w-full text-sm border-collapse">
          <thead>
            <tr className="border-b border-gray-200 text-left text-xs text-gray-400 uppercase tracking-wide">
              <th className="pb-2 pr-6 font-medium">Endpoint</th>
              <th className="pb-2 pr-6 font-medium">Methods</th>
              <th className="pb-2 font-medium">Versions</th>
            </tr>
          </thead>
          <tbody>
            {info.calls?.map(call => (
              <tr key={call.service} className="border-b border-gray-100 last:border-0">
                <td className="py-2 pr-6 font-mono text-green-700">/brapi/v2/{call.service}</td>
                <td className="py-2 pr-6">
                  {call.methods?.map(m => (
                    <span key={m} className="inline-block mr-1 px-1.5 py-0.5 text-xs rounded bg-gray-100 text-gray-600 font-mono">{m}</span>
                  ))}
                </td>
                <td className="py-2 text-gray-500">{call.versions?.join(', ')}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

    </div>
  )
}
