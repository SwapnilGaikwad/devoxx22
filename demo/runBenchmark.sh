#/bin/bash

pushd benchmark
mvn clean install
java -jar target/benchmarks.jar MyBenchmark