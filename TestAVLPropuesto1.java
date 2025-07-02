public class TestAVLPropuesto1 {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        int[] valores = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};

        System.out.println("=== INSERCION DE VALORES ===");
        for (int val : valores) {
            System.out.println("Insertando: " + val);
            tree.insert(val);
        }

        System.out.println("\n--- RECORRIDOS DEL ARBOL ---");
        System.out.print("InOrder: ");
        tree.inOrder();
        System.out.print("PreOrder: ");
        tree.preOrder();
        System.out.print("PostOrder: ");
        tree.postOrder();

        System.out.println("\n=== ELIMINACION DE VALORES ===");
        for (int val : valores) {
            System.out.println("Eliminando: " + val);
            tree.remove(val);
            System.out.print("InOrder tras eliminar " + val + ": ");
            tree.inOrder();
        }

        System.out.println("\nArbol vacio: " + (tree.isEmpty() ? "Si" : "No"));
    }
}
