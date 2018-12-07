package tecent1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Ethan-Walker on 2018/3/25.
将硬币分为两份：1，2，4，8，16，.....和1，2，4，8，16....
 组成两个数值为a,b的两个数字，他们的和是a+b=n;
 a在每一份中只可能有一种组合方式（二进制的思想）。
 将a和b使用二进制表示，那么对于n=11，有a=101，b=110这种组合，即 a=1+0+4=5,b=0+2+4=6。
 但是，请注意，对于a和b，在相同位取不同值，只有一种组合方法。
 如 111+100 和 101+110（即交换中间位）本质上都是同一种组合方法，因此对于该类型可以使用二进制异或进行去重。
 不同的组合 1+6 3+4 最终都是 (1 2 4) 同一个， 异或结果一样
 分析可知， 只有异或结果不同， 才是满足条件的不同组合
 */
public class Xor {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        long n=scanner.nextLong();
        if(n<=2) {
            System.out.println(n);
            return;
        }
        long start = System.currentTimeMillis();

        Set<Long> countset=new HashSet<>();
        long stop=n/2;
        for(int i=0;i<=stop;i++) {
            long result=(i)^(n-i);//异或a和b
            countset.add(result);
        }
        long end = System.currentTimeMillis();
        System.out.println("异或算法消耗的时间: "+(end-start));
        //  组合目标:  10000 100000 1000000     10000000 10^10
        //  结果:       205  713    1287        9469
        //  时间：      5    21        46       172
        System.out.println(countset.size());
    }
}
