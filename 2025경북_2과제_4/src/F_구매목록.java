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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
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
		
		popupMenu = new JPopupMenu();
		
		menuItem = new JMenuItem("\uC0AD\uC81C");
		menuItem.addActionListener(new MenuItemActionListener());
		popupMenu.add(menuItem);
		
		label = new JLabel("\uAD6C\uB9E4\uBAA9\uB85D");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(12, 10, 125, 31);
		add(label);
		
		label_1 = new JLabel("\uAD6C\uB9E4\uBAA9\uB85D");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(611, 10, 345, 31);
		add(label_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 51, 944, 396);
		add(scrollPane);
		
		table = new JTable() {
			@Override
			public Class<?> getColumnClass(int column) {
				if(column==0) {
					return Icon.class;
				}
				return super.getColumnClass(column);
			}
		};
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);
		
		loadtable();
		load();
	}

	List<Integer> onos = new ArrayList<Integer>();
	public JPopupMenu popupMenu;
	public JMenuItem menuItem;
	private void load() {
		model.setRowCount(0);
		onos.clear();
		try (var rs = res("select ono,img, pname,  o.quantity, price, date from `order` o join product using(pno) where uno= "+BF.uno)) {
			int sum = 0;
			while(rs.next()) {
				onos.add(rs.getInt("ono"));
				model.addRow(new Object[] {getIcon(rs.getBytes("img"),100,100),rs.getString(3),rs.getString(4)+"개",String.format("%,d원", rs.getInt(5)),String.format("%,d원", rs.getInt(5)*rs.getInt(4)),rs.getString("date")});
				sum += rs.getInt("price")*rs.getInt("quantity");
			}
			label_1.setText(String.format("총 금액 : %,d원", sum));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadtable() {
		model =new DefaultTableModel(" ,상품명,수량,단가,합계,구매날짜".split(","),0);
		table.setModel(model);
		table.setRowHeight(100);
		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
	}
	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(SwingUtilities.isRightMouseButton(e)) {
				table.changeSelection(table.rowAtPoint(e.getPoint()), 0, false, false);
				popupMenu.show(table, e.getX(), e.getY());
			}
		}
	}
	private class MenuItemActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int ono = onos.get(table.getSelectedRow());
			try {
				execute("delete from review where ono = "+ono);
				execute("delete from `order` where ono ="+ono);
				msgInfo("삭제가 완료되었습니다.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			load();
		}
	}
}
