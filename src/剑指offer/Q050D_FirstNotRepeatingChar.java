package 剑指offer;

/**
 * 字符流中第一个只出现一次的字符
 * <p>
 * 字符流，说明字符是动态逐渐插入的，形成的字符串是逐渐延伸的
 * created by Ethan-Walker on 2018/12/11
 */
public class Q050D_FirstNotRepeatingChar {


    byte[] hash = new byte[256];

    StringBuilder sb = new StringBuilder();
    int firstIndex;

    public char getFirstNotRepeatingChar() {
        for (int i = firstIndex; i < sb.length(); i++) {
            if (hash[sb.charAt(i)] == 1) {
                firstIndex = i;
                break;
            }
        }
        return sb.charAt(firstIndex);
    }

    public void insert(char ch) {
        sb.append(ch);
        hash[ch]++;
    }


    /**
     * 若不需要保存字符流数据呢？即不用 sb 来保存一个个字符, 如何返回得到第一个只出现一次的字符
     */
    class FirstNotRepeatingChar {

        /**
         * hash[ch]
         * =-1 说明该字符还不存在
         * >=0  该字符只存在一次 , 且 hash[ch] = j , j 为该字符所在字符流的位置索引,
         * 记录索引的目的是为了在多个只出现一次的字符中，找到第一个字符
         * =-2 说明该字符已经重复
         */
        int[] hash = new int[256];


        int index = 0; //

        public void insert(char ch) {
            if (hash[ch] == -1) {
                hash[ch] = index;
            } else if (hash[ch] >= 0) {
                hash[ch] = -2;
            }
            index++;
        }

        public char getFirstNotRepeatingChar() {

            int minIndex = Integer.MAX_VALUE;
            char c = 0;
            for (int ch = 0; ch < 256; ch++) {
                if (hash[ch] >= 0 && hash[ch] < minIndex) {
                    minIndex = hash[ch];
                    c = (char) ch;

                }
            }
            return c;
        }
    }


}


