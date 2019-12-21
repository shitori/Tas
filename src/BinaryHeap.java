import java.util.ArrayList;

/**
 * Cette classe est un proxy pour les ArrayList, c'est à dire les tableaux dynamiques en Java.
 * On utilise cette classe afin d'avoir le contrôle sur la gestion de la mémoire.
 */
public class BinaryHeap<T> {

    private ArrayList<T> data;
    private int capacity;

    public BinaryHeap() {
        this.capacity = 900000;
        this.data = new ArrayList<T>(this.capacity);
    }

    int dad(int i) {
        return (int) (i - 1) / 2;
    }

    int sonLeft(int i) {
        return 2 * i + 1;
    }

    int sonRight(int i) {
        return 2 * i + 2;
    }

    <T> boolean superior(T arg1, T arg2) {
        if (arg1 == null || arg2 == null) {
            return false;
        } else if (arg1 instanceof Integer && arg2 instanceof Integer) {
            int x = (Integer) arg1;
            int y = (Integer) arg2;
            return x > y;
        } else {
            return false;
        }
    }
    void heapUp(int i) {
        int l = sonLeft(i);
        int r = sonRight(i);
        int min;
        if (l < size() && superior(get(i), get(l))) {
            min = l;
        } else {
            min = i;
        }
        if (r < size() && superior(get(i), get(r)) && superior(get(l), get(r))) {
            min = r;
        }
        if (min != i) {
            T tmp = get(i);
            set(i, get(min));
            set(min, tmp);
            heapUp(min);
        }
    }

    T minimum() {
        return get(0);
    }

    T extractMin() {
        if (size() == 0) {
            System.err.println("Tas vide");
            return null;
        }
        T min = get(0);
        set(0, get(size() - 1));
        remove(size() - 1);
        heapUp(0);
        return min;
    }

    void reduceKey(int i, T k) {
        if (superior(k, get(i))) {
            System.err.println("erreur: " + k + ">" + get(i));
            return;
        }
        set(i, k);
        while (i > 0 && superior(get(dad(i)), get(i))) {
            T tmp = get(dad(i));
            set(dad(i), get(i));
            set(i, tmp);
            i = dad(i);
        }
    }

    void insert(T k) throws Exception {
        if (size()==capacity()){
            throw new Exception("capacité max atteind");
        }
        add(k);
        reduceKey(size() - 1, k);
    }

    void printer() {
        System.out.println("Data :" + data.toString());
    }

    T get(int pos) {
        return data.get(pos);
    }

    void set(int pos, T value) {
        data.set(pos, value);
    }

    void add(T value) {
        data.add(value);
    }

    int size() {
        return data.size();
    }

    void remove(int pos) {
        data.remove(pos);
    }

    int capacity() {
        return capacity;
    }
}
