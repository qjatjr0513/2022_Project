package p004io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.net.SocketAddress;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* renamed from: io.grpc.InternalChannelz */
public final class InternalChannelz {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalChannelz INSTANCE = new InternalChannelz();
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(InternalChannelz.class.getName());
    private final ConcurrentMap<Long, InternalInstrumented<SocketStats>> otherSockets = new ConcurrentHashMap();
    private final ConcurrentMap<Long, ServerSocketMap> perServerSockets = new ConcurrentHashMap();
    private final ConcurrentNavigableMap<Long, InternalInstrumented<ChannelStats>> rootChannels = new ConcurrentSkipListMap();
    private final ConcurrentNavigableMap<Long, InternalInstrumented<ServerStats>> servers = new ConcurrentSkipListMap();
    private final ConcurrentMap<Long, InternalInstrumented<ChannelStats>> subchannels = new ConcurrentHashMap();

    /* renamed from: io.grpc.InternalChannelz$ServerSocketMap */
    private static final class ServerSocketMap extends ConcurrentSkipListMap<Long, InternalInstrumented<SocketStats>> {
        private static final long serialVersionUID = -7883772124944661414L;

        private ServerSocketMap() {
        }
    }

    public static InternalChannelz instance() {
        return INSTANCE;
    }

    public void addServer(InternalInstrumented<ServerStats> server) {
        if (((ServerSocketMap) this.perServerSockets.put(Long.valueOf(m346id(server)), new ServerSocketMap())) == null) {
            add(this.servers, server);
            return;
        }
        throw new AssertionError();
    }

    public void addSubchannel(InternalInstrumented<ChannelStats> subchannel) {
        add(this.subchannels, subchannel);
    }

    public void addRootChannel(InternalInstrumented<ChannelStats> rootChannel) {
        add(this.rootChannels, rootChannel);
    }

    public void addClientSocket(InternalInstrumented<SocketStats> socket) {
        add(this.otherSockets, socket);
    }

    public void addListenSocket(InternalInstrumented<SocketStats> socket) {
        add(this.otherSockets, socket);
    }

    public void addServerSocket(InternalInstrumented<ServerStats> server, InternalInstrumented<SocketStats> socket) {
        ServerSocketMap serverSockets = (ServerSocketMap) this.perServerSockets.get(Long.valueOf(m346id(server)));
        if (serverSockets != null) {
            add(serverSockets, socket);
            return;
        }
        throw new AssertionError();
    }

    public void removeServer(InternalInstrumented<ServerStats> server) {
        remove(this.servers, server);
        ServerSocketMap prev = (ServerSocketMap) this.perServerSockets.remove(Long.valueOf(m346id(server)));
        if (prev == null) {
            throw new AssertionError();
        } else if (!prev.isEmpty()) {
            throw new AssertionError();
        }
    }

    public void removeSubchannel(InternalInstrumented<ChannelStats> subchannel) {
        remove(this.subchannels, subchannel);
    }

    public void removeRootChannel(InternalInstrumented<ChannelStats> channel) {
        remove(this.rootChannels, channel);
    }

    public void removeClientSocket(InternalInstrumented<SocketStats> socket) {
        remove(this.otherSockets, socket);
    }

    public void removeListenSocket(InternalInstrumented<SocketStats> socket) {
        remove(this.otherSockets, socket);
    }

    public void removeServerSocket(InternalInstrumented<ServerStats> server, InternalInstrumented<SocketStats> socket) {
        ServerSocketMap socketsOfServer = (ServerSocketMap) this.perServerSockets.get(Long.valueOf(m346id(server)));
        if (socketsOfServer != null) {
            remove(socketsOfServer, socket);
            return;
        }
        throw new AssertionError();
    }

    public RootChannelList getRootChannels(long fromId, int maxPageSize) {
        List<InternalInstrumented<ChannelStats>> channelList = new ArrayList<>();
        Iterator<InternalInstrumented<ChannelStats>> iterator = this.rootChannels.tailMap(Long.valueOf(fromId)).values().iterator();
        while (iterator.hasNext() && channelList.size() < maxPageSize) {
            channelList.add(iterator.next());
        }
        return new RootChannelList(channelList, !iterator.hasNext());
    }

