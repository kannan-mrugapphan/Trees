// 863.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// approach - make map which has child to parent relation
// have a queue to do bfs
// initially layernumber = 0
// till layer number = k, get layer size 
// iterate through all elements add its left, right and parent if they are not processed
// iff layer = k return elements in queue

// time - O(n)
// space - O(n) for queue, hashmap and set
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        buildParentRelation(root, null, parent);
        
        HashSet<TreeNode> processedNodes = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        
        //exploring nodes in layers so BFS
        Queue<TreeNode> support = new LinkedList<>();
        support.add(target);
        processedNodes.add(target);
        int currentLayer = 0;
        
        while(!support.isEmpty())
        {
            if(currentLayer == K)
            {
                //return elements in queue
                for(TreeNode node : support)
                {
                    result.add(node.val);
                }
                return result;
            }
            currentLayer += 1;
            int layerSize = support.size(); //number of elements in layer befor incrementing currentlayer by 1
            for(int i = 0; i < layerSize; i++)
            {
                TreeNode front = support.poll();
                if(front.left != null && !processedNodes.contains(front.left))
                {
                    support.offer(front.left);
                    processedNodes.add(front.left);
                }
                if(front.right != null && !processedNodes.contains(front.right))
                {
                    support.offer(front.right);
                    processedNodes.add(front.right);
                }
                if(parent.get(front) != null && !processedNodes.contains(parent.get(front)))
                {
                    support.offer(parent.get(front));
                    processedNodes.add(parent.get(front));
                }
            } 
        }
        return result;
    }
    
    public void buildParentRelation(TreeNode root, TreeNode parent, HashMap<TreeNode, TreeNode> map) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        map.put(root, parent);
        buildParentRelation(root.left, root, map);
        buildParentRelation(root.right, root, map);
    }
}
