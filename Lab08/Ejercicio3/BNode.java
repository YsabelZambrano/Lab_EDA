import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    public BNode(int orden) {
        this.count = 0;
        this.keys = new ArrayList<>(orden - 1);
        this.childs = new ArrayList<>(orden);

        for (int i = 0; i < orden - 1; i++) keys.add(null);
        for (int i = 0; i < orden; i++) childs.add(null);
    }

    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }

    public boolean searchNode(E key, int[] pos) {
        pos[0] = 0;
        while (pos[0] < count && key.compareTo(keys.get(pos[0])) > 0) {
            pos[0]++;
        }
        return pos[0] < count && key.compareTo(keys.get(pos[0])) == 0;
    }

    public String toString() {
        return keys.subList(0, count).toString();
    }
}
