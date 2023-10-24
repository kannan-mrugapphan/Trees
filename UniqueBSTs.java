// 96.
class Solution {
    public int numTrees(int n) {
        //if n = 0, then # of bsts = 1 as an empty tree is a bst
        //if n = 1, then # of bsts = 1, just the root node
        //if n = 2, then 1 can be the root and 2 is right child or 2 is root and 1 is left child, total = 2
        //if n = 3, then
        /*
            1 is root with no nodes in left and 2 nodes in right
            2 is root with 1 node in left and 1 in right
            3 is root with 2 nodes in left and 1 in right

            total = sum of 3 options
        */

        //return countTrees(n, new HashMap<>());
        return countTreesIterative(n);
    }

    //returns number of bsts with n keys from 1 to n
    private int countTrees(int n, HashMap<Integer, Integer> cache)
    {
        //base
        if(n == 0 || n == 1)
        {
            return 1;
        }

        //check cache
        if(cache.containsKey(n))
        {
            return cache.get(n);
        }

        int result = 0; //return value
        //each node from 1 to n can be the root
        for(int root = 1; root <= n; root++)
        {
            //for current root, number of nodes in left = [1, root - 1]
            //number of nodes in right = [root + 1, root - n]
            int leftSubTrees = countTrees(root - 1, cache);
            int rightSubTrees = countTrees(n - root, cache);

            //number of trees with current root = product of 2 
            int treesWithCurrentRoot = leftSubTrees * rightSubTrees;
            result += treesWithCurrentRoot;
        }

        cache.put(n, result); //update cache
        return result;
    }

    private int countTreesIterative(int n)
    {
        int[] result = new int[n + 1];
        result[0] = 1; //base
        result[1] = 1; //base

        for(int nodes = 2; nodes <= n; nodes++)
        {
            //each node from 1 to nodes can be the root
            for(int root = 1; root <= nodes; root++)
            {
                //for current root, number of nodes in left = [1, root - 1]
                //number of nodes in right = [root + 1, root - n]
                int leftSubTrees = result[root - 1];
                int rightSubTrees = result[nodes - root];

                //number of trees with current root = product of 2 
                int treesWithCurrentRoot = leftSubTrees * rightSubTrees;
                result[nodes] += treesWithCurrentRoot; 
            }
        }

        return result[n];
    }
}


class Solution {
    public List<TreeNode> generateTrees(int n) {
        return buildTrees(1, n, new HashMap<>());
    }

    private List<TreeNode> buildTrees(int lowestKey, int highestKey, HashMap<String, List<TreeNode>> cache)
    {
        List<TreeNode> result = new ArrayList<>(); //return list
        //base
        if(lowestKey > highestKey)
        {
            //no trees can be formed
            result.add(null);
            return result;
        }

        //base
        if(lowestKey == highestKey)
        {
            // tree just has 1 node
            result.add(new TreeNode(lowestKey));
            return result;
        }

        //check cache
        if(cache.containsKey(lowestKey + " - " + highestKey))
        {
            return cache.get(lowestKey + " - " + highestKey);
        }

        //all nodes from 1 to n can be the root
        for(int root = lowestKey; root <= highestKey; root++)
        {
            List<TreeNode> leftSubtrees = buildTrees(lowestKey, root - 1, cache); //number of nodes in left is root - 1
            List<TreeNode> rightSubtrees = buildTrees(root + 1, highestKey, cache); //number of nodes in right is n - root

            for(TreeNode leftSubtree : leftSubtrees)
            {
                for(TreeNode rightSubtree : rightSubtrees)
                {
                    TreeNode rootNode = new TreeNode(root);
                    rootNode.left = leftSubtree;
                    rootNode.right = rightSubtree;
                    result.add(rootNode);
                }
            }
        }

        cache.put(lowestKey + " - " + highestKey, result); //update cache
        return result;
    }
}

// 894.
class Solution {
    public List<TreeNode> allPossibleFBT(int n) {
        return buildTrees(n, new HashMap<>());
    }

    private List<TreeNode> buildTrees(int n, HashMap<Integer, List<TreeNode>> cache)
    {
        List<TreeNode> result = new ArrayList<>(); //return list
        //base
        if(n % 2 == 0)
        {
            //if total number of nodes in n, then we can't form trees with all nodes having 0 or 2 children
            return result;
        }

        //base
        if(n == 1)
        {
            result.add(new TreeNode());
            return result; //only 1 tree possible
        }

        //check cache
        if(cache.containsKey(n))
        {
            return cache.get(n);
        }

        //one node is for root
        //left sub tree can have 0 nodes or 1 nodes or 2 nodes or so on upto n-1 nodes (because 1 nodes is for root)
        //right sub tree will have the remaining nodes (n - left - 1)
        for(int leftSize = 0; leftSize < n; leftSize++)
        {
            int rightSize = n - leftSize - 1;

            List<TreeNode> leftSubtrees = buildTrees(leftSize, cache);
            List<TreeNode> rightSubtrees = buildTrees(rightSize, cache);

            for(TreeNode leftSubtree : leftSubtrees)
            {
                for(TreeNode rightSubtree : rightSubtrees)
                {
                    TreeNode root = new TreeNode();
                    root.left = leftSubtree;
                    root.right = rightSubtree;
                    result.add(root);
                }
            }
        }

        cache.put(n, result); //update cache
        return result;
    }
}
