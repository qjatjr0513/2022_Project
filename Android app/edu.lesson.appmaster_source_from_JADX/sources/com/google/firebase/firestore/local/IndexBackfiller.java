package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class IndexBackfiller {
    /* access modifiers changed from: private */
    public static final long INITIAL_BACKFILL_DELAY_MS = TimeUnit.SECONDS.toMillis(15);
    private static final String LOG_TAG = "IndexBackfiller";
    private static final int MAX_DOCUMENTS_TO_PROCESS = 50;
    /* access modifiers changed from: private */
    public static final long REGULAR_BACKFILL_DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    private IndexManager indexManager;
    private LocalDocumentsView localDocumentsView;
    private int maxDocumentsToProcess = 50;
    private final Persistence persistence;
    private final Scheduler scheduler;

    public IndexBackfiller(Persistence persistence2, AsyncQueue asyncQueue) {
        this.persistence = persistence2;
        this.scheduler = new Scheduler(asyncQueue);
    }

    public void setLocalDocumentsView(LocalDocumentsView localDocumentsView2) {
        this.localDocumentsView = localDocumentsView2;
    }

    public void setIndexManager(IndexManager indexManager2) {
        this.indexManager = indexManager2;
    }

    public class Scheduler implements Scheduler {
        private final AsyncQueue asyncQueue;
        private AsyncQueue.DelayedTask backfillTask;
        private boolean hasRun = false;

        public Scheduler(AsyncQueue asyncQueue2) {
            this.asyncQueue = asyncQueue2;
        }

        public void start() {
            scheduleBackfill();
        }

        public void stop() {
            AsyncQueue.DelayedTask delayedTask = this.backfillTask;
            if (delayedTask != null) {
                delayedTask.cancel();
            }
        }

        private void scheduleBackfill() {
            this.backfillTask = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.INDEX_BACKFILL, this.hasRun ? IndexBackfiller.REGULAR_BACKFILL_DELAY_MS : IndexBackfiller.INITIAL_BACKFILL_DELAY_MS, new IndexBackfiller$Scheduler$$ExternalSyntheticLambda0(this));
        }

        /* renamed from: lambda$scheduleBackfill$0$com-google-firebase-firestore-local-IndexBackfiller$Scheduler */
        public /* synthetic */ void mo9093x4c716e24() {
            Logger.debug(IndexBackfiller.LOG_TAG, "Documents written: %s", Integer.valueOf(IndexBackfiller.this.backfill()));
            this.hasRun = true;
            scheduleBackfill();
        }
    }

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    public int backfill() {
        boolean z = true;
        Assert.hardAssert(this.localDocumentsView != null, "setLocalDocumentsView() not called", new Object[0]);
        if (this.indexManager == null) {
            z = false;
        }
        Assert.hardAssert(z, "setIndexManager() not called", new Object[0]);
        return ((Integer) this.persistence.runTransaction("Backfill Indexes", new IndexBackfiller$$ExternalSyntheticLambda0(this))).intValue();
    }

    /* renamed from: lambda$backfill$0$com-google-firebase-firestore-local-IndexBackfiller */
    public /* synthetic */ Integer mo9089x14d875f6() {
        return Integer.valueOf(writeIndexEntries());
    }

    private int writeIndexEntries() {
        Set<String> processedCollectionGroups = new HashSet<>();
        int documentsRemaining = this.maxDocumentsToProcess;
        while (documentsRemaining > 0) {
            String collectionGroup = this.indexManager.getNextCollectionGroupToUpdate();
            if (collectionGroup == null || processedCollectionGroups.contains(collectionGroup)) {
                break;
            }
            Logger.debug(LOG_TAG, "Processing collection: %s", collectionGroup);
            documentsRemaining -= writeEntriesForCollectionGroup(collectionGroup, documentsRemaining);
            processedCollectionGroups.add(collectionGroup);
        }
        return this.maxDocumentsToProcess - documentsRemaining;
    }

    private int writeEntriesForCollectionGroup(String collectionGroup, int documentsRemainingUnderCap) {
        FieldIndex.IndexOffset existingOffset = this.indexManager.getMinOffset(collectionGroup);
        LocalDocumentsResult nextBatch = this.localDocumentsView.getNextDocuments(collectionGroup, existingOffset, documentsRemainingUnderCap);
        this.indexManager.updateIndexEntries(nextBatch.getDocuments());
        FieldIndex.IndexOffset newOffset = getNewOffset(existingOffset, nextBatch);
        Logger.debug(LOG_TAG, "Updating offset: %s", newOffset);
        this.indexManager.updateCollectionGroup(collectionGroup, newOffset);
        return nextBatch.getDocuments().size();
    }

    private FieldIndex.IndexOffset getNewOffset(FieldIndex.IndexOffset existingOffset, LocalDocumentsResult lookupResult) {
        FieldIndex.IndexOffset maxOffset = existingOffset;
        Iterator<Map.Entry<DocumentKey, Document>> it = lookupResult.getDocuments().iterator();
        while (it.hasNext()) {
            FieldIndex.IndexOffset newOffset = FieldIndex.IndexOffset.fromDocument(it.next().getValue());
            if (newOffset.compareTo(maxOffset) > 0) {
                maxOffset = newOffset;
            }
        }
        return FieldIndex.IndexOffset.create(maxOffset.getReadTime(), maxOffset.getDocumentKey(), Math.max(lookupResult.getBatchId(), existingOffset.getLargestBatchId()));
    }

    /* access modifiers changed from: package-private */
    public void setMaxDocumentsToProcess(int newMax) {
        this.maxDocumentsToProcess = newMax;
    }
}
