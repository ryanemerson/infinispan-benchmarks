package org.infinispan;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

@Fork(value=1, jvmArgs = {
      "-Xmx10G",
      "-Xms10G",
      "-XX:+HeapDumpOnOutOfMemoryError",
      "-Xss512k",
})
@BenchmarkMode(Mode.Throughput)
public class UtfBenchmark {
   @Benchmark
   public void testUtfWrite(UtfSetup utfSetup) throws IOException {
      utfSetup.strWriter.writeUTF(utfSetup.string);
      utfSetup.reset();
   }
}
