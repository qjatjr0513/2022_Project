package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.remote.Stream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.p002v1.FirestoreGrpc;
import com.google.firestore.p002v1.WriteRequest;
import com.google.firestore.p002v1.WriteResponse;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WriteStream extends AbstractStream<WriteRequest, WriteResponse, Callback> {
    public static final ByteString EMPTY_STREAM_TOKEN = ByteString.EMPTY;
    protected boolean handshakeComplete = false;
    private ByteString lastStreamToken = EMPTY_STREAM_TOKEN;
    private final RemoteSerializer serializer;

    public interface Callback extends Stream.StreamCallback {
        void onHandshakeComplete();

        void onWriteResponse(SnapshotVersion snapshotVersion, List<MutationResult> list);
    }

    public /* bridge */ /* synthetic */ void inhibitBackoff() {
        super.inhibitBackoff();
    }

    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    public /* bridge */ /* synthetic */ boolean isStarted() {
        return super.isStarted();
    }

    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    WriteStream(FirestoreChannel channel, AsyncQueue workerQueue, RemoteSerializer serializer2, Callback listener) {
        super(channel, FirestoreGrpc.getWriteMethod(), workerQueue, AsyncQueue.TimerId.WRITE_STREAM_CONNECTION_BACKOFF, AsyncQueue.TimerId.WRITE_STREAM_IDLE, AsyncQueue.TimerId.HEALTH_CHECK_TIMEOUT, listener);
        this.serializer = serializer2;
    }

    public void start() {
        this.handshakeComplete = false;
        super.start();
    }

    /* access modifiers changed from: protected */
    public void tearDown() {
        if (this.handshakeComplete) {
            writeMutations(Collections.emptyList());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isHandshakeComplete() {
        return this.handshakeComplete;
    }

    /* access modifiers changed from: package-private */
    public ByteString getLastStreamToken() {
        return this.lastStreamToken;
    }

    /* access modifiers changed from: package-private */
    public void setLastStreamToken(ByteString streamToken) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(streamToken);
    }

    /* access modifiers changed from: package-private */
    public void writeHandshake() {
        Assert.hardAssert(isOpen(), "Writing handshake requires an opened stream", new Object[0]);
        Assert.hardAssert(!this.handshakeComplete, "Handshake already completed", new Object[0]);
        writeRequest((WriteRequest) WriteRequest.newBuilder().setDatabase(this.serializer.databaseName()).build());
    }

    /* access modifiers changed from: package-private */
    public void writeMutations(List<Mutation> mutations) {
        Assert.hardAssert(isOpen(), "Writing mutations requires an opened stream", new Object[0]);
        Assert.hardAssert(this.handshakeComplete, "Handshake must be complete before writing mutations", new Object[0]);
        WriteRequest.Builder request = WriteRequest.newBuilder();
        for (Mutation mutation : mutations) {
            request.addWrites(this.serializer.encodeMutation(mutation));
        }
        request.setStreamToken(this.lastStreamToken);
        writeRequest((WriteRequest) request.build());
    }

    public void onNext(WriteResponse response) {
        this.lastStreamToken = response.getStreamToken();
        if (!this.handshakeComplete) {
            this.handshakeComplete = true;
            ((Callback) this.listener).onHandshakeComplete();
            return;
        }
        this.backoff.reset();
        SnapshotVersion commitVersion = this.serializer.decodeVersion(response.getCommitTime());
        int count = response.getWriteResultsCount();
        List<MutationResult> results = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            results.add(this.serializer.decodeMutationResult(response.getWriteResults(i), commitVersion));
        }
        ((Callback) this.listener).onWriteResponse(commitVersion, results);
    }
}
