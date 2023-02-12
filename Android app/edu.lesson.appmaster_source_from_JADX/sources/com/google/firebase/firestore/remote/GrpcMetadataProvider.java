package com.google.firebase.firestore.remote;

import p004io.grpc.Metadata;

public interface GrpcMetadataProvider {
    void updateMetadata(Metadata metadata);
}
