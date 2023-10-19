// 1448.
class Solution {
    public int goodNodes(TreeNode root) {
        return countGoodNodes(root, Integer.MIN_VALUE);
    }

    //time - O(n) - preorder traversal
    //space - O(h)
    private int countGoodNodes(TreeNode root, int largestSoFar)
    {
        //base
        if(root == null)
        {
            return 0; //no more good nodes
        }

        //process current node
        int count = 0; //return value
        if(root.val >= largestSoFar)
        {
            //root is a good node
            count += 1;
        }

        //get count of good nodes from left and right paths
        //largestSeenSoFar is max between current root and the prev largest seen
        //eg: 10 -> 12 when passsing max to children of 12, it is max(10, 12) = 12
        //eg: 10 -> 1 when passsing max to children of 1, it is max(10, 1) = 1
        int left = countGoodNodes(root.left, Math.max(root.val, largestSoFar)); 
        int right = countGoodNodes(root.right, Math.max(root.val, largestSoFar));

        return left + right + count;
    }
}
