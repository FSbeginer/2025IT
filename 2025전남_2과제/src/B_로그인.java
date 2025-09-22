import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class B_로그인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JTextField textField;
	public JTextField textField_1;
	public JButton button;
	public JCheckBox checkBox;
	public static String prev;
	public JLabel label_2;
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
		setBounds(100, 100, 420, 402);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel();
		label.setText("  Skills Qualification Association");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setBounds(93, 7, 229, 76);
		getContentPane().add(label);
		
		label_1 = new JLabel("LOGIN");
		label_1.setForeground(new Color(0, 0, 255));
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(75, 93, 245, 53);
		getContentPane().add(label_1);
		
		textField = new PlaceHolder("아이디를 입력하세요!");
		textField.setBounds(60, 156, 288, 37);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new PlaceHolder("비밀번호를 입력하세요!");
		textField_1.setColumns(10);
		textField_1.setBounds(60, 220, 288, 37);
		getContentPane().add(textField_1);
		
		button = new JButton("로그인");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(Color.BLUE);
		button.setForeground(new Color(255, 255, 255));
		button.setBounds(114, 296, 178, 37);
		getContentPane().add(button);
		
		checkBox = new JCheckBox("아이디 저장");
		checkBox.setBackground(Color.WHITE);
		checkBox.setBounds(58, 263, 115, 23);
		getContentPane().add(checkBox);
		
		label_2 = new JLabel(getIcon("icon/logo.png",60,60));
		label_2.setBounds(12, 10, 57, 62);
		label_2.addMouseListener(new LabelMouseListener());
		getContentPane().add(label_2);
		
		if(prev!=null)
			textField.setText(prev);
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw = textField_1.getText();
			if(id.isBlank()||pw.isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			try (var pre = pre("select * from user where id = ? and pw = ?")) {
				preSet(pre, id,pw);
				var rs = pre.executeQuery();
				if(rs.next()) {
					if(checkBox.isSelected()) prev = id;
					else prev ="";
					uname = rs.getString("uname");
					uno = rs.getInt("uno");
					msgInfo(uname+"님 환영합니다.");
					dispose();
				}
				else {
					var pre2 = pre("select * from teacher where tid = ? and tpw = ?");
					preSet(pre2, id,pw);
					var rs2 = pre2.executeQuery();
					if(rs2.next()) {
						if(checkBox.isSelected()) prev = id;
						else prev ="";
						uname = rs2.getString("uname");
						uno = rs2.getInt("uno");
						msgInfo(uname+"님 환영합니다.");
						showPage(new L_선생님메인(), "L_선생님메인");
					}
					else {
						msgErr("아이디또는 비밀번호가 올바르지 않습니다.");
						textField.setText("");
						textField_1.setText("");
						textField.requestFocus();
						checkBox.setSelected(false);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage("A_메인");
		}
	}
}
class PlaceHolder extends JTextField {
	JLabel jl;
	public PlaceHolder(String txt) {
		jl = new JLabel(txt);
		jl.setForeground(Color.gray);
		jl.setFont(new Font("맑은 고딕", 0, 12));
		setLayout(new BorderLayout());
		add(jl);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		jl.setVisible(getText().isBlank());
	}
}
