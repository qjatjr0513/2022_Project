package p004io.grpc;

/* renamed from: io.grpc.InternalDecompressorRegistry */
public final class InternalDecompressorRegistry {
    private InternalDecompressorRegistry() {
    }

    public static byte[] getRawAdvertisedMessageEncodings(DecompressorRegistry reg) {
        return reg.getRawAdvertisedMessageEncodings();
    }
}
