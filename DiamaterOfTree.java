// 543.
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] result = new int[]{Integer.MIN_VALUE};
        findHeight(root, result);
        return result[0];
    }

    //diameter may or may not pass through root
    //length of longest path through a node = left subtree height + right subtree height
    //find length of longest path through all nodes and max among them is the diameter
    //i/p - root, result[] of size 0 where 0th index is diameter of tree
    //time - O(n) height of tree code
    //space - O(h)
    private int findHeight(TreeNode root, int[] result)
    {
        //base
        //if root is null at start or becomes null due to a parent call
        if(root == null)
        {
            return 0; //height of empty tree
        }

        int leftSubtreeHeight = findHeight(root.left, result);
        int rightSubtreeHeight = findHeight(root.right, result);
        //find length of longest path through root
        int lps = leftSubtreeHeight + rightSubtreeHeight;
        //update result
        result[0] = Math.max(result[0], lps);

        //return height of root
        return 1 + Math.max(rightSubtreeHeight, leftSubtreeHeight);
    }
}
