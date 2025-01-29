package org.infinispan;

import java.io.IOException;

import org.infinispan.commons.marshall.Marshaller;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.Throughput)
public class MarshallerBenchmark {
   @Benchmark
   @Group("marshaller")
   public void marshall(MarshallerHolder mh, Blackhole bh, KeySequenceGenerator kg) throws IOException, InterruptedException {
      bh.consume(mh.getMarshaller().objectToByteBuffer(kg.getNextKey()));
   }

   @Benchmark
   @Group("marshaller")
   public void marshallUnmarshall(MarshallerHolder mh, Blackhole bh, KeySequenceGenerator kg) throws ClassNotFoundException, IOException, InterruptedException {
      Marshaller marshaller = mh.getMarshaller();
      Object object = kg.getNextKey();
      byte[] bytes = marshaller.objectToByteBuffer(object);
      bh.consume(marshaller.objectFromByteBuffer(bytes));
   }
}
