package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
final class zzku implements zzlo {
    private static final zzla zza = new zzks();
    private final zzla zzb;

    public zzku() {
        zzla zzla;
        zzla[] zzlaArr = new zzla[2];
        zzlaArr[0] = zzjs.zza();
        try {
            zzla = (zzla) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception e) {
            zzla = zza;
        }
        zzlaArr[1] = zzla;
        zzkt zzkt = new zzkt(zzlaArr);
        zzkf.zzf(zzkt, "messageInfoFactory");
        this.zzb = zzkt;
    }

    private static boolean zzb(zzkz zzkz) {
        return zzkz.zzc() == 1;
    }

    public final <T> zzln<T> zza(Class<T> cls) {
        zzlp.zzG(cls);
        zzkz zzb2 = this.zzb.zzb(cls);
        if (zzb2.zzb()) {
            if (zzjx.class.isAssignableFrom(cls)) {
                return zzlg.zzc(zzlp.zzB(), zzjm.zzb(), zzb2.zza());
            }
            return zzlg.zzc(zzlp.zzz(), zzjm.zza(), zzb2.zza());
        } else if (zzjx.class.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzlf.zzk(cls, zzb2, zzli.zzb(), zzkq.zzd(), zzlp.zzB(), zzjm.zzb(), zzky.zzb());
            }
            return zzlf.zzk(cls, zzb2, zzli.zzb(), zzkq.zzd(), zzlp.zzB(), (zzjk<?>) null, zzky.zzb());
        } else if (zzb(zzb2)) {
            return zzlf.zzk(cls, zzb2, zzli.zza(), zzkq.zzc(), zzlp.zzz(), zzjm.zza(), zzky.zza());
        } else {
            return zzlf.zzk(cls, zzb2, zzli.zza(), zzkq.zzc(), zzlp.zzA(), (zzjk<?>) null, zzky.zza());
        }
    }
}
