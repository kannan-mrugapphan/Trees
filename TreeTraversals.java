//144.
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>(); //return list
        preorderTraversalIterative(root, result);
        return result;
    }

    //preorder traversal - root, left, right
    //each node is processed, left subtree call returns, right subtree call returns
    //so each node is hit thrice
    //time - O(n)
    //size of call stack is largest when exploring the longest path in tree
    //space - O(h)
    private void preorderTraversal(TreeNode root, List<Integer> result)
    {
        //base - if root is initially null or if root becomes null from parent call
        if(root == null)
        {
            return;
        }

        //process node - add to result
        result.add(root.val);
        //explore left subtree
        preorderTraversal(root.left, result);
        //explore right subtree
        preorderTraversal(root.right, result);
    }

    //each node is pushed into and popped from stack once
    //time - O(n)
    //space - stack size is largest when exploring longest path - O(h)
    private void preorderTraversalIterative(TreeNode root, List<Integer> result)
    {
        //edge
        if(root == null)
        {
            return;
        }
        
        Stack<TreeNode> support = new Stack<>(); //mimics call stack
        support.push(root); //start from root

        //as long as stack has nodes
        while(!support.isEmpty())
        {
            TreeNode current = support.pop(); //get current node
            result.add(current.val); //process current
            //explore left first then right
            //stack is lifo, so push right then left to get left in next iteration
            if(current.right != null)
            {
                support.push(current.right);
            }
            if(current.left != null)
            {
                support.push(current.left);
            }
        }

        return;
    }
}

//145.
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>(); //return list
        postorderTraversalIterative(root, result);
        return result;
    }

    //left subtree is explored, right subtree is explored, root is processed last
    //each node is called from caller, left subtree call returns, right subtree call returns, node is added to result
    //time - O(n) - each node is processed thrice
    //space - implicit call stack is largest when exploring longest path - O(h)
    private void postorderTraversal(TreeNode root, List<Integer> result)
    {
        //base
        //if root is null initially or if current root is null because of call from parent
        if(root == null)
        {
            return;
        }
        //explore left subtree
        postorderTraversal(root.left, result);
        //explore right subtree
        postorderTraversal(root.right, result);
        //process root
        result.add(root.val);
        return; 
    }

    //each node is pushed into and popped from stack once
    //time - O(n)
    //size of stack is largest when exploring longest path
    //space - O(h)
    private void postorderTraversalIterative(TreeNode root, List<Integer> result)
    {
        //edge
        if(root == null)
        {
            return;
        }
        
        Stack<TreeNode> support = new Stack<>(); //mimics implit call stack
        support.push(root); //start from root

        //as long as stack has more nodes
        while(!support.isEmpty())
        {
            TreeNode current = support.pop(); //this node has to be processed only after its left and right children are processed
            result.add(0, current.val); //add to end of result list - use dll to have constant time for add operation

            //in next iteration, right subtree has to be processed so that it is inserted just to left of root
            //so insert left first then right as right is picked in next iteration
            if(current.left != null)
            {
                support.push(current.left);
            }
            if(current.right != null)
            {
                support.push(current.right);
            }
        }

        return;
    }
}

// 94.
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>(); //return list
        inorderTraversalIterative(root, result);
        return result;
    }

    //left subtree is processed first, then root is processed then right subtree is processed
    //time - O(n) - each node is called from parent, left subtree call returns, node is printed, right subtree call returns - node is processed thrice
    //space - O(h) - length of implicit call stack is largest when exploring longest path
    private void inorderTraversal(TreeNode root, List<Integer> result)
    {
        //base
        //if root is null at first or becomes null because of a parent call
        if(root == null)
        {
            return;
        }
        //explore left subtree
        inorderTraversal(root.left, result);
        result.add(root.val); //process node
        //explore right subtree
        inorderTraversal(root.right, result);
    }

    //time - O(n) - each node is pushed into and popped from stack once
    //space - O(h) - size of stack is longest when exploring longest path
    private void inorderTraversalIterative(TreeNode root, List<Integer> result)
    {
        //edge
        if(root == null)
        {
            return;
        }

        Stack<TreeNode> support = new Stack<>(); //mimics call stack
        TreeNode current = root; //copy reference of root
        while(true)
        {
            //explore left subtree of current until current becomes null
            if(current != null)
            {
                support.push(current); //current refernce is needed as it has to be proccessed after its left subtree
                current = current.left;
            }
            //current is null -> stack's top is caller of this current null
            else
            {
                if(support.isEmpty())
                {
                    break; //caller is also null, inorder is complete
                }
                current = support.pop(); 
                result.add(current.val); //process current -> add to result
                current = current.right; //explore right subtree of current
            }
        }

        return;
    }
}

