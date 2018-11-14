package 腾讯面试.循环buffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lenovo on 2018/4/21.
 */
public class RingBufferTest {

    @Test
    public void testBuffer(){
        RingBuffer<String> ringBuffer = new RingBuffer<>(5);
        ringBuffer.add("a");
        ringBuffer.add("b");
        ringBuffer.add("c");
        ringBuffer.add("d");
        ringBuffer.add("e");
        ringBuffer.add("f");  //   b c d e f
        System.out.println(Arrays.toString(ringBuffer.getAll()));

        String re1 = ringBuffer.remove();
        String re2 = ringBuffer.remove();
        String re3 = ringBuffer.remove();// e f
        System.out.println(Arrays.toString(ringBuffer.getAll()));

        ringBuffer.add("g");
        ringBuffer.add("h");
        ringBuffer.add("i");
        ringBuffer.add("j");
        ringBuffer.add("l");//  g h i j l
        System.out.println(Arrays.toString(ringBuffer.getAll()));

        String re4 = ringBuffer.remove();
        String re5 = ringBuffer.remove();  // i j l
        System.out.println(Arrays.toString(ringBuffer.getAll()));

        ringBuffer.add("m");
        ringBuffer.add("n");
        ringBuffer.add("o");  // j l m n o
        System.out.println(Arrays.toString(ringBuffer.getAll()));



    }
}
