package p004io.grpc;

import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import p004io.grpc.CallCredentials;

/* renamed from: io.grpc.CompositeCallCredentials */
public final class CompositeCallCredentials extends CallCredentials {
    private final CallCredentials credentials1;
    /* access modifiers changed from: private */
    public final CallCredentials credentials2;

    public CompositeCallCredentials(CallCredentials creds1, CallCredentials creds2) {
        this.credentials1 = (CallCredentials) Preconditions.checkNotNull(creds1, "creds1");
        this.credentials2 = (CallCredentials) Preconditions.checkNotNull(creds2, "creds2");
    }

    public void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor appExecutor, CallCredentials.MetadataApplier applier) {
        this.credentials1.applyRequestMetadata(requestInfo, appExecutor, new WrappingMetadataApplier(requestInfo, appExecutor, applier, Context.current()));
    }

    public void thisUsesUnstableApi() {
    }

    /* renamed from: io.grpc.CompositeCallCredentials$WrappingMetadataApplier */
    private final class WrappingMetadataApplier extends CallCredentials.MetadataApplier {
        private final Executor appExecutor;
        private final Context context;
        private final CallCredentials.MetadataApplier delegate;
        private final CallCredentials.RequestInfo requestInfo;

        public WrappingMetadataApplier(CallCredentials.RequestInfo requestInfo2, Executor appExecutor2, CallCredentials.MetadataApplier delegate2, Context context2) {
            this.requestInfo = requestInfo2;
            this.appExecutor = appExecutor2;
            this.delegate = (CallCredentials.MetadataApplier) Preconditions.checkNotNull(delegate2, "delegate");
            this.context = (Context) Preconditions.checkNotNull(context2, "context");
        }

        public void apply(Metadata headers) {
            Preconditions.checkNotNull(headers, "headers");
            Context previous = this.context.attach();
            try {
                CompositeCallCredentials.this.credentials2.applyRequestMetadata(this.requestInfo, this.appExecutor, new CombiningMetadataApplier(this.delegate, headers));
            } finally {
                this.context.detach(previous);
            }
        }

        public void fail(Status status) {
            this.delegate.fail(status);
        }
    }

    /* renamed from: io.grpc.CompositeCallCredentials$CombiningMetadataApplier */
    private static final class CombiningMetadataApplier extends CallCredentials.MetadataApplier {
        private final CallCredentials.MetadataApplier delegate;
        private final Metadata firstHeaders;

        public CombiningMetadataApplier(CallCredentials.MetadataApplier delegate2, Metadata firstHeaders2) {
            this.delegate = delegate2;
            this.firstHeaders = firstHeaders2;
        }

        public void apply(Metadata headers) {
            Preconditions.checkNotNull(headers, "headers");
            Metadata combined = new Metadata();
            combined.merge(this.firstHeaders);
            combined.merge(headers);
            this.delegate.apply(combined);
        }

        public void fail(Status status) {
            this.delegate.fail(status);
        }
    }
}
