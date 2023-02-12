package com.android.volley;

public abstract class RequestTask<T> implements Runnable {
    final Request<T> mRequest;

    public RequestTask(Request<T> request) {
        this.mRequest = request;
    }

    public int compareTo(RequestTask<?> other) {
        return this.mRequest.compareTo(other.mRequest);
    }
}
