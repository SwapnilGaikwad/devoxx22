#/bin/bash

javac MyTest2.java
java -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler \
-XX:-BackgroundCompilation -XX:CompileCommand='print,MyTest2.myInc' MyTest2
