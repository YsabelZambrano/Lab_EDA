public class TestHashClosed {
    public static void main(String[] args) {
        HashClosed<String> hash = new HashClosed<>(11);

        int[] claves = {100, 5, 14, 15, 22, 16, 17, 32, 13, 32, 100};
        for (int clave : claves) {
            hash.insert(new Register<>(clave, "valor-" + clave));
        }

        hash.showTable();

        System.out.println("\nBusqueda:");
        System.out.println("Buscar 32: "+hash.search(32));
        System.out.println("Buscar 200: "+hash.search(200));

        System.out.println("\nEliminando:");
        hash.delete(17);
        hash.delete(100);

        hash.showTable();
    }
}

