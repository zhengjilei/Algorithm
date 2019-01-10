package 程序员代码面试指南;

/**
 * created by Ethan-Walker on 2019/1/8
 */
public class Q069_JumpGame {


    /**
     * jump 表示当前到达位置i最少跳的步数
     * cur 表示跳了 jump 步最远到达的位置
     * next 表示跳 jump+1步最远到达的位置
     * <p>
     * 遍历每一个位置 i
     * 1. cur< i 跳 jump 步到达不了位置 i, 再跳一步，cur = next
     * 2. cur>= i,跳 jump 步能到达位置i
     * <p>
     * 每遍历一个位置，必须判断是否需要更新跳 jump+1 能到达的最远位置
     *
     * @param arr
     * @return
     */
    public int minStep(int[] arr) {

        int jump = 0, cur = 0, next = 0;

        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                if (next < i) { // 再跳一步最远仍然到达不了位置 i ,举例 3 2 1 0 5 3 ,i=3时，jump =1, cur=3, next=0
                    return -1;
                }
                jump++;
                cur = next;
            }
            next = Math.max(i + arr[i], next);
        }
        return jump;
    }

    /**
     * 判断是否可以跳跃到最后一个位置
     *
     * @return
     */
    public boolean canJump(int[] arr) {
        int jump = 0, cur = 0, next = 0;

        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                if (next < i) { // 再跳一步仍然到达不了
                    return false;
                }
                jump++;
                cur = next;
            }
            next = Math.max(i + arr[i], next);
        }
        return true;
    }

}
