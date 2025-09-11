import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class E_패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public E_패널(ImageIcon img, String name, String txt) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(name);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(57, 20));
		add(label, BorderLayout.NORTH);
		
		label_1 = new JLabel(img);
		add(label_1, BorderLayout.CENTER);
		
		label_2 = new JLabel(txt);
		label_2.setPreferredSize(new Dimension(57, 20));
		add(label_2, BorderLayout.SOUTH);

	}

}
