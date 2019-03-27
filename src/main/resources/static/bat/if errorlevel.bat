@echo off
: p
ping 61.152.XX.XX
IF ERRORLEVEL 1 goto aa
IF ERRORLEVEL 0 goto bb
:aa
echo 正在重启软件服务...
net stop MSSQLSERVER
net start MSSQLSERVER
goto p
:bb
echo 继续ping中。。。
goto p