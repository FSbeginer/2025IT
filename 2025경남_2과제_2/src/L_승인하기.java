import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class L_승인하기 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L_승인하기 frame = new L_승인하기(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	int apno;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JButton button;
	public JButton button_1;
	public L_승인하기(int apno) {
		getContentPane().setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		setTitle("\uC2B9\uC778\uD558\uAE30");
		this.apno = apno;
		setBounds(100, 100, 943, 475);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 205, 223);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_1.setBounds(229, 10, 205, 31);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_2.setBounds(229, 52, 288, 31);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_3.setBounds(229, 93, 288, 117);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_4.setVerticalAlignment(SwingConstants.TOP);
		label_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_4.setBounds(12, 253, 597, 147);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("New label");
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_5.setForeground(new Color(255, 128, 0));
		label_5.setOpaque(true);
		label_5.setBackground(Color.BLACK);
		label_5.setBounds(651, 10, 268, 306);
		getContentPane().add(label_5);
		
		button = new JButton("\uAC70\uC808\uD558\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 128, 0)));
		button.setBackground(new Color(255, 255, 255));
		button.setForeground(new Color(255, 128, 0));
		button.setBounds(661, 350, 123, 31);
		getContentPane().add(button);
		
		button_1 = new JButton("\uC2B9\uC778\uD558\uAE30");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(796, 350, 123, 31);
		getContentPane().add(button_1);

		load();
	}

	private void load() {
		try (var rs = res("select * from apply join job using(jno) join user using(uno) join brand using(bno) where apno = "+apno)) {
			rs.next();
			label.setIcon(getIcon("user/"+rs.getInt("uno")+".jpg",label.getWidth(),label.getHeight()));
			label_1.setText("[지원번호 : "+rs.getInt("apno")+"]");
			label_2.setText(rs.getString("apdate"));
			label_3.setText(String.format("<html>성명: %s<br>아이디: %s<br>성별: %s<br>생년월알: %s<br>학력: %s",  rs.getString("uname"),rs.getString("uid"),rs.getInt("ugender")==1?"남":"여",rs.getString("ubirth"),rs.getString("ugrade")));
			label_4.setText("<html>"+rs.getString("udetail"));
			label_5.setText(String.format("<html>%s<br><br>브랜드:%s<br>급여: %,d원<br>근무요일: 주 %d일<br>근무시간: %d시간<br>고용형태: %s<br><br>지원자격: %s<br>모집인원 %d명", rs.getString("jname"),rs.getString("bname"),rs.getInt("jmoney"),rs.getInt("jday"),rs.getInt("jtime"),rs.getInt("jwork")==1?"계약직":"정규직",getGrade(rs.getInt("jgrade")),rs.getInt("jpeople")));
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
				execute("update apply set apok = 1 where apno = "+apno);
				msgInfo("승인되었습니다.");
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
