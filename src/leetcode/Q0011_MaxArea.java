package leetcode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q0011_MaxArea {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        int area = 0;
        while (left < right) {
            area = Math.min(height[left], height[right]) * (right - left);
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;

    }
}
