public class TestVisual {
    public static void main(String[] args) {
        BTree<Integer> arbol = new BTree<>(4); // ARBOL B de grado 4

        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};

        for (int n : datos) {
            arbol.insert(n);
        }

        arbol.drawTree(); // Muestra el árbol con GraphStream
    }
}