// 102.
// time - O(n) each node is pushed into and removed from queue once
// space - O(n) - if tree is a complete binary tree, the last level will have the largest nodes (n/2 nodes approx) and queue will have all these nodes when 2nd last level is processed
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>(); //return list
        //edge
        if(root == null)
        {
            return result;
        }

        Queue<TreeNode> support = new LinkedList<>(); //to do bfs
        support.offer(root);

        //as long as queue has more nodes
        while(!support.isEmpty())
        {
            int levelSize = support.size(); //number of nodes in queue at start of iteration is number of nodes in current level
            List<Integer> currentLevelNodes = new ArrayList<>(); //to track nodes in current level
            //process all nodes in current level
            for(int i = 0; i < levelSize; i++)
            {
                TreeNode current = support.poll();
                currentLevelNodes.add(current.val); //process current node
                //offer children for processing in next iteration
                if(current.left != null)
                {
                    support.offer(current.left);
                }
                if(current.right != null)
                {
                    support.offer(current.right);
                }
            }

            //add nodes in current level to result list
            result.add(currentLevelNodes);
        }

        return result;
    }
}

// 103.
// time - O(n) - each node is inserted into and removed fro queue once
// space - O(n) - queue size is largest when largest level is processed which is n/2 in worst case in case of a full binary tree
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>(); //return list
        //edge
        if(root == null)
        {
            return result;
        }
        
        Queue<TreeNode> support = new LinkedList<>(); //for bfs
        support.offer(root); 
        boolean flag = true; //if flag is true, level nodes are from left to right else right to left, first level is true

        //as long as queue has more nodes
        while(!support.isEmpty())
        {
            int levelSize = support.size(); //number of nodes in current level is queue size at start of iteration
            Integer[] currentLevelNodes = new Integer[levelSize]; //list to hold nodes in current level
            
            //process all nodes in current level
            for(int i = 0; i < levelSize; i++)
            {
                TreeNode current = support.poll();
                //current node goes to i if flag is true i.e. current level is left to right
                //current goes at levelSize - i - 1 if flag is false i.e current level is right to left
                if(flag)
                {
                    currentLevelNodes[i] = current.val;
                }
                else
                {
                    currentLevelNodes[levelSize - i - 1] = current.val;
                }

                //offer children of current to queue to be processed in next iteration
                if(current.left != null)
                {
                    support.offer(current.left);
                }
                if(current.right != null)
                {
                    support.offer(current.right);
                }
            }
                
            //next level nodes have to processed in opposite direction
            flag = !flag;
            result.add(Arrays.asList(currentLevelNodes)); //add current level nodes to result list
        }

        return result;
    }
}

