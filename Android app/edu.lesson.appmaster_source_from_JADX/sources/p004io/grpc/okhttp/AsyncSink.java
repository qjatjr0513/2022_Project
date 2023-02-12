package p004io.grpc.okhttp;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.Socket;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;
import p004io.grpc.internal.SerializingExecutor;
import p004io.grpc.okhttp.ExceptionHandlingFrameWriter;
import p004io.perfmark.Link;
import p004io.perfmark.PerfMark;

/* renamed from: io.grpc.okhttp.AsyncSink */
final class AsyncSink implements Sink {
    /* access modifiers changed from: private */
    public final Buffer buffer = new Buffer();
    private boolean closed = false;
    /* access modifiers changed from: private */
    public boolean flushEnqueued = false;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final SerializingExecutor serializingExecutor;
    /* access modifiers changed from: private */
    @Nullable
    public Sink sink;
    /* access modifiers changed from: private */
    @Nullable
    public Socket socket;
    /* access modifiers changed from: private */
    public final ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler;
    /* access modifiers changed from: private */
    public boolean writeEnqueued = false;

    private AsyncSink(SerializingExecutor executor, ExceptionHandlingFrameWriter.TransportExceptionHandler exceptionHandler) {
        this.serializingExecutor = (SerializingExecutor) Preconditions.checkNotNull(executor, "executor");
        this.transportExceptionHandler = (ExceptionHandlingFrameWriter.TransportExceptionHandler) Preconditions.checkNotNull(exceptionHandler, "exceptionHandler");
    }

    static AsyncSink sink(SerializingExecutor executor, ExceptionHandlingFrameWriter.TransportExceptionHandler exceptionHandler) {
        return new AsyncSink(executor, exceptionHandler);
    }

    /* access modifiers changed from: package-private */
    public void becomeConnected(Sink sink2, Socket socket2) {
        Preconditions.checkState(this.sink == null, "AsyncSink's becomeConnected should only be called once.");
        this.sink = (Sink) Preconditions.checkNotNull(sink2, "sink");
        this.socket = (Socket) Preconditions.checkNotNull(socket2, "socket");
    }

    public void write(Buffer source, long byteCount) throws IOException {
        Preconditions.checkNotNull(source, "source");
        if (!this.closed) {
            PerfMark.startTask("AsyncSink.write");
            try {
                synchronized (this.lock) {
                    this.buffer.write(source, byteCount);
                    if (!this.writeEnqueued && !this.flushEnqueued) {
                        if (this.buffer.completeSegmentByteCount() > 0) {
                            this.writeEnqueued = true;
                            this.serializingExecutor.execute(new WriteRunnable() {
                                final Link link = PerfMark.linkOut();

                                public void doRun() throws IOException {
                                    PerfMark.startTask("WriteRunnable.runWrite");
                                    PerfMark.linkIn(this.link);
                                    Buffer buf = new Buffer();
                                    try {
                                        synchronized (AsyncSink.this.lock) {
                                            buf.write(AsyncSink.this.buffer, AsyncSink.this.buffer.completeSegmentByteCount());
                                            boolean unused = AsyncSink.this.writeEnqueued = false;
                                        }
                                        AsyncSink.this.sink.write(buf, buf.size());
                                        PerfMark.stopTask("WriteRunnable.runWrite");
                                    } catch (Throwable th) {
                                        PerfMark.stopTask("WriteRunnable.runWrite");
                                        throw th;
                                    }
                                }
                            });
                            PerfMark.stopTask("AsyncSink.write");
                            return;
                        }
                    }
                    PerfMark.stopTask("AsyncSink.write");
                }
            } catch (Throwable th) {
                PerfMark.stopTask("AsyncSink.write");
                throw th;
            }
        } else {
            throw new IOException("closed");
        }
    }

    public void flush() throws IOException {
        if (!this.closed) {
            PerfMark.startTask("AsyncSink.flush");
            try {
                synchronized (this.lock) {
                    if (this.flushEnqueued) {
                        PerfMark.stopTask("AsyncSink.flush");
                        return;
                    }
                    this.flushEnqueued = true;
                    this.serializingExecutor.execute(new WriteRunnable() {
                        final Link link = PerfMark.linkOut();

                        public void doRun() throws IOException {
                            PerfMark.startTask("WriteRunnable.runFlush");
                            PerfMark.linkIn(this.link);
                            Buffer buf = new Buffer();
                            try {
                                synchronized (AsyncSink.this.lock) {
                                    buf.write(AsyncSink.this.buffer, AsyncSink.this.buffer.size());
                                    boolean unused = AsyncSink.this.flushEnqueued = false;
                                }
                                AsyncSink.this.sink.write(buf, buf.size());
                                AsyncSink.this.sink.flush();
                                PerfMark.stopTask("WriteRunnable.runFlush");
                            } catch (Throwable th) {
                                PerfMark.stopTask("WriteRunnable.runFlush");
                                throw th;
                            }
                        }
                    });
                    PerfMark.stopTask("AsyncSink.flush");
                }
            } catch (Throwable th) {
                PerfMark.stopTask("AsyncSink.flush");
                throw th;
            }
        } else {
            throw new IOException("closed");
        }
    }

    public Timeout timeout() {
        return Timeout.NONE;
    }

    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    AsyncSink.this.buffer.close();
                    try {
                        if (AsyncSink.this.sink != null) {
                            AsyncSink.this.sink.close();
                        }
                    } catch (IOException e) {
                        AsyncSink.this.transportExceptionHandler.onException(e);
                    }
                    try {
                        if (AsyncSink.this.socket != null) {
                            AsyncSink.this.socket.close();
                        }
                    } catch (IOException e2) {
                        AsyncSink.this.transportExceptionHandler.onException(e2);
                    }
                }
            });
        }
    }

    /* renamed from: io.grpc.okhttp.AsyncSink$WriteRunnable */
    private abstract class WriteRunnable implements Runnable {
        public abstract void doRun() throws IOException;

        private WriteRunnable() {
        }

        public final void run() {
            try {
                if (AsyncSink.this.sink != null) {
                    doRun();
                    return;
                }
                throw new IOException("Unable to perform write due to unavailable sink.");
            } catch (Exception e) {
                AsyncSink.this.transportExceptionHandler.onException(e);
            }
        }
    }
}
