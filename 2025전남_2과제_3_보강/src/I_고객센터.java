import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class I_고객센터 extends BF {
	public JLabel label;
	public JScrollPane scrollPane;
	public JTable table;
	public JButton button;
	public JButton button_1;
	public JButton button_2;
	public JButton button_3;
	public JLabel label_1;
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
		setTitle("\uACE0\uAC1D\uC13C\uD130");
		setBounds(100, 100, 839, 496);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(getIcon("icon/logo.png",40,40));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("굴림", Font.BOLD, 20));
		label.setText("SKills Qualification Association");
		label.setBounds(12, 10, 419, 51);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(12, 67, 799, 318);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);
		
		button = new JButton("<<");
		button.addActionListener(new ButtonActionListener());
		button.setBorder(new LineBorder(new Color(0, 0, 0)));
		button.setBackground(Color.WHITE);
		button.setForeground(Color.GRAY);
		button.setBounds(195, 424, 62, 23);
		getContentPane().add(button);
		
		button_1 = new JButton("<");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_1.setBackground(Color.WHITE);
		button_1.setForeground(Color.GRAY);
		button_1.setBounds(269, 424, 62, 23);
		getContentPane().add(button_1);
		
		button_2 = new JButton(">");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_2.setBackground(Color.WHITE);
		button_2.setForeground(Color.GRAY);
		button_2.setBounds(495, 424, 62, 23);
		getContentPane().add(button_2);
		
		button_3 = new JButton(">>");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_3.setBackground(Color.WHITE);
		button_3.setForeground(Color.GRAY);
		button_3.setBounds(569, 424, 62, 23);
		getContentPane().add(button_3);
		
		label_1 = new JLabel("1");
		label_1.setOpaque(true);
		label_1.setBackground(new Color(0, 0, 255));
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(406, 428, 25, 15);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("\uBB38\uC758 \uD558\uB7EC \uAC00\uAE30 \u2192");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("굴림", Font.PLAIN, 17));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(603, 386, 208, 28);
		getContentPane().add(label_2);

		settable();
		lendering();
	}
	
	int idx = 0;
	public JLabel label_2;
	private void lendering() {
		model.setRowCount(0);
		try (var rs = res("select * from inquiry left join user using(uno) limit "+idx+", 9")) {
			while(rs.next()) {
				String img = rs.getInt("uno") == uno && !isAdmin ? "locker2.png"  : "locker1.png";
				model.addRow(new Object[] {rs.getInt("ino"), rs.getString("uname"), "<html><img src = 'file:datafiles/icon/"+img+"' width = 15 height= 15> 문의 내용입니다.", rs.getString("idate"), rs.getString("answer").isBlank()?"":"V"});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getmax() {
		try {
			var rs =res("select count(*) from inquiry");
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private void settable() {
		model = new DefaultTableModel("번호,이름,문의 내용,날짜,답장여부".split(","), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
		var render =new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getTableHeader().setBorder(new MatteBorder(2, 0,1, 1, Color.blue));
		table.setShowGrid(false);
		table.setRowHeight(30);
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx = 0;
			lendering();
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx = Math.max(0, idx-9);
			lendering();
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int max = getmax();
			idx = max - (max%9==0? 9 : max%9);
			lendering();
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int max = getmax();
			idx = Math.min(idx+9, max - (max%9==0? 9 : max%9));
			lendering();
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new 후기작성폼(), "후기작성폼");
		}
	}
	@Override
	public void updateForm() {
		lendering();
	}
	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				int ino =(int) table.getValueAt(table.rowAtPoint(e.getPoint()), 0);
				showPage(new 답변(ino), "답변");
			}
		}
	}
}
