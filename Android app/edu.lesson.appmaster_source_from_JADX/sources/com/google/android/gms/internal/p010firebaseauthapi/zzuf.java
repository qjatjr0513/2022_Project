package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuf */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzuf extends zzpv implements Api.ApiOptions.HasOptions {
    private final String zzb;

    /* synthetic */ zzuf(String str, zzud zzud) {
        this.zzb = Preconditions.checkNotEmpty(str, "A valid API key must be provided");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzuf)) {
            return false;
        }
        zzuf zzuf = (zzuf) obj;
        return Objects.equal(this.zzb, zzuf.zzb) && this.zza == zzuf.zza;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzb) + (true ^ this.zza ? 1 : 0);
    }

    /* renamed from: zzb */
    public final zzuf zza() {
        return new zzuf(Preconditions.checkNotEmpty(this.zzb), (zzud) null);
    }

    public final String zzc() {
        return this.zzb;
    }
}
