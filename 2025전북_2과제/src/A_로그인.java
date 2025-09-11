import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class A_로그인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JTextField textField;
	public JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A_로그인 frame = new A_로그인();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public A_로그인() {
		setTitle("\uB85C\uADF8\uC778");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("login");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 410, 59);
		getContentPane().add(label);
		
		label_1 = new JLabel("ID");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_1.setBounds(12, 79, 71, 48);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("PW");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_2.setBounds(12, 156, 71, 48);
		getContentPane().add(label_2);
		
		textField = new LineTextField();
		textField.addKeyListener(new TextFieldKeyListener());
		textField.setBounds(102, 90, 258, 32);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new LineTextField();
		textField_1.addKeyListener(new TextField_1KeyListener());
		textField_1.setColumns(10);
		textField_1.setBounds(102, 161, 258, 32);
		getContentPane().add(textField_1);

	}
	private class TextField_1KeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==e.VK_ENTER) {
				var id =textField.getText();
				var pw = textField_1.getText();
				if(id.isBlank()||pw.isBlank()) {
					msgErr("빈칸이 있씁니다.");
					return;
				}
				try {
					var pre = pre("select  * from user where id = ? and pw = ?");
					preSet(pre, id,pw);
					var rs = pre.executeQuery();
					if(rs.next()) {
						uno = rs.getInt(1);
						msgInfo(rs.getString("name")+" 회원님 환영합니다.");
						showPage(new B_메인(), "B_메인");
					}
					else {
						msgErr("아이디 또는 비밀번호를 확인해주세요.");
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	private class TextFieldKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() ==e.VK_ENTER) {
				textField_1.requestFocus();
			}
		}
	}
}
