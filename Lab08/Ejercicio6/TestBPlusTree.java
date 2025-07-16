public class TestBPlusTree {
    public static void main(String[] args) {
        BPlusTree tree = new BPlusTree();

        int[] insertList = {100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190};

        for (int key : insertList) {
            tree.insert(key);
        }

        System.out.println("Tree after insertions:");
        tree.printLeaves();

        for (int key : insertList) {
            tree.delete(key);
        }

        System.out.println("Tree after deletions:");
        tree.printLeaves();
    }
}
