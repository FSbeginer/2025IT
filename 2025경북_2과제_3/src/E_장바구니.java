import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class E_장바구니 extends BP {
	public JLabel label;
	public JScrollPane scrollPane;
	public JPanel panel;
	public JCheckBox checkBox;
	public JButton button;
	public JButton button_1;

	/**
	 * Create the panel.
	 */
	public E_장바구니() {

		label = new JLabel("\uC7A5\uBC14\uAD6C\uB2C8");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(12, 10, 180, 42);
		add(label);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 61, 887, 428);
		add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		checkBox = new JCheckBox("\uC804\uCCB4 \uC120\uD0DD");
		checkBox.addActionListener(new CheckBoxActionListener());
		checkBox.setBackground(Color.WHITE);
		checkBox.setBounds(22, 509, 95, 23);
		add(checkBox);

		button = new JButton("\uC0AD\uC81C");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(710, 499, 97, 23);
		add(button);

		button_1 = new JButton("\uAD6C\uB9E4\uD558\uAE30");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(812, 499, 97, 23);
		add(button_1);

		label_1 = new JLabel("\uC7A5\uBC14\uAD6C\uB2C8");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(610, 10, 299, 42);
		add(label_1);

		load();
	}

	List<E_패널> pps = new ArrayList<>();
	public JLabel label_1;

	private void load() {
		panel.removeAll();
		try {
			var rs = res("select pno,ctno, img, price, c.quantity, pname from cart c join product using(pno)  where uno ="
					+ BF.uno);
			int w = (scrollPane.getWidth() - 20) / 3;
			int h = scrollPane.getHeight() / 3, i = 0;
			while (rs.next()) {
				E_패널 pp = new E_패널(rs.getInt("pno"),rs.getInt("ctno"),getIcon(rs.getBytes("img"), w - 120 - 15, h), rs.getString("pname"), rs.getInt("price"), rs.getInt("quantity"));
				pp.setSize(w, h);
				pp.setLocation(w * (i % 3), h * (i / 3));
				pp.checkBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						boolean flag = true;
						for (var pp : pps) {
							if (!pp.checkBox.isSelected()) {
								flag = false;
								break;
							}
						}
						checkBox.setSelected(flag);
						setprice();
					}
				});
				panel.add(pp);
				pps.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();

	}

	private void setprice() {
		int tot = 0;
		for (E_패널 e_패널 : pps) {
			if (e_패널.checkBox.isSelected()) {
				tot += e_패널.sum;
			}
		}
		label_1.setText("총금액 : " + String.format("%,d원", tot));

	}

	private int getprice() {
		int tot = 0;
		for (E_패널 e_패널 : pps) {
			if (e_패널.checkBox.isSelected()) {
				tot += e_패널.sum;
			}
		}
		return tot;
	}

	private class CheckBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (checkBox.isSelected()) {
				for (E_패널 e_패널 : pps) {
					e_패널.checkBox.setSelected(true);
				}
			} else {
				for (E_패널 e_패널 : pps) {
					e_패널.checkBox.setSelected(false);
				}
			}
			setprice();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(getprice()==0) {
				msgErr("삭제할 상품을 선택하세요.");
				return;
			}
			for (E_패널 e_패널 : pps) {
				if(e_패널.checkBox.isSelected()) {
					try {
						execute("delete from cart where ctno = "+e_패널.ctno);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			load();
			setprice();
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(getprice()==0) {
				msgErr("삭제할 상품을 선택하세요.");
				return;
			}
			for (E_패널 e_패널 : pps) {
				if(e_패널.checkBox.isSelected()) {
					try {
						var rs =res("select quantity from product where pno = "+e_패널.pno);
						rs.next();
						if(rs.getInt(1)<e_패널.cnt) {
							msgErr("재고가 부족합니다.");
							return;
						}
						else {
							execute("update product set quantity = quantity - "+e_패널.cnt+" where pno = "+e_패널.pno);
							var pre =pre("insert into `order` values(0,?,?,?,?,0,0)");
							preSet(pre, BF.uno,e_패널.pno,e_패널.cnt,LocalDate.now());
							pre.execute();
							execute("delete from cart where ctno = "+e_패널.ctno);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			msgInfo("구매가 완료되었습니다.");
			load();
			setprice();
		}
	}
}
