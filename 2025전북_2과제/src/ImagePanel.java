import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ImagePanel extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public ImagePanel(ImageIcon img, String txt) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.CENTER);
		
		label_1 = new JLabel(txt);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.SOUTH);

	}

}
