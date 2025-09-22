import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class J_자격증 extends BF {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
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
					J_자격증 frame = new J_자격증();
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
	public J_자격증() {
		setTitle("자격증발급");
		setBounds(100, 100, 853, 467);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("자격증 발급");
		label.setForeground(Color.BLUE);
		label.setBounds(12, 10, 117, 28);
		getContentPane().add(label);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(95, 28, 681, 1);
		getContentPane().add(panel);
		
		label_1 = new JLabel("<html>홈으로>나의 강의실><font color = blue>자격증발급");
		label_1.setBounds(539, 3, 237, 15);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("<html>"+uname+"님<br>환영합니다.");
		label_2.setBounds(12, 135, 96, 43);
		getContentPane().add(label_2);
		
		label_3 = new JLabel(getIcon("icon/certi.jpg",625,273));
		label_3.setBounds(124, 47, 625, 273);
		getContentPane().add(label_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(124, 337, 625, 70);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);
		
		getmodel();
		load();
	}

	private void load() {
		try {
			var rs = res("select * from certi,user where find_in_set(cno, (select certy from user where uno = "+uno+")) and uno = "+uno);
			while(rs.next()) {
				model.addRow(new Object[] {model.getRowCount()+1,rs.getString("cname"), LocalDate.now(), rs.getString("address"), "PDF저장"});
				list.add(rs.getInt("cno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	List<Integer> list = new ArrayList<Integer>();

	private void getmodel() {
		model = new DefaultTableModel("번호,자격증명,취득일,배송지,PDF".split(","), 0);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setMinWidth(120);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(200);
		table.getTableHeader().setBackground(Color.white);
		table.getTableHeader().setBorder(null);
		table.setRowHeight(50);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
		table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {{setBackground(Color.blue);setForeground(Color.white);setHorizontalAlignment(0);}});
	}

	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(4== table.getSelectedColumn()) {
				showPage(new 자격확인서(list.get(table.getSelectedRow())), "자격확인서");
			}
		}
	}
}
