@echo off
: p
ping 61.152.XX.XX
IF ERRORLEVEL 1 goto aa
IF ERRORLEVEL 0 goto bb
:aa
echo ���������������...
net stop MSSQLSERVER
net start MSSQLSERVER
goto p
:bb
echo ����ping�С�����
goto p