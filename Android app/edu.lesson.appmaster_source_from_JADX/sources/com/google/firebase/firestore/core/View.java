package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.LimboDocumentChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class View {
    private boolean current;
    private DocumentSet documentSet;
    private ImmutableSortedSet<DocumentKey> limboDocuments;
    private ImmutableSortedSet<DocumentKey> mutatedKeys;
    private final Query query;
    private ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
    private ImmutableSortedSet<DocumentKey> syncedDocuments;

    public static class DocumentChanges {
        final DocumentViewChangeSet changeSet;
        final DocumentSet documentSet;
        final ImmutableSortedSet<DocumentKey> mutatedKeys;
        /* access modifiers changed from: private */
        public final boolean needsRefill;

        /* synthetic */ DocumentChanges(DocumentSet x0, DocumentViewChangeSet x1, ImmutableSortedSet x2, boolean x3, C07651 x4) {
            this(x0, x1, x2, x3);
        }

        private DocumentChanges(DocumentSet newDocuments, DocumentViewChangeSet changes, ImmutableSortedSet<DocumentKey> mutatedKeys2, boolean needsRefill2) {
            this.documentSet = newDocuments;
            this.changeSet = changes;
            this.mutatedKeys = mutatedKeys2;
            this.needsRefill = needsRefill2;
        }

        public boolean needsRefill() {
            return this.needsRefill;
        }
    }

    public View(Query query2, ImmutableSortedSet<DocumentKey> remoteDocuments) {
        this.query = query2;
        this.documentSet = DocumentSet.emptySet(query2.comparator());
        this.syncedDocuments = remoteDocuments;
        this.limboDocuments = DocumentKey.emptyKeySet();
        this.mutatedKeys = DocumentKey.emptyKeySet();
    }

    public ViewSnapshot.SyncState getSyncState() {
        return this.syncState;
    }

    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> docChanges) {
        return computeDocChanges(docChanges, (DocumentChanges) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b9, code lost:
        if (r7.hasCommittedMutations() != false) goto L_0x00be;
     */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x016c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0146  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.firestore.core.View.DocumentChanges computeDocChanges(com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.firestore.model.DocumentKey, com.google.firebase.firestore.model.Document> r21, com.google.firebase.firestore.core.View.DocumentChanges r22) {
        /*
            r20 = this;
            r0 = r20
            r1 = r22
            if (r1 == 0) goto L_0x0009
            com.google.firebase.firestore.core.DocumentViewChangeSet r2 = r1.changeSet
            goto L_0x000e
        L_0x0009:
            com.google.firebase.firestore.core.DocumentViewChangeSet r2 = new com.google.firebase.firestore.core.DocumentViewChangeSet
            r2.<init>()
        L_0x000e:
            if (r1 == 0) goto L_0x0013
            com.google.firebase.firestore.model.DocumentSet r3 = r1.documentSet
            goto L_0x0015
        L_0x0013:
            com.google.firebase.firestore.model.DocumentSet r3 = r0.documentSet
        L_0x0015:
            r9 = r3
            if (r1 == 0) goto L_0x001b
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r3 = r1.mutatedKeys
            goto L_0x001d
        L_0x001b:
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r3 = r0.mutatedKeys
        L_0x001d:
            r4 = r9
            r5 = 0
            com.google.firebase.firestore.core.Query r6 = r0.query
            boolean r6 = r6.hasLimitToFirst()
            if (r6 == 0) goto L_0x003b
            int r6 = r9.size()
            long r10 = (long) r6
            com.google.firebase.firestore.core.Query r6 = r0.query
            long r12 = r6.getLimitToFirst()
            int r6 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r6 != 0) goto L_0x003b
            com.google.firebase.firestore.model.Document r6 = r9.getLastDocument()
            goto L_0x003c
        L_0x003b:
            r6 = 0
        L_0x003c:
            r10 = r6
            com.google.firebase.firestore.core.Query r6 = r0.query
            boolean r6 = r6.hasLimitToLast()
            if (r6 == 0) goto L_0x0059
            int r6 = r9.size()
            long r11 = (long) r6
            com.google.firebase.firestore.core.Query r6 = r0.query
            long r13 = r6.getLimitToLast()
            int r6 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r6 != 0) goto L_0x0059
            com.google.firebase.firestore.model.Document r6 = r9.getFirstDocument()
            goto L_0x005a
        L_0x0059:
            r6 = 0
        L_0x005a:
            r11 = r6
            java.util.Iterator r6 = r21.iterator()
            r12 = r5
        L_0x0060:
            boolean r5 = r6.hasNext()
            if (r5 == 0) goto L_0x0172
            java.lang.Object r5 = r6.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r14 = r5.getKey()
            com.google.firebase.firestore.model.DocumentKey r14 = (com.google.firebase.firestore.model.DocumentKey) r14
            com.google.firebase.firestore.model.Document r15 = r9.getDocument(r14)
            com.google.firebase.firestore.core.Query r7 = r0.query
            java.lang.Object r16 = r5.getValue()
            r8 = r16
            com.google.firebase.firestore.model.Document r8 = (com.google.firebase.firestore.model.Document) r8
            boolean r7 = r7.matches(r8)
            if (r7 == 0) goto L_0x008d
            java.lang.Object r7 = r5.getValue()
            com.google.firebase.firestore.model.Document r7 = (com.google.firebase.firestore.model.Document) r7
            goto L_0x008e
        L_0x008d:
            r7 = 0
        L_0x008e:
            if (r15 == 0) goto L_0x009e
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r8 = r0.mutatedKeys
            com.google.firebase.firestore.model.DocumentKey r13 = r15.getKey()
            boolean r8 = r8.contains(r13)
            if (r8 == 0) goto L_0x009e
            r8 = 1
            goto L_0x009f
        L_0x009e:
            r8 = 0
        L_0x009f:
            if (r7 == 0) goto L_0x00c1
            boolean r13 = r7.hasLocalMutations()
            if (r13 != 0) goto L_0x00bc
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.model.DocumentKey> r13 = r0.mutatedKeys
            r17 = r5
            com.google.firebase.firestore.model.DocumentKey r5 = r7.getKey()
            boolean r5 = r13.contains(r5)
            if (r5 == 0) goto L_0x00c3
            boolean r5 = r7.hasCommittedMutations()
            if (r5 == 0) goto L_0x00c3
            goto L_0x00be
        L_0x00bc:
            r17 = r5
        L_0x00be:
            r16 = 1
            goto L_0x00c5
        L_0x00c1:
            r17 = r5
        L_0x00c3:
            r16 = 0
        L_0x00c5:
            r5 = r16
            r13 = 0
            if (r15 == 0) goto L_0x011d
            if (r7 == 0) goto L_0x011d
            r18 = r6
            com.google.firebase.firestore.model.ObjectValue r6 = r15.getData()
            r19 = r9
            com.google.firebase.firestore.model.ObjectValue r9 = r7.getData()
            boolean r6 = r6.equals(r9)
            if (r6 != 0) goto L_0x010f
            boolean r9 = r0.shouldWaitForSyncedDocument(r15, r7)
            if (r9 != 0) goto L_0x011c
            com.google.firebase.firestore.core.DocumentViewChange$Type r9 = com.google.firebase.firestore.core.DocumentViewChange.Type.MODIFIED
            com.google.firebase.firestore.core.DocumentViewChange r9 = com.google.firebase.firestore.core.DocumentViewChange.create(r9, r7)
            r2.addChange(r9)
            r9 = 1
            if (r10 == 0) goto L_0x00fc
            com.google.firebase.firestore.core.Query r13 = r0.query
            java.util.Comparator r13 = r13.comparator()
            int r13 = r13.compare(r7, r10)
            if (r13 > 0) goto L_0x010a
        L_0x00fc:
            if (r11 == 0) goto L_0x010d
            com.google.firebase.firestore.core.Query r13 = r0.query
            java.util.Comparator r13 = r13.comparator()
            int r13 = r13.compare(r7, r11)
            if (r13 >= 0) goto L_0x010d
        L_0x010a:
            r12 = 1
            r13 = r9
            goto L_0x011c
        L_0x010d:
            r13 = r9
            goto L_0x011c
        L_0x010f:
            if (r8 == r5) goto L_0x011c
            com.google.firebase.firestore.core.DocumentViewChange$Type r9 = com.google.firebase.firestore.core.DocumentViewChange.Type.METADATA
            com.google.firebase.firestore.core.DocumentViewChange r9 = com.google.firebase.firestore.core.DocumentViewChange.create(r9, r7)
            r2.addChange(r9)
            r9 = 1
            r13 = r9
        L_0x011c:
            goto L_0x0144
        L_0x011d:
            r18 = r6
            r19 = r9
            if (r15 != 0) goto L_0x0130
            if (r7 == 0) goto L_0x0130
            com.google.firebase.firestore.core.DocumentViewChange$Type r6 = com.google.firebase.firestore.core.DocumentViewChange.Type.ADDED
            com.google.firebase.firestore.core.DocumentViewChange r6 = com.google.firebase.firestore.core.DocumentViewChange.create(r6, r7)
            r2.addChange(r6)
            r13 = 1
            goto L_0x0144
        L_0x0130:
            if (r15 == 0) goto L_0x0144
            if (r7 != 0) goto L_0x0144
            com.google.firebase.firestore.core.DocumentViewChange$Type r6 = com.google.firebase.firestore.core.DocumentViewChange.Type.REMOVED
            com.google.firebase.firestore.core.DocumentViewChange r6 = com.google.firebase.firestore.core.DocumentViewChange.create(r6, r15)
            r2.addChange(r6)
            r13 = 1
            if (r10 != 0) goto L_0x0142
            if (r11 == 0) goto L_0x0144
        L_0x0142:
            r6 = 1
            r12 = r6
        L_0x0144:
            if (r13 == 0) goto L_0x016c
            if (r7 == 0) goto L_0x0164
            com.google.firebase.firestore.model.DocumentSet r4 = r4.add(r7)
            boolean r6 = r7.hasLocalMutations()
            if (r6 == 0) goto L_0x015b
            com.google.firebase.firestore.model.DocumentKey r6 = r7.getKey()
            com.google.firebase.database.collection.ImmutableSortedSet r3 = r3.insert(r6)
            goto L_0x016c
        L_0x015b:
            com.google.firebase.firestore.model.DocumentKey r6 = r7.getKey()
            com.google.firebase.database.collection.ImmutableSortedSet r3 = r3.remove(r6)
            goto L_0x016c
        L_0x0164:
            com.google.firebase.firestore.model.DocumentSet r4 = r4.remove(r14)
            com.google.firebase.database.collection.ImmutableSortedSet r3 = r3.remove(r14)
        L_0x016c:
            r6 = r18
            r9 = r19
            goto L_0x0060
        L_0x0172:
            r19 = r9
            com.google.firebase.firestore.core.Query r5 = r0.query
            boolean r5 = r5.hasLimitToFirst()
            if (r5 != 0) goto L_0x0188
            com.google.firebase.firestore.core.Query r5 = r0.query
            boolean r5 = r5.hasLimitToLast()
            if (r5 == 0) goto L_0x0185
            goto L_0x0188
        L_0x0185:
            r9 = r3
            r13 = r4
            goto L_0x01da
        L_0x0188:
            com.google.firebase.firestore.core.Query r5 = r0.query
            boolean r5 = r5.hasLimitToFirst()
            if (r5 == 0) goto L_0x0197
            com.google.firebase.firestore.core.Query r5 = r0.query
            long r5 = r5.getLimitToFirst()
            goto L_0x019d
        L_0x0197:
            com.google.firebase.firestore.core.Query r5 = r0.query
            long r5 = r5.getLimitToLast()
        L_0x019d:
            int r7 = r4.size()
            long r7 = (long) r7
            long r7 = r7 - r5
        L_0x01a3:
            r13 = 0
            int r9 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r9 <= 0) goto L_0x01d8
            com.google.firebase.firestore.core.Query r9 = r0.query
            boolean r9 = r9.hasLimitToFirst()
            if (r9 == 0) goto L_0x01b6
            com.google.firebase.firestore.model.Document r9 = r4.getLastDocument()
            goto L_0x01ba
        L_0x01b6:
            com.google.firebase.firestore.model.Document r9 = r4.getFirstDocument()
        L_0x01ba:
            com.google.firebase.firestore.model.DocumentKey r13 = r9.getKey()
            com.google.firebase.firestore.model.DocumentSet r4 = r4.remove(r13)
            com.google.firebase.firestore.model.DocumentKey r13 = r9.getKey()
            com.google.firebase.database.collection.ImmutableSortedSet r3 = r3.remove(r13)
            com.google.firebase.firestore.core.DocumentViewChange$Type r13 = com.google.firebase.firestore.core.DocumentViewChange.Type.REMOVED
            com.google.firebase.firestore.core.DocumentViewChange r13 = com.google.firebase.firestore.core.DocumentViewChange.create(r13, r9)
            r2.addChange(r13)
            r13 = 1
            long r7 = r7 - r13
            goto L_0x01a3
        L_0x01d8:
            r9 = r3
            r13 = r4
        L_0x01da:
            if (r12 == 0) goto L_0x01e1
            if (r1 != 0) goto L_0x01df
            goto L_0x01e1
        L_0x01df:
            r8 = 0
            goto L_0x01e2
        L_0x01e1:
            r8 = 1
        L_0x01e2:
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r4 = "View was refilled using docs that themselves needed refilling."
            com.google.firebase.firestore.util.Assert.hardAssert(r8, r4, r3)
            com.google.firebase.firestore.core.View$DocumentChanges r14 = new com.google.firebase.firestore.core.View$DocumentChanges
            r8 = 0
            r3 = r14
            r4 = r13
            r5 = r2
            r6 = r9
            r7 = r12
            r3.<init>(r4, r5, r6, r7, r8)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.View.computeDocChanges(com.google.firebase.database.collection.ImmutableSortedMap, com.google.firebase.firestore.core.View$DocumentChanges):com.google.firebase.firestore.core.View$DocumentChanges");
    }

    private boolean shouldWaitForSyncedDocument(Document oldDoc, Document newDoc) {
        return oldDoc.hasLocalMutations() && newDoc.hasCommittedMutations() && !newDoc.hasLocalMutations();
    }

    public ViewChange applyChanges(DocumentChanges docChanges) {
        return applyChanges(docChanges, (TargetChange) null);
    }

    public ViewChange applyChanges(DocumentChanges docChanges, TargetChange targetChange) {
        ViewSnapshot snapshot;
        List<LimboDocumentChange> limboDocumentChanges;
        DocumentChanges documentChanges = docChanges;
        Assert.hardAssert(!docChanges.needsRefill, "Cannot apply changes that need a refill", new Object[0]);
        DocumentSet oldDocumentSet = this.documentSet;
        this.documentSet = documentChanges.documentSet;
        this.mutatedKeys = documentChanges.mutatedKeys;
        List<DocumentViewChange> viewChanges = documentChanges.changeSet.getChanges();
        Collections.sort(viewChanges, new View$$ExternalSyntheticLambda0(this));
        applyTargetChange(targetChange);
        List<LimboDocumentChange> limboDocumentChanges2 = updateLimboDocuments();
        ViewSnapshot.SyncState newSyncState = this.limboDocuments.size() == 0 && this.current ? ViewSnapshot.SyncState.SYNCED : ViewSnapshot.SyncState.LOCAL;
        boolean syncStatedChanged = newSyncState != this.syncState;
        this.syncState = newSyncState;
        if (viewChanges.size() != 0 || syncStatedChanged) {
            ViewSnapshot.SyncState syncState2 = newSyncState;
            limboDocumentChanges = limboDocumentChanges2;
            snapshot = new ViewSnapshot(this.query, documentChanges.documentSet, oldDocumentSet, viewChanges, newSyncState == ViewSnapshot.SyncState.LOCAL, documentChanges.mutatedKeys, syncStatedChanged, false);
        } else {
            ViewSnapshot.SyncState syncState3 = newSyncState;
            limboDocumentChanges = limboDocumentChanges2;
            snapshot = null;
        }
        return new ViewChange(snapshot, limboDocumentChanges);
    }

    /* renamed from: lambda$applyChanges$0$com-google-firebase-firestore-core-View  reason: not valid java name */
    public /* synthetic */ int m450lambda$applyChanges$0$comgooglefirebasefirestorecoreView(DocumentViewChange o1, DocumentViewChange o2) {
        int typeComp = Util.compareIntegers(changeTypeOrder(o1), changeTypeOrder(o2));
        o1.getType().compareTo(o2.getType());
        if (typeComp != 0) {
            return typeComp;
        }
        return this.query.comparator().compare(o1.getDocument(), o2.getDocument());
    }

    public ViewChange applyOnlineStateChange(OnlineState onlineState) {
        if (!this.current || onlineState != OnlineState.OFFLINE) {
            return new ViewChange((ViewSnapshot) null, Collections.emptyList());
        }
        this.current = false;
        return applyChanges(new DocumentChanges(this.documentSet, new DocumentViewChangeSet(), this.mutatedKeys, false, (C07651) null));
    }

    private void applyTargetChange(TargetChange targetChange) {
        if (targetChange != null) {
            Iterator<DocumentKey> it = targetChange.getAddedDocuments().iterator();
            while (it.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.insert(it.next());
            }
            Iterator<DocumentKey> it2 = targetChange.getModifiedDocuments().iterator();
            while (it2.hasNext()) {
                DocumentKey documentKey = it2.next();
                Assert.hardAssert(this.syncedDocuments.contains(documentKey), "Modified document %s not found in view.", documentKey);
            }
            Iterator<DocumentKey> it3 = targetChange.getRemovedDocuments().iterator();
            while (it3.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.remove(it3.next());
            }
            this.current = targetChange.isCurrent();
        }
    }

    private List<LimboDocumentChange> updateLimboDocuments() {
        if (!this.current) {
            return Collections.emptyList();
        }
        ImmutableSortedSet<DocumentKey> oldLimboDocs = this.limboDocuments;
        this.limboDocuments = DocumentKey.emptyKeySet();
        Iterator<Document> it = this.documentSet.iterator();
        while (it.hasNext()) {
            Document doc = it.next();
            if (shouldBeLimboDoc(doc.getKey())) {
                this.limboDocuments = this.limboDocuments.insert(doc.getKey());
            }
        }
        List<LimboDocumentChange> changes = new ArrayList<>(oldLimboDocs.size() + this.limboDocuments.size());
        Iterator<DocumentKey> it2 = oldLimboDocs.iterator();
        while (it2.hasNext()) {
            DocumentKey key = it2.next();
            if (!this.limboDocuments.contains(key)) {
                changes.add(new LimboDocumentChange(LimboDocumentChange.Type.REMOVED, key));
            }
        }
        Iterator<DocumentKey> it3 = this.limboDocuments.iterator();
        while (it3.hasNext()) {
            DocumentKey key2 = it3.next();
            if (!oldLimboDocs.contains(key2)) {
                changes.add(new LimboDocumentChange(LimboDocumentChange.Type.ADDED, key2));
            }
        }
        return changes;
    }

    private boolean shouldBeLimboDoc(DocumentKey key) {
        Document doc;
        if (!this.syncedDocuments.contains(key) && (doc = this.documentSet.getDocument(key)) != null && !doc.hasLocalMutations()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<DocumentKey> getLimboDocuments() {
        return this.limboDocuments;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<DocumentKey> getSyncedDocuments() {
        return this.syncedDocuments;
    }

    /* renamed from: com.google.firebase.firestore.core.View$1 */
    static /* synthetic */ class C07651 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type */
        static final /* synthetic */ int[] f157x33862af7;

        static {
            int[] iArr = new int[DocumentViewChange.Type.values().length];
            f157x33862af7 = iArr;
            try {
                iArr[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f157x33862af7[DocumentViewChange.Type.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f157x33862af7[DocumentViewChange.Type.METADATA.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f157x33862af7[DocumentViewChange.Type.REMOVED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private static int changeTypeOrder(DocumentViewChange change) {
        switch (C07651.f157x33862af7[change.getType().ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 2;
            case 4:
                return 0;
            default:
                throw new IllegalArgumentException("Unknown change type: " + change.getType());
        }
    }
}
