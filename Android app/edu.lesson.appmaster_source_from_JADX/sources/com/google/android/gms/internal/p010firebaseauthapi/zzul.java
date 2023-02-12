package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzul */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzul extends zzva implements zzvq {
    zzum zza;
    private zzub zzb;
    private zzuc zzc;
    private zzve zzd;
    private final zzuk zze;
    private final Context zzf;
    private final String zzg;

    zzul(Context context, String str, zzuk zzuk, zzve zzve, zzub zzub, zzuc zzuc) {
        this.zzf = ((Context) Preconditions.checkNotNull(context)).getApplicationContext();
        this.zzg = Preconditions.checkNotEmpty(str);
        this.zze = (zzuk) Preconditions.checkNotNull(zzuk);
        zzw((zzve) null, (zzub) null, (zzuc) null);
        zzvr.zze(str, this);
    }

    private final zzum zzv() {
        if (this.zza == null) {
            this.zza = new zzum(this.zzf, this.zze.zzb());
        }
        return this.zza;
    }

    private final void zzw(zzve zzve, zzub zzub, zzuc zzuc) {
        this.zzd = null;
        this.zzb = null;
        this.zzc = null;
        String zza2 = zzvo.zza("firebear.secureToken");
        if (TextUtils.isEmpty(zza2)) {
            zza2 = zzvr.zzd(this.zzg);
        } else {
            String valueOf = String.valueOf(zza2);
            Log.e("LocalClient", valueOf.length() != 0 ? "Found hermetic configuration for secureToken URL: ".concat(valueOf) : new String("Found hermetic configuration for secureToken URL: "));
        }
        if (this.zzd == null) {
            this.zzd = new zzve(zza2, zzv());
        }
        String zza3 = zzvo.zza("firebear.identityToolkit");
        if (TextUtils.isEmpty(zza3)) {
            zza3 = zzvr.zzb(this.zzg);
        } else {
            String valueOf2 = String.valueOf(zza3);
            Log.e("LocalClient", valueOf2.length() != 0 ? "Found hermetic configuration for identityToolkit URL: ".concat(valueOf2) : new String("Found hermetic configuration for identityToolkit URL: "));
        }
        if (this.zzb == null) {
            this.zzb = new zzub(zza3, zzv());
        }
        String zza4 = zzvo.zza("firebear.identityToolkitV2");
        if (TextUtils.isEmpty(zza4)) {
            zza4 = zzvr.zzc(this.zzg);
        } else {
            String valueOf3 = String.valueOf(zza4);
            Log.e("LocalClient", valueOf3.length() != 0 ? "Found hermetic configuration for identityToolkitV2 URL: ".concat(valueOf3) : new String("Found hermetic configuration for identityToolkitV2 URL: "));
        }
        if (this.zzc == null) {
            this.zzc = new zzuc(zza4, zzv());
        }
    }

    public final void zza(zzvu zzvu, zzuz<zzvv> zzuz) {
        Preconditions.checkNotNull(zzvu);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/createAuthUri", this.zzg), zzvu, zzuz, zzvv.class, zzub.zzb);
    }

    public final void zzb(zzvx zzvx, zzuz<Void> zzuz) {
        Preconditions.checkNotNull(zzvx);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/deleteAccount", this.zzg), zzvx, zzuz, Void.class, zzub.zzb);
    }

    public final void zzc(zzvy zzvy, zzuz<zzvz> zzuz) {
        Preconditions.checkNotNull(zzvy);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/emailLinkSignin", this.zzg), zzvy, zzuz, zzvz.class, zzub.zzb);
    }

    public final void zzd(Context context, zzwa zzwa, zzuz<zzwb> zzuz) {
        Preconditions.checkNotNull(zzwa);
        Preconditions.checkNotNull(zzuz);
        zzuc zzuc = this.zzc;
        zzvb.zza(zzuc.zza("/mfaEnrollment:finalize", this.zzg), zzwa, zzuz, zzwb.class, zzuc.zzb);
    }

    public final void zze(Context context, zzwc zzwc, zzuz<zzwd> zzuz) {
        Preconditions.checkNotNull(zzwc);
        Preconditions.checkNotNull(zzuz);
        zzuc zzuc = this.zzc;
        zzvb.zza(zzuc.zza("/mfaSignIn:finalize", this.zzg), zzwc, zzuz, zzwd.class, zzuc.zzb);
    }

    public final void zzf(zzwf zzwf, zzuz<zzwq> zzuz) {
        Preconditions.checkNotNull(zzwf);
        Preconditions.checkNotNull(zzuz);
        zzve zzve = this.zzd;
        zzvb.zza(zzve.zza("/token", this.zzg), zzwf, zzuz, zzwq.class, zzve.zzb);
    }

    public final void zzg(zzwg zzwg, zzuz<zzwh> zzuz) {
        Preconditions.checkNotNull(zzwg);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/getAccountInfo", this.zzg), zzwg, zzuz, zzwh.class, zzub.zzb);
    }

    public final void zzh(zzwn zzwn, zzuz<zzwo> zzuz) {
        Preconditions.checkNotNull(zzwn);
        Preconditions.checkNotNull(zzuz);
        if (zzwn.zzb() != null) {
            zzv().zzc(zzwn.zzb().zze());
        }
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/getOobConfirmationCode", this.zzg), zzwn, zzuz, zzwo.class, zzub.zzb);
    }

    public final void zzi() {
        zzw((zzve) null, (zzub) null, (zzuc) null);
    }

    public final void zzj(zzxa zzxa, zzuz<zzxb> zzuz) {
        Preconditions.checkNotNull(zzxa);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/resetPassword", this.zzg), zzxa, zzuz, zzxb.class, zzub.zzb);
    }

    public final void zzk(zzxd zzxd, zzuz<zzxf> zzuz) {
        Preconditions.checkNotNull(zzxd);
        Preconditions.checkNotNull(zzuz);
        if (!TextUtils.isEmpty(zzxd.zzc())) {
            zzv().zzc(zzxd.zzc());
        }
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/sendVerificationCode", this.zzg), zzxd, zzuz, zzxf.class, zzub.zzb);
    }

    public final void zzl(zzxg zzxg, zzuz<zzxh> zzuz) {
        Preconditions.checkNotNull(zzxg);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/setAccountInfo", this.zzg), zzxg, zzuz, zzxh.class, zzub.zzb);
    }

    public final void zzm(String str, zzuz<Void> zzuz) {
        Preconditions.checkNotNull(zzuz);
        zzv().zzb(str);
        ((zzpk) zzuz).zza.zzm();
    }

    public final void zzn(zzxi zzxi, zzuz<zzxj> zzuz) {
        Preconditions.checkNotNull(zzxi);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/signupNewUser", this.zzg), zzxi, zzuz, zzxj.class, zzub.zzb);
    }

    public final void zzo(zzxk zzxk, zzuz<zzxl> zzuz) {
        Preconditions.checkNotNull(zzxk);
        Preconditions.checkNotNull(zzuz);
        if (!TextUtils.isEmpty(zzxk.zzc())) {
            zzv().zzc(zzxk.zzc());
        }
        zzuc zzuc = this.zzc;
        zzvb.zza(zzuc.zza("/mfaEnrollment:start", this.zzg), zzxk, zzuz, zzxl.class, zzuc.zzb);
    }

    public final void zzp(zzxm zzxm, zzuz<zzxn> zzuz) {
        Preconditions.checkNotNull(zzxm);
        Preconditions.checkNotNull(zzuz);
        if (!TextUtils.isEmpty(zzxm.zzc())) {
            zzv().zzc(zzxm.zzc());
        }
        zzuc zzuc = this.zzc;
        zzvb.zza(zzuc.zza("/mfaSignIn:start", this.zzg), zzxm, zzuz, zzxn.class, zzuc.zzb);
    }

    public final void zzq(Context context, zzxq zzxq, zzuz<zzxs> zzuz) {
        Preconditions.checkNotNull(zzxq);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/verifyAssertion", this.zzg), zzxq, zzuz, zzxs.class, zzub.zzb);
    }

    public final void zzr(zzxt zzxt, zzuz<zzxu> zzuz) {
        Preconditions.checkNotNull(zzxt);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/verifyCustomToken", this.zzg), zzxt, zzuz, zzxu.class, zzub.zzb);
    }

    public final void zzs(Context context, zzxw zzxw, zzuz<zzxx> zzuz) {
        Preconditions.checkNotNull(zzxw);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/verifyPassword", this.zzg), zzxw, zzuz, zzxx.class, zzub.zzb);
    }

    public final void zzt(Context context, zzxy zzxy, zzuz<zzxz> zzuz) {
        Preconditions.checkNotNull(zzxy);
        Preconditions.checkNotNull(zzuz);
        zzub zzub = this.zzb;
        zzvb.zza(zzub.zza("/verifyPhoneNumber", this.zzg), zzxy, zzuz, zzxz.class, zzub.zzb);
    }

    public final void zzu(zzya zzya, zzuz<zzyb> zzuz) {
        Preconditions.checkNotNull(zzya);
        Preconditions.checkNotNull(zzuz);
        zzuc zzuc = this.zzc;
        zzvb.zza(zzuc.zza("/mfaEnrollment:withdraw", this.zzg), zzya, zzuz, zzyb.class, zzuc.zzb);
    }
}
