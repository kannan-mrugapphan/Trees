// 1372.
class Solution {
    public int longestZigZag(TreeNode root) {
        //1st option - start from left of root
        //2nd option start from right of root
        //return max
        return Math.max(findLongestZigZagPath(root.left, 1, true), findLongestZigZagPath(root.right, 1, false)); 
    }

    //i/p - root, depth of root, and whether root was reached from left  or right of parent
    //time - O(n) - preorder traversal
    //space - O(h)
    private int findLongestZigZagPath(TreeNode root, int depth, boolean isLeft)
    {
        //base
        if(root == null)
        {
            return depth - 1; //path can't be extended and +1 accounted to travel to null node should be reduced
        }

        if(isLeft)
        {
            //we can go right and increase path length by 1 or we can go left and start new path with length 1
            //depth increases by 1 and travelled to right, so isLeft is false
            int rightPathLength = findLongestZigZagPath(root.right, depth + 1, false);
            
            //depth is set to 1 as root -> root.left, then root.left is reached from root through left so isLeft is true
            int leftPathLength = findLongestZigZagPath(root.left, 1, true);

            return  Math.max(rightPathLength, leftPathLength);
        }

        //isLeft is false
        //we can go left and increase path length by 1 or we can go right and start new path with length 1
        //depth increases by 1 and travelled to left, so isLeft is true
        int leftPathLength = findLongestZigZagPath(root.left, depth + 1, true);
        
        //depth is set to 1 as root -> root.right, then root.right is reached from root through right so isLeft is false
        int rightPathLength = findLongestZigZagPath(root.right, 1, false);

        return Math.max(rightPathLength, leftPathLength);
    }
}
