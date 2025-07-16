import java.util.Scanner;

public class TestBTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el grado del arbol B: ");
        int grado = sc.nextInt();
        BTree<Integer> arbol = new BTree<>(grado);

        int[] datos = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};

        int opcion;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Insertar clave");
            System.out.println("2. Eliminar clave");
            System.out.println("3. Buscar clave");
            System.out.println("4. Mostrar arbol");
            System.out.println("5. Mostrar minimo");
            System.out.println("6. Mostrar maximo");
            System.out.println("7. Predecesor");
            System.out.println("8. Sucesor");
            System.out.println("9. Insertar datos Ejercicio 1");
            System.out.println("10. Eliminar todos los datos");
            System.out.println("11. Vaciar arbol (destroy)");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese clave a insertar: ");
                    int ins = sc.nextInt();
                    arbol.insert(ins);
                    break;
                case 2:
                    System.out.print("Ingrese clave a eliminar: ");
                    int del = sc.nextInt();
                    arbol.remove(del);
                    break;
                case 3:
                    System.out.print("Ingrese clave a buscar: ");
                    int buscar = sc.nextInt();
                    System.out.println(arbol.search(buscar) ? "Clave encontrada" : "Clave NO encontrada");
                    break;
                case 4:
                    System.out.println(arbol.writeTree());
                    break;
                case 5:
                    System.out.println("Minimo: " + arbol.min());
                    break;
                case 6:
                    System.out.println("Maximo: " + arbol.max());
                    break;
                case 7:
                    System.out.print("Clave para predecesor: ");
                    int pre = sc.nextInt();
                    System.out.println("Predecesor: " + arbol.predecesor(pre));
                    break;
                case 8:
                    System.out.print("Clave para sucesor: ");
                    int suc = sc.nextInt();
                    System.out.println("Sucesor: " + arbol.sucesor(suc));
                    break;
                case 9:
                    for (int val : datos) arbol.insert(val);
                    System.out.println("Datos insertados.");
                    break;
                case 10:
                    for (int val : datos) arbol.remove(val);
                    System.out.println("Datos eliminados.");
                    break;
                case 11:
                    arbol.destroy();
                    System.out.println("Arbol vaciado.");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 0);

        sc.close();
    }
}
