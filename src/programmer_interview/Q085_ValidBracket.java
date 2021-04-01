package programmer_interview;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/1/26
 */
public class Q085_ValidBracket {

    public boolean isValidBracket(String str) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isValidBracket2(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                sum++;
            } else if (s.charAt(i) == ')') {
                sum--;
            }
            if (sum < 0) return false;
        }
        return true;
    }

    public String longestValidStr(String s) {
        int maxLen = 0;
        int maxEndIndex = 0;

        int[] dp = new int[s.length()];
        int preIndex = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                preIndex = i - 1 - dp[i - 1];
                if (preIndex >= 0 && s.charAt(preIndex) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    if (preIndex - 1 > 0) {
                        dp[i] += dp[preIndex - 1];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxEndIndex = i;
            }
        }
        if (maxLen == 0) return "";
        return s.substring(maxEndIndex - maxLen + 1, maxEndIndex + 1);
    }

    @Test
    public void test() {
        System.out.println(longestValidStr("()()"));
        System.out.println(longestValidStr("()("));
        System.out.println(longestValidStr(")"));
        System.out.println(longestValidStr(")("));
        System.out.println(longestValidStr("(())"));
        System.out.println(longestValidStr("())"));
        System.out.println(longestValidStr("())("));
    }
}
