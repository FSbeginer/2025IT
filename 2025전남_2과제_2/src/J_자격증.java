import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.table.TableCellRenderer;
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
		setBounds(100, 100, 851, 446);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("자격증 발급");
		label.setForeground(new Color(0, 0, 255));
		label.setBounds(12, 10, 116, 15);
		getContentPane().add(label);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(112, 28, 683, 2);
		getContentPane().add(panel);
		
		label_1 = new JLabel("홈으로>");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_1.setBounds(559, 10, 44, 15);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setBounds(12, 120, 98, 67);
		getContentPane().add(label_2);
		
		label_3 = new JLabel(getIcon("icon/certi.jpg",683,255));
		label_3.setBounds(122, 40, 672, 255);
		getContentPane().add(label_3);
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(112, 317, 683, 67);
		getContentPane().add(scrollPane);
		
		
		table = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				if(column==4) {
					comp.setBackground(Color.blue);
					comp.setForeground(Color.white);
				}
				else {
					comp.setBackground(Color.white);
					comp.setForeground(Color.black);
				}
				return comp;
			}
		};
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);
		
		label_4 = new JLabel("나의 강의실>");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_4.setBounds(615, 10, 77, 15);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("자격증발급");
		label_5.setForeground(Color.BLUE);
		label_5.setBounds(695, 10, 69, 15);
		getContentPane().add(label_5);

		getmodel();
		load();
	}

	private void getmodel() {
		model = new DefaultTableModel("번호 자격증명 취득일 배송지 PDF".split(" "), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(3).setMinWidth(150);
		table.setRowHeight(scrollPane.getHeight()-20);
		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
	}
	
	List<Integer> cno = new ArrayList<>();
	public JLabel label_4;
	public JLabel label_5;
	private void load() {
		try {
			var rs =res("select * from certi join test using(cno) join user using(uno) where uno ="+uno+" and passed = 1");
			while(rs.next()) {
				model.addRow(new Object[] {model.getRowCount()+1, rs.getString("cname"),rs.getDate("exam_date").toLocalDate().plusDays(1), rs.getString("address"), "PDF저장"});
				cno.add(rs.getInt("cno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(table.columnAtPoint(e.getPoint())==4) {
				showPage(new 자격확인서(cno.get(table.getSelectedRow())), "자격확인서");
			}
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage("A_메인");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new K_강의(-1),"K_강의");
		}
	}
}
