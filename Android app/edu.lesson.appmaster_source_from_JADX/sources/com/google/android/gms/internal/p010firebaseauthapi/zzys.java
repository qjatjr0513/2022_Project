package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;
import java.nio.charset.Charset;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzys */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
class zzys extends zzyr {
    protected final byte[] zza;

    zzys(byte[] bArr) {
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
        if (!(obj instanceof zzyu) || zzd() != ((zzyu) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzys)) {
            return obj.equals(this);
        }
        zzys zzys = (zzys) obj;
        int zzm = zzm();
        int zzm2 = zzys.zzm();
        if (zzm != 0 && zzm2 != 0 && zzm != zzm2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzys.zzd()) {
            int zzd2 = zzd();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(zzd);
            sb.append(zzd2);
            throw new IllegalArgumentException(sb.toString());
        } else if (zzd > zzys.zzd()) {
            int zzd3 = zzys.zzd();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(zzd);
            sb2.append(", ");
            sb2.append(zzd3);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzys instanceof zzys)) {
            return zzys.zzg(0, zzd).equals(zzg(0, zzd));
        } else {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzys.zza;
            zzys.zzc();
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
    public void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    /* access modifiers changed from: protected */
    public final int zzf(int i, int i2, int i3) {
        return zzaac.zzd(i, this.zza, 0, i3);
    }

    public final zzyu zzg(int i, int i2) {
        int zzl = zzl(0, i2, zzd());
        if (zzl == 0) {
            return zzyu.zzb;
        }
        return new zzyp(this.zza, 0, zzl);
    }

    public final zzyx zzh() {
        return zzyx.zzu(this.zza, 0, zzd(), true);
    }

    /* access modifiers changed from: protected */
    public final String zzi(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zzj(zzyk zzyk) throws IOException {
        zzyk.zza(this.zza, 0, zzd());
    }

    public final boolean zzk() {
        return zzaco.zzf(this.zza, 0, zzd());
    }
}
