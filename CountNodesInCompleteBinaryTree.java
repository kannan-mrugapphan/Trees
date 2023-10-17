// 222.
class Solution {
    public int countNodes(TreeNode root) {
        //edge
        if(root == null)
        {
            return 0;
        }

        //if a binary tree is full have nodes in all levels and if h is the number of levels then the count of nodes is 2^h - 1
        //if a binary tree is full, then its left height (number of nodes in left most path) is same as right height (number of nodes in right most path)
        return count(root);
    }

    //time - O(h) with constant space
    private int findHeight(TreeNode root, boolean flag)
    {
        //if flag is true, it returns left height or right height
        int result = 0;
        while(root != null)
        {
            result += 1; //account for current root
            if(flag)
            {
                root = root.left;
            }
            else
            {
                root = root.right;
            }
        }

        return result;
    }

    //preorder traversal
    //in each recursive call, if the root is not a full binary tree, one of its children will be ful binary tree because the whole tree is complete
    //each recursive call takes log n time (to find left and right height)
    //in total there will be log n recursive calls
    //time - O((log n) ^ 2)
    //space - O(log n)
    private int count(TreeNode root)
    {
        if(root == null)
        {
            return 0;
        }

        int leftHeight = findHeight(root, true);
        int rightHeight = findHeight(root, false);

        //if heights are same
        if(leftHeight == rightHeight)
        {
            return (int) Math.pow(2, leftHeight) - 1; //num of levels = left height or right height
        }

        //if heights are not same, number of nodes is size of left subtree + size of right subtree + 1
        return count(root.left) + count(root.right) + 1;
    }
}
