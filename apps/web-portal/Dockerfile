# ── Stage 1: build ────────────────────────────────────────────────────────────
FROM node:20-alpine AS build

WORKDIR /app

# Install dependencies first (cached layer — reruns only if package*.json changes).
COPY package*.json ./
RUN npm ci --prefer-offline

# Copy source and produce the optimised static bundle.
COPY . .
RUN npm run build

# ── Stage 2: serve ────────────────────────────────────────────────────────────
FROM nginx:1.27-alpine

# Replace the default nginx config with our custom one:
#   • listens on port 3000
#   • proxies /brapi/ → snp-api:8081
#   • SPA try_files fallback
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy the Vite production build output.
COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]
