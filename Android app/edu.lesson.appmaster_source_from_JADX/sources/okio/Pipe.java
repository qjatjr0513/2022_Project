package okio;

import java.io.IOException;
import javax.annotation.Nullable;

public final class Pipe {
    final Buffer buffer = new Buffer();
    /* access modifiers changed from: private */
    @Nullable
    public Sink foldedSink;
    final long maxBufferSize;
    private final Sink sink = new PipeSink();
    boolean sinkClosed;
    private final Source source = new PipeSource();
    boolean sourceClosed;

    public Pipe(long maxBufferSize2) {
        if (maxBufferSize2 >= 1) {
            this.maxBufferSize = maxBufferSize2;
            return;
        }
        throw new IllegalArgumentException("maxBufferSize < 1: " + maxBufferSize2);
    }

    public final Source source() {
        return this.source;
    }

    public final Sink sink() {
        return this.sink;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r8.write(r2, r2.size);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        if (r0 == false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        r8.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        if (1 != 0) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
        r4 = r7.buffer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r7.sourceClosed = true;
        r7.buffer.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004e, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004f, code lost:
        if (0 == 0) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0053, code lost:
        monitor-enter(r7.buffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r7.sourceClosed = true;
        r7.buffer.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0060, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fold(okio.Sink r8) throws java.io.IOException {
        /*
            r7 = this;
        L_0x0000:
            r0 = 0
            okio.Buffer r1 = r7.buffer
            monitor-enter(r1)
            okio.Sink r2 = r7.foldedSink     // Catch:{ all -> 0x0069 }
            if (r2 != 0) goto L_0x0061
            okio.Buffer r2 = r7.buffer     // Catch:{ all -> 0x0069 }
            boolean r2 = r2.exhausted()     // Catch:{ all -> 0x0069 }
            r3 = 1
            if (r2 == 0) goto L_0x0017
            r7.sourceClosed = r3     // Catch:{ all -> 0x0069 }
            r7.foldedSink = r8     // Catch:{ all -> 0x0069 }
            monitor-exit(r1)     // Catch:{ all -> 0x0069 }
            return
        L_0x0017:
            boolean r2 = r7.sinkClosed     // Catch:{ all -> 0x0069 }
            r0 = r2
            okio.Buffer r2 = new okio.Buffer     // Catch:{ all -> 0x0069 }
            r2.<init>()     // Catch:{ all -> 0x0069 }
            okio.Buffer r4 = r7.buffer     // Catch:{ all -> 0x0069 }
            long r5 = r4.size     // Catch:{ all -> 0x0069 }
            r2.write((okio.Buffer) r4, (long) r5)     // Catch:{ all -> 0x0069 }
            okio.Buffer r4 = r7.buffer     // Catch:{ all -> 0x0069 }
            r4.notifyAll()     // Catch:{ all -> 0x0069 }
            monitor-exit(r1)     // Catch:{ all -> 0x0069 }
            r1 = 0
            long r4 = r2.size     // Catch:{ all -> 0x004e }
            r8.write(r2, r4)     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x0038
            r8.close()     // Catch:{ all -> 0x004e }
            goto L_0x003b
        L_0x0038:
            r8.flush()     // Catch:{ all -> 0x004e }
        L_0x003b:
            r1 = 1
            if (r1 != 0) goto L_0x004d
            okio.Buffer r4 = r7.buffer
            monitor-enter(r4)
            r7.sourceClosed = r3     // Catch:{ all -> 0x004a }
            okio.Buffer r3 = r7.buffer     // Catch:{ all -> 0x004a }
            r3.notifyAll()     // Catch:{ all -> 0x004a }
            monitor-exit(r4)     // Catch:{ all -> 0x004a }
            goto L_0x004d
        L_0x004a:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x004a }
            throw r3
        L_0x004d:
            goto L_0x0000
        L_0x004e:
            r4 = move-exception
            if (r1 != 0) goto L_0x0060
            okio.Buffer r5 = r7.buffer
            monitor-enter(r5)
            r7.sourceClosed = r3     // Catch:{ all -> 0x005d }
            okio.Buffer r3 = r7.buffer     // Catch:{ all -> 0x005d }
            r3.notifyAll()     // Catch:{ all -> 0x005d }
            monitor-exit(r5)     // Catch:{ all -> 0x005d }
            goto L_0x0060
        L_0x005d:
            r3 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x005d }
            throw r3
        L_0x0060:
            throw r4
        L_0x0061:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0069 }
            java.lang.String r3 = "sink already folded"
            r2.<init>(r3)     // Catch:{ all -> 0x0069 }
            throw r2     // Catch:{ all -> 0x0069 }
        L_0x0069:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0069 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Pipe.fold(okio.Sink):void");
    }

    final class PipeSink implements Sink {
        final PushableTimeout timeout = new PushableTimeout();

        PipeSink() {
        }

        public void write(Buffer source, long byteCount) throws IOException {
            Sink delegate = null;
            synchronized (Pipe.this.buffer) {
                if (!Pipe.this.sinkClosed) {
                    while (true) {
                        if (byteCount <= 0) {
                            break;
                        } else if (Pipe.this.foldedSink != null) {
                            delegate = Pipe.this.foldedSink;
                            break;
                        } else if (!Pipe.this.sourceClosed) {
                            long bufferSpaceAvailable = Pipe.this.maxBufferSize - Pipe.this.buffer.size();
                            if (bufferSpaceAvailable == 0) {
                                this.timeout.waitUntilNotified(Pipe.this.buffer);
                            } else {
                                long bytesToWrite = Math.min(bufferSpaceAvailable, byteCount);
                                Pipe.this.buffer.write(source, bytesToWrite);
                                byteCount -= bytesToWrite;
                                Pipe.this.buffer.notifyAll();
                            }
                        } else {
                            throw new IOException("source is closed");
                        }
                    }
                } else {
                    throw new IllegalStateException("closed");
                }
            }
            if (delegate != null) {
                this.timeout.push(delegate.timeout());
                try {
                    delegate.write(source, byteCount);
                } finally {
                    this.timeout.pop();
                }
            }
        }

        public void flush() throws IOException {
            Sink delegate = null;
            synchronized (Pipe.this.buffer) {
                if (Pipe.this.sinkClosed) {
                    throw new IllegalStateException("closed");
                } else if (Pipe.this.foldedSink != null) {
                    delegate = Pipe.this.foldedSink;
                } else if (Pipe.this.sourceClosed) {
                    if (Pipe.this.buffer.size() > 0) {
                        throw new IOException("source is closed");
                    }
                }
            }
            if (delegate != null) {
                this.timeout.push(delegate.timeout());
                try {
                    delegate.flush();
                } finally {
                    this.timeout.pop();
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
            if (r0 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x004a, code lost:
            r6.timeout.push(r0.timeout());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x005c, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x005d, code lost:
            r6.timeout.pop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0062, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r6 = this;
                r0 = 0
                okio.Pipe r1 = okio.Pipe.this
                okio.Buffer r1 = r1.buffer
                monitor-enter(r1)
                okio.Pipe r2 = okio.Pipe.this     // Catch:{ all -> 0x0064 }
                boolean r2 = r2.sinkClosed     // Catch:{ all -> 0x0064 }
                if (r2 == 0) goto L_0x000e
                monitor-exit(r1)     // Catch:{ all -> 0x0064 }
                return
            L_0x000e:
                okio.Pipe r2 = okio.Pipe.this     // Catch:{ all -> 0x0064 }
                okio.Sink r2 = r2.foldedSink     // Catch:{ all -> 0x0064 }
                if (r2 == 0) goto L_0x001e
                okio.Pipe r2 = okio.Pipe.this     // Catch:{ all -> 0x0064 }
                okio.Sink r2 = r2.foldedSink     // Catch:{ all -> 0x0064 }
                r0 = r2
                goto L_0x0047
            L_0x001e:
                okio.Pipe r2 = okio.Pipe.this     // Catch:{ all -> 0x0064 }
                boolean r2 = r2.sourceClosed     // Catch:{ all -> 0x0064 }
                if (r2 == 0) goto L_0x003b
                okio.Pipe r2 = okio.Pipe.this     // Catch:{ all -> 0x0064 }
                okio.Buffer r2 = r2.buffer     // Catch:{ all -> 0x0064 }
                long r2 = r2.size()     // Catch:{ all -> 0x0064 }
                r4 = 0
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 > 0) goto L_0x0033
                goto L_0x003b
            L_0x0033:
                java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0064 }
                java.lang.String r3 = "source is closed"
                r2.<init>(r3)     // Catch:{ all -> 0x0064 }
                throw r2     // Catch:{ all -> 0x0064 }
            L_0x003b:
                okio.Pipe r2 = okio.Pipe.this     // Catch:{ all -> 0x0064 }
                r3 = 1
                r2.sinkClosed = r3     // Catch:{ all -> 0x0064 }
                okio.Pipe r2 = okio.Pipe.this     // Catch:{ all -> 0x0064 }
                okio.Buffer r2 = r2.buffer     // Catch:{ all -> 0x0064 }
                r2.notifyAll()     // Catch:{ all -> 0x0064 }
            L_0x0047:
                monitor-exit(r1)     // Catch:{ all -> 0x0064 }
                if (r0 == 0) goto L_0x0063
                okio.PushableTimeout r1 = r6.timeout
                okio.Timeout r2 = r0.timeout()
                r1.push(r2)
                r0.close()     // Catch:{ all -> 0x005c }
                okio.PushableTimeout r1 = r6.timeout
                r1.pop()
                goto L_0x0063
            L_0x005c:
                r1 = move-exception
                okio.PushableTimeout r2 = r6.timeout
                r2.pop()
                throw r1
            L_0x0063:
                return
            L_0x0064:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0064 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.Pipe.PipeSink.close():void");
        }

        public Timeout timeout() {
            return this.timeout;
        }
    }

    final class PipeSource implements Source {
        final Timeout timeout = new Timeout();

        PipeSource() {
        }

        public long read(Buffer sink, long byteCount) throws IOException {
            synchronized (Pipe.this.buffer) {
                if (!Pipe.this.sourceClosed) {
                    while (Pipe.this.buffer.size() == 0) {
                        if (Pipe.this.sinkClosed) {
                            return -1;
                        }
                        this.timeout.waitUntilNotified(Pipe.this.buffer);
                    }
                    long result = Pipe.this.buffer.read(sink, byteCount);
                    Pipe.this.buffer.notifyAll();
                    return result;
                }
                throw new IllegalStateException("closed");
            }
        }

        public void close() throws IOException {
            synchronized (Pipe.this.buffer) {
                Pipe.this.sourceClosed = true;
                Pipe.this.buffer.notifyAll();
            }
        }

        public Timeout timeout() {
            return this.timeout;
        }
    }
}
