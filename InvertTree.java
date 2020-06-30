// 226.

class Solution {
    public TreeNode invertTree(TreeNode root) {
        //edge
        if(root == null)
        {
            return root;
        }
        treeInverter(root);
        return root;
    }
    
    //do a post order traversal
    //visit left sub tree followed by right sub tree
    //when processing the node, swap its left and right sub tree
    //time - O(n)
    //space - O(h)
    private void treeInverter(TreeNode root) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        treeInverter(root.left);
        treeInverter(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right= temp;
        return;
    }
}
