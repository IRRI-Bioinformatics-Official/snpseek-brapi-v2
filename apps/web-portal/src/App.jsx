import { useState } from 'react'
import VariantSearchGrid from './components/VariantSearchGrid.jsx'
import ServerInfoPanel   from './components/ServerInfoPanel.jsx'

const NAV = [
  { id: 'variants',    label: 'Variant Search' },
  { id: 'serverinfo',  label: 'Server Info' },
]

export default function App() {
  const [active, setActive] = useState('variants')

  return (
    <div className="flex h-screen bg-gray-50">
      {/* ── Sidebar ─────────────────────────────────────────────── */}
      <aside className="w-56 shrink-0 flex flex-col bg-green-900 text-white">
        <div className="px-5 py-5 border-b border-green-700">
          <p className="text-xl font-bold tracking-tight">SNPseek</p>
          <p className="text-green-300 text-xs mt-0.5">BrAPI v2.1 Dashboard</p>
        </div>

        <nav className="flex-1 px-3 py-4 space-y-0.5">
          {NAV.map(({ id, label }) => (
            <button
              key={id}
              onClick={() => setActive(id)}
              className={
                'w-full text-left px-3 py-2 rounded text-sm transition-colors ' +
                (active === id
                  ? 'bg-green-700 text-white font-medium'
                  : 'text-green-200 hover:bg-green-800 hover:text-white')
              }
            >
              {label}
            </button>
          ))}
        </nav>

        <div className="px-5 py-3 border-t border-green-700 text-xs text-green-500">
          IRRI · Rice Genomics
        </div>
      </aside>

      {/* ── Main area ───────────────────────────────────────────── */}
      <div className="flex flex-col flex-1 overflow-hidden">
        {/* Topbar */}
        <header className="shrink-0 flex items-center justify-between bg-white border-b border-gray-200 px-6 py-3">
          <h1 className="text-gray-800 font-semibold text-base">
            {NAV.find(n => n.id === active)?.label}
          </h1>
          <span className="text-xs text-gray-400 font-mono">
            {active === 'variants'   ? 'POST /brapi/v2/search/variants' : 'GET /brapi/v2/serverinfo'}
          </span>
        </header>

        {/* Page */}
        <main className="flex-1 overflow-hidden p-5">
          {active === 'variants'   && <VariantSearchGrid />}
          {active === 'serverinfo' && <ServerInfoPanel />}
        </main>
      </div>
    </div>
  )
}
