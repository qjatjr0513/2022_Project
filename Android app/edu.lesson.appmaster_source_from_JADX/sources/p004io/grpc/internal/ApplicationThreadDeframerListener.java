package p004io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;
import p004io.grpc.internal.MessageDeframer;
import p004io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.ApplicationThreadDeframerListener */
final class ApplicationThreadDeframerListener implements MessageDeframer.Listener {
    private final Queue<InputStream> messageReadQueue = new ArrayDeque();
    /* access modifiers changed from: private */
    public final MessageDeframer.Listener storedListener;
    private final TransportExecutor transportExecutor;

    /* renamed from: io.grpc.internal.ApplicationThreadDeframerListener$TransportExecutor */
    public interface TransportExecutor {
        void runOnTransportThread(Runnable runnable);
    }

    public ApplicationThreadDeframerListener(MessageDeframer.Listener listener, TransportExecutor transportExecutor2) {
        this.storedListener = (MessageDeframer.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.transportExecutor = (TransportExecutor) Preconditions.checkNotNull(transportExecutor2, "transportExecutor");
    }

    public void bytesRead(final int numBytes) {
        this.transportExecutor.runOnTransportThread(new Runnable() {
            public void run() {
                ApplicationThreadDeframerListener.this.storedListener.bytesRead(numBytes);
            }
        });
    }

    public void messagesAvailable(StreamListener.MessageProducer producer) {
        while (true) {
            InputStream next = producer.next();
            InputStream message = next;
            if (next != null) {
                this.messageReadQueue.add(message);
            } else {
                return;
            }
        }
    }

    public void deframerClosed(final boolean hasPartialMessage) {
        this.transportExecutor.runOnTransportThread(new Runnable() {
            public void run() {
                ApplicationThreadDeframerListener.this.storedListener.deframerClosed(hasPartialMessage);
            }
        });
    }

    public void deframeFailed(final Throwable cause) {
        this.transportExecutor.runOnTransportThread(new Runnable() {
            public void run() {
                ApplicationThreadDeframerListener.this.storedListener.deframeFailed(cause);
            }
        });
    }

    public InputStream messageReadQueuePoll() {
        return this.messageReadQueue.poll();
    }
}
