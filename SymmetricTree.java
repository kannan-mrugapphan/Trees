// 101.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//consider checking whether an array is mirror of itself, so we will start from start and end & compare till start and end cross over - so intuition - 2 nodes to compare
//time - O(n)
//space - O(h)
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    
    public boolean isMirror(TreeNode p, TreeNode q) {
        //base
        if(p == null && q == null) //both null
        {
            return true;
        }
        if(p == null || q == null) //only one is null
        {
            return false;
        }
        //logic
        //if val of p and q are same
        //then compare left of p with right of q and right of p with left of q for equality
        if(p.val == q.val) 
        {
            return isMirror(p.left, q.right) && isMirror(p.right, q.left);
        }
        return false;
    }
}
