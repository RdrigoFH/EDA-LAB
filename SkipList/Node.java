class Node<T> {
   T data;
   Node<T>[] next;

   public Node(int level, T data) {
       next = new Node[level + 1];
       this.data = data;
   }
}