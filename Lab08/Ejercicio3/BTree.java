public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void destroy() {
        root = null;
    }

    public void insert(E key) {
        if (root == null) {
            root = new BNode<>(orden);
            root.keys.set(0, key);
            root.count = 1;
            return;
        }

        BNode<E> newRoot = insertRecursive(root, key);
        if (newRoot != null) {
            BNode<E> temp = new BNode<>(orden);
            temp.keys.set(0, newRoot.keys.get(0));
            temp.childs.set(0, root);
            temp.childs.set(1, newRoot.childs.get(1));
            temp.count = 1;
            root = temp;
        }
    }

    private BNode<E> insertRecursive(BNode<E> node, E key) {
        int i = 0;
        while (i < node.count && key.compareTo(node.keys.get(i)) > 0) i++;

        if (i < node.count && key.compareTo(node.keys.get(i)) == 0) return null;

        if (node.childs.get(0) == null) {
            insertInNode(node, key, null);
        } else {
            BNode<E> temp = insertRecursive(node.childs.get(i), key);
            if (temp != null) {
                insertInNode(node, temp.keys.get(0), temp.childs.get(1));
            }
        }

        if (node.count < orden - 1) return null;

        return split(node);
    }

    private void insertInNode(BNode<E> node, E key, BNode<E> child) {
        int i = node.count - 1;
        while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
            node.keys.set(i + 1, node.keys.get(i));
            node.childs.set(i + 2, node.childs.get(i + 1));
            i--;
        }
        node.keys.set(i + 1, key);
        node.childs.set(i + 2, child);
        node.count++;
    }

    private BNode<E> split(BNode<E> node) {
        int mid = (orden - 1) / 2;
        BNode<E> right = new BNode<>(orden);

        for (int i = mid + 1; i < orden - 1; i++) {
            right.keys.set(i - (mid + 1), node.keys.get(i));
            right.childs.set(i - (mid + 1), node.childs.get(i + 1));
        }

        right.childs.set(orden - mid - 2, node.childs.get(orden - 1));
        right.count = orden - 1 - mid - 1;

        BNode<E> parent = new BNode<>(orden);
        parent.keys.set(0, node.keys.get(mid));
        parent.childs.set(0, node);
        parent.childs.set(1, right);
        parent.count = 1;

        node.count = mid;

        return parent;
    }

    public void remove(E key) {
    int[] pos = new int[1];
    root = remove(root, key, pos);
    if (root != null && root.count == 0 && root.childs.get(0) != null) {
        root = root.childs.get(0);
    }
}

private BNode<E> remove(BNode<E> node, E key, int[] pos) {
    if (node == null) return null;

    boolean found = node.searchNode(key, pos);

    if (found) {
        if (node.childs.get(0) == null) {
            for (int i = pos[0]; i < node.count - 1; i++) {
                node.keys.set(i, node.keys.get(i + 1));
            }
            node.keys.set(node.count - 1, null);
            node.count--;
        } else {
            BNode<E> pred = node.childs.get(pos[0]);
            while (pred.childs.get(pred.count) != null)
                pred = pred.childs.get(pred.count);
            E predKey = pred.keys.get(pred.count - 1);
            node.keys.set(pos[0], predKey);
            node.childs.set(pos[0], remove(node.childs.get(pos[0]), predKey, new int[1]));
        }
    } else {
        BNode<E> temp = remove(node.childs.get(pos[0]), key, new int[1]);
        node.childs.set(pos[0], temp);
    }

    return node;
}


    public String toString() {
        if (isEmpty()) return "Arbol B vacio.";
        return writeTree(root, 0);
    }

    public String writeTree() {
        return writeTree(root, 0);
    }

    private String writeTree(BNode<E> node, int nivel) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("Nivel ").append(nivel).append(": ").append(node.toString()).append("\n");
        for (int i = 0; i <= node.count; i++) {
            sb.append(writeTree(node.childs.get(i), nivel + 1));
        }
        return sb.toString();
    }

    public boolean search(E key) {
        return searchRecursive(root, key);
    }

    private boolean searchRecursive(BNode<E> node, E key) {
        if (node == null) return false;
        int i = 0;
        while (i < node.count && key.compareTo(node.keys.get(i)) > 0) i++;

        if (i < node.count && key.compareTo(node.keys.get(i)) == 0) return true;

        return searchRecursive(node.childs.get(i), key);
    }

    public E min() {
        if (isEmpty()) return null;
        BNode<E> node = root;
        while (node.childs.get(0) != null) {
            node = node.childs.get(0);
        }
        return node.keys.get(0);
    }

    public E max() {
        if (isEmpty()) return null;
        BNode<E> node = root;
        while (node.childs.get(node.count) != null) {
            node = node.childs.get(node.count);
        }
        return node.keys.get(node.count - 1);
    }

    public E predecesor(E key) {
        BNode<E> node = root;
        E pred = null;
        while (node != null) {
            int i = 0;
            while (i < node.count && key.compareTo(node.keys.get(i)) > 0) {
                pred = node.keys.get(i);
                i++;
            }
            if (i < node.count && key.compareTo(node.keys.get(i)) == 0) break;
            node = node.childs.get(i);
        }
        return pred;
    }

    public E sucesor(E key) {
        BNode<E> node = root;
        while (node != null) {
            int i = 0;
            while (i < node.count && key.compareTo(node.keys.get(i)) > 0) i++;

            if (i < node.count && key.compareTo(node.keys.get(i)) == 0) {
                if (node.childs.get(i + 1) != null) {
                    node = node.childs.get(i + 1);
                    while (node.childs.get(0) != null)
                        node = node.childs.get(0);
                    return node.keys.get(0);
                } else {
                    return (i + 1 < node.count) ? node.keys.get(i + 1) : null;
                }
            }
            node = node.childs.get(i);
        }
        return null;
    }
}
