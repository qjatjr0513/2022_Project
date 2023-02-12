package com.google.firebase.firestore.model;

import com.google.firebase.firestore.util.Assert;

public final class DatabaseId implements Comparable<DatabaseId> {
    public static final String DEFAULT_DATABASE_ID = "(default)";
    public static final DatabaseId EMPTY = forDatabase("", "");
    private final String databaseId;
    private final String projectId;

    public static DatabaseId forProject(String projectId2) {
        return forDatabase(projectId2, DEFAULT_DATABASE_ID);
    }

    public static DatabaseId forDatabase(String projectId2, String databaseId2) {
        return new DatabaseId(projectId2, databaseId2);
    }

    private DatabaseId(String projectId2, String databaseId2) {
        this.projectId = projectId2;
        this.databaseId = databaseId2;
    }

    public static DatabaseId fromName(String name) {
        ResourcePath resourceName = ResourcePath.fromString(name);
        Assert.hardAssert(resourceName.length() > 3 && resourceName.getSegment(0).equals("projects") && resourceName.getSegment(2).equals("databases"), "Tried to parse an invalid resource name: %s", resourceName);
        return new DatabaseId(resourceName.getSegment(1), resourceName.getSegment(3));
    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getDatabaseId() {
        return this.databaseId;
    }

    public String toString() {
        return "DatabaseId(" + this.projectId + ", " + this.databaseId + ")";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DatabaseId that = (DatabaseId) o;
        if (!this.projectId.equals(that.projectId) || !this.databaseId.equals(that.databaseId)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.projectId.hashCode() * 31) + this.databaseId.hashCode();
    }

    public int compareTo(DatabaseId other) {
        int cmp = this.projectId.compareTo(other.projectId);
        return cmp != 0 ? cmp : this.databaseId.compareTo(other.databaseId);
    }
}
