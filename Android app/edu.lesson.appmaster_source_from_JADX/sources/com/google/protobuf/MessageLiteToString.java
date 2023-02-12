package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final String LIST_SUFFIX = "List";
    private static final String MAP_SUFFIX = "Map";

    MessageLiteToString() {
    }

    static String toString(MessageLite messageLite, String commentString) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("# ").append(commentString);
        reflectivePrintWithIndent(messageLite, buffer, 0);
        return buffer.toString();
    }

    private static void reflectivePrintWithIndent(MessageLite messageLite, StringBuilder buffer, int indent) {
        Map<String, Method> nameToNoArgMethod;
        int i;
        MessageLite messageLite2 = messageLite;
        StringBuilder sb = buffer;
        int i2 = indent;
        Map<String, Method> hashMap = new HashMap<>();
        Map<String, Method> nameToMethod = new HashMap<>();
        Set<String> getters = new TreeSet<>();
        int i3 = 0;
        for (Method method : messageLite.getClass().getDeclaredMethods()) {
            nameToMethod.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    getters.add(method.getName());
                }
            }
        }
        for (String getter : getters) {
            String suffix = getter.startsWith("get") ? getter.substring(3) : getter;
            if (suffix.endsWith(LIST_SUFFIX) && !suffix.endsWith(BUILDER_LIST_SUFFIX) && !suffix.equals(LIST_SUFFIX)) {
                String camelCase = suffix.substring(i3, 1).toLowerCase() + suffix.substring(1, suffix.length() - LIST_SUFFIX.length());
                Method listMethod = (Method) hashMap.get(getter);
                if (listMethod != null && listMethod.getReturnType().equals(List.class)) {
                    printField(sb, i2, camelCaseToSnakeCase(camelCase), GeneratedMessageLite.invokeOrDie(listMethod, messageLite2, new Object[i3]));
                }
            }
            if (suffix.endsWith(MAP_SUFFIX) && !suffix.equals(MAP_SUFFIX)) {
                String camelCase2 = suffix.substring(i3, 1).toLowerCase() + suffix.substring(1, suffix.length() - MAP_SUFFIX.length());
                Method mapMethod = (Method) hashMap.get(getter);
                if (mapMethod != null && mapMethod.getReturnType().equals(Map.class) && !mapMethod.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(mapMethod.getModifiers())) {
                    printField(sb, i2, camelCaseToSnakeCase(camelCase2), GeneratedMessageLite.invokeOrDie(mapMethod, messageLite2, new Object[i3]));
                }
            }
            if (nameToMethod.get("set" + suffix) != null && (!suffix.endsWith(BYTES_SUFFIX) || !hashMap.containsKey("get" + suffix.substring(i3, suffix.length() - BYTES_SUFFIX.length())))) {
                String camelCase3 = suffix.substring(i3, 1).toLowerCase() + suffix.substring(1);
                Method getMethod = hashMap.get("get" + suffix);
                Method hasMethod = hashMap.get("has" + suffix);
                if (getMethod != null) {
                    Object value = GeneratedMessageLite.invokeOrDie(getMethod, messageLite2, new Object[i3]);
                    if (hasMethod == null) {
                        nameToNoArgMethod = hashMap;
                        i = !isDefaultValue(value) ? 1 : i3;
                    } else {
                        nameToNoArgMethod = hashMap;
                        i = ((Boolean) GeneratedMessageLite.invokeOrDie(hasMethod, messageLite2, new Object[i3])).booleanValue();
                    }
                    if (i != 0) {
                        printField(sb, i2, camelCaseToSnakeCase(camelCase3), value);
                        hashMap = nameToNoArgMethod;
                        i3 = 0;
                    } else {
                        hashMap = nameToNoArgMethod;
                        i3 = 0;
                    }
                } else {
                    Map<String, Method> nameToNoArgMethod2 = hashMap;
                    i3 = 0;
                }
            }
        }
        Map<String, Method> nameToNoArgMethod3 = hashMap;
        if (messageLite2 instanceof GeneratedMessageLite.ExtendableMessage) {
            Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> iter = ((GeneratedMessageLite.ExtendableMessage) messageLite2).extensions.iterator();
            while (iter.hasNext()) {
                Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object> entry = iter.next();
                printField(sb, i2, "[" + entry.getKey().getNumber() + "]", entry.getValue());
            }
        }
        if (((GeneratedMessageLite) messageLite2).unknownFields != null) {
            ((GeneratedMessageLite) messageLite2).unknownFields.printWithIndent(sb, i2);
        }
    }

    private static boolean isDefaultValue(Object o) {
        if (o instanceof Boolean) {
            return !((Boolean) o).booleanValue();
        }
        if (o instanceof Integer) {
            if (((Integer) o).intValue() == 0) {
                return true;
            }
            return false;
        } else if (o instanceof Float) {
            if (Float.floatToRawIntBits(((Float) o).floatValue()) == 0) {
                return true;
            }
            return false;
        } else if (o instanceof Double) {
            if (Double.doubleToRawLongBits(((Double) o).doubleValue()) == 0) {
                return true;
            }
            return false;
        } else if (o instanceof String) {
            return o.equals("");
        } else {
            if (o instanceof ByteString) {
                return o.equals(ByteString.EMPTY);
            }
            if (o instanceof MessageLite) {
                if (o == ((MessageLite) o).getDefaultInstanceForType()) {
                    return true;
                }
                return false;
            } else if (!(o instanceof Enum)) {
                return false;
            } else {
                if (((Enum) o).ordinal() == 0) {
                    return true;
                }
                return false;
            }
        }
    }

    static final void printField(StringBuilder buffer, int indent, String name, Object object) {
        if (object instanceof List) {
            for (Object entry : (List) object) {
                printField(buffer, indent, name, entry);
            }
        } else if (object instanceof Map) {
            for (Map.Entry<?, ?> entry2 : ((Map) object).entrySet()) {
                printField(buffer, indent, name, entry2);
            }
        } else {
            buffer.append(10);
            for (int i = 0; i < indent; i++) {
                buffer.append(' ');
            }
            buffer.append(name);
            if (object instanceof String) {
                buffer.append(": \"").append(TextFormatEscaper.escapeText((String) object)).append('\"');
            } else if (object instanceof ByteString) {
                buffer.append(": \"").append(TextFormatEscaper.escapeBytes((ByteString) object)).append('\"');
            } else if (object instanceof GeneratedMessageLite) {
                buffer.append(" {");
                reflectivePrintWithIndent((GeneratedMessageLite) object, buffer, indent + 2);
                buffer.append("\n");
                for (int i2 = 0; i2 < indent; i2++) {
                    buffer.append(' ');
                }
                buffer.append("}");
            } else if (object instanceof Map.Entry) {
                buffer.append(" {");
                Map.Entry<?, ?> entry3 = (Map.Entry) object;
                printField(buffer, indent + 2, "key", entry3.getKey());
                printField(buffer, indent + 2, "value", entry3.getValue());
                buffer.append("\n");
                for (int i3 = 0; i3 < indent; i3++) {
                    buffer.append(' ');
                }
                buffer.append("}");
            } else {
                buffer.append(": ").append(object.toString());
            }
        }
    }

    private static final String camelCaseToSnakeCase(String camelCase) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                builder.append("_");
            }
            builder.append(Character.toLowerCase(ch));
        }
        return builder.toString();
    }
}
