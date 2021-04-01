package designpattern.adapter.adapter3;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class ThreeAdaptee implements ThreeInterface {
    @Override
    public void threeInsert() {
        System.out.println("三孔插头 插入");
    }

}
