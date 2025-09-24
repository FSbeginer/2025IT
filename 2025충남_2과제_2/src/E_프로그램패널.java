import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class E_프로그램패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public E_프로그램패널(ImageIcon img,String name, String txt) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(name);
		label.setPreferredSize(new Dimension(57, 30));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.NORTH);
		
		label_1 = new JLabel(txt);
		label_1.setPreferredSize(new Dimension(57, 30));
		add(label_1, BorderLayout.SOUTH);
		
		label_2 = new JLabel(img);
		add(label_2, BorderLayout.CENTER);

	}

}
