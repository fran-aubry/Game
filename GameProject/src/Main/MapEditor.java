package Main;

import java.awt.Dimension;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Actions.Action;
import Actions.Move;
import GUI.MapEditorPanel;
import GameObjects.GameObject;
import GameObjects.SampleSpritedMovableObject;
import Geometry.Point;
import IO.DataReader;
import Listeners.MapEditorML;

public class MapEditor {

	private static final String intRegex = "[1-9][0-9]*";
	private static MapBuilder mapBuilder;
	private static LinkedList<GameObject> gameObjects;


	public static void main(String[] args) throws InterruptedException {
		int[] size = new int[] {50, 50}; // for faster debug, to be removed.
		if(size == null) System.exit(0);
		mapBuilder = new MapBuilder(size[0], size[1]);


		// initialize frame
		MapEditorPanel mapEdPanel = new MapEditorPanel(DataReader.readTileset("sample_tileset"), 3, 25);
		JFrame mapEdFrame = new JFrame("Tileset");
		mapEdFrame.addMouseListener(mapEdPanel);
		mapEdFrame.add(mapEdPanel);
		mapEdFrame.setAlwaysOnTop(true);
		mapEdFrame.setSize(5 * 32, 20 * 32);
		mapEdFrame.setResizable(false);
		mapEdFrame.setAlwaysOnTop(true);
		mapEdFrame.setVisible(true);
		//

		// re-adjust the window size
		Dimension dimension = mapEdFrame.getSize();
		Insets insets = mapEdFrame.getInsets();
		int insetWidth = insets.left + insets.right;
		int insetHeight = insets.top + insets.bottom;
		mapEdFrame.setSize((int)dimension.getWidth() + insetWidth,(int)dimension.getHeight() + insetHeight); 
		//
		
		gameObjects = new LinkedList<GameObject>();

		/*
		// Initialize game objects (test only)
		SampleSpritedMovableObject sample3 = new SampleSpritedMovableObject(160, 160);
		LinkedList<Action> actions1 = Move.buildMovementFromPath(mapBuilder.getMap().shortestPath(sample3.getPoint(), new Point(224, 448)));
		for(Action action : actions1) {
			sample3.addAction(action);
		}
		gameObjects.add(sample3);
		//
		 */

		Game game = new Game(gameObjects, mapBuilder.getMap(), new MapEditorML(mapEdPanel));

		// Game loop
		while(true) {
			Thread.sleep(10);
			for(GameObject go : game.getGameObjects()) {
				go.performAction();
			}
			game.getGamePanel().repaint();
			mapEdFrame.repaint();
		}
		//
	}

	static int[] getSize() {
		JTextField heightTF = new JTextField(5);
		JTextField widthTF = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("height:"));
		myPanel.add(heightTF);
		myPanel.add(Box.createHorizontalStrut(15));
		myPanel.add(new JLabel("width:"));
		myPanel.add(widthTF);
		int result = -1;
		do {
			heightTF.setText("");
			widthTF.setText("");
			result = JOptionPane.showConfirmDialog(null, myPanel, "Enter the map dimensions", JOptionPane.OK_CANCEL_OPTION);
		} while(result == -1 && (!heightTF.getText().matches(intRegex) || !widthTF.getText().matches(intRegex)));

		if(result != JOptionPane.OK_OPTION) {
			return null;
		}

		return new int[] {Integer.parseInt(heightTF.getText()), Integer.parseInt(widthTF.getText())};
	}

}
