// 230.
// brute force - inorder travers tree and store into result list - return k - 1st index
class Solution {
    int counter = 0;
    int result = 0;
    public int kthSmallest(TreeNode root, int k) {
        //edge
        if(root == null)
        {
            return 0;
        }
        dfsRecursive(root, k);
        return result;
    }
    
    //time - O(h + k) - visiting every level irrespective of value of k
    //space - O(h) - max stack size is height of tree
    private int dfsIterative(TreeNode root, int k) {
        Stack<TreeNode> support = new Stack<>(); //mimics call stack
        while(root != null || !support.isEmpty())
        {
            while(root != null)
            {
                support.push(root);
                root = root.left;
            }
            //when processing the node, decrement k and return that node when k hits 0
            TreeNode top = support.pop();
            k--;
            if(k == 0)
            {
                return top.val;
            }
            root = top.right;
        }
        return -1; //code never reahes here
    }
    
    //time - O(h + k)
    //space - O(h) - max stack size is height of tree
    private void dfsRecursive(TreeNode root, int k) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        dfsRecursive(root.left, k); //recurse on left sub tree
        //when processing the node, incremnt counter and return that node when counter hits k
        counter++;
        if(counter == k)
        {
            result = root.val;
            return;
        }
        dfsRecursive(root.right, k); //recurse on right sub tree
        return;
    }
}
