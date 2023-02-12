package com.google.common.hash;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
final class Murmur3_128HashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction GOOD_FAST_HASH_128 = new Murmur3_128HashFunction(Hashing.GOOD_FAST_HASH_SEED);
    static final HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);
    private static final long serialVersionUID = 0;
    private final int seed;

    Murmur3_128HashFunction(int seed2) {
        this.seed = seed2;
    }

    public int bits() {
        return 128;
    }

    public Hasher newHasher() {
        return new Murmur3_128Hasher(this.seed);
    }

    public String toString() {
        return new StringBuilder(32).append("Hashing.murmur3_128(").append(this.seed).append(")").toString();
    }

    public boolean equals(@NullableDecl Object object) {
        if (!(object instanceof Murmur3_128HashFunction) || this.seed != ((Murmur3_128HashFunction) object).seed) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }

    private static final class Murmur3_128Hasher extends AbstractStreamingHasher {

        /* renamed from: C1 */
        private static final long f98C1 = -8663945395140668459L;

        /* renamed from: C2 */
        private static final long f99C2 = 5545529020109919103L;
        private static final int CHUNK_SIZE = 16;

        /* renamed from: h1 */
        private long f100h1;

        /* renamed from: h2 */
        private long f101h2;
        private int length = 0;

        Murmur3_128Hasher(int seed) {
            super(16);
            this.f100h1 = (long) seed;
            this.f101h2 = (long) seed;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer bb) {
            bmix64(bb.getLong(), bb.getLong());
            this.length += 16;
        }

        private void bmix64(long k1, long k2) {
            long mixK1 = this.f100h1 ^ mixK1(k1);
            this.f100h1 = mixK1;
            long rotateLeft = Long.rotateLeft(mixK1, 27);
            this.f100h1 = rotateLeft;
            long j = this.f101h2;
            long j2 = rotateLeft + j;
            this.f100h1 = j2;
            this.f100h1 = (j2 * 5) + 1390208809;
            long mixK2 = mixK2(k2) ^ j;
            this.f101h2 = mixK2;
            long rotateLeft2 = Long.rotateLeft(mixK2, 31);
            this.f101h2 = rotateLeft2;
            long j3 = rotateLeft2 + this.f100h1;
            this.f101h2 = j3;
            this.f101h2 = (j3 * 5) + 944331445;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0077, code lost:
            r2 = r2 ^ ((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(8)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0081, code lost:
            r0 = 0 ^ r14.getLong();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0094, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(5))) << 40);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x00a0, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(4))) << 32);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x00ac, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(3))) << 24);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x00b8, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(2))) << 16);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x00c4, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(1))) << 8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x00d0, code lost:
            r0 = r0 ^ ((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(0)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x00dc, code lost:
            r13.f100h1 ^= mixK1(r0);
            r13.f101h2 ^= mixK2(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x00ee, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0036, code lost:
            r2 = r2 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(13))) << 40);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0043, code lost:
            r2 = r2 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(12))) << 32);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0050, code lost:
            r2 = r2 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(11))) << 24);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x005d, code lost:
            r2 = r2 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(10))) << 16);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x006a, code lost:
            r2 = r2 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r14.get(9))) << 8);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void processRemaining(java.nio.ByteBuffer r14) {
            /*
                r13 = this;
                r0 = 0
                r2 = 0
                int r4 = r13.length
                int r5 = r14.remaining()
                int r4 = r4 + r5
                r13.length = r4
                int r4 = r14.remaining()
                r5 = 48
                r6 = 40
                r7 = 32
                r8 = 24
                r9 = 16
                r10 = 8
                switch(r4) {
                    case 1: goto L_0x00d0;
                    case 2: goto L_0x00c4;
                    case 3: goto L_0x00b8;
                    case 4: goto L_0x00ac;
                    case 5: goto L_0x00a0;
                    case 6: goto L_0x0094;
                    case 7: goto L_0x0087;
                    case 8: goto L_0x0081;
                    case 9: goto L_0x0077;
                    case 10: goto L_0x006a;
                    case 11: goto L_0x005d;
                    case 12: goto L_0x0050;
                    case 13: goto L_0x0043;
                    case 14: goto L_0x0036;
                    case 15: goto L_0x0028;
                    default: goto L_0x0020;
                }
            L_0x0020:
                java.lang.AssertionError r4 = new java.lang.AssertionError
                java.lang.String r5 = "Should never get here."
                r4.<init>(r5)
                throw r4
            L_0x0028:
                r4 = 14
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r11 = (long) r4
                long r4 = r11 << r5
                long r2 = r2 ^ r4
            L_0x0036:
                r4 = 13
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r6
                long r2 = r2 ^ r4
            L_0x0043:
                r4 = 12
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r7
                long r2 = r2 ^ r4
            L_0x0050:
                r4 = 11
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r8
                long r2 = r2 ^ r4
            L_0x005d:
                r4 = 10
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r9
                long r2 = r2 ^ r4
            L_0x006a:
                r4 = 9
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r10
                long r2 = r2 ^ r4
            L_0x0077:
                byte r4 = r14.get(r10)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r2 = r2 ^ r4
            L_0x0081:
                long r4 = r14.getLong()
                long r0 = r0 ^ r4
                goto L_0x00dc
            L_0x0087:
                r4 = 6
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r11 = (long) r4
                long r4 = r11 << r5
                long r0 = r0 ^ r4
            L_0x0094:
                r4 = 5
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r6
                long r0 = r0 ^ r4
            L_0x00a0:
                r4 = 4
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r7
                long r0 = r0 ^ r4
            L_0x00ac:
                r4 = 3
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r8
                long r0 = r0 ^ r4
            L_0x00b8:
                r4 = 2
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r9
                long r0 = r0 ^ r4
            L_0x00c4:
                r4 = 1
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r4 = r4 << r10
                long r0 = r0 ^ r4
            L_0x00d0:
                r4 = 0
                byte r4 = r14.get(r4)
                int r4 = com.google.common.primitives.UnsignedBytes.toInt(r4)
                long r4 = (long) r4
                long r0 = r0 ^ r4
            L_0x00dc:
                long r4 = r13.f100h1
                long r6 = mixK1(r0)
                long r4 = r4 ^ r6
                r13.f100h1 = r4
                long r4 = r13.f101h2
                long r6 = mixK2(r2)
                long r4 = r4 ^ r6
                r13.f101h2 = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.hash.Murmur3_128HashFunction.Murmur3_128Hasher.processRemaining(java.nio.ByteBuffer):void");
        }

        /* access modifiers changed from: protected */
        public HashCode makeHash() {
            long j = this.f100h1;
            int i = this.length;
            long j2 = j ^ ((long) i);
            this.f100h1 = j2;
            long j3 = this.f101h2 ^ ((long) i);
            this.f101h2 = j3;
            long j4 = j2 + j3;
            this.f100h1 = j4;
            this.f101h2 = j3 + j4;
            this.f100h1 = fmix64(j4);
            long fmix64 = fmix64(this.f101h2);
            this.f101h2 = fmix64;
            long j5 = this.f100h1 + fmix64;
            this.f100h1 = j5;
            this.f101h2 = fmix64 + j5;
            return HashCode.fromBytesNoCopy(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.f100h1).putLong(this.f101h2).array());
        }

        private static long fmix64(long k) {
            long k2 = (k ^ (k >>> 33)) * -49064778989728563L;
            long k3 = (k2 ^ (k2 >>> 33)) * -4265267296055464877L;
            return k3 ^ (k3 >>> 33);
        }

        private static long mixK1(long k1) {
            return Long.rotateLeft(k1 * f98C1, 31) * f99C2;
        }

        private static long mixK2(long k2) {
            return Long.rotateLeft(k2 * f99C2, 33) * f98C1;
        }
    }
}
