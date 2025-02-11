package org.infinispan;

import java.io.IOException;
import java.io.OutputStream;

import org.infinispan.protostream.ProtobufUtil;
import org.infinispan.protostream.RandomAccessOutputStream;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.protostream.impl.TagWriterImpl;

public class TagWriter implements StringWriter {

   static final SerializationContext SERIALIZATION_CONTEXT = ProtobufUtil.newSerializationContext();

   TagWriterImpl writer;
   OutputStream out;

   TagWriter(int pos, OutputStream out) {
      this.out = out;
//      out.setPosition(pos);
      if (out instanceof RandomAccessOutputStream ra) {
         writer = TagWriterImpl.newInstance(SERIALIZATION_CONTEXT, ra);
      } else {
         writer = TagWriterImpl.newInstance(SERIALIZATION_CONTEXT, out);
      }
   }

   @Override
   public void writeUTF(String string) throws IOException {
      writer.writeString(1, string);
   }
}
