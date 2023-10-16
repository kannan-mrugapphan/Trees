// 236.
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findLca(root, p, q);
    }

    //time - O(n) - preorder traversal
    //space - O(h)
    private TreeNode findLca(TreeNode root, TreeNode p, TreeNode q)
    {
        //base
        if(root == null)
        {
            return null; //both p and q not found from root to this null node
        }

        //check if current node is either p or q
        if(root == p || root == q)
        {
            return root; //one of the two nodes is found
        }

        //find p or q in left and right children
        TreeNode left = findLca(root.left, p, q);
        TreeNode right = findLca(root.right, p, q);

        //if current is not p or q and both aren't found in left as well as right
        if(left == null && right == null)
        {
            return null; //both these nodes aren't part of subtree with current node as root
        }

        //current node is not p or q
        //one of p or q is found on left and the other is found on right
        //current is lca after which 2 nodes diverge in diff paths
        if(left != null && right != null)
        {
            return root;
        }

        //both p and q is not found in right
        //either p or q or both found in left
        //current can't be the lca as the divergence is not at current
        //left might be lca if both p and q are found, return left
        if(left != null)
        {
            return left;
        }

        //just right is not null here
        //both p and q is not found in left
        //either p or q or both found in right
        //current can't be the lca as the divergence is not at current
        //right might be lca if both p and q are found, return right
        return right;
    }
}

// 235.
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findLcaRecursive(root, p, q);
    }

    //time - O(log n)
    //space - constant
    private TreeNode findLca(TreeNode root, TreeNode p, TreeNode q)
    {
        //as long as root is not p or q
        while(root != null)
        {
            //check if root is lca
            //one of p or q lies on left of root and the other is on right of root
            if((root.val > p.val && root.val < q.val) || (root.val > q.val && root.val < p.val))
            {
                return root;
            }

            //one of the nodes is found
            //so far from split didn't happen. so the other node will also be decendant of current
            //so current is lca
            else if(root == p || root == q)
            {
                return root;
            }

            //both p and q are smaller or larger than root
            else if(root.val > p.val && root.val > q.val)
            {
                //both lies on left of root
                root = root.left;
            }

            else if(root.val < p.val && root.val < q.val)
            {
                //both lies on right of root
                root = root.right;
            }
        }

        //lca not found
        return null;
    }

    //time - O(log n)
    //space - O(h)
    private TreeNode findLcaRecursive(TreeNode root, TreeNode p, TreeNode q)
    {
        //base
        if(root == null)
        {
            return null; //lca not found as null is reached
        }

        //check if root is the divergent node
        if((root.val > p.val && root.val < q.val) || (root.val > q.val && root.val < p.val))
        {
            return root; //root is lca as one is on left and the other is on right
        }

        //check if root is either p or q
        else if(root == p || root == q)
        {
            return root; //the other node is decendant of root, root is lca
        }

        //both nodes either lie on left or right of root
        else if(root.val > p.val && root.val > q.val)
        {
            return findLcaRecursive(root.left, p, q); //both lie on left
        }

        //else if(root.val < p.val && root.val < q.val)
        return findLcaRecursive(root.right, p, q); //both lie on right
    }
}
