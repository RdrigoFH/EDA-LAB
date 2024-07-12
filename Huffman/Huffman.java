import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

public class Huffman{
   PriorityQueue<Node> queue = new PriorityQueue<>(); 

   public void huffmanTree(HashMap<Character, Integer> map){
   // creamos los nodos para cada parte del mapa
      for(Map.Entry<Character, Integer> entry : map.entrySet())
         queue.add(new Node(entry.getValue(), entry.getKey()));

      // solo puede haber un nodo raiz, el resto se convertiran en hijos
      while(queue.size() > 1){
         
      }
   }


   public static void main(String[] args) {

   }
}