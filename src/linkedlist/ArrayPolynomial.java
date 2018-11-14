package linkedlist;

/**
 * 数组实现存储多项式
 * 缺点：如果各项指数相差较大（稀疏），可能浪费较多的空间
 */
public class ArrayPolynomial {
    private final int MAX = 10;
    double[] coef = new double[MAX + 1]; // i 表示指数， coef[i] 表示对应项的系数

    /**
     * 两个多项式相加
     *
     * @param p1
     * @param p2
     * @return
     */
    public double[] add(double[] p1, double[] p2) {
        double[] p3 = new double[p1.length];
        for (int i = 0; i < p1.length; i++) {
            p3[i] = p1[i] + p2[i];
        }
        return p3;
    }
    public double[] multiply(double[] p1, double[] p2) {
        double[] p3 = new double[p1.length];
        double[] addResult = new double[p1.length];

        for (int i = 0; i < p1.length; i++) {
            double[] t = new double[p1.length];
            for (int j = 0; j < p2.length; j++) {
                t[j + i] = p1[i] * p2[j];
            }
            addResult = add(addResult, t);
        }
        return addResult;
    }

}
