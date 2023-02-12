package com.google.common.hash;

import com.google.common.base.Preconditions;

final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    static final HashFunction FARMHASH_FINGERPRINT_64 = new FarmHashFingerprint64();

    /* renamed from: K0 */
    private static final long f95K0 = -4348849565147123417L;

    /* renamed from: K1 */
    private static final long f96K1 = -5435081209227447693L;

    /* renamed from: K2 */
    private static final long f97K2 = -7286425919675154353L;

    FarmHashFingerprint64() {
    }

    public HashCode hashBytes(byte[] input, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, input.length);
        return HashCode.fromLong(fingerprint(input, off, len));
    }

    public int bits() {
        return 64;
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }

    static long fingerprint(byte[] bytes, int offset, int length) {
        if (length <= 32) {
            if (length <= 16) {
                return hashLength0to16(bytes, offset, length);
            }
            return hashLength17to32(bytes, offset, length);
        } else if (length <= 64) {
            return hashLength33To64(bytes, offset, length);
        } else {
            return hashLength65Plus(bytes, offset, length);
        }
    }

    private static long shiftMix(long val) {
        return (val >>> 47) ^ val;
    }

    private static long hashLength16(long u, long v, long mul) {
        long a = (u ^ v) * mul;
        long b = (v ^ (a ^ (a >>> 47))) * mul;
        return (b ^ (b >>> 47)) * mul;
    }

    private static void weakHashLength32WithSeeds(byte[] bytes, int offset, long seedA, long seedB, long[] output) {
        byte[] bArr = bytes;
        long part1 = LittleEndianByteArray.load64(bytes, offset);
        long part2 = LittleEndianByteArray.load64(bArr, offset + 8);
        long part3 = LittleEndianByteArray.load64(bArr, offset + 16);
        long part4 = LittleEndianByteArray.load64(bArr, offset + 24);
        long seedA2 = seedA + part1;
        long c = seedA2;
        long seedA3 = seedA2 + part2 + part3;
        long seedB2 = Long.rotateRight(seedB + seedA2 + part4, 21) + Long.rotateRight(seedA3, 44);
        output[0] = seedA3 + part4;
        output[1] = seedB2 + c;
    }

    private static long hashLength0to16(byte[] bytes, int offset, int length) {
        byte[] bArr = bytes;
        int i = length;
        if (i >= 8) {
            long mul = ((long) (i * 2)) + f97K2;
            long a = f97K2 + LittleEndianByteArray.load64(bytes, offset);
            long b = LittleEndianByteArray.load64(bArr, (offset + i) - 8);
            return hashLength16((Long.rotateRight(b, 37) * mul) + a, (Long.rotateRight(a, 25) + b) * mul, mul);
        } else if (i >= 4) {
            return hashLength16(((long) i) + ((((long) LittleEndianByteArray.load32(bytes, offset)) & 4294967295L) << 3), ((long) LittleEndianByteArray.load32(bArr, (offset + i) - 4)) & 4294967295L, ((long) (i * 2)) + f97K2);
        } else if (i <= 0) {
            return f97K2;
        } else {
            return shiftMix((((long) ((bArr[offset] & 255) + ((bArr[offset + (i >> 1)] & 255) << 8))) * f97K2) ^ (((long) (((bArr[offset + (i - 1)] & 255) << 2) + i)) * f95K0)) * f97K2;
        }
    }

    private static long hashLength17to32(byte[] bytes, int offset, int length) {
        byte[] bArr = bytes;
        long mul = ((long) (length * 2)) + f97K2;
        long a = LittleEndianByteArray.load64(bytes, offset) * f96K1;
        long b = LittleEndianByteArray.load64(bArr, offset + 8);
        long c = LittleEndianByteArray.load64(bArr, (offset + length) - 8) * mul;
        long d = LittleEndianByteArray.load64(bArr, (offset + length) - 16) * f97K2;
        long j = c;
        return hashLength16(Long.rotateRight(a + b, 43) + Long.rotateRight(c, 30) + d, Long.rotateRight(f97K2 + b, 18) + a + c, mul);
    }

    private static long hashLength33To64(byte[] bytes, int offset, int length) {
        byte[] bArr = bytes;
        long mul = ((long) (length * 2)) + f97K2;
        long a = LittleEndianByteArray.load64(bytes, offset) * f97K2;
        long b = LittleEndianByteArray.load64(bArr, offset + 8);
        long c = LittleEndianByteArray.load64(bArr, (offset + length) - 8) * mul;
        long y = Long.rotateRight(a + b, 43) + Long.rotateRight(c, 30) + (LittleEndianByteArray.load64(bArr, (offset + length) - 16) * f97K2);
        long j = b;
        long j2 = c;
        long z = hashLength16(y, Long.rotateRight(f97K2 + b, 18) + a + c, mul);
        long e = LittleEndianByteArray.load64(bArr, offset + 16) * mul;
        long f = LittleEndianByteArray.load64(bArr, offset + 24);
        long g = (y + LittleEndianByteArray.load64(bArr, (offset + length) - 32)) * mul;
        long j3 = g;
        return hashLength16(Long.rotateRight(e + f, 43) + Long.rotateRight(g, 30) + ((z + LittleEndianByteArray.load64(bArr, (offset + length) - 24)) * mul), e + Long.rotateRight(f + a, 18) + g, mul);
    }

    private static long hashLength65Plus(byte[] bytes, int offset, int length) {
        byte[] bArr = bytes;
        long z = 2480279821605975764L;
        long z2 = shiftMix((2480279821605975764L * f97K2) + 113) * f97K2;
        long[] v = new long[2];
        long[] w = new long[2];
        long x = (f97K2 * 81) + LittleEndianByteArray.load64(bytes, offset);
        int end = offset + (((length - 1) / 64) * 64);
        int last64offset = (((length - 1) & 63) + end) - 63;
        long z3 = z2;
        int offset2 = offset;
        while (true) {
            long[] v2 = v;
            long x2 = Long.rotateRight(x + z + v[0] + LittleEndianByteArray.load64(bArr, offset2 + 8), 37) * f96K1;
            long y = Long.rotateRight(v2[1] + z + LittleEndianByteArray.load64(bArr, offset2 + 48), 42) * f96K1;
            long x3 = x2 ^ w[1];
            long y2 = y + v2[0] + LittleEndianByteArray.load64(bArr, offset2 + 40);
            long z4 = Long.rotateRight(w[0] + z3, 33) * f96K1;
            weakHashLength32WithSeeds(bytes, offset2, v2[1] * f96K1, x3 + w[0], v2);
            weakHashLength32WithSeeds(bytes, offset2 + 32, z4 + w[1], y2 + LittleEndianByteArray.load64(bArr, offset2 + 16), w);
            x = z4;
            long z5 = x3;
            offset2 += 64;
            if (offset2 == end) {
                long mul = ((255 & z5) << 1) + f96K1;
                int offset3 = last64offset;
                int i = end;
                int i2 = last64offset;
                w[0] = w[0] + ((long) ((length - 1) & 63));
                v2[0] = v2[0] + w[0];
                w[0] = w[0] + v2[0];
                long x4 = (w[1] * 9) ^ (Long.rotateRight(((x + y2) + v2[0]) + LittleEndianByteArray.load64(bArr, offset3 + 8), 37) * mul);
                long y3 = (Long.rotateRight(y2 + v2[1] + LittleEndianByteArray.load64(bArr, offset3 + 48), 42) * mul) + (v2[0] * 9) + LittleEndianByteArray.load64(bArr, offset3 + 40);
                long z6 = Long.rotateRight(w[0] + z5, 33) * mul;
                weakHashLength32WithSeeds(bytes, offset3, v2[1] * mul, w[0] + x4, v2);
                weakHashLength32WithSeeds(bytes, offset3 + 32, z6 + w[1], LittleEndianByteArray.load64(bArr, offset3 + 16) + y3, w);
                long j = mul;
                return hashLength16(hashLength16(v2[0], w[0], j) + (shiftMix(y3) * f95K0) + x4, hashLength16(v2[1], w[1], j) + z6, j);
            }
            int i3 = last64offset;
            z3 = z5;
            v = v2;
            z = y2;
        }
    }
}
