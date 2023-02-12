package com.google.firebase.database.core.utilities.encoding;

import android.util.Log;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.PropertyName;
import com.google.firebase.database.core.utilities.Utilities;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CustomClassMapper {
    private static final String LOG_TAG = "ClassMapper";
    private static final ConcurrentMap<Class<?>, BeanMapper<?>> mappers = new ConcurrentHashMap();

    public static Object convertToPlainJavaTypes(Object object) {
        return serialize(object);
    }

    public static Map<String, Object> convertToPlainJavaTypes(Map<String, Object> update) {
        Object converted = serialize(update);
        Utilities.hardAssert(converted instanceof Map);
        return (Map) converted;
    }

    public static <T> T convertToCustomClass(Object object, Class<T> clazz) {
        return deserializeToClass(object, clazz);
    }

    public static <T> T convertToCustomClass(Object object, GenericTypeIndicator<T> typeIndicator) {
        Type genericTypeIndicatorType = typeIndicator.getClass().getGenericSuperclass();
        if (genericTypeIndicatorType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericTypeIndicatorType;
            if (parameterizedType.getRawType().equals(GenericTypeIndicator.class)) {
                return deserializeToType(object, parameterizedType.getActualTypeArguments()[0]);
            }
            throw new DatabaseException("Not a direct subclass of GenericTypeIndicator: " + genericTypeIndicatorType);
        }
        throw new DatabaseException("Not a direct subclass of GenericTypeIndicator: " + genericTypeIndicatorType);
    }

    /* access modifiers changed from: private */
    public static <T> Object serialize(T o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Number) {
            if ((o instanceof Float) || (o instanceof Double)) {
                double doubleValue = ((Number) o).doubleValue();
                if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d || Math.floor(doubleValue) != doubleValue) {
                    return Double.valueOf(doubleValue);
                }
                return Long.valueOf(((Number) o).longValue());
            } else if ((o instanceof Long) || (o instanceof Integer)) {
                return o;
            } else {
                throw new DatabaseException(String.format("Numbers of type %s are not supported, please use an int, long, float or double", new Object[]{o.getClass().getSimpleName()}));
            }
        } else if ((o instanceof String) || (o instanceof Boolean)) {
            return o;
        } else {
            if (o instanceof Character) {
                throw new DatabaseException("Characters are not supported, please use Strings");
            } else if (o instanceof Map) {
                Map<String, Object> result = new HashMap<>();
                for (Map.Entry<Object, Object> entry : ((Map) o).entrySet()) {
                    Object key = entry.getKey();
                    if (key instanceof String) {
                        result.put((String) key, serialize(entry.getValue()));
                    } else {
                        throw new DatabaseException("Maps with non-string keys are not supported");
                    }
                }
                return result;
            } else if (o instanceof Collection) {
                if (o instanceof List) {
                    List<Object> list = o;
                    List<Object> result2 = new ArrayList<>(list.size());
                    for (Object object : list) {
                        result2.add(serialize(object));
                    }
                    return result2;
                }
                throw new DatabaseException("Serializing Collections is not supported, please use Lists instead");
            } else if (o.getClass().isArray()) {
                throw new DatabaseException("Serializing Arrays is not supported, please use Lists instead");
            } else if (o instanceof Enum) {
                return ((Enum) o).name();
            } else {
                return loadOrCreateBeanMapperForClass(o.getClass()).serialize(o);
            }
        }
    }

    /* access modifiers changed from: private */
    public static <T> T deserializeToType(Object o, Type type) {
        if (o == null) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return deserializeToParameterizedType(o, (ParameterizedType) type);
        }
        if (type instanceof Class) {
            return deserializeToClass(o, (Class) type);
        }
        boolean z = true;
        if (type instanceof WildcardType) {
            if (((WildcardType) type).getLowerBounds().length <= 0) {
                Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                if (upperBounds.length <= 0) {
                    z = false;
                }
                Utilities.hardAssert(z, "Wildcard type " + type + " is not upper bounded.");
                return deserializeToType(o, upperBounds[0]);
            }
            throw new DatabaseException("Generic lower-bounded wildcard types are not supported");
        } else if (type instanceof TypeVariable) {
            Type[] upperBounds2 = ((TypeVariable) type).getBounds();
            if (upperBounds2.length <= 0) {
                z = false;
            }
            Utilities.hardAssert(z, "Wildcard type " + type + " is not upper bounded.");
            return deserializeToType(o, upperBounds2[0]);
        } else if (type instanceof GenericArrayType) {
            throw new DatabaseException("Generic Arrays are not supported, please use Lists instead");
        } else {
            throw new IllegalStateException("Unknown type encountered: " + type);
        }
    }

    private static <T> T deserializeToClass(Object o, Class<T> clazz) {
        if (o == null) {
            return null;
        }
        if (clazz.isPrimitive() || Number.class.isAssignableFrom(clazz) || Boolean.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz)) {
            return deserializeToPrimitive(o, clazz);
        }
        if (String.class.isAssignableFrom(clazz)) {
            return convertString(o);
        }
        if (clazz.isArray()) {
            throw new DatabaseException("Converting to Arrays is not supported, please use Listsinstead");
        } else if (clazz.getTypeParameters().length > 0) {
            throw new DatabaseException("Class " + clazz.getName() + " has generic type parameters, please use GenericTypeIndicator instead");
        } else if (clazz.equals(Object.class)) {
            return o;
        } else {
            if (clazz.isEnum()) {
                return deserializeToEnum(o, clazz);
            }
            return convertBean(o, clazz);
        }
    }

    private static <T> T deserializeToParameterizedType(Object o, ParameterizedType type) {
        Class<?> rawType = (Class) type.getRawType();
        if (List.class.isAssignableFrom(rawType)) {
            Type genericType = type.getActualTypeArguments()[0];
            if (o instanceof List) {
                List<Object> list = (List) o;
                List<Object> result = new ArrayList<>(list.size());
                for (Object object : list) {
                    result.add(deserializeToType(object, genericType));
                }
                return result;
            }
            throw new DatabaseException("Expected a List while deserializing, but got a " + o.getClass());
        } else if (Map.class.isAssignableFrom(rawType)) {
            Type keyType = type.getActualTypeArguments()[0];
            Type valueType = type.getActualTypeArguments()[1];
            if (keyType.equals(String.class)) {
                Map<String, Object> map = expectMap(o);
                HashMap<String, Object> result2 = new HashMap<>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    result2.put(entry.getKey(), deserializeToType(entry.getValue(), valueType));
                }
                return result2;
            }
            throw new DatabaseException("Only Maps with string keys are supported, but found Map with key type " + keyType);
        } else if (!Collection.class.isAssignableFrom(rawType)) {
            Map<String, Object> map2 = expectMap(o);
            BeanMapper<T> mapper = loadOrCreateBeanMapperForClass(rawType);
            HashMap<TypeVariable<Class<T>>, Type> typeMapping = new HashMap<>();
            TypeVariable<Class<T>>[] typeVariables = mapper.clazz.getTypeParameters();
            Type[] types = type.getActualTypeArguments();
            if (types.length == typeVariables.length) {
                for (int i = 0; i < typeVariables.length; i++) {
                    typeMapping.put(typeVariables[i], types[i]);
                }
                return mapper.deserialize(map2, typeMapping);
            }
            throw new IllegalStateException("Mismatched lengths for type variables and actual types");
        } else {
            throw new DatabaseException("Collections are not supported, please use Lists instead");
        }
    }

    private static <T> T deserializeToPrimitive(Object o, Class<T> clazz) {
        if (Integer.class.isAssignableFrom(clazz) || Integer.TYPE.isAssignableFrom(clazz)) {
            return convertInteger(o);
        }
        if (Boolean.class.isAssignableFrom(clazz) || Boolean.TYPE.isAssignableFrom(clazz)) {
            return convertBoolean(o);
        }
        if (Double.class.isAssignableFrom(clazz) || Double.TYPE.isAssignableFrom(clazz)) {
            return convertDouble(o);
        }
        if (Long.class.isAssignableFrom(clazz) || Long.TYPE.isAssignableFrom(clazz)) {
            return convertLong(o);
        }
        if (Float.class.isAssignableFrom(clazz) || Float.TYPE.isAssignableFrom(clazz)) {
            return Float.valueOf(convertDouble(o).floatValue());
        }
        throw new DatabaseException(String.format("Deserializing values to %s is not supported", new Object[]{clazz.getSimpleName()}));
    }

    private static <T> T deserializeToEnum(Object object, Class<T> clazz) {
        if (object instanceof String) {
            String value = (String) object;
            try {
                return Enum.valueOf(clazz, value);
            } catch (IllegalArgumentException e) {
                throw new DatabaseException("Could not find enum value of " + clazz.getName() + " for value \"" + value + "\"");
            }
        } else {
            throw new DatabaseException("Expected a String while deserializing to enum " + clazz + " but got a " + object.getClass());
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

    private static Map<String, Object> expectMap(Object object) {
        if (object instanceof Map) {
            return (Map) object;
        }
        throw new DatabaseException("Expected a Map while deserializing, but got a " + object.getClass());
    }

    private static Integer convertInteger(Object o) {
        if (o instanceof Integer) {
            return (Integer) o;
        }
        if ((o instanceof Long) || (o instanceof Double)) {
            double value = ((Number) o).doubleValue();
            if (value >= -2.147483648E9d && value <= 2.147483647E9d) {
                return Integer.valueOf(((Number) o).intValue());
            }
            throw new DatabaseException("Numeric value out of 32-bit integer range: " + value + ". Did you mean to use a long or double instead of an int?");
        }
        throw new DatabaseException("Failed to convert a value of type " + o.getClass().getName() + " to int");
    }

    private static Long convertLong(Object o) {
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
            throw new DatabaseException("Numeric value out of 64-bit long range: " + value + ". Did you mean to use a double instead of a long?");
        }
        throw new DatabaseException("Failed to convert a value of type " + o.getClass().getName() + " to long");
    }

    private static Double convertDouble(Object o) {
        if (o instanceof Integer) {
            return Double.valueOf(((Integer) o).doubleValue());
        }
        if (o instanceof Long) {
            Double doubleValue = Double.valueOf(((Long) o).doubleValue());
            if (doubleValue.longValue() == ((Long) o).longValue()) {
                return doubleValue;
            }
            throw new DatabaseException("Loss of precision while converting number to double: " + o + ". Did you mean to use a 64-bit long instead?");
        } else if (o instanceof Double) {
            return (Double) o;
        } else {
            throw new DatabaseException("Failed to convert a value of type " + o.getClass().getName() + " to double");
        }
    }

    private static Boolean convertBoolean(Object o) {
        if (o instanceof Boolean) {
            return (Boolean) o;
        }
        throw new DatabaseException("Failed to convert value of type " + o.getClass().getName() + " to boolean");
    }

    private static String convertString(Object o) {
        if (o instanceof String) {
            return (String) o;
        }
        throw new DatabaseException("Failed to convert value of type " + o.getClass().getName() + " to String");
    }

    private static <T> T convertBean(Object o, Class<T> clazz) {
        BeanMapper<T> mapper = loadOrCreateBeanMapperForClass(clazz);
        if (o instanceof Map) {
            return mapper.deserialize(expectMap(o));
        }
        throw new DatabaseException("Can't convert object of type " + o.getClass().getName() + " to type " + clazz.getName());
    }

    private static class BeanMapper<T> {
        /* access modifiers changed from: private */
        public final Class<T> clazz;
        private final Constructor<T> constructor;
        private final Map<String, Field> fields = new HashMap();
        private final Map<String, Method> getters = new HashMap();
        private final Map<String, String> properties = new HashMap();
        private final Map<String, Method> setters = new HashMap();
        private final boolean throwOnUnknownProperties;
        private final boolean warnOnUnknownProperties;

        /* JADX WARNING: Removed duplicated region for block: B:24:0x00af  */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x0157  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public BeanMapper(java.lang.Class<T> r13) {
            /*
                r12 = this;
                r12.<init>()
                r12.clazz = r13
                java.lang.Class<com.google.firebase.database.ThrowOnExtraProperties> r0 = com.google.firebase.database.ThrowOnExtraProperties.class
                boolean r0 = r13.isAnnotationPresent(r0)
                r12.throwOnUnknownProperties = r0
                java.lang.Class<com.google.firebase.database.IgnoreExtraProperties> r0 = com.google.firebase.database.IgnoreExtraProperties.class
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
                r0 = 0
                r2 = 0
                java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ NoSuchMethodException -> 0x0040 }
                java.lang.reflect.Constructor r3 = r13.getDeclaredConstructor(r3)     // Catch:{ NoSuchMethodException -> 0x0040 }
                r0 = r3
                r0.setAccessible(r1)     // Catch:{ NoSuchMethodException -> 0x0040 }
                goto L_0x0042
            L_0x0040:
                r3 = move-exception
                r0 = 0
            L_0x0042:
                r12.constructor = r0
                java.lang.reflect.Method[] r3 = r13.getMethods()
                int r4 = r3.length
                r5 = r2
            L_0x004a:
                if (r5 >= r4) goto L_0x008c
                r6 = r3[r5]
                boolean r7 = shouldIncludeGetter(r6)
                if (r7 == 0) goto L_0x0089
                java.lang.String r7 = propertyName((java.lang.reflect.Method) r6)
                r12.addProperty(r7)
                r6.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Method> r8 = r12.getters
                boolean r8 = r8.containsKey(r7)
                if (r8 != 0) goto L_0x006c
                java.util.Map<java.lang.String, java.lang.reflect.Method> r8 = r12.getters
                r8.put(r7, r6)
                goto L_0x0089
            L_0x006c:
                com.google.firebase.database.DatabaseException r1 = new com.google.firebase.database.DatabaseException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Found conflicting getters for name: "
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r3 = r6.getName()
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                r1.<init>(r2)
                throw r1
            L_0x0089:
                int r5 = r5 + 1
                goto L_0x004a
            L_0x008c:
                java.lang.reflect.Field[] r3 = r13.getFields()
                int r4 = r3.length
                r5 = r2
            L_0x0092:
                if (r5 >= r4) goto L_0x00a6
                r6 = r3[r5]
                boolean r7 = shouldIncludeField(r6)
                if (r7 == 0) goto L_0x00a3
                java.lang.String r7 = propertyName((java.lang.reflect.Field) r6)
                r12.addProperty(r7)
            L_0x00a3:
                int r5 = r5 + 1
                goto L_0x0092
            L_0x00a6:
                r3 = r13
            L_0x00a7:
                java.lang.reflect.Method[] r4 = r3.getDeclaredMethods()
                int r5 = r4.length
                r6 = r2
            L_0x00ad:
                if (r6 >= r5) goto L_0x014f
                r7 = r4[r6]
                boolean r8 = shouldIncludeSetter(r7)
                if (r8 == 0) goto L_0x014b
                java.lang.String r8 = propertyName((java.lang.reflect.Method) r7)
                java.util.Map<java.lang.String, java.lang.String> r9 = r12.properties
                java.util.Locale r10 = java.util.Locale.US
                java.lang.String r10 = r8.toLowerCase(r10)
                java.lang.Object r9 = r9.get(r10)
                java.lang.String r9 = (java.lang.String) r9
                if (r9 == 0) goto L_0x014b
                boolean r10 = r9.equals(r8)
                if (r10 == 0) goto L_0x012e
                java.util.Map<java.lang.String, java.lang.reflect.Method> r10 = r12.setters
                java.lang.Object r10 = r10.get(r8)
                java.lang.reflect.Method r10 = (java.lang.reflect.Method) r10
                if (r10 != 0) goto L_0x00e4
                r7.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Method> r11 = r12.setters
                r11.put(r8, r7)
                goto L_0x014b
            L_0x00e4:
                boolean r11 = isSetterOverride(r7, r10)
                if (r11 == 0) goto L_0x00eb
                goto L_0x014b
            L_0x00eb:
                com.google.firebase.database.DatabaseException r1 = new com.google.firebase.database.DatabaseException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r4 = "Found a conflicting setters with name: "
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.String r4 = r7.getName()
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.String r4 = " (conflicts with "
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.String r4 = r10.getName()
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.String r4 = " defined on "
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.Class r4 = r10.getDeclaringClass()
                java.lang.String r4 = r4.getName()
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.String r4 = ")"
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.String r2 = r2.toString()
                r1.<init>(r2)
                throw r1
            L_0x012e:
                com.google.firebase.database.DatabaseException r1 = new com.google.firebase.database.DatabaseException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r4 = "Found setter with invalid case-sensitive name: "
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.String r4 = r7.getName()
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.String r2 = r2.toString()
                r1.<init>(r2)
                throw r1
            L_0x014b:
                int r6 = r6 + 1
                goto L_0x00ad
            L_0x014f:
                java.lang.reflect.Field[] r4 = r3.getDeclaredFields()
                int r5 = r4.length
                r6 = r2
            L_0x0155:
                if (r6 >= r5) goto L_0x017e
                r7 = r4[r6]
                java.lang.String r8 = propertyName((java.lang.reflect.Field) r7)
                java.util.Map<java.lang.String, java.lang.String> r9 = r12.properties
                java.util.Locale r10 = java.util.Locale.US
                java.lang.String r10 = r8.toLowerCase(r10)
                boolean r9 = r9.containsKey(r10)
                if (r9 == 0) goto L_0x017b
                java.util.Map<java.lang.String, java.lang.reflect.Field> r9 = r12.fields
                boolean r9 = r9.containsKey(r8)
                if (r9 != 0) goto L_0x017b
                r7.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Field> r9 = r12.fields
                r9.put(r8, r7)
            L_0x017b:
                int r6 = r6 + 1
                goto L_0x0155
            L_0x017e:
                java.lang.Class r3 = r3.getSuperclass()
                if (r3 == 0) goto L_0x018c
                java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x00a7
            L_0x018c:
                java.util.Map<java.lang.String, java.lang.String> r1 = r12.properties
                boolean r1 = r1.isEmpty()
                if (r1 != 0) goto L_0x0195
                return
            L_0x0195:
                com.google.firebase.database.DatabaseException r1 = new com.google.firebase.database.DatabaseException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r4 = "No properties to serialize found on class "
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.String r4 = r13.getName()
                java.lang.StringBuilder r2 = r2.append(r4)
                java.lang.String r2 = r2.toString()
                r1.<init>(r2)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.core.utilities.encoding.CustomClassMapper.BeanMapper.<init>(java.lang.Class):void");
        }

        private void addProperty(String property) {
            String oldValue = this.properties.put(property.toLowerCase(Locale.US), property);
            if (oldValue != null && !property.equals(oldValue)) {
                throw new DatabaseException("Found two getters or fields with conflicting case sensitivity for property: " + property.toLowerCase(Locale.US));
            }
        }

        public T deserialize(Map<String, Object> values) {
            return deserialize(values, Collections.emptyMap());
        }

        public T deserialize(Map<String, Object> values, Map<TypeVariable<Class<T>>, Type> types) {
            Constructor<T> constructor2 = this.constructor;
            if (constructor2 != null) {
                try {
                    T instance = constructor2.newInstance(new Object[0]);
                    for (Map.Entry<String, Object> entry : values.entrySet()) {
                        String propertyName = entry.getKey();
                        if (this.setters.containsKey(propertyName)) {
                            Method setter = this.setters.get(propertyName);
                            Type[] params = setter.getGenericParameterTypes();
                            if (params.length == 1) {
                                try {
                                    setter.invoke(instance, new Object[]{CustomClassMapper.deserializeToType(entry.getValue(), resolveType(params[0], types))});
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                } catch (InvocationTargetException e2) {
                                    throw new RuntimeException(e2);
                                }
                            } else {
                                throw new IllegalStateException("Setter does not have exactly one parameter");
                            }
                        } else if (this.fields.containsKey(propertyName)) {
                            Field field = this.fields.get(propertyName);
                            try {
                                field.set(instance, CustomClassMapper.deserializeToType(entry.getValue(), resolveType(field.getGenericType(), types)));
                            } catch (IllegalAccessException e3) {
                                throw new RuntimeException(e3);
                            }
                        } else {
                            String message = "No setter/field for " + propertyName + " found on class " + this.clazz.getName();
                            if (this.properties.containsKey(propertyName.toLowerCase(Locale.US))) {
                                message = message + " (fields/setters are case sensitive!)";
                            }
                            if (this.throwOnUnknownProperties) {
                                throw new DatabaseException(message);
                            } else if (this.warnOnUnknownProperties) {
                                Log.w(CustomClassMapper.LOG_TAG, message);
                            }
                        }
                    }
                    return instance;
                } catch (InstantiationException e4) {
                    throw new RuntimeException(e4);
                } catch (IllegalAccessException e5) {
                    throw new RuntimeException(e5);
                } catch (InvocationTargetException e6) {
                    throw new RuntimeException(e6);
                }
            } else {
                throw new DatabaseException("Class " + this.clazz.getName() + " does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped.");
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

        public Map<String, Object> serialize(T object) {
            Object propertyValue;
            if (this.clazz.isAssignableFrom(object.getClass())) {
                Map<String, Object> result = new HashMap<>();
                for (String property : this.properties.values()) {
                    if (this.getters.containsKey(property)) {
                        try {
                            propertyValue = this.getters.get(property).invoke(object, new Object[0]);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e2) {
                            throw new RuntimeException(e2);
                        }
                    } else {
                        Field field = this.fields.get(property);
                        if (field != null) {
                            try {
                                propertyValue = field.get(object);
                            } catch (IllegalAccessException e3) {
                                throw new RuntimeException(e3);
                            }
                        } else {
                            throw new IllegalStateException("Bean property without field or getter:" + property);
                        }
                    }
                    result.put(property, CustomClassMapper.serialize(propertyValue));
                }
                return result;
            }
            throw new IllegalArgumentException("Can't serialize object of class " + object.getClass() + " with BeanMapper for class " + this.clazz);
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
            Utilities.hardAssert(base.getDeclaringClass().isAssignableFrom(override.getDeclaringClass()), "Expected override from a base class");
            Utilities.hardAssert(base.getReturnType().equals(Void.TYPE), "Expected void return type");
            Utilities.hardAssert(override.getReturnType().equals(Void.TYPE), "Expected void return type");
            Type[] baseParameterTypes = base.getParameterTypes();
            Type[] overrideParameterTypes = override.getParameterTypes();
            Utilities.hardAssert(baseParameterTypes.length == 1, "Expected exactly one parameter");
            Utilities.hardAssert(overrideParameterTypes.length == 1, "Expected exactly one parameter");
            if (!base.getName().equals(override.getName()) || !baseParameterTypes[0].equals(overrideParameterTypes[0])) {
                return false;
            }
            return true;
        }

        private static String propertyName(Field field) {
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
}
