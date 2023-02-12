package okio;

import java.io.IOException;

final class PeekSource implements Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment;
    private long pos;
    private final BufferedSource upstream;

    PeekSource(BufferedSource upstream2) {
        this.upstream = upstream2;
        Buffer buffer2 = upstream2.buffer();
        this.buffer = buffer2;
        Segment segment = buffer2.head;
        this.expectedSegment = segment;
        this.expectedPos = segment != null ? segment.pos : -1;
    }

    public long read(Buffer sink, long byteCount) throws IOException {
        if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        } else if (!this.closed) {
            Segment segment = this.expectedSegment;
            if (segment != null && (segment != this.buffer.head || this.expectedPos != this.buffer.head.pos)) {
                throw new IllegalStateException("Peek source is invalid because upstream source was used");
            } else if (byteCount == 0) {
                return 0;
            } else {
                if (!this.upstream.request(this.pos + 1)) {
                    return -1;
                }
                if (this.expectedSegment == null && this.buffer.head != null) {
                    this.expectedSegment = this.buffer.head;
                    this.expectedPos = this.buffer.head.pos;
                }
                long toCopy = Math.min(byteCount, this.buffer.size - this.pos);
                this.buffer.copyTo(sink, this.pos, toCopy);
                this.pos += toCopy;
                return toCopy;
            }
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public Timeout timeout() {
        return this.upstream.timeout();
    }

    public void close() throws IOException {
        this.closed = true;
    }
}
