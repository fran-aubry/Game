package Main;

import java.util.LinkedList;

import Actions.Action;
import Actions.Move;
import GameObjects.GameObject;
import GameObjects.SampleSpritedMovableObject;
import Geometry.Point;
import Graphics.Map;
import IO.DataReader;

public class Run {

	public static void main(String[] args) throws InterruptedException {
		// Load map and game objects
		Map map = DataReader.readMap("sample_map");
		LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
		/*

				gameObjects.add(new SampleSpritedObject(0, 0));
				SampleSpritedMovableObject sample1 = new SampleSpritedMovableObject(64, 64);
				sample1.addAction(new Move(sample1.getX(), sample1.getY(), sample1.getX() + 32 * 8, sample1.getY()));
				SampleSpritedMovableObject sample2 = new SampleSpritedMovableObject(64, 64);
				sample2.addAction(new Move(sample2.getX(), sample2.getY(), sample2.getX() + 32 * 8, sample2.getY() + 32 * 8));
				gameObjects.add(sample1);
				gameObjects.add(sample2);
		 */
		SampleSpritedMovableObject sample3 = new SampleSpritedMovableObject(160, 160);
		LinkedList<Action> actions1 = Move.buildMovementFromPath(map.shortestPath(sample3.getPoint(), new Point(224, 448)));
		for(Action action : actions1) {
			sample3.addAction(action);
		}
		gameObjects.add(sample3);
		//

		Game game = new Game(gameObjects, map);
		// Game loop
		while(true) {
			Thread.sleep(10);
			for(GameObject go : game.getGameObjects()) {
				go.performAction();
			}
			game.getGamePanel().repaint();
		}
		//
	}

}
