package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f105c;

    /* renamed from: d */
    private final int f106d;

    /* renamed from: k0 */
    private final long f107k0;

    /* renamed from: k1 */
    private final long f108k1;

    SipHashFunction(int c, int d, long k0, long k1) {
        boolean z = true;
        Preconditions.checkArgument(c > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", c);
        Preconditions.checkArgument(d <= 0 ? false : z, "The number of SipRound iterations (d=%s) during Finalization must be positive.", d);
        this.f105c = c;
        this.f106d = d;
        this.f107k0 = k0;
        this.f108k1 = k1;
    }

    public int bits() {
        return 64;
    }

    public Hasher newHasher() {
        return new SipHasher(this.f105c, this.f106d, this.f107k0, this.f108k1);
    }

    public String toString() {
        int i = this.f105c;
        int i2 = this.f106d;
        long j = this.f107k0;
        return new StringBuilder(81).append("Hashing.sipHash").append(i).append(i2).append("(").append(j).append(", ").append(this.f108k1).append(")").toString();
    }

    public boolean equals(@NullableDecl Object object) {
        if (!(object instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction other = (SipHashFunction) object;
        if (this.f105c == other.f105c && this.f106d == other.f106d && this.f107k0 == other.f107k0 && this.f108k1 == other.f108k1) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((long) ((getClass().hashCode() ^ this.f105c) ^ this.f106d)) ^ this.f107k0) ^ this.f108k1);
    }

    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f109b = 0;

        /* renamed from: c */
        private final int f110c;

        /* renamed from: d */
        private final int f111d;
        private long finalM = 0;

        /* renamed from: v0 */
        private long f112v0 = 8317987319222330741L;

        /* renamed from: v1 */
        private long f113v1 = 7237128888997146477L;

        /* renamed from: v2 */
        private long f114v2 = 7816392313619706465L;

        /* renamed from: v3 */
        private long f115v3 = 8387220255154660723L;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SipHasher(int c, int d, long k0, long k1) {
            super(8);
            this.f110c = c;
            this.f111d = d;
            this.f112v0 = 8317987319222330741L ^ k0;
            this.f113v1 = 7237128888997146477L ^ k1;
            this.f114v2 = 7816392313619706465L ^ k0;
            this.f115v3 = 8387220255154660723L ^ k1;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer buffer) {
            this.f109b += 8;
            processM(buffer.getLong());
        }

        /* access modifiers changed from: protected */
        public void processRemaining(ByteBuffer buffer) {
            this.f109b += (long) buffer.remaining();
            int i = 0;
            while (buffer.hasRemaining()) {
                this.finalM ^= (((long) buffer.get()) & 255) << i;
                i += 8;
            }
        }

        /* access modifiers changed from: protected */
        public HashCode makeHash() {
            long j = this.finalM ^ (this.f109b << 56);
            this.finalM = j;
            processM(j);
            this.f114v2 ^= 255;
            sipRound(this.f111d);
            return HashCode.fromLong(((this.f112v0 ^ this.f113v1) ^ this.f114v2) ^ this.f115v3);
        }

        private void processM(long m) {
            this.f115v3 ^= m;
            sipRound(this.f110c);
            this.f112v0 ^= m;
        }

        private void sipRound(int iterations) {
            for (int i = 0; i < iterations; i++) {
                long j = this.f112v0;
                long j2 = this.f113v1;
                this.f112v0 = j + j2;
                this.f114v2 += this.f115v3;
                this.f113v1 = Long.rotateLeft(j2, 13);
                long rotateLeft = Long.rotateLeft(this.f115v3, 16);
                this.f115v3 = rotateLeft;
                long j3 = this.f113v1;
                long j4 = this.f112v0;
                this.f113v1 = j3 ^ j4;
                this.f115v3 = rotateLeft ^ this.f114v2;
                long rotateLeft2 = Long.rotateLeft(j4, 32);
                this.f112v0 = rotateLeft2;
                long j5 = this.f114v2;
                long j6 = this.f113v1;
                this.f114v2 = j5 + j6;
                this.f112v0 = rotateLeft2 + this.f115v3;
                this.f113v1 = Long.rotateLeft(j6, 17);
                long rotateLeft3 = Long.rotateLeft(this.f115v3, 21);
                this.f115v3 = rotateLeft3;
                long j7 = this.f113v1;
                long j8 = this.f114v2;
                this.f113v1 = j7 ^ j8;
                this.f115v3 = rotateLeft3 ^ this.f112v0;
                this.f114v2 = Long.rotateLeft(j8, 32);
            }
        }
    }
}
