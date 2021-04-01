package a_review.corejava;

import java.io.*;

/**
 * created by Ethan-Walker on 2019/2/21
 */
public class Serial {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        A a1 = new A(123, "abc", 182.32);
        String objectFile = "serialFile";

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(objectFile));
        objectOutputStream.writeObject(a1);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(objectFile));
        A a2 = (A) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(a2);
    }

    private static class A implements Serializable {

        private int x;
        private String y;
        private transient double z;

        A(int x, String y, double z) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x = " + x + "  " + "y = " + y + " z = " + z;
        }
    }
}
