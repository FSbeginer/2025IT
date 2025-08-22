import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class MoviePanel extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public MoviePanel(ImageIcon img, String txt) {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		add(label, BorderLayout.CENTER);
		
		label_1 = new JLabel(txt);
		label_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		label_1.setPreferredSize(new Dimension(57, 40));
		add(label_1, BorderLayout.SOUTH);

	}

}
