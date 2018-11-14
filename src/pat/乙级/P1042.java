package pat.乙级;

import java.util.*;

/**
 * Created by lenovo on 2018/4/22.
 */
public class P1042 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(s);
        HashMap<Character, Integer> hashMap = new HashMap<>();

        char[] chars = s.toCharArray();
        for (char b : chars) {
            if (b >= 'A' && b <= 'Z') {
                b = (char) (b + 32);
            }
            if (b >= 'a' && b <= 'z') {
                Integer integer = hashMap.get(b);
                if (integer != null && integer > 0) {
                    hashMap.put((char) b, integer + 1);
                } else {
                    hashMap.put((char) b, 1);
                }
            }
        }

        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return o1.getKey() - o2.getKey();
                }
                return o2.getValue() - o1.getValue();
            }
        });
        int maxCount = list.get(0).getValue();
        char maxChar = list.get(0).getKey();

        System.out.println(maxChar + " " + maxCount);
    }
}
