// 129.

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
    public int sumNumbers(TreeNode root) {
        int[] result = new int[1];
        findSum(root, result, 0);
        return result[0];
    }
    
    //currentSum is local coz, we have to revert back to previous state
    public void findSum(TreeNode root, int[] result, int currentSum) {
        //base
        if(root == null)
        {
            return;
        }
        if(root.left == null && root.right == null) //leaf
        {
            result[0] += root.val + currentSum;
            return;
        }
        //logic
        currentSum = (currentSum + root.val) * 10; //pass on to next level
        //recurse
        findSum(root.left, result, currentSum);
        findSum(root.right, result, currentSum);
    }
    
    // call stack
    // Input: [4,9,0,5,1]
    // fs(4, 0) -> fs(9, 40) -> fs(5, 490) -> base adds 495 to result
    //                       -> fs(1, 490) -> base adds 491 to result
    //          -> fs(4, 40) -> base adds 40 to result
}
