import java.util.ArrayList;
import java.util.Collections;

public class BNode<E extends Comparable<E>> {
    public ArrayList<E> keys;
    public ArrayList<BNode<E>> childs;
    public int count;

    public BNode(int orden) {
        keys = new ArrayList<>(Collections.nCopies(orden - 1, null));
        childs = new ArrayList<>(Collections.nCopies(orden, null));
        count = 0;
    }

    public boolean searchNode(E key, int[] pos) {
        pos[0] = 0;
        while (pos[0] < count && key.compareTo(keys.get(pos[0])) > 0)
            pos[0]++;
        return (pos[0] < count && key.compareTo(keys.get(pos[0])) == 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
