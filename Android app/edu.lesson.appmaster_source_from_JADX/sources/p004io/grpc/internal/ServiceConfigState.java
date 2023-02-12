package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import p004io.grpc.NameResolver;

/* renamed from: io.grpc.internal.ServiceConfigState */
final class ServiceConfigState {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    @Nullable
    private NameResolver.ConfigOrError currentServiceConfigOrError;
    @Nullable
    private final NameResolver.ConfigOrError defaultServiceConfig;
    private final boolean lookUpServiceConfig;
    private boolean updated;

    ServiceConfigState(@Nullable ManagedChannelServiceConfig defaultServiceConfig2, boolean lookUpServiceConfig2) {
        if (defaultServiceConfig2 == null) {
            this.defaultServiceConfig = null;
        } else {
            this.defaultServiceConfig = NameResolver.ConfigOrError.fromConfig(defaultServiceConfig2);
        }
        this.lookUpServiceConfig = lookUpServiceConfig2;
        if (!lookUpServiceConfig2) {
            this.currentServiceConfigOrError = this.defaultServiceConfig;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldWaitOnServiceConfig() {
        return !this.updated && expectUpdates();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public NameResolver.ConfigOrError getCurrent() {
        Preconditions.checkState(!shouldWaitOnServiceConfig(), "still waiting on service config");
        return this.currentServiceConfigOrError;
    }

    /* access modifiers changed from: package-private */
    public void update(@Nullable NameResolver.ConfigOrError coe) {
        Preconditions.checkState(expectUpdates(), "unexpected service config update");
        boolean firstUpdate = !this.updated;
        this.updated = true;
        if (firstUpdate) {
            if (coe == null) {
                this.currentServiceConfigOrError = this.defaultServiceConfig;
            } else if (coe.getError() != null) {
                NameResolver.ConfigOrError configOrError = this.defaultServiceConfig;
                if (configOrError != null) {
                    this.currentServiceConfigOrError = configOrError;
                } else {
                    this.currentServiceConfigOrError = coe;
                }
            } else if (coe.getConfig() != null) {
                this.currentServiceConfigOrError = coe;
            } else {
                throw new AssertionError();
            }
        } else if (coe == null) {
            NameResolver.ConfigOrError configOrError2 = this.defaultServiceConfig;
            if (configOrError2 != null) {
                this.currentServiceConfigOrError = configOrError2;
            } else {
                this.currentServiceConfigOrError = null;
            }
        } else if (coe.getError() != null) {
            NameResolver.ConfigOrError configOrError3 = this.currentServiceConfigOrError;
            if (configOrError3 != null && configOrError3.getError() != null) {
                this.currentServiceConfigOrError = coe;
            }
        } else if (coe.getConfig() != null) {
            this.currentServiceConfigOrError = coe;
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean expectUpdates() {
        return this.lookUpServiceConfig;
    }
}
