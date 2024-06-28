public class Test {
   public static void main(String[] args) {
      SkipList<Integer> list = new SkipList<>();
        list.insert(49);
        list.insert(50);
        list.insert(25);
        list.insert(7);
        list.insert(14);
        list.insert(11);
        list.insert(89);
        list.insert(55);
        list.insert(24);
        list.insert(27);

        System.out.println("Buscando elemento 14");
        System.out.println(list.search(14));
        System.out.println("Buscando elemento 55");
        System.out.println(list.search(55));
        System.out.println("Buscando elemento 99");
        System.out.println(list.search(99));


   }
}
