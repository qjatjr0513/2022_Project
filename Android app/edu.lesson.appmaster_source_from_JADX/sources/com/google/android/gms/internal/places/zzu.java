package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzu extends zzq<Boolean> implements zzbh<Boolean>, zzcw, RandomAccess {
    private static final zzu zzed;
    private int size;
    private boolean[] zzee;

    zzu() {
        this(new boolean[10], 0);
    }

    private zzu(boolean[] zArr, int i) {
        this.zzee = zArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzac();
        if (i2 >= i) {
            boolean[] zArr = this.zzee;
            System.arraycopy(zArr, i2, zArr, i, this.size - i2);
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
        if (!(obj instanceof zzu)) {
            return super.equals(obj);
        }
        zzu zzu = (zzu) obj;
        if (this.size != zzu.size) {
            return false;
        }
        boolean[] zArr = zzu.zzee;
        for (int i = 0; i < this.size; i++) {
            if (this.zzee[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbd.zze(this.zzee[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void addBoolean(boolean z) {
        zzb(this.size, z);
    }

    private final void zzb(int i, boolean z) {
        int i2;
        zzac();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        boolean[] zArr = this.zzee;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.zzee, i, zArr2, i + 1, this.size - i);
            this.zzee = zArr2;
        }
        this.zzee[i] = z;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzac();
        zzbd.checkNotNull(collection);
        if (!(collection instanceof zzu)) {
            return super.addAll(collection);
        }
        zzu zzu = (zzu) collection;
        int i = zzu.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.zzee;
            if (i3 > zArr.length) {
                this.zzee = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(zzu.zzee, 0, this.zzee, this.size, zzu.size);
            this.size = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean remove(Object obj) {
        zzac();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzee[i]))) {
                boolean[] zArr = this.zzee;
                System.arraycopy(zArr, i + 1, zArr, i, (this.size - i) - 1);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzac();
        zzf(i);
        boolean[] zArr = this.zzee;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ Object remove(int i) {
        zzac();
        zzf(i);
        boolean[] zArr = this.zzee;
        boolean z = zArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzb(i, ((Boolean) obj).booleanValue());
    }

    public final /* synthetic */ zzbh zzh(int i) {
        if (i >= this.size) {
            return new zzu(Arrays.copyOf(this.zzee, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzf(i);
        return Boolean.valueOf(this.zzee[i]);
    }

    static {
        zzu zzu = new zzu(new boolean[0], 0);
        zzed = zzu;
        zzu.zzab();
    }
}
