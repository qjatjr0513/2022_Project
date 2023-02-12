package com.google.firebase.firestore.util;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.cloud.datastore.core.number.NumberComparisonHelper;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.protobuf.ByteString;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import p004io.grpc.Status;
import p004io.grpc.StatusException;
import p004io.grpc.StatusRuntimeException;

public class Util {
    private static final String AUTO_ID_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int AUTO_ID_LENGTH = 20;
    private static final Continuation<Void, Void> VOID_ERROR_TRANSFORMER = Util$$ExternalSyntheticLambda0.INSTANCE;
    private static final Random rand = new SecureRandom();

    public static String autoId() {
        StringBuilder builder = new StringBuilder();
        int maxRandom = AUTO_ID_ALPHABET.length();
        for (int i = 0; i < 20; i++) {
            builder.append(AUTO_ID_ALPHABET.charAt(rand.nextInt(maxRandom)));
        }
        return builder.toString();
    }

    public static int compareBooleans(boolean b1, boolean b2) {
        if (b1 == b2) {
            return 0;
        }
        if (b1) {
            return 1;
        }
        return -1;
    }

    public static int compareIntegers(int i1, int i2) {
        if (i1 < i2) {
            return -1;
        }
        if (i1 > i2) {
            return 1;
        }
        return 0;
    }

    public static int compareLongs(long i1, long i2) {
        return NumberComparisonHelper.compareLongs(i1, i2);
    }

    public static int compareDoubles(double i1, double i2) {
        return NumberComparisonHelper.firestoreCompareDoubles(i1, i2);
    }

    public static int compareMixed(double doubleValue, long longValue) {
        return NumberComparisonHelper.firestoreCompareDoubleWithLong(doubleValue, longValue);
    }

    public static <T extends Comparable<T>> Comparator<T> comparator() {
        return Util$$ExternalSyntheticLambda5.INSTANCE;
    }

    public static FirebaseFirestoreException exceptionFromStatus(Status error) {
        StatusException statusException = error.asException();
        return new FirebaseFirestoreException(statusException.getMessage(), FirebaseFirestoreException.Code.fromValue(error.getCode().value()), statusException);
    }

    private static Exception convertStatusException(Exception e) {
        if (e instanceof StatusException) {
            return exceptionFromStatus(((StatusException) e).getStatus());
        }
        if (e instanceof StatusRuntimeException) {
            return exceptionFromStatus(((StatusRuntimeException) e).getStatus());
        }
        return e;
    }

    public static Exception convertThrowableToException(Throwable t) {
        if (t instanceof Exception) {
            return convertStatusException((Exception) t);
        }
        return new Exception(t);
    }

    static /* synthetic */ Void lambda$static$0(Task task) throws Exception {
        if (task.isSuccessful()) {
            return (Void) task.getResult();
        }
        Exception e = convertStatusException(task.getException());
        if (e instanceof FirebaseFirestoreException) {
            throw e;
        }
        throw new FirebaseFirestoreException(e.getMessage(), FirebaseFirestoreException.Code.UNKNOWN, e);
    }

    public static Continuation<Void, Void> voidErrorTransformer() {
        return VOID_ERROR_TRANSFORMER;
    }

    public static List<Object> collectUpdateArguments(int fieldPathOffset, Object field, Object val, Object... fieldsAndValues) {
        if (fieldsAndValues.length % 2 != 1) {
            List<Object> argumentList = new ArrayList<>();
            argumentList.add(field);
            argumentList.add(val);
            Collections.addAll(argumentList, fieldsAndValues);
            int i = 0;
            while (i < argumentList.size()) {
                Object fieldPath = argumentList.get(i);
                if ((fieldPath instanceof String) || (fieldPath instanceof FieldPath)) {
                    i += 2;
                } else {
                    throw new IllegalArgumentException("Excepted field name at argument position " + (i + fieldPathOffset + 1) + " but got " + fieldPath + " in call to update.  The arguments to update should alternate between field names and values");
                }
            }
            return argumentList;
        }
        throw new IllegalArgumentException("Missing value in call to update().  There must be an even number of arguments that alternate between field names and values");
    }

    public static String toDebugString(ByteString bytes) {
        int size = bytes.size();
        StringBuilder result = new StringBuilder(size * 2);
        for (int i = 0; i < size; i++) {
            int value = bytes.byteAt(i) & 255;
            result.append(Character.forDigit(value >>> 4, 16));
            result.append(Character.forDigit(value & 15, 16));
        }
        return result.toString();
    }

