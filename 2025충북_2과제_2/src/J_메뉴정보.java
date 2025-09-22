import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class J_메뉴정보 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					J_메뉴정보 frame = new J_메뉴정보(1);
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
	int fno;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JButton button;
	public JButton button_1;
	public JButton button_2;
	public JButton button_3;
	public JLabel label_3;
	public J_메뉴정보(int fno) {
		setTitle("메뉴 정보");
		this.fno = fno;
		setBounds(100, 100, 450, 562);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label.setBounds(0, 0, 434, 32);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label_1.setBounds(0, 42, 434, 32);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("");
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setBounds(10, 84, 412, 299);
		getContentPane().add(label_2);
		
		button = new JButton("-");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 396, 48, 71);
		getContentPane().add(button);
		
		button_1 = new JButton("+");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(374, 396, 48, 71);
		getContentPane().add(button_1);
		
		button_2 = new JButton("취소");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBackground(Color.RED);
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		button_2.setBounds(10, 472, 205, 41);
		getContentPane().add(button_2);
		
		button_3 = new JButton("구매");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		button_3.setBounds(227, 472, 195, 41);
		getContentPane().add(button_3);
		
		label_3 = new JLabel("1");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(72, 393, 285, 71);
		getContentPane().add(label_3);
		
		try {
			var rs = res("select * from food where f_no = "+fno);
			rs.next();
			label.setText("이름: "+rs.getString("f_name"));
			label_1.setText("가격: "+String.format("%,d",rs.getInt("f_price")));
			label_2.setIcon(getIcon("foods/"+fno+".jpg",label_2.getWidth(),label_2.getHeight()));
			price = rs.getInt("f_price");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	int price; 
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	int cnt = 1;
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cnt = Math.max(1, cnt--);
			label_3.setText(cnt+"");
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cnt = Math.min(10, cnt++);
			label_3.setText(cnt+"");
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int have=0;
			try (var rs = res("select * from user where u_no = "+uno)) {
				rs.next();
				price = rs.getInt("u_price");
				
				var birth = rs.getDate("u_birth").toLocalDate();
				int pay = price * cnt;
				
				if(birth.getMonth()== LocalDate.now().getMonth() && birth.getDayOfMonth()==LocalDate.now().getDayOfMonth()) {
					pay /=2;
				}
				if(JOptionPane.showConfirmDialog(null, "결제하시겠습니까?","결제", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) {
					if(have<pay) {
						msgErr("잔액이 부족합니다.");
						return;
					}
					else {
						execute("update user set u_price = u_price - "+pay+" where u_no = "+uno);
						var pre = pre("insert into fb values(0,?,?,?)");
						preSet(pre, uno,fno,cnt);
						pre.execute();
						msgInfo("결제가 완료되었습니다.");
						dispose();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
