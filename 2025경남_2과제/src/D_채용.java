import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setBounds(100, 100, 656, 625);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new MainIcon();
		label.setBounds(12, 0, 123, 58);
		getContentPane().add(label);
		
		textField = new PlaceHolder("검색");
		textField.setBorder(new LineBorder(orange));
		textField.setBounds(135, 10, 379, 36);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel(getIcon("icon/search.png",45,45));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(526, 8, 67, 50);
		getContentPane().add(label_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4"}));
		comboBox.setBounds(12, 60, 173, 36);
		getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\uAE30\uBCF8\uC21C", "\uC778\uAE30\uC21C", "\uAE09\uC5EC\uB192\uC740\uC21C"}));
		comboBox_1.setBounds(505, 56, 123, 36);
		getContentPane().add(comboBox_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(orange));
		scrollPane.setBounds(22, 106, 606, 470);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		addcategory();
		load("", "","", "order by jno");
	}

	private void addcategory() {
		try (var rs = res("select * from category")) {
			while(rs.next()) {
				comboBox.addItem(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void load(String where, String like1, String like2, String order) {
		panel.removeAll();
		
		Random rand = new Random();
		Queue<Integer> queue = new LinkedList<>();
		List<Integer> list = new ArrayList<Integer>();
		try {
			var rs = res("select * from advertise where true "+like1);
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Collections.shuffle(list);
		queue.addAll(list);
		try (var rs = res("select *, count(*) cnt from job left join apply using(jno) join brand using(bno) where true "+where+" "+like2+" group by jno "+order)) {
			int i = 0;
			int w = (scrollPane.getWidth()-45), h =scrollPane.getHeight()/5;
			while(rs.next()) {
				if(where.isBlank()&&rand.nextBoolean()&&!queue.isEmpty()) {
					int ano = queue.poll();
					var ad = res("select aname from advertise where ano = "+ano);
					ad.next();
					채용패널 pp = new 채용패널(ad.getString(1));
					pp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							var E = new E_광고정보(ano);
							E.setVisible(true);
						}
					});
					pp.label.setIcon(getIcon("advertise/"+ano+"-1.jpg",w/4,h));
					pp.label.setPreferredSize(new Dimension(w/4,0));
					pp.setLocation(10, 10+(h+10)*i);
					pp.setSize(w, h);
					panel.add(pp);
					i++;
				}
				채용패널 pp =new 채용패널(rs.getString("jname"));
				int jno = rs.getInt("jno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new C_알바정보(jno), "C_알바정보");
					}
				});
				pp.setLocation(10, 10+(h+10)*i);
				pp.setSize(w, h);
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, 10+(10+h)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			String where = "", like1 = "and aname like '%"+textField.getText()+"%'", like2 ="and jname like '%"+textField.getText()+"%'", order = "";
			switch (comboBox.getSelectedIndex()) {
			case 0:
				where = "";
				break;
			default:
				where = "and cno = "+comboBox.getSelectedIndex();
				break;
			}
			
			switch (comboBox_1.getSelectedIndex()) {
			case 0:
				order = "order by jno";
				break;
			case 1:
				order = "order by cnt desc";
				break;
			case 2:
				order = "order by jmoney";
				break;
			}
			
			load(where, like1, like2, order);
		}
	}
}
