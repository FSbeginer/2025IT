import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

	private JPanel contentPane;

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
	public JLabel label_3;
	public JButton button_2;
	public JButton button_3;
	private int price;
	public J_메뉴정보(int fno) {
		setTitle("메뉴 정보");
		this.fno = fno;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setBounds(12, 10, 431, 38);
		contentPane.add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(12, 52, 431, 38);
		contentPane.add(label_1);
		
		label_2 = new JLabel((String) null);
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setBounds(12, 100, 431, 288);
		contentPane.add(label_2);
		
		button = new JButton("-");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 398, 59, 81);
		contentPane.add(button);
		
		button_1 = new JButton("+");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(384, 398, 59, 81);
		contentPane.add(button_1);
		
		label_3 = new JLabel("1");
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(83, 398, 284, 81);
		contentPane.add(label_3);
		
		button_2 = new JButton("취소");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		button_2.setBackground(Color.RED);
		button_2.setBounds(12, 489, 209, 47);
		contentPane.add(button_2);
		
		button_3 = new JButton("구매");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		button_3.setBounds(234, 489, 209, 47);
		contentPane.add(button_3);
		load();
	}

	int cnt=1;
	private void load() {
		try (var rs = res("select * from food  where f_no = "+fno)) {
			while(rs.next()) {
				label.setText("이름:"+rs.getString(2));
				price = rs.getInt(3); 
				label_1.setText("가격: "+String.format("%,d", price));
				label_2.setIcon(getIcon("foods/"+fno+".jpg",label_2.getWidth(),label_2.getHeight()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		try {
			var rs =res("select * from user where u_no = "+uno);
			rs.next();
			var da = rs.getDate("u_birth").toLocalDate();
			if(da.equals(LocalDate.now())) {
				price /= 2;
				label_1.setText("가격: "+String.format("%,d", price));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
				
	}

	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cnt = Math.max(1, cnt-1);
			label_3.setText(cnt+"");
			label_1.setText("가격: "+String.format("%,d", price*cnt));
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cnt = Math.min(10, cnt+1);
			label_3.setText(cnt+"");
			label_1.setText("가격: "+String.format("%,d", price*cnt));
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(msgCon("결제", "결제하시겠습니까?")==JOptionPane.OK_OPTION) {
				try {
					var rs =res("select * from user where u_no = "+uno);
					rs.next();
					int hav = rs.getInt("u_price");
					if(hav<cnt*price) {
						msgErr("잔액이 부족합니다.");
					}
					else {
						execute("update user set u_price = u_price - "+(price*cnt)+" where u_no= "+uno);
						var pre =pre("insert into fb values(0,?,?,?)");
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
