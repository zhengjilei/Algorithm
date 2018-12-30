package 模式匹配;

public class KMP {

    public static int[] getNext(String pattern) {
        int k = -1, j = 0; // next[0] 也可以不是 -1, 可以指定一个其他的特殊负值，但是为了统一方便处理 next[++j] = ++k
        int[] next = new int[pattern.length()];
        next[j] = k; // 初始化 next[0] =-1
        while (j < pattern.length() - 1) {
            // 求next[j+1]  即求 p0~pk-1
            if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) {
                // k==-1  1. 初始状态， 下一步 k++, j++;next[j+1] = 0
                //        2. 说明求 next[j+1] 找不到合适的 P0 ~ Ph-1 = Pj-h+1 ~ Pj,即找不到前缀等于以p[j]为后缀的字符串（连p[0] 都不等于 p[j]）
                //           所以 next[j+1] = k+1=0
                // P[k] = P[j] ，所以 next[j+1] = k+1
                next[++j] = ++k;
            } else {
                // p[k] != p[j], 要求next[j+1] 必须递推求 h使得 P0~Ph-1 = Pk-h ~Pk-1 =Pj-h ~Pj-1  然后比较 P[h] = P[j]
                k = next[k];
            }
        }
        return next;
    }

    public static int kmp(String s, String p) {

        if (s == null || p == null || s.length() < p.length()) return -1;
        if (p.length() == 0) return 0; // p="" 时，不管 s是什么，都返回 0

        int[] next = getNext(p);
        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                // j==-1 说明 next[0] = -1  说明之前j=0时比较了 s[0] 和 [j] ，但不等，然后 j = next[0] = -1,
                // 故i/j 都需要往后走一步（i++,j++）,模式字符串从头开始比较
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == p.length()) return i - p.length();
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(kmp("ababcdabcgefg", "abc"));
    }
}
