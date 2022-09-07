import java.util.ArrayList;

public class Node {
    public ArrayList<Node> child;
    public String element;

    Node(String element){
        child = new ArrayList<>();
        this.element = element;
    }

}