    @Nullable
    public InternalInstrumented<ChannelStats> getChannel(long id) {
        return (InternalInstrumented) this.rootChannels.get(Long.valueOf(id));
    }

    @Nullable
    public InternalInstrumented<ChannelStats> getSubchannel(long id) {
        return (InternalInstrumented) this.subchannels.get(Long.valueOf(id));
    }

    public ServerList getServers(long fromId, int maxPageSize) {
        List<InternalInstrumented<ServerStats>> serverList = new ArrayList<>(maxPageSize);
        Iterator<InternalInstrumented<ServerStats>> iterator = this.servers.tailMap(Long.valueOf(fromId)).values().iterator();
        while (iterator.hasNext() && serverList.size() < maxPageSize) {
            serverList.add(iterator.next());
        }
        return new ServerList(serverList, !iterator.hasNext());
    }

    @Nullable
    public InternalInstrumented<ServerStats> getServer(long id) {
        return (InternalInstrumented) this.servers.get(Long.valueOf(id));
    }

    @Nullable
    public ServerSocketsList getServerSockets(long serverId, long fromId, int maxPageSize) {
        ServerSocketMap serverSockets = (ServerSocketMap) this.perServerSockets.get(Long.valueOf(serverId));
        if (serverSockets == null) {
            return null;
        }
        List<InternalWithLogId> socketList = new ArrayList<>(maxPageSize);
        Iterator<InternalInstrumented<SocketStats>> iterator = serverSockets.tailMap(Long.valueOf(fromId)).values().iterator();
        while (socketList.size() < maxPageSize && iterator.hasNext()) {
            socketList.add(iterator.next());
        }
        return new ServerSocketsList(socketList, !iterator.hasNext());
    }

    @Nullable
    public InternalInstrumented<SocketStats> getSocket(long id) {
        InternalInstrumented<SocketStats> clientSocket = (InternalInstrumented) this.otherSockets.get(Long.valueOf(id));
        if (clientSocket != null) {
            return clientSocket;
        }
        return getServerSocket(id);
    }

    private InternalInstrumented<SocketStats> getServerSocket(long id) {
        for (ServerSocketMap perServerSockets2 : this.perServerSockets.values()) {
            InternalInstrumented<SocketStats> serverSocket = (InternalInstrumented) perServerSockets2.get(Long.valueOf(id));
            if (serverSocket != null) {
                return serverSocket;
            }
        }
        return null;
    }

    public boolean containsServer(InternalLogId serverRef) {
        return contains(this.servers, serverRef);
    }

    public boolean containsSubchannel(InternalLogId subchannelRef) {
        return contains(this.subchannels, subchannelRef);
    }

    public InternalInstrumented<ChannelStats> getRootChannel(long id) {
        return (InternalInstrumented) this.rootChannels.get(Long.valueOf(id));
    }

    public boolean containsClientSocket(InternalLogId transportRef) {
        return contains(this.otherSockets, transportRef);
    }

    private static <T extends InternalInstrumented<?>> void add(Map<Long, T> map, T object) {
        if (((InternalInstrumented) map.put(Long.valueOf(object.getLogId().getId()), object)) != null) {
            throw new AssertionError();
        }
    }

    private static <T extends InternalInstrumented<?>> void remove(Map<Long, T> map, T object) {
        if (((InternalInstrumented) map.remove(Long.valueOf(m346id(object)))) == null) {
            throw new AssertionError();
        }
    }

    private static <T extends InternalInstrumented<?>> boolean contains(Map<Long, T> map, InternalLogId id) {
        return map.containsKey(Long.valueOf(id.getId()));
    }

    /* renamed from: io.grpc.InternalChannelz$RootChannelList */
    public static final class RootChannelList {
        public final List<InternalInstrumented<ChannelStats>> channels;
        public final boolean end;

        public RootChannelList(List<InternalInstrumented<ChannelStats>> channels2, boolean end2) {
            this.channels = (List) Preconditions.checkNotNull(channels2);
            this.end = end2;
        }
    }

    /* renamed from: io.grpc.InternalChannelz$ServerList */
    public static final class ServerList {
        public final boolean end;
        public final List<InternalInstrumented<ServerStats>> servers;

