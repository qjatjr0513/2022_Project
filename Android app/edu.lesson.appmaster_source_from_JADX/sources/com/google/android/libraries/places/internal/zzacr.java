package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzacr implements zzadl {
    private static final zzacx zza = new zzacp();
    private final zzacx zzb;

    public zzacr() {
        zzacx zzacx;
        zzacx[] zzacxArr = new zzacx[2];
        zzacxArr[0] = zzabo.zza();
        try {
            zzacx = (zzacx) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception e) {
            zzacx = zza;
        }
        zzacxArr[1] = zzacx;
        zzacq zzacq = new zzacq(zzacxArr);
        zzaca.zzf(zzacq, "messageInfoFactory");
        this.zzb = zzacq;
    }

    private static boolean zzb(zzacw zzacw) {
        return zzacw.zzc() == 1;
    }

    public final <T> zzadk<T> zza(Class<T> cls) {
        zzadm.zzE(cls);
        zzacw zzb2 = this.zzb.zzb(cls);
        if (zzb2.zzb()) {
            if (zzabs.class.isAssignableFrom(cls)) {
                return zzadd.zzg(zzadm.zzB(), zzabk.zzb(), zzb2.zza());
            }
            return zzadd.zzg(zzadm.zzz(), zzabk.zza(), zzb2.zza());
        } else if (zzabs.class.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzadc.zzg(cls, zzb2, zzadf.zzb(), zzacn.zzd(), zzadm.zzB(), zzabk.zzb(), zzacv.zzb());
            }
            return zzadc.zzg(cls, zzb2, zzadf.zzb(), zzacn.zzd(), zzadm.zzB(), (zzabi<?>) null, zzacv.zzb());
        } else if (zzb(zzb2)) {
            return zzadc.zzg(cls, zzb2, zzadf.zza(), zzacn.zzc(), zzadm.zzz(), zzabk.zza(), zzacv.zza());
        } else {
            return zzadc.zzg(cls, zzb2, zzadf.zza(), zzacn.zzc(), zzadm.zzA(), (zzabi<?>) null, zzacv.zza());
        }
    }
}
