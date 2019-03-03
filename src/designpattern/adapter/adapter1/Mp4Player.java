package designpattern.adapter.adapter1;

/**
 * Created by Ethan-Walker on 2018/4/25.
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playMp4(String fileName) {
        System.out.println("Mp4 Player 正在播放: "+fileName);
    }

    @Override
    public void playVlc(String fileName) {
        System.out.printf("...");
    }
}