        public ServerList(List<InternalInstrumented<ServerStats>> servers2, boolean end2) {
            this.servers = (List) Preconditions.checkNotNull(servers2);
            this.end = end2;
        }
    }

    /* renamed from: io.grpc.InternalChannelz$ServerSocketsList */
    public static final class ServerSocketsList {
        public final boolean end;
        public final List<InternalWithLogId> sockets;

        public ServerSocketsList(List<InternalWithLogId> sockets2, boolean end2) {
            this.sockets = sockets2;
            this.end = end2;
        }
    }

    /* renamed from: io.grpc.InternalChannelz$ServerStats */
    public static final class ServerStats {
        public final long callsFailed;
        public final long callsStarted;
        public final long callsSucceeded;
        public final long lastCallStartedNanos;
        public final List<InternalInstrumented<SocketStats>> listenSockets;

        public ServerStats(long callsStarted2, long callsSucceeded2, long callsFailed2, long lastCallStartedNanos2, List<InternalInstrumented<SocketStats>> listenSockets2) {
            this.callsStarted = callsStarted2;
            this.callsSucceeded = callsSucceeded2;
            this.callsFailed = callsFailed2;
            this.lastCallStartedNanos = lastCallStartedNanos2;
            this.listenSockets = (List) Preconditions.checkNotNull(listenSockets2);
        }

        /* renamed from: io.grpc.InternalChannelz$ServerStats$Builder */
        public static final class Builder {
            private long callsFailed;
            private long callsStarted;
            private long callsSucceeded;
            private long lastCallStartedNanos;
            public List<InternalInstrumented<SocketStats>> listenSockets = new ArrayList();

            public Builder setCallsStarted(long callsStarted2) {
                this.callsStarted = callsStarted2;
                return this;
            }

            public Builder setCallsSucceeded(long callsSucceeded2) {
                this.callsSucceeded = callsSucceeded2;
                return this;
            }

            public Builder setCallsFailed(long callsFailed2) {
                this.callsFailed = callsFailed2;
                return this;
            }

            public Builder setLastCallStartedNanos(long lastCallStartedNanos2) {
                this.lastCallStartedNanos = lastCallStartedNanos2;
                return this;
            }

            public Builder addListenSockets(List<InternalInstrumented<SocketStats>> listenSockets2) {
                Preconditions.checkNotNull(listenSockets2, "listenSockets");
                for (InternalInstrumented<SocketStats> checkNotNull : listenSockets2) {
                    this.listenSockets.add((InternalInstrumented) Preconditions.checkNotNull(checkNotNull, "null listen socket"));
                }
                return this;
            }

            public ServerStats build() {
                return new ServerStats(this.callsStarted, this.callsSucceeded, this.callsFailed, this.lastCallStartedNanos, this.listenSockets);
            }
        }
    }

    /* renamed from: io.grpc.InternalChannelz$ChannelStats */
    public static final class ChannelStats {
        public final long callsFailed;
        public final long callsStarted;
        public final long callsSucceeded;
        @Nullable
        public final ChannelTrace channelTrace;
        public final long lastCallStartedNanos;
        public final List<InternalWithLogId> sockets;
        public final ConnectivityState state;
        public final List<InternalWithLogId> subchannels;
        public final String target;

        private ChannelStats(String target2, ConnectivityState state2, @Nullable ChannelTrace channelTrace2, long callsStarted2, long callsSucceeded2, long callsFailed2, long lastCallStartedNanos2, List<InternalWithLogId> subchannels2, List<InternalWithLogId> sockets2) {
            Preconditions.checkState(subchannels2.isEmpty() || sockets2.isEmpty(), "channels can have subchannels only, subchannels can have either sockets OR subchannels, neither can have both");
            this.target = target2;
            this.state = state2;
            this.channelTrace = channelTrace2;
            this.callsStarted = callsStarted2;
            this.callsSucceeded = callsSucceeded2;
            this.callsFailed = callsFailed2;
            this.lastCallStartedNanos = lastCallStartedNanos2;
            this.subchannels = (List) Preconditions.checkNotNull(subchannels2);
            this.sockets = (List) Preconditions.checkNotNull(sockets2);
        }

