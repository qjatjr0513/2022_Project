package p004io.grpc.internal;

import java.util.ArrayList;
import javax.annotation.Nullable;

/* renamed from: io.grpc.internal.InsightBuilder */
public final class InsightBuilder {
    private final ArrayList<String> buffer = new ArrayList<>();

    public InsightBuilder append(@Nullable Object insight) {
        this.buffer.add(String.valueOf(insight));
        return this;
    }

    public InsightBuilder appendKeyValue(String key, @Nullable Object value) {
        this.buffer.add(key + "=" + value);
        return this;
    }

    public String toString() {
        return this.buffer.toString();
    }
}
