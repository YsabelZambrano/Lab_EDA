import java.util.Scanner;

public class MenuHashOpened {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashOpened<String> hash = new HashOpened<>(8); 

        int opcion;
        do {
            System.out.println("\n MENU HASH ABIERTO ");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Buscar elemento");
            System.out.println("3. Eliminar elemento");
            System.out.println("4. Mostrar tabla");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Clave (entera): ");
                    int clave = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Valor (texto): ");
                    String valor = scanner.nextLine();
                    hash.insert(new Register<>(clave, valor));
                    break;
                case 2:
                    System.out.print("Clave a buscar: ");
                    int buscar = scanner.nextInt();
                    hash.search(buscar);
                    break;
                case 3:
                    System.out.print("Clave a eliminar: ");
                    int eliminar = scanner.nextInt();
                    hash.delete(eliminar);
                    break;
                case 4:
                    hash.showTable();
                    break;
                case 5:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 5);

        scanner.close();
    }
}

