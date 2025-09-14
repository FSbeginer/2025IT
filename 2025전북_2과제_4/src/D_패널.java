import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import java.awt.Font;

public class D_ÆÐ³Î extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public D_ÆÐ³Î(ImageIcon img, String txt) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		label.setPreferredSize(new Dimension(5, 0));
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel(img);
		label_1.setBorder(new MatteBorder(0, 1, 0, 1, (Color) new Color(0, 0, 0)));
		add(label_1, BorderLayout.CENTER);
		
		label_2 = new JLabel("   "+txt);
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		add(label_2, BorderLayout.EAST);

	}

}
