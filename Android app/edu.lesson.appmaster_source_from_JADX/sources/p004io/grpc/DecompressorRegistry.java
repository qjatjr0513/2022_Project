package p004io.grpc;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import p004io.grpc.Codec;

/* renamed from: io.grpc.DecompressorRegistry */
public final class DecompressorRegistry {
    static final Joiner ACCEPT_ENCODING_JOINER = Joiner.m10on(',');
    private static final DecompressorRegistry DEFAULT_INSTANCE = emptyInstance().with(new Codec.Gzip(), true).with(Codec.Identity.NONE, false);
    private final byte[] advertisedDecompressors;
    private final Map<String, DecompressorInfo> decompressors;

    public static DecompressorRegistry emptyInstance() {
        return new DecompressorRegistry();
    }

    public static DecompressorRegistry getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public DecompressorRegistry with(Decompressor d, boolean advertised) {
        return new DecompressorRegistry(d, advertised, this);
    }

    private DecompressorRegistry(Decompressor d, boolean advertised, DecompressorRegistry parent) {
        String encoding = d.getMessageEncoding();
        Preconditions.checkArgument(!encoding.contains(","), "Comma is currently not allowed in message encoding");
        int newSize = parent.decompressors.size();
        Map<String, DecompressorInfo> newDecompressors = new LinkedHashMap<>(!parent.decompressors.containsKey(d.getMessageEncoding()) ? newSize + 1 : newSize);
        for (DecompressorInfo di : parent.decompressors.values()) {
            String previousEncoding = di.decompressor.getMessageEncoding();
            if (!previousEncoding.equals(encoding)) {
                newDecompressors.put(previousEncoding, new DecompressorInfo(di.decompressor, di.advertised));
            }
        }
        newDecompressors.put(encoding, new DecompressorInfo(d, advertised));
        this.decompressors = Collections.unmodifiableMap(newDecompressors);
        this.advertisedDecompressors = ACCEPT_ENCODING_JOINER.join((Iterable<?>) getAdvertisedMessageEncodings()).getBytes(StandardCharsets.US_ASCII);
    }

    private DecompressorRegistry() {
        this.decompressors = new LinkedHashMap(0);
        this.advertisedDecompressors = new byte[0];
    }

    public Set<String> getKnownMessageEncodings() {
        return this.decompressors.keySet();
    }

    /* access modifiers changed from: package-private */
    public byte[] getRawAdvertisedMessageEncodings() {
        return this.advertisedDecompressors;
    }

    public Set<String> getAdvertisedMessageEncodings() {
        Set<String> advertisedDecompressors2 = new HashSet<>(this.decompressors.size());
        for (Map.Entry<String, DecompressorInfo> entry : this.decompressors.entrySet()) {
            if (entry.getValue().advertised) {
                advertisedDecompressors2.add(entry.getKey());
            }
        }
        return Collections.unmodifiableSet(advertisedDecompressors2);
    }

    @Nullable
    public Decompressor lookupDecompressor(String messageEncoding) {
        DecompressorInfo info = this.decompressors.get(messageEncoding);
        if (info != null) {
            return info.decompressor;
        }
        return null;
    }

    /* renamed from: io.grpc.DecompressorRegistry$DecompressorInfo */
    private static final class DecompressorInfo {
        final boolean advertised;
        final Decompressor decompressor;

        DecompressorInfo(Decompressor decompressor2, boolean advertised2) {
            this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor2, "decompressor");
            this.advertised = advertised2;
        }
    }
}
