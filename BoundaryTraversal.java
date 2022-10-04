//time - O(n) -> all nodes must be visited
//space - O(h) -> call stack for leaves and right boundary traversal
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>(); //return list
        //edge
        if(root == null)
        {
            return result;
        }
        
        traverseLeftBoundary(root, result); //insert elements across left boundary
        insertLeaves(root, result); //insert leaves
        traverseRightBoundary(root, result); //insert elements across right boundary in reverse
        
        return result;
    }
    
    //const time
    private boolean isLeaf(TreeNode node) {
        if(node.left == null && node.right == null)
        {
            return true;
        }
        return false;
    }
    
    //O(h) time
    private void traverseLeftBoundary(TreeNode root, List<Integer> result) {
        //edge
        if(root.left == null)
        {
            if(!isLeaf(root))
            {
                result.add(root.val);
            }
            return;
        }
        
        //keep adding root until left becomes null, if left becomes null go right, skip and break if leaf is reached
        while(root != null)
        {
            if(isLeaf(root))
            {
                break;
            }
            result.add(root.val);
            if(root.left == null)
            {
                root = root.right;
            }
            else
            {
                root = root.left;
            }
        }
        
        return;
    }
    
    //O(n) time -> normal preorder traversal, add node to result if it is a leaf
    private void insertLeaves(TreeNode root, List<Integer> result) {
        if(root == null)
        {
            return;
        }
        if(isLeaf(root))
        {
            result.add(root.val);
            return;
        }
        insertLeaves(root.left, result);
        insertLeaves(root.right, result);
        return;
    }
    
    //time - O(h)
    //space - O(h)
    //keep adding root to temp stack until root.right becomes null. if root.right becomes null go to left
    //if leaf is reached skip and break
    //temp stack is needed because nodes across right boundary must be added in reverse
    private void traverseRightBoundary(TreeNode root, List<Integer> result) {
        if(isLeaf(root) || root.right == null)
        {
            return;
        }
        
        Stack<Integer> tempRightNodes = new Stack<>();
        root = root.right;
        
        while(root != null)
        {
            if(isLeaf(root))
            {
                break;
            }
            tempRightNodes.push(root.val);
            if(root.right != null)
            {
                root = root.right;
            }
            else
            {
                root = root.left;
            }
        }
        
        while(!tempRightNodes.isEmpty())
        {
            result.add(tempRightNodes.pop());
        }
        return;
    }
}
