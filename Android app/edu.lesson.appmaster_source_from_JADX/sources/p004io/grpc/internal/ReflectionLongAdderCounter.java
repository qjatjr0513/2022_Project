package p004io.grpc.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: io.grpc.internal.ReflectionLongAdderCounter */
public final class ReflectionLongAdderCounter implements LongCounter {
    private static final Method addMethod;
    private static final Constructor<?> defaultConstructor;
    private static final RuntimeException initializationException;
    private static final Logger logger = Logger.getLogger(ReflectionLongAdderCounter.class.getName());
    private static final Object[] one = {1L};
    private static final Method sumMethod;
    private final Object instance;

    static {
        Constructor<?> defaultConstructorLookup = null;
        Method addMethodLookup = null;
        Method sumMethodLookup = null;
        Throwable caught = null;
        try {
            Class<?> klass = Class.forName("java.util.concurrent.atomic.LongAdder");
            addMethodLookup = klass.getMethod("add", new Class[]{Long.TYPE});
            sumMethodLookup = klass.getMethod("sum", new Class[0]);
            Constructor<?>[] constructors = klass.getConstructors();
            int length = constructors.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Constructor<?> ctor = constructors[i];
                if (ctor.getParameterTypes().length == 0) {
                    defaultConstructorLookup = ctor;
                    break;
                }
                i++;
            }
        } catch (Throwable e) {
            logger.log(Level.FINE, "LongAdder can not be found via reflection, this is normal for JDK7 and below", e);
            caught = e;
        }
        if (caught != null || defaultConstructorLookup == null) {
            defaultConstructor = null;
            addMethod = null;
            sumMethod = null;
            initializationException = new RuntimeException(caught);
        } else {
            defaultConstructor = defaultConstructorLookup;
            addMethod = addMethodLookup;
            sumMethod = sumMethodLookup;
            initializationException = null;
        }
    }

    ReflectionLongAdderCounter() {
        RuntimeException runtimeException = initializationException;
        if (runtimeException == null) {
            try {
                this.instance = defaultConstructor.newInstance(new Object[0]);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3);
            }
        } else {
            throw runtimeException;
        }
    }

    static boolean isAvailable() {
        return initializationException == null;
    }

    public void add(long delta) {
        try {
            addMethod.invoke(this.instance, delta == 1 ? one : new Object[]{Long.valueOf(delta)});
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public long value() {
        try {
            return ((Long) sumMethod.invoke(this.instance, new Object[0])).longValue();
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        } catch (InvocationTargetException e2) {
            throw new RuntimeException();
        }
    }
}
