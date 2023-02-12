package p004io.grpc;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.p000io.BaseEncoding;
import com.google.common.p000io.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* renamed from: io.grpc.Metadata */
public final class Metadata {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final AsciiMarshaller<String> ASCII_STRING_MARSHALLER = new AsciiMarshaller<String>() {
        public String toAsciiString(String value) {
            return value;
        }

        public String parseAsciiString(String serialized) {
            return serialized;
        }
    };
    static final BaseEncoding BASE64_ENCODING_OMIT_PADDING = BaseEncoding.base64().omitPadding();
    public static final BinaryMarshaller<byte[]> BINARY_BYTE_MARSHALLER = new BinaryMarshaller<byte[]>() {
        public byte[] toBytes(byte[] value) {
            return value;
        }

        public byte[] parseBytes(byte[] serialized) {
            return serialized;
        }
    };
    public static final String BINARY_HEADER_SUFFIX = "-bin";
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(Metadata.class.getName());
    private Object[] namesAndValues;
    /* access modifiers changed from: private */
    public int size;

    /* renamed from: io.grpc.Metadata$AsciiMarshaller */
    public interface AsciiMarshaller<T> {
        T parseAsciiString(String str);

        String toAsciiString(T t);
    }

    /* renamed from: io.grpc.Metadata$BinaryMarshaller */
    public interface BinaryMarshaller<T> {
        T parseBytes(byte[] bArr);

        byte[] toBytes(T t);
    }

    /* renamed from: io.grpc.Metadata$BinaryStreamMarshaller */
    public interface BinaryStreamMarshaller<T> {
        T parseStream(InputStream inputStream);

        InputStream toStream(T t);
    }

    /* renamed from: io.grpc.Metadata$TrustedAsciiMarshaller */
    interface TrustedAsciiMarshaller<T> {
        T parseAsciiString(byte[] bArr);

        byte[] toAsciiString(T t);
    }

    Metadata(byte[]... binaryValues) {
        this(binaryValues.length / 2, binaryValues);
    }

    Metadata(int usedNames, byte[]... binaryValues) {
        this(usedNames, (Object[]) binaryValues);
    }

    Metadata(int usedNames, Object[] namesAndValues2) {
        if ((namesAndValues2.length & 1) == 0) {
            this.size = usedNames;
            this.namesAndValues = namesAndValues2;
            return;
        }
        throw new AssertionError("Odd number of key-value pairs " + namesAndValues2.length);
    }

    /* access modifiers changed from: private */
    public byte[] name(int i) {
        return (byte[]) this.namesAndValues[i * 2];
    }

    private void name(int i, byte[] name) {
        this.namesAndValues[i * 2] = name;
    }

    private Object value(int i) {
        return this.namesAndValues[(i * 2) + 1];
    }

    private void value(int i, byte[] value) {
        this.namesAndValues[(i * 2) + 1] = value;
    }

    private void value(int i, Object value) {
        if (this.namesAndValues instanceof byte[][]) {
            expand(cap());
        }
        this.namesAndValues[(i * 2) + 1] = value;
    }

    private byte[] valueAsBytes(int i) {
        Object value = value(i);
        if (value instanceof byte[]) {
            return (byte[]) value;
        }
        return ((LazyValue) value).toBytes();
    }

    private Object valueAsBytesOrStream(int i) {
        Object value = value(i);
        if (value instanceof byte[]) {
            return value;
        }
        return ((LazyValue) value).toStream();
    }

    /* access modifiers changed from: private */
    public <T> T valueAsT(int i, Key<T> key) {
        Object value = value(i);
        if (value instanceof byte[]) {
            return key.parseBytes((byte[]) value);
        }
        return ((LazyValue) value).toObject(key);
    }

    private int cap() {
        Object[] objArr = this.namesAndValues;
        if (objArr != null) {
            return objArr.length;
        }
        return 0;
    }

