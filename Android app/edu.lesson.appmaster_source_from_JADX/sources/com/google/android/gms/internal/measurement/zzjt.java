package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzjx;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public class zzjt<MessageType extends zzjx<MessageType, BuilderType>, BuilderType extends zzjt<MessageType, BuilderType>> extends zzig<MessageType, BuilderType> {
    protected MessageType zza;
    protected boolean zzb = false;
    private final MessageType zzc;

    protected zzjt(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (zzjx) messagetype.zzl(4, (Object) null, (Object) null);
    }

    private static final void zza(MessageType messagetype, MessageType messagetype2) {
        zzlk.zza().zzb(messagetype.getClass()).zzg(messagetype, messagetype2);
    }

    public final MessageType zzaA() {
        MessageType messagetype;
        MessageType zzaB = zzaC();
        boolean booleanValue = Boolean.TRUE.booleanValue();
        boolean z = true;
        byte byteValue = ((Byte) zzaB.zzl(1, (Object) null, (Object) null)).byteValue();
        if (byteValue != 1) {
            if (byteValue == 0) {
                z = false;
            } else {
                boolean zzj = zzlk.zza().zzb(zzaB.getClass()).zzj(zzaB);
                if (booleanValue) {
                    if (true != zzj) {
                        messagetype = null;
                    } else {
                        messagetype = zzaB;
                    }
                    zzaB.zzl(2, messagetype, (Object) null);
                }
                z = zzj;
            }
        }
        if (z) {
            return zzaB;
        }
        throw new zzma(zzaB);
    }

    /* renamed from: zzaB */
    public MessageType zzaC() {
        if (this.zzb) {
            return this.zza;
        }
        MessageType messagetype = this.zza;
        zzlk.zza().zzb(messagetype.getClass()).zzf(messagetype);
        this.zzb = true;
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public void zzaE() {
        MessageType messagetype = (zzjx) this.zza.zzl(4, (Object) null, (Object) null);
        zza(messagetype, this.zza);
        this.zza = messagetype;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzig zzar(zzih zzih) {
        zzay((zzjx) zzih);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzig zzas(byte[] bArr, int i, int i2) throws zzkh {
        zzaz(bArr, 0, i2, zzjj.zza());
        return this;
    }

    public final /* bridge */ /* synthetic */ zzig zzat(byte[] bArr, int i, int i2, zzjj zzjj) throws zzkh {
        zzaz(bArr, 0, i2, zzjj);
        return this;
    }

    /* renamed from: zzax */
    public final BuilderType zzaq() {
        BuilderType buildertype = (zzjt) this.zzc.zzl(5, (Object) null, (Object) null);
        buildertype.zzay(zzaC());
        return buildertype;
    }

    public final BuilderType zzay(MessageType messagetype) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zza(this.zza, messagetype);
        return this;
    }

    public final BuilderType zzaz(byte[] bArr, int i, int i2, zzjj zzjj) throws zzkh {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        try {
            zzlk.zza().zzb(this.zza.getClass()).zzh(this.zza, bArr, 0, i2, new zzik(zzjj));
            return this;
        } catch (zzkh e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw zzkh.zzf();
        } catch (IOException e3) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e3);
        }
    }

    public final /* synthetic */ zzlc zzbL() {
        return this.zzc;
    }
}
