package leetcode;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q0125_IsPalind {
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        s = s.trim();
        if (s.length() == 0) return true;
        int left = 0, right = s.length() - 1;

        while (left <= right) {
            while (left <= right && !isLetter(s.charAt(left))) left++;
            while (left <= right && !isLetter(s.charAt(right))) right--;

            if (left < right) {
                if (!s.substring(left, left + 1).equalsIgnoreCase(s.substring(right, right + 1))) {
                    return false;
                }
            }
            left++;
            right--;
        }

        return true;
    }

    boolean isLetter(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9';
    }
}
