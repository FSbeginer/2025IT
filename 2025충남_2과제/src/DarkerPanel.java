import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class DarkerPanel extends JPanel {
	public JLayeredPane layeredPane;

	public DarkerPanel(Component comp, DarkLabel jl) {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(255, 255, 255));
		add(layeredPane, BorderLayout.CENTER);
		
		setSize(comp.getSize());
		jl.setSize(comp.getSize());
		layeredPane.add(comp);
		layeredPane.add(jl);
		layeredPane.setLayer(jl, 0);
		layeredPane.setLayer(comp, 100);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		comp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				layeredPane.setLayer(jl, 100);
				layeredPane.setLayer(comp, 0);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				layeredPane.setLayer(jl, 0);
				layeredPane.setLayer(comp, 100);
			}
		});
	}

}