        /* renamed from: io.grpc.InternalChannelz$ChannelStats$Builder */
        public static final class Builder {
            private long callsFailed;
            private long callsStarted;
            private long callsSucceeded;
            private ChannelTrace channelTrace;
            private long lastCallStartedNanos;
            private List<InternalWithLogId> sockets = Collections.emptyList();
            private ConnectivityState state;
            private List<InternalWithLogId> subchannels = Collections.emptyList();
            private String target;

            public Builder setTarget(String target2) {
                this.target = target2;
                return this;
            }

            public Builder setState(ConnectivityState state2) {
                this.state = state2;
                return this;
            }

            public Builder setChannelTrace(ChannelTrace channelTrace2) {
                this.channelTrace = channelTrace2;
                return this;
            }

            public Builder setCallsStarted(long callsStarted2) {
                this.callsStarted = callsStarted2;
                return this;
            }

            public Builder setCallsSucceeded(long callsSucceeded2) {
                this.callsSucceeded = callsSucceeded2;
                return this;
            }

            public Builder setCallsFailed(long callsFailed2) {
                this.callsFailed = callsFailed2;
                return this;
            }

            public Builder setLastCallStartedNanos(long lastCallStartedNanos2) {
                this.lastCallStartedNanos = lastCallStartedNanos2;
                return this;
            }

            public Builder setSubchannels(List<InternalWithLogId> subchannels2) {
                Preconditions.checkState(this.sockets.isEmpty());
                this.subchannels = Collections.unmodifiableList((List) Preconditions.checkNotNull(subchannels2));
                return this;
            }

            public Builder setSockets(List<InternalWithLogId> sockets2) {
                Preconditions.checkState(this.subchannels.isEmpty());
                this.sockets = Collections.unmodifiableList((List) Preconditions.checkNotNull(sockets2));
                return this;
            }

            public ChannelStats build() {
                return new ChannelStats(this.target, this.state, this.channelTrace, this.callsStarted, this.callsSucceeded, this.callsFailed, this.lastCallStartedNanos, this.subchannels, this.sockets);
            }
        }
    }

    /* renamed from: io.grpc.InternalChannelz$ChannelTrace */
    public static final class ChannelTrace {
        public final long creationTimeNanos;
        public final List<Event> events;
        public final long numEventsLogged;

        private ChannelTrace(long numEventsLogged2, long creationTimeNanos2, List<Event> events2) {
            this.numEventsLogged = numEventsLogged2;
            this.creationTimeNanos = creationTimeNanos2;
            this.events = events2;
        }

        /* renamed from: io.grpc.InternalChannelz$ChannelTrace$Builder */
        public static final class Builder {
            private Long creationTimeNanos;
            private List<Event> events = Collections.emptyList();
            private Long numEventsLogged;

            public Builder setNumEventsLogged(long numEventsLogged2) {
                this.numEventsLogged = Long.valueOf(numEventsLogged2);
                return this;
            }

            public Builder setCreationTimeNanos(long creationTimeNanos2) {
                this.creationTimeNanos = Long.valueOf(creationTimeNanos2);
                return this;
            }

            public Builder setEvents(List<Event> events2) {
                this.events = Collections.unmodifiableList(new ArrayList(events2));
                return this;
            }

            public ChannelTrace build() {
                Preconditions.checkNotNull(this.numEventsLogged, "numEventsLogged");
                Preconditions.checkNotNull(this.creationTimeNanos, "creationTimeNanos");
                return new ChannelTrace(this.numEventsLogged.longValue(), this.creationTimeNanos.longValue(), this.events);
            }
        }

        /* renamed from: io.grpc.InternalChannelz$ChannelTrace$Event */
        public static final class Event {
            @Nullable
            public final InternalWithLogId channelRef;
            public final String description;
            public final Severity severity;
            @Nullable
            public final InternalWithLogId subchannelRef;
            public final long timestampNanos;

            /* renamed from: io.grpc.InternalChannelz$ChannelTrace$Event$Severity */
            public enum Severity {
                CT_UNKNOWN,
                CT_INFO,
                CT_WARNING,
                CT_ERROR
            }

