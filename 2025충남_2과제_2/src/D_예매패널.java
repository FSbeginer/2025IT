import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;

public class D_예매패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public D_예매패널(ImageIcon img, String name,String txt) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize( 531, 168);
		setLayout(null);
		
		label = new JLabel(img);
		label.setBounds(347, 0, 184, 168);
		add(label);
		
		label_1 = new JLabel(name);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_1.setBounds(12, 10, 273, 41);
		add(label_1);
		
		label_2 = new JLabel("<html>"+txt);
		label_2.setBounds(12, 61, 323, 97);
		add(label_2);
	}

}
