package com.google.firebase.platforminfo;

import javax.annotation.Nonnull;

abstract class LibraryVersion {
    @Nonnull
    public abstract String getLibraryName();

    @Nonnull
    public abstract String getVersion();

    LibraryVersion() {
    }

    /* access modifiers changed from: package-private */
    public static LibraryVersion create(String name, String version) {
        return new AutoValue_LibraryVersion(name, version);
    }
}
