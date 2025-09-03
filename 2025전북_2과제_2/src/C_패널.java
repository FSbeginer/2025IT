import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class C_패널 extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;

	/**
	 * Create the panel.
	 */
	public C_패널(ImageIcon img, String txt, String doctor, String date) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 0, 0));
		
		label = new JLabel(img);
		add(label);
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		label_1 = new JLabel("<html>"+txt);
		label_1.setFont(new Font("굴림", Font.PLAIN, 12));
		panel.add(label_1);
		
		label_2 = new JLabel(doctor+" 의사");
		panel.add(label_2);
		
		label_3 = new JLabel(date);
		panel.add(label_3);

	}

}
