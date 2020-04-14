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
    
    // 100.
    //time - O(n)
    //space - O(h)
    public boolean isSameTree(TreeNode p, TreeNode q) {
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
        //then compare left of p with left of q and right of p with right of q for equality
        if(p.val == q.val) 
        {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
    
    // 572.
    //time - O(n ^ 2)
    //space - O(h)
    public boolean isSubtree(TreeNode s, TreeNode t) {
        //t - sub tree and s - main tree
        //base
        if(t == null)
        {
            return true; //null is a subtree of any tree
        }
        if(s == null)
        {
            return false; //main tree itself is null
        }
        if(isSameTree(s, t))
        {
            return true; //both trees are same
        }
        
        //logic
        //recurse on left and right to find same tree
        return (isSubtree(s.left, t) || isSubtree(s.right, t));
    }
}
