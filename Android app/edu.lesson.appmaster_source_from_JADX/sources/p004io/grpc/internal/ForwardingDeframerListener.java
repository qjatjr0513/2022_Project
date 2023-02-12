package p004io.grpc.internal;

import p004io.grpc.internal.MessageDeframer;
import p004io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.ForwardingDeframerListener */
abstract class ForwardingDeframerListener implements MessageDeframer.Listener {
    /* access modifiers changed from: protected */
    public abstract MessageDeframer.Listener delegate();

    ForwardingDeframerListener() {
    }

    public void bytesRead(int numBytes) {
        delegate().bytesRead(numBytes);
    }

    public void messagesAvailable(StreamListener.MessageProducer producer) {
        delegate().messagesAvailable(producer);
    }

    public void deframerClosed(boolean hasPartialMessage) {
        delegate().deframerClosed(hasPartialMessage);
    }

    public void deframeFailed(Throwable cause) {
        delegate().deframeFailed(cause);
    }
}
