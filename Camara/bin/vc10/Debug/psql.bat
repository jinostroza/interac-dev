start "" "C:\Program Files\PostgreSQL\9.4\bin\psql.exe" -h 54.208.243.25 -U postgres -d Plataforma -c "\copy log_cam from '\Release\log.txt';"
TIMEOUT 2
del "log.txt"
TIMEOUT 10
start "" "C:\Release\invisible.vbs"

TIMEOUT 2

exit

