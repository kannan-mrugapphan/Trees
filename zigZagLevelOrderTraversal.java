// 103.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//time - O(n) visits every node once
//space - O(size of 2 stacks) - size of stack is largest in the lastlevel when tree is perfect
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //edge
        if(root == null)
        {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        
        //have 2 stacks
        //push root into stack 1
        //as long as either of the stacks is not empty,
        //pop from stack 1, process it, push its left and right children (if exisits) into stack2 till stack 1 is empty
        //pop from stack 2, process it, push its right and left children (if exisits) into stack1 till stack 2 is empty
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        
        while(!stack1.isEmpty() || !stack2.isEmpty())
        {
            List<Integer> current = new ArrayList<>();
            while(!stack1.isEmpty())
            {
                TreeNode top = stack1.pop();
                current.add(top.val);
                if(top.left != null)
                {
                    stack2.push(top.left);
                }
                if(top.right != null)
                {
                    stack2.push(top.right);
                }
            }
            if(current.size() > 0)
            {
               result.add(current); 
            }
            
            current = new ArrayList<>();
            while(!stack2.isEmpty())
            {
                TreeNode top = stack2.pop();
                current.add(top.val);
                if(top.right != null)
                {
                    stack1.push(top.right);
                }
                if(top.left != null)
                {
                    stack1.push(top.left);
                }
            }
            if(current.size() > 0)
            {
               result.add(current); 
            }
        }
        return result;
    }
}
