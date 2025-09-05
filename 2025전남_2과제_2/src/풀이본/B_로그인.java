package 풀이본;
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
	public PlaceHolder textField_1;
	public JCheckBox checkBox;
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
		setBounds(100, 100, 406, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new MainLogo();
		label.setBounds(12, 10, 78, 62);
		getContentPane().add(label);
		
		label_1 = new JLabel("Skills Qualification Association");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_1.setBounds(102, 10, 259, 57);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("LOGIN");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		label_2.setForeground(Color.BLUE);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(43, 119, 318, 62);
		getContentPane().add(label_2);
		
		textField = new PlaceHolder("아이디를 입력하세요!");
		textField.setBounds(43, 191, 318, 38);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new PlaceHolder("비밀번호를 입력하세요!");
		textField_1.setColumns(10);
		textField_1.setBounds(43, 250, 318, 38);
		getContentPane().add(textField_1);
		
		checkBox = new JCheckBox("아이디 저장");
		checkBox.setBackground(Color.WHITE);
		checkBox.setBounds(43, 294, 115, 23);
		getContentPane().add(checkBox);
		
		button = new JButton("로그인");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(Color.BLUE);
		button.setForeground(Color.WHITE);
		button.setBounds(102, 343, 184, 28);
		getContentPane().add(button);

		if(!prev.isBlank())
			textField.setText(prev);
	}
	static String prev = "";
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw =textField_1.getText();
			if(id.isBlank()||pw.isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			try (var pre = pre("select * from user where id = ? and pw = ?")) {
				preSet(pre, id, pw);
				var rs =pre.executeQuery();
				
				if(rs.next()) {
					uno =rs.getInt(1);
					if(checkBox.isSelected()) prev = id;
					else prev = "";
					msgInfo(rs.getString(2)+"님 환영합니다.");
					dispose();
				}
				else {
					var pre2 = pre("select * from teacher where tid = ? and tpw = ?");
					preSet(pre2, id,pw);
					var rs2 = pre2.executeQuery();
					if(rs2.next()) {
						uno =rs2.getInt(1);
						isAdmin =true;
						if(checkBox.isSelected()) prev = id;
						else prev = "";
						msgInfo(rs2.getString(2)+"님 환영합니다.");
						showPage(new L_선생님메인(),"L_선생님메인");
					}
					else {
						msgErr("아이디또는 비밀번호가 올바르지 않습니다.");
						textField.setText("");
						textField_1.setText("");
						checkBox.setSelected(false);
					}
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
