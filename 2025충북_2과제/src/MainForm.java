import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class MainForm extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	private BackgroundImageLabel[] ads;
	public JButton button_2;
	public JButton button_3;
	public JPanel psize;
	List<MoviePanelRank> pps = new ArrayList<MoviePanelRank>();
	List<MoviePanelStar> pps2 = new ArrayList<MoviePanelStar>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainForm() {
		setTitle("\uBA54\uC778");
		setBounds(100, 100, 753, 671);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 84, 713, 261);
		getContentPane().add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(12, 355, 351, 254);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		button_2 = new JButton("\uC601\uD654 \uC804\uCCB4\uBCF4\uAE30");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBounds(12, 10, 127, 23);
		panel_1.add(button_2);
		
		button_3 = new JButton("\uBA39\uAC70\uB9AC \uD0A4\uC624\uC2A4\uD06C");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setBounds(147, 10, 127, 23);
		panel_1.add(button_3);
		
		psize = new JPanel();
		psize.setVisible(false);
		psize.setBounds(12, 43, 143, 201);
		panel_1.add(psize);

		panel_2 = new JPanel();
		panel_2.setBounds(377, 355, 351, 254);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		psize2 = new JPanel();
		psize2.setVisible(false);
		psize2.setBounds(12, 10, 163, 234);
		panel_2.add(psize2);
		
		panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 737, 78);
		getContentPane().add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		panel_3.add(new UserPanel());
		
		try {
			setAd();
			addAreaA();
			addAreaB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addAreaB() throws SQLException {
		panel_2.removeAll();
		pps2.clear();
		
		var rs = res("select m_no, m_name, avg(re_star) star from review re join movie m using(m_no) group by m_no order by star desc, m_no limit 5;");
		int i = 0;
		while(rs.next()) {
			MoviePanelStar pp = new MoviePanelStar(getIcon("movies/"+rs.getInt(1)+".jpg", psize2.getWidth(), psize2.getHeight()-80), rs.getString(2), rs.getDouble(3));
			pp.setSize(psize2.getSize());
			pp.setLocation(psize2.getX()+(10+pp.getWidth())*i, psize2.getY());
			int mno = rs.getInt(1);
			pp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showPage(new MovieInfoForm(mno), "MovieInfoForm");
				}
			});
			pp.addMouseListener(new Panel_2MouseListener());
			pp.addMouseMotionListener(new Panel_2MouseMotionListener());
			panel_2.add(pp);
			pps2.add(pp);
			i++;
		}
		panel_2.revalidate();
		panel_2.repaint();
	}

	@Override
	public void updateForm() {
		try {
			addAreaA();
			addAreaB();
		} catch (SQLException e) {
		}
	}
	
	private void addAreaA() throws SQLException {
		for (var pp : panel_1.getComponents()) {
			if(pp instanceof MoviePanelRank)
				panel_1.remove(pp);
		}
		pps.clear();
		
		var rs = res("select * from reservation r join movie m using(m_no) group by (m_no) order by count(*) desc, m_no limit 10;");
		int i = 0;
		while(rs.next()) {
			MoviePanelRank pp = new MoviePanelRank(getIcon("movies/"+rs.getInt("m_no")+".jpg", psize.getWidth(), psize.getHeight()-40), rs.getString("m_name"), i+1);
			pp.setSize(psize.getSize());
			pp.setLocation(psize.getX()+(pp.getWidth()+10)*i, psize.getY());
			int mno = rs.getInt("m_no");
			pp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showPage(new MovieInfoForm(mno), "MovieInfoForm");
				}
			});
			pp.addMouseListener(new Panel_1MouseListener());
			pp.addMouseMotionListener(new Panel_1MouseMotionListener());
			panel_1.add(pp);
			pps.add(pp);
			i++;
		}
		panel_1.revalidate();
		panel_1.repaint();
	}

	private void setAd() throws SQLException {
		int[] adnos = { 6, 2, 32, 9, 18 };
		ads = new BackgroundImageLabel[5];
		for (int i = 0; i < 5; i++) {
			var rs = res("select m_name, m_dir, m_no from movie where m_no = " + adnos[i]);
			rs.next();
			ads[i] = new BackgroundImageLabel(getIcon("advertising/" + (i + 1) + ".jpg", panel.getWidth(), panel.getHeight()),
					"<html><br><font size = 6>" + rs.getString(1) + "<br></font><font size = 5>" + rs.getString(2), JLabel.LEFT);
			ads[i].jl.setForeground(Color.white);
			ads[i].setSize(panel.getSize());
			ads[i].setLocation(ads[i].getWidth() * i, 0);
			panel.add(ads[i]);
			int mno = rs.getInt(3);
			ads[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showPage(new MovieInfoForm(mno), "MovieInfoForm");
				}
			});
		}
		threadingAds();
	}

	private void threadingAds() {
		new Thread(new Runnable() {
			private boolean stop;

			@Override
			public void run() {
				stop = true;
				while(true) {
					try {
						if(stop) {
							Thread.sleep(1000);
							stop = false;
						}
						else {
							Thread.sleep(1);
						}
					} catch (InterruptedException e) {
						break;
					}
					for (JLabel jLabel : ads) {
						jLabel.setLocation(jLabel.getX()-1, 0);
						if(jLabel.getX()==-jLabel.getWidth()) {
							stop =true;
							jLabel.setLocation(jLabel.getWidth()*4, 0);
						}
					}
				}
			}
		}).start();
	}
	
	int x1;
	public JPanel psize2;

	private class Panel_1MouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - x1;
			if(pps.get(0).getX()+dx>0||pps.get(pps.size()-1).getX()+dx<panel_1.getWidth()-psize.getWidth()) return;
			for (var pp : pps) {
				pp.setLocation(pp.getX()+dx, pp.getY());
			}
		}
	}
	private class Panel_1MouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			x1 = e.getX();
		}
	}
	
	int x2;
	public JPanel panel_3;
	private class Panel_2MouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			x2 = e.getX();
		}
	}
	private class Panel_2MouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - x2;
			if(pps2.get(0).getX()+dx>0||pps2.get(pps2.size()-1).getX()+dx<panel_2.getWidth()-psize2.getWidth()) return;
			for (var pp : pps2) {
				pp.setLocation(pp.getX()+dx, pp.getY());
			}
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(uno!=0) {
				showPage(new Kiosk(), "Kiosk");
			}
			else {
				msgErr("로그인을 해주세요.");
				showPage(new LoginForm(),"LoginForm");
			}
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("이거 진짜 어디로 가야할지 모르겠어서 스킵하겟습니다.");
		}
	}
}
