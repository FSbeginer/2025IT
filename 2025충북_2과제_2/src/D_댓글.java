import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridLayout;

public class D_댓글 extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public D_댓글(ImageIcon img, String name, String txt) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setPreferredSize(new Dimension(50, 15));
		add(label, BorderLayout.WEST);
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		label_1 = new JLabel(name);
		panel.add(label_1);
		
		label_2 = new JLabel(txt);
		panel.add(label_2);

	}

}
