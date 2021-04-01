package remove_duplicate;

import java.util.*;

/**
 * 元素去重 计数
 *   数值范围 : [0,10000)
 *          hash        array       set         预排序+双指针         基数排序
 * 1 万       2        28          31              25                  1
 * 100 万     84       2099        268             215                 7
 * 1000万     523      27638       2197            948                 20
 * 1 亿      4600                  25022           8389                127
 * 10 亿     41003                                 82135               1301
 */
public class RenoveDuplicate {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 10000);
        }
//        System.out.println("去重前： ");
//        show(array, n);
        long begin = System.currentTimeMillis();
//      int length = setSolve(array);
//      HashMap<Integer, Integer> result = solve(array);
        int[] result = radixSort(array);

        long end = System.currentTimeMillis();
        System.out.println("去重消耗的时间: +" + (end - begin) + " ms, 去重后： ");

        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) {
                System.out.println(i + " == " + result[i]);
            }
        }
/*      Set<Map.Entry<Integer, Integer>> entries = result.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            System.out.println(entry.getKey() + " == " + entry.getValue());
        }
*/
//      show(array, length);

    }


    public static int hashSolve(int a[]) {

        HashSet<Integer> hashSet = new HashSet<>();
        int length = a.length;
        for (int i = 0; i < length; i++) {
            hashSet.add(a[i]);
        }
        int size = hashSet.size();
        Iterator<Integer> iterator = hashSet.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            a[i++] = iterator.next();
        }
        return size;
    }

    /**
     * 红黑树实现的集合 O(nlogn)
     *
     * @param a
     * @return
     */
    public static int setSolve(int a[]) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        int length = a.length;
        for (int i = 0; i < length; i++) {
            treeSet.add(a[i]);
        }
        int size = treeSet.size();
        for (int i = 0; i < size; i++) {
            a[i] = treeSet.pollFirst();
        }
        return size;
    }


    /**
     * 通过新建数组辅助去重 O(n^2)
     * 返回去重后的元素总数
     */
    public static int arraySolve(int a[]) {
        int length = a.length;
        int temp[] = new int[length];
        int t = 0, j;// 当前temp中的元素数量
        for (int i = 0; i < length; i++) {
            for (j = 0; j < t; j++) {
                if (temp[j] == a[i]) break;
            }
            if (j == t) {
                temp[t++] = a[i];
            }
        }
        for (j = 0; j < t; j++) {
            a[j] = temp[j];
        }
        return t;
    }

    public static void show(int a[], int length) {
        for (int i = 0; i < length; i++) {
            if (i % 10 == 9) {
                System.out.println(a[i]);
            } else {
                System.out.print(a[i] + " ");
            }
        }
    }

    /**
     * 预排序处理+双边界 去重计数
     * O(nlogn)
     *
     * @param a
     * @return
     */
    public static HashMap<Integer, Integer> solve(int a[]) {
        int length = a.length;
        Arrays.sort(a);
        HashMap<Integer, Integer> countMap = new LinkedHashMap<>();
        int l, r;
        for (l = 0, r = l + 1; r < length; r++) {
            if (a[l] != a[r]) {
                countMap.put(a[l], r - l);
                l = r;
            }
        }
        countMap.put(a[l], r - l);
        return countMap;
    }

    /**
     * 基数排序 O(n)
     * 适用于范围 range << 数值规模
     * 例如：这里的范围为 [0,10000)  规模可能达到 10 亿
     */
    public static int[] radixSort(int[] a) {
        int[] radixArray = new int[10001]; // 数值范围为 [0,10000) 就开 这么大的数组
        int length = a.length;
        for (int i = 0; i < length; i++) {
            radixArray[a[i]]++;
        }
        return radixArray;
    }
}
