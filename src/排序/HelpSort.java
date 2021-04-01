package 排序;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by EthanWalker on 2017/12/9.
 */
public class HelpSort {
    public static void main(String[] args) {
        Random random = new Random();
        Integer[] arr1 = new Integer[100];
        Integer[] arr2 = new Integer[1000];
        Integer[] arr3 = new Integer[5000];
        Integer[] arr4 = new Integer[10000];
        Integer[] arr5 = new Integer[50000];
        Integer[] arr6 = new Integer[100000];
        try {
            FileOutputStream fos11 = new FileOutputStream("100顺序.txt");
            FileOutputStream fos12 = new FileOutputStream("100逆序.txt");
            FileOutputStream fos13 = new FileOutputStream("100乱序.txt");
            FileOutputStream fos21 = new FileOutputStream("1000顺序.txt");
            FileOutputStream fos22 = new FileOutputStream("1000逆序.txt");
            FileOutputStream fos23 = new FileOutputStream("1000乱序.txt");
            FileOutputStream fos31 = new FileOutputStream("5000顺序.txt");
            FileOutputStream fos32 = new FileOutputStream("5000逆序.txt");
            FileOutputStream fos33 = new FileOutputStream("5000乱序.txt");
            FileOutputStream fos41 = new FileOutputStream("10000顺序.txt");
            FileOutputStream fos42 = new FileOutputStream("10000逆序.txt");
            FileOutputStream fos43 = new FileOutputStream("10000乱序.txt");
            FileOutputStream fos51 = new FileOutputStream("50000顺序.txt");
            FileOutputStream fos52 = new FileOutputStream("50000逆序.txt");
            FileOutputStream fos53 = new FileOutputStream("50000乱序.txt");
            FileOutputStream fos61 = new FileOutputStream("100000顺序.txt");
            FileOutputStream fos62 = new FileOutputStream("100000逆序.txt");
            FileOutputStream fos63 = new FileOutputStream("100000乱序.txt");

            // 100乱序
            for (int i = 0; i < 100; i++) {
                arr1[i] = random.nextInt();
                fos13.write(arr1[i].toString().getBytes());
                fos13.write(" ".getBytes());
            }
            fos13.close();


            // 100 顺序
            Arrays.sort(arr1);
            for (int i = 0; i < 100; i++) {
                fos11.write(arr1[i].toString().getBytes());
                fos11.write(" ".getBytes());
            }
            fos11.close();
            Comparator<Integer> comparator = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            };
            // 100逆序
            Arrays.sort(arr1, comparator);
            for (int i = 0; i < 100; i++) {
                fos12.write(arr1[i].toString().getBytes());
                fos12.write(" ".getBytes());
            }
            fos12.close();
//========================================

            // 1000乱序
            for (int i = 0; i < 1000; i++) {
                arr2[i] = random.nextInt();
                fos23.write(arr2[i].toString().getBytes());
                fos23.write(" ".getBytes());
            }
            fos23.close();

            // 1000 顺序
            Arrays.sort(arr2);
            for (int i = 0; i < 1000; i++) {
                fos21.write(arr2[i].toString().getBytes());
                fos21.write(" ".getBytes());
            }
            fos21.close();

            // 1000 逆序
            Arrays.sort(arr2, comparator);

            for (int i = 0; i < 1000; i++) {
                fos22.write(arr2[i].toString().getBytes());
                fos22.write(" ".getBytes());
            }
            fos22.close();

//========================================
            // 5000 乱序
            for (int i = 0; i < 5000; i++) {
                arr3[i] = random.nextInt();
                fos33.write(arr3[i].toString().getBytes());
                fos33.write(" ".getBytes());
            }
            fos33.close();

            // 5000 顺序
            Arrays.sort(arr3);
            for (int i = 0; i < 5000; i++) {
                fos31.write(arr3[i].toString().getBytes());
                fos31.write(" ".getBytes());
            }
            fos31.close();

            // 5000 逆序
            Arrays.sort(arr3, comparator);
            for (int i = 0; i < 5000; i++) {
                fos32.write(arr3[i].toString().getBytes());
                fos32.write(" ".getBytes());
            }
            fos32.close();

//============================
            // 10000 乱序
            for (int i = 0; i < 10000; i++) {
                arr4[i] = random.nextInt();
                fos43.write(arr4[i].toString().getBytes());
                fos43.write(" ".getBytes());
            }
            fos43.close();

            // 10000 顺序
            Arrays.sort(arr4);
            for (int i = 0; i < 10000; i++) {
                fos41.write(arr4[i].toString().getBytes());
                fos41.write(" ".getBytes());
            }
            fos41.close();

            // 10000 逆序
            Arrays.sort(arr4, comparator);
            for (int i = 0; i < 10000; i++) {
                fos42.write(arr4[i].toString().getBytes());
                fos42.write(" ".getBytes());
            }
            fos42.close();
//=====================================
            // 50000 乱序
            for (int i = 0; i < 50000; i++) {
                arr5[i] = random.nextInt();
                fos53.write(arr5[i].toString().getBytes());
                fos53.write(" ".getBytes());
            }
            fos53.close();

            // 50000 顺序
            Arrays.sort(arr5);
            for (int i = 0; i < 50000; i++) {
                fos51.write(arr5[i].toString().getBytes());
                fos51.write(" ".getBytes());
            }
            fos51.close();

            // 50000 逆序
            Arrays.sort(arr5, comparator);
            for (int i = 0; i < 50000; i++) {
                fos52.write(arr5[i].toString().getBytes());
                fos52.write(" ".getBytes());
            }
            fos52.close();

//===============================

            // 100000 乱序
            for (int i = 0; i < 100000; i++) {
                arr6[i] = random.nextInt();
                fos63.write(arr6[i].toString().getBytes());
                fos63.write(" ".getBytes());
            }
            fos63.close();

            // 100000 顺序
            Arrays.sort(arr6);
            for (int i = 0; i < 100000; i++) {
                fos61.write(arr6[i].toString().getBytes());
                fos61.write(" ".getBytes());
            }
            fos61.close();

            // 100000 逆序
            Arrays.sort(arr6, comparator);
            for (int i = 0; i < 100000; i++) {
                fos62.write(arr6[i].toString().getBytes());
                fos62.write(" ".getBytes());
            }
            fos62.close();

        } catch (Exception e) {

        }

    }


}
