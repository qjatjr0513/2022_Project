package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import javax.annotation.Nullable;
import p004io.grpc.Codec;
import p004io.grpc.Decompressor;
import p004io.grpc.Status;
import p004io.grpc.internal.StreamListener;

/* renamed from: io.grpc.internal.MessageDeframer */
public class MessageDeframer implements Closeable, Deframer {
    private static final int COMPRESSED_FLAG_MASK = 1;
    private static final int HEADER_LENGTH = 5;
    private static final int MAX_BUFFER_SIZE = 2097152;
    private static final int RESERVED_MASK = 254;
    private boolean closeWhenComplete = false;
    private boolean compressedFlag;
    private int currentMessageSeqNo = -1;
    private Decompressor decompressor;
    private GzipInflatingBuffer fullStreamDecompressor;
    private boolean inDelivery = false;
    private int inboundBodyWireSize;
    private byte[] inflatedBuffer;
    private int inflatedIndex;
    private Listener listener;
    private int maxInboundMessageSize;
    private CompositeReadableBuffer nextFrame;
    private long pendingDeliveries;
    private int requiredLength = 5;
    private State state = State.HEADER;
    private final StatsTraceContext statsTraceCtx;
    private volatile boolean stopDelivery = false;
    private final TransportTracer transportTracer;
    private CompositeReadableBuffer unprocessed = new CompositeReadableBuffer();

    /* renamed from: io.grpc.internal.MessageDeframer$Listener */
    public interface Listener {
        void bytesRead(int i);

        void deframeFailed(Throwable th);

        void deframerClosed(boolean z);

        void messagesAvailable(StreamListener.MessageProducer messageProducer);
    }

    /* renamed from: io.grpc.internal.MessageDeframer$State */
    private enum State {
        HEADER,
        BODY
    }

