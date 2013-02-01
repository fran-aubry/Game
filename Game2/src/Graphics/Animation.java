package Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;
	private int currentFrame, timeInFrame, timePerFrame;
	
	public Animation(BufferedImage[] frames, int timePerFrame) {
		this.frames = frames;
		this.timePerFrame = timePerFrame;
	}
	
	public BufferedImage getCurrentImage() {
		return frames[currentFrame];
	}
	
	public void goToNextImage() {
		timeInFrame = (timeInFrame + 1) % timePerFrame;
		if(timeInFrame == 0) {
			currentFrame = (currentFrame + 1) % frames.length;
		}
	}
	
}
