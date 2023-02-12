package com.google.android.gms.internal.common;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
abstract class zzj<T> implements Iterator<T> {
    @CheckForNull
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
    @CheckForNull
    public abstract T zza();

    /* access modifiers changed from: protected */
    @CheckForNull
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
