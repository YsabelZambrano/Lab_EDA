public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E cl) {
        up = false;
        E mediana = push(root, cl);
        if (up) {
            BNode<E> nuevaRaiz = new BNode<>(orden);
            nuevaRaiz.count = 1;
            nuevaRaiz.keys.set(0, mediana);
            nuevaRaiz.childs.set(0, root);
            nuevaRaiz.childs.set(1, nDes);
            root = nuevaRaiz;
        }
    }

    private E push(BNode<E> current, E cl) {
        int[] pos = new int[1];
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        }

        boolean found = current.searchNode(cl, pos);
        if (found) {
            System.out.println("Elemento duplicado: " + cl);
            up = false;
            return null;
        }

        E mediana = push(current.childs.get(pos[0]), cl);
        if (up) {
            if (current.nodeFull(orden - 1)) {
                mediana = divideNode(current, mediana, pos[0]);
            } else {
                insertInNode(current, mediana, nDes, pos[0]);
                up = false;
            }
        }

        return mediana;
    }

    private void insertInNode(BNode<E> current, E key, BNode<E> right, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, key);
        current.childs.set(k + 1, right);
        current.count++;
    }

    private E divideNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        BNode<E> nuevo = new BNode<>(orden);
        int mid = (orden - 1) / 2;

        for (int i = mid + 1; i < orden - 1; i++) {
            nuevo.keys.set(i - (mid + 1), current.keys.get(i));
            nuevo.childs.set(i - (mid + 1), current.childs.get(i + 1));
        }

        nuevo.childs.set(orden - mid - 2, current.childs.get(orden - 1));
        nuevo.count = (orden - 1) - (mid + 1);
        current.count = mid;

        if (k <= mid)
            insertInNode(current, cl, rd, k);
        else
            insertInNode(nuevo, cl, rd, k - (mid + 1));

        nDes = nuevo;
        return current.keys.get(mid);
    }

    public String toString() {
        if (isEmpty()) return "Arbol B vacio.";
        return writeTree(this.root, 0);
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
}
