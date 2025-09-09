import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class B_로그인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JButton button;
	public JTextField textField;
	public JTextField textField_1;

	MainFrame mf;

	public B_로그인(MainFrame mf) {
		this.mf = mf;
		setTitle("\uB85C\uADF8\uC778");
		setBounds(100, 100, 378, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("Roupang");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 10, 362, 56);
		getContentPane().add(label);

		label_1 = new JLabel("ID");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label_1.setBounds(32, 76, 44, 36);
		getContentPane().add(label_1);

		label_2 = new JLabel("PW");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label_2.setBounds(32, 122, 44, 36);
		getContentPane().add(label_2);

		button = new JButton("\uB85C\uADF8\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(32, 168, 282, 36);
		getContentPane().add(button);

		textField = new JTextField();
		textField.setBounds(106, 88, 206, 24);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(106, 134, 206, 24);
		getContentPane().add(textField_1);

	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw = textField_1.getText();
			if (id.isBlank() || pw.isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			if (id.equals("admin") && pw.equals("1234")) {
				msgInfo("관리자님 환영합니다.");
				isAdmin =true;
				mf.showPage(new A_메인(), "메인");
				dispose();
				return;
			}
			try (var pre = pre("select * from user where uid = ? and upw= ?")) {
				preSet(pre, id, pw);
				var rs = pre.executeQuery();
				if (rs.next()) {
					uno = rs.getInt("uno");
					uname = rs.getString("uname");
					var most = res(
							"select cno,cnam, count(*) cnt from product  left join `order` using(pno) join category using(cno) where uno = "
									+ uno + " group by cno order by cnt desc limit 1;");
					if (most.next()) {
						setuiunset();
						if (JOptionPane.showConfirmDialog(null,
								"<html>" + uname + "님이 가장 많이 구매한 카테고리는 " + most.getString(2)
										+ "입니다.<br>해당 카테고리상품을 확인하시겠습니까?",
								"확인질문", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
							setuiset();
							mf.showPage(new C_검색(rs.getInt(1)), "검색");
							dispose();
						} else {
							setuiset();
							mf.showPage(new A_메인(), "메인");
							dispose();
						}
					} else {
						mf.showPage(new A_메인(), "메인");
						dispose();
					}
				} else {
					msgErr("없는 회원입니다.");
					textField.setText("");
					textField_1.setText("");
					textField.requestFocus();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
