import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
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
					D_상세정보 frame = new D_상세정보(2);
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
	public D_상세정보(int pno) {
		setTitle("\uC0C1\uC138\uC815\uBCF4");
		this.pno = pno;
		setBounds(100, 100, 388, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label_1 = new JLabel("\u2605");
		label_1.setVisible(false);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		label_1.setForeground(Color.RED);
		label_1.setBounds(275, 10, 77, 59);
		getContentPane().add(label_1);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 343, 224);
		getContentPane().add(label);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_2.setBounds(12, 244, 348, 22);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_3.setVerticalAlignment(SwingConstants.TOP);
		label_3.setBounds(77, 276, 283, 51);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_4.setBounds(12, 337, 348, 22);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("New label");
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_5.setBounds(12, 380, 348, 22);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("New label");
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_6.setBounds(12, 431, 348, 22);
		getContentPane().add(label_6);
		
		label_7 = new JLabel("\uC218\uB7C9:");
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_7.setBounds(12, 479, 66, 22);
		getContentPane().add(label_7);
		
		textField = new JTextField();
		textField.setBounds(69, 472, 291, 38);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uC7A5\uBC14\uAD6C\uB2C8");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 511, 166, 40);
		getContentPane().add(button);
		
		button_1 = new JButton("\uAD6C\uB9E4");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(194, 511, 166, 40);
		getContentPane().add(button_1);
		
		label_8 = new JLabel("\uC124\uBA85:");
		label_8.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_8.setBounds(12, 276, 57, 15);
		getContentPane().add(label_8);

		load();
	}
	int left;
	private void load() {
		System.out.println("with rank1 as (select pno, rank() over(partition by cno order by sum(o.quantity) desc) r from `order` o right join product p  using(pno) group by pno)\r\n"
				+ "select pno,img,pname, description, cnam, p.price, avg(rating) star, p.quantity ,r from product p join rank1 using(pno) left join `order` o using(pno) left join review using(ono) join category using(cno) where pno = "+pno+" group by pno;");
		try (var rs = res("with rank1 as (select pno, rank() over(partition by cno order by sum(o.quantity) desc) r from `order` o right join product p  using(pno) group by pno)\r\n"
				+ "select pno,img,pname, description, cnam, p.price, avg(rating) star, p.quantity ,r from product p join rank1 using(pno) left join `order` o using(pno) left join review using(ono) join category using(cno) where pno = "+pno+" group by pno;")) {
			rs.next();
			label_1.setVisible(rs.getInt("r")==1);
			label.setIcon(getIcon(rs.getBytes("img"), label.getWidth(),label.getHeight()));
			label_2.setText("상품명 : "+rs.getString(3));
			label_3.setText("<html>"+rs.getString(4));
			label_4.setText("카테고리: "+rs.getString(5));
			label_5.setText("가격 : "+String.format("%,d", rs.getInt(6)));
			label_6.setText("평점 : "+String.format("%.1f", rs.getDouble(7)));
			left = rs.getInt("quantity");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int cnt = Integer.parseInt(textField.getText());
				try {
					var pre =pre("insert into cart values(0,?,?,?)");
					preSet(pre, uno, pno, cnt);
					pre.execute();
					msgInfo("장바구니에 등록되었습니다.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (NumberFormatException e1) {
				msgErr("수량을 확인하세요.");
			}
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int cnt = Integer.parseInt(textField.getText());
				try {
					if(cnt>left) {
						msgErr("재고가 부족합니다.");
						return;
					}
					var pre =pre("insert into `order` values(0,?,?,?,?,0,0)");
					preSet(pre, uno, pno, cnt,LocalDate.now());
					pre.execute();
					execute("update product set quantity = quantity -"+cnt+" where pno = "+pno);
					msgInfo("구매가 완료되었습니다.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (NumberFormatException e1) {
				msgErr("수량을 확인하세요.");
			}
		}
	}
}
