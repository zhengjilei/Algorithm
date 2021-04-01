package lca;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * wrong answer
 * created by Ethan-Walker on 2018/12/31
 */
public class HDU4547 {


    // 时间复杂度: O(m*h)  空间复杂度: O(h)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int n, m;
        String str1, str2;

        HashMap<String, String> parentMap = new HashMap<>();

        HashMap<String, String> stringMap = new HashMap<>(); // 保证各个相同的字符串能取得相同的对象
        while (t > 0) {

            n = scanner.nextInt();
            m = scanner.nextInt();
            parentMap.clear();
            stringMap.clear();

            for (int i = 0; i < n - 1; i++) {
                str1 = scanner.next();
                str2 = scanner.next();
                stringMap.putIfAbsent(str1, str1);
                stringMap.putIfAbsent(str2, str2);
                // 防止上一轮循环中 str1= A str2 =B 和这一轮中的 str11 = B str2 =C  中的 B 取得的不是一个对象
                parentMap.put(stringMap.get(str1), stringMap.get(str2)); // 从 stringMap 中取，而不是直接是 str1 str2
            }

            for (int i = 0; i < m; i++) {
                str1 = scanner.next();
                str2 = scanner.next();
                System.out.println(cal(parentMap, stringMap.get(str1), stringMap.get(str2)));
            }
            t--;
        }
    }

    // 因为要求从 str1 切换到 str2 的步数，故不能折叠路径

    // 时间复杂度: O(h)
    public static int cal(HashMap<String, String> parentMap, String str1, String str2) {

        if (str1.equals(str2)) return 0;
        String tempStr1 = str1, tempStr2 = str2;

        int length1 = 1;
        // 先求 str1 str2 的最近公共节点
        while (parentMap.containsKey(tempStr1)) {
            tempStr1 = parentMap.get(tempStr1);
            length1++;
        }

        // tempStr1 指向根节点

        int length2 = 1;
        while (parentMap.containsKey(tempStr2)) {
            tempStr2 = parentMap.get(tempStr2);
            length2++;
        }
        int fastStep = Math.abs(length1 - length2);


        String fast = str1, slow = str2;
        if (length2 > length1) {
            fast = str2;
            slow = str1;
        }


        int count = 0;
        while (fastStep > 0) {
            fast = parentMap.get(fast);
            fastStep--;
            count++;
        }


        while (fast != slow) {
            fast = parentMap.get(fast);
            slow = parentMap.get(slow);
            count++;
        }
        if (fast.equals(str1)) {
            // 最近公共祖先是 str1  ,str1-> str2 只需要一步
            return 1;
        } else if (fast.equals(str2)) {
            return count;
        }
        return count + 1;


    }

    @Test
    public void test() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("dsa");
        hashSet.add("dsa");

        String a = new String("dsa");
        hashSet.add(a);

        hashSet.forEach(e -> System.out.println(e));

        String b = new String("dsa");
        System.out.println(hashSet.contains(b));
    }


}
