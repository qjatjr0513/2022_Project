package p004io.grpc;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* renamed from: io.grpc.InternalLogId */
public final class InternalLogId {
    private static final AtomicLong idAlloc = new AtomicLong();
    @Nullable
    private final String details;

    /* renamed from: id */
    private final long f312id;
    private final String typeName;

    public static InternalLogId allocate(Class<?> type, @Nullable String details2) {
        return allocate(getClassName(type), details2);
    }

    public static InternalLogId allocate(String typeName2, @Nullable String details2) {
        return new InternalLogId(typeName2, details2, getNextId());
    }

    static long getNextId() {
        return idAlloc.incrementAndGet();
    }

    InternalLogId(String typeName2, String details2, long id) {
        Preconditions.checkNotNull(typeName2, "typeName");
        Preconditions.checkArgument(!typeName2.isEmpty(), "empty type");
        this.typeName = typeName2;
        this.details = details2;
        this.f312id = id;
    }

    public String getTypeName() {
        return this.typeName;
    }

    @Nullable
    public String getDetails() {
        return this.details;
    }

    public long getId() {
        return this.f312id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(shortName());
        if (this.details != null) {
            sb.append(": (");
            sb.append(this.details);
            sb.append(')');
        }
        return sb.toString();
    }

    private static String getClassName(Class<?> type) {
        String className = ((Class) Preconditions.checkNotNull(type, "type")).getSimpleName();
        if (!className.isEmpty()) {
            return className;
        }
        return type.getName().substring(type.getPackage().getName().length() + 1);
    }

    public String shortName() {
        return this.typeName + "<" + this.f312id + ">";
    }
}
