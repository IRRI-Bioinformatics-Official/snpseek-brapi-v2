import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

export default defineConfig({
  plugins: [
    react(),
    tailwindcss(),
  ],
  base: '/brapiv2ui/',
  build: {
    // AG-Grid community is ~1 MB minified — expected and unavoidable.
    chunkSizeWarningLimit: 1200,
    rollupOptions: {
      output: {
        // Split AG-Grid into its own lazy chunk separate from app code.
        manualChunks: {
          'ag-grid': ['ag-grid-community', 'ag-grid-react'],
        },
      },
    },
  },
  server: {
    port: 5173,
    proxy: {
      // Forward BrAPI calls to the Spring Boot API server in development so
      // the browser never hits a cross-origin request.
      '/brapi': {
        target: 'http://localhost:8081',
        changeOrigin: true,
      },
    },
  },
})
