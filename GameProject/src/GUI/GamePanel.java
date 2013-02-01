package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Actions.Move;
import GameObjects.DrawableObject;
import GameObjects.GameObject;
import Geometry.Rectangle;
import Graphics.Map;
import Graphics.MapLayer;
import Graphics.Tile;
import Main.Game;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private static final int resolutionWidth = 1280;
	private static final int resolutionHeight = 1024;


	private Game game;
	private int iCorner, jCorner; // coordinates of the first tile to draw
	private int nbOfHorizontalTiles, nbOfVerticalTiles;
	private Rectangle viewport;

	public GamePanel(Game game) {
		super(true);
		this.game = game;
		iCorner = 0;
		jCorner= 0;
		nbOfHorizontalTiles = resolutionWidth / 32;
		nbOfVerticalTiles = resolutionHeight / 32;
		viewport = new Rectangle(0, 0, resolutionHeight, resolutionWidth);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// compute current viewport
		int x = jCorner * 32, y = iCorner * 32;
		viewport = new Rectangle(x, y, x + resolutionHeight, y + resolutionWidth);
		//

		// draw map
		Map map = game.getMap();
		for(int l = 0; l < map.nbOfLayers(); l++) {
			for(int i = 0; i < nbOfVerticalTiles; i++) {
				for(int j = 0; j < nbOfHorizontalTiles; j++) {
					Tile tile = map.getLayer(l).getTile(iCorner + i, jCorner + j);
					if(tile != null) { // the null tile corresponds to "no image" - 0 on map layer
						g2d.drawImage(tile.getImage(), j * 32, i * 32, null);						
					}
				}
			}
		}
		//

		// draw game objects
		for(GameObject go : game.getGameObjects()) {
			if(go instanceof DrawableObject) {
				DrawableObject drawableObj = ((DrawableObject) go);
				if(viewport.intersects(drawableObj.getRectangle())) {
					drawableObj.draw(g2d, -jCorner * 32, -iCorner * 32);
				}
			}
		}
		//

		// debug
		Font font = new Font("Serif", Font.PLAIN, 20);
		g2d.setColor(Color.BLACK);
		g2d.setFont(font);
		g2d.drawString("use arrows to scroll the map", 20, 20);
		//
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		switch(ke.getKeyCode()) {
		case KeyEvent.VK_UP: // scroll up
			iCorner = Math.max(iCorner - 1, 0);
			break;
		case KeyEvent.VK_LEFT: // scroll left
			jCorner = Math.max(jCorner - 1, 0);
			break;
		case KeyEvent.VK_DOWN: // scroll down
			if(iCorner + nbOfVerticalTiles < game.getMap().getHeight()) {
				iCorner++;
			}
			break;
		case KeyEvent.VK_RIGHT: // scroll right
			if(jCorner + nbOfHorizontalTiles < game.getMap().getWidth()) {
				jCorner++;
			}
			break;
		}
	}


	@Override
	public void keyReleased(KeyEvent ke) {
		//game.getPlayer().clearActions();
	}


	@Override
	public void keyTyped(KeyEvent ke) {
		//System.out.println("T: " + ke.getID());
	}


	@Override
	public void mouseDragged(MouseEvent me) {

	}


	@Override
	public void mouseMoved(MouseEvent me) {

	}


	@Override
	public void mouseClicked(MouseEvent me) {

	}


	@Override
	public void mouseEntered(MouseEvent me) {

	}


	@Override
	public void mouseExited(MouseEvent me) {

	}


	@Override
	public void mousePressed(MouseEvent me) {

	}


	@Override
	public void mouseReleased(MouseEvent me) {

	}


}
