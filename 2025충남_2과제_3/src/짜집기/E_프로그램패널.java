package Â¥Áý±â;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class E_ÇÁ·Î±×·¥ÆÐ³Î extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public E_ÇÁ·Î±×·¥ÆÐ³Î(ImageIcon img, String txt, String txt2) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		add(label, BorderLayout.CENTER);
		
		label_1 = new JLabel(txt);
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.NORTH);
		
		label_2 = new JLabel(txt2);
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		add(label_2, BorderLayout.SOUTH);

	}

}
