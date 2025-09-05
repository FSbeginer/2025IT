package 풀이본;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class F_결제 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_결제 frame = new F_결제(1);
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
	int cno , sno;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	public JLabel label_3;
	public JLabel label_4;
	public JButton button;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	public JTextField textField_5;
	public JLabel label_5;
	public JLabel label_6;
	public JTextField textField_6;
	public JLabel label_7;
	private int p;
	
	public F_결제(int scno, LocalDate seldate, LocalTime seltime) {
		this(0);
		
	}
	
	public F_결제(int cno) {
		setTitle("결제하기");
		this.cno = cno;
		setBounds(100, 100, 658, 345);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("카드번호");
		label.setFont(new Font("굴림", Font.PLAIN, 15));
		label.setBounds(28, 25, 110, 39);
		getContentPane().add(label);
		
		label_1 = new JLabel("주민등록번호");
		label_1.setFont(new Font("굴림", Font.PLAIN, 15));
		label_1.setBounds(28, 91, 110, 39);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("비밀번호");
		label_2.setFont(new Font("굴림", Font.PLAIN, 15));
		label_2.setBounds(28, 161, 110, 39);
		getContentPane().add(label_2);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(38, 210, 572, 1);
		getContentPane().add(panel);
		
		label_3 = new JLabel("New label");
		label_3.setBounds(28, 221, 331, 23);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(384, 225, 225, 19);
		getContentPane().add(label_4);
		
		button = new JButton("결제하기");
		button.addMouseListener(new ButtonMouseListener());
		button.setBorder(new RoundBorder(Color.black));
		button.setBackground(SystemColor.control);
		button.setBounds(467, 260, 143, 33);
		getContentPane().add(button);
		
		textField = new JTextField();
		textField.setBounds(143, 25, 94, 33);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(265, 25, 94, 33);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(380, 25, 94, 33);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(498, 25, 94, 33);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(143, 91, 187, 33);
		getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(360, 91, 39, 33);
		getContentPane().add(textField_5);
		
		label_5 = new JLabel("-");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(329, 91, 30, 33);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("●●●●●●");
		label_6.setBounds(411, 91, 157, 33);
		getContentPane().add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(143, 167, 94, 33);
		getContentPane().add(textField_6);
		
		label_7 = new JLabel("●●");
		label_7.setBounds(242, 167, 157, 33);
		getContentPane().add(label_7);
		
		if(cno!=0)
			load();
	}
	private void load() {
		try (var rs = res("select *, curdate() < sale_end_date as flag from certi left join sale using(cno) where cno = "+cno)) {
			rs.next();
			p = rs.getInt("caftprice");
			if(rs.getBoolean("flag")) {
				p -=(int)(rs.getInt("caftprice")*rs.getInt("sale")/100.0);
			}
			label_3.setText(String.format("<html>원가 %,d원 - <font color = red>할인 가격 %,d원=", rs.getInt("caftprice"), rs.getBoolean("flag")? (int)(rs.getInt("caftprice")*rs.getInt("sale")/100.0): 0));
			label_4.setText("<html><font color = blue>최종결제금액: "+String.format("%,d원", p));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class ButtonMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			String card = textField.getText()+"-"+textField_1.getText()+"-"+textField_2.getText()+"-"+textField_3.getText();
			try (var rs = res("select * from user where uno = "+uno)) {
				rs.next();
				if(isempty(textField,textField_1,textField_2,textField_3,textField_4,textField_5,textField_6)) {
					msgErr("빈칸이 존재합니다.");
					return;
				}
				if(!card.equals(rs.getString("card"))) {
					msgErr("카드번호가 올바르지 않습니다.");
					return;
				}
				String idstring = textField_4.getText()+"-"+textField_5.getText();
				var id = getIDNumber(rs.getDate("birth").toLocalDate(), rs.getString("gender"));
				if(!idstring.equals(id)) {
					msgErr("주민번호가 올바르지 않습니다.");
					return;
				}
				String pw =textField_6.getText();
				if(!pw.equals(id.substring(2,1)+id.substring(5,1))) {
					msgErr("비밀번호를 확인해 주세요.");
					return;
				}
				
				if(cno!=-1) {
					var pre =pre("");
				}
				else {
					
				}
				msgInfo("결제가 완료되었습니다.");
				showPage(new G_나의과정(),"G_나의과정");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		private boolean isempty(JTextField...fields) {
			for (JTextField jf : fields) {
				if(jf.getText().isBlank())
					return true;
			}
			return false;
		}
	}
}
