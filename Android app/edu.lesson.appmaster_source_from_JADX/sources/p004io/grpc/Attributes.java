package p004io.grpc;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: io.grpc.Attributes */
public final class Attributes {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Attributes EMPTY = new Attributes(Collections.emptyMap());
    /* access modifiers changed from: private */
    public final Map<Key<?>, Object> data;

    private Attributes(Map<Key<?>, Object> data2) {
        if (data2 != null) {
            this.data = data2;
            return;
        }
        throw new AssertionError();
    }

    @Nullable
    public <T> T get(Key<T> key) {
        return this.data.get(key);
    }

    @Deprecated
    public Set<Key<?>> keys() {
        return Collections.unmodifiableSet(this.data.keySet());
    }

    /* access modifiers changed from: package-private */
    public Set<Key<?>> keysForTest() {
        return Collections.unmodifiableSet(this.data.keySet());
    }

    @Deprecated
    public static Builder newBuilder(Attributes base) {
        Preconditions.checkNotNull(base, "base");
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    /* renamed from: io.grpc.Attributes$Key */
    public static final class Key<T> {
        private final String debugString;

        private Key(String debugString2) {
            this.debugString = debugString2;
        }

        public String toString() {
            return this.debugString;
        }

        @Deprecated
        /* renamed from: of */
        public static <T> Key<T> m344of(String debugString2) {
            return new Key<>(debugString2);
        }

        public static <T> Key<T> create(String debugString2) {
            return new Key<>(debugString2);
        }
    }

    public String toString() {
        return this.data.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 1
            if (r8 != r9) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r9 == 0) goto L_0x005e
            java.lang.Class r2 = r8.getClass()
            java.lang.Class r3 = r9.getClass()
            if (r2 == r3) goto L_0x0012
            goto L_0x005e
        L_0x0012:
            r2 = r9
            io.grpc.Attributes r2 = (p004io.grpc.Attributes) r2
            java.util.Map<io.grpc.Attributes$Key<?>, java.lang.Object> r3 = r8.data
            int r3 = r3.size()
            java.util.Map<io.grpc.Attributes$Key<?>, java.lang.Object> r4 = r2.data
            int r4 = r4.size()
            if (r3 == r4) goto L_0x0024
            return r1
        L_0x0024:
            java.util.Map<io.grpc.Attributes$Key<?>, java.lang.Object> r3 = r8.data
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x002e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x005d
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.util.Map<io.grpc.Attributes$Key<?>, java.lang.Object> r5 = r2.data
            java.lang.Object r6 = r4.getKey()
            boolean r5 = r5.containsKey(r6)
            if (r5 != 0) goto L_0x0047
            return r1
        L_0x0047:
            java.lang.Object r5 = r4.getValue()
            java.util.Map<io.grpc.Attributes$Key<?>, java.lang.Object> r6 = r2.data
            java.lang.Object r7 = r4.getKey()
            java.lang.Object r6 = r6.get(r7)
            boolean r5 = com.google.common.base.Objects.equal(r5, r6)
            if (r5 != 0) goto L_0x005c
            return r1
        L_0x005c:
            goto L_0x002e
        L_0x005d:
            return r0
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.Attributes.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode = 0;
        for (Map.Entry<Key<?>, Object> e : this.data.entrySet()) {
            hashCode += Objects.hashCode(e.getKey(), e.getValue());
        }
        return hashCode;
    }

    /* renamed from: io.grpc.Attributes$Builder */
    public static final class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Attributes base;
        private Map<Key<?>, Object> newdata;

        static {
            Class<Attributes> cls = Attributes.class;
        }

        private Builder(Attributes base2) {
            if (base2 != null) {
                this.base = base2;
                return;
            }
            throw new AssertionError();
        }

        private Map<Key<?>, Object> data(int size) {
            if (this.newdata == null) {
                this.newdata = new IdentityHashMap(size);
            }
            return this.newdata;
        }

        public <T> Builder set(Key<T> key, T value) {
            data(1).put(key, value);
            return this;
        }

        public <T> Builder discard(Key<T> key) {
            if (this.base.data.containsKey(key)) {
                Map<Key<?>, Object> newBaseData = new IdentityHashMap<>(this.base.data);
                newBaseData.remove(key);
                this.base = new Attributes(newBaseData);
            }
            Map<Key<?>, Object> newBaseData2 = this.newdata;
            if (newBaseData2 != null) {
                newBaseData2.remove(key);
            }
            return this;
        }

        public Builder setAll(Attributes other) {
            data(other.data.size()).putAll(other.data);
            return this;
        }

        public Attributes build() {
            if (this.newdata != null) {
                for (Map.Entry<Key<?>, Object> entry : this.base.data.entrySet()) {
                    if (!this.newdata.containsKey(entry.getKey())) {
                        this.newdata.put(entry.getKey(), entry.getValue());
                    }
                }
                this.base = new Attributes(this.newdata);
                this.newdata = null;
            }
            return this.base;
        }
    }
}
