package leetcode;

/**
 * created by Ethan-Walker on 2018/12/27
 */
public class Q331_ValidatePreSerial {

    /**
     * 验证前序序列是否合法
     * 1. 节点数 + 1 = # 位  ( n0 = n2+1)
     * 2. 任意序列前 k (k< n)位，一定有 节点数>= # 数
     * 3. 序列最后一位为 #
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        int numberCount = 0, endCount = 0;
        String[] values = preorder.split(",");

        for (int i = 0; i < values.length - 1; i++) {
            if (values[i].equals("#")) {
                endCount++;
            } else {
                numberCount++;
            }
            if (numberCount < endCount) {
                return false;
            }
        }
        if (values[values.length - 1].equals("#"))
            endCount++;
        else
            return false;
        if (numberCount + 1 == endCount)
            return true;

        return false;
    }
}
