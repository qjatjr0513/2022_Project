package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import p004io.grpc.InternalChannelz;

/* renamed from: io.grpc.internal.TransportTracer */
public final class TransportTracer {
    private static final Factory DEFAULT_FACTORY = new Factory(TimeProvider.SYSTEM_TIME_PROVIDER);
    private FlowControlReader flowControlWindowReader;
    private long keepAlivesSent;
    private long lastLocalStreamCreatedTimeNanos;
    private volatile long lastMessageReceivedTimeNanos;
    private long lastMessageSentTimeNanos;
    private long lastRemoteStreamCreatedTimeNanos;
    private final LongCounter messagesReceived;
    private long messagesSent;
    private long streamsFailed;
    private long streamsStarted;
    private long streamsSucceeded;
    private final TimeProvider timeProvider;

    /* renamed from: io.grpc.internal.TransportTracer$FlowControlReader */
    public interface FlowControlReader {
        FlowControlWindows read();
    }

    public TransportTracer() {
        this.messagesReceived = LongCounterFactory.create();
        this.timeProvider = TimeProvider.SYSTEM_TIME_PROVIDER;
    }

    private TransportTracer(TimeProvider timeProvider2) {
        this.messagesReceived = LongCounterFactory.create();
        this.timeProvider = timeProvider2;
    }

    public InternalChannelz.TransportStats getStats() {
        FlowControlReader flowControlReader = this.flowControlWindowReader;
        long remoteFlowControlWindow = -1;
        long localFlowControlWindow = flowControlReader == null ? -1 : flowControlReader.read().localBytes;
        FlowControlReader flowControlReader2 = this.flowControlWindowReader;
        if (flowControlReader2 != null) {
            remoteFlowControlWindow = flowControlReader2.read().remoteBytes;
        }
        return new InternalChannelz.TransportStats(this.streamsStarted, this.lastLocalStreamCreatedTimeNanos, this.lastRemoteStreamCreatedTimeNanos, this.streamsSucceeded, this.streamsFailed, this.messagesSent, this.messagesReceived.value(), this.keepAlivesSent, this.lastMessageSentTimeNanos, this.lastMessageReceivedTimeNanos, localFlowControlWindow, remoteFlowControlWindow);
    }

    public void reportLocalStreamStarted() {
        this.streamsStarted++;
        this.lastLocalStreamCreatedTimeNanos = this.timeProvider.currentTimeNanos();
    }

    public void reportRemoteStreamStarted() {
        this.streamsStarted++;
        this.lastRemoteStreamCreatedTimeNanos = this.timeProvider.currentTimeNanos();
    }

    public void reportStreamClosed(boolean success) {
        if (success) {
            this.streamsSucceeded++;
        } else {
            this.streamsFailed++;
        }
    }

    public void reportMessageSent(int numMessages) {
        if (numMessages != 0) {
            this.messagesSent += (long) numMessages;
            this.lastMessageSentTimeNanos = this.timeProvider.currentTimeNanos();
        }
    }

    public void reportMessageReceived() {
        this.messagesReceived.add(1);
        this.lastMessageReceivedTimeNanos = this.timeProvider.currentTimeNanos();
    }

    public void reportKeepAliveSent() {
        this.keepAlivesSent++;
    }

    public void setFlowControlWindowReader(FlowControlReader flowControlWindowReader2) {
        this.flowControlWindowReader = (FlowControlReader) Preconditions.checkNotNull(flowControlWindowReader2);
    }

    /* renamed from: io.grpc.internal.TransportTracer$FlowControlWindows */
    public static final class FlowControlWindows {
        public final long localBytes;
        public final long remoteBytes;

        public FlowControlWindows(long localBytes2, long remoteBytes2) {
            this.localBytes = localBytes2;
            this.remoteBytes = remoteBytes2;
        }
    }

    /* renamed from: io.grpc.internal.TransportTracer$Factory */
    public static final class Factory {
        private final TimeProvider timeProvider;

        public Factory(TimeProvider timeProvider2) {
            this.timeProvider = timeProvider2;
        }

        public TransportTracer create() {
            return new TransportTracer(this.timeProvider);
        }
    }

    public static Factory getDefaultFactory() {
        return DEFAULT_FACTORY;
    }
}
