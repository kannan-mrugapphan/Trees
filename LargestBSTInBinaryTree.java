// 333.
class SubtreeInfo
{
    public int size;
    public int largest;
    public int smallest;

    public SubtreeInfo()
    {
        this.size = 0; //size of subtree with current node as root
        this.largest = Integer.MIN_VALUE; //largest element in current subtree
        this.smallest = Integer.MAX_VALUE; //smallest elemeent in current subtree
    }
}

class Solution {
    public int largestBSTSubtree(TreeNode root) {
        SubtreeInfo result = findLargestBst(root);
        return result.size;
    }

    //time - O(n) - post order traversal
    //space - O(h)
    private SubtreeInfo findLargestBst(TreeNode root)
    {
        //base
        if(root == null)
        {
            return new SubtreeInfo(); //empty tree is a bst of size 0 with largest as -inf and smallest as inf
        }

        //for a node, the subtree rooted at that node is a bst if its left child is a bst and right child is a bst and
        //node val > largest in left subtree and node val < smallest in right right subtree
        //size of bst rooted at that node = 1 + size of left subtree + size of right subtree
        SubtreeInfo left = findLargestBst(root.left); //find info about left subtree
        SubtreeInfo right = findLargestBst(root.right); //find info about left subtree
        
        SubtreeInfo rootInfo = new SubtreeInfo();
        if(root.val > left.largest && root.val < right.smallest)
        {
            //subtree rooted at current node is bst
            rootInfo.size = left.size + right.size + 1;

            //largest now becomes largest among root, largest in left and largest in right
            rootInfo.largest = Math.max(root.val, Math.max(left.largest, right.largest));
            //smallest now becomes smallest among root, smallest in left and smallest in right
            rootInfo.smallest = Math.min(root.val, Math.min(left.smallest, right.smallest));
        }
        else
        {
            //subtree rooted at current node is not a bst
            //return largest bst among left and right
            rootInfo.size = Math.max(left.size, right.size);

            //as current root is not a bst so setting to inf will cause this to not be used in parent recursive calls
            rootInfo.largest = Integer.MAX_VALUE; 

            //as current root is not a bst so setting to -inf will cause this to not be used in parent recursive calls
            rootInfo.smallest = Integer.MIN_VALUE; 
        }

        return rootInfo;
    }
}
