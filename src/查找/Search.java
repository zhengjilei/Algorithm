package 查找;

/**
 * Created by EthanWalker on 2017/12/8.
 */
public final class Search {
    /**
     * Find the max element in array a.
     *
     * @param a array contain n elements
     * @return the max value found in a.
     */
    public static int maxElement(int[] a) {
//		return sequentialSearchMax(a);
        return divideSearchMax(a, 0, a.length - 1);
    }

    private static int sequentialSearchMax(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    /*
     * Find the max value in array a[first..max].
     * @param a array
	 * @param left low end of range.
     *              Requires 0 <= left <= right <= a.length-1.
     * @param right high end of range.
     *              Requires 0 <= left <= right <= a.length-1.
     * @return max value in a
     */
    private static int divideSearchMax(int[] a, int left, int right) {
        if (left == right) return a[left];
        if (right - left == 1) {
            return Math.max(a[left], a[right]);
        }
        int mid = (left + right) / 2;
        int leftMax = divideSearchMax(a, left, mid);
        int rightMax = divideSearchMax(a, mid + 1, right);

        return Math.max(leftMax, rightMax);
    }

    /**
     * Find the first occurrence of x in sorted array a.
     *
     * @param a array sorted in increasing order (a[0] <= a[1] <= ... <= a[n-1])
     * @param x value to find
     * @return lowest i such that a[i]==x, or -1 if x not found in a.
     */
    public static int search(int[] a, int x) {

//		 return sequentialSearch(a, x);
        return binarySearch(a, x, 0, a.length - 1);
//		 return binarySearch(a, x);
    }

    /**
     * 顺序查找算法实现
     *
     * @param a 有n个元素的数组
     * @param x 待查找键值
     * @return 第一个值等于x的元素位置i，a[i]=x；如果a中不存在x，则返回-1.
     */
    public static int sequentialSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (x == a[i]) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Find the first occurrence of x in sorted array a[first..max].
     * @param x value to find
     * @param a array sorted in increasing order
     *          (a[0] <= a[1] <= ... <= a[n-1])
     * @param first low end of range.
     *              Requires 0 <= first <= a.length-1.
     * @param max high end of range.
     *              Requires 0 <= max <= a.length-1.
     * @return lowest i such that first<=i<=max and a[i]==x,
     *         or -1 if there's no such i.
     */
    public static int binarySearch(int[] a, int x, int left, int right) {
        if (left > right) return -1;
        int mid = (left + right) / 2;
        if (x == a[mid]) {
            while (true) {
                if (mid == 0) break;
                if (a[mid - 1] == x) {
                    mid--;
                } else {
                    break;
                }
            }
            return mid;
        }
        if (x > a[mid]) {
            return binarySearch(a, x, mid + 1, right);
        } else {
            return binarySearch(a, x, left, mid - 1);
        }
    }

    /**
     * Same as binarySearch(int[] a, int x, int left, int right)
     */
    public static int binarySearch(int[] a, int x) {

        int left = 0, right = a.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (x == a[mid]) {
                while (true) {
                    if (mid == 0) break;
                    if (a[mid - 1] == x) {
                        mid--;
                    } else {
                        break;
                    }
                }
                return mid;
            }
            if (x > a[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] a = {3, 5, 0, 8, 8, -1, 15};
        int x = 8;

        System.out.print("Array: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
//		System.out.println("Search for: " + x);
        System.out.println("Result index: " + search(a, x));

        System.out.println("The max value in a: " + maxElement(a));
    }
}
