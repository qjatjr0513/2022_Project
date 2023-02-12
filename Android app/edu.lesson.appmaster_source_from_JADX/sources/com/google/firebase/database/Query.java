package com.google.firebase.database;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.core.ChildEventRegistration;
import com.google.firebase.database.core.EventRegistration;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.Repo;
import com.google.firebase.database.core.ValueEventRegistration;
import com.google.firebase.database.core.ZombieEventManager;
import com.google.firebase.database.core.utilities.PushIdGenerator;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.utilities.Validation;
import com.google.firebase.database.core.view.QueryParams;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.snapshot.BooleanNode;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.DoubleNode;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.KeyIndex;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.PathIndex;
import com.google.firebase.database.snapshot.PriorityIndex;
import com.google.firebase.database.snapshot.PriorityUtilities;
import com.google.firebase.database.snapshot.StringNode;
import com.google.firebase.database.snapshot.ValueIndex;

public class Query {
    private final boolean orderByCalled;
    protected final QueryParams params;
    protected final Path path;
    protected final Repo repo;

    Query(Repo repo2, Path path2, QueryParams params2, boolean orderByCalled2) throws DatabaseException {
        this.repo = repo2;
        this.path = path2;
        this.params = params2;
        this.orderByCalled = orderByCalled2;
        Utilities.hardAssert(params2.isValid(), "Validation of queries failed.");
    }

    Query(Repo repo2, Path path2) {
        this.repo = repo2;
        this.path = path2;
        this.params = QueryParams.DEFAULT_PARAMS;
        this.orderByCalled = false;
    }

    private void validateQueryEndpoints(QueryParams params2) {
        if (params2.getIndex().equals(KeyIndex.getInstance())) {
            if (params2.hasStart()) {
                Node startNode = params2.getIndexStartValue();
                if (!Objects.equal(params2.getIndexStartName(), ChildKey.getMinName()) || !(startNode instanceof StringNode)) {
                    throw new IllegalArgumentException("You must use startAt(String value), startAfter(String value), endAt(String value), endBefore(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported");
                }
            }
            if (params2.hasEnd()) {
                Node endNode = params2.getIndexEndValue();
                if (!params2.getIndexEndName().equals(ChildKey.getMaxName()) || !(endNode instanceof StringNode)) {
                    throw new IllegalArgumentException("You must use startAt(String value), startAfter(String value), endAt(String value), endBefore(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported");
                }
            }
        } else if (!params2.getIndex().equals(PriorityIndex.getInstance())) {
        } else {
            if ((params2.hasStart() && !PriorityUtilities.isValidPriority(params2.getIndexStartValue())) || (params2.hasEnd() && !PriorityUtilities.isValidPriority(params2.getIndexEndValue()))) {
                throw new IllegalArgumentException("When using orderByPriority(), values provided to startAt(), startAfter(), endAt(), endBefore(), or equalTo() must be valid priorities.");
            }
        }
    }

    private void validateLimit(QueryParams params2) {
        if (params2.hasStart() && params2.hasEnd() && params2.hasLimit() && !params2.hasAnchoredLimit()) {
            throw new IllegalArgumentException("Can't combine startAt(), startAfter(), endAt(), endBefore(), and limit(). Use limitToFirst() or limitToLast() instead");
        }
    }

    private void validateEqualToCall() {
        if (this.params.hasStart()) {
            throw new IllegalArgumentException("Cannot combine equalTo() with startAt() or startAfter()");
        } else if (this.params.hasEnd()) {
            throw new IllegalArgumentException("Cannot combine equalTo() with endAt() or endBefore()");
        }
    }

    private void validateNoOrderByCall() {
        if (this.orderByCalled) {
            throw new IllegalArgumentException("You can't combine multiple orderBy calls!");
        }
    }

    public ValueEventListener addValueEventListener(ValueEventListener listener) {
        addEventRegistration(new ValueEventRegistration(this.repo, listener, getSpec()));
        return listener;
    }

    public ChildEventListener addChildEventListener(ChildEventListener listener) {
        addEventRegistration(new ChildEventRegistration(this.repo, listener, getSpec()));
        return listener;
    }

    public Task<DataSnapshot> get() {
        return this.repo.getValue(this);
    }

