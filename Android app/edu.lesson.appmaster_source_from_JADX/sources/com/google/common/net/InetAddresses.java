package com.google.common.net;

import com.google.common.base.CharMatcher;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.google.common.p000io.ByteStreams;
import com.google.common.primitives.Ints;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class InetAddresses {
    /* access modifiers changed from: private */
    public static final Inet4Address ANY4 = ((Inet4Address) forString("0.0.0.0"));
    private static final char IPV4_DELIMITER = '.';
    private static final CharMatcher IPV4_DELIMITER_MATCHER = CharMatcher.m4is(IPV4_DELIMITER);
    private static final int IPV4_PART_COUNT = 4;
    private static final char IPV6_DELIMITER = ':';
    private static final CharMatcher IPV6_DELIMITER_MATCHER = CharMatcher.m4is(IPV6_DELIMITER);
    private static final int IPV6_PART_COUNT = 8;
    private static final Inet4Address LOOPBACK4 = ((Inet4Address) forString("127.0.0.1"));

    private InetAddresses() {
    }

    private static Inet4Address getInet4Address(byte[] bytes) {
        Preconditions.checkArgument(bytes.length == 4, "Byte array has invalid length for an IPv4 address: %s != 4.", bytes.length);
        return (Inet4Address) bytesToInetAddress(bytes);
    }

    public static InetAddress forString(String ipString) {
        byte[] addr = ipStringToBytes(ipString);
        if (addr != null) {
            return bytesToInetAddress(addr);
        }
        throw formatIllegalArgumentException("'%s' is not an IP string literal.", ipString);
    }

    public static boolean isInetAddress(String ipString) {
        return ipStringToBytes(ipString) != null;
    }

    @NullableDecl
    private static byte[] ipStringToBytes(String ipString) {
        boolean hasColon = false;
        boolean hasDot = false;
        int percentIndex = -1;
        int i = 0;
        while (true) {
            if (i >= ipString.length()) {
                break;
            }
            char c = ipString.charAt(i);
            if (c == '.') {
                hasDot = true;
            } else if (c == ':') {
                if (hasDot) {
                    return null;
                }
                hasColon = true;
            } else if (c == '%') {
                percentIndex = i;
                break;
            } else if (Character.digit(c, 16) == -1) {
                return null;
            }
            i++;
        }
        if (hasColon) {
            if (hasDot && (ipString = convertDottedQuadToHex(ipString)) == null) {
                return null;
            }
            if (percentIndex != -1) {
                ipString = ipString.substring(0, percentIndex);
            }
            return textToNumericFormatV6(ipString);
        } else if (!hasDot || percentIndex != -1) {
            return null;
        } else {
            return textToNumericFormatV4(ipString);
        }
    }

    @NullableDecl
    private static byte[] textToNumericFormatV4(String ipString) {
        if (IPV4_DELIMITER_MATCHER.countIn(ipString) + 1 != 4) {
            return null;
        }
        byte[] bytes = new byte[4];
        int start = 0;
        int i = 0;
        while (i < 4) {
            int end = ipString.indexOf(46, start);
            if (end == -1) {
                end = ipString.length();
            }
            try {
                bytes[i] = parseOctet(ipString, start, end);
                start = end + 1;
                i++;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return bytes;
    }

    @NullableDecl
    private static byte[] textToNumericFormatV6(String ipString) {
        int delimiterCount = IPV6_DELIMITER_MATCHER.countIn(ipString);
        if (delimiterCount < 2 || delimiterCount > 8) {
            return null;
        }
        int partsSkipped = 8 - (delimiterCount + 1);
        boolean hasSkip = false;
        for (int i = 0; i < ipString.length() - 1; i++) {
            if (ipString.charAt(i) == ':' && ipString.charAt(i + 1) == ':') {
                if (hasSkip) {
                    return null;
                }
                hasSkip = true;
                partsSkipped++;
                if (i == 0) {
                    partsSkipped++;
                }
                if (i == ipString.length() - 2) {
                    partsSkipped++;
                }
            }
        }
        if (ipString.charAt(0) == ':' && ipString.charAt(1) != ':') {
            return null;
        }
        if (ipString.charAt(ipString.length() - 1) == ':' && ipString.charAt(ipString.length() - 2) != ':') {
            return null;
        }
        if (hasSkip && partsSkipped <= 0) {
            return null;
        }
        if (!hasSkip && delimiterCount + 1 != 8) {
            return null;
        }
        ByteBuffer rawBytes = ByteBuffer.allocate(16);
        int start = 0;
        try {
            if (ipString.charAt(0) == ':') {
                start = 1;
            }
            while (start < ipString.length()) {
                int end = ipString.indexOf(58, start);
                if (end == -1) {
                    end = ipString.length();
                }
                if (ipString.charAt(start) == ':') {
                    for (int i2 = 0; i2 < partsSkipped; i2++) {
                        rawBytes.putShort(0);
                    }
                } else {
                    rawBytes.putShort(parseHextet(ipString, start, end));
                }
                start = end + 1;
            }
            return rawBytes.array();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @NullableDecl
    private static String convertDottedQuadToHex(String ipString) {
        int lastColon = ipString.lastIndexOf(58);
        String initialPart = ipString.substring(0, lastColon + 1);
        byte[] quad = textToNumericFormatV4(ipString.substring(lastColon + 1));
        if (quad == null) {
            return null;
        }
        String penultimate = Integer.toHexString(((quad[0] & 255) << 8) | (quad[1] & 255));
        String ultimate = Integer.toHexString(((quad[2] & 255) << 8) | (quad[3] & 255));
        return new StringBuilder(String.valueOf(initialPart).length() + 1 + String.valueOf(penultimate).length() + String.valueOf(ultimate).length()).append(initialPart).append(penultimate).append(":").append(ultimate).toString();
    }

    private static byte parseOctet(String ipString, int start, int end) {
        int length = end - start;
        if (length <= 0 || length > 3) {
            throw new NumberFormatException();
        } else if (length <= 1 || ipString.charAt(start) != '0') {
            int octet = 0;
            int i = start;
            while (i < end) {
                int octet2 = octet * 10;
                int digit = Character.digit(ipString.charAt(i), 10);
                if (digit >= 0) {
                    octet = octet2 + digit;
                    i++;
                } else {
                    throw new NumberFormatException();
                }
            }
            if (octet <= 255) {
                return (byte) octet;
            }
            throw new NumberFormatException();
        } else {
            throw new NumberFormatException();
        }
    }

    private static short parseHextet(String ipString, int start, int end) {
        int length = end - start;
        if (length <= 0 || length > 4) {
            throw new NumberFormatException();
        }
        int hextet = 0;
        for (int i = start; i < end; i++) {
            hextet = (hextet << 4) | Character.digit(ipString.charAt(i), 16);
        }
        return (short) hextet;
    }

    private static InetAddress bytesToInetAddress(byte[] addr) {
        try {
            return InetAddress.getByAddress(addr);
        } catch (UnknownHostException e) {
            throw new AssertionError(e);
        }
    }

    public static String toAddrString(InetAddress ip) {
        Preconditions.checkNotNull(ip);
        if (ip instanceof Inet4Address) {
            return ip.getHostAddress();
        }
        Preconditions.checkArgument(ip instanceof Inet6Address);
        byte[] bytes = ip.getAddress();
        int[] hextets = new int[8];
        for (int i = 0; i < hextets.length; i++) {
            hextets[i] = Ints.fromBytes((byte) 0, (byte) 0, bytes[i * 2], bytes[(i * 2) + 1]);
        }
        compressLongestRunOfZeroes(hextets);
        return hextetsToIPv6String(hextets);
    }

    private static void compressLongestRunOfZeroes(int[] hextets) {
        int bestRunStart = -1;
        int bestRunLength = -1;
        int runStart = -1;
        for (int i = 0; i < hextets.length + 1; i++) {
            if (i >= hextets.length || hextets[i] != 0) {
                if (runStart >= 0) {
                    int runLength = i - runStart;
                    if (runLength > bestRunLength) {
                        bestRunStart = runStart;
                        bestRunLength = runLength;
                    }
                    runStart = -1;
                }
            } else if (runStart < 0) {
                runStart = i;
            }
        }
        if (bestRunLength >= 2) {
            Arrays.fill(hextets, bestRunStart, bestRunStart + bestRunLength, -1);
        }
    }

    private static String hextetsToIPv6String(int[] hextets) {
        StringBuilder buf = new StringBuilder(39);
        boolean lastWasNumber = false;
        for (int i = 0; i < hextets.length; i++) {
            boolean thisIsNumber = hextets[i] >= 0;
            if (thisIsNumber) {
                if (lastWasNumber) {
                    buf.append(IPV6_DELIMITER);
                }
                buf.append(Integer.toHexString(hextets[i]));
            } else if (i == 0 || lastWasNumber) {
                buf.append("::");
            }
            lastWasNumber = thisIsNumber;
        }
        return buf.toString();
    }

    public static String toUriString(InetAddress ip) {
        if (!(ip instanceof Inet6Address)) {
            return toAddrString(ip);
        }
        String addrString = toAddrString(ip);
        return new StringBuilder(String.valueOf(addrString).length() + 2).append("[").append(addrString).append("]").toString();
    }

    public static InetAddress forUriString(String hostAddr) {
        InetAddress addr = forUriStringNoThrow(hostAddr);
        if (addr != null) {
            return addr;
        }
        throw formatIllegalArgumentException("Not a valid URI IP literal: '%s'", hostAddr);
    }

    @NullableDecl
    private static InetAddress forUriStringNoThrow(String hostAddr) {
        int expectBytes;
        String ipString;
        Preconditions.checkNotNull(hostAddr);
        if (!hostAddr.startsWith("[") || !hostAddr.endsWith("]")) {
            ipString = hostAddr;
            expectBytes = 4;
        } else {
            ipString = hostAddr.substring(1, hostAddr.length() - 1);
            expectBytes = 16;
        }
        byte[] addr = ipStringToBytes(ipString);
        if (addr == null || addr.length != expectBytes) {
            return null;
        }
        return bytesToInetAddress(addr);
    }

    public static boolean isUriInetAddress(String ipString) {
        return forUriStringNoThrow(ipString) != null;
    }

    public static boolean isCompatIPv4Address(Inet6Address ip) {
        if (!ip.isIPv4CompatibleAddress()) {
            return false;
        }
        byte[] bytes = ip.getAddress();
        if (bytes[12] == 0 && bytes[13] == 0 && bytes[14] == 0 && (bytes[15] == 0 || bytes[15] == 1)) {
            return false;
        }
        return true;
    }

    public static Inet4Address getCompatIPv4Address(Inet6Address ip) {
        Preconditions.checkArgument(isCompatIPv4Address(ip), "Address '%s' is not IPv4-compatible.", (Object) toAddrString(ip));
        return getInet4Address(Arrays.copyOfRange(ip.getAddress(), 12, 16));
    }

    public static boolean is6to4Address(Inet6Address ip) {
        byte[] bytes = ip.getAddress();
        return bytes[0] == 32 && bytes[1] == 2;
    }

    public static Inet4Address get6to4IPv4Address(Inet6Address ip) {
        Preconditions.checkArgument(is6to4Address(ip), "Address '%s' is not a 6to4 address.", (Object) toAddrString(ip));
        return getInet4Address(Arrays.copyOfRange(ip.getAddress(), 2, 6));
    }

    public static final class TeredoInfo {
        private final Inet4Address client;
        private final int flags;
        private final int port;
        private final Inet4Address server;

        public TeredoInfo(@NullableDecl Inet4Address server2, @NullableDecl Inet4Address client2, int port2, int flags2) {
            boolean z = true;
            Preconditions.checkArgument(port2 >= 0 && port2 <= 65535, "port '%s' is out of range (0 <= port <= 0xffff)", port2);
            Preconditions.checkArgument((flags2 < 0 || flags2 > 65535) ? false : z, "flags '%s' is out of range (0 <= flags <= 0xffff)", flags2);
            this.server = (Inet4Address) MoreObjects.firstNonNull(server2, InetAddresses.ANY4);
            this.client = (Inet4Address) MoreObjects.firstNonNull(client2, InetAddresses.ANY4);
            this.port = port2;
            this.flags = flags2;
        }

        public Inet4Address getServer() {
            return this.server;
        }

        public Inet4Address getClient() {
            return this.client;
        }

        public int getPort() {
            return this.port;
        }

        public int getFlags() {
            return this.flags;
        }
    }

    public static boolean isTeredoAddress(Inet6Address ip) {
        byte[] bytes = ip.getAddress();
        return bytes[0] == 32 && bytes[1] == 1 && bytes[2] == 0 && bytes[3] == 0;
    }

    public static TeredoInfo getTeredoInfo(Inet6Address ip) {
        Preconditions.checkArgument(isTeredoAddress(ip), "Address '%s' is not a Teredo address.", (Object) toAddrString(ip));
        byte[] bytes = ip.getAddress();
        Inet4Address server = getInet4Address(Arrays.copyOfRange(bytes, 4, 8));
        int flags = ByteStreams.newDataInput(bytes, 8).readShort() & 65535;
        int port = 65535 & (~ByteStreams.newDataInput(bytes, 10).readShort());
        byte[] clientBytes = Arrays.copyOfRange(bytes, 12, 16);
        for (int i = 0; i < clientBytes.length; i++) {
            clientBytes[i] = (byte) (~clientBytes[i]);
        }
        return new TeredoInfo(server, getInet4Address(clientBytes), port, flags);
    }

    public static boolean isIsatapAddress(Inet6Address ip) {
        if (isTeredoAddress(ip)) {
            return false;
        }
        byte[] bytes = ip.getAddress();
        if ((bytes[8] | 3) == 3 && bytes[9] == 0 && bytes[10] == 94 && bytes[11] == -2) {
            return true;
        }
        return false;
    }

    public static Inet4Address getIsatapIPv4Address(Inet6Address ip) {
        Preconditions.checkArgument(isIsatapAddress(ip), "Address '%s' is not an ISATAP address.", (Object) toAddrString(ip));
        return getInet4Address(Arrays.copyOfRange(ip.getAddress(), 12, 16));
    }

    public static boolean hasEmbeddedIPv4ClientAddress(Inet6Address ip) {
        return isCompatIPv4Address(ip) || is6to4Address(ip) || isTeredoAddress(ip);
    }

    public static Inet4Address getEmbeddedIPv4ClientAddress(Inet6Address ip) {
        if (isCompatIPv4Address(ip)) {
            return getCompatIPv4Address(ip);
        }
        if (is6to4Address(ip)) {
            return get6to4IPv4Address(ip);
        }
        if (isTeredoAddress(ip)) {
            return getTeredoInfo(ip).getClient();
        }
        throw formatIllegalArgumentException("'%s' has no embedded IPv4 address.", toAddrString(ip));
    }

    public static boolean isMappedIPv4Address(String ipString) {
        byte[] bytes = ipStringToBytes(ipString);
        if (bytes == null || bytes.length != 16) {
            return false;
        }
        for (int i = 0; i < 10; i++) {
            if (bytes[i] != 0) {
                return false;
            }
        }
        for (int i2 = 10; i2 < 12; i2++) {
            if (bytes[i2] != -1) {
                return false;
            }
        }
        return true;
    }

    public static Inet4Address getCoercedIPv4Address(InetAddress ip) {
        long addressAsLong;
        if (ip instanceof Inet4Address) {
            return (Inet4Address) ip;
        }
        byte[] bytes = ip.getAddress();
        boolean leadingBytesOfZero = true;
        int i = 0;
        while (true) {
            if (i >= 15) {
                break;
            } else if (bytes[i] != 0) {
                leadingBytesOfZero = false;
                break;
            } else {
                i++;
            }
        }
        if (leadingBytesOfZero && bytes[15] == 1) {
            return LOOPBACK4;
        }
        if (leadingBytesOfZero && bytes[15] == 0) {
            return ANY4;
        }
        Inet6Address ip6 = (Inet6Address) ip;
        if (hasEmbeddedIPv4ClientAddress(ip6)) {
            addressAsLong = (long) getEmbeddedIPv4ClientAddress(ip6).hashCode();
        } else {
            addressAsLong = ByteBuffer.wrap(ip6.getAddress(), 0, 8).getLong();
        }
        int coercedHash = Hashing.murmur3_32().hashLong(addressAsLong).asInt() | -536870912;
        if (coercedHash == -1) {
            coercedHash = -2;
        }
        return getInet4Address(Ints.toByteArray(coercedHash));
    }

    public static int coerceToInteger(InetAddress ip) {
        return ByteStreams.newDataInput(getCoercedIPv4Address(ip).getAddress()).readInt();
    }

    public static BigInteger toBigInteger(InetAddress address) {
        return new BigInteger(1, address.getAddress());
    }

    public static Inet4Address fromInteger(int address) {
        return getInet4Address(Ints.toByteArray(address));
    }

    public static Inet4Address fromIPv4BigInteger(BigInteger address) {
        return (Inet4Address) fromBigInteger(address, false);
    }

    public static Inet6Address fromIPv6BigInteger(BigInteger address) {
        return (Inet6Address) fromBigInteger(address, true);
    }

    private static InetAddress fromBigInteger(BigInteger address, boolean isIpv6) {
        Preconditions.checkArgument(address.signum() >= 0, "BigInteger must be greater than or equal to 0");
        int numBytes = isIpv6 ? 16 : 4;
        byte[] addressBytes = address.toByteArray();
        byte[] targetCopyArray = new byte[numBytes];
        int srcPos = Math.max(0, addressBytes.length - numBytes);
        int copyLength = addressBytes.length - srcPos;
        int destPos = numBytes - copyLength;
        int i = 0;
        while (i < srcPos) {
            if (addressBytes[i] == 0) {
                i++;
            } else {
                throw formatIllegalArgumentException("BigInteger cannot be converted to InetAddress because it has more than %d bytes: %s", Integer.valueOf(numBytes), address);
            }
        }
        System.arraycopy(addressBytes, srcPos, targetCopyArray, destPos, copyLength);
        try {
            return InetAddress.getByAddress(targetCopyArray);
        } catch (UnknownHostException impossible) {
            throw new AssertionError(impossible);
        }
    }

    public static InetAddress fromLittleEndianByteArray(byte[] addr) throws UnknownHostException {
        byte[] reversed = new byte[addr.length];
        for (int i = 0; i < addr.length; i++) {
            reversed[i] = addr[(addr.length - i) - 1];
        }
        return InetAddress.getByAddress(reversed);
    }

    public static InetAddress decrement(InetAddress address) {
        byte[] addr = address.getAddress();
        int i = addr.length - 1;
        while (i >= 0 && addr[i] == 0) {
            addr[i] = -1;
            i--;
        }
        Preconditions.checkArgument(i >= 0, "Decrementing %s would wrap.", (Object) address);
        addr[i] = (byte) (addr[i] - 1);
        return bytesToInetAddress(addr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.InetAddress increment(java.net.InetAddress r6) {
        /*
            byte[] r0 = r6.getAddress()
            int r1 = r0.length
            r2 = 1
            int r1 = r1 - r2
        L_0x0007:
            r3 = 0
            if (r1 < 0) goto L_0x0014
            byte r4 = r0[r1]
            r5 = -1
            if (r4 != r5) goto L_0x0014
            r0[r1] = r3
            int r1 = r1 + -1
            goto L_0x0007
        L_0x0014:
            if (r1 < 0) goto L_0x0017
            r3 = r2
        L_0x0017:
            java.lang.String r4 = "Incrementing %s would wrap."
            com.google.common.base.Preconditions.checkArgument((boolean) r3, (java.lang.String) r4, (java.lang.Object) r6)
            byte r3 = r0[r1]
            int r3 = r3 + r2
            byte r2 = (byte) r3
            r0[r1] = r2
            java.net.InetAddress r2 = bytesToInetAddress(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.net.InetAddresses.increment(java.net.InetAddress):java.net.InetAddress");
    }

    public static boolean isMaximum(InetAddress address) {
        byte[] addr = address.getAddress();
        for (byte b : addr) {
            if (b != -1) {
                return false;
            }
        }
        return true;
    }

    private static IllegalArgumentException formatIllegalArgumentException(String format, Object... args) {
        return new IllegalArgumentException(String.format(Locale.ROOT, format, args));
    }
}
