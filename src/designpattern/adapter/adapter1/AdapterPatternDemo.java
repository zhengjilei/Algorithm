package designpattern.adapter.adapter1;

/**
 * Created by Ethan-Walker on 2018/4/25.
 */
public class AdapterPatternDemo {
    public static void main(String[] args) {
        CommonMediaPlayer mediaPlayer = new ImprovedAudioPlayer();
        mediaPlayer.play("mp3","等一分钟.mp3");
        mediaPlayer.play("mp4","等一分钟.mp4");
        mediaPlayer.play("vlc","等一分钟.vlc");

    }
}
