import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

public class C_패널 extends JPanel {
	public JLabel lblNO;
	public JLabel label_2;
	public JLabel lblImg;
	public JPanel panel;
	public JLabel lblName;
	public JLabel lblInfo;

	/**
	 * Create the panel.
	 */
	public C_패널(int limit, ImageIcon img, String name, double per, String date) {
		setLayout(new BorderLayout(0, 0));
		
		lblNO = new JLabel("");
		lblNO.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNO.setForeground(Color.WHITE);
		lblNO.setBackground(Color.PINK);
		lblNO.setOpaque(true);
		lblNO.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNO, BorderLayout.NORTH);
		
		JLabel lblAge = new JLabel(BF.getIcon("limits/"+limit+".png",30,30));
		lblAge.setVerticalAlignment(SwingConstants.TOP);
		lblAge.setPreferredSize(new Dimension(30, 0));
		add(lblAge, BorderLayout.WEST);
		
		label_2 = new JLabel("");
		label_2.setPreferredSize(new Dimension(30, 0));
		add(label_2, BorderLayout.EAST);
		
		lblImg = new JLabel(img);
		add(lblImg, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 30));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		lblName = new JLabel(name);
		lblName.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		panel.add(lblName);
		
		lblInfo = new JLabel(String.format("%.1f%% | 개봉일 : %s", per, date));
		lblInfo.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		panel.add(lblInfo);
		
	}

}
