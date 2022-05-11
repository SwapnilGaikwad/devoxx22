/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package demo.graal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(value = 1)
@Measurement(iterations = 3, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Warmup(iterations = 3, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
public class MyBenchmark {

    private static final int ARRAY_SIZE = 1024;
    private static final int seed = 42;
    private int randomNums[];

    @Setup
    public void setup() {
        randomNums = new int[ARRAY_SIZE];
        for(int i = 0; i < randomNums.length; i++){
            randomNums[i] = new Random().nextInt(seed);
        }
    }

    public int myInc(int num) {
        return num + 1;
    }

    @Benchmark
    @Fork(jvmArgsPrepend = "-XX:+UnlockExperimentalVMOptions", jvmArgsAppend = "-Xint")
    public void interpreter(Blackhole blackhole) {
        int sum = 0;
        for(int i = 0; i < randomNums.length; i++) {
            sum += myInc(randomNums[i]);
        }
        blackhole.consume(sum);
    }

    @Benchmark
    @Fork(jvmArgsPrepend = "-XX:+UnlockExperimentalVMOptions", jvmArgsAppend = "-XX:TieredStopAtLevel=1")
    public void tier1Only(Blackhole blackhole) {
        int sum = 0;
        for(int i = 0; i < randomNums.length; i++) {
            sum += myInc(randomNums[i]);
        }
        blackhole.consume(sum);
    }

    @Benchmark
    public void tierAll(Blackhole blackhole) {
        int sum = 0;
        for(int i = 0; i < randomNums.length; i++) {
            sum += myInc(randomNums[i]);
        }
        blackhole.consume(sum);
    }

    // @Benchmark
    @Fork(jvmArgsAppend="-XX:-TieredCompilation")
    public void topTierOnly(Blackhole blackhole) {
        int sum = 0;
        for(int i = 0; i < randomNums.length; i++) {
            sum += myInc(randomNums[i]);
        }
        blackhole.consume(sum);
    }
}
