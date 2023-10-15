// 110.
class Solution {
    public boolean isBalanced(TreeNode root) {
        //for a binary tree to be balanced,
        //|left subtree height - right subtree height| <= 1 for all nodes  
        int height = checkBalancedAndFindHeight(root);  
        if(height == -1)
        {
            return false;
        }    
        return true;
    }

    //returns height of node
    //time - O(n) - postorder traversal
    //space - O(h)
    private int findHeight(TreeNode root)
    {
        //base
        //if root is null at start or becomes null due to a parent call
        if(root == null)
        {
            return 0; //height of empty tree
        }

        //height of current root = 1 + max(height of left subtree, height of right subtree)
        int leftSubtreeHeight = findHeight(root.left);
        int rightSubtreeHeight = findHeight(root.right);
        return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
    }

    //checks if i/p tree is balanced
    //time - O(n^2) as each node is traversed and lh-rh is computed for each node
    //space - O(h^2) 
    private boolean check(TreeNode root)
    {
        //base
        //if root is null at start or becomes null due to a parent call
        if(root == null)
        {
            return true; //empty tree is balanced
        }

        //check if current node is valid
        int leftSubtreeHeight = findHeight(root.left);
        int rightSubtreeHeight = findHeight(root.right);
        if(Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1)
        {
            return false; //current root is invalid as abs diff is above 1
        }

        //current root is valid, check left and right sub trees
        return check(root.left) && check(root.right);
    }

    //returns -1 if root is not balanced else returns height of root
    //time - O(n) -> find height code
    //space - O(h)
    private int checkBalancedAndFindHeight(TreeNode root)
    {
        //base
        //if root is null at start or becomes null due to a parent call
        if(root == null)
        {
            return 0; //height of empty tree
        }

        //height of current root = 1 + max(height of left subtree, height of right subtree)
        int leftSubtreeHeight = checkBalancedAndFindHeight(root.left);
        //check if left subtree is balanced
        if(leftSubtreeHeight == -1)
        {
            return -1;
        }

        int rightSubtreeHeight = checkBalancedAndFindHeight(root.right);
        //check if right subtree is balanced
        if(rightSubtreeHeight == -1)
        {
            return -1;
        }
        
        //check if current root is balanced
        if(Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1)
        {
            return -1; //current root not balanced
        }

        //current root balanced, return its height
        return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight); 
    }
}
