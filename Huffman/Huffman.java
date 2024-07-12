import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

public class Huffman{
   PriorityQueue<Node> queue = new PriorityQueue<>(); 

   public Node huffmanTree(HashMap<Character, Integer> map){
   // creamos los nodos para cada parte del mapa
      for(Map.Entry<Character, Integer> entry : map.entrySet())
         queue.add(new Node(entry.getValue(), entry.getKey()));

      // solo puede haber un nodo raiz, el resto se convertiran en hijos
      while(queue.size() > 1){
         Node left = queue.poll();
         Node right = queue.poll();
         Node fusion = new Node(left.frequecy+right.frequecy, '\0', left, right);
         queue.add(fusion);
      }
      return queue.poll();
   }


   public static void main(String[] args) {

   }
}