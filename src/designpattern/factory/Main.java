package designpattern.factory;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Main {
    public static void main(String[] args) {
        WorkeFactory factory = new StudentWorkFactory();
        Work work = factory.getWork();

        work.doWork();
    }
}
