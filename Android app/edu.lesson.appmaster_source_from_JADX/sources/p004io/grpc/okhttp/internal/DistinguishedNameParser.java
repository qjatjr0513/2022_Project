package p004io.grpc.okhttp.internal;

import javax.security.auth.x500.X500Principal;

/* renamed from: io.grpc.okhttp.internal.DistinguishedNameParser */
final class DistinguishedNameParser {
    private int beg;
    private char[] chars;
    private int cur;

    /* renamed from: dn */
    private final String f317dn;
    private int end;
    private final int length;
    private int pos;

    public DistinguishedNameParser(X500Principal principal) {
        String name = principal.getName("RFC2253");
        this.f317dn = name;
        this.length = name.length();
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0015 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String nextAT() {
        /*
            r7 = this;
        L_0x0000:
            int r0 = r7.pos
            int r1 = r7.length
            r2 = 32
            if (r0 >= r1) goto L_0x0013
            char[] r3 = r7.chars
            char r3 = r3[r0]
            if (r3 != r2) goto L_0x0013
            int r0 = r0 + 1
            r7.pos = r0
            goto L_0x0000
        L_0x0013:
            if (r0 != r1) goto L_0x0017
            r0 = 0
            return r0
        L_0x0017:
            r7.beg = r0
            int r0 = r0 + 1
            r7.pos = r0
        L_0x001d:
            int r0 = r7.pos
            int r1 = r7.length
            r3 = 61
            if (r0 >= r1) goto L_0x0034
            char[] r4 = r7.chars
            char r5 = r4[r0]
            if (r5 == r3) goto L_0x0034
            char r4 = r4[r0]
            if (r4 == r2) goto L_0x0034
            int r0 = r0 + 1
            r7.pos = r0
            goto L_0x001d
        L_0x0034:
            java.lang.String r4 = "Unexpected end of DN: "
            if (r0 >= r1) goto L_0x00dc
            r7.end = r0
            char[] r1 = r7.chars
            char r0 = r1[r0]
            if (r0 != r2) goto L_0x0077
        L_0x0040:
            int r0 = r7.pos
            int r1 = r7.length
            if (r0 >= r1) goto L_0x0055
            char[] r5 = r7.chars
            char r6 = r5[r0]
            if (r6 == r3) goto L_0x0055
            char r5 = r5[r0]
            if (r5 != r2) goto L_0x0055
            int r0 = r0 + 1
            r7.pos = r0
            goto L_0x0040
        L_0x0055:
            char[] r5 = r7.chars
            char r5 = r5[r0]
            if (r5 != r3) goto L_0x005e
            if (r0 == r1) goto L_0x005e
            goto L_0x0077
        L_0x005e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r2 = r7.f317dn
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0077:
            int r0 = r7.pos
            int r0 = r0 + 1
            r7.pos = r0
        L_0x007d:
            int r0 = r7.pos
            int r1 = r7.length
            if (r0 >= r1) goto L_0x008e
            char[] r1 = r7.chars
            char r1 = r1[r0]
            if (r1 != r2) goto L_0x008e
            int r0 = r0 + 1
            r7.pos = r0
            goto L_0x007d
        L_0x008e:
            int r0 = r7.end
            int r1 = r7.beg
            int r0 = r0 - r1
            r2 = 4
            if (r0 <= r2) goto L_0x00cf
            char[] r0 = r7.chars
            int r3 = r1 + 3
            char r3 = r0[r3]
            r4 = 46
            if (r3 != r4) goto L_0x00cf
            char r3 = r0[r1]
            r4 = 79
            if (r3 == r4) goto L_0x00ac
            char r3 = r0[r1]
            r4 = 111(0x6f, float:1.56E-43)
            if (r3 != r4) goto L_0x00cf
        L_0x00ac:
            int r3 = r1 + 1
            char r3 = r0[r3]
            r4 = 73
            if (r3 == r4) goto L_0x00bc
            int r3 = r1 + 1
            char r3 = r0[r3]
            r4 = 105(0x69, float:1.47E-43)
            if (r3 != r4) goto L_0x00cf
        L_0x00bc:
            int r3 = r1 + 2
            char r3 = r0[r3]
            r4 = 68
            if (r3 == r4) goto L_0x00cc
            int r3 = r1 + 2
            char r0 = r0[r3]
            r3 = 100
            if (r0 != r3) goto L_0x00cf
        L_0x00cc:
            int r1 = r1 + r2
            r7.beg = r1
        L_0x00cf:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r7.chars
            int r2 = r7.beg
            int r3 = r7.end
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L_0x00dc:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r2 = r7.f317dn
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.okhttp.internal.DistinguishedNameParser.nextAT():java.lang.String");
    }

    private String quotedAV() {
        int i = this.pos + 1;
        this.pos = i;
        this.beg = i;
        this.end = i;
        while (true) {
            int i2 = this.pos;
            if (i2 != this.length) {
                char[] cArr = this.chars;
                if (cArr[i2] == '\"') {
                    this.pos = i2 + 1;
                    while (true) {
                        int i3 = this.pos;
                        if (i3 >= this.length || this.chars[i3] != ' ') {
                            char[] cArr2 = this.chars;
                            int i4 = this.beg;
                        } else {
                            this.pos = i3 + 1;
                        }
                    }
                    char[] cArr22 = this.chars;
                    int i42 = this.beg;
                    return new String(cArr22, i42, this.end - i42);
                }
                if (cArr[i2] == '\\') {
                    cArr[this.end] = getEscaped();
                } else {
                    cArr[this.end] = cArr[i2];
                }
                this.pos++;
                this.end++;
            } else {
                throw new IllegalStateException("Unexpected end of DN: " + this.f317dn);
            }
        }
    }

    private String hexAV() {
        int i;
        int i2 = this.pos;
        if (i2 + 4 < this.length) {
            this.beg = i2;
            this.pos = i2 + 1;
            while (true) {
                i = this.pos;
                if (i == this.length) {
                    break;
                }
                char[] cArr = this.chars;
                if (cArr[i] == '+' || cArr[i] == ',' || cArr[i] == ';') {
                    break;
                } else if (cArr[i] == ' ') {
                    this.end = i;
                    this.pos = i + 1;
                    while (true) {
                        int i3 = this.pos;
                        if (i3 >= this.length || this.chars[i3] != ' ') {
                            break;
                        }
                        this.pos = i3 + 1;
                    }
                } else {
                    if (cArr[i] >= 'A' && cArr[i] <= 'F') {
                        cArr[i] = (char) (cArr[i] + ' ');
                    }
                    this.pos = i + 1;
                }
            }
            this.end = i;
            int i4 = this.end;
            int i5 = this.beg;
            int hexLen = i4 - i5;
            if (hexLen < 5 || (hexLen & 1) == 0) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f317dn);
            }
            byte[] encoded = new byte[(hexLen / 2)];
            int p = i5 + 1;
            for (int i6 = 0; i6 < encoded.length; i6++) {
                encoded[i6] = (byte) getByte(p);
                p += 2;
            }
            return new String(this.chars, this.beg, hexLen);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.f317dn);
    }

    private String escapedAV() {
        int i;
        int i2;
        int i3 = this.pos;
        this.beg = i3;
        this.end = i3;
        while (true) {
            int i4 = this.pos;
            if (i4 < this.length) {
                char[] cArr = this.chars;
                switch (cArr[i4]) {
                    case ' ':
                        int i5 = this.end;
                        this.cur = i5;
                        this.pos = i4 + 1;
                        this.end = i5 + 1;
                        cArr[i5] = ' ';
                        while (true) {
                            i = this.pos;
                            i2 = this.length;
                            if (i < i2) {
                                char[] cArr2 = this.chars;
                                if (cArr2[i] == ' ') {
                                    int i6 = this.end;
                                    this.end = i6 + 1;
                                    cArr2[i6] = ' ';
                                    this.pos = i + 1;
                                }
                            }
                        }
                        if (i == i2) {
                            break;
                        } else {
                            char[] cArr3 = this.chars;
                            if (!(cArr3[i] == ',' || cArr3[i] == '+' || cArr3[i] == ';')) {
                                break;
                            }
                        }
                    case '+':
                    case ',':
                    case ';':
                        char[] cArr4 = this.chars;
                        int i7 = this.beg;
                        return new String(cArr4, i7, this.end - i7);
                    case '\\':
                        int i8 = this.end;
                        this.end = i8 + 1;
                        cArr[i8] = getEscaped();
                        this.pos++;
                        break;
                    default:
                        int i9 = this.end;
                        this.end = i9 + 1;
                        cArr[i9] = cArr[i4];
                        this.pos = i4 + 1;
                        break;
                }
            } else {
                char[] cArr5 = this.chars;
                int i10 = this.beg;
                return new String(cArr5, i10, this.end - i10);
            }
        }
        char[] cArr6 = this.chars;
        int i11 = this.beg;
        return new String(cArr6, i11, this.cur - i11);
    }

    private char getEscaped() {
        int i = this.pos + 1;
        this.pos = i;
        if (i != this.length) {
            char[] cArr = this.chars;
            switch (cArr[i]) {
                case ' ':
                case '\"':
                case '#':
                case '%':
                case '*':
                case '+':
                case ',':
                case ';':
                case '<':
                case '=':
                case '>':
                case '\\':
                case '_':
                    return cArr[i];
                default:
                    return getUTF8();
            }
        } else {
            throw new IllegalStateException("Unexpected end of DN: " + this.f317dn);
        }
    }

    private char getUTF8() {
        int count;
        int res;
        int res2 = getByte(this.pos);
        this.pos++;
        if (res2 < 128) {
            return (char) res2;
        }
        if (res2 < 192 || res2 > 247) {
            return '?';
        }
        if (res2 <= 223) {
            count = 1;
            res = res2 & 31;
        } else if (res2 <= 239) {
            count = 2;
            res = res2 & 15;
        } else {
            count = 3;
            res = res2 & 7;
        }
        for (int i = 0; i < count; i++) {
            int i2 = this.pos + 1;
            this.pos = i2;
            if (i2 == this.length || this.chars[i2] != '\\') {
                return '?';
            }
            int i3 = i2 + 1;
            this.pos = i3;
            int b = getByte(i3);
            this.pos++;
            if ((b & 192) != 128) {
                return '?';
            }
            res = (res << 6) + (b & 63);
        }
        return (char) res;
    }

    private int getByte(int position) {
        int b1;
        int b2;
        if (position + 1 < this.length) {
            char[] cArr = this.chars;
            char b12 = cArr[position];
            if (b12 >= '0' && b12 <= '9') {
                b1 = b12 - '0';
            } else if (b12 >= 'a' && b12 <= 'f') {
                b1 = b12 - 'W';
            } else if (b12 < 'A' || b12 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f317dn);
            } else {
                b1 = b12 - '7';
            }
            char b22 = cArr[position + 1];
            if (b22 >= '0' && b22 <= '9') {
                b2 = b22 - '0';
            } else if (b22 >= 'a' && b22 <= 'f') {
                b2 = b22 - 'W';
            } else if (b22 < 'A' || b22 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f317dn);
            } else {
                b2 = b22 - '7';
            }
            return (b1 << 4) + b2;
        }
        throw new IllegalStateException("Malformed DN: " + this.f317dn);
    }

    public String findMostSpecific(String attributeType) {
        this.pos = 0;
        this.beg = 0;
        this.end = 0;
        this.cur = 0;
        this.chars = this.f317dn.toCharArray();
        String attType = nextAT();
        if (attType == null) {
            return null;
        }
        do {
            String attValue = "";
            int i = this.pos;
            if (i == this.length) {
                return null;
            }
            switch (this.chars[i]) {
                case '\"':
                    attValue = quotedAV();
                    break;
                case '#':
                    attValue = hexAV();
                    break;
                case '+':
                case ',':
                case ';':
                    break;
                default:
                    attValue = escapedAV();
                    break;
            }
            if (attributeType.equalsIgnoreCase(attType)) {
                return attValue;
            }
            int i2 = this.pos;
            if (i2 >= this.length) {
                return null;
            }
            char[] cArr = this.chars;
            if (cArr[i2] == ',' || cArr[i2] == ';' || cArr[i2] == '+') {
                this.pos = i2 + 1;
                attType = nextAT();
            } else {
                throw new IllegalStateException("Malformed DN: " + this.f317dn);
            }
        } while (attType != null);
        throw new IllegalStateException("Malformed DN: " + this.f317dn);
    }
}
