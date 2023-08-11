package Question_4;

public class BrothersBinaryTree {
    static class TreeNode {//store information of node
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    //Store nodes depth and parent
    private int xDepth = -1;
    private int yDepth = -1;
    private TreeNode xParent = null;
    private TreeNode yParent = null;

    // Check if nodes x and y are brothers
    public boolean areBrothers(TreeNode root, int x, int y) {
        //checking weather node are brothers or not
        findNodes(root, null, 0, x, y);
        return xDepth == yDepth && xParent != yParent;
    }

    //Using recursive method
    private void findNodes(TreeNode node, TreeNode parent, int depth, int x, int y) {
        if (node == null) {
            // if node is empty then function will return
            return;
        }

        // Update info if node x is found
        if (node.val == x) {
            xDepth = depth;
            xParent = parent;
        }
        // Update info if node y is found
        else if (node.val == y) {
            yDepth = depth;
            yParent = parent;
        }

        // Recurse left and right subtrees
        findNodes(node.left, node, depth + 1, x, y);
        findNodes(node.right, node, depth + 1, x, y);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        int x = 4;
        int y = 3;

        BrothersBinaryTree solution = new BrothersBinaryTree();
        boolean result = solution.areBrothers(root, x, y);
        System.out.println("Nodes " + x + " and " + y + " are brothers: " + result);
    }
}
