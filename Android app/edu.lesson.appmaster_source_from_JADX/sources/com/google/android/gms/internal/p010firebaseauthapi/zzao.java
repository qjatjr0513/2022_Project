package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.internal.p010firebaseauthapi.zzaaz;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzao */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzao<KeyFormatProtoT extends zzaaz, KeyProtoT extends zzaaz> {
    final zzat<KeyFormatProtoT, KeyProtoT> zza;

    zzao(zzat<KeyFormatProtoT, KeyProtoT> zzat) {
        this.zza = zzat;
    }

    /* access modifiers changed from: package-private */
    public final KeyProtoT zza(zzyu zzyu) throws GeneralSecurityException, zzaae {
        KeyFormatProtoT zza2 = this.zza.zza(zzyu);
        this.zza.zze(zza2);
        return this.zza.zzc(zza2);
    }
}
