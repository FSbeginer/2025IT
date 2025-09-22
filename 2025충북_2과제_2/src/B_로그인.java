import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class B_로그인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JTextField textField;
	public JTextField textField_1;
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B_로그인 frame = new B_로그인();
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
	public B_로그인() {
		setTitle("로그인");
		setBounds(100, 100, 513, 295);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(" 로그인");
		label.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 34));
		label.setBounds(0, 0, 497, 57);
		getContentPane().add(label);
		
		label_1 = new JLabel("아이디");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label_1.setBounds(10, 67, 115, 47);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("비밀번호");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label_2.setBounds(10, 124, 115, 47);
		getContentPane().add(label_2);
		
		textField = new JTextField();
		textField.setBounds(115, 67, 357, 47);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(115, 124, 357, 47);
		getContentPane().add(textField_1);
		
		button = new JButton("로그인");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(377, 207, 97, 23);
		getContentPane().add(button);

	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw = textField_1.getText();
			if(id.isBlank()||pw.isBlank()) {
				msgErr("입력하지 않은 항목이 있습니다.");
				return;
			}
			if(id.equals("admin")&&pw.equals("1234")) {
				msgInfo("관리자님 환영합니다.");
				isAdmin = true;
				showPage(new C_영화검색(), "C_영화검색");
				return;
			}
			try (var pre = pre("select * from user where u_id = ? and u_pw = ?")) {
				preSet(pre, id,pw);
				var rs = pre.executeQuery();
				if(rs.next()) {
					uno = rs.getInt(1);
					ubirth = rs.getDate("u_birth").toLocalDate();
					msgInfo(rs.getString(2)+"회원님 환영합니다.");
					dispose();
				}
				else {
					msgErr("존재하는 회원이 없습니다.");
					textField.setText("");
					textField_1.setText("");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	}
}
