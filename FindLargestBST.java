
// time - O(n)
// space - O(h)
class Solution {
    public int largestBSTSubtree(TreeNode root) {
        Subtree result = findLargestBST(root);
        return result.count;
    }
    
    public Subtree findLargestBST(TreeNode root) {
        //empty tree is a bst with 0 nodes 
        if(root == null)
        {
            return new Subtree(0, null, null, true);
        }
        //leaf is a bst with 1 node and smallest, largest = node value
        if(root.left == null && root.right == null)
        {
            return new Subtree(1, root.val, root.val, true);
        }
        
        //get info from left and right sub trees
        Subtree left = findLargestBST(root.left); 
        Subtree right = findLargestBST(root.right);
        
        //current root is a bst if its left and right subtrees are bst and largest element in left sub tree is smaller than root and smallest element
        //in right subtree is larger than root
        //the count of nodes = 1 (for root) + count of left + count of right subtree
        Subtree current = new Subtree();
        if(left.isBST && right.isBST && (left.smallest == null || left.largest < root.val) && (right.smallest == null || root.val < right.smallest))
        {
            current.isBST = true;
            current.count = 1 + left.count + right.count;
            current.smallest = (left.smallest == null) ? root.val : left.smallest; //eg tree 5 with right child as 10 and left child as null
            current.largest = (right.largest == null) ? root.val : right.largest; //eg tree 5 with left child as 1 and right child as null
        }
        else
        {
            current.count = Math.max(left.count, right.count); //current root isn't bst, so have pass largest count in laft and right
        }
        
        return current;
    }
}

//each recursive call returns an object with fields to track if a subtree is a bst or not, number of nodes in that sub tree and the smallest, largest values
class Subtree
{
    public boolean isBST;
    public int count;
    public Integer smallest;
    public Integer largest;
    
    public Subtree() {
        
    }
    
    public Subtree(int count, Integer smallest, Integer largest, boolean isBST) {
        this.isBST = isBST;
        this.count = count;
        this.smallest = smallest;
        this.largest = largest;
    }
}
