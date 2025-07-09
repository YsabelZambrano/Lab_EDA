public class BPlusTree {
    private BPlusNode root;
    private final int degree = 4;

    public BPlusTree() {
        root = new BPlusNode(true);
    }

    public void insert(int key) {
        BPlusNode r = root;
        if (r.keys.size() == degree - 1) {
            BPlusNode newRoot = new BPlusNode(false);
            newRoot.children.add(r);
            split(newRoot, 0, r);
            root = newRoot;
        }
        insertNonFull(root, key);
    }

    private void insertNonFull(BPlusNode node, int key) {
        if (node.isLeaf) {
            int i = 0;
            while (i < node.keys.size() && key > node.keys.get(i)) i++;
            node.keys.add(i, key);
        } else {
            int i = 0;
            while (i < node.keys.size() && key > node.keys.get(i)) i++;
            BPlusNode child = node.children.get(i);
            if (child.keys.size() == degree - 1) {
                split(node, i, child);
                if (key > node.keys.get(i)) i++;
            }
            insertNonFull(node.children.get(i), key);
        }
    }

    private void split(BPlusNode parent, int index, BPlusNode child) {
        int mid = (degree - 1) / 2;
        BPlusNode newNode = new BPlusNode(child.isLeaf);

        // Copiar la mitad derecha de las claves
        for (int i = mid; i < child.keys.size(); i++) {
            newNode.keys.add(child.keys.get(i));
        }

        if (!child.isLeaf) {
            for (int i = mid + 1; i < child.children.size(); i++) {
                newNode.children.add(child.children.get(i));
            }
        }

        if (child.isLeaf) {
            newNode.next = child.next;
            child.next = newNode;
            newNode.children.clear();
        }

        // Reducir hijo original
        child.keys = new ArrayList<>(child.keys.subList(0, mid));
        if (!child.isLeaf) {
            child.children = new ArrayList<>(child.children.subList(0, mid + 1));
        }

        // Insertar en el padre
        parent.keys.add(index, newNode.keys.get(0));
        parent.children.add(index + 1, newNode);
    }

    public void delete(int key) {
        deleteRecursive(root, key);
    }

    private void deleteRecursive(BPlusNode node, int key) {
        if (node.isLeaf) {
            node.keys.remove((Integer) key);
        } else {
            int i = 0;
            while (i < node.keys.size() && key > node.keys.get(i)) i++;
            deleteRecursive(node.children.get(i), key);
        }
    }

    public void printLeaves() {
        BPlusNode node = root;
        while (!node.isLeaf) {
            node = node.children.get(0);
        }

        System.out.print("Leaf nodes: ");
        while (node != null) {
            System.out.print(node.keys + " -> ");
            node = node.next;
        }
        System.out.println("null");
    }
}
