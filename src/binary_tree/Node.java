package binary_tree;

/**
 * Created by Ethan-Walker on 2018/4/21.
 */
public class Node<T> {

    T value;

    public Node(T value) {
        this.value = value;
    }

    public Node leftChild;
    public Node rightChild;
    public Node parentNode;
}
