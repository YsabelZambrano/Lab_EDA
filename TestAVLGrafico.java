import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class TestAVLGrafico {
    static AVLTree<Integer> tree = new AVLTree<>();
    static Graph graph = new SingleGraph("Árbol AVL");

    public static void main(String[] args) {
        int[] valores = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};

        for (int v : valores)
            tree.insert(v);

        System.setProperty("org.graphstream.ui", "swing");
        graph.setAttribute("ui.stylesheet", "node { fill-color: lightblue; text-size: 18px; }");

        graph.display();

        graficar(tree.getRoot(), null, false);
    }

    // Metodo recursivo para graficar el arbol
    public static void graficar(NodeAVL<Integer> nodo, String padre, boolean esIzquierdo) {
        if (nodo == null) return;

        String id = nodo.data.toString();
        graph.addNode(id).setAttribute("ui.label", id);

        if (padre != null) {
            String edgeId = padre + "-" + id;
            graph.addEdge(edgeId, padre, id, true); // conexion dirigida
        }

        graficar(nodo.left, id, true);
        graficar(nodo.right, id, false);
    }
}

