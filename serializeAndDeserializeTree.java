// 297.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    
    //time - O(n) with call stack space = O(h)
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //base
        if(root == null)
        {
            return "X,"; //, is a delimiter o distinguisg between "123" where boundary is confusing and "1,2,3" whwre 1 is root 2 is left child and 3 is right child
        }
        //logic
        //return prorder traversal version
        return root.val + "," + serialize(root.left) + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    //space - O(n) for the queue
    public TreeNode deserialize(String data) {
        String encoded = data.substring(0, data.length() - 1); //ignores the last ,
        System.out.println(encoded);
        String[] nodes = encoded.split(",");
        Queue<String> unprocessedNodes = new LinkedList<>(Arrays.asList(nodes)); //using a queue coz of preorder
        return(deserializeUtil(unprocessedNodes));
    }
    
    //time - O(n) where n is the number of nodes in tree
    //sspace - recursive stack - O(h)
    private TreeNode deserializeUtil(Queue<String> unprocessedNodes) {
        String front = unprocessedNodes.poll();
        
        //base
        if(front.equals("X"))
        {
            return null;
        }
        //logic
        //build root which is the 1st element in q as it has preorder
        TreeNode root = new TreeNode(Integer.valueOf(front));
        //recurse
        root.left = deserializeUtil(unprocessedNodes);
        root.right = deserializeUtil(unprocessedNodes);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
