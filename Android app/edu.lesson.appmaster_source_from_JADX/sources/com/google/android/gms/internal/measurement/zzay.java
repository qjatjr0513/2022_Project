package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzay extends zzaw {
    public zzay() {
        this.zza.add(zzbl.EQUALS);
        this.zza.add(zzbl.GREATER_THAN);
        this.zza.add(zzbl.GREATER_THAN_EQUALS);
        this.zza.add(zzbl.IDENTITY_EQUALS);
        this.zza.add(zzbl.IDENTITY_NOT_EQUALS);
        this.zza.add(zzbl.LESS_THAN);
        this.zza.add(zzbl.LESS_THAN_EQUALS);
        this.zza.add(zzbl.NOT_EQUALS);
    }

    private static boolean zzc(zzap zzap, zzap zzap2) {
        if (zzap.getClass().equals(zzap2.getClass())) {
            if ((zzap instanceof zzau) || (zzap instanceof zzan)) {
                return true;
            }
            if (zzap instanceof zzah) {
                return !Double.isNaN(zzap.zzh().doubleValue()) && !Double.isNaN(zzap2.zzh().doubleValue()) && zzap.zzh().doubleValue() == zzap2.zzh().doubleValue();
            }
            if (zzap instanceof zzat) {
                return zzap.zzi().equals(zzap2.zzi());
            }
            if (zzap instanceof zzaf) {
                return zzap.zzg().equals(zzap2.zzg());
            }
            return zzap == zzap2;
        } else if (((zzap instanceof zzau) || (zzap instanceof zzan)) && ((zzap2 instanceof zzau) || (zzap2 instanceof zzan))) {
            return true;
        } else {
            boolean z = zzap instanceof zzah;
            if (z && (zzap2 instanceof zzat)) {
                return zzc(zzap, new zzah(zzap2.zzh()));
            }
            boolean z2 = zzap instanceof zzat;
            if (z2 && (zzap2 instanceof zzah)) {
                return zzc(new zzah(zzap.zzh()), zzap2);
            }
            if (zzap instanceof zzaf) {
                return zzc(new zzah(zzap.zzh()), zzap2);
            }
            if (zzap2 instanceof zzaf) {
                return zzc(zzap, new zzah(zzap2.zzh()));
            }
            if ((z2 || z) && (zzap2 instanceof zzal)) {
                return zzc(zzap, new zzat(zzap2.zzi()));
            }
            if (!(zzap instanceof zzal) || (!(zzap2 instanceof zzat) && !(zzap2 instanceof zzah))) {
                return false;
            }
            return zzc(new zzat(zzap.zzi()), zzap2);
        }
    }

    private static boolean zzd(zzap zzap, zzap zzap2) {
        if (zzap instanceof zzal) {
            zzap = new zzat(zzap.zzi());
        }
        if (zzap2 instanceof zzal) {
            zzap2 = new zzat(zzap2.zzi());
        }
        if ((zzap instanceof zzat) && (zzap2 instanceof zzat)) {
            return zzap.zzi().compareTo(zzap2.zzi()) < 0;
        }
        double doubleValue = zzap.zzh().doubleValue();
        double doubleValue2 = zzap2.zzh().doubleValue();
        if (Double.isNaN(doubleValue) || Double.isNaN(doubleValue2)) {
            return false;
        }
        int i = (doubleValue > 0.0d ? 1 : (doubleValue == 0.0d ? 0 : -1));
        return !(i == 0 && doubleValue2 == 0.0d) && !(i == 0 && doubleValue2 == 0.0d) && Double.compare(doubleValue, doubleValue2) < 0;
    }

    private static boolean zze(zzap zzap, zzap zzap2) {
        if (zzap instanceof zzal) {
            zzap = new zzat(zzap.zzi());
        }
        if (zzap2 instanceof zzal) {
            zzap2 = new zzat(zzap2.zzi());
        }
        if (((!(zzap instanceof zzat) || !(zzap2 instanceof zzat)) && (Double.isNaN(zzap.zzh().doubleValue()) || Double.isNaN(zzap2.zzh().doubleValue()))) || zzd(zzap2, zzap)) {
            return false;
        }
        return true;
    }

    public final zzap zza(String str, zzg zzg, List<zzap> list) {
        boolean z;
        zzh.zzh(zzh.zze(str).name(), 2, list);
        zzap zzb = zzg.zzb(list.get(0));
        zzap zzb2 = zzg.zzb(list.get(1));
        switch (zzh.zze(str).ordinal()) {
            case 23:
                z = zzc(zzb, zzb2);
                break;
            case 37:
                z = zzd(zzb2, zzb);
                break;
            case 38:
                z = zze(zzb2, zzb);
                break;
            case 39:
                z = zzh.zzl(zzb, zzb2);
                break;
            case 40:
                z = !zzh.zzl(zzb, zzb2);
                break;
            case 42:
                z = zzd(zzb, zzb2);
                break;
            case 43:
                z = zze(zzb, zzb2);
                break;
            case 48:
                z = !zzc(zzb, zzb2);
                break;
            default:
                return super.zzb(str);
        }
        return z ? zzap.zzk : zzap.zzl;
    }
}
