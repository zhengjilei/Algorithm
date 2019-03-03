package designpattern.adapter.adapter3;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Main {
    public static void main(String[] args) {
        // 只有三孔插头
        ThreeAdaptee threeAdaptee = new ThreeAdaptee();

        // 希望插入两孔插座中，通过 三-> 二 转换器
        ThreeToTwoAdapter adapter = new ThreeToTwoAdapter(threeAdaptee);
        twoAccept(adapter);

    }

    /**
     * 两孔插座：只接受两孔插头
     *
     * @param twoTarget
     */
    public static void twoAccept(TwoTarget twoTarget) {
        twoTarget.twoInsert();
    }
}
