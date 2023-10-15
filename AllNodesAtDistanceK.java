// 863.
// space - O(n) for parent map and O(n) if bfs or O(h) if dfs
// time - O(n) to build parent map and O(n) to find target nodes
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>(); //return list

        //edge
        if(root == null)
        {
            return result;
        }

        //tracks parent of each node in tree
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap); //root doesn't have parent

        HashSet<TreeNode> visited = new HashSet<>();
        findNodesAtKDistanceRecursive(target, parentMap, result, k, 0, visited);
        return result;
    }

    //preorder traversal
    //time - O(n)
    //space - O(h)
    private void buildParentMap(TreeNode root, TreeNode parent, HashMap<TreeNode, TreeNode> parentMap)
    {
        //base
        if(root == null)
        {
            return;
        }

        //build current root - parent relationship
        parentMap.put(root, parent);

        //explore the children
        buildParentMap(root.left, root, parentMap); //root is parent of its left child
        buildParentMap(root.right, root, parentMap); //root is parent of its left child
    }

    //time - O(n) - bfs - worst case k = last level
    //space - O(n)
    private void findNodesAtKDistance(TreeNode target, HashMap<TreeNode, TreeNode> parentMap, List<Integer> result, int k)
    {
        Queue<NodeInfo> support = new LinkedList<>(); //for bfs
        support.offer(new NodeInfo(target, 0)); //current node is at level 0
        HashSet<TreeNode> visited = new HashSet<>(); //needed to avoid processing same parent again
        visited.add(target);

        while(!support.isEmpty())
        {
            //number of nodes in current level is queue size at start of iteration
            int levelSize = support.size();
            //process all nodes in current level
            for(int i = 0; i < levelSize; i++)
            {
                NodeInfo current = support.poll();
                
                //if current node is at level k, add to result
                if(current.level == k)
                {
                    result.add(current.node.val);
                    continue; //no need to process children and parent of current node as they will be at level k + 1
                }
                
                TreeNode parent = parentMap.get(current.node);

                //parent, child of current node are at 1 + current level
                if(current.node.left != null && !visited.contains(current.node.left))
                {
                    support.offer(new NodeInfo(current.node.left, current.level + 1));
                    visited.add(current.node.left);
                }
                if(current.node.right != null && !visited.contains(current.node.right))
                {
                    support.offer(new NodeInfo(current.node.right, current.level + 1));
                    visited.add(current.node.right);
                }
                if(parent != null && !visited.contains(parent))
                {
                    support.offer(new NodeInfo(parent, current.level + 1));
                    visited.add(parent);
                }
            }
        }
    }

    //time - O(n) - preorder traversal
    //space - O(h)
    private void findNodesAtKDistanceRecursive(TreeNode target, HashMap<TreeNode, TreeNode> parentMap, List<Integer> result, int k, int currentLevel, HashSet<TreeNode> visited)
    {
        //base
        if(target == null || visited.contains(target))
        {
            return;
        }

        //check if current node is at level k
        if(currentLevel == k)
        {
            result.add(target.val);
        }
        visited.add(target); //mark current as visited

        //explore children and target
        findNodesAtKDistanceRecursive(target.left, parentMap, result, k, currentLevel + 1, visited);
        findNodesAtKDistanceRecursive(target.right, parentMap, result, k, currentLevel + 1, visited);
        findNodesAtKDistanceRecursive(parentMap.get(target), parentMap, result, k, currentLevel + 1, visited);
    }
}

class NodeInfo
{
    public TreeNode node;
    public int level;

    public NodeInfo(TreeNode _node, int _level)
    {
        this.node = _node;
        this.level = _level;
    }
}
