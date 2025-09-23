import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;

public class C_ÆÐ³Î extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;

	/**
	 * Create the panel.
	 */
	public C_ÆÐ³Î(ImageIcon img, String name, String doc, String date) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setPreferredSize(new Dimension(100, 15));
		add(label, BorderLayout.WEST);
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 0, 0, 0));
		
		label_1 = new JLabel("<html>"+name);
		label_1.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		panel.add(label_1);
		
		label_2 = new JLabel(doc+" ÀÇ»ç");
		label_2.setFont(new Font("±¼¸²", Font.PLAIN, 11));
		panel.add(label_2);
		
		label_3 = new JLabel(date);
		label_3.setFont(new Font("±¼¸²", Font.PLAIN, 11));
		panel.add(label_3);

	}

}
