package com.google.common.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class Throwables {
    private static final String JAVA_LANG_ACCESS_CLASSNAME = "sun.misc.JavaLangAccess";
    static final String SHARED_SECRETS_CLASSNAME = "sun.misc.SharedSecrets";
    /* access modifiers changed from: private */
    @NullableDecl
    public static final Method getStackTraceDepthMethod;
    /* access modifiers changed from: private */
    @NullableDecl
    public static final Method getStackTraceElementMethod;
    /* access modifiers changed from: private */
    @NullableDecl
    public static final Object jla;

    private Throwables() {
    }

    public static <X extends Throwable> void throwIfInstanceOf(Throwable throwable, Class<X> declaredType) throws Throwable {
        Preconditions.checkNotNull(throwable);
        if (declaredType.isInstance(throwable)) {
            throw ((Throwable) declaredType.cast(throwable));
        }
    }

    @Deprecated
    public static <X extends Throwable> void propagateIfInstanceOf(@NullableDecl Throwable throwable, Class<X> declaredType) throws Throwable {
        if (throwable != null) {
            throwIfInstanceOf(throwable, declaredType);
        }
    }

    public static void throwIfUnchecked(Throwable throwable) {
        Preconditions.checkNotNull(throwable);
        if (throwable instanceof RuntimeException) {
            throw ((RuntimeException) throwable);
        } else if (throwable instanceof Error) {
            throw ((Error) throwable);
        }
    }

    @Deprecated
    public static void propagateIfPossible(@NullableDecl Throwable throwable) {
        if (throwable != null) {
            throwIfUnchecked(throwable);
        }
    }

    public static <X extends Throwable> void propagateIfPossible(@NullableDecl Throwable throwable, Class<X> declaredType) throws Throwable {
        propagateIfInstanceOf(throwable, declaredType);
        propagateIfPossible(throwable);
    }

    public static <X1 extends Throwable, X2 extends Throwable> void propagateIfPossible(@NullableDecl Throwable throwable, Class<X1> declaredType1, Class<X2> declaredType2) throws Throwable, Throwable {
        Preconditions.checkNotNull(declaredType2);
        propagateIfInstanceOf(throwable, declaredType1);
        propagateIfPossible(throwable, declaredType2);
    }

    @Deprecated
    public static RuntimeException propagate(Throwable throwable) {
        throwIfUnchecked(throwable);
        throw new RuntimeException(throwable);
    }

    public static Throwable getRootCause(Throwable throwable) {
        Throwable slowPointer = throwable;
        boolean advanceSlowPointer = false;
        while (true) {
            Throwable cause = throwable.getCause();
            Throwable cause2 = cause;
            if (cause == null) {
                return throwable;
            }
            throwable = cause2;
            if (throwable != slowPointer) {
                if (advanceSlowPointer) {
                    slowPointer = slowPointer.getCause();
                }
                advanceSlowPointer = !advanceSlowPointer;
            } else {
                throw new IllegalArgumentException("Loop in causal chain detected.", throwable);
            }
        }
    }

    public static List<Throwable> getCausalChain(Throwable throwable) {
        Preconditions.checkNotNull(throwable);
        List<Throwable> causes = new ArrayList<>(4);
        causes.add(throwable);
        Throwable slowPointer = throwable;
        boolean advanceSlowPointer = false;
        while (true) {
            Throwable cause = throwable.getCause();
            Throwable cause2 = cause;
            if (cause == null) {
                return Collections.unmodifiableList(causes);
            }
            throwable = cause2;
            causes.add(throwable);
            if (throwable != slowPointer) {
                if (advanceSlowPointer) {
                    slowPointer = slowPointer.getCause();
                }
                advanceSlowPointer = !advanceSlowPointer;
            } else {
                throw new IllegalArgumentException("Loop in causal chain detected.", throwable);
            }
        }
    }

    public static <X extends Throwable> X getCauseAs(Throwable throwable, Class<X> expectedCauseType) {
        try {
            return (Throwable) expectedCauseType.cast(throwable.getCause());
        } catch (ClassCastException e) {
            e.initCause(throwable);
            throw e;
        }
    }

    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static List<StackTraceElement> lazyStackTrace(Throwable throwable) {
        if (lazyStackTraceIsLazy()) {
            return jlaStackTrace(throwable);
        }
        return Collections.unmodifiableList(Arrays.asList(throwable.getStackTrace()));
    }

    public static boolean lazyStackTraceIsLazy() {
        return (getStackTraceElementMethod == null || getStackTraceDepthMethod == null) ? false : true;
    }

    private static List<StackTraceElement> jlaStackTrace(final Throwable t) {
        Preconditions.checkNotNull(t);
        return new AbstractList<StackTraceElement>() {
            public StackTraceElement get(int n) {
                return (StackTraceElement) Throwables.invokeAccessibleNonThrowingMethod(Throwables.getStackTraceElementMethod, Throwables.jla, t, Integer.valueOf(n));
            }

            public int size() {
                return ((Integer) Throwables.invokeAccessibleNonThrowingMethod(Throwables.getStackTraceDepthMethod, Throwables.jla, t)).intValue();
            }
        };
    }

    /* access modifiers changed from: private */
    public static Object invokeAccessibleNonThrowingMethod(Method method, Object receiver, Object... params) {
        try {
            return method.invoke(receiver, params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw propagate(e2.getCause());
        }
    }

    static {
        Object jla2 = getJLA();
        jla = jla2;
        Method method = null;
        getStackTraceElementMethod = jla2 == null ? null : getGetMethod();
        if (jla2 != null) {
            method = getSizeMethod();
        }
        getStackTraceDepthMethod = method;
    }

    @NullableDecl
    private static Object getJLA() {
        try {
            return Class.forName(SHARED_SECRETS_CLASSNAME, false, (ClassLoader) null).getMethod("getJavaLangAccess", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (ThreadDeath death) {
            throw death;
        } catch (Throwable th) {
            return null;
        }
    }

    @NullableDecl
    private static Method getGetMethod() {
        return getJlaMethod("getStackTraceElement", Throwable.class, Integer.TYPE);
    }

    @NullableDecl
    private static Method getSizeMethod() {
        try {
            Method getStackTraceDepth = getJlaMethod("getStackTraceDepth", Throwable.class);
            if (getStackTraceDepth == null) {
                return null;
            }
            getStackTraceDepth.invoke(getJLA(), new Object[]{new Throwable()});
            return getStackTraceDepth;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException e) {
            return null;
        }
    }

    @NullableDecl
    private static Method getJlaMethod(String name, Class<?>... parameterTypes) throws ThreadDeath {
        try {
            return Class.forName(JAVA_LANG_ACCESS_CLASSNAME, false, (ClassLoader) null).getMethod(name, parameterTypes);
        } catch (ThreadDeath death) {
            throw death;
        } catch (Throwable th) {
            return null;
        }
    }
}
