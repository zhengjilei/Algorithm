package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/1/26
 */
public class Q017_NumberSet {


    HashMap<Integer, Character[]> numberChars = new HashMap<>();

    public void init() {
        numberChars.put(2, new Character[]{'a', 'b', 'c'});
        numberChars.put(3, new Character[]{'d', 'e', 'f'});
        numberChars.put(4, new Character[]{'g', 'h', 'i'});
        numberChars.put(5, new Character[]{'j', 'k', 'l'});
        numberChars.put(6, new Character[]{'m', 'n', 'o'});
        numberChars.put(7, new Character[]{'p', 'q', 'r', 's'});
        numberChars.put(8, new Character[]{'t', 'u', 'v'});
        numberChars.put(9, new Character[]{'w', 'x', 'y', 'z'});
    }


    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        List<String> res = new ArrayList<>();
        if(n==0) return res;
        init();
        char[] chs = new char[n];
        generate(chs, 0, digits, res);
        return res;
    }

    public void generate(char[] chs, int index, String digits, List<String> res) {
        if (digits.length() == index) {
            res.add(String.valueOf(chs));
            return;
        }
        int num = digits.charAt(index) - '0';
        Character[] characters = numberChars.get(num);
        for (int i = 0; i < characters.length; i++) {
            chs[index] = characters[i];
            generate(chs, index + 1, digits, res);
        }

    }
}
