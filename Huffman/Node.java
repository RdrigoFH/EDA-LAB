public class Node implements Comparable<Node>{
   protected int frequecy;
   protected char c;
   protected Node right;
   protected Node left;

   public int compareTo(Node node){
      return this.frequecy - node.frequecy;
   }
   public Node(int frequency, char c){
      this.frequecy = frequency; this.c = c;
   }
   public Node(int frequency, char c, Node left, Node right){
      this.frequecy = frequency; this.c = c;
      this.right = right;
      this.left = left;
   }
}