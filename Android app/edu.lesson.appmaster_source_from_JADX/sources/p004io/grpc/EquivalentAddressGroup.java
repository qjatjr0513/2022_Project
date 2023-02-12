package p004io.grpc;

import com.google.common.base.Preconditions;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p004io.grpc.Attributes;

/* renamed from: io.grpc.EquivalentAddressGroup */
public final class EquivalentAddressGroup {
    public static final Attributes.Key<String> ATTR_AUTHORITY_OVERRIDE = Attributes.Key.create("io.grpc.EquivalentAddressGroup.authorityOverride");
    private final List<SocketAddress> addrs;
    private final Attributes attrs;
    private final int hashCode;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: io.grpc.EquivalentAddressGroup$Attr */
    public @interface Attr {
    }

    public EquivalentAddressGroup(List<SocketAddress> addrs2) {
        this(addrs2, Attributes.EMPTY);
    }

    public EquivalentAddressGroup(List<SocketAddress> addrs2, Attributes attrs2) {
        Preconditions.checkArgument(!addrs2.isEmpty(), "addrs is empty");
        List<SocketAddress> unmodifiableList = Collections.unmodifiableList(new ArrayList(addrs2));
        this.addrs = unmodifiableList;
        this.attrs = (Attributes) Preconditions.checkNotNull(attrs2, "attrs");
        this.hashCode = unmodifiableList.hashCode();
    }

    public EquivalentAddressGroup(SocketAddress addr) {
        this(addr, Attributes.EMPTY);
    }

    public EquivalentAddressGroup(SocketAddress addr, Attributes attrs2) {
        this((List<SocketAddress>) Collections.singletonList(addr), attrs2);
    }

    public List<SocketAddress> getAddresses() {
        return this.addrs;
    }

    public Attributes getAttributes() {
        return this.attrs;
    }

    public String toString() {
        return "[" + this.addrs + "/" + this.attrs + "]";
    }

    public int hashCode() {
        return this.hashCode;
    }

    public boolean equals(Object other) {
        if (!(other instanceof EquivalentAddressGroup)) {
            return false;
        }
        EquivalentAddressGroup that = (EquivalentAddressGroup) other;
        if (this.addrs.size() != that.addrs.size()) {
            return false;
        }
        for (int i = 0; i < this.addrs.size(); i++) {
            if (!this.addrs.get(i).equals(that.addrs.get(i))) {
                return false;
            }
        }
        if (!this.attrs.equals(that.attrs)) {
            return false;
        }
        return true;
    }
}
