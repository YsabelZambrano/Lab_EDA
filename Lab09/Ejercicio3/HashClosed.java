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

        while (table[index] != null) {
            if (table[index].getKey() == reg.getKey()) {
                System.out.println("Clave duplicada: " + reg.getKey());
                return;
            }
            index = (index + 1) % size;
            if (index == start) {
                System.out.println("Tabla llena, no se pudo insertar " + reg);
                return;
            }
        }
        table[index] = reg;
        used[index] = true;
        System.out.println("Insertado: " + reg + " en posicion " + index);
    }
    public Register<E> search(int key) {
        int index = hash(key);
        int start = index;

        while (used[index]) {
            if (table[index] != null && table[index].getKey() == key) {
                System.out.println("Encontrado en posicion " + index + ": " + table[index]);
                return table[index];
            }
            index = (index + 1) % size;
            if (index == start) break;
        }

        System.out.println("No se encontro la clave: " + key);
        return null;
    }

    public void delete(int key) {
        int index = hash(key);
        int start = index;

        while (used[index]) {
            if (table[index] != null && table[index].getKey() == key) {
                System.out.println("Eliminado: " + table[index]);
                table[index] = null;
                return;
            }
            index = (index + 1) % size;
            if (index == start) break;
        }

        System.out.println("Clave no encontrada: " + key);
    }

    public void showTable() {
        System.out.println("\nEstado de la tabla hash cerrada");
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

