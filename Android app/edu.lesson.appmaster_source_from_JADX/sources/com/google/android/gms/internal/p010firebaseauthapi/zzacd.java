package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.Iterator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacd */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzacd implements Iterator<String> {
    final Iterator<String> zza;
    final /* synthetic */ zzace zzb;

    zzacd(zzace zzace) {
        this.zzb = zzace;
        this.zza = zzace.zza.iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return this.zza.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
