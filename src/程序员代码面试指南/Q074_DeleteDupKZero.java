package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/12
 */
public class Q074_DeleteDupKZero {

    public String deleteDupKZero(String str, int k) {
        int zeroCount = 0;
        int cnt = 0;
        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '0') {
                zeroCount++;
            } else {
                if (zeroCount != k) {
                    while (zeroCount > 0) {
                        chs[cnt++] = '0';
                        zeroCount--;
                    }
                }
                zeroCount = 0;
                chs[cnt++] = chs[i];
            }
        }
        if (zeroCount != k) {
            while (zeroCount > 0) {
                chs[cnt++] = '0';
                zeroCount--;
            }
        }
        return String.valueOf(chs, 0, cnt);
    }


    @Test
    public void test(){
        String str1 = "A00B";
        String str2= "A0000B000";
        String str3= "0000B000";
        String str4= "000B000";
        String str5= "000000";

        System.out.println(deleteDupKZero(str1,2));
        System.out.println(deleteDupKZero(str2,3));
        System.out.println(deleteDupKZero(str3,3));
        System.out.println(deleteDupKZero(str3,4));
        System.out.println(deleteDupKZero(str4,3));
        System.out.println(deleteDupKZero(str5,6));
    }
}
