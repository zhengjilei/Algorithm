package binary_tree;

import org.junit.Test;

import java.util.Arrays;

public class MultiTreeTest {

    @Test
    public void test() {
        MultiTree<String> tree = new MultiTree<>();

        MultiTreeNode<String> a = new MultiTreeNode<>("A");
        MultiTreeNode<String> b = new MultiTreeNode<>("B");
        MultiTreeNode<String> c = new MultiTreeNode<>("C");
        MultiTreeNode<String> d = new MultiTreeNode<>("D");
        MultiTreeNode<String> e = new MultiTreeNode<>("E");
        MultiTreeNode<String> f = new MultiTreeNode<>("F");
        MultiTreeNode<String> g = new MultiTreeNode<>("G");
        MultiTreeNode<String> h = new MultiTreeNode<>("H");
        MultiTreeNode<String> i = new MultiTreeNode<>("I");
        MultiTreeNode<String> j = new MultiTreeNode<>("J");
        MultiTreeNode<String> k = new MultiTreeNode<>("K");
        MultiTreeNode<String> l = new MultiTreeNode<>("L");
        MultiTreeNode<String> m = new MultiTreeNode<>("M");
        MultiTreeNode<String> n = new MultiTreeNode<>("N");
        MultiTreeNode<String> o = new MultiTreeNode<>("O");
        MultiTreeNode<String> p = new MultiTreeNode<>("P");
        MultiTreeNode<String> q = new MultiTreeNode<>("Q");

        a.children.add(b);
        a.children.add(c);
        a.children.add(d);

        b.children.add(e);
        b.children.add(f);

        c.children.add(g);

        d.children.add(h);
        d.children.add(i);
        d.children.add(j);

        e.children.add(k);
        e.children.add(l);

        f.children.add(m);

        g.children.add(n);
        g.children.add(o);
        g.children.add(p);

        i.children.add(q);

        tree.root = a;

//        MultiTreeNode node1 = tree.getNodeByValue("E");
//        System.out.println(node1);
//
//        MultiTreeNode[] path = tree.getPath(node1);
//        System.out.println(Arrays.toString(path));


        MultiTreeNode nodeP = tree.getNodeByValue("M");
        MultiTreeNode nodeQ = tree.getNodeByValue("L");
        MultiTreeNode father = tree.getFirstCommonFather(nodeP, nodeQ);
        System.out.println(father);

    }
}
