package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.Map;

public class Sound {

    private Clip clip;
    private URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/audio/interactiveObjects/book.wav");
    }

    public void setFile(String clipName){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[clipNameToIndex(clipName)]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

    public int clipNameToIndex(String clipName){
        switch(clipName){
            case "book":
                return 0;
            default:
                return -1;
        }
    }
}
