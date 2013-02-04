package Main;

import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Actions.MapBuilder;
import GUI.GamePanel;
import GameObjects.GameObject;
import Graphics.Map;

public class MapEditor {

	private static final String intRegex = "[1-9][0-9]*";
	private static MapBuilder mapBuilder;


	public static void main(String[] args) throws InterruptedException {
		int[] size = getSize();
		if(size == null) System.exit(0);
		mapBuilder = new MapBuilder(size[0], size[1]);
		Game game = new Game(new LinkedList<GameObject>(), mapBuilder.getMap());

		// Game loop
		while(true) {
			Thread.sleep(10);
			game.getGamePanel().repaint();
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
