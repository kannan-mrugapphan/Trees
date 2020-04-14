// 102.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//time - O(m + n) for bfs but m = n - 1 so O(n)
//space - O(n) queue can have max of n elements at a time
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
        {
            return result;
        }
        //BFS so use a queue
        Queue<TreeNode> support = new LinkedList<>();
        support.offer(root);
        
        while(!support.isEmpty())
        {
            int layerSize = support.size(); //get the number of nodes in each layer
            List<Integer> layerNodes = new ArrayList<>();
            for(int i = 0; i < layerSize; i++)
            {
                //push the popped element into arraylist
                //push the left and right childer (if present) of the popped node into queue
                TreeNode front = support.poll();
                layerNodes.add(front.val);
                if(front.left != null)
                {
                    support.offer(front.left);
                }
                if(front.right != null)
                {
                    support.offer(front.right);
                }
            }
            result.add(layerNodes);
        }
        
        return result;
    }
}

//recursive implementation using dfs(preorder)
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrderRecursive(result, root, 0); //root is at level 0
        return result;
    }
    
    //approach - if size of result list is same as level, create n new list, add node to it and add it to result
    //else get the list corresponding to that level and add root to that list
    //level is a local copy because, each node has different levels
    //time - O(n)
    //space - O(h)
    private void levelOrderRecursive(List<List<Integer>> result, TreeNode root, int level) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        if(level == result.size())
        {
            List<Integer> temp = new ArrayList<>();
            temp.add(root.val);
            result.add(temp);
        }
        else
        {
            result.get(level).add(root.val);
        }
        levelOrderRecursive(result, root.left, level + 1);
        levelOrderRecursive(result, root.right, level + 1);
    }
    
    // call stack
    // lor(3, 0) -> [[0]] -> lor(9, 1) -> [[3], [9]] -> lor(null, 2) 
    //                                               <- returns
    //                                               -> lor(null, 2)
    //                                               <- returns
    //           -> lor(20, 1) -> [[3], [9,20]] -> lor(15, 2) -> [[3], [9,20], [15]] -> lor(null, 3)
    //                                                                               <- returns
    //                                                                               -> lor(null, 3)
    //                                                                               <- returns
    //                                           -> lor(7, 2) -> [[3], [9,20], [15, 7]] -> lor(null, 4)
    //                                                                                  <- returns
    //                                                                                  -> lor(null, 4)
    //                                                                                 <- returns
        
}
