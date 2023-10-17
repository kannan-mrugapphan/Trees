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

class Solution
{
    //Function to return a list containing the bottom view of the given tree.
    public ArrayList <Integer> bottomView(Node root)
    {
        ArrayList<Integer> result = new ArrayList<>(); //return list
        //edge
        if(root == null)
        {
            return result;
        }
        
        //key - vertical, val - first/last node on vertical based on flag and height of that node in an int[]
        //treemap is needed for storing vertical in sorted order
        //can be optimized with hashmap and storing min and max verticals
        TreeMap<Integer, int[]> store = new TreeMap<>();
        buildStore(root, 0, 0, store, false);
        
        for(Integer vertical : store.keySet())
        {
            result.add(store.get(vertical)[0]);
        }
        
        return result;
    }
    
    //i/p - root, vertical of root, height of root, store and flag to check if top or bottom view is needed
    private void buildStore(Node root, int vertical, int height, TreeMap<Integer, int[]> store, boolean isTop)
    {
        //base 
        if(root == null)
        {
            return;
        }
        
        //process current node
        if(isTop)
        {
            //first node in each vertical is needed if flag is top view
            if(!store.containsKey(vertical))
            {
                store.put(vertical, new int[] { root.data, height }); 
            }
        }
        else
        {
            if(!store.containsKey(vertical) || store.get(vertical)[1] <= height)
            {
                //last node in each vertical at largest is needed if flag is bottom view
                store.put(vertical, new int[] { root.data, height }); 
            }
        }
        
        //left is at -1 vertical and right is at +1 vertical
        buildStore(root.left, vertical - 1, height + 1, store, isTop);
        buildStore(root.right, vertical + 1, height + 1, store, isTop);
        
    }
}

//Bottom View
class Solution
{
    //time - O(n) for bfs + O(number of verticals) to build result list
    //space - O(n) for bfs (queue) + O(number of verticals) for map
    public ArrayList <Integer> bottomView(Node root)
    {
        ArrayList<Integer> result = new ArrayList<>(); //return list
        
        //edge
        if(root == null)
        {
            return result;
        }
        
        int minVertical = Integer.MAX_VALUE; //should be minimized
        int maxVertical = Integer.MIN_VALUE; //should be maximized
        
        Queue<NodeInfo> support = new LinkedList<>(); //for bfs
        support.offer(new NodeInfo(root, 0, 0)); //root is at vertical 0 and level 0
        
        //key - vertical, val - node that will be selected in each vertical for bottom view
        HashMap<Integer, NodeInfo> store = new HashMap<>();
        
        while(!support.isEmpty())
        {
            int levelSize = support.size(); //number of nodes in current level is queue size at iteration start
            //process all nodes in current level
            for(int i = 0; i < levelSize; i++)
            {
                NodeInfo current = support.poll();
                
                //update min and max verticals
                minVertical = Math.min(minVertical, current.vertical);
                maxVertical = Math.max(maxVertical, current.vertical);
                
                //add to store map
                if(!store.containsKey(current.vertical))
                {
                    //first node in current vertical
                    store.put(current.vertical, current);
                }
                else
                {
                    //update prev entry if current node is at larger level or same level (bottom view)
                    //if top view <= becomes >= 
                    if(store.get(current.vertical).level <= current.level)
                    {
                        store.put(current.vertical, current);    
                    }
                }
                
                //process children
                if(current.node.left != null)
                {
                    support.offer(new NodeInfo(current.node.left, current.vertical - 1, current.level + 1));
                }
                
                if(current.node.right != null)
                {
                    support.offer(new NodeInfo(current.node.right, current.vertical + 1, current.level + 1));
                }
            }
        }
        
        //build result list
        for(int vertical = minVertical; vertical <= maxVertical; vertical++)
        {
            result.add(store.get(vertical).node.data);
        }
        
        
        return result;
    }
}

class NodeInfo
{
    public Node node;
    public int vertical;
    public int level;
    
    public NodeInfo(Node _node, int _vertical, int _level)
    {
        this.node = _node;
        this.vertical = _vertical;
        this.level = _level;
    }
}
