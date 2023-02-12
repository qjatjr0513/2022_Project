package p004io.grpc.internal;

import com.google.common.base.Verify;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import p004io.grpc.internal.DnsNameResolver;

/* renamed from: io.grpc.internal.JndiResourceResolverFactory */
final class JndiResourceResolverFactory implements DnsNameResolver.ResourceResolverFactory {
    /* access modifiers changed from: private */
    @Nullable
    public static final Throwable JNDI_UNAVAILABILITY_CAUSE = initJndi();

    /* renamed from: io.grpc.internal.JndiResourceResolverFactory$RecordFetcher */
    interface RecordFetcher {
        List<String> getAllRecords(String str, String str2) throws NamingException;
    }

    @Nullable
    private static Throwable initJndi() {
        try {
            Class.forName("javax.naming.directory.InitialDirContext");
            Class.forName("com.sun.jndi.dns.DnsContextFactory");
            return null;
        } catch (ClassNotFoundException e) {
            return e;
        } catch (RuntimeException e2) {
            return e2;
        } catch (Error e3) {
            return e3;
        }
    }

    @Nullable
    public DnsNameResolver.ResourceResolver newResourceResolver() {
        if (unavailabilityCause() != null) {
            return null;
        }
        return new JndiResourceResolver(new JndiRecordFetcher());
    }

    @Nullable
    public Throwable unavailabilityCause() {
        return JNDI_UNAVAILABILITY_CAUSE;
    }

    /* renamed from: io.grpc.internal.JndiResourceResolverFactory$JndiResourceResolver */
    static final class JndiResourceResolver implements DnsNameResolver.ResourceResolver {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final Logger logger = Logger.getLogger(JndiResourceResolver.class.getName());
        private static final Pattern whitespace = Pattern.compile("\\s+");
        private final RecordFetcher recordFetcher;

        static {
            Class<JndiResourceResolverFactory> cls = JndiResourceResolverFactory.class;
        }

        public JndiResourceResolver(RecordFetcher recordFetcher2) {
            this.recordFetcher = recordFetcher2;
        }

