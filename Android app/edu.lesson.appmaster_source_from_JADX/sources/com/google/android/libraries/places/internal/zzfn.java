package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzfn extends zzfp {
    final /* synthetic */ zzfo zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfn(zzfo zzfo, zzfq zzfq, CharSequence charSequence) {
        super(zzfq, "2.5.0");
        this.zza = zzfo;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(int i) {
        return i + 1;
    }

    /* access modifiers changed from: package-private */
    public final int zzd(int i) {
        int length = "2.5.0".length();
        zzfm.zzb(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if ("2.5.0".charAt(i) == '.') {
                return i;
            }
            i++;
        }
        return -1;
    }
}
