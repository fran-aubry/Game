package Actions;

import java.util.LinkedList;

import GameObjects.GameObject;
import GameObjects.State;
import Geometry.Vector;
import Geometry.Point;

public class Move implements Action {

	private Vector v;
	private Point destination;
	private Point origin;
	
	public Move(int xOrig, int yOrig, int xDest, int yDest) {
		this.v = new Vector(xOrig, yOrig, xDest, yDest);
		this.origin = v.getOrigin();
		this.destination = v.getDestination();
	}
	
	public Move(Point origin, Point destination) {
		this.origin = origin;
		this.destination = destination;
		this.v = new Vector(origin.getX(), origin.getY(), destination.getX(), destination.getY());
	}

	public void performAction(GameObject gameObject) {
		gameObject.updatePosition((int)Math.round(v.getDx() * gameObject.getSpeed()), 
				                  (int)Math.round(v.getDy() * gameObject.getSpeed()));
	}

	@Override
	public boolean isOver(GameObject gameObject) {
		int dx = gameObject.getX() + (int)Math.round(v.getDx() * gameObject.getSpeed());
		int dy = gameObject.getY() + (int)Math.round(v.getDy() * gameObject.getSpeed());
		Point P = new Point(dx, dy);
		long travaledDistance = origin.squaredDistance(P);
		if(travaledDistance >= origin.squaredDistance(destination)) {
			gameObject.setPosition(destination);
			return true;
		}
		return false;
	}
	
	public static LinkedList<Action> buildMovementFromPath(LinkedList<Point> path) {
		LinkedList<Action>  actions = new LinkedList<Action>();
		Point previous = path.removeFirst();
		Point next = null;
		while(!path.isEmpty()) {
			next = path.getFirst();
			// dx < 0 => look left; dx > 0 => look right
			int dx = next.getX() - previous.getX();
			// dy < 0 => look up; dy > 0 => look down
			int dy = next.getY() - previous.getY();
			if(dx <= 0 && dy <= 0) {
				if(Math.abs(dx) > Math.abs(dy)) {
					actions.add(new ChangeState(State.MOVE_LEFT));
				} else {
					actions.add(new ChangeState(State.MOVE_UP));
				}
			}
			if(dx >= 0 && dy <= 0) {
				if(Math.abs(dx) > Math.abs(dy)) {
					actions.add(new ChangeState(State.MOVE_RIGHT));
				} else {
					actions.add(new ChangeState(State.MOVE_UP));
				}
			}
			if(dx <= 0 && dy >= 0) {
				if(Math.abs(dx) > Math.abs(dy)) {
					actions.add(new ChangeState(State.MOVE_LEFT));
				} else {
					actions.add(new ChangeState(State.MOVE_DOWN));
				}
			}
			if(dx >= 0 && dy >= 0) {
				if(Math.abs(dx) > Math.abs(dy)) {
					actions.add(new ChangeState(State.MOVE_RIGHT));
				} else {
					actions.add(new ChangeState(State.MOVE_DOWN));
				}
			}
			
			actions.add(new Move(previous, next));
			previous = path.removeFirst();
		}
		
		actions.add(new StopMovement());
		return actions;
	}
	
	public String toString() {
		return "move";
	}

}
