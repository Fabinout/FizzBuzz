#mvn exec:java -Dexec.mainClass="com.FizzBuzz"

#echo "compiling..."
pwd
javac -sourcepath src -d bin -cp lib/javassist.jar src/main/com/reflective/FizzBuzz.java
javac -sourcepath src -d bin -cp lib/javassist.jar src/main/com/reflective/Setup.java
##javac -sourcepath src ../../src/java/*.java
#
java -cp lib/javassist.jar:bin reflective/Setup temp
#java -cp lib/javassist.jar:bin Setup temp
#java -cp lib/javassist.jar:bin net.thecodersbreakfast.codestory.foobarqix.Setup temp
#
echo "Launching the FooBarQix challenge..."
java -cp bin -Xbootclasspath/p:temp reflective.FizzBuzz
