package Abierto;
public class Register<E> implements Comparable<Register<E>> {
    private int key;
    private E value;
    private boolean deleted;

    public Register(int key, E value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }

    public int getKey() {
        return key;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        this.deleted = true;
    }

    @Override
    public int compareTo(Register<E> other) {
        return Integer.compare(this.key, other.key);
    }

    @Override
    public String toString() {
        return (deleted ? "eliminado " : "") + key + ": " + value;
    }
}
