public class Prueba {
    public static void main(String[] args) {
        SplayTree<Integer> splaytree1 = new SplayTree<>();

        int[] keys = {10, 20, 30, 40, 50, 25};
        for (int key : keys) {
            splaytree1.insert(key);
        }
        splaytree1.print();
        System.out.print("Buscamos 20 : ");
        System.out.println(splaytree1.search(20) + "\n");

        System.out.println("Eliminamos 20 :");
        splaytree1.delete(20);
        splaytree1.print();

        System.out.print("Buscamos otra vez 20 : ");
        System.out.println(splaytree1.search(20) + "\n");

        System.out.println("Añadimos 33 :\n");
        splaytree1.insert(33);
        splaytree1.print();

        splaytree1.visualize();
    }
}
