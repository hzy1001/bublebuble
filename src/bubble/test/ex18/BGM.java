package bubble.test.ex18;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class BGM {
	public BGM() {
		// TODO Auto-generated constructor stub
		// AudioInputStream ais = AudioSystem.getAudioInputStream(new
		// File("sound/bgm.wav"));
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/bgm.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			FloatControl gainConrol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

			gainConrol.setValue(-30.0f);

			clip.start();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
