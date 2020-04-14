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
    
    int currentRoot;
    HashMap<Integer, Integer> map;
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        currentRoot = postorder.length - 1;
        map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++)
        {
            map.put(inorder[i], i); //element to index map 
        }
        return treeBuilder2(postorder, inorder, 0, inorder.length - 1);
    }
    
    //time - O(n) neglecting the time to find root in inorder as it could be made O(1) with a hashmap
    //space - O(n) due to extra arrays
    private TreeNode treeBuilder1(int[] inorder, int[] postorder) {
        //base
        if(postorder.length <= 0) //no more elements
        {
            return null;
        }
        //logic
        //build root
        //last element in post[] is root
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        
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
        
        //builde new inorder and postorder arrays for recursive calls;
        int[] inLeft = Arrays.copyOfRange(inorder, 0, index); //[0, index)
        int[] inRight = Arrays.copyOfRange(inorder, index + 1, inorder.length); //[index + 1, end)
        
        int[] postLeft = Arrays.copyOfRange(postorder, 0, index); //[0, index)
        int[] postRight = Arrays.copyOfRange(postorder, index, postorder.length - 1); //[index, end - 1)
        
        //recurse
        root.left = treeBuilder1(inLeft, postLeft);
        root.right = treeBuilder1(inRight, postRight);
        
        //return root
        return root;
    }
    
    //time - O(n) with constant space ignoring the hashmap
    //only inorder array is used to get the size of subtrees and postorder[] is only for root
    private TreeNode treeBuilder2(int[] postorder, int[] inorder, int start, int end) {
        //base
        if(start > end || currentRoot < 0)
        {
            return null; //either inorder or postorder is exhausted
        }
        //logic
        //build root
        TreeNode root = new TreeNode(postorder[currentRoot]);
        currentRoot--;
        
        //find root index from map
        int index = map.get(root.val);
        
        //adjust ranges and recurse
        //first build right because postorder 
        //the second last element in post[] is root of right subtree of root, so process right first
        //end is same but start changes to element after root in inorder
        root.right = treeBuilder2(postorder, inorder, index + 1, end);
        //start is same but end changes to element before root in inorder
        root.left = treeBuilder2(postorder, inorder, start, index - 1); 
        
        
        return root;
    }
}
