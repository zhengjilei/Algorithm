package programmer_interview;

import org.junit.Test;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/1/14
 */
public class Q081_StrReplace {

    public void replace(char[] chs) {

        int index = 0;
        int spaceCount = 0;
        while (index < chs.length && chs[index] != 0) {
            if (chs[index] == ' ') spaceCount++;
            index++;
        }

        // index 为左部分的长度
        int resultLen = index + 2 * spaceCount;
        int resultIndex = resultLen - 1;

        //左部分从右到左转换
        index--;
        while (index >= 0) {
            if (chs[index] == ' ') {
                chs[resultIndex--] = '0';
                chs[resultIndex--] = '2';
                chs[resultIndex--] = '%';
            } else {
                chs[resultIndex--] = chs[index];
            }
            index--;
        }

    }


    public void modify(char[] chs) {
        int resultIndex = chs.length - 1;
        int index = chs.length - 1;
        while (index >= 0) {
            if (chs[index] >= '0' && chs[index] <= '9') {
                chs[resultIndex--] = chs[index];
            }
            index--;
        }

        while (resultIndex >= 0) {
            chs[resultIndex--] = '*';
        }

    }

    @Test
    public void test() {
//        char[] chs = {'a', ' ', 'b', ' ', ' ', 'c', 0, 0, 0, 0, 0, 0};
//        replace(chs);
//        System.out.println(Arrays.toString(chs));
        char[] chs2 = {'1', '2', '*', '*', '3', '*', '4', '5'};
        modify(chs2);
        System.out.println(Arrays.toString(chs2));
    }
}
