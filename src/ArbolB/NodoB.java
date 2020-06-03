/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

import Interfaces.Nodo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Equipo1
 */
public class NodoB<T extends Comparable<T>> implements Serializable {

    private int indiceLlave = 0;
    private Key<T>[] keys = null;
    private NodoB<T>[] children = null;
    private int childrenSize = 0;
    private int keysSize = 0;    
    private Comparator<NodoB<T>> comparator = new Comparator<NodoB<T>>() {
        @Override
        public int compare(NodoB<T> arg0, NodoB<T> arg1) {
            return arg0.getKey(0).getElemento().compareTo(arg1.getKey(0).getElemento());
        }
    };

    private NodoB<T> parent = null;

    public NodoB<T> getParent() {
        return parent;
    }

    public void setKeys(Key<T>[] keys) {
        this.keys = keys;
    }

    public void setKeysSize(int keysSize) {
        this.keysSize = keysSize;
    }

    public void setChildren(NodoB<T>[] children) {
        this.children = children;
    }

    public void setChildrenSize(int childrenSize) {
        this.childrenSize = childrenSize;
    }

    public void setComparator(Comparator<NodoB<T>> comparator) {
        this.comparator = comparator;
    }

    public void setParent(NodoB<T> parent) {
        this.parent = parent;
    }

    public Key<T>[] getKeys() {
        return keys;
    }

    public int getKeysSize() {
        return keysSize;
    }

    public NodoB<T>[] getChildren() {
        return children;
    }

    public int getChildrenSize() {
        return childrenSize;
    }

    public Comparator<NodoB<T>> getComparator() {
        return comparator;
    }

    public int getIndiceLlave() {
        return indiceLlave;
    }

    public void setIndiceLlave(int indiceLlave) {
        this.indiceLlave = indiceLlave;
    }

    public NodoB(NodoB<T> parent, int maxKeySize, int maxChildrenSize) {
        this.parent = parent;
        this.keys = new Key[maxKeySize + 1];
        this.keysSize = 0;
        this.children = new NodoB[maxChildrenSize + 1];
        this.childrenSize = 0;
    }

    public Key<T> getKey(int index) {
        return keys[index];
    }

    public int indexOf(T value) {
        for (int i = 0; i < keysSize; i++) {
            if (keys[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public void addKey(Key<T> value) {

        keys[keysSize++] = value;

        Arrays.sort(keys, 0, keysSize);
    }

    public Key<T> removeKey(T value) {
        Key<T> removed = null;
        boolean found = false;
        if (keysSize == 0) {
            return null;
        }
        for (int i = 0; i < keysSize; i++) {
            if (keys[i].equals(value)) {
                found = true;
                removed = keys[i];
            } else if (found) {
                // shift the rest of the keys down
                keys[i - 1] = keys[i];
            }
        }
        if (found) {
            keysSize--;
            keys[keysSize] = null;
        }
        return removed;
    }

    public Key<T> removeKey(int index) {
        if (index >= keysSize) {
            return null;
        }
        Key<T> value = keys[index];
        for (int i = index + 1; i < keysSize; i++) {
            // shift the rest of the keys down
            keys[i - 1] = keys[i];
        }
        keysSize--;
        keys[keysSize] = null;
        return value;
    }

    public int numberOfKeys() {
        return keysSize;
    }

    public NodoB<T> getChild(int index) {
        if (index >= childrenSize) {
            return null;
        }
        return children[index];
    }

    public int indexOf(NodoB<T> child) {
        for (int i = 0; i < childrenSize; i++) {
            if (children[i].equals(child)) {
                return i;
            }
        }
        return -1;
    }

    public boolean addChild(NodoB<T> child) {
        child.parent = this;
        children[childrenSize++] = child;
        Arrays.sort(children, 0, childrenSize, comparator);
        return true;
    }

    public boolean removeChild(NodoB<T> child) {
        boolean found = false;
        if (childrenSize == 0) {
            return found;
        }
        for (int i = 0; i < childrenSize; i++) {
            if (children[i].equals(child)) {
                found = true;
            } else if (found) {
                // shift the rest of the keys down
                children[i - 1] = children[i];
            }
        }
        if (found) {
            childrenSize--;
            children[childrenSize] = null;
        }
        return found;
    }

    public NodoB<T> removeChild(int index) {
        if (index >= childrenSize) {
            return null;
        }
        NodoB<T> value = children[index];
        children[index] = null;
        for (int i = index + 1; i < childrenSize; i++) {
            // shift the rest of the keys down
            children[i - 1] = children[i];
        }
        childrenSize--;
        children[childrenSize] = null;
        return value;
    }

    public int numberOfChildren() {
        return childrenSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("keys=[");
        for (int i = 0; i < numberOfKeys(); i++) {
            Key value = getKey(i);
            builder.append(value);
            if (i < numberOfKeys() - 1) {
                builder.append(", ");
            }
        }
        builder.append("]\n");

        if (parent != null) {
            builder.append("parent=[");
            for (int i = 0; i < parent.numberOfKeys(); i++) {
                Key value = parent.getKey(i);
                builder.append(value);
                if (i < parent.numberOfKeys() - 1) {
                    builder.append(", ");
                }
            }
            builder.append("]\n");
        }

        if (children != null) {
            builder.append("keySize=").append(numberOfKeys()).append(" children=").append(numberOfChildren()).append("\n");
        }

        return builder.toString();
    }

}
