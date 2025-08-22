import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class PageMain extends JPanel {
	public JComboBox comboBox;
	private String where = "";
	private String limit = "limit 10";
	private String order = "cnt desc";
	public JScrollPane scrollPane;
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public PageMain() {
		setBackground(new Color(255, 255, 255));
		setSize(778, 431);
		setLayout(null);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setBounds(12, 31, 115, 23);
		add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 754, 367);
		add(scrollPane);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
		
		update();

	}
	public void update() {
		addCate();
		load();
	}

	private void addCate() {
		comboBox.removeAllItems();
		comboBox.addItem("전체");
		if(BF.uno==0) {
			try (var rs = BF.res("select cnam from category")) {
				while (rs.next()) {
					comboBox.addItem(rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			comboBox.addItem("자주구매");
			comboBox.addItem("최근구매");
			comboBox.addItem("오래전구매");
		}
	}

	private void load() {
		panel.removeAll();
		try (var rs = BF.res(
				"SELECT pno, img, pname, price, count(*) cnt, avg(rating) FROM product p left join `order` o using(pno) left join review using(ono) where true "
						+ where + " group by pno order by " + order + " " + limit)) {
			int w = (scrollPane.getWidth() - 40) / 5;
			int h = (scrollPane.getHeight() - 10) / 2, i = 0;

			while (rs.next()) {
				PanelMain pp = new PanelMain(BF.getIcon(rs.getBytes(2), 100, 120), rs.getString(3), rs.getInt(4),
						rs.getInt(5), rs.getDouble(6));
				pp.setSize(w, h);
				pp.setLocation((w + 10) * (i % 5), (h + 5) * (i / 5));
				int pno = rs.getInt(1);
				pp.label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (BF.isAdmin && e.getClickCount() == 2) {
							((BF) SwingUtilities.getWindowAncestor(PageMain.this)).showPage(new ModifyForm(pno),
									"ModifyForm");
						}
					}
				});
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, (h + 5) * ((i+4) / 5)-5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int idx = comboBox.getSelectedIndex();
			if (BF.uno == 0) {
				switch (idx) {
				case 0:
					where = "";
					limit = "limit 10";
					order = "cnt desc";
					break;
				default:
					where = "and cno = " + idx;
					limit = "limit 10";
					order = "cnt desc";
					break;
				}
			} else {
				switch (idx) {
				case 0:
					where = "";
					limit = "";
					order = "cnt desc";
					break;
				case 1:
					where = " and uno = " + BF.uno;
					order = "cnt desc";
					break;
				case 2:
					where = " and uno = " + BF.uno;
					order = "date desc";
					break;
				case 3:
					where = " and uno = " + BF.uno;
					order = "date asc";
					break;
				}
			}

			load();
		}

	}
}
