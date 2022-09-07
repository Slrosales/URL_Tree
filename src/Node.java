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


}
