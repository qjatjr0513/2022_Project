package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyy */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzyy implements zzabk {
    private final zzyx zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzyy(zzyx zzyx) {
        zzaac.zzf(zzyx, "input");
        this.zza = zzyx;
        zzyx.zzb = this;
    }

    private final <T> T zzP(zzabl<T> zzabl, zzzj zzzj) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            T zze = zzabl.zze();
            zzabl.zzh(zze, this, zzzj);
            zzabl.zzf(zze);
            if (this.zzb == this.zzc) {
                return zze;
            }
            throw zzaae.zzg();
        } finally {
            this.zzc = i;
        }
    }

    private final <T> T zzQ(zzabl<T> zzabl, zzzj zzzj) throws IOException {
        int zze = ((zzyw) this.zza).zze();
        zzyx zzyx = this.zza;
        if (zzyx.zza < 100) {
            int zzc2 = zzyx.zzc(zze);
            T zze2 = zzabl.zze();
            this.zza.zza++;
            zzabl.zzh(zze2, this, zzzj);
            zzabl.zzf(zze2);
            this.zza.zzm(0);
            zzyx zzyx2 = this.zza;
            zzyx2.zza--;
            zzyx2.zzn(zzc2);
            return zze2;
        }
        throw new zzaae("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final void zzR(int i) throws IOException {
        if (this.zza.zzb() != i) {
            throw zzaae.zzi();
        }
    }

    private final void zzS(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzaae.zza();
        }
    }

    private static final void zzT(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzaae.zzg();
        }
    }

    private static final void zzU(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzaae.zzg();
        }
    }

    public static zzyy zzq(zzyx zzyx) {
        zzyy zzyy = zzyx.zzb;
        return zzyy != null ? zzyy : new zzyy(zzyx);
    }

    public final void zzA(List<Long> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzaao) {
            zzaao zzaao = (zzaao) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int zze = ((zzyw) this.zza).zze();
                    zzU(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzaao.zzf(((zzyw) this.zza).zzg());
                    } while (this.zza.zzb() < zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzaao.zzf(((zzyw) this.zza).zzg());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int zze2 = ((zzyw) this.zza).zze();
                zzU(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Long.valueOf(((zzyw) this.zza).zzg()));
                } while (this.zza.zzb() < zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Long.valueOf(((zzyw) this.zza).zzg()));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzB(List<Float> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzzq) {
            zzzq zzzq = (zzzq) list;
            switch (this.zzb & 7) {
                case 2:
                    int zze = ((zzyw) this.zza).zze();
                    zzT(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzzq.zze(Float.intBitsToFloat(((zzyw) this.zza).zzd()));
                    } while (this.zza.zzb() < zzb2);
                    return;
                case 5:
                    break;
                default:
                    throw zzaae.zza();
            }
            do {
                zzzq.zze(Float.intBitsToFloat(((zzyw) this.zza).zzd()));
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int zze2 = ((zzyw) this.zza).zze();
                zzT(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Float.valueOf(Float.intBitsToFloat(((zzyw) this.zza).zzd())));
                } while (this.zza.zzb() < zzb3);
                return;
            case 5:
                break;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Float.valueOf(Float.intBitsToFloat(((zzyw) this.zza).zzd())));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final <T> void zzC(List<T> list, zzabl<T> zzabl, zzzj zzzj) throws IOException {
        int zzf;
        int i = this.zzb;
        if ((i & 7) == 3) {
            do {
                list.add(zzP(zzabl, zzzj));
                if (!this.zza.zzp() && this.zzd == 0) {
                    zzf = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf == i);
            this.zzd = zzf;
            return;
        }
        throw zzaae.zza();
    }

    public final void zzD(List<Integer> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzzx) {
            zzzx zzzx = (zzzx) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzyw) this.zza).zze();
                    do {
                        zzzx.zzf(((zzyw) this.zza).zze());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzzx.zzf(((zzyw) this.zza).zze());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzyw) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzyw) this.zza).zze()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Integer.valueOf(((zzyw) this.zza).zze()));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzE(List<Long> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzaao) {
            zzaao zzaao = (zzaao) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzyw) this.zza).zze();
                    do {
                        zzaao.zzf(((zzyw) this.zza).zzh());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzaao.zzf(((zzyw) this.zza).zzh());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzyw) this.zza).zze();
                do {
                    list.add(Long.valueOf(((zzyw) this.zza).zzh()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Long.valueOf(((zzyw) this.zza).zzh()));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final <T> void zzF(List<T> list, zzabl<T> zzabl, zzzj zzzj) throws IOException {
        int zzf;
        int i = this.zzb;
        if ((i & 7) == 2) {
            do {
                list.add(zzQ(zzabl, zzzj));
                if (!this.zza.zzp() && this.zzd == 0) {
                    zzf = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf == i);
            this.zzd = zzf;
            return;
        }
        throw zzaae.zza();
    }

    public final void zzG(List<Integer> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzzx) {
            zzzx zzzx = (zzzx) list;
            switch (this.zzb & 7) {
                case 2:
                    int zze = ((zzyw) this.zza).zze();
                    zzT(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzzx.zzf(((zzyw) this.zza).zzd());
                    } while (this.zza.zzb() < zzb2);
                    return;
                case 5:
                    break;
                default:
                    throw zzaae.zza();
            }
            do {
                zzzx.zzf(((zzyw) this.zza).zzd());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int zze2 = ((zzyw) this.zza).zze();
                zzT(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Integer.valueOf(((zzyw) this.zza).zzd()));
                } while (this.zza.zzb() < zzb3);
                return;
            case 5:
                break;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Integer.valueOf(((zzyw) this.zza).zzd()));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzH(List<Long> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzaao) {
            zzaao zzaao = (zzaao) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int zze = ((zzyw) this.zza).zze();
                    zzU(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzaao.zzf(((zzyw) this.zza).zzg());
                    } while (this.zza.zzb() < zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzaao.zzf(((zzyw) this.zza).zzg());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int zze2 = ((zzyw) this.zza).zze();
                zzU(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Long.valueOf(((zzyw) this.zza).zzg()));
                } while (this.zza.zzb() < zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Long.valueOf(((zzyw) this.zza).zzg()));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzI(List<Integer> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzzx) {
            zzzx zzzx = (zzzx) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzyw) this.zza).zze();
                    do {
                        zzzx.zzf(zzyw.zzs(((zzyw) this.zza).zze()));
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzzx.zzf(zzyw.zzs(((zzyw) this.zza).zze()));
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzyw) this.zza).zze();
                do {
                    list.add(Integer.valueOf(zzyw.zzs(((zzyw) this.zza).zze())));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Integer.valueOf(zzyw.zzs(((zzyw) this.zza).zze())));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzJ(List<Long> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzaao) {
            zzaao zzaao = (zzaao) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzyw) this.zza).zze();
                    do {
                        zzaao.zzf(zzyw.zzt(((zzyw) this.zza).zzh()));
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzaao.zzf(zzyw.zzt(((zzyw) this.zza).zzh()));
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzyw) this.zza).zze();
                do {
                    list.add(Long.valueOf(zzyw.zzt(((zzyw) this.zza).zzh())));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Long.valueOf(zzyw.zzt(((zzyw) this.zza).zzh())));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzL(List<Integer> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzzx) {
            zzzx zzzx = (zzzx) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzyw) this.zza).zze();
                    do {
                        zzzx.zzf(((zzyw) this.zza).zze());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzzx.zzf(((zzyw) this.zza).zze());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzyw) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzyw) this.zza).zze()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Integer.valueOf(((zzyw) this.zza).zze()));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzM(List<Long> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzaao) {
            zzaao zzaao = (zzaao) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzyw) this.zza).zze();
                    do {
                        zzaao.zzf(((zzyw) this.zza).zzh());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzaao.zzf(((zzyw) this.zza).zzh());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzyw) this.zza).zze();
                do {
                    list.add(Long.valueOf(((zzyw) this.zza).zzh()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Long.valueOf(((zzyw) this.zza).zzh()));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final boolean zzN() throws IOException {
        zzS(0);
        return this.zza.zzq();
    }

    public final boolean zzO() throws IOException {
        int i;
        if (this.zza.zzp() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzr(i);
    }

    public final double zza() throws IOException {
        zzS(1);
        return Double.longBitsToDouble(((zzyw) this.zza).zzg());
    }

    public final float zzb() throws IOException {
        zzS(5);
        return Float.intBitsToFloat(((zzyw) this.zza).zzd());
    }

    public final int zzc() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            i = this.zza.zzf();
            this.zzb = i;
        }
        if (i == 0 || i == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    public final int zzd() {
        return this.zzb;
    }

    public final int zze() throws IOException {
        zzS(0);
        return ((zzyw) this.zza).zze();
    }

    public final int zzf() throws IOException {
        zzS(5);
        return ((zzyw) this.zza).zzd();
    }

    public final int zzg() throws IOException {
        zzS(0);
        return ((zzyw) this.zza).zze();
    }

    public final int zzh() throws IOException {
        zzS(5);
        return ((zzyw) this.zza).zzd();
    }

    public final int zzi() throws IOException {
        zzS(0);
        return zzyw.zzs(((zzyw) this.zza).zze());
    }

    public final int zzj() throws IOException {
        zzS(0);
        return ((zzyw) this.zza).zze();
    }

    public final long zzk() throws IOException {
        zzS(1);
        return ((zzyw) this.zza).zzg();
    }

    public final long zzl() throws IOException {
        zzS(0);
        return ((zzyw) this.zza).zzh();
    }

    public final long zzm() throws IOException {
        zzS(1);
        return ((zzyw) this.zza).zzg();
    }

    public final long zzn() throws IOException {
        zzS(0);
        return zzyw.zzt(((zzyw) this.zza).zzh());
    }

    public final long zzo() throws IOException {
        zzS(0);
        return ((zzyw) this.zza).zzh();
    }

    public final zzyu zzp() throws IOException {
        zzS(2);
        return this.zza.zzj();
    }

    public final <T> T zzr(zzabl<T> zzabl, zzzj zzzj) throws IOException {
        zzS(3);
        return zzP(zzabl, zzzj);
    }

    public final <T> T zzs(zzabl<T> zzabl, zzzj zzzj) throws IOException {
        zzS(2);
        return zzQ(zzabl, zzzj);
    }

    public final String zzt() throws IOException {
        zzS(2);
        return this.zza.zzk();
    }

    public final String zzu() throws IOException {
        zzS(2);
        return this.zza.zzl();
    }

    public final void zzv(List<Boolean> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzyj) {
            zzyj zzyj = (zzyj) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzyw) this.zza).zze();
                    do {
                        zzyj.zze(this.zza.zzq());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzyj.zze(this.zza.zzq());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzyw) this.zza).zze();
                do {
                    list.add(Boolean.valueOf(this.zza.zzq()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Boolean.valueOf(this.zza.zzq()));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzw(List<zzyu> list) throws IOException {
        int zzf;
        if ((this.zzb & 7) == 2) {
            do {
                list.add(zzp());
                if (!this.zza.zzp()) {
                    zzf = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf == this.zzb);
            this.zzd = zzf;
            return;
        }
        throw zzaae.zza();
    }

    public final void zzx(List<Double> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzzg) {
            zzzg zzzg = (zzzg) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int zze = ((zzyw) this.zza).zze();
                    zzU(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzzg.zze(Double.longBitsToDouble(((zzyw) this.zza).zzg()));
                    } while (this.zza.zzb() < zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzzg.zze(Double.longBitsToDouble(((zzyw) this.zza).zzg()));
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int zze2 = ((zzyw) this.zza).zze();
                zzU(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Double.valueOf(Double.longBitsToDouble(((zzyw) this.zza).zzg())));
                } while (this.zza.zzb() < zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Double.valueOf(Double.longBitsToDouble(((zzyw) this.zza).zzg())));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzy(List<Integer> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzzx) {
            zzzx zzzx = (zzzx) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzb2 = this.zza.zzb() + ((zzyw) this.zza).zze();
                    do {
                        zzzx.zzf(((zzyw) this.zza).zze());
                    } while (this.zza.zzb() < zzb2);
                    zzR(zzb2);
                    return;
                default:
                    throw zzaae.zza();
            }
            do {
                zzzx.zzf(((zzyw) this.zza).zze());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzb3 = this.zza.zzb() + ((zzyw) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzyw) this.zza).zze()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
                return;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Integer.valueOf(((zzyw) this.zza).zze()));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzz(List<Integer> list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzzx) {
            zzzx zzzx = (zzzx) list;
            switch (this.zzb & 7) {
                case 2:
                    int zze = ((zzyw) this.zza).zze();
                    zzT(zze);
                    int zzb2 = this.zza.zzb() + zze;
                    do {
                        zzzx.zzf(((zzyw) this.zza).zzd());
                    } while (this.zza.zzb() < zzb2);
                    return;
                case 5:
                    break;
                default:
                    throw zzaae.zza();
            }
            do {
                zzzx.zzf(((zzyw) this.zza).zzd());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int zze2 = ((zzyw) this.zza).zze();
                zzT(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Integer.valueOf(((zzyw) this.zza).zzd()));
                } while (this.zza.zzb() < zzb3);
                return;
            case 5:
                break;
            default:
                throw zzaae.zza();
        }
        do {
            list.add(Integer.valueOf(((zzyw) this.zza).zzd()));
            if (!this.zza.zzp()) {
                zzf = this.zza.zzf();
            } else {
                return;
            }
        } while (zzf == this.zzb);
        this.zzd = zzf;
    }

    public final void zzK(List<String> list, boolean z) throws IOException {
        int zzf;
        int zzf2;
        if ((this.zzb & 7) != 2) {
            throw zzaae.zza();
        } else if ((list instanceof zzaaj) && !z) {
            zzaaj zzaaj = (zzaaj) list;
            do {
                zzaaj.zzi(zzp());
                if (!this.zza.zzp()) {
                    zzf2 = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
        } else {
            do {
                list.add(z ? zzu() : zzt());
                if (!this.zza.zzp()) {
                    zzf = this.zza.zzf();
                } else {
                    return;
                }
            } while (zzf == this.zzb);
            this.zzd = zzf;
        }
    }
}
