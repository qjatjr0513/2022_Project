package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.firebase.analytics.FirebaseAnalytics;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzy */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzy extends zzac {
    final /* synthetic */ zzz zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzy(zzz zzz, zzae zzae, CharSequence charSequence) {
        super(zzae, charSequence);
        this.zza = zzz;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(int i) {
        return i + 1;
    }

    /* access modifiers changed from: package-private */
    public final int zzd(int i) {
        String str;
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        if (i < 0 || i > length) {
            if (i < 0) {
                str = zzaf.zzb("%s (%s) must not be negative", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i));
            } else if (length < 0) {
                StringBuilder sb = new StringBuilder(26);
                sb.append("negative size: ");
                sb.append(length);
                throw new IllegalArgumentException(sb.toString());
            } else {
                str = zzaf.zzb("%s (%s) must not be greater than size (%s)", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i), Integer.valueOf(length));
            }
            throw new IndexOutOfBoundsException(str);
        }
        while (i < length) {
            if (charSequence.charAt(i) == '.') {
                return i;
            }
            i++;
        }
        return -1;
    }
}
