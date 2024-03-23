package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q0049_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> res = new HashMap<>();
        char[] chs;
        String flag;
        for (String s : strs) {
            chs = s.toCharArray();
            Arrays.sort(chs);
            flag = String.valueOf(chs);
            if (!res.containsKey(flag)) {
                res.put(flag, new ArrayList<>());
            }
            res.get(flag).add(s);
        }
        return new ArrayList<>(res.values());
    }


    @Test
    public void test() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }
}
