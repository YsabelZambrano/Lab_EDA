public class TestEjercicio1 {
    public static void main(String[] args) {
        BTree<Integer> arbol = new BTree<>(5);

        int[] valores = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};

        System.out.println("----- INSERCION -----\n");
        for (int val : valores) {
            arbol.insert(val);
            System.out.println("Insertado: " + val);
            System.out.println(arbol);
            System.out.println("--------");
        }

        System.out.println("Arbol completo despues de todas las inserciones:");
        System.out.println(arbol);

        System.out.println("\n----- ELIMINACION -----\n");
        for (int val : valores) {
            arbol.remove(val);
            System.out.println("Eliminado: " + val);
            System.out.println(arbol);
            System.out.println("--------");
        }

        System.out.println("Arbol final despues de todas las eliminaciones:");
        System.out.println(arbol);
    }
}
