package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
class zzjm extends zzjn {
    final zzjj zzb;
    @CheckForNull
    final Character zzc;

    zzjm(zzjj zzjj, @CheckForNull Character ch) {
        boolean z;
        this.zzb = zzjj;
        if (ch != null) {
            z = !zzjj.zzb(ch.charValue());
        } else {
            z = true;
        }
        if (z) {
            this.zzc = ch;
            return;
        }
        throw new IllegalArgumentException(zzfr.zza("Padding character %s was already in alphabet", ch));
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzjm) {
            zzjm zzjm = (zzjm) obj;
            if (!this.zzb.equals(zzjm.zzb) || !zzfi.zza(this.zzc, zzjm.zzc)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb.hashCode() ^ Arrays.hashCode(new Object[]{this.zzc});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zzb.toString());
        if (8 % this.zzb.zzb != 0) {
            if (this.zzc == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzc);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void zza(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzfm.zzg(0, i2, bArr.length);
        while (i3 < i2) {
            zzc(appendable, bArr, i3, Math.min(this.zzb.zzd, i2 - i3));
            i3 += this.zzb.zzd;
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzb(int i) {
        zzjj zzjj = this.zzb;
        return zzjj.zzc * zzze.zza(i, zzjj.zzd, RoundingMode.CEILING);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        boolean z;
        zzfm.zzg(i, i + i2, bArr.length);
        int i3 = 0;
        if (i2 <= this.zzb.zzd) {
            z = true;
        } else {
            z = false;
        }
        zzfm.zzd(z);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | ((long) (bArr[i + i4] & 255))) << 8;
        }
        int i5 = ((i2 + 1) * 8) - this.zzb.zzb;
        while (i3 < i2 * 8) {
            zzjj zzjj = this.zzb;
            appendable.append(zzjj.zza(((int) (j >>> (i5 - i3))) & zzjj.zza));
            i3 += this.zzb.zzb;
        }
        if (this.zzc != null) {
            while (i3 < this.zzb.zzd * 8) {
                appendable.append(this.zzc.charValue());
                i3 += this.zzb.zzb;
            }
        }
    }

    zzjm(String str, String str2, @CheckForNull Character ch) {
        this(new zzjj(str, str2.toCharArray()), ch);
    }
}
