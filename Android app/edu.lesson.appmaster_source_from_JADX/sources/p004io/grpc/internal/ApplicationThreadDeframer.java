package p004io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;
import p004io.grpc.Decompressor;
import p004io.grpc.internal.ApplicationThreadDeframerListener;
import p004io.grpc.internal.MessageDeframer;
import p004io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.ApplicationThreadDeframer */
public class ApplicationThreadDeframer implements Deframer {
    /* access modifiers changed from: private */
    public final ApplicationThreadDeframerListener appListener;
    /* access modifiers changed from: private */
    public final MessageDeframer deframer;
    private final MessageDeframer.Listener storedListener;

    /* renamed from: io.grpc.internal.ApplicationThreadDeframer$TransportExecutor */
    interface TransportExecutor extends ApplicationThreadDeframerListener.TransportExecutor {
    }

    ApplicationThreadDeframer(MessageDeframer.Listener listener, TransportExecutor transportExecutor, MessageDeframer deframer2) {
        SquelchLateMessagesAvailableDeframerListener squelchLateMessagesAvailableDeframerListener = new SquelchLateMessagesAvailableDeframerListener((MessageDeframer.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
        this.storedListener = squelchLateMessagesAvailableDeframerListener;
        ApplicationThreadDeframerListener applicationThreadDeframerListener = new ApplicationThreadDeframerListener(squelchLateMessagesAvailableDeframerListener, transportExecutor);
        this.appListener = applicationThreadDeframerListener;
        deframer2.setListener(applicationThreadDeframerListener);
        this.deframer = deframer2;
    }

    public void setMaxInboundMessageSize(int messageSize) {
        this.deframer.setMaxInboundMessageSize(messageSize);
    }

    public void setDecompressor(Decompressor decompressor) {
        this.deframer.setDecompressor(decompressor);
    }

    public void setFullStreamDecompressor(GzipInflatingBuffer fullStreamDecompressor) {
        this.deframer.setFullStreamDecompressor(fullStreamDecompressor);
    }

    public void request(final int numMessages) {
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable() {
            public void run() {
                if (!ApplicationThreadDeframer.this.deframer.isClosed()) {
                    try {
                        ApplicationThreadDeframer.this.deframer.request(numMessages);
                    } catch (Throwable t) {
                        ApplicationThreadDeframer.this.appListener.deframeFailed(t);
                        ApplicationThreadDeframer.this.deframer.close();
                    }
                }
            }
        }));
    }

    public void deframe(final ReadableBuffer data) {
        this.storedListener.messagesAvailable(new CloseableInitializingMessageProducer(new Runnable() {
            public void run() {
                try {
                    ApplicationThreadDeframer.this.deframer.deframe(data);
                } catch (Throwable t) {
                    ApplicationThreadDeframer.this.appListener.deframeFailed(t);
                    ApplicationThreadDeframer.this.deframer.close();
                }
            }
        }, new Closeable() {
            public void close() {
                data.close();
            }
        }));
    }

    public void closeWhenComplete() {
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable() {
            public void run() {
                ApplicationThreadDeframer.this.deframer.closeWhenComplete();
            }
        }));
    }

    public void close() {
        this.deframer.stopDelivery();
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable() {
            public void run() {
                ApplicationThreadDeframer.this.deframer.close();
            }
        }));
    }

    /* access modifiers changed from: package-private */
    public MessageDeframer.Listener getAppListener() {
        return this.appListener;
    }

    /* renamed from: io.grpc.internal.ApplicationThreadDeframer$InitializingMessageProducer */
    private class InitializingMessageProducer implements StreamListener.MessageProducer {
        private boolean initialized;
        private final Runnable runnable;

        private InitializingMessageProducer(Runnable runnable2) {
            this.initialized = false;
            this.runnable = runnable2;
        }

        private void initialize() {
            if (!this.initialized) {
                this.runnable.run();
                this.initialized = true;
            }
        }

        @Nullable
        public InputStream next() {
            initialize();
            return ApplicationThreadDeframer.this.appListener.messageReadQueuePoll();
        }
    }

    /* renamed from: io.grpc.internal.ApplicationThreadDeframer$CloseableInitializingMessageProducer */
    private class CloseableInitializingMessageProducer extends InitializingMessageProducer implements Closeable {
        private final Closeable closeable;

        public CloseableInitializingMessageProducer(Runnable runnable, Closeable closeable2) {
            super(runnable);
            this.closeable = closeable2;
        }

        public void close() throws IOException {
            this.closeable.close();
        }
    }
}
