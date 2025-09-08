import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class C_패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;

	/**
	 * Create the panel.
	 */
	public C_패널(int limit ,ImageIcon img, String name, double per, String date) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label.setOpaque(true);
		label.setBackground(Color.PINK);
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.NORTH);
		
		label_1 = new JLabel(BF.getIcon("limits/"+limit+".png", 30, 30));
		label_1.setPreferredSize(new Dimension(40, 0));
		label_1.setVerticalAlignment(SwingConstants.TOP);
		add(label_1, BorderLayout.WEST);
		
		label_2 = new JLabel("");
		label_2.setPreferredSize(new Dimension(40, 0));
		add(label_2, BorderLayout.EAST);
		
		label_3 = new JLabel(img);
		add(label_3, BorderLayout.CENTER);
		
		label_4 = new JLabel(String.format("<html>%s<br>%.1f%% | 개봉일 : %s", name, per, date));
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_4.setPreferredSize(new Dimension(57, 40));
		add(label_4, BorderLayout.SOUTH);

	}

}
