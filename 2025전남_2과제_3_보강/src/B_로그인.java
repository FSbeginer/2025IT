import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
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
	public JCheckBox checkBox;
	public JButton button;
	static String prev = "";

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
		setTitle("\uB85C\uADF8\uC778");
		setBounds(100, 100, 391, 419);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new Logo(50, 50, true);
		label.setBounds(12, 10, 57, 49);
		getContentPane().add(label);

		label_1 = new JLabel("SKills Qualification Association");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(90, 16, 220, 43);
		getContentPane().add(label_1);

		label_2 = new JLabel("LOGIN");
		label_2.setForeground(new Color(18, 36, 199));
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 88, 375, 56);
		getContentPane().add(label_2);

		textField = new PlaceHolder("아이디를 입력하세요!");
		textField.setBounds(44, 154, 286, 35);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new PlaceHolder("비밀번호를 입력하세요!");
		textField_1.setColumns(10);
		textField_1.setBounds(44, 211, 286, 35);
		getContentPane().add(textField_1);

		checkBox = new JCheckBox("\uC544\uC774\uB514 \uC800\uC7A5");
		checkBox.setBackground(new Color(255, 255, 255));
		checkBox.setBounds(44, 262, 115, 23);
		getContentPane().add(checkBox);

		button = new JButton("\uB85C\uADF8\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(18, 36, 199));
		button.setBounds(104, 311, 170, 35);
		getContentPane().add(button);

		if (!prev.isBlank()) {
			textField.setText(prev);
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw = textField_1.getText();
			if (id.isBlank() || pw.isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			try (var pre = pre("select * from user where id = ? and pw = ?")) {
				preSet(pre, id, pw);
				var rs = pre.executeQuery();
				if (rs.next()) {
					uno = rs.getInt(1);
					uname =rs.getString("uname");
					msgInfo(uname+"님 환영합니다.");
					if(checkBox.isSelected()) prev = id;
					else prev ="";
					dispose();
				} else {
					var pre2 = pre("select * from teacher where tid = ? and tpw = ?");
					preSet(pre2, id, pw);
					var rs2 = pre2.executeQuery();
					if (rs2.next()) {
						uno =rs2.getInt(1);
						isAdmin = true;
						msgInfo(rs2.getString("tname")+"님 환영합니다.");
						if(checkBox.isSelected()) prev = id;
						else prev ="";
						showPage(new L_선생님메인(),"L_선생님메인");
					} else {
						msgErr("아이디또는 비밀번호가 올바르지 않습니다.");
						textField.setText("");
						textField_1.setText("");
						checkBox.setSelected(false);
						return;
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
