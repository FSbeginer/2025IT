import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class A_로그인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JTextField textField;
	public JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A_로그인 frame = new A_로그인();
					frame.setName("A_로그인");
					frame.setLocationRelativeTo(null);
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
	public A_로그인() {
		setTitle("\uB85C\uADF8\uC778");
		setBounds(100, 100, 450, 234);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("login");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(120, 10, 197, 47);
		getContentPane().add(label);

		label_1 = new JLabel("ID");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label_1.setBounds(38, 73, 39, 30);
		getContentPane().add(label_1);

		label_2 = new JLabel("PW");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label_2.setBounds(38, 129, 39, 30);
		getContentPane().add(label_2);

		textField = new JTextField();
		textField.addKeyListener(new TextFieldKeyListener());
		textField.setBounds(96, 81, 260, 22);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, blue));
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.addKeyListener(new TextField_1KeyListener());
		textField_1.setBorder(new MatteBorder(0, 0, 1, 0, blue));
		textField_1.setBounds(97, 137, 259, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setFocusable(false);
	}

	private class TextField_1KeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == e.VK_ENTER) {
				var id = textField.getText();
				var pw = textField_1.getText();
				if(id.isBlank()||pw.isBlank()) {
					msgErr("빈칸이 있습니다.");
					clear();
					return;
				}
				try {
					var pre = pre("select * from user where id = ? and pw = ?");
					preSet(pre, id,pw);
					var rs = pre.executeQuery();
					if(rs.next()) {
						clear();
						uno = rs.getInt(1);
						uname =rs.getString(2);
						msgInfo(uname+" 회원님 환영합니다.");
						showPage(new B_메인(), "B_메인");
					}
					else {
						msgErr("아이디 또는 비밀번호를 확인해주세요.");
						clear();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		private void clear() {
			textField.requestFocus();
			textField.setFocusable(true);
			textField_1.setFocusable(false);
		}
	}

	private class TextFieldKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == e.VK_ENTER) {
				textField_1.requestFocus();
				textField_1.setFocusable(true);
				textField.setFocusable(false);
			}
		}
	}
}
