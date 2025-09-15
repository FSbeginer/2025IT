import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class C_영화검색 extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JTextField textField;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C_영화검색 frame = new C_영화검색();
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
	public C_영화검색() {
		setTitle("영화 검색");
		addWindowListener(new ThisWindowListener());
		setBounds(100, 100, 1054, 502);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 70));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		label = new JLabel("검색창");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label.setBounds(12, 16, 84, 28);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setBounds(84, 16, 229, 28);
		panel_1.add(textField);
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "예매순", "평점순"}));
		comboBox.setBounds(466, 16, 110, 30);
		panel_1.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ComboBox_1ActionListener());
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"전체"}));
		comboBox_1.setBounds(590, 17, 110, 28);
		panel_1.add(comboBox_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 1014, 327);
		panel_1.add(scrollPane);
		
		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		button = new JButton("검색");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(325, 16, 110, 28);
		panel_1.add(button);

		if(!isAdmin) {
			유저패널 pp = new 유저패널();
			panel.add(pp);
		}
		else {
			panel.setPreferredSize(new Dimension(0,0));
			setBounds(100, 100, 1054, 502-70);
		}
		addcate();
		load();
		
	}
	private void addcate() {
		try (var rs = res("select * from genre")) {
			while(rs.next()) {
				comboBox_1.addItem(rs.getString("g_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	String where = "", like = "", order = "";
	
	private void load() {
		panel_2.removeAll();
		try (var rs = res("with rank1 as (select m_no, rank() over(order by count(*) desc,m_no) rank1 from reservation right join movie using(m_no) group by m_no),\r\n"
				+ "rank2 as (select m_no, rank() over(order by avg(re_star) desc, m_no) rank2 from review right join movie using(m_no) group by m_no),\r\n"
				+ "per as (select m_no,  round(count(*)/(select count(*) from reservation)*100,1) per from reservation group by m_no)\r\n"
				+ "select * from movie left join rank1 using(m_no) left join rank2 using(m_no) join per using(m_no) join genre using(g_no) where true "+like+" "+where+" "+order)) {
			int w = (scrollPane.getWidth()-60)/4;
			int h = 250, i=0;
			while(rs.next()) {
				C_패널 pp = new C_패널(getIcon("movies/"+rs.getInt("m_no")+".jpg", w-70,h-30), rs.getInt("l_no"), rs.getString("m_name"), rs.getDouble("per"), rs.getString("m_startday"));
				pp.setSize(w, h);
				pp.setLocation((w+10)*(i%4), (h+10)*(i/4));
				int mno = rs.getInt("m_no");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(isAdmin) {
							showPage(new L_영화수정(mno), "L_영화수정");
						}
						else {
							showPage(new D_영화정보(mno), "D_영화정보");
						}
					}
				});
				if(comboBox.getSelectedIndex()==1&&rs.getInt("rank1")<=10) {
					pp.label.setText("No. "+rs.getInt("rank1"));
				}
				else if(comboBox.getSelectedIndex()==2 && rs.getInt("rank2")<=5) {
					pp.label.setText("No. "+rs.getInt("rank2"));
				}
				panel_2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, (h+10) * ((i+3)/4)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_2.revalidate();
		panel_2.repaint();
		
	}

	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			isAdmin = false;
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var txt = textField.getText();
			like = "and m_name like '%"+txt+"%'";
			load();
			if(panel.getComponentCount()==0) {
				msgErr("검색결과가 없습니다.");
				like = "";
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				load();
			}
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				order ="";
			}
			else if(comboBox.getSelectedIndex()==1) {
				order = "order by rank1";
			}
			else  {
				System.out.println(1);
				order = "order by rank2";
			}
			load();
		}
	}
	private class ComboBox_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_1.getSelectedIndex()==0) {
				where = "";
			}
			else {
				where = "and g_name = '"+comboBox_1.getSelectedItem()+"'";
			}
			load();
		}
	}
}
