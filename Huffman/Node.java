public class Node implements Comparable<Node>{
   int frecuecy;
   char c;
   Node right;
   Node left;

   public int compareTo(Node node){
      return this.frecuecy - node.frecuecy;
   }
}