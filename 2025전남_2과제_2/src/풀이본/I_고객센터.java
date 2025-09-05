package 풀이본;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	public JLabel label_3;
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
		setBounds(100, 100, 824, 427);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(getIcon("icon/logo.png",40,40));
		label.setBounds(12, 10, 57, 47);
		getContentPane().add(label);
		
		label_1 = new JLabel("Skills Qualification Association");
		label_1.setBounds(81, 10, 264, 47);
		getContentPane().add(label_1);
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new ScrollPaneMouseListener());
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(22, 67, 761, 232);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		button = new JButton("<<");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(new Color(255, 255, 255));
		button.setBorder(new LineBorder(new Color(0, 0, 0)));
		button.setBounds(187, 332, 64, 23);
		getContentPane().add(button);
		
		button_1 = new JButton("<");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(263, 332, 64, 23);
		getContentPane().add(button_1);
		
		button_2 = new JButton(">");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(479, 332, 64, 23);
		getContentPane().add(button_2);
		
		button_3 = new JButton(">>");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(555, 332, 64, 23);
		getContentPane().add(button_3);
		
		label_2 = new JLabel("1");
		label_2.setOpaque(true);
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(Color.BLUE);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(388, 332, 20, 23);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("문의 하러 가기 →");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_3.setBounds(594, 309, 188, 15);
		getContentPane().add(label_3);
		
		try {
			var rs =res("select count(*) from inquiry join user using(uno);");
			rs.next();
			max =rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getmodel();
		load();
	}

	private void getmodel() {
		model = new DefaultTableModel("번호,이름,문의 내용,날짜,답장여부".split(","), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		var render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
		table.getTableHeader().setBorder(new MatteBorder(2, 0, 1, 1, Color.BLUE));
		table.setShowGrid(false);
		table.setRowHeight(scrollPane.getHeight()/10);
	}
	List<Integer> inos = new ArrayList<>();
	@Override
	public void updateForm() {
		try {
			var rs =res("select count(*) from inquiry join user using(uno);");
			rs.next();
			max =rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		idx = 0;
		load();
	}

	int max, idx;
	private void load() {
		model.setRowCount(0);
		inos.clear();
		try {
			var rs = res("select * from inquiry join user using(uno) limit "+idx+",9");
			while(rs.next()) {
				String path = (uno!=0&&uno==rs.getInt("uno"))||(isAdmin)? "locker2.png" : "locker1.png";
				String data = rs.getString("answer").isBlank() ? "" : "⩗";
				inos.add(rs.getInt("ino"));
				model.addRow(new Object[] {rs.getInt("ino"), rs.getString("uname"), "<html><img src = 'file:datafiles/icon/"+path+"' width =15 height = 15> 문의 내용입니다.", rs.getString("idate"), data});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx = Math.max(0, idx-9);
			load();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx = 0;
			load();
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx =Math.min(idx+9, max - (max%9==0?9:max%9));
			load();
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx = max - (max%9==0?9:max%9);
			load();
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new 후기작성폼(),"후기작성폼");
		}
	}
	private class ScrollPaneMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2 && table.getSelectedRow()!=-1&&isAdmin) {
				showPage(new 후기작성폼(inos.get(table.getSelectedRow())),"후기작성폼");
			}
		}
	}
}
