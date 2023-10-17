// 662.
// time - O(n) - bfs
// space - constant
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        //edge
        if(root == null)
        {
            return 0;
        }

        int result = 0; //return val - has to be maximized
        Queue<NodeInfo> support = new LinkedList<>(); //for bfs
        support.offer(new NodeInfo(root, 0)); //root is at position 0

        while(!support.isEmpty())
        {
            int levelSize = support.size(); //number of nodes in current level is queue size at start of iteration;

            //tracks left most and right most node in each level
            NodeInfo left = null;
            NodeInfo right = null;

            //process all nodes in current level
            for(int i = 0; i < levelSize; i++)
            {
                NodeInfo current = support.poll();

                if(i == 0)
                {
                    left = current; //left most node in current level
                }
                //else if is not possible as there can be only 1 node in a level
                if(i == levelSize - 1)
                {
                    right = current; //right most node in current level
                }

                //add children to be processed in next iteration
                if(current.node.left != null)
                {
                    //if node is at position i then its left is 2*i + 1
                    support.offer(new NodeInfo(current.node.left, (current.position * 2 + 1)));
                }

                if(current.node.right != null)
                {
                    //if node is at position i then its left is 2*i + 2
                    support.offer(new NodeInfo(current.node.right, (current.position * 2 + 2)));
                }
            }

            //update result with max distance in current level
            result = Math.max(result, right.position - left.position + 1);
        }

        return result;
    }
}

class NodeInfo
{
    public TreeNode node;
    public int position;

    public NodeInfo(TreeNode node, int position)
    {
        this.node = node;
        this.position = position;
    }
}
