
public class BinomialHeap {

    private Node head;

    public BinomialHeap() {
        head = null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node x) {
        head = x;
    }

    public Node getMin() {
        Node y = null;
        Node x = head;
        int min = (int) Double.POSITIVE_INFINITY;
        while (x != null) {
            if (x.key < min) {
                min = x.key;
                y = x;
            }
            x = x.sibling;
        }
        return y;
    }

    static void link(Node y, Node z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree += 1;
    }

    static public BinomialHeap union(BinomialHeap t1, BinomialHeap t2) {
        BinomialHeap t = new BinomialHeap();
        t1.merge(t2);
        t.head = t1.head;
        if (t.head == null) {
            return t;
        }
        Node avx = null;
        Node x = t.head;
        Node apx = x.sibling;
        while (apx != null) {
            if (x.degree != apx.degree || apx.sibling != null && apx.sibling.degree == x.degree) {
                avx = x;
                x = apx;
            } else {
                if (x.key < apx.key) {
                    x.sibling = apx.sibling;
                    link(apx, x);
                } else {
                    if (avx == null) {
                        t.head = apx;
                    } else {
                        avx.sibling = apx;
                    }
                    link(x, apx);
                }
                x = apx;
            }
            apx = x.sibling;
        }
        System.out.println("arbre merge:");
        t.head.print(0);
        return t;
    }

    public void merge(BinomialHeap bh) {
        Node t1 = head;
        Node t2 = bh.getMin();
        while (t1 != null && t2 != null) {
            if (t1.degree == t2.degree) {
                Node tmp = t2;
                t2 = t2.sibling;
                tmp.sibling = t1.sibling;
                t1.sibling = tmp;
                t1 = tmp.sibling;
            } else {
                if (t1.degree < t2.degree) {
                    if (t1.sibling == null || t1.sibling.degree > t2.degree) {
                        Node tmp = t2;
                        t2 = t2.sibling;
                        tmp.sibling = t1.sibling;
                        t1.sibling = tmp;
                        t1 = tmp.sibling;
                    } else {
                        t1 = t1.sibling;
                    }
                } else {
                    Node tmp = t1;
                    t1 = t2;
                    t2 = t2.sibling;
                    t1.sibling = tmp;
                    if (tmp == head) {
                        head = t1;
                    }
                }
            }
        }
        if (t1 == null) {
            t1 = head;
            while (t1.sibling != null) {
                t1 = t1.sibling;
            }
            t1.sibling = t2;
        }
    }

    static public void insert(BinomialHeap bh, Node x) {
        if (x != null) {
            BinomialHeap t = new BinomialHeap();
            x.parent = null;
            x.sibling = null;
            x.degree = 0;
            t.head = x;
            if (bh.head == null) {
                bh.setHead(x);
            } else {
                System.out.println("arbre bh:");
                bh.head.print(0);
                System.out.println("arbre t:");
                t.head.print(0);
                bh.setHead(union(bh, t).getHead());
            }
        }
    }

    public Node extractMin() {
        Node x = head;
        Node min = x;
        while (x != null) {
            if (x.key < min.key) {
                min = x;
            }
            x = x.sibling;
        }
        BinomialHeap t = new BinomialHeap();
        BinomialHeap t1 = new BinomialHeap();
        BinomialHeap t2 = new BinomialHeap();
        t2.head = min.sibling;
        x = head;
        while (x != min) {
            //System.out.println("current x= "+x.key);
            //System.out.println("current x.sibling= "+x.sibling.key);
            Node y = x.sibling;
            insert(t1, x);
            x = y;
        }
        union(t1, t2);
        Node orf = min.child;

        while (orf != null) {
            Node y = orf.sibling;
            insert(t, orf);
            orf = y;
        }
        union(t1, t);
        head = t1.head;
        return min;
    }
}