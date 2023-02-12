package com.google.protobuf;

import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.ByteString;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class MessageSchema<T> implements Schema<T> {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int INTS_PER_FIELD = 3;
    private static final int NO_PRESENCE_SENTINEL = 1048575;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_MASK = 268435456;
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;

    private MessageSchema(int[] buffer2, Object[] objects2, int minFieldNumber2, int maxFieldNumber2, MessageLite defaultInstance2, boolean proto32, boolean useCachedSizeField2, int[] intArray2, int checkInitialized, int mapFieldPositions, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema<?, ?> unknownFieldSchema2, ExtensionSchema<?> extensionSchema2, MapFieldSchema mapFieldSchema2) {
        MessageLite messageLite = defaultInstance2;
        ExtensionSchema<?> extensionSchema3 = extensionSchema2;
        this.buffer = buffer2;
        this.objects = objects2;
        this.minFieldNumber = minFieldNumber2;
        this.maxFieldNumber = maxFieldNumber2;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.proto3 = proto32;
        this.hasExtensions = extensionSchema3 != null && extensionSchema3.hasExtensions(messageLite);
        this.useCachedSizeField = useCachedSizeField2;
        this.intArray = intArray2;
        this.checkInitializedCount = checkInitialized;
        this.repeatedFieldOffsetStart = mapFieldPositions;
        this.newInstanceSchema = newInstanceSchema2;
        this.listFieldSchema = listFieldSchema2;
        this.unknownFieldSchema = unknownFieldSchema2;
        this.extensionSchema = extensionSchema3;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchema2;
    }

    static <T> MessageSchema<T> newSchema(Class<T> cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema<?, ?> unknownFieldSchema2, ExtensionSchema<?> extensionSchema2, MapFieldSchema mapFieldSchema2) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v0, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: java.lang.reflect.Field} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v28, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v31, resolved type: java.lang.reflect.Field} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: java.lang.reflect.Field} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v1, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v60, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v2, resolved type: int[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.protobuf.MessageSchema<T> newSchemaForRawMessageInfo(com.google.protobuf.RawMessageInfo r43, com.google.protobuf.NewInstanceSchema r44, com.google.protobuf.ListFieldSchema r45, com.google.protobuf.UnknownFieldSchema<?, ?> r46, com.google.protobuf.ExtensionSchema<?> r47, com.google.protobuf.MapFieldSchema r48) {
        /*
            com.google.protobuf.ProtoSyntax r0 = r43.getSyntax()
            com.google.protobuf.ProtoSyntax r1 = com.google.protobuf.ProtoSyntax.PROTO3
            if (r0 != r1) goto L_0x000a
            r0 = 1
            goto L_0x000b
        L_0x000a:
            r0 = 0
        L_0x000b:
            java.lang.String r1 = r43.getStringInfo()
            int r15 = r1.length()
            r4 = 0
            int r5 = r4 + 1
            char r4 = r1.charAt(r4)
            r6 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r6) goto L_0x0039
            r7 = r4 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x0023:
            int r9 = r5 + 1
            char r5 = r1.charAt(r5)
            r4 = r5
            if (r5 < r6) goto L_0x0034
            r5 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r8
            r7 = r7 | r5
            int r8 = r8 + 13
            r5 = r9
            goto L_0x0023
        L_0x0034:
            int r5 = r4 << r8
            r4 = r7 | r5
            r5 = r9
        L_0x0039:
            r20 = r4
            int r7 = r5 + 1
            char r4 = r1.charAt(r5)
            if (r4 < r6) goto L_0x005d
            r5 = r4 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x0047:
            int r9 = r7 + 1
            char r7 = r1.charAt(r7)
            r4 = r7
            if (r7 < r6) goto L_0x0058
            r7 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r8
            r5 = r5 | r7
            int r8 = r8 + 13
            r7 = r9
            goto L_0x0047
        L_0x0058:
            int r7 = r4 << r8
            r4 = r5 | r7
            r7 = r9
        L_0x005d:
            r21 = r4
            if (r21 != 0) goto L_0x0082
            r5 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            int[] r16 = EMPTY_INT_ARRAY
            r17 = 0
            r2 = r5
            r22 = r8
            r23 = r9
            r24 = r10
            r25 = r11
            r26 = r12
            r27 = r13
            r28 = r14
            r29 = r16
            r16 = r17
            goto L_0x01bb
        L_0x0082:
            int r5 = r7 + 1
            char r4 = r1.charAt(r7)
            if (r4 < r6) goto L_0x00a4
            r7 = r4 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x008e:
            int r9 = r5 + 1
            char r5 = r1.charAt(r5)
            r4 = r5
            if (r5 < r6) goto L_0x009f
            r5 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r8
            r7 = r7 | r5
            int r8 = r8 + 13
            r5 = r9
            goto L_0x008e
        L_0x009f:
            int r5 = r4 << r8
            r4 = r7 | r5
            r5 = r9
        L_0x00a4:
            r7 = r4
            int r8 = r5 + 1
            char r4 = r1.charAt(r5)
            if (r4 < r6) goto L_0x00c7
            r5 = r4 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x00b1:
            int r10 = r8 + 1
            char r8 = r1.charAt(r8)
            r4 = r8
            if (r8 < r6) goto L_0x00c2
            r8 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r9
            r5 = r5 | r8
            int r9 = r9 + 13
            r8 = r10
            goto L_0x00b1
        L_0x00c2:
            int r8 = r4 << r9
            r4 = r5 | r8
            r8 = r10
        L_0x00c7:
            r5 = r4
            int r9 = r8 + 1
            char r4 = r1.charAt(r8)
            if (r4 < r6) goto L_0x00ea
            r8 = r4 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x00d4:
            int r11 = r9 + 1
            char r9 = r1.charAt(r9)
            r4 = r9
            if (r9 < r6) goto L_0x00e5
            r9 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r8 = r8 | r9
            int r10 = r10 + 13
            r9 = r11
            goto L_0x00d4
        L_0x00e5:
            int r9 = r4 << r10
            r4 = r8 | r9
            r9 = r11
        L_0x00ea:
            r8 = r4
            int r10 = r9 + 1
            char r4 = r1.charAt(r9)
            if (r4 < r6) goto L_0x010d
            r9 = r4 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00f7:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            r4 = r10
            if (r10 < r6) goto L_0x0108
            r10 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r11
            r9 = r9 | r10
            int r11 = r11 + 13
            r10 = r12
            goto L_0x00f7
        L_0x0108:
            int r10 = r4 << r11
            r4 = r9 | r10
            r10 = r12
        L_0x010d:
            r9 = r4
            int r11 = r10 + 1
            char r4 = r1.charAt(r10)
            if (r4 < r6) goto L_0x0130
            r10 = r4 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x011a:
            int r13 = r11 + 1
            char r11 = r1.charAt(r11)
            r4 = r11
            if (r11 < r6) goto L_0x012b
            r11 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r10 = r10 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x011a
        L_0x012b:
            int r11 = r4 << r12
            r4 = r10 | r11
            r11 = r13
        L_0x0130:
            r10 = r4
            int r12 = r11 + 1
            char r4 = r1.charAt(r11)
            if (r4 < r6) goto L_0x0153
            r11 = r4 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x013d:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            r4 = r12
            if (r12 < r6) goto L_0x014e
            r12 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x013d
        L_0x014e:
            int r12 = r4 << r13
            r4 = r11 | r12
            r12 = r14
        L_0x0153:
            r11 = r4
            int r13 = r12 + 1
            char r4 = r1.charAt(r12)
            if (r4 < r6) goto L_0x0178
            r12 = r4 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x0160:
            int r16 = r13 + 1
            char r13 = r1.charAt(r13)
            r4 = r13
            if (r13 < r6) goto L_0x0172
            r13 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r16
            goto L_0x0160
        L_0x0172:
            int r13 = r4 << r14
            r4 = r12 | r13
            r13 = r16
        L_0x0178:
            r12 = r4
            int r14 = r13 + 1
            char r4 = r1.charAt(r13)
            if (r4 < r6) goto L_0x019e
            r13 = r4 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0185:
            int r17 = r14 + 1
            char r14 = r1.charAt(r14)
            r4 = r14
            if (r14 < r6) goto L_0x0198
            r14 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r16
            r13 = r13 | r14
            int r16 = r16 + 13
            r14 = r17
            goto L_0x0185
        L_0x0198:
            int r14 = r4 << r16
            r4 = r13 | r14
            r14 = r17
        L_0x019e:
            r13 = r4
            int r16 = r13 + r11
            int r2 = r16 + r12
            int[] r2 = new int[r2]
            int r16 = r7 * 2
            int r16 = r16 + r5
            r29 = r2
            r22 = r5
            r2 = r7
            r23 = r8
            r24 = r9
            r25 = r10
            r26 = r11
            r27 = r12
            r28 = r13
            r7 = r14
        L_0x01bb:
            sun.misc.Unsafe r14 = UNSAFE
            java.lang.Object[] r30 = r43.getObjects()
            r5 = 0
            com.google.protobuf.MessageLite r8 = r43.getDefaultInstance()
            java.lang.Class r13 = r8.getClass()
            int r8 = r25 * 3
            int[] r12 = new int[r8]
            int r8 = r25 * 2
            java.lang.Object[] r11 = new java.lang.Object[r8]
            r8 = r28
            int r9 = r28 + r26
            r10 = 0
            r31 = r4
            r32 = r5
            r33 = r8
            r34 = r9
            r35 = r10
            r36 = r16
            r10 = r7
        L_0x01e4:
            if (r10 >= r15) goto L_0x0442
            int r4 = r10 + 1
            char r5 = r1.charAt(r10)
            if (r5 < r6) goto L_0x0208
            r7 = r5 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x01f2:
            int r9 = r4 + 1
            char r4 = r1.charAt(r4)
            r5 = r4
            if (r4 < r6) goto L_0x0203
            r4 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r8
            r7 = r7 | r4
            int r8 = r8 + 13
            r4 = r9
            goto L_0x01f2
        L_0x0203:
            int r4 = r5 << r8
            r5 = r7 | r4
            r4 = r9
        L_0x0208:
            r7 = r5
            int r8 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r6) goto L_0x022b
            r5 = r4 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0215:
            int r10 = r8 + 1
            char r8 = r1.charAt(r8)
            r4 = r8
            if (r8 < r6) goto L_0x0226
            r8 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r9
            r5 = r5 | r8
            int r9 = r9 + 13
            r8 = r10
            goto L_0x0215
        L_0x0226:
            int r8 = r4 << r9
            r4 = r5 | r8
            r8 = r10
        L_0x022b:
            r5 = r4
            r9 = r5 & 255(0xff, float:3.57E-43)
            r10 = r5 & 1024(0x400, float:1.435E-42)
            if (r10 == 0) goto L_0x0238
            int r10 = r32 + 1
            r29[r32] = r35
            r32 = r10
        L_0x0238:
            r10 = 51
            if (r9 < r10) goto L_0x02df
            int r10 = r8 + 1
            char r4 = r1.charAt(r8)
            if (r4 < r6) goto L_0x0261
            r8 = r4 & 8191(0x1fff, float:1.1478E-41)
            r31 = 13
        L_0x0248:
            int r37 = r10 + 1
            char r10 = r1.charAt(r10)
            r4 = r10
            if (r10 < r6) goto L_0x025b
            r10 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r31
            r8 = r8 | r10
            int r31 = r31 + 13
            r10 = r37
            goto L_0x0248
        L_0x025b:
            int r10 = r4 << r31
            r4 = r8 | r10
            r10 = r37
        L_0x0261:
            r8 = r4
            int r6 = r9 + -51
            r3 = 9
            if (r6 == r3) goto L_0x0284
            r3 = 17
            if (r6 != r3) goto L_0x026d
            goto L_0x0284
        L_0x026d:
            r3 = 12
            if (r6 != r3) goto L_0x0294
            if (r0 != 0) goto L_0x0294
            int r3 = r35 / 3
            int r3 = r3 * 2
            r16 = 1
            int r3 = r3 + 1
            int r18 = r36 + 1
            r19 = r30[r36]
            r11[r3] = r19
            r36 = r18
            goto L_0x0294
        L_0x0284:
            int r3 = r35 / 3
            int r3 = r3 * 2
            r16 = 1
            int r3 = r3 + 1
            int r18 = r36 + 1
            r19 = r30[r36]
            r11[r3] = r19
            r36 = r18
        L_0x0294:
            int r3 = r8 * 2
            r18 = r4
            r4 = r30[r3]
            r19 = r6
            boolean r6 = r4 instanceof java.lang.reflect.Field
            if (r6 == 0) goto L_0x02a4
            r6 = r4
            java.lang.reflect.Field r6 = (java.lang.reflect.Field) r6
            goto L_0x02ad
        L_0x02a4:
            r6 = r4
            java.lang.String r6 = (java.lang.String) r6
            java.lang.reflect.Field r6 = reflectField(r13, r6)
            r30[r3] = r6
        L_0x02ad:
            r38 = r7
            r31 = r8
            long r7 = r14.objectFieldOffset(r6)
            int r7 = (int) r7
            int r3 = r3 + 1
            r4 = r30[r3]
            boolean r8 = r4 instanceof java.lang.reflect.Field
            if (r8 == 0) goto L_0x02c2
            r8 = r4
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x02cb
        L_0x02c2:
            r8 = r4
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = reflectField(r13, r8)
            r30[r3] = r8
        L_0x02cb:
            r39 = r3
            r40 = r4
            long r3 = r14.objectFieldOffset(r8)
            int r3 = (int) r3
            r4 = 0
            r39 = r1
            r40 = r2
            r31 = r18
            r16 = 1
            goto L_0x0415
        L_0x02df:
            r38 = r7
            int r3 = r36 + 1
            r6 = r30[r36]
            java.lang.String r6 = (java.lang.String) r6
            java.lang.reflect.Field r6 = reflectField(r13, r6)
            r7 = 49
            r10 = 9
            if (r9 == r10) goto L_0x036b
            r10 = 17
            if (r9 != r10) goto L_0x02f7
            goto L_0x036b
        L_0x02f7:
            r10 = 27
            if (r9 == r10) goto L_0x0358
            if (r9 != r7) goto L_0x02fe
            goto L_0x0358
        L_0x02fe:
            r10 = 12
            if (r9 == r10) goto L_0x0340
            r10 = 30
            if (r9 == r10) goto L_0x0340
            r10 = 44
            if (r9 != r10) goto L_0x030b
            goto L_0x0340
        L_0x030b:
            r10 = 50
            if (r9 != r10) goto L_0x033d
            int r10 = r33 + 1
            r29[r33] = r35
            int r18 = r35 / 3
            int r18 = r18 * 2
            int r19 = r3 + 1
            r3 = r30[r3]
            r11[r18] = r3
            r3 = r5 & 2048(0x800, float:2.87E-42)
            if (r3 == 0) goto L_0x0336
            int r3 = r35 / 3
            int r3 = r3 * 2
            r16 = 1
            int r3 = r3 + 1
            int r18 = r19 + 1
            r19 = r30[r19]
            r11[r3] = r19
            r33 = r10
            r3 = r18
            r16 = 1
            goto L_0x0379
        L_0x0336:
            r33 = r10
            r3 = r19
            r16 = 1
            goto L_0x0379
        L_0x033d:
            r16 = 1
            goto L_0x0379
        L_0x0340:
            if (r0 != 0) goto L_0x0355
            int r10 = r35 / 3
            int r10 = r10 * 2
            r16 = 1
            int r10 = r10 + 1
            int r18 = r3 + 1
            r3 = r30[r3]
            r11[r10] = r3
            r3 = r18
            r16 = 1
            goto L_0x0379
        L_0x0355:
            r16 = 1
            goto L_0x0379
        L_0x0358:
            int r10 = r35 / 3
            int r10 = r10 * 2
            r16 = 1
            int r10 = r10 + 1
            int r18 = r3 + 1
            r3 = r30[r3]
            r11[r10] = r3
            r3 = r18
            r16 = 1
            goto L_0x0379
        L_0x036b:
            int r10 = r35 / 3
            int r10 = r10 * 2
            r16 = 1
            int r10 = r10 + 1
            java.lang.Class r18 = r6.getType()
            r11[r10] = r18
        L_0x0379:
            r10 = r8
            long r7 = r14.objectFieldOffset(r6)
            int r7 = (int) r7
            r8 = r5 & 4096(0x1000, float:5.74E-42)
            r19 = r3
            r3 = 4096(0x1000, float:5.74E-42)
            if (r8 != r3) goto L_0x038a
            r3 = r16
            goto L_0x038b
        L_0x038a:
            r3 = 0
        L_0x038b:
            if (r3 == 0) goto L_0x03ed
            r8 = 17
            if (r9 > r8) goto L_0x03ed
            int r8 = r10 + 1
            char r4 = r1.charAt(r10)
            r10 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r10) goto L_0x03c1
            r10 = r4 & 8191(0x1fff, float:1.1478E-41)
            r31 = 13
        L_0x03a0:
            int r36 = r8 + 1
            char r8 = r1.charAt(r8)
            r4 = r8
            r39 = r1
            r1 = 55296(0xd800, float:7.7486E-41)
            if (r8 < r1) goto L_0x03ba
            r8 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r31
            r10 = r10 | r8
            int r31 = r31 + 13
            r8 = r36
            r1 = r39
            goto L_0x03a0
        L_0x03ba:
            int r8 = r4 << r31
            r4 = r10 | r8
            r8 = r36
            goto L_0x03c4
        L_0x03c1:
            r39 = r1
            r1 = r10
        L_0x03c4:
            r10 = r4
            int r31 = r2 * 2
            int r36 = r10 / 32
            int r31 = r31 + r36
            r1 = r30[r31]
            r40 = r2
            boolean r2 = r1 instanceof java.lang.reflect.Field
            if (r2 == 0) goto L_0x03d7
            r2 = r1
            java.lang.reflect.Field r2 = (java.lang.reflect.Field) r2
            goto L_0x03e0
        L_0x03d7:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.reflect.Field r2 = reflectField(r13, r2)
            r30[r31] = r2
        L_0x03e0:
            r36 = r3
            r41 = r4
            long r3 = r14.objectFieldOffset(r2)
            int r3 = (int) r3
            int r10 = r10 % 32
            r4 = r10
            goto L_0x03fc
        L_0x03ed:
            r39 = r1
            r40 = r2
            r36 = r3
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r2 = 0
            r3 = r1
            r41 = r4
            r8 = r10
            r4 = r2
        L_0x03fc:
            r1 = 18
            if (r9 < r1) goto L_0x0410
            r1 = 49
            if (r9 > r1) goto L_0x0410
            int r1 = r34 + 1
            r29[r34] = r7
            r34 = r1
            r10 = r8
            r36 = r19
            r31 = r41
            goto L_0x0415
        L_0x0410:
            r10 = r8
            r36 = r19
            r31 = r41
        L_0x0415:
            int r1 = r35 + 1
            r12[r35] = r38
            int r2 = r1 + 1
            r6 = r5 & 512(0x200, float:7.175E-43)
            if (r6 == 0) goto L_0x0422
            r6 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x0423
        L_0x0422:
            r6 = 0
        L_0x0423:
            r8 = r5 & 256(0x100, float:3.59E-43)
            if (r8 == 0) goto L_0x042a
            r8 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x042b
        L_0x042a:
            r8 = 0
        L_0x042b:
            r6 = r6 | r8
            int r8 = r9 << 20
            r6 = r6 | r8
            r6 = r6 | r7
            r12[r1] = r6
            int r35 = r2 + 1
            int r1 = r4 << 20
            r1 = r1 | r3
            r12[r2] = r1
            r1 = r39
            r2 = r40
            r6 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01e4
        L_0x0442:
            r39 = r1
            r40 = r2
            com.google.protobuf.MessageSchema r1 = new com.google.protobuf.MessageSchema
            com.google.protobuf.MessageLite r9 = r43.getDefaultInstance()
            r2 = 0
            int r3 = r28 + r26
            r4 = r1
            r5 = r12
            r6 = r11
            r7 = r23
            r8 = r24
            r37 = r10
            r10 = r0
            r38 = r11
            r11 = r2
            r2 = r12
            r12 = r29
            r41 = r13
            r13 = r28
            r42 = r14
            r14 = r3
            r3 = r15
            r15 = r44
            r16 = r45
            r17 = r46
            r18 = r47
            r19 = r48
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.newSchemaForRawMessageInfo(com.google.protobuf.RawMessageInfo, com.google.protobuf.NewInstanceSchema, com.google.protobuf.ListFieldSchema, com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, com.google.protobuf.MapFieldSchema):com.google.protobuf.MessageSchema");
    }

    private static Field reflectField(Class<?> messageClass, String fieldName) {
        try {
            return messageClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Field[] fields = messageClass.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + fieldName + " for " + messageClass.getName() + " not found. Known fields are " + Arrays.toString(fields));
        }
    }

    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo messageInfo, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema<?, ?> unknownFieldSchema2, ExtensionSchema<?> extensionSchema2, MapFieldSchema mapFieldSchema2) {
        int maxFieldNumber2;
        int minFieldNumber2;
        int[] checkInitialized;
        int[] mapFieldPositions;
        boolean isProto3;
        boolean isProto32 = messageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fis = messageInfo.getFields();
        if (fis.length == 0) {
            minFieldNumber2 = 0;
            maxFieldNumber2 = 0;
        } else {
            minFieldNumber2 = fis[0].getFieldNumber();
            maxFieldNumber2 = fis[fis.length - 1].getFieldNumber();
        }
        int numEntries = fis.length;
        int[] buffer2 = new int[(numEntries * 3)];
        Object[] objects2 = new Object[(numEntries * 2)];
        int mapFieldCount = 0;
        int repeatedFieldCount = 0;
        for (FieldInfo fi : fis) {
            if (fi.getType() == FieldType.MAP) {
                mapFieldCount++;
            } else if (fi.getType().mo13922id() >= 18 && fi.getType().mo13922id() <= 49) {
                repeatedFieldCount++;
            }
        }
        int[] repeatedFieldOffsets = null;
        int[] mapFieldPositions2 = mapFieldCount > 0 ? new int[mapFieldCount] : null;
        if (repeatedFieldCount > 0) {
            repeatedFieldOffsets = new int[repeatedFieldCount];
        }
        int[] checkInitialized2 = messageInfo.getCheckInitialized();
        if (checkInitialized2 == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        } else {
            checkInitialized = checkInitialized2;
        }
        int mapFieldCount2 = 0;
        int repeatedFieldCount2 = 0;
        int bufferIndex = 0;
        int checkInitializedIndex = 0;
        int fieldIndex = 0;
        while (fieldIndex < fis.length) {
            FieldInfo fi2 = fis[fieldIndex];
            int fieldNumber = fi2.getFieldNumber();
            storeFieldData(fi2, buffer2, bufferIndex, objects2);
            if (checkInitializedIndex < checkInitialized.length && checkInitialized[checkInitializedIndex] == fieldNumber) {
                checkInitialized[checkInitializedIndex] = bufferIndex;
                checkInitializedIndex++;
            }
            FieldInfo[] fis2 = fis;
            if (fi2.getType() == FieldType.MAP) {
                mapFieldPositions2[mapFieldCount2] = bufferIndex;
                mapFieldCount2++;
                isProto3 = isProto32;
            } else if (fi2.getType().mo13922id() < 18 || fi2.getType().mo13922id() > 49) {
                isProto3 = isProto32;
            } else {
                isProto3 = isProto32;
                repeatedFieldOffsets[repeatedFieldCount2] = (int) UnsafeUtil.objectFieldOffset(fi2.getField());
                repeatedFieldCount2++;
            }
            fieldIndex++;
            bufferIndex += 3;
            fis = fis2;
            isProto32 = isProto3;
        }
        boolean isProto33 = isProto32;
        if (mapFieldPositions2 == null) {
            mapFieldPositions = EMPTY_INT_ARRAY;
        } else {
            mapFieldPositions = mapFieldPositions2;
        }
        if (repeatedFieldOffsets == null) {
            repeatedFieldOffsets = EMPTY_INT_ARRAY;
        }
        int[] combined = new int[(checkInitialized.length + mapFieldPositions.length + repeatedFieldOffsets.length)];
        System.arraycopy(checkInitialized, 0, combined, 0, checkInitialized.length);
        System.arraycopy(mapFieldPositions, 0, combined, checkInitialized.length, mapFieldPositions.length);
        System.arraycopy(repeatedFieldOffsets, 0, combined, checkInitialized.length + mapFieldPositions.length, repeatedFieldOffsets.length);
        int i = fieldIndex;
        int i2 = checkInitializedIndex;
        int[] iArr = checkInitialized;
        Object[] objArr = objects2;
        int[] iArr2 = buffer2;
        int i3 = numEntries;
        boolean isProto34 = isProto33;
        int[] iArr3 = combined;
        return new MessageSchema(buffer2, objects2, minFieldNumber2, maxFieldNumber2, messageInfo.getDefaultInstance(), isProto34, true, combined, checkInitialized.length, checkInitialized.length + mapFieldPositions.length, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
    }

    private static void storeFieldData(FieldInfo fi, int[] buffer2, int bufferIndex, Object[] objects2) {
        int presenceFieldOffset;
        int typeId;
        int fieldOffset;
        int typeId2;
        int presenceFieldOffset2;
        OneofInfo oneof = fi.getOneof();
        if (oneof != null) {
            typeId2 = fi.getType().mo13922id() + 51;
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            typeId = (int) UnsafeUtil.objectFieldOffset(oneof.getCaseField());
            presenceFieldOffset = 0;
        } else {
            FieldType type = fi.getType();
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(fi.getField());
            int typeId3 = type.mo13922id();
            if (!type.isList() && !type.isMap()) {
                Field presenceField = fi.getPresenceField();
                if (presenceField == null) {
                    presenceFieldOffset2 = 1048575;
                } else {
                    presenceFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(presenceField);
                }
                presenceFieldOffset = Integer.numberOfTrailingZeros(fi.getPresenceMask());
                typeId2 = typeId3;
                typeId = presenceFieldOffset2;
            } else if (fi.getCachedSizeField() == null) {
                typeId2 = typeId3;
                typeId = 0;
                presenceFieldOffset = 0;
            } else {
                typeId2 = typeId3;
                typeId = (int) UnsafeUtil.objectFieldOffset(fi.getCachedSizeField());
                presenceFieldOffset = 0;
            }
        }
        buffer2[bufferIndex] = fi.getFieldNumber();
        int i = bufferIndex + 1;
        int i2 = 0;
        int i3 = fi.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0;
        if (fi.isRequired()) {
            i2 = REQUIRED_MASK;
        }
        buffer2[i] = i3 | i2 | (typeId2 << 20) | fieldOffset;
        buffer2[bufferIndex + 2] = (presenceFieldOffset << 20) | typeId;
        Object messageFieldClass = fi.getMessageFieldClass();
        if (fi.getMapDefaultEntry() != null) {
            objects2[(bufferIndex / 3) * 2] = fi.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objects2[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
            } else if (fi.getEnumVerifier() != null) {
                objects2[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
            }
        } else if (messageFieldClass != null) {
            objects2[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
        } else if (fi.getEnumVerifier() != null) {
            objects2[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
        }
    }

    public T newInstance() {
        return this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    public boolean equals(T message, T other) {
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            if (!equals(message, other, pos)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(message).equals(this.unknownFieldSchema.getFromMessage(other))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(message).equals(this.extensionSchema.getExtensions(other));
        }
        return true;
    }

    private boolean equals(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        switch (type(typeAndOffset)) {
            case 0:
                if (!arePresentForEquals(message, other, pos) || Double.doubleToLongBits(UnsafeUtil.getDouble((Object) message, offset)) != Double.doubleToLongBits(UnsafeUtil.getDouble((Object) other, offset))) {
                    return false;
                }
                return true;
            case 1:
                if (!arePresentForEquals(message, other, pos) || Float.floatToIntBits(UnsafeUtil.getFloat((Object) message, offset)) != Float.floatToIntBits(UnsafeUtil.getFloat((Object) other, offset))) {
                    return false;
                }
                return true;
            case 2:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getLong((Object) message, offset) != UnsafeUtil.getLong((Object) other, offset)) {
                    return false;
                }
                return true;
            case 3:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getLong((Object) message, offset) != UnsafeUtil.getLong((Object) other, offset)) {
                    return false;
                }
                return true;
            case 4:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt((Object) message, offset) != UnsafeUtil.getInt((Object) other, offset)) {
                    return false;
                }
                return true;
            case 5:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getLong((Object) message, offset) != UnsafeUtil.getLong((Object) other, offset)) {
                    return false;
                }
                return true;
            case 6:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt((Object) message, offset) != UnsafeUtil.getInt((Object) other, offset)) {
                    return false;
                }
                return true;
            case 7:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getBoolean((Object) message, offset) != UnsafeUtil.getBoolean((Object) other, offset)) {
                    return false;
                }
                return true;
            case 8:
                if (!arePresentForEquals(message, other, pos) || !SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) message, offset), UnsafeUtil.getObject((Object) other, offset))) {
                    return false;
                }
                return true;
            case 9:
                if (!arePresentForEquals(message, other, pos) || !SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) message, offset), UnsafeUtil.getObject((Object) other, offset))) {
                    return false;
                }
                return true;
            case 10:
                if (!arePresentForEquals(message, other, pos) || !SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) message, offset), UnsafeUtil.getObject((Object) other, offset))) {
                    return false;
                }
                return true;
            case 11:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt((Object) message, offset) != UnsafeUtil.getInt((Object) other, offset)) {
                    return false;
                }
                return true;
            case 12:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt((Object) message, offset) != UnsafeUtil.getInt((Object) other, offset)) {
                    return false;
                }
                return true;
            case 13:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt((Object) message, offset) != UnsafeUtil.getInt((Object) other, offset)) {
                    return false;
                }
                return true;
            case 14:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getLong((Object) message, offset) != UnsafeUtil.getLong((Object) other, offset)) {
                    return false;
                }
                return true;
            case 15:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt((Object) message, offset) != UnsafeUtil.getInt((Object) other, offset)) {
                    return false;
                }
                return true;
            case 16:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getLong((Object) message, offset) != UnsafeUtil.getLong((Object) other, offset)) {
                    return false;
                }
                return true;
            case 17:
                if (!arePresentForEquals(message, other, pos) || !SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) message, offset), UnsafeUtil.getObject((Object) other, offset))) {
                    return false;
                }
                return true;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) message, offset), UnsafeUtil.getObject((Object) other, offset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) message, offset), UnsafeUtil.getObject((Object) other, offset));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                if (!isOneofCaseEqual(message, other, pos) || !SchemaUtil.safeEquals(UnsafeUtil.getObject((Object) message, offset), UnsafeUtil.getObject((Object) other, offset))) {
                    return false;
                }
                return true;
            default:
                return true;
        }
    }

    public int hashCode(T message) {
        int hashCode = 0;
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int entryNumber = numberAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble((Object) message, offset)));
                    break;
                case 1:
                    hashCode = (hashCode * 53) + Float.floatToIntBits(UnsafeUtil.getFloat((Object) message, offset));
                    break;
                case 2:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong((Object) message, offset));
                    break;
                case 3:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong((Object) message, offset));
                    break;
                case 4:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt((Object) message, offset);
                    break;
                case 5:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong((Object) message, offset));
                    break;
                case 6:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt((Object) message, offset);
                    break;
                case 7:
                    hashCode = (hashCode * 53) + Internal.hashBoolean(UnsafeUtil.getBoolean((Object) message, offset));
                    break;
                case 8:
                    hashCode = (hashCode * 53) + ((String) UnsafeUtil.getObject((Object) message, offset)).hashCode();
                    break;
                case 9:
                    int protoHash = 37;
                    Object submessage = UnsafeUtil.getObject((Object) message, offset);
                    if (submessage != null) {
                        protoHash = submessage.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash;
                    break;
                case 10:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject((Object) message, offset).hashCode();
                    break;
                case 11:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt((Object) message, offset);
                    break;
                case 12:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt((Object) message, offset);
                    break;
                case 13:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt((Object) message, offset);
                    break;
                case 14:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong((Object) message, offset));
                    break;
                case 15:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt((Object) message, offset);
                    break;
                case 16:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong((Object) message, offset));
                    break;
                case 17:
                    int protoHash2 = 37;
                    Object submessage2 = UnsafeUtil.getObject((Object) message, offset);
                    if (submessage2 != null) {
                        protoHash2 = submessage2.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash2;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject((Object) message, offset).hashCode();
                    break;
                case 50:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject((Object) message, offset).hashCode();
                    break;
                case 51:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(message, offset)));
                        break;
                    }
                case 52:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + Float.floatToIntBits(oneofFloatAt(message, offset));
                        break;
                    }
                case 53:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    }
                case 54:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    }
                case 55:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + Internal.hashBoolean(oneofBooleanAt(message, offset));
                        break;
                    }
                case 59:
                    if (!isOneofPresent(message, entryNumber, pos)) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + ((String) UnsafeUtil.getObject((Object) message, offset)).hashCode();
                        break;
                    }
                case 60:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject((Object) message, offset).hashCode();
                        break;
                    }
                case 61:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject((Object) message, offset).hashCode();
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    }
                case 63:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    }
                case 66:
                    if (isOneofPresent(message, entryNumber, pos) == 0) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    }
                case 67:
                    if (!isOneofPresent(message, entryNumber, pos)) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    }
                case 68:
                    if (!isOneofPresent(message, entryNumber, pos)) {
                        break;
                    } else {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject((Object) message, offset).hashCode();
                        break;
                    }
            }
        }
        int hashCode2 = (hashCode * 53) + this.unknownFieldSchema.getFromMessage(message).hashCode();
        if (this.hasExtensions != 0) {
            return (hashCode2 * 53) + this.extensionSchema.getExtensions(message).hashCode();
        }
        return hashCode2;
    }

    public void mergeFrom(T message, T other) {
        if (other != null) {
            for (int i = 0; i < this.buffer.length; i += 3) {
                mergeSingleField(message, other, i);
            }
            SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
            if (this.hasExtensions) {
                SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private void mergeSingleField(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        int number = numberAt(pos);
        switch (type(typeAndOffset)) {
            case 0:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putDouble((Object) message, offset, UnsafeUtil.getDouble((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 1:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putFloat((Object) message, offset, UnsafeUtil.getFloat((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 2:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong((Object) message, offset, UnsafeUtil.getLong((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 3:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong((Object) message, offset, UnsafeUtil.getLong((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 4:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt((Object) message, offset, UnsafeUtil.getInt((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 5:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong((Object) message, offset, UnsafeUtil.getLong((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 6:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt((Object) message, offset, UnsafeUtil.getInt((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 7:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putBoolean((Object) message, offset, UnsafeUtil.getBoolean((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 8:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject((Object) message, offset, UnsafeUtil.getObject((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 9:
                mergeMessage(message, other, pos);
                return;
            case 10:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject((Object) message, offset, UnsafeUtil.getObject((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 11:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt((Object) message, offset, UnsafeUtil.getInt((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 12:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt((Object) message, offset, UnsafeUtil.getInt((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 13:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt((Object) message, offset, UnsafeUtil.getInt((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 14:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong((Object) message, offset, UnsafeUtil.getLong((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 15:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt((Object) message, offset, UnsafeUtil.getInt((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 16:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong((Object) message, offset, UnsafeUtil.getLong((Object) other, offset));
                    setFieldPresent(message, pos);
                    return;
                }
                return;
            case 17:
                mergeMessage(message, other, pos);
                return;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(message, other, offset);
                return;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, message, other, offset);
                return;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject((Object) message, offset, UnsafeUtil.getObject((Object) other, offset));
                    setOneofPresent(message, number, pos);
                    return;
                }
                return;
            case 60:
                mergeOneofMessage(message, other, pos);
                return;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject((Object) message, offset, UnsafeUtil.getObject((Object) other, offset));
                    setOneofPresent(message, number, pos);
                    return;
                }
                return;
            case 68:
                mergeOneofMessage(message, other, pos);
                return;
            default:
                return;
        }
    }

    private void mergeMessage(T message, T other, int pos) {
        long offset = offset(typeAndOffsetAt(pos));
        if (isFieldPresent(other, pos)) {
            Object mine = UnsafeUtil.getObject((Object) message, offset);
            Object theirs = UnsafeUtil.getObject((Object) other, offset);
            if (mine != null && theirs != null) {
                UnsafeUtil.putObject((Object) message, offset, Internal.mergeMessage(mine, theirs));
                setFieldPresent(message, pos);
            } else if (theirs != null) {
                UnsafeUtil.putObject((Object) message, offset, theirs);
                setFieldPresent(message, pos);
            }
        }
    }

    private void mergeOneofMessage(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        int number = numberAt(pos);
        long offset = offset(typeAndOffset);
        if (isOneofPresent(other, number, pos)) {
            Object mine = null;
            if (isOneofPresent(message, number, pos)) {
                mine = UnsafeUtil.getObject((Object) message, offset);
            }
            Object theirs = UnsafeUtil.getObject((Object) other, offset);
            if (mine != null && theirs != null) {
                UnsafeUtil.putObject((Object) message, offset, Internal.mergeMessage(mine, theirs));
                setOneofPresent(message, number, pos);
            } else if (theirs != null) {
                UnsafeUtil.putObject((Object) message, offset, theirs);
                setOneofPresent(message, number, pos);
            }
        }
    }

    public int getSerializedSize(T message) {
        return this.proto3 ? getSerializedSizeProto3(message) : getSerializedSizeProto2(message);
    }

    private int getSerializedSizeProto2(T message) {
        int currentPresenceFieldOffset;
        T t = message;
        int size = 0;
        Unsafe unsafe = UNSAFE;
        int currentPresenceFieldOffset2 = 1048575;
        int currentPresenceField = 0;
        int i = 0;
        while (i < this.buffer.length) {
            int typeAndOffset = typeAndOffsetAt(i);
            int number = numberAt(i);
            int fieldType = type(typeAndOffset);
            int presenceMaskAndOffset = 0;
            int presenceMask = 0;
            if (fieldType <= 17) {
                presenceMaskAndOffset = this.buffer[i + 2];
                int presenceFieldOffset = presenceMaskAndOffset & 1048575;
                presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                if (presenceFieldOffset != currentPresenceFieldOffset2) {
                    currentPresenceFieldOffset2 = presenceFieldOffset;
                    currentPresenceField = unsafe.getInt(t, (long) presenceFieldOffset);
                }
            } else if (this.useCachedSizeField && fieldType >= FieldType.DOUBLE_LIST_PACKED.mo13922id() && fieldType <= FieldType.SINT64_LIST_PACKED.mo13922id()) {
                presenceMaskAndOffset = this.buffer[i + 2] & 1048575;
            }
            long offset = offset(typeAndOffset);
            switch (fieldType) {
                case 0:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        break;
                    }
                case 1:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    }
                case 2:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt64Size(number, unsafe.getLong(t, offset));
                        break;
                    }
                case 3:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt64Size(number, unsafe.getLong(t, offset));
                        break;
                    }
                case 4:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt32Size(number, unsafe.getInt(t, offset));
                        break;
                    }
                case 5:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed64Size(number, 0);
                        break;
                    }
                case 6:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    }
                case 7:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    }
                case 8:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        Object value = unsafe.getObject(t, offset);
                        if (!(value instanceof ByteString)) {
                            size += CodedOutputStream.computeStringSize(number, (String) value);
                            break;
                        } else {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value);
                            break;
                        }
                    }
                case 9:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += SchemaUtil.computeSizeMessage(number, unsafe.getObject(t, offset), getMessageFieldSchema(i));
                        break;
                    }
                case 10:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(t, offset));
                        break;
                    }
                case 11:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt32Size(number, unsafe.getInt(t, offset));
                        break;
                    }
                case 12:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeEnumSize(number, unsafe.getInt(t, offset));
                        break;
                    }
                case 13:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    }
                case 14:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed64Size(number, 0);
                        break;
                    }
                case 15:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt32Size(number, unsafe.getInt(t, offset));
                        break;
                    }
                case 16:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt64Size(number, unsafe.getLong(t, offset));
                        break;
                    }
                case 17:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(t, offset), getMessageFieldSchema(i));
                        break;
                    }
                case 18:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 19:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 20:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeInt64List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 21:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeUInt64List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 22:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeInt32List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 23:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 24:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 25:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeBoolList(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 26:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeStringList(number, (List) unsafe.getObject(t, offset));
                    break;
                case 27:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeMessageList(number, (List) unsafe.getObject(t, offset), getMessageFieldSchema(i));
                    break;
                case 28:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeByteStringList(number, (List) unsafe.getObject(t, offset));
                    break;
                case 29:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeUInt32List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 30:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeEnumList(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 31:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 32:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 33:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeSInt32List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 34:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeSInt64List(number, (List) unsafe.getObject(t, offset), false);
                    break;
                case 35:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize2);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize3);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize4 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize4);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize5 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize5);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize6 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize6);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize7 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize7);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize8 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize8);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize9 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize9);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize10 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize10);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize11 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize11);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize12 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize12);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize13 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize13);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize14 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) presenceMaskAndOffset, fieldSize14);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeGroupList(number, (List) unsafe.getObject(t, offset), getMessageFieldSchema(i));
                    break;
                case 50:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += this.mapFieldSchema.getSerializedSize(number, unsafe.getObject(t, offset), getMapFieldDefaultEntry(i));
                    break;
                case 51:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 52:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 53:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeInt64Size(number, oneofLongAt(t, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 54:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(t, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 55:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeInt32Size(number, oneofIntAt(t, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 56:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed64Size(number, 0);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 57:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 58:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 59:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        Object value2 = unsafe.getObject(t, offset);
                        if (value2 instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value2);
                        }
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 60:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += SchemaUtil.computeSizeMessage(number, unsafe.getObject(t, offset), getMessageFieldSchema(i));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 61:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(t, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 62:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(t, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 63:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeEnumSize(number, oneofIntAt(t, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 64:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 65:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed64Size(number, 0);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 66:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(t, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 67:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(t, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 68:
                    if (!isOneofPresent(t, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(t, offset), getMessageFieldSchema(i));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                default:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    break;
            }
            i += 3;
            currentPresenceFieldOffset2 = currentPresenceFieldOffset;
        }
        int size2 = size + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t);
        if (this.hasExtensions) {
            return size2 + this.extensionSchema.getExtensions(t).getSerializedSize();
        }
        return size2;
    }

    private int getSerializedSizeProto3(T message) {
        T t = message;
        Unsafe unsafe = UNSAFE;
        int size = 0;
        for (int i = 0; i < this.buffer.length; i += 3) {
            int typeAndOffset = typeAndOffsetAt(i);
            int fieldType = type(typeAndOffset);
            int number = numberAt(i);
            long offset = offset(typeAndOffset);
            int cachedSizeOffset = (fieldType < FieldType.DOUBLE_LIST_PACKED.mo13922id() || fieldType > FieldType.SINT64_LIST_PACKED.mo13922id()) ? 0 : this.buffer[i + 2] & 1048575;
            switch (fieldType) {
                case 0:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        break;
                    }
                case 1:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    }
                case 2:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt64Size(number, UnsafeUtil.getLong((Object) t, offset));
                        break;
                    }
                case 3:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt64Size(number, UnsafeUtil.getLong((Object) t, offset));
                        break;
                    }
                case 4:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt32Size(number, UnsafeUtil.getInt((Object) t, offset));
                        break;
                    }
                case 5:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed64Size(number, 0);
                        break;
                    }
                case 6:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    }
                case 7:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    }
                case 8:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        Object value = UnsafeUtil.getObject((Object) t, offset);
                        if (!(value instanceof ByteString)) {
                            size += CodedOutputStream.computeStringSize(number, (String) value);
                            break;
                        } else {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value);
                            break;
                        }
                    }
                case 9:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += SchemaUtil.computeSizeMessage(number, UnsafeUtil.getObject((Object) t, offset), getMessageFieldSchema(i));
                        break;
                    }
                case 10:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) UnsafeUtil.getObject((Object) t, offset));
                        break;
                    }
                case 11:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt32Size(number, UnsafeUtil.getInt((Object) t, offset));
                        break;
                    }
                case 12:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeEnumSize(number, UnsafeUtil.getInt((Object) t, offset));
                        break;
                    }
                case 13:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    }
                case 14:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed64Size(number, 0);
                        break;
                    }
                case 15:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt32Size(number, UnsafeUtil.getInt((Object) t, offset));
                        break;
                    }
                case 16:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt64Size(number, UnsafeUtil.getLong((Object) t, offset));
                        break;
                    }
                case 17:
                    if (!isFieldPresent(t, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) UnsafeUtil.getObject((Object) t, offset), getMessageFieldSchema(i));
                        break;
                    }
                case 18:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(t, offset), false);
                    break;
                case 19:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(t, offset), false);
                    break;
                case 20:
                    size += SchemaUtil.computeSizeInt64List(number, listAt(t, offset), false);
                    break;
                case 21:
                    size += SchemaUtil.computeSizeUInt64List(number, listAt(t, offset), false);
                    break;
                case 22:
                    size += SchemaUtil.computeSizeInt32List(number, listAt(t, offset), false);
                    break;
                case 23:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(t, offset), false);
                    break;
                case 24:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(t, offset), false);
                    break;
                case 25:
                    size += SchemaUtil.computeSizeBoolList(number, listAt(t, offset), false);
                    break;
                case 26:
                    size += SchemaUtil.computeSizeStringList(number, listAt(t, offset));
                    break;
                case 27:
                    size += SchemaUtil.computeSizeMessageList(number, listAt(t, offset), getMessageFieldSchema(i));
                    break;
                case 28:
                    size += SchemaUtil.computeSizeByteStringList(number, listAt(t, offset));
                    break;
                case 29:
                    size += SchemaUtil.computeSizeUInt32List(number, listAt(t, offset), false);
                    break;
                case 30:
                    size += SchemaUtil.computeSizeEnumList(number, listAt(t, offset), false);
                    break;
                case 31:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(t, offset), false);
                    break;
                case 32:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(t, offset), false);
                    break;
                case 33:
                    size += SchemaUtil.computeSizeSInt32List(number, listAt(t, offset), false);
                    break;
                case 34:
                    size += SchemaUtil.computeSizeSInt64List(number, listAt(t, offset), false);
                    break;
                case 35:
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize2);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize3);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize4 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize4);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize5 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize5);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize6 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize6);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize7 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize7);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize8 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize8);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize9 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize9);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize10 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize10);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize11 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize11);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize12 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize12);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize13 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize13);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (fieldSize14 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, (long) cachedSizeOffset, fieldSize14);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    size += SchemaUtil.computeSizeGroupList(number, listAt(t, offset), getMessageFieldSchema(i));
                    break;
                case 50:
                    size += this.mapFieldSchema.getSerializedSize(number, UnsafeUtil.getObject((Object) t, offset), getMapFieldDefaultEntry(i));
                    break;
                case 51:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        break;
                    }
                case 52:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    }
                case 53:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt64Size(number, oneofLongAt(t, offset));
                        break;
                    }
                case 54:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(t, offset));
                        break;
                    }
                case 55:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt32Size(number, oneofIntAt(t, offset));
                        break;
                    }
                case 56:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed64Size(number, 0);
                        break;
                    }
                case 57:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    }
                case 58:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    }
                case 59:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        Object value2 = UnsafeUtil.getObject((Object) t, offset);
                        if (!(value2 instanceof ByteString)) {
                            size += CodedOutputStream.computeStringSize(number, (String) value2);
                            break;
                        } else {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                            break;
                        }
                    }
                case 60:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += SchemaUtil.computeSizeMessage(number, UnsafeUtil.getObject((Object) t, offset), getMessageFieldSchema(i));
                        break;
                    }
                case 61:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) UnsafeUtil.getObject((Object) t, offset));
                        break;
                    }
                case 62:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(t, offset));
                        break;
                    }
                case 63:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeEnumSize(number, oneofIntAt(t, offset));
                        break;
                    }
                case 64:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    }
                case 65:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed64Size(number, 0);
                        break;
                    }
                case 66:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(t, offset));
                        break;
                    }
                case 67:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(t, offset));
                        break;
                    }
                case 68:
                    if (!isOneofPresent(t, number, i)) {
                        break;
                    } else {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) UnsafeUtil.getObject((Object) t, offset), getMessageFieldSchema(i));
                        break;
                    }
            }
        }
        return size + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        return schema.getSerializedSize(schema.getFromMessage(message));
    }

    private static List<?> listAt(Object message, long offset) {
        return (List) UnsafeUtil.getObject(message, offset);
    }

    public void writeTo(T message, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(message, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(message, writer);
        } else {
            writeFieldsInAscendingOrderProto2(message, writer);
        }
    }

    private void writeFieldsInAscendingOrderProto2(T message, Writer writer) throws IOException {
        Map.Entry nextExtension;
        int currentPresenceFieldOffset;
        T t = message;
        Writer writer2 = writer;
        Iterator<Map.Entry<?, Object>> it = null;
        Map.Entry nextExtension2 = null;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(t);
            if (!extensions.isEmpty()) {
                it = extensions.iterator();
                nextExtension2 = it.next();
            }
        }
        int currentPresenceFieldOffset2 = 1048575;
        int currentPresenceField = 0;
        int bufferLength = this.buffer.length;
        Unsafe unsafe = UNSAFE;
        int pos = 0;
        while (pos < bufferLength) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int number = numberAt(pos);
            int fieldType = type(typeAndOffset);
            int presenceMask = 0;
            Map.Entry nextExtension3 = nextExtension2;
            if (fieldType <= 17) {
                int presenceMaskAndOffset = this.buffer[pos + 2];
                int presenceFieldOffset = 1048575 & presenceMaskAndOffset;
                if (presenceFieldOffset != currentPresenceFieldOffset2) {
                    currentPresenceField = unsafe.getInt(t, (long) presenceFieldOffset);
                    currentPresenceFieldOffset2 = presenceFieldOffset;
                }
                presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                nextExtension = nextExtension3;
            } else {
                nextExtension = nextExtension3;
            }
            while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) <= number) {
                this.extensionSchema.serializeExtension(writer2, nextExtension);
                nextExtension = it.hasNext() ? it.next() : null;
            }
            Map.Entry nextExtension4 = nextExtension;
            int currentPresenceFieldOffset3 = currentPresenceFieldOffset2;
            long offset = offset(typeAndOffset);
            int bufferLength2 = bufferLength;
            switch (fieldType) {
                case 0:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeDouble(number, doubleAt(t, offset));
                        break;
                    }
                case 1:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i2 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeFloat(number, floatAt(t, offset));
                        break;
                    }
                case 2:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i3 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeInt64(number, unsafe.getLong(t, offset));
                        break;
                    }
                case 3:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i4 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeUInt64(number, unsafe.getLong(t, offset));
                        break;
                    }
                case 4:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i5 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeInt32(number, unsafe.getInt(t, offset));
                        break;
                    }
                case 5:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i6 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeFixed64(number, unsafe.getLong(t, offset));
                        break;
                    }
                case 6:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i7 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeFixed32(number, unsafe.getInt(t, offset));
                        break;
                    }
                case 7:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i8 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeBool(number, booleanAt(t, offset));
                        break;
                    }
                case 8:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i9 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writeString(number, unsafe.getObject(t, offset), writer2);
                        break;
                    }
                case 9:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i10 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeMessage(number, unsafe.getObject(t, offset), getMessageFieldSchema(pos));
                        break;
                    }
                case 10:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i11 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeBytes(number, (ByteString) unsafe.getObject(t, offset));
                        break;
                    }
                case 11:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i12 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeUInt32(number, unsafe.getInt(t, offset));
                        break;
                    }
                case 12:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i13 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeEnum(number, unsafe.getInt(t, offset));
                        break;
                    }
                case 13:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i14 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeSFixed32(number, unsafe.getInt(t, offset));
                        break;
                    }
                case 14:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i15 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeSFixed64(number, unsafe.getLong(t, offset));
                        break;
                    }
                case 15:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i16 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeSInt32(number, unsafe.getInt(t, offset));
                        break;
                    }
                case 16:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i17 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeSInt64(number, unsafe.getLong(t, offset));
                        break;
                    }
                case 17:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i18 = typeAndOffset;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer2.writeGroup(number, unsafe.getObject(t, offset), getMessageFieldSchema(pos));
                        break;
                    }
                case 18:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i19 = typeAndOffset;
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 19:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i20 = typeAndOffset;
                    SchemaUtil.writeFloatList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 20:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i21 = typeAndOffset;
                    SchemaUtil.writeInt64List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 21:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i22 = typeAndOffset;
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 22:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i23 = typeAndOffset;
                    SchemaUtil.writeInt32List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 23:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i24 = typeAndOffset;
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 24:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i25 = typeAndOffset;
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 25:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i26 = typeAndOffset;
                    SchemaUtil.writeBoolList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 26:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i27 = typeAndOffset;
                    SchemaUtil.writeStringList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2);
                    break;
                case 27:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i28 = typeAndOffset;
                    SchemaUtil.writeMessageList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, getMessageFieldSchema(pos));
                    break;
                case 28:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i29 = typeAndOffset;
                    SchemaUtil.writeBytesList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2);
                    break;
                case 29:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i30 = typeAndOffset;
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 30:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i31 = typeAndOffset;
                    SchemaUtil.writeEnumList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 31:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i32 = typeAndOffset;
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 32:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i33 = typeAndOffset;
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 33:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i34 = typeAndOffset;
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 34:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i35 = typeAndOffset;
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, false);
                    break;
                case 35:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i36 = typeAndOffset;
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 36:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i37 = typeAndOffset;
                    SchemaUtil.writeFloatList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 37:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i38 = typeAndOffset;
                    SchemaUtil.writeInt64List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 38:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i39 = typeAndOffset;
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 39:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i40 = typeAndOffset;
                    SchemaUtil.writeInt32List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 40:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i41 = typeAndOffset;
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 41:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i42 = typeAndOffset;
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 42:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i43 = typeAndOffset;
                    SchemaUtil.writeBoolList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 43:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i44 = typeAndOffset;
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 44:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i45 = typeAndOffset;
                    SchemaUtil.writeEnumList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 45:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i46 = typeAndOffset;
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 46:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i47 = typeAndOffset;
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 47:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i48 = typeAndOffset;
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 48:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i49 = typeAndOffset;
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, true);
                    break;
                case 49:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i50 = typeAndOffset;
                    SchemaUtil.writeGroupList(numberAt(pos), (List) unsafe.getObject(t, offset), writer2, getMessageFieldSchema(pos));
                    break;
                case 50:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i51 = typeAndOffset;
                    writeMapHelper(writer2, number, unsafe.getObject(t, offset), pos);
                    break;
                case 51:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i52 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeDouble(number, oneofDoubleAt(t, offset));
                        break;
                    }
                case 52:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i53 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeFloat(number, oneofFloatAt(t, offset));
                        break;
                    }
                case 53:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i54 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeInt64(number, oneofLongAt(t, offset));
                        break;
                    }
                case 54:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i55 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeUInt64(number, oneofLongAt(t, offset));
                        break;
                    }
                case 55:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i56 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeInt32(number, oneofIntAt(t, offset));
                        break;
                    }
                case 56:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i57 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeFixed64(number, oneofLongAt(t, offset));
                        break;
                    }
                case 57:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i58 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeFixed32(number, oneofIntAt(t, offset));
                        break;
                    }
                case 58:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i59 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeBool(number, oneofBooleanAt(t, offset));
                        break;
                    }
                case 59:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i60 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writeString(number, unsafe.getObject(t, offset), writer2);
                        break;
                    }
                case 60:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i61 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeMessage(number, unsafe.getObject(t, offset), getMessageFieldSchema(pos));
                        break;
                    }
                case 61:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i62 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeBytes(number, (ByteString) unsafe.getObject(t, offset));
                        break;
                    }
                case 62:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i63 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeUInt32(number, oneofIntAt(t, offset));
                        break;
                    }
                case 63:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i64 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeEnum(number, oneofIntAt(t, offset));
                        break;
                    }
                case 64:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i65 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeSFixed32(number, oneofIntAt(t, offset));
                        break;
                    }
                case 65:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i66 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeSFixed64(number, oneofLongAt(t, offset));
                        break;
                    }
                case 66:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i67 = typeAndOffset;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        writer2.writeSInt32(number, oneofIntAt(t, offset));
                        break;
                    }
                case 67:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(t, number, pos)) {
                        break;
                    } else {
                        int i68 = typeAndOffset;
                        writer2.writeSInt64(number, oneofLongAt(t, offset));
                        break;
                    }
                case 68:
                    if (!isOneofPresent(t, number, pos)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset3;
                        break;
                    } else {
                        currentPresenceFieldOffset = currentPresenceFieldOffset3;
                        writer2.writeGroup(number, unsafe.getObject(t, offset), getMessageFieldSchema(pos));
                        break;
                    }
                default:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int i69 = typeAndOffset;
                    break;
            }
            pos += 3;
            currentPresenceFieldOffset2 = currentPresenceFieldOffset;
            nextExtension2 = nextExtension4;
            bufferLength = bufferLength2;
        }
        int i70 = bufferLength;
        while (nextExtension2 != null) {
            this.extensionSchema.serializeExtension(writer2, nextExtension2);
            nextExtension2 = it.hasNext() ? it.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, t, writer2);
    }

    private void writeFieldsInAscendingOrderProto3(T message, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> it = null;
        Map.Entry nextExtension = null;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                it = extensions.iterator();
                nextExtension = it.next();
            }
        }
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int number = numberAt(pos);
            while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) <= number) {
                this.extensionSchema.serializeExtension(writer, nextExtension);
                nextExtension = it.hasNext() ? it.next() : null;
            }
            switch (type(typeAndOffset)) {
                case 0:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 1:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 2:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 3:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 4:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 5:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 6:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 7:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 8:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writeString(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer);
                        break;
                    }
                case 9:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeMessage(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        break;
                    }
                case 10:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeBytes(number, (ByteString) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)));
                        break;
                    }
                case 11:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 12:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 13:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 14:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 15:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 16:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 17:
                    if (!isFieldPresent(message, pos)) {
                        break;
                    } else {
                        writer.writeGroup(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        break;
                    }
                case 18:
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                    break;
                case 50:
                    writeMapHelper(writer, number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), pos);
                    break;
                case 51:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 52:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 53:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 54:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 55:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 56:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 57:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 58:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 59:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writeString(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer);
                        break;
                    }
                case 60:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeMessage(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        break;
                    }
                case 61:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeBytes(number, (ByteString) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)));
                        break;
                    }
                case 62:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 63:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 64:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 65:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 66:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 67:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    }
                case 68:
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeGroup(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        break;
                    }
            }
        }
        while (nextExtension != null) {
            this.extensionSchema.serializeExtension(writer, nextExtension);
            nextExtension = it.hasNext() ? it.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    private void writeFieldsInDescendingOrder(T message, Writer writer) throws IOException {
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
        Iterator<Map.Entry<?, Object>> it = null;
        Map.Entry nextExtension = null;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                it = extensions.descendingIterator();
                nextExtension = it.next();
            }
        }
        int pos = this.buffer.length;
        while (true) {
            pos -= 3;
            if (pos >= 0) {
                int typeAndOffset = typeAndOffsetAt(pos);
                int number = numberAt(pos);
                while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) > number) {
                    this.extensionSchema.serializeExtension(writer, nextExtension);
                    nextExtension = it.hasNext() ? it.next() : null;
                }
                switch (type(typeAndOffset)) {
                    case 0:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 1:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 2:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 3:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 4:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 5:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 6:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 7:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 8:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writeString(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer);
                            break;
                        }
                    case 9:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeMessage(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                            break;
                        }
                    case 10:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)));
                            break;
                        }
                    case 11:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 12:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 13:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 14:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 15:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 16:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 17:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeGroup(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                            break;
                        }
                    case 18:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 19:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 20:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 21:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 22:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 23:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 24:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 25:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 26:
                        SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer);
                        break;
                    case 27:
                        SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 28:
                        SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer);
                        break;
                    case 29:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 30:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 31:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 32:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 33:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 34:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, false);
                        break;
                    case 35:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 36:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 37:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 38:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 39:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 40:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 41:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 42:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 43:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 44:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 45:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 46:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 47:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 48:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, true);
                        break;
                    case 49:
                        SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 50:
                        writeMapHelper(writer, number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), pos);
                        break;
                    case 51:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 52:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 53:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 54:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 55:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 56:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 57:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 58:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 59:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writeString(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), writer);
                            break;
                        }
                    case 60:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeMessage(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                            break;
                        }
                    case 61:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject((Object) message, offset(typeAndOffset)));
                            break;
                        }
                    case 62:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 63:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 64:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 65:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 66:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 67:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 68:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeGroup(number, UnsafeUtil.getObject((Object) message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                            break;
                        }
                }
            } else {
                while (nextExtension != null) {
                    this.extensionSchema.serializeExtension(writer, nextExtension);
                    nextExtension = it.hasNext() ? it.next() : null;
                }
                return;
            }
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int number, Object mapField, int pos) throws IOException {
        if (mapField != null) {
            writer.writeMap(number, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos)), this.mapFieldSchema.forMapData(mapField));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> schema, T message, Writer writer) throws IOException {
        schema.writeTo(schema.getFromMessage(message), writer);
    }

    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        if (extensionRegistry != null) {
            mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
            return;
        }
        throw new NullPointerException();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0700 A[LOOP:6: B:203:0x06fc->B:205:0x0700, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x070d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <UT, UB, ET extends com.google.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(com.google.protobuf.UnknownFieldSchema<UT, UB> r19, com.google.protobuf.ExtensionSchema<ET> r20, T r21, com.google.protobuf.Reader r22, com.google.protobuf.ExtensionRegistryLite r23) throws java.io.IOException {
        /*
            r18 = this;
            r8 = r18
            r9 = r19
            r10 = r21
            r11 = r22
            r12 = r23
            r1 = 0
            r2 = 0
            r13 = r1
            r14 = r2
        L_0x000e:
            int r1 = r22.getFieldNumber()     // Catch:{ all -> 0x06f6 }
            r15 = r1
            int r1 = r8.positionForFieldNumber(r15)     // Catch:{ all -> 0x06f6 }
            r7 = r1
            if (r7 >= 0) goto L_0x00a8
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r15 != r1) goto L_0x0036
            int r1 = r8.checkInitializedCount
        L_0x0021:
            int r2 = r8.repeatedFieldOffsetStart
            if (r1 >= r2) goto L_0x0030
            int[] r2 = r8.intArray
            r2 = r2[r1]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r2, r13, r9)
            int r1 = r1 + 1
            goto L_0x0021
        L_0x0030:
            if (r13 == 0) goto L_0x0035
            r9.setBuilderToMessage(r10, r13)
        L_0x0035:
            return
        L_0x0036:
            boolean r1 = r8.hasExtensions     // Catch:{ all -> 0x06f6 }
            if (r1 != 0) goto L_0x003e
            r1 = 0
            r6 = r20
            goto L_0x0046
        L_0x003e:
            com.google.protobuf.MessageLite r1 = r8.defaultInstance     // Catch:{ all -> 0x06f6 }
            r6 = r20
            java.lang.Object r1 = r6.findExtensionByNumber(r12, r1, r15)     // Catch:{ all -> 0x06f6 }
        L_0x0046:
            r16 = r1
            if (r16 == 0) goto L_0x006e
            if (r14 != 0) goto L_0x0056
            com.google.protobuf.FieldSet r1 = r20.getMutableExtensions(r21)     // Catch:{ all -> 0x0052 }
            r14 = r1
            goto L_0x0056
        L_0x0052:
            r0 = move-exception
            r1 = r0
            goto L_0x06fa
        L_0x0056:
            r1 = r20
            r2 = r22
            r3 = r16
            r4 = r23
            r5 = r14
            r6 = r13
            r17 = r14
            r14 = r7
            r7 = r19
            java.lang.Object r1 = r1.parseExtension(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x06f1 }
            r13 = r1
            r14 = r17
            goto L_0x000e
        L_0x006e:
            r17 = r14
            r14 = r7
            boolean r1 = r9.shouldDiscardUnknownFields(r11)     // Catch:{ all -> 0x06f1 }
            if (r1 == 0) goto L_0x0080
            boolean r1 = r22.skipField()     // Catch:{ all -> 0x06f1 }
            if (r1 == 0) goto L_0x0091
            r14 = r17
            goto L_0x000e
        L_0x0080:
            if (r13 != 0) goto L_0x0087
            java.lang.Object r1 = r9.getBuilderFromMessage(r10)     // Catch:{ all -> 0x06f1 }
            r13 = r1
        L_0x0087:
            boolean r1 = r9.mergeOneFieldFrom(r13, r11)     // Catch:{ all -> 0x06f1 }
            if (r1 == 0) goto L_0x0091
            r14 = r17
            goto L_0x000e
        L_0x0091:
            int r1 = r8.checkInitializedCount
        L_0x0093:
            int r2 = r8.repeatedFieldOffsetStart
            if (r1 >= r2) goto L_0x00a2
            int[] r2 = r8.intArray
            r2 = r2[r1]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r2, r13, r9)
            int r1 = r1 + 1
            goto L_0x0093
        L_0x00a2:
            if (r13 == 0) goto L_0x00a7
            r9.setBuilderToMessage(r10, r13)
        L_0x00a7:
            return
        L_0x00a8:
            r17 = r14
            r14 = r7
            int r1 = r8.typeAndOffsetAt(r14)     // Catch:{ all -> 0x06f1 }
            r7 = r1
            int r1 = type(r7)     // Catch:{ InvalidWireTypeException -> 0x06a3 }
            switch(r1) {
                case 0: goto L_0x0671;
                case 1: goto L_0x0661;
                case 2: goto L_0x0651;
                case 3: goto L_0x0641;
                case 4: goto L_0x0631;
                case 5: goto L_0x0620;
                case 6: goto L_0x060f;
                case 7: goto L_0x05fe;
                case 8: goto L_0x05f5;
                case 9: goto L_0x05bb;
                case 10: goto L_0x05aa;
                case 11: goto L_0x0599;
                case 12: goto L_0x0573;
                case 13: goto L_0x0562;
                case 14: goto L_0x0551;
                case 15: goto L_0x0540;
                case 16: goto L_0x052f;
                case 17: goto L_0x04f5;
                case 18: goto L_0x04e5;
                case 19: goto L_0x04d5;
                case 20: goto L_0x04c5;
                case 21: goto L_0x04b5;
                case 22: goto L_0x04a5;
                case 23: goto L_0x0495;
                case 24: goto L_0x0485;
                case 25: goto L_0x0475;
                case 26: goto L_0x046c;
                case 27: goto L_0x044f;
                case 28: goto L_0x043c;
                case 29: goto L_0x0429;
                case 30: goto L_0x040c;
                case 31: goto L_0x03f9;
                case 32: goto L_0x03e6;
                case 33: goto L_0x03d3;
                case 34: goto L_0x03c0;
                case 35: goto L_0x03ad;
                case 36: goto L_0x039a;
                case 37: goto L_0x0387;
                case 38: goto L_0x0374;
                case 39: goto L_0x0361;
                case 40: goto L_0x034e;
                case 41: goto L_0x033b;
                case 42: goto L_0x0328;
                case 43: goto L_0x0315;
                case 44: goto L_0x02f8;
                case 45: goto L_0x02e5;
                case 46: goto L_0x02d2;
                case 47: goto L_0x02bf;
                case 48: goto L_0x02ac;
                case 49: goto L_0x028d;
                case 50: goto L_0x0275;
                case 51: goto L_0x025f;
                case 52: goto L_0x0249;
                case 53: goto L_0x0233;
                case 54: goto L_0x021d;
                case 55: goto L_0x0207;
                case 56: goto L_0x01f1;
                case 57: goto L_0x01db;
                case 58: goto L_0x01c5;
                case 59: goto L_0x01bc;
                case 60: goto L_0x0180;
                case 61: goto L_0x016f;
                case 62: goto L_0x0159;
                case 63: goto L_0x012e;
                case 64: goto L_0x0118;
                case 65: goto L_0x0102;
                case 66: goto L_0x00ec;
                case 67: goto L_0x00d6;
                case 68: goto L_0x00c0;
                default: goto L_0x00b7;
            }
        L_0x00b7:
            r1 = r7
            if (r13 != 0) goto L_0x0685
            java.lang.Object r2 = r19.newBuilder()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x0684
        L_0x00c0:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.Schema r3 = r8.getMessageFieldSchema(r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Object r3 = r11.readGroupBySchemaWithCheck(r3, r12)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x00d6:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            long r3 = r22.readSInt64()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x00ec:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            int r3 = r22.readSInt32()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x0102:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            long r3 = r22.readSFixed64()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x0118:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            int r3 = r22.readSFixed32()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x012e:
            int r1 = r22.readEnum()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.Internal$EnumVerifier r2 = r8.getEnumFieldVerifier(r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            if (r2 == 0) goto L_0x0148
            boolean r3 = r2.isInRange(r1)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            if (r3 == 0) goto L_0x013f
            goto L_0x0148
        L_0x013f:
            java.lang.Object r3 = com.google.protobuf.SchemaUtil.storeUnknownEnum(r15, r1, r13, r9)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r13 = r3
            r1 = r7
            goto L_0x06a2
        L_0x0148:
            long r3 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r3, (java.lang.Object) r5)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x0159:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            int r3 = r22.readUInt32()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x016f:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.ByteString r3 = r22.readBytes()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x0180:
            boolean r1 = r8.isOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            if (r1 == 0) goto L_0x01a3
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Object r1 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r10, (long) r1)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.Schema r2 = r8.getMessageFieldSchema(r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Object r2 = r11.readMessageBySchemaWithCheck(r2, r12)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Object r1 = com.google.protobuf.Internal.mergeMessage(r1, r2)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            long r2 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r2, (java.lang.Object) r1)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            goto L_0x01b6
        L_0x01a3:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.Schema r3 = r8.getMessageFieldSchema(r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Object r3 = r11.readMessageBySchemaWithCheck(r3, r12)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
        L_0x01b6:
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x01bc:
            r8.readString(r10, r7, r11)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x01c5:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            boolean r3 = r22.readBool()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x01db:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            int r3 = r22.readFixed32()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x01f1:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            long r3 = r22.readFixed64()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x0207:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            int r3 = r22.readInt32()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x021d:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            long r3 = r22.readUInt64()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x0233:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            long r3 = r22.readInt64()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x0249:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            float r3 = r22.readFloat()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x025f:
            long r1 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            double r3 = r22.readDouble()     // Catch:{ InvalidWireTypeException -> 0x0288 }
            java.lang.Double r3 = java.lang.Double.valueOf(r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r1, (java.lang.Object) r3)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r8.setOneofPresent(r10, r15, r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x0275:
            java.lang.Object r4 = r8.getMapFieldDefaultEntry(r14)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r18
            r2 = r21
            r3 = r14
            r5 = r23
            r6 = r22
            r1.mergeMap(r2, r3, r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x0288 }
            r1 = r7
            goto L_0x06a2
        L_0x0288:
            r0 = move-exception
            r2 = r0
            r1 = r7
            goto L_0x06a6
        L_0x028d:
            long r3 = offset(r7)     // Catch:{ InvalidWireTypeException -> 0x02a7 }
            com.google.protobuf.Schema r6 = r8.getMessageFieldSchema(r14)     // Catch:{ InvalidWireTypeException -> 0x02a7 }
            r1 = r18
            r2 = r21
            r5 = r22
            r16 = r7
            r7 = r23
            r1.readGroupList(r2, r3, r5, r6, r7)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x02a7:
            r0 = move-exception
            r2 = r0
            r1 = r7
            goto L_0x06a6
        L_0x02ac:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readSInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x02bf:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readSInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x02d2:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readSFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x02e5:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readSFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x02f8:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readEnumList(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            com.google.protobuf.Internal$EnumVerifier r2 = r8.getEnumFieldVerifier(r14)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.lang.Object r2 = com.google.protobuf.SchemaUtil.filterUnknownEnumList((int) r15, (java.util.List<java.lang.Integer>) r1, (com.google.protobuf.Internal.EnumVerifier) r2, r13, r9)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r13 = r2
            r1 = r16
            goto L_0x06a2
        L_0x0315:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readUInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x0328:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readBoolList(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x033b:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x034e:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x0361:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x0374:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readUInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x0387:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x039a:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readFloatList(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x03ad:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readDoubleList(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x03c0:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readSInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x03d3:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readSInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x03e6:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readSFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x03f9:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readSFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x040c:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readEnumList(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            com.google.protobuf.Internal$EnumVerifier r2 = r8.getEnumFieldVerifier(r14)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.lang.Object r2 = com.google.protobuf.SchemaUtil.filterUnknownEnumList((int) r15, (java.util.List<java.lang.Integer>) r1, (com.google.protobuf.Internal.EnumVerifier) r2, r13, r9)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r13 = r2
            r1 = r16
            goto L_0x06a2
        L_0x0429:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readUInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x043c:
            r16 = r7
            com.google.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0466 }
            long r2 = offset(r16)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            java.util.List r1 = r1.mutableListAt(r10, r2)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r11.readBytesList(r1)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x044f:
            r16 = r7
            com.google.protobuf.Schema r5 = r8.getMessageFieldSchema(r14)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r18
            r2 = r21
            r3 = r16
            r4 = r22
            r6 = r23
            r1.readMessageList(r2, r3, r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x0466 }
            r1 = r16
            goto L_0x06a2
        L_0x0466:
            r0 = move-exception
            r2 = r0
            r1 = r16
            goto L_0x06a6
        L_0x046c:
            r16 = r7
            r1 = r16
            r8.readStringList(r10, r1, r11)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0475:
            r1 = r7
            com.google.protobuf.ListFieldSchema r2 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r3 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.util.List r2 = r2.mutableListAt(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r11.readBoolList(r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0485:
            r1 = r7
            com.google.protobuf.ListFieldSchema r2 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r3 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.util.List r2 = r2.mutableListAt(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r11.readFixed32List(r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0495:
            r1 = r7
            com.google.protobuf.ListFieldSchema r2 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r3 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.util.List r2 = r2.mutableListAt(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r11.readFixed64List(r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x04a5:
            r1 = r7
            com.google.protobuf.ListFieldSchema r2 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r3 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.util.List r2 = r2.mutableListAt(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r11.readInt32List(r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x04b5:
            r1 = r7
            com.google.protobuf.ListFieldSchema r2 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r3 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.util.List r2 = r2.mutableListAt(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r11.readUInt64List(r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x04c5:
            r1 = r7
            com.google.protobuf.ListFieldSchema r2 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r3 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.util.List r2 = r2.mutableListAt(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r11.readInt64List(r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x04d5:
            r1 = r7
            com.google.protobuf.ListFieldSchema r2 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r3 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.util.List r2 = r2.mutableListAt(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r11.readFloatList(r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x04e5:
            r1 = r7
            com.google.protobuf.ListFieldSchema r2 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r3 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.util.List r2 = r2.mutableListAt(r10, r3)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r11.readDoubleList(r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x04f5:
            r1 = r7
            boolean r2 = r8.isFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            if (r2 == 0) goto L_0x051a
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.lang.Object r2 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r10, (long) r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.Schema r3 = r8.getMessageFieldSchema(r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.lang.Object r3 = r11.readGroupBySchemaWithCheck(r3, r12)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.lang.Object r2 = com.google.protobuf.Internal.mergeMessage(r2, r3)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r3 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r3, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x051a:
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.Schema r4 = r8.getMessageFieldSchema(r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.lang.Object r4 = r11.readGroupBySchemaWithCheck(r4, r12)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r2, (java.lang.Object) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x052f:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r4 = r22.readSInt64()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r10, (long) r2, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0540:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            int r4 = r22.readSInt32()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r2, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0551:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r4 = r22.readSFixed64()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r10, (long) r2, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0562:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            int r4 = r22.readSFixed32()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r2, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0573:
            r1 = r7
            int r2 = r22.readEnum()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.Internal$EnumVerifier r3 = r8.getEnumFieldVerifier(r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            if (r3 == 0) goto L_0x058d
            boolean r4 = r3.isInRange(r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            if (r4 == 0) goto L_0x0585
            goto L_0x058d
        L_0x0585:
            java.lang.Object r4 = com.google.protobuf.SchemaUtil.storeUnknownEnum(r15, r2, r13, r9)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r13 = r4
            goto L_0x06a2
        L_0x058d:
            long r4 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r4, (int) r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0599:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            int r4 = r22.readUInt32()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r2, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x05aa:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.ByteString r4 = r22.readBytes()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r2, (java.lang.Object) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x05bb:
            r1 = r7
            boolean r2 = r8.isFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            if (r2 == 0) goto L_0x05e0
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.lang.Object r2 = com.google.protobuf.UnsafeUtil.getObject((java.lang.Object) r10, (long) r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.Schema r3 = r8.getMessageFieldSchema(r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.lang.Object r3 = r11.readMessageBySchemaWithCheck(r3, r12)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.lang.Object r2 = com.google.protobuf.Internal.mergeMessage(r2, r3)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r3 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r3, (java.lang.Object) r2)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x05e0:
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.Schema r4 = r8.getMessageFieldSchema(r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            java.lang.Object r4 = r11.readMessageBySchemaWithCheck(r4, r12)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putObject((java.lang.Object) r10, (long) r2, (java.lang.Object) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x05f5:
            r1 = r7
            r8.readString(r10, r1, r11)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x05fe:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            boolean r4 = r22.readBool()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putBoolean((java.lang.Object) r10, (long) r2, (boolean) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x060f:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            int r4 = r22.readFixed32()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r2, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0620:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r4 = r22.readFixed64()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r10, (long) r2, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0631:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            int r4 = r22.readInt32()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putInt((java.lang.Object) r10, (long) r2, (int) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0641:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r4 = r22.readUInt64()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r10, (long) r2, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0651:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            long r4 = r22.readInt64()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putLong((java.lang.Object) r10, (long) r2, (long) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0661:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            float r4 = r22.readFloat()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putFloat((java.lang.Object) r10, (long) r2, (float) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0671:
            r1 = r7
            long r2 = offset(r1)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            double r4 = r22.readDouble()     // Catch:{ InvalidWireTypeException -> 0x0681 }
            com.google.protobuf.UnsafeUtil.putDouble((java.lang.Object) r10, (long) r2, (double) r4)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            r8.setFieldPresent(r10, r14)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            goto L_0x06a2
        L_0x0681:
            r0 = move-exception
            r2 = r0
            goto L_0x06a6
        L_0x0684:
            r13 = r2
        L_0x0685:
            boolean r2 = r9.mergeOneFieldFrom(r13, r11)     // Catch:{ InvalidWireTypeException -> 0x0681 }
            if (r2 != 0) goto L_0x06a2
            int r2 = r8.checkInitializedCount
        L_0x068d:
            int r3 = r8.repeatedFieldOffsetStart
            if (r2 >= r3) goto L_0x069c
            int[] r3 = r8.intArray
            r3 = r3[r2]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r3, r13, r9)
            int r2 = r2 + 1
            goto L_0x068d
        L_0x069c:
            if (r13 == 0) goto L_0x06a1
            r9.setBuilderToMessage(r10, r13)
        L_0x06a1:
            return
        L_0x06a2:
            goto L_0x06ed
        L_0x06a3:
            r0 = move-exception
            r1 = r7
            r2 = r0
        L_0x06a6:
            boolean r3 = r9.shouldDiscardUnknownFields(r11)     // Catch:{ all -> 0x06f1 }
            if (r3 == 0) goto L_0x06c9
            boolean r3 = r22.skipField()     // Catch:{ all -> 0x06f1 }
            if (r3 != 0) goto L_0x06ed
            int r3 = r8.checkInitializedCount
        L_0x06b4:
            int r4 = r8.repeatedFieldOffsetStart
            if (r3 >= r4) goto L_0x06c3
            int[] r4 = r8.intArray
            r4 = r4[r3]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r4, r13, r9)
            int r3 = r3 + 1
            goto L_0x06b4
        L_0x06c3:
            if (r13 == 0) goto L_0x06c8
            r9.setBuilderToMessage(r10, r13)
        L_0x06c8:
            return
        L_0x06c9:
            if (r13 != 0) goto L_0x06d0
            java.lang.Object r3 = r9.getBuilderFromMessage(r10)     // Catch:{ all -> 0x06f1 }
            r13 = r3
        L_0x06d0:
            boolean r3 = r9.mergeOneFieldFrom(r13, r11)     // Catch:{ all -> 0x06f1 }
            if (r3 != 0) goto L_0x06ed
            int r3 = r8.checkInitializedCount
        L_0x06d8:
            int r4 = r8.repeatedFieldOffsetStart
            if (r3 >= r4) goto L_0x06e7
            int[] r4 = r8.intArray
            r4 = r4[r3]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r4, r13, r9)
            int r3 = r3 + 1
            goto L_0x06d8
        L_0x06e7:
            if (r13 == 0) goto L_0x06ec
            r9.setBuilderToMessage(r10, r13)
        L_0x06ec:
            return
        L_0x06ed:
            r14 = r17
            goto L_0x000e
        L_0x06f1:
            r0 = move-exception
            r1 = r0
            r14 = r17
            goto L_0x06fa
        L_0x06f6:
            r0 = move-exception
            r17 = r14
            r1 = r0
        L_0x06fa:
            int r2 = r8.checkInitializedCount
        L_0x06fc:
            int r3 = r8.repeatedFieldOffsetStart
            if (r2 >= r3) goto L_0x070b
            int[] r3 = r8.intArray
            r3 = r3[r2]
            java.lang.Object r13 = r8.filterMapUnknownEnumValues(r10, r3, r13, r9)
            int r2 = r2 + 1
            goto L_0x06fc
        L_0x070b:
            if (r13 == 0) goto L_0x0710
            r9.setBuilderToMessage(r10, r13)
        L_0x0710:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.mergeFromHelper(com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, java.lang.Object, com.google.protobuf.Reader, com.google.protobuf.ExtensionRegistryLite):void");
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object message) {
        UnknownFieldSetLite unknownFields = ((GeneratedMessageLite) message).unknownFields;
        if (unknownFields != UnknownFieldSetLite.getDefaultInstance()) {
            return unknownFields;
        }
        UnknownFieldSetLite unknownFields2 = UnknownFieldSetLite.newInstance();
        ((GeneratedMessageLite) message).unknownFields = unknownFields2;
        return unknownFields2;
    }

    /* renamed from: com.google.protobuf.MessageSchema$1 */
    static /* synthetic */ class C10261 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private int decodeMapEntryValue(byte[] data, int position, int limit, WireFormat.FieldType fieldType, Class<?> messageType, ArrayDecoders.Registers registers) throws IOException {
        switch (C10261.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int position2 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return position2;
            case 2:
                return ArrayDecoders.decodeBytes(data, position, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(data, position));
                return position + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(data, position));
                return position + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(data, position));
                return position + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(data, position));
                return position + 4;
            case 9:
            case 10:
            case 11:
                int position3 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return position3;
            case 12:
            case 13:
                int position4 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return position4;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor(messageType), data, position, limit, registers);
            case 15:
                int position5 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return position5;
            case 16:
                int position6 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return position6;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: byte} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <K, V> int decodeMapEntry(byte[] r19, int r20, int r21, com.google.protobuf.MapEntryLite.Metadata<K, V> r22, java.util.Map<K, V> r23, com.google.protobuf.ArrayDecoders.Registers r24) throws java.io.IOException {
        /*
            r18 = this;
            r7 = r19
            r8 = r21
            r9 = r22
            r10 = r24
            r0 = r20
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r7, r0, r10)
            int r11 = r10.int1
            if (r11 < 0) goto L_0x00aa
            int r1 = r8 - r0
            if (r11 > r1) goto L_0x00aa
            int r12 = r0 + r11
            K r1 = r9.defaultKey
            V r2 = r9.defaultValue
            r13 = r1
            r14 = r2
        L_0x001e:
            if (r0 >= r12) goto L_0x0099
            int r1 = r0 + 1
            byte r0 = r7[r0]
            if (r0 >= 0) goto L_0x002f
            int r1 = com.google.protobuf.ArrayDecoders.decodeVarint32(r0, r7, r1, r10)
            int r0 = r10.int1
            r15 = r0
            r6 = r1
            goto L_0x0031
        L_0x002f:
            r15 = r0
            r6 = r1
        L_0x0031:
            int r16 = r15 >>> 3
            r5 = r15 & 7
            switch(r16) {
                case 1: goto L_0x006d;
                case 2: goto L_0x003e;
                default: goto L_0x0038;
            }
        L_0x0038:
            r17 = r6
            r20 = r11
            r11 = r5
            goto L_0x0090
        L_0x003e:
            com.google.protobuf.WireFormat$FieldType r0 = r9.valueType
            int r0 = r0.getWireType()
            if (r5 != r0) goto L_0x0067
            com.google.protobuf.WireFormat$FieldType r4 = r9.valueType
            V r0 = r9.defaultValue
            java.lang.Class r17 = r0.getClass()
            r0 = r18
            r1 = r19
            r2 = r6
            r3 = r21
            r20 = r11
            r11 = r5
            r5 = r17
            r17 = r6
            r6 = r24
            int r0 = r0.decodeMapEntryValue(r1, r2, r3, r4, r5, r6)
            java.lang.Object r14 = r10.object1
            r11 = r20
            goto L_0x001e
        L_0x0067:
            r17 = r6
            r20 = r11
            r11 = r5
            goto L_0x0090
        L_0x006d:
            r17 = r6
            r20 = r11
            r11 = r5
            com.google.protobuf.WireFormat$FieldType r0 = r9.keyType
            int r0 = r0.getWireType()
            if (r11 != r0) goto L_0x0090
            com.google.protobuf.WireFormat$FieldType r4 = r9.keyType
            r5 = 0
            r0 = r18
            r1 = r19
            r2 = r17
            r3 = r21
            r6 = r24
            int r0 = r0.decodeMapEntryValue(r1, r2, r3, r4, r5, r6)
            java.lang.Object r13 = r10.object1
            r11 = r20
            goto L_0x001e
        L_0x0090:
            r1 = r17
            int r0 = com.google.protobuf.ArrayDecoders.skipField(r15, r7, r1, r8, r10)
            r11 = r20
            goto L_0x001e
        L_0x0099:
            r20 = r11
            if (r0 != r12) goto L_0x00a3
            r1 = r23
            r1.put(r13, r14)
            return r12
        L_0x00a3:
            r1 = r23
            com.google.protobuf.InvalidProtocolBufferException r2 = com.google.protobuf.InvalidProtocolBufferException.parseFailure()
            throw r2
        L_0x00aa:
            r1 = r23
            r20 = r11
            com.google.protobuf.InvalidProtocolBufferException r2 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.decodeMapEntry(byte[], int, int, com.google.protobuf.MapEntryLite$Metadata, java.util.Map, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    private int parseRepeatedField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int bufferPosition, long typeAndOffset, int fieldType, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Internal.ProtobufList<?> list;
        int position2;
        T t = message;
        byte[] bArr = data;
        int position3 = position;
        int i = wireType;
        int i2 = bufferPosition;
        long j = fieldOffset;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList<?> list2 = (Internal.ProtobufList) unsafe.getObject(t, j);
        if (!list2.isModifiable()) {
            int size = list2.size();
            Internal.ProtobufList<?> list3 = list2.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
            unsafe.putObject(t, j, list3);
            list = list3;
        } else {
            list = list2;
        }
        switch (fieldType) {
            case 18:
            case 35:
                int i3 = number;
                Internal.ProtobufList<?> list4 = list;
                if (i == 2) {
                    return ArrayDecoders.decodePackedDoubleList(bArr, position3, list4, registers2);
                }
                if (i == 1) {
                    return ArrayDecoders.decodeDoubleList(tag, data, position, limit, list4, registers);
                }
                break;
            case 19:
            case 36:
                int i4 = number;
                Internal.ProtobufList<?> list5 = list;
                if (i == 2) {
                    return ArrayDecoders.decodePackedFloatList(bArr, position3, list5, registers2);
                }
                if (i == 5) {
                    return ArrayDecoders.decodeFloatList(tag, data, position, limit, list5, registers);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                int i5 = number;
                Internal.ProtobufList<?> list6 = list;
                if (i == 2) {
                    return ArrayDecoders.decodePackedVarint64List(bArr, position3, list6, registers2);
                }
                if (i == 0) {
                    return ArrayDecoders.decodeVarint64List(tag, data, position, limit, list6, registers);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                int i6 = number;
                Internal.ProtobufList<?> list7 = list;
                if (i == 2) {
                    return ArrayDecoders.decodePackedVarint32List(bArr, position3, list7, registers2);
                }
                if (i == 0) {
                    return ArrayDecoders.decodeVarint32List(tag, data, position, limit, list7, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                int i7 = number;
                Internal.ProtobufList<?> list8 = list;
                if (i == 2) {
                    return ArrayDecoders.decodePackedFixed64List(bArr, position3, list8, registers2);
                }
                if (i == 1) {
                    return ArrayDecoders.decodeFixed64List(tag, data, position, limit, list8, registers);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                int i8 = number;
                Internal.ProtobufList<?> list9 = list;
                if (i == 2) {
                    return ArrayDecoders.decodePackedFixed32List(bArr, position3, list9, registers2);
                }
                if (i == 5) {
                    return ArrayDecoders.decodeFixed32List(tag, data, position, limit, list9, registers);
                }
                break;
            case 25:
            case 42:
                int i9 = number;
                Internal.ProtobufList<?> list10 = list;
                if (i == 2) {
                    return ArrayDecoders.decodePackedBoolList(bArr, position3, list10, registers2);
                }
                if (i == 0) {
                    return ArrayDecoders.decodeBoolList(tag, data, position, limit, list10, registers);
                }
                break;
            case 26:
                int i10 = number;
                Internal.ProtobufList<?> list11 = list;
                if (i == 2) {
                    if ((typeAndOffset & 536870912) == 0) {
                        return ArrayDecoders.decodeStringList(tag, data, position, limit, list11, registers);
                    }
                    return ArrayDecoders.decodeStringListRequireUtf8(tag, data, position, limit, list11, registers);
                }
                break;
            case 27:
                int i11 = number;
                Internal.ProtobufList<?> list12 = list;
                if (i == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(i2), tag, data, position, limit, list12, registers);
                }
                break;
            case 28:
                int i12 = number;
                Internal.ProtobufList<?> list13 = list;
                if (i == 2) {
                    return ArrayDecoders.decodeBytesList(tag, data, position, limit, list13, registers);
                }
                break;
            case 30:
            case 44:
                Internal.ProtobufList<?> list14 = list;
                if (i != 2) {
                    if (i != 0) {
                        int i13 = number;
                        break;
                    } else {
                        position2 = ArrayDecoders.decodeVarint32List(tag, data, position, limit, list14, registers);
                    }
                } else {
                    position2 = ArrayDecoders.decodePackedVarint32List(bArr, position3, list14, registers2);
                }
                UnknownFieldSetLite unknownFields = ((GeneratedMessageLite) t).unknownFields;
                if (unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFields = null;
                }
                UnknownFieldSetLite unknownFields2 = (UnknownFieldSetLite) SchemaUtil.filterUnknownEnumList(number, (List<Integer>) list14, getEnumFieldVerifier(i2), unknownFields, this.unknownFieldSchema);
                if (unknownFields2 == null) {
                    return position2;
                }
                ((GeneratedMessageLite) t).unknownFields = unknownFields2;
                return position2;
            case 33:
            case 47:
                Internal.ProtobufList<?> list15 = list;
                if (i != 2) {
                    if (i != 0) {
                        int i14 = number;
                        break;
                    } else {
                        int i15 = number;
                        return ArrayDecoders.decodeSInt32List(tag, data, position, limit, list15, registers);
                    }
                } else {
                    int i16 = number;
                    return ArrayDecoders.decodePackedSInt32List(bArr, position3, list15, registers2);
                }
            case 34:
            case 48:
                Internal.ProtobufList<?> list16 = list;
                if (i != 2) {
                    if (i != 0) {
                        int i17 = number;
                        break;
                    } else {
                        int i18 = number;
                        return ArrayDecoders.decodeSInt64List(tag, data, position, limit, list16, registers);
                    }
                } else {
                    int i19 = number;
                    return ArrayDecoders.decodePackedSInt64List(bArr, position3, list16, registers2);
                }
            case 49:
                if (i != 3) {
                    int i20 = number;
                    break;
                } else {
                    Internal.ProtobufList<?> protobufList = list;
                    int i21 = number;
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(i2), tag, data, position, limit, list, registers);
                }
            default:
                int i22 = number;
                Internal.ProtobufList<?> protobufList2 = list;
                break;
        }
        return position3;
    }

    private <K, V> int parseMapField(T message, byte[] data, int position, int limit, int bufferPosition, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Object mapField;
        T t = message;
        long j = fieldOffset;
        Unsafe unsafe = UNSAFE;
        Object mapDefaultEntry = getMapFieldDefaultEntry(bufferPosition);
        Object mapField2 = unsafe.getObject(t, j);
        if (this.mapFieldSchema.isImmutable(mapField2)) {
            Object oldMapField = mapField2;
            Object mapField3 = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField3, oldMapField);
            unsafe.putObject(t, j, mapField3);
            mapField = mapField3;
        } else {
            mapField = mapField2;
        }
        return decodeMapEntry(data, position, limit, this.mapFieldSchema.forMapMetadata(mapDefaultEntry), this.mapFieldSchema.forMutableMapData(mapField), registers);
    }

    private int parseOneofField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int typeAndOffset, int fieldType, long fieldOffset, int bufferPosition, ArrayDecoders.Registers registers) throws IOException {
        int position2;
        Unsafe unsafe;
        T t = message;
        byte[] bArr = data;
        int i = position;
        int i2 = tag;
        int i3 = number;
        int i4 = wireType;
        long j = fieldOffset;
        int i5 = bufferPosition;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe2 = UNSAFE;
        long oneofCaseOffset = (long) (this.buffer[i5 + 2] & 1048575);
        Object obj = null;
        switch (fieldType) {
            case 51:
                long j2 = oneofCaseOffset;
                Unsafe unsafe3 = unsafe2;
                position2 = i;
                byte[] bArr2 = bArr;
                ArrayDecoders.Registers registers3 = registers2;
                long oneofCaseOffset2 = j2;
                if (i4 == 1) {
                    unsafe3.putObject(t, j, Double.valueOf(ArrayDecoders.decodeDouble(data, position)));
                    int position3 = position2 + 8;
                    unsafe3.putInt(t, oneofCaseOffset2, i3);
                    return position3;
                }
                break;
            case 52:
                long j3 = oneofCaseOffset;
                Unsafe unsafe4 = unsafe2;
                position2 = i;
                byte[] bArr3 = bArr;
                ArrayDecoders.Registers registers4 = registers2;
                long oneofCaseOffset3 = j3;
                if (i4 == 5) {
                    unsafe4.putObject(t, j, Float.valueOf(ArrayDecoders.decodeFloat(data, position)));
                    int position4 = position2 + 4;
                    unsafe4.putInt(t, oneofCaseOffset3, i3);
                    return position4;
                }
                break;
            case 53:
            case 54:
                long j4 = oneofCaseOffset;
                Unsafe unsafe5 = unsafe2;
                position2 = i;
                byte[] bArr4 = bArr;
                ArrayDecoders.Registers registers5 = registers2;
                long oneofCaseOffset4 = j4;
                if (i4 == 0) {
                    int position5 = ArrayDecoders.decodeVarint64(bArr4, position2, registers5);
                    unsafe5.putObject(t, j, Long.valueOf(registers5.long1));
                    unsafe5.putInt(t, oneofCaseOffset4, i3);
                    return position5;
                }
                break;
            case 55:
            case 62:
                long j5 = oneofCaseOffset;
                Unsafe unsafe6 = unsafe2;
                position2 = i;
                byte[] bArr5 = bArr;
                ArrayDecoders.Registers registers6 = registers2;
                long oneofCaseOffset5 = j5;
                if (i4 == 0) {
                    int position6 = ArrayDecoders.decodeVarint32(bArr5, position2, registers6);
                    unsafe6.putObject(t, j, Integer.valueOf(registers6.int1));
                    unsafe6.putInt(t, oneofCaseOffset5, i3);
                    return position6;
                }
                break;
            case 56:
            case 65:
                long j6 = oneofCaseOffset;
                Unsafe unsafe7 = unsafe2;
                position2 = i;
                byte[] bArr6 = bArr;
                ArrayDecoders.Registers registers7 = registers2;
                long oneofCaseOffset6 = j6;
                if (i4 == 1) {
                    unsafe7.putObject(t, j, Long.valueOf(ArrayDecoders.decodeFixed64(data, position)));
                    int position7 = position2 + 8;
                    unsafe7.putInt(t, oneofCaseOffset6, i3);
                    return position7;
                }
                break;
            case 57:
            case 64:
                long j7 = oneofCaseOffset;
                Unsafe unsafe8 = unsafe2;
                position2 = i;
                byte[] bArr7 = bArr;
                ArrayDecoders.Registers registers8 = registers2;
                long oneofCaseOffset7 = j7;
                if (i4 == 5) {
                    unsafe8.putObject(t, j, Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)));
                    int position8 = position2 + 4;
                    unsafe8.putInt(t, oneofCaseOffset7, i3);
                    return position8;
                }
                break;
            case 58:
                long j8 = oneofCaseOffset;
                Unsafe unsafe9 = unsafe2;
                position2 = i;
                byte[] bArr8 = bArr;
                ArrayDecoders.Registers registers9 = registers2;
                long oneofCaseOffset8 = j8;
                if (i4 == 0) {
                    int position9 = ArrayDecoders.decodeVarint64(bArr8, position2, registers9);
                    unsafe9.putObject(t, j, Boolean.valueOf(registers9.long1 != 0));
                    unsafe9.putInt(t, oneofCaseOffset8, i3);
                    return position9;
                }
                break;
            case 59:
                long j9 = oneofCaseOffset;
                Unsafe unsafe10 = unsafe2;
                position2 = i;
                byte[] bArr9 = bArr;
                ArrayDecoders.Registers registers10 = registers2;
                long oneofCaseOffset9 = j9;
                if (i4 == 2) {
                    int position10 = ArrayDecoders.decodeVarint32(bArr9, position2, registers10);
                    int length = registers10.int1;
                    if (length == 0) {
                        unsafe10.putObject(t, j, "");
                    } else if ((typeAndOffset & ENFORCE_UTF8_MASK) == 0 || Utf8.isValidUtf8(bArr9, position10, position10 + length)) {
                        unsafe10.putObject(t, j, new String(bArr9, position10, length, Internal.UTF_8));
                        position10 += length;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    unsafe10.putInt(t, oneofCaseOffset9, i3);
                    return position10;
                }
                break;
            case 60:
                long j10 = oneofCaseOffset;
                Unsafe unsafe11 = unsafe2;
                position2 = i;
                byte[] bArr10 = bArr;
                ArrayDecoders.Registers registers11 = registers2;
                long oneofCaseOffset10 = j10;
                if (i4 != 2) {
                    int i6 = limit;
                    break;
                } else {
                    int position11 = ArrayDecoders.decodeMessageField(getMessageFieldSchema(i5), bArr10, position2, limit, registers11);
                    if (unsafe11.getInt(t, oneofCaseOffset10) == i3) {
                        obj = unsafe11.getObject(t, j);
                    }
                    Object oldValue = obj;
                    if (oldValue == null) {
                        unsafe11.putObject(t, j, registers11.object1);
                    } else {
                        unsafe11.putObject(t, j, Internal.mergeMessage(oldValue, registers11.object1));
                    }
                    unsafe11.putInt(t, oneofCaseOffset10, i3);
                    return position11;
                }
            case 61:
                ArrayDecoders.Registers registers12 = registers2;
                long j11 = oneofCaseOffset;
                Unsafe unsafe12 = unsafe2;
                long oneofCaseOffset11 = j11;
                position2 = i;
                if (i4 != 2) {
                    byte[] bArr11 = data;
                    break;
                } else {
                    int position12 = ArrayDecoders.decodeBytes(data, position2, registers12);
                    unsafe12.putObject(t, j, registers12.object1);
                    unsafe12.putInt(t, oneofCaseOffset11, i3);
                    return position12;
                }
            case 63:
                Unsafe unsafe13 = unsafe2;
                ArrayDecoders.Registers registers13 = registers2;
                long oneofCaseOffset12 = oneofCaseOffset;
                byte[] bArr12 = bArr;
                position2 = i;
                if (i4 != 0) {
                    ArrayDecoders.Registers registers14 = registers13;
                    Unsafe unsafe14 = unsafe13;
                    int i7 = tag;
                    byte[] bArr13 = data;
                    break;
                } else {
                    int position13 = ArrayDecoders.decodeVarint32(bArr12, position2, registers13);
                    int enumValue = registers13.int1;
                    ArrayDecoders.Registers registers15 = registers13;
                    Internal.EnumVerifier enumVerifier = getEnumFieldVerifier(i5);
                    if (enumVerifier == null) {
                        unsafe = unsafe13;
                        int i8 = tag;
                    } else if (enumVerifier.isInRange(enumValue)) {
                        Internal.EnumVerifier enumVerifier2 = enumVerifier;
                        unsafe = unsafe13;
                        int i9 = tag;
                    } else {
                        Internal.EnumVerifier enumVerifier3 = enumVerifier;
                        unsafe = unsafe13;
                        getMutableUnknownFields(message).storeField(tag, Long.valueOf((long) enumValue));
                        byte[] bArr14 = data;
                        Unsafe unsafe15 = unsafe;
                        return position13;
                    }
                    unsafe.putObject(t, j, Integer.valueOf(enumValue));
                    unsafe.putInt(t, oneofCaseOffset12, i3);
                    byte[] bArr142 = data;
                    Unsafe unsafe152 = unsafe;
                    return position13;
                }
            case 66:
                Unsafe unsafe16 = unsafe2;
                ArrayDecoders.Registers registers16 = registers2;
                long oneofCaseOffset13 = oneofCaseOffset;
                byte[] bArr15 = bArr;
                position2 = i;
                if (i4 != 0) {
                    ArrayDecoders.Registers registers17 = registers16;
                    byte[] bArr16 = bArr15;
                    Unsafe unsafe17 = unsafe16;
                    int i10 = tag;
                    break;
                } else {
                    int position14 = ArrayDecoders.decodeVarint32(bArr15, position2, registers16);
                    unsafe16.putObject(t, j, Integer.valueOf(CodedInputStream.decodeZigZag32(registers16.int1)));
                    unsafe16.putInt(t, oneofCaseOffset13, i3);
                    ArrayDecoders.Registers registers18 = registers16;
                    byte[] bArr17 = bArr15;
                    Unsafe unsafe18 = unsafe16;
                    int i11 = tag;
                    return position14;
                }
            case 67:
                long oneofCaseOffset14 = oneofCaseOffset;
                Unsafe unsafe19 = unsafe2;
                ArrayDecoders.Registers registers19 = registers2;
                if (i4 != 0) {
                    position2 = position;
                    long j12 = oneofCaseOffset14;
                    ArrayDecoders.Registers registers20 = registers19;
                    byte[] bArr18 = data;
                    Unsafe unsafe20 = unsafe19;
                    int i12 = tag;
                    break;
                } else {
                    byte[] bArr19 = data;
                    int position15 = ArrayDecoders.decodeVarint64(bArr19, position, registers19);
                    unsafe19.putObject(t, j, Long.valueOf(CodedInputStream.decodeZigZag64(registers19.long1)));
                    unsafe19.putInt(t, oneofCaseOffset14, i3);
                    ArrayDecoders.Registers registers21 = registers19;
                    byte[] bArr20 = bArr19;
                    Unsafe unsafe21 = unsafe19;
                    int i13 = tag;
                    return position15;
                }
            case 68:
                if (i4 != 3) {
                    Unsafe unsafe22 = unsafe2;
                    position2 = position;
                    long j13 = oneofCaseOffset;
                    Unsafe unsafe23 = unsafe22;
                    byte[] bArr21 = data;
                    int i14 = tag;
                    ArrayDecoders.Registers registers22 = registers2;
                    break;
                } else {
                    int endTag = (i2 & -8) | 4;
                    long oneofCaseOffset15 = oneofCaseOffset;
                    Unsafe unsafe24 = unsafe2;
                    ArrayDecoders.Registers registers23 = registers2;
                    int position16 = ArrayDecoders.decodeGroupField(getMessageFieldSchema(i5), data, position, limit, endTag, registers);
                    if (unsafe24.getInt(t, oneofCaseOffset15) == i3) {
                        obj = unsafe24.getObject(t, j);
                    }
                    Object oldValue2 = obj;
                    if (oldValue2 == null) {
                        unsafe24.putObject(t, j, registers23.object1);
                    } else {
                        unsafe24.putObject(t, j, Internal.mergeMessage(oldValue2, registers23.object1));
                    }
                    unsafe24.putInt(t, oneofCaseOffset15, i3);
                    long j14 = oneofCaseOffset15;
                    Unsafe unsafe25 = unsafe24;
                    byte[] bArr22 = data;
                    int i15 = tag;
                    ArrayDecoders.Registers registers24 = registers23;
                    return position16;
                }
            default:
                long j15 = oneofCaseOffset;
                Unsafe unsafe26 = unsafe2;
                position2 = i;
                byte[] bArr23 = bArr;
                ArrayDecoders.Registers registers25 = registers2;
                long j16 = j15;
                break;
        }
        return position2;
    }

    private Schema getMessageFieldSchema(int pos) {
        int index = (pos / 3) * 2;
        Schema schema = (Schema) this.objects[index];
        if (schema != null) {
            return schema;
        }
        Schema schema2 = Protobuf.getInstance().schemaFor((Class) this.objects[index + 1]);
        this.objects[index] = schema2;
        return schema2;
    }

    private Object getMapFieldDefaultEntry(int pos) {
        return this.objects[(pos / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int pos) {
        return (Internal.EnumVerifier) this.objects[((pos / 3) * 2) + 1];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: com.google.protobuf.UnknownFieldSetLite} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v48, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v52, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v53, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int parseProto2Message(T r34, byte[] r35, int r36, int r37, int r38, com.google.protobuf.ArrayDecoders.Registers r39) throws java.io.IOException {
        /*
            r33 = this;
            r15 = r33
            r14 = r34
            r12 = r35
            r13 = r37
            r11 = r38
            r9 = r39
            sun.misc.Unsafe r10 = UNSAFE
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r1 = 0
            r2 = 0
            r3 = -1
            r4 = 0
            r8 = r0
            r7 = r1
            r0 = r36
        L_0x0019:
            if (r0 >= r13) goto L_0x0659
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002a
            int r1 = com.google.protobuf.ArrayDecoders.decodeVarint32(r0, r12, r1, r9)
            int r0 = r9.int1
            r2 = r0
            r5 = r1
            goto L_0x002c
        L_0x002a:
            r2 = r0
            r5 = r1
        L_0x002c:
            int r1 = r2 >>> 3
            r0 = r2 & 7
            if (r1 <= r3) goto L_0x003a
            int r6 = r4 / 3
            int r4 = r15.positionForFieldNumber(r1, r6)
            r6 = r4
            goto L_0x003f
        L_0x003a:
            int r4 = r15.positionForFieldNumber(r1)
            r6 = r4
        L_0x003f:
            r16 = r1
            r3 = -1
            if (r6 != r3) goto L_0x0053
            r3 = 0
            r15 = r0
            r24 = r1
            r36 = r2
            r21 = r3
            r13 = r5
            r17 = r7
            r30 = r10
            goto L_0x05f9
        L_0x0053:
            int[] r3 = r15.buffer
            int r4 = r6 + 1
            r4 = r3[r4]
            int r3 = type(r4)
            long r11 = offset(r4)
            r17 = r2
            r2 = 17
            r18 = r4
            if (r3 > r2) goto L_0x0477
            int[] r2 = r15.buffer
            int r19 = r6 + 2
            r19 = r2[r19]
            int r2 = r19 >>> 20
            r4 = 1
            int r21 = r4 << r2
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r19 & r2
            if (r13 == r8) goto L_0x0090
            if (r8 == r2) goto L_0x0084
            r36 = r5
            long r4 = (long) r8
            r10.putInt(r14, r4, r7)
            goto L_0x0086
        L_0x0084:
            r36 = r5
        L_0x0086:
            r4 = r13
            r5 = r3
            long r2 = (long) r13
            int r2 = r10.getInt(r14, r2)
            r7 = r2
            r8 = r4
            goto L_0x0093
        L_0x0090:
            r36 = r5
            r5 = r3
        L_0x0093:
            r2 = 5
            switch(r5) {
                case 0: goto L_0x0438;
                case 1: goto L_0x0405;
                case 2: goto L_0x03cd;
                case 3: goto L_0x03cd;
                case 4: goto L_0x039a;
                case 5: goto L_0x0356;
                case 6: goto L_0x0321;
                case 7: goto L_0x02e3;
                case 8: goto L_0x02a2;
                case 9: goto L_0x024d;
                case 10: goto L_0x0215;
                case 11: goto L_0x039a;
                case 12: goto L_0x01a9;
                case 13: goto L_0x0321;
                case 14: goto L_0x0356;
                case 15: goto L_0x016c;
                case 16: goto L_0x0123;
                case 17: goto L_0x00af;
                default: goto L_0x0097;
            }
        L_0x0097:
            r24 = r1
            r15 = r6
            r25 = r8
            r6 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r32 = r13
            r13 = r36
            r36 = r17
            r17 = r32
            goto L_0x046c
        L_0x00af:
            r2 = 3
            if (r0 != r2) goto L_0x010b
            int r2 = r1 << 3
            r20 = r2 | 4
            com.google.protobuf.Schema r2 = r15.getMessageFieldSchema(r6)
            r4 = r0
            r0 = r2
            r24 = r1
            r1 = r35
            r3 = r17
            r2 = r36
            r17 = r13
            r13 = r5
            r5 = r3
            r3 = r37
            r25 = r8
            r8 = r4
            r32 = r18
            r18 = r13
            r13 = r32
            r4 = r20
            r26 = r13
            r13 = r36
            r36 = r5
            r5 = r39
            int r0 = com.google.protobuf.ArrayDecoders.decodeGroupField(r0, r1, r2, r3, r4, r5)
            r1 = r7 & r21
            if (r1 != 0) goto L_0x00ec
            java.lang.Object r1 = r9.object1
            r10.putObject(r14, r11, r1)
            goto L_0x00fa
        L_0x00ec:
            java.lang.Object r1 = r10.getObject(r14, r11)
            java.lang.Object r2 = r9.object1
            java.lang.Object r1 = com.google.protobuf.Internal.mergeMessage(r1, r2)
            r10.putObject(r14, r11, r1)
        L_0x00fa:
            r7 = r7 | r21
            r12 = r35
            r2 = r36
            r13 = r37
            r11 = r38
            r4 = r6
            r3 = r16
            r8 = r25
            goto L_0x0019
        L_0x010b:
            r24 = r1
            r25 = r8
            r26 = r18
            r8 = r0
            r18 = r5
            r32 = r13
            r13 = r36
            r36 = r17
            r17 = r32
            r15 = r6
            r6 = r10
            r10 = r11
            r12 = r35
            goto L_0x046c
        L_0x0123:
            r24 = r1
            r25 = r8
            r26 = r18
            r8 = r0
            r18 = r5
            r32 = r13
            r13 = r36
            r36 = r17
            r17 = r32
            if (r8 != 0) goto L_0x0162
            r4 = r11
            r12 = r35
            int r11 = com.google.protobuf.ArrayDecoders.decodeVarint64(r12, r13, r9)
            long r0 = r9.long1
            long r22 = com.google.protobuf.CodedInputStream.decodeZigZag64(r0)
            r0 = r10
            r1 = r34
            r2 = r4
            r27 = r10
            r13 = r11
            r10 = r4
            r4 = r22
            r0.putLong(r1, r2, r4)
            r7 = r7 | r21
            r2 = r36
            r11 = r38
            r4 = r6
            r0 = r13
            r3 = r16
            r8 = r25
            r10 = r27
            r13 = r37
            goto L_0x0019
        L_0x0162:
            r27 = r10
            r10 = r11
            r12 = r35
            r15 = r6
            r6 = r27
            goto L_0x046c
        L_0x016c:
            r24 = r1
            r25 = r8
            r27 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r32 = r13
            r13 = r36
            r36 = r17
            r17 = r32
            if (r8 != 0) goto L_0x01a3
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r12, r13, r9)
            int r1 = r9.int1
            int r1 = com.google.protobuf.CodedInputStream.decodeZigZag32(r1)
            r4 = r27
            r4.putInt(r14, r10, r1)
            r7 = r7 | r21
            r2 = r36
            r13 = r37
            r11 = r38
            r10 = r4
            r4 = r6
            r3 = r16
            r8 = r25
            goto L_0x0019
        L_0x01a3:
            r4 = r27
            r15 = r6
            r6 = r4
            goto L_0x046c
        L_0x01a9:
            r24 = r1
            r25 = r8
            r4 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r32 = r13
            r13 = r36
            r36 = r17
            r17 = r32
            if (r8 != 0) goto L_0x020c
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r12, r13, r9)
            int r1 = r9.int1
            com.google.protobuf.Internal$EnumVerifier r2 = r15.getEnumFieldVerifier(r6)
            if (r2 == 0) goto L_0x01f5
            boolean r3 = r2.isInRange(r1)
            if (r3 == 0) goto L_0x01d7
            r27 = r6
            r6 = r36
            goto L_0x01f9
        L_0x01d7:
            com.google.protobuf.UnknownFieldSetLite r3 = getMutableUnknownFields(r34)
            r27 = r6
            long r5 = (long) r1
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            r6 = r36
            r3.storeField(r6, r5)
            r13 = r37
            r11 = r38
            r10 = r4
            r2 = r6
            r3 = r16
            r8 = r25
            r4 = r27
            goto L_0x0019
        L_0x01f5:
            r27 = r6
            r6 = r36
        L_0x01f9:
            r4.putInt(r14, r10, r1)
            r7 = r7 | r21
            r13 = r37
            r11 = r38
            r10 = r4
            r2 = r6
            r3 = r16
            r8 = r25
            r4 = r27
            goto L_0x0019
        L_0x020c:
            r27 = r6
            r6 = r36
            r15 = r27
            r6 = r4
            goto L_0x046c
        L_0x0215:
            r24 = r1
            r27 = r6
            r25 = r8
            r4 = r10
            r10 = r11
            r6 = r17
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r17 = r13
            r13 = r36
            r0 = 2
            if (r8 != r0) goto L_0x0246
            int r0 = com.google.protobuf.ArrayDecoders.decodeBytes(r12, r13, r9)
            java.lang.Object r1 = r9.object1
            r4.putObject(r14, r10, r1)
            r7 = r7 | r21
            r13 = r37
            r11 = r38
            r10 = r4
            r2 = r6
            r3 = r16
            r8 = r25
            r4 = r27
            goto L_0x0019
        L_0x0246:
            r36 = r6
            r15 = r27
            r6 = r4
            goto L_0x046c
        L_0x024d:
            r24 = r1
            r27 = r6
            r25 = r8
            r4 = r10
            r10 = r11
            r6 = r17
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r17 = r13
            r13 = r36
            r0 = 2
            if (r8 != r0) goto L_0x0298
            r5 = r27
            com.google.protobuf.Schema r0 = r15.getMessageFieldSchema(r5)
            r3 = r37
            int r0 = com.google.protobuf.ArrayDecoders.decodeMessageField(r0, r12, r13, r3, r9)
            r1 = r7 & r21
            if (r1 != 0) goto L_0x027c
            java.lang.Object r1 = r9.object1
            r4.putObject(r14, r10, r1)
            goto L_0x028a
        L_0x027c:
            java.lang.Object r1 = r4.getObject(r14, r10)
            java.lang.Object r2 = r9.object1
            java.lang.Object r1 = com.google.protobuf.Internal.mergeMessage(r1, r2)
            r4.putObject(r14, r10, r1)
        L_0x028a:
            r7 = r7 | r21
            r11 = r38
            r13 = r3
            r10 = r4
            r4 = r5
            r2 = r6
            r3 = r16
            r8 = r25
            goto L_0x0019
        L_0x0298:
            r3 = r37
            r5 = r27
            r15 = r5
            r36 = r6
            r6 = r4
            goto L_0x046c
        L_0x02a2:
            r3 = r37
            r24 = r1
            r25 = r8
            r4 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r5 = r6
            r6 = r17
            r17 = r13
            r13 = r36
            r0 = 2
            if (r8 != r0) goto L_0x02dd
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r26 & r0
            if (r0 != 0) goto L_0x02c6
            int r0 = com.google.protobuf.ArrayDecoders.decodeString(r12, r13, r9)
            goto L_0x02ca
        L_0x02c6:
            int r0 = com.google.protobuf.ArrayDecoders.decodeStringRequireUtf8(r12, r13, r9)
        L_0x02ca:
            java.lang.Object r1 = r9.object1
            r4.putObject(r14, r10, r1)
            r7 = r7 | r21
            r11 = r38
            r13 = r3
            r10 = r4
            r4 = r5
            r2 = r6
            r3 = r16
            r8 = r25
            goto L_0x0019
        L_0x02dd:
            r15 = r5
            r36 = r6
            r6 = r4
            goto L_0x046c
        L_0x02e3:
            r3 = r37
            r24 = r1
            r25 = r8
            r4 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r5 = r6
            r6 = r17
            r17 = r13
            r13 = r36
            if (r8 != 0) goto L_0x031b
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint64(r12, r13, r9)
            long r1 = r9.long1
            r27 = 0
            int r1 = (r1 > r27 ? 1 : (r1 == r27 ? 0 : -1))
            if (r1 == 0) goto L_0x0309
            r1 = 1
            goto L_0x030a
        L_0x0309:
            r1 = 0
        L_0x030a:
            com.google.protobuf.UnsafeUtil.putBoolean((java.lang.Object) r14, (long) r10, (boolean) r1)
            r7 = r7 | r21
            r11 = r38
            r13 = r3
            r10 = r4
            r4 = r5
            r2 = r6
            r3 = r16
            r8 = r25
            goto L_0x0019
        L_0x031b:
            r15 = r5
            r36 = r6
            r6 = r4
            goto L_0x046c
        L_0x0321:
            r3 = r37
            r24 = r1
            r25 = r8
            r4 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r5 = r6
            r6 = r17
            r17 = r13
            r13 = r36
            if (r8 != r2) goto L_0x0350
            int r0 = com.google.protobuf.ArrayDecoders.decodeFixed32(r12, r13)
            r4.putInt(r14, r10, r0)
            int r0 = r13 + 4
            r7 = r7 | r21
            r11 = r38
            r13 = r3
            r10 = r4
            r4 = r5
            r2 = r6
            r3 = r16
            r8 = r25
            goto L_0x0019
        L_0x0350:
            r15 = r5
            r36 = r6
            r6 = r4
            goto L_0x046c
        L_0x0356:
            r3 = r37
            r24 = r1
            r25 = r8
            r4 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r5 = r6
            r6 = r17
            r17 = r13
            r13 = r36
            r0 = 1
            if (r8 != r0) goto L_0x0394
            long r22 = com.google.protobuf.ArrayDecoders.decodeFixed64(r12, r13)
            r0 = r4
            r1 = r34
            r2 = r10
            r15 = r5
            r36 = r6
            r6 = r4
            r4 = r22
            r0.putLong(r1, r2, r4)
            int r0 = r13 + 8
            r7 = r7 | r21
            r2 = r36
            r13 = r37
            r11 = r38
            r10 = r6
            r4 = r15
            r3 = r16
            r8 = r25
            r15 = r33
            goto L_0x0019
        L_0x0394:
            r15 = r5
            r36 = r6
            r6 = r4
            goto L_0x046c
        L_0x039a:
            r24 = r1
            r15 = r6
            r25 = r8
            r6 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r32 = r13
            r13 = r36
            r36 = r17
            r17 = r32
            if (r8 != 0) goto L_0x046c
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r12, r13, r9)
            int r1 = r9.int1
            r6.putInt(r14, r10, r1)
            r7 = r7 | r21
            r2 = r36
            r13 = r37
            r11 = r38
            r10 = r6
            r4 = r15
            r3 = r16
            r8 = r25
            r15 = r33
            goto L_0x0019
        L_0x03cd:
            r24 = r1
            r15 = r6
            r25 = r8
            r6 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r32 = r13
            r13 = r36
            r36 = r17
            r17 = r32
            if (r8 != 0) goto L_0x046c
            int r13 = com.google.protobuf.ArrayDecoders.decodeVarint64(r12, r13, r9)
            long r4 = r9.long1
            r0 = r6
            r1 = r34
            r2 = r10
            r0.putLong(r1, r2, r4)
            r7 = r7 | r21
            r2 = r36
            r11 = r38
            r10 = r6
            r0 = r13
            r4 = r15
            r3 = r16
            r8 = r25
            r15 = r33
            r13 = r37
            goto L_0x0019
        L_0x0405:
            r24 = r1
            r15 = r6
            r25 = r8
            r6 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r32 = r13
            r13 = r36
            r36 = r17
            r17 = r32
            if (r8 != r2) goto L_0x046c
            float r0 = com.google.protobuf.ArrayDecoders.decodeFloat(r12, r13)
            com.google.protobuf.UnsafeUtil.putFloat((java.lang.Object) r14, (long) r10, (float) r0)
            int r0 = r13 + 4
            r7 = r7 | r21
            r2 = r36
            r13 = r37
            r11 = r38
            r10 = r6
            r4 = r15
            r3 = r16
            r8 = r25
            r15 = r33
            goto L_0x0019
        L_0x0438:
            r24 = r1
            r15 = r6
            r25 = r8
            r6 = r10
            r10 = r11
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r5
            r32 = r13
            r13 = r36
            r36 = r17
            r17 = r32
            r0 = 1
            if (r8 != r0) goto L_0x046c
            double r0 = com.google.protobuf.ArrayDecoders.decodeDouble(r12, r13)
            com.google.protobuf.UnsafeUtil.putDouble((java.lang.Object) r14, (long) r10, (double) r0)
            int r0 = r13 + 8
            r7 = r7 | r21
            r2 = r36
            r13 = r37
            r11 = r38
            r10 = r6
            r4 = r15
            r3 = r16
            r8 = r25
            r15 = r33
            goto L_0x0019
        L_0x046c:
            r30 = r6
            r17 = r7
            r21 = r15
            r15 = r8
            r8 = r25
            goto L_0x05f9
        L_0x0477:
            r24 = r1
            r13 = r5
            r15 = r6
            r25 = r8
            r6 = r10
            r10 = r11
            r36 = r17
            r26 = r18
            r12 = r35
            r8 = r0
            r18 = r3
            r0 = 27
            r5 = r18
            if (r5 != r0) goto L_0x04f8
            r0 = 2
            if (r8 != r0) goto L_0x04e6
            java.lang.Object r0 = r6.getObject(r14, r10)
            com.google.protobuf.Internal$ProtobufList r0 = (com.google.protobuf.Internal.ProtobufList) r0
            boolean r1 = r0.isModifiable()
            if (r1 != 0) goto L_0x04b2
            int r1 = r0.size()
            if (r1 != 0) goto L_0x04a6
            r2 = 10
            goto L_0x04a8
        L_0x04a6:
            int r2 = r1 * 2
        L_0x04a8:
            com.google.protobuf.Internal$ProtobufList r0 = r0.mutableCopyWithCapacity(r2)
            r6.putObject(r14, r10, r0)
            r17 = r0
            goto L_0x04b4
        L_0x04b2:
            r17 = r0
        L_0x04b4:
            r4 = r15
            r15 = r33
            com.google.protobuf.Schema r0 = r15.getMessageFieldSchema(r4)
            r1 = r36
            r2 = r35
            r3 = r13
            r18 = r4
            r4 = r37
            r12 = r5
            r5 = r17
            r19 = r36
            r21 = r18
            r18 = r6
            r6 = r39
            int r0 = com.google.protobuf.ArrayDecoders.decodeMessageList(r0, r1, r2, r3, r4, r5, r6)
            r12 = r35
            r13 = r37
            r11 = r38
            r3 = r16
            r10 = r18
            r2 = r19
            r4 = r21
            r8 = r25
            goto L_0x0019
        L_0x04e6:
            r19 = r36
            r12 = r5
            r18 = r6
            r21 = r15
            r15 = r33
            r17 = r7
            r15 = r8
            r30 = r18
            r19 = r13
            goto L_0x05b1
        L_0x04f8:
            r19 = r36
            r12 = r5
            r18 = r6
            r21 = r15
            r15 = r33
            r0 = 49
            if (r12 > r0) goto L_0x0560
            r6 = r13
            r5 = r26
            long r3 = (long) r5
            r0 = r33
            r1 = r34
            r17 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r35
            r22 = r3
            r3 = r13
            r4 = r37
            r20 = r5
            r5 = r19
            r15 = r6
            r6 = r24
            r17 = r7
            r7 = r8
            r29 = r8
            r8 = r21
            r26 = r10
            r30 = r18
            r11 = r9
            r9 = r22
            r11 = r12
            r31 = r12
            r36 = r19
            r18 = r20
            r19 = r13
            r12 = r26
            r14 = r39
            int r0 = r0.parseRepeatedField(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x0559
            r15 = r33
            r14 = r34
            r12 = r35
            r2 = r36
            r13 = r37
            r11 = r38
            r9 = r39
            r3 = r16
            r7 = r17
            r4 = r21
            r8 = r25
            r10 = r30
            goto L_0x0019
        L_0x0559:
            r13 = r0
            r8 = r25
            r15 = r29
            goto L_0x05f9
        L_0x0560:
            r17 = r7
            r29 = r8
            r31 = r12
            r30 = r18
            r36 = r19
            r18 = r26
            r26 = r10
            r19 = r13
            r0 = 50
            r14 = r31
            if (r14 != r0) goto L_0x05b6
            r15 = r29
            r0 = 2
            if (r15 != r0) goto L_0x05b1
            r9 = r19
            r0 = r33
            r1 = r34
            r2 = r35
            r3 = r19
            r4 = r37
            r5 = r21
            r6 = r26
            r8 = r39
            int r0 = r0.parseMapField(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r9) goto L_0x05ad
            r15 = r33
            r14 = r34
            r12 = r35
            r2 = r36
            r13 = r37
            r11 = r38
            r9 = r39
            r3 = r16
            r7 = r17
            r4 = r21
            r8 = r25
            r10 = r30
            goto L_0x0019
        L_0x05ad:
            r13 = r0
            r8 = r25
            goto L_0x05f9
        L_0x05b1:
            r13 = r19
            r8 = r25
            goto L_0x05f9
        L_0x05b6:
            r15 = r29
            r13 = r19
            r0 = r33
            r1 = r34
            r2 = r35
            r3 = r19
            r4 = r37
            r5 = r36
            r6 = r24
            r7 = r15
            r8 = r18
            r9 = r14
            r10 = r26
            r12 = r21
            r31 = r14
            r14 = r13
            r13 = r39
            int r0 = r0.parseOneofField(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r14) goto L_0x05f6
            r15 = r33
            r14 = r34
            r12 = r35
            r2 = r36
            r13 = r37
            r11 = r38
            r9 = r39
            r3 = r16
            r7 = r17
            r4 = r21
            r8 = r25
            r10 = r30
            goto L_0x0019
        L_0x05f6:
            r13 = r0
            r8 = r25
        L_0x05f9:
            r10 = r36
            r9 = r38
            if (r10 != r9) goto L_0x060f
            if (r9 == 0) goto L_0x060f
            r11 = r33
            r12 = r39
            r2 = r10
            r0 = r13
            r3 = r16
            r7 = r17
            r4 = r21
            goto L_0x0662
        L_0x060f:
            r11 = r33
            boolean r0 = r11.hasExtensions
            if (r0 == 0) goto L_0x0632
            r12 = r39
            com.google.protobuf.ExtensionRegistryLite r0 = r12.extensionRegistry
            com.google.protobuf.ExtensionRegistryLite r1 = com.google.protobuf.ExtensionRegistryLite.getEmptyRegistry()
            if (r0 == r1) goto L_0x0634
            com.google.protobuf.MessageLite r5 = r11.defaultInstance
            com.google.protobuf.UnknownFieldSchema<?, ?> r6 = r11.unknownFieldSchema
            r0 = r10
            r1 = r35
            r2 = r13
            r3 = r37
            r4 = r34
            r7 = r39
            int r0 = com.google.protobuf.ArrayDecoders.decodeExtensionOrUnknownField(r0, r1, r2, r3, r4, r5, r6, r7)
            goto L_0x0645
        L_0x0632:
            r12 = r39
        L_0x0634:
            com.google.protobuf.UnknownFieldSetLite r4 = getMutableUnknownFields(r34)
            r0 = r10
            r1 = r35
            r2 = r13
            r3 = r37
            r5 = r39
            int r0 = com.google.protobuf.ArrayDecoders.decodeUnknownField(r0, r1, r2, r3, r4, r5)
        L_0x0645:
            r14 = r34
            r13 = r37
            r2 = r10
            r15 = r11
            r3 = r16
            r7 = r17
            r4 = r21
            r10 = r30
            r11 = r9
            r9 = r12
            r12 = r35
            goto L_0x0019
        L_0x0659:
            r17 = r7
            r25 = r8
            r12 = r9
            r30 = r10
            r9 = r11
            r11 = r15
        L_0x0662:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            if (r8 == r1) goto L_0x0670
            long r5 = (long) r8
            r1 = r34
            r10 = r30
            r10.putInt(r1, r5, r7)
            goto L_0x0674
        L_0x0670:
            r1 = r34
            r10 = r30
        L_0x0674:
            r5 = 0
            int r6 = r11.checkInitializedCount
        L_0x0677:
            int r13 = r11.repeatedFieldOffsetStart
            if (r6 >= r13) goto L_0x068b
            int[] r13 = r11.intArray
            r13 = r13[r6]
            com.google.protobuf.UnknownFieldSchema<?, ?> r14 = r11.unknownFieldSchema
            java.lang.Object r13 = r11.filterMapUnknownEnumValues(r1, r13, r5, r14)
            r5 = r13
            com.google.protobuf.UnknownFieldSetLite r5 = (com.google.protobuf.UnknownFieldSetLite) r5
            int r6 = r6 + 1
            goto L_0x0677
        L_0x068b:
            if (r5 == 0) goto L_0x0692
            com.google.protobuf.UnknownFieldSchema<?, ?> r6 = r11.unknownFieldSchema
            r6.setBuilderToMessage(r1, r5)
        L_0x0692:
            if (r9 != 0) goto L_0x069e
            r6 = r37
            if (r0 != r6) goto L_0x0699
            goto L_0x06a4
        L_0x0699:
            com.google.protobuf.InvalidProtocolBufferException r13 = com.google.protobuf.InvalidProtocolBufferException.parseFailure()
            throw r13
        L_0x069e:
            r6 = r37
            if (r0 > r6) goto L_0x06a5
            if (r2 != r9) goto L_0x06a5
        L_0x06a4:
            return r0
        L_0x06a5:
            com.google.protobuf.InvalidProtocolBufferException r13 = com.google.protobuf.InvalidProtocolBufferException.parseFailure()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.parseProto2Message(java.lang.Object, byte[], int, int, int, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseProto3Message(T r32, byte[] r33, int r34, int r35, com.google.protobuf.ArrayDecoders.Registers r36) throws java.io.IOException {
        /*
            r31 = this;
            r15 = r31
            r14 = r32
            r12 = r33
            r13 = r35
            r11 = r36
            sun.misc.Unsafe r9 = UNSAFE
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r1 = 0
            r2 = 0
            r3 = -1
            r4 = 0
            r10 = r0
            r8 = r1
            r0 = r34
        L_0x0017:
            if (r0 >= r13) goto L_0x04c3
            int r5 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0029
            int r5 = com.google.protobuf.ArrayDecoders.decodeVarint32(r0, r12, r5, r11)
            int r0 = r11.int1
            r16 = r0
            r7 = r5
            goto L_0x002c
        L_0x0029:
            r16 = r0
            r7 = r5
        L_0x002c:
            int r6 = r16 >>> 3
            r5 = r16 & 7
            if (r6 <= r3) goto L_0x003a
            int r0 = r4 / 3
            int r0 = r15.positionForFieldNumber(r6, r0)
            r4 = r0
            goto L_0x003f
        L_0x003a:
            int r0 = r15.positionForFieldNumber(r6)
            r4 = r0
        L_0x003f:
            r17 = r6
            r0 = -1
            if (r4 != r0) goto L_0x004e
            r0 = 0
            r19 = r0
            r15 = r5
            r18 = r6
            r29 = r9
            goto L_0x049d
        L_0x004e:
            int[] r0 = r15.buffer
            int r2 = r4 + 1
            r2 = r0[r2]
            int r3 = type(r2)
            r18 = r6
            r34 = r7
            long r6 = offset(r2)
            r0 = 17
            if (r3 > r0) goto L_0x0329
            int[] r0 = r15.buffer
            int r20 = r4 + 2
            r20 = r0[r20]
            int r0 = r20 >>> 20
            r1 = 1
            int r22 = r1 << r0
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r20 & r0
            if (r13 == r10) goto L_0x008c
            if (r10 == r0) goto L_0x007f
            r19 = r2
            long r1 = (long) r10
            r9.putInt(r14, r1, r8)
            goto L_0x0081
        L_0x007f:
            r19 = r2
        L_0x0081:
            if (r13 == r0) goto L_0x0089
            long r0 = (long) r13
            int r0 = r9.getInt(r14, r0)
            r8 = r0
        L_0x0089:
            r0 = r13
            r10 = r0
            goto L_0x008e
        L_0x008c:
            r19 = r2
        L_0x008e:
            r0 = 5
            switch(r3) {
                case 0: goto L_0x02f8;
                case 1: goto L_0x02d2;
                case 2: goto L_0x02a7;
                case 3: goto L_0x02a7;
                case 4: goto L_0x0281;
                case 5: goto L_0x024a;
                case 6: goto L_0x021d;
                case 7: goto L_0x01e7;
                case 8: goto L_0x01ae;
                case 9: goto L_0x0165;
                case 10: goto L_0x013b;
                case 11: goto L_0x0281;
                case 12: goto L_0x0112;
                case 13: goto L_0x021d;
                case 14: goto L_0x024a;
                case 15: goto L_0x00e5;
                case 16: goto L_0x00a1;
                default: goto L_0x0092;
            }
        L_0x0092:
            r25 = r3
            r24 = r13
            r26 = r19
            r13 = r34
            r34 = r4
            r19 = r10
            r10 = r5
            goto L_0x031f
        L_0x00a1:
            if (r5 != 0) goto L_0x00d4
            r2 = r34
            int r21 = com.google.protobuf.ArrayDecoders.decodeVarint64(r12, r2, r11)
            long r0 = r11.long1
            long r23 = com.google.protobuf.CodedInputStream.decodeZigZag64(r0)
            r0 = r9
            r1 = r32
            r34 = r13
            r13 = r19
            r19 = r10
            r10 = r3
            r2 = r6
            r25 = r10
            r26 = r13
            r13 = r4
            r10 = r5
            r4 = r23
            r0.putLong(r1, r2, r4)
            r8 = r8 | r22
            r4 = r13
            r2 = r16
            r3 = r17
            r10 = r19
            r0 = r21
            r13 = r35
            goto L_0x0017
        L_0x00d4:
            r2 = r34
            r25 = r3
            r34 = r13
            r26 = r19
            r13 = r4
            r19 = r10
            r10 = r5
            r34 = r13
            r13 = r2
            goto L_0x031f
        L_0x00e5:
            r2 = r34
            r25 = r3
            r34 = r13
            r26 = r19
            r13 = r4
            r19 = r10
            r10 = r5
            if (r10 != 0) goto L_0x010d
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r12, r2, r11)
            int r1 = r11.int1
            int r1 = com.google.protobuf.CodedInputStream.decodeZigZag32(r1)
            r9.putInt(r14, r6, r1)
            r8 = r8 | r22
            r4 = r13
            r2 = r16
            r3 = r17
            r10 = r19
            r13 = r35
            goto L_0x0017
        L_0x010d:
            r34 = r13
            r13 = r2
            goto L_0x031f
        L_0x0112:
            r2 = r34
            r25 = r3
            r34 = r13
            r26 = r19
            r13 = r4
            r19 = r10
            r10 = r5
            if (r10 != 0) goto L_0x0136
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r12, r2, r11)
            int r1 = r11.int1
            r9.putInt(r14, r6, r1)
            r8 = r8 | r22
            r4 = r13
            r2 = r16
            r3 = r17
            r10 = r19
            r13 = r35
            goto L_0x0017
        L_0x0136:
            r34 = r13
            r13 = r2
            goto L_0x031f
        L_0x013b:
            r2 = r34
            r25 = r3
            r34 = r13
            r26 = r19
            r13 = r4
            r19 = r10
            r10 = r5
            r0 = 2
            if (r10 != r0) goto L_0x0160
            int r0 = com.google.protobuf.ArrayDecoders.decodeBytes(r12, r2, r11)
            java.lang.Object r1 = r11.object1
            r9.putObject(r14, r6, r1)
            r8 = r8 | r22
            r4 = r13
            r2 = r16
            r3 = r17
            r10 = r19
            r13 = r35
            goto L_0x0017
        L_0x0160:
            r34 = r13
            r13 = r2
            goto L_0x031f
        L_0x0165:
            r2 = r34
            r25 = r3
            r34 = r13
            r26 = r19
            r13 = r4
            r19 = r10
            r10 = r5
            r0 = 2
            if (r10 != r0) goto L_0x01a5
            com.google.protobuf.Schema r0 = r15.getMessageFieldSchema(r13)
            r24 = r34
            r4 = r35
            int r0 = com.google.protobuf.ArrayDecoders.decodeMessageField(r0, r12, r2, r4, r11)
            java.lang.Object r1 = r9.getObject(r14, r6)
            if (r1 != 0) goto L_0x018d
            java.lang.Object r2 = r11.object1
            r9.putObject(r14, r6, r2)
            goto L_0x0196
        L_0x018d:
            java.lang.Object r2 = r11.object1
            java.lang.Object r2 = com.google.protobuf.Internal.mergeMessage(r1, r2)
            r9.putObject(r14, r6, r2)
        L_0x0196:
            r8 = r8 | r22
            r2 = r16
            r3 = r17
            r10 = r19
            r30 = r13
            r13 = r4
            r4 = r30
            goto L_0x0017
        L_0x01a5:
            r24 = r34
            r4 = r35
            r34 = r13
            r13 = r2
            goto L_0x031f
        L_0x01ae:
            r2 = r34
            r25 = r3
            r24 = r13
            r26 = r19
            r13 = r4
            r19 = r10
            r4 = r35
            r10 = r5
            r0 = 2
            if (r10 != r0) goto L_0x01e2
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r26 & r0
            if (r0 != 0) goto L_0x01ca
            int r0 = com.google.protobuf.ArrayDecoders.decodeString(r12, r2, r11)
            goto L_0x01ce
        L_0x01ca:
            int r0 = com.google.protobuf.ArrayDecoders.decodeStringRequireUtf8(r12, r2, r11)
        L_0x01ce:
            java.lang.Object r1 = r11.object1
            r9.putObject(r14, r6, r1)
            r8 = r8 | r22
            r2 = r16
            r3 = r17
            r10 = r19
            r30 = r13
            r13 = r4
            r4 = r30
            goto L_0x0017
        L_0x01e2:
            r34 = r13
            r13 = r2
            goto L_0x031f
        L_0x01e7:
            r2 = r34
            r25 = r3
            r24 = r13
            r26 = r19
            r13 = r4
            r19 = r10
            r4 = r35
            r10 = r5
            if (r10 != 0) goto L_0x0218
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint64(r12, r2, r11)
            long r1 = r11.long1
            r27 = 0
            int r1 = (r1 > r27 ? 1 : (r1 == r27 ? 0 : -1))
            if (r1 == 0) goto L_0x0205
            r1 = 1
            goto L_0x0206
        L_0x0205:
            r1 = 0
        L_0x0206:
            com.google.protobuf.UnsafeUtil.putBoolean((java.lang.Object) r14, (long) r6, (boolean) r1)
            r8 = r8 | r22
            r2 = r16
            r3 = r17
            r10 = r19
            r30 = r13
            r13 = r4
            r4 = r30
            goto L_0x0017
        L_0x0218:
            r34 = r13
            r13 = r2
            goto L_0x031f
        L_0x021d:
            r2 = r34
            r25 = r3
            r24 = r13
            r26 = r19
            r13 = r4
            r19 = r10
            r4 = r35
            r10 = r5
            if (r10 != r0) goto L_0x0245
            int r0 = com.google.protobuf.ArrayDecoders.decodeFixed32(r12, r2)
            r9.putInt(r14, r6, r0)
            int r0 = r2 + 4
            r8 = r8 | r22
            r2 = r16
            r3 = r17
            r10 = r19
            r30 = r13
            r13 = r4
            r4 = r30
            goto L_0x0017
        L_0x0245:
            r34 = r13
            r13 = r2
            goto L_0x031f
        L_0x024a:
            r2 = r34
            r25 = r3
            r24 = r13
            r26 = r19
            r13 = r4
            r19 = r10
            r4 = r35
            r10 = r5
            r0 = 1
            if (r10 != r0) goto L_0x027c
            long r27 = com.google.protobuf.ArrayDecoders.decodeFixed64(r12, r2)
            r0 = r9
            r1 = r32
            r5 = r2
            r2 = r6
            r34 = r13
            r13 = r5
            r4 = r27
            r0.putLong(r1, r2, r4)
            int r0 = r13 + 8
            r8 = r8 | r22
            r4 = r34
            r13 = r35
            r2 = r16
            r3 = r17
            r10 = r19
            goto L_0x0017
        L_0x027c:
            r34 = r13
            r13 = r2
            goto L_0x031f
        L_0x0281:
            r25 = r3
            r24 = r13
            r26 = r19
            r13 = r34
            r34 = r4
            r19 = r10
            r10 = r5
            if (r10 != 0) goto L_0x031f
            int r0 = com.google.protobuf.ArrayDecoders.decodeVarint32(r12, r13, r11)
            int r1 = r11.int1
            r9.putInt(r14, r6, r1)
            r8 = r8 | r22
            r4 = r34
            r13 = r35
            r2 = r16
            r3 = r17
            r10 = r19
            goto L_0x0017
        L_0x02a7:
            r25 = r3
            r24 = r13
            r26 = r19
            r13 = r34
            r34 = r4
            r19 = r10
            r10 = r5
            if (r10 != 0) goto L_0x031f
            int r13 = com.google.protobuf.ArrayDecoders.decodeVarint64(r12, r13, r11)
            long r4 = r11.long1
            r0 = r9
            r1 = r32
            r2 = r6
            r0.putLong(r1, r2, r4)
            r8 = r8 | r22
            r4 = r34
            r0 = r13
            r2 = r16
            r3 = r17
            r10 = r19
            r13 = r35
            goto L_0x0017
        L_0x02d2:
            r25 = r3
            r24 = r13
            r26 = r19
            r13 = r34
            r34 = r4
            r19 = r10
            r10 = r5
            if (r10 != r0) goto L_0x031f
            float r0 = com.google.protobuf.ArrayDecoders.decodeFloat(r12, r13)
            com.google.protobuf.UnsafeUtil.putFloat((java.lang.Object) r14, (long) r6, (float) r0)
            int r0 = r13 + 4
            r8 = r8 | r22
            r4 = r34
            r13 = r35
            r2 = r16
            r3 = r17
            r10 = r19
            goto L_0x0017
        L_0x02f8:
            r25 = r3
            r24 = r13
            r26 = r19
            r13 = r34
            r34 = r4
            r19 = r10
            r10 = r5
            r0 = 1
            if (r10 != r0) goto L_0x031f
            double r0 = com.google.protobuf.ArrayDecoders.decodeDouble(r12, r13)
            com.google.protobuf.UnsafeUtil.putDouble((java.lang.Object) r14, (long) r6, (double) r0)
            int r0 = r13 + 8
            r8 = r8 | r22
            r4 = r34
            r13 = r35
            r2 = r16
            r3 = r17
            r10 = r19
            goto L_0x0017
        L_0x031f:
            r29 = r9
            r15 = r10
            r7 = r13
            r10 = r19
            r19 = r34
            goto L_0x049d
        L_0x0329:
            r13 = r34
            r26 = r2
            r25 = r3
            r34 = r4
            r19 = r10
            r10 = r5
            r0 = 27
            r5 = r25
            if (r5 != r0) goto L_0x039f
            r0 = 2
            if (r10 != r0) goto L_0x038b
            java.lang.Object r0 = r9.getObject(r14, r6)
            com.google.protobuf.Internal$ProtobufList r0 = (com.google.protobuf.Internal.ProtobufList) r0
            boolean r1 = r0.isModifiable()
            if (r1 != 0) goto L_0x035e
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0352
            r2 = 10
            goto L_0x0354
        L_0x0352:
            int r2 = r1 * 2
        L_0x0354:
            com.google.protobuf.Internal$ProtobufList r0 = r0.mutableCopyWithCapacity(r2)
            r9.putObject(r14, r6, r0)
            r20 = r0
            goto L_0x0360
        L_0x035e:
            r20 = r0
        L_0x0360:
            r4 = r34
            com.google.protobuf.Schema r0 = r15.getMessageFieldSchema(r4)
            r1 = r16
            r2 = r33
            r3 = r13
            r22 = r4
            r4 = r35
            r23 = r9
            r9 = r5
            r5 = r20
            r24 = r6
            r6 = r36
            int r0 = com.google.protobuf.ArrayDecoders.decodeMessageList(r0, r1, r2, r3, r4, r5, r6)
            r13 = r35
            r2 = r16
            r3 = r17
            r10 = r19
            r4 = r22
            r9 = r23
            goto L_0x0017
        L_0x038b:
            r22 = r34
            r24 = r6
            r23 = r9
            r9 = r5
            r28 = r8
            r15 = r10
            r27 = r13
            r29 = r23
            r23 = r19
            r19 = r22
            goto L_0x0453
        L_0x039f:
            r22 = r34
            r24 = r6
            r23 = r9
            r9 = r5
            r0 = 49
            if (r9 > r0) goto L_0x0402
            r7 = r13
            r6 = r26
            long r4 = (long) r6
            r0 = r31
            r1 = r32
            r2 = r33
            r3 = r13
            r20 = r4
            r4 = r35
            r5 = r16
            r6 = r18
            r27 = r13
            r7 = r10
            r15 = r8
            r8 = r22
            r34 = r9
            r28 = r15
            r29 = r23
            r15 = r10
            r23 = r19
            r9 = r20
            r11 = r34
            r19 = r22
            r20 = r26
            r22 = r15
            r15 = r13
            r12 = r24
            r14 = r36
            int r0 = r0.parseRepeatedField(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x03f9
            r15 = r31
            r14 = r32
            r12 = r33
            r13 = r35
            r11 = r36
            r2 = r16
            r3 = r17
            r4 = r19
            r10 = r23
            r8 = r28
            r9 = r29
            goto L_0x0017
        L_0x03f9:
            r7 = r0
            r15 = r22
            r10 = r23
            r8 = r28
            goto L_0x049d
        L_0x0402:
            r28 = r8
            r34 = r9
            r27 = r13
            r29 = r23
            r20 = r26
            r23 = r19
            r19 = r22
            r22 = r10
            r0 = 50
            r14 = r34
            if (r14 != r0) goto L_0x045a
            r15 = r22
            r0 = 2
            if (r15 != r0) goto L_0x0453
            r9 = r27
            r0 = r31
            r1 = r32
            r2 = r33
            r3 = r27
            r4 = r35
            r5 = r19
            r6 = r24
            r8 = r36
            int r0 = r0.parseMapField(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r9) goto L_0x044d
            r15 = r31
            r14 = r32
            r12 = r33
            r13 = r35
            r11 = r36
            r2 = r16
            r3 = r17
            r4 = r19
            r10 = r23
            r8 = r28
            r9 = r29
            goto L_0x0017
        L_0x044d:
            r7 = r0
            r10 = r23
            r8 = r28
            goto L_0x049d
        L_0x0453:
            r10 = r23
            r7 = r27
            r8 = r28
            goto L_0x049d
        L_0x045a:
            r15 = r22
            r13 = r27
            r0 = r31
            r1 = r32
            r2 = r33
            r3 = r27
            r4 = r35
            r5 = r16
            r6 = r18
            r7 = r15
            r8 = r20
            r9 = r14
            r10 = r24
            r12 = r19
            r34 = r14
            r14 = r13
            r13 = r36
            int r0 = r0.parseOneofField(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r14) goto L_0x0498
            r15 = r31
            r14 = r32
            r12 = r33
            r13 = r35
            r11 = r36
            r2 = r16
            r3 = r17
            r4 = r19
            r10 = r23
            r8 = r28
            r9 = r29
            goto L_0x0017
        L_0x0498:
            r7 = r0
            r10 = r23
            r8 = r28
        L_0x049d:
            com.google.protobuf.UnknownFieldSetLite r4 = getMutableUnknownFields(r32)
            r0 = r16
            r1 = r33
            r2 = r7
            r3 = r35
            r5 = r36
            int r0 = com.google.protobuf.ArrayDecoders.decodeUnknownField(r0, r1, r2, r3, r4, r5)
            r15 = r31
            r14 = r32
            r12 = r33
            r13 = r35
            r11 = r36
            r2 = r16
            r3 = r17
            r4 = r19
            r9 = r29
            goto L_0x0017
        L_0x04c3:
            r28 = r8
            r29 = r9
            r23 = r10
            r1 = 1048575(0xfffff, float:1.469367E-39)
            if (r10 == r1) goto L_0x04d9
            long r5 = (long) r10
            r1 = r32
            r8 = r28
            r7 = r29
            r7.putInt(r1, r5, r8)
            goto L_0x04df
        L_0x04d9:
            r1 = r32
            r8 = r28
            r7 = r29
        L_0x04df:
            r5 = r35
            if (r0 != r5) goto L_0x04e4
            return r0
        L_0x04e4:
            com.google.protobuf.InvalidProtocolBufferException r6 = com.google.protobuf.InvalidProtocolBufferException.parseFailure()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.parseProto3Message(java.lang.Object, byte[], int, int, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        if (this.proto3) {
            parseProto3Message(message, data, position, limit, registers);
        } else {
            parseProto2Message(message, data, position, limit, 0, registers);
        }
    }

    public void makeImmutable(T message) {
        for (int i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++) {
            long offset = offset(typeAndOffsetAt(this.intArray[i]));
            Object mapField = UnsafeUtil.getObject((Object) message, offset);
            if (mapField != null) {
                UnsafeUtil.putObject((Object) message, offset, this.mapFieldSchema.toImmutable(mapField));
            }
        }
        int length = this.intArray.length;
        for (int i2 = this.repeatedFieldOffsetStart; i2 < length; i2++) {
            this.listFieldSchema.makeImmutableListAt(message, (long) this.intArray[i2]);
        }
        this.unknownFieldSchema.makeImmutable(message);
        if (this.hasExtensions) {
            this.extensionSchema.makeImmutable(message);
        }
    }

    private final <K, V> void mergeMap(Object message, int pos, Object mapDefaultEntry, ExtensionRegistryLite extensionRegistry, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(pos));
        Object mapField = UnsafeUtil.getObject(message, offset);
        if (mapField == null) {
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            UnsafeUtil.putObject(message, offset, mapField);
        } else if (this.mapFieldSchema.isImmutable(mapField)) {
            Object oldMapField = mapField;
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField, oldMapField);
            UnsafeUtil.putObject(message, offset, mapField);
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(mapField), this.mapFieldSchema.forMapMetadata(mapDefaultEntry), extensionRegistry);
    }

    private final <UT, UB> UB filterMapUnknownEnumValues(Object message, int pos, UB unknownFields, UnknownFieldSchema<UT, UB> unknownFieldSchema2) {
        Internal.EnumVerifier enumVerifier;
        int i = pos;
        int fieldNumber = numberAt(i);
        Object mapField = UnsafeUtil.getObject(message, offset(typeAndOffsetAt(i)));
        if (mapField == null || (enumVerifier = getEnumFieldVerifier(i)) == null) {
            return unknownFields;
        }
        return filterUnknownEnumMap(pos, fieldNumber, this.mapFieldSchema.forMutableMapData(mapField), enumVerifier, unknownFields, unknownFieldSchema2);
    }

    private final <K, V, UT, UB> UB filterUnknownEnumMap(int pos, int number, Map<K, V> mapData, Internal.EnumVerifier enumVerifier, UB unknownFields, UnknownFieldSchema<UT, UB> unknownFieldSchema2) {
        MapEntryLite.Metadata<?, ?> forMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos));
        Iterator<Map.Entry<K, V>> it = mapData.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> entry = it.next();
            if (!enumVerifier.isInRange(((Integer) entry.getValue()).intValue())) {
                if (unknownFields == null) {
                    unknownFields = unknownFieldSchema2.newBuilder();
                }
                ByteString.CodedBuilder codedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(forMapMetadata, entry.getKey(), entry.getValue()));
                try {
                    MapEntryLite.writeTo(codedBuilder.getCodedOutput(), forMapMetadata, entry.getKey(), entry.getValue());
                    unknownFieldSchema2.addLengthDelimited(unknownFields, number, codedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return unknownFields;
    }

    public final boolean isInitialized(T message) {
        int currentPresenceField;
        int currentPresenceFieldOffset;
        T t = message;
        int currentPresenceFieldOffset2 = 1048575;
        int currentPresenceField2 = 0;
        int i = 0;
        while (i < this.checkInitializedCount) {
            int pos = this.intArray[i];
            int number = numberAt(pos);
            int typeAndOffset = typeAndOffsetAt(pos);
            int presenceMaskAndOffset = this.buffer[pos + 2];
            int presenceFieldOffset = presenceMaskAndOffset & 1048575;
            int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
            if (presenceFieldOffset != currentPresenceFieldOffset2) {
                int currentPresenceFieldOffset3 = presenceFieldOffset;
                if (currentPresenceFieldOffset3 != 1048575) {
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    currentPresenceField = UNSAFE.getInt(t, (long) presenceFieldOffset);
                } else {
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    currentPresenceField = currentPresenceField2;
                }
            } else {
                currentPresenceFieldOffset = currentPresenceFieldOffset2;
                currentPresenceField = currentPresenceField2;
            }
            if (isRequired(typeAndOffset) != 0 && !isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask)) {
                return false;
            }
            switch (type(typeAndOffset)) {
                case 9:
                case 17:
                    if (isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask) && !isInitialized(t, typeAndOffset, getMessageFieldSchema(pos))) {
                        return false;
                    }
                case 27:
                case 49:
                    if (isListInitialized(t, typeAndOffset, pos)) {
                        break;
                    } else {
                        return false;
                    }
                case 50:
                    if (isMapInitialized(t, typeAndOffset, pos)) {
                        break;
                    } else {
                        return false;
                    }
                case 60:
                case 68:
                    if (isOneofPresent(t, number, pos) && !isInitialized(t, typeAndOffset, getMessageFieldSchema(pos))) {
                        return false;
                    }
            }
            i++;
            currentPresenceFieldOffset2 = currentPresenceFieldOffset;
            currentPresenceField2 = currentPresenceField;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(t).isInitialized();
    }

    private static boolean isInitialized(Object message, int typeAndOffset, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(message, offset(typeAndOffset)));
    }

    private <N> boolean isListInitialized(Object message, int typeAndOffset, int pos) {
        List<N> list = (List) UnsafeUtil.getObject(message, offset(typeAndOffset));
        if (list.isEmpty()) {
            return true;
        }
        Schema schema = getMessageFieldSchema(pos);
        for (int i = 0; i < list.size(); i++) {
            if (!schema.isInitialized(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isMapInitialized(T message, int typeAndOffset, int pos) {
        Map<?, ?> map = this.mapFieldSchema.forMapData(UnsafeUtil.getObject((Object) message, offset(typeAndOffset)));
        if (map.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        Schema schema = null;
        for (Object nested : map.values()) {
            if (schema == null) {
                schema = Protobuf.getInstance().schemaFor(nested.getClass());
            }
            if (!schema.isInitialized(nested)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writer.writeString(fieldNumber, (String) value);
        } else {
            writer.writeBytes(fieldNumber, (ByteString) value);
        }
    }

    private void readString(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), (Object) reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), (Object) reader.readString());
        } else {
            UnsafeUtil.putObject(message, offset(typeAndOffset), (Object) reader.readBytes());
        }
    }

    private void readStringList(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        }
    }

    private <E> void readMessageList(Object message, int typeAndOffset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readMessageList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)), schema, extensionRegistry);
    }

    private <E> void readGroupList(Object message, long offset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private int numberAt(int pos) {
        return this.buffer[pos];
    }

    private int typeAndOffsetAt(int pos) {
        return this.buffer[pos + 1];
    }

    private int presenceMaskAndOffsetAt(int pos) {
        return this.buffer[pos + 2];
    }

    private static int type(int value) {
        return (FIELD_TYPE_MASK & value) >>> 20;
    }

    private static boolean isRequired(int value) {
        return (REQUIRED_MASK & value) != 0;
    }

    private static boolean isEnforceUtf8(int value) {
        return (ENFORCE_UTF8_MASK & value) != 0;
    }

    private static long offset(int value) {
        return (long) (1048575 & value);
    }

    private static <T> double doubleAt(T message, long offset) {
        return UnsafeUtil.getDouble((Object) message, offset);
    }

    private static <T> float floatAt(T message, long offset) {
        return UnsafeUtil.getFloat((Object) message, offset);
    }

    private static <T> int intAt(T message, long offset) {
        return UnsafeUtil.getInt((Object) message, offset);
    }

    private static <T> long longAt(T message, long offset) {
        return UnsafeUtil.getLong((Object) message, offset);
    }

    private static <T> boolean booleanAt(T message, long offset) {
        return UnsafeUtil.getBoolean((Object) message, offset);
    }

    private static <T> double oneofDoubleAt(T message, long offset) {
        return ((Double) UnsafeUtil.getObject((Object) message, offset)).doubleValue();
    }

    private static <T> float oneofFloatAt(T message, long offset) {
        return ((Float) UnsafeUtil.getObject((Object) message, offset)).floatValue();
    }

    private static <T> int oneofIntAt(T message, long offset) {
        return ((Integer) UnsafeUtil.getObject((Object) message, offset)).intValue();
    }

    private static <T> long oneofLongAt(T message, long offset) {
        return ((Long) UnsafeUtil.getObject((Object) message, offset)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T message, long offset) {
        return ((Boolean) UnsafeUtil.getObject((Object) message, offset)).booleanValue();
    }

    private boolean arePresentForEquals(T message, T other, int pos) {
        return isFieldPresent(message, pos) == isFieldPresent(other, pos);
    }

    private boolean isFieldPresent(T message, int pos, int presenceFieldOffset, int presenceField, int presenceMask) {
        if (presenceFieldOffset == 1048575) {
            return isFieldPresent(message, pos);
        }
        return (presenceField & presenceMask) != 0;
    }

    private boolean isFieldPresent(T message, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        if (((long) (presenceMaskAndOffset & 1048575)) == 1048575) {
            int typeAndOffset = typeAndOffsetAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    if (Double.doubleToRawLongBits(UnsafeUtil.getDouble((Object) message, offset)) != 0) {
                        return true;
                    }
                    return false;
                case 1:
                    if (Float.floatToRawIntBits(UnsafeUtil.getFloat((Object) message, offset)) != 0) {
                        return true;
                    }
                    return false;
                case 2:
                    if (UnsafeUtil.getLong((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 3:
                    if (UnsafeUtil.getLong((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 4:
                    if (UnsafeUtil.getInt((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 5:
                    if (UnsafeUtil.getLong((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 6:
                    if (UnsafeUtil.getInt((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 7:
                    return UnsafeUtil.getBoolean((Object) message, offset);
                case 8:
                    Object value = UnsafeUtil.getObject((Object) message, offset);
                    if (value instanceof String) {
                        return !((String) value).isEmpty();
                    }
                    if (value instanceof ByteString) {
                        return !ByteString.EMPTY.equals(value);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    if (UnsafeUtil.getObject((Object) message, offset) != null) {
                        return true;
                    }
                    return false;
                case 10:
                    return !ByteString.EMPTY.equals(UnsafeUtil.getObject((Object) message, offset));
                case 11:
                    if (UnsafeUtil.getInt((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 12:
                    if (UnsafeUtil.getInt((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 13:
                    if (UnsafeUtil.getInt((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 14:
                    if (UnsafeUtil.getLong((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 15:
                    if (UnsafeUtil.getInt((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 16:
                    if (UnsafeUtil.getLong((Object) message, offset) != 0) {
                        return true;
                    }
                    return false;
                case 17:
                    if (UnsafeUtil.getObject((Object) message, offset) != null) {
                        return true;
                    }
                    return false;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            if ((UnsafeUtil.getInt((Object) message, (long) (1048575 & presenceMaskAndOffset)) & (1 << (presenceMaskAndOffset >>> 20))) != 0) {
                return true;
            }
            return false;
        }
    }

    private void setFieldPresent(T message, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        long presenceFieldOffset = (long) (1048575 & presenceMaskAndOffset);
        if (presenceFieldOffset != 1048575) {
            UnsafeUtil.putInt((Object) message, presenceFieldOffset, UnsafeUtil.getInt((Object) message, presenceFieldOffset) | (1 << (presenceMaskAndOffset >>> 20)));
        }
    }

    private boolean isOneofPresent(T message, int fieldNumber, int pos) {
        return UnsafeUtil.getInt((Object) message, (long) (1048575 & presenceMaskAndOffsetAt(pos))) == fieldNumber;
    }

    private boolean isOneofCaseEqual(T message, T other, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt((Object) message, (long) (presenceMaskAndOffset & 1048575)) == UnsafeUtil.getInt((Object) other, (long) (1048575 & presenceMaskAndOffset));
    }

    private void setOneofPresent(T message, int fieldNumber, int pos) {
        UnsafeUtil.putInt((Object) message, (long) (1048575 & presenceMaskAndOffsetAt(pos)), fieldNumber);
    }

    private int positionForFieldNumber(int number) {
        if (number < this.minFieldNumber || number > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(number, 0);
    }

    private int positionForFieldNumber(int number, int min) {
        if (number < this.minFieldNumber || number > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(number, min);
    }

    private int slowPositionForFieldNumber(int number, int min) {
        int max = (this.buffer.length / 3) - 1;
        while (min <= max) {
            int mid = (max + min) >>> 1;
            int pos = mid * 3;
            int midFieldNumber = numberAt(pos);
            if (number == midFieldNumber) {
                return pos;
            }
            if (number < midFieldNumber) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getSchemaSize() {
        return this.buffer.length * 3;
    }
}
