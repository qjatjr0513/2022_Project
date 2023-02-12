package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzj */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
abstract class zzj<T> implements Iterator<T> {
    @NullableDecl
    private T zza;
    private int zzb = 2;

    protected zzj() {
    }

    public final T next() {
        if (hasNext()) {
            this.zzb = 2;
            T t = this.zza;
            this.zza = null;
            return t;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public abstract T zza();

    /* access modifiers changed from: protected */
    @NullableDecl
    public final T zzb() {
        this.zzb = 3;
        return null;
    }

    public final boolean hasNext() {
        int i = this.zzb;
        if (i != 4) {
            int i2 = i - 1;
            if (i != 0) {
                switch (i2) {
                    case 0:
                        return true;
                    case 2:
                        return false;
                    default:
                        this.zzb = 4;
                        this.zza = zza();
                        if (this.zzb == 3) {
                            return false;
                        }
                        this.zzb = 1;
                        return true;
                }
            } else {
                throw null;
            }
        } else {
            throw new IllegalStateException();
        }
    }
}
