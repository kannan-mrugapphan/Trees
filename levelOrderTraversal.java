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
