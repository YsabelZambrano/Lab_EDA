public class TestBTree {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(5); // arbol B de orden 5

        int[] valores = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};

        for (int val : valores) {
            tree.insert(val);
            System.out.println("Insertado: " + val);
            System.out.println(tree);
        }
    }
}
