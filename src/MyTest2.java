import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Ethan-Walker on 2018/7/28.
 */
public class MyTest2 {
    public static void main(String[] args) {
        Node[] nodes = new Node[20];
        for (int i = 0; i < 20; i++) {
            nodes[i] = new Node(i);
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.val - o1.val;
            }
        });
        System.out.println(Arrays.toString(nodes));
    }

    static class Node implements Comparable<Node> {
        int val;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }

        @Override
        public String toString() {
            return this.val + "";
        }
    }

}




