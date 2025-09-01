import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class D_채용 extends BF {
	public JLabel label;
	public JTextField textField;
	public JLabel label_1;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JScrollPane scrollPane;
	public JPanel panel;
	private String like = "";
	private String order = "order by jno",where ="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					D_채용 frame = new D_채용();
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
	public D_채용() {
		addWindowListener(new ThisWindowListener());
		setTitle("\uCC44\uC6A9");
		setBounds(100, 100, 592, 637);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new MainLogo(110,50);
		label.setBounds(12, 10, 110, 50);
		getContentPane().add(label);
		
		textField = new PlaceHolder("검색");
		textField.setBorder(new LineBorder(new Color(255, 128, 0)));
		textField.setBounds(134, 24, 323, 36);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel(getIcon("icon/search.png",40,40));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(469, 20, 45, 42);
		getContentPane().add(label_1);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4"}));
		comboBox.setBounds(12, 78, 188, 28);
		getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ComboBox_1ActionListener());
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\uAE30\uBCF8", "\uC778\uAE30", "\uAE09\uC5EC"}));
		comboBox_1.setBounds(432, 78, 132, 28);
		getContentPane().add(comboBox_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(255, 128, 0)));
		scrollPane.setBounds(12, 116, 552, 472);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		addcate();
		load();
	}

	private void addcate() {
		try (var rs = res("select* from category")) {
			while(rs.next()) {
				comboBox.addItem(rs.getString("cname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void load() {
		panel.removeAll();
		
		Queue<Object[]> list = new LinkedList<>();
		try (var rs = res("select * from advertise where aname like '%"+like+"%' order by rand()")) {
			while(rs.next()) {
				list.add(new Object[] {rs.getInt("ano"), rs.getString("aname")});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Random rand= new Random();
		
		try (var rs = res("select *, count(*) cnt from job join brand using(bno) left join apply using(jno) where jname like '%"+like+"%' "+where+" group by jno "+order)) {
			int i = 0;
			int w =  508;
			int h = 90;
			while(rs.next()) {
				if(rand.nextBoolean()&&!list.isEmpty()) {
					var data = list.poll();
					int ano = (int) data[0];
					String name = (String) data[1];
					D_채용패널 pp =new D_채용패널(name);
					pp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							var d = new E_광고정보(ano);
							d.setVisible(true);
						}
					});
					pp.label.setIcon(getIcon("advertise/"+ano+"-1.jpg",200,90));
					pp.label.setPreferredSize(new Dimension(200,0));
					pp.setLocation(10, 10+(100)*(i));
					panel.add(pp);
					i++;
				}
				D_채용패널 pp =new D_채용패널(rs.getString("jname"));
				int jno =rs.getInt("jno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new C_알바정보(jno), "C_알바정보");
					}
				});
				pp.setLocation(10, 10+(100)*(i));
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, 10+(100)*(i)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		panel.revalidate();
		panel.repaint();
	}

	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			showPage("B_메인");
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			like = textField.getText();
			load();
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				where = "";
			}
			else {
				where = "and cno ="+comboBox.getSelectedIndex();
			}
			load();
		}
	}
	private class ComboBox_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_1.getSelectedIndex()==0) {
				order = "order by jno";
			}
			else if(comboBox_1.getSelectedIndex()==1) {
				order = "order by cnt desc"; 
			}
			else {
				order = "order by jmoney desc";
			}
			load();
		}
	}
}
