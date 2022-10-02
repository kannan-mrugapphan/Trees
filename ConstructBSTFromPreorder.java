//1008.
//breute force -> for each element in preorder list, invoke insert operation on bst -> time = O(n^2) in case of skewed tree
//brute force -> sort preorder to get inorder and build tree with both preorder and inorder -> time - O(nlogn) for sorting plus O(n) extra space

//time - O(n)
//space - O(h)
class Solution {
    
    int rootIndex = 0; //tracks root index in preorder list
    
    public TreeNode bstFromPreorder(int[] preorder) {
        return buildTree(preorder, Integer.MAX_VALUE); //initially root can be any value between -inf and inf
    }
    
    //takes preorder list and upperbound tracks max allowed value for current root
    public TreeNode buildTree(int[] preorder, int upperBound) {
        if(preorder == null || preorder.length <= rootIndex || preorder[rootIndex] > upperBound)
        {
            //1st element in preorder list is invalid
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[rootIndex++]); //1st element in preorder list is root
        
        //recursively build left and right subtrees
        //for left subtree all elements must be smaller than root, so upper bound changes to root - 1
        root.left = buildTree(preorder, root.val - 1); 
        //for tight subtree all elements must be greater than root and less than incomin upper bound
        root.right = buildTree(preorder, upperBound);
        
        return root;
    }
}
