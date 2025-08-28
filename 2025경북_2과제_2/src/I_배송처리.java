import javax.swing.JPanel;
import javax.swing.JComboBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import java.awt.Color;

public class I_배송처리 extends BP {
	public JComboBox comboBox;
	public JButton button;
	public JScrollPane scrollPane;
	public JTable table;
	public JLabel label;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public I_배송처리() {

		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());

		popupMenu = new JPopupMenu();
		popupMenu.setBackground(Color.WHITE);

		menuItem = new JMenuItem("New menu item");
		menuItem.addActionListener(new MenuItemActionListener());
		menuItem.setBackground(Color.WHITE);
		popupMenu.add(menuItem);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC804\uCCB4", "\uACB0\uC81C\uC804",
				"\uBC30\uC1A1\uC900\uBE44", "\uBC30\uC1A1\uC911" }));
		comboBox.setBounds(12, 10, 115, 34);
		add(comboBox);

		button = new JButton("\uC77C\uAD04\uCC98\uB9AC");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(139, 10, 97, 34);
		add(button);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 53, 852, 365);
		add(scrollPane);

		table = new JTable() {
			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 0)
					return Boolean.class;
				return super.getColumnClass(column);
			}
		};
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);

		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label.setBounds(12, 434, 586, 15);
		add(label);

		table.setComponentPopupMenu(popupMenu);

		getmodel();
		load();
	}

	private void getmodel() {
		model = new DefaultTableModel(" ,번호,상품명,회원명,수량,가격,총금액,배송현황".split(","), 0);
		table.setModel(model);

		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);

		table.getColumnModel().getColumn(0).setMaxWidth(50);
	}

	String where = "";
	List<Integer> onos = new ArrayList<Integer>();
	List<int[]> condition = new ArrayList<int[]>();
	public JPopupMenu popupMenu;
	public JMenuItem menuItem;

	private void load() {
		model.setRowCount(0);
		onos.clear();
		condition.clear();
		try (var rs = res(
				"select pno,ono,pname,uname,o.quantity,p.price,o.pay,o.delivery from `order` o join user u using(uno) join product p using(pno) where delivery != 3 "
						+ where + " order by ono")) {
			while (rs.next()) {
				model.addRow(new Object[] { false, model.getRowCount() + 1, rs.getString(3), rs.getString(4),
						rs.getString(5), String.format("%,d", rs.getInt(6)),
						String.format("%,d", rs.getInt(5) * rs.getInt(6)), getDelivery(rs.getInt(7), rs.getInt(8)) });
				onos.add(rs.getInt(2));
				condition.add(new int[] {rs.getInt(7),rs.getInt(8)});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getTot();
	}

	private void getTot() {
		try (var rs = res(
				"select pno,ono,pname,uname,o.quantity,p.price, o.pay,o.delivery from `order` o join user u using(uno) join product p using(pno) where delivery != 3 order by ono;")) {
			int[] cnt = { 0, 0, 0, 0 };
			while (rs.next()) {
				int idx = getidx(rs.getInt("pay"), rs.getInt("delivery"));
				cnt[idx]++;
			}
			label.setText(
					String.format("결제 전 : %d건   결제완료 : %d건   배송준비 : %d건   배송중 : %d건", cnt[0], cnt[1], cnt[2], cnt[3]));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getidx(int pay, int delivery) {
		if (pay == 0 && delivery == 0)
			return 0;
		else if (delivery == 0)
			return 1;
		else if (delivery == 1)
			return 2;
		else if (delivery == 2)
			return 3;
		else
			return -1;
	}

	private String getDelivery(int pay, int delivery) {
		if (pay == 0 && delivery == 0)
			return "결제전";
		else if (delivery == 0)
			return "결제완료";
		else if (delivery == 1)
			return "배송준비";
		else if (delivery == 2)
			return "배송중";
		else
			return "배송완료";
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (comboBox.getSelectedIndex() == 0)
				where = "";
			else if (comboBox.getSelectedIndex() == 1)
				where = "and pay = 0 and delivery = 0";
			else
				where = "and delivery = " + (comboBox.getSelectedIndex() - 1);
			load();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			List<Integer> sel = new ArrayList<Integer>();
			for (int i = 0; i < model.getRowCount(); i++) {
				Boolean flag = (Boolean) table.getValueAt(i, 0);
				if (flag) {
					sel.add(onos.get(table.getSelectedRow()));
				}
			}
			if (sel.size() == 0) {
				msgErr("배송 처리할 리스트를 선택하세요.");
			} else {
				for (Integer integer : sel) {
					try {
						execute("update `order` set delivery = delivery +1 where ono = " + integer);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				load();
			}
		}

	}

	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				table.changeSelection(table.rowAtPoint(e.getPoint()), 0, false, false);
				var data = condition.get(table.getSelectedRow());
				menuItem.setText(getDelivery(data[0], data[1]+1));
			}
		}
	}
	private class MenuItemActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int ono = onos.get(table.getSelectedRow());
			try {
				execute("update `order` set delivery = delivery +1 where ono = " + ono);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			load();
		}
	}
}
