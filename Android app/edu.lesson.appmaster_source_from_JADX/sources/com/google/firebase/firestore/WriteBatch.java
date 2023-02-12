package com.google.firebase.firestore;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Map;

public class WriteBatch {
    private boolean committed = false;
    private final FirebaseFirestore firestore;
    private final ArrayList<Mutation> mutations = new ArrayList<>();

    public interface Function {
        void apply(WriteBatch writeBatch);
    }

    WriteBatch(FirebaseFirestore firestore2) {
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firestore2);
    }

    public WriteBatch set(DocumentReference documentRef, Object data) {
        return set(documentRef, data, SetOptions.OVERWRITE);
    }

    public WriteBatch set(DocumentReference documentRef, Object data, SetOptions options) {
        UserData.ParsedSetData parsed;
        this.firestore.validateReference(documentRef);
        Preconditions.checkNotNull(data, "Provided data must not be null.");
        Preconditions.checkNotNull(options, "Provided options must not be null.");
        verifyNotCommitted();
        if (options.isMerge()) {
            parsed = this.firestore.getUserDataReader().parseMergeData(data, options.getFieldMask());
        } else {
            parsed = this.firestore.getUserDataReader().parseSetData(data);
        }
        this.mutations.add(parsed.toMutation(documentRef.getKey(), Precondition.NONE));
        return this;
    }

    public WriteBatch update(DocumentReference documentRef, Map<String, Object> data) {
        return update(documentRef, this.firestore.getUserDataReader().parseUpdateData(data));
    }

    public WriteBatch update(DocumentReference documentRef, String field, Object value, Object... moreFieldsAndValues) {
        return update(documentRef, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, field, value, moreFieldsAndValues)));
    }

    public WriteBatch update(DocumentReference documentRef, FieldPath fieldPath, Object value, Object... moreFieldsAndValues) {
        return update(documentRef, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, value, moreFieldsAndValues)));
    }

    private WriteBatch update(DocumentReference documentRef, UserData.ParsedUpdateData updateData) {
        this.firestore.validateReference(documentRef);
        verifyNotCommitted();
        this.mutations.add(updateData.toMutation(documentRef.getKey(), Precondition.exists(true)));
        return this;
    }

    public WriteBatch delete(DocumentReference documentRef) {
        this.firestore.validateReference(documentRef);
        verifyNotCommitted();
        this.mutations.add(new DeleteMutation(documentRef.getKey(), Precondition.NONE));
        return this;
    }

    public Task<Void> commit() {
        verifyNotCommitted();
        this.committed = true;
        if (this.mutations.size() > 0) {
            return this.firestore.getClient().write(this.mutations);
        }
        return Tasks.forResult(null);
    }

    private void verifyNotCommitted() {
        if (this.committed) {
            throw new IllegalStateException("A write batch can no longer be used after commit() has been called.");
        }
    }
}
