import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class E_장바구니 extends BP {
	public JLabel label;
	public JScrollPane scrollPane;
	public JPanel panel;
	public JLabel label_1;
	public JCheckBox checkBox;
	public JButton button;
	public JButton button_1;

	/**
	 * Create the panel.
	 */
	public E_장바구니() {

		label = new JLabel("\uC7A5\uBC14\uAD6C\uB2C8");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(12, 10, 101, 33);
		add(label);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 52, 944, 369);
		add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		label_1 = new JLabel("\uCD1D\uAE08\uC561 : 0\uC6D0");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(450, 10, 506, 33);
		add(label_1);

		checkBox = new JCheckBox("\uC804\uCCB4 \uC120\uD0DD");
		checkBox.addActionListener(new CheckBoxActionListener());
		checkBox.setBackground(new Color(255, 255, 255));
		checkBox.setBounds(8, 428, 115, 23);
		add(checkBox);

		button = new JButton("\uC0AD\uC81C");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(785, 431, 66, 23);
		add(button);

		button_1 = new JButton("\uAD6C\uB9E4\uD558\uAE30");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(859, 431, 97, 23);
		add(button_1);

		load();
	}

	List<E_패널> pps = new ArrayList<>();

	private void load() {
		pps.clear();
		panel.removeAll();
		try {
			var rs = res(
					"select ctno, pno, cart.quantity, pname, img, price from cart join product using(pno) where uno = "
							+ BF.uno);
			int w = (scrollPane.getWidth() - 20) / 3;
			int h = (scrollPane.getHeight()) / 3, i = 0;
			while (rs.next()) {
				E_패널 pp = new E_패널(getIcon(rs.getBytes("img"), w - 140, h), rs.getInt("ctno"), rs.getInt("pno"),
						rs.getInt("quantity"), rs.getInt("price"), rs.getString("pname"));
				pp.checkBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						boolean flag = true;
						for (E_패널 pp : pps) {
							if (!pp.checkBox.isSelected()) {
								flag = false;
								break;
							}
						}
						checkBox.setSelected(flag);
						calResult();
					}
				});
				pp.setSize(w, h);
				pp.setLocation(w * (i % 3), h * (i / 3));
				panel.add(pp);
				pps.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, h * ((i + 2) / 3)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}

	private void calResult() {
		int sum = getSum();
		label_1.setText("총금액 : " + String.format("%,d원", sum));
	}

	private int getSum() {
		int sum = 0;
		for (E_패널 pp : pps) {
			if (pp.checkBox.isSelected()) {
				sum += pp.price * pp.cnt;
			}
		}
		return sum;
	}

	private class CheckBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (checkBox.isSelected()) {
				for (E_패널 pp : pps) {
					pp.checkBox.setSelected(true);
				}
			} else {
				for (E_패널 pp : pps) {
					pp.checkBox.setSelected(false);
				}
			}
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (getSum() == 0) {
				msgErr("삭제할 상품을 선택하세요.");
				return;
			}
			for (var pp : pps) {
				if (pp.checkBox.isSelected()) {

					try {
						execute("delete from cart where ctno = " + pp.ctno);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			load();
		}
	}

	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (getSum() == 0) {
				msgErr("구매할 상품을 선택하세요.");
				return;
			}
			for (E_패널 pp : pps) {
				if (pp.checkBox.isSelected()) {
					try {
						var rs = res("select * from product where pno = " + pp.pno);
						if (rs.next() && rs.getInt("quantity") < pp.cnt) {
							msgErr("재고가 부족합니다.");
							break;
						}
						execute("delete from cart where ctno = " + pp.ctno);
						execute("update product set quantity = quantity - " + pp.cnt + " where pno = " + pp.pno);
						var pre = pre("insert into `order` values(0,?,?,?,curdate(),0,0)");
						preSet(pre, BF.uno, pp.pno, pp.cnt);
						pre.execute();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			msgInfo("구매가 완료되었습니다.");
			load();
			calResult();
		}
	}
}
