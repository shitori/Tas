public class Node {
    int key, degree;
    Node parent;
    Node sibling;
    Node child;

    /* Constructor */
    public Node(int k) {
        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }

    int getSize(){
        if (this == null){
            return 0;
        }
        return 1+sibling.getSize()+child.getSize();
    }

    public void print(int level) {
        Node curr = this;
        while (curr != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level; i++) {
                sb.append(" ");
            }
            sb.append(curr.key);
            System.out.println(sb.toString());
            if (curr.child != null) {
                curr.child.print(level + 1);
            }
            curr = curr.sibling;
        }
    }
}