            private Event(String description2, Severity severity2, long timestampNanos2, @Nullable InternalWithLogId channelRef2, @Nullable InternalWithLogId subchannelRef2) {
                this.description = description2;
                this.severity = (Severity) Preconditions.checkNotNull(severity2, "severity");
                this.timestampNanos = timestampNanos2;
                this.channelRef = channelRef2;
                this.subchannelRef = subchannelRef2;
            }

            public int hashCode() {
                return Objects.hashCode(this.description, this.severity, Long.valueOf(this.timestampNanos), this.channelRef, this.subchannelRef);
            }

            public boolean equals(Object o) {
                if (!(o instanceof Event)) {
                    return false;
                }
                Event that = (Event) o;
                if (!Objects.equal(this.description, that.description) || !Objects.equal(this.severity, that.severity) || this.timestampNanos != that.timestampNanos || !Objects.equal(this.channelRef, that.channelRef) || !Objects.equal(this.subchannelRef, that.subchannelRef)) {
                    return false;
                }
                return true;
            }

            public String toString() {
                return MoreObjects.toStringHelper((Object) this).add("description", (Object) this.description).add("severity", (Object) this.severity).add("timestampNanos", this.timestampNanos).add("channelRef", (Object) this.channelRef).add("subchannelRef", (Object) this.subchannelRef).toString();
            }

            /* renamed from: io.grpc.InternalChannelz$ChannelTrace$Event$Builder */
            public static final class Builder {
                private InternalWithLogId channelRef;
                private String description;
                private Severity severity;
                private InternalWithLogId subchannelRef;
                private Long timestampNanos;

                public Builder setDescription(String description2) {
                    this.description = description2;
                    return this;
                }

                public Builder setTimestampNanos(long timestampNanos2) {
                    this.timestampNanos = Long.valueOf(timestampNanos2);
                    return this;
                }

                public Builder setSeverity(Severity severity2) {
                    this.severity = severity2;
                    return this;
                }

                public Builder setChannelRef(InternalWithLogId channelRef2) {
                    this.channelRef = channelRef2;
                    return this;
                }

                public Builder setSubchannelRef(InternalWithLogId subchannelRef2) {
                    this.subchannelRef = subchannelRef2;
                    return this;
                }

                public Event build() {
                    Preconditions.checkNotNull(this.description, "description");
                    Preconditions.checkNotNull(this.severity, "severity");
                    Preconditions.checkNotNull(this.timestampNanos, "timestampNanos");
                    Preconditions.checkState(this.channelRef == null || this.subchannelRef == null, "at least one of channelRef and subchannelRef must be null");
                    return new Event(this.description, this.severity, this.timestampNanos.longValue(), this.channelRef, this.subchannelRef);
                }
            }
        }
    }

    /* renamed from: io.grpc.InternalChannelz$Security */
    public static final class Security {
        @Nullable
        public final OtherSecurity other;
        @Nullable
        public final Tls tls;

        public Security(Tls tls2) {
            this.tls = (Tls) Preconditions.checkNotNull(tls2);
            this.other = null;
        }

        public Security(OtherSecurity other2) {
            this.tls = null;
            this.other = (OtherSecurity) Preconditions.checkNotNull(other2);
        }
    }

    /* renamed from: io.grpc.InternalChannelz$OtherSecurity */
    public static final class OtherSecurity {
        @Nullable
        public final Object any;
        public final String name;

        public OtherSecurity(String name2, @Nullable Object any2) {
            this.name = (String) Preconditions.checkNotNull(name2);
            Preconditions.checkState(any2 == null || any2.getClass().getName().endsWith("com.google.protobuf.Any"), "the 'any' object must be of type com.google.protobuf.Any");
            this.any = any2;
        }
    }

    /* renamed from: io.grpc.InternalChannelz$Tls */
    public static final class Tls {
        public final String cipherSuiteStandardName;
        @Nullable
        public final Certificate localCert;
        @Nullable
        public final Certificate remoteCert;

        public Tls(String cipherSuiteName, Certificate localCert2, Certificate remoteCert2) {
            this.cipherSuiteStandardName = cipherSuiteName;
            this.localCert = localCert2;
            this.remoteCert = remoteCert2;
        }

