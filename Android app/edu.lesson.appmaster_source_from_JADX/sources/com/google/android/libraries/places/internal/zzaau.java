package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
class zzaau extends zzaat {
    protected final byte[] zza;

    zzaau(byte[] bArr) {
        if (bArr != null) {
            this.zza = bArr;
            return;
        }
        throw null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaax) || zzd() != ((zzaax) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzaau)) {
            return obj.equals(this);
        }
        zzaau zzaau = (zzaau) obj;
        int zzk = zzk();
        int zzk2 = zzaau.zzk();
        if (zzk != 0 && zzk2 != 0 && zzk != zzk2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzaau.zzd()) {
            int zzd2 = zzd();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(zzd);
            sb.append(zzd2);
            throw new IllegalArgumentException(sb.toString());
        } else if (zzd > zzaau.zzd()) {
            int zzd3 = zzaau.zzd();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(zzd);
            sb2.append(", ");
            sb2.append(zzd3);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzaau instanceof zzaau)) {
            return zzaau.zzf(0, zzd).equals(zzf(0, zzd));
        } else {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzaau.zza;
            zzaau.zzc();
            int i = 0;
            int i2 = 0;
            while (i < zzd) {
                if (bArr[i] != bArr2[i2]) {
                    return false;
                }
                i++;
                i2++;
            }
            return true;
        }
    }

    public byte zza(int i) {
        return this.zza[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzb(int i) {
        return this.zza[i];
    }

    /* access modifiers changed from: protected */
    public int zzc() {
        return 0;
    }

    public int zzd() {
        return this.zza.length;
    }

    /* access modifiers changed from: protected */
    public final int zze(int i, int i2, int i3) {
        return zzaca.zzd(i, this.zza, 0, i3);
    }

    public final zzaax zzf(int i, int i2) {
        zzj(0, i2, zzd());
        if (i2 == 0) {
            return zzaax.zzb;
        }
        return new zzaar(this.zza, 0, i2);
    }

    /* access modifiers changed from: protected */
    public final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zzh(zzaam zzaam) throws IOException {
        ((zzabc) zzaam).zzc(this.zza, 0, zzd());
    }

    public final boolean zzi() {
        return zzaep.zze(this.zza, 0, zzd());
    }
}
