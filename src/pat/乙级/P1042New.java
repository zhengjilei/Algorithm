package pat.乙级;

import java.util.Scanner;

/**
 * Created by lenovo on 2018/4/22.
 */
public class P1042New {
    public static void main(String[] args) {
        int[] count = new int[26];
        for (int i = 0; i < 26; i++) {
            count[i] = 0;
        }
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c >= 'a' && c <= 'z') {
                count[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                count[c - 'A']++;
            }
        }
        int maxCount = 0;
        int index = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
                index = i;
            }
        }
        System.out.println((char) (index + 'a') + " " + maxCount);
    }
}
