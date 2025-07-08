public class AVLTree<T extends Comparable<T>> {
    private NodeAVL<T> root;

    public boolean isEmpty() {
        return root == null;
    }

    public void destroy() {
        root = null;
    }

    public void insert(T key) {
        root = insert(root, key);
    }

    private NodeAVL<T> insert(NodeAVL<T> node, T key) {
        if (node == null) return new NodeAVL<>(key);

        int cmp = key.compareTo(node.data);
        if (cmp < 0)
            node.left = insert(node.left, key);
        else if (cmp > 0)
            node.right = insert(node.right, key);
        else
            return node;

        updateHeight(node);
        return balance(node);
    }

    public void remove(T key) {
        root = remove(root, key);
    }

    private NodeAVL<T> remove(NodeAVL<T> node, T key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.data);
        if (cmp < 0)
            node.left = remove(node.left, key);
        else if (cmp > 0)
            node.right = remove(node.right, key);
        else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                NodeAVL<T> minNode = getMin(node.right);
                node.data = minNode.data;
                node.right = remove(node.right, minNode.data);
            }
        }

        if (node != null) {
            updateHeight(node);
            node = balance(node);
        }
        return node;
    }

    private NodeAVL<T> getMin(NodeAVL<T> node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    public T Min() {
        return getMin(root).data;
    }

    public T Max() {
        NodeAVL<T> node = root;
        while (node.right != null)
            node = node.right;
        return node.data;
    }

    public boolean search(T key) {
        return search(root, key);
    }

    private boolean search(NodeAVL<T> node, T key) {
        if (node == null) return false;

        int cmp = key.compareTo(node.data);
        if (cmp < 0) return search(node.left, key);
        if (cmp > 0) return search(node.right, key);
        return true;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(NodeAVL<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(NodeAVL<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(NodeAVL<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    private void updateHeight(NodeAVL<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(NodeAVL<T> node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(NodeAVL<T> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private NodeAVL<T> balance(NodeAVL<T> node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0)
                node.left = rotateLeft(node.left); // Rotación doble I-D
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0)
                node.right = rotateRight(node.right); // Rotación doble D-I
            return rotateLeft(node);
        }

        return node;
    }

    private NodeAVL<T> rotateRight(NodeAVL<T> y) {
        NodeAVL<T> x = y.left;
        NodeAVL<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private NodeAVL<T> rotateLeft(NodeAVL<T> x) {
        NodeAVL<T> y = x.right;
        NodeAVL<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }
    public T predecesor(T key) {
    NodeAVL<T> pred = null;
    NodeAVL<T> current = root;
    while (current != null) {
        if (key.compareTo(current.data) > 0) {
            pred = current;
            current = current.right;
        } else {
            current = current.left;
        }
    }
    return pred != null ? pred.data : null;
}

    public T sucesor(T key) {
        NodeAVL<T> succ = null;
        NodeAVL<T> current = root;
        while (current != null) {
            if (key.compareTo(current.data) < 0) {
                succ = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return succ != null ? succ.data : null;
    }
    public NodeAVL<T> getRoot() {
    return root;
}
}

