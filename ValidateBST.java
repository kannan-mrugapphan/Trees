// 98.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //TreeNode prev = null;
    
    public boolean isValidBST(TreeNode root) {
        return recursiveRangeValidation(root, null, null);
    }
    
    //time - O(n) as each node is visited once, space - O(h) as largest stack size is height of tree
    private boolean iterativeInorderValidation(TreeNode root) {
        Stack<TreeNode> support = new Stack<>();
        TreeNode prev = null;
        while(root != null || !support.isEmpty())
        {
            while(root != null)
            {
                support.push(root);
                root = root.left;
            }
            TreeNode top = support.pop();
            //the prev should always be strictly less than current(top)
            if(prev != null && prev.val >= top.val)
            {
                return false;
            }
            prev = top; //adjusting prev
            root = top.right;
        }
        return true; 
    }
    
    //time - O(n)
    //space - O(h)
    //prev is a global, if local is used, a new prev comes out from call stack and breaking condition is not validated correctly
    private boolean recursiveInorderValidation(TreeNode root) {
        //base
        if(root == null)
        {
            return true;
        }
        //logic
        if(recursiveInorderValidation(root.left) == false)
        {
            return false;
        }
        //the prev should always be strictly less than current(top)
        if(prev != null && prev.val >= root.val)
        {
            return false;
        }
        prev = root; //adjusting prev
        return recursiveInorderValidation(root.right);
    }
    
    //the range of values is checked at each node
    //infinity can also be used instead of null but edge case occurs when a node has value which is infinity
    //time - O(n)
    //space - O(h)
    private boolean recursiveRangeValidation(TreeNode root, Integer min, Integer max) {
        //base
        if(root == null)
        {
            return true;
        }
        if((min != null && root.val <= min) || (max != null && root.val >= max))
        {
            return false;
        }
        //logic
        return (recursiveRangeValidation(root.left, min, root.val) && recursiveRangeValidation(root.right, root.val, max));
    }
}
