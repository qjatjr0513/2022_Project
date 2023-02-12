package com.google.android.gms.internal.places;

public class zzbp {
    private static final zzap zzdv = zzap.zzao();
    private zzw zzju;
    private volatile zzck zzjv;
    private volatile zzw zzjw;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbp)) {
            return false;
        }
        zzbp zzbp = (zzbp) obj;
        zzck zzck = this.zzjv;
        zzck zzck2 = zzbp.zzjv;
        if (zzck == null && zzck2 == null) {
            return zzv().equals(zzbp.zzv());
        }
        if (zzck != null && zzck2 != null) {
            return zzck.equals(zzck2);
        }
        if (zzck != null) {
            return zzck.equals(zzbp.zzi(zzck.zzbg()));
        }
        return zzi(zzck2.zzbg()).equals(zzck2);
    }

    public int hashCode() {
        return 1;
    }

    private final zzck zzi(zzck zzck) {
        if (this.zzjv == null) {
            synchronized (this) {
                if (this.zzjv == null) {
                    try {
                        this.zzjv = zzck;
                        this.zzjw = zzw.zzeg;
                    } catch (zzbk e) {
                        this.zzjv = zzck;
                        this.zzjw = zzw.zzeg;
                    }
                }
            }
        }
        return this.zzjv;
    }

    public final zzck zzj(zzck zzck) {
        zzck zzck2 = this.zzjv;
        this.zzju = null;
        this.zzjw = null;
        this.zzjv = zzck;
        return zzck2;
    }

    public final int zzbh() {
        if (this.zzjw != null) {
            return this.zzjw.size();
        }
        if (this.zzjv != null) {
            return this.zzjv.zzbh();
        }
        return 0;
    }

    public final zzw zzv() {
        if (this.zzjw != null) {
            return this.zzjw;
        }
        synchronized (this) {
            if (this.zzjw != null) {
                zzw zzw = this.zzjw;
                return zzw;
            }
            if (this.zzjv == null) {
                this.zzjw = zzw.zzeg;
            } else {
                this.zzjw = this.zzjv.zzv();
            }
            zzw zzw2 = this.zzjw;
            return zzw2;
        }
    }
}
