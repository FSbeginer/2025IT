import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class D_ÆÐ³Î extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public D_ÆÐ³Î(ImageIcon img, String name) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		label.setPreferredSize(new Dimension(5, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel(img);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.CENTER);
		
		label_2 = new JLabel("   "+name);
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		label_2.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_2, BorderLayout.EAST);

	}

}
