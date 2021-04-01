package programmer_interview;

/**
 * created by Ethan-Walker on 2019/1/8
 */
public class Q069_JumpGame {


    /**
     * jump     表示当前跳的步数
     * reachPos 表示跳 jump 步最远到达的位置
     * nextPos  表示跳 jump + 1 步最远到达的位置
     * <p>
     * 遍历每一个位置 i
     * 1. reachPos < i 跳 jump 步到达不了位置 i, 再跳一步，reachPos = nextPos
     * 2. reachPos >= i,跳 jump 步能到达位置i
     * 每遍历一个位置，必须判断是否需要更新跳 jump+1 能到达的最远位置
     *
     * @param arr
     * @return
     */
    public int minStep(int[] arr) {

        int jump = 0, reachPos = 0, nextPos = 0;

        for (int i = 0; i < arr.length; i++) {
            if (reachPos < i) {
                if (nextPos < i) { // 再跳一步最远仍然到达不了位置 i ,举例 3 2 1 0 5 3 ,i=3时，jump =1, reachPos=3, nextPos=3
                    return -1;
                }
                jump++;
                reachPos = nextPos;
                if (nextPos >= arr.length - 1) {
                    // 不需要再跳了
                    break;
                }
            }
            nextPos = Math.max(i + arr[i], nextPos);
        }
        return jump; // 可以处理 arr={0} 的情况
    }

    /**
     * 判断是否可以跳跃到最后一个位置
     *
     * @return
     */
    public boolean canJump(int[] arr) {
        if (arr.length == 1) return true;
        int jump = 0, reachPos = 0, nextPos = 0;

        for (int i = 0; i < arr.length; i++) {
            if (reachPos < i) {
                if (nextPos < i) { // 再跳一步仍然到达不了
                    return false;
                }
                jump++;
                reachPos = nextPos;
            }
            nextPos = Math.max(i + arr[i], nextPos);
        }
        return true;
    }

}
