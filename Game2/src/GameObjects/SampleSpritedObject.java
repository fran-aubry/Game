package GameObjects;
import IO.DataReader;


public class SampleSpritedObject extends SpritedObject {

	public SampleSpritedObject(int x, int y) {
		super(x, y, DataReader.readSprite("sample_sprite"));
	}

}
