public class HashClosed<E> {
    private Register<E>[] table;
    private boolean[] used;
    private int size;

    @SuppressWarnings("unchecked")
    public HashClosed(int capacity) {
        table = new Register[capacity];
        used = new boolean[capacity];
        size = capacity;
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(Register<E> reg) {
        int index = hash(reg.getKey());
        int start = index;
        do {
            if (table[index] == null) {
                table[index] = reg;
                used[index] = true;
                System.out.println("Agregado: " + reg);
                return;
            } else if (table[index].getKey() == reg.getKey()) {
                System.out.println("Clave duplicada: " + reg.getKey());
                return;
            }
            index = (index + 1) % size;
        } while (index != start);
        System.out.println("La tabla está llena" + reg);
    }

    public Register<E> search(int key) {
        int index = hash(key);
        int start = index;
        do {
            if (table[index] != null && table[index].getKey() == key) {
                return table[index];
            } else if (!used[index]) {
                return null;
            }
            index = (index + 1) % size;
        } while (index != start);
        return null;
    }

    public void delete(int key) {
        int index = hash(key);
        int start = index;
        do {
            if (table[index] != null && table[index].getKey() == key) {
                System.out.println("Eliminado: " + table[index]);
                table[index] = null;
                return;
            } else if (!used[index]) {
                break;
            }
            index = (index + 1) % size;
        } while (index != start);
        System.out.println("La clave no se encuentra:" + key);
    }

    public void showTable() {
        System.out.println("\n Estado de la Tabla Hash cerrado");
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            if (table[i] != null) {
                System.out.println(table[i]);
            } else {
                System.out.println("vacio");
            }
        }
    }
}
