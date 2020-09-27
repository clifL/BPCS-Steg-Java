mkdir class
mkdir bin

javac -d class ../src/*.java ../src/BPCS/*.java

jar cvfm bin/app.jar manifest-add.txt -C class/ .

xcopy "../resources" "bin/resources" /E /I /Y

cd bin
echo java -cp "app.jar" Main > bpcs-steg-java.bat

pause