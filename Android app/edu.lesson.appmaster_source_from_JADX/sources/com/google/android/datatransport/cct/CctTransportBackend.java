package com.google.android.datatransport.cct;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.backend.cct.BuildConfig;
import com.google.android.datatransport.cct.internal.AndroidClientInfo;
import com.google.android.datatransport.cct.internal.BatchedLogRequest;
import com.google.android.datatransport.cct.internal.ClientInfo;
import com.google.android.datatransport.cct.internal.LogEvent;
import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.android.datatransport.cct.internal.LogResponse;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;
import com.google.android.datatransport.cct.internal.QosTier;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.retries.Retries;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.common.net.HttpHeaders;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.logging.type.LogSeverity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import p004io.grpc.internal.GrpcUtil;

final class CctTransportBackend implements TransportBackend {
    private static final String ACCEPT_ENCODING_HEADER_KEY = "Accept-Encoding";
    static final String API_KEY_HEADER_KEY = "X-Goog-Api-Key";
    private static final int CONNECTION_TIME_OUT = 30000;
    private static final String CONTENT_ENCODING_HEADER_KEY = "Content-Encoding";
    private static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    private static final String GZIP_CONTENT_ENCODING = "gzip";
    private static final int INVALID_VERSION_CODE = -1;
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String KEY_APPLICATION_BUILD = "application_build";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_DEVICE = "device";
    private static final String KEY_FINGERPRINT = "fingerprint";
    private static final String KEY_HARDWARE = "hardware";
    private static final String KEY_LOCALE = "locale";
    private static final String KEY_MANUFACTURER = "manufacturer";
    private static final String KEY_MCC_MNC = "mcc_mnc";
    static final String KEY_MOBILE_SUBTYPE = "mobile-subtype";
    private static final String KEY_MODEL = "model";
    static final String KEY_NETWORK_TYPE = "net-type";
    private static final String KEY_OS_BUILD = "os-uild";
    private static final String KEY_PRODUCT = "product";
    private static final String KEY_SDK_VERSION = "sdk-version";
    private static final String KEY_TIMEZONE_OFFSET = "tz-offset";
    private static final String LOG_TAG = "CctTransportBackend";
    private static final int READ_TIME_OUT = 40000;
    private final Context applicationContext;
    private final ConnectivityManager connectivityManager;
    private final DataEncoder dataEncoder;
    final URL endPoint;
    private final int readTimeout;
    private final Clock uptimeClock;
    private final Clock wallTimeClock;