        public Tls(SSLSession session) {
            String cipherSuiteStandardName2 = session.getCipherSuite();
            Certificate localCert2 = null;
            Certificate remoteCert2 = null;
            Certificate[] localCerts = session.getLocalCertificates();
            localCert2 = localCerts != null ? localCerts[0] : localCert2;
            try {
                Certificate[] peerCerts = session.getPeerCertificates();
                if (peerCerts != null) {
                    remoteCert2 = peerCerts[0];
                }
            } catch (SSLPeerUnverifiedException e) {
                InternalChannelz.log.log(Level.FINE, String.format("Peer cert not available for peerHost=%s", new Object[]{session.getPeerHost()}), e);
            }
            this.cipherSuiteStandardName = cipherSuiteStandardName2;
            this.localCert = localCert2;
            this.remoteCert = remoteCert2;
        }
    }

    /* renamed from: io.grpc.InternalChannelz$SocketStats */
    public static final class SocketStats {
        @Nullable
        public final TransportStats data;
        @Nullable
        public final SocketAddress local;
        @Nullable
        public final SocketAddress remote;
        @Nullable
        public final Security security;
        public final SocketOptions socketOptions;

        public SocketStats(TransportStats data2, @Nullable SocketAddress local2, @Nullable SocketAddress remote2, SocketOptions socketOptions2, Security security2) {
            this.data = data2;
            this.local = (SocketAddress) Preconditions.checkNotNull(local2, "local socket");
            this.remote = remote2;
            this.socketOptions = (SocketOptions) Preconditions.checkNotNull(socketOptions2);
            this.security = security2;
        }
    }

    /* renamed from: io.grpc.InternalChannelz$TcpInfo */
    public static final class TcpInfo {
        public final int advmss;
        public final int ato;
        public final int backoff;
        public final int caState;
        public final int fackets;
        public final int lastAckRecv;
        public final int lastAckSent;
        public final int lastDataRecv;
        public final int lastDataSent;
        public final int lost;
        public final int options;
        public final int pmtu;
        public final int probes;
        public final int rcvMss;
        public final int rcvSsthresh;
        public final int rcvWscale;
        public final int reordering;
        public final int retrans;
        public final int retransmits;
        public final int rto;
        public final int rtt;
        public final int rttvar;
        public final int sacked;
        public final int sndCwnd;
        public final int sndMss;
        public final int sndSsthresh;
        public final int sndWscale;
        public final int state;
        public final int unacked;

        TcpInfo(int state2, int caState2, int retransmits2, int probes2, int backoff2, int options2, int sndWscale2, int rcvWscale2, int rto2, int ato2, int sndMss2, int rcvMss2, int unacked2, int sacked2, int lost2, int retrans2, int fackets2, int lastDataSent2, int lastAckSent2, int lastDataRecv2, int lastAckRecv2, int pmtu2, int rcvSsthresh2, int rtt2, int rttvar2, int sndSsthresh2, int sndCwnd2, int advmss2, int reordering2) {
            this.state = state2;
            this.caState = caState2;
            this.retransmits = retransmits2;
            this.probes = probes2;
            this.backoff = backoff2;
            this.options = options2;
            this.sndWscale = sndWscale2;
            this.rcvWscale = rcvWscale2;
            this.rto = rto2;
            this.ato = ato2;
            this.sndMss = sndMss2;
            this.rcvMss = rcvMss2;
            this.unacked = unacked2;
            this.sacked = sacked2;
            this.lost = lost2;
            this.retrans = retrans2;
            this.fackets = fackets2;
            this.lastDataSent = lastDataSent2;
            this.lastAckSent = lastAckSent2;
            this.lastDataRecv = lastDataRecv2;
            this.lastAckRecv = lastAckRecv2;
            this.pmtu = pmtu2;
            this.rcvSsthresh = rcvSsthresh2;
            this.rtt = rtt2;
            this.rttvar = rttvar2;
            this.sndSsthresh = sndSsthresh2;
            this.sndCwnd = sndCwnd2;
            this.advmss = advmss2;
            this.reordering = reordering2;
        }

