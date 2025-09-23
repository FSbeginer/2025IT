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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		addWindowListener(new ThisWindowListener());
		setTitle("\uCC44\uC6A9");
		setBounds(100, 100, 574, 614);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new MainLogo(116, 53);
		label.setBounds(12, 10, 116, 53);
		getContentPane().add(label);
		
		textField = new PlaceHolder("검색");
		textField.setBorder(new LineBorder(new Color(255, 128, 0)));
		textField.setBounds(140, 21, 294, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel(getIcon("icon/search.png", 50, 50));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(446, 10, 57, 53);
		getContentPane().add(label_1);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4"}));
		comboBox.setBounds(12, 82, 124, 28);
		getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ComboBox_1ActionListener());
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\uAE30\uBCF8\uC21C", "\uC778\uAE30\uC21C", "\uAE09\uC5EC\uB192\uC740\uC21C"}));
		comboBox_1.setBounds(422, 82, 124, 28);
		getContentPane().add(comboBox_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 123, 534, 442);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		load();
	}

	String like = "",where = "", order = "jno";
	Random rand = new Random();
	private void load() {
		panel.removeAll();
		Queue<Integer> anos = new LinkedList<>();
		try (var rs = res("select * from advertise where aname like '%"+like+"%'")) {
			while(rs.next()) {
				anos.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select *, count(*) cnt from job join brand using(bno) left join apply using(jno) where true "+where+" and jname like '%"+ like+"%' group by jno order by "+order)) {
			int w = scrollPane.getWidth()-40;
			int h = (scrollPane.getHeight()-50)/5;
			int i = 0;
			while(rs.next()) {
				if(rand.nextBoolean()&&!anos.isEmpty()) {
					int ano = anos.poll();
					var ad =res("select * from advertise where ano = "+ano);
					ad.next();
					D_패널 pp =new D_패널(ad.getString("aname"));
					pp.label.setPreferredSize(new Dimension(w/3,0));
					pp.label.setIcon(getIcon("advertise/"+ano+"-1.jpg",w/3, h));
					pp.setSize(w, h);
					pp.setLocation(10, 10+(h+10)*i);
					pp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							var d =new E_광고정보(ano);
							d.setVisible(true);
						}
					});
					panel.add(pp);
					i++;
				}
				D_패널 pp =new D_패널(rs.getString("jname"));
				pp.setSize(w, h);
				pp.setLocation(10, 10+(h+10)*i);
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
			panel.setPreferredSize(new Dimension(0, 10+(h+10)*i));
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
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				where = "";
			}
			else
				where = "and cno = "+comboBox.getSelectedIndex();
			load();
		}
	}
	private class ComboBox_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_1.getSelectedIndex()==0) {
				order = "jno";
			}
			else if(comboBox_1.getSelectedIndex()==1)
				order = "cnt desc, jno";
			else
				order = "jmoney desc, jno";
			load();
		}
	}
	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			showPage("B_메인");
		}
	}
}
