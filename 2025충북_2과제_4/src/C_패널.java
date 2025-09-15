import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

public class C_패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JPanel panel;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;

	/**
	 * Create the panel.
	 */
	public C_패널(ImageIcon img, int lno, String mname, double per, String date) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setOpaque(true);
		label.setBackground(Color.PINK);
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		add(label, BorderLayout.NORTH);
		
		label_1 = new JLabel(img);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.CENTER);
		
		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		label_2 = new JLabel(mname);
		panel.add(label_2);
		
		label_3 = new JLabel(String.format("%.1f%% | 개봉일: %s", per, date));
		panel.add(label_3);
		
		label_4 = new JLabel(BF.getIcon("limits/"+lno+".png",35,35));
		label_4.setVerticalAlignment(SwingConstants.TOP);
		label_4.setPreferredSize(new Dimension(35, 0));
		add(label_4, BorderLayout.WEST);
		
		label_5 = new JLabel("");
		label_5.setPreferredSize(new Dimension(35, 0));
		add(label_5, BorderLayout.EAST);

	}

}
