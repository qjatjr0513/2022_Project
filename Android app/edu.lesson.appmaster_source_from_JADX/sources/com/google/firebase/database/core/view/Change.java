package com.google.firebase.database.core.view;

import com.google.firebase.database.core.view.Event;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.Node;

public class Change {
    private final ChildKey childKey;
    private final Event.EventType eventType;
    private final IndexedNode indexedNode;
    private final IndexedNode oldIndexedNode;
    private final ChildKey prevName;

    private Change(Event.EventType eventType2, IndexedNode indexedNode2, ChildKey childKey2, ChildKey prevName2, IndexedNode oldIndexedNode2) {
        this.eventType = eventType2;
        this.indexedNode = indexedNode2;
        this.childKey = childKey2;
        this.prevName = prevName2;
        this.oldIndexedNode = oldIndexedNode2;
    }

    public static Change valueChange(IndexedNode snapshot) {
        return new Change(Event.EventType.VALUE, snapshot, (ChildKey) null, (ChildKey) null, (IndexedNode) null);
    }

    public static Change childAddedChange(ChildKey childKey2, Node snapshot) {
        return childAddedChange(childKey2, IndexedNode.from(snapshot));
    }

    public static Change childAddedChange(ChildKey childKey2, IndexedNode snapshot) {
        return new Change(Event.EventType.CHILD_ADDED, snapshot, childKey2, (ChildKey) null, (IndexedNode) null);
    }

    public static Change childRemovedChange(ChildKey childKey2, Node snapshot) {
        return childRemovedChange(childKey2, IndexedNode.from(snapshot));
    }

    public static Change childRemovedChange(ChildKey childKey2, IndexedNode snapshot) {
        return new Change(Event.EventType.CHILD_REMOVED, snapshot, childKey2, (ChildKey) null, (IndexedNode) null);
    }

    public static Change childChangedChange(ChildKey childKey2, Node newSnapshot, Node oldSnapshot) {
        return childChangedChange(childKey2, IndexedNode.from(newSnapshot), IndexedNode.from(oldSnapshot));
    }

    public static Change childChangedChange(ChildKey childKey2, IndexedNode newSnapshot, IndexedNode oldSnapshot) {
        return new Change(Event.EventType.CHILD_CHANGED, newSnapshot, childKey2, (ChildKey) null, oldSnapshot);
    }

    public static Change childMovedChange(ChildKey childKey2, Node snapshot) {
        return childMovedChange(childKey2, IndexedNode.from(snapshot));
    }

    public static Change childMovedChange(ChildKey childKey2, IndexedNode snapshot) {
        return new Change(Event.EventType.CHILD_MOVED, snapshot, childKey2, (ChildKey) null, (IndexedNode) null);
    }

    public Change changeWithPrevName(ChildKey prevName2) {
        return new Change(this.eventType, this.indexedNode, this.childKey, prevName2, this.oldIndexedNode);
    }

    public ChildKey getChildKey() {
        return this.childKey;
    }

    public Event.EventType getEventType() {
        return this.eventType;
    }

    public IndexedNode getIndexedNode() {
        return this.indexedNode;
    }

    public ChildKey getPrevName() {
        return this.prevName;
    }

    public IndexedNode getOldIndexedNode() {
        return this.oldIndexedNode;
    }

    public String toString() {
        return "Change: " + this.eventType + " " + this.childKey;
    }
}
