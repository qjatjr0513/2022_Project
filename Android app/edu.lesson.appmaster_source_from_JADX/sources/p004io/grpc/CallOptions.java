package p004io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import p004io.grpc.ClientStreamTracer;

@CheckReturnValue
/* renamed from: io.grpc.CallOptions */
public final class CallOptions {
    public static final CallOptions DEFAULT = new CallOptions();
    @Nullable
    private String authority;
    @Nullable
    private String compressorName;
    @Nullable
    private CallCredentials credentials;
    private Object[][] customOptions;
    @Nullable
    private Deadline deadline;
    @Nullable
    private Executor executor;
    @Nullable
    private Integer maxInboundMessageSize;
    @Nullable
    private Integer maxOutboundMessageSize;
    private List<ClientStreamTracer.Factory> streamTracerFactories;
    @Nullable
    private Boolean waitForReady;

    public CallOptions withAuthority(@Nullable String authority2) {
        CallOptions newOptions = new CallOptions(this);
        newOptions.authority = authority2;
        return newOptions;
    }

    public CallOptions withCallCredentials(@Nullable CallCredentials credentials2) {
        CallOptions newOptions = new CallOptions(this);
        newOptions.credentials = credentials2;
        return newOptions;
    }

    public CallOptions withCompression(@Nullable String compressorName2) {
        CallOptions newOptions = new CallOptions(this);
        newOptions.compressorName = compressorName2;
        return newOptions;
    }

    public CallOptions withDeadline(@Nullable Deadline deadline2) {
        CallOptions newOptions = new CallOptions(this);
        newOptions.deadline = deadline2;
        return newOptions;
    }

    public CallOptions withDeadlineAfter(long duration, TimeUnit unit) {
        return withDeadline(Deadline.after(duration, unit));
    }

    @Nullable
    public Deadline getDeadline() {
        return this.deadline;
    }

    public CallOptions withWaitForReady() {
        CallOptions newOptions = new CallOptions(this);
        newOptions.waitForReady = Boolean.TRUE;
        return newOptions;
    }

    public CallOptions withoutWaitForReady() {
        CallOptions newOptions = new CallOptions(this);
        newOptions.waitForReady = Boolean.FALSE;
        return newOptions;
    }

    @Nullable
    public String getCompressor() {
        return this.compressorName;
    }

    @Nullable
    public String getAuthority() {
        return this.authority;
    }

    @Nullable
    public CallCredentials getCredentials() {
        return this.credentials;
    }

    public CallOptions withExecutor(@Nullable Executor executor2) {
        CallOptions newOptions = new CallOptions(this);
        newOptions.executor = executor2;
        return newOptions;
    }

    public CallOptions withStreamTracerFactory(ClientStreamTracer.Factory factory) {
        CallOptions newOptions = new CallOptions(this);
        ArrayList<ClientStreamTracer.Factory> newList = new ArrayList<>(this.streamTracerFactories.size() + 1);
        newList.addAll(this.streamTracerFactories);
        newList.add(factory);
        newOptions.streamTracerFactories = Collections.unmodifiableList(newList);
        return newOptions;
    }

