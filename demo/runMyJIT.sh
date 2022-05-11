#/bin/bash

javac MyJITDemo.java
java -server -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler -Dgraal.Dump=: -XX:-BackgroundCompilation '-XX:CompileCommand=compileonly, MyJITDemo.myInc' '-XX:CompileCommand=print, MyJITDemo.myInc' MyJITDemo
