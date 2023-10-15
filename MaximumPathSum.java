// 124.
class Solution {
    public int maxPathSum(TreeNode root) {
        int[] result = new int[] { Integer.MIN_VALUE };
        findMaxSum(root, result);
        return result[0];
    }

    //i/p - root, result[] where 0th index is max path sum in tree
    //time - O(n) - find height code
    //space - O(h)
    private int findMaxSum(TreeNode root, int[] result)
    {
        //base
        //if root is null at start or becomes null due to call
        if(root == null)
        {
            return 0;
        }

        //max sum through current root is root's val + max sum from left + max sum from right
        int leftSum = Math.max(0, findMaxSum(root.left, result));
        int rightSum = Math.max(0, findMaxSum(root.right, result));

        //    10
        //   /  \
        //  -3  -4
        //leftSum = -3, rightSum = -4, maxSumThroughRoot = 10
        //so make leftSum and rightSum as 0 (ignore them) when they are -ve
        //theoretically the case maxSumThroughRoot ignoring both children

        //compute max sum through root
        int maxSumThroughRoot = leftSum + rightSum + root.val;
        result[0] = Math.max(result[0], maxSumThroughRoot); //update result

        //to caller, root can either attach with left subtree or right subtree because a path is needed, the other option is it can ignore both and return just itself
        //  node -> root -> left
        //               \
        //                -> right

        //possible paths are either node -> root -> left or node -> root -> right
        //return path with max sum
        return Math.max(root.val + leftSum, root.val + rightSum);
    }
}
