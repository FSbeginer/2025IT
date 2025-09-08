import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class A_메인 extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JPanel panel_4;
	public JButton button;
	public JButton button_1;
	public JPanel psize1;
	public JPanel psize2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A_메인 frame = new A_메인();
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
	public A_메인() {
		setTitle("메인");
		setBounds(100, 100, 836, 656);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 90));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBounds(12, 10, 792, 259);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBounds(12, 279, 379, 238);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		button = new JButton("영화 전체보기");
		button.setBounds(12, 10, 125, 23);
		panel_3.add(button);

		button_1 = new JButton("먹거리 키오스크");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(149, 10, 121, 23);
		panel_3.add(button_1);

		psize1 = new JPanel();
		psize1.setVisible(false);
		psize1.setBounds(12, 43, 142, 185);
		panel_3.add(psize1);

		panel_4 = new JPanel();
		panel_4.setBounds(425, 279, 379, 238);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		psize2 = new JPanel();
		psize2.setVisible(false);
		psize2.setBounds(12, 10, 159, 218);
		panel_4.add(psize2);

		유저패널 유저패널 = new 유저패널();
		유저패널.setPreferredSize(new Dimension(360, 75));
		panel.add(유저패널);

		load();
		updateForm();
	}
	@Override
	public void updateForm() {
		loadA();
		loadB();
	}

	private void loadB() {
		pps2.clear();
		panel_4.removeAll();
		try (var rs = res("select *,avg(review.re_star) star from movie left join review using(m_no) group by m_no order by star desc, m_no limit 5;")) {
			int w = psize2.getWidth();
			int h = psize2.getHeight();
			int i = 0;
			while(rs.next()) {
				A_별점패널 pp = new A_별점패널(getIcon("movies/"+rs.getInt("m_no")+".jpg", w,h-30), rs.getString("m_name"), rs.getDouble("star"));
				pp.setSize(w, h);
				pp.setLocation(10+(w+10)*i, psize2.getY());
				int mno = rs.getInt("m_no");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_영화정보(mno),"D_영화정보");
					}
					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX()-cx;
						if(pps2.size()==0||pps2.get(0).getX()+dx>10||pps2.get(pps2.size()-1).getX()+dx<panel_4.getWidth()-w-10) return;
						for (var pp : pps2) {
							pp.setLocation(pp.getX()+dx, pp.getY());
						}
					}
				});
				pps2.add(pp);
				panel_4.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_4.revalidate();
		panel_4.repaint();
	}

	List<JPanel> pps = new ArrayList<JPanel>();
	List<JPanel> pps2 = new ArrayList<JPanel>();
	
	int cx = 0;
	
	private void loadA() {
		pps.clear();
		for (var comp : panel_3.getComponents()) {
			if(comp instanceof JButton) continue;
			panel_3.remove(comp);
		}
		try (var rs = res("select *,count(*) cnt from movie left join reservation using(m_no) group by m_no order by cnt desc, m_no limit 5;")) {
			int w = psize1.getWidth();
			int h = psize1.getHeight();
			int i = 0;
			while(rs.next()) {
				A_랭킹패널 pp = new A_랭킹패널(getIcon("movies/"+rs.getInt("m_no")+".jpg", w,h-30), rs.getString("m_name"), i+1);
				pp.setSize(w, h);
				pp.setLocation(10+(w+10)*i, psize1.getY());
				int mno = rs.getInt("m_no");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_영화정보(mno),"D_영화정보");
					}
					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX()-cx;
						if(pps.size()==0||pps.get(0).getX()+dx>10||pps.get(pps.size()-1).getX()+dx<panel_3.getWidth()-w-10) return;
						for (var pp : pps) {
							pp.setLocation(pp.getX()+dx, pp.getY());
						}
					}
				});
				pps.add(pp);
				panel_3.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_3.revalidate();
		panel_3.repaint();
	}

	JLabel[] jls = new JLabel[5];

	private void load() {
		int[] mno = { 6, 2, 32, 9, 18 };
		for (int i = 0; i < mno.length; i++) {
			jls[i] = new JLabel(getIcon("advertising/" + (i+1) + ".jpg",panel_2.getWidth(),panel_2.getHeight()));
			var jl = new JLabel();
			jl.setForeground(Color.white);
			jls[i].setLayout(new BorderLayout());
			jls[i].add(jl);
			try {
				var rs = res("select * from movie where m_no = " + mno[i]);
				rs.next();
				jl.setText("<html><br><font size = 6>" + rs.getString("m_name") + "<br></font><font size = 5>"
						+ rs.getString("m_dir"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jls[i].setSize(panel_2.getSize());
			jls[i].setLocation(panel_2.getWidth() * i, 0);
			int m = mno[i];
			jls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showPage(new D_영화정보(m), "D_영화정보");
				}
			});
			panel_2.add(jls[i]);
		}
		new Thread(new Runnable() {
			boolean stop = true;
			
			@Override
			public void run() {
				while (true) {
					try {
						if (stop) {
							Thread.sleep(2000);
							stop = false;
						} else {
							Thread.sleep(1);
						}
						for (var jl : jls) {
							jl.setLocation(jl.getX()-1, 0);
							if(jl.getX()==-jl.getWidth()) {
								stop = true;
								jl.setLocation(jl.getWidth()*4, 0);
							}
						}
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(uno==0) {
				msgErr("로그인을 해주세요.");
				var l = new B_로그인();
				l.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if(BF.uno!=0) {
							button.setText("내 정보");
						}
					}
				});
				showPage(l, "B_로그인");
				return;
			}
			showPage(new I_키오스크(),"I_키오스크");
		}
	}
}
