import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;

public class A_패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public A_패널(ImageIcon img, String txt) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		add(label, BorderLayout.CENTER);
		
		label_1 = new JLabel(txt);
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_1.setPreferredSize(new Dimension(57, 30));
		add(label_1, BorderLayout.SOUTH);

	}

}
