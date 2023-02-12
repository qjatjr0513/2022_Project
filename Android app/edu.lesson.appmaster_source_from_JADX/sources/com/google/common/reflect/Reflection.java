package com.google.common.reflect;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public final class Reflection {
    public static String getPackageName(Class<?> clazz) {
        return getPackageName(clazz.getName());
    }

    public static String getPackageName(String classFullName) {
        int lastDot = classFullName.lastIndexOf(46);
        return lastDot < 0 ? "" : classFullName.substring(0, lastDot);
    }

    public static void initialize(Class<?>... classes) {
        int length = classes.length;
        int i = 0;
        while (i < length) {
            Class<?> clazz = classes[i];
            try {
                Class.forName(clazz.getName(), true, clazz.getClassLoader());
                i++;
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }
    }

    public static <T> T newProxy(Class<T> interfaceType, InvocationHandler handler) {
        Preconditions.checkNotNull(handler);
        Preconditions.checkArgument(interfaceType.isInterface(), "%s is not an interface", (Object) interfaceType);
        return interfaceType.cast(Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, handler));
    }

    private Reflection() {
    }
}
