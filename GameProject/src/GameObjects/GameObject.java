package GameObjects;

import java.util.LinkedList;

import Actions.Action;
import Actions.ActionMode;
import Geometry.Point;

public abstract class GameObject {

	protected int x, y;
	protected double speed;
	protected LinkedList<Action> actionList;
	protected ActionMode actionMode;
	protected State state;
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		speed = 0;
		actionList = new LinkedList<Action>();
		actionMode = ActionMode.NORMAL;
		state = State.STOPPED;
	}

	public GameObject(int x, int y, double speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		actionList = new LinkedList<Action>();
		actionMode = ActionMode.NORMAL;
		state = State.STOPPED;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public void setPosition(Point position) {
		this.x = position.getX();
		this.y = position.getY();
	}
	
	public Point getPoint() {
		return new Point(x, y);
	}
	
	public void setActionMode(ActionMode actionMode) {
		this.actionMode = actionMode;
	}
	
	public void addAction(Action action) {
		actionList.addLast(action);
	}
	
	public void addActions(LinkedList<Action> actions) {
		actionList.addAll(actions);
	}
	
	public void clearActions() {
		actionList = new LinkedList<Action>();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void updatePosition(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public double getSpeed() {
		return speed;
	}

	public void performAction() {
		if(!actionList.isEmpty()) {
			Action action = actionList.getFirst();
			if(action.isOver(this)) {
				if(actionMode == ActionMode.LOOP) {
					addAction(action);
				}
				actionList.removeFirst();
				performAction();
			} else {
				action.performAction(this);
			}
		}
	}

}
