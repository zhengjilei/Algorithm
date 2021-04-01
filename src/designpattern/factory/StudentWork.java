package designpattern.factory;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class StudentWork implements Work {
    @Override
    public void doWork() {
        System.out.println(" 学生做作业");
    }
}
