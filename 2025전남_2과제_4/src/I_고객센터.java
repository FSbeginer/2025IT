import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class I_고객센터 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JScrollPane scrollPane;
	public JTable table;
	public JButton button;
	public JButton button_1;
	public JButton button_2;
	public JButton button_3;
	public JLabel label_2;
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
		setBounds(100, 100, 765, 449);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(getIcon("icon/logo.png",40,40));
		label.setBounds(12, 10, 64, 51);
		getContentPane().add(label);
		
		label_1 = new JLabel();
		label_1.setText("Skills Qualification Association");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		label_1.setBounds(88, 10, 286, 46);
		getContentPane().add(label_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBounds(12, 74, 725, 226);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		button = new JButton("<<");
		button.setBorder(new LineBorder(new Color(0, 0, 0)));
		button.setBackground(new Color(255, 255, 255));
		button.setForeground(new Color(0, 0, 0));
		button.addActionListener(new ButtonActionListener());
		button.setBounds(197, 361, 49, 23);
		getContentPane().add(button);
		
		button_1 = new JButton("<");
		button_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_1.setBackground(new Color(255, 255, 255));
		button_1.setForeground(new Color(0, 0, 0));
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(258, 361, 49, 23);
		getContentPane().add(button_1);
		
		button_2 = new JButton(">");
		button_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_2.setBackground(new Color(255, 255, 255));
		button_2.setForeground(new Color(0, 0, 0));
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBounds(426, 361, 49, 23);
		getContentPane().add(button_2);
		
		button_3 = new JButton(">>");
		button_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_3.setBackground(new Color(255, 255, 255));
		button_3.setForeground(new Color(0, 0, 0));
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setBounds(486, 361, 49, 23);
		getContentPane().add(button_3);
		
		label_2 = new JLabel("1");
		label_2.setOpaque(true);
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setBackground(new Color(0, 0, 255));
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(356, 365, 17, 15);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("문의 하러 가기 →");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_3.setBounds(466, 310, 271, 33);
		getContentPane().add(label_3);

		getMOlset();
		load();
	}
	private void getMOlset() {
		model = new DefaultTableModel("번호,이름,문의 내용,날짜,답장여부".split(","),0);
		table.setModel(model);
		var render =new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getTableHeader().setBorder(new MatteBorder(2,0,1,1,Color.blue));
		table.setRowHeight(20);
		table.setShowGrid(false);
	}
	int idx = 0;
	public JLabel label_3;
	private void load() {
		model.setRowCount(0);
		try (var rs = res("select * from inquiry join user using(uno) limit "+idx+",9")) {
			while(rs.next()) {
				String path  = rs.getInt("uno")==uno&&!isAdmin?"locker2.png" : "locker1.png";
				model.addRow(new Object[] { rs.getInt("ino"),rs.getString("uname"),"<html><img src = 'file:datafiles/icon/"+path+"' width = 15 height = 15> 문의 내용입니다.", rs.getString("idate"),rs.getString("answer").isBlank()?"":"V"});
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
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx = Math.max(0, idx-9);
			load();
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int max = getmax();
			idx = Math.min(idx+9, max - (max%9==0?9:max%9));
			load();
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int max = getmax();
			idx = max - (max%9==0?9:max%9);
			load();
		}

	}
	private int getmax() {
		try {
			var rs= res("select count(*) from inquiry");
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public void updateForm() {
		load();
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new 후기작성폼(), "후기작성폼");
		}
	}
}
