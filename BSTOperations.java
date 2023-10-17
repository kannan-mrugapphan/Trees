// 701.
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //edge
        if(root == null)
        {
            return new TreeNode(val); //empty tree
        }

        return insertRecursive(root, val);
    }

    //time - O(log n)
    //space - constant
    private TreeNode insert(TreeNode root, int val)
    {
        TreeNode rootCopy = root; //root has to be returned after insert

        //search insert position
        while(root != null)
        {
            if(root.val < val)
            {
                //node has to be inserted in right subtree of root
                if(root.right == null)
                {
                    root.right = new TreeNode(val); //root.right is the insert position
                    break; //insert done
                }
                root = root.right;
            }
            else if(root.val > val)
            {
                //node has to be inserted in left subtree of root
                if(root.left == null)
                {
                    root.left = new TreeNode(val); //root.left is the insert position
                    break; //insert done
                }
                root = root.left;
            }
        }

        return rootCopy;
    }

    //time - O(log n)
    //space - O(log n) for call stack
    private TreeNode insertRecursive(TreeNode root, int val)
    {
        //base
        if(root == null)
        {
            //insert position found
            return new TreeNode(val);
        }

        if(root.val < val)
        {
            //node has to be inserted in right subtree of root
            root.right = insertRecursive(root.right, val);
        }
        else if(root.val > val)
        {
            //node has to be inserted in left subtree of root
            root.left = insertRecursive(root.left, val);
        }

        return root;
    }
}
