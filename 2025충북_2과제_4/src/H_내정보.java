import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;

public class H_내정보 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	public JScrollPane scrollPane;
	public JScrollPane scrollPane_1;
	public JPanel panel_1;
	public JPanel panel_2;

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
		setBounds(100, 100, 800, 473);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 120, 109);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(144, 10, 179, 28);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setBounds(144, 48, 179, 28);
		getContentPane().add(label_2);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 132, 760, 289);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 5, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1);
		
		panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		loadA();
	}

	private void loadA() {
		try (var rs = res("select * from user where  u_no = "+uno)) {
			rs.next();
			label_1.setText(rs.getString("u_id"));
			label_2.setText(rs.getString("u_name"));
			label.setIcon(getIcon("user/"+uno+".jpg",label.getWidth(),label.getHeight()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from fb left join food using(f_no) where u_no = "+uno)) {
			int w = (((panel.getWidth()-10)/2)-20)/2;
			int h = panel.getHeight()*2/5;
			int  i = 0;
			while(rs.next()) {
				A_패널 pp = new A_패널(getIcon("foods/"+rs.getInt("f_no")+".jpg",70,70), rs.getString("f_name"));
				pp.label_1.setHorizontalAlignment(0);
				pp.setSize(w, h);
				pp.setLocation(w*(i%2), h*(i/2));
				panel_1.add(pp);
				i++;
			}
			i++;
			panel_1.setPreferredSize(new Dimension(w*2,h*(i/2)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from reservation join movie using(m_no) where u_no = "+uno)) {
			int w = (((panel.getWidth()-10)/2)-20)/2;
			int h = panel.getHeight()/2+10;
			int i = 0;
			while(rs.next()) {
				A_패널 pp = new A_패널(getIcon("movies/"+rs.getInt("m_no")+".jpg",70,100), rs.getString("m_name"));
				pp.label_1.setHorizontalAlignment(0);
				pp.setSize(w, h);
				pp.setLocation(w*(i%2), h*(i/2));
				int mno = rs.getInt("m_no");
						
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_영화정보(mno), "D_영화정보");
					}
				});
				panel_2.add(pp);
				i++;
			}
			i++;
			panel_2.setPreferredSize(new Dimension(w*2,h*(i/2)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_1.revalidate();
		panel_1.repaint();
		panel_2.revalidate();
		panel_2.repaint();
	}

}
