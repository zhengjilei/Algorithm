package designpattern.adapter.adapter1;

/**
 * 音频播放器
 * Created by Ethan-Walker on 2018/4/25.
 */
public class AudioPlayer implements CommonMediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        System.out.println("播放音频：" + fileName);
    }
}
