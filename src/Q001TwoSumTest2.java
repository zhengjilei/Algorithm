import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ethan-Walker on 2018/4/5.
 */
public class Q001TwoSumTest2 {

    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(null, null);
        hashMap.put(null, "dsadsa");
        Set<Map.Entry<Integer, String>> entries = hashMap.entrySet();

        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    @Test
    public void testA() {
        /*
          -2
          原码:   10000000 00000000 00000000 00000010
          反码:   11111111 11111111 11111111 11111101
          补码:   11111111 11111111 11111111 11111110
        -2<<1
          补码:   11111111 11111111 11111111 11111100
          反码:   11111111 11111111 11111111 11111011
          原码:   10000000 00000000 00000000 00000100
                    -4
        -2>>1  （负数右移高位补1)
           补码:  11111111 11111111 11111111 1111111
                    -1
        -2>>>1  无符号右移: 高位始终补0
           补码:  01111111 11111111 11111111 1111111
           反码:  01111111 11111111 11111111 1111110
           原码:  00000000 00000000 00000000 0000001
           2147483647
                1111111111111111111111111111111
                1000000000000000000000000000000
                1000000000000000000000000000001
         */
        System.out.println(-2 << 1); // -4
        System.out.println(-2 >> 1); // -1
        System.out.println(-2 >>> 1);   // 2147483647
        System.out.println(-3 >> 1); // -2
        Integer i = 2147483647;
//        1111111111111111111111111111111
        System.out.println(Integer.toBinaryString(i));
    }

    @Test
    public void testSwitch() {
        int t = 1;
        switch (t) {
            case 1:
            case 2:
            case 3:
                System.out.println("呵呵好吧");
            default:
                System.out.println("default");
        }

    }

}