    private static URL parseUrlOrThrow(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid url: " + url, e);
        }
    }

    CctTransportBackend(Context applicationContext2, Clock wallTimeClock2, Clock uptimeClock2, int readTimeout2) {
        this.dataEncoder = BatchedLogRequest.createDataEncoder();
        this.applicationContext = applicationContext2;
        this.connectivityManager = (ConnectivityManager) applicationContext2.getSystemService("connectivity");
        this.endPoint = parseUrlOrThrow(CCTDestination.DEFAULT_END_POINT);
        this.uptimeClock = uptimeClock2;
        this.wallTimeClock = wallTimeClock2;
        this.readTimeout = readTimeout2;
    }

    CctTransportBackend(Context applicationContext2, Clock wallTimeClock2, Clock uptimeClock2) {
        this(applicationContext2, wallTimeClock2, uptimeClock2, READ_TIME_OUT);
    }

    private static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    private static int getPackageVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Logging.m401e(LOG_TAG, "Unable to find version code for package", e);
            return -1;
        }
    }

    public EventInternal decorate(EventInternal eventInternal) {
        NetworkInfo networkInfo = this.connectivityManager.getActiveNetworkInfo();
        return eventInternal.toBuilder().addMetadata(KEY_SDK_VERSION, Build.VERSION.SDK_INT).addMetadata(KEY_MODEL, Build.MODEL).addMetadata(KEY_HARDWARE, Build.HARDWARE).addMetadata(KEY_DEVICE, Build.DEVICE).addMetadata(KEY_PRODUCT, Build.PRODUCT).addMetadata(KEY_OS_BUILD, Build.ID).addMetadata(KEY_MANUFACTURER, Build.MANUFACTURER).addMetadata(KEY_FINGERPRINT, Build.FINGERPRINT).addMetadata(KEY_TIMEZONE_OFFSET, getTzOffset()).addMetadata(KEY_NETWORK_TYPE, getNetTypeValue(networkInfo)).addMetadata(KEY_MOBILE_SUBTYPE, getNetSubtypeValue(networkInfo)).addMetadata(KEY_COUNTRY, Locale.getDefault().getCountry()).addMetadata(KEY_LOCALE, Locale.getDefault().getLanguage()).addMetadata(KEY_MCC_MNC, getTelephonyManager(this.applicationContext).getSimOperator()).addMetadata(KEY_APPLICATION_BUILD, Integer.toString(getPackageVersionCode(this.applicationContext))).build();
    }

    private static int getNetTypeValue(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return NetworkConnectionInfo.NetworkType.NONE.getValue();
        }
        return networkInfo.getType();
    }

    private static int getNetSubtypeValue(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return NetworkConnectionInfo.MobileSubtype.UNKNOWN_MOBILE_SUBTYPE.getValue();
        }
        int subtype = networkInfo.getSubtype();
        if (subtype == -1) {
            return NetworkConnectionInfo.MobileSubtype.COMBINED.getValue();
        }
        if (NetworkConnectionInfo.MobileSubtype.forNumber(subtype) != null) {
            return subtype;
        }
        return 0;
    }

    private BatchedLogRequest getRequestBody(BackendRequest backendRequest) {
        LogEvent.Builder event;
        HashMap hashMap = new HashMap();
        for (EventInternal eventInternal : backendRequest.getEvents()) {
            String key = eventInternal.getTransportName();
            if (!hashMap.containsKey(key)) {
                List<EventInternal> eventInternalList = new ArrayList<>();
                eventInternalList.add(eventInternal);
                hashMap.put(key, eventInternalList);
            } else {
                ((List) hashMap.get(key)).add(eventInternal);
            }
        }
        List<LogRequest> batchedRequests = new ArrayList<>();
        for (Map.Entry<String, List<EventInternal>> entry : hashMap.entrySet()) {
            EventInternal firstEvent = (EventInternal) entry.getValue().get(0);
            LogRequest.Builder requestBuilder = LogRequest.builder().setQosTier(QosTier.DEFAULT).setRequestTimeMs(this.wallTimeClock.getTime()).setRequestUptimeMs(this.uptimeClock.getTime()).setClientInfo(ClientInfo.builder().setClientType(ClientInfo.ClientType.ANDROID_FIREBASE).setAndroidClientInfo(AndroidClientInfo.builder().setSdkVersion(Integer.valueOf(firstEvent.getInteger(KEY_SDK_VERSION))).setModel(firstEvent.get(KEY_MODEL)).setHardware(firstEvent.get(KEY_HARDWARE)).setDevice(firstEvent.get(KEY_DEVICE)).setProduct(firstEvent.get(KEY_PRODUCT)).setOsBuild(firstEvent.get(KEY_OS_BUILD)).setManufacturer(firstEvent.get(KEY_MANUFACTURER)).setFingerprint(firstEvent.get(KEY_FINGERPRINT)).setCountry(firstEvent.get(KEY_COUNTRY)).setLocale(firstEvent.get(KEY_LOCALE)).setMccMnc(firstEvent.get(KEY_MCC_MNC)).setApplicationBuild(firstEvent.get(KEY_APPLICATION_BUILD)).build()).build());
            try {
                requestBuilder.setSource(Integer.parseInt(entry.getKey()));
            } catch (NumberFormatException e) {
                requestBuilder.setSource(entry.getKey());
            }
            List<LogEvent> logEvents = new ArrayList<>();
            for (EventInternal eventInternal2 : entry.getValue()) {
                EncodedPayload encodedPayload = eventInternal2.getEncodedPayload();
                Encoding encoding = encodedPayload.getEncoding();
                if (encoding.equals(Encoding.m395of("proto"))) {
                    event = LogEvent.protoBuilder(encodedPayload.getBytes());
                } else if (encoding.equals(Encoding.m395of("json"))) {
                    event = LogEvent.jsonBuilder(new String(encodedPayload.getBytes(), Charset.forName("UTF-8")));
                } else {
                    Logging.m403w(LOG_TAG, "Received event of unsupported encoding %s. Skipping...", encoding);
                }
                event.setEventTimeMs(eventInternal2.getEventMillis()).setEventUptimeMs(eventInternal2.getUptimeMillis()).setTimezoneOffsetSeconds(eventInternal2.getLong(KEY_TIMEZONE_OFFSET)).setNetworkConnectionInfo(NetworkConnectionInfo.builder().setNetworkType(NetworkConnectionInfo.NetworkType.forNumber(eventInternal2.getInteger(KEY_NETWORK_TYPE))).setMobileSubtype(NetworkConnectionInfo.MobileSubtype.forNumber(eventInternal2.getInteger(KEY_MOBILE_SUBTYPE))).build());
                if (eventInternal2.getCode() != null) {
                    event.setEventCode(eventInternal2.getCode());
                }
                logEvents.add(event.build());
            }
            requestBuilder.setLogEvents(logEvents);
            batchedRequests.add(requestBuilder.build());
        }
        return BatchedLogRequest.create(batchedRequests);
    }

    /* access modifiers changed from: private */
    public HttpResponse doSend(HttpRequest request) throws IOException {
        OutputStream outputStream;
        InputStream inputStream;
        Logging.m398d(LOG_TAG, "Making request to: %s", (Object) request.url);
        HttpURLConnection connection = (HttpURLConnection) request.url.openConnection();
        connection.setConnectTimeout(CONNECTION_TIME_OUT);
        connection.setReadTimeout(this.readTimeout);
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(GrpcUtil.HTTP_METHOD);
        connection.setRequestProperty(HttpHeaders.USER_AGENT, String.format("datatransport/%s android/", new Object[]{BuildConfig.VERSION_NAME}));
        connection.setRequestProperty("Content-Encoding", GZIP_CONTENT_ENCODING);
        connection.setRequestProperty("Content-Type", JSON_CONTENT_TYPE);
        connection.setRequestProperty("Accept-Encoding", GZIP_CONTENT_ENCODING);
        if (request.apiKey != null) {
            connection.setRequestProperty(API_KEY_HEADER_KEY, request.apiKey);
        }
        try {
            OutputStream conn = connection.getOutputStream();
            try {
                outputStream = new GZIPOutputStream(conn);
                this.dataEncoder.encode(request.requestBody, new BufferedWriter(new OutputStreamWriter(outputStream)));
                outputStream.close();
                if (conn != null) {
                    conn.close();
                }
                int responseCode = connection.getResponseCode();
                Logging.m402i(LOG_TAG, "Status Code: " + responseCode);
                Logging.m402i(LOG_TAG, "Content-Type: " + connection.getHeaderField("Content-Type"));
                Logging.m402i(LOG_TAG, "Content-Encoding: " + connection.getHeaderField("Content-Encoding"));
                if (responseCode == 302 || responseCode == 301 || responseCode == 307) {
                    return new HttpResponse(responseCode, new URL(connection.getHeaderField(HttpHeaders.LOCATION)), 0);
                }
                if (responseCode != 200) {
                    return new HttpResponse(responseCode, (URL) null, 0);
                }
                InputStream connStream = connection.getInputStream();
                try {
                    inputStream = maybeUnGzip(connStream, connection.getHeaderField("Content-Encoding"));
                    HttpResponse httpResponse = new HttpResponse(responseCode, (URL) null, LogResponse.fromJson(new BufferedReader(new InputStreamReader(inputStream))).getNextRequestWaitMillis());
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (connStream != null) {
                        connStream.close();
                    }
                    return httpResponse;
                } catch (Throwable th) {
                    if (connStream != null) {
                        try {
                            connStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
                throw th;
                throw th;
            } catch (Throwable th3) {
                if (conn != null) {
                    conn.close();
                }
                throw th3;
            }
        } catch (ConnectException | UnknownHostException e) {
            Logging.m401e(LOG_TAG, "Couldn't open connection, returning with 500", e);
            return new HttpResponse(500, (URL) null, 0);
        } catch (EncodingException | IOException e2) {
            Logging.m401e(LOG_TAG, "Couldn't encode request, returning with 400", e2);
            return new HttpResponse(LogSeverity.WARNING_VALUE, (URL) null, 0);
        } catch (Throwable th4) {
            th3.addSuppressed(th4);
        }
    }

    private static InputStream maybeUnGzip(InputStream input, String contentEncoding) throws IOException {
        if (GZIP_CONTENT_ENCODING.equals(contentEncoding)) {
            return new GZIPInputStream(input);
        }
        return input;
    }

    public BackendResponse send(BackendRequest request) {
        BatchedLogRequest requestBody = getRequestBody(request);
        String apiKey = null;
        URL actualEndPoint = this.endPoint;
        if (request.getExtras() != null) {
            try {
                CCTDestination destination = CCTDestination.fromByteArray(request.getExtras());
                if (destination.getAPIKey() != null) {
                    apiKey = destination.getAPIKey();
                }
                if (destination.getEndPoint() != null) {
                    actualEndPoint = parseUrlOrThrow(destination.getEndPoint());
                }
            } catch (IllegalArgumentException e) {
                return BackendResponse.fatalError();
            }
        }
        try {
            HttpResponse response = (HttpResponse) Retries.retry(5, new HttpRequest(actualEndPoint, requestBody, apiKey), new CctTransportBackend$$ExternalSyntheticLambda0(this), CctTransportBackend$$ExternalSyntheticLambda1.INSTANCE);
            if (response.code == 200) {
                return BackendResponse.m396ok(response.nextRequestMillis);
            }
            if (response.code < 500) {
                if (response.code != 404) {
                    return BackendResponse.fatalError();
                }
            }
            return BackendResponse.transientError();
        } catch (IOException e2) {
            Logging.m401e(LOG_TAG, "Could not make request to the backend", e2);
            return BackendResponse.transientError();
        }
    }

    static /* synthetic */ HttpRequest lambda$send$0(HttpRequest req, HttpResponse resp) {
        if (resp.redirectUrl == null) {
            return null;
        }
        Logging.m398d(LOG_TAG, "Following redirect to: %s", (Object) resp.redirectUrl);
        return req.withUrl(resp.redirectUrl);
    }

    static long getTzOffset() {
        Calendar.getInstance();
        return (long) (TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000);
    }

    static final class HttpResponse {
        final int code;
        final long nextRequestMillis;
        final URL redirectUrl;

        HttpResponse(int code2, URL redirectUrl2, long nextRequestMillis2) {
            this.code = code2;
            this.redirectUrl = redirectUrl2;
            this.nextRequestMillis = nextRequestMillis2;
        }
    }

    static final class HttpRequest {
        final String apiKey;
        final BatchedLogRequest requestBody;
        final URL url;

        HttpRequest(URL url2, BatchedLogRequest requestBody2, String apiKey2) {
            this.url = url2;
            this.requestBody = requestBody2;
            this.apiKey = apiKey2;
        }

        /* access modifiers changed from: package-private */
        public HttpRequest withUrl(URL newUrl) {
            return new HttpRequest(newUrl, this.requestBody, this.apiKey);
        }
    }
}
