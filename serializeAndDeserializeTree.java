//297.
public class Codec {

    Queue<String> serializedTree = new LinkedList<>();

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder serializedString = new StringBuilder(); //tracks the str version of tree
        serializeHelperIterative(root, serializedString);

        serializedString.deleteCharAt(serializedString.length() - 1); //last delimiter is not needed

        return serializedString.toString();
    }

    //time - O(n) - preorder traversal
    //space - O(h)
    private void serializeHelper(TreeNode root, StringBuilder serializedString)
    {
        //base
        if(root == null)
        {
            serializedString.append("#,"); //append special char for null node
            return;
        }

        //process current node - add to serialized string with a delimiter
        serializedString.append(root.val + ",");

        //explore left and right subtrees
        serializeHelper(root.left, serializedString);
        serializeHelper(root.right, serializedString);
    }

    //time - O(n) - bfs
    //space - O(n)
    private void serializeHelperIterative(TreeNode root, StringBuilder serializedString)
    {
        Queue<TreeNode> support = new LinkedList<>(); //for bfs
        support.offer(root);

        while(!support.isEmpty())
        {
            int levelSize = support.size();
            for(int i = 0; i < levelSize; i++)
            {
                TreeNode current = support.poll();

                if(current == null)
                {
                    serializedString.append("#,"); //append special char for null node
                    continue;
                }

                serializedString.append(current.val + ",");

                //children should be processed even if null to maintain structure
                support.offer(current.left);
                support.offer(current.right);
            }
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //populate queue for deserialization
        serializedTree = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelperIterative();
    }

    //time - O(n) - preorder traversal
    //space - O(h)
    private TreeNode deserializeHelper()
    {
        String currentNode = serializedTree.poll(); //get first node from queue
        //base
        if(currentNode.equals("#"))
        {
            return null;
        }

        //root is the 1st element in queue
        TreeNode root = new TreeNode(Integer.parseInt(currentNode));

        //build left sub tree
        root.left = deserializeHelper();
        
        //build left sub tree
        root.right = deserializeHelper();

        return root;
    }

    //time - O(n) - bfs
    //space - O(n)
    private TreeNode deserializeHelperIterative()
    {
        String firstNode = serializedTree.poll(); //get the first node
        if(firstNode.equals("#"))
        {
            return null; //empty tree
        }
        
        TreeNode root = new TreeNode(Integer.parseInt(firstNode)); //create root
        Queue<TreeNode> support = new LinkedList<>(); //for bfs
        support.offer(root);

        while(!support.isEmpty())
        {
            int levelSize = support.size();
            for(int i = 0; i < levelSize; i++)
            {
                TreeNode current = support.poll();

                String leftNode = serializedTree.poll();
                String rightNode = serializedTree.poll();

                if(!leftNode.equals("#"))
                {
                    //valid left node
                    current.left = new TreeNode(Integer.parseInt(leftNode));
                    support.offer(current.left); //children of current.left will be added in mext iteration
                }

                if(!rightNode.equals("#"))
                {
                    //valid left node
                    current.right = new TreeNode(Integer.parseInt(rightNode));
                    support.offer(current.right); //children of current.right will be added in mext iteration
                }
            }
        }

        return root;
    }
}
