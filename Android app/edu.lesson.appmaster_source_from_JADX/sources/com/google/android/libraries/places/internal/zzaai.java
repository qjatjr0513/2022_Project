package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzaai;
import com.google.android.libraries.places.internal.zzaaj;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzaai<MessageType extends zzaaj<MessageType, BuilderType>, BuilderType extends zzaai<MessageType, BuilderType>> implements zzacy {
    /* renamed from: zzo */
    public abstract BuilderType clone();

    /* access modifiers changed from: protected */
    public abstract BuilderType zzp(MessageType messagetype);

    public final /* bridge */ /* synthetic */ zzacy zzq(zzacz zzacz) {
        if (zzw().getClass().isInstance(zzacz)) {
            return zzp((zzaaj) zzacz);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
