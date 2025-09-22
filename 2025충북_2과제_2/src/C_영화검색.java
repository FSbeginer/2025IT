import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class C_영화검색 extends BF {
	public JPanel panel;

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
		setBounds(100, 100, 982, 551);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		if(!isAdmin)
			panel.add(new 유저패널());
		else {
			setSize(982, 500);
		}
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		label = new JLabel("검색창");
		label.setFont(new Font("굴림", Font.BOLD, 14));
		label.setBounds(12, 10, 77, 29);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(62, 10, 205, 29);
		panel_1.add(textField);
		
		button = new JButton("검색");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(279, 13, 97, 23);
		panel_1.add(button);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "예매순", "평점순"}));
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setBounds(388, 13, 104, 26);
		panel_1.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"전체"}));
		comboBox_1.addActionListener(new ComboBox_1ActionListener());
		comboBox_1.setBounds(499, 13, 104, 26);
		panel_1.add(comboBox_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 46, 942, 374);
		panel_1.add(scrollPane);
		
		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		addcombo();
		load();
	}
	private void addcombo() {
		try (var rs = res("select * from genre")) {
			while(rs.next()) {
				comboBox_1.addItem(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	String order = "m_no", where = "", like = "";
	public JPanel panel_1;
	public JLabel label;
	public JTextField textField;
	public JButton button;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	private void load() {
		panel_2.removeAll();
		try {
			var rs = res("with rank1 as (select m_no, rank() over(order by count(*) desc, m_no) rank1, round(count(*)/tot * 100,1) per from movie m join reservation r using(m_no), (select count(*) tot from reservation) sub group by m_no),\r\n"
					+ "rank2 as (select m_no, rank() over(order by avg(re_star) desc, m_no) rank2 from movie m left join review re using(m_no) group by m_no)\r\n"
					+ "select *  from movie join rank1 using(m_no) join rank2 using(m_no) where true "+where+" "+like+" order by "+order);
			int w = (scrollPane.getWidth()-20-30)/4;
			int h = 240, i = 0;
			while(rs.next()) {
				C_패널 pp =new C_패널(rs.getInt("l_no"), getIcon("movies/"+rs.getInt("m_no")+".jpg",w-60,h-30), rs.getString("m_name"), rs.getDouble("per"), rs.getString("m_startday"));
				pp.setSize(w,h);
				pp.setLocation((w+10)*(i%4), (h +10)*(i/4));
				int mno = rs.getInt("m_no");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(isAdmin) {
							showPage(new L_영화수정(mno), "L_영화수정");
						}else {
							showPage(new D_영화정보(mno), "D_영화정보");
						}
					}
				});
				if(comboBox.getSelectedIndex()==1&&rs.getInt("rank1")<=10) {
					pp.lblNO.setText("No. "+rs.getInt("rank1"));
				}
				else if(comboBox.getSelectedIndex()==2&&rs.getInt("rank2")<=5) {
					pp.lblNO.setText("No. "+rs.getInt("rank2"));
				}
				panel_2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, (h+10)*((i+3)/4)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_2.revalidate();
		panel_2.repaint();
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			like = "and m_name like '%"+textField.getText()+"%'";
			load();
			if(panel_2.getComponents().length==0) {
				msgErr("검색 결과가 없습니다.");
				like = "";
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
			}
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				order = "m_no";
			}
			else if(comboBox.getSelectedIndex()==1) {
				order = "rank1";
			}
			else
				order = "rank2";
			load();
		}
	}
	private class ComboBox_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_1.getSelectedIndex()==0)
				where = "";
			else {
				where = "and g_no ="+comboBox_1.getSelectedIndex();
			}
			load();
		}
	}
}
