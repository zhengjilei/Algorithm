package designpattern.adapter;

/**
 * CommonMediaPlayer 接口的适配器, 使得CommonMediaAdapter 拥有AdvancedPlayer 的功能
 * Created by Ethan-Walker on 2018/4/25.
 */
public class CommonMediaAdapter implements CommonMediaPlayer{

    private AdvancedMediaPlayer advancedMediaPlayer;

    public CommonMediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMediaPlayer = new VlcPlayer();
        }else if (audioType.equalsIgnoreCase("mp4")){
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMediaPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}
