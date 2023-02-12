package p004io.grpc;

import java.util.List;
import p004io.grpc.ServiceProviders;

/* renamed from: io.grpc.InternalServiceProviders */
public final class InternalServiceProviders {

    /* renamed from: io.grpc.InternalServiceProviders$PriorityAccessor */
    public interface PriorityAccessor<T> extends ServiceProviders.PriorityAccessor<T> {
    }

    private InternalServiceProviders() {
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Iterable<java.lang.Class<?>>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T load(java.lang.Class<T> r1, java.lang.Iterable<java.lang.Class<?>> r2, java.lang.ClassLoader r3, p004io.grpc.InternalServiceProviders.PriorityAccessor<T> r4) {
        /*
            java.lang.Object r0 = p004io.grpc.ServiceProviders.load(r1, r2, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.InternalServiceProviders.load(java.lang.Class, java.lang.Iterable, java.lang.ClassLoader, io.grpc.InternalServiceProviders$PriorityAccessor):java.lang.Object");
    }

    public static <T> List<T> loadAll(Class<T> klass, Iterable<Class<?>> hardCodedClasses, ClassLoader classLoader, PriorityAccessor<T> priorityAccessor) {
        return ServiceProviders.loadAll(klass, hardCodedClasses, classLoader, priorityAccessor);
    }

    public static <T> Iterable<T> getCandidatesViaServiceLoader(Class<T> klass, ClassLoader cl) {
        return ServiceProviders.getCandidatesViaServiceLoader(klass, cl);
    }

    public static <T> Iterable<T> getCandidatesViaHardCoded(Class<T> klass, Iterable<Class<?>> hardcoded) {
        return ServiceProviders.getCandidatesViaHardCoded(klass, hardcoded);
    }

    public static boolean isAndroid(ClassLoader cl) {
        return ServiceProviders.isAndroid(cl);
    }
}
