package com.google.firebase.database.core;

import com.google.firebase.database.core.utilities.Predicate;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.CacheNode;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WriteTree {
    private static final Predicate<UserWriteRecord> DEFAULT_FILTER = new Predicate<UserWriteRecord>() {
        public boolean evaluate(UserWriteRecord write) {
            return write.isVisible();
        }
    };
    private List<UserWriteRecord> allWrites = new ArrayList();
    private Long lastWriteId = -1L;
    private CompoundWrite visibleWrites = CompoundWrite.emptyWrite();

    public WriteTreeRef childWrites(Path path) {
        return new WriteTreeRef(path, this);
    }

    public void addOverwrite(Path path, Node snap, Long writeId, boolean visible) {
        Utilities.hardAssert(writeId.longValue() > this.lastWriteId.longValue());
        this.allWrites.add(new UserWriteRecord(writeId.longValue(), path, snap, visible));
        if (visible) {
            this.visibleWrites = this.visibleWrites.addWrite(path, snap);
        }
        this.lastWriteId = writeId;
    }

    public void addMerge(Path path, CompoundWrite changedChildren, Long writeId) {
        Utilities.hardAssert(writeId.longValue() > this.lastWriteId.longValue());
        this.allWrites.add(new UserWriteRecord(writeId.longValue(), path, changedChildren));
        this.visibleWrites = this.visibleWrites.addWrites(path, changedChildren);
        this.lastWriteId = writeId;
    }

    public UserWriteRecord getWrite(long writeId) {
        for (UserWriteRecord record : this.allWrites) {
            if (record.getWriteId() == writeId) {
                return record;
            }
        }
        return null;
    }

    public List<UserWriteRecord> purgeAllWrites() {
        List<UserWriteRecord> purgedWrites = new ArrayList<>(this.allWrites);
        this.visibleWrites = CompoundWrite.emptyWrite();
        this.allWrites = new ArrayList();
        return purgedWrites;
    }

    public boolean removeWrite(long writeId) {
        UserWriteRecord writeToRemove = null;
        int idx = 0;
        Iterator<UserWriteRecord> it = this.allWrites.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            UserWriteRecord record = it.next();
            if (record.getWriteId() == writeId) {
                writeToRemove = record;
                break;
            }
            idx++;
        }
        Utilities.hardAssert(writeToRemove != null, "removeWrite called with nonexistent writeId");
        this.allWrites.remove(writeToRemove);
        boolean removedWriteWasVisible = writeToRemove.isVisible();
        boolean removedWriteOverlapsWithOtherWrites = false;
        int i = this.allWrites.size() - 1;
        while (removedWriteWasVisible && i >= 0) {
            UserWriteRecord currentWrite = this.allWrites.get(i);
            if (currentWrite.isVisible()) {
                if (i >= idx && recordContainsPath(currentWrite, writeToRemove.getPath())) {
                    removedWriteWasVisible = false;
                } else if (writeToRemove.getPath().contains(currentWrite.getPath())) {
                    removedWriteOverlapsWithOtherWrites = true;
                }
            }
            i--;
        }
        if (!removedWriteWasVisible) {
            return false;
        }
        if (removedWriteOverlapsWithOtherWrites) {
            resetTree();
            return true;
        }
        if (writeToRemove.isOverwrite()) {
            this.visibleWrites = this.visibleWrites.removeWrite(writeToRemove.getPath());
        } else {
            Iterator<Map.Entry<Path, Node>> it2 = writeToRemove.getMerge().iterator();
            while (it2.hasNext()) {
                this.visibleWrites = this.visibleWrites.removeWrite(writeToRemove.getPath().child(it2.next().getKey()));
            }
        }
        return true;
    }

    public Node getCompleteWriteData(Path path) {
        return this.visibleWrites.getCompleteNode(path);
    }

    public Node calcCompleteEventCache(Path treePath, Node completeServerCache) {
        return calcCompleteEventCache(treePath, completeServerCache, new ArrayList());
    }

    public Node calcCompleteEventCache(Path treePath, Node completeServerCache, List<Long> writeIdsToExclude) {
        return calcCompleteEventCache(treePath, completeServerCache, writeIdsToExclude, false);
    }

    public Node calcCompleteEventCache(final Path treePath, Node completeServerCache, final List<Long> writeIdsToExclude, final boolean includeHiddenWrites) {
        Node layeredCache;
        if (!writeIdsToExclude.isEmpty() || includeHiddenWrites) {
            CompoundWrite merge = this.visibleWrites.childCompoundWrite(treePath);
            if (!includeHiddenWrites && merge.isEmpty()) {
                return completeServerCache;
            }
            if (!includeHiddenWrites && completeServerCache == null && !merge.hasCompleteWrite(Path.getEmptyPath())) {
                return null;
            }
            return layerTree(this.allWrites, new Predicate<UserWriteRecord>() {
                public boolean evaluate(UserWriteRecord write) {
                    return (write.isVisible() || includeHiddenWrites) && !writeIdsToExclude.contains(Long.valueOf(write.getWriteId())) && (write.getPath().contains(treePath) || treePath.contains(write.getPath()));
                }
            }, treePath).apply(completeServerCache != null ? completeServerCache : EmptyNode.Empty());
        }
        Node shadowingNode = this.visibleWrites.getCompleteNode(treePath);
        if (shadowingNode != null) {
            return shadowingNode;
        }
        CompoundWrite subMerge = this.visibleWrites.childCompoundWrite(treePath);
        if (subMerge.isEmpty()) {
            return completeServerCache;
        }
        if (completeServerCache == null && !subMerge.hasCompleteWrite(Path.getEmptyPath())) {
            return null;
        }
        if (completeServerCache != null) {
            layeredCache = completeServerCache;
        } else {
            layeredCache = EmptyNode.Empty();
        }
        return subMerge.apply(layeredCache);
    }

    public Node calcCompleteEventChildren(Path treePath, Node completeServerChildren) {
        Node completeChildren = EmptyNode.Empty();
        Node<NamedNode> topLevelSet = this.visibleWrites.getCompleteNode(treePath);
        if (topLevelSet != null) {
            if (!topLevelSet.isLeafNode()) {
                for (NamedNode childEntry : topLevelSet) {
                    completeChildren = completeChildren.updateImmediateChild(childEntry.getName(), childEntry.getNode());
                }
            }
            return completeChildren;
        }
        CompoundWrite merge = this.visibleWrites.childCompoundWrite(treePath);
        Iterator it = completeServerChildren.iterator();
        while (it.hasNext()) {
            NamedNode entry = (NamedNode) it.next();
            completeChildren = completeChildren.updateImmediateChild(entry.getName(), merge.childCompoundWrite(new Path(entry.getName())).apply(entry.getNode()));
        }
        for (NamedNode node : merge.getCompleteChildren()) {
            completeChildren = completeChildren.updateImmediateChild(node.getName(), node.getNode());
        }
        return completeChildren;
    }

    public Node calcEventCacheAfterServerOverwrite(Path treePath, Path childPath, Node existingEventSnap, Node existingServerSnap) {
        Utilities.hardAssert((existingEventSnap == null && existingServerSnap == null) ? false : true, "Either existingEventSnap or existingServerSnap must exist");
        Path path = treePath.child(childPath);
        if (this.visibleWrites.hasCompleteWrite(path)) {
            return null;
        }
        CompoundWrite childMerge = this.visibleWrites.childCompoundWrite(path);
        if (childMerge.isEmpty()) {
            return existingServerSnap.getChild(childPath);
        }
        return childMerge.apply(existingServerSnap.getChild(childPath));
    }

    public Node calcCompleteChild(Path treePath, ChildKey childKey, CacheNode existingServerSnap) {
        Path path = treePath.child(childKey);
        Node shadowingNode = this.visibleWrites.getCompleteNode(path);
        if (shadowingNode != null) {
            return shadowingNode;
        }
        if (existingServerSnap.isCompleteForChild(childKey)) {
            return this.visibleWrites.childCompoundWrite(path).apply(existingServerSnap.getNode().getImmediateChild(childKey));
        }
        return null;
    }

    public Node shadowingWrite(Path path) {
        return this.visibleWrites.getCompleteNode(path);
    }

    public NamedNode calcNextNodeAfterPost(Path treePath, Node completeServerData, NamedNode post, boolean reverse, Index index) {
        Node<NamedNode> toIterate;
        CompoundWrite merge = this.visibleWrites.childCompoundWrite(treePath);
        Node shadowingNode = merge.getCompleteNode(Path.getEmptyPath());
        if (shadowingNode != null) {
            toIterate = shadowingNode;
        } else if (completeServerData == null) {
            return null;
        } else {
            toIterate = merge.apply(completeServerData);
        }
        NamedNode currentNext = null;
        for (NamedNode node : toIterate) {
            if (index.compare(node, post, reverse) > 0 && (currentNext == null || index.compare(node, currentNext, reverse) < 0)) {
                currentNext = node;
            }
        }
        return currentNext;
    }

    private boolean recordContainsPath(UserWriteRecord writeRecord, Path path) {
        if (writeRecord.isOverwrite()) {
            return writeRecord.getPath().contains(path);
        }
        Iterator<Map.Entry<Path, Node>> it = writeRecord.getMerge().iterator();
        while (it.hasNext()) {
            if (writeRecord.getPath().child(it.next().getKey()).contains(path)) {
                return true;
            }
        }
        return false;
    }

    private void resetTree() {
        this.visibleWrites = layerTree(this.allWrites, DEFAULT_FILTER, Path.getEmptyPath());
        if (this.allWrites.size() > 0) {
            List<UserWriteRecord> list = this.allWrites;
            this.lastWriteId = Long.valueOf(list.get(list.size() - 1).getWriteId());
            return;
        }
        this.lastWriteId = -1L;
    }

    private static CompoundWrite layerTree(List<UserWriteRecord> writes, Predicate<UserWriteRecord> filter, Path treeRoot) {
        CompoundWrite compoundWrite = CompoundWrite.emptyWrite();
        for (UserWriteRecord write : writes) {
            if (filter.evaluate(write)) {
                Path writePath = write.getPath();
                if (write.isOverwrite()) {
                    if (treeRoot.contains(writePath)) {
                        compoundWrite = compoundWrite.addWrite(Path.getRelative(treeRoot, writePath), write.getOverwrite());
                    } else if (writePath.contains(treeRoot)) {
                        compoundWrite = compoundWrite.addWrite(Path.getEmptyPath(), write.getOverwrite().getChild(Path.getRelative(writePath, treeRoot)));
                    }
                } else if (treeRoot.contains(writePath)) {
                    compoundWrite = compoundWrite.addWrites(Path.getRelative(treeRoot, writePath), write.getMerge());
                } else if (writePath.contains(treeRoot)) {
                    Path relativePath = Path.getRelative(writePath, treeRoot);
                    if (relativePath.isEmpty()) {
                        compoundWrite = compoundWrite.addWrites(Path.getEmptyPath(), write.getMerge());
                    } else {
                        Node deepNode = write.getMerge().getCompleteNode(relativePath);
                        if (deepNode != null) {
                            compoundWrite = compoundWrite.addWrite(Path.getEmptyPath(), deepNode);
                        }
                    }
                }
            }
        }
        return compoundWrite;
    }
}
