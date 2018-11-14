package 模式匹配;

public class KMP {

    public static int[] getNext(String pattern) {
        int k = -1, j = 0;
        int[] next = new int[pattern.length()];
        next[j] = k; // 初始化 next[0] =-1
        while (j < pattern.length() - 1) {
            // 求next[j+1]
            if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) {
                // k==-1  1. 初始状态，下一步 k++, j++;next[1] = 0
                //        2. 说明求 next[j+1] 找不到合适的 P0 ~ Ph-1 = Pj-h ~ Pj-1, 故 pattern 应该从 0 开始重新比较，next[j+1] = k+1=0

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
        int[] next = getNext(p);
        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                // j==-1 说明 next[0] = -1, 或者未找到 满足条件的 h 使得 P0~Ph-1 = Pk-h~Ph-1 = Pj-h~Pj-1d
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
