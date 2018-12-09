package 剑指offer;

/**
 * created by Ethan-Walker on 2018/12/9
 * <p>
 * Boyer-Moore算法
 * 基本思想：
 * 比较直观的解释：在数组中找到两个不相同的元素并删除它们，不断重复此过程，直到数组中元素都相同，那么剩下的元素就是主要元素。
 * 思想并不复杂，但是要凭空想出这个算法来也不是件容易的事。另外，给我们的是数组，直接在里面删除元素是很费时的。取而代之，可以利用一个计数变量来实现。
 * <p>
 * 思路：
 * 先随意确定一个候选元素，count是候选元素的计数，当遇到一个跟候选元素不同的元素时，两者数量上抵消一个，count减1。一旦count变成0，就重新找一个候选元素。
 * 当遇到一个与候选元素不同的元素时，就要抵消。对于候选元素和当前元素，可能存在两种情况：1）两者中有一个正好是主要元素；2）两者都不是主要元素。
 * 对于情况1)，抵消过后，主要元素还是主要元素；对于情况2），可以说主要的元素的地位得到了巩固。所以算法最终能找到主要元素。
 * One More Thing
 * 上面的题目指出，满足条件的元素一定存在，那就可以直接返回我们找到的元素了。但事实上有时候这样的元素不一定存在，那么当我们找到这样一个元素时，
 * 还要进一步验证一下它是否满足条件。很简单，再遍历一遍，统计它的出现次数。
 * <p>
 * <p>
 * <p>
 * 使用 cnt 来统计一个元素出现的次数，当遍历到的元素和统计元素相等时，令 cnt++，否则令 cnt--。如果前面查找了 i 个元素，且 cnt == 0，
 * 说明前 i 个元素没有 majority，或者有 majority，但是出现的次数少于 i / 2 ，因为如果多于 i / 2 的话 cnt 就一定不会为 0 。
 * 此时剩下的 n - i 个元素中，majority 的数目依然多于 (n - i) / 2，因此继续查找就能找出 majority。
 */
public class Q039_MoreThanHalfNum2 {

    public int moreThanHalfNum(int[] array) {
        if (array == null || array.length == 0) return 0;
        int candidate = array[0];
        int count = 1; // count 并不是majority元素实际出现的累计次数, 而是majority 相对于其他元素的次数

        for (int i = 1; i < array.length; i++) {
            if (count == 0) { // 说明主要元素 不在a[0]~a[i-1] 之间或者是在其之间但在 a[0]~a[i-1] 范围内累计次数不超过 i/2, 即不是 a[0]~a[i-1]局部内的主要元素
                // 主要元素在 a[i] ~ a[n-1] 之间
                // 重新选定候选元素
                candidate = array[i];
                count = 1;
            } else if (array[i] == candidate) {
                count++;
            } else {
                count--; // 不同，抵消一个元素
            }
        }
        // 注意 ： count >=1 并不代表candidate 就是主要元素 示例 1 2 3 4 5 6 7 最终得到count=1  但没有candidate

        // 如果有 超过一半次数的数存在，一定为 candidate
        // 但是candidate 存在，不代表其就是超过一半次数的数，因为可能该数组压根不存在这样的值

        if (checkMoreThanHalf(array, candidate)) {
            return candidate;
        } else {
            return 0;
        }


    }

    public boolean checkMoreThanHalf(int[] a, int num) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == num) {
                count++;
            }
        }
        if (count > a.length / 2) {
            return true;
        }
        return false;
    }

}
