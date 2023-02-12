package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class ExtensionSchemaLite extends ExtensionSchema<GeneratedMessageLite.ExtensionDescriptor> {
    ExtensionSchemaLite() {
    }

    /* access modifiers changed from: package-private */
    public boolean hasExtensions(MessageLite prototype) {
        return prototype instanceof GeneratedMessageLite.ExtendableMessage;
    }

    /* access modifiers changed from: package-private */
    public FieldSet<GeneratedMessageLite.ExtensionDescriptor> getExtensions(Object message) {
        return ((GeneratedMessageLite.ExtendableMessage) message).extensions;
    }

    /* access modifiers changed from: package-private */
    public void setExtensions(Object message, FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions) {
        ((GeneratedMessageLite.ExtendableMessage) message).extensions = extensions;
    }

    /* access modifiers changed from: package-private */
    public FieldSet<GeneratedMessageLite.ExtensionDescriptor> getMutableExtensions(Object message) {
        return ((GeneratedMessageLite.ExtendableMessage) message).ensureExtensionsAreMutable();
    }

    /* access modifiers changed from: package-private */
    public void makeImmutable(Object message) {
        getExtensions(message).makeImmutable();
    }

    /* access modifiers changed from: package-private */
    public <UT, UB> UB parseExtension(Reader reader, Object extensionObject, ExtensionRegistryLite extensionRegistry, FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions, UB unknownFields, UnknownFieldSchema<UT, UB> unknownFieldSchema) throws IOException {
        Object value;
        GeneratedMessageLite.GeneratedExtension<?, ?> extension = (GeneratedMessageLite.GeneratedExtension) extensionObject;
        int fieldNumber = extension.getNumber();
        if (!extension.descriptor.isRepeated() || !extension.descriptor.isPacked()) {
            Object value2 = null;
            if (extension.getLiteType() != WireFormat.FieldType.ENUM) {
                switch (C10081.$SwitchMap$com$google$protobuf$WireFormat$FieldType[extension.getLiteType().ordinal()]) {
                    case 1:
                        value2 = Double.valueOf(reader.readDouble());
                        break;
                    case 2:
                        value2 = Float.valueOf(reader.readFloat());
                        break;
                    case 3:
                        value2 = Long.valueOf(reader.readInt64());
                        break;
                    case 4:
                        value2 = Long.valueOf(reader.readUInt64());
                        break;
                    case 5:
                        value2 = Integer.valueOf(reader.readInt32());
                        break;
                    case 6:
                        value2 = Long.valueOf(reader.readFixed64());
                        break;
                    case 7:
                        value2 = Integer.valueOf(reader.readFixed32());
                        break;
                    case 8:
                        value2 = Boolean.valueOf(reader.readBool());
                        break;
                    case 9:
                        value2 = Integer.valueOf(reader.readUInt32());
                        break;
                    case 10:
                        value2 = Integer.valueOf(reader.readSFixed32());
                        break;
                    case 11:
                        value2 = Long.valueOf(reader.readSFixed64());
                        break;
                    case 12:
                        value2 = Integer.valueOf(reader.readSInt32());
                        break;
                    case 13:
                        value2 = Long.valueOf(reader.readSInt64());
                        break;
                    case 14:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 15:
                        value2 = reader.readBytes();
                        break;
                    case 16:
                        value2 = reader.readString();
                        break;
                    case 17:
                        value2 = reader.readGroup(extension.getMessageDefaultInstance().getClass(), extensionRegistry);
                        break;
                    case 18:
                        value2 = reader.readMessage(extension.getMessageDefaultInstance().getClass(), extensionRegistry);
                        break;
                }
            } else {
                int number = reader.readInt32();
                if (extension.descriptor.getEnumType().findValueByNumber(number) == null) {
                    return SchemaUtil.storeUnknownEnum(fieldNumber, number, unknownFields, unknownFieldSchema);
                }
                value2 = Integer.valueOf(number);
            }
            if (extension.isRepeated()) {
                extensions.addRepeatedField(extension.descriptor, value2);
            } else {
                switch (extension.getLiteType()) {
                    case GROUP:
                    case MESSAGE:
                        Object oldValue = extensions.getField(extension.descriptor);
                        if (oldValue != null) {
                            value2 = Internal.mergeMessage(oldValue, value2);
                            break;
                        }
                        break;
                }
                extensions.setField(extension.descriptor, value2);
            }
        } else {
            switch (C10081.$SwitchMap$com$google$protobuf$WireFormat$FieldType[extension.getLiteType().ordinal()]) {
                case 1:
                    List<Integer> list = new ArrayList<>();
                    reader.readDoubleList(list);
                    value = list;
                    break;
                case 2:
                    List<Long> list2 = new ArrayList<>();
                    reader.readFloatList(list2);
                    value = list2;
                    break;
                case 3:
                    List<Long> list3 = new ArrayList<>();
                    reader.readInt64List(list3);
                    value = list3;
                    break;
                case 4:
                    List<Long> list4 = new ArrayList<>();
                    reader.readUInt64List(list4);
                    value = list4;
                    break;
                case 5:
                    List<Long> list5 = new ArrayList<>();
                    reader.readInt32List(list5);
                    value = list5;
                    break;
                case 6:
                    List<Long> list6 = new ArrayList<>();
                    reader.readFixed64List(list6);
                    value = list6;
                    break;
                case 7:
                    List<Long> list7 = new ArrayList<>();
                    reader.readFixed32List(list7);
                    value = list7;
                    break;
                case 8:
                    List<Long> list8 = new ArrayList<>();
                    reader.readBoolList(list8);
                    value = list8;
                    break;
                case 9:
                    List<Long> list9 = new ArrayList<>();
                    reader.readUInt32List(list9);
                    value = list9;
                    break;
                case 10:
                    List<Long> list10 = new ArrayList<>();
                    reader.readSFixed32List(list10);
                    value = list10;
                    break;
                case 11:
                    List<Long> list11 = new ArrayList<>();
                    reader.readSFixed64List(list11);
                    value = list11;
                    break;
                case 12:
                    List<Long> list12 = new ArrayList<>();
                    reader.readSInt32List(list12);
                    value = list12;
                    break;
                case 13:
                    List<Long> list13 = new ArrayList<>();
                    reader.readSInt64List(list13);
                    value = list13;
                    break;
                case 14:
                    List<Integer> list14 = new ArrayList<>();
                    reader.readEnumList(list14);
                    unknownFields = SchemaUtil.filterUnknownEnumList(fieldNumber, list14, extension.descriptor.getEnumType(), unknownFields, unknownFieldSchema);
                    value = list14;
                    break;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + extension.descriptor.getLiteType());
            }
            extensions.setField(extension.descriptor, value);
        }
        return unknownFields;
    }

    /* access modifiers changed from: package-private */
    public int extensionNumber(Map.Entry<?, ?> extension) {
        return ((GeneratedMessageLite.ExtensionDescriptor) extension.getKey()).getNumber();
    }

    /* access modifiers changed from: package-private */
    public void serializeExtension(Writer writer, Map.Entry<?, ?> extension) throws IOException {
        GeneratedMessageLite.ExtensionDescriptor descriptor = (GeneratedMessageLite.ExtensionDescriptor) extension.getKey();
        if (descriptor.isRepeated()) {
            switch (C10081.$SwitchMap$com$google$protobuf$WireFormat$FieldType[descriptor.getLiteType().ordinal()]) {
                case 1:
                    SchemaUtil.writeDoubleList(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 2:
                    SchemaUtil.writeFloatList(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 3:
                    SchemaUtil.writeInt64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 4:
                    SchemaUtil.writeUInt64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 5:
                    SchemaUtil.writeInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 6:
                    SchemaUtil.writeFixed64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 7:
                    SchemaUtil.writeFixed32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 8:
                    SchemaUtil.writeBoolList(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 9:
                    SchemaUtil.writeUInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 10:
                    SchemaUtil.writeSFixed32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 11:
                    SchemaUtil.writeSFixed64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 12:
                    SchemaUtil.writeSInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 13:
                    SchemaUtil.writeSInt64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 14:
                    SchemaUtil.writeInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    return;
                case 15:
                    SchemaUtil.writeBytesList(descriptor.getNumber(), (List) extension.getValue(), writer);
                    return;
                case 16:
                    SchemaUtil.writeStringList(descriptor.getNumber(), (List) extension.getValue(), writer);
                    return;
                case 17:
                    List<?> data = (List) extension.getValue();
                    if (data != null && !data.isEmpty()) {
                        SchemaUtil.writeGroupList(descriptor.getNumber(), (List) extension.getValue(), writer, Protobuf.getInstance().schemaFor(data.get(0).getClass()));
                        return;
                    }
                    return;
                case 18:
                    List<?> data2 = (List) extension.getValue();
                    if (data2 != null && !data2.isEmpty()) {
                        SchemaUtil.writeMessageList(descriptor.getNumber(), (List) extension.getValue(), writer, Protobuf.getInstance().schemaFor(data2.get(0).getClass()));
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            switch (C10081.$SwitchMap$com$google$protobuf$WireFormat$FieldType[descriptor.getLiteType().ordinal()]) {
                case 1:
                    writer.writeDouble(descriptor.getNumber(), ((Double) extension.getValue()).doubleValue());
                    return;
                case 2:
                    writer.writeFloat(descriptor.getNumber(), ((Float) extension.getValue()).floatValue());
                    return;
                case 3:
                    writer.writeInt64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                    return;
                case 4:
                    writer.writeUInt64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                    return;
                case 5:
                    writer.writeInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                    return;
                case 6:
                    writer.writeFixed64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                    return;
                case 7:
                    writer.writeFixed32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                    return;
                case 8:
                    writer.writeBool(descriptor.getNumber(), ((Boolean) extension.getValue()).booleanValue());
                    return;
                case 9:
                    writer.writeUInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                    return;
                case 10:
                    writer.writeSFixed32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                    return;
                case 11:
                    writer.writeSFixed64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                    return;
                case 12:
                    writer.writeSInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                    return;
                case 13:
                    writer.writeSInt64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                    return;
                case 14:
                    writer.writeInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                    return;
                case 15:
                    writer.writeBytes(descriptor.getNumber(), (ByteString) extension.getValue());
                    return;
                case 16:
                    writer.writeString(descriptor.getNumber(), (String) extension.getValue());
                    return;
                case 17:
                    writer.writeGroup(descriptor.getNumber(), extension.getValue(), Protobuf.getInstance().schemaFor(extension.getValue().getClass()));
                    return;
                case 18:
                    writer.writeMessage(descriptor.getNumber(), extension.getValue(), Protobuf.getInstance().schemaFor(extension.getValue().getClass()));
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object findExtensionByNumber(ExtensionRegistryLite extensionRegistry, MessageLite defaultInstance, int number) {
        return extensionRegistry.findLiteExtensionByNumber(defaultInstance, number);
    }

    /* access modifiers changed from: package-private */
    public void parseLengthPrefixedMessageSetItem(Reader reader, Object extensionObject, ExtensionRegistryLite extensionRegistry, FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions) throws IOException {
        GeneratedMessageLite.GeneratedExtension<?, ?> extension = (GeneratedMessageLite.GeneratedExtension) extensionObject;
        extensions.setField(extension.descriptor, reader.readMessage(extension.getMessageDefaultInstance().getClass(), extensionRegistry));
    }

    /* access modifiers changed from: package-private */
    public void parseMessageSetItem(ByteString data, Object extensionObject, ExtensionRegistryLite extensionRegistry, FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions) throws IOException {
        GeneratedMessageLite.GeneratedExtension<?, ?> extension = (GeneratedMessageLite.GeneratedExtension) extensionObject;
        Object value = extension.getMessageDefaultInstance().newBuilderForType().buildPartial();
        Reader reader = BinaryReader.newInstance(ByteBuffer.wrap(data.toByteArray()), true);
        Protobuf.getInstance().mergeFrom(value, reader, extensionRegistry);
        extensions.setField(extension.descriptor, value);
        if (reader.getFieldNumber() != Integer.MAX_VALUE) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }
}
