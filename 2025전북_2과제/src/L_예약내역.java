import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;


public class L_예약내역 extends BF {

	private JPanel contentPane;
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 661, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("\uAE40\uC9C0\uBBFC\uB2D8\uC758 \uC608\uC57D\uB0B4\uC5ED");
		label.setFont(new Font("굴림", Font.PLAIN, 16));
		label.setBounds(12, 10, 264, 28);
		contentPane.add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 52, 621, 159);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel("날짜 주치의 병원".split(" "), 0);
		table.setModel(model);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, render);
		
		table.setRowHeight(60);
		try (var rs = res("select re.date, d.name dname, h.name hname from reservation re join doctor d using(dno) join hospital h using(hno) where uno = "+uno+" order by re.date")) {
			while(rs.next()) {
				model.addRow(new Object[] { rs.getString(1), rs.getString(2),rs.getString(3)});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from user where uno = "+uno)) {
			rs.next();
			label.setText(rs.getString("name")+"님의 예약내역");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