    public void addListenerForSingleValueEvent(final ValueEventListener listener) {
        addEventRegistration(new ValueEventRegistration(this.repo, new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                Query.this.removeEventListener((ValueEventListener) this);
                listener.onDataChange(snapshot);
            }

            public void onCancelled(DatabaseError error) {
                listener.onCancelled(error);
            }
        }, getSpec()));
    }

    public void removeEventListener(ValueEventListener listener) {
        if (listener != null) {
            removeEventRegistration(new ValueEventRegistration(this.repo, listener, getSpec()));
            return;
        }
        throw new NullPointerException("listener must not be null");
    }

    public void removeEventListener(ChildEventListener listener) {
        if (listener != null) {
            removeEventRegistration(new ChildEventRegistration(this.repo, listener, getSpec()));
            return;
        }
        throw new NullPointerException("listener must not be null");
    }

    private void removeEventRegistration(final EventRegistration registration) {
        ZombieEventManager.getInstance().zombifyForRemove(registration);
        this.repo.scheduleNow(new Runnable() {
            public void run() {
                Query.this.repo.removeEventCallback(registration);
            }
        });
    }

    private void addEventRegistration(final EventRegistration listener) {
        ZombieEventManager.getInstance().recordEventRegistration(listener);
        this.repo.scheduleNow(new Runnable() {
            public void run() {
                Query.this.repo.addEventCallback(listener);
            }
        });
    }

    public void keepSynced(final boolean keepSynced) {
        if (this.path.isEmpty() || !this.path.getFront().equals(ChildKey.getInfoKey())) {
            this.repo.scheduleNow(new Runnable() {
                public void run() {
                    Query.this.repo.keepSynced(Query.this.getSpec(), keepSynced);
                }
            });
            return;
        }
        throw new DatabaseException("Can't call keepSynced() on .info paths.");
    }

    public Query startAfter(String value) {
        if (value == null || !this.params.getIndex().equals(KeyIndex.getInstance())) {
            return startAt(value, ChildKey.getMaxName().asString());
        }
        return startAt(PushIdGenerator.successor(value));
    }

    public Query startAfter(double value) {
        return startAt(value, ChildKey.getMaxName().asString());
    }

    public Query startAfter(boolean value) {
        return startAt(value, ChildKey.getMaxName().asString());
    }

    public Query startAfter(String value, String key) {
        if (value != null && this.params.getIndex().equals(KeyIndex.getInstance())) {
            value = PushIdGenerator.successor(value);
        }
        return startAfter(value != null ? new StringNode(value, PriorityUtilities.NullPriority()) : EmptyNode.Empty(), key);
    }

    public Query startAfter(double value, String key) {
        return startAfter((Node) new DoubleNode(Double.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    public Query startAfter(boolean value, String key) {
        return startAfter((Node) new BooleanNode(Boolean.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    private Query startAfter(Node node, String key) {
        return startAt(node, PushIdGenerator.successor(key));
    }

    public Query startAt(String value) {
        return startAt(value, (String) null);
    }

    public Query startAt(double value) {
        return startAt(value, (String) null);
    }

    public Query startAt(boolean value) {
        return startAt(value, (String) null);
    }

    public Query startAt(String value, String key) {
        return startAt(value != null ? new StringNode(value, PriorityUtilities.NullPriority()) : EmptyNode.Empty(), key);
    }

    public Query startAt(double value, String key) {
        return startAt((Node) new DoubleNode(Double.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    public Query startAt(boolean value, String key) {
        return startAt((Node) new BooleanNode(Boolean.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    private Query startAt(Node node, String key) {
        Validation.validateNullableKey(key);
        if (!node.isLeafNode() && !node.isEmpty()) {
            throw new IllegalArgumentException("Can only use simple values for startAt() and startAfter()");
        } else if (!this.params.hasStart()) {
            ChildKey childKey = null;
            if (key != null) {
                if (key.equals(ChildKey.MIN_KEY_NAME)) {
                    childKey = ChildKey.getMinName();
                } else if (key.equals(ChildKey.MAX_KEY_NAME)) {
                    childKey = ChildKey.getMaxName();
                } else {
                    childKey = ChildKey.fromString(key);
                }
            }
            QueryParams newParams = this.params.startAt(node, childKey);
            validateLimit(newParams);
            validateQueryEndpoints(newParams);
            Utilities.hardAssert(newParams.isValid());
            return new Query(this.repo, this.path, newParams, this.orderByCalled);
        } else {
            throw new IllegalArgumentException("Can't call startAt(), startAfte(), or equalTo() multiple times");
        }
    }

    public Query endBefore(String value) {
        if (value == null || !this.params.getIndex().equals(KeyIndex.getInstance())) {
            return endAt(value, ChildKey.getMinName().asString());
        }
        return endAt(PushIdGenerator.predecessor(value));
    }

    public Query endBefore(double value) {
        return endAt(value, ChildKey.getMinName().asString());
    }

    public Query endBefore(boolean value) {
        return endAt(value, ChildKey.getMinName().asString());
    }

    public Query endBefore(String value, String key) {
        if (value != null && this.params.getIndex().equals(KeyIndex.getInstance())) {
            value = PushIdGenerator.predecessor(value);
        }
        return endBefore(value != null ? new StringNode(value, PriorityUtilities.NullPriority()) : EmptyNode.Empty(), key);
    }

    public Query endBefore(double value, String key) {
        return endBefore((Node) new DoubleNode(Double.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    public Query endBefore(boolean value, String key) {
        return endBefore((Node) new BooleanNode(Boolean.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    private Query endBefore(Node node, String key) {
        return endAt(node, PushIdGenerator.predecessor(key));
    }

    public Query endAt(String value) {
        return endAt(value, (String) null);
    }

    public Query endAt(double value) {
        return endAt(value, (String) null);
    }

    public Query endAt(boolean value) {
        return endAt(value, (String) null);
    }

    public Query endAt(String value, String key) {
        return endAt(value != null ? new StringNode(value, PriorityUtilities.NullPriority()) : EmptyNode.Empty(), key);
    }

    public Query endAt(double value, String key) {
        return endAt((Node) new DoubleNode(Double.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    public Query endAt(boolean value, String key) {
        return endAt((Node) new BooleanNode(Boolean.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    private Query endAt(Node node, String key) {
        Validation.validateNullableKey(key);
        if (node.isLeafNode() || node.isEmpty()) {
            ChildKey childKey = key != null ? ChildKey.fromString(key) : null;
            if (!this.params.hasEnd()) {
                QueryParams newParams = this.params.endAt(node, childKey);
                validateLimit(newParams);
                validateQueryEndpoints(newParams);
                Utilities.hardAssert(newParams.isValid());
                return new Query(this.repo, this.path, newParams, this.orderByCalled);
            }
            throw new IllegalArgumentException("Can't call endAt() or equalTo() multiple times");
        }
        throw new IllegalArgumentException("Can only use simple values for endAt()");
    }

    public Query equalTo(String value) {
        validateEqualToCall();
        return startAt(value).endAt(value);
    }

    public Query equalTo(double value) {
        validateEqualToCall();
        return startAt(value).endAt(value);
    }

    public Query equalTo(boolean value) {
        validateEqualToCall();
        return startAt(value).endAt(value);
    }

    public Query equalTo(String value, String key) {
        validateEqualToCall();
        return startAt(value, key).endAt(value, key);
    }

    public Query equalTo(double value, String key) {
        validateEqualToCall();
        return startAt(value, key).endAt(value, key);
    }

    public Query equalTo(boolean value, String key) {
        validateEqualToCall();
        return startAt(value, key).endAt(value, key);
    }

    public Query limitToFirst(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be a positive integer!");
        } else if (!this.params.hasLimit()) {
            return new Query(this.repo, this.path, this.params.limitToFirst(limit), this.orderByCalled);
        } else {
            throw new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
        }
    }

    public Query limitToLast(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be a positive integer!");
        } else if (!this.params.hasLimit()) {
            return new Query(this.repo, this.path, this.params.limitToLast(limit), this.orderByCalled);
        } else {
            throw new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
        }
    }

    public Query orderByChild(String path2) {
        if (path2 == null) {
            throw new NullPointerException("Key can't be null");
        } else if (path2.equals("$key") || path2.equals(".key")) {
            throw new IllegalArgumentException("Can't use '" + path2 + "' as path, please use orderByKey() instead!");
        } else if (path2.equals("$priority") || path2.equals(".priority")) {
            throw new IllegalArgumentException("Can't use '" + path2 + "' as path, please use orderByPriority() instead!");
        } else if (path2.equals("$value") || path2.equals(".value")) {
            throw new IllegalArgumentException("Can't use '" + path2 + "' as path, please use orderByValue() instead!");
        } else {
            Validation.validatePathString(path2);
            validateNoOrderByCall();
            Path indexPath = new Path(path2);
            if (indexPath.size() != 0) {
                return new Query(this.repo, this.path, this.params.orderBy(new PathIndex(indexPath)), true);
            }
            throw new IllegalArgumentException("Can't use empty path, use orderByValue() instead!");
        }
    }

    public Query orderByPriority() {
        validateNoOrderByCall();
        QueryParams newParams = this.params.orderBy(PriorityIndex.getInstance());
        validateQueryEndpoints(newParams);
        return new Query(this.repo, this.path, newParams, true);
    }

    public Query orderByKey() {
        validateNoOrderByCall();
        QueryParams newParams = this.params.orderBy(KeyIndex.getInstance());
        validateQueryEndpoints(newParams);
        return new Query(this.repo, this.path, newParams, true);
    }

    public Query orderByValue() {
        validateNoOrderByCall();
        return new Query(this.repo, this.path, this.params.orderBy(ValueIndex.getInstance()), true);
    }

    public DatabaseReference getRef() {
        return new DatabaseReference(this.repo, getPath());
    }

    public Path getPath() {
        return this.path;
    }

    public Repo getRepo() {
        return this.repo;
    }

    public QuerySpec getSpec() {
        return new QuerySpec(this.path, this.params);
    }
}
