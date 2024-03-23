package leetcode2;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class Q0678 {
    public boolean checkValidString(String s) {
        if (s.length() == 0) return true;
        return isValid(s.toCharArray(), 0, 0);
    }

    /**
     * @param chs
     * @param index
     * @param countOfLeft 左括号的数目
     * @return
     */
    public boolean isValid(char[] chs, int index, int countOfLeft) {
        if (index == chs.length) {
            if (countOfLeft == 0)
                return true;
            return false;
        }
        if (chs[index] == '(') {
            return isValid(chs, index + 1, countOfLeft + 1);
        } else if (chs[index] == ')') {
            if (countOfLeft <= 0) return false;
            return isValid(chs, index + 1, countOfLeft - 1);
        } else {
            // *
            return isValid(chs, index + 1, countOfLeft + 1)
                    || isValid(chs, index + 1, countOfLeft)
                    || (countOfLeft > 0 && isValid(chs, index + 1, countOfLeft - 1));

        }

    }

    public boolean checkValidString2(String s) {
        ArrayDeque<Integer> leftIndex = new ArrayDeque<>();
        ArrayDeque<Integer> signIndex = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    leftIndex.push(i);
                    break;
                case '*':
                    signIndex.push(i);
                    break;
                case ')':
                    if (!leftIndex.isEmpty()) {
                        leftIndex.pop();
                    } else if (!signIndex.isEmpty()) {
                        signIndex.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        }

        while (!leftIndex.isEmpty() && !signIndex.isEmpty()) {
            if (leftIndex.pop() > signIndex.pop()) {
                return false;
            }
        }
        return leftIndex.isEmpty();

    }


}
