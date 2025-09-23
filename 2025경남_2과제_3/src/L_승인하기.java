import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class L_승인하기 extends BF {

	int apno;
	public JLabel label;
	public JTextArea textArea;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public JButton button;
	public JButton button_1;
	public L_승인하기(int apno) {
		setTitle("\uC2B9\uC778\uD558\uAE30");
		this.apno = apno;
		setBounds(100, 100, 741, 430);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 187, 230);
		getContentPane().add(label);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(12, 250, 487, 125);
		getContentPane().add(textArea);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_1.setBounds(211, 10, 161, 45);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_2.setBounds(211, 65, 152, 24);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_3.setBounds(211, 99, 152, 24);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_4.setBounds(211, 133, 152, 24);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("New label");
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_5.setBounds(211, 164, 152, 24);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("New label");
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_6.setBounds(211, 198, 152, 24);
		getContentPane().add(label_6);
		
		label_7 = new JLabel("New label");
		label_7.setOpaque(true);
		label_7.setBackground(new Color(0, 0, 0));
		label_7.setForeground(new Color(255, 128, 0));
		label_7.setBounds(511, 27, 202, 303);
		getContentPane().add(label_7);
		
		button = new JButton("\uAC70\uC808\uD558\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBorder(new LineBorder(new Color(255, 128, 0)));
		button.setBackground(new Color(255, 255, 255));
		button.setForeground(new Color(255, 128, 0));
		button.setBounds(511, 352, 97, 23);
		getContentPane().add(button);
		
		button_1 = new JButton("\uC2B9\uC778\uD558\uAE30");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(616, 352, 97, 23);
		getContentPane().add(button_1);
		
		load();
	}
	private void load() {
		try (var rs = res("select * from apply join user using(uno) join job join brand join category where apno = "+apno)) {
			rs.next();
			label.setIcon(getIcon("user/"+rs.getInt("uno")+".jpg",label.getWidth(),label.getHeight()));
			label_1.setText(String.format("<html>[지원번호 : %d]<br>지원 날짜 : %s", apno, rs.getString("apdate")));
			label_2.setText("성명: "+rs.getString("uname"));
			label_3.setText("아이디: "+rs.getString("uid"));
			label_4.setText("성별: "+(rs.getInt("ugender")==1?"남":"여"));
			label_5.setText("생년월일: "+rs.getString("ubirth"));
			label_6.setText("학력: "+rs.getString("ugrade"));
			textArea.setText(rs.getString("udetail"));
			label_7.setText( String.format( "<html>%s<br><br>브랜드: %s<br>급여: %,d<br>근무요일: 주 %d일<br>근무시간: %d시간<br>고용형태: %s<br><br>지원자격: %s<br>모집인원: %d명", rs.getString("jname"),rs.getString("bname"),rs.getInt("jmoney"),rs.getInt("jday"),rs.getInt("jtime"),rs.getInt("jwork")==1?"계약직":"정규직", getGrade(rs.getInt("jgrade")),rs.getInt("jpeople") ) );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				execute("update apply set apok = 1 where apno ="+apno);
				msgInfo("승인되었습니다.");
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
