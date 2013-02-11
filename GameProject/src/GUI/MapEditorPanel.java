package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Geometry.Index;
import Graphics.Tile;

public class MapEditorPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private final int y0 = 32;
	
	private JButton addLayerBut;
	private JComboBox<Integer> layerList;
	private Index selectedIndex;
	private ArrayList<Tile[]> tilesets;
	private int mouseDx, mouseDy;

	public MapEditorPanel(Tile[] tileset, int mouseDx, int mouseDy) {
		super(true);
		this.mouseDx = mouseDx;
		this.mouseDy = mouseDy;
		tilesets = new ArrayList<Tile[]>();
		tilesets.add(tileset);
		selectedIndex = new Index(0, 0);
		
		// initialize components
		addLayerBut = new JButton("Add Layer");
		add(addLayerBut);
		layerList = new JComboBox<Integer>();
		layerList.addItem(1);
		add(layerList);
		//
	}
	
	public Index getSelectedIndex() {
		return selectedIndex;
	}
	
	public Tile getSelectedTile() {
		Tile[] tileset = tilesets.get(layerList.getSelectedIndex());
		int i = getIndex(selectedIndex);
		if(i >= tileset.length) {
			return Tile.EMPTY_TILE;
		} else {
			return tileset[i];
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		// draw tileset
		Tile[] tileset = tilesets.get(layerList.getSelectedIndex());
		for(int i = 0; i < tileset.length; i++) {
			Index I = getIndex(i);
			int x = I.getJ() * 32;
			int y = y0 + I.getI() * 32;
			if(tileset[i].getGroundImage() != null) {
				g2d.drawImage(tileset[i].getGroundImage(), x, y + tileset[i].getCut(), null);
			}
			if(tileset[i].getSkyImage() != null) {
				g2d.drawImage(tileset[i].getSkyImage(), x, y, null);
			}
		}
		//
		
		// draw vertical lines
		g2d.setColor(Color.black);
		int nbOfVerticalTiles = 20;
		int nbOfHorizontalTiles = 5;
		for(int i = 1; i <= nbOfVerticalTiles; i++) {
			g2d.drawLine(0, i * 32, 32 * nbOfHorizontalTiles, i * 32);
		}		
		for(int i = 1; i <= nbOfHorizontalTiles; i++) {
			g2d.drawLine(i * 32, y0, i * 32, 32 * nbOfVerticalTiles + y0);
		}
		//
		
		// draw selection
		g2d.setColor(Color.RED);
		g2d.drawRect(selectedIndex.getI() * 32, y0 + selectedIndex.getJ() * 32, 32, 32);
		//
	}
	
	private int getCorrectedX(MouseEvent me) {
		return me.getX() - mouseDx;
	}
	
	private int getCorrectedY(MouseEvent me) {
		return me.getY() - mouseDy;
	}
	
	private Index getIndex(int i) {
		return new Index(i / 5, i % 5);
	}
	
	private int getIndex(Index I) {
		return I.getI() * 5 + I.getJ();
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		int x = getCorrectedX(me);
		int y = getCorrectedY(me);
		y -= y0;
		selectedIndex.setI(x / 32);
		selectedIndex.setJ(y / 32);
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub

	}

}
