// 124.

//post order traversal as a node is processed only after knowing values from its left and right sub trees
//a node can belong to a path 
//max path sum for such a path till that node = node value + max(value from left subtree, value from right sub tree)
//this node can also be starting node of a path = node value
//case1 = max(node value, node value + max(values from left and right subtrees))

//a node can be a root of a path
//case2 = node value + value from left sub tree + value from right sub tree

//case 3 = a node is not part of max path 

//have a global result and update it with max(case1, case2, current global result)

class Solution {
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        //edge
        if(root == null)
        {
            return 0;
        }
        findMaxPath(root);
        return result;
    }
    
    private int findMaxPath(TreeNode root) {
        //base
        if(root == null) //max path sum in an empty tree is 0
        {
            return 0;
        }
        //logic
        int left = findMaxPath(root.left); //find max path sum from right and left sub trees
        int right = findMaxPath(root.right);
        
        //handle 3 cases
        int case1 = Math.max(root.val + Math.max(left, right), root.val);
        int case2 = Math.max(case1, root.val + left + right);
        result = Math.max(result, case2);
        
        return case1; //return case1 to handle that case when current root is not part of max path (case 3)
    }
}
