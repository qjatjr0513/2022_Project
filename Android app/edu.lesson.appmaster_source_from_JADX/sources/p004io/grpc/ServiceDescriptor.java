package p004io.grpc;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: io.grpc.ServiceDescriptor */
public final class ServiceDescriptor {
    private final Collection<MethodDescriptor<?, ?>> methods;
    private final String name;
    private final Object schemaDescriptor;

    public ServiceDescriptor(String name2, MethodDescriptor<?, ?>... methods2) {
        this(name2, (Collection<MethodDescriptor<?, ?>>) Arrays.asList(methods2));
    }

    public ServiceDescriptor(String name2, Collection<MethodDescriptor<?, ?>> methods2) {
        this(newBuilder(name2).addAllMethods((Collection) Preconditions.checkNotNull(methods2, "methods")));
    }

    private ServiceDescriptor(Builder b) {
        String access$100 = b.name;
        this.name = access$100;
        validateMethodNames(access$100, b.methods);
        this.methods = Collections.unmodifiableList(new ArrayList(b.methods));
        this.schemaDescriptor = b.schemaDescriptor;
    }

    public String getName() {
        return this.name;
    }

    public Collection<MethodDescriptor<?, ?>> getMethods() {
        return this.methods;
    }

    @Nullable
    public Object getSchemaDescriptor() {
        return this.schemaDescriptor;
    }

    static void validateMethodNames(String serviceName, Collection<MethodDescriptor<?, ?>> methods2) {
        Set<String> allNames = new HashSet<>(methods2.size());
        for (MethodDescriptor<?, ?> method : methods2) {
            Preconditions.checkNotNull(method, FirebaseAnalytics.Param.METHOD);
            String methodServiceName = method.getServiceName();
            Preconditions.checkArgument(serviceName.equals(methodServiceName), "service names %s != %s", (Object) methodServiceName, (Object) serviceName);
            Preconditions.checkArgument(allNames.add(method.getFullMethodName()), "duplicate name %s", (Object) method.getFullMethodName());
        }
    }

    public static Builder newBuilder(String name2) {
        return new Builder(name2);
    }

    /* renamed from: io.grpc.ServiceDescriptor$Builder */
    public static final class Builder {
        /* access modifiers changed from: private */
        public List<MethodDescriptor<?, ?>> methods;
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public Object schemaDescriptor;

        private Builder(String name2) {
            this.methods = new ArrayList();
            setName(name2);
        }

        public Builder setName(String name2) {
            this.name = (String) Preconditions.checkNotNull(name2, AppMeasurementSdk.ConditionalUserProperty.NAME);
            return this;
        }

        public Builder addMethod(MethodDescriptor<?, ?> method) {
            this.methods.add((MethodDescriptor) Preconditions.checkNotNull(method, FirebaseAnalytics.Param.METHOD));
            return this;
        }

        /* access modifiers changed from: private */
        public Builder addAllMethods(Collection<MethodDescriptor<?, ?>> methods2) {
            this.methods.addAll(methods2);
            return this;
        }

        public Builder setSchemaDescriptor(@Nullable Object schemaDescriptor2) {
            this.schemaDescriptor = schemaDescriptor2;
            return this;
        }

        public ServiceDescriptor build() {
            return new ServiceDescriptor(this);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, (Object) this.name).add("schemaDescriptor", this.schemaDescriptor).add("methods", (Object) this.methods).omitNullValues().toString();
    }
}
