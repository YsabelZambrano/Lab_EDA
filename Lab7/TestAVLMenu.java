import java.util.Scanner;

public class TestAVLMenu {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("\n===== MENU AVL =====");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Eliminar nodo");
            System.out.println("3. Buscar nodo");
            System.out.println("4. Mostrar InOrder");
            System.out.println("5. Mostrar PreOrder");
            System.out.println("6. Mostrar PostOrder");
            System.out.println("7. Minimo");
            System.out.println("8. Maximo");
            System.out.println("9. Predecesor");
            System.out.println("10. Sucesor");
            System.out.println("11. ¿Esta vacío?");
            System.out.println("12. Destruir arbol");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Valor a insertar: ");
                    tree.insert(sc.nextInt());
                    break;
                case 2:
                    System.out.print("Valor a eliminar: ");
                    tree.remove(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Valor a buscar: ");
                    System.out.println(tree.search(sc.nextInt()) ? "Si esta en el arbol" : "No se encontro");
                    break;
                case 4:
                    System.out.print("InOrder: ");
                    tree.inOrder();
                    break;
                case 5:
                    System.out.print("PreOrder: ");
                    tree.preOrder();
                    break;
                case 6:
                    System.out.print("PostOrder: ");
                    tree.postOrder();
                    break;
                case 7:
                    System.out.println("Minimo: " + tree.Min());
                    break;
                case 8:
                    System.out.println("Maximo: " + tree.Max());
                    break;
                case 9:
                    System.out.print("Predecesor de: ");
                    Integer p = sc.nextInt();
                    Integer pred = tree.predecesor(p);
                    System.out.println(pred != null ? "Predecesor: " + pred : "No tiene predecesor");
                    break;
                case 10:
                    System.out.print("Sucesor de: ");
                    Integer s = sc.nextInt();
                    Integer succ = tree.sucesor(s);
                    System.out.println(succ != null ? "Sucesor: " + succ : "No tiene sucesor");
                    break;
                case 11:
                    System.out.println(tree.isEmpty() ? "Arbol está vacio" : "Arbol NO esta vacio");
                    break;
                case 12:
                    tree.destroy();
                    System.out.println("Arbol destruido.");
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }

        } while (op != 0);

        sc.close();
    }
}
