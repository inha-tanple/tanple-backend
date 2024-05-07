
@if not exist "%USERPROFILE%\tanple.mv.db" echo. > "%USERPROFILE%\tanple.mv.db"

@java -cp "h2-2.2.224.jar;%H2DRIVERS%;%CLASSPATH%" org.h2.tools.Console %*
@if errorlevel 1 pause
