package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.AsyncNetwork;
import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestTask;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.AsyncHttpStack;
import com.android.volley.toolbox.NetworkUtility;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class BasicAsyncNetwork extends AsyncNetwork {
    private final AsyncHttpStack mAsyncStack;
    /* access modifiers changed from: private */
    public final ByteArrayPool mPool;

    private BasicAsyncNetwork(AsyncHttpStack httpStack, ByteArrayPool pool) {
        this.mAsyncStack = httpStack;
        this.mPool = pool;
    }

    public void setBlockingExecutor(ExecutorService executor) {
        super.setBlockingExecutor(executor);
        this.mAsyncStack.setBlockingExecutor(executor);
    }

    public void setNonBlockingExecutor(ExecutorService executor) {
        super.setNonBlockingExecutor(executor);
        this.mAsyncStack.setNonBlockingExecutor(executor);
    }

    /* access modifiers changed from: private */
    public void onRequestSucceeded(Request<?> request, long requestStartMs, HttpResponse httpResponse, AsyncNetwork.OnRequestComplete callback) {
        byte[] responseContents;
        int statusCode = httpResponse.getStatusCode();
        List<Header> responseHeaders = httpResponse.getHeaders();
        if (statusCode == 304) {
            AsyncNetwork.OnRequestComplete onRequestComplete = callback;
            onRequestComplete.onSuccess(NetworkUtility.getNotModifiedNetworkResponse(request, SystemClock.elapsedRealtime() - requestStartMs, responseHeaders));
            return;
        }
        Request<?> request2 = request;
        AsyncNetwork.OnRequestComplete onRequestComplete2 = callback;
        byte[] responseContents2 = httpResponse.getContentBytes();
        if (responseContents2 == null && httpResponse.getContent() == null) {
            responseContents = new byte[0];
        } else {
            responseContents = responseContents2;
        }
        if (responseContents != null) {
            onResponseRead(requestStartMs, statusCode, httpResponse, request, callback, responseHeaders, responseContents);
            return;
        }
        InputStream inputStream = httpResponse.getContent();
        ResponseParsingTask responseParsingTask = r0;
        List<Header> list = responseHeaders;
        List<Header> list2 = responseHeaders;
        ExecutorService blockingExecutor = getBlockingExecutor();
        ResponseParsingTask responseParsingTask2 = new ResponseParsingTask(inputStream, httpResponse, request, callback, requestStartMs, list, statusCode);
        blockingExecutor.execute(responseParsingTask);
    }

    /* access modifiers changed from: private */
    public void onRequestFailed(Request<?> request, AsyncNetwork.OnRequestComplete callback, IOException exception, long requestStartMs, HttpResponse httpResponse, byte[] responseContents) {
        try {
            getBlockingExecutor().execute(new InvokeRetryPolicyTask(request, NetworkUtility.shouldRetryException(request, exception, requestStartMs, httpResponse, responseContents), callback));
        } catch (VolleyError volleyError) {
            callback.onError(volleyError);
        }
    }

    private class InvokeRetryPolicyTask<T> extends RequestTask<T> {
        final AsyncNetwork.OnRequestComplete callback;
        final Request<T> request;
        final NetworkUtility.RetryInfo retryInfo;

        InvokeRetryPolicyTask(Request<T> request2, NetworkUtility.RetryInfo retryInfo2, AsyncNetwork.OnRequestComplete callback2) {
            super(request2);
            this.request = request2;
            this.retryInfo = retryInfo2;
            this.callback = callback2;
        }

        public void run() {
            try {
                NetworkUtility.attemptRetryOnException(this.request, this.retryInfo);
                BasicAsyncNetwork.this.performRequest(this.request, this.callback);
            } catch (VolleyError e) {
                this.callback.onError(e);
            }
        }
    }

    public void performRequest(Request<?> request, AsyncNetwork.OnRequestComplete callback) {
        if (getBlockingExecutor() != null) {
            long requestStartMs = SystemClock.elapsedRealtime();
            final Request<?> request2 = request;
            final long j = requestStartMs;
            final AsyncNetwork.OnRequestComplete onRequestComplete = callback;
            this.mAsyncStack.executeRequest(request, HttpHeaderParser.getCacheHeaders(request.getCacheEntry()), new AsyncHttpStack.OnRequestComplete() {
                public void onSuccess(HttpResponse httpResponse) {
                    BasicAsyncNetwork.this.onRequestSucceeded(request2, j, httpResponse, onRequestComplete);
                }

                public void onAuthError(AuthFailureError authFailureError) {
                    onRequestComplete.onError(authFailureError);
                }

                public void onError(IOException ioException) {
                    BasicAsyncNetwork.this.onRequestFailed(request2, onRequestComplete, ioException, j, (HttpResponse) null, (byte[]) null);
                }
            });
            return;
        }
        throw new IllegalStateException("mBlockingExecuter must be set before making a request");
    }

    /* access modifiers changed from: private */
    public void onResponseRead(long requestStartMs, int statusCode, HttpResponse httpResponse, Request<?> request, AsyncNetwork.OnRequestComplete callback, List<Header> responseHeaders, byte[] responseContents) {
        int i = statusCode;
        NetworkUtility.logSlowRequests(SystemClock.elapsedRealtime() - requestStartMs, request, responseContents, i);
        if (i < 200) {
            AsyncNetwork.OnRequestComplete onRequestComplete = callback;
        } else if (i > 299) {
            AsyncNetwork.OnRequestComplete onRequestComplete2 = callback;
        } else {
            AsyncNetwork.OnRequestComplete onRequestComplete3 = callback;
            onRequestComplete3.onSuccess(new NetworkResponse(statusCode, responseContents, false, SystemClock.elapsedRealtime() - requestStartMs, responseHeaders));
            return;
        }
        onRequestFailed(request, callback, new IOException(), requestStartMs, httpResponse, responseContents);
    }

    private class ResponseParsingTask<T> extends RequestTask<T> {
        AsyncNetwork.OnRequestComplete callback;
        HttpResponse httpResponse;
        InputStream inputStream;
        Request<T> request;
        long requestStartMs;
        List<Header> responseHeaders;
        int statusCode;

        ResponseParsingTask(InputStream inputStream2, HttpResponse httpResponse2, Request<T> request2, AsyncNetwork.OnRequestComplete callback2, long requestStartMs2, List<Header> responseHeaders2, int statusCode2) {
            super(request2);
            this.inputStream = inputStream2;
            this.httpResponse = httpResponse2;
            this.request = request2;
            this.callback = callback2;
            this.requestStartMs = requestStartMs2;
            this.responseHeaders = responseHeaders2;
            this.statusCode = statusCode2;
        }

        public void run() {
            try {
                BasicAsyncNetwork.this.onResponseRead(this.requestStartMs, this.statusCode, this.httpResponse, this.request, this.callback, this.responseHeaders, NetworkUtility.inputStreamToBytes(this.inputStream, this.httpResponse.getContentLength(), BasicAsyncNetwork.this.mPool));
            } catch (IOException e) {
                BasicAsyncNetwork.this.onRequestFailed(this.request, this.callback, e, this.requestStartMs, this.httpResponse, (byte[]) null);
            }
        }
    }

    public static class Builder {
        private static final int DEFAULT_POOL_SIZE = 4096;
        private AsyncHttpStack mAsyncStack;
        private ByteArrayPool mPool = null;

        public Builder(AsyncHttpStack httpStack) {
            this.mAsyncStack = httpStack;
        }

        public Builder setPool(ByteArrayPool pool) {
            this.mPool = pool;
            return this;
        }

        public BasicAsyncNetwork build() {
            if (this.mPool == null) {
                this.mPool = new ByteArrayPool(4096);
            }
            return new BasicAsyncNetwork(this.mAsyncStack, this.mPool);
        }
    }
}
