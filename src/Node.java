import java.util.ArrayList;

public class Node {
    public ArrayList<Node> child;
    public String element;

    public int x, y;

    Node(String element){
        child = new ArrayList<>();
        this.element = element;
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public int getChildSize() {
        return child.size();
    }
}
