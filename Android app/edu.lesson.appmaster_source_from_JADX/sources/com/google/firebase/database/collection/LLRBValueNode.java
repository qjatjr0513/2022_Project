package com.google.firebase.database.collection;

import com.google.firebase.database.collection.LLRBNode;
import java.util.Comparator;

public abstract class LLRBValueNode<K, V> implements LLRBNode<K, V> {
    private final K key;
    private LLRBNode<K, V> left;
    private final LLRBNode<K, V> right;
    private final V value;

    /* access modifiers changed from: protected */
    public abstract LLRBValueNode<K, V> copy(K k, V v, LLRBNode<K, V> lLRBNode, LLRBNode<K, V> lLRBNode2);

    /* access modifiers changed from: protected */
    public abstract LLRBNode.Color getColor();

    private static LLRBNode.Color oppositeColor(LLRBNode node) {
        return node.isRed() ? LLRBNode.Color.BLACK : LLRBNode.Color.RED;
    }

    LLRBValueNode(K key2, V value2, LLRBNode<K, V> left2, LLRBNode<K, V> right2) {
        this.key = key2;
        this.value = value2;
        this.left = left2 == null ? LLRBEmptyNode.getInstance() : left2;
        this.right = right2 == null ? LLRBEmptyNode.getInstance() : right2;
    }

    public LLRBNode<K, V> getLeft() {
        return this.left;
    }

    public LLRBNode<K, V> getRight() {
        return this.right;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public LLRBValueNode<K, V> copy(K key2, V value2, LLRBNode.Color color, LLRBNode<K, V> left2, LLRBNode<K, V> right2) {
        K newKey = key2 == null ? this.key : key2;
        V newValue = value2 == null ? this.value : value2;
        LLRBNode<K, V> newLeft = left2 == null ? this.left : left2;
        LLRBNode<K, V> newRight = right2 == null ? this.right : right2;
        if (color == LLRBNode.Color.RED) {
            return new LLRBRedValueNode(newKey, newValue, newLeft, newRight);
        }
        return new LLRBBlackValueNode(newKey, newValue, newLeft, newRight);
    }

    public LLRBNode<K, V> insert(K key2, V value2, Comparator<K> comparator) {
        LLRBValueNode<K, V> n;
        int cmp = comparator.compare(key2, this.key);
        if (cmp < 0) {
            n = copy((Object) null, (Object) null, this.left.insert(key2, value2, comparator), (LLRBNode<K, V>) null);
        } else if (cmp == 0) {
            n = copy(key2, value2, (LLRBNode) null, (LLRBNode) null);
        } else {
            n = copy((Object) null, (Object) null, (LLRBNode) null, this.right.insert(key2, value2, comparator));
        }
        return n.fixUp();
    }

    public LLRBNode<K, V> remove(K key2, Comparator<K> comparator) {
        LLRBValueNode<K, V> n;
        LLRBValueNode lLRBValueNode = this;
        if (comparator.compare(key2, lLRBValueNode.key) < 0) {
            if (!lLRBValueNode.left.isEmpty() && !lLRBValueNode.left.isRed() && !((LLRBValueNode) lLRBValueNode.left).left.isRed()) {
                lLRBValueNode = lLRBValueNode.moveRedLeft();
            }
            n = lLRBValueNode.copy((Object) null, (Object) null, lLRBValueNode.left.remove(key2, comparator), (LLRBNode<K, V>) null);
        } else {
            if (lLRBValueNode.left.isRed()) {
                lLRBValueNode = lLRBValueNode.rotateRight();
            }
            if (!lLRBValueNode.right.isEmpty() && !lLRBValueNode.right.isRed() && !((LLRBValueNode) lLRBValueNode.right).left.isRed()) {
                lLRBValueNode = lLRBValueNode.moveRedRight();
            }
            if (comparator.compare(key2, lLRBValueNode.key) == 0) {
                if (lLRBValueNode.right.isEmpty()) {
                    return LLRBEmptyNode.getInstance();
                }
                LLRBNode<K, V> smallest = lLRBValueNode.right.getMin();
                lLRBValueNode = lLRBValueNode.copy(smallest.getKey(), smallest.getValue(), (LLRBNode) null, ((LLRBValueNode) lLRBValueNode.right).removeMin());
            }
            n = lLRBValueNode.copy((Object) null, (Object) null, (LLRBNode) null, lLRBValueNode.right.remove(key2, comparator));
        }
        return n.fixUp();
    }

    public boolean isEmpty() {
        return false;
    }

    public LLRBNode<K, V> getMin() {
        if (this.left.isEmpty()) {
            return this;
        }
        return this.left.getMin();
    }

    public LLRBNode<K, V> getMax() {
        if (this.right.isEmpty()) {
            return this;
        }
        return this.right.getMax();
    }

    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> visitor) {
        this.left.inOrderTraversal(visitor);
        visitor.visitEntry(this.key, this.value);
        this.right.inOrderTraversal(visitor);
    }

