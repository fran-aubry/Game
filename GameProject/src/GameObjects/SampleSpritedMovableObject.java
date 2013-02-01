package GameObjects;
import IO.DataReader;


public class SampleSpritedMovableObject extends SpritedObject {

	public SampleSpritedMovableObject(int x, int y) {
		super(x, y, 1.5, DataReader.readSprite("sample_sprite2"));
	}

}
