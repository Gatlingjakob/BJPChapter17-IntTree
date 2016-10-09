/**
 * Created by Jakob on 04-10-2016.
 */
// Short program that demonstrates the use of the IntTree class.

public class IntTreeClient {
    public static void main(String[] args) {
        IntTree t = new IntTree(15);
        System.out.println("Tree structure:");
        t.printSideways();
        System.out.println();
        t.printPreorder();
        t.printInorder();
        t.printPostorder();

        //CH17EX01//

        System.out.println("Number of left nodes: " + t.countLeftNodes());

        //CH17EX02//
        System.out.println("Number of empty branches: " + t.countEmpty());

        //CH17EX03//
        System.out.println("Based on the level/depth of the values stored in this tree, the 'depthsum' is: " + t.depthSum());

        //CH17EX04//
        System.out.println("Number of even branches: " + t.countEvenBranches());

        //CH17EX05//
        int level = 3;
        System.out.println("Values on this level: ");
        t.printLevel(level);

        //CH17EX06//
        System.out.println("Leaf values on this tree: ");
        t.printLeaves();
        System.out.println();

        //CH17EX07//
        System.out.println("The tree is full: " + t.isFull());

        //CH17EX08//

        System.out.println(t);

        //CH17EX09//

        //CH17EX10//
        t.doublePositives();
        System.out.println("All positive values in this tree have been doubled:");
        System.out.println(t);

    }
}


