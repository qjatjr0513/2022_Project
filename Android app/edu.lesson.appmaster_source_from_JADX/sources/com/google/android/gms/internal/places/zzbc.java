package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzbc;
import com.google.android.gms.internal.places.zzbc.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzbc<MessageType extends zzbc<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzm<MessageType, BuilderType> {
    private static Map<Object, zzbc<?, ?>> zzij = new ConcurrentHashMap();
    protected zzdr zzih = zzdr.zzdh();
    private int zzii = -1;

    public static class zzd<T extends zzbc<T, ?>> extends zzn<T> {
        private final T zzie;

        public zzd(T t) {
            this.zzie = t;
        }
    }

    /* 'enum' modifier removed */
    public static final class zze {
        public static final int zzil = 1;
        public static final int zzim = 2;
        public static final int zzin = 3;
        public static final int zzio = 4;
        public static final int zzip = 5;
        public static final int zziq = 6;
        public static final int zzir = 7;
        private static final /* synthetic */ int[] zzis = {1, 2, 3, 4, 5, 6, 7};
        public static final int zzit = 1;
        public static final int zziu = 2;
        private static final /* synthetic */ int[] zziv = {1, 2};
        public static final int zziw = 1;
        public static final int zzix = 2;
        private static final /* synthetic */ int[] zziy = {1, 2};

        public static int[] zzbn() {
            return (int[]) zzis.clone();
        }
    }

    public static class zzf<ContainingType extends zzck, Type> extends zzan<ContainingType, Type> {
    }

    /* access modifiers changed from: protected */
    public abstract Object zzb(int i, Object obj, Object obj2);

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzbc<MessageType, BuilderType> implements zzcm {
        protected zzav<Object> zzik = zzav.zzau();

        /* access modifiers changed from: package-private */
        public final zzav<Object> zzbm() {
            if (this.zzik.isImmutable()) {
                this.zzik = (zzav) this.zzik.clone();
            }
            return this.zzik;
        }
    }

    public String toString() {
        return zzcl.zzb(this, super.toString());
    }

    public int hashCode() {
        if (this.zzdt != 0) {
            return this.zzdt;
        }
        this.zzdt = zzcv.zzcq().zzq(this).hashCode(this);
        return this.zzdt;
    }

    public static abstract class zzb<MessageType extends zzbc<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzo<MessageType, BuilderType> {
        private final MessageType zzie;
        private MessageType zzif;
        private boolean zzig = false;

        protected zzb(MessageType messagetype) {
            this.zzie = messagetype;
            this.zzif = (zzbc) messagetype.zzb(zze.zzio, (Object) null, (Object) null);
        }

        public final boolean isInitialized() {
            return zzbc.zzb(this.zzif, false);
        }

        /* renamed from: zzbc */
        public MessageType zzbe() {
            if (this.zzig) {
                return this.zzif;
            }
            this.zzif.zzab();
            this.zzig = true;
            return this.zzif;
        }

        /* renamed from: zzbd */
        public final MessageType zzbf() {
            MessageType messagetype = (zzbc) zzbe();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzdp(messagetype);
        }

        public final BuilderType zzb(MessageType messagetype) {
            if (this.zzig) {
                MessageType messagetype2 = (zzbc) this.zzif.zzb(zze.zzio, (Object) null, (Object) null);
                zzb(messagetype2, this.zzif);
                this.zzif = messagetype2;
                this.zzig = false;
            }
            zzb(this.zzif, messagetype);
            return this;
        }

        private static void zzb(MessageType messagetype, MessageType messagetype2) {
            zzcv.zzcq().zzq(messagetype).zzd(messagetype, messagetype2);
        }

        public final /* synthetic */ zzo zzx() {
            return (zzb) clone();
        }

        public final /* synthetic */ zzck zzbg() {
            return this.zzie;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzb = (zzb) ((zzbc) this.zzie).zzb(zze.zzip, (Object) null, (Object) null);
            zzb.zzb((zzbc) zzbe());
            return zzb;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzbc) zzb(zze.zziq, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzcv.zzcq().zzq(this).equals(this, (zzbc) obj);
    }

    /* access modifiers changed from: protected */
    public final void zzab() {
        zzcv.zzcq().zzq(this).zzd(this);
    }

    public final boolean isInitialized() {
        return zzb(this, Boolean.TRUE.booleanValue());
    }

    /* access modifiers changed from: package-private */
    public final int zzw() {
        return this.zzii;
    }

    /* access modifiers changed from: package-private */
    public final void zze(int i) {
        this.zzii = i;
    }

    public final void zzc(zzaj zzaj) throws IOException {
        zzcv.zzcq().zzf(getClass()).zzb(this, zzam.zzb(zzaj));
    }

    public final int zzbh() {
        if (this.zzii == -1) {
            this.zzii = zzcv.zzcq().zzq(this).zzn(this);
        }
        return this.zzii;
    }

    static <T extends zzbc<?, ?>> T zzd(Class<T> cls) {
        T t = (zzbc) zzij.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzbc) zzij.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzbc) ((zzbc) zzdy.zzh(cls)).zzb(zze.zziq, (Object) null, (Object) null);
            if (t != null) {
                zzij.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzbc<?, ?>> void zzb(Class<T> cls, T t) {
        zzij.put(cls, t);
    }

    protected static Object zzb(zzck zzck, String str, Object[] objArr) {
        return new zzcx(zzck, str, objArr);
    }

    static Object zzb(Method method, Object obj, Object... objArr) {
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

    protected static final <T extends zzbc<T, ?>> boolean zzb(T t, boolean z) {
        byte byteValue = ((Byte) t.zzb(zze.zzil, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzp = zzcv.zzcq().zzq(t).zzp(t);
        if (z) {
            t.zzb(zze.zzim, (Object) zzp ? t : null, (Object) null);
        }
        return zzp;
    }

    protected static zzbi zzbi() {
        return zzbe.zzbo();
    }

    protected static <E> zzbh<E> zzbj() {
        return zzcy.zzct();
    }

    private static <T extends zzbc<T, ?>> T zzb(T t, byte[] bArr, int i, int i2, zzap zzap) throws zzbk {
        T t2 = (zzbc) t.zzb(zze.zzio, (Object) null, (Object) null);
        try {
            zzcv.zzcq().zzq(t2).zzb(t2, bArr, 0, i2, new zzr(zzap));
            t2.zzab();
            if (t2.zzdt == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzbk) {
                throw ((zzbk) e.getCause());
            }
            throw new zzbk(e.getMessage()).zzh(t2);
        } catch (IndexOutOfBoundsException e2) {
            throw zzbk.zzbp().zzh(t2);
        }
    }

    protected static <T extends zzbc<T, ?>> T zzb(T t, byte[] bArr) throws zzbk {
        T zzb2 = zzb(t, bArr, 0, bArr.length, zzap.zzao());
        if (zzb2 == null || zzb2.isInitialized()) {
            return zzb2;
        }
        throw new zzbk(new zzdp(zzb2).getMessage()).zzh(zzb2);
    }

    public final /* synthetic */ zzcj zzbk() {
        zzb zzb2 = (zzb) zzb(zze.zzip, (Object) null, (Object) null);
        zzb2.zzb(this);
        return zzb2;
    }

    public final /* synthetic */ zzcj zzbl() {
        return (zzb) zzb(zze.zzip, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzck zzbg() {
        return (zzbc) zzb(zze.zziq, (Object) null, (Object) null);
    }
}
