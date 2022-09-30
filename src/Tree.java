import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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

        int max = 1;
        for (Node childNode  : root.child) {
            int height = Height(childNode);
            if (height > max)
                max = height;
        }
        return max + 1;
    }

    public static void print(Node node, boolean[] depthB, int depth, boolean isLast ){
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

    public static void print(Node node){
        int nv = 1000;

        boolean[] depthB = new boolean[nv];
        Arrays.fill(depthB, true);

        // Tree Formation

        print(node, depthB, 0, false);
    }

    public static int maxLevelNodes(Node node, int maxCont) {
        if (node == null)
            return maxCont;

        Queue<Node > q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty())
        {
            int n = q.size();

            while (n > 0)
            {
                int cont = 0;

                Node p = q.peek();
                q.remove();

                for (int i = 0; i < p.child.size(); i++){
                    q.add(p.child.get(i));
                    cont = cont + 1;
                }

                if (maxCont < cont){
                    maxCont = cont;
                }

                n--;
            }
        }
        return maxCont;
    }

    public static void coordinates(Node node, int width, int xy) {
        if (node != null) {
            node.setX(width / 2);
            node.setY(xy);

            Queue<Node> Q = new LinkedList<>();
            Q.add(node);
            int div = xy;

            while (!Q.isEmpty()) {
                int size = Q.size();
                int plus = 0;
                div = xy + div;

                while (size > 0) {

                    Node current = Q.peek();
                    Q.remove();

                    for (int i = 0; i < current.child.size(); i++) {
                        plus = (width / (current.child.size() + 1)) + plus;
                        Q.add(current.child.get(i));
                        current.child.get(i).setX(plus);
                        current.child.get(i).setY(div);
                    }
                    size--;

                }
            }
        } else {
            return;
        }
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.println("Nombre: " + node.element + " coordenadas: " + node.getX() +"," + node.getY());
            for (Node current : node.child) {
                preOrder(current);
            }
        }
    }

    void preOrder() {
        preOrder(getRoot());
    }
}

