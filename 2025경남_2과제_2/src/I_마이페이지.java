import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class I_마이페이지 extends BF {
	public JToggleButton toggleButton;
	public JToggleButton toggleButton_1;
	public JToggleButton toggleButton_2;
	public JScrollPane scrollPane;
	public JTable table;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private DefaultTableModel model3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I_마이페이지 frame = new I_마이페이지();
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
	public I_마이페이지() {
		addWindowListener(new ThisWindowListener());
		setTitle("\uB9C8\uC774\uD398\uC774\uC9C0");
		setBounds(100, 100, 706, 517);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		toggleButton = new JToggleButton("\uB0B4\uAC00 \uC88B\uC544\uD55C \uC54C\uBC14");
		toggleButton.addChangeListener(new ToggleButtonChangeListener());
		toggleButton.setForeground(new Color(255, 128, 0));
		toggleButton.setBackground(new Color(255, 255, 255));
		toggleButton.setBorder(new LineBorder(new Color(255, 128, 0)));
		toggleButton.setBounds(12, 10, 203, 23);
		getContentPane().add(toggleButton);

		toggleButton_1 = new JToggleButton("\uB300\uAE30\uC911\uC778 \uC54C\uBC14");
		toggleButton_1.addChangeListener(new ToggleButton_1ChangeListener());
		toggleButton_1.setForeground(new Color(255, 128, 0));
		toggleButton_1.setBackground(new Color(255, 255, 255));
		toggleButton_1.setBorder(new LineBorder(new Color(255, 128, 0)));
		toggleButton_1.setBounds(239, 10, 203, 23);
		getContentPane().add(toggleButton_1);

		toggleButton_2 = new JToggleButton("\uD569\uACA9\uD55C \uC54C\uBC14");
		toggleButton_2.addChangeListener(new ToggleButton_2ChangeListener());
		toggleButton_2.setForeground(new Color(255, 128, 0));
		toggleButton_2.setBackground(new Color(255, 255, 255));
		toggleButton_2.setBorder(new LineBorder(new Color(255, 128, 0)));
		toggleButton_2.setBounds(472, 10, 203, 23);
		getContentPane().add(toggleButton_2);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 56, 663, 412);
		getContentPane().add(scrollPane);

		table = new JTable() {
			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 2)
					return Icon.class;
				return super.getColumnClass(column);
			}
		};
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);
		render.setHorizontalAlignment(0);
		render2.setVerticalAlignment(SwingConstants.TOP);

		ButtonGroup bg = new ButtonGroup();
		bg.add(toggleButton);
		bg.add(toggleButton_1);
		bg.add(toggleButton_2);
		
		toggleButton.setContentAreaFilled(false);
		toggleButton_1.setContentAreaFilled(false);
		toggleButton_2.setContentAreaFilled(false);
		toggleButton.setOpaque(true);
		toggleButton_1.setOpaque(true);
		toggleButton_2.setOpaque(true);
		
		model();
		load();
		toggleButton.setSelected(true);
	}

	DefaultTableCellRenderer render = new DefaultTableCellRenderer(), render2 = new DefaultTableCellRenderer();

	private void model() {
		model = new DefaultTableModel("번호,카테고리,브랜드,브랜드명,알바,좋아요 한 알바".split(","), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model2 = new DefaultTableModel("번호,지원 번호,브랜드,알바,지원 날짜,지원 자격".split(","), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model3 = new DefaultTableModel("번호,지원 번호,브랜드,알바,지원 날짜,급여".split(","), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setRowHeight(100);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setBackground(orange);;
	}

	private void load3() {
		model3.setRowCount(0);
		bnos.clear();
		jnos.clear();
		table.setModel(model3);
		try (var rs = res("select * from apply join job using(jno) where apok = 1 and uno = "+uno)) {
			while(rs.next()) {
				model3.addRow(new Object[] {model3.getRowCount()+1, rs.getInt("apno"), getIcon("brand/"+rs.getInt("bno")+".png",100,100),"<html>"+rs.getString("jname"),rs.getString("apdate"),String.format("%,d", rs.getInt("jmoney"))});
				bnos.add(rs.getInt("bno"));
				jnos.add(rs.getInt("jno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setModel(model3);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(70);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(200);

		for (int i = 0; i < table.getColumnCount(); i++) {
			if (i == 2)
				continue;
			if (i == 3) {
				table.getColumnModel().getColumn(i).setCellRenderer(render2);
			} else {
				table.getColumnModel().getColumn(i).setCellRenderer(render);
			}
		}
	}
	
	private void load2() {
		model2.setRowCount(0);
		bnos.clear();
		jnos.clear();
		try (var rs = res("select * from apply join job using(jno) where apok = 0 and uno = "+uno)) {
			while(rs.next()) {
				model2.addRow(new Object[] {model2.getRowCount()+1, rs.getInt("apno"), getIcon("brand/"+rs.getInt("bno")+".png",100,100),"<html>"+rs.getString("jname"),rs.getString("apdate"),getGrade(rs.getInt("jgrade"))});
				bnos.add(rs.getInt("bno"));
				jnos.add(rs.getInt("jno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		table.setModel(model2);

		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(70);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(200);

		for (int i = 0; i < table.getColumnCount(); i++) {
			if (i == 2)
				continue;
			if (i == 3) {
				table.getColumnModel().getColumn(i).setCellRenderer(render2);
			} else {
				table.getColumnModel().getColumn(i).setCellRenderer(render);
			}
		}
	}

	List<Integer> bnos = new ArrayList<>();
	List<Integer> jnos = new ArrayList<>();
	private void load() {
		bnos.clear();
		jnos.clear();
		model.setRowCount(0);
		try (var rs = res(
				"select * from likes join job using(jno) join brand using(bno) join category using(cno) where uno="
						+ BF.uno)) {
			while (rs.next()) {
				model.addRow(new Object[] { model.getRowCount() + 1,"<html>"+ rs.getString("cname"),
						getIcon("brand/" + rs.getInt("bno") + ".png", 100, 100), rs.getString("bname"),
						"<html>"+rs.getString("jname"), rs.getString("ldate") });
				bnos.add(rs.getInt("bno"));
				jnos.add(rs.getInt("jno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setModel(model);

		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(70);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(200);

		for (int i = 0; i < table.getColumnCount(); i++) {
			if (i == 2)
				continue;
			if (i == 1 || i == 4) {
				table.getColumnModel().getColumn(i).setCellRenderer(render2);
			} else {
				table.getColumnModel().getColumn(i).setCellRenderer(render);
			}
		}
	}

	private class ToggleButtonChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (toggleButton.isSelected()) {
				toggleButton.setBackground(orange);
				toggleButton.setForeground(Color.white);
			} else {
				toggleButton.setBackground(Color.white);
				toggleButton.setForeground(orange);
			}
			load();
		}
	}

	private class ToggleButton_1ChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (toggleButton_1.isSelected()) {
				toggleButton_1.setBackground(orange);
				toggleButton_1.setForeground(Color.white);
			} else {
				toggleButton_1.setBackground(Color.white);
				toggleButton_1.setForeground(orange);
			}
			load2();
		}
	}

	private class ToggleButton_2ChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (toggleButton_2.isSelected()) {
				toggleButton_2.setBackground(orange);
				toggleButton_2.setForeground(Color.white);
			} else {
				toggleButton_2.setBackground(Color.white);
				toggleButton_2.setForeground(orange);
			}
			load3();
		}
	}
	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int col = table.columnAtPoint(e.getPoint());
			if(col==2&&e.getClickCount()==2) {
				showPage(new G_브랜드정보(bnos.get(table.getSelectedRow())),"G_브랜드정보");
			}
			if((toggleButton.isSelected()&&col==4 ||col==3&&!toggleButton.isSelected())&&e.getClickCount()==2) {
				showPage(new C_알바정보(jnos.get(table.getSelectedRow())),"C_알바정보");
			}
		}
	}
	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			showPage("B_메인");
		}
	}
}
