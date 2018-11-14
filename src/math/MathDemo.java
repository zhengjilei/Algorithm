package math;

public class MathDemo {
    public static double sqrt(double a) {
        if (a < 0) return Double.NaN;
        double t = a;
        while (Math.abs(t - a / t) > 1e-15) {
            t = (t + a / t) / 2.0;   // 逼近
        }
        return t;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(5));
    }
}
