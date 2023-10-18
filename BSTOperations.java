//Search for a target in BST
// 700.
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        return searchRecursive(root, val);
    }

    //time - O(log n)
    //space - constant
    private TreeNode search(TreeNode root, int val)
    {
        //as long as root is not null
        while(root != null)
        {
            //check if root is target
            if(root.val == val)
            {
                return root;
            }

            else if(root.val < val)
            {
                //target is in right subtree
                root = root.right;
            }

            else if(root.val > val)
            {
                //target is in left subtree
                root = root.left;
            }
        }

        return null; //target not found
    }

    //time - O(log n)
    //space - O(log n) for call stack
    private TreeNode searchRecursive(TreeNode root, int val)
    {
        //base
        if(root == null)
        {
            return null; //traget absent
        }

        //check if root is target
        if(root.val == val)
        {
            return root;
        }

        else if(root.val < val)
        {
            //target is in right subtree
            return searchRecursive(root.right, val); 
        }

        //else if(root.val > val)
        //target is in left subtree
        return searchRecursive(root.left, val);
    }
}

//Insert a node in BST
// 701.
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //edge
        if(root == null)
        {
            return new TreeNode(val); //empty tree
        }

        return insertRecursive(root, val);
    }

    //time - O(log n)
    //space - constant
    private TreeNode insert(TreeNode root, int val)
    {
        TreeNode rootCopy = root; //root has to be returned after insert

        //search insert position
        while(root != null)
        {
            if(root.val < val)
            {
                //node has to be inserted in right subtree of root
                if(root.right == null)
                {
                    root.right = new TreeNode(val); //root.right is the insert position
                    break; //insert done
                }
                root = root.right;
            }
            else if(root.val > val)
            {
                //node has to be inserted in left subtree of root
                if(root.left == null)
                {
                    root.left = new TreeNode(val); //root.left is the insert position
                    break; //insert done
                }
                root = root.left;
            }
        }

        return rootCopy;
    }

    //time - O(log n)
    //space - O(log n) for call stack
    private TreeNode insertRecursive(TreeNode root, int val)
    {
        //base
        if(root == null)
        {
            //insert position found
            return new TreeNode(val);
        }

        if(root.val < val)
        {
            //node has to be inserted in right subtree of root
            root.right = insertRecursive(root.right, val);
        }
        else if(root.val > val)
        {
            //node has to be inserted in left subtree of root
            root.left = insertRecursive(root.left, val);
        }

        return root;
    }
}

//Floor and Ceil of a target in BST
// 270.
class Solution {
    public int closestValue(TreeNode root, double target) {
        //closest value is either the floor or ceil of target (one out of two that is more closer to target)
        int floor = findClosestValue(root, target, true);
        int ceil = findClosestValue(root, target, false);

        //if floor is not present, return ceil and vice versa
        if(floor == -1)
        {
            return ceil;
        }
        if(ceil == -1)
        {
            return floor;
        }

        //if ceil is more closer to target than floor
        if(Math.abs(ceil * 1.0 - target) < Math.abs(floor * 1.0 - target))
        {
            return ceil;
        }

        return floor; //floor is more closer
    }

    //returns ceil of target if flag is false else returns floor of target
    //time - O(log n) with constant space
    private int findClosestValue(TreeNode root, double target, boolean flag)
    {
        int result = -1; //to account for cases when ceil (or floor) is not possible for a given target

        while(root != null)
        {
            //check if target is found
            if(root.val * 1.0 == target)
            {
                return root.val; //return int version
            }

            else if(root.val * 1.0 > target)
            {
                //target is in left subtree
                //if ceil is requested, root can potentially be the ceil
                if(!flag)
                {
                    result = root.val;
                }
                root = root.left; //search for target or potentially more closer ones in left
            }

            else if(root.val * 1.0 < target)
            {
                //target is in right subtree
                //if floor is requested, root can potentially be the floor
                if(flag)
                {
                    result = root.val;
                }
                root = root.right; //search for target or potentially more closer ones in right
            }
        }

        return result;
    }
}

// 450.
class Solution {
    //total time - O(log n) for search and O(log n) for delete 
    //total space - constant
    public TreeNode deleteNode(TreeNode root, int key) {
        //edge
        if(root == null)
        {
            return null;
        }

        //search node to be deleted and its parent
        TreeNode parent = null;
        TreeNode target = null;

        //search logic
        //time - O(log n) with constant space
        TreeNode current = root;
        while(current != null)
        {
            //node found
            if(current.val == key)
            {
                target = current;
                break;
            }

            //node is in right subtree
            else if(current.val < key)
            {
                parent = current;
                current = current.right;
            }

            //node is in left subtree
            else if(current.val > key)
            {
                parent = current;
                current = current.left;
            }
        }        

        //target is absent
        if(target == null)
        {
            return root; //no deletion needed
        }

        //parent null and target not null means root of the tree has to be deleted
        if(parent == null)
        {
            return handleDelete(target);
        }

        //delete target node and attach t0 parent
        if(parent.left == target)
        {
            parent.left = handleDelete(target);
        }
        else
        {
            parent.right = handleDelete(target);
        }

        return root; //return after node deletion
    }

    //time - O(log n) to find min in left subtree
    //space - O(1)
    private TreeNode handleDelete(TreeNode target)
    {
        //edge
        if(target.left == null)
        {
            return target.right; //target will be deleted and target's right will be attached to parent
        }
        if(target.right == null)
        {
            return target.left; //target will be deleted and target's left will be attached to parent
        }
        
        //approach - find max in left sub tree, attach right of max in left sub tree to right sub tree, return left subtree to be attached to parent
        TreeNode maxInLeftSubtree = findMax(target.left);
        maxInLeftSubtree.right = target.right;
        return target.left;
    }

    //time - O(log n) to find min in left subtree
    //space - O(1)
    private TreeNode findMax(TreeNode node)
    {
        //max is the rightmost element in bst
        while(node.right != null)
        {
            node = node.right;
        }
        return node;
    }
}
