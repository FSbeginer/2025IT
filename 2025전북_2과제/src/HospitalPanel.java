import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HospitalPanel extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 * @param name 
	 */
	public HospitalPanel(ImageIcon img, String name) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel();
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setPreferredSize(new Dimension(5, 0));
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel(img);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(label_1, BorderLayout.CENTER);
		
		label_2 = new JLabel("  "+name);
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setPreferredSize(new Dimension(160, 160));
		add(label_2, BorderLayout.EAST);

	}

}
