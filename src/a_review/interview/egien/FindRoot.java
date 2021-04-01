package a_review.interview.egien;

import org.junit.Test;

import java.util.*;

/**
 * 艾耕科技
 * 给出 [[1,2],[2,3] [4,5]] 表示1->2, 2->3 4->5
 * 找出所有的起点: 1, 4
 * created by Ethan-Walker on 2019/3/19
 */
public class FindRoot {
    public Set<Integer> getRoot(int[][] num) {
        UFSets ufSets = new UFSets();

        for (int i = 0; i < num.length; i++) {
            if (!ufSets.contain(num[i][0])) {
                ufSets.insert(num[i][0]);
            }
            if (!ufSets.contain(num[i][1])) {
                ufSets.insert(num[i][1]);
            }
        }
        for (int i = 0; i < num.length; i++) {
            ufSets.union(num[i][0], num[i][1]);
        }

        HashMap<Integer, Integer> parent = ufSets.parent;
        HashSet<Integer> result = new HashSet<>();

        Set<Integer> keySet = parent.keySet();

        for (Integer key : keySet) {
            result.add(ufSets.find(key));
        }
        return result;
    }


    class UFSets {
        HashMap<Integer, Integer> parent;

        public UFSets() {
            parent = new HashMap<>();

        }

        public int find(int index) {
            while (parent.get(index) != index) {
                index = parent.get(index);
            }
            return index;
        }


        public int union(int i, int j) {
            int parentI = find(i);
            int parentJ = find(j);
            if (parentI == parentJ) return parentI;
            parent.put(parentJ, parentI);
            return parentI;
        }

        public void insert(int i) {
            parent.put(i, i);
        }

        public boolean contain(int i) {
            return parent.containsKey(i);
        }
    }

    @Test
    public void test() {
        int[][] a = new int[][]{{1, 2}, {3, 4}, {4, 5}, {7, 9}};
        System.out.println(getRoot(a));
        int[][] b = new int[][]{{1, 2}, {3, 4}, {2, 3}, {7, 9}};
        System.out.println(getRoot(b));
        int[][] c = new int[][]{{1, 2}, {2, 3}};
        System.out.println(getRoot(c));
        int[][] d = new int[][]{{1, 2}, {2, 1}};
        System.out.println(getRoot(d));


    }


    @Test
    public void test2() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(12);
        set.add(1);
        set.add(32);
        set.add(15);
        set.add(9);
        System.out.println(set);

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(12);
        hashSet.add(1);
        hashSet.add(32);
        hashSet.add(15);
        hashSet.add(9);
        System.out.println(hashSet);

        LinkedHashSet<Integer> lhs = new LinkedHashSet<>();
        lhs.add(12);
        lhs.add(1);
        lhs.add(32);
        lhs.add(15);
        lhs.add(9);
        System.out.println(lhs);
    }


}
