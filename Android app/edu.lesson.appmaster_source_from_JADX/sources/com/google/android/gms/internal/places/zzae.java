package com.google.android.gms.internal.places;

final class zzae {
    private final byte[] buffer;
    private final zzaj zzem;

    private zzae(int i) {
        byte[] bArr = new byte[i];
        this.buffer = bArr;
        this.zzem = zzaj.zzc(bArr);
    }

    public final zzw zzah() {
        if (this.zzem.zzak() == 0) {
            return new zzag(this.buffer);
        }
        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final zzaj zzai() {
        return this.zzem;
    }

    /* synthetic */ zzae(int i, zzv zzv) {
        this(i);
    }
}
