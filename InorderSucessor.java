// 285.
// brute force - find inorder traversal store it in list[] 
//time - O(log n)
//space - constant
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //edge
        if(root == null)
        {
            return null;
        }
        //if given node has a right subtree, then its inorder sucessor is the minimum node in right subtree of p
        if(p.right != null)
        {
            return findMin(p.right);
        }
        //if the right sub tree of p is null,
        //then its in order sucessor is the node obtained by taking the last left while seraching p in tree
        return search(root, p);
    }
    
    //time - O(h)
    //space - constant
    public TreeNode findMin(TreeNode root) {
        //go as left as possible and return that node whose left child is null
        while(root.left != null)
        {
            root = root.left;
        }
        return root;
    }
    
    //time - O(log n)
    //space - constant
    private TreeNode search(TreeNode root, TreeNode target) {
        TreeNode sucessor = null; //return node
        while(root != target)
        {
            if(target.val > root.val)
            {
                root = root.right;
            }
            else //store current in result if left path is taken
            {
                sucessor = root;
                root = root.left;
            }
        }
        return sucessor;
    }
}