        /* renamed from: io.grpc.InternalChannelz$TcpInfo$Builder */
        public static final class Builder {
            private int advmss;
            private int ato;
            private int backoff;
            private int caState;
            private int fackets;
            private int lastAckRecv;
            private int lastAckSent;
            private int lastDataRecv;
            private int lastDataSent;
            private int lost;
            private int options;
            private int pmtu;
            private int probes;
            private int rcvMss;
            private int rcvSsthresh;
            private int rcvWscale;
            private int reordering;
            private int retrans;
            private int retransmits;
            private int rto;
            private int rtt;
            private int rttvar;
            private int sacked;
            private int sndCwnd;
            private int sndMss;
            private int sndSsthresh;
            private int sndWscale;
            private int state;
            private int unacked;

            public Builder setState(int state2) {
                this.state = state2;
                return this;
            }

            public Builder setCaState(int caState2) {
                this.caState = caState2;
                return this;
            }

            public Builder setRetransmits(int retransmits2) {
                this.retransmits = retransmits2;
                return this;
            }

            public Builder setProbes(int probes2) {
                this.probes = probes2;
                return this;
            }

            public Builder setBackoff(int backoff2) {
                this.backoff = backoff2;
                return this;
            }

            public Builder setOptions(int options2) {
                this.options = options2;
                return this;
            }

            public Builder setSndWscale(int sndWscale2) {
                this.sndWscale = sndWscale2;
                return this;
            }

            public Builder setRcvWscale(int rcvWscale2) {
                this.rcvWscale = rcvWscale2;
                return this;
            }

            public Builder setRto(int rto2) {
                this.rto = rto2;
                return this;
            }

            public Builder setAto(int ato2) {
                this.ato = ato2;
                return this;
            }

            public Builder setSndMss(int sndMss2) {
                this.sndMss = sndMss2;
                return this;
            }

            public Builder setRcvMss(int rcvMss2) {
                this.rcvMss = rcvMss2;
                return this;
            }

            public Builder setUnacked(int unacked2) {
                this.unacked = unacked2;
                return this;
            }

            public Builder setSacked(int sacked2) {
                this.sacked = sacked2;
                return this;
            }

            public Builder setLost(int lost2) {
                this.lost = lost2;
                return this;
            }

            public Builder setRetrans(int retrans2) {
                this.retrans = retrans2;
                return this;
            }

            public Builder setFackets(int fackets2) {
                this.fackets = fackets2;
                return this;
            }

            public Builder setLastDataSent(int lastDataSent2) {
                this.lastDataSent = lastDataSent2;
                return this;
            }

            public Builder setLastAckSent(int lastAckSent2) {
                this.lastAckSent = lastAckSent2;
                return this;
            }

            public Builder setLastDataRecv(int lastDataRecv2) {
                this.lastDataRecv = lastDataRecv2;
                return this;
            }

            public Builder setLastAckRecv(int lastAckRecv2) {
                this.lastAckRecv = lastAckRecv2;
                return this;
            }

            public Builder setPmtu(int pmtu2) {
                this.pmtu = pmtu2;
                return this;
            }

            public Builder setRcvSsthresh(int rcvSsthresh2) {
                this.rcvSsthresh = rcvSsthresh2;
                return this;
            }

            public Builder setRtt(int rtt2) {
                this.rtt = rtt2;
                return this;
            }

            public Builder setRttvar(int rttvar2) {
                this.rttvar = rttvar2;
                return this;
            }

            public Builder setSndSsthresh(int sndSsthresh2) {
                this.sndSsthresh = sndSsthresh2;
                return this;
            }

            public Builder setSndCwnd(int sndCwnd2) {
                this.sndCwnd = sndCwnd2;
                return this;
            }

            public Builder setAdvmss(int advmss2) {
                this.advmss = advmss2;
                return this;
            }

            public Builder setReordering(int reordering2) {
                this.reordering = reordering2;
                return this;
            }

            public TcpInfo build() {
                return new TcpInfo(this.state, this.caState, this.retransmits, this.probes, this.backoff, this.options, this.sndWscale, this.rcvWscale, this.rto, this.ato, this.sndMss, this.rcvMss, this.unacked, this.sacked, this.lost, this.retrans, this.fackets, this.lastDataSent, this.lastAckSent, this.lastDataRecv, this.lastAckRecv, this.pmtu, this.rcvSsthresh, this.rtt, this.rttvar, this.sndSsthresh, this.sndCwnd, this.advmss, this.reordering);
            }
        }
    }

