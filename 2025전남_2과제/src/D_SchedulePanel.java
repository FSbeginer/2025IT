import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class D_SChedulePanel extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JButton button;

	/**
	 * Create the panel.
	 * @param name 
	 * @param esdate 
	 * @param eedate 
	 * @param ssdate 
	 * @param sedate 
	 * @param cgname 
	 * @param location 
	 * @param explain 
	 * @param rating, 
	 */
	public D_SChedulePanel(ImageIcon img, String name, int rating, LocalDate edate, LocalDate sdate, String cgname, String location, String explain) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(649, 227);
		setLayout(null);
		
		label = new JLabel(img);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(43, 26, 162, 151);
		add(label);
		
		label_1 = new JLabel(String.format("<html>%s %d급   %s~%s<br>신청일자:%s~%s<br>분류:%s<br>장소:%s<br>정의:%s", name, rating, edate, edate.plusDays(7), sdate, sdate.plusDays(5), cgname, location, explain));
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setBounds(221, 38, 281, 167);
		add(label_1);
		
		button = new JButton("신청하러 가기");
		button.setBackground(Color.BLUE);
		button.setForeground(new Color(255, 255, 255));
		button.setBounds(514, 182, 123, 23);
		add(button);
	}
}
