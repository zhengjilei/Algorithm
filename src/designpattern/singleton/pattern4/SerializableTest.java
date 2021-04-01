package designpattern.singleton.pattern4;


import java.io.*;

/**
 * Created by Ethan-Walker on 2018/5/11.
 */
public class SerializableTest {
    public static void main(String[] args) {
        serializable();
        deserializable();
    }

    public static void serializable() {
        MySingleObj mySingleObj = MySingleObj.getInstance();
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(new File("a.txt")));
            outputStream.writeObject(mySingleObj);
            System.out.println(mySingleObj);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void deserializable(){
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(new FileInputStream(new File("a.txt")));
            MySingleObj mySingleObj = (MySingleObj) inputStream.readObject();
            System.out.println(mySingleObj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
