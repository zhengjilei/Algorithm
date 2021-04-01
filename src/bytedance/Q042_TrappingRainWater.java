package bytedance;

/**
 * created by Ethan-Walker on 2019/2/12
 */
public class Q042_TrappingRainWater {


    public int trap(int[] height) {
        int[] leftMax = new int[height.length]; // leftMax[i] = k 表示下标i的左边最大高度为 k
        int[] rightMax = new int[height.length];

        // leftMax[0] = 0, leftMax[i] = max(height[i-1],leftMax[i-1])
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i - 1], leftMax[i - 1]);
        }
        //rightMax[n-1] = 0
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i + 1], rightMax[i + 1]);
        }

        int result = 0;
        for (int i = 0; i < height.length; i++) {
            result += Math.max(0, Math.min(rightMax[i], leftMax[i]) - height[i]);
            // 可能会出现 Math.min(rightMax[i], leftMax[i]) <= height[i] 的情况, 即在下标i处不会积水
        }
        return result;

    }
}
