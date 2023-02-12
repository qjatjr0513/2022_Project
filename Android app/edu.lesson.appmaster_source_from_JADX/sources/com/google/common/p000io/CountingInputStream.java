package com.google.common.p000io;

import com.google.common.base.Preconditions;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.google.common.io.CountingInputStream */
public final class CountingInputStream extends FilterInputStream {
    private long count;
    private long mark = -1;

    public CountingInputStream(InputStream in) {
        super((InputStream) Preconditions.checkNotNull(in));
    }

    public long getCount() {
        return this.count;
    }

    public int read() throws IOException {
        int result = this.in.read();
        if (result != -1) {
            this.count++;
        }
        return result;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int result = this.in.read(b, off, len);
        if (result != -1) {
            this.count += (long) result;
        }
        return result;
    }

    public long skip(long n) throws IOException {
        long result = this.in.skip(n);
        this.count += result;
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
}
