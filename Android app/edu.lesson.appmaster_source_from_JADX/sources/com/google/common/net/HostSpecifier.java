package com.google.common.net;

import com.google.common.base.Preconditions;
import java.net.InetAddress;
import java.text.ParseException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class HostSpecifier {
    private final String canonicalForm;

    private HostSpecifier(String canonicalForm2) {
        this.canonicalForm = canonicalForm2;
    }

    public static HostSpecifier fromValid(String specifier) {
        HostAndPort parsedHost = HostAndPort.fromString(specifier);
        Preconditions.checkArgument(!parsedHost.hasPort());
        String host = parsedHost.getHost();
        InetAddress addr = null;
        try {
            addr = InetAddresses.forString(host);
        } catch (IllegalArgumentException e) {
        }
        if (addr != null) {
            return new HostSpecifier(InetAddresses.toUriString(addr));
        }
        InternetDomainName domain = InternetDomainName.from(host);
        if (domain.hasPublicSuffix()) {
            return new HostSpecifier(domain.toString());
        }
        String valueOf = String.valueOf(host);
        throw new IllegalArgumentException(valueOf.length() != 0 ? "Domain name does not have a recognized public suffix: ".concat(valueOf) : new String("Domain name does not have a recognized public suffix: "));
    }

    public static HostSpecifier from(String specifier) throws ParseException {
        try {
            return fromValid(specifier);
        } catch (IllegalArgumentException e) {
            String valueOf = String.valueOf(specifier);
            ParseException parseException = new ParseException(valueOf.length() != 0 ? "Invalid host specifier: ".concat(valueOf) : new String("Invalid host specifier: "), 0);
            parseException.initCause(e);
            throw parseException;
        }
    }

    public static boolean isValid(String specifier) {
        try {
            fromValid(specifier);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean equals(@NullableDecl Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof HostSpecifier) {
            return this.canonicalForm.equals(((HostSpecifier) other).canonicalForm);
        }
        return false;
    }

    public int hashCode() {
        return this.canonicalForm.hashCode();
    }

    public String toString() {
        return this.canonicalForm;
    }
}
