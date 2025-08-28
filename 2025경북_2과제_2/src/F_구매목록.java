import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class F_구매목록 extends BP {
	public JLabel label;
	public JLabel label_1;
	public JScrollPane scrollPane;
	public JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public F_구매목록() {

		label = new JLabel("\uAD6C\uB9E4\uBAA9\uB85D");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(12, 10, 120, 36);
		add(label);

		label_1 = new JLabel("\uCD1D \uAE08\uC561:\\");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(464, 10, 400, 36);
		add(label_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 852, 393);
		scrollPane.getViewport().setBackground(Color.white);
		add(scrollPane);

		table = new JTable() {
			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 0)
					return Icon.class;
				return super.getColumnClass(column);
			}
		};
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);

		popupMenu = new JPopupMenu();
		popupMenu.setBounds(0, 0, 200, 50);
		add(popupMenu);

		menuItem = new JMenuItem("\uC0AD\uC81C");
		menuItem.addActionListener(new MenuItemActionListener());
		popupMenu.add(menuItem);

		table.setComponentPopupMenu(popupMenu);

		getmodel();
		load();
	}

	List<Integer> onos = new ArrayList<Integer>();
	public JPopupMenu popupMenu;
	public JMenuItem menuItem;

	private void load() {
		model.setRowCount(0);
		int tot = 0;
		try (var rs = res(
				"select pno,ono,img,pname,o.quantity,p.price,o.date from product p join `order` o using(pno) where uno = "
						+ BF.uno)) {
			while (rs.next()) {
				model.addRow(new Object[] { getIcon(rs.getBytes(3), 80, 80), rs.getString(4), rs.getInt(5) + "개",
						String.format("%,d원", rs.getInt(6)), String.format("%,d원", rs.getInt(6) * rs.getInt(5)),
						rs.getString(7) });
				int sum = rs.getInt(5) * rs.getInt(6);
				onos.add(rs.getInt(2));
				tot += sum;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		label_1.setText(String.format("총 금액 : %,d원", tot));
	}

	private void getmodel() {
		model = new DefaultTableModel(" ,상품명,수량,단가,합계,구매날짜".split(","), 0);
		table.setModel(model);
		table.setRowHeight(80);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
		table.getTableHeader().setBackground(Color.white);
	}

	private class MenuItemActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int ono = onos.get(table.getSelectedRow());
			try {
				execute("delete from `order` where ono = " + ono);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			msgInfo("삭제가 완료되었습니다.");
			load();
		}
	}

	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				table.changeSelection(table.rowAtPoint(e.getPoint()), 0, false, false);
			}
		}
	}
}
