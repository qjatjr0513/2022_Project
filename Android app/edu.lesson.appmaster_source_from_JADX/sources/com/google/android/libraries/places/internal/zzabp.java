package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzabp;
import com.google.android.libraries.places.internal.zzabs;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public class zzabp<MessageType extends zzabs<MessageType, BuilderType>, BuilderType extends zzabp<MessageType, BuilderType>> extends zzaai<MessageType, BuilderType> {
    protected MessageType zza;
    protected boolean zzb = false;
    private final MessageType zzc;

    protected zzabp(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (zzabs) messagetype.zzd(4, (Object) null, (Object) null);
    }

    private static final void zza(MessageType messagetype, MessageType messagetype2) {
        zzadh.zza().zzb(messagetype.getClass()).zzd(messagetype, messagetype2);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ zzaai zzp(zzaaj zzaaj) {
        zzs((zzabs) zzaaj);
        return this;
    }

    /* renamed from: zzr */
    public final BuilderType zzo() {
        BuilderType buildertype = (zzabp) this.zzc.zzd(5, (Object) null, (Object) null);
        buildertype.zzs(zzv());
        return buildertype;
    }

    public final BuilderType zzs(MessageType messagetype) {
        if (this.zzb) {
            zzx();
            this.zzb = false;
        }
        zza(this.zza, messagetype);
        return this;
    }

    public final MessageType zzt() {
        MessageType messagetype;
        MessageType zzu = zzv();
        boolean booleanValue = Boolean.TRUE.booleanValue();
        boolean z = true;
        byte byteValue = ((Byte) zzu.zzd(1, (Object) null, (Object) null)).byteValue();
        if (byteValue != 1) {
            if (byteValue == 0) {
                z = false;
            } else {
                boolean zzf = zzadh.zza().zzb(zzu.getClass()).zzf(zzu);
                if (booleanValue) {
                    if (true != zzf) {
                        messagetype = null;
                    } else {
                        messagetype = zzu;
                    }
                    zzu.zzd(2, messagetype, (Object) null);
                }
                z = zzf;
            }
        }
        if (z) {
            return zzu;
        }
        throw new zzaea(zzu);
    }

    /* renamed from: zzu */
    public MessageType zzv() {
        if (this.zzb) {
            return this.zza;
        }
        MessageType messagetype = this.zza;
        zzadh.zza().zzb(messagetype.getClass()).zzc(messagetype);
        this.zzb = true;
        return this.zza;
    }

    public final /* bridge */ /* synthetic */ zzacz zzw() {
        return this.zzc;
    }

    /* access modifiers changed from: protected */
    public void zzx() {
        MessageType messagetype = (zzabs) this.zza.zzd(4, (Object) null, (Object) null);
        zza(messagetype, this.zza);
        this.zza = messagetype;
    }
}
