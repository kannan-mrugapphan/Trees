// 114.
class Solution {

    TreeNode prev = null;

    public void flatten(TreeNode root) {
        flattenIterative(root);
        return;
    }

    //     root
    //     /   \
    //  left  right

    //  after flattening the tree to LL, root -> left -> right
    // left subtree has to be falttened, right subtree has to be flattened, then root's right must be assigned to flattened left and last element in flattened's left must be assigned to flattened right
    // if left is flattened first and assign to root's right, root's right reference might be lost
    // so right has to flattened first then left

    //time - O(n) - reverse postorder traversal
    //space - O(h)
    private void flattenRecursive(TreeNode root)
    {
        //base
        if(root == null)
        {
            return;
        }

        flatten(root.right);
        flatten(root.left);

        //assign right of root to prev and left of root to null
        root.right = prev;
        root.left = null;
        prev = root; //set prev to root for caller 
    }

    //time - O(n) - preorder traversal
    //space - O(h)
    private void flattenIterative(TreeNode root)
    {
        //edge
        if(root == null)
        {
            return;
        }

        Stack<TreeNode> support = new Stack<>(); //to simulate pre-order traversal
        support.push(root);

        while(!support.isEmpty())
        {
            TreeNode current = support.pop();

            //process children
            if(current.right != null)
            {
                support.push(current.right);
            }
            if(current.left != null)
            {
                support.push(current.left);
            }

            //children are already accounted for (inserted into stack)
            //fix current
            if(!support.isEmpty())
            {
                current.right = support.peek(); //points to current's left
            }
            else
            {
                current.right = null;
            }
            
            current.left = null; //set left to null
        }

        return;
    }

} 

// 426.
class Solution {
    public Node treeToDoublyList(Node root) {
        //edge
        if(root == null)
        {
            return root;
        }        

        Node[] boundary = new Node[2];
        flattenIterative(root, boundary);

        //fix head and tail
        boundary[1].right = boundary[0];
        boundary[0].left = boundary[1];

        return boundary[0]; //return head
    }

    //flattens tree into dll based on inorder
    //i/p root and Node[] of size 2 where 1st element is head of ll and next element is tail of ll
    // time - O(n) - inorder traversal
    // space - O(h)
    private void flatten(Node root, Node[] boundary)
    {
        //base
        if(root == null)
        {
            return;
        }

        //explore left subtree
        flatten(root.left, boundary);

        //fix current root
        if(boundary[1] == null)
        {
            //current root is the very first node in inorder traversal
            boundary[0] = root; //set head
            boundary[1] = root; //mark current root as last visited node
        }
        else
        {
            //current node is in the middle of inorder traversal
            boundary[1].right = root; //set right of prev to current
            root.left = boundary[1]; //set left of current to prev, left can be updated as it is already traversed
            boundary[1] = root; //update last visited
        }

        //explore right subtree
        flatten(root.right, boundary); 
    }

    // time - O(n) - inorder traversal
    // space - O(h)
    private void flattenIterative(Node root, Node[] boundary)
    {
        Stack<Node> support = new Stack<>(); //for traversal
        while(true)
        {
            if(root != null)
            {
                support.push(root);
                root = root.left; //explore left subtree
            }
            else
            {
                if(support.isEmpty())
                {
                    break;
                }
                
                root = support.pop(); //returns from last left node

                //fix current root
                if(boundary[1] == null)
                {
                    //current root is the very first node in inorder traversal
                    boundary[0] = root; //set head
                    boundary[1] = root; //mark current root as last visited node
                }
                else
                {
                    //current node is in the middle of inorder traversal
                    boundary[1].right = root; //set right of prev to current
                    root.left = boundary[1]; //set left of current to prev, left can be updated as it is already traversed
                    boundary[1] = root; //update last visited
                }

                root = root.right; //explore right subtree
            }
        }

        return;
    }
}
