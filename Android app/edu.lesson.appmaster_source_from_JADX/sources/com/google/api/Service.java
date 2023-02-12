package com.google.api;

import com.google.api.Authentication;
import com.google.api.Backend;
import com.google.api.Billing;
import com.google.api.Context;
import com.google.api.Control;
import com.google.api.Documentation;
import com.google.api.Endpoint;
import com.google.api.Http;
import com.google.api.LogDescriptor;
import com.google.api.Logging;
import com.google.api.MetricDescriptor;
import com.google.api.MonitoredResourceDescriptor;
import com.google.api.Monitoring;
import com.google.api.Quota;
import com.google.api.SourceInfo;
import com.google.api.SystemParameters;
import com.google.api.Usage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Api;
import com.google.protobuf.ApiOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Enum;
import com.google.protobuf.EnumOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Type;
import com.google.protobuf.TypeOrBuilder;
import com.google.protobuf.UInt32Value;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Service extends GeneratedMessageLite<Service, Builder> implements ServiceOrBuilder {
    public static final int APIS_FIELD_NUMBER = 3;
    public static final int AUTHENTICATION_FIELD_NUMBER = 11;
    public static final int BACKEND_FIELD_NUMBER = 8;
    public static final int BILLING_FIELD_NUMBER = 26;
    public static final int CONFIG_VERSION_FIELD_NUMBER = 20;
    public static final int CONTEXT_FIELD_NUMBER = 12;
    public static final int CONTROL_FIELD_NUMBER = 21;
    /* access modifiers changed from: private */
    public static final Service DEFAULT_INSTANCE;
    public static final int DOCUMENTATION_FIELD_NUMBER = 6;
    public static final int ENDPOINTS_FIELD_NUMBER = 18;
    public static final int ENUMS_FIELD_NUMBER = 5;
    public static final int HTTP_FIELD_NUMBER = 9;
    public static final int ID_FIELD_NUMBER = 33;
    public static final int LOGGING_FIELD_NUMBER = 27;
    public static final int LOGS_FIELD_NUMBER = 23;
    public static final int METRICS_FIELD_NUMBER = 24;
    public static final int MONITORED_RESOURCES_FIELD_NUMBER = 25;
    public static final int MONITORING_FIELD_NUMBER = 28;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<Service> PARSER = null;
    public static final int PRODUCER_PROJECT_ID_FIELD_NUMBER = 22;
    public static final int QUOTA_FIELD_NUMBER = 10;
    public static final int SOURCE_INFO_FIELD_NUMBER = 37;
    public static final int SYSTEM_PARAMETERS_FIELD_NUMBER = 29;
    public static final int TITLE_FIELD_NUMBER = 2;
    public static final int TYPES_FIELD_NUMBER = 4;
    public static final int USAGE_FIELD_NUMBER = 15;
    private Internal.ProtobufList<Api> apis_ = emptyProtobufList();
    private Authentication authentication_;
    private Backend backend_;
    private Billing billing_;
    private UInt32Value configVersion_;
    private Context context_;
    private Control control_;
    private Documentation documentation_;
    private Internal.ProtobufList<Endpoint> endpoints_ = emptyProtobufList();
    private Internal.ProtobufList<Enum> enums_ = emptyProtobufList();
    private Http http_;
    private String id_ = "";
    private Logging logging_;
    private Internal.ProtobufList<LogDescriptor> logs_ = emptyProtobufList();
    private Internal.ProtobufList<MetricDescriptor> metrics_ = emptyProtobufList();
    private Internal.ProtobufList<MonitoredResourceDescriptor> monitoredResources_ = emptyProtobufList();
    private Monitoring monitoring_;
    private String name_ = "";
    private String producerProjectId_ = "";
    private Quota quota_;
    private SourceInfo sourceInfo_;
    private SystemParameters systemParameters_;
    private String title_ = "";
    private Internal.ProtobufList<Type> types_ = emptyProtobufList();
    private Usage usage_;

    private Service() {
    }

    public boolean hasConfigVersion() {
        return this.configVersion_ != null;
    }

    public UInt32Value getConfigVersion() {
        UInt32Value uInt32Value = this.configVersion_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    /* access modifiers changed from: private */
    public void setConfigVersion(UInt32Value value) {
        value.getClass();
        this.configVersion_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeConfigVersion(UInt32Value value) {
        value.getClass();
        UInt32Value uInt32Value = this.configVersion_;
        if (uInt32Value == null || uInt32Value == UInt32Value.getDefaultInstance()) {
            this.configVersion_ = value;
        } else {
            this.configVersion_ = (UInt32Value) ((UInt32Value.Builder) UInt32Value.newBuilder(this.configVersion_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearConfigVersion() {
        this.configVersion_ = null;
    }

    public String getName() {
        return this.name_;
    }

    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    public void setName(String value) {
        value.getClass();
        this.name_ = value;
    }

    /* access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    public void setNameBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.name_ = value.toStringUtf8();
    }

    public String getId() {
        return this.id_;
    }

    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    /* access modifiers changed from: private */
    public void setId(String value) {
        value.getClass();
        this.id_ = value;
    }

    /* access modifiers changed from: private */
    public void clearId() {
        this.id_ = getDefaultInstance().getId();
    }

    /* access modifiers changed from: private */
    public void setIdBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.id_ = value.toStringUtf8();
    }

    public String getTitle() {
        return this.title_;
    }

    public ByteString getTitleBytes() {
        return ByteString.copyFromUtf8(this.title_);
    }

    /* access modifiers changed from: private */
    public void setTitle(String value) {
        value.getClass();
        this.title_ = value;
    }

    /* access modifiers changed from: private */
    public void clearTitle() {
        this.title_ = getDefaultInstance().getTitle();
    }

    /* access modifiers changed from: private */
    public void setTitleBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.title_ = value.toStringUtf8();
    }

    public String getProducerProjectId() {
        return this.producerProjectId_;
    }

    public ByteString getProducerProjectIdBytes() {
        return ByteString.copyFromUtf8(this.producerProjectId_);
    }

    /* access modifiers changed from: private */
    public void setProducerProjectId(String value) {
        value.getClass();
        this.producerProjectId_ = value;
    }

    /* access modifiers changed from: private */
    public void clearProducerProjectId() {
        this.producerProjectId_ = getDefaultInstance().getProducerProjectId();
    }

    /* access modifiers changed from: private */
    public void setProducerProjectIdBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.producerProjectId_ = value.toStringUtf8();
    }

    public List<Api> getApisList() {
        return this.apis_;
    }

    public List<? extends ApiOrBuilder> getApisOrBuilderList() {
        return this.apis_;
    }

    public int getApisCount() {
        return this.apis_.size();
    }

    public Api getApis(int index) {
        return (Api) this.apis_.get(index);
    }

    public ApiOrBuilder getApisOrBuilder(int index) {
        return (ApiOrBuilder) this.apis_.get(index);
    }

    private void ensureApisIsMutable() {
        Internal.ProtobufList<Api> tmp = this.apis_;
        if (!tmp.isModifiable()) {
            this.apis_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setApis(int index, Api value) {
        value.getClass();
        ensureApisIsMutable();
        this.apis_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addApis(Api value) {
        value.getClass();
        ensureApisIsMutable();
        this.apis_.add(value);
    }

    /* access modifiers changed from: private */
    public void addApis(int index, Api value) {
        value.getClass();
        ensureApisIsMutable();
        this.apis_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllApis(Iterable<? extends Api> values) {
        ensureApisIsMutable();
        AbstractMessageLite.addAll(values, this.apis_);
    }

    /* access modifiers changed from: private */
    public void clearApis() {
        this.apis_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeApis(int index) {
        ensureApisIsMutable();
        this.apis_.remove(index);
    }

    public List<Type> getTypesList() {
        return this.types_;
    }

    public List<? extends TypeOrBuilder> getTypesOrBuilderList() {
        return this.types_;
    }

    public int getTypesCount() {
        return this.types_.size();
    }

    public Type getTypes(int index) {
        return (Type) this.types_.get(index);
    }

    public TypeOrBuilder getTypesOrBuilder(int index) {
        return (TypeOrBuilder) this.types_.get(index);
    }

    private void ensureTypesIsMutable() {
        Internal.ProtobufList<Type> tmp = this.types_;
        if (!tmp.isModifiable()) {
            this.types_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setTypes(int index, Type value) {
        value.getClass();
        ensureTypesIsMutable();
        this.types_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addTypes(Type value) {
        value.getClass();
        ensureTypesIsMutable();
        this.types_.add(value);
    }

    /* access modifiers changed from: private */
    public void addTypes(int index, Type value) {
        value.getClass();
        ensureTypesIsMutable();
        this.types_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllTypes(Iterable<? extends Type> values) {
        ensureTypesIsMutable();
        AbstractMessageLite.addAll(values, this.types_);
    }

    /* access modifiers changed from: private */
    public void clearTypes() {
        this.types_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeTypes(int index) {
        ensureTypesIsMutable();
        this.types_.remove(index);
    }

    public List<Enum> getEnumsList() {
        return this.enums_;
    }

    public List<? extends EnumOrBuilder> getEnumsOrBuilderList() {
        return this.enums_;
    }

    public int getEnumsCount() {
        return this.enums_.size();
    }

    public Enum getEnums(int index) {
        return (Enum) this.enums_.get(index);
    }

    public EnumOrBuilder getEnumsOrBuilder(int index) {
        return (EnumOrBuilder) this.enums_.get(index);
    }

    private void ensureEnumsIsMutable() {
        Internal.ProtobufList<Enum> tmp = this.enums_;
        if (!tmp.isModifiable()) {
            this.enums_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setEnums(int index, Enum value) {
        value.getClass();
        ensureEnumsIsMutable();
        this.enums_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addEnums(Enum value) {
        value.getClass();
        ensureEnumsIsMutable();
        this.enums_.add(value);
    }

    /* access modifiers changed from: private */
    public void addEnums(int index, Enum value) {
        value.getClass();
        ensureEnumsIsMutable();
        this.enums_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllEnums(Iterable<? extends Enum> values) {
        ensureEnumsIsMutable();
        AbstractMessageLite.addAll(values, this.enums_);
    }

    /* access modifiers changed from: private */
    public void clearEnums() {
        this.enums_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeEnums(int index) {
        ensureEnumsIsMutable();
        this.enums_.remove(index);
    }

    public boolean hasDocumentation() {
        return this.documentation_ != null;
    }

    public Documentation getDocumentation() {
        Documentation documentation = this.documentation_;
        return documentation == null ? Documentation.getDefaultInstance() : documentation;
    }

    /* access modifiers changed from: private */
    public void setDocumentation(Documentation value) {
        value.getClass();
        this.documentation_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeDocumentation(Documentation value) {
        value.getClass();
        Documentation documentation = this.documentation_;
        if (documentation == null || documentation == Documentation.getDefaultInstance()) {
            this.documentation_ = value;
        } else {
            this.documentation_ = (Documentation) ((Documentation.Builder) Documentation.newBuilder(this.documentation_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearDocumentation() {
        this.documentation_ = null;
    }

    public boolean hasBackend() {
        return this.backend_ != null;
    }

    public Backend getBackend() {
        Backend backend = this.backend_;
        return backend == null ? Backend.getDefaultInstance() : backend;
    }

    /* access modifiers changed from: private */
    public void setBackend(Backend value) {
        value.getClass();
        this.backend_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeBackend(Backend value) {
        value.getClass();
        Backend backend = this.backend_;
        if (backend == null || backend == Backend.getDefaultInstance()) {
            this.backend_ = value;
        } else {
            this.backend_ = (Backend) ((Backend.Builder) Backend.newBuilder(this.backend_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearBackend() {
        this.backend_ = null;
    }

    public boolean hasHttp() {
        return this.http_ != null;
    }

    public Http getHttp() {
        Http http = this.http_;
        return http == null ? Http.getDefaultInstance() : http;
    }

    /* access modifiers changed from: private */
    public void setHttp(Http value) {
        value.getClass();
        this.http_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeHttp(Http value) {
        value.getClass();
        Http http = this.http_;
        if (http == null || http == Http.getDefaultInstance()) {
            this.http_ = value;
        } else {
            this.http_ = (Http) ((Http.Builder) Http.newBuilder(this.http_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearHttp() {
        this.http_ = null;
    }

    public boolean hasQuota() {
        return this.quota_ != null;
    }

    public Quota getQuota() {
        Quota quota = this.quota_;
        return quota == null ? Quota.getDefaultInstance() : quota;
    }

    /* access modifiers changed from: private */
    public void setQuota(Quota value) {
        value.getClass();
        this.quota_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeQuota(Quota value) {
        value.getClass();
        Quota quota = this.quota_;
        if (quota == null || quota == Quota.getDefaultInstance()) {
            this.quota_ = value;
        } else {
            this.quota_ = (Quota) ((Quota.Builder) Quota.newBuilder(this.quota_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearQuota() {
        this.quota_ = null;
    }

    public boolean hasAuthentication() {
        return this.authentication_ != null;
    }

    public Authentication getAuthentication() {
        Authentication authentication = this.authentication_;
        return authentication == null ? Authentication.getDefaultInstance() : authentication;
    }

    /* access modifiers changed from: private */
    public void setAuthentication(Authentication value) {
        value.getClass();
        this.authentication_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeAuthentication(Authentication value) {
        value.getClass();
        Authentication authentication = this.authentication_;
        if (authentication == null || authentication == Authentication.getDefaultInstance()) {
            this.authentication_ = value;
        } else {
            this.authentication_ = (Authentication) ((Authentication.Builder) Authentication.newBuilder(this.authentication_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearAuthentication() {
        this.authentication_ = null;
    }

    public boolean hasContext() {
        return this.context_ != null;
    }

    public Context getContext() {
        Context context = this.context_;
        return context == null ? Context.getDefaultInstance() : context;
    }

    /* access modifiers changed from: private */
    public void setContext(Context value) {
        value.getClass();
        this.context_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeContext(Context value) {
        value.getClass();
        Context context = this.context_;
        if (context == null || context == Context.getDefaultInstance()) {
            this.context_ = value;
        } else {
            this.context_ = (Context) ((Context.Builder) Context.newBuilder(this.context_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearContext() {
        this.context_ = null;
    }

    public boolean hasUsage() {
        return this.usage_ != null;
    }

    public Usage getUsage() {
        Usage usage = this.usage_;
        return usage == null ? Usage.getDefaultInstance() : usage;
    }

    /* access modifiers changed from: private */
    public void setUsage(Usage value) {
        value.getClass();
        this.usage_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeUsage(Usage value) {
        value.getClass();
        Usage usage = this.usage_;
        if (usage == null || usage == Usage.getDefaultInstance()) {
            this.usage_ = value;
        } else {
            this.usage_ = (Usage) ((Usage.Builder) Usage.newBuilder(this.usage_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearUsage() {
        this.usage_ = null;
    }

    public List<Endpoint> getEndpointsList() {
        return this.endpoints_;
    }

    public List<? extends EndpointOrBuilder> getEndpointsOrBuilderList() {
        return this.endpoints_;
    }

    public int getEndpointsCount() {
        return this.endpoints_.size();
    }

    public Endpoint getEndpoints(int index) {
        return (Endpoint) this.endpoints_.get(index);
    }

    public EndpointOrBuilder getEndpointsOrBuilder(int index) {
        return (EndpointOrBuilder) this.endpoints_.get(index);
    }

    private void ensureEndpointsIsMutable() {
        Internal.ProtobufList<Endpoint> tmp = this.endpoints_;
        if (!tmp.isModifiable()) {
            this.endpoints_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setEndpoints(int index, Endpoint value) {
        value.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addEndpoints(Endpoint value) {
        value.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.add(value);
    }

    /* access modifiers changed from: private */
    public void addEndpoints(int index, Endpoint value) {
        value.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllEndpoints(Iterable<? extends Endpoint> values) {
        ensureEndpointsIsMutable();
        AbstractMessageLite.addAll(values, this.endpoints_);
    }

    /* access modifiers changed from: private */
    public void clearEndpoints() {
        this.endpoints_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeEndpoints(int index) {
        ensureEndpointsIsMutable();
        this.endpoints_.remove(index);
    }

    public boolean hasControl() {
        return this.control_ != null;
    }

    public Control getControl() {
        Control control = this.control_;
        return control == null ? Control.getDefaultInstance() : control;
    }

    /* access modifiers changed from: private */
    public void setControl(Control value) {
        value.getClass();
        this.control_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeControl(Control value) {
        value.getClass();
        Control control = this.control_;
        if (control == null || control == Control.getDefaultInstance()) {
            this.control_ = value;
        } else {
            this.control_ = (Control) ((Control.Builder) Control.newBuilder(this.control_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearControl() {
        this.control_ = null;
    }

    public List<LogDescriptor> getLogsList() {
        return this.logs_;
    }

    public List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList() {
        return this.logs_;
    }

    public int getLogsCount() {
        return this.logs_.size();
    }

    public LogDescriptor getLogs(int index) {
        return (LogDescriptor) this.logs_.get(index);
    }

    public LogDescriptorOrBuilder getLogsOrBuilder(int index) {
        return (LogDescriptorOrBuilder) this.logs_.get(index);
    }

    private void ensureLogsIsMutable() {
        Internal.ProtobufList<LogDescriptor> tmp = this.logs_;
        if (!tmp.isModifiable()) {
            this.logs_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setLogs(int index, LogDescriptor value) {
        value.getClass();
        ensureLogsIsMutable();
        this.logs_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addLogs(LogDescriptor value) {
        value.getClass();
        ensureLogsIsMutable();
        this.logs_.add(value);
    }

    /* access modifiers changed from: private */
    public void addLogs(int index, LogDescriptor value) {
        value.getClass();
        ensureLogsIsMutable();
        this.logs_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllLogs(Iterable<? extends LogDescriptor> values) {
        ensureLogsIsMutable();
        AbstractMessageLite.addAll(values, this.logs_);
    }

    /* access modifiers changed from: private */
    public void clearLogs() {
        this.logs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeLogs(int index) {
        ensureLogsIsMutable();
        this.logs_.remove(index);
    }

    public List<MetricDescriptor> getMetricsList() {
        return this.metrics_;
    }

    public List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList() {
        return this.metrics_;
    }

    public int getMetricsCount() {
        return this.metrics_.size();
    }

    public MetricDescriptor getMetrics(int index) {
        return (MetricDescriptor) this.metrics_.get(index);
    }

    public MetricDescriptorOrBuilder getMetricsOrBuilder(int index) {
        return (MetricDescriptorOrBuilder) this.metrics_.get(index);
    }

    private void ensureMetricsIsMutable() {
        Internal.ProtobufList<MetricDescriptor> tmp = this.metrics_;
        if (!tmp.isModifiable()) {
            this.metrics_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setMetrics(int index, MetricDescriptor value) {
        value.getClass();
        ensureMetricsIsMutable();
        this.metrics_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addMetrics(MetricDescriptor value) {
        value.getClass();
        ensureMetricsIsMutable();
        this.metrics_.add(value);
    }

    /* access modifiers changed from: private */
    public void addMetrics(int index, MetricDescriptor value) {
        value.getClass();
        ensureMetricsIsMutable();
        this.metrics_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllMetrics(Iterable<? extends MetricDescriptor> values) {
        ensureMetricsIsMutable();
        AbstractMessageLite.addAll(values, this.metrics_);
    }

    /* access modifiers changed from: private */
    public void clearMetrics() {
        this.metrics_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeMetrics(int index) {
        ensureMetricsIsMutable();
        this.metrics_.remove(index);
    }

    public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
        return this.monitoredResources_;
    }

    public List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList() {
        return this.monitoredResources_;
    }

    public int getMonitoredResourcesCount() {
        return this.monitoredResources_.size();
    }

    public MonitoredResourceDescriptor getMonitoredResources(int index) {
        return (MonitoredResourceDescriptor) this.monitoredResources_.get(index);
    }

    public MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int index) {
        return (MonitoredResourceDescriptorOrBuilder) this.monitoredResources_.get(index);
    }

    private void ensureMonitoredResourcesIsMutable() {
        Internal.ProtobufList<MonitoredResourceDescriptor> tmp = this.monitoredResources_;
        if (!tmp.isModifiable()) {
            this.monitoredResources_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setMonitoredResources(int index, MonitoredResourceDescriptor value) {
        value.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addMonitoredResources(MonitoredResourceDescriptor value) {
        value.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.add(value);
    }

    /* access modifiers changed from: private */
    public void addMonitoredResources(int index, MonitoredResourceDescriptor value) {
        value.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> values) {
        ensureMonitoredResourcesIsMutable();
        AbstractMessageLite.addAll(values, this.monitoredResources_);
    }

    /* access modifiers changed from: private */
    public void clearMonitoredResources() {
        this.monitoredResources_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeMonitoredResources(int index) {
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.remove(index);
    }

    public boolean hasBilling() {
        return this.billing_ != null;
    }

    public Billing getBilling() {
        Billing billing = this.billing_;
        return billing == null ? Billing.getDefaultInstance() : billing;
    }

    /* access modifiers changed from: private */
    public void setBilling(Billing value) {
        value.getClass();
        this.billing_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeBilling(Billing value) {
        value.getClass();
        Billing billing = this.billing_;
        if (billing == null || billing == Billing.getDefaultInstance()) {
            this.billing_ = value;
        } else {
            this.billing_ = (Billing) ((Billing.Builder) Billing.newBuilder(this.billing_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearBilling() {
        this.billing_ = null;
    }

    public boolean hasLogging() {
        return this.logging_ != null;
    }

    public Logging getLogging() {
        Logging logging = this.logging_;
        return logging == null ? Logging.getDefaultInstance() : logging;
    }

    /* access modifiers changed from: private */
    public void setLogging(Logging value) {
        value.getClass();
        this.logging_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeLogging(Logging value) {
        value.getClass();
        Logging logging = this.logging_;
        if (logging == null || logging == Logging.getDefaultInstance()) {
            this.logging_ = value;
        } else {
            this.logging_ = (Logging) ((Logging.Builder) Logging.newBuilder(this.logging_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearLogging() {
        this.logging_ = null;
    }

    public boolean hasMonitoring() {
        return this.monitoring_ != null;
    }

    public Monitoring getMonitoring() {
        Monitoring monitoring = this.monitoring_;
        return monitoring == null ? Monitoring.getDefaultInstance() : monitoring;
    }

    /* access modifiers changed from: private */
    public void setMonitoring(Monitoring value) {
        value.getClass();
        this.monitoring_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeMonitoring(Monitoring value) {
        value.getClass();
        Monitoring monitoring = this.monitoring_;
        if (monitoring == null || monitoring == Monitoring.getDefaultInstance()) {
            this.monitoring_ = value;
        } else {
            this.monitoring_ = (Monitoring) ((Monitoring.Builder) Monitoring.newBuilder(this.monitoring_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearMonitoring() {
        this.monitoring_ = null;
    }

    public boolean hasSystemParameters() {
        return this.systemParameters_ != null;
    }

    public SystemParameters getSystemParameters() {
        SystemParameters systemParameters = this.systemParameters_;
        return systemParameters == null ? SystemParameters.getDefaultInstance() : systemParameters;
    }

    /* access modifiers changed from: private */
    public void setSystemParameters(SystemParameters value) {
        value.getClass();
        this.systemParameters_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeSystemParameters(SystemParameters value) {
        value.getClass();
        SystemParameters systemParameters = this.systemParameters_;
        if (systemParameters == null || systemParameters == SystemParameters.getDefaultInstance()) {
            this.systemParameters_ = value;
        } else {
            this.systemParameters_ = (SystemParameters) ((SystemParameters.Builder) SystemParameters.newBuilder(this.systemParameters_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearSystemParameters() {
        this.systemParameters_ = null;
    }

    public boolean hasSourceInfo() {
        return this.sourceInfo_ != null;
    }

    public SourceInfo getSourceInfo() {
        SourceInfo sourceInfo = this.sourceInfo_;
        return sourceInfo == null ? SourceInfo.getDefaultInstance() : sourceInfo;
    }

    /* access modifiers changed from: private */
    public void setSourceInfo(SourceInfo value) {
        value.getClass();
        this.sourceInfo_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeSourceInfo(SourceInfo value) {
        value.getClass();
        SourceInfo sourceInfo = this.sourceInfo_;
        if (sourceInfo == null || sourceInfo == SourceInfo.getDefaultInstance()) {
            this.sourceInfo_ = value;
        } else {
            this.sourceInfo_ = (SourceInfo) ((SourceInfo.Builder) SourceInfo.newBuilder(this.sourceInfo_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearSourceInfo() {
        this.sourceInfo_ = null;
    }

    public static Service parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Service parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Service parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Service parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Service parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Service parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Service parseFrom(InputStream input) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Service parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Service parseDelimitedFrom(InputStream input) throws IOException {
        return (Service) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Service parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Service) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Service parseFrom(CodedInputStream input) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Service parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Service prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Service, Builder> implements ServiceOrBuilder {
        /* synthetic */ Builder(C00481 x0) {
            this();
        }

        private Builder() {
            super(Service.DEFAULT_INSTANCE);
        }

        public boolean hasConfigVersion() {
            return ((Service) this.instance).hasConfigVersion();
        }

        public UInt32Value getConfigVersion() {
            return ((Service) this.instance).getConfigVersion();
        }

        public Builder setConfigVersion(UInt32Value value) {
            copyOnWrite();
            ((Service) this.instance).setConfigVersion(value);
            return this;
        }

        public Builder setConfigVersion(UInt32Value.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setConfigVersion((UInt32Value) builderForValue.build());
            return this;
        }

        public Builder mergeConfigVersion(UInt32Value value) {
            copyOnWrite();
            ((Service) this.instance).mergeConfigVersion(value);
            return this;
        }

        public Builder clearConfigVersion() {
            copyOnWrite();
            ((Service) this.instance).clearConfigVersion();
            return this;
        }

        public String getName() {
            return ((Service) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((Service) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((Service) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Service) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((Service) this.instance).setNameBytes(value);
            return this;
        }

        public String getId() {
            return ((Service) this.instance).getId();
        }

        public ByteString getIdBytes() {
            return ((Service) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((Service) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((Service) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((Service) this.instance).setIdBytes(value);
            return this;
        }

        public String getTitle() {
            return ((Service) this.instance).getTitle();
        }

        public ByteString getTitleBytes() {
            return ((Service) this.instance).getTitleBytes();
        }

        public Builder setTitle(String value) {
            copyOnWrite();
            ((Service) this.instance).setTitle(value);
            return this;
        }

        public Builder clearTitle() {
            copyOnWrite();
            ((Service) this.instance).clearTitle();
            return this;
        }

        public Builder setTitleBytes(ByteString value) {
            copyOnWrite();
            ((Service) this.instance).setTitleBytes(value);
            return this;
        }

        public String getProducerProjectId() {
            return ((Service) this.instance).getProducerProjectId();
        }

        public ByteString getProducerProjectIdBytes() {
            return ((Service) this.instance).getProducerProjectIdBytes();
        }

        public Builder setProducerProjectId(String value) {
            copyOnWrite();
            ((Service) this.instance).setProducerProjectId(value);
            return this;
        }

        public Builder clearProducerProjectId() {
            copyOnWrite();
            ((Service) this.instance).clearProducerProjectId();
            return this;
        }

        public Builder setProducerProjectIdBytes(ByteString value) {
            copyOnWrite();
            ((Service) this.instance).setProducerProjectIdBytes(value);
            return this;
        }

        public List<Api> getApisList() {
            return Collections.unmodifiableList(((Service) this.instance).getApisList());
        }

        public int getApisCount() {
            return ((Service) this.instance).getApisCount();
        }

        public Api getApis(int index) {
            return ((Service) this.instance).getApis(index);
        }

        public Builder setApis(int index, Api value) {
            copyOnWrite();
            ((Service) this.instance).setApis(index, value);
            return this;
        }

        public Builder setApis(int index, Api.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setApis(index, (Api) builderForValue.build());
            return this;
        }

        public Builder addApis(Api value) {
            copyOnWrite();
            ((Service) this.instance).addApis(value);
            return this;
        }

        public Builder addApis(int index, Api value) {
            copyOnWrite();
            ((Service) this.instance).addApis(index, value);
            return this;
        }

        public Builder addApis(Api.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addApis((Api) builderForValue.build());
            return this;
        }

        public Builder addApis(int index, Api.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addApis(index, (Api) builderForValue.build());
            return this;
        }

        public Builder addAllApis(Iterable<? extends Api> values) {
            copyOnWrite();
            ((Service) this.instance).addAllApis(values);
            return this;
        }

        public Builder clearApis() {
            copyOnWrite();
            ((Service) this.instance).clearApis();
            return this;
        }

        public Builder removeApis(int index) {
            copyOnWrite();
            ((Service) this.instance).removeApis(index);
            return this;
        }

        public List<Type> getTypesList() {
            return Collections.unmodifiableList(((Service) this.instance).getTypesList());
        }

        public int getTypesCount() {
            return ((Service) this.instance).getTypesCount();
        }

        public Type getTypes(int index) {
            return ((Service) this.instance).getTypes(index);
        }

        public Builder setTypes(int index, Type value) {
            copyOnWrite();
            ((Service) this.instance).setTypes(index, value);
            return this;
        }

        public Builder setTypes(int index, Type.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setTypes(index, (Type) builderForValue.build());
            return this;
        }

        public Builder addTypes(Type value) {
            copyOnWrite();
            ((Service) this.instance).addTypes(value);
            return this;
        }

        public Builder addTypes(int index, Type value) {
            copyOnWrite();
            ((Service) this.instance).addTypes(index, value);
            return this;
        }

        public Builder addTypes(Type.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addTypes((Type) builderForValue.build());
            return this;
        }

        public Builder addTypes(int index, Type.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addTypes(index, (Type) builderForValue.build());
            return this;
        }

        public Builder addAllTypes(Iterable<? extends Type> values) {
            copyOnWrite();
            ((Service) this.instance).addAllTypes(values);
            return this;
        }

        public Builder clearTypes() {
            copyOnWrite();
            ((Service) this.instance).clearTypes();
            return this;
        }

        public Builder removeTypes(int index) {
            copyOnWrite();
            ((Service) this.instance).removeTypes(index);
            return this;
        }

        public List<Enum> getEnumsList() {
            return Collections.unmodifiableList(((Service) this.instance).getEnumsList());
        }

        public int getEnumsCount() {
            return ((Service) this.instance).getEnumsCount();
        }

        public Enum getEnums(int index) {
            return ((Service) this.instance).getEnums(index);
        }

        public Builder setEnums(int index, Enum value) {
            copyOnWrite();
            ((Service) this.instance).setEnums(index, value);
            return this;
        }

        public Builder setEnums(int index, Enum.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setEnums(index, (Enum) builderForValue.build());
            return this;
        }

        public Builder addEnums(Enum value) {
            copyOnWrite();
            ((Service) this.instance).addEnums(value);
            return this;
        }

        public Builder addEnums(int index, Enum value) {
            copyOnWrite();
            ((Service) this.instance).addEnums(index, value);
            return this;
        }

        public Builder addEnums(Enum.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addEnums((Enum) builderForValue.build());
            return this;
        }

        public Builder addEnums(int index, Enum.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addEnums(index, (Enum) builderForValue.build());
            return this;
        }

        public Builder addAllEnums(Iterable<? extends Enum> values) {
            copyOnWrite();
            ((Service) this.instance).addAllEnums(values);
            return this;
        }

        public Builder clearEnums() {
            copyOnWrite();
            ((Service) this.instance).clearEnums();
            return this;
        }

        public Builder removeEnums(int index) {
            copyOnWrite();
            ((Service) this.instance).removeEnums(index);
            return this;
        }

        public boolean hasDocumentation() {
            return ((Service) this.instance).hasDocumentation();
        }

        public Documentation getDocumentation() {
            return ((Service) this.instance).getDocumentation();
        }

        public Builder setDocumentation(Documentation value) {
            copyOnWrite();
            ((Service) this.instance).setDocumentation(value);
            return this;
        }

        public Builder setDocumentation(Documentation.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setDocumentation((Documentation) builderForValue.build());
            return this;
        }

        public Builder mergeDocumentation(Documentation value) {
            copyOnWrite();
            ((Service) this.instance).mergeDocumentation(value);
            return this;
        }

        public Builder clearDocumentation() {
            copyOnWrite();
            ((Service) this.instance).clearDocumentation();
            return this;
        }

        public boolean hasBackend() {
            return ((Service) this.instance).hasBackend();
        }

        public Backend getBackend() {
            return ((Service) this.instance).getBackend();
        }

        public Builder setBackend(Backend value) {
            copyOnWrite();
            ((Service) this.instance).setBackend(value);
            return this;
        }

        public Builder setBackend(Backend.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setBackend((Backend) builderForValue.build());
            return this;
        }

        public Builder mergeBackend(Backend value) {
            copyOnWrite();
            ((Service) this.instance).mergeBackend(value);
            return this;
        }

        public Builder clearBackend() {
            copyOnWrite();
            ((Service) this.instance).clearBackend();
            return this;
        }

        public boolean hasHttp() {
            return ((Service) this.instance).hasHttp();
        }

        public Http getHttp() {
            return ((Service) this.instance).getHttp();
        }

        public Builder setHttp(Http value) {
            copyOnWrite();
            ((Service) this.instance).setHttp(value);
            return this;
        }

        public Builder setHttp(Http.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setHttp((Http) builderForValue.build());
            return this;
        }

        public Builder mergeHttp(Http value) {
            copyOnWrite();
            ((Service) this.instance).mergeHttp(value);
            return this;
        }

        public Builder clearHttp() {
            copyOnWrite();
            ((Service) this.instance).clearHttp();
            return this;
        }

        public boolean hasQuota() {
            return ((Service) this.instance).hasQuota();
        }

        public Quota getQuota() {
            return ((Service) this.instance).getQuota();
        }

        public Builder setQuota(Quota value) {
            copyOnWrite();
            ((Service) this.instance).setQuota(value);
            return this;
        }

        public Builder setQuota(Quota.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setQuota((Quota) builderForValue.build());
            return this;
        }

        public Builder mergeQuota(Quota value) {
            copyOnWrite();
            ((Service) this.instance).mergeQuota(value);
            return this;
        }

        public Builder clearQuota() {
            copyOnWrite();
            ((Service) this.instance).clearQuota();
            return this;
        }

        public boolean hasAuthentication() {
            return ((Service) this.instance).hasAuthentication();
        }

        public Authentication getAuthentication() {
            return ((Service) this.instance).getAuthentication();
        }

        public Builder setAuthentication(Authentication value) {
            copyOnWrite();
            ((Service) this.instance).setAuthentication(value);
            return this;
        }

        public Builder setAuthentication(Authentication.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setAuthentication((Authentication) builderForValue.build());
            return this;
        }

        public Builder mergeAuthentication(Authentication value) {
            copyOnWrite();
            ((Service) this.instance).mergeAuthentication(value);
            return this;
        }

        public Builder clearAuthentication() {
            copyOnWrite();
            ((Service) this.instance).clearAuthentication();
            return this;
        }

        public boolean hasContext() {
            return ((Service) this.instance).hasContext();
        }

        public Context getContext() {
            return ((Service) this.instance).getContext();
        }

        public Builder setContext(Context value) {
            copyOnWrite();
            ((Service) this.instance).setContext(value);
            return this;
        }

        public Builder setContext(Context.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setContext((Context) builderForValue.build());
            return this;
        }

        public Builder mergeContext(Context value) {
            copyOnWrite();
            ((Service) this.instance).mergeContext(value);
            return this;
        }

        public Builder clearContext() {
            copyOnWrite();
            ((Service) this.instance).clearContext();
            return this;
        }

        public boolean hasUsage() {
            return ((Service) this.instance).hasUsage();
        }

        public Usage getUsage() {
            return ((Service) this.instance).getUsage();
        }

        public Builder setUsage(Usage value) {
            copyOnWrite();
            ((Service) this.instance).setUsage(value);
            return this;
        }

        public Builder setUsage(Usage.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setUsage((Usage) builderForValue.build());
            return this;
        }

        public Builder mergeUsage(Usage value) {
            copyOnWrite();
            ((Service) this.instance).mergeUsage(value);
            return this;
        }

        public Builder clearUsage() {
            copyOnWrite();
            ((Service) this.instance).clearUsage();
            return this;
        }

        public List<Endpoint> getEndpointsList() {
            return Collections.unmodifiableList(((Service) this.instance).getEndpointsList());
        }

        public int getEndpointsCount() {
            return ((Service) this.instance).getEndpointsCount();
        }

        public Endpoint getEndpoints(int index) {
            return ((Service) this.instance).getEndpoints(index);
        }

        public Builder setEndpoints(int index, Endpoint value) {
            copyOnWrite();
            ((Service) this.instance).setEndpoints(index, value);
            return this;
        }

        public Builder setEndpoints(int index, Endpoint.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setEndpoints(index, (Endpoint) builderForValue.build());
            return this;
        }

        public Builder addEndpoints(Endpoint value) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(value);
            return this;
        }

        public Builder addEndpoints(int index, Endpoint value) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(index, value);
            return this;
        }

        public Builder addEndpoints(Endpoint.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints((Endpoint) builderForValue.build());
            return this;
        }

        public Builder addEndpoints(int index, Endpoint.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(index, (Endpoint) builderForValue.build());
            return this;
        }

        public Builder addAllEndpoints(Iterable<? extends Endpoint> values) {
            copyOnWrite();
            ((Service) this.instance).addAllEndpoints(values);
            return this;
        }

        public Builder clearEndpoints() {
            copyOnWrite();
            ((Service) this.instance).clearEndpoints();
            return this;
        }

        public Builder removeEndpoints(int index) {
            copyOnWrite();
            ((Service) this.instance).removeEndpoints(index);
            return this;
        }

        public boolean hasControl() {
            return ((Service) this.instance).hasControl();
        }

        public Control getControl() {
            return ((Service) this.instance).getControl();
        }

        public Builder setControl(Control value) {
            copyOnWrite();
            ((Service) this.instance).setControl(value);
            return this;
        }

        public Builder setControl(Control.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setControl((Control) builderForValue.build());
            return this;
        }

        public Builder mergeControl(Control value) {
            copyOnWrite();
            ((Service) this.instance).mergeControl(value);
            return this;
        }

        public Builder clearControl() {
            copyOnWrite();
            ((Service) this.instance).clearControl();
            return this;
        }

        public List<LogDescriptor> getLogsList() {
            return Collections.unmodifiableList(((Service) this.instance).getLogsList());
        }

        public int getLogsCount() {
            return ((Service) this.instance).getLogsCount();
        }

        public LogDescriptor getLogs(int index) {
            return ((Service) this.instance).getLogs(index);
        }

        public Builder setLogs(int index, LogDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).setLogs(index, value);
            return this;
        }

        public Builder setLogs(int index, LogDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setLogs(index, (LogDescriptor) builderForValue.build());
            return this;
        }

        public Builder addLogs(LogDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addLogs(value);
            return this;
        }

        public Builder addLogs(int index, LogDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addLogs(index, value);
            return this;
        }

        public Builder addLogs(LogDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addLogs((LogDescriptor) builderForValue.build());
            return this;
        }

        public Builder addLogs(int index, LogDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addLogs(index, (LogDescriptor) builderForValue.build());
            return this;
        }

        public Builder addAllLogs(Iterable<? extends LogDescriptor> values) {
            copyOnWrite();
            ((Service) this.instance).addAllLogs(values);
            return this;
        }

        public Builder clearLogs() {
            copyOnWrite();
            ((Service) this.instance).clearLogs();
            return this;
        }

        public Builder removeLogs(int index) {
            copyOnWrite();
            ((Service) this.instance).removeLogs(index);
            return this;
        }

        public List<MetricDescriptor> getMetricsList() {
            return Collections.unmodifiableList(((Service) this.instance).getMetricsList());
        }

        public int getMetricsCount() {
            return ((Service) this.instance).getMetricsCount();
        }

        public MetricDescriptor getMetrics(int index) {
            return ((Service) this.instance).getMetrics(index);
        }

        public Builder setMetrics(int index, MetricDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).setMetrics(index, value);
            return this;
        }

        public Builder setMetrics(int index, MetricDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setMetrics(index, (MetricDescriptor) builderForValue.build());
            return this;
        }

        public Builder addMetrics(MetricDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(value);
            return this;
        }

        public Builder addMetrics(int index, MetricDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(index, value);
            return this;
        }

        public Builder addMetrics(MetricDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addMetrics((MetricDescriptor) builderForValue.build());
            return this;
        }

        public Builder addMetrics(int index, MetricDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(index, (MetricDescriptor) builderForValue.build());
            return this;
        }

        public Builder addAllMetrics(Iterable<? extends MetricDescriptor> values) {
            copyOnWrite();
            ((Service) this.instance).addAllMetrics(values);
            return this;
        }

        public Builder clearMetrics() {
            copyOnWrite();
            ((Service) this.instance).clearMetrics();
            return this;
        }

        public Builder removeMetrics(int index) {
            copyOnWrite();
            ((Service) this.instance).removeMetrics(index);
            return this;
        }

        public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
            return Collections.unmodifiableList(((Service) this.instance).getMonitoredResourcesList());
        }

        public int getMonitoredResourcesCount() {
            return ((Service) this.instance).getMonitoredResourcesCount();
        }

        public MonitoredResourceDescriptor getMonitoredResources(int index) {
            return ((Service) this.instance).getMonitoredResources(index);
        }

        public Builder setMonitoredResources(int index, MonitoredResourceDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).setMonitoredResources(index, value);
            return this;
        }

        public Builder setMonitoredResources(int index, MonitoredResourceDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setMonitoredResources(index, (MonitoredResourceDescriptor) builderForValue.build());
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(value);
            return this;
        }

        public Builder addMonitoredResources(int index, MonitoredResourceDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(index, value);
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources((MonitoredResourceDescriptor) builderForValue.build());
            return this;
        }

        public Builder addMonitoredResources(int index, MonitoredResourceDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(index, (MonitoredResourceDescriptor) builderForValue.build());
            return this;
        }

        public Builder addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> values) {
            copyOnWrite();
            ((Service) this.instance).addAllMonitoredResources(values);
            return this;
        }

        public Builder clearMonitoredResources() {
            copyOnWrite();
            ((Service) this.instance).clearMonitoredResources();
            return this;
        }

        public Builder removeMonitoredResources(int index) {
            copyOnWrite();
            ((Service) this.instance).removeMonitoredResources(index);
            return this;
        }

        public boolean hasBilling() {
            return ((Service) this.instance).hasBilling();
        }

        public Billing getBilling() {
            return ((Service) this.instance).getBilling();
        }

        public Builder setBilling(Billing value) {
            copyOnWrite();
            ((Service) this.instance).setBilling(value);
            return this;
        }

        public Builder setBilling(Billing.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setBilling((Billing) builderForValue.build());
            return this;
        }

        public Builder mergeBilling(Billing value) {
            copyOnWrite();
            ((Service) this.instance).mergeBilling(value);
            return this;
        }

        public Builder clearBilling() {
            copyOnWrite();
            ((Service) this.instance).clearBilling();
            return this;
        }

        public boolean hasLogging() {
            return ((Service) this.instance).hasLogging();
        }

        public Logging getLogging() {
            return ((Service) this.instance).getLogging();
        }

        public Builder setLogging(Logging value) {
            copyOnWrite();
            ((Service) this.instance).setLogging(value);
            return this;
        }

        public Builder setLogging(Logging.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setLogging((Logging) builderForValue.build());
            return this;
        }

        public Builder mergeLogging(Logging value) {
            copyOnWrite();
            ((Service) this.instance).mergeLogging(value);
            return this;
        }

        public Builder clearLogging() {
            copyOnWrite();
            ((Service) this.instance).clearLogging();
            return this;
        }

        public boolean hasMonitoring() {
            return ((Service) this.instance).hasMonitoring();
        }

        public Monitoring getMonitoring() {
            return ((Service) this.instance).getMonitoring();
        }

        public Builder setMonitoring(Monitoring value) {
            copyOnWrite();
            ((Service) this.instance).setMonitoring(value);
            return this;
        }

        public Builder setMonitoring(Monitoring.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setMonitoring((Monitoring) builderForValue.build());
            return this;
        }

        public Builder mergeMonitoring(Monitoring value) {
            copyOnWrite();
            ((Service) this.instance).mergeMonitoring(value);
            return this;
        }

        public Builder clearMonitoring() {
            copyOnWrite();
            ((Service) this.instance).clearMonitoring();
            return this;
        }

        public boolean hasSystemParameters() {
            return ((Service) this.instance).hasSystemParameters();
        }

        public SystemParameters getSystemParameters() {
            return ((Service) this.instance).getSystemParameters();
        }

        public Builder setSystemParameters(SystemParameters value) {
            copyOnWrite();
            ((Service) this.instance).setSystemParameters(value);
            return this;
        }

        public Builder setSystemParameters(SystemParameters.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setSystemParameters((SystemParameters) builderForValue.build());
            return this;
        }

        public Builder mergeSystemParameters(SystemParameters value) {
            copyOnWrite();
            ((Service) this.instance).mergeSystemParameters(value);
            return this;
        }

        public Builder clearSystemParameters() {
            copyOnWrite();
            ((Service) this.instance).clearSystemParameters();
            return this;
        }

        public boolean hasSourceInfo() {
            return ((Service) this.instance).hasSourceInfo();
        }

        public SourceInfo getSourceInfo() {
            return ((Service) this.instance).getSourceInfo();
        }

        public Builder setSourceInfo(SourceInfo value) {
            copyOnWrite();
            ((Service) this.instance).setSourceInfo(value);
            return this;
        }

        public Builder setSourceInfo(SourceInfo.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setSourceInfo((SourceInfo) builderForValue.build());
            return this;
        }

        public Builder mergeSourceInfo(SourceInfo value) {
            copyOnWrite();
            ((Service) this.instance).mergeSourceInfo(value);
            return this;
        }

        public Builder clearSourceInfo() {
            copyOnWrite();
            ((Service) this.instance).clearSourceInfo();
            return this;
        }
    }

    /* renamed from: com.google.api.Service$1 */
    static /* synthetic */ class C00481 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f40xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f40xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f40xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f40xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f40xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f40xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f40xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f40xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C00481.f40xa1df5c61[method.ordinal()]) {
            case 1:
                return new Service();
            case 2:
                return new Builder((C00481) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0019\u0000\u0000\u0001%\u0019\u0000\u0007\u0000\u0001Ȉ\u0002Ȉ\u0003\u001b\u0004\u001b\u0005\u001b\u0006\t\b\t\t\t\n\t\u000b\t\f\t\u000f\t\u0012\u001b\u0014\t\u0015\t\u0016Ȉ\u0017\u001b\u0018\u001b\u0019\u001b\u001a\t\u001b\t\u001c\t\u001d\t!Ȉ%\t", new Object[]{"name_", "title_", "apis_", Api.class, "types_", Type.class, "enums_", Enum.class, "documentation_", "backend_", "http_", "quota_", "authentication_", "context_", "usage_", "endpoints_", Endpoint.class, "configVersion_", "control_", "producerProjectId_", "logs_", LogDescriptor.class, "metrics_", MetricDescriptor.class, "monitoredResources_", MonitoredResourceDescriptor.class, "billing_", "logging_", "monitoring_", "systemParameters_", "id_", "sourceInfo_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Service> parser = PARSER;
                if (parser == null) {
                    synchronized (Service.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        Service defaultInstance = new Service();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Service.class, defaultInstance);
    }

    public static Service getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Service> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
