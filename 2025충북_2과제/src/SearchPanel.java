import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class SearchPanel extends JPanel {
	public JLabel lblImg;
	public JLabel lblAge;
	public JLabel label_2;
	public JLabel lblTxt;
	public JLabel lblNo;

	/**
	 * Create the panel.
	 */
	public SearchPanel(ImageIcon img, ImageIcon age, String mname, double percent, String date) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		lblImg = new JLabel(img);
		add(lblImg, BorderLayout.CENTER);
		
		lblAge = new JLabel(age);
		lblAge.setPreferredSize(new Dimension(40, 0));
		lblAge.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge.setVerticalAlignment(SwingConstants.TOP);
		add(lblAge, BorderLayout.WEST);
		
		label_2 = new JLabel("");
		label_2.setPreferredSize(new Dimension(40, 0));
		add(label_2, BorderLayout.EAST);
		
		lblTxt = new JLabel(String.format("<html>%s<br>%.1f%% | 개봉일: %s", mname, percent, date));
		lblTxt.setPreferredSize(new Dimension(57, 50));
		add(lblTxt, BorderLayout.SOUTH);
		
		lblNo = new JLabel();
		lblNo.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNo.setForeground(new Color(255, 255, 255));
		lblNo.setBackground(new Color(255, 182, 147));
		lblNo.setOpaque(true);
		lblNo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNo, BorderLayout.NORTH);

	}

}
