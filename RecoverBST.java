// 99.
// brute force - do inorder traversal and store in list - there could be either 1 or 2 pairs of nodes violating the increasing order nature - swap the first value in 1st pair with last value in second pair to reconstruct increasing order - if 2 adjacent elements in inorder traversal are swapped, then only 1 pair of nodes violate the increasing order - in that case swap them to get a linear time and linear space solution
// time - O(n)
// space - constant
class Solution {
    //first and middle tracks the 1st pair violating the increasing order
    //last tracks the last element of second pair violating the increasing order
    TreeNode first = null;
    TreeNode middle = null;
    TreeNode last = null;
    
    TreeNode prev = null; //support pointer
    public void recoverTree(TreeNode root) {
        //edge
        if(root == null)
        {
            return;
        }
        recursive(root); //populate pointers
        //swap and return
        if(last != null)
        {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
            return;
        }
        else
        {
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
            return;
        }
    }
    
    private void iterative(TreeNode root) {
        Stack<TreeNode> support = new Stack<>(); //mimics call stack
        while(root != null || !support.isEmpty())
        {
            while(root != null) //recursing on left subtree
            {
                support.push(root);
                root = root.left;
            }
            TreeNode top = support.pop(); //processing node
            if(prev != null && prev.val >= top.val)
            {
                //a pair violating increasing order is found
                //check if its the 1st or 2nd pair and set the appropriate pointers
                if(first == null)
                {
                    first = prev;
                    middle = top;
                }
                else
                {
                    last = top;
                }
            }
            prev = top;
            root = top.right; //recursing on right subtree
        }
        return;
    }
    
    private void recursive(TreeNode root) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        recursive(root.left); 
        if(prev != null && prev.val >= root.val)
        {
            //a pair violating increasing order is found
            //check if its the 1st or 2nd pair and set the appropriate pointers
            if(first == null)
            {
                first = prev;
                middle = root;
            }
            else
            {
                last = root;
            }
        }
        prev = root;
        recursive(root.right);
        return;
    }
}
