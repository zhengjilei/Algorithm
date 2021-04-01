package kth;

import org.junit.Test;

import java.util.*;

/**
 * 找出数据流中的最小的前k个元素
 * 堆实现
 * 时间复杂度: O(nlogk)
 * 空间复杂度: O(k)
 * Created by Ethan-Walker on 2018/5/14.
 */
public class TopKMinInStream {

    private static int[] heap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();//数组总数
        int k = scanner.nextInt(); // top k
        heap = new int[n];
        for (int i = 0; i < n; i++) {
            heap[i] = scanner.nextInt();
        }
        getKthMin(heap, k);
        for (int i = 0; i < k; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    /**
     * 建大顶堆
     * 获取数组 arr 中的最小的 top k 个
     * 堆顶元素为第 k 小的（最大堆，堆顶元素是最小的k个元素中的最大值）
     * <p>
     * 时间复杂度: O(nlogk)
     * 空间复杂度: O(1)
     *
     * @param arr
     * @param k
     */
    public static void getKthMin(int[] arr, int k) {
        buildMaxHeap(arr, k); // 前 k 个数建最大堆
        // 遍历剩余 n-k+1 个数，比堆顶小，替换堆顶元素，重新下沉调整堆
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < arr[0]) {
                arr[0] = arr[i];
                siftDown(arr, 0, k - 1);
            }
        }
        // 最终得到的第 0 ~ k-1 个数为 最小的 k 个数
    }

    /**
     * 对数组 arr 的前 k 个数建最大堆
     *
     * @param arr
     * @param k
     */
    public static void buildMaxHeap(int[] arr, int k) {
        int startPos = (k - 2) / 2;
        while (startPos >= 0) {
            siftDown(arr, startPos, k - 1);
            startPos--;
        }
    }

    /**
     * @param arr        待调整数组
     * @param startIndex 开始调整索引
     * @param endIndex   堆中最后一个元素索引
     */
    public static void siftDown(int[] arr, int startIndex, int endIndex) {
        int i = startIndex, j = i * 2 + 1; // i为父节点，j为左子节点
        while (j <= endIndex) {
            if (j < endIndex && arr[j + 1] > arr[j]) j++;
            if (arr[i] < arr[j]) {
                // 交换 arr[i] 、arr[j]
                arr[j] = arr[i] + arr[j] - (arr[i] = arr[j]);
                i = j;
                j = 2 * i + 1;
            } else {
                break;
            }
        }
    }

    /**
     * 使用jdk PriorityQueue(底层是堆，默认最小堆)实现 top k
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] findKthMin(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 建大顶堆
                return o2 - o1;
            }
        });
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                priorityQueue.offer(arr[i]);
            } else {
                if (arr[i] < priorityQueue.peek()) {
                    // 弹出最大堆 堆顶元素，插入新元素
                    priorityQueue.poll();
                    priorityQueue.offer(arr[i]);
                }
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll();
        }
        return result;
    }

    @Test
    public void testPriority() {
        Random random = new Random();

        int n = random.nextInt(20);
        int[] arr = new int[n];
        int k = random.nextInt(n);
        if (k == 0) k++;
        System.out.println("n==" + n + ",k=" + k);
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(20);
        }
        System.out.println(Arrays.toString(arr) + "\n");

        int[] kthMin = findKthMin(arr, k);
        System.out.println(Arrays.toString(kthMin));
    }

}
/*
10 3
100 38 50 51 49 46 61 68 69 85
*/
