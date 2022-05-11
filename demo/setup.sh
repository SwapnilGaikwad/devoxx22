#!/bin/bash

export JAVA_HOME=$(mx --java-home /usr/lib/jvm/labsjdk11-latest-jvmci --primary-suite ~/graal/compiler graalvm-home)
echo "export JAVA_HOME=${JAVA_HOME}" >> ~/.bashrc
echo "export PATH=${JAVA_HOME}/bin:${PATH}" >> ~/.bashrc
echo "export LD_LIBRARY_PATH=${JAVA_HOME}/lib" >> ~/.bashrc

# Set JAVA_HOME to Graal enabled JDK
export PATH=$JAVA_HOME/bin:$PATH
export LD_LIBRARY_PATH=$JAVA_HOME/lib
mx --primary-suite ~/graal/compiler/ hsdis