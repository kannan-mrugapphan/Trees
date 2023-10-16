//230.
//if kth largest element is asked, perform reverse inorder - right subtree, root, left subtree
//brute force - store inorder traversal in list and return kth index
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // int[] result = new int[2]; //intially [0,0]
        // inorderTraversal(root, k, result);
        // return result[0];

        return inorderTraversalIterative(root, k);
    }

    //i/p - root, k, result[2] where 0th index is kth smallest element and 1st index tracks the position of current node in inorder traversal
    //time - O(n) - inorder traversal
    //space - O(h)
    private void inorderTraversal(TreeNode root, int k, int[] result)
    {
        //base
        if(root == null)
        {
            return;
        }

        inorderTraversal(root.left, k, result); //explore left subtree
        //increase counter to account for current node
        result[1]++;
        //check if root is kth node
        if(result[1] == k)
        {
            result[0] = root.val; //track kth smallest in result
            //don't continue exploring other nodes
            return;
        }

        inorderTraversal(root.right, k, result); //explore left subtree
    }

    //time - O(n) - inorder traversal
    //space - O(h)
    private int inorderTraversalIterative(TreeNode root, int k)
    {
        Stack<TreeNode> support = new Stack<>();

        while(true)
        {
            if(root != null)
            {
                support.push(root);
                root = root.left; //explore left subtree first
            }
            else
            {
                if(support.isEmpty())
                {
                    break; //k > number of nodes in tree
                }

                root = support.pop();
                //account for root;
                k--;

                if(k == 0)
                {
                    //root is kth smallest element
                    return root.val;
                }

                root = root.right; //explore right subtree
            }
        }

        return -1;
    }
}
