// 987.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//time - O(n)
//space - constant as hashmap is part of result
class Solution {
    HashMap<Integer, List<Integer>> result;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        result = new HashMap<>();
        buildVerticalTraversal(root, result, 0); //verticalDistance of root = 0
        //leetcode is expecting lists based on increasing order of vertical distance
        //so add map into treemap to make keys sorted and return treemap
        //treemap has O(log n) for add but ignoring time and space for treemap
        TreeMap<Integer, List<Integer>> answer = new TreeMap<>();
        answer.putAll(result);
        return new ArrayList<List<Integer>> (answer.values());
    }
    
    private void buildVerticalTraversal(TreeNode root, HashMap<Integer, List<Integer>> result, int verticalDistance) {
        //base
        if(root == null)
        {
            return;
        }
        //logic(preorder)
        //update the hashmap with the verticalDistance, root pair
        if(!result.containsKey(verticalDistance))
        {
            List<Integer> temp = new ArrayList<>();
            temp.add(root.val);
            result.put(verticalDistance, temp);
        }
        else
        {
            result.get(verticalDistance).add(root.val);    
        }
        
        //recurse
        //verticalDistance of left child = verticalDistance of parent - 1
        buildVerticalTraversal(root.left, result, verticalDistance - 1);
        
        //verticalDistance of right child = verticalDistance of parent + 1
        buildVerticalTraversal(root.right, result, verticalDistance + 1);
    }
}
