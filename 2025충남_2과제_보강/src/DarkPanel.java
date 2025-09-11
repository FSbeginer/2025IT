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
	public DarkPanel(Component comp, DarkLabel dl) {
		setLayout(new BorderLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		add(layeredPane, BorderLayout.CENTER);

		setSize(comp.getSize());
		dl.setSize(comp.getSize());
		layeredPane.add(comp);
		layeredPane.add(dl);
		layeredPane.setLayer(comp, 100);
		layeredPane.setLayer(dl, 0);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				layeredPane.setLayer(comp, 0);
				layeredPane.setLayer(layeredPane, 100);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				layeredPane.setLayer(comp, 100);
				layeredPane.setLayer(dl, 0);
			}
		});
	}

}

class DarkLabel extends JLabel{
	public DarkLabel() {
		setHorizontalAlignment(0);
		setForeground(Color.white);
		setFont(new Font("¸¼Àº °íµñ", 1, 15));
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0,0,0,70));
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
}
