package com.google.android.gms.internal.places;

import java.io.IOException;
import java.nio.charset.Charset;

class zzag extends zzad {
    protected final byte[] zzen;

    zzag(byte[] bArr) {
        if (bArr != null) {
            this.zzen = bArr;
            return;
        }
        throw new NullPointerException();
    }

    public byte zzi(int i) {
        return this.zzen[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzj(int i) {
        return this.zzen[i];
    }

    public int size() {
        return this.zzen.length;
    }

    public final zzw zzb(int i, int i2) {
        int zzc = zzc(0, i2, size());
        if (zzc == 0) {
            return zzw.zzeg;
        }
        return new zzz(this.zzen, zzag(), zzc);
    }

    /* access modifiers changed from: protected */
    public void zzb(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzen, 0, bArr, 0, i3);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzt zzt) throws IOException {
        zzt.zzb(this.zzen, zzag(), size());
    }

    /* access modifiers changed from: protected */
    public final String zzb(Charset charset) {
        return new String(this.zzen, zzag(), size(), charset);
    }

    public final boolean zzae() {
        int zzag = zzag();
        return zzea.zzf(this.zzen, zzag, size() + zzag);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzw) || size() != ((zzw) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzag)) {
            return obj.equals(this);
        }
        zzag zzag = (zzag) obj;
        int zzaf = zzaf();
        int zzaf2 = zzag.zzaf();
        if (zzaf == 0 || zzaf2 == 0 || zzaf == zzaf2) {
            return zzb(zzag, 0, size());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb(zzw zzw, int i, int i2) {
        if (i2 > zzw.size()) {
            throw new IllegalArgumentException(new StringBuilder(40).append("Length too large: ").append(i2).append(size()).toString());
        } else if (i2 > zzw.size()) {
            throw new IllegalArgumentException(new StringBuilder(59).append("Ran off end of other: 0, ").append(i2).append(", ").append(zzw.size()).toString());
        } else if (!(zzw instanceof zzag)) {
            return zzw.zzb(0, i2).equals(zzb(0, i2));
        } else {
            zzag zzag = (zzag) zzw;
            byte[] bArr = this.zzen;
            byte[] bArr2 = zzag.zzen;
            int zzag2 = zzag() + i2;
            int zzag3 = zzag();
            int zzag4 = zzag.zzag();
            while (zzag3 < zzag2) {
                if (bArr[zzag3] != bArr2[zzag4]) {
                    return false;
                }
                zzag3++;
                zzag4++;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final int zzb(int i, int i2, int i3) {
        return zzbd.zzb(i, this.zzen, zzag(), i3);
    }

    /* access modifiers changed from: protected */
    public int zzag() {
        return 0;
    }
}
