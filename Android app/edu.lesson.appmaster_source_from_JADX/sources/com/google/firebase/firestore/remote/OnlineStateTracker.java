package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.core.OnlineState;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import java.util.Locale;
import p004io.grpc.Status;

class OnlineStateTracker {
    private static final String LOG_TAG = "OnlineStateTracker";
    private static final int MAX_WATCH_STREAM_FAILURES = 1;
    private static final int ONLINE_STATE_TIMEOUT_MS = 10000;
    private final OnlineStateCallback onlineStateCallback;
    private AsyncQueue.DelayedTask onlineStateTimer;
    private boolean shouldWarnClientIsOffline = true;
    private OnlineState state = OnlineState.UNKNOWN;
    private int watchStreamFailures;
    private final AsyncQueue workerQueue;

    interface OnlineStateCallback {
        void handleOnlineStateChange(OnlineState onlineState);
    }

    OnlineStateTracker(AsyncQueue workerQueue2, OnlineStateCallback onlineStateCallback2) {
        this.workerQueue = workerQueue2;
        this.onlineStateCallback = onlineStateCallback2;
    }

    /* access modifiers changed from: package-private */
    public OnlineState getState() {
        return this.state;
    }

    /* access modifiers changed from: package-private */
    public void handleWatchStreamStart() {
        if (this.watchStreamFailures == 0) {
            setAndBroadcastState(OnlineState.UNKNOWN);
            Assert.hardAssert(this.onlineStateTimer == null, "onlineStateTimer shouldn't be started yet", new Object[0]);
            this.onlineStateTimer = this.workerQueue.enqueueAfterDelay(AsyncQueue.TimerId.ONLINE_STATE_TIMEOUT, 10000, new OnlineStateTracker$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: lambda$handleWatchStreamStart$0$com-google-firebase-firestore-remote-OnlineStateTracker */
    public /* synthetic */ void mo9836x16bdafa1() {
        this.onlineStateTimer = null;
        Assert.hardAssert(this.state == OnlineState.UNKNOWN, "Timer should be canceled if we transitioned to a different state.", new Object[0]);
        logClientOfflineWarningIfNecessary(String.format(Locale.ENGLISH, "Backend didn't respond within %d seconds\n", new Object[]{10}));
        setAndBroadcastState(OnlineState.OFFLINE);
    }

    /* access modifiers changed from: package-private */
    public void handleWatchStreamFailure(Status status) {
        boolean z = true;
        if (this.state == OnlineState.ONLINE) {
            setAndBroadcastState(OnlineState.UNKNOWN);
            Assert.hardAssert(this.watchStreamFailures == 0, "watchStreamFailures must be 0", new Object[0]);
            if (this.onlineStateTimer != null) {
                z = false;
            }
            Assert.hardAssert(z, "onlineStateTimer must be null", new Object[0]);
            return;
        }
        int i = this.watchStreamFailures + 1;
        this.watchStreamFailures = i;
        if (i >= 1) {
            clearOnlineStateTimer();
            logClientOfflineWarningIfNecessary(String.format(Locale.ENGLISH, "Connection failed %d times. Most recent error: %s", new Object[]{1, status}));
            setAndBroadcastState(OnlineState.OFFLINE);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateState(OnlineState newState) {
        clearOnlineStateTimer();
        this.watchStreamFailures = 0;
        if (newState == OnlineState.ONLINE) {
            this.shouldWarnClientIsOffline = false;
        }
        setAndBroadcastState(newState);
    }

    private void setAndBroadcastState(OnlineState newState) {
        if (newState != this.state) {
            this.state = newState;
            this.onlineStateCallback.handleOnlineStateChange(newState);
        }
    }

    private void logClientOfflineWarningIfNecessary(String reason) {
        String message = String.format("Could not reach Cloud Firestore backend. %s\nThis typically indicates that your device does not have a healthy Internet connection at the moment. The client will operate in offline mode until it is able to successfully connect to the backend.", new Object[]{reason});
        if (this.shouldWarnClientIsOffline) {
            Logger.warn(LOG_TAG, "%s", message);
            this.shouldWarnClientIsOffline = false;
            return;
        }
        Logger.debug(LOG_TAG, "%s", message);
    }

    private void clearOnlineStateTimer() {
        AsyncQueue.DelayedTask delayedTask = this.onlineStateTimer;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.onlineStateTimer = null;
        }
    }
}
