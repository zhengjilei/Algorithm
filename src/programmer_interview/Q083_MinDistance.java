package programmer_interview;

import org.junit.Test;

import java.util.HashMap;

/**
 * created by Ethan-Walker on 2019/1/24
 */
public class Q083_MinDistance {

    /**
     * 每找到一个 str1,依次从此位置向两边查找str2
     * 时间复杂度: 最坏O(n^2)
     * 空间复杂度: O(1)
     *
     * @param strs
     * @param str1
     * @param str2
     * @return
     */
    public int minDistance(String[] strs, String str1, String str2) {
        if (strs == null || strs.length == 0 || str1 == null || str2 == null) return -1;
        //查找 str1
        int str1Pos = -1;
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(str1)) {
                for (int dis = 1; dis < minDis && (i - dis >= 0 || i + dis < strs.length); dis++) {
                    if (i - dis >= 0 && strs[i - dis].equals(str2)) {
                        minDis = dis;
                        break;
                    }
                    if (i + dis < strs.length && strs[i + dis].equals(str2)) {
                        minDis = dis;
                        break;
                    }
                }
            }
        }
        if (minDis == Integer.MAX_VALUE) {
            return -1;
        }
        return minDis;
    }


    public int minDistance2(String[] strs, String str1, String str2) {
        if (strs == null || strs.length == 0 || str1 == null || str2 == null) return -1;

        int lastStr1 = -1;
        int lastStr2 = -1;
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {

            if (strs[i].equals(str1)) {
                lastStr1 = i;
                if (lastStr2 != -1 && lastStr1 - lastStr2 < minDis) {
                    minDis = lastStr1 - lastStr2;
                }
            } else if (strs[i].equals(str2)) {
                lastStr2 = i;

                if (lastStr1 != -1 && lastStr2 - lastStr1 < minDis) {
                    minDis = lastStr2 - lastStr1;
                }
            }
        }
        return minDis == Integer.MAX_VALUE ? -1 : minDis;
    }

    HashMap<String, HashMap<String, Integer>> disMap = new HashMap<>();

    // 求strs 数组中任意两个字符串的最近距离
    // 多次查，要求一次计算之后，以后每次查的复杂度都是O(1)
    public void initDisMap(String[] strs) {
        int dis = 0;
        HashMap<String, Integer> innerMap;
        HashMap<String, Integer> innerMap2; // 对称map
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (disMap.containsKey(strs[i])) {
                    innerMap = disMap.get(strs[i]);

                    if (innerMap.containsKey(strs[j])) {
                        innerMap2 = disMap.get(strs[j]);
                        if (j - i < innerMap.get(strs[j])) {
                            innerMap.put(strs[j], j - i);
                            innerMap2.put(strs[i], j - i);
                        }
                    } else {
                        innerMap.put(strs[j], j - i);
                        innerMap2 = disMap.get(strs[j]);
                        if (innerMap2 == null) {
                            innerMap2 = new HashMap<>();
                            disMap.put(strs[j], innerMap2);
                        } else {
                            innerMap2.put(strs[i], j - i);
                        }
                    }
                } else {
                    innerMap = new HashMap<>();
                    innerMap.put(strs[j], j - i);

                    innerMap2 = new HashMap<>();
                    innerMap2.put(strs[i], j - i);

                    disMap.put(strs[i], innerMap);
                    disMap.put(strs[j], innerMap2);
                }
            }
        }

    }

    public int minDistance3(String str1, String str2) {
        if (str1 == null || str2 == null) return -1;
        if (str1.equals(str2)) return 0;
        return disMap.get(str1) == null ? -1 : disMap.get(str1).getOrDefault(str2, -1);
    }

    @Test
    public void test() {
        String[] strs = {"a", "b", "c", "d", "a", "g", "h", "o", "b", "o", "10", "e", "r", "g"};
        String str1 = "o";
        String str2 = "g";
        System.out.println(minDistance(strs, str1, str2));
        System.out.println(minDistance2(strs, str1, str2));
        initDisMap(strs);
        System.out.println(minDistance3(str1, str2));
        System.out.println(minDistance3("a", "d"));
        System.out.println(minDistance3(null, "ds"));
        System.out.println(minDistance3("c", "z"));
        System.out.println(minDistance3("r", "c"));
        System.out.println(minDistance3("r", "g"));
        System.out.println(minDistance3("o", "g"));
    }
}