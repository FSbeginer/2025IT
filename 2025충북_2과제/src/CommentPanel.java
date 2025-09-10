import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

public class CommentPanel extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;

	public CommentPanel(ImageIcon img, String name, String comment) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setPreferredSize(new Dimension(57, 40));
		add(label, BorderLayout.WEST);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		label_1 = new JLabel(name);
		panel.add(label_1);
		
		label_2 = new JLabel(comment);
		panel.add(label_2);
	}

}
