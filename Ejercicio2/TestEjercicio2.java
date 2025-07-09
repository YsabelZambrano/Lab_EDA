public class TestEjercicio2 {
    public static void main(String[] args) {
        BTree<Integer> arbol = new BTree<>(4); // grado 4 (max 3 claves por nodo)

        int[] valores = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};

        System.out.println("----- INSERCION -----\n");

        for (int val : valores) {
            System.out.println("Insertando: " + val);
            arbol.insert(val);
            System.out.println(arbol);
            System.out.println("--------");
        }

        System.out.println("Arbol despues de todas las inserciones:");
        System.out.println(arbol);

        System.out.println("\n----- ELIMINACION -----\n");

        for (int val : valores) {
            System.out.println("Eliminando: " + val);
            arbol.remove(val);
            System.out.println(arbol);
            System.out.println("--------");
        }

        System.out.println("Arbol final despues de todas las eliminaciones:");
        System.out.println(arbol);
    }
}
