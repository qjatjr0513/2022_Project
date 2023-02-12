package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.internal.zzao;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuu */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzuu extends zztw {
    final /* synthetic */ zzux zza;

    zzuu(zzux zzux) {
        this.zza = zzux;
    }

    private final void zzb(zzuv zzuv) {
        this.zza.zzi.execute(new zzut(this, zzuv));
    }

    private final void zzc(Status status, AuthCredential authCredential, String str, String str2) {
        zzux.zzk(this.zza, status);
        zzux zzux = this.zza;
        zzux.zzp = authCredential;
        zzux.zzq = str;
        zzux.zzr = str2;
        zzao zzao = zzux.zzg;
        if (zzao != null) {
            zzao.zzb(status);
        }
        this.zza.zzl(status);
    }

    public final void zzh(zzny zzny) {
        zzc(zzny.zza(), zzny.zzb(), zzny.zzc(), zzny.zzd());
    }

    public final void zzi(zzoa zzoa) {
        zzux zzux = this.zza;
        zzux.zzs = zzoa;
        zzux.zzl(zzai.zza("REQUIRES_SECOND_FACTOR_AUTH"));
    }

    public final void zzk(Status status) throws RemoteException {
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null) {
            if (statusMessage.contains("MISSING_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17081);
            } else if (statusMessage.contains("MISSING_MFA_ENROLLMENT_ID")) {
                status = new Status(17082);
            } else if (statusMessage.contains("INVALID_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17083);
            } else if (statusMessage.contains("MFA_ENROLLMENT_NOT_FOUND")) {
                status = new Status(17084);
            } else if (statusMessage.contains("ADMIN_ONLY_OPERATION")) {
                status = new Status(17085);
            } else if (statusMessage.contains("UNVERIFIED_EMAIL")) {
                status = new Status(17086);
            } else if (statusMessage.contains("SECOND_FACTOR_EXISTS")) {
                status = new Status(17087);
            } else if (statusMessage.contains("SECOND_FACTOR_LIMIT_EXCEEDED")) {
                status = new Status(17088);
            } else if (statusMessage.contains("UNSUPPORTED_FIRST_FACTOR")) {
                status = new Status(17089);
            } else if (statusMessage.contains("EMAIL_CHANGE_NEEDS_VERIFICATION")) {
                status = new Status(17090);
            }
        }
        zzux zzux = this.zza;
        if (zzux.zzb == 8) {
            boolean unused = zzux.zza = true;
            zzb(new zzus(this, status));
            return;
        }
        zzux.zzk(zzux, status);
        this.zza.zzl(status);
    }

    public final void zze(String str) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 8;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        this.zza.zzo = str;
        zzb(new zzup(this, str));
    }

    public final void zzf(zzvv zzvv) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 3;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzux zzux = this.zza;
        zzux.zzl = zzvv;
        zzux.zzj(zzux);
    }

    public final void zzg() throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 5;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzux.zzj(this.zza);
    }

    public final void zzj(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 2;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzc(status, phoneAuthCredential, (String) null, (String) null);
    }

    public final void zzl(zzwq zzwq, zzwj zzwj) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 2;
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unexpected response type: ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzux zzux = this.zza;
        zzux.zzj = zzwq;
        zzux.zzk = zzwj;
        zzux.zzj(zzux);
    }

    public final void zzm(zzxb zzxb) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 4;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzux zzux = this.zza;
        zzux.zzm = zzxb;
        zzux.zzj(zzux);
    }

    public final void zzn() throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 6;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzux.zzj(this.zza);
    }

    public final void zzo(String str) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 7;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzux zzux = this.zza;
        zzux.zzn = str;
        zzux.zzj(zzux);
    }

    public final void zzp() throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 9;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzux.zzj(this.zza);
    }

    public final void zzq(zzwq zzwq) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unexpected response type: ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzux zzux = this.zza;
        zzux.zzj = zzwq;
        zzux.zzj(zzux);
    }

    public final void zzd(String str) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 8;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzux zzux = this.zza;
        zzux.zzo = str;
        boolean unused = zzux.zza = true;
        zzb(new zzur(this, str));
    }

    public final void zzr(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = i == 8;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        boolean unused = this.zza.zza = true;
        zzb(new zzuq(this, phoneAuthCredential));
    }
}
