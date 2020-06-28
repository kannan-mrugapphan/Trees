// 111.
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null)
        {
            return 0;
        }
        return bfs(root);
    }
    
    //time - O(n) - each node is visited once
    //space - O(h) - height of tree is max call stack size
    private int dfs(TreeNode root) {
        //base
        //if null is hit, then its parent caller has only one child, so this child subtree call is invalid (no root to leaf path) thus return infinity as the parent caller picks the min
        if(root == null)
        {
            return Integer.MAX_VALUE;
        }
        //if leaf is hit, a valid root to leaf path is obtained, so return 1 (the leaf encountered)
        if(root.left == null && root.right == null)
        {
            return 1;
        }
        //logic
        //find min depth on left subtree and find min depth on right subtree
        //return 1 + min among both options
        return 1 + Math.min(dfs(root.left), dfs(root.right));
    }
    
    //time - O(n) - each node is visited once
    //space - O(n) - for queue
    //bfs is more optimal because shortest root to leaf path is first found and returned instead of traversing full tree
    private int bfs(TreeNode root) {
        int depthMin = 1; //start from level 1
        Queue<TreeNode> support = new LinkedList<>(); //queue for bfs
        support.offer(root); //add root to queue initially
        
        while(!support.isEmpty())
        {
            int layerSize = support.size(); //get the current queue size - level size
            for(int i = 0; i < layerSize; i++)
            {
                TreeNode current = support.poll(); //poll the front node
                if(current.left == null && current.right == null)
                {
                    //current is leaf - this is the shortest root to leaf path - as nodes are explored in layers
                    return depthMin; //return number of levels traversed so far - depthMin
                }
                //add left and right children (if present) of the current node into the q
                if(current.left != null)
                {
                    support.offer(current.left);
                }
                if(current.right != null)
                {
                    support.offer(current.right);
                }
            }
            depthMin++; //increment depthMin (number of level traversed so far) after processing the current level
        }
        
        return depthMin;
    }
}
