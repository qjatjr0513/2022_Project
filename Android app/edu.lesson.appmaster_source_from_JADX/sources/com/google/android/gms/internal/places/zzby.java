package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzby extends zzq<Long> implements zzbh<Long>, zzcw, RandomAccess {
    private static final zzby zzkg;
    private int size;
    private long[] zzkh;

    zzby() {
        this(new long[10], 0);
    }

    private zzby(long[] jArr, int i) {
        this.zzkh = jArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzac();
        if (i2 >= i) {
            long[] jArr = this.zzkh;
            System.arraycopy(jArr, i2, jArr, i, this.size - i2);
            this.size -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzby)) {
            return super.equals(obj);
        }
        zzby zzby = (zzby) obj;
        if (this.size != zzby.size) {
            return false;
        }
        long[] jArr = zzby.zzkh;
        for (int i = 0; i < this.size; i++) {
            if (this.zzkh[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbd.zzl(this.zzkh[i2]);
        }
        return i;
    }

    public final long getLong(int i) {
        zzf(i);
        return this.zzkh[i];
    }

    public final int size() {
        return this.size;
    }

    public final void zzm(long j) {
        zzl(this.size, j);
    }

    private final void zzl(int i, long j) {
        int i2;
        zzac();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        long[] jArr = this.zzkh;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.zzkh, i, jArr2, i + 1, this.size - i);
            this.zzkh = jArr2;
        }
        this.zzkh[i] = j;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzac();
        zzbd.checkNotNull(collection);
        if (!(collection instanceof zzby)) {
            return super.addAll(collection);
        }
        zzby zzby = (zzby) collection;
        int i = zzby.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zzkh;
            if (i3 > jArr.length) {
                this.zzkh = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzby.zzkh, 0, this.zzkh, this.size, zzby.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzac();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzkh[i]))) {
                long[] jArr = this.zzkh;
                System.arraycopy(jArr, i + 1, jArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzf(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
    }

    private final String zzg(int i) {
        return new StringBuilder(35).append("Index:").append(i).append(", Size:").append(this.size).toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzac();
        zzf(i);
        long[] jArr = this.zzkh;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object remove(int i) {
        zzac();
        zzf(i);
        long[] jArr = this.zzkh;
        long j = jArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzl(i, ((Long) obj).longValue());
    }

    public final /* synthetic */ zzbh zzh(int i) {
        if (i >= this.size) {
            return new zzby(Arrays.copyOf(this.zzkh, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzby zzby = new zzby(new long[0], 0);
        zzkg = zzby;
        zzby.zzab();
    }
}
