import javax.swing.Icon;
import javax.swing.JLabel;
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
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
		label.setBounds(12, 10, 152, 47);
		add(label);
		
		label_1 = new JLabel("\uAD6C\uB9E4\uBAA9\uB85D");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(588, 10, 337, 47);
		add(label_1);
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new ScrollPaneMouseListener());
		scrollPane.setBounds(12, 67, 913, 461);
		add(scrollPane);
		
		popupMenu = new JPopupMenu();
		
		menuItem = new JMenuItem("\uC0AD\uC81C");
		menuItem.addActionListener(new MenuItemActionListener());
		popupMenu.add(menuItem);
		
		table = new JTable() {
			@Override
			public Class<?> getColumnClass(int column) {
				if(column==0)
					return Icon.class;
				return super.getColumnClass(column);
			}
		};
		scrollPane.setViewportView(table);

		table.setComponentPopupMenu(popupMenu);
		getmodel();
		load();
	}

	List<Integer> ono = new ArrayList<Integer>(); 
	public JPopupMenu popupMenu;
	public JMenuItem menuItem;
	private void load() {
		model.setRowCount(0);
		ono.clear();
		try (var rs = res("select ono,pno, img, pname, o.quantity, price, date from  `order` o join product p using(pno) where uno = "+BF.uno+" order by date;")) {
			int sum = 0;
			while(rs.next()) {
				model.addRow(new Object[] {getIcon(rs.getBytes("img"), 100,100), rs.getString(4),rs.getInt(5)+"개",String.format("%,d원", rs.getInt(6)),String.format("%,d원", rs.getInt(5)*rs.getInt(6)), rs.getString(7)});
				ono.add(rs.getInt(1));
				sum += rs.getInt(5)*rs.getInt(6);
			}
			label_1.setText(String.format("총 금액 : %,d원", sum));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getmodel() {
		model = new DefaultTableModel(" ,상품명,수량,단가,합계,구매날짜".split(","),0);
		table.setModel(model);
		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
		table.setRowHeight(100);
	}
	private class MenuItemActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int on = ono.get(table.getSelectedRow());
			try {
				execute("delete from review where ono = "+on);
				execute("delete from `order` where ono = "+on);
				msgInfo("삭제가 완료되었습니다.");
				load();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class ScrollPaneMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(SwingUtilities.isRightMouseButton(e)) {
				table.changeSelection(table.rowAtPoint(e.getPoint()), table.columnAtPoint(e.getPoint()), false, false);
			}
		}
	}
}
