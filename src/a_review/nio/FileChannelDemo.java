package a_review.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class FileChannelDemo {

    public static void main(String[] args) {
        FileChannelDemo demo = new FileChannelDemo();
        demo.copy("a.txt","b.txt");
    }

    public void copy(String src, String dest) {
        try (FileInputStream fis = new FileInputStream(new File(src));
             FileOutputStream fos = new FileOutputStream(new File(dest));
             FileChannel readChannel = fis.getChannel();
             FileChannel writeChannel = fos.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len = -1;
            while ((len = readChannel.read(buffer)) != -1) {
                buffer.flip(); // 写模式切换到读模式
                writeChannel.write(buffer);
                buffer.clear(); // 重新从读模式置为写模式
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
