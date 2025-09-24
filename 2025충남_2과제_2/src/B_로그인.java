import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class B_로그인 extends BP {
	public JPanel panel;
	public JLabel label;
	public JPanel panel_1;
	public JLabel label_1;
	public JLabel label_2;
	public JTextField textField;
	public PlaceHolder textField_1;
	public JCheckBox checkBox;
	public JButton button;
	static String  prev =""; 
	/**
	 * Create the panel.
	 */
	public B_로그인() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(59, 49, 781, 286);
		add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 195, 286);
		panel_1.setBackground(blue);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		label_1 = new JLabel(getLogoIcon(195, 210));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1, BorderLayout.CENTER);
		
		label_2 = new JLabel("LOGIN");
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setPreferredSize(new Dimension(57, 70));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_2, BorderLayout.SOUTH);
		
		textField = new PlaceHolder("Id");
		textField.setBounds(252, 43, 460, 41);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new PlaceHolder("Password");
		textField_1.setColumns(10);
		textField_1.setBounds(252, 124, 460, 41);
		panel.add(textField_1);
		
		checkBox = new JCheckBox("\uC544\uC774\uB514 \uAE30\uC5B5\uD558\uAE30");
		checkBox.setBackground(new Color(255, 255, 255));
		checkBox.setBounds(252, 171, 175, 23);
		panel.add(checkBox);
		
		button = new RoundButton("\uB85C\uADF8\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(248, 214, 464, 34);
		panel.add(button);
		
		label = new JLabel(getIcon("메인/5.png",getWidth(),getHeight()));
		label.setBounds(0, 0, 998, 427);
		add(label);
		
		if(!prev.isBlank())
			textField.setText(prev);
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw = textField_1.getText();
			
			if(id.equals("admin")&&pw.equals("1234")) {
				msgInfo("관리자님, 환영합니다.");
				BF.isAdmin = true;
				((MainFrame)SwingUtilities.getWindowAncestor(checkBox)).showPage(new A_메인(),"메인");
				return;
			}
			
			try (var pre = pre("select * from user where id = ? and pw = ?")) {
				preSet(pre, id,pw);
				var rs = pre.executeQuery();
				if(rs.next()) {
					msgInfo(rs.getString("name")+"님, 환영합니다.");
					BF.uno = rs.getInt("uno");
					((MainFrame)SwingUtilities.getWindowAncestor(checkBox)).showPage(new A_메인(),"메인");
				}
				else {
					msgErr("아이디 또는 비밀번호를 확인하세요.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
