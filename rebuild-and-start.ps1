# Script PowerShell pour reconstruire et redémarrer tous les services

Write-Host "=== Arrêt de tous les conteneurs ===" -ForegroundColor Yellow
docker compose down

Write-Host "`n=== Reconstruction des images Docker ===" -ForegroundColor Yellow
docker compose build --no-cache

Write-Host "`n=== Démarrage des services ===" -ForegroundColor Yellow
docker compose up -d

Write-Host "`n=== Attente du démarrage (30 secondes) ===" -ForegroundColor Yellow
Start-Sleep -Seconds 30

Write-Host "`n=== Vérification des services ===" -ForegroundColor Green
docker compose ps

Write-Host "`n=== Pour voir les logs en temps réel, exécutez: ===" -ForegroundColor Cyan
Write-Host "docker compose logs -f" -ForegroundColor Cyan

Write-Host "`n=== Pour vérifier Eureka, ouvrez: ===" -ForegroundColor Cyan
Write-Host "http://localhost:8761" -ForegroundColor Cyan

