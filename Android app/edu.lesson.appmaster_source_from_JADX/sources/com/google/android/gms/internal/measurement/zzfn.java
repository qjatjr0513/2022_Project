package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.0.0 */
public final class zzfn extends zzjt<zzfo, zzfn> implements zzld {
    private zzfn() {
        super(zzfo.zza);
    }

    public final int zza() {
        return ((zzfo) this.zza).zzb();
    }

    public final long zzb() {
        return ((zzfo) this.zza).zzc();
    }

    public final long zzc() {
        return ((zzfo) this.zza).zzd();
    }

    public final zzfn zzd(Iterable<? extends zzfs> iterable) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfo.zzm((zzfo) this.zza, iterable);
        return this;
    }

    public final zzfn zze(zzfr zzfr) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfo.zzk((zzfo) this.zza, (zzfs) zzfr.zzaA());
        return this;
    }

    public final zzfn zzf(zzfs zzfs) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfo.zzk((zzfo) this.zza, zzfs);
        return this;
    }

    public final zzfn zzg() {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        ((zzfo) this.zza).zzf = zzfo.zzbA();
        return this;
    }

    public final zzfn zzh(int i) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfo.zzo((zzfo) this.zza, i);
        return this;
    }

    public final zzfn zzi(String str) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfo.zzp((zzfo) this.zza, str);
        return this;
    }

    public final zzfn zzj(int i, zzfr zzfr) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfo.zzj((zzfo) this.zza, i, (zzfs) zzfr.zzaA());
        return this;
    }

    public final zzfn zzk(int i, zzfs zzfs) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfo.zzj((zzfo) this.zza, i, zzfs);
        return this;
    }

    public final zzfn zzl(long j) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfo.zzr((zzfo) this.zza, j);
        return this;
    }

    public final zzfn zzm(long j) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfo.zzq((zzfo) this.zza, j);
        return this;
    }

    public final zzfs zzn(int i) {
        return ((zzfo) this.zza).zzg(i);
    }

    public final String zzo() {
        return ((zzfo) this.zza).zzh();
    }

    public final List<zzfs> zzp() {
        return Collections.unmodifiableList(((zzfo) this.zza).zzi());
    }

    public final boolean zzq() {
        return ((zzfo) this.zza).zzu();
    }

    /* synthetic */ zzfn(zzff zzff) {
        super(zzfo.zza);
    }
}
