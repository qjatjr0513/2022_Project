package p004io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

/* renamed from: io.grpc.ClientStreamTracer */
public abstract class ClientStreamTracer extends StreamTracer {
    public void streamCreated(Attributes transportAttrs, Metadata headers) {
    }

    public void outboundHeaders() {
    }

    public void inboundHeaders() {
    }

    public void inboundTrailers(Metadata trailers) {
    }

    /* renamed from: io.grpc.ClientStreamTracer$Factory */
    public static abstract class Factory {
        public ClientStreamTracer newClientStreamTracer(StreamInfo info, Metadata headers) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    /* renamed from: io.grpc.ClientStreamTracer$StreamInfo */
    public static final class StreamInfo {
        private final CallOptions callOptions;
        private final boolean isTransparentRetry;
        private final int previousAttempts;

        StreamInfo(CallOptions callOptions2, int previousAttempts2, boolean isTransparentRetry2) {
            this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions2, "callOptions");
            this.previousAttempts = previousAttempts2;
            this.isTransparentRetry = isTransparentRetry2;
        }

        public CallOptions getCallOptions() {
            return this.callOptions;
        }

        public int getPreviousAttempts() {
            return this.previousAttempts;
        }

        public boolean isTransparentRetry() {
            return this.isTransparentRetry;
        }

        public Builder toBuilder() {
            return new Builder().setCallOptions(this.callOptions).setPreviousAttempts(this.previousAttempts).setIsTransparentRetry(this.isTransparentRetry);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("callOptions", (Object) this.callOptions).add("previousAttempts", this.previousAttempts).add("isTransparentRetry", this.isTransparentRetry).toString();
        }

        /* renamed from: io.grpc.ClientStreamTracer$StreamInfo$Builder */
        public static final class Builder {
            private CallOptions callOptions = CallOptions.DEFAULT;
            private boolean isTransparentRetry;
            private int previousAttempts;

            Builder() {
            }

            public Builder setCallOptions(CallOptions callOptions2) {
                this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions2, "callOptions cannot be null");
                return this;
            }

            public Builder setPreviousAttempts(int previousAttempts2) {
                this.previousAttempts = previousAttempts2;
                return this;
            }

            public Builder setIsTransparentRetry(boolean isTransparentRetry2) {
                this.isTransparentRetry = isTransparentRetry2;
                return this;
            }

            public StreamInfo build() {
                return new StreamInfo(this.callOptions, this.previousAttempts, this.isTransparentRetry);
            }
        }
    }
}
