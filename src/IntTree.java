/**
 * Created by Jakob on 04-10-2016.
 */

// Simple binary tree class that includes methods to construct a
// tree of ints, to print the structure, and to print the data
// using a preorder, inorder or postorder traversal.  The trees
// built have nodes numbered starting with 1 and numbered
// sequentially level by level with no gaps in the tree.  The
// documentation refers to these as "sequential trees."


import java.util.*;

public class IntTree {
    private IntTreeNode overallRoot;

    // pre : max > 0
    // post: constructs a sequential tree with given number of
    //       nodes
    public IntTree(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        overallRoot = buildTree(1, max);
    }

    // post: returns a sequential tree with n as its root unless
    //       n is greater than max, in which case it returns an
    //       empty tree
    private IntTreeNode buildTree(int n, int max) {
        if (n > max) {
            return null;
        } else {
            return new IntTreeNode(n, buildTree(2 * n, max),
                    buildTree(2 * n + 1, max));
        }
    }

    // post: prints the tree contents using a preorder traversal
    public void printPreorder() {
        System.out.print("preorder:");
        printPreorder(overallRoot);
        System.out.println();
    }

    // post: prints the tree contents using a preorder traversal
    // post: prints in preorder the tree with given root
    private void printPreorder(IntTreeNode root) {
        if (root != null) {
            System.out.print(" " + root.data);
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    // post: prints the tree contents using a inorder traversal
    public void printInorder() {
        System.out.print("inorder:");
        printInorder(overallRoot);
        System.out.println();
    }

    // post: prints in inorder the tree with given root
    private void printInorder(IntTreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    // post: prints the tree contents using a postorder traversal
    public void printPostorder() {
        System.out.print("postorder:");
        printPostorder(overallRoot);
        System.out.println();
    }

    // post: prints in postorder the tree with given root
    private void printPostorder(IntTreeNode root) {
        if (root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(" " + root.data);
        }
    }

    // post: prints the tree contents, one per line, following an
    //       inorder traversal and using indentation to indicate
    //       node depth; prints right to left so that it looks
    //       correct when the output is rotated.
    public void printSideways() {
        printSideways(overallRoot, 0);
    }

    // post: prints in reversed preorder the tree with given
    //       root, indenting each line to the given level
    private void printSideways(IntTreeNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.data);
            printSideways(root.left, level + 1);
        }
    }

    public IntTreeNode getRoot() {
        return overallRoot;
    }

    public void setRoot(IntTreeNode root) {
        overallRoot = root;
    }

    //CH17EX01//

    public int countLeftNodes(){
        return countLeftNodes(getRoot());
    }

    private int countLeftNodes(IntTreeNode node){

        if (node == null) {
            return 0;
        }

        int count = 0;

        if (node.left!=null) {
            count += 1 + countLeftNodes(node.left);
        }

        if (node.right!=null) {
            count += countLeftNodes(node.right);
        }
        return count;
    }

    //CH17EX02//

    public int countEmpty() {
        return countEmpty(overallRoot);
    }

    private int countEmpty(IntTreeNode someRoot) {
        //first base case
        if (someRoot == null) {
            return 1;
        }
        //second base case...both null
        else if (someRoot.left == null && someRoot.right == null) {
            return 2;
        }
        //third base case...left null
        else if (someRoot.left == null) {
            return 1 + countEmpty(someRoot.right);
        }
        //last base case...right null
        else if (someRoot.right == null) {
            return 1 + countEmpty(someRoot.left);
        } else {
            return countEmpty(someRoot.left) + countEmpty(someRoot.right);
        }
    }

    //CH17EX03//

    public int depthSum() {

        int height = 1;

        return depthSum(getRoot(), height);

    }

    private int depthSum(IntTreeNode node, int n) {

        if (node == null) {
            return 0;
        }
        else {
            return n * node.data + depthSum(node.left, n + 1) + depthSum(node.right, n + 1);
        }
    }

    //CH17EX04//

    public int countEvenBranches()
    {
        return countEvenBranches(overallRoot);
    }

    private int countEvenBranches(IntTreeNode someRoot) {
        //null base case
        if (someRoot == null) {
            return 0;
        }
        //leaf base case
        else if (someRoot.left == null && someRoot.right == null) {
            return 0;
        }
        //branch recursive case
        else {
            //check if node is even
            if (someRoot.data % 2 == 0) {
                return 1 + countEvenBranches(someRoot.left) + countEvenBranches(someRoot.right);
            }
            //ignore, but recurse

            return countEvenBranches(someRoot.left) + countEvenBranches(someRoot.right);
        }
    }

    //CH17EX05//

    public void printLevel(int someNum) {
        if (someNum < 1) {
            throw new IllegalArgumentException();
        }

        int leftLevel = 1, rightLevel = 1;

        printLevel(leftLevel, rightLevel, someNum, this.overallRoot);
    }

    private void printLevel(int leftLevel, int rightLevel, int targetLevel, IntTreeNode someRoot) {
        if (someRoot == null) {
            //do nothing
        }
        else if (leftLevel == targetLevel) {
            System.out.println(someRoot.data);
        }
        else if (rightLevel == targetLevel) {
            System.out.println(someRoot.data);
        }
        else {
            leftLevel++;
            printLevel(leftLevel, rightLevel, targetLevel, someRoot.left);
            rightLevel++;
            printLevel(leftLevel, rightLevel, targetLevel, someRoot.right);
        }
    }

    //CH17EX06//

    public void printLeaves() {
        printLeaves(overallRoot);
    }

    private void printLeaves(IntTreeNode someNode) {
        if (someNode == null) {
            //do nothing
        } else if (someNode.left == null && someNode.right == null) {
            System.out.print(someNode.data + " ");
        } else {
            printLeaves(someNode.left);
            printLeaves(someNode.right);
        }
    }

    //CH17EX07//

    public boolean isFull(){

        if (getRoot() == null) {
            return true;
        }

        return isFull(getRoot());
    }

    private boolean isFull(IntTreeNode node) {

        if (node == null) {
            return false;
        }

        else if (isFull(node.left) && isFull(node.right)) {
            return true;
        }

        else if (node.left == null && node.right == null) {
            return true;
        }

        else return false;
    }

    //CH17EX08//

    public String toString() {

        if (overallRoot == null) { // If empty tree
            return "empty";
        } else {
            return toString(overallRoot);
        }
    }

    private String toString(IntTreeNode root) {

        String value = "";

        if (root.left != null && root.right != null) { // If both branches exist
            value += "(" + root.data + ", " + toString(root.left)+ ", " + toString(root.right) + ")";
        }

        else if (root.left != null && root.right == null) { // if right branch is empty
            value += "(" + root.data + ", " + toString(root.left) + ", empty)";
        }

        else if (root.left == null && root.right != null) { // if left branch is empty
            value += "(" + root.data + ", empty, " + toString(root.right) + ")";
        }

        else { // If at a leaf
            return "" + root.data;
        }

        return value;
    }





    //CH17EX09//





    //CH17EX10//

    public void doublePositives(){
        doublePositives(getRoot());
    }

    private void doublePositives(IntTreeNode node) {

        if (node == null) {
            return;
        }

        else if (node.data > 0){
            node.data*=2;
            doublePositives(node.left);
            doublePositives(node.right);
        }

        else {

            doublePositives(node.left);
            doublePositives(node.right);
        }

    }





}