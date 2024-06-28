public class SkipList<T extends Comparable<? super T>> {
   // Limite, lo maximo en cuanto a niveles que puede tener nuestra lista
   private static final int MAX_LEVEL = 7;
   private final Node<T> head;
   private int currentLevel;

   public SkipList() {
       this.currentLevel = 0;
       // Iniciamos con la cabeza, esta tendra la altura maxima pues desde
       // aqui se inicaran los saltos a otros nodos del mismo nivel para la busqueda
       this.head = new Node<>(MAX_LEVEL, null);
   }

   private int generateRandomLevel() {
       int level = 0;
       while (level < MAX_LEVEL && Math.random() < 0.5) {
           level++;
       }
       return level;
   }

   public void insert(T data) {
       // update es una estructura auxiliar que sirve para ir guardando los nodo
       // de cada nivel que necesitarian actualizar su referencia al siguiente nodo
       Node<T> update[] = new Node[MAX_LEVEL + 1];
       Node<T> current = head;

       // Avanzamos por las referencias al siguiente nodo para encontrar
       // La posicion adecuada para la insersion
       for (int i = currentLevel; i >= 0; i--) {
           while (current.next[i] != null && current.next[i].data.compareTo(data) < 0) {
               current = current.next[i];
           }
           // se van almacenando los nodos de cada nivel que requeririan 
           // actualizacion de referencias
           update[i] = current;
       }

       current = current.next[0];
       // se verifica que el elmento no exista
       if (current == null || !current.data.equals(data)) {
           int newLevel = generateRandomLevel();
           if (newLevel > currentLevel) {
               for (int i = currentLevel + 1; i <= newLevel; i++) {
                   update[i] = head;
               }
               currentLevel = newLevel;
           }
           // insertamos el nuevo nodo
           Node<T> newNode = new Node<>(newLevel, data);
           for (int i = 0; i <= newLevel; i++) {
               newNode.next[i] = update[i].next[i];
               update[i].next[i] = newNode;
           }
       }
   }

   public boolean search(T data) {
        Node<T> current = head;
        for (int i = currentLevel; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].data.compareTo(data) < 0) {
                current = current.next[i];
            }
        }
        current = current.next[0];
        return current != null && current.data.equals(data);
    }
}