import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

public class G_ÆÐ³Î extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public G_ÆÐ³Î(ImageIcon img,String name,String name2) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel("<html>"+name+"ÀÇ»ç<br>"+name2);
		label_1.setBorder(new EmptyBorder(0, 10, 0, 0));
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		add(label_1, BorderLayout.CENTER);

	}

}
