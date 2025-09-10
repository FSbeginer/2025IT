import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovieSearch extends BF {
	public JLabel label_1;
	public JTextField textField;
	public JButton button;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JScrollPane scrollPane;
	public JPanel panel;
	public JPanel panel_1;
	private String order = "order by m_no";
	private String where = "";

	public MovieSearch() {
		setTitle("영화 검색");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1052, 487);
		
		label_1 = new JLabel("검색창");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_1.setBounds(12, 71, 51, 25);
		getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(63, 75, 194, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("검색");
		button.setBounds(269, 74, 97, 23);
		getContentPane().add(button);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "예매순", "평점순"}));
		comboBox.setBounds(378, 74, 103, 25);
		getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ComboBox_1ActionListener());
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"전체"}));
		comboBox_1.setBounds(488, 74, 103, 25);
		getContentPane().add(comboBox_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 110, 1012, 328);
		getContentPane().add(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1036, 65);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		if(!isAdmin)
			panel.add(new UserPanel());
		
		try {
			combAdd();
			loadMovies();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void combAdd() throws SQLException {
		var rs = res("select * from genre");
		while(rs.next()) {
			comboBox_1.addItem(rs.getString("g_name"));
		}
	}

	private void loadMovies() throws SQLException {
		panel_1.removeAll();
		var rs = res("with rank1 as(select m_no, round(count(*)/(select count(*) from reservation)*100,1) per, rank() over(order by round(count(*)/(select count(*) from reservation)*100,1) desc,m_no) rank1 from reservation join movie using(m_no) group by m_no) ,\r\n"
				+ "rank2 as(select m_no, avg(re_star) star,  rank() over(order by avg(re_star) desc,m_no) rank2 from review right join movie using(m_no) group by m_no)\r\n"
				+ "select * from movie m join rank1 using(m_no) join rank2 using(m_no) where true "+where+" "+order);
		int w = (scrollPane.getWidth()-30)/4;
		int h = scrollPane.getHeight()-30;
		int i = 0;
		int idx =comboBox.getSelectedIndex();
		while(rs.next()) {
			SearchPanel pp = new SearchPanel(getIcon("movies/"+rs.getInt("m_no")+".jpg", w-80, h-70), getIcon("limits/"+rs.getInt("l_no")+".png",40,40), rs.getString("m_name"), rs.getDouble("per"), rs.getString("m_startday"));
			pp.setSize(w,h);
			pp.setLocation((w+10)*(i%4), (h+10)*(i/4));
			int mno = rs.getInt("m_no");
			pp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(isAdmin)
						showPage(new MovieModify(mno),"MovieModify");
					else
						showPage(new MovieInfoForm(mno), "MovieInfoForm");
				}
			});
			if(idx == 1 && rs.getInt("rank1")<=10) {
				pp.lblNo.setText("No. "+rs.getInt("rank1"));
			}
			else if(idx==2 && rs.getInt("rank2")<=5) {
				pp.lblNo.setText("No. "+rs.getInt("rank2"));
			}
			panel_1.add(pp);
			i++;
		}
		panel_1.setPreferredSize(new Dimension(0, (h+10)*((i+3)/4)));
		panel_1.revalidate();
		panel_1.repaint();
	}
	private class ComboBox_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox_1.getSelectedIndex()==0) {
				where = "";
			}
			else {
				where = " and g_no = "+comboBox_1.getSelectedIndex();
			}
			try {
				loadMovies();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				order = "order by m_no";
			}
			else if(comboBox.getSelectedIndex()==1) {
				order = "order by rank1";
			}
			else {
				order = "order by rank2";
			}
			try {
				loadMovies();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
