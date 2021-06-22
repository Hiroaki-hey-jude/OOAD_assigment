import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class Sound {
    private String clickSound;
    public Clip clip;

    public String getClickSound() {
        return clickSound;
    }

    public Sound(){
        clickSound = "./res/clickSound.wav";
    }

    public void setSound(String soundFileWav){
        try{
            File file = new File(soundFileWav);
            AudioInputStream input = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(input);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
}
