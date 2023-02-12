package com.google.common.p000io;

import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.google.common.io.ByteSource */
public abstract class ByteSource {
    public abstract InputStream openStream() throws IOException;

    protected ByteSource() {
    }

    public CharSource asCharSource(Charset charset) {
        return new AsCharSource(charset);
    }

    public InputStream openBufferedStream() throws IOException {
        InputStream in = openStream();
        if (in instanceof BufferedInputStream) {
            return (BufferedInputStream) in;
        }
        return new BufferedInputStream(in);
    }

    public ByteSource slice(long offset, long length) {
        return new SlicedByteSource(offset, length);
    }

    public boolean isEmpty() throws IOException {
        Optional<Long> sizeIfKnown = sizeIfKnown();
        boolean z = true;
        if (!sizeIfKnown.isPresent()) {
            Closer closer = Closer.create();
            try {
                if (((InputStream) closer.register(openStream())).read() != -1) {
                    z = false;
                }
                closer.close();
                return z;
            } catch (Throwable e) {
                closer.close();
                throw e;
            }
        } else if (sizeIfKnown.get().longValue() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Optional<Long> sizeIfKnown() {
        return Optional.absent();
    }

    public long size() throws IOException {
        Closer closer;
        Optional<Long> sizeIfKnown = sizeIfKnown();
        if (sizeIfKnown.isPresent()) {
            return sizeIfKnown.get().longValue();
        }
        Closer closer2 = Closer.create();
        try {
            long countBySkipping = countBySkipping((InputStream) closer2.register(openStream()));
            closer2.close();
            return countBySkipping;
        } catch (IOException e) {
            closer2.close();
            closer = Closer.create();
            long exhaust = ByteStreams.exhaust((InputStream) closer.register(openStream()));
            closer.close();
            return exhaust;
        } catch (Throwable e2) {
            try {
                throw closer.rethrow(e2);
            } catch (Throwable e3) {
                closer.close();
                throw e3;
            }
        }
    }

    private long countBySkipping(InputStream in) throws IOException {
        long count = 0;
        while (true) {
            long skipUpTo = ByteStreams.skipUpTo(in, 2147483647L);
            long skipped = skipUpTo;
            if (skipUpTo <= 0) {
                return count;
            }
            count += skipped;
        }
    }

    public long copyTo(OutputStream output) throws IOException {
        Preconditions.checkNotNull(output);
        Closer closer = Closer.create();
        try {
            long copy = ByteStreams.copy((InputStream) closer.register(openStream()), output);
            closer.close();
            return copy;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public long copyTo(ByteSink sink) throws IOException {
        Preconditions.checkNotNull(sink);
        Closer closer = Closer.create();
        try {
            long copy = ByteStreams.copy((InputStream) closer.register(openStream()), (OutputStream) closer.register(sink.openStream()));
            closer.close();
            return copy;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public byte[] read() throws IOException {
        byte[] bArr;
        Closer closer = Closer.create();
        try {
            InputStream in = (InputStream) closer.register(openStream());
            Optional<Long> size = sizeIfKnown();
            if (size.isPresent()) {
                bArr = ByteStreams.toByteArray(in, size.get().longValue());
            } else {
                bArr = ByteStreams.toByteArray(in);
            }
            closer.close();
            return bArr;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public <T> T read(ByteProcessor<T> processor) throws IOException {
        Preconditions.checkNotNull(processor);
        Closer closer = Closer.create();
        try {
            T readBytes = ByteStreams.readBytes((InputStream) closer.register(openStream()), processor);
            closer.close();
            return readBytes;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public HashCode hash(HashFunction hashFunction) throws IOException {
        Hasher hasher = hashFunction.newHasher();
        copyTo(Funnels.asOutputStream(hasher));
        return hasher.hash();
    }

    public boolean contentEquals(ByteSource other) throws IOException {
        int read1;
        Preconditions.checkNotNull(other);
        byte[] buf1 = ByteStreams.createBuffer();
        byte[] buf2 = ByteStreams.createBuffer();
        Closer closer = Closer.create();
        try {
            InputStream in1 = (InputStream) closer.register(openStream());
            InputStream in2 = (InputStream) closer.register(other.openStream());
            do {
                read1 = ByteStreams.read(in1, buf1, 0, buf1.length);
                if (read1 == ByteStreams.read(in2, buf2, 0, buf2.length)) {
                    if (!Arrays.equals(buf1, buf2)) {
                    }
                }
                closer.close();
                return false;
            } while (read1 == buf1.length);
            closer.close();
            return true;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public static ByteSource concat(Iterable<? extends ByteSource> sources) {
        return new ConcatenatedByteSource(sources);
    }

    public static ByteSource concat(Iterator<? extends ByteSource> sources) {
        return concat((Iterable<? extends ByteSource>) ImmutableList.copyOf(sources));
    }

    public static ByteSource concat(ByteSource... sources) {
        return concat((Iterable<? extends ByteSource>) ImmutableList.copyOf((E[]) sources));
    }

    public static ByteSource wrap(byte[] b) {
        return new ByteArrayByteSource(b);
    }

    public static ByteSource empty() {
        return EmptyByteSource.INSTANCE;
    }

    /* renamed from: com.google.common.io.ByteSource$AsCharSource */
    class AsCharSource extends CharSource {
        final Charset charset;

        AsCharSource(Charset charset2) {
            this.charset = (Charset) Preconditions.checkNotNull(charset2);
        }

        public ByteSource asByteSource(Charset charset2) {
            if (charset2.equals(this.charset)) {
                return ByteSource.this;
            }
            return super.asByteSource(charset2);
        }

        public Reader openStream() throws IOException {
            return new InputStreamReader(ByteSource.this.openStream(), this.charset);
        }

        public String read() throws IOException {
            return new String(ByteSource.this.read(), this.charset);
        }

        public String toString() {
            String obj = ByteSource.this.toString();
            String valueOf = String.valueOf(this.charset);
            return new StringBuilder(String.valueOf(obj).length() + 15 + String.valueOf(valueOf).length()).append(obj).append(".asCharSource(").append(valueOf).append(")").toString();
        }
    }

    /* renamed from: com.google.common.io.ByteSource$SlicedByteSource */
    private final class SlicedByteSource extends ByteSource {
        final long length;
        final long offset;

        SlicedByteSource(long offset2, long length2) {
            boolean z = true;
            Preconditions.checkArgument(offset2 >= 0, "offset (%s) may not be negative", offset2);
            Preconditions.checkArgument(length2 < 0 ? false : z, "length (%s) may not be negative", length2);
            this.offset = offset2;
            this.length = length2;
        }

        public InputStream openStream() throws IOException {
            return sliceStream(ByteSource.this.openStream());
        }

        public InputStream openBufferedStream() throws IOException {
            return sliceStream(ByteSource.this.openBufferedStream());
        }

        private InputStream sliceStream(InputStream in) throws IOException {
            Closer closer;
            long j = this.offset;
            if (j > 0) {
                try {
                    if (ByteStreams.skipUpTo(in, j) < this.offset) {
                        in.close();
                        return new ByteArrayInputStream(new byte[0]);
                    }
                } catch (Throwable th) {
                    closer.close();
                    throw th;
                }
            }
            return ByteStreams.limit(in, this.length);
        }

        public ByteSource slice(long offset2, long length2) {
            boolean z = true;
            Preconditions.checkArgument(offset2 >= 0, "offset (%s) may not be negative", offset2);
            if (length2 < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "length (%s) may not be negative", length2);
            long maxLength = this.length - offset2;
            if (maxLength <= 0) {
                return ByteSource.empty();
            }
            return ByteSource.this.slice(this.offset + offset2, Math.min(length2, maxLength));
        }

        public boolean isEmpty() throws IOException {
            return this.length == 0 || ByteSource.super.isEmpty();
        }

        public Optional<Long> sizeIfKnown() {
            Optional<Long> optionalUnslicedSize = ByteSource.this.sizeIfKnown();
            if (!optionalUnslicedSize.isPresent()) {
                return Optional.absent();
            }
            long unslicedSize = optionalUnslicedSize.get().longValue();
            return Optional.m12of(Long.valueOf(Math.min(this.length, unslicedSize - Math.min(this.offset, unslicedSize))));
        }

        public String toString() {
            String obj = ByteSource.this.toString();
            long j = this.offset;
            return new StringBuilder(String.valueOf(obj).length() + 50).append(obj).append(".slice(").append(j).append(", ").append(this.length).append(")").toString();
        }
    }

    /* renamed from: com.google.common.io.ByteSource$ByteArrayByteSource */
    private static class ByteArrayByteSource extends ByteSource {
        final byte[] bytes;
        final int length;
        final int offset;

        ByteArrayByteSource(byte[] bytes2) {
            this(bytes2, 0, bytes2.length);
        }

        ByteArrayByteSource(byte[] bytes2, int offset2, int length2) {
            this.bytes = bytes2;
            this.offset = offset2;
            this.length = length2;
        }

        public InputStream openStream() {
            return new ByteArrayInputStream(this.bytes, this.offset, this.length);
        }

        public InputStream openBufferedStream() throws IOException {
            return openStream();
        }

        public boolean isEmpty() {
            return this.length == 0;
        }

        public long size() {
            return (long) this.length;
        }

        public Optional<Long> sizeIfKnown() {
            return Optional.m12of(Long.valueOf((long) this.length));
        }

        public byte[] read() {
            byte[] bArr = this.bytes;
            int i = this.offset;
            return Arrays.copyOfRange(bArr, i, this.length + i);
        }

        public <T> T read(ByteProcessor<T> processor) throws IOException {
            processor.processBytes(this.bytes, this.offset, this.length);
            return processor.getResult();
        }

        public long copyTo(OutputStream output) throws IOException {
            output.write(this.bytes, this.offset, this.length);
            return (long) this.length;
        }

        public HashCode hash(HashFunction hashFunction) throws IOException {
            return hashFunction.hashBytes(this.bytes, this.offset, this.length);
        }

        public ByteSource slice(long offset2, long length2) {
            boolean z = true;
            Preconditions.checkArgument(offset2 >= 0, "offset (%s) may not be negative", offset2);
            if (length2 < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "length (%s) may not be negative", length2);
            long offset3 = Math.min(offset2, (long) this.length);
            return new ByteArrayByteSource(this.bytes, this.offset + ((int) offset3), (int) Math.min(length2, ((long) this.length) - offset3));
        }

        public String toString() {
            String truncate = Ascii.truncate(BaseEncoding.base16().encode(this.bytes, this.offset, this.length), 30, "...");
            return new StringBuilder(String.valueOf(truncate).length() + 17).append("ByteSource.wrap(").append(truncate).append(")").toString();
        }
    }

    /* renamed from: com.google.common.io.ByteSource$EmptyByteSource */
    private static final class EmptyByteSource extends ByteArrayByteSource {
        static final EmptyByteSource INSTANCE = new EmptyByteSource();

        EmptyByteSource() {
            super(new byte[0]);
        }

        public CharSource asCharSource(Charset charset) {
            Preconditions.checkNotNull(charset);
            return CharSource.empty();
        }

        public byte[] read() {
            return this.bytes;
        }

        public String toString() {
            return "ByteSource.empty()";
        }
    }

    /* renamed from: com.google.common.io.ByteSource$ConcatenatedByteSource */
    private static final class ConcatenatedByteSource extends ByteSource {
        final Iterable<? extends ByteSource> sources;

        ConcatenatedByteSource(Iterable<? extends ByteSource> sources2) {
            this.sources = (Iterable) Preconditions.checkNotNull(sources2);
        }

        public InputStream openStream() throws IOException {
            return new MultiInputStream(this.sources.iterator());
        }

        public boolean isEmpty() throws IOException {
            for (ByteSource source : this.sources) {
                if (!source.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        public Optional<Long> sizeIfKnown() {
            Iterable<? extends ByteSource> iterable = this.sources;
            if (!(iterable instanceof Collection)) {
                return Optional.absent();
            }
            long result = 0;
            for (ByteSource source : iterable) {
                Optional<Long> sizeIfKnown = source.sizeIfKnown();
                if (!sizeIfKnown.isPresent()) {
                    return Optional.absent();
                }
                result += sizeIfKnown.get().longValue();
                if (result < 0) {
                    return Optional.m12of(Long.MAX_VALUE);
                }
            }
            return Optional.m12of(Long.valueOf(result));
        }

        public long size() throws IOException {
            long result = 0;
            for (ByteSource source : this.sources) {
                result += source.size();
                if (result < 0) {
                    return Long.MAX_VALUE;
                }
            }
            return result;
        }

        public String toString() {
            String valueOf = String.valueOf(this.sources);
            return new StringBuilder(String.valueOf(valueOf).length() + 19).append("ByteSource.concat(").append(valueOf).append(")").toString();
        }
    }
}
