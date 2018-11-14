package linkedlist;

/**
 * Created by lenovo on 2018/7/22.
 * 多项式
 */
public class Polynomial {

    private Node head; // 带附加头结点的 链表


    public Polynomial() {
        head = new Node(-1, -1);
        head.next = null;
    }

    public void init(double[] coef, int[] exp) {
        int length = coef.length;
        int i = 0;
        Node tail = head;
        while (i < length) {
            Node node = new Node(coef[i], exp[i]);
            tail.next = node;
            tail = node;
            i++;
        }
        sort();
    }

    // 对链表中的节点进行排序，按照阶从小到大排序，只需要交换系数、指数值就行，不需要改变每个节点的实际位置coef 、exp
    public void sort() {
        // 冒泡排序
        Node prev = head;
        Node p = head.next;  //
        Node q = null;
        while (p != null) {
            // 将之后最小的与 p 交换,每次选出最小的放入 p 位置
            q = p.next;
            while (q != null) {
                if (q.exp < p.exp) {
                    q.exp = p.exp + q.exp - (p.exp = q.exp);
                    q.coef = p.coef + q.coef - (p.coef = q.coef);
                }
                q = q.next;
            }
            p = p.next;
        }
    }

    public void print() {
        Node node = head.next;
        System.out.println("系数\t\t指数");
        while (node != null) {
            System.out.println(node.coef + "\t\t" + node.exp);
            node = node.next;
        }
    }

    public Polynomial add(Polynomial p2) {
        Polynomial p1 = this;
        Node a = p1.head;
        Node b = p2.head;
        Node c = new Node(-1, -1);  // 和的链表头指针
        Node cTemp = c;
        while (a.next != null && b.next != null) {
            Node aTemp = a.next;
            Node bTemp = b.next;

            if (aTemp.exp < bTemp.exp) {
                Node node = new Node(aTemp.coef, aTemp.exp);
                cTemp.next = node;
                cTemp = node;
                a = a.next;
            } else if (aTemp.exp == bTemp.exp) {
                Node node = new Node(aTemp.coef + bTemp.coef, aTemp.exp);
                cTemp.next = node;
                cTemp = node;
                a = a.next;
                b = b.next;
            } else {
                Node node = new Node(bTemp.coef, bTemp.exp);
                cTemp.next = node;
                cTemp = node;
                b = b.next;
            }
        }
        while (a.next != null) {
            Node node = new Node(a.next.coef, a.next.exp);
            cTemp.next = node;
            cTemp = node;
            a = a.next;
        }
        while (b.next != null) {
            Node node = new Node(b.next.coef, b.next.exp);
            cTemp.next = node;
            cTemp = node;
            b = b.next;
        }
        Polynomial polynomial = new Polynomial();
        polynomial.head = c;
        return polynomial;
    }

    public Polynomial multiply(Polynomial p2) {
        Polynomial p1 = this;
        Node p1Head = this.head;
        Node p2Head = p2.head;

        Node p1Temp = p1Head.next;
        Polynomial innerP1 = null;
        Polynomial innerP2 = null;
        while (p1Temp != null) {

            // 用 p1 中的第1项 * p2 中的每一项 ，得到一个 多项式
            Node tempPHead = new Node(-1, -1);
            Node innerPTempNode = tempPHead;
            Node p2Temp = p2Head.next;
            while (p2Temp != null) {
                Node node = new Node(p1Temp.coef * p2Temp.coef, p1Temp.exp + p2Temp.exp);
                innerPTempNode.next = node;
                innerPTempNode = node;
                p2Temp = p2Temp.next;
            }

            if (innerP1 == null) {
                // 第一次计算时， innerP1 ==null
                innerP1 = new Polynomial();
                innerP1.head = tempPHead;
            } else {
                // 非第一次计算
                innerP2 = new Polynomial();
                innerP2.head = tempPHead;

                // 计算 p1 和 p2 的和，并将结果赋予 p1
                innerP1 = innerP1.add(innerP2);
            }
            p1Temp = p1Temp.next;
        }
        return innerP1;
    }

    private class Node {
        double coef; // 系数
        int exp; // 指数
        Node next;

        public Node(double coef, int exp) {
            this.coef = coef;
            this.exp = exp;
        }
    }

    public static void main(String[] args) {
        double[] coef1 = new double[]{1.3, 2.3, 4, 14, 3, 0};
        int[] exp1 = new int[]{2, 8, 1, 0, 12, 5};

        double[] coef2 = new double[]{3.3, 21, 8.1, 4.5, 6, 2.1};
        int[] exp2 = new int[]{3, 5, 1, 8, 4, 12};

        Polynomial polynomial1 = new Polynomial();
        polynomial1.init(coef1, exp1);
        polynomial1.print();
        Polynomial polynomial2 = new Polynomial();
        polynomial2.init(coef2, exp2);
        polynomial2.print();

        Polynomial result = polynomial1.add(polynomial2);
        result.print();

        Polynomial multiplyResult = polynomial1.multiply(polynomial2);
        multiplyResult.print();
    }
}
