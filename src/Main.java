
public class Main {

    public static void main(String[] args) {
        /*BinaryHeap<Integer> a = new BinaryHeap<Integer>();
        a.insert(3);
        a.insert(4);
        a.insert(45);
        a.insert(49);
        a.insert(10);
        a.insert(78);
        a.insert(56);
        a.insert(61);
        a.insert(54);
        a.insert(72);
        a.extractMin();
        a.extractMin();
        a.extractMin();
        a.extractMin();
        a.printer();*/

        BinomialHeap<Integer> t = new BinomialHeap<Integer>();
        t.insert(new BinomialHeap.Node<Integer>(4));
        t.insert(new BinomialHeap.Node<Integer>(2));
        t.insert(new BinomialHeap.Node<Integer>(6));
        t.print();
        t.extractMin();
        t.print();

    }
}
