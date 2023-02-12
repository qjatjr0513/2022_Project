package p004io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import p004io.grpc.InternalMetadata;
import p004io.grpc.InternalStatus;
import p004io.grpc.Metadata;
import p004io.grpc.Status;
import p004io.grpc.internal.AbstractClientStream;

/* renamed from: io.grpc.internal.Http2ClientStreamTransportState */
public abstract class Http2ClientStreamTransportState extends AbstractClientStream.TransportState {
    private static final Metadata.Key<Integer> HTTP2_STATUS;
    private static final InternalMetadata.TrustedAsciiMarshaller<Integer> HTTP_STATUS_MARSHALLER;
    private Charset errorCharset = Charsets.UTF_8;
    private boolean headersReceived;
    private Status transportError;
    private Metadata transportErrorMetadata;

    /* access modifiers changed from: protected */
    public abstract void http2ProcessingFailed(Status status, boolean z, Metadata metadata);

    public /* bridge */ /* synthetic */ void deframerClosed(boolean z) {
        super.deframerClosed(z);
    }

    static {
        C12431 r0 = new InternalMetadata.TrustedAsciiMarshaller<Integer>() {
            public byte[] toAsciiString(Integer value) {
                throw new UnsupportedOperationException();
            }

            public Integer parseAsciiString(byte[] serialized) {
                if (serialized.length >= 3) {
                    return Integer.valueOf(((serialized[0] - 48) * 100) + ((serialized[1] - 48) * 10) + (serialized[2] - 48));
                }
                throw new NumberFormatException("Malformed status code " + new String(serialized, InternalMetadata.US_ASCII));
            }
        };
        HTTP_STATUS_MARSHALLER = r0;
        HTTP2_STATUS = InternalMetadata.keyOf(":status", r0);
    }

    protected Http2ClientStreamTransportState(int maxMessageSize, StatsTraceContext statsTraceCtx, TransportTracer transportTracer) {
        super(maxMessageSize, statsTraceCtx, transportTracer);
    }