        public List<String> resolveTxt(String serviceConfigHostname) throws NamingException {
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINER)) {
                logger2.log(Level.FINER, "About to query TXT records for {0}", new Object[]{serviceConfigHostname});
            }
            List<String> serviceConfigRawTxtRecords = this.recordFetcher.getAllRecords("TXT", "dns:///" + serviceConfigHostname);
            if (logger2.isLoggable(Level.FINER)) {
                logger2.log(Level.FINER, "Found {0} TXT records", new Object[]{Integer.valueOf(serviceConfigRawTxtRecords.size())});
            }
            List<String> serviceConfigTxtRecords = new ArrayList<>(serviceConfigRawTxtRecords.size());
            for (String serviceConfigRawTxtRecord : serviceConfigRawTxtRecords) {
                serviceConfigTxtRecords.add(unquote(serviceConfigRawTxtRecord));
            }
            return Collections.unmodifiableList(serviceConfigTxtRecords);
        }

        public List<DnsNameResolver.SrvRecord> resolveSrv(String host) throws Exception {
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINER)) {
                logger2.log(Level.FINER, "About to query SRV records for {0}", new Object[]{host});
            }
            List<String> rawSrvRecords = this.recordFetcher.getAllRecords("SRV", "dns:///" + host);
            if (logger2.isLoggable(Level.FINER)) {
                logger2.log(Level.FINER, "Found {0} SRV records", new Object[]{Integer.valueOf(rawSrvRecords.size())});
            }
            List<DnsNameResolver.SrvRecord> srvRecords = new ArrayList<>(rawSrvRecords.size());
            Exception first = null;
            Level level = Level.WARNING;
            for (String rawSrv : rawSrvRecords) {
                try {
                    String[] parts = whitespace.split(rawSrv);
                    Verify.verify(parts.length == 4, "Bad SRV Record: %s", (Object) rawSrv);
                    if (parts[3].endsWith(".")) {
                        srvRecords.add(new DnsNameResolver.SrvRecord(parts[3], Integer.parseInt(parts[2])));
                    } else {
                        throw new RuntimeException("Returned SRV host does not end in period: " + parts[3]);
                    }
                } catch (RuntimeException e) {
                    logger.log(level, "Failed to construct SRV record " + rawSrv, e);
                    if (first == null) {
                        first = e;
                        level = Level.FINE;
                    }
                }
            }
            if (!srvRecords.isEmpty() || first == null) {
                return Collections.unmodifiableList(srvRecords);
            }
            throw first;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
            r2 = r2 + 1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static java.lang.String unquote(java.lang.String r6) {
            /*
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                int r1 = r6.length()
                r0.<init>(r1)
                r1 = 0
                r2 = 0
            L_0x000b:
                int r3 = r6.length()
                if (r2 >= r3) goto L_0x0041
                char r3 = r6.charAt(r2)
                r4 = 34
                if (r1 != 0) goto L_0x0022
                r5 = 32
                if (r3 != r5) goto L_0x001e
                goto L_0x003e
            L_0x001e:
                if (r3 != r4) goto L_0x003b
                r1 = 1
                goto L_0x003e
            L_0x0022:
                if (r3 != r4) goto L_0x0026
                r1 = 0
                goto L_0x003e
            L_0x0026:
                r5 = 92
                if (r3 != r5) goto L_0x003b
                int r2 = r2 + 1
                char r3 = r6.charAt(r2)
                if (r3 == r4) goto L_0x003b
                if (r3 != r5) goto L_0x0035
                goto L_0x003b
            L_0x0035:
                java.lang.AssertionError r4 = new java.lang.AssertionError
                r4.<init>()
                throw r4
            L_0x003b:
                r0.append(r3)
            L_0x003e:
                int r2 = r2 + 1
                goto L_0x000b
            L_0x0041:
                java.lang.String r2 = r0.toString()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.JndiResourceResolverFactory.JndiResourceResolver.unquote(java.lang.String):java.lang.String");
        }
    }

    /* renamed from: io.grpc.internal.JndiResourceResolverFactory$JndiRecordFetcher */
    static final class JndiRecordFetcher implements RecordFetcher {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<JndiResourceResolverFactory> cls = JndiResourceResolverFactory.class;
        }

        JndiRecordFetcher() {
        }

        public List<String> getAllRecords(String recordType, String name) throws NamingException {
            checkAvailable();
            String[] rrType = {recordType};
            List<String> records = new ArrayList<>();
            Hashtable<String, String> env = new Hashtable<>();
            env.put("com.sun.jndi.ldap.connect.timeout", "5000");
            env.put("com.sun.jndi.ldap.read.timeout", "5000");
            DirContext dirContext = new InitialDirContext(env);
            try {
                NamingEnumeration<? extends Attribute> rrGroups = dirContext.getAttributes(name, rrType).getAll();
                while (rrGroups.hasMore()) {
                    try {
                        Attribute rrEntry = (Attribute) rrGroups.next();
                        if (Arrays.asList(rrType).contains(rrEntry.getID())) {
                            NamingEnumeration<?> rrValues = rrEntry.getAll();
                            while (rrValues.hasMore()) {
                                try {
                                    records.add(String.valueOf(rrValues.next()));
                                } catch (NamingException ne) {
                                    closeThenThrow(rrValues, ne);
                                }
                            }
                            rrValues.close();
                        } else {
                            throw new AssertionError();
                        }
                    } catch (NamingException ne2) {
                        closeThenThrow((NamingEnumeration<?>) rrGroups, ne2);
                    }
                }
                rrGroups.close();
            } catch (NamingException ne3) {
                closeThenThrow(dirContext, ne3);
            }
            dirContext.close();
            return records;
        }

        private static void closeThenThrow(NamingEnumeration<?> namingEnumeration, NamingException e) throws NamingException {
            try {
                namingEnumeration.close();
            } catch (NamingException e2) {
            }
            throw e;
        }

        private static void closeThenThrow(DirContext ctx, NamingException e) throws NamingException {
            try {
                ctx.close();
            } catch (NamingException e2) {
            }
            throw e;
        }

        private static void checkAvailable() {
            if (JndiResourceResolverFactory.JNDI_UNAVAILABILITY_CAUSE != null) {
                throw new UnsupportedOperationException("JNDI is not currently available", JndiResourceResolverFactory.JNDI_UNAVAILABILITY_CAUSE);
            }
        }
    }
}
