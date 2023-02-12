package com.android.volley.toolbox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ByteArrayPool {
    protected static final Comparator<byte[]> BUF_COMPARATOR = new Comparator<byte[]>() {
        public int compare(byte[] lhs, byte[] rhs) {
            return lhs.length - rhs.length;
        }
    };
    private final List<byte[]> mBuffersByLastUse = new ArrayList();
    private final List<byte[]> mBuffersBySize = new ArrayList(64);
    private int mCurrentSize = 0;
    private final int mSizeLimit;

    public ByteArrayPool(int sizeLimit) {
        this.mSizeLimit = sizeLimit;
    }

    public synchronized byte[] getBuf(int len) {
        for (int i = 0; i < this.mBuffersBySize.size(); i++) {
            byte[] buf = this.mBuffersBySize.get(i);
            if (buf.length >= len) {
                this.mCurrentSize -= buf.length;
                this.mBuffersBySize.remove(i);
                this.mBuffersByLastUse.remove(buf);
                return buf;
            }
        }
        return new byte[len];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void returnBuf(byte[] r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 == 0) goto L_0x002e
            int r0 = r4.length     // Catch:{ all -> 0x002b }
            int r1 = r3.mSizeLimit     // Catch:{ all -> 0x002b }
            if (r0 <= r1) goto L_0x0009
            goto L_0x002e
        L_0x0009:
            java.util.List<byte[]> r0 = r3.mBuffersByLastUse     // Catch:{ all -> 0x002b }
            r0.add(r4)     // Catch:{ all -> 0x002b }
            java.util.List<byte[]> r0 = r3.mBuffersBySize     // Catch:{ all -> 0x002b }
            java.util.Comparator<byte[]> r1 = BUF_COMPARATOR     // Catch:{ all -> 0x002b }
            int r0 = java.util.Collections.binarySearch(r0, r4, r1)     // Catch:{ all -> 0x002b }
            if (r0 >= 0) goto L_0x001b
            int r1 = -r0
            int r0 = r1 + -1
        L_0x001b:
            java.util.List<byte[]> r1 = r3.mBuffersBySize     // Catch:{ all -> 0x002b }
            r1.add(r0, r4)     // Catch:{ all -> 0x002b }
            int r1 = r3.mCurrentSize     // Catch:{ all -> 0x002b }
            int r2 = r4.length     // Catch:{ all -> 0x002b }
            int r1 = r1 + r2
            r3.mCurrentSize = r1     // Catch:{ all -> 0x002b }
            r3.trim()     // Catch:{ all -> 0x002b }
            monitor-exit(r3)
            return
        L_0x002b:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x002e:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.ByteArrayPool.returnBuf(byte[]):void");
    }

    private synchronized void trim() {
        while (this.mCurrentSize > this.mSizeLimit) {
            byte[] buf = this.mBuffersByLastUse.remove(0);
            this.mBuffersBySize.remove(buf);
            this.mCurrentSize -= buf.length;
        }
    }
}
