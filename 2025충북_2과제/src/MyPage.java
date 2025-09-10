import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class MyPage extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JScrollPane scrollPane;
	public JScrollPane scrollPane_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel lblSize;
	public JLabel lblSize2;

	public MyPage() {
		setTitle("내 정보");
		setBounds(100, 100, 786, 517);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 150));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 120, 130);
		panel.add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(144, 10, 165, 28);
		panel.add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setBounds(144, 48, 165, 28);
		panel.add(label_2);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 5, 0));
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		lblSize = new JLabel("New label");
		lblSize.setVisible(false);
		lblSize.setBounds(0, 0, 174, 140);
		panel_2.add(lblSize);
		
		scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		panel_3 = new JPanel();
		scrollPane_1.setViewportView(panel_3);
		panel_3.setLayout(null);
		
		lblSize2 = new JLabel("New label");
		lblSize2.setVisible(false);
		lblSize2.setBounds(0, 0, 169, 211);
		panel_3.add(lblSize2);
		
		label.setIcon(getIcon("user/"+uno+".jpg", label.getWidth(), label.getHeight()));
		try (var rs = res("select * from user where u_no = "+uno)) {
			rs.next();
			label_1.setText(rs.getString("u_id"));
			label_2.setText(rs.getString("u_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		load();
	}

	private void load() {
		try (var rs = res("select * from fb join food using(f_no) where u_no = "+uno)) {
			int w = lblSize.getWidth(), h = lblSize.getHeight();
			int i = 0;
			while(rs.next()) {
				마이페이지패널 pp = new 마이페이지패널();
				pp.label.setIcon(getIcon("foods/"+rs.getInt("f_no")+".jpg", w-80,h-20));
				pp.label_1.setText(rs.getString("f_name"));
				pp.setSize(lblSize.getSize());
				pp.setLocation(w*(i%2),h*(i/2));
				panel_2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, h*(i/2)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from reservation r join movie m using(m_no) where u_no ="+uno)) {
			int w = lblSize2.getWidth(), h = lblSize2.getHeight(), i = 0;
			while(rs.next()) {
				마이페이지패널 pp = new 마이페이지패널();
				pp.label.setIcon(getIcon("movies/"+rs.getInt("m_no")+".jpg",w-80,h-20));
				pp.label_1.setText(rs.getString("m_name"));
				int mno = rs.getInt("m_no");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new MovieInfoForm(mno), "MovieInfoForm");
					}
				});
				pp.setSize(lblSize2.getSize());
				pp.setLocation(w*(i%2), h*(i/2));
				panel_3.add(pp);
				i++;
			}
			panel_3.setPreferredSize(new Dimension(0,h*(i/2)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
