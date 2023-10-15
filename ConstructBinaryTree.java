// 105.
// time - O(n) - all nodes have to built
// space - O(h)
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //edge
        if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0)
        {
            return null; //empty tree
        }

        //tracks index of each element in inorder[]
        //map can be used as all nodes in tree have unique value
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++)
        {
            indexMap.put(inorder[i], i);
        }

        return treeBuilder(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, indexMap);
    }

    //i/p - preorder list, 2 pointers to track range of preorder, inorder list, 2 pointers to track range of inorder list and index map of inorder list
    private TreeNode treeBuilder(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart, int inorderEnd, HashMap<Integer, Integer> indexMap)
    {
        //base
        if(preorderStart > preorderEnd || inorderStart > inorderEnd)
        {
            return null; //no more nodes
        }

        //1st element in preorder list is root
        TreeNode root = new TreeNode(preorder[preorderStart]);
        //find index of root in inorder list
        int rootIndex = indexMap.get(root.val);
        
        //for inorder traversal
        //all elements from inorderStart to rootIndex - 1 in inorder traversal will be in left
        //all elements from rootIndex + 1 to inorderEnd in inorder traversal will be in right
        int leftSubtreeCount = rootIndex - 1 - inorderStart + 1; //count in range = end - start + 1
        
        //for preorder traversal
        //all elemnts from preorderStart + 1 to preorderStart + leftSubtreeCount will be in left
        //all elements from preorderStart + leftSubtreeCount + 1 to preorderEnd will be in right

        //build left subtree of root
        root.left = treeBuilder(preorder, preorderStart + 1, preorderStart + leftSubtreeCount, inorder, inorderStart, rootIndex - 1, indexMap);

        //build right subtree of root
        root.right = treeBuilder(preorder, preorderStart + leftSubtreeCount + 1, preorderEnd, inorder, rootIndex + 1, inorderEnd, indexMap);

        return root;
    }
}
