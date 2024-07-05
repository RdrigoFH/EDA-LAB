import java.util.HashMap;

class Node {
    private char data;
    private HashMap<Character, Node> sonNodes;
    private boolean isEnd;
    private int frequency;

    public Node() {
        sonNodes = new HashMap<>();
        isEnd = false;
        frequency = 0;
    }

    public Node(char data) {
        this.data = data;
        sonNodes = new HashMap<>();
        isEnd = false;
        frequency = 0;
    }

    public void setData(char data) {this.data = data;}

    public char getData() {return data;}

    public void setSon(Node node) {sonNodes.put(node.getData(), node);}

    public Node getSon(char ch) {return sonNodes.get(ch);}

    public int getChildCount() {return sonNodes.size();}

    public HashMap<Character, Node> getSons() {return sonNodes;}

    public void setIsEnd(boolean isEndOfWord) {this.isEnd = isEndOfWord;}

    public boolean isEnd() {return isEnd;}

    public void increaseFrequency() {this.frequency++;}

    public int getFrequency() {return frequency;}
}