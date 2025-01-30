package org.infinispan;

import java.io.IOException;
import java.io.OutputStream;

import org.infinispan.protostream.ImmutableSerializationContext;
import org.infinispan.protostream.LazyByteArrayOutputStream;
import org.infinispan.protostream.ProtobufUtil;
import org.infinispan.protostream.impl.ByteArrayOutputStreamEx;
import org.infinispan.protostream.impl.TagWriterImpl;

public class TagWriter implements StringWriter {

   TagWriterImpl writer;
   LazyByteArrayOutputStream out;

   TagWriter(int pos, LazyByteArrayOutputStream out) {
      this.out = out;
      out.setPosition(pos);
      ImmutableSerializationContext ctx = ProtobufUtil.newSerializationContext();
      writer = TagWriterImpl.newInstance(ctx, (OutputStream) out);
   }

   @Override
   public void writeUTF(String string) throws IOException {
      writer.writeString(1, string);
   }
}
