package com.android.volley.toolbox;

import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.VolleyLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiskBasedCache implements Cache {
    private static final int CACHE_MAGIC = 538247942;
    private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
    static final float HYSTERESIS_FACTOR = 0.9f;
    private final Map<String, CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final FileSupplier mRootDirectorySupplier;
    private long mTotalSize;

    public interface FileSupplier {
        File get();
    }

    public DiskBasedCache(final File rootDirectory, int maxCacheSizeInBytes) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0;
        this.mRootDirectorySupplier = new FileSupplier() {
            public File get() {
                return rootDirectory;
            }
        };
        this.mMaxCacheSizeInBytes = maxCacheSizeInBytes;
    }

    public DiskBasedCache(FileSupplier rootDirectorySupplier, int maxCacheSizeInBytes) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0;
        this.mRootDirectorySupplier = rootDirectorySupplier;
        this.mMaxCacheSizeInBytes = maxCacheSizeInBytes;
    }

    public DiskBasedCache(File rootDirectory) {
        this(rootDirectory, (int) DEFAULT_DISK_USAGE_BYTES);
    }

    public DiskBasedCache(FileSupplier rootDirectorySupplier) {
        this(rootDirectorySupplier, (int) DEFAULT_DISK_USAGE_BYTES);
    }

    public synchronized void clear() {
        File[] files = this.mRootDirectorySupplier.get().listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        this.mEntries.clear();
        this.mTotalSize = 0;
        VolleyLog.m391d("Cache cleared.", new Object[0]);
    }

    public synchronized Cache.Entry get(String key) {
        CountingInputStream cis;
        CacheHeader entry = this.mEntries.get(key);
        if (entry == null) {
            return null;
        }
        File file = getFileForKey(key);
        try {
            cis = new CountingInputStream(new BufferedInputStream(createInputStream(file)), file.length());
            CacheHeader entryOnDisk = CacheHeader.readHeader(cis);
            if (!TextUtils.equals(key, entryOnDisk.key)) {
                VolleyLog.m391d("%s: key=%s, found=%s", file.getAbsolutePath(), key, entryOnDisk.key);
                removeEntry(key);
                cis.close();
                return null;
            }
            Cache.Entry cacheEntry = entry.toCacheEntry(streamToBytes(cis, cis.bytesRemaining()));
            cis.close();
            return cacheEntry;
        } catch (IOException e) {
            VolleyLog.m391d("%s: %s", file.getAbsolutePath(), e.toString());
            remove(key);
            return null;
        } catch (Throwable th) {
            cis.close();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void initialize() {
        /*
            r10 = this;
            monitor-enter(r10)
            com.android.volley.toolbox.DiskBasedCache$FileSupplier r0 = r10.mRootDirectorySupplier     // Catch:{ all -> 0x0061 }
            java.io.File r0 = r0.get()     // Catch:{ all -> 0x0061 }
            boolean r1 = r0.exists()     // Catch:{ all -> 0x0061 }
            r2 = 0
            if (r1 != 0) goto L_0x0024
            boolean r1 = r0.mkdirs()     // Catch:{ all -> 0x0061 }
            if (r1 != 0) goto L_0x0022
            java.lang.String r1 = "Unable to create cache dir %s"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = r0.getAbsolutePath()     // Catch:{ all -> 0x0061 }
            r3[r2] = r4     // Catch:{ all -> 0x0061 }
            com.android.volley.VolleyLog.m392e(r1, r3)     // Catch:{ all -> 0x0061 }
        L_0x0022:
            monitor-exit(r10)
            return
        L_0x0024:
            java.io.File[] r1 = r0.listFiles()     // Catch:{ all -> 0x0061 }
            if (r1 != 0) goto L_0x002c
            monitor-exit(r10)
            return
        L_0x002c:
            int r3 = r1.length     // Catch:{ all -> 0x0061 }
        L_0x002d:
            if (r2 >= r3) goto L_0x005f
            r4 = r1[r2]     // Catch:{ all -> 0x0061 }
            long r5 = r4.length()     // Catch:{ IOException -> 0x0058 }
            com.android.volley.toolbox.DiskBasedCache$CountingInputStream r7 = new com.android.volley.toolbox.DiskBasedCache$CountingInputStream     // Catch:{ IOException -> 0x0058 }
            java.io.BufferedInputStream r8 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0058 }
            java.io.InputStream r9 = r10.createInputStream(r4)     // Catch:{ IOException -> 0x0058 }
            r8.<init>(r9)     // Catch:{ IOException -> 0x0058 }
            r7.<init>(r8, r5)     // Catch:{ IOException -> 0x0058 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r8 = com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r7)     // Catch:{ all -> 0x0053 }
            r8.size = r5     // Catch:{ all -> 0x0053 }
            java.lang.String r9 = r8.key     // Catch:{ all -> 0x0053 }
            r10.putEntry(r9, r8)     // Catch:{ all -> 0x0053 }
            r7.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0053:
            r8 = move-exception
            r7.close()     // Catch:{ IOException -> 0x0058 }
            throw r8     // Catch:{ IOException -> 0x0058 }
        L_0x0058:
            r5 = move-exception
            r4.delete()     // Catch:{ all -> 0x0061 }
        L_0x005c:
            int r2 = r2 + 1
            goto L_0x002d
        L_0x005f:
            monitor-exit(r10)
            return
        L_0x0061:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.initialize():void");
    }

    public synchronized void invalidate(String key, boolean fullExpire) {
        Cache.Entry entry = get(key);
        if (entry != null) {
            entry.softTtl = 0;
            if (fullExpire) {
                entry.ttl = 0;
            }
            put(key, entry);
        }
    }

    public synchronized void put(String key, Cache.Entry entry) {
        if (this.mTotalSize + ((long) entry.data.length) > ((long) this.mMaxCacheSizeInBytes)) {
            if (((float) entry.data.length) > ((float) this.mMaxCacheSizeInBytes) * HYSTERESIS_FACTOR) {
                return;
            }
        }
        File file = getFileForKey(key);
        try {
            BufferedOutputStream fos = new BufferedOutputStream(createOutputStream(file));
            CacheHeader e = new CacheHeader(key, entry);
            if (e.writeHeader(fos)) {
                fos.write(entry.data);
                fos.close();
                e.size = file.length();
                putEntry(key, e);
                pruneIfNeeded();
            } else {
                fos.close();
                VolleyLog.m391d("Failed to write header for %s", file.getAbsolutePath());
                throw new IOException();
            }
        } catch (IOException e2) {
            if (!file.delete()) {
                VolleyLog.m391d("Could not clean up file %s", file.getAbsolutePath());
            }
            initializeIfRootDirectoryDeleted();
        }
    }

    public synchronized void remove(String key) {
        boolean deleted = getFileForKey(key).delete();
        removeEntry(key);
        if (!deleted) {
            VolleyLog.m391d("Could not delete cache entry for key=%s, filename=%s", key, getFilenameForKey(key));
        }
    }

    private String getFilenameForKey(String key) {
        int firstHalfLength = key.length() / 2;
        return String.valueOf(key.substring(0, firstHalfLength).hashCode()) + String.valueOf(key.substring(firstHalfLength).hashCode());
    }

    public File getFileForKey(String key) {
        return new File(this.mRootDirectorySupplier.get(), getFilenameForKey(key));
    }

    private void initializeIfRootDirectoryDeleted() {
        if (!this.mRootDirectorySupplier.get().exists()) {
            VolleyLog.m391d("Re-initializing cache after external clearing.", new Object[0]);
            this.mEntries.clear();
            this.mTotalSize = 0;
            initialize();
        }
    }

    private void pruneIfNeeded() {
        long before;
        if (this.mTotalSize >= ((long) this.mMaxCacheSizeInBytes)) {
            if (VolleyLog.DEBUG) {
                VolleyLog.m394v("Pruning old cache entries.", new Object[0]);
            }
            long before2 = this.mTotalSize;
            int prunedFiles = 0;
            long startTime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, CacheHeader>> iterator = this.mEntries.entrySet().iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    before = before2;
                    break;
                }
                CacheHeader e = iterator.next().getValue();
                if (getFileForKey(e.key).delete()) {
                    before = before2;
                    this.mTotalSize -= e.size;
                } else {
                    before = before2;
                    VolleyLog.m391d("Could not delete cache entry for key=%s, filename=%s", e.key, getFilenameForKey(e.key));
                }
                iterator.remove();
                prunedFiles++;
                if (((float) this.mTotalSize) < ((float) this.mMaxCacheSizeInBytes) * HYSTERESIS_FACTOR) {
                    break;
                }
                before2 = before;
            }
            if (VolleyLog.DEBUG) {
                VolleyLog.m394v("pruned %d files, %d bytes, %d ms", Integer.valueOf(prunedFiles), Long.valueOf(this.mTotalSize - before), Long.valueOf(SystemClock.elapsedRealtime() - startTime));
            }
        }
    }

    private void putEntry(String key, CacheHeader entry) {
        if (!this.mEntries.containsKey(key)) {
            this.mTotalSize += entry.size;
        } else {
            this.mTotalSize += entry.size - this.mEntries.get(key).size;
        }
        this.mEntries.put(key, entry);
    }

    private void removeEntry(String key) {
        CacheHeader removed = this.mEntries.remove(key);
        if (removed != null) {
            this.mTotalSize -= removed.size;
        }
    }

    static byte[] streamToBytes(CountingInputStream cis, long length) throws IOException {
        long maxLength = cis.bytesRemaining();
        if (length < 0 || length > maxLength || ((long) ((int) length)) != length) {
            throw new IOException("streamToBytes length=" + length + ", maxLength=" + maxLength);
        }
        byte[] bytes = new byte[((int) length)];
        new DataInputStream(cis).readFully(bytes);
        return bytes;
    }

    /* access modifiers changed from: package-private */
    public InputStream createInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    /* access modifiers changed from: package-private */
    public OutputStream createOutputStream(File file) throws FileNotFoundException {
        return new FileOutputStream(file);
    }

    static class CacheHeader {
        final List<Header> allResponseHeaders;
        final String etag;
        final String key;
        final long lastModified;
        final long serverDate;
        long size;
        final long softTtl;
        final long ttl;

        private CacheHeader(String key2, String etag2, long serverDate2, long lastModified2, long ttl2, long softTtl2, List<Header> allResponseHeaders2) {
            this.key = key2;
            this.etag = "".equals(etag2) ? null : etag2;
            this.serverDate = serverDate2;
            this.lastModified = lastModified2;
            this.ttl = ttl2;
            this.softTtl = softTtl2;
            this.allResponseHeaders = allResponseHeaders2;
        }

        CacheHeader(String key2, Cache.Entry entry) {
            this(key2, entry.etag, entry.serverDate, entry.lastModified, entry.ttl, entry.softTtl, getAllResponseHeaders(entry));
        }

        private static List<Header> getAllResponseHeaders(Cache.Entry entry) {
            if (entry.allResponseHeaders != null) {
                return entry.allResponseHeaders;
            }
            return HttpHeaderParser.toAllHeaderList(entry.responseHeaders);
        }

        static CacheHeader readHeader(CountingInputStream is) throws IOException {
            if (DiskBasedCache.readInt(is) == DiskBasedCache.CACHE_MAGIC) {
                return new CacheHeader(DiskBasedCache.readString(is), DiskBasedCache.readString(is), DiskBasedCache.readLong(is), DiskBasedCache.readLong(is), DiskBasedCache.readLong(is), DiskBasedCache.readLong(is), DiskBasedCache.readHeaderList(is));
            }
            throw new IOException();
        }

        /* access modifiers changed from: package-private */
        public Cache.Entry toCacheEntry(byte[] data) {
            Cache.Entry e = new Cache.Entry();
            e.data = data;
            e.etag = this.etag;
            e.serverDate = this.serverDate;
            e.lastModified = this.lastModified;
            e.ttl = this.ttl;
            e.softTtl = this.softTtl;
            e.responseHeaders = HttpHeaderParser.toHeaderMap(this.allResponseHeaders);
            e.allResponseHeaders = Collections.unmodifiableList(this.allResponseHeaders);
            return e;
        }

        /* access modifiers changed from: package-private */
        public boolean writeHeader(OutputStream os) {
            try {
                DiskBasedCache.writeInt(os, DiskBasedCache.CACHE_MAGIC);
                DiskBasedCache.writeString(os, this.key);
                String str = this.etag;
                if (str == null) {
                    str = "";
                }
                DiskBasedCache.writeString(os, str);
                DiskBasedCache.writeLong(os, this.serverDate);
                DiskBasedCache.writeLong(os, this.lastModified);
                DiskBasedCache.writeLong(os, this.ttl);
                DiskBasedCache.writeLong(os, this.softTtl);
                DiskBasedCache.writeHeaderList(this.allResponseHeaders, os);
                os.flush();
                return true;
            } catch (IOException e) {
                VolleyLog.m391d("%s", e.toString());
                return false;
            }
        }
    }

    static class CountingInputStream extends FilterInputStream {
        private long bytesRead;
        private final long length;

        CountingInputStream(InputStream in, long length2) {
            super(in);
            this.length = length2;
        }

        public int read() throws IOException {
            int result = super.read();
            if (result != -1) {
                this.bytesRead++;
            }
            return result;
        }

        public int read(byte[] buffer, int offset, int count) throws IOException {
            int result = super.read(buffer, offset, count);
            if (result != -1) {
                this.bytesRead += (long) result;
            }
            return result;
        }

        /* access modifiers changed from: package-private */
        public long bytesRead() {
            return this.bytesRead;
        }

        /* access modifiers changed from: package-private */
        public long bytesRemaining() {
            return this.length - this.bytesRead;
        }
    }

    private static int read(InputStream is) throws IOException {
        int b = is.read();
        if (b != -1) {
            return b;
        }
        throw new EOFException();
    }

    static void writeInt(OutputStream os, int n) throws IOException {
        os.write((n >> 0) & 255);
        os.write((n >> 8) & 255);
        os.write((n >> 16) & 255);
        os.write((n >> 24) & 255);
    }

    static int readInt(InputStream is) throws IOException {
        return 0 | (read(is) << 0) | (read(is) << 8) | (read(is) << 16) | (read(is) << 24);
    }

    static void writeLong(OutputStream os, long n) throws IOException {
        os.write((byte) ((int) (n >>> 0)));
        os.write((byte) ((int) (n >>> 8)));
        os.write((byte) ((int) (n >>> 16)));
        os.write((byte) ((int) (n >>> 24)));
        os.write((byte) ((int) (n >>> 32)));
        os.write((byte) ((int) (n >>> 40)));
        os.write((byte) ((int) (n >>> 48)));
        os.write((byte) ((int) (n >>> 56)));
    }

    static long readLong(InputStream is) throws IOException {
        return 0 | ((((long) read(is)) & 255) << 0) | ((((long) read(is)) & 255) << 8) | ((((long) read(is)) & 255) << 16) | ((((long) read(is)) & 255) << 24) | ((((long) read(is)) & 255) << 32) | ((((long) read(is)) & 255) << 40) | ((((long) read(is)) & 255) << 48) | ((((long) read(is)) & 255) << 56);
    }

    static void writeString(OutputStream os, String s) throws IOException {
        byte[] b = s.getBytes("UTF-8");
        writeLong(os, (long) b.length);
        os.write(b, 0, b.length);
    }

    static String readString(CountingInputStream cis) throws IOException {
        return new String(streamToBytes(cis, readLong(cis)), "UTF-8");
    }

    static void writeHeaderList(List<Header> headers, OutputStream os) throws IOException {
        if (headers != null) {
            writeInt(os, headers.size());
            for (Header header : headers) {
                writeString(os, header.getName());
                writeString(os, header.getValue());
            }
            return;
        }
        writeInt(os, 0);
    }

    static List<Header> readHeaderList(CountingInputStream cis) throws IOException {
        int size = readInt(cis);
        if (size >= 0) {
            List<Header> result = size == 0 ? Collections.emptyList() : new ArrayList<>();
            for (int i = 0; i < size; i++) {
                result.add(new Header(readString(cis).intern(), readString(cis).intern()));
            }
            return result;
        }
        throw new IOException("readHeaderList size=" + size);
    }
}
