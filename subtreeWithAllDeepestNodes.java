// 865.
//time - O(n^2) without map and O(n) with map
//space - O(h) without map and O(n) with map
class Solution {
    
    HashMap<TreeNode, Integer> heights = new HashMap<>(); //stores height of each node
    
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        //edge
        if(root == null)
        {
            return null;
        }
        
        //find height of all nodes in the tree
        //if height of left child of a node is same as height of right child, then this node is root of result subtree
        //if height(left child) > height(right child), then result subtree is in left subtree of current node
        //because the deepest nodes are present in left subtree as it is taller than the right sub tree
        //else if height(left child) < height(right child), then result subtree is in right subtree of current node
        
        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);
        if(leftHeight == rightHeight)
        {
            return root;
        }
        else if(leftHeight > rightHeight) //deepest nodes are in left subtree
        {
            return subtreeWithAllDeepest(root.left);
        }
        else//deepest nodes are in right subtree
        {
            return subtreeWithAllDeepest(root.right);
        }
        //return null; //unreachable code
    }
    
    //time - O(n)
    //space - O(h)
    private int findHeight(TreeNode root) {
        //base
        if(heights.containsKey(root)) //check the cache
        {
            return heights.get(root); 
        }
        if(root == null)
        {
            return -1; //height of empty tree
        }
        //logic
        int leftHeight = findHeight(root.left); //height of left subtree
        int rightHeight = findHeight(root.right);//height of right subtree
        heights.put(root, Math.max(leftHeight, rightHeight) + 1); //update the cache
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
