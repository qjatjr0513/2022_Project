package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzm;
import com.google.android.gms.internal.places.zzo;

public abstract class zzo<MessageType extends zzm<MessageType, BuilderType>, BuilderType extends zzo<MessageType, BuilderType>> implements zzcj {
    /* access modifiers changed from: protected */
    public abstract BuilderType zzb(MessageType messagetype);

    /* renamed from: zzx */
    public abstract BuilderType clone();

    public final /* synthetic */ zzcj zzb(zzck zzck) {
        if (zzbg().getClass().isInstance(zzck)) {
            return zzb((zzm) zzck);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
