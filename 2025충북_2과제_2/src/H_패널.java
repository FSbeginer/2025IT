import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class H_패널 extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public H_패널(ImageIcon img, String txt) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		add(label, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(30, 10));
		add(panel, BorderLayout.WEST);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(30, 10));
		add(panel_1, BorderLayout.EAST);
		
		label_1 = new JLabel(txt);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.SOUTH);

	}

}
