package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzao extends zzq<Double> implements zzbh<Double>, zzcw, RandomAccess {
    private static final zzao zzex;
    private int size;
    private double[] zzey;

    zzao() {
        this(new double[10], 0);
    }

    private zzao(double[] dArr, int i) {
        this.zzey = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzac();
        if (i2 >= i) {
            double[] dArr = this.zzey;
            System.arraycopy(dArr, i2, dArr, i, this.size - i2);
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
        if (!(obj instanceof zzao)) {
            return super.equals(obj);
        }
        zzao zzao = (zzao) obj;
        if (this.size != zzao.size) {
            return false;
        }
        double[] dArr = zzao.zzey;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzey[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbd.zzl(Double.doubleToLongBits(this.zzey[i2]));
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void zzd(double d) {
        zzd(this.size, d);
    }

    private final void zzd(int i, double d) {
        int i2;
        zzac();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        double[] dArr = this.zzey;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zzey, i, dArr2, i + 1, this.size - i);
            this.zzey = dArr2;
        }
        this.zzey[i] = d;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzac();
        zzbd.checkNotNull(collection);
        if (!(collection instanceof zzao)) {
            return super.addAll(collection);
        }
        zzao zzao = (zzao) collection;
        int i = zzao.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.zzey;
            if (i3 > dArr.length) {
                this.zzey = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(zzao.zzey, 0, this.zzey, this.size, zzao.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzac();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzey[i]))) {
                double[] dArr = this.zzey;
                System.arraycopy(dArr, i + 1, dArr, i, (this.size - i) - 1);
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
        double doubleValue = ((Double) obj).doubleValue();
        zzac();
        zzf(i);
        double[] dArr = this.zzey;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzac();
        zzf(i);
        double[] dArr = this.zzey;
        double d = dArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzd(i, ((Double) obj).doubleValue());
    }

    public final /* synthetic */ zzbh zzh(int i) {
        if (i >= this.size) {
            return new zzao(Arrays.copyOf(this.zzey, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzf(i);
        return Double.valueOf(this.zzey[i]);
    }

    static {
        zzao zzao = new zzao(new double[0], 0);
        zzex = zzao;
        zzao.zzab();
    }
}
