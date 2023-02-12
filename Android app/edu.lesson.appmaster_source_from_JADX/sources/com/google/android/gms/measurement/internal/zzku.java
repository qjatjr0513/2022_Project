package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzej;
import com.google.android.gms.internal.measurement.zzel;
import com.google.android.gms.internal.measurement.zzeq;
import com.google.android.gms.internal.measurement.zzes;
import com.google.android.gms.internal.measurement.zzex;
import com.google.android.gms.internal.measurement.zzfk;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzjj;
import com.google.android.gms.internal.measurement.zzkh;
import com.google.android.gms.internal.measurement.zzlb;
import com.google.android.gms.internal.measurement.zzpe;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzku extends zzki {
    zzku(zzks zzks) {
        super(zzks);
    }

    static final void zzA(zzfn zzfn, String str, Object obj) {
        List<zzfs> zzp = zzfn.zzp();
        int i = 0;
        while (true) {
            if (i >= zzp.size()) {
                i = -1;
                break;
            } else if (str.equals(zzp.get(i).zzg())) {
                break;
            } else {
                i++;
            }
        }
        zzfr zze = zzfs.zze();
        zze.zzj(str);
        if (obj instanceof Long) {
            zze.zzi(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zze.zzk((String) obj);
        } else if (obj instanceof Double) {
            zze.zzh(((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            zze.zzb(zzq((Bundle[]) obj));
        }
        if (i >= 0) {
            zzfn.zzj(i, zze);
        } else {
            zzfn.zze(zze);
        }
    }

    static final boolean zzB(zzat zzat, zzp zzp) {
        Preconditions.checkNotNull(zzat);
        Preconditions.checkNotNull(zzp);
        return !TextUtils.isEmpty(zzp.zzb) || !TextUtils.isEmpty(zzp.zzq);
    }

    static final zzfs zzC(zzfo zzfo, String str) {
        for (zzfs next : zzfo.zzi()) {
            if (next.zzg().equals(str)) {
                return next;
            }
        }
        return null;
    }

    static final Object zzD(zzfo zzfo, String str) {
        zzfs zzC = zzC(zzfo, str);
        if (zzC == null) {
            return null;
        }
        if (zzC.zzy()) {
            return zzC.zzh();
        }
        if (zzC.zzw()) {
            return Long.valueOf(zzC.zzd());
        }
        if (zzC.zzu()) {
            return Double.valueOf(zzC.zza());
        }
        if (zzC.zzc() <= 0) {
            return null;
        }
        List<zzfs> zzi = zzC.zzi();
        ArrayList arrayList = new ArrayList();
        for (zzfs next : zzi) {
            if (next != null) {
                Bundle bundle = new Bundle();
                for (zzfs next2 : next.zzi()) {
                    if (next2.zzy()) {
                        bundle.putString(next2.zzg(), next2.zzh());
                    } else if (next2.zzw()) {
                        bundle.putLong(next2.zzg(), next2.zzd());
                    } else if (next2.zzu()) {
                        bundle.putDouble(next2.zzg(), next2.zza());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    private final void zzE(StringBuilder sb, int i, List<zzfs> list) {
        String str;
        String str2;
        Long l;
        if (list != null) {
            int i2 = i + 1;
            for (zzfs next : list) {
                if (next != null) {
                    zzG(sb, i2);
                    sb.append("param {\n");
                    Double d = null;
                    if (next.zzx()) {
                        str = this.zzs.zzj().zze(next.zzg());
                    } else {
                        str = null;
                    }
                    zzJ(sb, i2, AppMeasurementSdk.ConditionalUserProperty.NAME, str);
                    if (next.zzy()) {
                        str2 = next.zzh();
                    } else {
                        str2 = null;
                    }
                    zzJ(sb, i2, "string_value", str2);
                    if (next.zzw()) {
                        l = Long.valueOf(next.zzd());
                    } else {
                        l = null;
                    }
                    zzJ(sb, i2, "int_value", l);
                    if (next.zzu()) {
                        d = Double.valueOf(next.zza());
                    }
                    zzJ(sb, i2, "double_value", d);
                    if (next.zzc() > 0) {
                        zzE(sb, i2, next.zzi());
                    }
                    zzG(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private final void zzF(StringBuilder sb, int i, zzel zzel) {
        String str;
        if (zzel != null) {
            zzG(sb, i);
            sb.append("filter {\n");
            if (zzel.zzh()) {
                zzJ(sb, i, "complement", Boolean.valueOf(zzel.zzg()));
            }
            if (zzel.zzj()) {
                zzJ(sb, i, "param_name", this.zzs.zzj().zze(zzel.zze()));
            }
            if (zzel.zzk()) {
                int i2 = i + 1;
                zzex zzd = zzel.zzd();
                if (zzd != null) {
                    zzG(sb, i2);
                    sb.append("string_filter {\n");
                    if (zzd.zzi()) {
                        switch (zzd.zzj()) {
                            case 1:
                                str = "UNKNOWN_MATCH_TYPE";
                                break;
                            case 2:
                                str = "REGEXP";
                                break;
                            case 3:
                                str = "BEGINS_WITH";
                                break;
                            case 4:
                                str = "ENDS_WITH";
                                break;
                            case 5:
                                str = "PARTIAL";
                                break;
                            case 6:
                                str = "EXACT";
                                break;
                            default:
                                str = "IN_LIST";
                                break;
                        }
                        zzJ(sb, i2, "match_type", str);
                    }
                    if (zzd.zzh()) {
                        zzJ(sb, i2, "expression", zzd.zzd());
                    }
                    if (zzd.zzg()) {
                        zzJ(sb, i2, "case_sensitive", Boolean.valueOf(zzd.zzf()));
                    }
                    if (zzd.zza() > 0) {
                        zzG(sb, i2 + 1);
                        sb.append("expression_list {\n");
                        for (String append : zzd.zze()) {
                            zzG(sb, i2 + 2);
                            sb.append(append);
                            sb.append("\n");
                        }
                        sb.append("}\n");
                    }
                    zzG(sb, i2);
                    sb.append("}\n");
                }
            }
            if (zzel.zzi()) {
                zzK(sb, i + 1, "number_filter", zzel.zzc());
            }
            zzG(sb, i);
            sb.append("}\n");
        }
    }

    private static final void zzG(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static final String zzH(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private static final void zzI(StringBuilder sb, int i, String str, zzgd zzgd) {
        Integer num;
        Integer num2;
        if (zzgd != null) {
            zzG(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            if (zzgd.zzb() != 0) {
                zzG(sb, 4);
                sb.append("results: ");
                int i2 = 0;
                for (Long next : zzgd.zzk()) {
                    int i3 = i2 + 1;
                    if (i2 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next);
                    i2 = i3;
                }
                sb.append(10);
            }
            if (zzgd.zzd() != 0) {
                zzG(sb, 4);
                sb.append("status: ");
                int i4 = 0;
                for (Long next2 : zzgd.zzn()) {
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next2);
                    i4 = i5;
                }
                sb.append(10);
            }
            if (zzgd.zza() != 0) {
                zzG(sb, 4);
                sb.append("dynamic_filter_timestamps: {");
                int i6 = 0;
                for (zzfm next3 : zzgd.zzj()) {
                    int i7 = i6 + 1;
                    if (i6 != 0) {
                        sb.append(", ");
                    }
                    if (next3.zzh()) {
                        num2 = Integer.valueOf(next3.zza());
                    } else {
                        num2 = null;
                    }
                    sb.append(num2);
                    sb.append(":");
                    sb.append(next3.zzg() ? Long.valueOf(next3.zzb()) : null);
                    i6 = i7;
                }
                sb.append("}\n");
            }
            if (zzgd.zzc() != 0) {
                zzG(sb, 4);
                sb.append("sequence_filter_timestamps: {");
                int i8 = 0;
                for (zzgf next4 : zzgd.zzm()) {
                    int i9 = i8 + 1;
                    if (i8 != 0) {
                        sb.append(", ");
                    }
                    if (next4.zzi()) {
                        num = Integer.valueOf(next4.zzb());
                    } else {
                        num = null;
                    }
                    sb.append(num);
                    sb.append(": [");
                    int i10 = 0;
                    for (Long longValue : next4.zzf()) {
                        long longValue2 = longValue.longValue();
                        int i11 = i10 + 1;
                        if (i10 != 0) {
                            sb.append(", ");
                        }
                        sb.append(longValue2);
                        i10 = i11;
                    }
                    sb.append("]");
                    i8 = i9;
                }
                sb.append("}\n");
            }
            zzG(sb, 3);
            sb.append("}\n");
        }
    }

    private static final void zzJ(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zzG(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    private static final void zzK(StringBuilder sb, int i, String str, zzeq zzeq) {
        String str2;
        if (zzeq != null) {
            zzG(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzeq.zzg()) {
                switch (zzeq.zzm()) {
                    case 1:
                        str2 = "UNKNOWN_COMPARISON_TYPE";
                        break;
                    case 2:
                        str2 = "LESS_THAN";
                        break;
                    case 3:
                        str2 = "GREATER_THAN";
                        break;
                    case 4:
                        str2 = "EQUAL";
                        break;
                    default:
                        str2 = "BETWEEN";
                        break;
                }
                zzJ(sb, i, "comparison_type", str2);
            }
            if (zzeq.zzi()) {
                zzJ(sb, i, "match_as_float", Boolean.valueOf(zzeq.zzf()));
            }
            if (zzeq.zzh()) {
                zzJ(sb, i, "comparison_value", zzeq.zzc());
            }
            if (zzeq.zzk()) {
                zzJ(sb, i, "min_comparison_value", zzeq.zze());
            }
            if (zzeq.zzj()) {
                zzJ(sb, i, "max_comparison_value", zzeq.zzd());
            }
            zzG(sb, i);
            sb.append("}\n");
        }
    }

    static int zza(zzfx zzfx, String str) {
        for (int i = 0; i < zzfx.zzb(); i++) {
            if (str.equals(zzfx.zzak(i).zzf())) {
                return i;
            }
        }
        return -1;
    }

    static <Builder extends zzlb> Builder zzl(Builder builder, byte[] bArr) throws zzkh {
        zzjj zzb = zzjj.zzb();
        if (zzb != null) {
            return builder.zzaw(bArr, zzb);
        }
        return builder.zzav(bArr);
    }

    static List<zzfs> zzq(Bundle[] bundleArr) {
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : bundleArr) {
            if (bundle != null) {
                zzfr zze = zzfs.zze();
                for (String str : bundle.keySet()) {
                    zzfr zze2 = zzfs.zze();
                    zze2.zzj(str);
                    Object obj = bundle.get(str);
                    if (obj instanceof Long) {
                        zze2.zzi(((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        zze2.zzk((String) obj);
                    } else if (obj instanceof Double) {
                        zze2.zzh(((Double) obj).doubleValue());
                    }
                    zze.zzc(zze2);
                }
                if (zze.zza() > 0) {
                    arrayList.add((zzfs) zze.zzaA());
                }
            }
        }
        return arrayList;
    }

    static List<Long> zzs(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i * 64) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    static boolean zzw(List<Long> list, int i) {
        if (i >= list.size() * 64) {
            return false;
        }
        return ((1 << (i % 64)) & list.get(i / 64).longValue()) != 0;
    }

    static boolean zzy(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final long zzd(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        this.zzs.zzv().zzg();
        MessageDigest zzE = zzkz.zzE();
        if (zzE != null) {
            return zzkz.zzp(zzE.digest(bArr));
        }
        this.zzs.zzay().zzd().zza("Failed to get MD5");
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzf(Map<String, Object> map, boolean z) {
        Bundle bundle = new Bundle();
        for (String next : map.keySet()) {
            Object obj = map.get(next);
            if (obj == null) {
                bundle.putString(next, (String) null);
            } else if (obj instanceof Long) {
                bundle.putLong(next, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(next, ((Double) obj).doubleValue());
            } else if (!(obj instanceof ArrayList)) {
                bundle.putString(next, obj.toString());
            } else if (z) {
                zzpe.zzc();
                if (this.zzs.zzf().zzs((String) null, zzdy.zzau)) {
                    ArrayList arrayList = (ArrayList) obj;
                    ArrayList arrayList2 = new ArrayList();
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        arrayList2.add(zzf((Map) arrayList.get(i), false));
                    }
                    bundle.putParcelableArray(next, (Parcelable[]) arrayList2.toArray(new Parcelable[0]));
                } else {
                    ArrayList arrayList3 = (ArrayList) obj;
                    ArrayList arrayList4 = new ArrayList();
                    int size2 = arrayList3.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        arrayList4.add(zzf((Map) arrayList3.get(i2), false));
                    }
                    bundle.putParcelableArrayList(next, arrayList4);
                }
            }
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public final <T extends Parcelable> T zzh(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            return (Parcelable) creator.createFromParcel(obtain);
        } catch (SafeParcelReader.ParseException e) {
            this.zzs.zzay().zzd().zza("Failed to load parcelable from buffer");
            return null;
        } finally {
            obtain.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    public final zzat zzi(zzaa zzaa) {
        String str;
        String str2;
        Object obj;
        Bundle zzf = zzf(zzaa.zze(), true);
        if (!zzf.containsKey("_o") || (obj = zzf.get("_o")) == null) {
            str = "app";
        } else {
            str = obj.toString();
        }
        String zzb = zzgs.zzb(zzaa.zzd());
        if (zzb == null) {
            str2 = zzaa.zzd();
        } else {
            str2 = zzb;
        }
        return new zzat(str2, new zzar(zzf), str, zzaa.zza());
    }

    /* access modifiers changed from: package-private */
    public final zzfo zzj(zzao zzao) {
        zzfn zze = zzfo.zze();
        zze.zzl(zzao.zze);
        zzaq zzaq = new zzaq(zzao.zzf);
        while (zzaq.hasNext()) {
            String zza = zzaq.next();
            zzfr zze2 = zzfs.zze();
            zze2.zzj(zza);
            Object zzf = zzao.zzf.zzf(zza);
            Preconditions.checkNotNull(zzf);
            zzu(zze2, zzf);
            zze.zze(zze2);
        }
        return (zzfo) zze.zzaA();
    }

    /* access modifiers changed from: package-private */
    public final String zzm(zzfw zzfw) {
        Long l;
        Long l2;
        Double d;
        if (zzfw == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        for (zzfy next : zzfw.zzd()) {
            if (next != null) {
                zzG(sb, 1);
                sb.append("bundle {\n");
                if (next.zzbh()) {
                    zzJ(sb, 1, "protocol_version", Integer.valueOf(next.zzd()));
                }
                zzJ(sb, 1, "platform", next.zzK());
                if (next.zzbd()) {
                    zzJ(sb, 1, "gmp_version", Long.valueOf(next.zzn()));
                }
                if (next.zzbn()) {
                    zzJ(sb, 1, "uploading_gmp_version", Long.valueOf(next.zzs()));
                }
                if (next.zzbb()) {
                    zzJ(sb, 1, "dynamite_version", Long.valueOf(next.zzk()));
                }
                if (next.zzaY()) {
                    zzJ(sb, 1, "config_version", Long.valueOf(next.zzi()));
                }
                zzJ(sb, 1, "gmp_app_id", next.zzH());
                zzJ(sb, 1, "admob_app_id", next.zzx());
                zzJ(sb, 1, "app_id", next.zzy());
                zzJ(sb, 1, "app_version", next.zzB());
                if (next.zzaW()) {
                    zzJ(sb, 1, "app_version_major", Integer.valueOf(next.zza()));
                }
                zzJ(sb, 1, "firebase_instance_id", next.zzF());
                if (next.zzba()) {
                    zzJ(sb, 1, "dev_cert_hash", Long.valueOf(next.zzj()));
                }
                zzJ(sb, 1, "app_store", next.zzA());
                if (next.zzbm()) {
                    zzJ(sb, 1, "upload_timestamp_millis", Long.valueOf(next.zzr()));
                }
                if (next.zzbk()) {
                    zzJ(sb, 1, "start_timestamp_millis", Long.valueOf(next.zzq()));
                }
                if (next.zzbc()) {
                    zzJ(sb, 1, "end_timestamp_millis", Long.valueOf(next.zzm()));
                }
                if (next.zzbg()) {
                    zzJ(sb, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(next.zzp()));
                }
                if (next.zzbf()) {
                    zzJ(sb, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(next.zzo()));
                }
                zzJ(sb, 1, "app_instance_id", next.zzz());
                zzJ(sb, 1, "resettable_device_id", next.zzL());
                zzJ(sb, 1, "ds_id", next.zzE());
                if (next.zzbe()) {
                    zzJ(sb, 1, "limited_ad_tracking", Boolean.valueOf(next.zzaT()));
                }
                zzJ(sb, 1, "os_version", next.zzJ());
                zzJ(sb, 1, "device_model", next.zzD());
                zzJ(sb, 1, "user_default_language", next.zzM());
                if (next.zzbl()) {
                    zzJ(sb, 1, "time_zone_offset_minutes", Integer.valueOf(next.zzf()));
                }
                if (next.zzaX()) {
                    zzJ(sb, 1, "bundle_sequential_index", Integer.valueOf(next.zzb()));
                }
                if (next.zzbj()) {
                    zzJ(sb, 1, "service_upload", Boolean.valueOf(next.zzaU()));
                }
                zzJ(sb, 1, "health_monitor", next.zzI());
                if (!this.zzs.zzf().zzs((String) null, zzdy.zzam) && next.zzaV() && next.zzh() != 0) {
                    zzJ(sb, 1, "android_id", Long.valueOf(next.zzh()));
                }
                if (next.zzbi()) {
                    zzJ(sb, 1, "retry_counter", Integer.valueOf(next.zze()));
                }
                if (next.zzaZ()) {
                    zzJ(sb, 1, "consent_signals", next.zzC());
                }
                List<zzgh> zzP = next.zzP();
                if (zzP != null) {
                    for (zzgh next2 : zzP) {
                        if (next2 != null) {
                            zzG(sb, 2);
                            sb.append("user_property {\n");
                            if (next2.zzs()) {
                                l = Long.valueOf(next2.zzc());
                            } else {
                                l = null;
                            }
                            zzJ(sb, 2, "set_timestamp_millis", l);
                            zzJ(sb, 2, AppMeasurementSdk.ConditionalUserProperty.NAME, this.zzs.zzj().zzf(next2.zzf()));
                            zzJ(sb, 2, "string_value", next2.zzg());
                            if (next2.zzr()) {
                                l2 = Long.valueOf(next2.zzb());
                            } else {
                                l2 = null;
                            }
                            zzJ(sb, 2, "int_value", l2);
                            if (next2.zzq()) {
                                d = Double.valueOf(next2.zza());
                            } else {
                                d = null;
                            }
                            zzJ(sb, 2, "double_value", d);
                            zzG(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzfk> zzN = next.zzN();
                if (zzN != null) {
                    for (zzfk next3 : zzN) {
                        if (next3 != null) {
                            zzG(sb, 2);
                            sb.append("audience_membership {\n");
                            if (next3.zzk()) {
                                zzJ(sb, 2, "audience_id", Integer.valueOf(next3.zza()));
                            }
                            if (next3.zzm()) {
                                zzJ(sb, 2, "new_audience", Boolean.valueOf(next3.zzj()));
                            }
                            zzI(sb, 2, "current_data", next3.zzd());
                            if (next3.zzn()) {
                                zzI(sb, 2, "previous_data", next3.zze());
                            }
                            zzG(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzfo> zzO = next.zzO();
                if (zzO != null) {
                    for (zzfo next4 : zzO) {
                        if (next4 != null) {
                            zzG(sb, 2);
                            sb.append("event {\n");
                            zzJ(sb, 2, AppMeasurementSdk.ConditionalUserProperty.NAME, this.zzs.zzj().zzd(next4.zzh()));
                            if (next4.zzu()) {
                                zzJ(sb, 2, "timestamp_millis", Long.valueOf(next4.zzd()));
                            }
                            if (next4.zzt()) {
                                zzJ(sb, 2, "previous_timestamp_millis", Long.valueOf(next4.zzc()));
                            }
                            if (next4.zzs()) {
                                zzJ(sb, 2, "count", Integer.valueOf(next4.zza()));
                            }
                            if (next4.zzb() != 0) {
                                zzE(sb, 2, next4.zzi());
                            }
                            zzG(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zzG(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zzo(zzej zzej) {
        if (zzej == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzej.zzp()) {
            zzJ(sb, 0, "filter_id", Integer.valueOf(zzej.zzb()));
        }
        zzJ(sb, 0, "event_name", this.zzs.zzj().zzd(zzej.zzg()));
        String zzH = zzH(zzej.zzk(), zzej.zzm(), zzej.zzn());
        if (!zzH.isEmpty()) {
            zzJ(sb, 0, "filter_type", zzH);
        }
        if (zzej.zzo()) {
            zzK(sb, 1, "event_count_filter", zzej.zzf());
        }
        if (zzej.zza() > 0) {
            sb.append("  filters {\n");
            for (zzel zzF : zzej.zzh()) {
                zzF(sb, 2, zzF);
            }
        }
        zzG(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zzp(zzes zzes) {
        if (zzes == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zzes.zzj()) {
            zzJ(sb, 0, "filter_id", Integer.valueOf(zzes.zza()));
        }
        zzJ(sb, 0, "property_name", this.zzs.zzj().zzf(zzes.zze()));
        String zzH = zzH(zzes.zzg(), zzes.zzh(), zzes.zzi());
        if (!zzH.isEmpty()) {
            zzJ(sb, 0, "filter_type", zzH);
        }
        zzF(sb, 1, zzes.zzb());
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final List<Long> zzr(List<Long> list, List<Integer> list2) {
        ArrayList arrayList = new ArrayList(list);
        for (Integer next : list2) {
            if (next.intValue() < 0) {
                this.zzs.zzay().zzk().zzb("Ignoring negative bit index to be cleared", next);
            } else {
                int intValue = next.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    this.zzs.zzay().zzk().zzc("Ignoring bit index greater than bitSet size", next, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & (~(1 << (next.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (size2 >= 0 && ((Long) arrayList.get(size2)).longValue() == 0) {
            size = size2;
            size2--;
        }
        return arrayList.subList(0, size);
    }

    /* access modifiers changed from: package-private */
    public final Map<String, Object> zzt(Bundle bundle, boolean z) {
        HashMap hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            zzpe.zzc();
            if (!this.zzs.zzf().zzs((String) null, zzdy.zzau) ? (obj instanceof Bundle[]) || (obj instanceof ArrayList) || (obj instanceof Bundle) : (obj instanceof Parcelable[]) || (obj instanceof ArrayList) || (obj instanceof Bundle)) {
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    if (obj instanceof Parcelable[]) {
                        for (Parcelable parcelable : (Parcelable[]) obj) {
                            if (parcelable instanceof Bundle) {
                                arrayList.add(zzt((Bundle) parcelable, false));
                            }
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList2 = (ArrayList) obj;
                        int size = arrayList2.size();
                        for (int i = 0; i < size; i++) {
                            Object obj2 = arrayList2.get(i);
                            if (obj2 instanceof Bundle) {
                                arrayList.add(zzt((Bundle) obj2, false));
                            }
                        }
                    } else if (obj instanceof Bundle) {
                        arrayList.add(zzt((Bundle) obj, false));
                    }
                    hashMap.put(str, arrayList);
                }
            } else if (obj != null) {
                hashMap.put(str, obj);
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final void zzu(zzfr zzfr, Object obj) {
        Preconditions.checkNotNull(obj);
        zzfr.zzg();
        zzfr.zze();
        zzfr.zzd();
        zzfr.zzf();
        if (obj instanceof String) {
            zzfr.zzk((String) obj);
        } else if (obj instanceof Long) {
            zzfr.zzi(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzfr.zzh(((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            zzfr.zzb(zzq((Bundle[]) obj));
        } else {
            this.zzs.zzay().zzd().zzb("Ignoring invalid (type) event param value", obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzv(zzgg zzgg, Object obj) {
        Preconditions.checkNotNull(obj);
        zzgg.zzc();
        zzgg.zzb();
        zzgg.zza();
        if (obj instanceof String) {
            zzgg.zzh((String) obj);
        } else if (obj instanceof Long) {
            zzgg.zze(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzgg.zzd(((Double) obj).doubleValue());
        } else {
            this.zzs.zzay().zzd().zzb("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzx(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(this.zzs.zzav().currentTimeMillis() - j) > j2;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzz(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            this.zzs.zzay().zzd().zzb("Failed to gzip content", e);
            throw e;
        }
    }
}
