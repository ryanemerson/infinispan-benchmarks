package org.infinispan;

import java.io.IOException;

import org.infinispan.commons.marshall.Marshaller;
import org.infinispan.factories.GlobalComponentRegistry;
import org.infinispan.factories.KnownComponentNames;
import org.infinispan.manager.DefaultCacheManager;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Group)
public class MarshallerHolder {
   private DefaultCacheManager cm;
   private Marshaller marshaller;

   @Setup
   public void initializeState() throws IOException {
      cm = new DefaultCacheManager();
      GlobalComponentRegistry gcr = GlobalComponentRegistry.of(cm);
      marshaller = gcr.getComponent(Marshaller.class, KnownComponentNames.INTERNAL_MARSHALLER);
   }

   @TearDown
   public void shutdownState() {
      cm.stop();
   }

   public Marshaller getMarshaller() {
      return marshaller;
   }
}
