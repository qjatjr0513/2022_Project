package com.google.firebase.firestore.core;

final class QueryView {
    private final Query query;
    private final int targetId;
    private final View view;

    QueryView(Query query2, int targetId2, View view2) {
        this.query = query2;
        this.targetId = targetId2;
        this.view = view2;
    }

    public Query getQuery() {
        return this.query;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public View getView() {
        return this.view;
    }
}
