import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class L_예약내역 extends BF {
	public JLabel label;
	public JScrollPane scrollPane;
	public JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L_예약내역 frame = new L_예약내역();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public L_예약내역() {
		setBounds(100, 100, 618, 231);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(uname+"\uB2D8\uC758 \uAC80\uC0AC \uACB0\uACFC");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		label.setBounds(12, 10, 248, 36);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 54, 556, 116);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		getmodel();
		load();
	}

	private void load() {
		try (var rs = res("select date, d.name dn, h.name hn from reservation join doctor d using(dno) join hospital h using(hno) where uno = "+uno)) {
			while(rs.next())
				model.addRow(new Object[] {rs.getString(1), rs.getString(2)+"의사",rs.getString(3)});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getmodel() {
		model = new DefaultTableModel("날짜 주치의 병원".split(" "), 0);
		table.setModel(model);
		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, render);
		table.setRowHeight(40);
		table.getColumnModel().getColumn(1).setMinWidth(200);
	}

}