    public static String typeName(Object obj) {
        return obj == null ? "null" : obj.getClass().getName();
    }

    public static void crashMainThread(RuntimeException exception) {
        new Handler(Looper.getMainLooper()).post(new Util$$ExternalSyntheticLambda2(exception));
    }

    static /* synthetic */ void lambda$crashMainThread$1(RuntimeException exception) {
        throw exception;
    }

    public static int compareByteArrays(byte[] left, byte[] right) {
        int size = Math.min(left.length, right.length);
        for (int i = 0; i < size; i++) {
            int thisByte = left[i] & 255;
            int otherByte = right[i] & 255;
            if (thisByte < otherByte) {
                return -1;
            }
            if (thisByte > otherByte) {
                return 1;
            }
        }
        return compareIntegers(left.length, right.length);
    }

    public static int compareByteStrings(ByteString left, ByteString right) {
        int size = Math.min(left.size(), right.size());
        for (int i = 0; i < size; i++) {
            int thisByte = left.byteAt(i) & 255;
            int otherByte = right.byteAt(i) & 255;
            if (thisByte < otherByte) {
                return -1;
            }
            if (thisByte > otherByte) {
                return 1;
            }
        }
        return compareIntegers(left.size(), right.size());
    }

    public static StringBuilder repeatSequence(CharSequence sequence, int count, CharSequence delimiter) {
        StringBuilder sb = new StringBuilder();
        if (count != 0) {
            sb.append(sequence);
            for (int i = 1; i < count; i++) {
                sb.append(delimiter);
                sb.append(sequence);
            }
        }
        return sb;
    }

    public static <T> void diffCollections(Collection<T> before, Collection<T> after, Comparator<T> comparator, Consumer<T> onAdd, Consumer<T> onRemove) {
        List<T> beforeEntries = new ArrayList<>(before);
        Collections.sort(beforeEntries, comparator);
        List<T> afterEntries = new ArrayList<>(after);
        Collections.sort(afterEntries, comparator);
        diffCollections(beforeEntries.iterator(), afterEntries.iterator(), comparator, onAdd, onRemove);
    }

    public static <T extends Comparable<T>> void diffCollections(SortedSet<T> before, SortedSet<T> after, Consumer<T> onAdd, Consumer<T> onRemove) {
        diffCollections(before.iterator(), after.iterator(), before.comparator() != null ? before.comparator() : Util$$ExternalSyntheticLambda4.INSTANCE, onAdd, onRemove);
    }

    private static <T> void diffCollections(Iterator<T> beforeSorted, Iterator<T> afterSorted, Comparator<? super T> comparator, Consumer<T> onAdd, Consumer<T> onRemove) {
        T beforeValue = advanceIterator(beforeSorted);
        T afterValue = advanceIterator(afterSorted);
        while (true) {
            if (beforeValue != null || afterValue != null) {
                boolean added = false;
                boolean removed = false;
                if (beforeValue != null && afterValue != null) {
                    int cmp = comparator.compare(beforeValue, afterValue);
                    if (cmp < 0) {
                        removed = true;
                    } else if (cmp > 0) {
                        added = true;
                    }
                } else if (beforeValue != null) {
                    removed = true;
                } else {
                    added = true;
                }
                if (added) {
                    onAdd.accept(afterValue);
                    afterValue = advanceIterator(afterSorted);
                } else if (removed) {
                    onRemove.accept(beforeValue);
                    beforeValue = advanceIterator(beforeSorted);
                } else {
                    beforeValue = advanceIterator(beforeSorted);
                    afterValue = advanceIterator(afterSorted);
                }
            } else {
                return;
            }
        }
    }

    private static <T> T advanceIterator(Iterator<T> it) {
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static <K, V> Iterable<V> values(Iterable<Map.Entry<K, V>> map) {
        return new Util$$ExternalSyntheticLambda1(map);
    }

    public static <K, V> Map<K, V> firstNEntries(Map<K, V> data, int n, Comparator<V> comp) {
        if (data.size() <= n) {
            return data;
        }
        List<Map.Entry<K, V>> sortedValues = new ArrayList<>(data.entrySet());
        Collections.sort(sortedValues, new Util$$ExternalSyntheticLambda3(comp));
        Map<K, V> result = new HashMap<>();
        for (int i = 0; i < n; i++) {
            result.put(sortedValues.get(i).getKey(), sortedValues.get(i).getValue());
        }
        return result;
    }
}
