package com.google.common.hash;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
final class Murmur3_32HashFunction extends AbstractHashFunction implements Serializable {

    /* renamed from: C1 */
    private static final int f102C1 = -862048943;

    /* renamed from: C2 */
    private static final int f103C2 = 461845907;
    private static final int CHUNK_SIZE = 4;
    static final HashFunction GOOD_FAST_HASH_32 = new Murmur3_32HashFunction(Hashing.GOOD_FAST_HASH_SEED);
    static final HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0);
    private static final long serialVersionUID = 0;
    private final int seed;

    Murmur3_32HashFunction(int seed2) {
        this.seed = seed2;
    }

    public int bits() {
        return 32;
    }

    public Hasher newHasher() {
        return new Murmur3_32Hasher(this.seed);
    }

    public String toString() {
        return new StringBuilder(31).append("Hashing.murmur3_32(").append(this.seed).append(")").toString();
    }

    public boolean equals(@NullableDecl Object object) {
        if (!(object instanceof Murmur3_32HashFunction) || this.seed != ((Murmur3_32HashFunction) object).seed) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }

    public HashCode hashInt(int input) {
        return fmix(mixH1(this.seed, mixK1(input)), 4);
    }

    public HashCode hashLong(long input) {
        return fmix(mixH1(mixH1(this.seed, mixK1((int) input)), mixK1((int) (input >>> 32))), 8);
    }

    public HashCode hashUnencodedChars(CharSequence input) {
        int h1 = this.seed;
        for (int i = 1; i < input.length(); i += 2) {
            h1 = mixH1(h1, mixK1(input.charAt(i - 1) | (input.charAt(i) << 16)));
        }
        if ((input.length() & 1) == 1) {
            h1 ^= mixK1(input.charAt(input.length() - 1));
        }
        return fmix(h1, input.length() * 2);
    }

    public HashCode hashString(CharSequence input, Charset charset) {
        int len;
        if (!Charsets.UTF_8.equals(charset)) {
            return hashBytes(input.toString().getBytes(charset));
        }
        int utf16Length = input.length();
        int h1 = this.seed;
        int i = 0;
        int len2 = 0;
        while (i + 4 <= utf16Length) {
            int c0 = input.charAt(i);
            char c1 = input.charAt(i + 1);
            char c2 = input.charAt(i + 2);
            char c3 = input.charAt(i + 3);
            if (c0 >= 128 || c1 >= 128 || c2 >= 128 || c3 >= 128) {
                break;
            }
            h1 = mixH1(h1, mixK1((c1 << 8) | c0 | (c2 << 16) | (c3 << 24)));
            i += 4;
            len2 = len + 4;
        }
        long buffer = 0;
        int shift = 0;
        while (i < utf16Length) {
            char c = input.charAt(i);
            if (c < 128) {
                buffer |= ((long) c) << shift;
                shift += 8;
                len++;
            } else if (c < 2048) {
                buffer |= charToTwoUtf8Bytes(c) << shift;
                shift += 16;
                len += 2;
            } else if (c < 55296 || c > 57343) {
                buffer |= charToThreeUtf8Bytes(c) << shift;
                shift += 24;
                len += 3;
            } else {
                int codePoint = Character.codePointAt(input, i);
                if (codePoint == c) {
                    return hashBytes(input.toString().getBytes(charset));
                }
                i++;
                buffer |= codePointToFourUtf8Bytes(codePoint) << shift;
                len += 4;
            }
            if (shift >= 32) {
                h1 = mixH1(h1, mixK1((int) buffer));
                buffer >>>= 32;
                shift -= 32;
            }
            i++;
        }
        return fmix(h1 ^ mixK1((int) buffer), len);
    }

    public HashCode hashBytes(byte[] input, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, input.length);
        int h1 = this.seed;
        int i = 0;
        while (i + 4 <= len) {
            h1 = mixH1(h1, mixK1(getIntLittleEndian(input, off + i)));
            i += 4;
        }
        int k1 = 0;
        int shift = 0;
        while (i < len) {
            k1 ^= UnsignedBytes.toInt(input[off + i]) << shift;
            i++;
            shift += 8;
        }
        return fmix(h1 ^ mixK1(k1), len);
    }

    /* access modifiers changed from: private */
    public static int getIntLittleEndian(byte[] input, int offset) {
        return Ints.fromBytes(input[offset + 3], input[offset + 2], input[offset + 1], input[offset]);
    }

    /* access modifiers changed from: private */
    public static int mixK1(int k1) {
        return Integer.rotateLeft(k1 * f102C1, 15) * f103C2;
    }

    /* access modifiers changed from: private */
    public static int mixH1(int h1, int k1) {
        return (Integer.rotateLeft(h1 ^ k1, 13) * 5) - 430675100;
    }

    /* access modifiers changed from: private */
    public static HashCode fmix(int h1, int length) {
        int h12 = h1 ^ length;
        int h13 = (h12 ^ (h12 >>> 16)) * -2048144789;
        int h14 = (h13 ^ (h13 >>> 13)) * -1028477387;
        return HashCode.fromInt(h14 ^ (h14 >>> 16));
    }

    private static final class Murmur3_32Hasher extends AbstractHasher {
        private long buffer;

        /* renamed from: h1 */
        private int f104h1;
        private boolean isDone = false;
        private int length = 0;
        private int shift;

        Murmur3_32Hasher(int seed) {
            this.f104h1 = seed;
        }

        private void update(int nBytes, long update) {
            long j = this.buffer;
            int i = this.shift;
            long j2 = j | ((4294967295L & update) << i);
            this.buffer = j2;
            int i2 = i + (nBytes * 8);
            this.shift = i2;
            this.length += nBytes;
            if (i2 >= 32) {
                this.f104h1 = Murmur3_32HashFunction.mixH1(this.f104h1, Murmur3_32HashFunction.mixK1((int) j2));
                this.buffer >>>= 32;
                this.shift -= 32;
            }
        }

        public Hasher putByte(byte b) {
            update(1, (long) (b & 255));
            return this;
        }

        public Hasher putBytes(byte[] bytes, int off, int len) {
            Preconditions.checkPositionIndexes(off, off + len, bytes.length);
            int i = 0;
            while (i + 4 <= len) {
                update(4, (long) Murmur3_32HashFunction.getIntLittleEndian(bytes, off + i));
                i += 4;
            }
            while (i < len) {
                putByte(bytes[off + i]);
                i++;
            }
            return this;
        }

        public Hasher putBytes(ByteBuffer buffer2) {
            ByteOrder bo = buffer2.order();
            buffer2.order(ByteOrder.LITTLE_ENDIAN);
            while (buffer2.remaining() >= 4) {
                putInt(buffer2.getInt());
            }
            while (buffer2.hasRemaining()) {
                putByte(buffer2.get());
            }
            buffer2.order(bo);
            return this;
        }

        public Hasher putInt(int i) {
            update(4, (long) i);
            return this;
        }

        public Hasher putLong(long l) {
            update(4, (long) ((int) l));
            update(4, l >>> 32);
            return this;
        }

        public Hasher putChar(char c) {
            update(2, (long) c);
            return this;
        }

        public Hasher putString(CharSequence input, Charset charset) {
            if (!Charsets.UTF_8.equals(charset)) {
                return super.putString(input, charset);
            }
            int utf16Length = input.length();
            int i = 0;
            while (i + 4 <= utf16Length) {
                char c0 = input.charAt(i);
                char c1 = input.charAt(i + 1);
                char c2 = input.charAt(i + 2);
                char c3 = input.charAt(i + 3);
                if (c0 >= 128 || c1 >= 128 || c2 >= 128 || c3 >= 128) {
                    break;
                }
                update(4, (long) ((c1 << 8) | c0 | (c2 << 16) | (c3 << 24)));
                i += 4;
            }
            while (i < utf16Length) {
                char c = input.charAt(i);
                if (c < 128) {
                    update(1, (long) c);
                } else if (c < 2048) {
                    update(2, Murmur3_32HashFunction.charToTwoUtf8Bytes(c));
                } else if (c < 55296 || c > 57343) {
                    update(3, Murmur3_32HashFunction.charToThreeUtf8Bytes(c));
                } else {
                    int codePoint = Character.codePointAt(input, i);
                    if (codePoint == c) {
                        putBytes(input.subSequence(i, utf16Length).toString().getBytes(charset));
                        return this;
                    }
                    i++;
                    update(4, Murmur3_32HashFunction.codePointToFourUtf8Bytes(codePoint));
                }
                i++;
            }
            return this;
        }

        public HashCode hash() {
            Preconditions.checkState(!this.isDone);
            this.isDone = true;
            int access$000 = this.f104h1 ^ Murmur3_32HashFunction.mixK1((int) this.buffer);
            this.f104h1 = access$000;
            return Murmur3_32HashFunction.fmix(access$000, this.length);
        }
    }

    /* access modifiers changed from: private */
    public static long codePointToFourUtf8Bytes(int codePoint) {
        return ((((long) (codePoint >>> 18)) | 240) & 255) | ((((long) ((codePoint >>> 12) & 63)) | 128) << 8) | ((((long) ((codePoint >>> 6) & 63)) | 128) << 16) | ((((long) (codePoint & 63)) | 128) << 24);
    }

    /* access modifiers changed from: private */
    public static long charToThreeUtf8Bytes(char c) {
        return (long) ((((c >>> 12) | 480) & 255) | ((((c >>> 6) & 63) | 128) << 8) | (((c & '?') | 128) << 16));
    }

    /* access modifiers changed from: private */
    public static long charToTwoUtf8Bytes(char c) {
        return (long) ((((c >>> 6) | 960) & 255) | (((c & '?') | 128) << 8));
    }
}
