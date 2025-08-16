import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class PanelProgram extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public PanelProgram(ImageIcon img, String program, String name) {
		setBackground(new Color(255, 255, 255));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(program);
		label.setPreferredSize(new Dimension(57, 40));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(new Color(255, 255, 255));
		add(label, BorderLayout.NORTH);
		
		label_1 = new JLabel(img);
		label_1.setBackground(new Color(255, 255, 255));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.CENTER);
		
		label_2 = new JLabel("   "+name);
		label_2.setPreferredSize(new Dimension(57, 40));
		label_2.setBackground(new Color(255, 255, 255));
		add(label_2, BorderLayout.SOUTH);

	}

}
