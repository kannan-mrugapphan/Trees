// 116.
class Solution {
    public Node connect(Node root) {
        //edge
        if(root == null)
        {
            return root;
        }
        recursive(root);
        return root;
    }
    
    //go level by level and build next links
    //time - O(n)
    //space - O(n)
    private void levelOrderTrversal(Node root) {
        Queue<Node> support = new LinkedList<>(); //for bfs
        support.offer(root);
        
        while(!support.isEmpty())
        {
            int size = support.size();
            Node prev = support.poll(); //1st node in current level
            //add children of prev into queue
            if(prev.left != null)
            {
                support.offer(prev.left);
            }
            if(prev.right != null)
            {
                support.offer(prev.right);
            }
            for(int i = 1; i < size; i++)
            {
                Node current = support.poll(); //build the next link between current and prev
                prev.next = current;
                //add children (if present) into the queue to be processed in the next level
                if(current.left != null)
                {
                    support.offer(current.left);
                }
                if(current.right != null)
                {
                    support.offer(current.right);
                }
                prev = current; //make the current as prev
            }
        }
        
        return;
    }
    
    //time - O(n)
    //space - constant
    private void iterativeConstantSpace(Node root) {
        Node temp = root; //start from the 1st level
        //go till the second last leevl
        while(temp.left != null)
        {
            Node current = temp; //start from left most node in current level
            //as long as there are more nodes in current level
            while(current != null)
            {
                //if the current has a left child set the next link of current's left to current's right
                if(current.left != null)
                {
                    current.left.next = current.right;
                }
                //if the current has next link then set next link of current's right to left of next
                if(current.next != null)
                {
                    current.right.next = current.next.left;
                }
                //make current to current's next to go to next node in current level
                current = current.next;
            }
            temp = temp.left; //go to next level
        }
        return;
    }
    
    //time - O(n)
    //space - O(h) - max call stack size
    private void recursive(Node root) {
        //base
        if(root == null)
        {
            return;
        }
        //logic
        if(root.left != null)
        {
            root.left.next = root.right;
        }
        if(root.right != null && root.next != null)
        {
            root.right.next = root.next.left;
        }
        recursive(root.left);
        recursive(root.right);
    }
}
