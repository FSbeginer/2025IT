import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginForm extends BF {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JTextField textField;
	public JTextField textField_1;
	public JButton button;

	public LoginForm() {
		setTitle("로그인");
		setBounds(100, 100, 446, 266);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("  로그인");
		label.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		getContentPane().add(label, BorderLayout.NORTH);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		label_1 = new JLabel("아이디");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(12, 21, 111, 42);
		panel.add(label_1);
		
		label_2 = new JLabel("비밀번호");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_2.setBounds(12, 84, 111, 42);
		panel.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(70, 21, 348, 38);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(70, 84, 348, 38);
		panel.add(textField_1);
		
		button = new JButton("로그인");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(321, 144, 97, 23);
		panel.add(button);

	}
	@Override
	public void updateForm() {
		isAdmin = false;
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
				showPage(new MovieSearch(),"MovieSearch");
				return;
			}
			try (var pre = pre("select * from user where u_id = ? and u_pw = ?")) {
				preSet(pre, id, pw);
				var rs = pre.executeQuery();
				if(rs.next()) {
					uno = rs.getInt("u_no");
					msgInfo(rs.getString("u_name")+"회원님 환영합니다.");
					dispose();
				}
				else {
					msgErr("존재하는 회원이 없습니다.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
