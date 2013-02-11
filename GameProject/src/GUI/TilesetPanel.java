package GUI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TilesetPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TilesetPanel() {
		super();
		setLayout(new GridLayout(20, 5));
		add(new JButton("ok0"));
		add(new JButton("ok1"));
		add(new JButton("ok2"));
		add(new JButton("ok3"));
		add(new JButton("ok4"));
		
	}
	
}
