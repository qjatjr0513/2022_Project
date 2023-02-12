package p004io.grpc;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import p004io.grpc.Attributes;
import p004io.grpc.LoadBalancer;

/* renamed from: io.grpc.InternalConfigSelector */
public abstract class InternalConfigSelector {
    public static final Attributes.Key<InternalConfigSelector> KEY = Attributes.Key.create("io.grpc.config-selector");

    public abstract Result selectConfig(LoadBalancer.PickSubchannelArgs pickSubchannelArgs);

    /* renamed from: io.grpc.InternalConfigSelector$Result */
    public static final class Result {
        private final Object config;
        @Nullable
        public ClientInterceptor interceptor;
        private final Status status;

        private Result(Status status2, Object config2, ClientInterceptor interceptor2) {
            this.status = (Status) Preconditions.checkNotNull(status2, NotificationCompat.CATEGORY_STATUS);
            this.config = config2;
            this.interceptor = interceptor2;
        }

        public static Result forError(Status status2) {
            Preconditions.checkArgument(!status2.isOk(), "status is OK");
            return new Result(status2, (Object) null, (ClientInterceptor) null);
        }

        public Status getStatus() {
            return this.status;
        }

        public Object getConfig() {
            return this.config;
        }

        @Nullable
        public ClientInterceptor getInterceptor() {
            return this.interceptor;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        /* renamed from: io.grpc.InternalConfigSelector$Result$Builder */
        public static final class Builder {
            private Object config;
            private ClientInterceptor interceptor;

            private Builder() {
            }

            public Builder setConfig(Object config2) {
                this.config = Preconditions.checkNotNull(config2, "config");
                return this;
            }

            public Builder setInterceptor(ClientInterceptor interceptor2) {
                this.interceptor = (ClientInterceptor) Preconditions.checkNotNull(interceptor2, "interceptor");
                return this;
            }

            public Result build() {
                Preconditions.checkState(this.config != null, "config is not set");
                return new Result(Status.f313OK, this.config, this.interceptor);
            }
        }
    }
}
