package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzbk extends zzaw {
    protected zzbk() {
        this.zza.add(zzbl.ASSIGN);
        this.zza.add(zzbl.CONST);
        this.zza.add(zzbl.CREATE_ARRAY);
        this.zza.add(zzbl.CREATE_OBJECT);
        this.zza.add(zzbl.EXPRESSION_LIST);
        this.zza.add(zzbl.GET);
        this.zza.add(zzbl.GET_INDEX);
        this.zza.add(zzbl.GET_PROPERTY);
        this.zza.add(zzbl.NULL);
        this.zza.add(zzbl.SET_PROPERTY);
        this.zza.add(zzbl.TYPEOF);
        this.zza.add(zzbl.UNDEFINED);
        this.zza.add(zzbl.VAR);
    }

    public final zzap zza(String str, zzg zzg, List<zzap> list) {
        String str2;
        zzbl zzbl = zzbl.ADD;
        int i = 0;
        switch (zzh.zze(str).ordinal()) {
            case 3:
                zzh.zzh(zzbl.ASSIGN.name(), 2, list);
                zzap zzb = zzg.zzb(list.get(0));
                if (!(zzb instanceof zzat)) {
                    throw new IllegalArgumentException(String.format("Expected string for assign var. got %s", new Object[]{zzb.getClass().getCanonicalName()}));
                } else if (zzg.zzh(zzb.zzi())) {
                    zzap zzb2 = zzg.zzb(list.get(1));
                    zzg.zzg(zzb.zzi(), zzb2);
                    return zzb2;
                } else {
                    throw new IllegalArgumentException(String.format("Attempting to assign undefined value %s", new Object[]{zzb.zzi()}));
                }
            case 14:
                zzh.zzi(zzbl.CONST.name(), 2, list);
                if (list.size() % 2 == 0) {
                    int i2 = 0;
                    while (i2 < list.size() - 1) {
                        zzap zzb3 = zzg.zzb(list.get(i2));
                        if (zzb3 instanceof zzat) {
                            zzg.zzf(zzb3.zzi(), zzg.zzb(list.get(i2 + 1)));
                            i2 += 2;
                        } else {
                            throw new IllegalArgumentException(String.format("Expected string for const name. got %s", new Object[]{zzb3.getClass().getCanonicalName()}));
                        }
                    }
                    return zzap.zzf;
                }
                throw new IllegalArgumentException(String.format("CONST requires an even number of arguments, found %s", new Object[]{Integer.valueOf(list.size())}));
            case 17:
                if (list.isEmpty()) {
                    return new zzae();
                }
                zzae zzae = new zzae();
                for (zzap zzb4 : list) {
                    zzap zzb5 = zzg.zzb(zzb4);
                    if (!(zzb5 instanceof zzag)) {
                        zzae.zzq(i, zzb5);
                        i++;
                    } else {
                        throw new IllegalStateException("Failed to evaluate array element");
                    }
                }
                return zzae;
            case 18:
                if (list.isEmpty()) {
                    return new zzam();
                }
                if (list.size() % 2 == 0) {
                    zzam zzam = new zzam();
                    while (i < list.size() - 1) {
                        zzap zzb6 = zzg.zzb(list.get(i));
                        zzap zzb7 = zzg.zzb(list.get(i + 1));
                        if ((zzb6 instanceof zzag) || (zzb7 instanceof zzag)) {
                            throw new IllegalStateException("Failed to evaluate map entry");
                        }
                        zzam.zzr(zzb6.zzi(), zzb7);
                        i += 2;
                    }
                    return zzam;
                }
                throw new IllegalArgumentException(String.format("CREATE_OBJECT requires an even number of arguments, found %s", new Object[]{Integer.valueOf(list.size())}));
            case 24:
                zzh.zzi(zzbl.EXPRESSION_LIST.name(), 1, list);
                zzap zzap = zzap.zzf;
                while (i < list.size()) {
                    zzap = zzg.zzb(list.get(i));
                    if (!(zzap instanceof zzag)) {
                        i++;
                    } else {
                        throw new IllegalStateException("ControlValue cannot be in an expression list");
                    }
                }
                return zzap;
            case 33:
                zzh.zzh(zzbl.GET.name(), 1, list);
                zzap zzb8 = zzg.zzb(list.get(0));
                if (zzb8 instanceof zzat) {
                    return zzg.zzd(zzb8.zzi());
                }
                throw new IllegalArgumentException(String.format("Expected string for get var. got %s", new Object[]{zzb8.getClass().getCanonicalName()}));
            case 35:
            case 36:
                zzh.zzh(zzbl.GET_PROPERTY.name(), 2, list);
                zzap zzb9 = zzg.zzb(list.get(0));
                zzap zzb10 = zzg.zzb(list.get(1));
                if ((zzb9 instanceof zzae) && zzh.zzk(zzb10)) {
                    return ((zzae) zzb9).zze(zzb10.zzh().intValue());
                }
                if (zzb9 instanceof zzal) {
                    return ((zzal) zzb9).zzf(zzb10.zzi());
                }
                if (zzb9 instanceof zzat) {
                    if ("length".equals(zzb10.zzi())) {
                        return new zzah(Double.valueOf((double) zzb9.zzi().length()));
                    }
                    if (zzh.zzk(zzb10) && zzb10.zzh().doubleValue() < ((double) zzb9.zzi().length())) {
                        return new zzat(String.valueOf(zzb9.zzi().charAt(zzb10.zzh().intValue())));
                    }
                }
                return zzap.zzf;
            case 49:
                zzh.zzh(zzbl.NULL.name(), 0, list);
                return zzap.zzg;
            case 58:
                zzh.zzh(zzbl.SET_PROPERTY.name(), 3, list);
                zzap zzb11 = zzg.zzb(list.get(0));
                zzap zzb12 = zzg.zzb(list.get(1));
                zzap zzb13 = zzg.zzb(list.get(2));
                if (zzb11 == zzap.zzf || zzb11 == zzap.zzg) {
                    throw new IllegalStateException(String.format("Can't set property %s of %s", new Object[]{zzb12.zzi(), zzb11.zzi()}));
                }
                if ((zzb11 instanceof zzae) && (zzb12 instanceof zzah)) {
                    ((zzae) zzb11).zzq(zzb12.zzh().intValue(), zzb13);
                } else if (zzb11 instanceof zzal) {
                    ((zzal) zzb11).zzr(zzb12.zzi(), zzb13);
                }
                return zzb13;
            case 62:
                zzh.zzh(zzbl.TYPEOF.name(), 1, list);
                zzap zzb14 = zzg.zzb(list.get(0));
                if (zzb14 instanceof zzau) {
                    str2 = "undefined";
                } else if (zzb14 instanceof zzaf) {
                    str2 = TypedValues.Custom.S_BOOLEAN;
                } else if (zzb14 instanceof zzah) {
                    str2 = "number";
                } else if (zzb14 instanceof zzat) {
                    str2 = TypedValues.Custom.S_STRING;
                } else if (zzb14 instanceof zzao) {
                    str2 = "function";
                } else if ((zzb14 instanceof zzaq) || (zzb14 instanceof zzag)) {
                    throw new IllegalArgumentException(String.format("Unsupported value type %s in typeof", new Object[]{zzb14}));
                } else {
                    str2 = "object";
                }
                return new zzat(str2);
            case 63:
                zzh.zzh(zzbl.UNDEFINED.name(), 0, list);
                return zzap.zzf;
            case 64:
                zzh.zzi(zzbl.VAR.name(), 1, list);
                for (zzap zzb15 : list) {
                    zzap zzb16 = zzg.zzb(zzb15);
                    if (zzb16 instanceof zzat) {
                        zzg.zze(zzb16.zzi(), zzap.zzf);
                    } else {
                        throw new IllegalArgumentException(String.format("Expected string for var name. got %s", new Object[]{zzb16.getClass().getCanonicalName()}));
                    }
                }
                return zzap.zzf;
            default:
                return super.zzb(str);
        }
    }
}
