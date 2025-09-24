import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class DarkPanel extends JPanel {
	public JLayeredPane layeredPane;

	/**
	 * Create the panel.
	 */
	public DarkPanel(Component comp, DarkLabel dark) {
		setLayout(new BorderLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		add(layeredPane, BorderLayout.CENTER);
		setSize(comp.getSize());
		dark.setSize(comp.getSize());
		
		layeredPane.add(dark);
		layeredPane.add(comp);
		
		layeredPane.setLayer(dark, 0);
		layeredPane.setLayer(comp, 100);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				layeredPane.setLayer(comp, 0);
				layeredPane.setLayer(dark, 100);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				layeredPane.setLayer(comp, 100);
				layeredPane.setLayer(dark, 0);
			}
		});
	}

}
class DarkLabel extends JLabel {
	public DarkLabel() {
		setHorizontalAlignment(0);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0,0,0,50));
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
}
