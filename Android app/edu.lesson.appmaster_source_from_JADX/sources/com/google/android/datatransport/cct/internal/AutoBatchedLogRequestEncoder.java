package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;

public final class AutoBatchedLogRequestEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoBatchedLogRequestEncoder();

    private AutoBatchedLogRequestEncoder() {
    }

    public void configure(EncoderConfig<?> cfg) {
        cfg.registerEncoder((Class<U>) BatchedLogRequest.class, (ObjectEncoder<? super U>) BatchedLogRequestEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) AutoValue_BatchedLogRequest.class, (ObjectEncoder<? super U>) BatchedLogRequestEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) LogRequest.class, (ObjectEncoder<? super U>) LogRequestEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) AutoValue_LogRequest.class, (ObjectEncoder<? super U>) LogRequestEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) ClientInfo.class, (ObjectEncoder<? super U>) ClientInfoEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) AutoValue_ClientInfo.class, (ObjectEncoder<? super U>) ClientInfoEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) AndroidClientInfo.class, (ObjectEncoder<? super U>) AndroidClientInfoEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) AutoValue_AndroidClientInfo.class, (ObjectEncoder<? super U>) AndroidClientInfoEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) LogEvent.class, (ObjectEncoder<? super U>) LogEventEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) AutoValue_LogEvent.class, (ObjectEncoder<? super U>) LogEventEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) NetworkConnectionInfo.class, (ObjectEncoder<? super U>) NetworkConnectionInfoEncoder.INSTANCE);
        cfg.registerEncoder((Class<U>) AutoValue_NetworkConnectionInfo.class, (ObjectEncoder<? super U>) NetworkConnectionInfoEncoder.INSTANCE);
    }

    private static final class BatchedLogRequestEncoder implements ObjectEncoder<BatchedLogRequest> {
        static final BatchedLogRequestEncoder INSTANCE = new BatchedLogRequestEncoder();
        private static final FieldDescriptor LOGREQUEST_DESCRIPTOR = FieldDescriptor.m195of("logRequest");

        private BatchedLogRequestEncoder() {
        }

        public void encode(BatchedLogRequest value, ObjectEncoderContext ctx) throws IOException {
            ctx.add(LOGREQUEST_DESCRIPTOR, (Object) value.getLogRequests());
        }
    }

    private static final class LogRequestEncoder implements ObjectEncoder<LogRequest> {
        private static final FieldDescriptor CLIENTINFO_DESCRIPTOR = FieldDescriptor.m195of("clientInfo");
        static final LogRequestEncoder INSTANCE = new LogRequestEncoder();
        private static final FieldDescriptor LOGEVENT_DESCRIPTOR = FieldDescriptor.m195of("logEvent");
        private static final FieldDescriptor LOGSOURCENAME_DESCRIPTOR = FieldDescriptor.m195of("logSourceName");
        private static final FieldDescriptor LOGSOURCE_DESCRIPTOR = FieldDescriptor.m195of("logSource");
        private static final FieldDescriptor QOSTIER_DESCRIPTOR = FieldDescriptor.m195of("qosTier");
        private static final FieldDescriptor REQUESTTIMEMS_DESCRIPTOR = FieldDescriptor.m195of("requestTimeMs");
        private static final FieldDescriptor REQUESTUPTIMEMS_DESCRIPTOR = FieldDescriptor.m195of("requestUptimeMs");

        private LogRequestEncoder() {
        }

        public void encode(LogRequest value, ObjectEncoderContext ctx) throws IOException {
            ctx.add(REQUESTTIMEMS_DESCRIPTOR, value.getRequestTimeMs());
            ctx.add(REQUESTUPTIMEMS_DESCRIPTOR, value.getRequestUptimeMs());
            ctx.add(CLIENTINFO_DESCRIPTOR, (Object) value.getClientInfo());
            ctx.add(LOGSOURCE_DESCRIPTOR, (Object) value.getLogSource());
            ctx.add(LOGSOURCENAME_DESCRIPTOR, (Object) value.getLogSourceName());
            ctx.add(LOGEVENT_DESCRIPTOR, (Object) value.getLogEvents());
            ctx.add(QOSTIER_DESCRIPTOR, (Object) value.getQosTier());
        }
    }

    private static final class ClientInfoEncoder implements ObjectEncoder<ClientInfo> {
        private static final FieldDescriptor ANDROIDCLIENTINFO_DESCRIPTOR = FieldDescriptor.m195of("androidClientInfo");
        private static final FieldDescriptor CLIENTTYPE_DESCRIPTOR = FieldDescriptor.m195of("clientType");
        static final ClientInfoEncoder INSTANCE = new ClientInfoEncoder();

        private ClientInfoEncoder() {
        }

        public void encode(ClientInfo value, ObjectEncoderContext ctx) throws IOException {
            ctx.add(CLIENTTYPE_DESCRIPTOR, (Object) value.getClientType());
            ctx.add(ANDROIDCLIENTINFO_DESCRIPTOR, (Object) value.getAndroidClientInfo());
        }
    }

    private static final class AndroidClientInfoEncoder implements ObjectEncoder<AndroidClientInfo> {
        private static final FieldDescriptor APPLICATIONBUILD_DESCRIPTOR = FieldDescriptor.m195of("applicationBuild");
        private static final FieldDescriptor COUNTRY_DESCRIPTOR = FieldDescriptor.m195of("country");
        private static final FieldDescriptor DEVICE_DESCRIPTOR = FieldDescriptor.m195of("device");
        private static final FieldDescriptor FINGERPRINT_DESCRIPTOR = FieldDescriptor.m195of("fingerprint");
        private static final FieldDescriptor HARDWARE_DESCRIPTOR = FieldDescriptor.m195of("hardware");
        static final AndroidClientInfoEncoder INSTANCE = new AndroidClientInfoEncoder();
        private static final FieldDescriptor LOCALE_DESCRIPTOR = FieldDescriptor.m195of("locale");
        private static final FieldDescriptor MANUFACTURER_DESCRIPTOR = FieldDescriptor.m195of("manufacturer");
        private static final FieldDescriptor MCCMNC_DESCRIPTOR = FieldDescriptor.m195of("mccMnc");
        private static final FieldDescriptor MODEL_DESCRIPTOR = FieldDescriptor.m195of("model");
        private static final FieldDescriptor OSBUILD_DESCRIPTOR = FieldDescriptor.m195of("osBuild");
        private static final FieldDescriptor PRODUCT_DESCRIPTOR = FieldDescriptor.m195of("product");
        private static final FieldDescriptor SDKVERSION_DESCRIPTOR = FieldDescriptor.m195of("sdkVersion");

        private AndroidClientInfoEncoder() {
        }

        public void encode(AndroidClientInfo value, ObjectEncoderContext ctx) throws IOException {
            ctx.add(SDKVERSION_DESCRIPTOR, (Object) value.getSdkVersion());
            ctx.add(MODEL_DESCRIPTOR, (Object) value.getModel());
            ctx.add(HARDWARE_DESCRIPTOR, (Object) value.getHardware());
            ctx.add(DEVICE_DESCRIPTOR, (Object) value.getDevice());
            ctx.add(PRODUCT_DESCRIPTOR, (Object) value.getProduct());
            ctx.add(OSBUILD_DESCRIPTOR, (Object) value.getOsBuild());
            ctx.add(MANUFACTURER_DESCRIPTOR, (Object) value.getManufacturer());
            ctx.add(FINGERPRINT_DESCRIPTOR, (Object) value.getFingerprint());
            ctx.add(LOCALE_DESCRIPTOR, (Object) value.getLocale());
            ctx.add(COUNTRY_DESCRIPTOR, (Object) value.getCountry());
            ctx.add(MCCMNC_DESCRIPTOR, (Object) value.getMccMnc());
            ctx.add(APPLICATIONBUILD_DESCRIPTOR, (Object) value.getApplicationBuild());
        }
    }

    private static final class LogEventEncoder implements ObjectEncoder<LogEvent> {
        private static final FieldDescriptor EVENTCODE_DESCRIPTOR = FieldDescriptor.m195of("eventCode");
        private static final FieldDescriptor EVENTTIMEMS_DESCRIPTOR = FieldDescriptor.m195of("eventTimeMs");
        private static final FieldDescriptor EVENTUPTIMEMS_DESCRIPTOR = FieldDescriptor.m195of("eventUptimeMs");
        static final LogEventEncoder INSTANCE = new LogEventEncoder();
        private static final FieldDescriptor NETWORKCONNECTIONINFO_DESCRIPTOR = FieldDescriptor.m195of("networkConnectionInfo");
        private static final FieldDescriptor SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR = FieldDescriptor.m195of("sourceExtensionJsonProto3");
        private static final FieldDescriptor SOURCEEXTENSION_DESCRIPTOR = FieldDescriptor.m195of("sourceExtension");
        private static final FieldDescriptor TIMEZONEOFFSETSECONDS_DESCRIPTOR = FieldDescriptor.m195of("timezoneOffsetSeconds");

        private LogEventEncoder() {
        }

        public void encode(LogEvent value, ObjectEncoderContext ctx) throws IOException {
            ctx.add(EVENTTIMEMS_DESCRIPTOR, value.getEventTimeMs());
            ctx.add(EVENTCODE_DESCRIPTOR, (Object) value.getEventCode());
            ctx.add(EVENTUPTIMEMS_DESCRIPTOR, value.getEventUptimeMs());
            ctx.add(SOURCEEXTENSION_DESCRIPTOR, (Object) value.getSourceExtension());
            ctx.add(SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR, (Object) value.getSourceExtensionJsonProto3());
            ctx.add(TIMEZONEOFFSETSECONDS_DESCRIPTOR, value.getTimezoneOffsetSeconds());
            ctx.add(NETWORKCONNECTIONINFO_DESCRIPTOR, (Object) value.getNetworkConnectionInfo());
        }
    }

    private static final class NetworkConnectionInfoEncoder implements ObjectEncoder<NetworkConnectionInfo> {
        static final NetworkConnectionInfoEncoder INSTANCE = new NetworkConnectionInfoEncoder();
        private static final FieldDescriptor MOBILESUBTYPE_DESCRIPTOR = FieldDescriptor.m195of("mobileSubtype");
        private static final FieldDescriptor NETWORKTYPE_DESCRIPTOR = FieldDescriptor.m195of("networkType");

        private NetworkConnectionInfoEncoder() {
        }

        public void encode(NetworkConnectionInfo value, ObjectEncoderContext ctx) throws IOException {
            ctx.add(NETWORKTYPE_DESCRIPTOR, (Object) value.getNetworkType());
            ctx.add(MOBILESUBTYPE_DESCRIPTOR, (Object) value.getMobileSubtype());
        }
    }
}
