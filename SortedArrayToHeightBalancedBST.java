// 108.

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
//space - O(h)
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) //edge
        {
            return null;
        }
        return treeBuilder(nums, 0, nums.length - 1);
    }
    
    private TreeNode treeBuilder(int[] nums, int start, int end) {
        //base
        if(start > end)
        {
            return null;
        }
        //build root
        int middle = (start + end) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        
        //recurse
        root.left = treeBuilder(nums, start, middle - 1);
        root.right = treeBuilder(nums, middle + 1, end);
        
        return root;
    }
}
