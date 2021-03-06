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
import java.util.LinkedList;
import java.util.Queue;

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
	private boolean drawGrid;

	public GamePanel(Game game, boolean drawGrid) {
		super(true);
		this.drawGrid = drawGrid;
		this.game = game;
		//setBackground(new Color(134, 208, 211));
		iCorner = 0;
		jCorner= 0;
		nbOfHorizontalTiles = Math.min(resolutionWidth / 32, game.getMap().getWidth());
		nbOfVerticalTiles = Math.min(resolutionHeight / 32, game.getMap().getHeight());
		viewport = new Rectangle(0, 0, resolutionHeight, resolutionWidth);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// compute current viewport
		int x = jCorner * 32, y = iCorner * 32;
		viewport = new Rectangle(x, y, x + resolutionWidth, y + resolutionHeight);
		//
		g2d.setColor(new Color(134, 208, 211));
		g2d.fillRect(x, y, 32 * nbOfHorizontalTiles, 32 * nbOfVerticalTiles);

		// draw map (ground layers)
		Map map = game.getMap();
		for(int l = 0; l < map.nbOfLayers(); l++) {
			for(int i = 0; i < nbOfVerticalTiles; i++) {
				for(int j = 0; j < nbOfHorizontalTiles; j++) {
					Tile tile = map.getLayer(l).getTile(iCorner + i, jCorner + j);
					if(tile.getGroundImage() != null) {
						g2d.drawImage(tile.getGroundImage(), j * 32, i * 32 + tile.getCut(), null);						
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

		// draw map (above layers)
		for(int l = 0; l < map.nbOfLayers(); l++) {
			for(int i = 0; i < nbOfVerticalTiles; i++) {
				for(int j = 0; j < nbOfHorizontalTiles; j++) {
					Tile tile = map.getLayer(l).getTile(iCorner + i, jCorner + j);
					if(tile.getSkyImage() != null) {
						g2d.drawImage(tile.getSkyImage(), j * 32, i * 32, null);
					}
				}
			}
		}
		//

		// draw grid
		if(drawGrid) {
			g2d.setColor(Color.black);
			// draw horizontal lines
			for(int i = 0; i <= nbOfVerticalTiles; i++) {
				g2d.drawLine(0, i * 32, 32 * nbOfHorizontalTiles, i * 32);
			}
			// draw vertical lines
			for(int i = 0; i <= nbOfHorizontalTiles; i++) {
				g2d.drawLine(i * 32, 0, i * 32, 32 * nbOfVerticalTiles);
			}
			g2d.drawRect(11 - x, 11 - y, 10, 10);
		}
		//
		
		// debug
		Font font = new Font("Serif", Font.PLAIN, 20);
		g2d.setColor(Color.BLACK);
		g2d.setFont(font);
		g2d.drawString("use arrows to scroll the map", 50, 20);
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
