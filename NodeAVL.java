public class NodeAVL<T extends Comparable<T>> {
    T data;
    NodeAVL<T> left, right;
    int height;

    public NodeAVL(T data) {
        this.data = data;
        this.height = 1;
    }
}