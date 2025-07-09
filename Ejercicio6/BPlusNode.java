import java.util.ArrayList;

public class BPlusNode {
    boolean isLeaf;
    ArrayList<Integer> keys;
    ArrayList<BPlusNode> children;
    BPlusNode next;

    public BPlusNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.next = null;
    }
}
