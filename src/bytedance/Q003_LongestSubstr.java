package bytedance;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * created by Ethan-Walker on 2019/2/1
 */
public class Q003_LongestSubstr {

    /**
     * 方法一: 嵌套循环
     * 时间复杂度: O(n^3)
     * 空间复杂度: O(1)
     *
     * @param str
     * @return
     */
    public int longestSubStr(String str) {
        if (str == null) return 0;
        int longest = 0;
        boolean in = true;
        for (int i = 0; i < str.length() - longest; i++) {
            for (int j = i; j < str.length(); j++) {
                // str[i..j-1]  已经加入
                // 加入 str[j] 判断是否和 str[i..j-1] 中有重复的字符
                int k;
                for (k = i; k < j && str.charAt(k) != str.charAt(j); k++) {
                }
                if (k == j) {
                    in = true;
                } else {
                    in = false;
                }
                if (in) {
                    // str[j]
                    if (j - i + 1 > longest) {
                        longest = j - i + 1;
                    }
                } else {
                    // str[j] 加入失败，选定下一个 i
                    break;
                }
            }
        }
        return longest;

    }

    /**
     * 方法二: 集合
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)
     *
     * @param str
     * @return
     */
    public int longestSubStr2(String str) {
        if (str == null || str.length() == 0) return 0;
        int longest = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length() - longest; i++) {
            for (int j = i; j < str.length(); j++) {
                // 尝试将str[j] 添加到集合中
                if (set.contains(str.charAt(j))) {
                    break;
                }
                set.add(str.charAt(j));
                if (j - i + 1 > longest) {
                    longest = j - i + 1;
                }
            }
            set.clear();
        }
        return longest;
    }

    /**
     * 方法三: 滑动窗口
     * 计算以 str[i]为开头的最长不重复子串, 判断 str[j] 是否在集合中
     * * 如果str[j] 在集合中，移除 str[i] ,i 前进一步，集合中只有 str[i+1..j-1]
     * * 如果str[j] 不在集合中，加入
     * 时间复杂度: O(2n)
     * 空间复杂度: O(n)
     *
     * @param str
     * @return
     */
    public int longestSubStr3(String str) {

        if (str == null || str.length() == 0) return 0;
        int longest = 0;
        Set<Character> set = new HashSet<>();

        int i = 0, j = 0;
        while (i < str.length() && j < str.length()) {
            if (set.contains(str.charAt(j))) {
                set.remove(str.charAt(i++));
            } else {
                set.add(str.charAt(j));
                if (j - i + 1 > longest) {
                    longest = j - i + 1;
                }
                j++;
            }
        }
        return longest;
    }

    /**
     * 方法四:
     * 思路和方法三类似，但是用 Map 记录每个字符对应的位置，且每个字符都添加到map中，不剔除
     * str[i]  ~ str[j-1] 表示当前子串
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     *
     * @param str
     * @return
     */
    public int longestSubStr4(String str) {
        if (str == null || str.length() == 0) return 0;
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();

        int i = 0, j = 0;
        while (i < str.length() && j < str.length()) {
            if (map.containsKey(str.charAt(j))) {
                int dupIndex = map.get(str.charAt(j));
                i = Math.max(dupIndex + 1, i);
                // dupIndex+1 < i 表示 str[dupIndex] 不在当前比较的子串范围中
                // dupIndex+1 > i 表示 str[dupIndex] 在当前比较的子串范围中，i前进到 dupIndex+1
            }
            // str[i]~str[j] 为子串
            map.put(str.charAt(j), j);
            if (j - i + 1 > longest) {
                longest = j - i + 1;
            }
            j++;
        }

        return longest;
    }

    @Test
    public void test() {
        System.out.println(longestSubStr4("abcabcbb"));
        System.out.println(longestSubStr4("bbbbbb"));
        System.out.println(longestSubStr4("pwwkew"));
        System.out.println(longestSubStr4(""));
        System.out.println(longestSubStr4(" "));
    }
}
