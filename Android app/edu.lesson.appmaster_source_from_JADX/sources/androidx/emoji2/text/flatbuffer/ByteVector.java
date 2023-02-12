package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class ByteVector extends BaseVector {
    public ByteVector __assign(int vector, ByteBuffer bb) {
        __reset(vector, 1, bb);
        return this;
    }

    public byte get(int j) {
        return this.f395bb.get(__element(j));
    }

    public int getAsUnsigned(int j) {
        return get(j) & 255;
    }
}
