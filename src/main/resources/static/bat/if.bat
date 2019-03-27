@echo off
set /A a=5 
set /A b=10 
set /A c=%a% + %b%
if %c%==15 echo "The value of variable c is 15" 
if %c%==10 echo "The value of variable c is 10"
pause