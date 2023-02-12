package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztl */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public class zztl {
    private final zztx zza;
    private final Logger zzb;

    public zztl(zztl zztl) {
        this(zztl.zza, zztl.zzb);
    }

    public final void zza(String str) {
        try {
            this.zza.zzd(str);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending auto retrieval timeout response.", e, new Object[0]);
        }
    }

    public void zzb(String str) {
        try {
            this.zza.zze(str);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending send verification code response.", e, new Object[0]);
        }
    }

    public final void zzc(zzvv zzvv) {
        try {
            this.zza.zzf(zzvv);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending create auth uri response.", e, new Object[0]);
        }
    }

    public final void zzd() {
        try {
            this.zza.zzg();
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending delete account response.", e, new Object[0]);
        }
    }

    public final void zze(zzny zzny) {
        try {
            this.zza.zzh(zzny);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending failure result with credential", e, new Object[0]);
        }
    }

    public final void zzf(zzoa zzoa) {
        try {
            this.zza.zzi(zzoa);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending failure result for mfa", e, new Object[0]);
        }
    }

    public final void zzg(Status status, PhoneAuthCredential phoneAuthCredential) {
        try {
            this.zza.zzj(status, phoneAuthCredential);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending failure result.", e, new Object[0]);
        }
    }

    public void zzh(Status status) {
        try {
            this.zza.zzk(status);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending failure result.", e, new Object[0]);
        }
    }

    public final void zzi(zzwq zzwq, zzwj zzwj) {
        try {
            this.zza.zzl(zzwq, zzwj);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending get token and account info user response", e, new Object[0]);
        }
    }

    public final void zzj(zzxb zzxb) {
        try {
            this.zza.zzm(zzxb);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending password reset response.", e, new Object[0]);
        }
    }

    public final void zzk() {
        try {
            this.zza.zzn();
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending email verification response.", e, new Object[0]);
        }
    }

    public final void zzl(String str) {
        try {
            this.zza.zzo(str);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending set account info response.", e, new Object[0]);
        }
    }

    public final void zzm() {
        try {
            this.zza.zzp();
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when setting FirebaseUI Version", e, new Object[0]);
        }
    }

    public final void zzn(zzwq zzwq) {
        try {
            this.zza.zzq(zzwq);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending token result.", e, new Object[0]);
        }
    }

    public final void zzo(PhoneAuthCredential phoneAuthCredential) {
        try {
            this.zza.zzr(phoneAuthCredential);
        } catch (RemoteException e) {
            this.zzb.mo31545e("RemoteException when sending verification completed response.", e, new Object[0]);
        }
    }

    public zztl(zztx zztx, Logger logger) {
        this.zza = (zztx) Preconditions.checkNotNull(zztx);
        this.zzb = (Logger) Preconditions.checkNotNull(logger);
    }
}
