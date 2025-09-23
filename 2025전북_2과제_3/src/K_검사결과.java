import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class K_검사결과 extends BF {
	public JLabel label;
	public JScrollPane scrollPane;
	public JTable table;
	public JPanel panel;
	public JButton button;
	public JTextArea textArea;
	public JPanel panel_1;
	public JLabel label_1;
	public JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					K_검사결과 frame = new K_검사결과();
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
	public K_검사결과() {
		setTitle("\uAC80\uC0AC \uACB0\uACFC");
		setBounds(100, 100, 674, 451);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(uname+"님의 검사 결과");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label.setBounds(12, 10, 248, 36);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 53, 634, 133);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 250, 634, 119);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panel.add(textArea, "name_10002291019400");
		
		panel_1 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (List<Point> list : plist) {
					for (int i = 0; i < list.size()-1; i++) {
						g.drawLine(list.get(i).x, list.get(i).y, list.get(i+1).x, list.get(i+1).y);
					}
				}
			}
		};
		panel_1.addMouseMotionListener(new Panel_1MouseMotionListener());
		panel_1.addMouseListener(new Panel_1MouseListener());
		panel.add(panel_1, "name_10006973041200");
		
		button = new JButton("\uC800\uC7A5\uD558\uAE30");
		button.setBounds(549, 379, 97, 23);
		getContentPane().add(button);
		
		label_1 = new JLabel("\uAC74\uAD11\uAD00\uB9AC \uACC4\uD68D\uC77C\uC9C0");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_1.setBounds(12, 212, 146, 15);
		getContentPane().add(label_1);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uAE00\uB85C \uC791\uC131\uD558\uAE30", "\uADF8\uB9BC\uC73C\uB85C \uC791\uC131\uD558\uAE30"}));
		comboBox.setBounds(153, 211, 133, 23);
		getContentPane().add(comboBox);

		card = (CardLayout) panel.getLayout();
		getmodel();
		load();
	}
	
	private void load() {
		try (var rs = res("select date, result, d.name dn, h.name hn from record r join doctor d using(dno) join hospital h using(hno) where uno = "+uno+" order by date")) {
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString(1),"<html>"+rs.getString(2),rs.getString(3)+"의사",rs.getString(4)});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	CardLayout card;
	private DefaultTableModel model;
	
	private void getmodel() {
		model = new DefaultTableModel("날짜 결과 주치의 병원".split(" "), 0);
		table.setModel(model);
		table.getColumnModel().getColumn(1).setMinWidth(150);
		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		var render2 = new DefaultTableCellRenderer();
		render2.setVerticalAlignment(SwingConstants.TOP);
		table.setDefaultRenderer(Object.class, render);
		table.getColumnModel().getColumn(1).setCellRenderer(render2);
		table.setRowHeight(50);
	}
	int idx = 0;
	List<List<Point>> plist = new ArrayList<List<Point>>(); 
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0)
				card.first(panel);
			else {
				card.last(panel);
			}
		}
	}
	private class Panel_1MouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			plist.add(new ArrayList<>());
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			idx++;
		}
	}
	private class Panel_1MouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			plist.get(idx).add(e.getPoint());
			panel_1.repaint();
		}
	}
}
