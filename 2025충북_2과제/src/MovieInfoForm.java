import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovieInfoForm extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	public JPanel panel_3;
	public JScrollPane scrollPane_1;
	public JPanel panel_4;
	public JTextArea textArea;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JButton button;
	int mno;
	
	public MovieInfoForm(int mno) {
		this.mno = mno;
		setTitle("영화 정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 803, 519);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 65));
		getContentPane().add(panel, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 950));
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 10));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		label = new JLabel("");
		label.setBounds(12, 10, 184, 290);
		panel_3.add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(208, 10, 538, 63);
		panel_3.add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setBounds(208, 78, 538, 31);
		panel_3.add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setBounds(208, 119, 538, 31);
		panel_3.add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setBounds(208, 160, 538, 31);
		panel_3.add(label_4);
		
		button = new JButton("예매하기");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(208, 219, 97, 23);
		panel_3.add(button);
		
		scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane_1.setViewportView(textArea);
		
		panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel.add(new UserPanel());
		load();
	}

	private void load() {
		try {
			var rs = res("select * from movie join genre using(g_no) where m_no = "+mno);
			rs.next();
			label.setIcon(getIcon("movies/"+rs.getInt("m_no")+".jpg",label.getWidth(),label.getHeight()));
			label_1.setText("제목: "+rs.getString("m_name"));
			label_2.setText("감독:"+rs.getString("m_dir"));
			label_3.setText("장르:"+rs.getString("g_name"));
			label_4.setText("개봉일:"+rs.getString("m_startday"));
			textArea.setText(rs.getString("m_plot"));
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.add(new InfoPanel(mno));
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					scrollPane_1.getVerticalScrollBar().setValue(0);
				}
			}); 
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new Reservation(mno), "Reservation");
		}
	}
}
