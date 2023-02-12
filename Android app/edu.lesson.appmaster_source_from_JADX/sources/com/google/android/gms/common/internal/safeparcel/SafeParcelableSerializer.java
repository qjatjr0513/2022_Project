package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.common.zzag;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class SafeParcelableSerializer {
    private SafeParcelableSerializer() {
    }

    public static <T extends SafeParcelable> T deserializeFromBytes(byte[] serializedBytes, Parcelable.Creator<T> safeParcelableCreator) {
        Preconditions.checkNotNull(safeParcelableCreator);
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(serializedBytes, 0, serializedBytes.length);
        obtain.setDataPosition(0);
        T t = (SafeParcelable) safeParcelableCreator.createFromParcel(obtain);
        obtain.recycle();
        return t;
    }

    public static <T extends SafeParcelable> T deserializeFromIntentExtra(Intent intent, String extra, Parcelable.Creator<T> safeParcelableCreator) {
        byte[] byteArrayExtra = intent.getByteArrayExtra(extra);
        if (byteArrayExtra == null) {
            return null;
        }
        return deserializeFromBytes(byteArrayExtra, safeParcelableCreator);
    }

    public static <T extends SafeParcelable> T deserializeFromString(String serializedString, Parcelable.Creator<T> safeParcelableCreator) {
        return deserializeFromBytes(Base64Utils.decodeUrlSafe(serializedString), safeParcelableCreator);
    }

    @Deprecated
    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromBundle(Bundle bundle, String key, Parcelable.Creator<T> safeParcelableCreator) {
        ArrayList arrayList = (ArrayList) bundle.getSerializable(key);
        if (arrayList == null) {
            return null;
        }
        ArrayList<T> arrayList2 = new ArrayList<>(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList2.add(deserializeFromBytes((byte[]) arrayList.get(i), safeParcelableCreator));
        }
        return arrayList2;
    }

    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromBundleSafe(Bundle bundle, String key, Parcelable.Creator<T> safeParcelableCreator) {
        return deserializeIterableFromBytes(bundle.getByteArray(key), safeParcelableCreator);
    }

    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromBytes(byte[] serializedBytes, Parcelable.Creator<T> safeParcelableCreator) {
        if (serializedBytes == null) {
            return null;
        }
        int length = serializedBytes.length;
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(serializedBytes, 0, length);
        obtain.setDataPosition(0);
        try {
            ArrayList<T> arrayList = new ArrayList<>();
            obtain.readTypedList(arrayList, safeParcelableCreator);
            return arrayList;
        } finally {
            obtain.recycle();
        }
    }

    @Deprecated
    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromIntentExtra(Intent intent, String extra, Parcelable.Creator<T> safeParcelableCreator) {
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra(extra);
        if (arrayList == null) {
            return null;
        }
        ArrayList<T> arrayList2 = new ArrayList<>(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList2.add(deserializeFromBytes((byte[]) arrayList.get(i), safeParcelableCreator));
        }
        return arrayList2;
    }

    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromIntentExtraSafe(Intent intent, String extra, Parcelable.Creator<T> safeParcelableCreator) {
        return deserializeIterableFromBytes(intent.getByteArrayExtra(extra), safeParcelableCreator);
    }

    @Deprecated
    public static <T extends SafeParcelable> void serializeIterableToBundle(Iterable<T> safeParcelables, Bundle bundle, String key) {
        ArrayList arrayList = new ArrayList();
        for (T serializeToBytes : safeParcelables) {
            arrayList.add(serializeToBytes(serializeToBytes));
        }
        bundle.putSerializable(key, arrayList);
    }

    public static <T extends SafeParcelable> void serializeIterableToBundleSafe(Iterable<T> safeParcelables, Bundle bundle, String key) {
        bundle.putByteArray(key, zza(safeParcelables));
    }

    @Deprecated
    public static <T extends SafeParcelable> void serializeIterableToIntentExtra(Iterable<T> safeParcelables, Intent intent, String extra) {
        ArrayList arrayList = new ArrayList();
        for (T serializeToBytes : safeParcelables) {
            arrayList.add(serializeToBytes(serializeToBytes));
        }
        intent.putExtra(extra, arrayList);
    }

    public static <T extends SafeParcelable> void serializeIterableToIntentExtraSafe(Iterable<T> safeParcelables, Intent intent, String extra) {
        intent.putExtra(extra, zza(safeParcelables));
    }

    public static <T extends SafeParcelable> byte[] serializeToBytes(T safeParcelable) {
        Parcel obtain = Parcel.obtain();
        safeParcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    public static <T extends SafeParcelable> void serializeToIntentExtra(T safeParcelable, Intent intent, String extra) {
        intent.putExtra(extra, serializeToBytes(safeParcelable));
    }

    public static <T extends SafeParcelable> String serializeToString(T safeParcelable) {
        return Base64Utils.encodeUrlSafe(serializeToBytes(safeParcelable));
    }

    private static <T extends SafeParcelable> byte[] zza(Iterable<T> iterable) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeTypedList(zzag.zzj(iterable));
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }
}
