package p004io.grpc.internal;

import com.google.common.math.LongMath;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* renamed from: io.grpc.internal.JsonUtil */
public class JsonUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long DURATION_SECONDS_MAX = 315576000000L;
    private static final long DURATION_SECONDS_MIN = -315576000000L;
    private static final long NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);

    @Nullable
    public static List<?> getList(Map<String, ?> obj, String key) {
        if (key == null) {
            throw new AssertionError();
        } else if (!obj.containsKey(key)) {
            return null;
        } else {
            Object obj2 = obj.get(key);
            if (obj2 instanceof List) {
                return (List) obj2;
            }
            throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not List", new Object[]{obj2, key, obj}));
        }
    }

    @Nullable
    public static List<Map<String, ?>> getListOfObjects(Map<String, ?> obj, String key) {
        List<?> list = getList(obj, key);
        if (list == null) {
            return null;
        }
        return checkObjectList(list);
    }

    @Nullable
    public static List<String> getListOfStrings(Map<String, ?> obj, String key) {
        List<?> list = getList(obj, key);
        if (list == null) {
            return null;
        }
        return checkStringList(list);
    }

    @Nullable
    public static Map<String, ?> getObject(Map<String, ?> obj, String key) {
        if (key == null) {
            throw new AssertionError();
        } else if (!obj.containsKey(key)) {
            return null;
        } else {
            Object obj2 = obj.get(key);
            if (obj2 instanceof Map) {
                return (Map) obj2;
            }
            throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not object", new Object[]{obj2, key, obj}));
        }
    }

    @Nullable
    public static Double getNumberAsDouble(Map<String, ?> obj, String key) {
        if (key == null) {
            throw new AssertionError();
        } else if (!obj.containsKey(key)) {
            return null;
        } else {
            Object obj2 = obj.get(key);
            if (obj2 instanceof Double) {
                return (Double) obj2;
            }
            if (obj2 instanceof String) {
                try {
                    return Double.valueOf(Double.parseDouble((String) obj2));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not a double", new Object[]{obj2, key}));
                }
            } else {
                throw new IllegalArgumentException(String.format("value '%s' for key '%s' in '%s' is not a number", new Object[]{obj2, key, obj}));
            }
        }
    }

    @Nullable
    public static Integer getNumberAsInteger(Map<String, ?> obj, String key) {
        if (key == null) {
            throw new AssertionError();
        } else if (!obj.containsKey(key)) {
            return null;
        } else {
            Object obj2 = obj.get(key);
            if (obj2 instanceof Double) {
                Double d = (Double) obj2;
                int i = d.intValue();
                if (((double) i) == d.doubleValue()) {
                    return Integer.valueOf(i);
                }
                throw new ClassCastException("Number expected to be integer: " + d);
            } else if (obj2 instanceof String) {
                try {
                    return Integer.valueOf(Integer.parseInt((String) obj2));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not an integer", new Object[]{obj2, key}));
                }
            } else {
                throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not an integer", new Object[]{obj2, key}));
            }
        }
    }

    public static Long getNumberAsLong(Map<String, ?> obj, String key) {
        if (key == null) {
            throw new AssertionError();
        } else if (!obj.containsKey(key)) {
            return null;
        } else {
            Object obj2 = obj.get(key);
            if (obj2 instanceof Double) {
                Double d = (Double) obj2;
                long l = d.longValue();
                if (((double) l) == d.doubleValue()) {
                    return Long.valueOf(l);
                }
                throw new ClassCastException("Number expected to be long: " + d);
            } else if (obj2 instanceof String) {
                try {
                    return Long.valueOf(Long.parseLong((String) obj2));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not a long integer", new Object[]{obj2, key}));
                }
            } else {
                throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not a long integer", new Object[]{obj2, key}));
            }
        }
    }

    @Nullable
    public static String getString(Map<String, ?> obj, String key) {
        if (key == null) {
            throw new AssertionError();
        } else if (!obj.containsKey(key)) {
            return null;
        } else {
            Object obj2 = obj.get(key);
            if (obj2 instanceof String) {
                return (String) obj2;
            }
            throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not String", new Object[]{obj2, key, obj}));
        }
    }

    public static Long getStringAsDuration(Map<String, ?> obj, String key) {
        String value = getString(obj, key);
        if (value == null) {
            return null;
        }
        try {
            return Long.valueOf(parseDuration(value));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static Boolean getBoolean(Map<String, ?> obj, String key) {
        if (key == null) {
            throw new AssertionError();
        } else if (!obj.containsKey(key)) {
            return null;
        } else {
            Object obj2 = obj.get(key);
            if (obj2 instanceof Boolean) {
                return (Boolean) obj2;
            }
            throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not Boolean", new Object[]{obj2, key, obj}));
        }
    }

    public static List<Map<String, ?>> checkObjectList(List<?> rawList) {
        int i = 0;
        while (i < rawList.size()) {
            if (rawList.get(i) instanceof Map) {
                i++;
            } else {
                throw new ClassCastException(String.format("value %s for idx %d in %s is not object", new Object[]{rawList.get(i), Integer.valueOf(i), rawList}));
            }
        }
        return rawList;
    }

    public static List<String> checkStringList(List<?> rawList) {
        int i = 0;
        while (i < rawList.size()) {
            if (rawList.get(i) instanceof String) {
                i++;
            } else {
                throw new ClassCastException(String.format("value '%s' for idx %d in '%s' is not string", new Object[]{rawList.get(i), Integer.valueOf(i), rawList}));
            }
        }
        return rawList;
    }

    private static long parseDuration(String value) throws ParseException {
        if (value.isEmpty() || value.charAt(value.length() - 1) != 's') {
            throw new ParseException("Invalid duration string: " + value, 0);
        }
        boolean negative = false;
        if (value.charAt(0) == '-') {
            negative = true;
            value = value.substring(1);
        }
        String secondValue = value.substring(0, value.length() - 1);
        String nanoValue = "";
        int pointPosition = secondValue.indexOf(46);
        if (pointPosition != -1) {
            nanoValue = secondValue.substring(pointPosition + 1);
            secondValue = secondValue.substring(0, pointPosition);
        }
        long seconds = Long.parseLong(secondValue);
        int nanos = nanoValue.isEmpty() ? 0 : parseNanos(nanoValue);
        if (seconds >= 0) {
            if (negative) {
                seconds = -seconds;
                nanos = -nanos;
            }
            try {
                return normalizedDuration(seconds, nanos);
            } catch (IllegalArgumentException e) {
                throw new ParseException("Duration value is out of range.", 0);
            }
        } else {
            throw new ParseException("Invalid duration string: " + value, 0);
        }
    }

    private static int parseNanos(String value) throws ParseException {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            result *= 10;
            if (i < value.length()) {
                if (value.charAt(i) < '0' || value.charAt(i) > '9') {
                    throw new ParseException("Invalid nanoseconds.", 0);
                }
                result += value.charAt(i) - '0';
            }
        }
        return result;
    }

    private static long normalizedDuration(long seconds, int nanos) {
        long j = NANOS_PER_SECOND;
        if (((long) nanos) <= (-j) || ((long) nanos) >= j) {
            seconds = LongMath.checkedAdd(seconds, ((long) nanos) / j);
            nanos = (int) (((long) nanos) % j);
        }
        if (seconds > 0 && nanos < 0) {
            nanos = (int) (((long) nanos) + j);
            seconds--;
        }
        if (seconds < 0 && nanos > 0) {
            nanos = (int) (((long) nanos) - j);
            seconds++;
        }
        if (durationIsValid(seconds, nanos)) {
            return saturatedAdd(TimeUnit.SECONDS.toNanos(seconds), (long) nanos);
        }
        throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", new Object[]{Long.valueOf(seconds), Integer.valueOf(nanos)}));
    }

    private static boolean durationIsValid(long seconds, int nanos) {
        if (seconds < DURATION_SECONDS_MIN || seconds > DURATION_SECONDS_MAX || ((long) nanos) < -999999999 || ((long) nanos) >= NANOS_PER_SECOND) {
            return false;
        }
        if (seconds >= 0 && nanos >= 0) {
            return true;
        }
        if (seconds > 0 || nanos > 0) {
            return false;
        }
        return true;
    }

    private static long saturatedAdd(long a, long b) {
        long naiveSum = a + b;
        boolean z = true;
        boolean z2 = (a ^ b) < 0;
        if ((a ^ naiveSum) < 0) {
            z = false;
        }
        if (z2 || z) {
            return naiveSum;
        }
        return ((naiveSum >>> 63) ^ 1) + Long.MAX_VALUE;
    }
}
