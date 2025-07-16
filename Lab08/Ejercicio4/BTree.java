import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
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

    public void drawTree() {
        Graph graph = new SingleGraph("BTree");
        graph.setStrict(false);
        graph.setAutoCreate(true);

        graph.setAttribute("ui.stylesheet",
            "node { " +
            "   fill-color: #9AD0EC;" +
            "   shape: box;" +
            "   size-mode: fit;" +
            "   padding: 10px;" +
            "   text-size: 24px;" +
            "   text-color: black;" +
            "   text-style: bold;" +
            "   stroke-mode: plain;" +
            "   stroke-color: black;" +
            "} " +
            "edge { " +
            "   fill-color: #555;" +
            "   arrow-shape: none;" +
            "}");

        drawNode(graph, root, "root");

        for (Node node : graph) {
            node.setAttribute("ui.style", "text-alignment: center;");
        }

        graph.display();
    }

    private void drawNode(Graph graph, BNode<E> node, String nodeId) {
        if (node == null) return;

        if (graph.getNode(nodeId) == null) {
            Node graphNode = graph.addNode(nodeId);

            // 🔍 Aquí vemos qué se está mostrando en cada nodo
            System.out.println("Agregando nodo: " + node.toString());

            graphNode.setAttribute("ui.label", node.toString());
        }

        for (int i = 0; i <= node.count; i++) {
            BNode<E> child = node.childs.get(i);
            if (child != null) {
                String childId = nodeId + "-" + i;
                drawNode(graph, child, childId);
                String edgeId = nodeId + "_" + childId;
                if (graph.getEdge(edgeId) == null) {
                    graph.addEdge(edgeId, nodeId, childId, true);
                }
            }
        }
    }
}
