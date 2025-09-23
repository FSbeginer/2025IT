import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class D_ÆÐ³Î extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public D_ÆÐ³Î(ImageIcon img, String txt, String txt2) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(569, 169);
		setLayout(null);
		
		label = new JLabel(txt);
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		label.setBounds(10, 10, 343, 38);
		add(label);
		
		label_1 = new JLabel("<html>"+txt2);
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_1.setBounds(10, 63, 343, 96);
		add(label_1);
		
		label_2 = new JLabel(img);
		label_2.setBounds(369, 0, 200, 169);
		add(label_2);
	}

}
