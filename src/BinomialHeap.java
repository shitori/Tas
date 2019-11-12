import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;

public class BinomialHeap<T> {
    public static class Node<T> {
        public T key;
        public int degree;
        public Node<T> parent;
        public Node<T> child;
        public Node<T> sibling;

        public Node() {
            key = null;
            parent = null;
            sibling = null;
            child = null;
            degree = 0;
        }

        public Node(T x) {
            key = x;
            parent = null;
            sibling = null;
            child = null;
            degree = 0;
        }

        public void print(int level) {
            Node<T> curr = this;
            while (curr != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < level; i++) {
                    sb.append(" ");
                }
                sb.append(curr.key.toString());
                System.out.println(sb.toString());
                if (curr.child != null) {
                    curr.child.print(level + 1);
                }
                curr = curr.sibling;
            }
        }

    }

    private Node<T> head;

    public BinomialHeap() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
    }

    public Node<T> minimum() {
        Node y = new Node();
        Node x = head;
        T min = head.key;
        while (x != null) {
            if (superior(min, (T) x.key)) {
                min = (T) x.key;
                y = x;
            }
            x = x.sibling;
        }
        return y;
    }

    void link(Node y, Node z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree += 1;
    }

    Node<T> merge(BinomialHeap<T> heap1, BinomialHeap<T> heap2) {
        if (heap1.head == null) {
            return heap2.head;
        } else if (heap2.head == null) {
            return heap1.head;
        } else {
            Node<T> head;
            Node<T> heap1Next = heap1.head;
            Node<T> heap2Next = heap2.head;

            if (heap1.head.degree <= heap2.head.degree) {
                head = heap1.head;
                heap1Next = heap1Next.sibling;
            } else {
                head = heap2.head;
                heap2Next = heap2Next.sibling;
            }

            Node<T> tail = head;

            while (heap1Next != null && heap2Next != null) {
                if (heap1Next.degree <= heap2Next.degree) {
                    tail.sibling = heap1Next;
                    heap1Next = heap1Next.sibling;
                } else {
                    tail.sibling = heap2Next;
                    heap2Next = heap2Next.sibling;
                }

                tail = tail.sibling;
            }

            if (heap1Next != null) {
                tail.sibling = heap1Next;
            } else {
                tail.sibling = heap2Next;
            }

            return head;
        }
    }

    BinomialHeap<T> union(BinomialHeap<T> heap1, BinomialHeap<T> heap2) {
        BinomialHeap<T> t = new BinomialHeap<T>();
        t.head = merge(heap1, heap2);
        if (t.head == null) {
            return t;
        }
        Node<T> beforeX = null;
        Node<T> x = t.head;
        Node<T> afterX = x.sibling;
        while (afterX != null) {
            if (x.degree != afterX.degree || afterX.sibling != null && afterX.sibling.degree == x.degree) {
                beforeX = x;
                x = afterX;
            } else {
                if (superiorEqual(afterX.key, x.key)) {
                    x.sibling = afterX.sibling;
                    link(afterX, x);
                } else {
                    if (beforeX == null) {
                        t.head = afterX;
                    } else {
                        beforeX.sibling = afterX;
                    }
                    link(x, afterX);
                }
                x = afterX;
            }
            afterX = x.sibling;
        }
        return t;
    }

    void insert(Node<T> x) {
        BinomialHeap<T> tbis = new BinomialHeap<T>();
        x.parent = null;
        x.sibling = null;
        x.degree = 0;
        tbis.head = x;
        tbis = union(this, tbis);
        this.head = tbis.head;
    }

    T findMin() {
        Node<T> next = this.head.sibling;
        T min = head.key;
        while (next != null) {
            if (superior(min, next.key)) {
                min = next.key;
            }
            next = next.sibling;
        }
        return min;
    }

    Node<T> extractMin() {
        T min = findMin();
        BinomialHeap<T> t = new BinomialHeap<T>();
        BinomialHeap<T> t1= new BinomialHeap<T>();
        BinomialHeap<T> t2= new BinomialHeap<T>();
        Node<T> x = this.head;
        while (x.key != min) {
            Node<T> tmp = x;
            t1.insert(tmp);
            x = x.sibling;
        }
        Node<T> y = x.child;
        Node<T> z = new Node<>();

        while (y!=null){
            Node<T> tmp = z;
            z = new Node<>();
            z.key = y.key;
            z.sibling = tmp;
            y=y.sibling;
        }
        t2.head = z;
        t = union(t1,t2);
        this.head = t.head;
        return x;
    }

    public void print() {
        System.out.println("Binomial heap:");
        if (head != null) {
            head.print(0);
        }
    }

    boolean superior(T arg1, T arg2) {
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

    boolean superiorEqual(T arg1, T arg2) {
        if (arg1 == null || arg2 == null) {
            return false;
        } else if (arg1 instanceof Integer && arg2 instanceof Integer) {
            int x = (Integer) arg1;
            int y = (Integer) arg2;
            return x >= y;
        } else {
            return false;
        }
    }


}
