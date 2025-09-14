import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class B_로그인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JTextField textField;
	public JTextField textField_1;
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
		setBounds(100, 100, 450, 445);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel();
		label.setText("Skills Qualification Association");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label.setBounds(88, 10, 251, 46);
		getContentPane().add(label);
		
		label_1 = new JLabel("LOGIN");
		label_1.setForeground(new Color(0, 0, 255));
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 92, 410, 69);
		getContentPane().add(label_1);
		
		textField = new PlaceHolder("아이디를 입력하세요!");
		textField.setBounds(44, 192, 339, 38);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new PlaceHolder("비밀번호를 입력하세요!");
		textField_1.setColumns(10);
		textField_1.setBounds(44, 240, 339, 38);
		getContentPane().add(textField_1);
		
		checkBox = new JCheckBox("아이디 저장");
		checkBox.setBackground(new Color(255, 255, 255));
		checkBox.setBounds(44, 314, 115, 23);
		getContentPane().add(checkBox);
		
		button = new JButton("로그인");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(new Color(0, 0, 255));
		button.setForeground(new Color(255, 255, 255));
		button.setBounds(126, 343, 169, 38);
		getContentPane().add(button);
		
		label_2 = new JLabel(getIcon("icon/logo.png",40,40));
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setBounds(12, 10, 57, 51);
		getContentPane().add(label_2);
		if(!prev.isBlank()) {
			textField.setText(prev);
		}

	}
	static String prev = "";
	public JLabel label_2;
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
					uno = rs.getInt(1);
					uname = rs.getString("uname");
					if(checkBox.isSelected())
						 prev = id;
					else
						 prev = "";
					msgInfo(uname+"님 환영합니다.");
					dispose();
					return;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try (var pre = pre("select * from teacher where tid = ? and tpw = ?")) {
				preSet(pre, id,pw);
				var rs = pre.executeQuery();
				if(rs.next()) {
					uno = rs.getInt(1);
					uname = rs.getString("tname");
					if(checkBox.isSelected())
						prev = id;
					else
						prev = "";
					isAdmin = true;
					msgInfo(uname+"님 환영합니다.");
					showPage(new L_선생님메인(),"L_선생님메인");
					return;
				}
				else {
					msgErr("아이디또는 비밀번호가 올바르지 않습니다.");
					textField.setText("");
					textField_1.setText("");
					textField.requestFocus();
					checkBox.setSelected(false);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
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
		jl.setEnabled(false);
		setLayout(new BorderLayout());
		jl.setFont(new Font("맑은 고딕",1,13));
		add(jl);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		jl.setVisible(getText().isBlank());
	}
}
