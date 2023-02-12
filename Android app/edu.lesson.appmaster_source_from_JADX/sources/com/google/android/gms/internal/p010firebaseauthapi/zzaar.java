package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaar */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzaar implements zzabm {
    private static final zzaax zza = new zzaap();
    private final zzaax zzb;

    public zzaar() {
        zzaax zzaax;
        zzaax[] zzaaxArr = new zzaax[2];
        zzaaxArr[0] = zzzr.zza();
        try {
            zzaax = (zzaax) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception e) {
            zzaax = zza;
        }
        zzaaxArr[1] = zzaax;
        zzaaq zzaaq = new zzaaq(zzaaxArr);
        zzaac.zzf(zzaaq, "messageInfoFactory");
        this.zzb = zzaaq;
    }

    private static boolean zzb(zzaaw zzaaw) {
        return zzaaw.zzc() == 1;
    }

    public final <T> zzabl<T> zza(Class<T> cls) {
        zzabn.zzG(cls);
        zzaaw zzb2 = this.zzb.zzb(cls);
        if (zzb2.zzb()) {
            if (zzzw.class.isAssignableFrom(cls)) {
                return zzabd.zzc(zzabn.zzB(), zzzm.zzb(), zzb2.zza());
            }
            return zzabd.zzc(zzabn.zzz(), zzzm.zza(), zzb2.zza());
        } else if (zzzw.class.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzabc.zzl(cls, zzb2, zzabf.zzb(), zzaan.zze(), zzabn.zzB(), zzzm.zzb(), zzaav.zzb());
            }
            return zzabc.zzl(cls, zzb2, zzabf.zzb(), zzaan.zze(), zzabn.zzB(), (zzzk<?>) null, zzaav.zzb());
        } else if (zzb(zzb2)) {
            return zzabc.zzl(cls, zzb2, zzabf.zza(), zzaan.zzd(), zzabn.zzz(), zzzm.zza(), zzaav.zza());
        } else {
            return zzabc.zzl(cls, zzb2, zzabf.zza(), zzaan.zzd(), zzabn.zzA(), (zzzk<?>) null, zzaav.zza());
        }
    }
}
