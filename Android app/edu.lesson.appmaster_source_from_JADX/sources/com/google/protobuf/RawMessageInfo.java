package com.google.protobuf;

final class RawMessageInfo implements MessageInfo {
    private final MessageLite defaultInstance;
    private final int flags;
    private final String info;
    private final Object[] objects;

    RawMessageInfo(MessageLite defaultInstance2, String info2, Object[] objects2) {
        this.defaultInstance = defaultInstance2;
        this.info = info2;
        this.objects = objects2;
        int position = 0 + 1;
        int value = info2.charAt(0);
        if (value < 55296) {
            this.flags = value;
            return;
        }
        int result = value & 8191;
        int shift = 13;
        while (true) {
            int position2 = position + 1;
            int position3 = info2.charAt(position);
            int value2 = position3;
            if (position3 >= 55296) {
                result |= (value2 & 8191) << shift;
                shift += 13;
                position = position2;
            } else {
                this.flags = (value2 << shift) | result;
                int i = position2;
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String getStringInfo() {
        return this.info;
    }

    /* access modifiers changed from: package-private */
    public Object[] getObjects() {
        return this.objects;
    }

    public MessageLite getDefaultInstance() {
        return this.defaultInstance;
    }

    public ProtoSyntax getSyntax() {
        return (this.flags & 1) == 1 ? ProtoSyntax.PROTO2 : ProtoSyntax.PROTO3;
    }

    public boolean isMessageSetWireFormat() {
        return (this.flags & 2) == 2;
    }
}
