@echo on
choice /c:abc Apple,Banana,Cat
if errorlevel 3 goto apple
if errorlevel 2 goto banana
if errorlevel 1 goto cat
:apple
echo "Apple"
goto end
:banana
echo "Banana"
goto end
:cat
echo "Cat"
goto end
:end
echo good bye
pause