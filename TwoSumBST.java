// 653.
// brute force - store inorder traversal of bst into a list and run 2 sum on it, time - O(n) to get inorder list + O(n) for 2 sum, space - O(n) to store inorder list and O(h) to do inorder traversal

//Approach
//Design an iterator to iterate over the BST in increasing order
//Design an iterator to iterate over the BST in descreasing order
//get next elements from both and check against target

class BSTIterator
{
    private Stack<TreeNode> support; //to keep track of left most or right most elements in tree based on falg
    private boolean flag; //if true it gets next ascending order else descending order

    //time to intialize a bstIterator object is log n
    public BSTIterator(TreeNode root, boolean flag)
    {
        support = new Stack<>();
        this.flag = flag;

        if(flag)
        {
            //get next in ascending order
            //push the left most elements into the stack
            pushLeft(root);
        }

        else
        {
            //get next in descending order
            //push right most elements into stack
            pushRight(root);
        }
    }

    //time - O(log n) with constant space
    private void pushLeft(TreeNode root)
    {
        while(root != null)
        {
            support.push(root);
            root = root.left;
        }
        return;
    }

    //time - O(log n) with constant space
    private void pushRight(TreeNode root)
    {
        while(root != null)
        {
            support.push(root);
            root = root.right;
        }
        return;
    }

    //time and space - constant
    public boolean hasNext()
    {
        return support.size() > 0;
    }

    //peek() should be called only if hasNext() returns true
    //time and space - constant
    public int peek()
    {
        return support.peek().val;
    }

    ///next() should be called only if hasNext() returns true
    //time - O(log n) in worst case and O(1) in avg and space - constant
    public int next()
    {
        TreeNode current = support.pop();
        if(this.flag)
        {
            pushLeft(current.right);
        }
        else
        {
            pushRight(current.left);
        }
        return current.val;
    }
}

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        //edge
        if(root == null)
        {
            return false;
        }

        BSTIterator ascOrder = new BSTIterator(root, true);
        BSTIterator descOrder = new BSTIterator(root, false);

        //as long as there are more elements
        while(ascOrder.hasNext() && descOrder.hasNext())
        {
            int ascOrderCurrent = ascOrder.peek();
            int descOrderCurrent = descOrder.peek();

            if(ascOrderCurrent == descOrderCurrent)
            {
                return false; //2 elements in bst can't be same, so sequence has only 1 remaining element and a pair with sum k is not possible
            }

            if(descOrderCurrent + ascOrderCurrent == k)
            {
                return true; 
            }

            else if(ascOrderCurrent + descOrderCurrent > k)
            {
                //reduce larger element
                descOrder.next(); //gets next larger in next iteration
            }

            else if(ascOrderCurrent + descOrderCurrent < k)
            {
                //reduce smaller element
                ascOrder.next(); //gets next smaller in next iteration
            }
        }

        return false;
    }
}
