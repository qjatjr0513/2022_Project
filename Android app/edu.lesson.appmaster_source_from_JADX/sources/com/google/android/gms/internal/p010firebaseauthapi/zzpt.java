package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpt */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzpt {
    /* access modifiers changed from: private */
    public final zzva zza;

    public zzpt(zzva zzva) {
        this.zza = (zzva) Preconditions.checkNotNull(zzva);
    }

    private final void zzM(String str, zzuz<zzwq> zzuz) {
        Preconditions.checkNotNull(zzuz);
        Preconditions.checkNotEmpty(str);
        zzwq zzd = zzwq.zzd(str);
        if (zzd.zzj()) {
            zzuz.zzb(zzd);
            return;
        }
        this.zza.zzf(new zzwf(zzd.zzf()), new zzps(this, zzuz));
    }

    /* access modifiers changed from: private */
    public final void zzN(zzvy zzvy, zztl zztl) {
        Preconditions.checkNotNull(zzvy);
        Preconditions.checkNotNull(zztl);
        this.zza.zzc(zzvy, new zzof(this, zztl));
    }

    /* access modifiers changed from: private */
    public final void zzO(zzwq zzwq, String str, String str2, Boolean bool, zze zze, zztl zztl, zzuy zzuy) {
        Preconditions.checkNotNull(zzwq);
        Preconditions.checkNotNull(zzuy);
        Preconditions.checkNotNull(zztl);
        this.zza.zzg(new zzwg(zzwq.zze()), new zzoi(this, zzuy, str2, str, bool, zze, zztl, zzwq));
    }

    private final void zzP(zzwn zzwn, zztl zztl) {
        Preconditions.checkNotNull(zzwn);
        Preconditions.checkNotNull(zztl);
        this.zza.zzh(zzwn, new zzpl(this, zztl));
    }

    static /* synthetic */ void zzc(zzpt zzpt, zzxs zzxs, zztl zztl, zzuy zzuy) {
        Status status;
        if (zzxs.zzp()) {
            zze zzc = zzxs.zzc();
            String zzd = zzxs.zzd();
            String zzk = zzxs.zzk();
            if (zzxs.zzn()) {
                status = new Status(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL);
            } else {
                status = zzai.zza(zzxs.zze());
            }
            zztl.zze(new zzny(status, zzc, zzd, zzk));
            return;
        }
        zzpt.zzO(new zzwq(zzxs.zzj(), zzxs.zzf(), Long.valueOf(zzxs.zzb()), "Bearer"), zzxs.zzi(), zzxs.zzh(), Boolean.valueOf(zzxs.zzo()), zzxs.zzc(), zztl, zzuy);
    }

    static /* synthetic */ void zzd(zzpt zzpt, zztl zztl, zzwq zzwq, zzxg zzxg, zzuy zzuy) {
        Preconditions.checkNotNull(zztl);
        Preconditions.checkNotNull(zzwq);
        Preconditions.checkNotNull(zzxg);
        Preconditions.checkNotNull(zzuy);
        zzpt.zza.zzg(new zzwg(zzwq.zze()), new zzog(zzpt, zzuy, zztl, zzwq, zzxg));
    }

    static /* synthetic */ void zzf(zzpt zzpt, zztl zztl, zzwq zzwq, zzwj zzwj, zzxg zzxg, zzuy zzuy) {
        Preconditions.checkNotNull(zztl);
        Preconditions.checkNotNull(zzwq);
        Preconditions.checkNotNull(zzwj);
        Preconditions.checkNotNull(zzxg);
        Preconditions.checkNotNull(zzuy);
        zzpt.zza.zzl(zzxg, new zzoh(zzpt, zzxg, zzwj, zztl, zzwq, zzuy));
    }

    public final void zzA(Context context, zzxq zzxq, zztl zztl) {
        Preconditions.checkNotNull(zzxq);
        Preconditions.checkNotNull(zztl);
        zzxq.zzd(true);
        this.zza.zzq((Context) null, zzxq, new zzpm(this, zztl));
    }

    public final void zzB(zzxt zzxt, zztl zztl) {
        Preconditions.checkNotNull(zzxt);
        Preconditions.checkNotNull(zztl);
        this.zza.zzr(zzxt, new zzpb(this, zztl));
    }

    public final void zzC(Context context, String str, String str2, String str3, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztl);
        this.zza.zzs((Context) null, new zzxw(str, str2, str3), new zzod(this, zztl));
    }

    public final void zzD(EmailAuthCredential emailAuthCredential, zztl zztl) {
        Preconditions.checkNotNull(emailAuthCredential);
        Preconditions.checkNotNull(zztl);
        if (emailAuthCredential.zzh()) {
            zzM(emailAuthCredential.zzc(), new zzoe(this, emailAuthCredential, zztl));
        } else {
            zzN(new zzvy(emailAuthCredential, (String) null), zztl);
        }
    }

    public final void zzE(Context context, zzxy zzxy, zztl zztl) {
        Preconditions.checkNotNull(zzxy);
        Preconditions.checkNotNull(zztl);
        this.zza.zzt((Context) null, zzxy, new zzop(this, zztl));
    }

    public final void zzF(zzxk zzxk, zztl zztl) {
        Preconditions.checkNotNull(zzxk);
        Preconditions.checkNotNull(zztl);
        this.zza.zzo(zzxk, new zzpa(this, zztl));
    }

    public final void zzG(zzxm zzxm, zztl zztl) {
        Preconditions.checkNotNull(zzxm);
        Preconditions.checkNotNull(zztl);
        this.zza.zzp(zzxm, new zzpf(this, zztl));
    }

    public final void zzH(String str, String str2, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztl);
        zzM(str, new zzoz(this, str2, zztl));
    }

    public final void zzI(String str, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztl);
        zzM(str, new zzov(this, zztl));
    }

    public final void zzJ(String str, String str2, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztl);
        zzM(str2, new zzox(this, str, zztl));
    }

    public final void zzK(String str, UserProfileChangeRequest userProfileChangeRequest, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zztl);
        zzM(str, new zzpo(this, userProfileChangeRequest, zztl));
    }

    public final void zzL(zzwn zzwn, zztl zztl) {
        zzP(zzwn, zztl);
    }

    public final void zzg(String str, String str2, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztl);
        zzxg zzxg = new zzxg();
        zzxg.zzf(str);
        zzxg.zzi(str2);
        this.zza.zzl(zzxg, new zzpr(this, zztl));
    }

    public final void zzh(String str, String str2, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztl);
        zzM(str, new zzpp(this, str2, zztl));
    }

    public final void zzi(String str, String str2, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztl);
        zzM(str, new zzpq(this, str2, zztl));
    }

    public final void zzj(String str, String str2, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztl);
        this.zza.zzj(new zzxa(str, (String) null, str2), new zzol(this, zztl));
    }

    public final void zzk(String str, String str2, String str3, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztl);
        this.zza.zzj(new zzxa(str, str2, str3), new zzon(this, zztl));
    }

    public final void zzl(String str, String str2, String str3, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zztl);
        this.zza.zzn(new zzxi(str, str2, (String) null, str3), new zzoc(this, zztl));
    }

    public final void zzm(String str, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztl);
        zzM(str, new zzpj(this, zztl));
    }

    public final void zzn(Context context, zzwa zzwa, String str, zztl zztl) {
        Preconditions.checkNotNull(zzwa);
        Preconditions.checkNotNull(zztl);
        zzM(str, new zzpd(this, zzwa, (Context) null, zztl));
    }

    public final void zzo(Context context, zzwc zzwc, zztl zztl) {
        Preconditions.checkNotNull(zzwc);
        Preconditions.checkNotNull(zztl);
        this.zza.zze((Context) null, zzwc, new zzpe(this, zztl));
    }

    public final void zzp(String str, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztl);
        this.zza.zzf(new zzwf(str), new zzom(this, zztl));
    }

    public final void zzq(String str, String str2, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztl);
        this.zza.zza(new zzvu(str, str2), new zzoj(this, zztl));
    }

    public final void zzr(String str, String str2, String str3, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zztl);
        zzM(str3, new zzoq(this, str, str2, zztl));
    }

    public final void zzs(String str, zzxq zzxq, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxq);
        Preconditions.checkNotNull(zztl);
        zzM(str, new zzou(this, zzxq, zztl));
    }

    public final void zzt(Context context, String str, zzxy zzxy, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxy);
        Preconditions.checkNotNull(zztl);
        zzM(str, new zzos(this, zzxy, (Context) null, zztl));
    }

    public final void zzu(String str, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztl);
        zzM(str, new zzph(this, zztl));
    }

    public final void zzv(String str, ActionCodeSettings actionCodeSettings, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztl);
        zzwn zzwn = new zzwn(4);
        zzwn.zzg(str);
        if (actionCodeSettings != null) {
            zzwn.zzd(actionCodeSettings);
        }
        zzP(zzwn, zztl);
    }

    public final void zzw(String str, ActionCodeSettings actionCodeSettings, String str2, zztl zztl) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zztl);
        zzwn zzwn = new zzwn(actionCodeSettings.zza());
        zzwn.zze(str);
        zzwn.zzd(actionCodeSettings);
        zzwn.zzf(str2);
        this.zza.zzh(zzwn, new zzok(this, zztl));
    }

    public final void zzx(zzxd zzxd, zztl zztl) {
        Preconditions.checkNotEmpty(zzxd.zzd());
        Preconditions.checkNotNull(zztl);
        this.zza.zzk(zzxd, new zzoo(this, zztl));
    }

    public final void zzy(String str, zztl zztl) {
        Preconditions.checkNotNull(zztl);
        this.zza.zzm(str, new zzpk(this, zztl));
    }

    public final void zzz(String str, zztl zztl) {
        Preconditions.checkNotNull(zztl);
        this.zza.zzn(new zzxi(str), new zzpn(this, zztl));
    }
}
