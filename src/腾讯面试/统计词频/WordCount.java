package 腾讯面试.统计词频;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Created by lenovo on 2018/4/22.
 */
public class WordCount {

    @Test
    public void changeFormat() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("passage.txt")));
        PrintWriter writer = new PrintWriter(new FileWriter("format.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            String s = line.replaceAll("[^a-zA-Z]", " ");
            writer.println(s);
        }
        reader.close();
        writer.close();
    }

    @Test
    public void count() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("format.txt")));
        HashMap<String, Integer> map = new HashMap<>();
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(" ");
            for (String str : split) {
                if (str.length() > 0) {
                    String s = str.toLowerCase();
                    Integer hasCount = map.get(s);

                    if (hasCount != null && hasCount > 0) {
                        map.put(s, hasCount + 1);
                    } else {
                        map.put(s, 1);
                    }
                }
            }
        }
        reader.close();
        // 先插完，再排序（插的过程中，得不停的查找 Map 中是否已存在，如果一开始用 LinkedHashMap,查找效率降低）
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        PrintWriter printWriter = new PrintWriter(new FileWriter("count-result.txt"));
        printWriter.println(list.size());
        for (Map.Entry<String, Integer> entry : list) {
            String str = entry.getKey() + ":\t" + entry.getValue();
            printWriter.println(str);
//            System.out.printf("%-15s", entry.getKey());
//            System.out.printf("%-10s", entry.getValue());
//            System.out.println();
        }
        printWriter.close();

    }


    @Test
    public void testA() {
        String str = "dsa  frg , grtgtr, 32.你我";
        String[] s = str.split("[^a-zA-Z]");// "dsa","","frg","","","grtgtr"
        System.out.println(s.length);
        System.out.println(Arrays.toString(s));


    }

    @Test
    public void testB() {
        String s = "boo:and:foo";
        String[] strs = s.split(":"); // 替换: 为 空字符串，进行分割
        String[] strings = s.split("o");//b__:and:f__   -->   b,"",:and:f  ,首尾连续多个舍弃
        System.out.println(strs.toString());
        System.out.println(Arrays.toString(strings));

        String s1 = s.replaceAll("[^a-zA-Z]", " ");
        System.out.println(s);
        System.out.println(s1);
    }

    @Test
    public void testFilePosition() {
        File f = new File("passage.txt");
        System.out.println(f.exists());
    }

}
