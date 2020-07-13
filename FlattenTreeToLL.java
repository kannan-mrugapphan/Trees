// 114.
class Solution {
    TreeNode prev;
    Stack<TreeNode> support;
    public void flatten(TreeNode root) {
        prev = null; //tracks the node to which the current becomes right child
        support = new Stack<>(); //tracks the right sub tree as right child is changed every iteration
        dfs(root);
        return;
    }
    
    //time - O(n) - every node is hit once
    //space - O(h) - size of support stack is height of tree
    private void dfs(TreeNode root) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        if(prev == null) //current root is the first node in ll i.e is the main root of the ip tree
        {
            prev = root;
        }
        else
        {
            prev.right = root; //make root as right child of prev
            prev.left = null; //set left child to null
            prev = root; //change prev to point to current root
        }
        support.push(root.right); //hold ref of right subtree in stack
        dfs(root.left); //recurse on left subtree
        dfs(support.pop()); //recurse on right subtree - stack top
        return;
    }
}
