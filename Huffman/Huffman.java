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
   
   public HashMap<Character, Integer> buildMap(String str){
      HashMap<Character, Integer> map = new HashMap<>();
      for(char ch : str.toCharArray()){
         map.compute(ch, (key,value) -> (value== null)? 1 : value + 1);
      }
      return map;
   }  

   public HashMap<Character, String> generateCodes(Node huffmanTree){
      HashMap<Character, String> codeMap = new HashMap<>();
      generateCodes(huffmanTree,"",codeMap);
      return codeMap;
   }
   public void generateCodes(Node node, String code, Map<Character, String> huffmanCode) {
      if (node == null) {
          return;
      }

      if (node.left == null && node.right == null) {
          huffmanCode.put(node.c, code);
      }

      generateCodes(node.left, code + '0', huffmanCode);
      generateCodes(node.right, code + '1', huffmanCode);
   }
   public HashMap compres(String str){
      HashMap map = buildMap(str); 
      Node node = huffmanTree(map);
      return generateCodes(node);
   }
   public static void main(String[] args) {
      String str = "abecedario, cirstiano ronaldo, lionel messi, bobo, colombia a ganar la copa america ";
      Huffman h = new Huffman();
      System.out.println("los caracteres comprimidos son: \n" + h.compres(str));
   }
}