// 987.
// time - O(nlogn) to build store and O(nlogn) to build result
// space - O(n) - all nodes are stored in store map
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //edge
        if(root == null)
        {
            return new ArrayList<>();
        }

        //key - vertical (int), value - TreeMap where key is level and value is pq of nodes in that level, value map is a TreeMap because levels are to be stored in sorted order
        HashMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> store = new HashMap<>();

        //verticalBounds[0] tracks the left most vertical and 1st index tracks right most vertical
        int[] verticalBounds = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

        buildStore(root, new int[]{0, 0}, store, verticalBounds);

        //build result
        List<List<Integer>> result = new ArrayList<>();
        //for each vertical from left most to right most
        for(int vertical = verticalBounds[0]; vertical <= verticalBounds[1]; vertical++)
        {
            List<Integer> currentVerticalNodes = new ArrayList<>(); //tracks all nodes in current vertical
            //for each level inside vertical
            for(int level : store.get(vertical).keySet())
            {
                //add all nodes in current level to current vertical node list
                while(!store.get(vertical).get(level).isEmpty())
                {
                    currentVerticalNodes.add(store.get(vertical).get(level).poll());
                }
            }

            result.add(currentVerticalNodes); //add current vertical nodes to result list
        }

        return result;
    }

    //params - root, rootInfo is an array of size 2 where 0th index is vertical and 1st index is level, store map and verticalBounds
    //time - O(nlogn) for traversing tree and inserting into store
    //space - o(h) - call stack
    private void buildStore(TreeNode root, int[] rootInfo, HashMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> store, int[] verticalBounds)
    {
        //base
        if(root == null)
        {
            return;
        }

        //process current node
        //update min vertical with current
        verticalBounds[0] = Math.min(verticalBounds[0], rootInfo[0]); 
        //update min vertical with current
        verticalBounds[1] = Math.max(verticalBounds[1], rootInfo[0]); 
        //insert current node into store
        int currentVertical = rootInfo[0];
        int currentLevel = rootInfo[1];
        if(!store.containsKey(currentVertical))
        {
            store.put(currentVertical, new TreeMap<>());
        }
        if(!store.get(currentVertical).containsKey(currentLevel))
        {
            store.get(currentVertical).put(currentLevel, new PriorityQueue<>());
        }
        store.get(currentVertical).get(currentLevel).offer(root.val);

        //explore left subtree
        buildStore(root.left, new int[]{currentVertical - 1, currentLevel + 1}, store, verticalBounds);

        //explore right subtree
        buildStore(root.right, new int[]{currentVertical + 1, currentLevel + 1}, store, verticalBounds);
    }
}

// 545.
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>(); //return list

        //edge
        //empty tree
        if(root == null)
        {
            return result;
        }    

        //edge
        //tree is just the root
        if(isLeaf(root))
        {
            result.add(root.val);
            return result;
        }

        result.add(root.val); //root is not a leaf and won't be covered in left boundary, right boundary and leaf
        
        //left boundar is not there if root's left is null
        if(root.left != null)
        {    
            //build left boundary
            TreeNode rootCopy = root.left;
            traverseLeftBoundary(rootCopy, result); 
        }
        
        //find leaves
        findLeaves(root, result);

        //find right boundary in reverse
        traverseRightBoundaryInReverse(root, result);

        return result;
    }

    //returns true if i/p node is a leaf, time - constant, space - constant
    private boolean isLeaf(TreeNode node)
    {
        if(node != null && node.left == null && node.right == null)
        {
            return true;
        }

        return false;
    }

    //time - O(h) - number of elemnts in left boundary
    //space - constant
    private void traverseLeftBoundary(TreeNode root, List<Integer> result)
    {
        //as long as leaf is not found and current is not null
        while(root != null)
        {
            //leaf found, all left boundary elements are found
            if(isLeaf(root))
            {
                break;
            }
            result.add(root.val); //add root to result and go left if not null else right
            if(root.left != null)
            {
                root = root.left;
            }
            else
            {
                root = root.right;
            }
        }
        return;
    }

    //time - O(n) - preorder traversal
    //space - O(h)
    private void findLeaves(TreeNode root, List<Integer> result)
    {
        //base
        if(root == null)
        {
            return;
        }
        //check if current root is leaf
        if(isLeaf(root))
        {
            result.add(root.val); //add to result
            return;
        }
        findLeaves(root.left, result); //find leaves in left subtree
        findLeaves(root.right, result); //find leaves in right subtree
    }

    //time - O(n)
    //space - O(h)
    private void traverseRightBoundaryInReverse(TreeNode root, List<Integer> result)
    {
        //right boundary in reverse without root
        root = root.right;
        Stack<TreeNode> support = new Stack<>();

        //as long as current root is not null and not leaf
        while(root != null)
        {
            if(isLeaf(root))
            {
                break;
            }
            support.push(root);
            //go right if not null else left
            if(root.right != null)
            {
                root = root.right;
            }
            else
            {
                root = root.left;
            }
        }

        //stack has all nodes in revrse, insert into result
        while(!support.isEmpty())
        {
            TreeNode current = support.pop();
            result.add(current.val);
        }

        return;
    }
}
