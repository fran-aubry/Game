package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.MapEditorPanel;

public class MapEditorML implements MouseListener {

	private MapEditorPanel mapEdPanel;
	
	public MapEditorML(MapEditorPanel mapEdPanel) {
		this.mapEdPanel = mapEdPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		System.out.println(me.getX());
		System.out.println(me.getY());
		mapEdPanel.getSelectedTile();
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
