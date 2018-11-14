package binary_tree;

/**
 * Created by Ethan-Walker on 2018/4/21.
 */
public class Node<T> {

    T value;

    public Node(T value) {
        this.value = value;
    }

    Node leftChild;
    Node rightChild;
    Node parentNode;
}
