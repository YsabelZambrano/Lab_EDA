public class TestResuelto {
    public static void main(String[] args) {
        BTree<Integer> arbol = new BTree<>(5); // orden 5 como en el ejemplo base

        int[] datos = {10, 20, 30, 40, 50};

        for (int valor : datos) {
            arbol.insert(valor);
            System.out.println("Insertado: " + valor);
            System.out.println(arbol);
        }
    }
}
