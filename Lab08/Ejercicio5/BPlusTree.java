import java.util.*;

class BPlusNode<K extends Comparable<K>, V> {
    boolean isLeaf;
    List<K> keys;
    List<BPlusNode<K, V>> children; // para internos
    List<V> values;                 // para hojas
    BPlusNode<K, V> next;          // para hojas enlazadas

    public BPlusNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        keys = new ArrayList<>();
        if (isLeaf) {
            values = new ArrayList<>();
        } else {
            children = new ArrayList<>();
        }
    }

    public String toString() {
        return isLeaf ? "Leaf" + values : "Node" + keys;
    }
}

public class BPlusTree<K extends Comparable<K>, V> {
    private int order;
    private BPlusNode<K, V> root;

    public BPlusTree(int order) {
        this.order = order;
        this.root = new BPlusNode<>(true);
    }

    public void insert(K key, V value) {
        BPlusNode<K, V> newRoot = insert(root, key, value);
        if (newRoot != null) {
            BPlusNode<K, V> temp = new BPlusNode<>(false);
            temp.keys.add(newRoot.keys.get(0));
            temp.children = new ArrayList<>(Arrays.asList(root, newRoot));
            root = temp;
        }
    }

    private BPlusNode<K, V> insert(BPlusNode<K, V> node, K key, V value) {
        if (node.isLeaf) {
            int i = Collections.binarySearch(node.keys, key);
            if (i >= 0) return null;
            i = -i - 1;
            node.keys.add(i, key);
            node.values.add(i, value);

            if (node.keys.size() < order) return null;

            return splitLeaf(node);
        } else {
            int i = Collections.binarySearch(node.keys, key);
            i = i >= 0 ? i + 1 : -i - 1;
            BPlusNode<K, V> newChild = insert(node.children.get(i), key, value);
            if (newChild != null) {
                K newKey = newChild.keys.get(0);
                node.keys.add(i, newKey);
                node.children.add(i + 1, newChild);

                if (node.keys.size() < order) return null;

                return splitInternal(node);
            }
        }
        return null;
    }

    private BPlusNode<K, V> splitLeaf(BPlusNode<K, V> leaf) {
        BPlusNode<K, V> newLeaf = new BPlusNode<>(true);
        int mid = (order + 1) / 2;

        newLeaf.keys.addAll(leaf.keys.subList(mid, leaf.keys.size()));
        newLeaf.values.addAll(leaf.values.subList(mid, leaf.values.size()));
        leaf.keys.subList(mid, leaf.keys.size()).clear();
        leaf.values.subList(mid, leaf.values.size()).clear();

        newLeaf.next = leaf.next;
        leaf.next = newLeaf;

        return newLeaf;
    }

    private BPlusNode<K, V> splitInternal(BPlusNode<K, V> node) {
        BPlusNode<K, V> newNode = new BPlusNode<>(false);
        int mid = node.keys.size() / 2;

        newNode.keys.addAll(node.keys.subList(mid + 1, node.keys.size()));
        newNode.children.addAll(node.children.subList(mid + 1, node.children.size()));

        node.keys.subList(mid, node.keys.size()).clear();
        node.children.subList(mid + 1, node.children.size()).clear();

        return newNode;
    }

    public void remove(K key) {
        BPlusNode<K, V> node = root;
        while (!node.isLeaf) {
            int i = Collections.binarySearch(node.keys, key);
            i = i >= 0 ? i + 1 : -i - 1;
            node = node.children.get(i);
        }

        int i = node.keys.indexOf(key);
        if (i != -1) {
            node.keys.remove(i);
            node.values.remove(i);
        }
    }

    public void print() {
        Queue<BPlusNode<K, V>> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("Nivel " + level++ + ": ");
            for (int i = 0; i < size; i++) {
                BPlusNode<K, V> node = queue.poll();
                System.out.print(node + "  ");
                if (!node.isLeaf) queue.addAll(node.children);
            }
            System.out.println();
        }
    }

    public void printLeaves() {
        BPlusNode<K, V> node = root;
        while (!node.isLeaf) node = node.children.get(0);
        System.out.print("Hojas: ");
        while (node != null) {
            System.out.print(node.values + " ");
            node = node.next;
        }
        System.out.println();
    }
}
