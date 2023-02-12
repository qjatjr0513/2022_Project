package p004io.grpc.okhttp;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.ByteString;
import p004io.grpc.okhttp.OkHttpFrameLogger;
import p004io.grpc.okhttp.internal.framed.ErrorCode;
import p004io.grpc.okhttp.internal.framed.FrameWriter;
import p004io.grpc.okhttp.internal.framed.Header;
import p004io.grpc.okhttp.internal.framed.Settings;

/* renamed from: io.grpc.okhttp.ExceptionHandlingFrameWriter */
final class ExceptionHandlingFrameWriter implements FrameWriter {
    private static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    private final OkHttpFrameLogger frameLogger;
    private final FrameWriter frameWriter;
    private final TransportExceptionHandler transportExceptionHandler;

    /* renamed from: io.grpc.okhttp.ExceptionHandlingFrameWriter$TransportExceptionHandler */
    interface TransportExceptionHandler {
        void onException(Throwable th);
    }

    ExceptionHandlingFrameWriter(TransportExceptionHandler transportExceptionHandler2, FrameWriter frameWriter2) {
        this(transportExceptionHandler2, frameWriter2, new OkHttpFrameLogger(Level.FINE, (Class<?>) OkHttpClientTransport.class));
    }

    ExceptionHandlingFrameWriter(TransportExceptionHandler transportExceptionHandler2, FrameWriter frameWriter2, OkHttpFrameLogger frameLogger2) {
        this.transportExceptionHandler = (TransportExceptionHandler) Preconditions.checkNotNull(transportExceptionHandler2, "transportExceptionHandler");
        this.frameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter2, "frameWriter");
        this.frameLogger = (OkHttpFrameLogger) Preconditions.checkNotNull(frameLogger2, "frameLogger");
    }

    public void connectionPreface() {
        try {
            this.frameWriter.connectionPreface();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void ackSettings(Settings peerSettings) {
        this.frameLogger.logSettingsAck(OkHttpFrameLogger.Direction.OUTBOUND);
        try {
            this.frameWriter.ackSettings(peerSettings);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) {
        this.frameLogger.logPushPromise(OkHttpFrameLogger.Direction.OUTBOUND, streamId, promisedStreamId, requestHeaders);
        try {
            this.frameWriter.pushPromise(streamId, promisedStreamId, requestHeaders);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void flush() {
        try {
            this.frameWriter.flush();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void synStream(boolean outFinished, boolean inFinished, int streamId, int associatedStreamId, List<Header> headerBlock) {
        try {
            this.frameWriter.synStream(outFinished, inFinished, streamId, associatedStreamId, headerBlock);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void synReply(boolean outFinished, int streamId, List<Header> headerBlock) {
        try {
            this.frameWriter.synReply(outFinished, streamId, headerBlock);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void headers(int streamId, List<Header> headerBlock) {
        this.frameLogger.logHeaders(OkHttpFrameLogger.Direction.OUTBOUND, streamId, headerBlock, false);
        try {
            this.frameWriter.headers(streamId, headerBlock);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void rstStream(int streamId, ErrorCode errorCode) {
        this.frameLogger.logRstStream(OkHttpFrameLogger.Direction.OUTBOUND, streamId, errorCode);
        try {
            this.frameWriter.rstStream(streamId, errorCode);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public int maxDataLength() {
        return this.frameWriter.maxDataLength();
    }

    public void data(boolean outFinished, int streamId, Buffer source, int byteCount) {
        this.frameLogger.logData(OkHttpFrameLogger.Direction.OUTBOUND, streamId, source.buffer(), byteCount, outFinished);
        try {
            this.frameWriter.data(outFinished, streamId, source, byteCount);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void settings(Settings okHttpSettings) {
        this.frameLogger.logSettings(OkHttpFrameLogger.Direction.OUTBOUND, okHttpSettings);
        try {
            this.frameWriter.settings(okHttpSettings);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void ping(boolean ack, int payload1, int payload2) {
        if (ack) {
            this.frameLogger.logPingAck(OkHttpFrameLogger.Direction.OUTBOUND, (4294967295L & ((long) payload2)) | (((long) payload1) << 32));
        } else {
            this.frameLogger.logPing(OkHttpFrameLogger.Direction.OUTBOUND, (4294967295L & ((long) payload2)) | (((long) payload1) << 32));
        }
        try {
            this.frameWriter.ping(ack, payload1, payload2);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void goAway(int lastGoodStreamId, ErrorCode errorCode, byte[] debugData) {
        this.frameLogger.logGoAway(OkHttpFrameLogger.Direction.OUTBOUND, lastGoodStreamId, errorCode, ByteString.m355of(debugData));
        try {
            this.frameWriter.goAway(lastGoodStreamId, errorCode, debugData);
            this.frameWriter.flush();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void windowUpdate(int streamId, long windowSizeIncrement) {
        this.frameLogger.logWindowsUpdate(OkHttpFrameLogger.Direction.OUTBOUND, streamId, windowSizeIncrement);
        try {
            this.frameWriter.windowUpdate(streamId, windowSizeIncrement);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    public void close() {
        try {
            this.frameWriter.close();
        } catch (IOException e) {
            log.log(getLogLevel(e), "Failed closing connection", e);
        }
    }

    static Level getLogLevel(Throwable t) {
        if (t.getClass().equals(IOException.class)) {
            return Level.FINE;
        }
        return Level.INFO;
    }
}
