#!/bin/bash
# deploy.sh — Same-server Build & Deployment Script
set -e

# 1. Pull latest code
REPO_ROOT="$(dirname "$0")/.."
echo "─── Pulling Latest Code ──────────────────────────────────────────"
git -C "$REPO_ROOT" pull

# 2. Navigate to the docker directory relative to the script location
cd "$REPO_ROOT/infrastructure/docker"

echo "─── Syncing Environment ──────────────────────────────────────────"

# Create .env from example if it doesn't exist
if [ ! -f .env ]; then
    echo "Creating .env from .env.example..."
    cp .env.example .env
fi

# Sync server shell variables to .env file
# We map your server's DB_USERNAME to the project's DB_USER
# If these variables are already exported in the server's environment (~/.bashrc, etc.),
# they will be picked up here.
echo "Checking for server environment variables..."
[[ -n "$DB_USERNAME" ]] && echo "Found DB_USERNAME in server env. Updating .env..." && sed -i "s/^DB_USERNAME=.*/DB_USERNAME=$DB_USERNAME/" .env
[[ -n "$DB_PASSWORD" ]] && echo "Found DB_PASSWORD in server env. Updating .env..." && sed -i "s/^DB_PASSWORD=.*/DB_PASSWORD=$DB_PASSWORD/" .env
# DB_HOST stays as localhost — PostgreSQL runs on the same server,
# and host networking mode makes localhost resolve to the host directly.

# Docker image tags must be lowercase. Force GITHUB_REPOSITORY_OWNER to lowercase.
OWNER_LC="${GITHUB_REPOSITORY_OWNER,,}"
if grep -q "^GITHUB_REPOSITORY_OWNER=" .env; then
    sed -i "s/^GITHUB_REPOSITORY_OWNER=.*/GITHUB_REPOSITORY_OWNER=$OWNER_LC/" .env
else
    echo "GITHUB_REPOSITORY_OWNER=$OWNER_LC" >> .env
fi
echo "Using image owner: $OWNER_LC"

echo "─── Building Images Locally ──────────────────────────────────────"
echo "Building from source to bypass GHCR unauthorized errors..."

# Build images locally. This prevents Docker from trying to pull 
# from the private/unauthorized ghcr.io repository.
docker compose build --no-cache snp-api snp-ui

echo "─── Starting Services ────────────────────────────────────────────"
# -d runs in background, --no-build ensures we use the images we just built locally
docker compose up -d --no-build

echo "─── Cleanup ──────────────────────────────────────────────────────"
# Remove old, unused images to save disk space
docker image prune -af

echo ""
echo "Deployment complete! Your services are running at:"
echo "  • UI: http://localhost:3000"
echo "  • API Server: http://localhost:8081"
echo ""
echo "To view logs, run: cd infrastructure/docker && docker compose logs -f"
