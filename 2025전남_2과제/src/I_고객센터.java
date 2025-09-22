import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class I_고객센터 extends BF {
	public JLabel label;
	public JScrollPane scrollPane;
	public JTable table;
	public JLabel label_1;
	public JButton button;
	public JButton button_1;
	public JButton button_2;
	public JButton button_3;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I_고객센터 frame = new I_고객센터();
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
	public I_고객센터() {
		setTitle("고객센터");
		setBounds(100, 100, 855, 515);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(getIcon("icon/logo.png",50,50));
		label.setText("Skills Qualification Association");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label.setBounds(12, 10, 346, 53);
		getContentPane().add(label);
		
		label_1 = new JLabel("1");
		label_1.setOpaque(true);
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBackground(Color.BLUE);
		label_1.setBounds(399, 429, 23, 15);
		getContentPane().add(label_1);
		
		button = new JButton("<<");
		button.addActionListener(new ButtonActionListener());
		button.setBorder(new LineBorder(new Color(0, 0, 0)));
		button.setForeground(Color.BLACK);
		button.setBackground(Color.WHITE);
		button.setBounds(110, 425, 81, 23);
		getContentPane().add(button);
		
		button_1 = new JButton("<");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_1.setForeground(Color.BLACK);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(206, 425, 81, 23);
		getContentPane().add(button_1);
		
		button_2 = new JButton(">");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_2.setForeground(Color.BLACK);
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(513, 425, 81, 23);
		getContentPane().add(button_2);
		
		button_3 = new JButton(">>");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_3.setForeground(Color.BLACK);
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(610, 425, 81, 23);
		getContentPane().add(button_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(22, 90, 805, 269);
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.getViewport().setBorder(null);;
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new TableMouseListener());
		table.setShowGrid(false);
		table.setBackground(Color.WHITE);
		table.setBorder(null);
		scrollPane.setViewportView(table);
		
		label_2 = new JLabel("문의 하러 가기 →");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setForeground(Color.GRAY);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("굴림", Font.BOLD, 17));
		label_2.setBounds(568, 369, 259, 30);
		getContentPane().add(label_2);
		
		try {
			var rs = res("select count(*) from inquiry");
			rs.next();
			max = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model();
		load();
	}
	private void model() {
		model = new DefaultTableModel("번호,이름,문의 내용,날짜,답장여부".split(","), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMaxWidth(70);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, render);
		table.setRowHeight((scrollPane.getHeight()-40)/9);
		table.getTableHeader().setBorder(new MatteBorder(2,0,1,1,Color.blue));
	}
	
	int idx = 0, max;
	public JLabel label_2;
	private void load() {
		model.setRowCount(0);
		try (var rs = res("select * from inquiry join user using(uno) order by ino limit "+idx+",9 ")) {
			while(rs.next()) {
				int img = rs.getInt("uno")==uno? 2 : 1;
				if(teacher) img = 2;
				boolean answer = !rs.getString("answer").isBlank();					
				model.addRow(new Object[] { rs.getInt("ino"), rs.getString("uname"),"<html><img src ='file:datafiles/icon/locker"+img+".png' width = 20 height = 20> 문의 내용입니다.", rs.getString("idate"), answer? "ⅴ" : ""});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx = 0;
			load();
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx = max-max%9;
			load();
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx = Math.max(0, idx-9);
			load();
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx = Math.min(max-max%9, idx+9);
			load();
		}
	}
	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				showPage(new 후기작성폼(), "후기작성폼");
			}
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new 후기작성폼(), "후기작성폼");
		}
	}
}
