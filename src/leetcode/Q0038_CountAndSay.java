package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q0038_CountAndSay {
    public String countAndSay(int n) {
        String a = "1";
        int cnt = 1;
        while (cnt < n) {
            a = getNext(a);
            cnt++;
        }
        return a;

    }

    public String getNext(String str) {
        int prevIndex = 0;
        int curIndex = 1;
        StringBuilder sb = new StringBuilder();
        while (prevIndex < str.length()) {
            while (curIndex < str.length() && str.charAt(prevIndex) == str.charAt(curIndex)) {
                curIndex++;
            }
            sb.append(curIndex - prevIndex).append(str.charAt(prevIndex));
            prevIndex = curIndex;
            curIndex++;
        }

        return sb.toString();
    }


    @Test
    public void test() {
        System.out.println(countAndSay(1));
        System.out.println(countAndSay(2));
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));
        System.out.println(countAndSay(6));
        System.out.println(countAndSay(7));
        System.out.println(countAndSay(8));
        System.out.println(countAndSay(9));
        System.out.println(countAndSay(10));
    }

}
