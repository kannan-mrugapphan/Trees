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

// 889.
//time - O(n)
//space - O(n) for map + O(h) for call stack
class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        //edge
        if(preorder == null || preorder.length == 0 || postorder == null || postorder.length == 0)
        {
            return null;
        }

        //map tracks index of each element in postorder[]
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < postorder.length; i++)
        {
            indexMap.put(postorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1, indexMap);
    }

    //start and end pointers track range of preorder and postorder lists
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd, HashMap<Integer, Integer> indexMap)
    {
        //base
        if(preStart > preEnd || postStart > postEnd)
        {
            return null;
        }

        //1st element in preorder list is root
        TreeNode root = new TreeNode(preorder[preStart]);

        //if only 1 element
        if(preStart == preEnd || postStart == postEnd)
        {
            return root;
        }

        //2nd element in preorder is root of left subtree of current root
        int leftChild = preorder[preStart + 1];
        int leftChildIndex = indexMap.get(leftChild);

        //postorder = left, right, root
        //postorder[postStart, leftChildIndex] is part of left subtree
        //postorder[leftChildIndex + 1, postEnd - 1] is part of right subtree
        int leftSubtreeSize = leftChildIndex - postStart + 1; //count in range = end - start + 1

        //preorder[preStart + 1, preStart + leftSubtreeSize] is part of left subtree
        //preorder[preStart + leftSubtreeSize + 1, preEnd] is part of right subtree

        root.left = buildTree(preorder, preStart + 1, preStart + leftSubtreeSize, postorder, postStart, leftChildIndex, indexMap);

        root.right = buildTree(preorder, preStart + leftSubtreeSize + 1, preEnd, postorder, leftChildIndex + 1, postEnd - 1, indexMap);

        return root;
    }
}
