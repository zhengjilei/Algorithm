package 图像压缩;

/**
 * Created by EthanWalker on 2017/11/28.
 */
public class PicCompress {

    //像素序列
    private static int[] p;

    // p[0] 无值
    // 设置 s[0] = 0 的目的是，保证能对 (整个序列作为完整的一段 即 k 最大为全部元素的个数) 这种情况 考虑到
    // s[i] 表示 p[1] ~ p[i] 的最优分段的存储位数, s[0] = 0 ,s[1]
    private static int[] s;

    //b[i] 表示序号为 i 的像素点, 所需的存储位数
    private static int[] b;

    // l[i] 表式 1~i 的序列中, 最优分段的最后一段的段长度
    private static int[] l;

    public static int compress() {
        s[0] = 0;
        for (int i = 1; i < p.length; i++) {
            // 初始最后一个分段的元素个数为 1, 即k = 1 ,
            int min = s[i - 1] + 1 * (int) Math.ceil(Math.log(p[i] + 1) / Math.log(2)) + 11;
            l[i] = 1;
            b[i] = (int) Math.ceil(Math.log(p[i] + 1) / Math.log(2));
            //逐渐增大 最后一个分段的元素个数
            for (int k = 2; k <= i && k <= 256; k++) {
//                求 s[i] = s[i-k] + log(max{p[i-k+1],p[i-k],..p[i] } +1)  +11
                int temp = s[i - k] + k * (int) Math.ceil(Math.log(maxBit(i - k + 1, i) + 1) / Math.log(2)) + 11;
                if (temp < min) {
                    min = temp;
                    l[i] = k;
                }
            }
            s[i] = min;
        }
        return s[p.length - 1];
    }

    // 优化， 减少求最大值的重复计算
    public static int compress2() {
        s[0] = 0;
        for (int i = 1; i < p.length; i++) {
            b[i] = (int) Math.ceil(Math.log(p[i] + 1) / Math.log(2));
            int bMax = b[i];  // bMax 为最后一个分段 的灰度值最大值， 先指定为末尾的元素值
            s[i] = s[i - 1] + bMax;  // 最后一个元素 单独作为一段
            l[i] = 1;  // 最后一个分段的长度 默认为 1
            // k 表示末尾 作为整体段 的元素个数
            for (int k = 2; k <= i && k <= 256; k++) {
                // 后面 k 个作为整体段 , 该段的最大灰度值 可能会发生变化, 即该段每个像素端 需要的存储空间就会变化
                if (b[i - k + 1] > bMax) {
                    bMax = b[i - k + 1];
                }
                if (s[i] > s[i - k] + k * bMax) {
                    s[i] = s[i - k] + k * bMax;
                    l[i] = k;
                }
            }
            s[i] += 11;
        }
        return s[p.length - 1];
    }

    /**
     * 求数组 p 从索引 begin~end 之间的最大元素, 即该段的最大灰度值
     *
     * @param begin
     * @param end
     * @return
     */
    public static int maxBit(int begin, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = begin; i <= end; i++) {
            if (p[i] > max) {
                max = p[i];
            }
        }
        return max;
    }

    private static int m = 0;

    public static void traceBack(int n) {
        if (n == 0) return;
        traceBack(n - l[n]);
        s[m++] = n - l[n];  // s[i] 存储 前 i 分段的 总长，即元素个数
        // s[0]=0, s[1]=3, s[2]=4
    }
// 例: 分割的结果如下       _ _ _ | _  | _ _

    /**
     * @param n 序列的个数
     */
    public static void output(int n) {

        System.out.println("图像压缩后的最小空间为： " + s[n]);

        traceBack(n);
        s[m] = n;  // s[3]=6;
        System.out.println("将原灰度序列分成 " + m + " 段序列段");

        for (int j = 1; j <= m; j++) {
            l[j] = l[s[j]]; // l[j] 表示第 j 段 的段长(元素个数)
            b[j] = b[s[j]]; // b[j] 表示第 j 段 的存储位数
        }

        for (int j = 1; j <= m; j++) {
            System.out.println("段长度：" + l[j] + ",所需存储位数:" + b[j]);
        }
    }

    public static void main(String[] args) {
        p = new int[]{0, 10, 12, 15, 255, 1, 2};
        int n = p.length - 1;
        s = new int[p.length];
        l = new int[p.length];
        b = new int[p.length];
        compress();
        output(n);
    }
}
