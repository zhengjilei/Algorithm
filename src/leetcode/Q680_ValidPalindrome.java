package leetcode;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q680_ValidPalindrome {


    public boolean validPalindrome(String s) {
        return isValid(s);
    }

    public boolean isValid(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else { // 遇到不同的，两种删除办法
                return isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1);
            }
        }
        return true;
    }
    // 判断 s[i...j] 是否是回文串
    public boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }


}
