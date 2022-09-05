import java.util.ArrayList;

public class Node {
    public ArrayList<Node> child;
    private String element;

    Node(String element){
        child = new ArrayList<>();
        this.element = element;
    }

    public ArrayList<Node> getChild() {
        return child;
    }

    public void setChild(ArrayList<Node> child) {
        this.child = child;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
}
