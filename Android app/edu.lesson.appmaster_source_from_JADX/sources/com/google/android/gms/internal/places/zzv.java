package com.google.android.gms.internal.places;

import java.util.NoSuchElementException;

final class zzv extends zzx {
    private final int limit;
    private int position = 0;
    private final /* synthetic */ zzw zzef;

    zzv(zzw zzw) {
        this.zzef = zzw;
        this.limit = zzw.size();
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return this.zzef.zzj(i);
        }
        throw new NoSuchElementException();
    }
}