    /* renamed from: io.grpc.InternalChannelz$SocketOptions */
    public static final class SocketOptions {
        @Nullable
        public final Integer lingerSeconds;
        public final Map<String, String> others;
        @Nullable
        public final Integer soTimeoutMillis;
        @Nullable
        public final TcpInfo tcpInfo;

        public SocketOptions(@Nullable Integer timeoutMillis, @Nullable Integer lingerSeconds2, @Nullable TcpInfo tcpInfo2, Map<String, String> others2) {
            Preconditions.checkNotNull(others2);
            this.soTimeoutMillis = timeoutMillis;
            this.lingerSeconds = lingerSeconds2;
            this.tcpInfo = tcpInfo2;
            this.others = Collections.unmodifiableMap(new HashMap(others2));
        }

        /* renamed from: io.grpc.InternalChannelz$SocketOptions$Builder */
        public static final class Builder {
            private Integer lingerSeconds;
            private final Map<String, String> others = new HashMap();
            private TcpInfo tcpInfo;
            private Integer timeoutMillis;

            public Builder setSocketOptionTimeoutMillis(Integer timeoutMillis2) {
                this.timeoutMillis = timeoutMillis2;
                return this;
            }

            public Builder setSocketOptionLingerSeconds(Integer lingerSeconds2) {
                this.lingerSeconds = lingerSeconds2;
                return this;
            }

            public Builder setTcpInfo(TcpInfo tcpInfo2) {
                this.tcpInfo = tcpInfo2;
                return this;
            }

            public Builder addOption(String name, String value) {
                this.others.put(name, (String) Preconditions.checkNotNull(value));
                return this;
            }

            public Builder addOption(String name, int value) {
                this.others.put(name, Integer.toString(value));
                return this;
            }

            public Builder addOption(String name, boolean value) {
                this.others.put(name, Boolean.toString(value));
                return this;
            }

            public SocketOptions build() {
                return new SocketOptions(this.timeoutMillis, this.lingerSeconds, this.tcpInfo, this.others);
            }
        }
    }

    /* renamed from: io.grpc.InternalChannelz$TransportStats */
    public static final class TransportStats {
        public final long keepAlivesSent;
        public final long lastLocalStreamCreatedTimeNanos;
        public final long lastMessageReceivedTimeNanos;
        public final long lastMessageSentTimeNanos;
        public final long lastRemoteStreamCreatedTimeNanos;
        public final long localFlowControlWindow;
        public final long messagesReceived;
        public final long messagesSent;
        public final long remoteFlowControlWindow;
        public final long streamsFailed;
        public final long streamsStarted;
        public final long streamsSucceeded;

        public TransportStats(long streamsStarted2, long lastLocalStreamCreatedTimeNanos2, long lastRemoteStreamCreatedTimeNanos2, long streamsSucceeded2, long streamsFailed2, long messagesSent2, long messagesReceived2, long keepAlivesSent2, long lastMessageSentTimeNanos2, long lastMessageReceivedTimeNanos2, long localFlowControlWindow2, long remoteFlowControlWindow2) {
            this.streamsStarted = streamsStarted2;
            this.lastLocalStreamCreatedTimeNanos = lastLocalStreamCreatedTimeNanos2;
            this.lastRemoteStreamCreatedTimeNanos = lastRemoteStreamCreatedTimeNanos2;
            this.streamsSucceeded = streamsSucceeded2;
            this.streamsFailed = streamsFailed2;
            this.messagesSent = messagesSent2;
            this.messagesReceived = messagesReceived2;
            this.keepAlivesSent = keepAlivesSent2;
            this.lastMessageSentTimeNanos = lastMessageSentTimeNanos2;
            this.lastMessageReceivedTimeNanos = lastMessageReceivedTimeNanos2;
            this.localFlowControlWindow = localFlowControlWindow2;
            this.remoteFlowControlWindow = remoteFlowControlWindow2;
        }
    }

    /* renamed from: id */
    public static long m346id(InternalWithLogId withLogId) {
        return withLogId.getLogId().getId();
    }
}
