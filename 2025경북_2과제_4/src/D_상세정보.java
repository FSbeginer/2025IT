import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class D_상세정보 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					D_상세정보 frame = new D_상세정보(1);
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
	int pno;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public JTextField textField;
	public JButton button;
	public JButton button_1;
	public JLabel label_8;
	private int left;

	public D_상세정보(int pno) {
		setTitle("\uC0C1\uC138\uC815\uBCF4");
		this.pno = pno;
		setBounds(100, 100, 450, 570);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label_8 = new JLabel("\u2605");
		label_8.setForeground(new Color(255, 0, 0));
		label_8.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(365, 29, 57, 44);
		getContentPane().add(label_8);

		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 410, 222);
		getContentPane().add(label);

		label_1 = new JLabel("New label");
		label_1.setBounds(12, 242, 410, 25);
		getContentPane().add(label_1);

		label_2 = new JLabel("\uC124\uBA85:");
		label_2.setBounds(12, 277, 36, 15);
		getContentPane().add(label_2);

		label_3 = new JLabel("New label");
		label_3.setVerticalAlignment(SwingConstants.TOP);
		label_3.setBounds(58, 277, 364, 44);
		getContentPane().add(label_3);

		label_4 = new JLabel("New label");
		label_4.setBounds(12, 331, 410, 25);
		getContentPane().add(label_4);

		label_5 = new JLabel("New label");
		label_5.setBounds(12, 366, 410, 25);
		getContentPane().add(label_5);

		label_6 = new JLabel("New label");
		label_6.setBounds(12, 401, 410, 25);
		getContentPane().add(label_6);

		label_7 = new JLabel("\uC218\uB7C9:");
		label_7.setBounds(12, 440, 57, 25);
		getContentPane().add(label_7);

		textField = new JTextField();
		textField.setBounds(81, 436, 341, 31);
		getContentPane().add(textField);
		textField.setColumns(10);

		button = new JButton("\uC7A5\uBC14\uAD6C\uB2C8");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 490, 199, 31);
		getContentPane().add(button);

		button_1 = new JButton("\uAD6C\uB9E4");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(223, 490, 199, 31);
		getContentPane().add(button_1);

		load();
	}

	private void load() {
		try (var rs = res(
						"with rank1 as (select pno,pname,cno, rank() over(partition by cno order by sum(`order`.quantity) desc) rank1 , sum(`order`.quantity) cnt  from product left join `order` using(pno) group by pno)\r\n"
						+ "select *, avg(rating) star, p.quantity l from product p join category using(cno) left join `order` using(pno) left join review using(ono) left join rank1 using(pno) where pno = "
						+ pno + " group by pno;")) {
			rs.next();
			label.setIcon(getIcon(rs.getBytes("img"),label.getWidth(),label.getHeight()));
			label_1.setText("상품명: "+rs.getString("pname"));
			label_3.setText("<html>"+rs.getString("description"));
			label_4.setText("카테고리: "+rs.getString("cnam"));
			label_5.setText("가격: "+String.format("%,d", rs.getInt("price")));
			label_6.setText("평점: "+String.format("%.1f", rs.getDouble("star")));
			left =rs.getInt("l");
			label_8.setVisible(rs.getInt("rank1")==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			try {
				int cnt = Integer.parseInt(textField.getText());
				try (var pre = pre("insert into cart values(0,?,?,?)")) {
					preSet(pre, uno, pno, cnt);
					pre.execute();
					msgInfo("장바구니에 등록되었습니다.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (NumberFormatException e2) {
				msgErr("수량을 확인하세요.");
			}
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int cnt = Integer.parseInt(textField.getText());
				if(cnt>left) {
					msgErr("재고가 부족합니다.");
					return;
				}
				try {
					execute("update product set quantity = quantity -"+cnt+" where pno ="+pno);
					var pre = pre("insert into `order` values(0,?,?,?,curdate(),0,0)");
					preSet(pre, uno,pno,cnt);
					pre.execute();
					msgInfo("구매가 완료되었습니다.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (NumberFormatException e2) {
				msgErr("수량을 확인하세요.");
			}
		}
	}
}
