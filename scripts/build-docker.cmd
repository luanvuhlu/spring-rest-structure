@echo off
REM Check if Docker Desktop is running
tasklist /FI "IMAGENAME eq Docker Desktop.exe" 2>NUL | find /I /N "Docker Desktop.exe">NUL
if "%ERRORLEVEL%"=="0" (
    echo Docker Desktop is running
) else (
    echo Docker Desktop is not running, starting it...
    start "" "C:\Program Files\Docker\Docker\Docker Desktop.exe"
    REM Wait for Docker Desktop to start
    timeout /t 30 /nobreak
)

REM Run the Maven command
call mvnw.cmd compile jib:dockerBuild