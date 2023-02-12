package com.google.firebase.database.connection;

import java.util.Collections;
import java.util.List;

public class CompoundHash {
    private final List<String> hashes;
    private final List<List<String>> posts;

    public CompoundHash(List<List<String>> posts2, List<String> hashes2) {
        if (posts2.size() == hashes2.size() - 1) {
            this.posts = posts2;
            this.hashes = hashes2;
            return;
        }
        throw new IllegalArgumentException("Number of posts need to be n-1 for n hashes in CompoundHash");
    }

    public List<List<String>> getPosts() {
        return Collections.unmodifiableList(this.posts);
    }

    public List<String> getHashes() {
        return Collections.unmodifiableList(this.hashes);
    }
}
