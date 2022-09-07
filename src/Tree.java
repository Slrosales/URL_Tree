public class Tree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(String element, String father){
        root = insert(root, element, father);
    }

    private Node insert(Node node, String element, String father ){
        if (node == null){
            node = new Node(element);
        } else {
            Node current = search(father);
            if (current != null){
                current.child.add(new Node(element));
            }else{
                node.child.add(new Node(element));
            }
        }
        return node;
    }

    Node resultado = null;
    private Node search(Node node, String element){
        if (node==null)
            resultado = null;

        if (element == node.element)
            resultado = node;

        for (Node current : node.child)
            search(current, element);

        return resultado;
    }

    public Node search(String element) {
        return(search(root, element));
    }

    public int Height(Node root){

        int max = 0;
        for (Node childNode  : root.child) {
            int height = Height(childNode);
            if (height > max)
                max = height;
        }
        return max + 1;
    }

}
