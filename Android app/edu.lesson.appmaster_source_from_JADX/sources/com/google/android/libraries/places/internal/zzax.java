package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzax {
    private String description;
    private Integer distanceMeters;
    private zzb[] matchedSubstrings;
    private String placeId;
    private zza structuredFormatting;
    private String[] types;

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    class zza {
        private String mainText;
        private zzb[] mainTextMatchedSubstrings;
        private String secondaryText;
        private zzb[] secondaryTextMatchedSubstrings;

        zza() {
        }

        /* access modifiers changed from: package-private */
        public final zzge<zzb> zza() {
            zzb[] zzbArr = this.mainTextMatchedSubstrings;
            if (zzbArr != null) {
                return zzge.zzl(zzbArr);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public final zzge<zzb> zzb() {
            zzb[] zzbArr = this.secondaryTextMatchedSubstrings;
            if (zzbArr != null) {
                return zzge.zzl(zzbArr);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public final String zzc() {
            return this.mainText;
        }

        /* access modifiers changed from: package-private */
        public final String zzd() {
            return this.secondaryText;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    class zzb {
        Integer length;
        Integer offset;

        zzb() {
        }
    }

    zzax() {
    }

    /* access modifiers changed from: package-private */
    public final zza zza() {
        return this.structuredFormatting;
    }

    /* access modifiers changed from: package-private */
    public final zzge<zzb> zzb() {
        zzb[] zzbArr = this.matchedSubstrings;
        if (zzbArr != null) {
            return zzge.zzl(zzbArr);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final zzge<String> zzc() {
        String[] strArr = this.types;
        if (strArr != null) {
            return zzge.zzl(strArr);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final Integer zzd() {
        return this.distanceMeters;
    }

    /* access modifiers changed from: package-private */
    public final String zze() {
        return this.description;
    }

    /* access modifiers changed from: package-private */
    public final String zzf() {
        return this.placeId;
    }
}
