import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class D_패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public D_패널(ImageIcon img, String txt) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		label.setPreferredSize(new Dimension(5, 0));
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel(img);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(label_1, BorderLayout.CENTER);
		
		label_2 = new JLabel("   "+txt);
		add(label_2, BorderLayout.EAST);

	}

}
