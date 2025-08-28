import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
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
	public JButton button;

	MainFrame mf;
	public B_로그인(MainFrame mf) {
		this.mf = mf;
		setTitle("\uB85C\uADF8\uC778");
		setBounds(100, 100, 450, 321);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("Roupang");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 410, 63);
		getContentPane().add(label);
		
		label_1 = new JLabel("ID");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label_1.setBounds(45, 83, 42, 63);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("PW");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label_2.setBounds(45, 156, 42, 63);
		getContentPane().add(label_2);
		
		textField = new JTextField();
		textField.setBounds(132, 100, 263, 38);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(132, 169, 263, 38);
		getContentPane().add(textField_1);
		
		button = new JButton("\uB85C\uADF8\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(45, 217, 357, 34);
		getContentPane().add(button);

	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var id = textField.getText();
			var pw = textField_1.getText();
			if(id.isBlank()||pw.isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			if(id.equals("admin")&&pw.equals("1234")) {
				msgInfo("관리자님 환영합니다.");
				isAdmin= true;
				mf.showPage(new A_메인());
				mf.label.setIcon(getIcon("logo/메인.png",40,40,blue));
				dispose();
			}
			try (var pre = pre("select * from user where uid = ? and upw = ?")) {
				preSet(pre, id,pw);
				var rs = pre.executeQuery();
				if(rs.next()) {
					uno = rs.getInt(1);
					uname = rs.getString("uname");
					var cate = res("select *, sum(`order`.quantity) cnt from `order` join product using(pno) join category using(cno) where uno = "+uno+" group by cno order by cnt desc;");
					if(cate.next()) {
						setUIunset();
						if(JOptionPane.showConfirmDialog(null, "<html>"+uname+"님이 가장 많이 구매한 카테고리는 "+cate.getString("cnam")+"입니다.<br>해당 카테고리상품을 확인하시겠습니까?", "확인질문",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) {
							setUIset();
							int cno  =cate.getInt("cno");
							mf.showPage(new C_검색(cno));
							mf.label_1.setIcon(getIcon("logo/검색.png",40,40,blue));
						}
						else {
							mf.showPage(new A_메인());
							mf.label.setIcon(getIcon("logo/메인.png",40,40,blue));
						}
					}
					else {
						mf.showPage(new A_메인());
						mf.label.setIcon(getIcon("logo/메인.png",40,40,blue));
					}
					dispose();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
