package com.google.android.gms.common.internal.safeparcel;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import androidx.core.internal.view.SupportMenu;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public class SafeParcelReader {

    /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
    public static class ParseException extends RuntimeException {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ParseException(java.lang.String r4, android.os.Parcel r5) {
            /*
                r3 = this;
                int r0 = r5.dataPosition()
                int r5 = r5.dataSize()
                java.lang.String r1 = java.lang.String.valueOf(r4)
                int r1 = r1.length()
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                int r1 = r1 + 41
                r2.<init>(r1)
                r2.append(r4)
                java.lang.String r4 = " Parcel: pos="
                r2.append(r4)
                r2.append(r0)
                java.lang.String r4 = " size="
                r2.append(r4)
                r2.append(r5)
                java.lang.String r4 = r2.toString()
                r3.<init>(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException.<init>(java.lang.String, android.os.Parcel):void");
        }
    }

    private SafeParcelReader() {
    }

    public static BigDecimal createBigDecimal(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        byte[] createByteArray = p.createByteArray();
        int readInt = p.readInt();
        p.setDataPosition(dataPosition + header2);
        return new BigDecimal(new BigInteger(createByteArray), readInt);
    }

    public static BigDecimal[] createBigDecimalArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        int readInt = p.readInt();
        BigDecimal[] bigDecimalArr = new BigDecimal[readInt];
        for (int i = 0; i < readInt; i++) {
            byte[] createByteArray = p.createByteArray();
            bigDecimalArr[i] = new BigDecimal(new BigInteger(createByteArray), p.readInt());
        }
        p.setDataPosition(dataPosition + header2);
        return bigDecimalArr;
    }

    public static BigInteger createBigInteger(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        byte[] createByteArray = p.createByteArray();
        p.setDataPosition(dataPosition + header2);
        return new BigInteger(createByteArray);
    }

    public static BigInteger[] createBigIntegerArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        int readInt = p.readInt();
        BigInteger[] bigIntegerArr = new BigInteger[readInt];
        for (int i = 0; i < readInt; i++) {
            bigIntegerArr[i] = new BigInteger(p.createByteArray());
        }
        p.setDataPosition(dataPosition + header2);
        return bigIntegerArr;
    }

    public static boolean[] createBooleanArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        boolean[] createBooleanArray = p.createBooleanArray();
        p.setDataPosition(dataPosition + header2);
        return createBooleanArray;
    }

    public static ArrayList<Boolean> createBooleanList(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        ArrayList<Boolean> arrayList = new ArrayList<>();
        int readInt = p.readInt();
        for (int i = 0; i < readInt; i++) {
            arrayList.add(Boolean.valueOf(p.readInt() != 0));
        }
        p.setDataPosition(dataPosition + header2);
        return arrayList;
    }

    public static Bundle createBundle(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        Bundle readBundle = p.readBundle();
        p.setDataPosition(dataPosition + header2);
        return readBundle;
    }

    public static byte[] createByteArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        byte[] createByteArray = p.createByteArray();
        p.setDataPosition(dataPosition + header2);
        return createByteArray;
    }

    public static byte[][] createByteArrayArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        int readInt = p.readInt();
        byte[][] bArr = new byte[readInt][];
        for (int i = 0; i < readInt; i++) {
            bArr[i] = p.createByteArray();
        }
        p.setDataPosition(dataPosition + header2);
        return bArr;
    }

    public static SparseArray<byte[]> createByteArraySparseArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        int readInt = p.readInt();
        SparseArray<byte[]> sparseArray = new SparseArray<>(readInt);
        for (int i = 0; i < readInt; i++) {
            sparseArray.append(p.readInt(), p.createByteArray());
        }
        p.setDataPosition(dataPosition + header2);
        return sparseArray;
    }

    public static char[] createCharArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        char[] createCharArray = p.createCharArray();
        p.setDataPosition(dataPosition + header2);
        return createCharArray;
    }

    public static double[] createDoubleArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        double[] createDoubleArray = p.createDoubleArray();
        p.setDataPosition(dataPosition + header2);
        return createDoubleArray;
    }

    public static ArrayList<Double> createDoubleList(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        ArrayList<Double> arrayList = new ArrayList<>();
        int readInt = p.readInt();
        for (int i = 0; i < readInt; i++) {
            arrayList.add(Double.valueOf(p.readDouble()));
        }
        p.setDataPosition(dataPosition + header2);
        return arrayList;
    }

    public static SparseArray<Double> createDoubleSparseArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        SparseArray<Double> sparseArray = new SparseArray<>();
        int readInt = p.readInt();
        for (int i = 0; i < readInt; i++) {
            sparseArray.append(p.readInt(), Double.valueOf(p.readDouble()));
        }
        p.setDataPosition(dataPosition + header2);
        return sparseArray;
    }

    public static float[] createFloatArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        float[] createFloatArray = p.createFloatArray();
        p.setDataPosition(dataPosition + header2);
        return createFloatArray;
    }

    public static ArrayList<Float> createFloatList(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        ArrayList<Float> arrayList = new ArrayList<>();
        int readInt = p.readInt();
        for (int i = 0; i < readInt; i++) {
            arrayList.add(Float.valueOf(p.readFloat()));
        }
        p.setDataPosition(dataPosition + header2);
        return arrayList;
    }

    public static SparseArray<Float> createFloatSparseArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        SparseArray<Float> sparseArray = new SparseArray<>();
        int readInt = p.readInt();
        for (int i = 0; i < readInt; i++) {
            sparseArray.append(p.readInt(), Float.valueOf(p.readFloat()));
        }
        p.setDataPosition(dataPosition + header2);
        return sparseArray;
    }

    public static IBinder[] createIBinderArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        IBinder[] createBinderArray = p.createBinderArray();
        p.setDataPosition(dataPosition + header2);
        return createBinderArray;
    }

    public static ArrayList<IBinder> createIBinderList(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        ArrayList<IBinder> createBinderArrayList = p.createBinderArrayList();
        p.setDataPosition(dataPosition + header2);
        return createBinderArrayList;
    }

    public static SparseArray<IBinder> createIBinderSparseArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        int readInt = p.readInt();
        SparseArray<IBinder> sparseArray = new SparseArray<>(readInt);
        for (int i = 0; i < readInt; i++) {
            sparseArray.append(p.readInt(), p.readStrongBinder());
        }
        p.setDataPosition(dataPosition + header2);
        return sparseArray;
    }

    public static int[] createIntArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        int[] createIntArray = p.createIntArray();
        p.setDataPosition(dataPosition + header2);
        return createIntArray;
    }

    public static ArrayList<Integer> createIntegerList(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        int readInt = p.readInt();
        for (int i = 0; i < readInt; i++) {
            arrayList.add(Integer.valueOf(p.readInt()));
        }
        p.setDataPosition(dataPosition + header2);
        return arrayList;
    }

    public static long[] createLongArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        long[] createLongArray = p.createLongArray();
        p.setDataPosition(dataPosition + header2);
        return createLongArray;
    }

    public static ArrayList<Long> createLongList(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        ArrayList<Long> arrayList = new ArrayList<>();
        int readInt = p.readInt();
        for (int i = 0; i < readInt; i++) {
            arrayList.add(Long.valueOf(p.readLong()));
        }
        p.setDataPosition(dataPosition + header2);
        return arrayList;
    }

    public static Parcel createParcel(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.appendFrom(p, dataPosition, header2);
        p.setDataPosition(dataPosition + header2);
        return obtain;
    }

    public static Parcel[] createParcelArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        int readInt = p.readInt();
        Parcel[] parcelArr = new Parcel[readInt];
        for (int i = 0; i < readInt; i++) {
            int readInt2 = p.readInt();
            if (readInt2 != 0) {
                int dataPosition2 = p.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(p, dataPosition2, readInt2);
                parcelArr[i] = obtain;
                p.setDataPosition(dataPosition2 + readInt2);
            } else {
                parcelArr[i] = null;
            }
        }
        p.setDataPosition(dataPosition + header2);
        return parcelArr;
    }

    public static ArrayList<Parcel> createParcelList(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        int readInt = p.readInt();
        ArrayList<Parcel> arrayList = new ArrayList<>();
        for (int i = 0; i < readInt; i++) {
            int readInt2 = p.readInt();
            if (readInt2 != 0) {
                int dataPosition2 = p.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(p, dataPosition2, readInt2);
                arrayList.add(obtain);
                p.setDataPosition(dataPosition2 + readInt2);
            } else {
                arrayList.add((Object) null);
            }
        }
        p.setDataPosition(dataPosition + header2);
        return arrayList;
    }

    public static SparseArray<Parcel> createParcelSparseArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        int readInt = p.readInt();
        SparseArray<Parcel> sparseArray = new SparseArray<>();
        for (int i = 0; i < readInt; i++) {
            int readInt2 = p.readInt();
            int readInt3 = p.readInt();
            if (readInt3 != 0) {
                int dataPosition2 = p.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(p, dataPosition2, readInt3);
                sparseArray.append(readInt2, obtain);
                p.setDataPosition(dataPosition2 + readInt3);
            } else {
                sparseArray.append(readInt2, (Object) null);
            }
        }
        p.setDataPosition(dataPosition + header2);
        return sparseArray;
    }

    public static <T extends Parcelable> T createParcelable(Parcel p, int header, Parcelable.Creator<T> creator) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        T t = (Parcelable) creator.createFromParcel(p);
        p.setDataPosition(dataPosition + header2);
        return t;
    }

    public static SparseBooleanArray createSparseBooleanArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        SparseBooleanArray readSparseBooleanArray = p.readSparseBooleanArray();
        p.setDataPosition(dataPosition + header2);
        return readSparseBooleanArray;
    }

    public static SparseIntArray createSparseIntArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        int readInt = p.readInt();
        for (int i = 0; i < readInt; i++) {
            sparseIntArray.append(p.readInt(), p.readInt());
        }
        p.setDataPosition(dataPosition + header2);
        return sparseIntArray;
    }

    public static SparseLongArray createSparseLongArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        SparseLongArray sparseLongArray = new SparseLongArray();
        int readInt = p.readInt();
        for (int i = 0; i < readInt; i++) {
            sparseLongArray.append(p.readInt(), p.readLong());
        }
        p.setDataPosition(dataPosition + header2);
        return sparseLongArray;
    }

    public static String createString(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        String readString = p.readString();
        p.setDataPosition(dataPosition + header2);
        return readString;
    }

    public static String[] createStringArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        String[] createStringArray = p.createStringArray();
        p.setDataPosition(dataPosition + header2);
        return createStringArray;
    }

    public static ArrayList<String> createStringList(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = p.createStringArrayList();
        p.setDataPosition(dataPosition + header2);
        return createStringArrayList;
    }

    public static SparseArray<String> createStringSparseArray(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        SparseArray<String> sparseArray = new SparseArray<>();
        int readInt = p.readInt();
        for (int i = 0; i < readInt; i++) {
            sparseArray.append(p.readInt(), p.readString());
        }
        p.setDataPosition(dataPosition + header2);
        return sparseArray;
    }

    public static <T> T[] createTypedArray(Parcel p, int header, Parcelable.Creator<T> c) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        T[] createTypedArray = p.createTypedArray(c);
        p.setDataPosition(dataPosition + header2);
        return createTypedArray;
    }

    public static <T> ArrayList<T> createTypedList(Parcel p, int header, Parcelable.Creator<T> c) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        ArrayList<T> createTypedArrayList = p.createTypedArrayList(c);
        p.setDataPosition(dataPosition + header2);
        return createTypedArrayList;
    }

    public static <T> SparseArray<T> createTypedSparseArray(Parcel p, int header, Parcelable.Creator<T> c) {
        T t;
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        int readInt = p.readInt();
        SparseArray<T> sparseArray = new SparseArray<>();
        for (int i = 0; i < readInt; i++) {
            int readInt2 = p.readInt();
            if (p.readInt() != 0) {
                t = c.createFromParcel(p);
            } else {
                t = null;
            }
            sparseArray.append(readInt2, t);
        }
        p.setDataPosition(dataPosition + header2);
        return sparseArray;
    }

    public static void ensureAtEnd(Parcel parcel, int end) {
        if (parcel.dataPosition() != end) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Overread allowed size end=");
            sb.append(end);
            throw new ParseException(sb.toString(), parcel);
        }
    }

    public static int getFieldId(int i) {
        return (char) i;
    }

    public static boolean readBoolean(Parcel p, int header) {
        zzb(p, header, 4);
        return p.readInt() != 0;
    }

    public static Boolean readBooleanObject(Parcel p, int header) {
        int readSize = readSize(p, header);
        if (readSize == 0) {
            return null;
        }
        zza(p, header, readSize, 4);
        return Boolean.valueOf(p.readInt() != 0);
    }

    public static byte readByte(Parcel p, int header) {
        zzb(p, header, 4);
        return (byte) p.readInt();
    }

    public static char readChar(Parcel p, int header) {
        zzb(p, header, 4);
        return (char) p.readInt();
    }

    public static double readDouble(Parcel p, int header) {
        zzb(p, header, 8);
        return p.readDouble();
    }

    public static Double readDoubleObject(Parcel p, int header) {
        int readSize = readSize(p, header);
        if (readSize == 0) {
            return null;
        }
        zza(p, header, readSize, 8);
        return Double.valueOf(p.readDouble());
    }

    public static float readFloat(Parcel p, int header) {
        zzb(p, header, 4);
        return p.readFloat();
    }

    public static Float readFloatObject(Parcel p, int header) {
        int readSize = readSize(p, header);
        if (readSize == 0) {
            return null;
        }
        zza(p, header, readSize, 4);
        return Float.valueOf(p.readFloat());
    }

    public static int readHeader(Parcel p) {
        return p.readInt();
    }

    public static IBinder readIBinder(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        IBinder readStrongBinder = p.readStrongBinder();
        p.setDataPosition(dataPosition + header2);
        return readStrongBinder;
    }

    public static int readInt(Parcel p, int header) {
        zzb(p, header, 4);
        return p.readInt();
    }

    public static Integer readIntegerObject(Parcel p, int header) {
        int readSize = readSize(p, header);
        if (readSize == 0) {
            return null;
        }
        zza(p, header, readSize, 4);
        return Integer.valueOf(p.readInt());
    }

    public static void readList(Parcel p, int header, List list, ClassLoader loader) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 != 0) {
            p.readList(list, loader);
            p.setDataPosition(dataPosition + header2);
        }
    }

    public static long readLong(Parcel p, int header) {
        zzb(p, header, 8);
        return p.readLong();
    }

    public static Long readLongObject(Parcel p, int header) {
        int readSize = readSize(p, header);
        if (readSize == 0) {
            return null;
        }
        zza(p, header, readSize, 8);
        return Long.valueOf(p.readLong());
    }

    public static PendingIntent readPendingIntent(Parcel p, int header) {
        int header2 = readSize(p, header);
        int dataPosition = p.dataPosition();
        if (header2 == 0) {
            return null;
        }
        PendingIntent readPendingIntentOrNullFromParcel = PendingIntent.readPendingIntentOrNullFromParcel(p);
        p.setDataPosition(dataPosition + header2);
        return readPendingIntentOrNullFromParcel;
    }

    public static short readShort(Parcel p, int header) {
        zzb(p, header, 4);
        return (short) p.readInt();
    }

    public static int readSize(Parcel p, int header) {
        return (header & SupportMenu.CATEGORY_MASK) != -65536 ? (char) (header >> 16) : p.readInt();
    }

    public static void skipUnknownField(Parcel p, int header) {
        p.setDataPosition(p.dataPosition() + readSize(p, header));
    }

    public static int validateObjectHeader(Parcel p) {
        int readHeader = readHeader(p);
        int readSize = readSize(p, readHeader);
        int dataPosition = p.dataPosition();
        if (getFieldId(readHeader) != 20293) {
            String valueOf = String.valueOf(Integer.toHexString(readHeader));
            throw new ParseException(valueOf.length() != 0 ? "Expected object header. Got 0x".concat(valueOf) : new String("Expected object header. Got 0x"), p);
        }
        int i = readSize + dataPosition;
        if (i >= dataPosition && i <= p.dataSize()) {
            return i;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("Size read is invalid start=");
        sb.append(dataPosition);
        sb.append(" end=");
        sb.append(i);
        throw new ParseException(sb.toString(), p);
    }

    private static void zza(Parcel parcel, int i, int i2, int i3) {
        if (i2 != i3) {
            String hexString = Integer.toHexString(i2);
            StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 46);
            sb.append("Expected size ");
            sb.append(i3);
            sb.append(" got ");
            sb.append(i2);
            sb.append(" (0x");
            sb.append(hexString);
            sb.append(")");
            throw new ParseException(sb.toString(), parcel);
        }
    }

    private static void zzb(Parcel parcel, int i, int i2) {
        int readSize = readSize(parcel, i);
        if (readSize != i2) {
            String hexString = Integer.toHexString(readSize);
            StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 46);
            sb.append("Expected size ");
            sb.append(i2);
            sb.append(" got ");
            sb.append(readSize);
            sb.append(" (0x");
            sb.append(hexString);
            sb.append(")");
            throw new ParseException(sb.toString(), parcel);
        }
    }
}
