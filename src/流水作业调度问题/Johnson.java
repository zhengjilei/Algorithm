package 流水作业调度问题;

import java.util.*;

/**
 * Johnson 算法
 * Created by EthanWalker on 2017/12/2.
 */
public class

Johnson {

    private static int[][] time;

    private static int[] order;
    private static TreeMap<Integer, Integer> higher;  // 按 key 升序
    private static TreeMap<Integer, Integer> lower;  // 按value降序

    private static int minTime = 0;
    static List<Map.Entry<Integer, Integer>> list;

    public static int minTime() {

        ArrayList<Integer> higherIndex = new ArrayList<>();
        ArrayList<Integer> lowerIndex = new ArrayList<>();
        order = new int[time.length];
        higher = new TreeMap<>();
        lower = new TreeMap<>();

        int higherCount = 0;
        int lowerCount = 0;
        for (int i = 0; i < time.length; i++) {
            if (time[i][0] < time[i][1]) {
                // 加入到 按键升序map中
                higher.put(time[i][0], time[i][1]);
            } else {
                lower.put(time[i][0], time[i][1]);
            }
        }

        list = new ArrayList<>(lower.entrySet());
        // 对list 排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        // 2,5 4,7 6,9
        cal(higher, list);
        // 7,3  6,2  8,2
        return minTime;
    }


    public static void cal(Map<Integer, Integer> map, List<Map.Entry<Integer, Integer>> list) {
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int lastSecondTime = 0;
        int[] m1 = new int[time.length];  // m1[i] 表示第 i+1 个作业在 M1 上执行过后的时刻, m1[0]=2 表示第 1 个作业执行过后时刻为 2
        int[] m2 = new int[time.length];  // m2[i] 表示第 i+1 个作业在 M2 上执行过后的时刻, m1[0] = 7, 表示第 1 个作业在M2 执行过后的时刻为 7

        int m1Count = 0, m2Count = 0;
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (m1Count == 0) {
                m1[m1Count++] = entry.getKey();
            } else {
                m1[m1Count] = entry.getKey() + m1[m1Count - 1];
                m1Count++;
            }

            if (m2Count == 0) {
                m2[m2Count++] = m1[0] + entry.getValue();
            } else {
                if (m2[m2Count - 1] >= m1[m2Count]) {
                    m2[m2Count] = m2[m2Count - 1] + entry.getValue();
                } else {
                    m2[m2Count] = m1[m2Count] + entry.getValue();
                }
                m2Count++;
            }
        }
        for (Map.Entry<Integer, Integer> entry : list) {
            m1[m1Count] = entry.getKey() + m1[m1Count - 1];
            m1Count++;

            if (m2[m2Count - 1] >= m1[m2Count]) {
                m2[m2Count] = m2[m2Count - 1] + entry.getValue();
            } else {
                m2[m2Count] = m1[m2Count] + entry.getValue();
            }
            m2Count++;
        }

        minTime = m2[m2Count - 1];
        System.out.println(Arrays.toString(m1));
        System.out.println(Arrays.toString(m2));
        return;
    }

    public static void trackback() {
        int orderCount = 0;
        Set<Map.Entry<Integer, Integer>> entries = higher.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            System.out.println(entry.getKey() + "," + entry.getValue());
            for (int i = 0; i < time.length; i++) {
                if (time[i][0] == entry.getKey() && time[i][1] == entry.getValue()) {
                    order[orderCount++] = i+1;
                    break;
                }
            }
        }
        entries = lower.entrySet();
        for (Map.Entry<Integer, Integer> entry : list) {
            System.out.println(entry.getKey() + "," + entry.getValue());

            for (int i = 0; i < time.length; i++) {
                if (time[i][0] == entry.getKey() && time[i][1] == entry.getValue()) {
                    order[orderCount++] = i+1;
                    break;
                }
            }
        }
        System.out.println("执行顺序为: " + Arrays.toString(order));

    }

    public static void main(String[] args) {
        time = new int[][]{{2, 5}, {7, 3}, {6, 2}, {4, 7}, {6, 9}, {8, 2}};
        int i = minTime();
        System.out.println("最小时间为: " + i);
        trackback();
    }

}
