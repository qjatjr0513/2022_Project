package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzig;
import com.google.android.gms.internal.measurement.zzih;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public abstract class zzig<MessageType extends zzih<MessageType, BuilderType>, BuilderType extends zzig<MessageType, BuilderType>> implements zzlb {
    /* renamed from: zzaq */
    public abstract BuilderType clone();

    /* access modifiers changed from: protected */
    public abstract BuilderType zzar(MessageType messagetype);

    public BuilderType zzas(byte[] bArr, int i, int i2) throws zzkh {
        throw null;
    }

    public BuilderType zzat(byte[] bArr, int i, int i2, zzjj zzjj) throws zzkh {
        throw null;
    }

    public final /* bridge */ /* synthetic */ zzlb zzau(zzlc zzlc) {
        if (zzbL().getClass().isInstance(zzlc)) {
            return zzar((zzih) zzlc);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public final /* synthetic */ zzlb zzav(byte[] bArr) throws zzkh {
        return zzas(bArr, 0, bArr.length);
    }

    public final /* synthetic */ zzlb zzaw(byte[] bArr, zzjj zzjj) throws zzkh {
        return zzat(bArr, 0, bArr.length, zzjj);
    }
}
