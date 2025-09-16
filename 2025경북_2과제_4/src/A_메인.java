import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class A_메인 extends BP {
	public JComboBox comboBox;
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public A_메인() {

		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC804\uCCB4" }));
		comboBox.setBounds(12, 10, 123, 31);
		add(comboBox);

		panel = new JPanel();
		panel.setBounds(12, 51, 944, 396);
		add(panel);
		panel.setLayout(null);

		category();
		addPanel();
	}

	private void category() {
		if (BF.uno == 0) {
			try {
				var rs = res("SELECT * FROM category;");
				while (rs.next()) {
					comboBox.addItem(rs.getString(2));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			comboBox.addItem("자주구매");
			comboBox.addItem("최근구매");
			comboBox.addItem("오래전구매");
		}
	}

	String where = "", order = "order by selling desc, pno";

	private void addPanel() {
		panel.removeAll();
		try (var rs = res(
				"select *,sum(quantity) selling, avg(rating) star from (select ono,date,uno,pno, pname, img,cno, price, o.quantity,rating, row_number() over(partition by pno order by date desc,ono)  from product p left join `order` o using(pno) left join review using (ono)) sub "
						+ where + " group by pno " + order + " limit 10")) {
			int w = (panel.getWidth() - 40) / 5;
			int h = (panel.getHeight() - 10) / 2, i = 0;
			while (rs.next()) {
				A_패널 pp = new A_패널(getIcon(rs.getBytes("img"), w - 10, h - 90), rs.getString("pname"),
						rs.getInt("price"), rs.getInt("selling"), rs.getDouble("star"));
				pp.setLocation((w + 10) * (i % 5), (h + 10) * (i / 5));
				pp.setSize(w, h);
				int pno = rs.getInt("pno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2 && BF.isAdmin) {
							((BF) SwingUtilities.getWindowAncestor(pp)).showPage(new H_상품등록(pno), "H_상품등록");
						}
					}
				});
				panel.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (BF.uno == 0) {
				if (comboBox.getSelectedIndex() == 0) {
					where = "";
				} else {
					where = " where cno = " + comboBox.getSelectedIndex();
				}
				order = " order by selling desc, pno";
			} else {
				switch (comboBox.getSelectedIndex()) {
				case 1: {
					where = "where uno = " + BF.uno;
					order = "order by selling desc, pno";
				}
				case 2: {
					where = "where uno = " + BF.uno;
					order = "order by date desc, ono";
					break;
				}
				case 3: {
					where = "where uno = " + BF.uno;
					order = "order by date , ono";
					break;
				}
				default: {
					where = "";
					order = "order by selling desc, pno";
					break;
				}
				}
			}
			addPanel();
		}

	}
}
