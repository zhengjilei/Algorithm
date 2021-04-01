package hash;

import java.util.Arrays;

/**
 * 数组作为hash映射地址
 * created by Ethan-Walker on 2018/11/7
 */
public class MyHash {

    int[] value;
    int size;
    private final int INITIAL_VALUE = 0x7fffffff;
    int prime;// 线性探查法的质数 ，不大于 size 的最大质数
    int dualPrime; // 双散列法 第二次执行哈希函数的质数
    int twoPrime; // 二次探查法的质数，4m+3 <=size

    MyHash(int size) {
        this.size = size;

        this.prime = maxPrime(size);
        this.dualPrime = getDualPrime(prime);
        this.twoPrime = getTwoPrime(size);
        this.value = new int[size];
        for (int i = 0; i < size; i++) {
            value[i] = INITIAL_VALUE;
        }
    }

    /**
     * hash 函数：     除留余数法（除数是小于等于size的最大质数）
     *
     * @param val
     * @return
     */
    public int hash(int val) {
        return val % prime;
    }

    /**
     * 插入数据
     * hash 冲突解决：  线性探查法
     *
     * @param val
     * @return 插入的位置，-1表示数组已满
     */
    public int insert(int val) {
        int address = hash(val);
        if (value[address] == INITIAL_VALUE) {
            value[address] = val;
            return address;
        } else if (value[address] != 0 && value[address] != val) {
            //被其他数据占用
            //线性探查法(数组组织成环形结构)
            for (int j = (address + 1) % size; j != address; j++) {  //j == address  循环一圈没找到空闲位置
                if (value[j] == INITIAL_VALUE) {
                    value[j] = val;
                    return j;
                }
            }
        } else if (value[address] == val) {
            //该值已存在
            return address;
        }
        return -1;
    }

    /**
     * 线性探查法对应的搜索
     *
     * @param val
     * @return
     */
    public int findPos(int val) {
        int address = val % prime;
        if (value[address] == val) return address;
        else if (value[address] == INITIAL_VALUE) {
            //没有该值
            return -1;
        } else {
            // !=val  && !=Initial_value 说明被其他值占用位置了
            for (int j = (address + 1) % prime; j != address; j++) {
                if (value[j] == val) {
                    return j;
                }
            }
            return -1;
        }
    }

    /**
     * 插入数据
     * hash 冲突解决：二次探查法 H1 = (H0 +/- i^2) %size
     *
     * @param val
     * @return
     */
    public int insert2(int val) {
        int address = hash(val);
        if (value[address] == INITIAL_VALUE) {
            value[address] = val;
            return address;
        } else if (value[address] == val) {
            return address;
        } else if (value[address] != 0 && value[address] != val) {
            //被其他数据占用
            for (int i = 1; i <= (twoPrime - 1) / 2; i++) {
                int index = (address + (int) Math.pow(-1, i + 1) * i * i) % twoPrime; // 下一次探查位置
                if (value[index] == INITIAL_VALUE) {
                    value[index] = val;
                    return index;
                }
            }

        }
        return -1;
    }

    /**
     * 双散列法
     * h1 = (h0+rehash(val))%prime
     *
     * @param val
     * @return
     */
    public int insert3(int val) {
        int index = firstHash(val);
        if (value[index] == INITIAL_VALUE) {
            value[index] = val;
            return index;
        } else if (value[index] == val) {
            return index;
        } else {
            int h0 = index, h1;
            for (int i = 1; i <= prime; i++) {
                h1 = (h0 + rehash(val)) % prime;
                if (value[h1] == INITIAL_VALUE) {
                    value[h1] = val;
                    return h1;
                } else {
                    h0 = h1;
                }
            }
        }
        return -1;
    }


    /**
     * 哈希函数： 3*val % prime
     *
     * @param val
     * @return
     */
    public int firstHash(int val) {
        return (3 * val) % prime;
    }

    /**
     * 哈希函数：(7*val % dualPrime) + 1
     *
     * @param val
     * @return
     */
    public int rehash(int val) {
        return (7 * val) % dualPrime + 1;
    }

    /**
     * 计算小于等于 n 的 最大质数
     */
    public int maxPrime(int n) {
        for (int i = n; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return 2;
    }

    /**
     * 计算双散列法的第二次hash函数需要的质数 < prime,与 prime 互质
     * 即求小于prime的最大互质数
     *
     * @param prime
     * @return
     */
    public int getDualPrime(int prime) {
        int t = 1;
        for (t = prime - 1; t >= 1; t--) {
            if (isRelativelyPrime(prime, t))
                return t;
        }
        return t;
    }

    /**
     * 判断两个数是否互质
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isRelativelyPrime(int a, int b) {
        int t = gcd(a, b);
        if (t > 1) {
            return false;
        } else return true;
    }

    /**
     * 求两个数的最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b) {

        while (a > 0) {
            int t = b % a;
            b = a;
            a = t;
        }
        return b;
    }

    /**
     * 获取二次探查法的最大质数
     * 4*i+3 <=n
     *
     * @param n
     * @return
     */
    public int getTwoPrime(int n) {
        int i = n / 4;
        if (i * 4 + 3 <= n) {
            return i * 4 + 3;
        } else {
            return (i - 1) * 4 + 3;
        }
    }

    /**
     * 判断是否是质数
     *
     * @param n
     * @return
     */
    public boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {

        MyHash myHash = new MyHash(12);
        int[] a = new int[]{22, 41, 53, 46, 30, 13, 1, 67};
        for (int i = 0; i < a.length; i++) {
            myHash.insert3(a[i]);
        }
        System.out.println(Arrays.toString(myHash.value));
    }
}
