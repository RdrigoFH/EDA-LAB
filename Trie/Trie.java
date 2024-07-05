import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.List;
import java.util.Comparator;
public class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String text) {
        // Se crea un nodo auxiliar con una referencia a la raiz
        Node current = root;
        for (int i = 0; i < text.length(); i++) {
            //char que almacena temporamente el valor de la letra i del string
            char ch = text.charAt(i);
            if (current.getSon(ch) == null) {
                current.setSon(new Node(ch));
            }
            // se actualiza la referencia a current a su hijo
            current = current.getSon(ch);
        }
        // se assigna el final de una palabra cuando ya se han insertado todas las letras
        current.setIsEnd(true);
        // se aumenta la frecuencia
        current.increaseFrequency();
    }

    public boolean search(String text) {
        // Creamos un auxiliar current
        Node current = root;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            // si ya no hay hijos y se acabo la palabra retornamos falso
            if (current.getSon(ch) == null) {
                return false;
            }
            //acutalizamos la referencia a current
            current = current.getSon(ch);
        }
        return current.isEnd();
    }

    public void replace(String oldText, String newText) {
        if (search(oldText)) {
            delete(oldText);
            insert(newText);
        }
    }

    private void delete(String text) {
        delete(root, text, 0);
    }

    private boolean delete(Node current, String text, int index) {
        // commprueba si es el final de la palabra
        if (index == text.length()) {
            // si no es el final de una palabra significa que el nodo no existe
            if (!current.isEnd()) {
                return false;
            }
            // marca al nodo current como una letra intermedia y no el final
            current.setIsEnd(false);
            // verifica (boolean) que el nodo actual sea hoja(sin hijos) y puede ser eliminado
            return current.getChildCount() == 0;
        }
    
        // OOptiene el caracter de la posicion actual de la palabra
        char ch = text.charAt(index);
        // objeto node que representa el hijo actual 
        Node node = current.getSon(ch);
    
        // si el hijo es nulo no existe 
        if (node == null) {
            return false;
        }
    
        boolean shouldDeleteCurrentNode = delete(node, text, index + 1) && !node.isEnd();
    
        // Si el nodo hijo debe ser eliminado (no forma parte de otra palabra)
        if (shouldDeleteCurrentNode) {
            current.getSons().remove(ch);
            // si el nodo actual no tiene hijos puede ser eliminado
            return current.getChildCount() == 0;
        }
    
        // retorna falso si el nodo actual no puede ser eliminado
        return false;
    }
    

    public List<String> topKFrequentWords(int k) {
        // crear una cola de prioridad para las entradas de mapa 
        // y las compara por su valor
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            Comparator.comparingInt(Map.Entry::getValue)
        );
        // crear un mapa para contar las palabras
        Map<String, Integer> wordCount = new HashMap<>();
        // recolectar palabras y sus frecuencias desde la raiz
        collectWords(root, "", wordCount);
    
        // agregar entradas del mapa a la cola de prioridad
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            pq.offer(entry);
            // mantener solo los k elementos mas frecuentes en la cola de prioridad
            if (pq.size() > k) {
                pq.poll();
            }
        }
    
        // crear una lista para almacenar el resultado
        List<String> result = new ArrayList<>();
        // agregar palabras de la cola de prioridad a la lista de resultados
        while (!pq.isEmpty()) {
            result.add(pq.poll().getKey());
        }
    
        // devolver la lista de palabras más frecuentes
        return result;
    }
    

    private void collectWords(Node node, String word, Map<String, Integer> wordCount) {
        if (node == null) {
            return;
        }
        if (node.isEnd()) {
            wordCount.put(word, node.getFrequency());
        }
        for (Map.Entry<Character, Node> entry : node.getSons().entrySet()) {
            collectWords(entry.getValue(), word + entry.getKey(), wordCount);
        }
    }

    public void print() {
        System.out.println("Palabras guardadas:\n");
        print(root, "");
    }

    private void print(Node node, String preText) {
        if (node != null) {
            if (node.getData() != '\0') {
                preText += node.getData();
            }

            if (node.isEnd()) {
                System.out.println(preText + " (frecuencia: " + node.getFrequency() + ")");
            }

            for (Node child : node.getSons().values()) {
                print(child, preText);
            }
        }
    }
}

