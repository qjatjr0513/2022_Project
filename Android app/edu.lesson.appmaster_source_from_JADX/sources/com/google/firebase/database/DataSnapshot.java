package com.google.firebase.database;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.utilities.Validation;
import com.google.firebase.database.core.utilities.encoding.CustomClassMapper;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.NamedNode;
import java.util.Iterator;

public class DataSnapshot {
    private final IndexedNode node;
    /* access modifiers changed from: private */
    public final DatabaseReference query;

    DataSnapshot(DatabaseReference ref, IndexedNode node2) {
        this.node = node2;
        this.query = ref;
    }

    public DataSnapshot child(String path) {
        return new DataSnapshot(this.query.child(path), IndexedNode.from(this.node.getNode().getChild(new Path(path))));
    }

    public boolean hasChild(String path) {
        if (this.query.getParent() == null) {
            Validation.validateRootPathString(path);
        } else {
            Validation.validatePathString(path);
        }
        return !this.node.getNode().getChild(new Path(path)).isEmpty();
    }

    public boolean hasChildren() {
        return this.node.getNode().getChildCount() > 0;
    }

    public boolean exists() {
        return !this.node.getNode().isEmpty();
    }

    public Object getValue() {
        return this.node.getNode().getValue();
    }

    public Object getValue(boolean useExportFormat) {
        return this.node.getNode().getValue(useExportFormat);
    }

    public <T> T getValue(Class<T> valueType) {
        return CustomClassMapper.convertToCustomClass(this.node.getNode().getValue(), valueType);
    }

    public <T> T getValue(GenericTypeIndicator<T> t) {
        return CustomClassMapper.convertToCustomClass(this.node.getNode().getValue(), t);
    }

    public long getChildrenCount() {
        return (long) this.node.getNode().getChildCount();
    }

    public DatabaseReference getRef() {
        return this.query;
    }

    public String getKey() {
        return this.query.getKey();
    }

    public Iterable<DataSnapshot> getChildren() {
        final Iterator<NamedNode> iter = this.node.iterator();
        return new Iterable<DataSnapshot>() {
            public Iterator<DataSnapshot> iterator() {
                return new Iterator<DataSnapshot>() {
                    public boolean hasNext() {
                        return iter.hasNext();
                    }

                    public DataSnapshot next() {
                        NamedNode namedNode = (NamedNode) iter.next();
                        return new DataSnapshot(DataSnapshot.this.query.child(namedNode.getName().asString()), IndexedNode.from(namedNode.getNode()));
                    }

                    public void remove() {
                        throw new UnsupportedOperationException("remove called on immutable collection");
                    }
                };
            }
        };
    }

    public Object getPriority() {
        Object priority = this.node.getNode().getPriority().getValue();
        if (priority instanceof Long) {
            return Double.valueOf((double) ((Long) priority).longValue());
        }
        return priority;
    }

    public String toString() {
        return "DataSnapshot { key = " + this.query.getKey() + ", value = " + this.node.getNode().getValue(true) + " }";
    }
}
