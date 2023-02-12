package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzace */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzace extends AbstractList<String> implements RandomAccess, zzaaj {
    /* access modifiers changed from: private */
    public final zzaaj zza;

    public zzace(zzaaj zzaaj) {
        this.zza = zzaaj;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzaai) this.zza).get(i);
    }

    public final Iterator<String> iterator() {
        return new zzacd(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzacc(this, i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzaaj zze() {
        return this;
    }

    public final Object zzf(int i) {
        return this.zza.zzf(i);
    }

    public final List<?> zzh() {
        return this.zza.zzh();
    }

    public final void zzi(zzyu zzyu) {
        throw new UnsupportedOperationException();
    }
}
