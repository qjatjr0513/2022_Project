package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.ClientError;
import com.android.volley.Header;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.List;

final class NetworkUtility {
    private static final int SLOW_REQUEST_THRESHOLD_MS = 3000;

    private NetworkUtility() {
    }

    static void logSlowRequests(long requestLifetime, Request<?> request, byte[] responseContents, int statusCode) {
        if (VolleyLog.DEBUG || requestLifetime > 3000) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(requestLifetime);
            objArr[2] = responseContents != null ? Integer.valueOf(responseContents.length) : "null";
            objArr[3] = Integer.valueOf(statusCode);
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.m391d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    static NetworkResponse getNotModifiedNetworkResponse(Request<?> request, long requestDuration, List<Header> responseHeaders) {
        Cache.Entry entry = request.getCacheEntry();
        if (entry == null) {
            return new NetworkResponse(304, (byte[]) null, true, requestDuration, responseHeaders);
        }
        return new NetworkResponse(304, entry.data, true, requestDuration, HttpHeaderParser.combineHeaders(responseHeaders, entry));
    }

    static byte[] inputStreamToBytes(InputStream in, int contentLength, ByteArrayPool pool) throws IOException {
        PoolingByteArrayOutputStream bytes = new PoolingByteArrayOutputStream(pool, contentLength);
        byte[] buffer = null;
        try {
            buffer = pool.getBuf(1024);
            while (true) {
                int read = in.read(buffer);
                int count = read;
                if (read == -1) {
                    break;
                }
                bytes.write(buffer, 0, count);
            }
            return bytes.toByteArray();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    VolleyLog.m394v("Error occurred when closing InputStream", new Object[0]);
                }
            }
            pool.returnBuf(buffer);
            bytes.close();
        }
    }

    static void attemptRetryOnException(Request<?> request, RetryInfo retryInfo) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int oldTimeout = request.getTimeoutMs();
        try {
            retryPolicy.retry(retryInfo.errorToRetry);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{retryInfo.logPrefix, Integer.valueOf(oldTimeout)}));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{retryInfo.logPrefix, Integer.valueOf(oldTimeout)}));
            throw e;
        }
    }

    static class RetryInfo {
        /* access modifiers changed from: private */
        public final VolleyError errorToRetry;
        /* access modifiers changed from: private */
        public final String logPrefix;

        private RetryInfo(String logPrefix2, VolleyError errorToRetry2) {
            this.logPrefix = logPrefix2;
            this.errorToRetry = errorToRetry2;
        }
    }

    static RetryInfo shouldRetryException(Request<?> request, IOException exception, long requestStartMs, HttpResponse httpResponse, byte[] responseContents) throws VolleyError {
        IOException iOException = exception;
        if (iOException instanceof SocketTimeoutException) {
            return new RetryInfo("socket", new TimeoutError());
        }
        if (iOException instanceof MalformedURLException) {
            throw new RuntimeException("Bad URL " + request.getUrl(), exception);
        } else if (httpResponse != null) {
            int statusCode = httpResponse.getStatusCode();
            VolleyLog.m392e("Unexpected response code %d for %s", Integer.valueOf(statusCode), request.getUrl());
            if (responseContents == null) {
                return new RetryInfo("network", new NetworkError());
            }
            int i = statusCode;
            byte[] bArr = responseContents;
            NetworkResponse networkResponse = new NetworkResponse(i, bArr, false, SystemClock.elapsedRealtime() - requestStartMs, httpResponse.getHeaders());
            if (statusCode == 401 || statusCode == 403) {
                return new RetryInfo("auth", new AuthFailureError(networkResponse));
            }
            if (statusCode >= 400 && statusCode <= 499) {
                throw new ClientError(networkResponse);
            } else if (statusCode >= 500 && statusCode <= 599 && request.shouldRetryServerErrors()) {
                return new RetryInfo("server", new ServerError(networkResponse));
            } else {
                throw new ServerError(networkResponse);
            }
        } else if (request.shouldRetryConnectionErrors() != 0) {
            return new RetryInfo("connection", new NoConnectionError());
        } else {
            throw new NoConnectionError(exception);
        }
    }
}
