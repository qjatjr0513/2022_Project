package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AsyncHttpStack extends BaseHttpStack {
    private ExecutorService mBlockingExecutor;
    private ExecutorService mNonBlockingExecutor;

    public interface OnRequestComplete {
        void onAuthError(AuthFailureError authFailureError);

        void onError(IOException iOException);

        void onSuccess(HttpResponse httpResponse);
    }

    public abstract void executeRequest(Request<?> request, Map<String, String> map, OnRequestComplete onRequestComplete);

    public void setNonBlockingExecutor(ExecutorService executor) {
        this.mNonBlockingExecutor = executor;
    }

    public void setBlockingExecutor(ExecutorService executor) {
        this.mBlockingExecutor = executor;
    }

    /* access modifiers changed from: protected */
    public ExecutorService getBlockingExecutor() {
        return this.mBlockingExecutor;
    }

    /* access modifiers changed from: protected */
    public ExecutorService getNonBlockingExecutor() {
        return this.mNonBlockingExecutor;
    }

    public final HttpResponse executeRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException, AuthFailureError {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<Response> entry = new AtomicReference<>();
        executeRequest(request, additionalHeaders, new OnRequestComplete() {
            public void onSuccess(HttpResponse httpResponse) {
                entry.set(new Response(httpResponse, (IOException) null, (AuthFailureError) null));
                latch.countDown();
            }

            public void onAuthError(AuthFailureError authFailureError) {
                entry.set(new Response((HttpResponse) null, (IOException) null, authFailureError));
                latch.countDown();
            }

            public void onError(IOException ioException) {
                entry.set(new Response((HttpResponse) null, ioException, (AuthFailureError) null));
                latch.countDown();
            }
        });
        try {
            latch.await();
            Response response = entry.get();
            if (response.httpResponse != null) {
                return response.httpResponse;
            }
            if (response.ioException != null) {
                throw response.ioException;
            }
            throw response.authFailureError;
        } catch (InterruptedException e) {
            VolleyLog.m393e(e, "while waiting for CountDownLatch", new Object[0]);
            Thread.currentThread().interrupt();
            throw new InterruptedIOException(e.toString());
        }
    }

    private static class Response {
        AuthFailureError authFailureError;
        HttpResponse httpResponse;
        IOException ioException;

        private Response(HttpResponse httpResponse2, IOException ioException2, AuthFailureError authFailureError2) {
            this.httpResponse = httpResponse2;
            this.ioException = ioException2;
            this.authFailureError = authFailureError2;
        }
    }
}
