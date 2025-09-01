import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class A_로그인 extends BF {
	public JLabel label;
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
		setBounds(100, 100, 623, 214);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("\uC54C\uBC14\uCEA3");
		label.setForeground(new Color(255, 128, 0));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 583, 57);
		getContentPane().add(label);
		
		textField = new PlaceHolder("ID");
		textField.setBounds(22, 77, 265, 37);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new PlaceHolder("PW");
		textField_1.setColumns(10);
		textField_1.setBounds(330, 77, 265, 37);
		getContentPane().add(textField_1);
		
		button = new JButton("\uB85C\uADF8\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(22, 135, 573, 30);
		getContentPane().add(button);

	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw = textField_1.getText();
			if(id.equals("admin")&&pw.equals("1234")) {
				msgInfo("관리자로 로그인하였습니다.");
				isAdmin = true;
				showPage(new K_관리자메인(), "K_관리자메인");
				textField.setText("");
				textField_1.setText("");
				return;
			}
				
			try (var pre = pre("select * from user where uid = ? and upw=  ?")) {
				preSet(pre, id,pw);
				var rs = pre.executeQuery();
				if(rs.next()) {
					uno = rs.getInt(1);
					msgInfo(rs.getString("unick")+" 회원님 환영합니다.");
					showPage(new B_메인(),"B_메인");
					textField.setText("");
					textField_1.setText("");
				}
				else {
					msgErr("일치하는 회원 정보가 없습니다.");
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
