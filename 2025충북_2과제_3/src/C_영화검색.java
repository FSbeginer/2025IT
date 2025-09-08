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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class C_영화검색 extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JTextField textField;
	public JButton button;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JScrollPane scrollPane;
	public JPanel panel_2;

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
		setBounds(100, 100, 979, 538);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 80));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		label = new JLabel("검색창");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label.setBounds(12, 10, 70, 25);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setBounds(75, 10, 214, 25);
		panel_1.add(textField);
		textField.setColumns(10);
		
		button = new JButton("검색");
		button.setBounds(301, 10, 70, 23);
		panel_1.add(button);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "예매순", "평점순"}));
		comboBox.setBounds(383, 10, 107, 25);
		panel_1.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ComboBox_1ActionListener());
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"전체"}));
		comboBox_1.setBounds(502, 10, 107, 25);
		panel_1.add(comboBox_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 47, 939, 362);
		panel_1.add(scrollPane);
		
		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		if(!isAdmin) {
			panel.add(new 유저패널());
		}
		else {
			setBounds(100, 100, 979, 538-80);
			panel.setPreferredSize(new Dimension(0,0));
		}
		
		addGenre();
		load();
	}

	@Override
	public void updateForm() {
		load();
	}
	private void addGenre() {
		try (var rs = res("select * from genre")) {
			while(rs.next())
				comboBox_1.addItem(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	String order = "", where = "", like = "";
	private void load() {
		panel_2.removeAll();
		try (var rs = res("with rank1 as(select m_no, rank() over(order by count(*) desc, m_no) rank1,  round(count(*)/(select count(*) from reservation) *100.0,1) per from movie left join reservation using(m_no) group by m_no order by m_no),\r\n"
				+ "rank2 as (select m_no,rank() over(order by avg(review.re_star) desc,m_no) rank2 from movie left join review using(m_no) group by m_no order by m_no)\r\n"
				+ "select * from movie left join rank1 using(m_no) left join rank2 using(m_no) where m_name like '%"+like+"%' "+where+" "+order)) {
			int w = (scrollPane.getWidth()-60)/4;
			int h = 280,i=0;
			while(rs.next()) {
				C_패널 pp =new C_패널(rs.getInt("l_no"), getIcon("movies/"+rs.getInt("m_no")+".jpg",w,h-40), rs.getString("m_name"), rs.getDouble("per"), rs.getString("m_startday"));
				pp.setSize(w, h);
				pp.setLocation((w+20)*(i%4), h*(i/4));
				int mno = rs.getInt("m_no");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(isAdmin) {
							showPage(new L_영화수정(mno),"L_영화수정");
						}else {
							showPage(new D_영화정보(mno), "D_영화정보");
						}
					}
				});
				
				if(comboBox.getSelectedIndex()==1&&rs.getInt("rank1")<=10) {
					pp.label.setText("No."+rs.getInt("rank1"));
				}
				if(comboBox.getSelectedIndex()==2&&rs.getInt("rank2")<=5) {
					pp.label.setText("No."+rs.getInt("rank2"));
				}
				panel_2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, h*((i+3)/4)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_2.revalidate();
		panel_2.repaint();
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				order = "";
			}
			else if(comboBox.getSelectedIndex()==1) {
				order = "order by rank1 ";
			}
			else 
				order = "order by rank2 ";
			load();
		}
	}
	private class ComboBox_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_1.getSelectedIndex()==0) {
				where = "";
			}
			else if(comboBox_1.getSelectedIndex()==1) {
				where = "and g_no ="+comboBox_1.getSelectedIndex();
			}
		}
	}
}
