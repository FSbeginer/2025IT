import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class C_ImageListPanel extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPopupMenu popupMenu;
	public JMenuItem menuItem_1;
	public JMenuItem menuItem_2;
	public JMenuItem menuItem;

	/**
	 * Create the panel.
	 * @param cname 
	 * @param teacher 
	 * @param str 
	 * @param str2 
	 */
	public C_ImageListPanel(ImageIcon img, String cname, String teacher, String quali,String str, String str2) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(292, 358);
		setLayout(null);
		
		label = new JLabel(img);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(37, 10, 213, 133);
		add(label);
		
		label_1 = new JLabel(cname);
		label_1.setFont(new Font("굴림", Font.BOLD, 16));
		label_1.setBounds(12, 153, 268, 32);
		add(label_1);
		
		label_2 = new JLabel(String.format("<html><font color = blue>ㆍ</font><font color = gray>담당교수 </font>%s  </font><font color = blue>ㆍ</font><font color = gray>응시조건 </font>%s<br><font color = blue>ㆍ</font><font color = gray>주무부처 </font>%s    <font color = blue>ㆍ</font><font color = gray>발급기관 </font>%s<br><font color = blue>ㆍ</font> <font color = red>교안무료+시험예상기출문제 제공", teacher+" 교수", quali, str, str2));
		label_2.setFont(new Font("굴림", Font.PLAIN, 16));
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setBounds(12, 187, 268, 161);
		add(label_2);
		
		popupMenu = new JPopupMenu();
		popupMenu.setBounds(0, 0, 200, 50);
		add(popupMenu);
		
		menuItem = new JMenuItem("상세보기");
		popupMenu.add(menuItem);
		
		menuItem_1 = new JMenuItem("선택하기");
		popupMenu.add(menuItem_1);
		
		menuItem_2 = new JMenuItem("맛보기");
		popupMenu.add(menuItem_2);
		setComponentPopupMenu(popupMenu);
	}
}
