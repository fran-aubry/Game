package Main;


import java.util.LinkedList;

import javax.swing.JFrame;

import Actions.Action;
import Actions.ActionMode;
import Actions.Loop;
import Actions.Move;
import GUI.GamePanel;
import GameObjects.GameObject;
import GameObjects.SampleSpritedMovableObject;
import GameObjects.SampleSpritedObject;
import Geometry.Index;
import Geometry.Point;
import Graphics.Map;
import Graphics.MapLayer;
import IO.DataReader;


public class Game {

	public static void main(String[] args) throws InterruptedException {
		new Game();
		while(true) {
			Thread.sleep(10);
			for(GameObject go : gameObjects) {
				go.performAction();
			}
			gamePanel.repaint();
		}
	}

	private static LinkedList<GameObject> gameObjects;
	private static GamePanel gamePanel;
	private static Map map;
	
	public Game() {
		// Load map and game objects
		map = DataReader.readMap("sample_map");
		gameObjects = new LinkedList<GameObject>();
		gameObjects.add(new SampleSpritedObject(0, 0));
		SampleSpritedMovableObject sample1 = new SampleSpritedMovableObject(64, 64);
		sample1.addAction(new Move(sample1.getX(), sample1.getY(), sample1.getX() + 32 * 8, sample1.getY()));
		SampleSpritedMovableObject sample2 = new SampleSpritedMovableObject(64, 64);
		sample2.addAction(new Move(sample2.getX(), sample2.getY(), sample2.getX() + 32 * 8, sample2.getY() + 32 * 8));
		gameObjects.add(sample1);
		gameObjects.add(sample2);
		
		SampleSpritedMovableObject sample3 = new SampleSpritedMovableObject(96, 96);
		LinkedList<Action> actions = Move.buildMovementFromPath(map.shortestPath(sample2.getPoint(), new Point(254, 496)));
		for(Action action : actions) {
			sample3.addAction(action);
		}
		gameObjects.add(sample3);

		//

		// Initialize window
		JFrame window = new JFrame();
		window.setUndecorated(true);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel = new GamePanel(this);
		window.add(gamePanel);
		window.addKeyListener(gamePanel);
		window.addMouseListener(gamePanel);
		window.setFocusable(true);
		window.setVisible(true);
		//
	}

	public LinkedList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public Map getMap() {
		return map;
	}


}
