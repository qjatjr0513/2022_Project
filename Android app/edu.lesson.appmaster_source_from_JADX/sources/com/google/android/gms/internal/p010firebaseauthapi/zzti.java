package com.google.android.gms.internal.p010firebaseauthapi;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zzan;
import com.google.firebase.auth.internal.zzba;
import com.google.firebase.auth.internal.zzbk;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzt;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.auth.internal.zzz;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzti */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzti extends zzpy<zzuf> {
    private final Context zza;
    private final zzuf zzb;
    private final Future<zzpu<zzuf>> zzc = zzd();

    zzti(Context context, zzuf zzuf) {
        this.zza = context;
        this.zzb = zzuf;
    }

    static zzx zzR(FirebaseApp firebaseApp, zzwj zzwj) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(zzwj);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new zzt(zzwj, FirebaseAuthProvider.PROVIDER_ID));
        List<zzww> zzr = zzwj.zzr();
        if (zzr != null && !zzr.isEmpty()) {
            for (int i = 0; i < zzr.size(); i++) {
                arrayList.add(new zzt(zzr.get(i)));
            }
        }
        zzx zzx = new zzx(firebaseApp, arrayList);
        zzx.zzr(new zzz(zzwj.zzb(), zzwj.zza()));
        zzx.zzq(zzwj.zzt());
        zzx.zzp(zzwj.zzd());
        zzx.zzi(zzba.zzb(zzwj.zzq()));
        return zzx;
    }

    public final Task<Void> zzA(String str) {
        return zzb(new zzrx(str));
    }

    public final Task<AuthResult> zzB(FirebaseApp firebaseApp, zzg zzg, String str) {
        zzrz zzrz = new zzrz(str);
        zzrz.zzg(firebaseApp);
        zzrz.zze(zzg);
        return zzb(zzrz);
    }

    public final Task<AuthResult> zzC(FirebaseApp firebaseApp, AuthCredential authCredential, String str, zzg zzg) {
        zzsb zzsb = new zzsb(authCredential, str);
        zzsb.zzg(firebaseApp);
        zzsb.zze(zzg);
        return zzb(zzsb);
    }

    public final Task<AuthResult> zzD(FirebaseApp firebaseApp, String str, String str2, zzg zzg) {
        zzsd zzsd = new zzsd(str, str2);
        zzsd.zzg(firebaseApp);
        zzsd.zze(zzg);
        return zzb(zzsd);
    }

    public final Task<AuthResult> zzE(FirebaseApp firebaseApp, String str, String str2, String str3, zzg zzg) {
        zzsf zzsf = new zzsf(str, str2, str3);
        zzsf.zzg(firebaseApp);
        zzsf.zze(zzg);
        return zzb(zzsf);
    }

    public final Task<AuthResult> zzF(FirebaseApp firebaseApp, EmailAuthCredential emailAuthCredential, zzg zzg) {
        zzsh zzsh = new zzsh(emailAuthCredential);
        zzsh.zzg(firebaseApp);
        zzsh.zze(zzg);
        return zzb(zzsh);
    }

    public final Task<AuthResult> zzG(FirebaseApp firebaseApp, PhoneAuthCredential phoneAuthCredential, String str, zzg zzg) {
        zzvh.zzc();
        zzsj zzsj = new zzsj(phoneAuthCredential, str);
        zzsj.zzg(firebaseApp);
        zzsj.zze(zzg);
        return zzb(zzsj);
    }

    public final Task<Void> zzH(zzag zzag, String str, String str2, long j, boolean z, boolean z2, String str3, String str4, boolean z3, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzsl zzsl = new zzsl(zzag, str, str2, j, z, z2, str3, str4, z3);
        String str5 = str;
        zzsl.zzi(onVerificationStateChangedCallbacks, activity, executor, str);
        return zzb(zzsl);
    }

    public final Task<Void> zzI(zzag zzag, PhoneMultiFactorInfo phoneMultiFactorInfo, String str, long j, boolean z, boolean z2, String str2, String str3, boolean z3, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzsn zzsn = new zzsn(phoneMultiFactorInfo, zzag.zzd(), str, j, z, z2, str2, str3, z3);
        Activity activity2 = activity;
        zzsn.zzi(onVerificationStateChangedCallbacks, activity2, executor, phoneMultiFactorInfo.getUid());
        return zzb(zzsn);
    }

    public final Task<Void> zzJ(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzsp zzsp = new zzsp(firebaseUser.zzf(), str);
        zzsp.zzg(firebaseApp);
        zzsp.zzh(firebaseUser);
        zzsp.zze(zzbk);
        zzsp.zzf(zzbk);
        return zzb(zzsp);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> zzK(com.google.firebase.FirebaseApp r2, com.google.firebase.auth.FirebaseUser r3, java.lang.String r4, com.google.firebase.auth.internal.zzbk r5) {
        /*
            r1 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.util.List r0 = r3.zzg()
            if (r0 == 0) goto L_0x0018
            boolean r0 = r0.contains(r4)
            if (r0 == 0) goto L_0x001e
        L_0x0018:
            boolean r0 = r3.isAnonymous()
            if (r0 == 0) goto L_0x002e
        L_0x001e:
            com.google.android.gms.common.api.Status r2 = new com.google.android.gms.common.api.Status
            r3 = 17016(0x4278, float:2.3844E-41)
            r2.<init>((int) r3, (java.lang.String) r4)
            com.google.firebase.FirebaseException r2 = com.google.android.gms.internal.p010firebaseauthapi.zzto.zza(r2)
            com.google.android.gms.tasks.Task r2 = com.google.android.gms.tasks.Tasks.forException(r2)
            return r2
        L_0x002e:
            int r0 = r4.hashCode()
            switch(r0) {
                case 1216985755: goto L_0x0036;
                default: goto L_0x0035;
            }
        L_0x0035:
            goto L_0x0041
        L_0x0036:
            java.lang.String r0 = "password"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0035
            r0 = 0
            goto L_0x0042
        L_0x0041:
            r0 = -1
        L_0x0042:
            switch(r0) {
                case 0: goto L_0x005b;
                default: goto L_0x0045;
            }
        L_0x0045:
            com.google.android.gms.internal.firebase-auth-api.zzst r0 = new com.google.android.gms.internal.firebase-auth-api.zzst
            r0.<init>(r4)
            r0.zzg(r2)
            r0.zzh(r3)
            r0.zze(r5)
            r0.zzf(r5)
            com.google.android.gms.tasks.Task r2 = r1.zzb(r0)
            return r2
        L_0x005b:
            com.google.android.gms.internal.firebase-auth-api.zzsr r4 = new com.google.android.gms.internal.firebase-auth-api.zzsr
            r4.<init>()
            r4.zzg(r2)
            r4.zzh(r3)
            r4.zze(r5)
            r4.zzf(r5)
            com.google.android.gms.tasks.Task r2 = r1.zzb(r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzti.zzK(com.google.firebase.FirebaseApp, com.google.firebase.auth.FirebaseUser, java.lang.String, com.google.firebase.auth.internal.zzbk):com.google.android.gms.tasks.Task");
    }

    public final Task<Void> zzL(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzsv zzsv = new zzsv(str);
        zzsv.zzg(firebaseApp);
        zzsv.zzh(firebaseUser);
        zzsv.zze(zzbk);
        zzsv.zzf(zzbk);
        return zzb(zzsv);
    }

    public final Task<Void> zzM(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzsx zzsx = new zzsx(str);
        zzsx.zzg(firebaseApp);
        zzsx.zzh(firebaseUser);
        zzsx.zze(zzbk);
        zzsx.zzf(zzbk);
        return zzb(zzsx);
    }

    public final Task<Void> zzN(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, zzbk zzbk) {
        zzvh.zzc();
        zzsz zzsz = new zzsz(phoneAuthCredential);
        zzsz.zzg(firebaseApp);
        zzsz.zzh(firebaseUser);
        zzsz.zze(zzbk);
        zzsz.zzf(zzbk);
        return zzb(zzsz);
    }

    public final Task<Void> zzO(FirebaseApp firebaseApp, FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest, zzbk zzbk) {
        zztb zztb = new zztb(userProfileChangeRequest);
        zztb.zzg(firebaseApp);
        zztb.zzh(firebaseUser);
        zztb.zze(zzbk);
        zztb.zzf(zzbk);
        return zzb(zztb);
    }

    public final Task<Void> zzP(String str, String str2, ActionCodeSettings actionCodeSettings) {
        actionCodeSettings.zzg(7);
        return zzb(new zztd(str, str2, actionCodeSettings));
    }

    public final Task<String> zzQ(FirebaseApp firebaseApp, String str, String str2) {
        zztf zztf = new zztf(str, str2);
        zztf.zzg(firebaseApp);
        return zzb(zztf);
    }

    public final void zzS(FirebaseApp firebaseApp, zzxd zzxd, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        zzth zzth = new zzth(zzxd);
        zzth.zzg(firebaseApp);
        zzth.zzi(onVerificationStateChangedCallbacks, activity, executor, zzxd.zzd());
        zzb(zzth);
    }

    /* access modifiers changed from: package-private */
    public final Future<zzpu<zzuf>> zzd() {
        Future<zzpu<zzuf>> future = this.zzc;
        if (future != null) {
            return future;
        }
        return zzh.zza().zza(2).submit(new zztj(this.zzb, this.zza));
    }

    public final Task<Void> zze(FirebaseApp firebaseApp, String str, String str2) {
        zzqb zzqb = new zzqb(str, str2);
        zzqb.zzg(firebaseApp);
        return zzb(zzqb);
    }

    public final Task<ActionCodeResult> zzf(FirebaseApp firebaseApp, String str, String str2) {
        zzqd zzqd = new zzqd(str, str2);
        zzqd.zzg(firebaseApp);
        return zzb(zzqd);
    }

    public final Task<Void> zzg(FirebaseApp firebaseApp, String str, String str2, String str3) {
        zzqf zzqf = new zzqf(str, str2, str3);
        zzqf.zzg(firebaseApp);
        return zzb(zzqf);
    }

    public final Task<AuthResult> zzh(FirebaseApp firebaseApp, String str, String str2, String str3, zzg zzg) {
        zzqh zzqh = new zzqh(str, str2, str3);
        zzqh.zzg(firebaseApp);
        zzqh.zze(zzg);
        return zzb(zzqh);
    }

    public final Task<Void> zzi(FirebaseUser firebaseUser, zzan zzan) {
        zzqj zzqj = new zzqj();
        zzqj.zzh(firebaseUser);
        zzqj.zze(zzan);
        zzqj.zzf(zzan);
        return zzb(zzqj);
    }

    public final Task<SignInMethodQueryResult> zzj(FirebaseApp firebaseApp, String str, String str2) {
        zzql zzql = new zzql(str, str2);
        zzql.zzg(firebaseApp);
        return zza(zzql);
    }

    public final Task<Void> zzk(FirebaseApp firebaseApp, PhoneMultiFactorAssertion phoneMultiFactorAssertion, FirebaseUser firebaseUser, String str, zzg zzg) {
        zzvh.zzc();
        zzqn zzqn = new zzqn(phoneMultiFactorAssertion, firebaseUser.zzf(), str);
        zzqn.zzg(firebaseApp);
        zzqn.zze(zzg);
        return zzb(zzqn);
    }

    public final Task<AuthResult> zzl(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneMultiFactorAssertion phoneMultiFactorAssertion, String str, zzg zzg) {
        zzvh.zzc();
        zzqp zzqp = new zzqp(phoneMultiFactorAssertion, str);
        zzqp.zzg(firebaseApp);
        zzqp.zze(zzg);
        if (firebaseUser != null) {
            zzqp.zzh(firebaseUser);
        }
        return zzb(zzqp);
    }

    public final Task<GetTokenResult> zzm(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzqr zzqr = new zzqr(str);
        zzqr.zzg(firebaseApp);
        zzqr.zzh(firebaseUser);
        zzqr.zze(zzbk);
        zzqr.zzf(zzbk);
        return zza(zzqr);
    }

    public final Task<AuthResult> zzn(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, zzbk zzbk) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzbk);
        List<String> zzg = firebaseUser.zzg();
        if (zzg != null && zzg.contains(authCredential.getProvider())) {
            return Tasks.forException(zzto.zza(new Status(FirebaseError.ERROR_PROVIDER_ALREADY_LINKED)));
        }
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!emailAuthCredential.zzg()) {
                zzqt zzqt = new zzqt(emailAuthCredential);
                zzqt.zzg(firebaseApp);
                zzqt.zzh(firebaseUser);
                zzqt.zze(zzbk);
                zzqt.zzf(zzbk);
                return zzb(zzqt);
            }
            zzqz zzqz = new zzqz(emailAuthCredential);
            zzqz.zzg(firebaseApp);
            zzqz.zzh(firebaseUser);
            zzqz.zze(zzbk);
            zzqz.zzf(zzbk);
            return zzb(zzqz);
        } else if (authCredential instanceof PhoneAuthCredential) {
            zzvh.zzc();
            zzqx zzqx = new zzqx((PhoneAuthCredential) authCredential);
            zzqx.zzg(firebaseApp);
            zzqx.zzh(firebaseUser);
            zzqx.zze(zzbk);
            zzqx.zzf(zzbk);
            return zzb(zzqx);
        } else {
            Preconditions.checkNotNull(firebaseApp);
            Preconditions.checkNotNull(authCredential);
            Preconditions.checkNotNull(firebaseUser);
            Preconditions.checkNotNull(zzbk);
            zzqv zzqv = new zzqv(authCredential);
            zzqv.zzg(firebaseApp);
            zzqv.zzh(firebaseUser);
            zzqv.zze(zzbk);
            zzqv.zzf(zzbk);
            return zzb(zzqv);
        }
    }

    public final Task<Void> zzo(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzbk zzbk) {
        zzrb zzrb = new zzrb(authCredential, str);
        zzrb.zzg(firebaseApp);
        zzrb.zzh(firebaseUser);
        zzrb.zze(zzbk);
        zzrb.zzf(zzbk);
        return zzb(zzrb);
    }

    public final Task<AuthResult> zzp(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzbk zzbk) {
        zzrd zzrd = new zzrd(authCredential, str);
        zzrd.zzg(firebaseApp);
        zzrd.zzh(firebaseUser);
        zzrd.zze(zzbk);
        zzrd.zzf(zzbk);
        return zzb(zzrd);
    }

    public final Task<Void> zzq(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzbk zzbk) {
        zzrf zzrf = new zzrf(emailAuthCredential);
        zzrf.zzg(firebaseApp);
        zzrf.zzh(firebaseUser);
        zzrf.zze(zzbk);
        zzrf.zzf(zzbk);
        return zzb(zzrf);
    }

    public final Task<AuthResult> zzr(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzbk zzbk) {
        zzrh zzrh = new zzrh(emailAuthCredential);
        zzrh.zzg(firebaseApp);
        zzrh.zzh(firebaseUser);
        zzrh.zze(zzbk);
        zzrh.zzf(zzbk);
        return zzb(zzrh);
    }

    public final Task<Void> zzs(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, zzbk zzbk) {
        zzrj zzrj = new zzrj(str, str2, str3);
        zzrj.zzg(firebaseApp);
        zzrj.zzh(firebaseUser);
        zzrj.zze(zzbk);
        zzrj.zzf(zzbk);
        return zzb(zzrj);
    }

    public final Task<AuthResult> zzt(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, zzbk zzbk) {
        zzrl zzrl = new zzrl(str, str2, str3);
        zzrl.zzg(firebaseApp);
        zzrl.zzh(firebaseUser);
        zzrl.zze(zzbk);
        zzrl.zzf(zzbk);
        return zzb(zzrl);
    }

    public final Task<Void> zzu(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzbk zzbk) {
        zzvh.zzc();
        zzrn zzrn = new zzrn(phoneAuthCredential, str);
        zzrn.zzg(firebaseApp);
        zzrn.zzh(firebaseUser);
        zzrn.zze(zzbk);
        zzrn.zzf(zzbk);
        return zzb(zzrn);
    }

    public final Task<AuthResult> zzv(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzbk zzbk) {
        zzvh.zzc();
        zzrp zzrp = new zzrp(phoneAuthCredential, str);
        zzrp.zzg(firebaseApp);
        zzrp.zzh(firebaseUser);
        zzrp.zze(zzbk);
        zzrp.zzf(zzbk);
        return zzb(zzrp);
    }

    public final Task<Void> zzw(FirebaseApp firebaseApp, FirebaseUser firebaseUser, zzbk zzbk) {
        zzrr zzrr = new zzrr();
        zzrr.zzg(firebaseApp);
        zzrr.zzh(firebaseUser);
        zzrr.zze(zzbk);
        zzrr.zzf(zzbk);
        return zza(zzrr);
    }

    public final Task<Void> zzx(FirebaseApp firebaseApp, ActionCodeSettings actionCodeSettings, String str) {
        zzrt zzrt = new zzrt(str, actionCodeSettings);
        zzrt.zzg(firebaseApp);
        return zzb(zzrt);
    }

    public final Task<Void> zzy(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2) {
        actionCodeSettings.zzg(1);
        zzrv zzrv = new zzrv(str, actionCodeSettings, str2, "sendPasswordResetEmail");
        zzrv.zzg(firebaseApp);
        return zzb(zzrv);
    }

    public final Task<Void> zzz(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2) {
        actionCodeSettings.zzg(6);
        zzrv zzrv = new zzrv(str, actionCodeSettings, str2, "sendSignInLinkToEmail");
        zzrv.zzg(firebaseApp);
        return zzb(zzrv);
    }
}
