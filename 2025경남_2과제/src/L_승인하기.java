import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class L_승인하기 extends BF {

	int apno;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JButton button;
	public JButton button_1;
	public L_승인하기(int apno) {
		setTitle("\uC2B9\uC778\uD558\uAE30");
		this.apno = apno;
		setBounds(100, 100, 764, 407);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 170, 209);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(12, 229, 448, 129);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_2.setBounds(194, 10, 291, 74);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_3.setBounds(194, 94, 241, 119);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_4.setForeground(new Color(255, 128, 0));
		label_4.setBackground(Color.BLACK);
		label_4.setOpaque(true);
		label_4.setBounds(497, 10, 239, 305);
		getContentPane().add(label_4);
		
		button = new JButton("\uC2B9\uC778\uD558\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(new Color(255, 128, 0));
		button.setBorder(new LineBorder(new Color(255, 128, 0)));
		button.setForeground(Color.WHITE);
		button.setBounds(621, 325, 112, 33);
		getContentPane().add(button);
		
		button_1 = new JButton("\uAC70\uC808\uD558\uAE30");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBorder(new LineBorder(new Color(255, 128, 0)));
		button_1.setBackground(Color.WHITE);
		button_1.setForeground(new Color(255, 128, 0));
		button_1.setBounds(497, 325, 112, 33);
		getContentPane().add(button_1);

		
		load();
	}
	private void load() {
		try (var rs = res("select * from apply join job using(jno) join user using(uno) join brand using(bno) where apno = "+apno)) {
			rs.next();
			label.setIcon(getIcon("user/"+uno+".jpg",label.getWidth(),label.getHeight()));
			label_2.setText(String.format("<html>[지원번호: %d]<br>지원 날짜: %s", apno,rs.getString("apdate")));
			label_3.setText(String.format("<html>성명: %s<br>아이디: %s<br>성별:%s<br>생년월일:%s<br>학력:%s", rs.getString("uname"), rs.getString("uid"), rs.getInt("ugender")==1?"남자":"여자", rs.getString("ubirth"), rs.getString("ugrade")));
			label_1.setText("<html>"+rs.getString("udetail"));
			label_4.setText(String.format("<html>%s<br><br>브랜드:%s<br>급여:%,d원<br>근무요일:주 %d일<br>근무시간:%d시간<br>고용형태:%s<br><br>지원자격:%s<br>모집인원:%d명", rs.getString("jname"), rs.getString("bname"), rs.getInt("jmoney"), rs.getInt("jday"), rs.getInt("jtime"), rs.getInt("jwork")==1?"계약직":"정규직", rs.getInt("jgrade")==0?"무관":rs.getInt("jgrade")==1?"대학":"고등",rs.getInt("jpeople")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				execute("update apply set apok = 1 where apno = "+apno);
				msgInfo("승인되었습니다.");
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
