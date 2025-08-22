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
	private String cmb1 = "";
	private String cmb2 = "";
	private String limits = "";

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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체"}));
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
		var rs = res("select *, round(count(*)/tot*100,1) cnt from movie m left join review r using(m_no) join movie_limit using(l_no), (select count(*) tot from review) sub where true "+cmb2+" group by m_no "+cmb1+limits);
		int w = (scrollPane.getWidth()-30)/4;
		int h = scrollPane.getHeight()-30;
		int i = 0;
		while(rs.next()) {
			SearchPanel pp = new SearchPanel(getIcon("movies/"+rs.getInt("m_no")+".jpg", w-80, h-50), getIcon("limits/"+rs.getInt("l_no")+".png",40,40), rs.getString("m_name"), rs.getDouble("cnt"), rs.getString("re_date"));
			pp.setSize(w,h);
			pp.setLocation((w+10)*(i%4), (h+10)*(i/4));
			int mno = rs.getInt("m_no");
			pp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showPage(new MovieInfoForm(mno), "MovieInfoForm");
				}
			});
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
				cmb2 = "";
			}
			else {
				cmb2 = " and gno = "+comboBox_1.getSelectedIndex();
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
//			cmb1 = 
		}
	}
}