    /* access modifiers changed from: protected */
    public void transportHeadersReceived(Metadata headers) {
        Preconditions.checkNotNull(headers, "headers");
        Status status = this.transportError;
        if (status != null) {
            this.transportError = status.augmentDescription("headers: " + headers);
            return;
        }
        try {
            if (this.headersReceived) {
                Status withDescription = Status.INTERNAL.withDescription("Received headers twice");
                this.transportError = withDescription;
                if (withDescription != null) {
                    this.transportError = withDescription.augmentDescription("headers: " + headers);
                    this.transportErrorMetadata = headers;
                    this.errorCharset = extractCharset(headers);
                    return;
                }
                return;
            }
            Integer httpStatus = (Integer) headers.get(HTTP2_STATUS);
            if (httpStatus == null || httpStatus.intValue() < 100 || httpStatus.intValue() >= 200) {
                this.headersReceived = true;
                Status validateInitialMetadata = validateInitialMetadata(headers);
                this.transportError = validateInitialMetadata;
                if (validateInitialMetadata == null) {
                    stripTransportDetails(headers);
                    inboundHeadersReceived(headers);
                    Status status2 = this.transportError;
                    if (status2 != null) {
                        this.transportError = status2.augmentDescription("headers: " + headers);
                        this.transportErrorMetadata = headers;
                        this.errorCharset = extractCharset(headers);
                    }
                } else if (validateInitialMetadata != null) {
                    this.transportError = validateInitialMetadata.augmentDescription("headers: " + headers);
                    this.transportErrorMetadata = headers;
                    this.errorCharset = extractCharset(headers);
                }
            }
        } finally {
            Status status3 = this.transportError;
            if (status3 != null) {
                this.transportError = status3.augmentDescription("headers: " + headers);
                this.transportErrorMetadata = headers;
                this.errorCharset = extractCharset(headers);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void transportDataReceived(ReadableBuffer frame, boolean endOfStream) {
        Status status = this.transportError;
        if (status != null) {
            this.transportError = status.augmentDescription("DATA-----------------------------\n" + ReadableBuffers.readAsString(frame, this.errorCharset));
            frame.close();
            if (this.transportError.getDescription().length() > 1000 || endOfStream) {
                http2ProcessingFailed(this.transportError, false, this.transportErrorMetadata);
            }
        } else if (!this.headersReceived) {
            http2ProcessingFailed(Status.INTERNAL.withDescription("headers not received before payload"), false, new Metadata());
        } else {
            int frameSize = frame.readableBytes();
            inboundDataReceived(frame);
            if (endOfStream) {
                if (frameSize > 0) {
                    this.transportError = Status.INTERNAL.withDescription("Received unexpected EOS on non-empty DATA frame from server");
                } else {
                    this.transportError = Status.INTERNAL.withDescription("Received unexpected EOS on empty DATA frame from server");
                }
                Metadata metadata = new Metadata();
                this.transportErrorMetadata = metadata;
                transportReportStatus(this.transportError, false, metadata);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void transportTrailersReceived(Metadata trailers) {
        Preconditions.checkNotNull(trailers, GrpcUtil.TE_TRAILERS);
        if (this.transportError == null && !this.headersReceived) {
            Status validateInitialMetadata = validateInitialMetadata(trailers);
            this.transportError = validateInitialMetadata;
            if (validateInitialMetadata != null) {
                this.transportErrorMetadata = trailers;
            }
        }
        Status status = this.transportError;
        if (status != null) {
            Status augmentDescription = status.augmentDescription("trailers: " + trailers);
            this.transportError = augmentDescription;
            http2ProcessingFailed(augmentDescription, false, this.transportErrorMetadata);
            return;
        }
        Status status2 = statusFromTrailers(trailers);
        stripTransportDetails(trailers);
        inboundTrailersReceived(trailers, status2);
    }

    private Status statusFromTrailers(Metadata trailers) {
        Status status;
        Status status2 = (Status) trailers.get(InternalStatus.CODE_KEY);
        if (status2 != null) {
            return status2.withDescription((String) trailers.get(InternalStatus.MESSAGE_KEY));
        }
        if (this.headersReceived) {
            return Status.UNKNOWN.withDescription("missing GRPC status in response");
        }
        Integer httpStatus = (Integer) trailers.get(HTTP2_STATUS);
        if (httpStatus != null) {
            status = GrpcUtil.httpStatusToGrpcStatus(httpStatus.intValue());
        } else {
            status = Status.INTERNAL.withDescription("missing HTTP status code");
        }
        return status.augmentDescription("missing GRPC status, inferred error from HTTP status code");
    }

    @Nullable
    private Status validateInitialMetadata(Metadata headers) {
        Integer httpStatus = (Integer) headers.get(HTTP2_STATUS);
        if (httpStatus == null) {
            return Status.INTERNAL.withDescription("Missing HTTP status code");
        }
        String contentType = (String) headers.get(GrpcUtil.CONTENT_TYPE_KEY);
        if (!GrpcUtil.isGrpcContentType(contentType)) {
            return GrpcUtil.httpStatusToGrpcStatus(httpStatus.intValue()).augmentDescription("invalid content-type: " + contentType);
        }
        return null;
    }

    private static Charset extractCharset(Metadata headers) {
        String contentType = (String) headers.get(GrpcUtil.CONTENT_TYPE_KEY);
        if (contentType != null) {
            String[] split = contentType.split("charset=", 2);
            try {
                return Charset.forName(split[split.length - 1].trim());
            } catch (Exception e) {
            }
        }
        return Charsets.UTF_8;
    }

    private static void stripTransportDetails(Metadata metadata) {
        metadata.discardAll(HTTP2_STATUS);
        metadata.discardAll(InternalStatus.CODE_KEY);
        metadata.discardAll(InternalStatus.MESSAGE_KEY);
    }
}
