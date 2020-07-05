// 298.
class Solution {
    int result = 0; //return value
    public int longestConsecutive(TreeNode root) {
        if(root == null)
        {
            return 0;
        }
        findPath(root, 0, root.val);
        return result;
    }
    
    //time - O(n)
    //space - O(h)
    private void findPath(TreeNode root, int currentPathLength, int expectedValue) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        if(root.val == expectedValue) //if the current root is same as expected value increase current length by 1
        {
            currentPathLength += 1;
        }
        else //else reset current length to 1 (current root is the current increasing path of length 1)
        {
            currentPathLength = 1;
        }
        result = Math.max(result, currentPathLength); //update result
        findPath(root.left, currentPathLength, root.val + 1); //recurse on left and right subtrees
        findPath(root.right, currentPathLength, root.val + 1);
        return;
    }
}
