import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
	public JPanel panel;
	public JPanel panel_1;
	public JTextField textField;
	public JTextField textField_1;
	public JCheckBox checkBox;
	public JButton button;
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setSize(681, 250);
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(BF.blue);
		panel.setPreferredSize(new Dimension(180, 10));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(BF.getLogoIcon(180, 180));
		panel.add(label, BorderLayout.CENTER);
		
		label_1 = new JLabel("LOGIN");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setPreferredSize(new Dimension(57, 70));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1, BorderLayout.SOUTH);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textField = new PlaceHolder("id");
		textField.setBounds(70, 39, 358, 36);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new PlaceHolder("Password");
		textField_1.setColumns(10);
		textField_1.setBounds(70, 85, 358, 36);
		panel_1.add(textField_1);
		
		checkBox = new JCheckBox("\uC544\uC774\uB514 \uAE30\uC5B5\uD558\uAE30");
		checkBox.setBackground(new Color(255, 255, 255));
		checkBox.setBounds(70, 131, 115, 23);
		panel_1.add(checkBox);
		
		button = new RoundButton("로그인");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(70, 173, 358, 36);
		panel_1.add(button);
		if(BF.id!=null)
			textField.setText(BF.id);
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw = textField_1.getText();
			if(id.equals("admin")&&pw.equals("1234")) {
				BF.isAdmin = true;
				BF.msgInfo("관리자님, 환영합니다.");
				((MainFrame)SwingUtilities.getWindowAncestor(getParent())).showPage(new PageMain());
				return;
			}
			try (var pre = BF.pre("select * from user where id = ? and pw = ?")) {
				BF.preSet(pre, id, pw);
				var rs = pre.executeQuery();
				if(rs.next()) {
					BF.uno = rs.getInt("uno");
					if(checkBox.isSelected()) BF.id = rs.getString("id");
					else BF.id = null;
					BF.msgInfo(rs.getString("name")+"님, 환영합니다.");
					((MainFrame)SwingUtilities.getWindowAncestor(getParent())).showPage(new PageMain());
				}
				else {
					BF.msgErr("아이디 또는 비밀번호를 확인하세요.");
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
