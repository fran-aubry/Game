package Actions;

import GameObjects.GameObject;
import GameObjects.State;

public class StopMovement implements Action {

	private static boolean isOver;
	
	public StopMovement() {
		isOver = false;
	}
	
	@Override
	public boolean isOver(GameObject gameObject) {
		return isOver;
	}

	@Override
	public void performAction(GameObject gameObject) {
		if(gameObject.getState() == State.MOVE_DOWN) {
			gameObject.setState(State.STOPPED_DOWN);
		} else if(gameObject.getState() == State.MOVE_UP) {
			gameObject.setState(State.STOPPED_UP);
		} else if(gameObject.getState() == State.MOVE_LEFT) {
			gameObject.setState(State.STOPPED_LEFT);
		} else if(gameObject.getState() == State.MOVE_RIGHT) {
			gameObject.setState(State.STOPPED_RIGHT);
		}
		isOver = true;
	}

}
