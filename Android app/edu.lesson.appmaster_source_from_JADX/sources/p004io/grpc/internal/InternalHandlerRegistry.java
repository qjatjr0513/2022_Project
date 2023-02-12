package p004io.grpc.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import p004io.grpc.HandlerRegistry;
import p004io.grpc.ServerMethodDefinition;
import p004io.grpc.ServerServiceDefinition;

/* renamed from: io.grpc.internal.InternalHandlerRegistry */
final class InternalHandlerRegistry extends HandlerRegistry {
    private final Map<String, ServerMethodDefinition<?, ?>> methods;
    private final List<ServerServiceDefinition> services;

    private InternalHandlerRegistry(List<ServerServiceDefinition> services2, Map<String, ServerMethodDefinition<?, ?>> methods2) {
        this.services = services2;
        this.methods = methods2;
    }

    public List<ServerServiceDefinition> getServices() {
        return this.services;
    }

    @Nullable
    public ServerMethodDefinition<?, ?> lookupMethod(String methodName, @Nullable String authority) {
        return this.methods.get(methodName);
    }

    /* renamed from: io.grpc.internal.InternalHandlerRegistry$Builder */
    static final class Builder {
        private final HashMap<String, ServerServiceDefinition> services = new LinkedHashMap();

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public Builder addService(ServerServiceDefinition service) {
            this.services.put(service.getServiceDescriptor().getName(), service);
            return this;
        }

        /* access modifiers changed from: package-private */
        public InternalHandlerRegistry build() {
            Map<String, ServerMethodDefinition<?, ?>> map = new HashMap<>();
            for (ServerServiceDefinition service : this.services.values()) {
                for (ServerMethodDefinition<?, ?> method : service.getMethods()) {
                    map.put(method.getMethodDescriptor().getFullMethodName(), method);
                }
            }
            return new InternalHandlerRegistry(Collections.unmodifiableList(new ArrayList(this.services.values())), Collections.unmodifiableMap(map));
        }
    }
}
