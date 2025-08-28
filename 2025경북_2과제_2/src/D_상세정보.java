import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class D_상세정보 extends BF {

	int pno;
	boolean top;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JTextField textField;
	public JButton button;
	public JButton button_1;
	public JLabel label_7;
	public D_상세정보(int pno) {
		setTitle("\uC0C1\uC138\uC815\uBCF4");
		this.pno = pno;
		setBounds(100, 100, 450, 656);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(top) {
					g.setColor(Color.red);
					g.setFont(new Font("맑은 고딕", Font.BOLD, 40));
					g.drawString("★", getWidth()-g.getFontMetrics().stringWidth("★"), 50);
				}
			}
		};
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 410, 256);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setBounds(12, 276, 410, 40);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("\uC124\uBA85:");
		label_2.setBounds(12, 326, 50, 40);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("\uC124\uBA85:");
		label_3.setVerticalAlignment(SwingConstants.TOP);
		label_3.setBounds(74, 337, 348, 40);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setBounds(12, 387, 410, 27);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("New label");
		label_5.setBounds(12, 424, 410, 27);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("\uC218\uB7C9:");
		label_6.setBounds(12, 508, 50, 40);
		getContentPane().add(label_6);
		
		textField = new JTextField();
		textField.setBounds(74, 508, 348, 40);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uC7A5\uBC14\uAD6C\uB2C8");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 558, 192, 34);
		getContentPane().add(button);
		
		button_1 = new JButton("\uAD6C\uB9E4");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(230, 558, 192, 34);
		getContentPane().add(button_1);
		
		label_7 = new JLabel("\uAC00\uACA9: <dynamic>");
		label_7.setBounds(12, 461, 410, 27);
		getContentPane().add(label_7);
		
		load();
	}

	int left = 0;
	private void load() {
		try (var rs = res("with cte as( select pno, rank() over(partition by cno order by sum(`order`.quantity) desc) ra from product join category using(cno) left join `order` using(pno)  group by pno)\r\n"
				+ " select pno,img,description, cnam, price, round(avg(rating),1) star, product.quantity from product join category using(cno) left join `order` using(pno) left join review r using(ono) join cte using(pno) where pno = "+pno+" group by pno ;")) {
			rs.next();
			label.setIcon(getIcon(rs.getBytes("img"), label.getWidth(),label.getHeight()));
			label_1.setText("상품명: "+rs.getString("pname"));
			label_3.setText("<html>"+rs.getString("description"));
			label_4.setText("카테고리: "+rs.getString("cnam"));
			label_5.setText("가격: " +String.format("%,d", rs.getInt("price")));
			label_7.setText(String.format("평점: %.1f", rs.getDouble("star")));
			top = rs.getInt("ra")==1? true:false;
			left = rs.getInt("quantity");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int quantity = Integer.parseInt(textField.getText());
				var pre = pre("insert into cart values(0,?,?,?)");
				preSet(pre, uno, pno, quantity);
				pre.execute();
				msgInfo("장바구니에 등록되었습니다.");
			} catch (Exception e1) {
				msgErr("수량을 확인하세요.");
			}
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int quantity = Integer.parseInt(textField.getText());
				if(quantity>left) {
					msgErr("재고가 부족합니다.");
					return;
				}
				var pre = pre("insert into `order` values(0,?,?,?,?,0,0)");
				preSet(pre, uno, pno, quantity, LocalDate.now());
				pre.execute();
				execute("update product set quantity = quantity - "+quantity+" where pno = "+pno);
				msgInfo("구매가 완료되었습니다.");
				
			} catch (Exception e1) {
				msgErr("수량을 확인하세요.");
			}
		}
	}
}
