    //inorder

    //time - O(n)
    //space - O(h) - max call stack size = height of tree
    private void dfs(TreeNode root, List<Integer> result) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        dfs(root.left, result); //recurse on left substree
        result.add(root.val); //process the current node
        dfs(root.right, result); //recurse on right subtree
    }
    
    //time - O(n)
    //space - O(n)
    private void bfs(TreeNode root, List<Integer> result) {
        Stack<TreeNode> support = new Stack<>(); //mimics call stack
        //as long as the root is not null or stack has more elements
        while(root != null || !support.isEmpty()) 
        {
            while(root != null)
            {
                support.push(root); //push current root into stack and move to its left child
                root = root.left; 
            }
            //the inner while loop is same as dfs(root.left)
            root = support.pop(); //base case is hit 
            result.add(root.val); //process current
            root = root.right; //recurse on right
        }
        return;
    }
    
    //preorder
    //time - O(n)
    //space - O(n)
    private void bfs(TreeNode root, List<Integer> result) {
        Stack<TreeNode> support = new Stack<>(); //mimics the call stack
        support.push(root); //initially push the root into stack
        
        while(!support.isEmpty())
        {
            //pop from stack and process the node
            TreeNode current = support.pop();
            result.add(current.val);
            //add the children of current (if present) into the stack
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
    
    //time - O(n)
    //space - O(h) - max call stack size = height of tree
    private void dfs(TreeNode root, List<Integer> result) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        result.add(root.val); //process the current
        dfs(root.left, result); //recurse on the left subtree
        dfs(root.right, result); //recurse on the right sub tree
        return;
    }
    
    //postorder
    //time - O(n)
    //space - O(h) - max call stack size = height of tree
    private void dfs(TreeNode root, List<Integer> result) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        dfs(root.left, result); //recurse on the left subtree
        dfs(root.right, result); //recurse on the right sub tree
        result.add(root.val); //process the current
        return;
    }
    
    //time - O(n)
    //space - O(n)
    private void bfs(TreeNode root, List<Integer> result) {
        Stack<TreeNode> support1 = new Stack<>(); //mimics call stack
        Stack<TreeNode> support2 = new Stack<>(); //result
        support1.push(root); //initially push the root
        
        while(!support1.isEmpty())
        {
            TreeNode current = support1.pop(); //pop the stack top
            //add its children (if present) into stack1
            if(current.left != null)
            {
                support1.push(current.left);
            }
            if(current.right != null)
            {
                support1.push(current.right);
            }
            //push current into stack 2
            support2.push(current);
        }
        
        //return stack2 in reverse order
        while(!support2.isEmpty())
        {
            result.add(support2.pop().val);
        }
        
        return;
    }
