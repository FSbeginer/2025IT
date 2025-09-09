import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class A_메인 extends BP {
	public JComboBox comboBox;
	public JScrollPane scrollPane;
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public A_메인() {

		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC804\uCCB4" }));
		comboBox.setBounds(12, 10, 132, 23);
		add(comboBox);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(12, 44, 913, 484);
		add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		addCate();
		load();
	}

	String where = "", order = "cnt desc, pno";

	private void load() {
		panel.removeAll();
		try (var rs = res(
				"select p.*, sum(o.quantity) cnt,  avg(rating) star from (select *, row_number() over(order by date desc) from `order`) o join product p using(pno) left join review using(ono) where true "
						+ where + " group by pno order by " + order + " limit 10")) {
			int w = (scrollPane.getWidth() - 20 - 40) / 5;
			int h = (scrollPane.getHeight() - 20) / 2, i = 0;
			while (rs.next()) {
				A_패널 pp = new A_패널(getIcon(rs.getBytes("img"), w - 10, h - 90), rs.getString("pname"),
						rs.getInt("price"), rs.getInt("cnt"), rs.getDouble("star"));
				pp.setSize(w, h);
				pp.setLocation((w + 10) * (i % 5), (h + 10) * (i / 5));
				int pno = rs.getInt("pno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (BF.isAdmin && e.getClickCount() == 2) {
							((MainFrame) SwingUtilities.getWindowAncestor(pp)).showPage(new H_상품등록(pno), "H_상품등록");
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

	private void addCate() {
		if (BF.uno == 0) {
			try (var rs = BF.res("select * from category")) {
				while (rs.next()) {
					comboBox.addItem(rs.getString("cnam"));
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

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (BF.uno == 0) {
				if (comboBox.getSelectedIndex() == 0)
					where = "";
				else
					where = "and cno = "+comboBox.getSelectedIndex();
			} else {
				if (comboBox.getSelectedIndex() == 0) {
					where = "";
					order = "cnt desc, pno";
				}
				else if(comboBox.getSelectedIndex() == 1) {
					where = "and uno = "+BF.uno;
					order = "date desc, pno";
				}
				else if(comboBox.getSelectedIndex() == 2) {
					where = "and uno = "+BF.uno;
					order = "date, pno";
				}
			}
			load();
		}
	}
}
