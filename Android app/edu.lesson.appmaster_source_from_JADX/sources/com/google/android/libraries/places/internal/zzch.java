package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzch {
    private zza[] addressComponents;
    private String businessStatus;
    private String formattedAddress;
    private zzb geometry;
    private String icon;
    private String iconBackgroundColor;
    private String iconMaskBaseUri;
    private String internationalPhoneNumber;
    private String name;
    private zzc openingHours;
    private zzd[] photos;
    private String placeId;
    private zze plusCode;
    private Integer priceLevel;
    private Double rating;
    private String[] types;
    private Integer userRatingsTotal;
    private Integer utcOffset;
    private String website;

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    class zza {
        private String longName;
        private String shortName;
        private String[] types;

        zza() {
        }

        /* access modifiers changed from: package-private */
        public final zzge<String> zza() {
            String[] strArr = this.types;
            if (strArr != null) {
                return zzge.zzl(strArr);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public final String zzb() {
            return this.longName;
        }

        /* access modifiers changed from: package-private */
        public final String zzc() {
            return this.shortName;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    class zzb {
        private zza location;
        private C2509zzb viewport;

        /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
        class zza {
            private Double lat;
            private Double lng;

            zza() {
            }

            /* access modifiers changed from: package-private */
            public final Double zza() {
                return this.lat;
            }

            /* access modifiers changed from: package-private */
            public final Double zzb() {
                return this.lng;
            }
        }

        /* renamed from: com.google.android.libraries.places.internal.zzch$zzb$zzb  reason: collision with other inner class name */
        /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
        class C2509zzb {
            private zza northeast;
            private zza southwest;

            C2509zzb() {
            }

            /* access modifiers changed from: package-private */
            public final zza zza() {
                return this.northeast;
            }

            /* access modifiers changed from: package-private */
            public final zza zzb() {
                return this.southwest;
            }
        }

        zzb() {
        }

        /* access modifiers changed from: package-private */
        public final zza zza() {
            return this.location;
        }

        /* access modifiers changed from: package-private */
        public final C2509zzb zzb() {
            return this.viewport;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    class zzc {
        private zza[] periods;
        private String[] weekdayText;

        /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
        class zza {
            private zzb close;
            private zzb open;

            zza() {
            }

            /* access modifiers changed from: package-private */
            public final zzb zza() {
                return this.close;
            }

            /* access modifiers changed from: package-private */
            public final zzb zzb() {
                return this.open;
            }
        }

        /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
        class zzb {
            private Integer day;
            private String time;

            zzb() {
            }

            /* access modifiers changed from: package-private */
            public final Integer zza() {
                return this.day;
            }

            /* access modifiers changed from: package-private */
            public final String zzb() {
                return this.time;
            }
        }

        zzc() {
        }

        /* access modifiers changed from: package-private */
        public final zzge<zza> zza() {
            zza[] zzaArr = this.periods;
            if (zzaArr != null) {
                return zzge.zzl(zzaArr);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public final zzge<String> zzb() {
            String[] strArr = this.weekdayText;
            if (strArr != null) {
                return zzge.zzl(strArr);
            }
            return null;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    class zzd {
        private Integer height;
        private String[] htmlAttributions;
        private String photoReference;
        private Integer width;

        zzd() {
        }

        /* access modifiers changed from: package-private */
        public final zzge<String> zza() {
            String[] strArr = this.htmlAttributions;
            if (strArr != null) {
                return zzge.zzl(strArr);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public final Integer zzb() {
            return this.height;
        }

        /* access modifiers changed from: package-private */
        public final Integer zzc() {
            return this.width;
        }

        /* access modifiers changed from: package-private */
        public final String zzd() {
            return this.photoReference;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@2.5.0 */
    class zze {
        private String compoundCode;
        private String globalCode;

        zze() {
        }

        /* access modifiers changed from: package-private */
        public final String zza() {
            return this.compoundCode;
        }

        /* access modifiers changed from: package-private */
        public final String zzb() {
            return this.globalCode;
        }
    }

    zzch() {
    }

    /* access modifiers changed from: package-private */
    public final zzb zza() {
        return this.geometry;
    }

    /* access modifiers changed from: package-private */
    public final zzc zzb() {
        return this.openingHours;
    }

    /* access modifiers changed from: package-private */
    public final zze zzc() {
        return this.plusCode;
    }

    /* access modifiers changed from: package-private */
    public final zzge<zza> zzd() {
        zza[] zzaArr = this.addressComponents;
        if (zzaArr != null) {
            return zzge.zzl(zzaArr);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final zzge<zzd> zze() {
        zzd[] zzdArr = this.photos;
        if (zzdArr != null) {
            return zzge.zzl(zzdArr);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final zzge<String> zzf() {
        String[] strArr = this.types;
        if (strArr != null) {
            return zzge.zzl(strArr);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final Double zzg() {
        return this.rating;
    }

    /* access modifiers changed from: package-private */
    public final Integer zzh() {
        return this.priceLevel;
    }

    /* access modifiers changed from: package-private */
    public final Integer zzi() {
        return this.userRatingsTotal;
    }

    /* access modifiers changed from: package-private */
    public final Integer zzj() {
        return this.utcOffset;
    }

    /* access modifiers changed from: package-private */
    public final String zzk() {
        return this.businessStatus;
    }

    /* access modifiers changed from: package-private */
    public final String zzl() {
        return this.formattedAddress;
    }

    /* access modifiers changed from: package-private */
    public final String zzm() {
        return this.iconBackgroundColor;
    }

    /* access modifiers changed from: package-private */
    public final String zzn() {
        return this.iconMaskBaseUri;
    }

    /* access modifiers changed from: package-private */
    public final String zzo() {
        return this.internationalPhoneNumber;
    }

    /* access modifiers changed from: package-private */
    public final String zzp() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public final String zzq() {
        return this.placeId;
    }

    /* access modifiers changed from: package-private */
    public final String zzr() {
        return this.website;
    }
}
