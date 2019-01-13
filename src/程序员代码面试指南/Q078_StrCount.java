package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/13
 */
public class Q078_StrCount {

    public String strCount(String str) {
        StringBuilder sb = new StringBuilder();

        int index = 0, lastIndex = 0;
        char ch;
        char[] chs = str.toCharArray();
        boolean isFirst = true;
        while (index < str.length()) {
            lastIndex = index++;
            while (index < str.length() && chs[index] == chs[lastIndex]) {
                index++;
            }

            if (!isFirst) {
                sb.append("_");
            } else {
                isFirst = false;
            }
            sb.append(chs[lastIndex]).append("_").append(index - lastIndex);
        }
        return sb.toString();
    }


    public char charOfStrings(String str, int destIndex) {

        int cnt = 0;
        int index = 0;
        int j = 0;
        for (int i = 0; i < str.length(); i += 4) {
            j = i + 2; // 数字值
            if (destIndex >= cnt && destIndex < (cnt + str.charAt(j) - '0')) {
                // 当前字符就是目标字符
                return str.charAt(i);
            }
            cnt += (str.charAt(j) - '0');
        }
        return 0;
    }

    @Test
    public void test() {
        String str1, str2, str3, str4;
        System.out.println(str1=strCount("aaabbadddffx"));
        System.out.println(str2=strCount("a"));
        System.out.println(str3= strCount("aaad"));
        System.out.println(str4= strCount("ax"));

        System.out.println(charOfStrings(str1,0));
        System.out.println(charOfStrings(str1,1));
        System.out.println(charOfStrings(str1,2));
        System.out.println(charOfStrings(str1,3));
        System.out.println(charOfStrings(str1,4));
        System.out.println(charOfStrings(str1,5));
        System.out.println(charOfStrings(str1,6));
        System.out.println(charOfStrings(str1,7));
        System.out.println(charOfStrings(str1,8));

        System.out.println(charOfStrings("a_1_b_100",1));
    }
}
