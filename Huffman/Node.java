public class Node implements Comparable<Node>{
   private int frequecy;
   private char c;
   private Node right;
   private Node left;

   public int compareTo(Node node){
      return this.frequecy - node.frequecy;
   }
   public Node(int frequency, char c){
      this.frequecy = frequency; this.c = c;
   }
}