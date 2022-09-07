import java.util.Arrays;

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

    static void print(Node node, boolean[] depthB, int depth, boolean isLast )
    {
        if (node == null)
            return;

        for (int i = 1; i < depth; ++i) {
            
            if (depthB[i] == true) {
                System.out.print("│" + " " + " " + " ");
            } else {
                System.out.print(" " + " " + " " + " ");
            }
        }

        if (depth == 0)
            System.out.println(node.element);

        else if (isLast) {
            System.out.print("└── " +  node.element + '\n');

            depthB[depth] = false;
        }
        else {
            System.out.print("└── " +  node.element + '\n');
        }

        int it = 0;
        for (Node i : node.child) {
            print(i, depthB, depth + 1, it == (node.child.size()) - 1);
        }
        depthB[depth] = true;
    }

    static void print(Node node){
        int nv = 1000;

        boolean[] depthB = new boolean[nv];
        Arrays.fill(depthB, true);

        // Tree Formation

        print(node, depthB, 0, false);
    }

}

