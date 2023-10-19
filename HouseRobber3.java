// 337.
class Solution {
    public int rob(TreeNode root) {
        int[] result = findMaxProfit(root);
        return Math.max(result[0], result[1]);
    }

    //a decision on whether a house can be robbed or not can be made only after the profits from children are available
    //time - O(n) - postorder traversal
    //space - O(h)
    private int[] findMaxProfit(TreeNode root)
    {
        //base
        if(root == null)
        {
            //if the tree is empty, max profit including the root (0th index) is 0 and max profit without including the root is also 0 (index 1)
            return new int[]{0, 0}; 
        }

        //option 1 - rob from current node
        //max profit = current node value + skip current's left + skip current's right
        //option 2 - skip current node
        //max profit = max(rob current's left, skip current's left) + max(rob current's right, skip current's right) 
        //max is max among 2 options

        int[] left = findMaxProfit(root.left);
        int[] right = findMaxProfit(root.right);

        int[] result = new int[2];
        //include current node
        result[0] = root.val + left[1] + right[1];
        //skip current node
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return result;
    }
}
