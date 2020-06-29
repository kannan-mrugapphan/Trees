// 938.
class Solution {
    
    int sum;
    public int rangeSumBST(TreeNode root, int L, int R) {
        sum = 0;
        //edge
        if(root == null)
        {
            return sum;
        }
        dfsIterative(root, L, R); //populates sum
        return sum;
    }
    
    //time - O(n) - worst case all nodes are within [L,R]
    //space - O(h) - max call stack size is height of tree
    private void dfs(TreeNode root, int L, int R) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        //check if current root is within range
        if(root.val >= L && root.val <= R)
        {
            sum += root.val;
        }
        if(L < root.val)
        {
            //possible to find more nodes in left sub tree 
            dfs(root.left, L, R);
        }
        if(R > root.val)
        {
            //possible to find more nodes in right sub tree 
            dfs(root.right, L, R);
        }
    }
    
    //time - O(n) - worst case all nodes are within [L,R]
    //space - O(h) - max stack size is height of tree
    private void dfsIterative(TreeNode root, int L, int R) {
        Stack<TreeNode> support = new Stack<>(); //mimics call stack
        
        while(!support.isEmpty() || root != null)
        {
            while(root != null)
            {
                support.push(root); //initailly push the root into stack
                if(L < root.val)
                {
                    root = root.left; //possible to find valid nodes in left subtree of root
                }
                else
                {
                    root = null; //no need to explore left sub tree
                }
            }
            
            TreeNode top = support.pop(); 
            if(top.val >= L && top.val <= R) //add valid node to running sum
            {
                sum += top.val;
            }
            if(top.val < R)
            {
                root = top.right; //possible to find valid nodes in right subtree of top
            }
            else
            {
                root = null;
            }
        }
        
        return;
    }
}
