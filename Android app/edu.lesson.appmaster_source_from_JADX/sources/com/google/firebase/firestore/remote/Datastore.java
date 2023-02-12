package com.google.firebase.firestore.remote;

import android.content.Context;
import android.os.Build;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.remote.WatchStream;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firestore.p002v1.BatchGetDocumentsRequest;
import com.google.firestore.p002v1.BatchGetDocumentsResponse;
import com.google.firestore.p002v1.CommitRequest;
import com.google.firestore.p002v1.CommitResponse;
import com.google.firestore.p002v1.FirestoreGrpc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLHandshakeException;
import p004io.grpc.Status;

public class Datastore {
    static final String SSL_DEPENDENCY_ERROR_MESSAGE = "The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.";
    static final Set<String> WHITE_LISTED_HEADERS = new HashSet(Arrays.asList(new String[]{"date", "x-google-backends", "x-google-netmon-label", "x-google-service", "x-google-gfe-request-trace"}));
    private final FirestoreChannel channel;
    private final DatabaseInfo databaseInfo;
    private final RemoteSerializer serializer;
    private final AsyncQueue workerQueue;

    public Datastore(DatabaseInfo databaseInfo2, AsyncQueue workerQueue2, CredentialsProvider<User> authProvider, CredentialsProvider<String> appCheckProvider, Context context, GrpcMetadataProvider metadataProvider) {
        this.databaseInfo = databaseInfo2;
        this.workerQueue = workerQueue2;
        this.serializer = new RemoteSerializer(databaseInfo2.getDatabaseId());
        this.channel = initializeChannel(databaseInfo2, workerQueue2, authProvider, appCheckProvider, context, metadataProvider);
    }

    /* access modifiers changed from: package-private */
    public FirestoreChannel initializeChannel(DatabaseInfo databaseInfo2, AsyncQueue workerQueue2, CredentialsProvider<User> authProvider, CredentialsProvider<String> appCheckProvider, Context context, GrpcMetadataProvider metadataProvider) {
        return new FirestoreChannel(workerQueue2, context, authProvider, appCheckProvider, databaseInfo2, metadataProvider);
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        this.channel.shutdown();
    }

    /* access modifiers changed from: package-private */
    public AsyncQueue getWorkerQueue() {
        return this.workerQueue;
    }

    /* access modifiers changed from: package-private */
    public DatabaseInfo getDatabaseInfo() {
        return this.databaseInfo;
    }

    /* access modifiers changed from: package-private */
    public WatchStream createWatchStream(WatchStream.Callback listener) {
        return new WatchStream(this.channel, this.workerQueue, this.serializer, listener);
    }

    /* access modifiers changed from: package-private */
    public WriteStream createWriteStream(WriteStream.Callback listener) {
        return new WriteStream(this.channel, this.workerQueue, this.serializer, listener);
    }

    public Task<List<MutationResult>> commit(List<Mutation> mutations) {
        CommitRequest.Builder builder = CommitRequest.newBuilder();
        builder.setDatabase(this.serializer.databaseName());
        for (Mutation mutation : mutations) {
            builder.addWrites(this.serializer.encodeMutation(mutation));
        }
        return this.channel.runRpc(FirestoreGrpc.getCommitMethod(), (CommitRequest) builder.build()).continueWith(this.workerQueue.getExecutor(), new Datastore$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$commit$0$com-google-firebase-firestore-remote-Datastore  reason: not valid java name */
    public /* synthetic */ List m451lambda$commit$0$comgooglefirebasefirestoreremoteDatastore(Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                this.channel.invalidateToken();
            }
            throw task.getException();
        }
        CommitResponse response = (CommitResponse) task.getResult();
        SnapshotVersion commitVersion = this.serializer.decodeVersion(response.getCommitTime());
        int count = response.getWriteResultsCount();
        ArrayList<MutationResult> results = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            results.add(this.serializer.decodeMutationResult(response.getWriteResults(i), commitVersion));
        }
        return results;
    }

    public Task<List<MutableDocument>> lookup(List<DocumentKey> keys) {
        BatchGetDocumentsRequest.Builder builder = BatchGetDocumentsRequest.newBuilder();
        builder.setDatabase(this.serializer.databaseName());
        for (DocumentKey key : keys) {
            builder.addDocuments(this.serializer.encodeKey(key));
        }
        return this.channel.runStreamingResponseRpc(FirestoreGrpc.getBatchGetDocumentsMethod(), (BatchGetDocumentsRequest) builder.build()).continueWith(this.workerQueue.getExecutor(), new Datastore$$ExternalSyntheticLambda1(this, keys));
    }

    /* renamed from: lambda$lookup$1$com-google-firebase-firestore-remote-Datastore  reason: not valid java name */
    public /* synthetic */ List m452lambda$lookup$1$comgooglefirebasefirestoreremoteDatastore(List keys, Task task) throws Exception {
        if (!task.isSuccessful() && (task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
            this.channel.invalidateToken();
        }
        Map<DocumentKey, MutableDocument> resultMap = new HashMap<>();
        for (BatchGetDocumentsResponse response : (List) task.getResult()) {
            MutableDocument doc = this.serializer.decodeMaybeDocument(response);
            resultMap.put(doc.getKey(), doc);
        }
        List<MutableDocument> results = new ArrayList<>();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            results.add(resultMap.get((DocumentKey) it.next()));
        }
        return results;
    }

    public static boolean isPermanentError(Status status) {
        return isPermanentError(FirebaseFirestoreException.Code.fromValue(status.getCode().value()));
    }

    /* renamed from: com.google.firebase.firestore.remote.Datastore$1 */
    static /* synthetic */ class C07861 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code */
        static final /* synthetic */ int[] f179xf2f5b1d8;

        static {
            int[] iArr = new int[FirebaseFirestoreException.Code.values().length];
            f179xf2f5b1d8 = iArr;
            try {
                iArr[FirebaseFirestoreException.Code.OK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.CANCELLED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.DEADLINE_EXCEEDED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.INTERNAL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.UNAVAILABLE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.UNAUTHENTICATED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.INVALID_ARGUMENT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.NOT_FOUND.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.ALREADY_EXISTS.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.PERMISSION_DENIED.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.FAILED_PRECONDITION.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.ABORTED.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.OUT_OF_RANGE.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.UNIMPLEMENTED.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f179xf2f5b1d8[FirebaseFirestoreException.Code.DATA_LOSS.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    public static boolean isPermanentError(FirebaseFirestoreException.Code code) {
        switch (C07861.f179xf2f5b1d8[code.ordinal()]) {
            case 1:
                throw new IllegalArgumentException("Treated status OK as error");
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return false;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                return true;
            default:
                throw new IllegalArgumentException("Unknown gRPC status code: " + code);
        }
    }

    public static boolean isMissingSslCiphers(Status status) {
        Status.Code code = status.getCode();
        Throwable t = status.getCause();
        boolean hasCipherError = false;
        if ((t instanceof SSLHandshakeException) && t.getMessage().contains("no ciphers available")) {
            hasCipherError = true;
        }
        return Build.VERSION.SDK_INT < 21 && code.equals(Status.Code.UNAVAILABLE) && hasCipherError;
    }

    public static boolean isPermanentWriteError(Status status) {
        return isPermanentError(status) && !status.getCode().equals(Status.Code.ABORTED);
    }
}
