package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlc */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzlc extends ThreadLocal<Mac> {
    final /* synthetic */ zzld zza;

    zzlc(zzld zzld) {
        this.zza = zzld;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public final Mac initialValue() {
        try {
            Mac zza2 = zzkp.zzb.zza(this.zza.zzb);
            zza2.init(this.zza.zzc);
            return zza2;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
