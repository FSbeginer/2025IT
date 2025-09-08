import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;

public class A_패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public A_패널(ImageIcon img, String name) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		add(label, BorderLayout.CENTER);
		
		label_1 = new JLabel(name);
		label_1.setPreferredSize(new Dimension(65, 30));
		add(label_1, BorderLayout.SOUTH);
		
	}

}
