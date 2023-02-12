package com.squareup.okhttp.internal.http;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.net.HttpHeaders;
import com.squareup.okhttp.Address;
import com.squareup.okhttp.CertificatePinner;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.Version;
import com.squareup.okhttp.internal.http.CacheStrategy;
import java.io.Closeable;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.ProtocolException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class HttpEngine {
    private static final ResponseBody EMPTY_BODY = new ResponseBody() {
        public MediaType contentType() {
            return null;
        }

        public long contentLength() {
            return 0;
        }

        public BufferedSource source() {
            return new Buffer();
        }
    };
    public static final int MAX_FOLLOW_UPS = 20;
    public final boolean bufferRequestBody;
    private BufferedSink bufferedRequestBody;
    private Response cacheResponse;
    private CacheStrategy cacheStrategy;
    private final boolean callerWritesRequestBody;
    final OkHttpClient client;
    private final boolean forWebSocket;
    /* access modifiers changed from: private */
    public HttpStream httpStream;
    /* access modifiers changed from: private */
    public Request networkRequest;
    private final Response priorResponse;
    private Sink requestBodyOut;
    long sentRequestMillis = -1;
    private CacheRequest storeRequest;
    public final StreamAllocation streamAllocation;
    private boolean transparentGzip;
    private final Request userRequest;
    private Response userResponse;

    public HttpEngine(OkHttpClient client2, Request request, boolean bufferRequestBody2, boolean callerWritesRequestBody2, boolean forWebSocket2, StreamAllocation streamAllocation2, RetryableSink requestBodyOut2, Response priorResponse2) {
        StreamAllocation streamAllocation3;
        this.client = client2;
        this.userRequest = request;
        this.bufferRequestBody = bufferRequestBody2;
        this.callerWritesRequestBody = callerWritesRequestBody2;
        this.forWebSocket = forWebSocket2;
        if (streamAllocation2 != null) {
            streamAllocation3 = streamAllocation2;
        } else {
            streamAllocation3 = new StreamAllocation(client2.getConnectionPool(), createAddress(client2, request));
        }
        this.streamAllocation = streamAllocation3;
        this.requestBodyOut = requestBodyOut2;
        this.priorResponse = priorResponse2;
    }

    public void sendRequest() throws RequestException, RouteException, IOException {
        if (this.cacheStrategy == null) {
            if (this.httpStream == null) {
                Request request = networkRequest(this.userRequest);
                InternalCache responseCache = Internal.instance.internalCache(this.client);
                Response cacheCandidate = responseCache != null ? responseCache.get(request) : null;
                CacheStrategy cacheStrategy2 = new CacheStrategy.Factory(System.currentTimeMillis(), request, cacheCandidate).get();
                this.cacheStrategy = cacheStrategy2;
                this.networkRequest = cacheStrategy2.networkRequest;
                this.cacheResponse = this.cacheStrategy.cacheResponse;
                if (responseCache != null) {
                    responseCache.trackResponse(this.cacheStrategy);
                }
                if (cacheCandidate != null && this.cacheResponse == null) {
                    Util.closeQuietly((Closeable) cacheCandidate.body());
                }
                if (this.networkRequest != null) {
                    HttpStream connect = connect();
                    this.httpStream = connect;
                    connect.setHttpEngine(this);
                    if (this.callerWritesRequestBody && permitsRequestBody(this.networkRequest) && this.requestBodyOut == null) {
                        long contentLength = OkHeaders.contentLength(request);
                        if (!this.bufferRequestBody) {
                            this.httpStream.writeRequestHeaders(this.networkRequest);
                            this.requestBodyOut = this.httpStream.createRequestBody(this.networkRequest, contentLength);
                        } else if (contentLength > 2147483647L) {
                            throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
                        } else if (contentLength != -1) {
                            this.httpStream.writeRequestHeaders(this.networkRequest);
                            this.requestBodyOut = new RetryableSink((int) contentLength);
                        } else {
                            this.requestBodyOut = new RetryableSink();
                        }
                    }
                } else {
                    Response response = this.cacheResponse;
                    if (response != null) {
                        this.userResponse = response.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).cacheResponse(stripBody(this.cacheResponse)).build();
                    } else {
                        this.userResponse = new Response.Builder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).protocol(Protocol.HTTP_1_1).code(TypedValues.PositionType.TYPE_PERCENT_HEIGHT).message("Unsatisfiable Request (only-if-cached)").body(EMPTY_BODY).build();
                    }
                    this.userResponse = unzip(this.userResponse);
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private HttpStream connect() throws RouteException, RequestException, IOException {
        return this.streamAllocation.newStream(this.client.getConnectTimeout(), this.client.getReadTimeout(), this.client.getWriteTimeout(), this.client.getRetryOnConnectionFailure(), !this.networkRequest.method().equals("GET"));
    }

    private static Response stripBody(Response response) {
        return (response == null || response.body() == null) ? response : response.newBuilder().body((ResponseBody) null).build();
    }

    public void writingRequestHeaders() {
        if (this.sentRequestMillis == -1) {
            this.sentRequestMillis = System.currentTimeMillis();
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: package-private */
    public boolean permitsRequestBody(Request request) {
        return HttpMethod.permitsRequestBody(request.method());
    }

    public Sink getRequestBody() {
        if (this.cacheStrategy != null) {
            return this.requestBodyOut;
        }
        throw new IllegalStateException();
    }

    public BufferedSink getBufferedRequestBody() {
        BufferedSink result = this.bufferedRequestBody;
        if (result != null) {
            return result;
        }
        Sink requestBody = getRequestBody();
        if (requestBody == null) {
            return null;
        }
        BufferedSink buffer = Okio.buffer(requestBody);
        this.bufferedRequestBody = buffer;
        return buffer;
    }

    public boolean hasResponse() {
        return this.userResponse != null;
    }

    public Request getRequest() {
        return this.userRequest;
    }

    public Response getResponse() {
        Response response = this.userResponse;
        if (response != null) {
            return response;
        }
        throw new IllegalStateException();
    }

    public Connection getConnection() {
        return this.streamAllocation.connection();
    }

    public HttpEngine recover(RouteException e) {
        if (!this.streamAllocation.recover(e) || !this.client.getRetryOnConnectionFailure()) {
            return null;
        }
        return new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, this.callerWritesRequestBody, this.forWebSocket, close(), (RetryableSink) this.requestBodyOut, this.priorResponse);
    }

    public HttpEngine recover(IOException e, Sink requestBodyOut2) {
        if (!this.streamAllocation.recover(e, requestBodyOut2) || !this.client.getRetryOnConnectionFailure()) {
            return null;
        }
        return new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, this.callerWritesRequestBody, this.forWebSocket, close(), (RetryableSink) requestBodyOut2, this.priorResponse);
    }

    public HttpEngine recover(IOException e) {
        return recover(e, this.requestBodyOut);
    }

    private void maybeCache() throws IOException {
        InternalCache responseCache = Internal.instance.internalCache(this.client);
        if (responseCache != null) {
            if (CacheStrategy.isCacheable(this.userResponse, this.networkRequest)) {
                this.storeRequest = responseCache.put(stripBody(this.userResponse));
            } else if (HttpMethod.invalidatesCache(this.networkRequest.method())) {
                try {
                    responseCache.remove(this.networkRequest);
                } catch (IOException e) {
                }
            }
        }
    }

    public void releaseStreamAllocation() throws IOException {
        this.streamAllocation.release();
    }

    public void cancel() {
        this.streamAllocation.cancel();
    }

    public StreamAllocation close() {
        BufferedSink bufferedSink = this.bufferedRequestBody;
        if (bufferedSink != null) {
            Util.closeQuietly((Closeable) bufferedSink);
        } else {
            Sink sink = this.requestBodyOut;
            if (sink != null) {
                Util.closeQuietly((Closeable) sink);
            }
        }
        Response response = this.userResponse;
        if (response != null) {
            Util.closeQuietly((Closeable) response.body());
        } else {
            this.streamAllocation.connectionFailed();
        }
        return this.streamAllocation;
    }

    private Response unzip(Response response) throws IOException {
        if (!this.transparentGzip || !"gzip".equalsIgnoreCase(this.userResponse.header(HttpHeaders.CONTENT_ENCODING)) || response.body() == null) {
            return response;
        }
        GzipSource responseBody = new GzipSource(response.body().source());
        Headers strippedHeaders = response.headers().newBuilder().removeAll(HttpHeaders.CONTENT_ENCODING).removeAll(HttpHeaders.CONTENT_LENGTH).build();
        return response.newBuilder().headers(strippedHeaders).body(new RealResponseBody(strippedHeaders, Okio.buffer((Source) responseBody))).build();
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals("HEAD")) {
            return false;
        }
        int responseCode = response.code();
        if (((responseCode >= 100 && responseCode < 200) || responseCode == 204 || responseCode == 304) && OkHeaders.contentLength(response) == -1 && !"chunked".equalsIgnoreCase(response.header(HttpHeaders.TRANSFER_ENCODING))) {
            return false;
        }
        return true;
    }

    private Request networkRequest(Request request) throws IOException {
        Request.Builder result = request.newBuilder();
        if (request.header(HttpHeaders.HOST) == null) {
            result.header(HttpHeaders.HOST, Util.hostHeader(request.httpUrl()));
        }
        if (request.header(HttpHeaders.CONNECTION) == null) {
            result.header(HttpHeaders.CONNECTION, "Keep-Alive");
        }
        if (request.header(HttpHeaders.ACCEPT_ENCODING) == null) {
            this.transparentGzip = true;
            result.header(HttpHeaders.ACCEPT_ENCODING, "gzip");
        }
        CookieHandler cookieHandler = this.client.getCookieHandler();
        if (cookieHandler != null) {
            OkHeaders.addCookies(result, cookieHandler.get(request.uri(), OkHeaders.toMultimap(result.build().headers(), (String) null)));
        }
        if (request.header(HttpHeaders.USER_AGENT) == null) {
            result.header(HttpHeaders.USER_AGENT, Version.userAgent());
        }
        return result.build();
    }

    public void readResponse() throws IOException {
        Response networkResponse;
        if (this.userResponse == null) {
            Request request = this.networkRequest;
            if (request == null && this.cacheResponse == null) {
                throw new IllegalStateException("call sendRequest() first!");
            } else if (request != null) {
                if (this.forWebSocket) {
                    this.httpStream.writeRequestHeaders(request);
                    networkResponse = readNetworkResponse();
                } else if (!this.callerWritesRequestBody) {
                    networkResponse = new NetworkInterceptorChain(0, this.networkRequest).proceed(this.networkRequest);
                } else {
                    BufferedSink bufferedSink = this.bufferedRequestBody;
                    if (bufferedSink != null && bufferedSink.buffer().size() > 0) {
                        this.bufferedRequestBody.emit();
                    }
                    if (this.sentRequestMillis == -1) {
                        if (OkHeaders.contentLength(this.networkRequest) == -1) {
                            Sink sink = this.requestBodyOut;
                            if (sink instanceof RetryableSink) {
                                this.networkRequest = this.networkRequest.newBuilder().header(HttpHeaders.CONTENT_LENGTH, Long.toString(((RetryableSink) sink).contentLength())).build();
                            }
                        }
                        this.httpStream.writeRequestHeaders(this.networkRequest);
                    }
                    Sink sink2 = this.requestBodyOut;
                    if (sink2 != null) {
                        BufferedSink bufferedSink2 = this.bufferedRequestBody;
                        if (bufferedSink2 != null) {
                            bufferedSink2.close();
                        } else {
                            sink2.close();
                        }
                        Sink sink3 = this.requestBodyOut;
                        if (sink3 instanceof RetryableSink) {
                            this.httpStream.writeRequestBody((RetryableSink) sink3);
                        }
                    }
                    networkResponse = readNetworkResponse();
                }
                receiveHeaders(networkResponse.headers());
                Response response = this.cacheResponse;
                if (response != null) {
                    if (validate(response, networkResponse)) {
                        this.userResponse = this.cacheResponse.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).headers(combine(this.cacheResponse.headers(), networkResponse.headers())).cacheResponse(stripBody(this.cacheResponse)).networkResponse(stripBody(networkResponse)).build();
                        networkResponse.body().close();
                        releaseStreamAllocation();
                        InternalCache responseCache = Internal.instance.internalCache(this.client);
                        responseCache.trackConditionalCacheHit();
                        responseCache.update(this.cacheResponse, stripBody(this.userResponse));
                        this.userResponse = unzip(this.userResponse);
                        return;
                    }
                    Util.closeQuietly((Closeable) this.cacheResponse.body());
                }
                Response build = networkResponse.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).cacheResponse(stripBody(this.cacheResponse)).networkResponse(stripBody(networkResponse)).build();
                this.userResponse = build;
                if (hasBody(build)) {
                    maybeCache();
                    this.userResponse = unzip(cacheWritingResponse(this.storeRequest, this.userResponse));
                }
            }
        }
    }

    class NetworkInterceptorChain implements Interceptor.Chain {
        private int calls;
        private final int index;
        private final Request request;

        NetworkInterceptorChain(int index2, Request request2) {
            this.index = index2;
            this.request = request2;
        }

        public Connection connection() {
            return HttpEngine.this.streamAllocation.connection();
        }

        public Request request() {
            return this.request;
        }

        public Response proceed(Request request2) throws IOException {
            this.calls++;
            if (this.index > 0) {
                Interceptor caller = HttpEngine.this.client.networkInterceptors().get(this.index - 1);
                Address address = connection().getRoute().getAddress();
                if (!request2.httpUrl().host().equals(address.getUriHost()) || request2.httpUrl().port() != address.getUriPort()) {
                    throw new IllegalStateException("network interceptor " + caller + " must retain the same host and port");
                } else if (this.calls > 1) {
                    throw new IllegalStateException("network interceptor " + caller + " must call proceed() exactly once");
                }
            }
            if (this.index < HttpEngine.this.client.networkInterceptors().size()) {
                NetworkInterceptorChain chain = new NetworkInterceptorChain(this.index + 1, request2);
                Interceptor interceptor = HttpEngine.this.client.networkInterceptors().get(this.index);
                Response interceptedResponse = interceptor.intercept(chain);
                if (chain.calls != 1) {
                    throw new IllegalStateException("network interceptor " + interceptor + " must call proceed() exactly once");
                } else if (interceptedResponse != null) {
                    return interceptedResponse;
                } else {
                    throw new NullPointerException("network interceptor " + interceptor + " returned null");
                }
            } else {
                HttpEngine.this.httpStream.writeRequestHeaders(request2);
                Request unused = HttpEngine.this.networkRequest = request2;
                if (HttpEngine.this.permitsRequestBody(request2) && request2.body() != null) {
                    BufferedSink bufferedRequestBody = Okio.buffer(HttpEngine.this.httpStream.createRequestBody(request2, request2.body().contentLength()));
                    request2.body().writeTo(bufferedRequestBody);
                    bufferedRequestBody.close();
                }
                Response response = HttpEngine.this.readNetworkResponse();
                int code = response.code();
                if ((code != 204 && code != 205) || response.body().contentLength() <= 0) {
                    return response;
                }
                throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + response.body().contentLength());
            }
        }
    }

    /* access modifiers changed from: private */
    public Response readNetworkResponse() throws IOException {
        this.httpStream.finishRequest();
        Response networkResponse = this.httpStream.readResponseHeaders().request(this.networkRequest).handshake(this.streamAllocation.connection().getHandshake()).header(OkHeaders.SENT_MILLIS, Long.toString(this.sentRequestMillis)).header(OkHeaders.RECEIVED_MILLIS, Long.toString(System.currentTimeMillis())).build();
        if (!this.forWebSocket) {
            networkResponse = networkResponse.newBuilder().body(this.httpStream.openResponseBody(networkResponse)).build();
        }
        if ("close".equalsIgnoreCase(networkResponse.request().header(HttpHeaders.CONNECTION)) || "close".equalsIgnoreCase(networkResponse.header(HttpHeaders.CONNECTION))) {
            this.streamAllocation.noNewStreams();
        }
        return networkResponse;
    }

    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        Sink cacheBodyUnbuffered;
        if (cacheRequest == null || (cacheBodyUnbuffered = cacheRequest.body()) == null) {
            return response;
        }
        final BufferedSource source = response.body().source();
        final BufferedSink cacheBody = Okio.buffer(cacheBodyUnbuffered);
        return response.newBuilder().body(new RealResponseBody(response.headers(), Okio.buffer(new Source() {
            boolean cacheRequestClosed;

            public long read(Buffer sink, long byteCount) throws IOException {
                try {
                    long bytesRead = source.read(sink, byteCount);
                    if (bytesRead == -1) {
                        if (!this.cacheRequestClosed) {
                            this.cacheRequestClosed = true;
                            cacheBody.close();
                        }
                        return -1;
                    }
                    sink.copyTo(cacheBody.buffer(), sink.size() - bytesRead, bytesRead);
                    cacheBody.emitCompleteSegments();
                    return bytesRead;
                } catch (IOException e) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw e;
                }
            }

            public Timeout timeout() {
                return source.timeout();
            }

            public void close() throws IOException {
                if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                source.close();
            }
        }))).build();
    }

    private static boolean validate(Response cached, Response network) {
        Date networkLastModified;
        if (network.code() == 304) {
            return true;
        }
        Date lastModified = cached.headers().getDate(HttpHeaders.LAST_MODIFIED);
        if (lastModified == null || (networkLastModified = network.headers().getDate(HttpHeaders.LAST_MODIFIED)) == null || networkLastModified.getTime() >= lastModified.getTime()) {
            return false;
        }
        return true;
    }

    private static Headers combine(Headers cachedHeaders, Headers networkHeaders) throws IOException {
        Headers.Builder result = new Headers.Builder();
        int size = cachedHeaders.size();
        for (int i = 0; i < size; i++) {
            String fieldName = cachedHeaders.name(i);
            String value = cachedHeaders.value(i);
            if ((!HttpHeaders.WARNING.equalsIgnoreCase(fieldName) || !value.startsWith("1")) && (!OkHeaders.isEndToEnd(fieldName) || networkHeaders.get(fieldName) == null)) {
                result.add(fieldName, value);
            }
        }
        int size2 = networkHeaders.size();
        for (int i2 = 0; i2 < size2; i2++) {
            String fieldName2 = networkHeaders.name(i2);
            if (!HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(fieldName2) && OkHeaders.isEndToEnd(fieldName2)) {
                result.add(fieldName2, networkHeaders.value(i2));
            }
        }
        return result.build();
    }

    public void receiveHeaders(Headers headers) throws IOException {
        CookieHandler cookieHandler = this.client.getCookieHandler();
        if (cookieHandler != null) {
            cookieHandler.put(this.userRequest.uri(), OkHeaders.toMultimap(headers, (String) null));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0050, code lost:
        return com.squareup.okhttp.internal.http.OkHeaders.processAuthHeader(r12.client.getAuthenticator(), r12.userResponse, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0066, code lost:
        if (r12.client.getFollowRedirects() != false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0068, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0069, code lost:
        r7 = r12.userResponse.header(com.google.common.net.HttpHeaders.LOCATION);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0071, code lost:
        if (r7 != null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0073, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0074, code lost:
        r8 = r12.userRequest.httpUrl().resolve(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007e, code lost:
        if (r8 != null) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0080, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0093, code lost:
        if (r8.scheme().equals(r12.userRequest.httpUrl().scheme()) != false) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009b, code lost:
        if (r12.client.getFollowSslRedirects() != false) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009e, code lost:
        r10 = r12.userRequest.newBuilder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a8, code lost:
        if (com.squareup.okhttp.internal.http.HttpMethod.permitsRequestBody(r5) == false) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ae, code lost:
        if (com.squareup.okhttp.internal.http.HttpMethod.redirectsToGet(r5) == false) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b0, code lost:
        r10.method("GET", (com.squareup.okhttp.RequestBody) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b4, code lost:
        r10.method(r5, (com.squareup.okhttp.RequestBody) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b7, code lost:
        r10.removeHeader(com.google.common.net.HttpHeaders.TRANSFER_ENCODING);
        r10.removeHeader(com.google.common.net.HttpHeaders.CONTENT_LENGTH);
        r10.removeHeader("Content-Type");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ca, code lost:
        if (sameConnection(r8) != false) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00cc, code lost:
        r10.removeHeader(com.google.common.net.HttpHeaders.AUTHORIZATION);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d9, code lost:
        return r10.url(r8).build();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.squareup.okhttp.Request followUpRequest() throws java.io.IOException {
        /*
            r12 = this;
            com.squareup.okhttp.Response r0 = r12.userResponse
            if (r0 == 0) goto L_0x00da
            com.squareup.okhttp.internal.http.StreamAllocation r0 = r12.streamAllocation
            com.squareup.okhttp.internal.io.RealConnection r0 = r0.connection()
            r1 = 0
            if (r0 == 0) goto L_0x0012
            com.squareup.okhttp.Route r2 = r0.getRoute()
            goto L_0x0013
        L_0x0012:
            r2 = r1
        L_0x0013:
            if (r2 == 0) goto L_0x001a
            java.net.Proxy r3 = r2.getProxy()
            goto L_0x0020
        L_0x001a:
            com.squareup.okhttp.OkHttpClient r3 = r12.client
            java.net.Proxy r3 = r3.getProxy()
        L_0x0020:
            com.squareup.okhttp.Response r4 = r12.userResponse
            int r4 = r4.code()
            com.squareup.okhttp.Request r5 = r12.userRequest
            java.lang.String r5 = r5.method()
            java.lang.String r6 = "GET"
            switch(r4) {
                case 300: goto L_0x0060;
                case 301: goto L_0x0060;
                case 302: goto L_0x0060;
                case 303: goto L_0x0060;
                case 307: goto L_0x0051;
                case 308: goto L_0x0051;
                case 401: goto L_0x0044;
                case 407: goto L_0x0033;
                default: goto L_0x0032;
            }
        L_0x0032:
            return r1
        L_0x0033:
            java.net.Proxy$Type r1 = r3.type()
            java.net.Proxy$Type r6 = java.net.Proxy.Type.HTTP
            if (r1 != r6) goto L_0x003c
            goto L_0x0044
        L_0x003c:
            java.net.ProtocolException r1 = new java.net.ProtocolException
            java.lang.String r6 = "Received HTTP_PROXY_AUTH (407) code while not using proxy"
            r1.<init>(r6)
            throw r1
        L_0x0044:
            com.squareup.okhttp.OkHttpClient r1 = r12.client
            com.squareup.okhttp.Authenticator r1 = r1.getAuthenticator()
            com.squareup.okhttp.Response r6 = r12.userResponse
            com.squareup.okhttp.Request r1 = com.squareup.okhttp.internal.http.OkHeaders.processAuthHeader(r1, r6, r3)
            return r1
        L_0x0051:
            boolean r7 = r5.equals(r6)
            if (r7 != 0) goto L_0x0060
            java.lang.String r7 = "HEAD"
            boolean r7 = r5.equals(r7)
            if (r7 != 0) goto L_0x0060
            return r1
        L_0x0060:
            com.squareup.okhttp.OkHttpClient r7 = r12.client
            boolean r7 = r7.getFollowRedirects()
            if (r7 != 0) goto L_0x0069
            return r1
        L_0x0069:
            com.squareup.okhttp.Response r7 = r12.userResponse
            java.lang.String r8 = "Location"
            java.lang.String r7 = r7.header(r8)
            if (r7 != 0) goto L_0x0074
            return r1
        L_0x0074:
            com.squareup.okhttp.Request r8 = r12.userRequest
            com.squareup.okhttp.HttpUrl r8 = r8.httpUrl()
            com.squareup.okhttp.HttpUrl r8 = r8.resolve(r7)
            if (r8 != 0) goto L_0x0081
            return r1
        L_0x0081:
            java.lang.String r9 = r8.scheme()
            com.squareup.okhttp.Request r10 = r12.userRequest
            com.squareup.okhttp.HttpUrl r10 = r10.httpUrl()
            java.lang.String r10 = r10.scheme()
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x009e
            com.squareup.okhttp.OkHttpClient r10 = r12.client
            boolean r10 = r10.getFollowSslRedirects()
            if (r10 != 0) goto L_0x009e
            return r1
        L_0x009e:
            com.squareup.okhttp.Request r10 = r12.userRequest
            com.squareup.okhttp.Request$Builder r10 = r10.newBuilder()
            boolean r11 = com.squareup.okhttp.internal.http.HttpMethod.permitsRequestBody(r5)
            if (r11 == 0) goto L_0x00c6
            boolean r11 = com.squareup.okhttp.internal.http.HttpMethod.redirectsToGet(r5)
            if (r11 == 0) goto L_0x00b4
            r10.method(r6, r1)
            goto L_0x00b7
        L_0x00b4:
            r10.method(r5, r1)
        L_0x00b7:
            java.lang.String r1 = "Transfer-Encoding"
            r10.removeHeader(r1)
            java.lang.String r1 = "Content-Length"
            r10.removeHeader(r1)
            java.lang.String r1 = "Content-Type"
            r10.removeHeader(r1)
        L_0x00c6:
            boolean r1 = r12.sameConnection(r8)
            if (r1 != 0) goto L_0x00d1
            java.lang.String r1 = "Authorization"
            r10.removeHeader(r1)
        L_0x00d1:
            com.squareup.okhttp.Request$Builder r1 = r10.url((com.squareup.okhttp.HttpUrl) r8)
            com.squareup.okhttp.Request r1 = r1.build()
            return r1
        L_0x00da:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.HttpEngine.followUpRequest():com.squareup.okhttp.Request");
    }

    public boolean sameConnection(HttpUrl followUp) {
        HttpUrl url = this.userRequest.httpUrl();
        return url.host().equals(followUp.host()) && url.port() == followUp.port() && url.scheme().equals(followUp.scheme());
    }

    private static Address createAddress(OkHttpClient client2, Request request) {
        SSLSocketFactory sslSocketFactory = null;
        HostnameVerifier hostnameVerifier = null;
        CertificatePinner certificatePinner = null;
        if (request.isHttps()) {
            sslSocketFactory = client2.getSslSocketFactory();
            hostnameVerifier = client2.getHostnameVerifier();
            certificatePinner = client2.getCertificatePinner();
        }
        return new Address(request.httpUrl().host(), request.httpUrl().port(), client2.getDns(), client2.getSocketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, client2.getAuthenticator(), client2.getProxy(), client2.getProtocols(), client2.getConnectionSpecs(), client2.getProxySelector());
    }
}
