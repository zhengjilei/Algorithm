package 排序;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by EthanWalker on 2017/12/6.
 */
public class Sort {

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] a) {
        int temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j + 1] < a[j]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        int i, j, tmp;
        for (i = 1; i < a.length; i++) {
            tmp = a[i];
            for (j = i - 1; j >= 0 && a[j] > tmp; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = tmp;
        }
    }


    /**
     * 选取中位数做 pivot
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static int median3(int[] a, int left, int right) {
        int mid = (left + right) / 2;
        if (a[left] > a[mid]) swap(a, mid, left);
        if (a[left] > a[right]) swap(a, left, right);
        if (a[mid] > a[right]) swap(a, mid, right);
        // a[left]<=a[mid]<=a[right]

        //选取中位数  A[mid] 为 pivot
        swap(a, mid, right - 1);  //将 pivot 藏到倒数第二位（最后一位一定比 pivot 大）
        return a[right - 1];
    }

    /**
     * 快排
     *
     * @param a
     * @param left
     * @param right
     */
    public static void quickSort(int[] a, int left, int right) {

        if (left < right) {
            int pivot = median3(a, left, right);
            int l = left - 1;
            int r = right - 1;
            for (; ; ) {

                while (a[++l] < pivot) {
                }
                if (r == 0) break;
                while (a[--r] > pivot) {
                }
                if (l < r) {
                    swap(a, l, r);
                } else {
                    break;
                }
            }
            if (right - 1 >= 0) {
                swap(a, l, right - 1); // 将中位数 pivot 交换到 l 位置
            }

            quickSort(a, left, l - 1);
            quickSort(a, l + 1, right);

        }
    }

    /**
     * 交换数组中两个数
     *
     * @param a
     * @param m
     * @param n
     */
    public static void swap(int[] a, int m, int n) {
        int t = a[m];
        a[m] = a[n];
        a[n] = t;
    }


    /**
     * 合并两个数组
     *
     * @param arr
     * @param temp
     * @param lStart
     * @param rStart
     * @param rEnd
     */
    public static void merge(int[] arr, int[] temp, int lStart, int rStart, int rEnd) {
        int lEnd = rStart - 1;
        int num = rEnd - lStart + 1; // 处理数据的总数
        int t = lStart;

        while (lStart <= lEnd && rStart <= rEnd) {
            if (arr[lStart] < arr[rStart]) {
                temp[t++] = arr[lStart++];
            } else {
                temp[t++] = arr[rStart++];
            }
        }
        while (lStart <= lEnd) {
            temp[t++] = arr[lStart++];
        }
        while (rStart <= rEnd) {
            temp[t++] = arr[rStart++];
        }

        for (int i = 0; i < num; i++, rEnd--) {
            arr[rEnd] = temp[rEnd];
        }
    }

    /**
     * 归并排序
     *
     * @param arr
     * @param temp
     * @param start
     * @param end
     */
    public static void mSort(int[] arr, int[] temp, int start, int end) {
        int mid;
        if (start < end) {
            mid = (start + end) / 2;
            mSort(arr, temp, start, mid);
            mSort(arr, temp, mid + 1, end);
            merge(arr, temp, start, mid + 1, end);
        }
    }


    /**
     * 边界测试
     */
    @Test
    public void testBorderSort() {
        int[] a = new int[]{2147483647, -2147483648, -1, 0, 1, 434, -434};
        int[] b = new int[]{2147483647, -2147483648, -1, 0, 1, 434, -434};

        bubbleSort(a);
        System.out.println("bubbleSort:  " + Arrays.toString(a));
        copy(a, b);

        insertSort(a);
        System.out.println("insertSort:  " + Arrays.toString(a));
        copy(a, b);

        int[] temp = new int[a.length];
        mSort(a, temp, 0, a.length - 1);
        System.out.println("mergeSort:   " + Arrays.toString(a));
        copy(a, b);

        quickSort(a, 0, a.length - 1);
        System.out.println("quickSort:   " + Arrays.toString(a));
        copy(a, b);

    }

    /**
     * 正序测试
     */
    @Test
    public void testSeqSort() {
        int[] a = new int[]{-212, -32, -10, 0, 7, 11, 32, 100, 432, 1324};
        int[] b = new int[]{-212, -32, -10, 0, 7, 11, 32, 100, 432, 1324};
        bubbleSort(a);
        System.out.println("bubbleSort:  " + Arrays.toString(a));
        copy(a, b);

        insertSort(a);
        System.out.println("insertSort:  " + Arrays.toString(a));
        copy(a, b);

        int[] temp = new int[a.length];
        mSort(a, temp, 0, a.length - 1);
        System.out.println("mergeSort:   " + Arrays.toString(a));
        copy(a, b);

        quickSort(a, 0, a.length - 1);
        System.out.println("quickSort:   " + Arrays.toString(a));
        copy(a, b);

    }

    /**
     * 逆序测试
     */
    @Test
    public void testInverseSeqSort() {
        int[] a = new int[]{1324, 432, 100, 32, 11, 7, 0, -10, -32, -212};
        int[] b = new int[]{1324, 432, 100, 32, 11, 7, 0, -10, -32, -212};
        bubbleSort(a);
        System.out.println("bubbleSort:  " + Arrays.toString(a));
        copy(a, b);

        insertSort(a);
        System.out.println("insertSort:  " + Arrays.toString(a));
        copy(a, b);

        int[] temp = new int[a.length];
        mSort(a, temp, 0, a.length - 1);
        System.out.println("mergeSort:   " + Arrays.toString(a));
        copy(a, b);

        quickSort(a, 0, a.length - 1);
        System.out.println("quickSort:   " + Arrays.toString(a));
        copy(a, b);

    }

    /**
     * 乱序测试
     */
    @Test
    public void testSort() {
        int[] a = new int[]{12, 54, 3, 5, 2, 1, 4345, 21, 0, 4, 45};
        int[] b = new int[]{12, 54, 3, 5, 2, 1, 4345, 21, 0, 4, 45};
        bubbleSort(a);
        System.out.println("bubbleSort:  " + Arrays.toString(a));
        copy(a, b);

        insertSort(a);
        System.out.println("insertSort:  " + Arrays.toString(a));
        copy(a, b);

        int[] temp = new int[a.length];
        mSort(a, temp, 0, a.length - 1);
        System.out.println("mergeSort:   " + Arrays.toString(a));
        copy(a, b);

        quickSort(a, 0, a.length - 1);
        System.out.println("quickSort:   " + Arrays.toString(a));
        copy(a, b);
    }


    /**
     * 测试相等数据
     */
    @Test
    public void testRepeatSort() {
        int[] a = new int[]{12, 54, 54, 54, 12, 1, 11, 0, 0, 4, 45, 33, 33, 3, 0, 4, 11};
        int[] b = new int[]{12, 54, 54, 54, 12, 1, 11, 0, 0, 4, 45, 33, 33, 3, 0, 4, 11};
        bubbleSort(a);
        System.out.println("bubbleSort:  " + Arrays.toString(a));
        copy(a, b);

        insertSort(a);
        System.out.println("insertSort:  " + Arrays.toString(a));
        copy(a, b);

        int[] temp = new int[a.length];
        mSort(a, temp, 0, a.length - 1);
        System.out.println("mergeSort:   " + Arrays.toString(a));
        copy(a, b);

        quickSort(a, 0, a.length - 1);
        System.out.println("quickSort:   " + Arrays.toString(a));
        copy(a, b);

    }


    public static void copy(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] = b[i];
        }
    }

    int[] arr11 = new int[100];      // 100乱序
    int[] arr12 = new int[100];     // 100 逆序
    int[] arr13 = new int[100];     // 100顺序

    int[] arr21 = new int[1000];
    int[] arr22 = new int[1000];
    int[] arr23 = new int[1000];

    int[] arr31 = new int[5000];
    int[] arr32 = new int[5000];
    int[] arr33 = new int[5000];

    int[] arr41 = new int[10000];
    int[] arr42 = new int[10000];
    int[] arr43 = new int[10000];

    int[] arr51 = new int[50000];
    int[] arr52 = new int[50000];
    int[] arr53 = new int[50000];

    int[] arr61 = new int[100000];
    int[] arr62 = new int[100000];
    int[] arr63 = new int[100000];

    @Before
    public void init100() {
        try {
            FileInputStream fis21 = new FileInputStream("1000乱序.txt");
            byte[] bys11 = new byte[1024];
            ArrayList<Byte> arrayList = new ArrayList<>();
            int length = -1;
            while ((length = fis21.read(bys11)) != -1) {
                for (int i = 0; i < length; i++) {
                    arrayList.add(bys11[i]);
                }
            }
            fis21.close();
            byte[] byFinal = new byte[arrayList.size()];
            for (int i = 0; i < byFinal.length; i++) {
                byFinal[i] = arrayList.get(i);
            }

            String str = new String(byFinal).trim();
            String[] nums = str.split(" ");

            for (int i = 0; i < nums.length; i++) {
                arr21[i] = Integer.valueOf(nums[i]);
            }

            //
            FileInputStream fis22 = new FileInputStream("1000逆序.txt");
            byte[] bys12 = new byte[1024];
            ArrayList<Byte> arrayList12 = new ArrayList<>();
            length = -1;
            while ((length = fis22.read(bys12)) != -1) {
                for (int i = 0; i < length; i++) {
                    arrayList12.add(bys12[i]);
                }
            }
            fis22.close();
            byte[] byFinal12 = new byte[arrayList12.size()];
            for (int i = 0; i < byFinal12.length; i++) {
                byFinal12[i] = arrayList12.get(i);
            }

            String str12 = new String(byFinal12).trim();
            String[] nums12 = str12.split(" ");

            for (int i = 0; i < nums12.length; i++) {
                arr22[i] = Integer.valueOf(nums12[i]);
            }
            //
            FileInputStream fis13 = new FileInputStream("1000顺序.txt");
            byte[] bys13 = new byte[1024];
            ArrayList<Byte> arrayList13 = new ArrayList<>();
            length = -1;
            while ((length = fis13.read(bys13)) != -1) {
                for (int i = 0; i < length; i++) {
                    arrayList13.add(bys13[i]);
                }
            }
            fis13.close();
            byte[] byFinal13 = new byte[arrayList13.size()];
            for (int i = 0; i < byFinal13.length; i++) {
                byFinal13[i] = arrayList13.get(i);
            }

            String str13 = new String(byFinal13).trim();
            String[] nums13 = str13.split(" ");

            for (int i = 0; i < nums13.length; i++) {
                arr23[i] = Integer.valueOf(nums13[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试排序的耗时
     */
    @Test
    public void testBubbleSort() {

        long start = System.currentTimeMillis();
//        bubbleSort(arr61);
//        insertSort(arr21);
//        quickSort(arr21,0,arr21.length-1);
        mSort(arr21, new int[arr21.length], 0, arr21.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("乱序: " + (end - start));

        start = System.currentTimeMillis();
//        bubbleSort(arr62);
//        insertSort(arr22);
//        quickSort(arr22,0,arr22.length-1);
        mSort(arr22, new int[arr22.length], 0, arr22.length - 1);
        end = System.currentTimeMillis();
        System.out.println("逆序: " + (end - start));

        start = System.currentTimeMillis();
//        bubbleSort(arr63);
//        insertSort(arr23);
//        quickSort(arr23,0,arr23.length-1);
        mSort(arr23, new int[arr23.length], 0, arr23.length - 1);
        end = System.currentTimeMillis();
        System.out.println("顺序: " + (end - start));


    }

}
