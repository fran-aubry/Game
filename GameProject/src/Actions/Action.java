package Actions;

import GameObjects.GameObject;

public interface Action {
	
	public abstract boolean isOver(GameObject gameObject);

	public abstract void performAction(GameObject gameObject);
	
}
