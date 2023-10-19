// 437.
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        return countPathsOptimal(root, targetSum, 0, new HashMap<>());
    }

    //counts the number of paths starting from a node and ends at a node with sum = target
    //code to count number of paths with sum = target from root to any node
    //time - O(n) - preorder traversal
    //space - O(h)
    private int countPaths(TreeNode node, int targetSum, long currentSum)
    {
        //base
        if(node == null)
        {
            return 0; //no paths found
        }

        //process node
        int count = 0; //return value
        if(currentSum + node.val == targetSum)
        {
            //found one valid path from start to current
            count += 1;
        }

        //continue processing children as val can be -ve and target could be reached again
        int left = countPaths(node.left, targetSum, currentSum + node.val); //current sum update
        int right = countPaths(node.right, targetSum, currentSum + node.val); //current sum update
    
        count += left + right;
        return count;
    }

    //counts paths with sum from any node to any other node
    //time - O(n^2)
    //space - O(h^2)
    private int countAllPaths(TreeNode root, int targetSum)
    {
        //base
        if(root == null)
        {
            return 0; //no paths found
        }

        //process node
        int count = 0; //return value
        //get count of paths starting from current root
        count += countPaths(root, targetSum, 0);

        //get count of paths starting from children
        count += countAllPaths(root.left, targetSum);
        count += countAllPaths(root.right, targetSum);
        return count;
    }

    //time - O(n) - preorder traversal
    //space - O(n) for map and O(h) for call stack
    private int countPathsOptimal(TreeNode root, int targetSum, long currentSum ,HashMap<Long, Integer> countMap)
    {
        //base
        if(root == null)
        {
            return 0; //no paths found
        }

        //process current node
        int count = 0; //return value
        //check if current becomes target at current node
        if(currentSum + root.val == targetSum)
        {
            count += 1; //to account for current path
        }

        //this sum has to be subtracted from root to current node sum to see if any middle path is possible
        //eg: 10->5->3 : current sum = 18
        //if target sum is 8, then the path is 5->3 which can be reached by ignoring 10 from current path
        //expectedSum = 10, check is this expected sum was already seen
        long expectedSum = currentSum + root.val - targetSum; 
        int countOfPathsToBeRemovedToReachTarget = countMap.getOrDefault(expectedSum, 0);
        count += countOfPathsToBeRemovedToReachTarget;

        //add current sum seen in current node
        countMap.put(currentSum + root.val, countMap.getOrDefault(currentSum + root.val, 0) + 1);

        //explore children
        int left = countPathsOptimal(root.left, targetSum, currentSum + root.val, countMap);
        int right = countPathsOptimal(root.right, targetSum, currentSum + root.val, countMap);

        //the parent caller path doesn't include the current node, so reduce freq of current sum by 1 to account for current root not being considered anymore
        countMap.put(currentSum + root.val, countMap.get(currentSum + root.val) - 1);
        if(countMap.get(currentSum + root.val) == 0)
        {
            countMap.remove(currentSum + root.val);
        }

        count += left + right;
        return count;
    }
}
