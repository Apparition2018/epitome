@echo off
netstat -a > a.txt
type a.txt
del a.txt
pause