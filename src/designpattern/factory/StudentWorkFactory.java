package designpattern.factory;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class StudentWorkFactory implements WorkeFactory {
    @Override
    public Work getWork() {
        return new StudentWork();
    }
}
