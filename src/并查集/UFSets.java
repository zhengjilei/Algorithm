package 并查集;

import java.util.LinkedHashMap;

/**
 * 当需要统计每个集合中的总数时，设 parent[i]= -1 这种方式比较适合
 * created by Ethan-Walker on 2018/11/7
 */
public class UFSets {

    int[] parent; //parent[i]= j 表示编号为 i 的节点父节点编号为 j
    int size;

    LinkedHashMap<Integer, Integer> valueNum; // key 表示数值，value 表示数值对应的编号
    int[] numValue; // 根据编号查数值

    public UFSets(int[] value, int size) {
        this.size = size;
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = -1;
        }
        buildValueIndex(value);
    }

    /**
     * 构建数值和索引间的联系
     *
     * @param value
     */
    public void buildValueIndex(int[] value) {
        numValue = value;
        valueNum = new LinkedHashMap<>();
        for (int i = 0; i < value.length; i++) {
            valueNum.put(value[i], i);
        }
    }

    /**
     * @param val 数值，不是该数值对应的编号
     * @return 返回数值所在的集合名（根节点的编号）
     */
    public int simpleFind(int val) {
        Integer index = valueNum.get(val); // 获取该数值对应的编号
        while (parent[index] >= 0) index = parent[index];
        return index;
    }

    /**
     * 折叠压缩路径
     * 从该节点到根节点的路径上所有的节点(包括该节点本身)全部指向根节点
     * 大概花费原来的查找时间的 2倍，但是能大大减少下一次的查找时间
     *
     * @param val 数值，不是该数值对应的编号
     * @return
     */
    public int collapsingFind(int val) {
        int root = simpleFind(val);
        int j = valueNum.get(val), t;
        while (parent[j] >= 0) {
            t = parent[j];
            parent[j] = root;
            j = t;
        }
        return root;
    }


    /**
     * 将root2 并入 root1中
     * 这样可能会导致整棵树线性化
     *
     * @param root1 集合名（根节点的编号） parent[root1]=-xxx  xxx为集合 root1 中的元素个数
     * @param root2
     */
    public void simpleUnion(int root1, int root2) {
        parent[root1] += parent[root2];
        parent[root2] = root1;
    }

    /**
     * root1、root2 两个集合，数量小的并入数量大的，避免树线性化
     * 能保证m个节点的树，高度不大于 log2(m)
     *
     * @param root1 表示所在集合的根节点索引
     * @param root2
     */
    public void weightUnion(int root1, int root2) {
        int size1 = -parent[root1];
        int size2 = -parent[root2];
        if (size1 > size2) {
            // root2 并入 root1 中
            parent[root1] += parent[root2];
            parent[root2] = root1;
        } else {
            parent[root2] += parent[root1];
            parent[root1] = root2;
        }
    }

    public void printValueAndParent() {
        System.out.print("index\t");
        for (int i = 0; i < size; i++) {
            System.out.printf("%5d", i);
        }
        System.out.println();
        System.out.print("value\t");
        for (int i = 0; i < size; i++) {
            System.out.printf("%5d", numValue[i]);
        }
        System.out.println();

        System.out.print("parent\t");
        for (int i = 0; i < size; i++) {
            System.out.printf("%5d", parent[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] value = new int[]{21, 24, 12, 9, 3, 19, 4, 10, 8};
        UFSets ufSets = new UFSets(value, 9);
        for (int i = 0; i < value.length - 1; i++) {
            int unionIndex = ufSets.simpleFind(value[i]);
            int unionIndex2 = ufSets.simpleFind(value[i + 1]);
            ufSets.weightUnion(unionIndex, unionIndex2);
        }
        ufSets.printValueAndParent();
    }
}
