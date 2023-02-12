package com.google.android.gms.internal.places;

final class zzak extends zzai {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzet;
    private int zzeu;
    private int zzev;
    private int zzew;

    private zzak(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzew = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzev = i;
        this.zzet = z;
    }

    public final int zzl(int i) throws zzbk {
        if (i >= 0) {
            int zzaj = i + zzaj();
            int i2 = this.zzew;
            if (zzaj <= i2) {
                this.zzew = zzaj;
                int i3 = this.limit + this.zzeu;
                this.limit = i3;
                int i4 = i3 - this.zzev;
                if (i4 > zzaj) {
                    int i5 = i4 - zzaj;
                    this.zzeu = i5;
                    this.limit = i3 - i5;
                } else {
                    this.zzeu = 0;
                }
                return i2;
            }
            throw zzbk.zzbp();
        }
        throw zzbk.zzbq();
    }

    public final int zzaj() {
        return this.pos - this.zzev;
    }
}
