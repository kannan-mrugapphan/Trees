// 285.
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return findInorderSuccessor(root, p);
    }

    //time - O(log n)
    //space - constant
    private TreeNode findInorderSuccessor(TreeNode root, TreeNode p)
    {
        TreeNode sucessor = null; //to account for case when target is largest in bst and doesn't have a successor

        //as long as there are more nodes
        while(root != null)
        {
            if(root.val <= p.val)
            {
                //all elements in left sub tree including the root are smaller than p and can never be the successor
                //root might be equal to p
                //sucessor will lie on the right
                root = root.right;
            }

            else if(root.val > p.val)
            {
                //all elements in right subtree are larger than p
                //root is also larger than p
                //update result with root, discard right sub tree and continue searching in left for target
                sucessor = root;
                root = root.left;
            }
        }

        return sucessor;
    }
}
