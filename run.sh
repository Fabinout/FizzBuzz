#!/bin/sh
#mvn exec:java -Dexec.mainClass="com.FizzBuzz"

echo "compiling..."

javac -sourcepath src -d bin -cp lib/javassist.jar src/main/com/reflective/FizzBuzz.java
javac -sourcepath src -d bin -cp lib/javassist.jar src/main/com/reflective/Setup.java
java -cp lib/javassist.jar:bin reflective/Setup temp
echo "Launching the FUZZBIZZ INTERVIEW..."
java -cp bin -Xbootclasspath/p:temp reflective.FizzBuzz
