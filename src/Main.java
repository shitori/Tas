
public class Main {

    public static void main(String[] args) {
        BinomialHeap bh = new BinomialHeap();
        BinomialHeap.insert(bh,new Node(5));
        BinomialHeap.insert(bh,new Node(15));
        BinomialHeap.insert(bh,new Node(25));
        BinomialHeap.insert(bh,new Node(35));
        BinomialHeap.insert(bh,new Node(45));
        bh.getHead().print(0);
        Node min = bh.extractMin();
        System.out.println("arbre apres extraction:");
        bh.getHead().print(0);
        System.out.println("min= "+min.key);

        BinomialHeap bh1 = new BinomialHeap();

        BinomialHeap.insert(bh1,new Node(25));
        BinomialHeap.insert(bh1,new Node(35));
        BinomialHeap.insert(bh1,new Node(15));
        System.out.println("arbre apres all insert:");
        bh1.getHead().print(0);

    }
}
