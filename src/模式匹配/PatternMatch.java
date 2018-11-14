package 模式匹配;

public class PatternMatch {
    /**
     * 在字符串 target 中寻找 pattern ，返回第一个位置
     * O(n*m)
     * @param target
     * @param pattern
     * @return
     */
    public static int patternMatch(String target, String pattern) {
        int k;
        for (int j = 0; j < target.length(); j++) {
            for (k = 0; k < pattern.length(); k++) {
                if (target.charAt(j + k) != pattern.charAt(k)) break;
            }
            if (k == pattern.length()) return j;
        }
        return -1;
    }

    public static void main(String[] args) {

        System.out.println(patternMatch("abcdefg", "bd"));
        System.out.println(patternMatch("abcdefg", "bef"));
        System.out.println(patternMatch("abcdefg", "defg"));
        System.out.println(patternMatch("abcdefg", "ff"));
        System.out.println(patternMatch("abcdefg", "fg"));
    }
}
