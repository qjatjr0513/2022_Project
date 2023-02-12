package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzbh extends zzaw {
    protected zzbh() {
        this.zza.add(zzbl.FOR_IN);
        this.zza.add(zzbl.FOR_IN_CONST);
        this.zza.add(zzbl.FOR_IN_LET);
        this.zza.add(zzbl.FOR_LET);
        this.zza.add(zzbl.FOR_OF);
        this.zza.add(zzbl.FOR_OF_CONST);
        this.zza.add(zzbl.FOR_OF_LET);
        this.zza.add(zzbl.WHILE);
    }

    private static zzap zzc(zzbf zzbf, Iterator<zzap> it, zzap zzap) {
        if (it != null) {
            while (it.hasNext()) {
                zzap zzc = zzbf.zza(it.next()).zzc((zzae) zzap);
                if (zzc instanceof zzag) {
                    zzag zzag = (zzag) zzc;
                    if ("break".equals(zzag.zzc())) {
                        return zzap.zzf;
                    }
                    if ("return".equals(zzag.zzc())) {
                        return zzag;
                    }
                }
            }
        }
        return zzap.zzf;
    }

    private static zzap zzd(zzbf zzbf, zzap zzap, zzap zzap2) {
        return zzc(zzbf, zzap.zzl(), zzap2);
    }

    private static zzap zze(zzbf zzbf, zzap zzap, zzap zzap2) {
        if (zzap instanceof Iterable) {
            return zzc(zzbf, ((Iterable) zzap).iterator(), zzap2);
        }
        throw new IllegalArgumentException("Non-iterable type in for...of loop.");
    }

    public final zzap zza(String str, zzg zzg, List<zzap> list) {
        zzbl zzbl = zzbl.ADD;
        switch (zzh.zze(str).ordinal()) {
            case 26:
                zzh.zzh(zzbl.FOR_IN.name(), 3, list);
                if (list.get(0) instanceof zzat) {
                    return zzd(new zzbg(zzg, list.get(0).zzi()), zzg.zzb(list.get(1)), zzg.zzb(list.get(2)));
                }
                throw new IllegalArgumentException("Variable name in FOR_IN must be a string");
            case 27:
                zzh.zzh(zzbl.FOR_IN_CONST.name(), 3, list);
                if (list.get(0) instanceof zzat) {
                    return zzd(new zzbd(zzg, list.get(0).zzi()), zzg.zzb(list.get(1)), zzg.zzb(list.get(2)));
                }
                throw new IllegalArgumentException("Variable name in FOR_IN_CONST must be a string");
            case 28:
                zzh.zzh(zzbl.FOR_IN_LET.name(), 3, list);
                if (list.get(0) instanceof zzat) {
                    return zzd(new zzbe(zzg, list.get(0).zzi()), zzg.zzb(list.get(1)), zzg.zzb(list.get(2)));
                }
                throw new IllegalArgumentException("Variable name in FOR_IN_LET must be a string");
            case 29:
                zzh.zzh(zzbl.FOR_LET.name(), 4, list);
                zzap zzb = zzg.zzb(list.get(0));
                if (zzb instanceof zzae) {
                    zzae zzae = (zzae) zzb;
                    zzap zzap = list.get(1);
                    zzap zzap2 = list.get(2);
                    zzap zzb2 = zzg.zzb(list.get(3));
                    zzg zza = zzg.zza();
                    for (int i = 0; i < zzae.zzc(); i++) {
                        String zzi = zzae.zze(i).zzi();
                        zza.zzg(zzi, zzg.zzd(zzi));
                    }
                    while (zzg.zzb(zzap).zzg().booleanValue()) {
                        zzap zzc = zzg.zzc((zzae) zzb2);
                        if (zzc instanceof zzag) {
                            zzag zzag = (zzag) zzc;
                            if ("break".equals(zzag.zzc())) {
                                return zzap.zzf;
                            }
                            if ("return".equals(zzag.zzc())) {
                                return zzag;
                            }
                        }
                        zzg zza2 = zzg.zza();
                        for (int i2 = 0; i2 < zzae.zzc(); i2++) {
                            String zzi2 = zzae.zze(i2).zzi();
                            zza2.zzg(zzi2, zza.zzd(zzi2));
                        }
                        zza2.zzb(zzap2);
                        zza = zza2;
                    }
                    return zzap.zzf;
                }
                throw new IllegalArgumentException("Initializer variables in FOR_LET must be an ArrayList");
            case 30:
                zzh.zzh(zzbl.FOR_OF.name(), 3, list);
                if (list.get(0) instanceof zzat) {
                    return zze(new zzbg(zzg, list.get(0).zzi()), zzg.zzb(list.get(1)), zzg.zzb(list.get(2)));
                }
                throw new IllegalArgumentException("Variable name in FOR_OF must be a string");
            case 31:
                zzh.zzh(zzbl.FOR_OF_CONST.name(), 3, list);
                if (list.get(0) instanceof zzat) {
                    return zze(new zzbd(zzg, list.get(0).zzi()), zzg.zzb(list.get(1)), zzg.zzb(list.get(2)));
                }
                throw new IllegalArgumentException("Variable name in FOR_OF_CONST must be a string");
            case 32:
                zzh.zzh(zzbl.FOR_OF_LET.name(), 3, list);
                if (list.get(0) instanceof zzat) {
                    return zze(new zzbe(zzg, list.get(0).zzi()), zzg.zzb(list.get(1)), zzg.zzb(list.get(2)));
                }
                throw new IllegalArgumentException("Variable name in FOR_OF_LET must be a string");
            case 65:
                zzh.zzh(zzbl.WHILE.name(), 4, list);
                zzap zzap3 = list.get(0);
                zzap zzap4 = list.get(1);
                zzap zzb3 = zzg.zzb(list.get(3));
                if (zzg.zzb(list.get(2)).zzg().booleanValue()) {
                    zzap zzc2 = zzg.zzc((zzae) zzb3);
                    if (zzc2 instanceof zzag) {
                        zzag zzag2 = (zzag) zzc2;
                        if ("break".equals(zzag2.zzc())) {
                            return zzap.zzf;
                        }
                        if ("return".equals(zzag2.zzc())) {
                            return zzag2;
                        }
                    }
                }
                while (zzg.zzb(zzap3).zzg().booleanValue()) {
                    zzap zzc3 = zzg.zzc((zzae) zzb3);
                    if (zzc3 instanceof zzag) {
                        zzag zzag3 = (zzag) zzc3;
                        if ("break".equals(zzag3.zzc())) {
                            return zzap.zzf;
                        }
                        if ("return".equals(zzag3.zzc())) {
                            return zzag3;
                        }
                    }
                    zzg.zzb(zzap4);
                }
                return zzap.zzf;
            default:
                return super.zzb(str);
        }
    }
}
