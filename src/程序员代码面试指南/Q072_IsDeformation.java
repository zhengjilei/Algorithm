package 程序员代码面试指南;

/**
 * created by Ethan-Walker on 2019/1/11
 */
public class Q072_IsDeformation {


    public boolean isDeformation(String str1, String str2) {
        int[] a = new int[256];
        for (int i = 0; i < str1.length(); i++) {
            a[str1.charAt(i)]++;
        }
        for (int i = 0; i < str2.length(); i++) {
            if (a[str2.charAt(i)]-- == 0) {
                return false;
            }
        }
        return true;
    }
}
