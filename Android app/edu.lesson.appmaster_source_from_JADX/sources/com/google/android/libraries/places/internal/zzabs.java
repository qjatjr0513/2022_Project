package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzabp;
import com.google.android.libraries.places.internal.zzabs;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzabs<MessageType extends zzabs<MessageType, BuilderType>, BuilderType extends zzabp<MessageType, BuilderType>> extends zzaaj<MessageType, BuilderType> {
    private static final Map<Object, zzabs<?, ?>> zzb = new ConcurrentHashMap();
    protected zzaec zzc = zzaec.zzc();
    protected int zzd = -1;

    protected static zzaby zzA() {
        return zzaco.zze();
    }

    protected static <E> zzabz<E> zzB() {
        return zzadi.zzd();
    }

    protected static <E> zzabz<E> zzC(zzabz<E> zzabz) {
        int i;
        int size = zzabz.size();
        if (size == 0) {
            i = 10;
        } else {
            i = size + size;
        }
        return zzabz.zzf(i);
    }

    static Object zzE(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static Object zzF(zzacz zzacz, String str, Object[] objArr) {
        return new zzadj(zzacz, str, objArr);
    }

    protected static <T extends zzabs> void zzG(Class<T> cls, T t) {
        zzb.put(cls, t);
    }

    static <T extends zzabs> T zzy(Class<T> cls) {
        Map<Object, zzabs<?, ?>> map = zzb;
        T t = (zzabs) map.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzabs) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzabs) ((zzabs) zzael.zze(cls)).zzd(6, (Object) null, (Object) null);
            if (t != null) {
                map.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static zzabw zzz() {
        return zzabt.zze();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzadh.zza().zzb(getClass()).zze(this, (zzabs) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzb2 = zzadh.zza().zzb(getClass()).zzb(this);
        this.zza = zzb2;
        return zzb2;
    }

    public final String toString() {
        return zzadb.zza(this, super.toString());
    }

    public final /* bridge */ /* synthetic */ zzacy zzD() {
        zzabp zzabp = (zzabp) zzd(5, (Object) null, (Object) null);
        zzabp.zzs(this);
        return zzabp;
    }

    public final void zzH(zzabf zzabf) throws IOException {
        zzadh.zza().zzb(getClass()).zzi(this, zzabg.zza(zzabf));
    }

    /* access modifiers changed from: protected */
    public abstract Object zzd(int i, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public final int zzr() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final void zzu(int i) {
        this.zzd = i;
    }

    public final int zzv() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int zza = zzadh.zza().zzb(getClass()).zza(this);
        this.zzd = zza;
        return zza;
    }

    public final /* bridge */ /* synthetic */ zzacz zzw() {
        return (zzabs) zzd(6, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzabs<MessageType, BuilderType>, BuilderType extends zzabp<MessageType, BuilderType>> BuilderType zzx() {
        return (zzabp) zzd(5, (Object) null, (Object) null);
    }
}
