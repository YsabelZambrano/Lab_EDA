package Abierto;
public class TestHashOpened {
    public static void main(String[] args) {
        HashOpened<String> hash = new HashOpened<>(8);

        hash.insert(new Register<>(5, "Pepe"));
        hash.insert(new Register<>(21, "Jesus"));
        hash.insert(new Register<>(19, "Juan"));
        hash.insert(new Register<>(16, "Maria"));
        hash.insert(new Register<>(21, "DUPLICADO"));

        hash.showTable();

        System.out.println("\nBusqueda:");
        System.out.println("Buscar 5: " + hash.search(5));
        System.out.println("Buscar 21: " + hash.search(21));

        System.out.println("\nEliminando:");
        hash.delete(21);
        hash.delete(100);

        hash.showTable();
    }
}
