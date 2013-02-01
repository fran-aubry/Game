package Actions;

import GameObjects.GameObject;

public class Loop implements Action {

	private int n, i;
	private boolean isOver;
	
	public Loop(int n) {
		this.n = n;
		this.i = 1;
		this.isOver = false;
	}
	
	@Override
	public boolean isOver(GameObject gameObject) {
		if(isOver) {
			isOver = false;
			return true;
		}
		return false;
	}

	@Override
	public void performAction(GameObject gameObject) {
		if(i == n) {
			gameObject.setActionMode(ActionMode.NORMAL);
		} else {
			gameObject.setActionMode(ActionMode.LOOP);
		}
		i++;
		isOver = true;
	}

}
