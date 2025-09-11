import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class B_로그인 extends BP {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JButton button;
	public JTextField textField;
	public JTextField textField_1;
	public JCheckBox checkBox;

	/**
	 * Create the panel.
	 */
	public B_로그인() {

		panel = new JPanel();
		panel.setBounds(116, 108, 728, 297);
		add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBackground(BF.blue);
		panel_1.setBounds(0, 0, 189, 338);
		panel.add(panel_1);
		panel_1.setLayout(null);

		label = new JLabel(getLogo(189, 189));
		label.setBounds(0, 0, 189, 248);
		panel_1.add(label);

		label_1 = new JLabel("LOGIN");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 258, 167, 34);
		panel_1.add(label_1);

		button = new RoundButton("\uB85C\uADF8\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(BF.blue);
		button.setForeground(Color.white);
		button.setBounds(277, 247, 340, 40);
		panel.add(button);

		textField = new PlaceHolder("Id");
		textField.setBounds(277, 71, 340, 33);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new PlaceHolder("Password");
		textField_1.setColumns(10);
		textField_1.setBounds(277, 133, 340, 33);
		panel.add(textField_1);

		checkBox = new JCheckBox("\uC544\uC774\uB514 \uC800\uC7A5\uD558\uAE30");
		checkBox.setBackground(Color.WHITE);
		checkBox.setBounds(277, 188, 115, 23);
		panel.add(checkBox);

		label_2 = new JLabel(getIcon("메인/5.png", 1007, 514));
		label_2.setBounds(0, 0, 1007, 514);
		add(label_2);

		if (prev.isBlank()) {
			textField.setText(prev);
		}
	}

	static String prev = "";

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw =textField_1.getText();
			if(id.equals("admin")&&pw.equals("1234")) {
				BF.isAdmin = true;
				msgInfo("관리자님, 환영합니다.");
				getmf(button).showpage(new A_메인(), "A_메인");
				return;
			}
			try (var pre = pre("select * from user where id = ? and pw = ?")) {
				preSet(pre, id,pw);
				var rs = pre.executeQuery();
				if(rs.next()) {
					BF.uno = rs.getInt("uno");
					msgInfo(rs.getString("name")+"님, 환영합니다.");	
					getmf(button).showpage(new A_메인(), "A_메인");
				}
				else {
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
