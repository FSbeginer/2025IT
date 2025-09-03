import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.sql.SQLException;

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
		setTitle("\uC608\uC57D \uB0B4\uC5ED");
		setBounds(100, 100, 580, 254);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("굴림", Font.BOLD, 15));
		label.setBounds(12, 10, 403, 39);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 65, 540, 124);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		getmodel();
		data();
		label.setText(uname+"님의 예약내역");
	}

	private void data() {
		try (var rs = res("select r.*, h.*, d.name dname from reservation r join doctor d using(dno) join hospital h using(hno) where uno = "+uno)) {
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString("date"),rs.getString("dname"),rs.getString("name")});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getmodel() {
		model = new DefaultTableModel("날짜 주치의 병원".split(" "), 0);
		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setModel(model);
		table.setDefaultRenderer(Object.class, render);
		table.getColumnModel().getColumn(1).setMinWidth(200);
		table.setRowHeight(50);
	}
}
