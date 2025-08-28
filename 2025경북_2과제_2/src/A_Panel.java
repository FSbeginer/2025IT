import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class A_Panel extends JPanel {
	public JPanel panel;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JPanel panel_1;
	public JLabel label_4;

	/**
	 * Create the panel.
	 */
	public A_Panel(ImageIcon img, String name, int price, int quantity, double star) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setPreferredSize(new Dimension(10, 80));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		label = new JLabel("ªÛ«∞∏Ì : "+name);
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel.add(label);
		
		label_1 = new JLabel("∞°∞› : "+String.format("%,d", price));
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel.add(label_1);
		
		label_2 = new JLabel("∆«∏≈∑Æ : "+quantity);
		label_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel.add(label_2);
		
		label_3 = new JLabel("∆Ú¡° : "+String.format("%.1f",star));
		label_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel.add(label_3);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 10, 5, 10));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		label_4 = new JLabel(img);
		label_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(label_4);

	}

}
