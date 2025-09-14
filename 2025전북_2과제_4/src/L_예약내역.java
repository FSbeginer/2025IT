import java.awt.Color;
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
		setBounds(100, 100, 597, 248);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(uname+"님의 예약내역");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setBounds(12, 10, 244, 38);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBounds(12, 52, 557, 147);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel("날짜 주치의 병원".split(" "), 0);
		table.setModel(model);
		
		table.setRowHeight(50);
		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
		table.getColumnModel().getColumn(1).setMinWidth(150);
		
		try (var rs = res("select date, d.name dn , h.name hn from reservation join doctor d using(dno) join hospital h using(hno) where uno = "+uno+" order by date;")) {
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString(1),rs.getString(2)+"의사",rs.getString(3)});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
