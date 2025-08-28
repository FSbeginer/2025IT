import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

public class A_메인 extends BP {
	public JComboBox comboBox;
	public JScrollPane scrollPane;
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public A_메인() {
		setSize(876, 459);
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setBounds(12, 10, 112, 30);
		add(comboBox);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(12, 54, 852, 374);
		add(scrollPane);

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		comboItem();
	}

	private void comboItem() {
		comboBox.addItem("전체");
		if (BF.uno == 0) {
			try (var rs = res("select * from category;")) {
				while (rs.next()) {
					comboBox.addItem(rs.getString(2));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		} else {
			comboBox.addItem("자주구매");
			comboBox.addItem("최근구매");
			comboBox.addItem("오래전구매");
		}
	}

	String limit = "limit 10", order = " cnt desc", where = "";

	private void load() {
		panel.removeAll();
		int w = (scrollPane.getWidth() - 60 - 25) / 5, h = (scrollPane.getHeight() - 10) / 2;
		try (var rs = res(
				"select p.img, p.pname, p.price, sum(o.quantity) cnt, round(avg(r.rating),1) star, pno from `order` o join product p using(pno) left join review r using(ono) join category c using(cno) where true "
						+ where + " group by pno order by " + order + " " + limit)) {
			int i = 0;
			while (rs.next()) {
				A_Panel pp = new A_Panel(getIcon(rs.getBytes(1), 100, 100), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getDouble(5));
				pp.setSize(w, h);
				pp.setLocation((w + 15) * (i % 5), (h + 10) * (i / 5));
				int pno = rs.getInt("pno");
				pp.label_4.addMouseListener(new MouseAdapter() {
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
			panel.setPreferredSize(new Dimension(0, (h + 10) * ((i + 4) / 5)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(BF.uno==0) {
				if(comboBox.getSelectedIndex()==0) {
					where = "";
				}
				else {
					where = "and cno = "+comboBox.getSelectedIndex();
				}
			}
			else {
				switch (comboBox.getSelectedIndex()) {
				case 0:
					where = "and uno = "+BF.uno;
					limit = "";
					order = "cnt desc";
					break;
				case 1:
					where = "and uno = "+BF.uno;
					limit = "";
					order = "o.date desc";
					break;
				case 2:
					where = "and uno = "+BF.uno;
					limit = "";
					order = "o.date";
					break;
				}
			}
			load();
		}
	}
}
