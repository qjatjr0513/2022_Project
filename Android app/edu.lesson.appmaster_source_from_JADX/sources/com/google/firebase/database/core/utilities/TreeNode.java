package com.google.firebase.database.core.utilities;

import com.google.firebase.database.snapshot.ChildKey;
import java.util.HashMap;
import java.util.Map;

public class TreeNode<T> {
    public Map<ChildKey, TreeNode<T>> children = new HashMap();
    public T value;

    /* access modifiers changed from: package-private */
    public String toString(String prefix) {
        String result = prefix + "<value>: " + this.value + "\n";
        if (this.children.isEmpty()) {
            return result + prefix + "<empty>";
        }
        for (Map.Entry<ChildKey, TreeNode<T>> entry : this.children.entrySet()) {
            result = result + prefix + entry.getKey() + ":\n" + entry.getValue().toString(prefix + "\t") + "\n";
        }
        return result;
    }
}
