
// 993.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//time - O(n)
//space - O(h) for call stack
//cousins have same depth but different parents
//so track the depth and parent of both x and y & compare
class Solution {
    
    TreeNode xParent;
    TreeNode yParent;
    int xLevel;
    int yLevel;
    
    public boolean isCousins(TreeNode root, int x, int y) {
        findDepthAndParent(root, x, y, 0, null);
        return (xLevel == yLevel) && (xParent != yParent);
    }
    
    private void findDepthAndParent(TreeNode root, int x, int y, int level, TreeNode parent) {
        //base
        if(root == null)
        {
            return;
        }
        //if x and y are hit update level and parent
        if(root.val == x)
        {
            xLevel = level;
            xParent = parent;
        }
        if(root.val == y)
        {
            yLevel = level;
            yParent = parent;
        }
        //logic
        findDepthAndParent(root.left, x, y, level + 1, root);
        findDepthAndParent(root.right, x, y, level + 1, root);
    }
}
