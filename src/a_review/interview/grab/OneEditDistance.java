package a_review.interview.grab;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/18
 */
public class OneEditDistance {
    /**
     * 1.长度之差 >1 : false;
     * 2.长度相等: 编辑有且仅有一次，注意：编辑次数为 0 也返回 false
     * 3.长度之差 = 1, 让s1 指向长字符串，s2 指向短字符串. 不存在编辑次数为  0 的可能性。
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean oneEdit(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        int diff = Math.abs(s1.length() - s2.length());
        if (diff > 1) return false;
        boolean hasEdit = false; // 判断是否编辑过
        int i = 0, j = 0;
        if (diff == 0) {
            while (i < s1.length()) {
                if (s1.charAt(i) != s2.charAt(j)) {
                    if (hasEdit) {
                        return false;
                    } else {
                        // 替换位置 i 的字符
                        hasEdit = true;
                    }
                }
                i++;
                j++;
            }
            if (!hasEdit) return false; // 两个字符串完全相等，编辑距离为 0
        } else {

            if (s1.length() < s2.length()) {
                String tmp = s1;
                s1 = s2;
                s2 = tmp;
            }
            //s1 指向更长的字符串，s2 指向短字符串
            while (j < s2.length()) { // 只需要判断 j 是否溢出即可,s2 更短
                if (s1.charAt(i) != s2.charAt(j)) {
                    if (hasEdit) {
                        return false;
                    } else {        // 删除位置 i 的字符
                        hasEdit = true;
                        i++;
                    }
                } else {
                    i++;
                    j++;
                }
            }
            // j ==s2.length 时，如果i=s1.length-1 ，表示前面都匹配，s1 只需要删除最后一个字符即可
        }
        return true;

    }


    @Test
    public void test() {
        //字符串长度之差 = 1
        System.out.println(oneEdit("abc", "ac"));
        System.out.println(oneEdit("ac", "acb"));

        // 字符串长度之差 >1
        System.out.println(oneEdit("a", "acb"));

        // 字符串长度相等
        System.out.println(oneEdit("adb", "acb"));
        System.out.println(oneEdit("adb", "adb"));
        System.out.println(oneEdit("abd", "acd"));

        System.out.println("-----------------------");
        //字符串长度之差 = 1
        System.out.println(oneEditReview("abc", "ac"));
        System.out.println(oneEditReview("ac", "acb"));

        // 字符串长度之差 >1
        System.out.println(oneEditReview("a", "acb"));

        // 字符串长度相等
        System.out.println(oneEditReview("adb", "acb"));
        System.out.println(oneEditReview("adb", "adb"));
        System.out.println(oneEditReview("abd", "acd"));
    }


    public boolean oneEditReview(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        int s1Len = s1.length();
        int s2Len = s2.length();
        int diff = Math.abs(s1Len - s2Len);
        if (diff > 1) return false;
        boolean hasEdit = false;
        if (diff == 0) {
            for (int i = 0; i < s1Len; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    if (hasEdit) return false;
                    else {
                        hasEdit = true;
                    }
                }
            }
            if (!hasEdit) return false;
        } else {
            if (s1Len < s2Len) {
                String tmp = s1;
                s1 = s2;
                s2 = tmp;
                s1Len = s1.length();
                s2Len = s2.length();
            }
            int i = 0, j = 0;
            while (j < s2Len) {
                if (s1.charAt(i) != s2.charAt(j)) {
                    if (hasEdit) {
                        return false;
                    } else {
                        hasEdit = true;
                        i++;
                    }
                } else {
                    i++;
                    j++;
                }
            }
        }
        return true;
    }


}
