#/bin/bash

javac MyTest.java
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler \
-XX:-BackgroundCompilation -XX:CompileCommand='print,MyTest.myInc' MyTest
