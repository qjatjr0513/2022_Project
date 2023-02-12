package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.internal.p010firebaseauthapi.zzzs;
import com.google.android.gms.internal.p010firebaseauthapi.zzzw;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzs */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public class zzzs<MessageType extends zzzw<MessageType, BuilderType>, BuilderType extends zzzs<MessageType, BuilderType>> extends zzyd<MessageType, BuilderType> {
    protected MessageType zza;
    protected boolean zzb = false;
    private final MessageType zzc;

    protected zzzs(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (zzzw) messagetype.zzj(4, (Object) null, (Object) null);
    }

    private static final void zza(MessageType messagetype, MessageType messagetype2) {
        zzabh.zza().zzb(messagetype.getClass()).zzg(messagetype, messagetype2);
    }

    public final /* bridge */ /* synthetic */ zzaaz zzI() {
        return this.zzc;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ zzyd zzg(zzye zzye) {
        zzj((zzzw) zzye);
        return this;
    }

    /* renamed from: zzi */
    public final BuilderType zzf() {
        BuilderType buildertype = (zzzs) this.zzc.zzj(5, (Object) null, (Object) null);
        buildertype.zzj(zzm());
        return buildertype;
    }

    public final BuilderType zzj(MessageType messagetype) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zza(this.zza, messagetype);
        return this;
    }

    public final MessageType zzk() {
        MessageType zzl = zzm();
        if (zzl.zzH()) {
            return zzl;
        }
        throw new zzaby(zzl);
    }

    /* renamed from: zzl */
    public MessageType zzm() {
        if (this.zzb) {
            return this.zza;
        }
        MessageType messagetype = this.zza;
        zzabh.zza().zzb(messagetype.getClass()).zzf(messagetype);
        this.zzb = true;
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public void zzo() {
        MessageType messagetype = (zzzw) this.zza.zzj(4, (Object) null, (Object) null);
        zza(messagetype, this.zza);
        this.zza = messagetype;
    }
}
