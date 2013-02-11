package Main;


import java.awt.event.MouseListener;
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

	private LinkedList<GameObject> gameObjects;
	private GamePanel gamePanel;
	private Map map;
	
	public Game(LinkedList<GameObject> gameObjects, Map map, MouseListener mouseListener) {
		this.gameObjects = gameObjects;
		this.map = map;

		// Initialize window
		JFrame window = new JFrame();
		window.setUndecorated(true);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel = new GamePanel(this, true);
		window.add(gamePanel);
		window.addKeyListener(gamePanel);
		window.addMouseListener(mouseListener);
		window.setFocusable(true);
		window.setVisible(true);
		//
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public LinkedList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public Map getMap() {
		return map;
	}


}
