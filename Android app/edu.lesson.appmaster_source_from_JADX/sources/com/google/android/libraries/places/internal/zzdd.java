package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzdd extends zzdf {
    private final String zza;
    private final int zzb;
    private final int zzc;

    /* synthetic */ zzdd(String str, int i, int i2, zzdc zzdc) {
        this.zza = str;
        this.zzb = i;
        this.zzc = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzdf) {
            zzdf zzdf = (zzdf) obj;
            if (this.zza.equals(zzdf.zzb()) && this.zzb == zzdf.zza()) {
                int i = this.zzc;
                int zzc2 = zzdf.zzc();
                if (i == 0) {
                    throw null;
                } else if (i == zzc2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb) * 1000003;
        int i = this.zzc;
        if (i != 0) {
            return hashCode ^ i;
        }
        throw null;
    }

    public final String toString() {
        String str;
        String str2 = this.zza;
        int i = this.zzb;
        switch (this.zzc) {
            case 1:
                str = "PROGRAMMATIC_API";
                break;
            case 2:
                str = "AUTOCOMPLETE_WIDGET";
                break;
            default:
                str = "null";
                break;
        }
        StringBuilder sb = new StringBuilder(str2.length() + 68 + str.length());
        sb.append("ClientProfile{packageName=");
        sb.append(str2);
        sb.append(", versionCode=");
        sb.append(i);
        sb.append(", requestSource=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    public final int zza() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zza;
    }

    public final int zzc() {
        return this.zzc;
    }
}
