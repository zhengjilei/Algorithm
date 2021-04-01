package designpattern.adapter.adapter3;

/**
 * 由于只有两孔插座
 * 现在让三孔插头 通过该适配器 转换成 两孔插头
 * created by Ethan-Walker on 2019/3/2
 */
public class ThreeToTwoAdapter implements TwoTarget {

    private ThreeInterface threeInterface;

    public ThreeToTwoAdapter(ThreeInterface ti) {
        threeInterface = ti;
    }

    @Override
    public void twoInsert() {
        threeInterface.threeInsert();
    }
}
