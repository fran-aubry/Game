package Actions;

import GameObjects.GameObject;
import GameObjects.State;

public class ChangeState implements Action {

	private State state;
	private boolean isOver;
	
	public ChangeState(State state) {
		this.state = state;
		this.isOver = false;
	}
	
	@Override
	public boolean isOver(GameObject gameObject) {
		return isOver;
	}

	@Override
	public void performAction(GameObject gameObject) {
		gameObject.setState(state);
		isOver = true;
	}
	
	public String toString() {
		return "change state";
	}

}
