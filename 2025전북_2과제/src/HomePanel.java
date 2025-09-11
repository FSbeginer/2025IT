import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class HomePanel extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;

	/**
	 * Create the panel.
	 */
	public HomePanel(ImageIcon img, String hname, String dname, String date) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setPreferredSize(new Dimension(90, 90));
		add(label, BorderLayout.WEST);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		label_1 = new JLabel("<html>"+hname);
		label_1.setFont(new Font("±¼¸²", Font.BOLD, 14));
		panel.add(label_1);
		
		label_2 = new JLabel(dname);
		panel.add(label_2);
		
		label_3 = new JLabel(date);
		panel.add(label_3);

	}

}
