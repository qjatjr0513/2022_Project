package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firestore.p002v1.Value;

public class ServerTimestampOperation implements TransformOperation {
    private static final ServerTimestampOperation SHARED_INSTANCE = new ServerTimestampOperation();

    private ServerTimestampOperation() {
    }

    public static ServerTimestampOperation getInstance() {
        return SHARED_INSTANCE;
    }

    public Value applyToLocalView(Value previousValue, Timestamp localWriteTime) {
        return ServerTimestamps.valueOf(localWriteTime, previousValue);
    }

    public Value applyToRemoteDocument(Value previousValue, Value transformResult) {
        return transformResult;
    }

    public Value computeBaseValue(Value currentValue) {
        return null;
    }
}
