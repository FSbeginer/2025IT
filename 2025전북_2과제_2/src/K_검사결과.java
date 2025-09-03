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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class K_검사결과 extends BF {
	public JLabel label;
	public JScrollPane scrollPane;
	public JTable table;
	public JLabel label_1;
	public JComboBox comboBox;
	public JPanel panel;
	public JTextArea textArea;
	public JPanel panel_1;
	public JButton button;
	private DefaultTableModel model;

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
	JLabel jl = new JLabel("글 작성");
	public K_검사결과() {
		setTitle("\uAC80\uC0AC\uACB0\uACFC");
		setBounds(100, 100, 719, 441);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(uname+"님의 검사결과");
		label.setFont(new Font("굴림", Font.BOLD, 15));
		label.setBounds(12, 10, 403, 39);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 65, 679, 125);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		label_1 = new JLabel("\uAC74\uAC15\uAD00\uB9AC \uACC4\uD68D\uC77C\uC9C0");
		label_1.setBounds(12, 200, 192, 30);
		getContentPane().add(label_1);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uAE00\uB85C \uC791\uC131\uD558\uAE30", "\uADF8\uB9BC\uC73C\uB85C \uC791\uC131\uD558\uAE30"}));
		comboBox.setBounds(136, 204, 136, 26);
		getContentPane().add(comboBox);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 240, 679, 119);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		jl.setEnabled(false);
		jl.setVerticalAlignment(SwingConstants.TOP);
		textArea = new JTextArea() {
			{
				setLayout(new BorderLayout());
				add(jl);
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				jl.setVisible(textArea.getText().isBlank());
			}
		};
		panel.add(textArea, "name_26871422616600");
		
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
		panel_1.addMouseListener(new Panel_1MouseListener());
		panel_1.addMouseMotionListener(new Panel_1MouseMotionListener());
		panel.add(panel_1, "name_26873453428700");
		
		button = new JButton("\uC800\uC7A5\uD558\uAE30");
		button.setBounds(583, 369, 108, 23);
		getContentPane().add(button);

		getmodel();
		load();
	}

	private void getmodel() {
		model = new DefaultTableModel("날짜 결과 주치의 병원".split(" "), 0);
		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		var render2 = new DefaultTableCellRenderer();
		render2.setVerticalAlignment(SwingConstants.TOP);
		table.setModel(model);
		table.setDefaultRenderer(Object.class, render);
		table.getColumnModel().getColumn(1).setMinWidth(150);
		table.getColumnModel().getColumn(1).setCellRenderer(render2);
		table.setRowHeight(40);
	}
	
	List<List<Point>> plist = new ArrayList<List<Point>>();
	int idx = 0;
	private void load() {
		try {
			var rs =res("select *, d.name dname, h.name hname from record join doctor d using(dno) join hospital h using(hno)  where uno = "+uno);
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString("date"),"<html>"+rs.getString("result"),rs.getString("dname"),rs.getString("hname")});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0)
				((CardLayout)panel.getLayout()).first(panel);
			else {
				((CardLayout)panel.getLayout()).last(panel);
			}
		}
	}
	private class Panel_1MouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			plist.get(idx).add(e.getPoint());
			panel_1.repaint();
		}
	}
	private class Panel_1MouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			plist.add(new ArrayList<Point>());
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			idx++;
		}
	}
}
