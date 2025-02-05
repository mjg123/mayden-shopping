import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: 'http://172.18.208.1:8080/',  // <-- hardcoded IP because of WSL
        changeOrigin: true,
      },
    },
  },
})