    public List<ClientStreamTracer.Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }

    /* renamed from: io.grpc.CallOptions$Key */
    public static final class Key<T> {
        private final String debugString;
        /* access modifiers changed from: private */
        public final T defaultValue;

        private Key(String debugString2, T defaultValue2) {
            this.debugString = debugString2;
            this.defaultValue = defaultValue2;
        }

        public T getDefault() {
            return this.defaultValue;
        }

        public String toString() {
            return this.debugString;
        }

        @Deprecated
        /* renamed from: of */
        public static <T> Key<T> m345of(String debugString2, T defaultValue2) {
            Preconditions.checkNotNull(debugString2, "debugString");
            return new Key<>(debugString2, defaultValue2);
        }

        public static <T> Key<T> create(String debugString2) {
            Preconditions.checkNotNull(debugString2, "debugString");
            return new Key<>(debugString2, (Object) null);
        }

        public static <T> Key<T> createWithDefault(String debugString2, T defaultValue2) {
            Preconditions.checkNotNull(debugString2, "debugString");
            return new Key<>(debugString2, defaultValue2);
        }
    }

    public <T> CallOptions withOption(Key<T> key, T value) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(value, "value");
        CallOptions newOptions = new CallOptions(this);
        int existingIdx = -1;
        int i = 0;
        while (true) {
            Object[][] objArr = this.customOptions;
            if (i >= objArr.length) {
                break;
            } else if (key.equals(objArr[i][0])) {
                existingIdx = i;
                break;
            } else {
                i++;
            }
        }
        int length = this.customOptions.length;
        int i2 = existingIdx == -1 ? 1 : 0;
        int[] iArr = new int[2];
        iArr[1] = 2;
        iArr[0] = length + i2;
        Object[][] objArr2 = (Object[][]) Array.newInstance(Object.class, iArr);
        newOptions.customOptions = objArr2;
        Object[][] objArr3 = this.customOptions;
        System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
        if (existingIdx == -1) {
            newOptions.customOptions[this.customOptions.length] = new Object[]{key, value};
        } else {
            newOptions.customOptions[existingIdx] = new Object[]{key, value};
        }
        return newOptions;
    }

    public <T> T getOption(Key<T> key) {
        Preconditions.checkNotNull(key, "key");
        int i = 0;
        while (true) {
            Object[][] objArr = this.customOptions;
            if (i >= objArr.length) {
                return key.defaultValue;
            }
            if (key.equals(objArr[i][0])) {
                return this.customOptions[i][1];
            }
            i++;
        }
    }

    @Nullable
    public Executor getExecutor() {
        return this.executor;
    }

    private CallOptions() {
        this.streamTracerFactories = Collections.emptyList();
        this.customOptions = (Object[][]) Array.newInstance(Object.class, new int[]{0, 2});
    }

    public boolean isWaitForReady() {
        return Boolean.TRUE.equals(this.waitForReady);
    }

    /* access modifiers changed from: package-private */
    public Boolean getWaitForReady() {
        return this.waitForReady;
    }

    public CallOptions withMaxInboundMessageSize(int maxSize) {
        Preconditions.checkArgument(maxSize >= 0, "invalid maxsize %s", maxSize);
        CallOptions newOptions = new CallOptions(this);
        newOptions.maxInboundMessageSize = Integer.valueOf(maxSize);
        return newOptions;
    }

    public CallOptions withMaxOutboundMessageSize(int maxSize) {
        Preconditions.checkArgument(maxSize >= 0, "invalid maxsize %s", maxSize);
        CallOptions newOptions = new CallOptions(this);
        newOptions.maxOutboundMessageSize = Integer.valueOf(maxSize);
        return newOptions;
    }

    @Nullable
    public Integer getMaxInboundMessageSize() {
        return this.maxInboundMessageSize;
    }

    @Nullable
    public Integer getMaxOutboundMessageSize() {
        return this.maxOutboundMessageSize;
    }

    private CallOptions(CallOptions other) {
        this.streamTracerFactories = Collections.emptyList();
        this.deadline = other.deadline;
        this.authority = other.authority;
        this.credentials = other.credentials;
        this.executor = other.executor;
        this.compressorName = other.compressorName;
        this.customOptions = other.customOptions;
        this.waitForReady = other.waitForReady;
        this.maxInboundMessageSize = other.maxInboundMessageSize;
        this.maxOutboundMessageSize = other.maxOutboundMessageSize;
        this.streamTracerFactories = other.streamTracerFactories;
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper((Object) this).add("deadline", (Object) this.deadline).add("authority", (Object) this.authority).add("callCredentials", (Object) this.credentials);
        Executor executor2 = this.executor;
        return add.add("executor", (Object) executor2 != null ? executor2.getClass() : null).add("compressorName", (Object) this.compressorName).add("customOptions", (Object) Arrays.deepToString(this.customOptions)).add("waitForReady", isWaitForReady()).add("maxInboundMessageSize", (Object) this.maxInboundMessageSize).add("maxOutboundMessageSize", (Object) this.maxOutboundMessageSize).add("streamTracerFactories", (Object) this.streamTracerFactories).toString();
    }
}
