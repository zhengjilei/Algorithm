package designpattern.adapter;

/**
 * Created by Ethan-Walker on 2018/4/25.
 */
public class ImprovedAudioPlayer implements CommonMediaPlayer {

    private CommonMediaAdapter commonMediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            // 内置功能 已经支持
            System.out.println("MP3播放: " + fileName);
        } else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            commonMediaAdapter = new CommonMediaAdapter(audioType);
            commonMediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("invalid media. format not supported. ");
        }
    }
}