    private int len() {
        return this.size * 2;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    public Metadata() {
    }

    /* access modifiers changed from: package-private */
    public int headerCount() {
        return this.size;
    }

    public boolean containsKey(Key<?> key) {
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public <T> T get(Key<T> key) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return valueAsT(i, key);
            }
        }
        return null;
    }

    /* renamed from: io.grpc.Metadata$IterableAt */
    private final class IterableAt<T> implements Iterable<T> {
        /* access modifiers changed from: private */
        public final Key<T> key;
        /* access modifiers changed from: private */
        public int startIdx;

        private IterableAt(Key<T> key2, int startIdx2) {
            this.key = key2;
            this.startIdx = startIdx2;
        }

        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private boolean hasNext = true;
                private int idx;

                {
                    this.idx = IterableAt.this.startIdx;
                }

                public boolean hasNext() {
                    if (this.hasNext) {
                        return true;
                    }
                    while (this.idx < Metadata.this.size) {
                        if (Metadata.this.bytesEqual(IterableAt.this.key.asciiName(), Metadata.this.name(this.idx))) {
                            this.hasNext = true;
                            return true;
                        }
                        this.idx++;
                    }
                    return false;
                }

                public T next() {
                    if (hasNext()) {
                        this.hasNext = false;
                        Metadata metadata = Metadata.this;
                        int i = this.idx;
                        this.idx = i + 1;
                        return metadata.valueAsT(i, IterableAt.this.key);
                    }
                    throw new NoSuchElementException();
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    @Nullable
    public <T> Iterable<T> getAll(Key<T> key) {
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i))) {
                return new IterableAt(key, i);
            }
        }
        return null;
    }

    public Set<String> keys() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        Set<String> ks = new HashSet<>(this.size);
        for (int i = 0; i < this.size; i++) {
            ks.add(new String(name(i), 0));
        }
        return Collections.unmodifiableSet(ks);
    }

    public <T> void put(Key<T> key, T value) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(value, "value");
        maybeExpand();
        name(this.size, key.asciiName());
        if (key.serializesToStreams()) {
            value(this.size, (Object) LazyValue.create(key, value));
        } else {
            value(this.size, key.toBytes(value));
        }
        this.size++;
    }

    private void maybeExpand() {
        if (len() == 0 || len() == cap()) {
            expand(Math.max(len() * 2, 8));
        }
    }

    private void expand(int newCapacity) {
        Object[] newNamesAndValues = new Object[newCapacity];
        if (!isEmpty()) {
            System.arraycopy(this.namesAndValues, 0, newNamesAndValues, 0, len());
        }
        this.namesAndValues = newNamesAndValues;
    }

    public <T> boolean remove(Key<T> key, T value) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(value, "value");
        for (int i = 0; i < this.size; i++) {
            if (bytesEqual(key.asciiName(), name(i)) && value.equals(valueAsT(i, key))) {
                int readIdx = (i + 1) * 2;
                Object[] objArr = this.namesAndValues;
                System.arraycopy(objArr, readIdx, objArr, i * 2, len() - readIdx);
                int i2 = this.size - 1;
                this.size = i2;
                name(i2, (byte[]) null);
                value(this.size, (byte[]) null);
                return true;
            }
        }
        return false;
    }

    public <T> Iterable<T> removeAll(Key<T> key) {
        if (isEmpty()) {
            return null;
        }
        int writeIdx = 0;
        List<T> ret = null;
        for (int readIdx = 0; readIdx < this.size; readIdx++) {
            if (bytesEqual(key.asciiName(), name(readIdx))) {
                ret = ret != null ? ret : new ArrayList<>();
                ret.add(valueAsT(readIdx, key));
            } else {
                name(writeIdx, name(readIdx));
                value(writeIdx, value(readIdx));
                writeIdx++;
            }
        }
        Arrays.fill(this.namesAndValues, writeIdx * 2, len(), (Object) null);
        this.size = writeIdx;
        return ret;
    }

    public <T> void discardAll(Key<T> key) {
        if (!isEmpty()) {
            int writeIdx = 0;
            for (int readIdx = 0; readIdx < this.size; readIdx++) {
                if (!bytesEqual(key.asciiName(), name(readIdx))) {
                    name(writeIdx, name(readIdx));
                    value(writeIdx, value(readIdx));
                    writeIdx++;
                }
            }
            Arrays.fill(this.namesAndValues, writeIdx * 2, len(), (Object) null);
            this.size = writeIdx;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public byte[][] serialize() {
        byte[][] serialized = new byte[len()][];
        Object[] objArr = this.namesAndValues;
        if (objArr instanceof byte[][]) {
            System.arraycopy(objArr, 0, serialized, 0, len());
        } else {
            for (int i = 0; i < this.size; i++) {
                serialized[i * 2] = name(i);
                serialized[(i * 2) + 1] = valueAsBytes(i);
            }
        }
        return serialized;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Object[] serializePartial() {
        Object[] serialized = new Object[len()];
        for (int i = 0; i < this.size; i++) {
            serialized[i * 2] = name(i);
            serialized[(i * 2) + 1] = valueAsBytesOrStream(i);
        }
        return serialized;
    }

    public void merge(Metadata other) {
        if (!other.isEmpty()) {
            int remaining = cap() - len();
            if (isEmpty() || remaining < other.len()) {
                expand(len() + other.len());
            }
            System.arraycopy(other.namesAndValues, 0, this.namesAndValues, len(), other.len());
            this.size += other.size;
        }
    }

    public void merge(Metadata other, Set<Key<?>> keys) {
        Preconditions.checkNotNull(other, "other");
        Map<ByteBuffer, Key<?>> asciiKeys = new HashMap<>(keys.size());
        for (Key<?> key : keys) {
            asciiKeys.put(ByteBuffer.wrap(key.asciiName()), key);
        }
        for (int i = 0; i < other.size; i++) {
            if (asciiKeys.containsKey(ByteBuffer.wrap(other.name(i)))) {
                maybeExpand();
                name(this.size, other.name(i));
                value(this.size, other.value(i));
                this.size++;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Metadata(");
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                sb.append(',');
            }
            String headerName = new String(name(i), Charsets.US_ASCII);
            sb.append(headerName).append('=');
            if (headerName.endsWith(BINARY_HEADER_SUFFIX)) {
                sb.append(BASE64_ENCODING_OMIT_PADDING.encode(valueAsBytes(i)));
            } else {
                sb.append(new String(valueAsBytes(i), Charsets.US_ASCII));
            }
        }
        return sb.append(')').toString();
    }

    /* access modifiers changed from: private */
    public boolean bytesEqual(byte[] left, byte[] right) {
        return Arrays.equals(left, right);
    }

    /* renamed from: io.grpc.Metadata$Key */
    public static abstract class Key<T> {
        private static final BitSet VALID_T_CHARS = generateValidTChars();
        private final Object marshaller;
        private final String name;
        private final byte[] nameBytes;
        private final String originalName;

        /* access modifiers changed from: package-private */
        public abstract T parseBytes(byte[] bArr);

        /* access modifiers changed from: package-private */
        public abstract byte[] toBytes(T t);

        /* renamed from: of */
        public static <T> Key<T> m348of(String name2, BinaryMarshaller<T> marshaller2) {
            return new BinaryKey(name2, marshaller2);
        }

        /* renamed from: of */
        public static <T> Key<T> m349of(String name2, BinaryStreamMarshaller<T> marshaller2) {
            return new LazyStreamBinaryKey(name2, marshaller2);
        }

        /* renamed from: of */
        public static <T> Key<T> m347of(String name2, AsciiMarshaller<T> marshaller2) {
            return m350of(name2, false, marshaller2);
        }

        /* renamed from: of */
        static <T> Key<T> m350of(String name2, boolean pseudo, AsciiMarshaller<T> marshaller2) {
            return new AsciiKey(name2, pseudo, marshaller2);
        }

        /* renamed from: of */
        static <T> Key<T> m351of(String name2, boolean pseudo, TrustedAsciiMarshaller<T> marshaller2) {
            return new TrustedAsciiKey(name2, pseudo, marshaller2);
        }

        private static BitSet generateValidTChars() {
            BitSet valid = new BitSet(127);
            valid.set(45);
            valid.set(95);
            valid.set(46);
            for (char c = '0'; c <= '9'; c = (char) (c + 1)) {
                valid.set(c);
            }
            for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
                valid.set(c2);
            }
            return valid;
        }

        private static String validateName(String n, boolean pseudo) {
            Preconditions.checkNotNull(n, AppMeasurementSdk.ConditionalUserProperty.NAME);
            Preconditions.checkArgument(!n.isEmpty(), "token must have at least 1 tchar");
            if (n.equals("connection")) {
                Metadata.logger.log(Level.WARNING, "Metadata key is 'Connection', which should not be used. That is used by HTTP/1 for connection-specific headers which are not to be forwarded. There is probably an HTTP/1 conversion bug. Simply removing the Connection header is not enough; you should remove all headers it references as well. See RFC 7230 section 6.1", new RuntimeException("exception to show backtrace"));
            }
            for (int i = 0; i < n.length(); i++) {
                char tChar = n.charAt(i);
                if (!pseudo || tChar != ':' || i != 0) {
                    Preconditions.checkArgument(VALID_T_CHARS.get(tChar), "Invalid character '%s' in key name '%s'", tChar, (Object) n);
                }
            }
            return n;
        }

        private Key(String name2, boolean pseudo, Object marshaller2) {
            String str = (String) Preconditions.checkNotNull(name2, AppMeasurementSdk.ConditionalUserProperty.NAME);
            this.originalName = str;
            String validateName = validateName(str.toLowerCase(Locale.ROOT), pseudo);
            this.name = validateName;
            this.nameBytes = validateName.getBytes(Charsets.US_ASCII);
            this.marshaller = marshaller2;
        }

        public final String originalName() {
            return this.originalName;
        }

        public final String name() {
            return this.name;
        }

        /* access modifiers changed from: package-private */
        public byte[] asciiName() {
            return this.nameBytes;
        }

        public final boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return this.name.equals(((Key) o).name);
        }

        public final int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            return "Key{name='" + this.name + "'}";
        }

        /* access modifiers changed from: package-private */
        public boolean serializesToStreams() {
            return false;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public final <M> M getMarshaller(Class<M> marshallerClass) {
            if (marshallerClass.isInstance(this.marshaller)) {
                return marshallerClass.cast(this.marshaller);
            }
            return null;
        }
    }

    /* renamed from: io.grpc.Metadata$BinaryKey */
    private static class BinaryKey<T> extends Key<T> {
        private final BinaryMarshaller<T> marshaller;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private BinaryKey(String name, BinaryMarshaller<T> marshaller2) {
            super(name, false, marshaller2);
            boolean z = false;
            Preconditions.checkArgument(name.endsWith(Metadata.BINARY_HEADER_SUFFIX), "Binary header is named %s. It must end with %s", (Object) name, (Object) Metadata.BINARY_HEADER_SUFFIX);
            Preconditions.checkArgument(name.length() > Metadata.BINARY_HEADER_SUFFIX.length() ? true : z, "empty key name");
            this.marshaller = (BinaryMarshaller) Preconditions.checkNotNull(marshaller2, "marshaller is null");
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes(T value) {
            return this.marshaller.toBytes(value);
        }

        /* access modifiers changed from: package-private */
        public T parseBytes(byte[] serialized) {
            return this.marshaller.parseBytes(serialized);
        }
    }

    /* renamed from: io.grpc.Metadata$LazyStreamBinaryKey */
    private static class LazyStreamBinaryKey<T> extends Key<T> {
        private final BinaryStreamMarshaller<T> marshaller;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private LazyStreamBinaryKey(String name, BinaryStreamMarshaller<T> marshaller2) {
            super(name, false, marshaller2);
            boolean z = false;
            Preconditions.checkArgument(name.endsWith(Metadata.BINARY_HEADER_SUFFIX), "Binary header is named %s. It must end with %s", (Object) name, (Object) Metadata.BINARY_HEADER_SUFFIX);
            Preconditions.checkArgument(name.length() > Metadata.BINARY_HEADER_SUFFIX.length() ? true : z, "empty key name");
            this.marshaller = (BinaryStreamMarshaller) Preconditions.checkNotNull(marshaller2, "marshaller is null");
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes(T value) {
            return Metadata.streamToBytes(this.marshaller.toStream(value));
        }

        /* access modifiers changed from: package-private */
        public T parseBytes(byte[] serialized) {
            return this.marshaller.parseStream(new ByteArrayInputStream(serialized));
        }

        /* access modifiers changed from: package-private */
        public boolean serializesToStreams() {
            return true;
        }
    }

    /* renamed from: io.grpc.Metadata$LazyValue */
    static final class LazyValue<T> {
        private final BinaryStreamMarshaller<T> marshaller;
        private volatile byte[] serialized;
        private final T value;

        static <T> LazyValue<T> create(Key<T> key, T value2) {
            return new LazyValue<>((BinaryStreamMarshaller) Preconditions.checkNotNull(getBinaryStreamMarshaller(key)), value2);
        }

        LazyValue(BinaryStreamMarshaller<T> marshaller2, T value2) {
            this.marshaller = marshaller2;
            this.value = value2;
        }

        /* access modifiers changed from: package-private */
        public InputStream toStream() {
            return this.marshaller.toStream(this.value);
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes() {
            if (this.serialized == null) {
                synchronized (this) {
                    if (this.serialized == null) {
                        this.serialized = Metadata.streamToBytes(toStream());
                    }
                }
            }
            return this.serialized;
        }

        /* access modifiers changed from: package-private */
        public <T2> T2 toObject(Key<T2> key) {
            BinaryStreamMarshaller<T2> marshaller2;
            if (!key.serializesToStreams() || (marshaller2 = getBinaryStreamMarshaller(key)) == null) {
                return key.parseBytes(toBytes());
            }
            return marshaller2.parseStream(toStream());
        }

        @Nullable
        private static <T> BinaryStreamMarshaller<T> getBinaryStreamMarshaller(Key<T> key) {
            return (BinaryStreamMarshaller) key.getMarshaller(BinaryStreamMarshaller.class);
        }
    }

    /* renamed from: io.grpc.Metadata$AsciiKey */
    private static class AsciiKey<T> extends Key<T> {
        private final AsciiMarshaller<T> marshaller;

        private AsciiKey(String name, boolean pseudo, AsciiMarshaller<T> marshaller2) {
            super(name, pseudo, marshaller2);
            Preconditions.checkArgument(!name.endsWith(Metadata.BINARY_HEADER_SUFFIX), "ASCII header is named %s.  Only binary headers may end with %s", (Object) name, (Object) Metadata.BINARY_HEADER_SUFFIX);
            this.marshaller = (AsciiMarshaller) Preconditions.checkNotNull(marshaller2, "marshaller");
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes(T value) {
            return this.marshaller.toAsciiString(value).getBytes(Charsets.US_ASCII);
        }

        /* access modifiers changed from: package-private */
        public T parseBytes(byte[] serialized) {
            return this.marshaller.parseAsciiString(new String(serialized, Charsets.US_ASCII));
        }
    }

    /* renamed from: io.grpc.Metadata$TrustedAsciiKey */
    private static final class TrustedAsciiKey<T> extends Key<T> {
        private final TrustedAsciiMarshaller<T> marshaller;

        private TrustedAsciiKey(String name, boolean pseudo, TrustedAsciiMarshaller<T> marshaller2) {
            super(name, pseudo, marshaller2);
            Preconditions.checkArgument(!name.endsWith(Metadata.BINARY_HEADER_SUFFIX), "ASCII header is named %s.  Only binary headers may end with %s", (Object) name, (Object) Metadata.BINARY_HEADER_SUFFIX);
            this.marshaller = (TrustedAsciiMarshaller) Preconditions.checkNotNull(marshaller2, "marshaller");
        }

        /* access modifiers changed from: package-private */
        public byte[] toBytes(T value) {
            return this.marshaller.toAsciiString(value);
        }

        /* access modifiers changed from: package-private */
        public T parseBytes(byte[] serialized) {
            return this.marshaller.parseAsciiString(serialized);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] streamToBytes(InputStream stream) {
        try {
            return ByteStreams.toByteArray(stream);
        } catch (IOException ioe) {
            throw new RuntimeException("failure reading serialized stream", ioe);
        }
    }
}