    public boolean shortCircuitingInOrderTraversal(LLRBNode.ShortCircuitingNodeVisitor<K, V> visitor) {
        if (!this.left.shortCircuitingInOrderTraversal(visitor) || !visitor.shouldContinue(this.key, this.value)) {
            return false;
        }
        return this.right.shortCircuitingInOrderTraversal(visitor);
    }

    public boolean shortCircuitingReverseOrderTraversal(LLRBNode.ShortCircuitingNodeVisitor<K, V> visitor) {
        if (!this.right.shortCircuitingReverseOrderTraversal(visitor) || !visitor.shouldContinue(this.key, this.value)) {
            return false;
        }
        return this.left.shortCircuitingReverseOrderTraversal(visitor);
    }

    /* access modifiers changed from: package-private */
    public void setLeft(LLRBNode<K, V> left2) {
        this.left = left2;
    }

    private LLRBNode<K, V> removeMin() {
        if (this.left.isEmpty()) {
            return LLRBEmptyNode.getInstance();
        }
        LLRBValueNode lLRBValueNode = this;
        if (!lLRBValueNode.getLeft().isRed() && !lLRBValueNode.getLeft().getLeft().isRed()) {
            lLRBValueNode = lLRBValueNode.moveRedLeft();
        }
        return lLRBValueNode.copy((Object) null, (Object) null, ((LLRBValueNode) lLRBValueNode.left).removeMin(), (LLRBNode) null).fixUp();
    }

    private LLRBValueNode<K, V> moveRedLeft() {
        LLRBValueNode<K, V> n = colorFlip();
        if (n.getRight().getLeft().isRed()) {
            return n.copy((K) null, (V) null, (LLRBNode<K, V>) null, ((LLRBValueNode) n.getRight()).rotateRight()).rotateLeft().colorFlip();
        }
        return n;
    }

    private LLRBValueNode<K, V> moveRedRight() {
        LLRBValueNode<K, V> n = colorFlip();
        if (n.getLeft().getLeft().isRed()) {
            return n.rotateRight().colorFlip();
        }
        return n;
    }

    private LLRBValueNode<K, V> fixUp() {
        LLRBValueNode lLRBValueNode = this;
        if (lLRBValueNode.right.isRed() && !lLRBValueNode.left.isRed()) {
            lLRBValueNode = lLRBValueNode.rotateLeft();
        }
        if (lLRBValueNode.left.isRed() && ((LLRBValueNode) lLRBValueNode.left).left.isRed()) {
            lLRBValueNode = lLRBValueNode.rotateRight();
        }
        if (!lLRBValueNode.left.isRed() || !lLRBValueNode.right.isRed()) {
            return lLRBValueNode;
        }
        return lLRBValueNode.colorFlip();
    }

    private LLRBValueNode<K, V> rotateLeft() {
        return (LLRBValueNode) this.right.copy(null, null, getColor(), copy((Object) null, (Object) null, LLRBNode.Color.RED, (LLRBNode) null, (LLRBNode) ((LLRBValueNode) this.right).left), (LLRBValueNode<K, V>) null);
    }

    private LLRBValueNode<K, V> rotateRight() {
        return (LLRBValueNode) this.left.copy(null, null, getColor(), (LLRBNode) null, copy((Object) null, (Object) null, LLRBNode.Color.RED, (LLRBNode) ((LLRBValueNode) this.left).right, (LLRBNode) null));
    }

    private LLRBValueNode<K, V> colorFlip() {
        LLRBNode<K, V> lLRBNode = this.left;
        LLRBNode<K, V> newLeft = lLRBNode.copy(null, null, oppositeColor(lLRBNode), (LLRBNode<K, V>) null, (LLRBNode<K, V>) null);
        LLRBNode<K, V> lLRBNode2 = this.right;
        return copy((Object) null, (Object) null, oppositeColor(this), (LLRBNode) newLeft, (LLRBNode) lLRBNode2.copy(null, null, oppositeColor(lLRBNode2), (LLRBNode<K, V>) null, (LLRBNode<K, V>) null));
    }
}
