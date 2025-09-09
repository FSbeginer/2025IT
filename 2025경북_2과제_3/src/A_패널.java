import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class A_∆–≥Œ extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;

	/**
	 * Create the panel.
	 */
	public A_∆–≥Œ(ImageIcon img, String name, int price, int cnt, double star) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(0, 0, 0))));
		add(label, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 80));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(4, 0, 0, 0));
		
		label_1 = new JLabel("ªÛ«∞∏Ì : "+name);
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel.add(label_1);
		
		label_2 = new JLabel("∞°∞› : "+String.format("%,d", price));
		label_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel.add(label_2);
		
		label_3 = new JLabel("∆«∏≈∑Æ : "+cnt);
		label_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel.add(label_3);
		
		label_4 = new JLabel("∆Ú¡° : "+String.format("%.1f", star));
		label_4.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel.add(label_4);

	}

}
