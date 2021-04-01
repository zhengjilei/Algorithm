package designpattern.factory;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class TeacherWorkFactory implements WorkeFactory {
    @Override
    public Work getWork() {
        return new TeacherWork();
    }
}
