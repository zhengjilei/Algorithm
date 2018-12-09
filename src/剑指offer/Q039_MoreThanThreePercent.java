package 剑指offer;

import org.junit.Test;

import java.util.*;

/**
 * 求数组中出现次数超过 1/3 的数
 * <p>
 * 分析：这样的数可能存在 0 、1 、2 个
 * 用一个大小为 3 的数组，每次删除 3 个不同的数，遍历结束后，如果有超过 1/3 的数，一定在该数组中
 * created by Ethan-Walker on 2018/12/9
 */
public class Q039_MoreThanThreePercent {


    /**
     * 时间复杂度: O(nk)
     * 空间复杂度: O(k) HashMap 需要存放 k 个 key、value
     *
     * @param array
     * @param k     数组中次数超过数组长度 1/k 的数
     * @return
     */
    public ArrayList<Integer> getMoreThanThreePer(int[] array, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length == 0) return list;
        if (array.length == 1) {
            list.add(array[0]);
            return list;
        }
        HashMap<Integer, Integer> map = new HashMap<>(); // key 存放数值，value存放该数值的累计次数
        // map 中如果出现k个不同的key，就需要删除一次

        map.put(array[0], 1);
        for (int i = 1; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                // 如果
                int count = map.get(array[i]);
                map.put(array[i], ++count);
            } else {
                // 不包含，插入，插入之后检查 key的个数是否等于 k
                map.put(array[i], 1);

                if (map.size() == k) {
                    Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
                    Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();

                    while (iterator.hasNext()) {
                        Map.Entry<Integer, Integer> item = iterator.next();
                        Integer key = item.getKey();
                        Integer value = item.getValue();
                        value--;
                        if (value == 0) {
                            iterator.remove();
                        } else {
                            map.put(key, value);
                        }
                    }

                }
            }
        }

        if (map.size() >= 1) {
            Set<Integer> keySet = map.keySet();
            for (int key : keySet) {
                if (checkMoreThanThreePercent(array, key, k)) {
                    list.add(key);
                }
            }
        }
        return list;

    }


    /**
     * @param array
     * @return
     */
    public ArrayList<Integer> getMoreThanThreePercentNum(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length == 0) return list;
        if (array.length == 1) {
            list.add(array[0]);
            return list;
        }
        int candidate1 = array[0], candidate2 = -1;
        int count1 = 1, count2 = 0;
        for (int i = 1; i < array.length; i++) {
            if (count1 == 0) {
                candidate1 = array[i];
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = array[i];
                count2 = 1;
            }

            if (array[i] == candidate1) {
                candidate1++;
            } else {
                candidate1--;
            }


        }
        return list;
    }

    @Test
    public void test() {
        int[] a = new int[]{5, 4, 3, 4};
        ArrayList<Integer> list = getMoreThanThreePer(a, 3);

        System.out.println(list);
    }

    public boolean checkMoreThanThreePercent(int[] array, int num, int k) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                count++;
            }
        }
        if (count > array.length / k) {
            return true;
        }
        return false;
    }
}
