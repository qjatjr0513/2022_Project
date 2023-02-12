package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzeq;
import com.google.android.gms.internal.measurement.zzex;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
abstract class zzx {
    final String zzb;
    final int zzc;
    Boolean zzd;
    Boolean zze;
    Long zzf;
    Long zzg;

    zzx(String str, int i) {
        this.zzb = str;
        this.zzc = i;
    }

    private static Boolean zzd(String str, int i, boolean z, String str2, List<String> list, String str3, zzel zzel) {
        int i2;
        if (i == 7) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 2) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i - 1) {
            case 1:
                if (str3 == null) {
                    return null;
                }
                if (true != z) {
                    i2 = 66;
                } else {
                    i2 = 0;
                }
                try {
                    return Boolean.valueOf(Pattern.compile(str3, i2).matcher(str).matches());
                } catch (PatternSyntaxException e) {
                    if (zzel != null) {
                        zzel.zzk().zzb("Invalid regular expression in REGEXP audience filter. expression", str3);
                    }
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                if (list == null) {
                    return null;
                }
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    static Boolean zze(BigDecimal bigDecimal, zzeq zzeq, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        Preconditions.checkNotNull(zzeq);
        if (zzeq.zzg()) {
            boolean z = true;
            if (zzeq.zzm() != 1) {
                if (zzeq.zzm() == 5) {
                    if (!zzeq.zzk() || !zzeq.zzj()) {
                        return null;
                    }
                } else if (!zzeq.zzh()) {
                    return null;
                }
                int zzm = zzeq.zzm();
                if (zzeq.zzm() == 5) {
                    if (!zzku.zzy(zzeq.zze()) || !zzku.zzy(zzeq.zzd())) {
                        return null;
                    }
                    try {
                        BigDecimal bigDecimal5 = new BigDecimal(zzeq.zze());
                        bigDecimal3 = new BigDecimal(zzeq.zzd());
                        bigDecimal2 = bigDecimal5;
                        bigDecimal4 = null;
                    } catch (NumberFormatException e) {
                        return null;
                    }
                } else if (!zzku.zzy(zzeq.zzc())) {
                    return null;
                } else {
                    try {
                        bigDecimal4 = new BigDecimal(zzeq.zzc());
                        bigDecimal2 = null;
                        bigDecimal3 = null;
                    } catch (NumberFormatException e2) {
                        return null;
                    }
                }
                if (zzm == 5) {
                    if (bigDecimal2 == null) {
                        return null;
                    }
                } else if (bigDecimal4 == null) {
                    return null;
                }
                switch (zzm - 1) {
                    case 1:
                        if (bigDecimal4 == null) {
                            return null;
                        }
                        if (bigDecimal.compareTo(bigDecimal4) >= 0) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        if (bigDecimal4 == null) {
                            return null;
                        }
                        if (bigDecimal.compareTo(bigDecimal4) <= 0) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        if (bigDecimal4 == null) {
                            return null;
                        }
                        if (d != 0.0d) {
                            if (bigDecimal.compareTo(bigDecimal4.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) <= 0 || bigDecimal.compareTo(bigDecimal4.add(new BigDecimal(d).multiply(new BigDecimal(2)))) >= 0) {
                                z = false;
                            }
                            return Boolean.valueOf(z);
                        }
                        if (bigDecimal.compareTo(bigDecimal4) != 0) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 4:
                        if (bigDecimal2 == null) {
                            return null;
                        }
                        if (bigDecimal.compareTo(bigDecimal2) < 0 || bigDecimal.compareTo(bigDecimal3) > 0) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        return null;
                }
            }
        }
        return null;
    }

    static Boolean zzf(String str, zzex zzex, zzel zzel) {
        String str2;
        List<String> list;
        String str3;
        Preconditions.checkNotNull(zzex);
        if (str == null || !zzex.zzi() || zzex.zzj() == 1) {
            return null;
        }
        if (zzex.zzj() == 7) {
            if (zzex.zza() == 0) {
                return null;
            }
        } else if (!zzex.zzh()) {
            return null;
        }
        int zzj = zzex.zzj();
        boolean zzf2 = zzex.zzf();
        if (zzf2 || zzj == 2 || zzj == 7) {
            str2 = zzex.zzd();
        } else {
            str2 = zzex.zzd().toUpperCase(Locale.ENGLISH);
        }
        if (zzex.zza() == 0) {
            list = null;
        } else {
            List<String> zze2 = zzex.zze();
            if (!zzf2) {
                ArrayList arrayList = new ArrayList(zze2.size());
                for (String upperCase : zze2) {
                    arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
                }
                list = Collections.unmodifiableList(arrayList);
            } else {
                list = zze2;
            }
        }
        if (zzj == 2) {
            str3 = str2;
        } else {
            str3 = null;
        }
        return zzd(str, zzj, zzf2, str2, list, str3, zzel);
    }

    static Boolean zzg(double d, zzeq zzeq) {
        try {
            return zze(new BigDecimal(d), zzeq, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static Boolean zzh(long j, zzeq zzeq) {
        try {
            return zze(new BigDecimal(j), zzeq, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static Boolean zzi(String str, zzeq zzeq) {
        if (!zzku.zzy(str)) {
            return null;
        }
        try {
            return zze(new BigDecimal(str), zzeq, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static Boolean zzj(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    /* access modifiers changed from: package-private */
    public abstract int zza();

    /* access modifiers changed from: package-private */
    public abstract boolean zzb();

    /* access modifiers changed from: package-private */
    public abstract boolean zzc();
}
