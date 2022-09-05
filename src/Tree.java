import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
            String end = element.substring(0, 1);
            if(end != "/"){
                node.child.add(new Node(element));
            }else{

            }
        }

        return node;
    }

}
