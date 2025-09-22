package 답없음;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class wait extends JFrame {

	private JPanel contentPane;
	public JScrollPane scrollPane;
	public JTable table;
	public JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wait frame = new wait();
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
	public wait() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 75, 464, 176);
		contentPane.add(scrollPane);
		
		table = new JTable() {
			@Override
			public Class<?> getColumnClass(int column) {
				if(column==1)
					return JLabel.class;
				return super.getColumnClass(column);
			}
		};
		scrollPane.setViewportView(table);
		
		DefaultTableModel model = new DefaultTableModel("아,오,에,이".split(","),0);
		table.setModel(model);
		
		label = new JLabel("<html><img src = 'file:datafiles/아이콘/아이콘.png'; width = 20; height = 20;>ㄴ");
		label.setToolTipText("<html><img src = 'file:datafiles/아이콘/아이콘.png' width = 20 height = 20>ㅈ");
		label.setBounds(49, 275, 521, 67);
		contentPane.add(label);
		
		table.setRowHeight(20);
		model.addRow(new Object[] {1,"<html><img src = 'file:datafiles/아이콘/아이콘.png' width = 20 height = 20>ㅇ", 2, 3});
	}

}
