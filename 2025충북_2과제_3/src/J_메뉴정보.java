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
	public JButton button;
	public JButton button_1;
	public JLabel label_2;
	public JButton button_2;
	public JButton button_3;
	private int price;
	public J_메뉴정보(int fno) {
		setTitle("메뉴 정보");
		this.fno = fno;
		setBounds(100, 100, 450, 586);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(12, 10, 410, 66);
		getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(12, 86, 410, 302);
		getContentPane().add(label_1);
		
		button = new JButton("-");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 398, 55, 72);
		getContentPane().add(button);
		
		button_1 = new JButton("+");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(367, 398, 55, 72);
		getContentPane().add(button_1);
		
		label_2 = new JLabel("1");
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(79, 399, 277, 71);
		getContentPane().add(label_2);
		
		button_2 = new JButton("취소");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBackground(Color.RED);
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		button_2.setBounds(12, 480, 204, 57);
		getContentPane().add(button_2);
		
		button_3 = new JButton("구매");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		button_3.setBounds(218, 480, 204, 57);
		getContentPane().add(button_3);

		load();
	}

	private void load() {
		try (var rs = res("select * from  food where f_no="+fno)) {
			rs.next();
			label.setText(String.format("<html>이름: %s<br>가격: %,d", rs.getString("f_name"),rs.getInt("f_price")));
			label_1.setIcon(getIcon("foods/"+fno+".jpg",label_1.getWidth(),label_1.getHeight()));
			price = rs.getInt("f_price");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	int cnt = 1;
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cnt = Math.max(cnt-1, 1);
			label_2.setText(cnt+"");
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int pay =price * cnt;
			if(birth.equals(LocalDate.now())) {
				pay /=2;
			}
			setUIsUnet();
			if(JOptionPane.showConfirmDialog(null, "결제하시겠습니까?","결제", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) {
				try {
					var rs =res("select * from user where u_no = "+uno);
					rs.next();
					if(rs.getInt("u_price")<price) {
						msgErr("잔액이 부족합니다.");
					}
					else {
						execute("update user set u_price = u_price - "+pay+" where u_no = "+uno);
						var pre = pre("insert into fb values(0,?,?,?)");
						preSet(pre, uno, fno, cnt);
						pre.execute();
						msgInfo("결제가 완료되었습니다.");
						dispose();
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			setUIset();
			
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cnt = Math.min(cnt+1, 10);
			label_2.setText(cnt+"");
		}
	}
}
