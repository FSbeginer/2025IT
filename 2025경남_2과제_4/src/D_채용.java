import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.LinkedList;
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
		setTitle("\uCC44\uC6A9");
		setBounds(100, 100, 585, 680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new MainLogo(84, 40);
		label.setBounds(12, 10, 84, 40);
		getContentPane().add(label);

		textField = new PlaceHolder("검색");
		textField.setBorder(new LineBorder(new Color(255, 128, 0)));
		textField.setBounds(108, 10, 320, 33);
		getContentPane().add(textField);
		textField.setColumns(10);

		label_1 = new JLabel(getIcon("icon/search.png", 50, 50));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(439, 0, 57, 50);
		getContentPane().add(label_1);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC804\uCCB4" }));
		comboBox.setBounds(22, 63, 130, 33);
		getContentPane().add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ComboBox_1ActionListener());
		comboBox_1.setModel(new DefaultComboBoxModel(
				new String[] { "\uAE30\uBCF8\uC21C", "\uC778\uAE30\uC21C", "\uAE09\uC5EC\uB192\uC740\uC21C" }));
		comboBox_1.setBounds(427, 63, 130, 33);
		getContentPane().add(comboBox_1);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(255, 128, 0)));
		scrollPane.setBounds(12, 106, 545, 525);
		getContentPane().add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		addcate();
		load();
	}

	private void addcate() {
		try (var rs = res("select * from category ")) {
			while (rs.next()) {
				comboBox.addItem(rs.getString("cname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	String where ="", like ="", order="";
	Random rand = new Random();
	private void load() {
		panel.removeAll();
		Queue<Object[]> queue = new LinkedList<Object[]>();
		try {
			var rs =res("select * from advertise where aname like '%"+like+"%'");
			while(rs.next()) {
				queue.add(new Object[] {rs.getInt(1),rs.getString(2)});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select *,count(*) cnt from job left join apply using(jno) left join brand using(bno) where jname like '%"+like+"%' "+where+" group by jno "+order)) {
			int w =  505 ,h= 96,i=0;
			while(rs.next()) {
				if(rand.nextBoolean()&&!queue.isEmpty()) {
					var d =queue.poll();
					D_패널 pp = new D_패널(d[1].toString());
					pp.label.setPreferredSize(new Dimension(w/4,0));
					pp.label.setIcon(getIcon("advertise/"+d[0]+"-1.jpg",w/4,h));
					pp.setLocation(10, 10+(10+h)*i);
					pp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							var db = new E_광고정보((int)d[0]);
							db.setVisible(true);
						}
					});
					panel.add(pp);
					i++;
				}
				D_패널 pp = new D_패널(rs.getString("jname"));
				pp.setLocation(10, 10+(10+h)*i);
				int jno = rs.getInt("jno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new C_알바정보(jno),"C_알바정보");
					}
				});
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0,10+(h+10)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		panel.revalidate();
		panel.repaint();
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			like = textField.getText();
			load();
		}
	}
	private class ComboBox_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_1.getSelectedIndex()==0) {
				order = "";
			}
			else if(comboBox_1.getSelectedIndex()==1) {
				order = "order by cnt desc,jno";
			}
			else {
				order = "order by jmoney desc,jno";
			}
			load();
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				where = " ";
			}
			else {
				where = "and cno = "+comboBox.getSelectedIndex();
			}
			load();
		}
	}
}
