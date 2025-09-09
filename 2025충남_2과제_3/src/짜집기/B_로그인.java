package 짜집기;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class B_로그인 extends BP {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	public JTextField textField;
	public JTextField textField_1;
	public JButton button;
	public JCheckBox checkBox;
	static String prev = "";
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public B_로그인() {

		panel = new JPanel();
		panel.setBounds(103, 100, 738, 274);
		add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBackground(BF.blue);
		panel_1.setBounds(0, 0, 206, 274);
		panel.add(panel_1);
		panel_1.setLayout(null);

		label = new JLabel(BF.getLogoImage(206, 186));
		label.setBounds(0, 0, 206, 186);
		panel_1.add(label);

		label_1 = new JLabel("LOGIN");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(34, 212, 141, 28);
		panel_1.add(label_1);

		textField = new PlaceHolder("Id");
		textField.setBounds(272, 53, 385, 32);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new PlaceHolder("Password");
		textField_1.setColumns(10);
		textField_1.setBounds(272, 95, 385, 32);
		panel.add(textField_1);

		button = new RoundButton("\uB85C\uADF8\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(BF.blue);
		button.setBounds(272, 203, 385, 42);
		panel.add(button);

		checkBox = new JCheckBox("\uC544\uC774\uB514 \uAE30\uC5B5\uD558\uAE30");
		checkBox.setBackground(new Color(255, 255, 255));
		checkBox.setBounds(272, 149, 115, 23);
		panel.add(checkBox);

		label_2 = new JLabel(getIcon("메인/5.png", 962, 496));
		label_2.setBounds(0, 0, 962, 496);
		add(label_2);

		if (!prev.isBlank()) {
			textField.setText(prev);
		}
	}


	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw = textField_1.getText();
			if (id.equals("admin") && pw.equals("1234")) {
				msgInfo("관리자님, 환영합니다.");
				BF.isAdmin = true;
				((MainFrame) SwingUtilities.getWindowAncestor(label)).showPage(new A_메인(), "메인");
				return;
			}
			try (var pre = pre("select * from user where id = ? and pw = ?")) {
				preSet(pre, id, pw);
				var rs = pre.executeQuery();
				if (rs.next()) {
					if (checkBox.isSelected())
						prev = id;
					BF.uno = rs.getInt(1);
					msgInfo(rs.getString(4) + "님, 환영합니다.");
					((MainFrame) SwingUtilities.getWindowAncestor(label)).showPage(new A_메인(), "메인");
				} else {
					msgErr("아이디 또는 비밀전호를 확인하세요.");
					return;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
