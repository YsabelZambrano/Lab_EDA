public class TestEjercicio1 {
    public static void main(String[] args) {
        BTree<Integer> arbol = new BTree<>(5); // Árbol B de orden 5

        int[] valores = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};

        for (int valor : valores) {
            arbol.insert(valor);
            System.out.println("Insertado: " + valor);
            System.out.println(arbol);
            System.out.println("--------");
        }
    }
}