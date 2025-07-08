public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        // Inserciones indicadas
        int[] valores = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};

        System.out.println("=== INSERCION ===");
        for (int val : valores) {
            System.out.println("Insertando: " + val);
            tree.insert(val);
        }
        System.out.println("\nRecorrido InOrder:");
        tree.inOrder();

        System.out.println("\nRecorrido PreOrder:");
        tree.preOrder();

        System.out.println("\nRecorrido PostOrder:");
        tree.postOrder();

        // Eliminaciones indicadas
        System.out.println("\n=== ELIMINACIÓN ===");
        for (int val : valores) {
            System.out.println("Eliminando: " + val);
            tree.remove(val);
            System.out.print("InOrder tras eliminar " + val + ": ");
            tree.inOrder();
        }

        System.out.println("\nArbol vacio: " + (tree.isEmpty() ? "Si" : "No"));
    }
}
