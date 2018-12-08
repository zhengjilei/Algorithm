package 剑指offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 求字符串中的所有排列
 * created by Ethan-Walker on 2018/12/8
 */
public class Q038_Permutation {

    public ArrayList<String> Permutation(String str) {
        char[] chs = str.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        if (chs.length == 0) {
            return list;
        }

        Arrays.sort(chs);
        list.add(new String(chs));

        while (true) {
            int index = -1;
            int j = chs.length - 2;
            for (; j >= 0; j--) {
                if (chs[j] < chs[j + 1]) {
                    index = j;
                    break;
                }
            }
            if (index == -1) {
                break;
            }
            // 从 j+1 开始寻找大于 a[j]且最小的数
            int min = (int) chs[index + 1];
            int minIndex = index + 1;

            for (j = index + 2; j < chs.length; j++) {
                if (chs[j] > chs[index] && chs[j] < min) {
                    minIndex = j;
                    min = chs[j];
                }
            }

            swap(chs, index, minIndex);
            Arrays.sort(chs, index + 1, chs.length);

            list.add(new String(chs));

        }
        return list;


    }

    public void swap(char[] chs, int i, int j) {
        char c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }

    @Test
    public void test() {
        String s = "";
        System.out.println(Permutation(s));

    }


}
