package com.google.firebase.firestore.util;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.PropertyName;
import com.google.firebase.firestore.ServerTimestamp;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CustomClassMapper {
    private static final int MAX_DEPTH = 500;
    private static final ConcurrentMap<Class<?>, BeanMapper<?>> mappers = new ConcurrentHashMap();

    private static void hardAssert(boolean assertion) {
        hardAssert(assertion, "Internal inconsistency");
    }

    /* access modifiers changed from: private */
    public static void hardAssert(boolean assertion, String message) {
        if (!assertion) {
            throw new RuntimeException("Hard assert failed: " + message);
        }
    }

    public static Object convertToPlainJavaTypes(Object object) {
        return serialize(object);
    }

    public static Map<String, Object> convertToPlainJavaTypes(Map<?, Object> update) {
        Object converted = serialize(update);
        hardAssert(converted instanceof Map);
        return (Map) converted;
    }

    public static <T> T convertToCustomClass(Object object, Class<T> clazz, DocumentReference docRef) {
        return deserializeToClass(object, clazz, new DeserializeContext(ErrorPath.EMPTY, docRef));
    }

    private static <T> Object serialize(T o) {
        return serialize(o, ErrorPath.EMPTY);
    }

    /* access modifiers changed from: private */
    public static <T> Object serialize(T o, ErrorPath path) {
        if (path.getLength() > 500) {
            throw serializeError(path, "Exceeded maximum depth of 500, which likely indicates there's an object cycle");
        } else if (o == null) {
            return null;
        } else {
            if (o instanceof Number) {
                if ((o instanceof Long) || (o instanceof Integer) || (o instanceof Double) || (o instanceof Float)) {
                    return o;
                }
                throw serializeError(path, String.format("Numbers of type %s are not supported, please use an int, long, float or double", new Object[]{o.getClass().getSimpleName()}));
            } else if ((o instanceof String) || (o instanceof Boolean)) {
                return o;
            } else {
                if (o instanceof Character) {
                    throw serializeError(path, "Characters are not supported, please use Strings");
                } else if (o instanceof Map) {
                    Map<String, Object> result = new HashMap<>();
                    for (Map.Entry<Object, Object> entry : ((Map) o).entrySet()) {
                        Object key = entry.getKey();
                        if (key instanceof String) {
                            String keyString = (String) key;
                            result.put(keyString, serialize(entry.getValue(), path.child(keyString)));
                        } else {
                            throw serializeError(path, "Maps with non-string keys are not supported");
                        }
                    }
                    return result;
                } else if (o instanceof Collection) {
                    if (o instanceof List) {
                        List<Object> list = o;
                        List<Object> result2 = new ArrayList<>(list.size());
                        for (int i = 0; i < list.size(); i++) {
                            result2.add(serialize(list.get(i), path.child("[" + i + "]")));
                        }
                        return result2;
                    }
                    throw serializeError(path, "Serializing Collections is not supported, please use Lists instead");
                } else if (o.getClass().isArray()) {
                    throw serializeError(path, "Serializing Arrays is not supported, please use Lists instead");
                } else if (o instanceof Enum) {
                    String enumName = ((Enum) o).name();
                    try {
                        return BeanMapper.propertyName(o.getClass().getField(enumName));
                    } catch (NoSuchFieldException e) {
                        return enumName;
                    }
                } else if ((o instanceof Date) || (o instanceof Timestamp) || (o instanceof GeoPoint) || (o instanceof Blob) || (o instanceof DocumentReference) || (o instanceof FieldValue)) {
                    return o;
                } else {
                    return loadOrCreateBeanMapperForClass(o.getClass()).serialize(o, path);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static <T> T deserializeToType(Object o, Type type, DeserializeContext context) {
        if (o == null) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return deserializeToParameterizedType(o, (ParameterizedType) type, context);
        }
        if (type instanceof Class) {
            return deserializeToClass(o, (Class) type, context);
        }
        boolean z = true;
        if (type instanceof WildcardType) {
            if (((WildcardType) type).getLowerBounds().length <= 0) {
                Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                if (upperBounds.length <= 0) {
                    z = false;
                }
                hardAssert(z, "Unexpected type bounds on wildcard " + type);
                return deserializeToType(o, upperBounds[0], context);
            }
            throw deserializeError(context.errorPath, "Generic lower-bounded wildcard types are not supported");
        } else if (type instanceof TypeVariable) {
            Type[] upperBounds2 = ((TypeVariable) type).getBounds();
            if (upperBounds2.length <= 0) {
                z = false;
            }
            hardAssert(z, "Unexpected type bounds on type variable " + type);
            return deserializeToType(o, upperBounds2[0], context);
        } else if (type instanceof GenericArrayType) {
            throw deserializeError(context.errorPath, "Generic Arrays are not supported, please use Lists instead");
        } else {
            throw deserializeError(context.errorPath, "Unknown type encountered: " + type);
        }
    }

    private static <T> T deserializeToClass(Object o, Class<T> clazz, DeserializeContext context) {
        if (o == null) {
            return null;
        }
        if (clazz.isPrimitive() || Number.class.isAssignableFrom(clazz) || Boolean.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz)) {
            return deserializeToPrimitive(o, clazz, context);
        }
        if (String.class.isAssignableFrom(clazz)) {
            return convertString(o, context);
        }
        if (Date.class.isAssignableFrom(clazz)) {
            return convertDate(o, context);
        }
        if (Timestamp.class.isAssignableFrom(clazz)) {
            return convertTimestamp(o, context);
        }
        if (Blob.class.isAssignableFrom(clazz)) {
            return convertBlob(o, context);
        }
        if (GeoPoint.class.isAssignableFrom(clazz)) {
            return convertGeoPoint(o, context);
        }
        if (DocumentReference.class.isAssignableFrom(clazz)) {
            return convertDocumentReference(o, context);
        }
        if (clazz.isArray()) {
            throw deserializeError(context.errorPath, "Converting to Arrays is not supported, please use Lists instead");
        } else if (clazz.getTypeParameters().length > 0) {
            throw deserializeError(context.errorPath, "Class " + clazz.getName() + " has generic type parameters, please use GenericTypeIndicator instead");
        } else if (clazz.equals(Object.class)) {
            return o;
        } else {
            if (clazz.isEnum()) {
                return deserializeToEnum(o, clazz, context);
            }
            return convertBean(o, clazz, context);
        }
    }

    private static <T> T deserializeToParameterizedType(Object o, ParameterizedType type, DeserializeContext context) {
        Class<?> rawType = (Class) type.getRawType();
        if (List.class.isAssignableFrom(rawType)) {
            Type genericType = type.getActualTypeArguments()[0];
            if (o instanceof List) {
                List<Object> list = (List) o;
                List<Object> result = new ArrayList<>(list.size());
                for (int i = 0; i < list.size(); i++) {
                    result.add(deserializeToType(list.get(i), genericType, context.newInstanceWithErrorPath(context.errorPath.child("[" + i + "]"))));
                }
                return result;
            }
            throw deserializeError(context.errorPath, "Expected a List, but got a " + o.getClass());
        } else if (Map.class.isAssignableFrom(rawType)) {
            Type keyType = type.getActualTypeArguments()[0];
            Type valueType = type.getActualTypeArguments()[1];
            if (keyType.equals(String.class)) {
                Map<String, Object> map = expectMap(o, context);
                HashMap<String, Object> result2 = new HashMap<>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    result2.put(entry.getKey(), deserializeToType(entry.getValue(), valueType, context.newInstanceWithErrorPath(context.errorPath.child(entry.getKey()))));
                }
                return result2;
            }
            throw deserializeError(context.errorPath, "Only Maps with string keys are supported, but found Map with key type " + keyType);
        } else if (!Collection.class.isAssignableFrom(rawType)) {
            Map<String, Object> map2 = expectMap(o, context);
            BeanMapper<T> mapper = loadOrCreateBeanMapperForClass(rawType);
            HashMap<TypeVariable<Class<T>>, Type> typeMapping = new HashMap<>();
            TypeVariable<Class<T>>[] typeVariables = mapper.clazz.getTypeParameters();
            Type[] types = type.getActualTypeArguments();
            if (types.length == typeVariables.length) {
                for (int i2 = 0; i2 < typeVariables.length; i2++) {
                    typeMapping.put(typeVariables[i2], types[i2]);
                }
                return mapper.deserialize(map2, typeMapping, context);
            }
            throw new IllegalStateException("Mismatched lengths for type variables and actual types");
        } else {
            throw deserializeError(context.errorPath, "Collections are not supported, please use Lists instead");
        }
    }

    private static <T> T deserializeToPrimitive(Object o, Class<T> clazz, DeserializeContext context) {
        if (Integer.class.isAssignableFrom(clazz) || Integer.TYPE.isAssignableFrom(clazz)) {
            return convertInteger(o, context);
        }
        if (Boolean.class.isAssignableFrom(clazz) || Boolean.TYPE.isAssignableFrom(clazz)) {
            return convertBoolean(o, context);
        }
        if (Double.class.isAssignableFrom(clazz) || Double.TYPE.isAssignableFrom(clazz)) {
            return convertDouble(o, context);
        }
        if (Long.class.isAssignableFrom(clazz) || Long.TYPE.isAssignableFrom(clazz)) {
            return convertLong(o, context);
        }
        if (Float.class.isAssignableFrom(clazz) || Float.TYPE.isAssignableFrom(clazz)) {
            return Float.valueOf(convertDouble(o, context).floatValue());
        }
        throw deserializeError(context.errorPath, String.format("Deserializing values to %s is not supported", new Object[]{clazz.getSimpleName()}));
    }

    private static <T> T deserializeToEnum(Object object, Class<T> clazz, DeserializeContext context) {
        if (object instanceof String) {
            String value = (String) object;
            Field[] enumFields = clazz.getFields();
            int length = enumFields.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    Field field = enumFields[i];
                    if (field.isEnumConstant() && value.equals(BeanMapper.propertyName(field))) {
                        value = field.getName();
                        break;
                    }
                    i++;
                }
            }
            try {
                return Enum.valueOf(clazz, value);
            } catch (IllegalArgumentException e) {
                throw deserializeError(context.errorPath, "Could not find enum value of " + clazz.getName() + " for value \"" + value + "\"");
            }
        } else {
            throw deserializeError(context.errorPath, "Expected a String while deserializing to enum " + clazz + " but got a " + object.getClass());
        }
    }

    private static <T> BeanMapper<T> loadOrCreateBeanMapperForClass(Class<T> clazz) {
        ConcurrentMap<Class<?>, BeanMapper<?>> concurrentMap = mappers;
        BeanMapper<T> mapper = (BeanMapper) concurrentMap.get(clazz);
        if (mapper != null) {
            return mapper;
        }
        BeanMapper<T> mapper2 = new BeanMapper<>(clazz);
        concurrentMap.put(clazz, mapper2);
        return mapper2;
    }

    private static Map<String, Object> expectMap(Object object, DeserializeContext context) {
        if (object instanceof Map) {
            return (Map) object;
        }
        throw deserializeError(context.errorPath, "Expected a Map while deserializing, but got a " + object.getClass());
    }

    private static Integer convertInteger(Object o, DeserializeContext context) {
        if (o instanceof Integer) {
            return (Integer) o;
        }
        if ((o instanceof Long) || (o instanceof Double)) {
            double value = ((Number) o).doubleValue();
            if (value >= -2.147483648E9d && value <= 2.147483647E9d) {
                return Integer.valueOf(((Number) o).intValue());
            }
            throw deserializeError(context.errorPath, "Numeric value out of 32-bit integer range: " + value + ". Did you mean to use a long or double instead of an int?");
        }
        throw deserializeError(context.errorPath, "Failed to convert a value of type " + o.getClass().getName() + " to int");
    }

    private static Long convertLong(Object o, DeserializeContext context) {
        if (o instanceof Integer) {
            return Long.valueOf(((Integer) o).longValue());
        }
        if (o instanceof Long) {
            return (Long) o;
        }
        if (o instanceof Double) {
            Double value = (Double) o;
            if (value.doubleValue() >= -9.223372036854776E18d && value.doubleValue() <= 9.223372036854776E18d) {
                return Long.valueOf(value.longValue());
            }
            throw deserializeError(context.errorPath, "Numeric value out of 64-bit long range: " + value + ". Did you mean to use a double instead of a long?");
        }
        throw deserializeError(context.errorPath, "Failed to convert a value of type " + o.getClass().getName() + " to long");
    }

    private static Double convertDouble(Object o, DeserializeContext context) {
        if (o instanceof Integer) {
            return Double.valueOf(((Integer) o).doubleValue());
        }
        if (o instanceof Long) {
            Double doubleValue = Double.valueOf(((Long) o).doubleValue());
            if (doubleValue.longValue() == ((Long) o).longValue()) {
                return doubleValue;
            }
            throw deserializeError(context.errorPath, "Loss of precision while converting number to double: " + o + ". Did you mean to use a 64-bit long instead?");
        } else if (o instanceof Double) {
            return (Double) o;
        } else {
            throw deserializeError(context.errorPath, "Failed to convert a value of type " + o.getClass().getName() + " to double");
        }
    }

    private static Boolean convertBoolean(Object o, DeserializeContext context) {
        if (o instanceof Boolean) {
            return (Boolean) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to boolean");
    }

    private static String convertString(Object o, DeserializeContext context) {
        if (o instanceof String) {
            return (String) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to String");
    }

    private static Date convertDate(Object o, DeserializeContext context) {
        if (o instanceof Date) {
            return (Date) o;
        }
        if (o instanceof Timestamp) {
            return ((Timestamp) o).toDate();
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to Date");
    }

    private static Timestamp convertTimestamp(Object o, DeserializeContext context) {
        if (o instanceof Timestamp) {
            return (Timestamp) o;
        }
        if (o instanceof Date) {
            return new Timestamp((Date) o);
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to Timestamp");
    }

    private static Blob convertBlob(Object o, DeserializeContext context) {
        if (o instanceof Blob) {
            return (Blob) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to Blob");
    }

    private static GeoPoint convertGeoPoint(Object o, DeserializeContext context) {
        if (o instanceof GeoPoint) {
            return (GeoPoint) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to GeoPoint");
    }

    private static DocumentReference convertDocumentReference(Object o, DeserializeContext context) {
        if (o instanceof DocumentReference) {
            return (DocumentReference) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to DocumentReference");
    }

    private static <T> T convertBean(Object o, Class<T> clazz, DeserializeContext context) {
        BeanMapper<T> mapper = loadOrCreateBeanMapperForClass(clazz);
        if (o instanceof Map) {
            return mapper.deserialize(expectMap(o, context), context);
        }
        throw deserializeError(context.errorPath, "Can't convert object of type " + o.getClass().getName() + " to type " + clazz.getName());
    }

    private static IllegalArgumentException serializeError(ErrorPath path, String reason) {
        String reason2 = "Could not serialize object. " + reason;
        if (path.getLength() > 0) {
            reason2 = reason2 + " (found in field '" + path.toString() + "')";
        }
        return new IllegalArgumentException(reason2);
    }

    /* access modifiers changed from: private */
    public static RuntimeException deserializeError(ErrorPath path, String reason) {
        String reason2 = "Could not deserialize object. " + reason;
        if (path.getLength() > 0) {
            reason2 = reason2 + " (found in field '" + path.toString() + "')";
        }
        return new RuntimeException(reason2);
    }

    private static class BeanMapper<T> {
        /* access modifiers changed from: private */
        public final Class<T> clazz;
        private final Constructor<T> constructor;
        private final HashSet<String> documentIdPropertyNames = new HashSet<>();
        private final Map<String, Field> fields = new HashMap();
        private final Map<String, Method> getters = new HashMap();
        private final Map<String, String> properties = new HashMap();
        private final HashSet<String> serverTimestamps = new HashSet<>();
        private final Map<String, Method> setters = new HashMap();
        private final boolean throwOnUnknownProperties;
        private final boolean warnOnUnknownProperties;

        /* JADX WARNING: Removed duplicated region for block: B:24:0x00d0  */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x01b6  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        BeanMapper(java.lang.Class<T> r13) {
            /*
                r12 = this;
                r12.<init>()
                r12.clazz = r13
                java.lang.Class<com.google.firebase.firestore.ThrowOnExtraProperties> r0 = com.google.firebase.firestore.ThrowOnExtraProperties.class
                boolean r0 = r13.isAnnotationPresent(r0)
                r12.throwOnUnknownProperties = r0
                java.lang.Class<com.google.firebase.firestore.IgnoreExtraProperties> r0 = com.google.firebase.firestore.IgnoreExtraProperties.class
                boolean r0 = r13.isAnnotationPresent(r0)
                r1 = 1
                r0 = r0 ^ r1
                r12.warnOnUnknownProperties = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r12.properties = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r12.setters = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r12.getters = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r12.fields = r0
                java.util.HashSet r0 = new java.util.HashSet
                r0.<init>()
                r12.serverTimestamps = r0
                java.util.HashSet r0 = new java.util.HashSet
                r0.<init>()
                r12.documentIdPropertyNames = r0
                r0 = 0
                java.lang.Class[] r2 = new java.lang.Class[r0]     // Catch:{ NoSuchMethodException -> 0x004c }
                java.lang.reflect.Constructor r2 = r13.getDeclaredConstructor(r2)     // Catch:{ NoSuchMethodException -> 0x004c }
                r2.setAccessible(r1)     // Catch:{ NoSuchMethodException -> 0x004c }
                goto L_0x004f
            L_0x004c:
                r2 = move-exception
                r3 = 0
                r2 = r3
            L_0x004f:
                r12.constructor = r2
                java.lang.reflect.Method[] r3 = r13.getMethods()
                int r4 = r3.length
                r5 = r0
            L_0x0057:
                if (r5 >= r4) goto L_0x00aa
                r6 = r3[r5]
                boolean r7 = shouldIncludeGetter(r6)
                if (r7 == 0) goto L_0x00a7
                java.lang.String r7 = propertyName((java.lang.reflect.Method) r6)
                r12.addProperty(r7)
                r6.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Method> r8 = r12.getters
                boolean r8 = r8.containsKey(r7)
                if (r8 != 0) goto L_0x007c
                java.util.Map<java.lang.String, java.lang.reflect.Method> r8 = r12.getters
                r8.put(r7, r6)
                r12.applyGetterAnnotations(r6)
                goto L_0x00a7
            L_0x007c:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r3 = "Found conflicting getters for name "
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r3 = r6.getName()
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r3 = " on class "
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r3 = r13.getName()
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x00a7:
                int r5 = r5 + 1
                goto L_0x0057
            L_0x00aa:
                java.lang.reflect.Field[] r3 = r13.getFields()
                int r4 = r3.length
                r5 = r0
            L_0x00b0:
                if (r5 >= r4) goto L_0x00c7
                r6 = r3[r5]
                boolean r7 = shouldIncludeField(r6)
                if (r7 == 0) goto L_0x00c4
                java.lang.String r7 = propertyName((java.lang.reflect.Field) r6)
                r12.addProperty(r7)
                r12.applyFieldAnnotations(r6)
            L_0x00c4:
                int r5 = r5 + 1
                goto L_0x00b0
            L_0x00c7:
                r3 = r13
            L_0x00c8:
                java.lang.reflect.Method[] r4 = r3.getDeclaredMethods()
                int r5 = r4.length
                r6 = r0
            L_0x00ce:
                if (r6 >= r5) goto L_0x01ae
                r7 = r4[r6]
                boolean r8 = shouldIncludeSetter(r7)
                if (r8 == 0) goto L_0x01aa
                java.lang.String r8 = propertyName((java.lang.reflect.Method) r7)
                java.util.Map<java.lang.String, java.lang.String> r9 = r12.properties
                java.util.Locale r10 = java.util.Locale.US
                java.lang.String r10 = r8.toLowerCase(r10)
                java.lang.Object r9 = r9.get(r10)
                java.lang.String r9 = (java.lang.String) r9
                if (r9 == 0) goto L_0x01aa
                boolean r10 = r9.equals(r8)
                if (r10 == 0) goto L_0x017f
                java.util.Map<java.lang.String, java.lang.reflect.Method> r10 = r12.setters
                java.lang.Object r10 = r10.get(r8)
                java.lang.reflect.Method r10 = (java.lang.reflect.Method) r10
                if (r10 != 0) goto L_0x0109
                r7.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Method> r11 = r12.setters
                r11.put(r8, r7)
                r12.applySetterAnnotations(r7)
                goto L_0x01aa
            L_0x0109:
                boolean r11 = isSetterOverride(r7, r10)
                if (r11 != 0) goto L_0x01aa
                if (r3 != r13) goto L_0x013c
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r4 = "Class "
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = r13.getName()
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = " has multiple setter overloads with name "
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = r7.getName()
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x013c:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r4 = "Found conflicting setters with name: "
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = r7.getName()
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = " (conflicts with "
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = r10.getName()
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = " defined on "
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.Class r4 = r10.getDeclaringClass()
                java.lang.String r4 = r4.getName()
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = ")"
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x017f:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r4 = "Found setter on "
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = r3.getName()
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = " with invalid case-sensitive name: "
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = r7.getName()
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x01aa:
                int r6 = r6 + 1
                goto L_0x00ce
            L_0x01ae:
                java.lang.reflect.Field[] r4 = r3.getDeclaredFields()
                int r5 = r4.length
                r6 = r0
            L_0x01b4:
                if (r6 >= r5) goto L_0x01e0
                r7 = r4[r6]
                java.lang.String r8 = propertyName((java.lang.reflect.Field) r7)
                java.util.Map<java.lang.String, java.lang.String> r9 = r12.properties
                java.util.Locale r10 = java.util.Locale.US
                java.lang.String r10 = r8.toLowerCase(r10)
                boolean r9 = r9.containsKey(r10)
                if (r9 == 0) goto L_0x01dd
                java.util.Map<java.lang.String, java.lang.reflect.Field> r9 = r12.fields
                boolean r9 = r9.containsKey(r8)
                if (r9 != 0) goto L_0x01dd
                r7.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Field> r9 = r12.fields
                r9.put(r8, r7)
                r12.applyFieldAnnotations(r7)
            L_0x01dd:
                int r6 = r6 + 1
                goto L_0x01b4
            L_0x01e0:
                java.lang.Class r3 = r3.getSuperclass()
                if (r3 == 0) goto L_0x01ee
                java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x00c8
            L_0x01ee:
                java.util.Map<java.lang.String, java.lang.String> r0 = r12.properties
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L_0x0248
                java.util.HashSet<java.lang.String> r0 = r12.documentIdPropertyNames
                java.util.Iterator r0 = r0.iterator()
            L_0x01fc:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0247
                java.lang.Object r1 = r0.next()
                java.lang.String r1 = (java.lang.String) r1
                java.util.Map<java.lang.String, java.lang.reflect.Method> r4 = r12.setters
                boolean r4 = r4.containsKey(r1)
                if (r4 != 0) goto L_0x0246
                java.util.Map<java.lang.String, java.lang.reflect.Field> r4 = r12.fields
                boolean r4 = r4.containsKey(r1)
                if (r4 == 0) goto L_0x0219
                goto L_0x0246
            L_0x0219:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "@DocumentId is annotated on property "
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.StringBuilder r4 = r4.append(r1)
                java.lang.String r5 = " of class "
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r5 = r13.getName()
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r5 = " but no field or public setter was found"
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r4 = r4.toString()
                r0.<init>(r4)
                throw r0
            L_0x0246:
                goto L_0x01fc
            L_0x0247:
                return
            L_0x0248:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r4 = "No properties to serialize found on class "
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r4 = r13.getName()
                java.lang.StringBuilder r1 = r1.append(r4)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.util.CustomClassMapper.BeanMapper.<init>(java.lang.Class):void");
        }

        private void addProperty(String property) {
            String oldValue = this.properties.put(property.toLowerCase(Locale.US), property);
            if (oldValue != null && !property.equals(oldValue)) {
                throw new RuntimeException("Found two getters or fields with conflicting case sensitivity for property: " + property.toLowerCase(Locale.US));
            }
        }

        /* access modifiers changed from: package-private */
        public T deserialize(Map<String, Object> values, DeserializeContext context) {
            return deserialize(values, Collections.emptyMap(), context);
        }

        /* access modifiers changed from: package-private */
        public T deserialize(Map<String, Object> values, Map<TypeVariable<Class<T>>, Type> types, DeserializeContext context) {
            Map<TypeVariable<Class<T>>, Type> map = types;
            DeserializeContext deserializeContext = context;
            Constructor<T> constructor2 = this.constructor;
            if (constructor2 != null) {
                T instance = ApiUtil.newInstance(constructor2);
                HashSet hashSet = new HashSet();
                for (Map.Entry<String, Object> entry : values.entrySet()) {
                    String propertyName = entry.getKey();
                    ErrorPath childPath = deserializeContext.errorPath.child(propertyName);
                    if (this.setters.containsKey(propertyName)) {
                        Method setter = this.setters.get(propertyName);
                        Type[] params = setter.getGenericParameterTypes();
                        if (params.length == 1) {
                            ApiUtil.invoke(setter, instance, CustomClassMapper.deserializeToType(entry.getValue(), resolveType(params[0], map), deserializeContext.newInstanceWithErrorPath(childPath)));
                            hashSet.add(propertyName);
                        } else {
                            throw CustomClassMapper.deserializeError(childPath, "Setter does not have exactly one parameter");
                        }
                    } else if (this.fields.containsKey(propertyName)) {
                        Field field = this.fields.get(propertyName);
                        try {
                            field.set(instance, CustomClassMapper.deserializeToType(entry.getValue(), resolveType(field.getGenericType(), map), deserializeContext.newInstanceWithErrorPath(childPath)));
                            hashSet.add(propertyName);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        String message = "No setter/field for " + propertyName + " found on class " + this.clazz.getName();
                        if (this.properties.containsKey(propertyName.toLowerCase(Locale.US))) {
                            message = message + " (fields/setters are case sensitive!)";
                        }
                        if (this.throwOnUnknownProperties) {
                            throw new RuntimeException(message);
                        } else if (this.warnOnUnknownProperties) {
                            Logger.warn(CustomClassMapper.class.getSimpleName(), "%s", message);
                        }
                    }
                }
                populateDocumentIdProperties(map, deserializeContext, instance, hashSet);
                return instance;
            }
            throw CustomClassMapper.deserializeError(deserializeContext.errorPath, "Class " + this.clazz.getName() + " does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped");
        }

        private void populateDocumentIdProperties(Map<TypeVariable<Class<T>>, Type> types, DeserializeContext context, T instance, HashSet<String> deserialzedProperties) {
            Iterator<String> it = this.documentIdPropertyNames.iterator();
            while (it.hasNext()) {
                String docIdPropertyName = it.next();
                if (!deserialzedProperties.contains(docIdPropertyName)) {
                    ErrorPath childPath = context.errorPath.child(docIdPropertyName);
                    if (this.setters.containsKey(docIdPropertyName)) {
                        Method setter = this.setters.get(docIdPropertyName);
                        Type[] params = setter.getGenericParameterTypes();
                        if (params.length != 1) {
                            throw CustomClassMapper.deserializeError(childPath, "Setter does not have exactly one parameter");
                        } else if (resolveType(params[0], types) == String.class) {
                            ApiUtil.invoke(setter, instance, context.documentRef.getId());
                        } else {
                            ApiUtil.invoke(setter, instance, context.documentRef);
                        }
                    } else {
                        Field docIdField = this.fields.get(docIdPropertyName);
                        try {
                            if (docIdField.getType() == String.class) {
                                docIdField.set(instance, context.documentRef.getId());
                            } else {
                                docIdField.set(instance, context.documentRef);
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    throw new RuntimeException("'" + docIdPropertyName + "' was found from document " + context.documentRef.getPath() + ", cannot apply @DocumentId on this property for class " + this.clazz.getName());
                }
            }
        }

        private Type resolveType(Type type, Map<TypeVariable<Class<T>>, Type> types) {
            if (!(type instanceof TypeVariable)) {
                return type;
            }
            Type resolvedType = types.get(type);
            if (resolvedType != null) {
                return resolvedType;
            }
            throw new IllegalStateException("Could not resolve type " + type);
        }

        /* access modifiers changed from: package-private */
        public Map<String, Object> serialize(T object, ErrorPath path) {
            Object propertyValue;
            Object serializedValue;
            if (this.clazz.isAssignableFrom(object.getClass())) {
                Map<String, Object> result = new HashMap<>();
                for (String property : this.properties.values()) {
                    if (!this.documentIdPropertyNames.contains(property)) {
                        if (this.getters.containsKey(property)) {
                            propertyValue = ApiUtil.invoke(this.getters.get(property), object, new Object[0]);
                        } else {
                            Field field = this.fields.get(property);
                            if (field != null) {
                                try {
                                    propertyValue = field.get(object);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                throw new IllegalStateException("Bean property without field or getter: " + property);
                            }
                        }
                        if (!this.serverTimestamps.contains(property) || propertyValue != null) {
                            serializedValue = CustomClassMapper.serialize(propertyValue, path.child(property));
                        } else {
                            serializedValue = FieldValue.serverTimestamp();
                        }
                        result.put(property, serializedValue);
                    }
                }
                return result;
            }
            throw new IllegalArgumentException("Can't serialize object of class " + object.getClass() + " with BeanMapper for class " + this.clazz);
        }

        private void applyFieldAnnotations(Field field) {
            if (field.isAnnotationPresent(ServerTimestamp.class)) {
                Class<?> fieldType = field.getType();
                if (fieldType == Date.class || fieldType == Timestamp.class) {
                    this.serverTimestamps.add(propertyName(field));
                } else {
                    throw new IllegalArgumentException("Field " + field.getName() + " is annotated with @ServerTimestamp but is " + fieldType + " instead of Date or Timestamp.");
                }
            }
            if (field.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Field", "is", field.getType());
                this.documentIdPropertyNames.add(propertyName(field));
            }
        }

        private void applyGetterAnnotations(Method method) {
            if (method.isAnnotationPresent(ServerTimestamp.class)) {
                Class<?> returnType = method.getReturnType();
                if (returnType == Date.class || returnType == Timestamp.class) {
                    this.serverTimestamps.add(propertyName(method));
                } else {
                    throw new IllegalArgumentException("Method " + method.getName() + " is annotated with @ServerTimestamp but returns " + returnType + " instead of Date or Timestamp.");
                }
            }
            if (method.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Method", "returns", method.getReturnType());
                this.documentIdPropertyNames.add(propertyName(method));
            }
        }

        private void applySetterAnnotations(Method method) {
            if (method.isAnnotationPresent(ServerTimestamp.class)) {
                throw new IllegalArgumentException("Method " + method.getName() + " is annotated with @ServerTimestamp but should not be. @ServerTimestamp can only be applied to fields and getters, not setters.");
            } else if (method.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Method", "accepts", method.getParameterTypes()[0]);
                this.documentIdPropertyNames.add(propertyName(method));
            }
        }

        private void ensureValidDocumentIdType(String fieldDescription, String operation, Type type) {
            if (type != String.class && type != DocumentReference.class) {
                throw new IllegalArgumentException(fieldDescription + " is annotated with @DocumentId but " + operation + " " + type + " instead of String or DocumentReference.");
            }
        }

        private static boolean shouldIncludeGetter(Method method) {
            if ((method.getName().startsWith("get") || method.getName().startsWith("is")) && !method.getDeclaringClass().equals(Object.class) && Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()) && !method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 0 && !method.isAnnotationPresent(Exclude.class)) {
                return true;
            }
            return false;
        }

        private static boolean shouldIncludeSetter(Method method) {
            if (method.getName().startsWith("set") && !method.getDeclaringClass().equals(Object.class) && !Modifier.isStatic(method.getModifiers()) && method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 1 && !method.isAnnotationPresent(Exclude.class)) {
                return true;
            }
            return false;
        }

        private static boolean shouldIncludeField(Field field) {
            if (!field.getDeclaringClass().equals(Object.class) && Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()) && !field.isAnnotationPresent(Exclude.class)) {
                return true;
            }
            return false;
        }

        private static boolean isSetterOverride(Method base, Method override) {
            CustomClassMapper.hardAssert(base.getDeclaringClass().isAssignableFrom(override.getDeclaringClass()), "Expected override from a base class");
            CustomClassMapper.hardAssert(base.getReturnType().equals(Void.TYPE), "Expected void return type");
            CustomClassMapper.hardAssert(override.getReturnType().equals(Void.TYPE), "Expected void return type");
            Type[] baseParameterTypes = base.getParameterTypes();
            Type[] overrideParameterTypes = override.getParameterTypes();
            CustomClassMapper.hardAssert(baseParameterTypes.length == 1, "Expected exactly one parameter");
            CustomClassMapper.hardAssert(overrideParameterTypes.length == 1, "Expected exactly one parameter");
            if (!base.getName().equals(override.getName()) || !baseParameterTypes[0].equals(overrideParameterTypes[0])) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static String propertyName(Field field) {
            String annotatedName = annotatedName(field);
            return annotatedName != null ? annotatedName : field.getName();
        }

        private static String propertyName(Method method) {
            String annotatedName = annotatedName(method);
            return annotatedName != null ? annotatedName : serializedName(method.getName());
        }

        private static String annotatedName(AccessibleObject obj) {
            if (obj.isAnnotationPresent(PropertyName.class)) {
                return ((PropertyName) obj.getAnnotation(PropertyName.class)).value();
            }
            return null;
        }

        private static String serializedName(String methodName) {
            String methodPrefix = null;
            for (String prefix : new String[]{"get", "set", "is"}) {
                if (methodName.startsWith(prefix)) {
                    methodPrefix = prefix;
                }
            }
            if (methodPrefix != null) {
                char[] chars = methodName.substring(methodPrefix.length()).toCharArray();
                int pos = 0;
                while (pos < chars.length && Character.isUpperCase(chars[pos])) {
                    chars[pos] = Character.toLowerCase(chars[pos]);
                    pos++;
                }
                return new String(chars);
            }
            throw new IllegalArgumentException("Unknown Bean prefix for method: " + methodName);
        }
    }

    static class ErrorPath {
        static final ErrorPath EMPTY = new ErrorPath((ErrorPath) null, (String) null, 0);
        private final int length;
        private final String name;
        private final ErrorPath parent;

        ErrorPath(ErrorPath parent2, String name2, int length2) {
            this.parent = parent2;
            this.name = name2;
            this.length = length2;
        }

        /* access modifiers changed from: package-private */
        public int getLength() {
            return this.length;
        }

        /* access modifiers changed from: package-private */
        public ErrorPath child(String name2) {
            return new ErrorPath(this, name2, this.length + 1);
        }

        public String toString() {
            int i = this.length;
            if (i == 0) {
                return "";
            }
            if (i == 1) {
                return this.name;
            }
            return this.parent.toString() + "." + this.name;
        }
    }

    static class DeserializeContext {
        final DocumentReference documentRef;
        final ErrorPath errorPath;

        DeserializeContext(ErrorPath path, DocumentReference docRef) {
            this.errorPath = path;
            this.documentRef = docRef;
        }

        /* access modifiers changed from: package-private */
        public DeserializeContext newInstanceWithErrorPath(ErrorPath newPath) {
            return new DeserializeContext(newPath, this.documentRef);
        }
    }
}
