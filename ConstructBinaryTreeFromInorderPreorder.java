// 105.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int currentRoot = 0;
    HashMap<Integer, Integer> map;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++)
        {
            map.put(inorder[i], i); //element to index map 
        }
        return treeBuilder2(preorder, inorder, 0, inorder.length);
    }
    
    //time - O(n) neglecting the time to find root in inorder as it could be made O(1) with a hashmap
    //space - O(n) due to extra arrays
    private TreeNode treeBuilder1(int[] preorder, int[] inorder) {
        //base
        if(preorder.length <= 0) //no more elements
        {
            return null;
        }
        //logic
        //build root - first element of preorder array
        TreeNode root = new TreeNode(preorder[0]);
        
        //use a hashmap to find root index in inorder array at constant time
        //find root in inorder array
        int index = 0;
        for(int i = 0; i < inorder.length; i++)
        {
            if(inorder[i] == root.val)
            {
                index = i;
                break;
            }
        }
        
        //builde new inorder and preorder arrays for recursive calls;
        int[] inLeft = Arrays.copyOfRange(inorder, 0, index); //[0, index)
        int[] inRight = Arrays.copyOfRange(inorder, index + 1, inorder.length); //[index + 1, end)
        
        int[] preLeft = Arrays.copyOfRange(preorder, 1, index + 1); //[1, index + 1)
        int[] preRight = Arrays.copyOfRange(preorder, index + 1, preorder.length); //[index + 1, end)
        
        //recurse
        root.left = treeBuilder1(preLeft, inLeft);
        root.right = treeBuilder1(preRight, inRight);
        
        //return root
        return root;
    }
    
    //time - O(n) with constant space ignoring the hashmap
    //only inorder array is used to get the size of subtrees and preorder[] is only for root
    private TreeNode treeBuilder2(int[] preorder, int[] inorder, int start, int end) {
        //base
        if(start > end || currentRoot >= preorder.length)
        {
            return null; //either inorder or preorder is exhausted
        }
        //logic
        //build root
        TreeNode root = new TreeNode(preorder[currentRoot]);
        currentRoot++;
        
        //find root index from map
        int index = map.get(root.val);
        
        //adjust ranges and recurse
        //start is same but end changes to element before root in inorder
        root.left = treeBuilder2(preorder, inorder, start, index - 1); 
        //end is same but start changes to element after root in inorder
        root.right = treeBuilder2(preorder, inorder, index + 1, end);
        
        return root;
    }
}
