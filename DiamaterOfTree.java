// 543.
// case 1 - diamater passes through root => left subtree height + right subtree height + 1 (for the root) is the number of nodes in this path
// case 2 - diamater doesnt pass through root
// find diamater of left and right sub trees and return max 
// base - diamater of empty tree is 0
// time - O(n^2)
// space - O(h)
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        //base
        if(root == null)
        {
           return 0; 
        }
        //logic
        int diameterRoot = findHeight(root.left) + findHeight(root.right);
        int lDiameter = diameterOfBinaryTree(root.left);
        int rDiameter = diameterOfBinaryTree(root.right);
        return Math.max(diameterRoot, Math.max(lDiameter, rDiameter));
    }
    
    //find height method retuns height of tree
    //base - height of empty tree is 0
    //time - O(n)
    //space - O(h)
    private int findHeight(TreeNode root) {
        //base
        if(root == null)
        {
            return 0;
        }
        //logic
        return 1 + Math.max(findHeight(root.left), findHeight(root.right));
    }
}
