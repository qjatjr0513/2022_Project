package com.google.firebase.messaging;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public final class SendException extends Exception {
    public static final int ERROR_INVALID_PARAMETERS = 1;
    public static final int ERROR_SIZE = 2;
    public static final int ERROR_TOO_MANY_MESSAGES = 4;
    public static final int ERROR_TTL_EXCEEDED = 3;
    public static final int ERROR_UNKNOWN = 0;
    private final int errorCode;

    SendException(String str) {
        super(str);
        this.errorCode = parseErrorCode(str);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseErrorCode(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.Locale r1 = java.util.Locale.US
            java.lang.String r7 = r7.toLowerCase(r1)
            int r1 = r7.hashCode()
            r2 = 1
            r3 = 2
            r4 = 4
            r5 = 3
            switch(r1) {
                case -1743242157: goto L_0x003e;
                case -1290953729: goto L_0x0034;
                case -920906446: goto L_0x002a;
                case -617027085: goto L_0x0020;
                case -95047692: goto L_0x0016;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x0048
        L_0x0016:
            java.lang.String r1 = "missing_to"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0015
            r7 = r2
            goto L_0x0049
        L_0x0020:
            java.lang.String r1 = "messagetoobig"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0015
            r7 = r3
            goto L_0x0049
        L_0x002a:
            java.lang.String r1 = "invalid_parameters"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0015
            r7 = r0
            goto L_0x0049
        L_0x0034:
            java.lang.String r1 = "toomanymessages"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0015
            r7 = r4
            goto L_0x0049
        L_0x003e:
            java.lang.String r1 = "service_not_available"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0015
            r7 = r5
            goto L_0x0049
        L_0x0048:
            r7 = -1
        L_0x0049:
            switch(r7) {
                case 0: goto L_0x0050;
                case 1: goto L_0x0050;
                case 2: goto L_0x004f;
                case 3: goto L_0x004e;
                case 4: goto L_0x004d;
                default: goto L_0x004c;
            }
        L_0x004c:
            return r0
        L_0x004d:
            return r4
        L_0x004e:
            return r5
        L_0x004f:
            return r3
        L_0x0050:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.SendException.parseErrorCode(java.lang.String):int");
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
