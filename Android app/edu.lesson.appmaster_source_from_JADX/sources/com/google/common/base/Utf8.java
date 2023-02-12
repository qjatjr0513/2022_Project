package com.google.common.base;

public final class Utf8 {
    public static int encodedLength(CharSequence sequence) {
        int utf16Length = sequence.length();
        int utf8Length = utf16Length;
        int i = 0;
        while (i < utf16Length && sequence.charAt(i) < 128) {
            i++;
        }
        while (true) {
            if (i < utf16Length) {
                char c = sequence.charAt(i);
                if (c >= 2048) {
                    utf8Length += encodedLengthGeneral(sequence, i);
                    break;
                }
                utf8Length += (127 - c) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (utf8Length >= utf16Length) {
            return utf8Length;
        }
        throw new IllegalArgumentException(new StringBuilder(54).append("UTF-8 length does not fit in int: ").append(((long) utf8Length) + 4294967296L).toString());
    }

    private static int encodedLengthGeneral(CharSequence sequence, int start) {
        int utf16Length = sequence.length();
        int utf8Length = 0;
        int i = start;
        while (i < utf16Length) {
            char c = sequence.charAt(i);
            if (c < 2048) {
                utf8Length += (127 - c) >>> 31;
            } else {
                utf8Length += 2;
                if (55296 <= c && c <= 57343) {
                    if (Character.codePointAt(sequence, i) != c) {
                        i++;
                    } else {
                        throw new IllegalArgumentException(unpairedSurrogateMsg(i));
                    }
                }
            }
            i++;
        }
        return utf8Length;
    }

    public static boolean isWellFormed(byte[] bytes) {
        return isWellFormed(bytes, 0, bytes.length);
    }

    public static boolean isWellFormed(byte[] bytes, int off, int len) {
        int end = off + len;
        Preconditions.checkPositionIndexes(off, end, bytes.length);
        for (int i = off; i < end; i++) {
            if (bytes[i] < 0) {
                return isWellFormedSlowPath(bytes, i, end);
            }
        }
        return true;
    }

    private static boolean isWellFormedSlowPath(byte[] bytes, int off, int end) {
        int index = off;
        while (index < end) {
            int index2 = index + 1;
            byte index3 = bytes[index];
            int byte1 = index3;
            if (index3 >= 0) {
                index = index2;
            } else if (byte1 < -32) {
                if (index2 != end && byte1 >= -62) {
                    index = index2 + 1;
                    if (bytes[index2] > -65) {
                        int i = index;
                    }
                }
                return false;
            } else if (byte1 < -16) {
                if (index2 + 1 >= end) {
                    return false;
                }
                int index4 = index2 + 1;
                byte index5 = bytes[index2];
                if (index5 <= -65 && ((byte1 != -32 || index5 >= -96) && (byte1 != -19 || -96 > index5))) {
                    index = index4 + 1;
                    if (bytes[index4] > -65) {
                        int i2 = index;
                    }
                }
                return false;
            } else if (index2 + 2 >= end) {
                return false;
            } else {
                int index6 = index2 + 1;
                byte index7 = bytes[index2];
                if (index7 <= -65 && (((byte1 << 28) + (index7 + 112)) >> 30) == 0) {
                    int index8 = index6 + 1;
                    if (bytes[index6] <= -65) {
                        index = index8 + 1;
                        if (bytes[index8] > -65) {
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    private static String unpairedSurrogateMsg(int i) {
        return new StringBuilder(39).append("Unpaired surrogate at index ").append(i).toString();
    }

    private Utf8() {
    }
}
