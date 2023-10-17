//1008.
//breute force -> for each element in preorder list, invoke insert operation on bst -> time = O(n^2) in case of skewed tree
//brute force -> sort preorder to get inorder and build tree with both preorder and inorder -> time - O(nlogn) for sorting plus O(n) extra space

class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return treebuilder(preorder, new int[]{0}, Integer.MAX_VALUE);
    }

    //i/p - preorder list, current index tracking the next available element in preorder list, upper limit for current node
    //time - O(n) 
    //space - O(h) for call stack
    private TreeNode treebuilder(int[] preorder, int[] currentIndex, int bound)
    {
        //base
        if(currentIndex[0] == preorder.length || preorder[currentIndex[0]] >= bound)
        {
            //current index in preorder list can't be used or preorder list is empty
            return null;
        }

        //root of tree is 1st element in preorder list
        TreeNode root = new TreeNode(preorder[currentIndex[0]]);
        currentIndex[0]++; //current index is consumed by root

        root.left = treebuilder(preorder, currentIndex, root.val); //bound for left sub tree of root is root itself
        root.right = treebuilder(preorder, currentIndex, bound); //bound for right sub tree is same

        return root;
    }
}
