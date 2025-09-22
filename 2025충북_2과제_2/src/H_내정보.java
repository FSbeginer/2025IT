import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class H_내정보 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JScrollPane scrollPane;
	public JScrollPane scrollPane_1;
	public JPanel panel;
	public JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					H_내정보 frame = new H_내정보();
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
	public H_내정보() {
		setTitle("내 정보");
		setBounds(100, 100, 772, 464);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 119, 113);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(143, 10, 176, 35);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setBounds(143, 55, 176, 35);
		getContentPane().add(label_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 133, 360, 282);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(384, 133, 360, 282);
		getContentPane().add(scrollPane_1);
		  
		panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		try (var rs = res("select * from user where u_no= "+uno)) {
			rs.next();
			label_1.setText(rs.getString("u_id"));
			label_2.setText(rs.getString("u_name"));
			label.setIcon(getIcon("user/"+uno+".jpg",label.getWidth(),label.getHeight()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		load();
	}

	private void load() {
		try (var rs = res("select * from food join fb using(f_no) where u_no = "+uno)) {
			int w = (scrollPane.getWidth()-20)/2;
			int h = scrollPane.getHeight() * 2 / 5, i = 0;
			while(rs.next()) {
				H_패널 pp = new H_패널(getIcon("foods/"+rs.getInt("f_no")+".jpg",100,100), rs.getString("f_name"));
				pp.setSize(w, h);
				pp.setLocation(w*(i%2), h*(i/2));
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, h*((i+1)/2)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from reservation join movie using(m_no) where u_no ="+uno)) {
			int w = (scrollPane.getWidth()-20)/2;
			int h = scrollPane.getHeight() / 2,i = 0;
			while(rs.next()) {
				H_패널 pp = new H_패널(getIcon("movies/"+rs.getInt("m_no")+".jpg",100,100), rs.getString("m_name"));
				pp.setSize(w, h);
				pp.setLocation(w*(i%2), h*(i/2));
				panel_1.add(pp);
				int mno = rs.getInt("m_no");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_영화정보(mno), "D_영화정보");
					}
				});
				i++;
			}
			panel_1.setPreferredSize(new Dimension(0, h*((i+1)/2)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
