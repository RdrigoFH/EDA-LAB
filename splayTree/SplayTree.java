import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class SplayTree<E extends Comparable<E>> {
    private Node<E> root;

    public SplayTree() {
        this.root = null;
    }

    public void insert(E key) {
        if (root == null) {
            root = new Node<E>(key);
            return;
        }

        root = splay(root, key);

        if (root.getData().compareTo(key) == 0) return;

        Node<E> newNode = new Node<E>(key);

        if (root.getData().compareTo(key) > 0) {
            newNode.setRight(root);
            newNode.setLeft(root.getLeft());
            root.setLeft(null);
        } else {
            newNode.setLeft(root);
            newNode.setRight(root.getRight());
            root.setRight(null);
        }

        root = newNode;
    }

    private Node<E> splay(Node<E> root, E key) {
        if (root == null || root.getData().compareTo(key) == 0)
            return root;

        if (root.getData().compareTo(key) > 0) {
            if (root.getLeft() == null) return root;

            if (root.getLeft().getData().compareTo(key) > 0) {
                root.getLeft().setLeft(splay(root.getLeft().getLeft(), key));
                root = rightRotate(root);
            } else if (root.getLeft().getData().compareTo(key) < 0) {
                root.getLeft().setRight(splay(root.getLeft().getRight(), key));
                if (root.getLeft().getRight() != null)
                    root.setLeft(leftRotate(root.getLeft()));
            }

            return (root.getLeft() == null) ? root : rightRotate(root);
        } else {
            if (root.getRight() == null) return root;

            if (root.getRight().getData().compareTo(key) < 0) {
                root.getRight().setRight(splay(root.getRight().getRight(), key));
                root = leftRotate(root);
            } else if (root.getRight().getData().compareTo(key) > 0) {
                root.getRight().setLeft(splay(root.getRight().getLeft(), key));
                if (root.getRight().getLeft() != null)
                    root.setRight(rightRotate(root.getRight()));
            }

            return (root.getRight() == null) ? root : leftRotate(root);
        }
    }

    public void delete(E key) {
        if (root == null) return;

        root = splay(root, key);

        if (!(root.getData().compareTo(key) == 0)) return;

        if (root.getLeft() == null) {
            root = root.getRight();
        } else {
            Node<E> temp = root;
            root = splay(root.getLeft(), key);
            root.setRight(temp.getRight());
        }
    }

    public boolean search(E key) {
        root = splay(root, key);
        return root != null && root.getData().compareTo(key) == 0;
    }

    public void print() {
        inorderHelper(root);
        System.out.println("\n");
    }

    private void inorderHelper(Node<E> root) {
        if (root != null) {
            inorderHelper(root.getLeft());
            System.out.print(root.getData() + " ");
            inorderHelper(root.getRight());
        }
    }

    private Node<E> leftRotate(Node<E> x) {
        Node<E> y = x.getRight();
        Node<E> T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);
        return y;
    }

    private Node<E> rightRotate(Node<E> y) {
        Node<E> x = y.getLeft();
        Node<E> T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);
        return x;
    }

    public void visualize() {
        Graph graph = new SingleGraph("Splay Tree");
        graph.setAttribute("ui.stylesheet", "node { text-alignment: at-right; text-color: black; text-background-mode: rounded-box; text-background-color: yellow; }");

        addNodesToGraph(graph, root, null);

    
        System.setProperty("org.graphstream.ui", "swing");
        graph.display();
    }

    private void addNodesToGraph(Graph graph, Node<E> node, String parentId) {
        if (node == null) return;

        String nodeId = node.getData().toString();
        graph.addNode(nodeId);
        graph.getNode(nodeId).setAttribute("ui.label", nodeId);

        if (parentId != null) {
            graph.addEdge(parentId + "-" + nodeId, parentId, nodeId, true);
        }

        addNodesToGraph(graph, node.getLeft(), nodeId);
        addNodesToGraph(graph, node.getRight(), nodeId);
    }
}