TITLE InteracPush
start "" "C:\Program Files\PostgreSQL\9.4\bin\psql.exe" -h localhost -U postgres -d interacLocal -c "\copy camara_log from '\Release\log.txt';"
TIMEOUT 2
del "log.txt"
exit