    public MessageDeframer(Listener listener2, Decompressor decompressor2, int maxMessageSize, StatsTraceContext statsTraceCtx2, TransportTracer transportTracer2) {
        this.listener = (Listener) Preconditions.checkNotNull(listener2, "sink");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor2, "decompressor");
        this.maxInboundMessageSize = maxMessageSize;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceCtx2, "statsTraceCtx");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2, "transportTracer");
    }

    /* access modifiers changed from: package-private */
    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    public void setMaxInboundMessageSize(int messageSize) {
        this.maxInboundMessageSize = messageSize;
    }

    public void setDecompressor(Decompressor decompressor2) {
        Preconditions.checkState(this.fullStreamDecompressor == null, "Already set full stream decompressor");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor2, "Can't pass an empty decompressor");
    }

    public void setFullStreamDecompressor(GzipInflatingBuffer fullStreamDecompressor2) {
        boolean z = true;
        Preconditions.checkState(this.decompressor == Codec.Identity.NONE, "per-message decompressor already set");
        if (this.fullStreamDecompressor != null) {
            z = false;
        }
        Preconditions.checkState(z, "full stream decompressor already set");
        this.fullStreamDecompressor = (GzipInflatingBuffer) Preconditions.checkNotNull(fullStreamDecompressor2, "Can't pass a null full stream decompressor");
        this.unprocessed = null;
    }

    public void request(int numMessages) {
        Preconditions.checkArgument(numMessages > 0, "numMessages must be > 0");
        if (!isClosed()) {
            this.pendingDeliveries += (long) numMessages;
            deliver();
        }
    }

    public void deframe(ReadableBuffer data) {
        Preconditions.checkNotNull(data, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        boolean needToCloseData = true;
        try {
            if (!isClosedOrScheduledToClose()) {
                GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
                if (gzipInflatingBuffer != null) {
                    gzipInflatingBuffer.addGzippedBytes(data);
                } else {
                    this.unprocessed.addBuffer(data);
                }
                needToCloseData = false;
                deliver();
            }
        } finally {
            if (needToCloseData) {
                data.close();
            }
        }
    }

    public void closeWhenComplete() {
        if (!isClosed()) {
            if (isStalled()) {
                close();
            } else {
                this.closeWhenComplete = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void stopDelivery() {
        this.stopDelivery = true;
    }

    /* access modifiers changed from: package-private */
    public boolean hasPendingDeliveries() {
        return this.pendingDeliveries != 0;
    }

    /* JADX INFO: finally extract failed */
    public void close() {
        if (!isClosed()) {
            CompositeReadableBuffer compositeReadableBuffer = this.nextFrame;
            boolean z = true;
            boolean hasPartialMessage = compositeReadableBuffer != null && compositeReadableBuffer.readableBytes() > 0;
            try {
                GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
                if (gzipInflatingBuffer != null) {
                    if (!hasPartialMessage) {
                        if (!gzipInflatingBuffer.hasPartialData()) {
                            z = false;
                        }
                    }
                    hasPartialMessage = z;
                    this.fullStreamDecompressor.close();
                }
                CompositeReadableBuffer compositeReadableBuffer2 = this.unprocessed;
                if (compositeReadableBuffer2 != null) {
                    compositeReadableBuffer2.close();
                }
                CompositeReadableBuffer compositeReadableBuffer3 = this.nextFrame;
                if (compositeReadableBuffer3 != null) {
                    compositeReadableBuffer3.close();
                }
                this.fullStreamDecompressor = null;
                this.unprocessed = null;
                this.nextFrame = null;
                this.listener.deframerClosed(hasPartialMessage);
            } catch (Throwable th) {
                this.fullStreamDecompressor = null;
                this.unprocessed = null;
                this.nextFrame = null;
                throw th;
            }
        }
    }

    public boolean isClosed() {
        return this.unprocessed == null && this.fullStreamDecompressor == null;
    }

    private boolean isClosedOrScheduledToClose() {
        return isClosed() || this.closeWhenComplete;
    }

    private boolean isStalled() {
        GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
        if (gzipInflatingBuffer != null) {
            return gzipInflatingBuffer.isStalled();
        }
        return this.unprocessed.readableBytes() == 0;
    }

    private void deliver() {
        if (!this.inDelivery) {
            this.inDelivery = true;
            while (!this.stopDelivery && this.pendingDeliveries > 0 && readRequiredBytes()) {
                try {
                    switch (C12801.$SwitchMap$io$grpc$internal$MessageDeframer$State[this.state.ordinal()]) {
                        case 1:
                            processHeader();
                            break;
                        case 2:
                            processBody();
                            this.pendingDeliveries--;
                            break;
                        default:
                            throw new AssertionError("Invalid state: " + this.state);
                    }
                } finally {
                    this.inDelivery = false;
                }
            }
            if (this.stopDelivery) {
                close();
                return;
            }
            if (this.closeWhenComplete && isStalled()) {
                close();
            }
            this.inDelivery = false;
        }
    }

    /* renamed from: io.grpc.internal.MessageDeframer$1 */
    static /* synthetic */ class C12801 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$internal$MessageDeframer$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$grpc$internal$MessageDeframer$State = iArr;
            try {
                iArr[State.HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$io$grpc$internal$MessageDeframer$State[State.BODY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private boolean readRequiredBytes() {
        int totalBytesRead = 0;
        int deflatedBytesRead = 0;
        try {
            if (this.nextFrame == null) {
                this.nextFrame = new CompositeReadableBuffer();
            }
            while (true) {
                int readableBytes = this.requiredLength - this.nextFrame.readableBytes();
                int missingBytes = readableBytes;
                if (readableBytes <= 0) {
                    if (totalBytesRead > 0) {
                        this.listener.bytesRead(totalBytesRead);
                        if (this.state == State.BODY) {
                            if (this.fullStreamDecompressor != null) {
                                this.statsTraceCtx.inboundWireSize((long) deflatedBytesRead);
                                this.inboundBodyWireSize += deflatedBytesRead;
                            } else {
                                this.statsTraceCtx.inboundWireSize((long) totalBytesRead);
                                this.inboundBodyWireSize += totalBytesRead;
                            }
                        }
                    }
                    return true;
                } else if (this.fullStreamDecompressor != null) {
                    byte[] bArr = this.inflatedBuffer;
                    if (bArr == null || this.inflatedIndex == bArr.length) {
                        this.inflatedBuffer = new byte[Math.min(missingBytes, 2097152)];
                        this.inflatedIndex = 0;
                    }
                    int n = this.fullStreamDecompressor.inflateBytes(this.inflatedBuffer, this.inflatedIndex, Math.min(missingBytes, this.inflatedBuffer.length - this.inflatedIndex));
                    totalBytesRead += this.fullStreamDecompressor.getAndResetBytesConsumed();
                    deflatedBytesRead += this.fullStreamDecompressor.getAndResetDeflatedBytesConsumed();
                    if (n == 0) {
                        if (totalBytesRead > 0) {
                            this.listener.bytesRead(totalBytesRead);
                            if (this.state == State.BODY) {
                                if (this.fullStreamDecompressor != null) {
                                    this.statsTraceCtx.inboundWireSize((long) deflatedBytesRead);
                                    this.inboundBodyWireSize += deflatedBytesRead;
                                } else {
                                    this.statsTraceCtx.inboundWireSize((long) totalBytesRead);
                                    this.inboundBodyWireSize += totalBytesRead;
                                }
                            }
                        }
                        return false;
                    }
                    this.nextFrame.addBuffer(ReadableBuffers.wrap(this.inflatedBuffer, this.inflatedIndex, n));
                    this.inflatedIndex += n;
                } else if (this.unprocessed.readableBytes() == 0) {
                    if (totalBytesRead > 0) {
                        this.listener.bytesRead(totalBytesRead);
                        if (this.state == State.BODY) {
                            if (this.fullStreamDecompressor != null) {
                                this.statsTraceCtx.inboundWireSize((long) deflatedBytesRead);
                                this.inboundBodyWireSize += deflatedBytesRead;
                            } else {
                                this.statsTraceCtx.inboundWireSize((long) totalBytesRead);
                                this.inboundBodyWireSize += totalBytesRead;
                            }
                        }
                    }
                    return false;
                } else {
                    int toRead = Math.min(missingBytes, this.unprocessed.readableBytes());
                    totalBytesRead += toRead;
                    this.nextFrame.addBuffer(this.unprocessed.readBytes(toRead));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DataFormatException e2) {
            throw new RuntimeException(e2);
        } catch (Throwable th) {
            if (totalBytesRead > 0) {
                this.listener.bytesRead(totalBytesRead);
                if (this.state == State.BODY) {
                    if (this.fullStreamDecompressor != null) {
                        this.statsTraceCtx.inboundWireSize((long) deflatedBytesRead);
                        this.inboundBodyWireSize += deflatedBytesRead;
                    } else {
                        this.statsTraceCtx.inboundWireSize((long) totalBytesRead);
                        this.inboundBodyWireSize += totalBytesRead;
                    }
                }
            }
            throw th;
        }
    }

    private void processHeader() {
        int type = this.nextFrame.readUnsignedByte();
        if ((type & RESERVED_MASK) == 0) {
            this.compressedFlag = (type & 1) != 0;
            int readInt = this.nextFrame.readInt();
            this.requiredLength = readInt;
            if (readInt < 0 || readInt > this.maxInboundMessageSize) {
                throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("gRPC message exceeds maximum size %d: %d", new Object[]{Integer.valueOf(this.maxInboundMessageSize), Integer.valueOf(this.requiredLength)})).asRuntimeException();
            }
            int i = this.currentMessageSeqNo + 1;
            this.currentMessageSeqNo = i;
            this.statsTraceCtx.inboundMessage(i);
            this.transportTracer.reportMessageReceived();
            this.state = State.BODY;
            return;
        }
        throw Status.INTERNAL.withDescription("gRPC frame header malformed: reserved bits not zero").asRuntimeException();
    }

    private void processBody() {
        this.statsTraceCtx.inboundMessageRead(this.currentMessageSeqNo, (long) this.inboundBodyWireSize, -1);
        this.inboundBodyWireSize = 0;
        InputStream stream = this.compressedFlag ? getCompressedBody() : getUncompressedBody();
        this.nextFrame = null;
        this.listener.messagesAvailable(new SingleMessageProducer(stream, (C12801) null));
        this.state = State.HEADER;
        this.requiredLength = 5;
    }

    private InputStream getUncompressedBody() {
        this.statsTraceCtx.inboundUncompressedSize((long) this.nextFrame.readableBytes());
        return ReadableBuffers.openStream(this.nextFrame, true);
    }

    private InputStream getCompressedBody() {
        if (this.decompressor != Codec.Identity.NONE) {
            try {
                return new SizeEnforcingInputStream(this.decompressor.decompress(ReadableBuffers.openStream(this.nextFrame, true)), this.maxInboundMessageSize, this.statsTraceCtx);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw Status.INTERNAL.withDescription("Can't decode compressed gRPC message as compression not configured").asRuntimeException();
        }
    }

    /* renamed from: io.grpc.internal.MessageDeframer$SizeEnforcingInputStream */
    static final class SizeEnforcingInputStream extends FilterInputStream {
        private long count;
        private long mark = -1;
        private long maxCount;
        private final int maxMessageSize;
        private final StatsTraceContext statsTraceCtx;

        SizeEnforcingInputStream(InputStream in, int maxMessageSize2, StatsTraceContext statsTraceCtx2) {
            super(in);
            this.maxMessageSize = maxMessageSize2;
            this.statsTraceCtx = statsTraceCtx2;
        }

        public int read() throws IOException {
            int result = this.in.read();
            if (result != -1) {
                this.count++;
            }
            verifySize();
            reportCount();
            return result;
        }

        public int read(byte[] b, int off, int len) throws IOException {
            int result = this.in.read(b, off, len);
            if (result != -1) {
                this.count += (long) result;
            }
            verifySize();
            reportCount();
            return result;
        }

        public long skip(long n) throws IOException {
            long result = this.in.skip(n);
            this.count += result;
            verifySize();
            reportCount();
            return result;
        }

        public synchronized void mark(int readlimit) {
            this.in.mark(readlimit);
            this.mark = this.count;
        }

        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            } else if (this.mark != -1) {
                this.in.reset();
                this.count = this.mark;
            } else {
                throw new IOException("Mark not set");
            }
        }

        private void reportCount() {
            long j = this.count;
            long j2 = this.maxCount;
            if (j > j2) {
                this.statsTraceCtx.inboundUncompressedSize(j - j2);
                this.maxCount = this.count;
            }
        }

        private void verifySize() {
            if (this.count > ((long) this.maxMessageSize)) {
                throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("Decompressed gRPC message exceeds maximum size %d", new Object[]{Integer.valueOf(this.maxMessageSize)})).asRuntimeException();
            }
        }
    }

    /* renamed from: io.grpc.internal.MessageDeframer$SingleMessageProducer */
    private static class SingleMessageProducer implements StreamListener.MessageProducer {
        private InputStream message;

        /* synthetic */ SingleMessageProducer(InputStream x0, C12801 x1) {
            this(x0);
        }

        private SingleMessageProducer(InputStream message2) {
            this.message = message2;
        }

        @Nullable
        public InputStream next() {
            InputStream messageToReturn = this.message;
            this.message = null;
            return messageToReturn;
        }
    }
}
