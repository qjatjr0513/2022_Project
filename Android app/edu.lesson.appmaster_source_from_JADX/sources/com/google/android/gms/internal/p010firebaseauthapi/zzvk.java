package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvk */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzvk extends zztl {
    final /* synthetic */ zzvn zza;
    private final String zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzvk(zzvn zzvn, zztl zztl, String str) {
        super(zztl);
        this.zza = zzvn;
        this.zzb = str;
    }

    public final void zzb(String str) {
        zzvn.zza.mo31544d("onCodeSent", new Object[0]);
        zzvm zzvm = (zzvm) this.zza.zzd.get(this.zzb);
        if (zzvm != null) {
            for (zztl zzb2 : zzvm.zzb) {
                zzb2.zzb(str);
            }
            zzvm.zzg = true;
            zzvm.zzd = str;
            if (zzvm.zza <= 0) {
                this.zza.zzh(this.zzb);
            } else if (!zzvm.zzc) {
                this.zza.zzn(this.zzb);
            } else if (!zzaf.zzd(zzvm.zze)) {
                zzvn.zze(this.zza, this.zzb);
            }
        }
    }

    public final void zzh(Status status) {
        Logger zza2 = zzvn.zza;
        String statusCodeString = CommonStatusCodes.getStatusCodeString(status.getStatusCode());
        String statusMessage = status.getStatusMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(statusCodeString).length() + 39 + String.valueOf(statusMessage).length());
        sb.append("SMS verification code request failed: ");
        sb.append(statusCodeString);
        sb.append(" ");
        sb.append(statusMessage);
        zza2.mo31546e(sb.toString(), new Object[0]);
        zzvm zzvm = (zzvm) this.zza.zzd.get(this.zzb);
        if (zzvm != null) {
            for (zztl zzh : zzvm.zzb) {
                zzh.zzh(status);
            }
            this.zza.zzj(this.zzb);
        }
    }
}
