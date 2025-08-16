import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Dimension;
public class PageCommu extends BP {
	List<Integer> target =  new ArrayList<Integer>();
	List<Integer> cno =  new ArrayList<Integer>();
	public JPanel panel_1;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JScrollPane scrollPane_1;
	public JPanel panel_2;
	public JTextField textField;
	public JLabel label_4;
	public JLabel label_5;
	public JButton button;
	private DefaultTableModel model;
	public JPanel panel;
	public JScrollPane scrollPane;
	public JLabel label;
	public JTable table;
	public JLabel label_6;

	/**
	 * Create the panel.
	 */
	public PageCommu() {
		setLayout(new CardLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "name_26173659607600");
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBounds(12, 71, 858, 388);
		panel.add(scrollPane);
		
		table = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				if(target.contains(row)) 
					comp.setForeground(Color.blue);
				else
					comp.setForeground(Color.black);
				return comp;
			}
		};
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);
		
		label = new JLabel("+") {
			protected void paintComponent(Graphics g) {
				g.setColor(BF.blue);
				g.fillOval(0, 0, 40, 40);
				super.paintComponent(g);
			}
		};
		label.addMouseListener(new LabelMouseListener());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label.setBounds(810, 10, 50, 40);
		panel.add(label);
		
		label_6 = new JLabel("\uAE00\uC4F0\uAE30");
		label_6.setBounds(807, 53, 57, 15);
		panel.add(label_6);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		add(panel_1, "name_26745085504100");
		panel_1.setLayout(null);
		
		label_1 = new JLabel(getIcon("아이콘/이전.png",118,50));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(0, 0, 118, 52);
		panel_1.add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setBounds(41, 62, 818, 43);
		panel_1.add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_3.setVerticalAlignment(SwingConstants.TOP);
		label_3.setBounds(41, 115, 384, 319);
		panel_1.add(label_3);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(437, 115, 422, 272);
		panel_1.add(scrollPane_1);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		textField = new PlaceHolder("댓글 달기");
		textField.setBounds(437, 397, 352, 32);
		panel_1.add(textField);
		textField.setColumns(10);
		
		label_4 = new JLabel("\u2192") {
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(BF.blue);
				g.fillOval(0, 0, 50, 50);
				super.paintComponent(g);
			}
		};
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setForeground(new Color(255, 255, 255));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label_4.setBounds(801, 386, 57, 52);
		panel_1.add(label_4);
		
		label_5 = new JLabel("New label");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(198, 444, 232, 21);
		panel_1.add(label_5);
		
		button = new JButton("\uC0AD\uC81C\uD558\uAE30");
		button.setVisible(false);
		button.setBackground(Color.RED);
		button.setForeground(new Color(255, 255, 255));
		button.setBounds(41, 444, 97, 23);
		panel_1.add(button);
		
		setTable();
		load();
	}
	private void load() {
		model.setRowCount(0);
		cno.clear();
		target.clear();
		try (var rs = res("select * from community order by uno!=0, date;")) {
			int cnt = 1;
			while(rs.next()) {
				int uno = rs.getInt("uno");
				if((uno==BF.uno&&uno!=0)||(uno==0&&BF.isAdmin)) {
					target.add(model.getRowCount());
				}
				model.addRow(new Object[] {uno==0?"공지":cnt++, rs.getString("title"), rs.getString("date"), rs.getInt("view")});
				cno.add(rs.getInt("cno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setTable() {
		model = new DefaultTableModel("번호,제목,날짜,조회수".split(","), 0);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.setModel(model);
		table.setDefaultRenderer(Object.class, render);
	}
	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			button.setVisible(target.contains(row));
			textField.setText("");
			loadComment();
			((CardLayout)getLayout()).next(PageCommu.this);
		}

	}
	private void loadComment() {
		panel_2.removeAll();
		try (var rs = res("select *, u.uno cuno,c.date cdate, co.date codate from community c join comment co using(cno) join user u on co.uno = u.uno where cno = "+cno.get(table.getSelectedRow()))) {
			int i = 0;
			int bt=0;
			while(rs.next()) {
				label_2.setText(rs.getString("title"));
				label_3.setText("<html>"+rs.getString("detail"));
				label_5.setText(rs.getString("cdate"));
				PanelComment pc = new PanelComment(rs.getString("name"), rs.getString("text"), rs.getString("codate"));
				pc.setLocation(0, (pc.getHeight()+40)*i);
				pc.label_2.setVisible(rs.getInt("cuno")==BF.uno);
				int co = rs.getInt("cono");
				pc.label_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							execute("delete from comment where cono ="+co);
							loadComment();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				panel_2.add(pc);
				bt = pc.getY()+pc.getHeight();
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, bt));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_2.revalidate();
		panel_2.repaint();
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((CardLayout)getLayout()).previous(PageCommu.this);
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try (var pre = pre("insert into comment values(0,?,?,?,?)")) {
				preSet(pre, cno.get(table.getSelectedRow()), BF.uno, textField.getText(), LocalDate.now());
				pre.execute();
				loadComment();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(PageCommu.this)).showPage(new 글쓰기(), "글쓰기");
		}
	}
}
