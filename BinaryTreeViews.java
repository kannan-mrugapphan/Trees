// 199.
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>(); //return list
        //edge
        if(root == null)
        {
            return result;
        }       

        buildViewRecursive(root, 0, result, false);
        return result; 

    }

    //i/p - root, result list and flag to check if left view is needed or right view is needed
    //time - bfs - O(n)
    //space - O(n)
    private void buildView(TreeNode root, List<Integer> result, boolean isLeft)
    {
        Queue<TreeNode> support = new LinkedList<>(); //for bfs
        support.offer(root);

        //as long as queue has more nodes
        while(!support.isEmpty())
        {
            //number of nodes in current level is queue size at iteration start
            int levelSize = support.size(); 
            TreeNode target = null; //tracks left or right most node in current level based on flag
            //process all nodes in current level
            for(int i = 0; i < levelSize; i++)
            {
                TreeNode current = support.poll();
                //check if current is target
                if(isLeft && i == 0)
                {
                    //left most node is the first node in current level
                    target = current;
                }
                else if(!isLeft && i == levelSize - 1)
                {
                    //right most node is last node in current level
                    target = current;
                }

                //offer children to queue for processing in next iteration
                if(current.left != null)
                {
                    support.offer(current.left);
                }
                if(current.right != null)
                {
                    support.offer(current.right);
                }
            }

            result.add(target.val); //add target to result
        }

        return;
    }

    //i/p - root, depth of root, result list and flag to check if left view is needed or right view is needed
    //time - O(n) preorder traversal
    //space - O(h)
    private void buildViewRecursive(TreeNode root, int depth, List<Integer> result, boolean isLeft)
    {
        //base
        //if root is null at start or becomes null due to parent call
        if(root == null)
        {
            return;
        }

        //processs current node
        if(isLeft && result.size() == depth)
        {
            //current node is the 1st node in current depth, add to result
            result.add(root.val);
        }
        else if(!isLeft)
        {
            //current node is the 1st node in current depth, add to result
            if(result.size() == depth)
            {
                result.add(root.val);
            }
            //current node is not the 1st node in current depth, replace the node based on index
            else
            {
                result.set(depth, root.val);
            }
        }

        //children are at next level
        buildViewRecursive(root.left, depth + 1, result, isLeft);
        buildViewRecursive(root.right, depth + 1, result, isLeft);
        return;
    }
}
