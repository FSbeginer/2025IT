import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class C_ListPanel extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JButton button;
	public JButton button_1;
	public JButton button_2;

	/**
	 * Create the panel.
	 * @param cname 
	 * @param teacher 
	 * @param quali 
	 * @param str 
	 * @param str2 
	 */
	public C_ListPanel(ImageIcon img, String cname, String teacher, String quali, String str, String str2) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(880, 200);
		setLayout(null);
		
		label = new JLabel(img);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(27, 29, 207, 144);
		add(label);
		
		label_1 = new JLabel(cname);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_1.setBounds(249, 29, 295, 31);
		add(label_1);
		
		label_2 = new JLabel(String.format("<html><pre><font color = blue>ㆍ</font><font color = gray>담당교수 </font>%s   </font><font color = blue>ㆍ</font><font color = gray>응시조건 </font>%s<br><font color = blue>ㆍ</font><font color = gray>주무부처 </font>%s    <font color = blue>ㆍ</font><font color = gray>발급기관 </font>%s<br><font color = blue>ㆍ</font> <font color = red>교안무료+시험예상기출문제 제공", teacher+" 교수", quali, str, str2));
		label_2.setFont(new Font("굴림", Font.PLAIN, 14));
		label_2.setBounds(246, 56, 450, 117);
		add(label_2);
		
		button = new JButton("상세내용보기");
		button.setBounds(718, 45, 150, 31);
		add(button);
		
		button_1 = new JButton("과목선택하기");
		button_1.setBounds(718, 95, 150, 31);
		add(button_1);
		
		button_2 = new JButton("기출문제 맛보기");
		button_2.setBounds(718, 142, 150, 31);
		add(button_2);
	}

}
