package com.google.protobuf;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class Protobuf {
    private static final Protobuf INSTANCE = new Protobuf();
    private final ConcurrentMap<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap();
    private final SchemaFactory schemaFactory = new ManifestSchemaFactory();

    public static Protobuf getInstance() {
        return INSTANCE;
    }

    public <T> void writeTo(T message, Writer writer) throws IOException {
        schemaFor(message).writeTo(message, writer);
    }

    public <T> void mergeFrom(T message, Reader reader) throws IOException {
        mergeFrom(message, reader, ExtensionRegistryLite.getEmptyRegistry());
    }

    public <T> void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        schemaFor(message).mergeFrom(message, reader, extensionRegistry);
    }

    public <T> void makeImmutable(T message) {
        schemaFor(message).makeImmutable(message);
    }

    /* access modifiers changed from: package-private */
    public <T> boolean isInitialized(T message) {
        return schemaFor(message).isInitialized(message);
    }

    public <T> Schema<T> schemaFor(Class<T> messageType) {
        Internal.checkNotNull(messageType, "messageType");
        Schema<T> schema = (Schema) this.schemaCache.get(messageType);
        if (schema != null) {
            return schema;
        }
        Schema<T> schema2 = this.schemaFactory.createSchema(messageType);
        Schema<?> registerSchema = registerSchema(messageType, schema2);
        if (registerSchema != null) {
            return registerSchema;
        }
        return schema2;
    }

    public <T> Schema<T> schemaFor(T message) {
        return schemaFor(message.getClass());
    }

    public Schema<?> registerSchema(Class<?> messageType, Schema<?> schema) {
        Internal.checkNotNull(messageType, "messageType");
        Internal.checkNotNull(schema, "schema");
        return this.schemaCache.putIfAbsent(messageType, schema);
    }

    public Schema<?> registerSchemaOverride(Class<?> messageType, Schema<?> schema) {
        Internal.checkNotNull(messageType, "messageType");
        Internal.checkNotNull(schema, "schema");
        return (Schema) this.schemaCache.put(messageType, schema);
    }

    private Protobuf() {
    }

    /* access modifiers changed from: package-private */
    public int getTotalSchemaSize() {
        int result = 0;
        for (Schema<?> schema : this.schemaCache.values()) {
            if (schema instanceof MessageSchema) {
                result += ((MessageSchema) schema).getSchemaSize();
            }
        }
        return result;
    }
}
