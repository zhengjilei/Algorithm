package designpattern.adapter.adapter1;

/**
 * Created by Ethan-Walker on 2018/4/25.
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playMp4(String fileName) {
        System.out.println("...");
    }

    @Override
    public void playVlc(String fileName) {
        System.out.println("VLC player 正在播放: "+fileName);
    }
}
