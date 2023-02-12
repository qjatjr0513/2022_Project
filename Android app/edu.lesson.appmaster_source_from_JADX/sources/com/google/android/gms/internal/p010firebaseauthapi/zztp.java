package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztp */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zztp extends zztz {
    private static final Logger zza = new Logger("FirebaseAuth", "FirebaseAuthFallback:");
    private final zzpt zzb;
    private final zzvn zzc;

    public zztp(Context context, String str) {
        Preconditions.checkNotNull(context);
        Context context2 = context;
        this.zzb = new zzpt(new zzul(context2, Preconditions.checkNotEmpty(str), zzuk.zza(), (zzve) null, (zzub) null, (zzuc) null));
        this.zzc = new zzvn(context);
    }

    private static boolean zzH(long j, boolean z) {
        if (j > 0 && z) {
            return true;
        }
        zza.mo31553w("App hash will not be appended to the request.", new Object[0]);
        return false;
    }

    public final void zzA(zznk zznk, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zznk);
        Preconditions.checkNotNull(zztx);
        String zzd = zznk.zzd();
        zztl zztl = new zztl(zztx, zza);
        if (this.zzc.zzl(zzd)) {
            if (zznk.zzg()) {
                this.zzc.zzj(zzd);
            } else {
                this.zzc.zzi(zztl, zzd);
                return;
            }
        }
        long zza2 = zznk.zza();
        boolean zzh = zznk.zzh();
        zzxk zzb2 = zzxk.zzb(zznk.zzb(), zznk.zzd(), zznk.zzc(), zznk.zze(), zznk.zzf());
        if (zzH(zza2, zzh)) {
            zzb2.zzd(new zzvs(this.zzc.zzc()));
        }
        this.zzc.zzk(zzd, zztl, zza2, zzh);
        this.zzb.zzF(zzb2, new zzvk(this.zzc, zztl, zzd));
    }

    public final void zzB(zznm zznm, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zznm);
        Preconditions.checkNotNull(zztx);
        String phoneNumber = zznm.zzb().getPhoneNumber();
        zztl zztl = new zztl(zztx, zza);
        if (this.zzc.zzl(phoneNumber)) {
            if (zznm.zzg()) {
                this.zzc.zzj(phoneNumber);
            } else {
                this.zzc.zzi(zztl, phoneNumber);
                return;
            }
        }
        long zza2 = zznm.zza();
        boolean zzh = zznm.zzh();
        zzxm zzb2 = zzxm.zzb(zznm.zzd(), zznm.zzb().getUid(), zznm.zzb().getPhoneNumber(), zznm.zzc(), zznm.zze(), zznm.zzf());
        if (zzH(zza2, zzh)) {
            zzb2.zzd(new zzvs(this.zzc.zzc()));
        }
        this.zzc.zzk(phoneNumber, zztl, zza2, zzh);
        this.zzb.zzG(zzb2, new zzvk(this.zzc, zztl, phoneNumber));
    }

    public final void zzC(zzno zzno, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzno);
        Preconditions.checkNotNull(zztx);
        this.zzb.zzH(zzno.zza(), zzno.zzb(), new zztl(zztx, zza));
    }

    public final void zzD(zznq zznq, zztx zztx) {
        Preconditions.checkNotNull(zznq);
        Preconditions.checkNotEmpty(zznq.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzI(zznq.zza(), new zztl(zztx, zza));
    }

    public final void zzE(zzns zzns, zztx zztx) {
        Preconditions.checkNotNull(zzns);
        Preconditions.checkNotEmpty(zzns.zzb());
        Preconditions.checkNotEmpty(zzns.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzJ(zzns.zzb(), zzns.zza(), new zztl(zztx, zza));
    }

    public final void zzF(zznu zznu, zztx zztx) {
        Preconditions.checkNotNull(zznu);
        Preconditions.checkNotEmpty(zznu.zzb());
        Preconditions.checkNotNull(zznu.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzK(zznu.zzb(), zznu.zza(), new zztl(zztx, zza));
    }

    public final void zzG(zznw zznw, zztx zztx) {
        Preconditions.checkNotNull(zznw);
        this.zzb.zzL(zzwn.zzc(zznw.zza(), zznw.zzb(), zznw.zzc()), new zztl(zztx, zza));
    }

    public final void zzb(zzlm zzlm, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzlm);
        Preconditions.checkNotEmpty(zzlm.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzg(zzlm.zza(), zzlm.zzb(), new zztl(zztx, zza));
    }

    public final void zzc(zzlo zzlo, zztx zztx) {
        Preconditions.checkNotNull(zzlo);
        Preconditions.checkNotEmpty(zzlo.zza());
        Preconditions.checkNotEmpty(zzlo.zzb());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzh(zzlo.zza(), zzlo.zzb(), new zztl(zztx, zza));
    }

    public final void zzd(zzlq zzlq, zztx zztx) {
        Preconditions.checkNotNull(zzlq);
        Preconditions.checkNotEmpty(zzlq.zza());
        Preconditions.checkNotEmpty(zzlq.zzb());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzi(zzlq.zza(), zzlq.zzb(), new zztl(zztx, zza));
    }

    public final void zze(zzls zzls, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzls);
        Preconditions.checkNotEmpty(zzls.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzj(zzls.zza(), zzls.zzb(), new zztl(zztx, zza));
    }

    public final void zzf(zzlu zzlu, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzlu);
        Preconditions.checkNotEmpty(zzlu.zza());
        Preconditions.checkNotEmpty(zzlu.zzb());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzk(zzlu.zza(), zzlu.zzb(), zzlu.zzc(), new zztl(zztx, zza));
    }

    public final void zzg(zzlw zzlw, zztx zztx) {
        Preconditions.checkNotNull(zzlw);
        Preconditions.checkNotEmpty(zzlw.zza());
        Preconditions.checkNotEmpty(zzlw.zzb());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzl(zzlw.zza(), zzlw.zzb(), zzlw.zzc(), new zztl(zztx, zza));
    }

    public final void zzh(zzly zzly, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzly);
        Preconditions.checkNotEmpty(zzly.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzm(zzly.zza(), new zztl(zztx, zza));
    }

    public final void zzi(zzma zzma, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzma);
        Preconditions.checkNotNull(zztx);
        this.zzb.zzn((Context) null, zzwa.zzb(zzma.zzb(), zzma.zza().zzg(), zzma.zza().getSmsCode(), zzma.zzc()), zzma.zzb(), new zztl(zztx, zza));
    }

    public final void zzj(zzmc zzmc, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzmc);
        Preconditions.checkNotNull(zztx);
        this.zzb.zzo((Context) null, zzwc.zzb(zzmc.zzb(), zzmc.zza().zzg(), zzmc.zza().getSmsCode()), new zztl(zztx, zza));
    }

    public final void zzk(zzme zzme, zztx zztx) {
        Preconditions.checkNotNull(zzme);
        Preconditions.checkNotNull(zztx);
        Preconditions.checkNotEmpty(zzme.zza());
        this.zzb.zzp(zzme.zza(), new zztl(zztx, zza));
    }

    public final void zzl(zzmg zzmg, zztx zztx) {
        Preconditions.checkNotNull(zzmg);
        Preconditions.checkNotEmpty(zzmg.zza());
        this.zzb.zzq(zzmg.zza(), zzmg.zzb(), new zztl(zztx, zza));
    }

    public final void zzm(zzmi zzmi, zztx zztx) {
        Preconditions.checkNotNull(zzmi);
        Preconditions.checkNotEmpty(zzmi.zzb());
        Preconditions.checkNotEmpty(zzmi.zzc());
        Preconditions.checkNotEmpty(zzmi.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzr(zzmi.zzb(), zzmi.zzc(), zzmi.zza(), new zztl(zztx, zza));
    }

    public final void zzn(zzmk zzmk, zztx zztx) {
        Preconditions.checkNotNull(zzmk);
        Preconditions.checkNotEmpty(zzmk.zzb());
        Preconditions.checkNotNull(zzmk.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzs(zzmk.zzb(), zzmk.zza(), new zztl(zztx, zza));
    }

    public final void zzo(zzmm zzmm, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zztx);
        Preconditions.checkNotNull(zzmm);
        this.zzb.zzt((Context) null, Preconditions.checkNotEmpty(zzmm.zzb()), zzvd.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzmm.zza())), new zztl(zztx, zza));
    }

    public final void zzp(zzmo zzmo, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzmo);
        Preconditions.checkNotEmpty(zzmo.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzu(zzmo.zza(), new zztl(zztx, zza));
    }

    public final void zzq(zzmq zzmq, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzmq);
        Preconditions.checkNotEmpty(zzmq.zzb());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzv(zzmq.zzb(), zzmq.zza(), new zztl(zztx, zza));
    }

    public final void zzr(zzms zzms, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzms);
        Preconditions.checkNotEmpty(zzms.zzb());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzw(zzms.zzb(), zzms.zza(), zzms.zzc(), new zztl(zztx, zza));
    }

    public final void zzs(zzmu zzmu, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zztx);
        Preconditions.checkNotNull(zzmu);
        zzxd zzxd = (zzxd) Preconditions.checkNotNull(zzmu.zza());
        String zzd = zzxd.zzd();
        zztl zztl = new zztl(zztx, zza);
        if (this.zzc.zzl(zzd)) {
            if (zzxd.zzf()) {
                this.zzc.zzj(zzd);
            } else {
                this.zzc.zzi(zztl, zzd);
                return;
            }
        }
        long zzb2 = zzxd.zzb();
        boolean zzg = zzxd.zzg();
        if (zzH(zzb2, zzg)) {
            zzxd.zze(new zzvs(this.zzc.zzc()));
        }
        this.zzc.zzk(zzd, zztl, zzb2, zzg);
        this.zzb.zzx(zzxd, new zzvk(this.zzc, zztl, zzd));
    }

    public final void zzt(zzmw zzmw, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zzmw);
        Preconditions.checkNotNull(zztx);
        this.zzb.zzy(zzmw.zza(), new zztl(zztx, zza));
    }

    public final void zzu(zzmy zzmy, zztx zztx) {
        Preconditions.checkNotNull(zzmy);
        Preconditions.checkNotNull(zztx);
        this.zzb.zzz(zzmy.zza(), new zztl(zztx, zza));
    }

    public final void zzv(zzna zzna, zztx zztx) {
        Preconditions.checkNotNull(zzna);
        Preconditions.checkNotNull(zzna.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzA((Context) null, zzna.zza(), new zztl(zztx, zza));
    }

    public final void zzw(zznc zznc, zztx zztx) {
        Preconditions.checkNotNull(zznc);
        Preconditions.checkNotEmpty(zznc.zzb());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzB(new zzxt(zznc.zzb(), zznc.zza()), new zztl(zztx, zza));
    }

    public final void zzx(zzne zzne, zztx zztx) {
        Preconditions.checkNotNull(zzne);
        Preconditions.checkNotEmpty(zzne.zza());
        Preconditions.checkNotEmpty(zzne.zzb());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzC((Context) null, zzne.zza(), zzne.zzb(), zzne.zzc(), new zztl(zztx, zza));
    }

    public final void zzy(zzng zzng, zztx zztx) {
        Preconditions.checkNotNull(zzng);
        Preconditions.checkNotNull(zzng.zza());
        Preconditions.checkNotNull(zztx);
        this.zzb.zzD(zzng.zza(), new zztl(zztx, zza));
    }

    public final void zzz(zzni zzni, zztx zztx) throws RemoteException {
        Preconditions.checkNotNull(zztx);
        Preconditions.checkNotNull(zzni);
        this.zzb.zzE((Context) null, zzvd.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzni.zza())), new zztl(zztx, zza));
    }
}
