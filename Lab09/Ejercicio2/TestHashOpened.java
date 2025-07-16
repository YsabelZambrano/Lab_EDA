public class TestHashOpened {
    public static void main(String[] args) {
        HashOpened<String> hash = new HashOpened<>(8); 

        // Inserta
        hash.insert(new Register<>(5, "Pepe"));
        hash.insert(new Register<>(21, "Jesus"));
        hash.insert(new Register<>(19, "Juan"));
        hash.insert(new Register<>(16, "Maria"));
        hash.insert(new Register<>(21, "DUPLICADO"));

        // Muestrala tabla
        hash.showTable();

        // Busca
        System.out.println("\nBusqueda:");
        hash.search(5);
        hash.search(21);

        // Elimina
        System.out.println("\nEliminando:");
        hash.delete(21);
        hash.delete(100);

        // Muestra la tabla final
        hash.showTable();
    }
}
