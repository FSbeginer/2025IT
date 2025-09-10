import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class 메뉴정보 extends BF {

	int fno, price;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JButton button;
	public JButton button_1;
	public JLabel label_3;
	public JButton button_2;
	public JButton button_3;
	
	public 메뉴정보(int fno) {
		setTitle("메뉴 정보");
		this.fno = fno;
		setBounds(100, 100, 508, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label.setBounds(0, 0, 492, 45);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_1.setBounds(0, 44, 492, 45);
		getContentPane().add(label_1);
		
		label_2 = new JLabel(getIcon("foods/"+fno+".jpg",482,314));
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setBounds(10, 99, 482, 314);
		getContentPane().add(label_2);
		
		button = new JButton("-");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 423, 43, 71);
		getContentPane().add(button);
		
		button_1 = new JButton("+");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(437, 423, 43, 71);
		getContentPane().add(button_1);
		
		label_3 = new JLabel("1");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_3.setBounds(67, 423, 358, 71);
		getContentPane().add(label_3);
		
		button_2 = new JButton("취소");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_2.setBackground(Color.RED);
		button_2.setBounds(10, 504, 231, 49);
		getContentPane().add(button_2);
		
		button_3 = new JButton("구매");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_3.setBounds(249, 504, 231, 49);
		getContentPane().add(button_3);

		try (var rs = res("select * food where f_no = "+fno)) {
			label.setText("이름: "+rs.getString(2));
			label_1.setText(String.format("가격: %,d", price = rs.getInt(3)));
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
			cnt = Math.max(1, cnt-1);
			label_3.setText(cnt+"");
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cnt = Math.min(10, cnt+1); 
			label_3.setText(cnt+"");
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(null, "결제하시겠습니까?", "결제", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.QUESTION_MESSAGE) {
				try {
					var rs = res("select * from user where u_no = "+uno);
					rs.next();
					int having = rs.getInt("u_price");
					if(having < cnt*price) {
						msgErr("잔액이 부족합니다.");
						return;
					}
					else {
						execute("update user set u_price = u_price-"+(cnt*price)+" where u_no"+uno);
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
		}
	}
}
