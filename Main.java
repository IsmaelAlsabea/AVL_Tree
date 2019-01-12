
public class Main {
    public static void main(String[] args) {
        /* Creating object of AVLTree */
        AVLtree avlt = new AVLtree();
        int count = 0;
        System.out.println("AVLTree Tree Test\n");
        // at 41 there is a LLR D case. Parent is not root.
        // at 90 there is a LRR D case. Parent is not root.
        Node1 nodes[] = { new Node1(100), new Node1(190), new Node1(85), new Node1(200), new Node1(185), new Node1(189),
                new Node1(197), new Node1(195), new Node1(196), new Node1(186), new Node1(192), new Node1(250),
                new Node1(500), new Node1(700), new Node1(550), new Node1(340), new Node1(241), new Node1(50),
                new Node1(41), new Node1(90) };
        for (Node1 a : nodes) {
            avlt.insert(a);
            count += 1;
        }
        avlt.root.display();
        avlt.root.leftChild.display();
        avlt.root.leftChild.leftChild.display();
        avlt.root.leftChild.rightChild.display();
        avlt.inorder();
        System.out.println("\n\n");
        avlt.find(250);
        avlt.delete(185);
        avlt.delete(100);
        avlt.find(90);
        avlt.inorder();
    }
}