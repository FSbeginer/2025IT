import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class J_자격증 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
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
		setBounds(100, 100, 844, 501);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("자격증 발급");
		label.setForeground(new Color(0, 0, 255));
		label.setBounds(12, 24, 74, 15);
		getContentPane().add(label);

		label_1 = new JLabel("홈으로>");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(513, 10, 57, 15);
		getContentPane().add(label_1);

		label_2 = new JLabel("나의 강의실>");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setBounds(566, 10, 74, 15);
		getContentPane().add(label_2);

		label_3 = new JLabel("<html><font color = blue>자격증 발급");
		label_3.setBounds(641, 8, 74, 15);
		getContentPane().add(label_3);

		label_4 = new JLabel("<html>" + uname + "님<br>환영합니다.");
		label_4.setBounds(12, 136, 98, 62);
		getContentPane().add(label_4);

		label_5 = new JLabel(getIcon("icon/certi.jpg", 683, 254));
		label_5.setBounds(98, 35, 683, 266);
		getContentPane().add(label_5);

		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(98, 330, 683, 70);
		getContentPane().add(scrollPane);

		table = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				if (column == 4) {
					comp.setBackground(Color.blue);
					comp.setForeground(Color.white);
				} else {
					comp.setBackground(Color.white);
					comp.setForeground(Color.black);
				}
				return comp;
			}
		};
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);

		setmodel();
		lod();
	}

	List<Integer> cnos = new ArrayList<Integer>();
	private void lod() {
		cnos.clear();
		try (var rs = res("select cno, cname, exam_date,address from test join certi using(cno) join user  using(uno) where uno = "+uno+" order by exam_date")) {
			while(rs.next()) {
				model.addRow(new Object[] {model.getRowCount()+1, rs.getString(2),rs.getDate(3).toLocalDate().plusDays(1).toString(),rs.getString(4),"PDF 저장"});
				cnos.add(rs.getInt(1));
				System.out.println(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setmodel() {
		model = new DefaultTableModel("번호 자격증명 취득일 배송지 PDF".split(" "), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(3).setMinWidth(180);
		table.getColumnModel().getColumn(4).setMinWidth(100);
		table.getTableHeader().setBackground(Color.white);
		table.getTableHeader().setFont(new Font("맑은 고딕", 1, 13));
		table.setRowHeight(scrollPane.getHeight()-24);

	}

	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(table.columnAtPoint(e.getPoint())==4) {
				showPage(new 자격확인서(cnos.get(table.getSelectedRow())),"자격확인서");
			}
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage("A_메인");
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new K_강의(),"K_강의");
		}
	}
}
