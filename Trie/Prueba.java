public class Prueba {
    public static void main(String[] args) {
        Trie trieTree= new Trie();

        String[] keys = {"carro", "carton", "cordon", "corporacion", "arbol", "carbon", "cartel"};
        for (String key : keys) {
            trieTree.insert(key);
        }
        trieTree.print();
        
    }


}
