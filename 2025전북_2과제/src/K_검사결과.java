import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class K_검사결과 extends BF {

	private JPanel contentPane;
	public JLabel label;
	public JScrollPane scrollPane;
	public JTable table;
	public JLabel label_1;
	public JComboBox comboBox;
	public JTextArea textArea;
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
	public K_검사결과() {
		setTitle("\uAC80\uC0AC \uACB0\uACFC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("굴림", Font.PLAIN, 14));
		label.setBounds(12, 10, 241, 23);
		contentPane.add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 590, 139);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		label_1 = new JLabel("\uAC74\uAC15\uAD00\uB9AC \uACC4\uD68D\uC77C\uC9C0");
		label_1.setFont(new Font("굴림", Font.PLAIN, 14));
		label_1.setBounds(12, 192, 139, 23);
		contentPane.add(label_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uAE00\uB85C \uC791\uC131\uD558\uAE30", "\uADF8\uB9BC\uC73C\uB85C \uC791\uC131\uD558\uAE30"}));
		comboBox.setBounds(163, 192, 106, 23);
		contentPane.add(comboBox);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(12, 218, 590, 119);
		contentPane.add(textArea);
		
		button = new JButton("\uC800\uC7A5\uD558\uAE30");
		button.setBounds(505, 347, 97, 23);
		contentPane.add(button);
		
		try {
			var rs = res("select * from user where uno="+uno);
			rs.next();
			label.setText(rs.getString("name")+"님의 검사결과");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model = new DefaultTableModel("날짜,결과,주치의,병원".split(","),0);
		table.setModel(model);
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer render2 = new DefaultTableCellRenderer();
		render2.setVerticalAlignment(SwingConstants.TOP);
		for (int i = 0; i < 4; i++) {
			if(i!=1) {
				table.getColumnModel().getColumn(i).setCellRenderer(render);
			}
			else {
				table.getColumnModel().getColumn(i).setCellRenderer(render2);
			}
		}
		table.setRowHeight(40);
		try (var rs = res("select r.date, result, d.name dname, h.name hname from record r join doctor d using(dno) join hospital h using(hno) where uno = "+uno+" order by date;")) {
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString(1),"<html>"+rs.getString(2), rs.getString(3),rs.getString(4)});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
