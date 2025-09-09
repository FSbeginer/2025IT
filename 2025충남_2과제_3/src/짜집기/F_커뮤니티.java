package 짜집기;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Font;
import java.awt.Graphics;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class F_커뮤니티 extends BP {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JScrollPane scrollPane;
	public JLabel label_1;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public F_커뮤니티() {
		setLayout(new CardLayout(0, 0));

		panel = new JPanel();
		add(panel, "name_10343615276500");
		panel.setLayout(null);

		label = new JLabel("+") {
			protected void paintComponent(Graphics g) {
				g.setColor(BF.blue);
				g.fillOval(0, 0, 60, 60);
				super.paintComponent(g);
			}
		};
		label.addMouseListener(new LabelMouseListener());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("굴림", Font.BOLD, 30));
		label.setBounds(890, 10, 60, 60);
		panel.add(label);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 80, 927, 377);
		panel.add(scrollPane);

		table = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				if (target.contains(row)) {
					comp.setForeground(BF.blue);
				} else {
					comp.setForeground(Color.black);
				}

				return comp;
			}
		};
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);

		label_1 = new JLabel("\uAE00\uC4F0\uAE30");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(900, 68, 47, 15);
		panel.add(label_1);

		panel_1 = new JPanel();
		add(panel_1, "name_10346798053600");
		panel_1.setLayout(null);

		label_2 = new JLabel(getIcon("아이콘/이전.png", 132, 58));
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setBounds(8, 3, 97, 45);
		panel_1.add(label_2);

		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_3.setBounds(31, 71, 342, 33);
		panel_1.add(label_3);

		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(31, 114, 405, 345);
		panel_1.add(textArea);

		label_4 = new JLabel("New label");
		label_4.setForeground(Color.GRAY);
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(229, 469, 206, 23);
		panel_1.add(label_4);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(448, 114, 426, 323);
		panel_1.add(scrollPane_1);

		panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);

		textField = new PlaceHolder("댓글 달기");
		textField.setBounds(448, 447, 367, 21);
		panel_1.add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uC0AD\uC81C\uD558\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(Color.RED);
		button.setBounds(31, 469, 97, 23);
		panel_1.add(button);
		
				label_5 = new JLabel("\u2192") {
					protected void paintComponent(Graphics g) {
						g.setColor(BF.blue);
						g.fillOval(0, 0, 45, 45);
						super.paintComponent(g);
					}
				};
				label_5.setForeground(new Color(255, 255, 255));
				label_5.setBounds(827, 437, 45, 45);
				panel_1.add(label_5);
				label_5.addMouseListener(new Label_5MouseListener());
				label_5.setFont(new Font("맑은 고딕", Font.BOLD, 32));
				label_5.setHorizontalAlignment(SwingConstants.CENTER);

		card = (CardLayout) getLayout();
		model();
		load();
	}

	private void model() {
		model = new DefaultTableModel("번호,제목,날짜,조회수".split(","), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setModel(model);
		table.setDefaultRenderer(Object.class, render);
		table.getColumnModel().getColumn(1).setMinWidth(200);
	}

	List<Integer> cnos = new ArrayList<Integer>();
	List<Integer> target = new ArrayList<Integer>();

	private void load() {
		cnos.clear();
		model.setRowCount(0);
		target.clear();
		try (var rs = res("select * from community order by uno !=0, date;")) {
			int i = 1;
			int r = 0;
			while (rs.next()) {
				cnos.add(rs.getInt("cno"));
				String no = rs.getInt("uno") == 0 ? "공지" : i++ + "";
				model.addRow(new Object[] { no, rs.getString("title"), rs.getString("date"), rs.getInt("view") });
				if ((BF.uno != 0 && rs.getInt("uno") == BF.uno) || (BF.isAdmin && rs.getInt("uno") == 0))
					target.add(r);
				r++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	CardLayout card;
	public JTable table;
	public JLabel label_2;
	public JLabel label_3;
	public JTextArea textArea;
	public JLabel label_4;
	public JScrollPane scrollPane_1;
	public JPanel panel_2;
	public JTextField textField;
	public JLabel label_5;
	public JButton button;

	private void load2(int cno) {
		int writer = 0;
		try (var rs = res("select * from community where cno = "+cno)) {
			rs.next();
			label_3.setText("<html>" + rs.getString("title"));
			textArea.setText(rs.getString("detail"));
			label_4.setText(rs.getString("date"));
			writer = rs.getInt("uno");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from comment join user using(uno) where cno = " + cno)) {
			int w = 0, h = 0, i = 0;
			while (rs.next()) {
				F_댓글 pp = new F_댓글(rs.getString("name"), rs.getString("text"), rs.getString("date"));
				h = pp.getHeight();
				pp.setLocation(0, (h+20)*i);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, (h+20)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		button.setVisible(writer==BF.uno);
		if(writer==0) {
			button.setVisible(BF.isAdmin);
		}
	}

	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			load2(cnos.get(table.getSelectedRow()));
			try {
				execute("update community set view = view + 1 where cno = " + cnos.get(table.getSelectedRow()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			card.last(F_커뮤니티.this);
		}

	}

	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			load();
			card.first(F_커뮤니티.this);
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var txt = textField.getText();
			try (var pre = pre("insert into comment values(0,?,?,?,?)")) {
				preSet(pre, cnos.get(table.getSelectedRow()), BF.uno, txt, LocalDate.now());
				pre.execute();
				load2(cnos.get(table.getSelectedRow()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				execute("delete from comment where cno = "+cnos.get(table.getSelectedRow()));
				execute("delete from community where cno = "+cnos.get(table.getSelectedRow()));
				load();
				card.first(F_커뮤니티.this);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(BF.uno!=0||BF.isAdmin)
				((MainFrame)SwingUtilities.getWindowAncestor(scrollPane)).showPage(new G_글쓰기(),"G_글쓰기");
			else {
				msgErr("로그인 후 이용가능합니다.");
			}
		}
	}
}
