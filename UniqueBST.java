// 96.
// time - O(n)
// space - O(n)
class Solution {
    public int numTrees(int n) {
        // n = 0 -> number of possible bst = 1 i.e just null
        // n = 1 -> # of possible bst = 1 i.e. just the key
        // n = 2 -> eg - (1,2) -> 1 at root with 2 as right child or 2 at root with 1 as left child - 2 unique trees
        // n = 3 -> eg -> (1,2,3) -> 1 at root with 2 keys in right sub tree (2) + 2 at root and 1 key in left subtree and 1 key in right sub tree (1) 
        //                           + 3 at root with 2 keys in left sub tree (2) -> 5 unique tree
        
        int[] result = new int[n + 1];
        //base
        result[0] = 1;
        result[1] = 1;
        for(int i = 2; i <= n; i++)
        {
            for(int j = 1; j <= i; j++)
            {
                //considering the jth key as root, j - 1 keys will be on the left subtree and remaining (i - j) will be on the right subtree
                //total trees with jth key as root = result[j - 1] (# of trees with j - 1 keys) * result[i - j] (# of trees with i - j keys) - add all js from 1 to i
                result[i] += result[j - 1] * result[i - j];
            }
        }
        
        return result[n];
    }
}
