# Devoxx22 Talk

## Demo Steps

On a host

```
docker build . --tag devoxx22
docker run -it --name devoxx22-demo --rm devoxx22 /bin/bash
```

Inside the container

```
cd ~/demo
./runBenchmark.sh
./runMyTest.sh
./runMyJIT.sh
```

## Tools

Chose a version of [IdealGraphVisualizer](https://www.oracle.com/downloads/graalvm-downloads.html)
for JDK 11 that suits your OS.

The dumps for the graphs referred to in the talk are available in `igv` directory.

Run IGV using the following command

```
idealgraphvisualizer --jdkhome $JAVA_HOME
```
