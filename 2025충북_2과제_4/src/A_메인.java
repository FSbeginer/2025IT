import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class A_메인 extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
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
					frame.setLocationRelativeTo(null);
					frame.setName("A_메인");
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
		setBounds(100, 100, 762, 628);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 746, 60);
		getContentPane().add(panel);

		panel_1 = new JPanel();
		panel_1.setBounds(10, 70, 724, 247);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(12, 327, 348, 254);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		button = new JButton("영화 전체보기");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 10, 109, 23);
		panel_2.add(button);

		button_1 = new JButton("먹거리 키오스크");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(133, 10, 121, 23);
		panel_2.add(button_1);

		psize1 = new JPanel();
		psize1.setVisible(false);
		psize1.setBounds(12, 43, 137, 201);
		panel_2.add(psize1);

		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(386, 327, 348, 254);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		psize2 = new JPanel();
		psize2.setVisible(false);
		psize2.setBounds(12, 10, 159, 234);
		panel_3.add(psize2);
		panel.setLayout(new BorderLayout(0, 0));

		유저패널 pp = new 유저패널();
		panel.add(pp);

		loadAd();
		updateForm();
	}

	@Override
	public void updateForm() {
		setA();
		setB();
	}
	private void setB() {
		panel_3.removeAll();
		
		pps2.clear();
		try (var rs = res("select m_no, m_name, avg(re_star) rank1 from review right join movie using(m_no) group by m_no order by rank1 desc, m_no limit 5;")) {
			int w = psize2.getWidth();
			int h = psize2.getHeight();
			int bx = psize2.getX();
			int by = psize2.getY();
			int i = 0;
			while(rs.next()) {
				A_별점패널  pp = new A_별점패널(getIcon("movies/"+rs.getInt(1)+".jpg",w,h-30), rs.getString(2), rs.getDouble(3));	
				pp.setSize(w, h);
				pp.setLocation(bx+(w+10)*i, by);
				int mno = rs.getInt(1);
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_영화정보(mno), "D_영화정보");
					}
					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX() - cx;
						if(pps2.size()==0 || pps2.get(0).getX()+dx>bx || pps2.get(pps2.size()-1).getX() +dx< panel_3.getWidth()-bx-w) {
							return;
						}
						for (var pp : pps2) {
							pp.setLocation(pp.getX()+dx, pp.getY());
						}
					}
				});
				panel_3.add(pp);
				pps2.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_3.repaint();
		panel_3.revalidate();
	}

	int cx;
	List<JPanel> pps = new ArrayList<JPanel>();
	List<JPanel> pps2 = new ArrayList<JPanel>();
	private void setA() {
		for (var comp : panel_2.getComponents()) {
			if(comp instanceof JButton) continue;
			panel_2.remove(comp);
		}
		
		pps.clear();
		try (var rs = res("select m_no, m_name, rank() over(order by count(*) desc,m_no) rank1 from reservation right join movie using(m_no) group by m_no limit 10;")) {
			int w = psize1.getWidth();
			int h = psize1.getHeight();
			int bx = psize1.getX();
			int by = psize1.getY();
			int i = 0;
			while(rs.next()) {
				A_순위패널 pp = new A_순위패널(getIcon("movies/"+rs.getInt(1)+".jpg",w,h-30), rs.getString(2), rs.getInt(3));
				pp.setSize(w, h);
				pp.setLocation(bx+(w+10)*i, by);
				int mno = rs.getInt(1);
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_영화정보(mno), "D_영화정보");
					}
					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX() - cx;
						if(pps.size()==0 || pps.get(0).getX()+dx>bx || pps.get(pps.size()-1).getX() +dx< panel_2.getWidth()-bx-w) {
							return;
						}
						for (var pp : pps) {
							pp.setLocation(pp.getX()+dx, pp.getY());
						}
					}
				});
				panel_2.add(pp);
				pps.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_2.repaint();
		panel_2.revalidate();
	}

	JLabel[] jls = new JLabel[5];

	private void loadAd() {
		int[] mnos = { 6, 2, 32, 9, 18 };
		for (int i = 0; i < jls.length; i++) {
			jls[i] = new JLabel(getIcon("advertising/" + (i + 1) + ".jpg",panel_1.getWidth(),panel_1.getHeight()));
			int mno = mnos[i];
			try {
				var rs = res("select * from movie where m_no = " + mno);
				rs.next();
				JLabel jl = new JLabel("<html><br><font size = 6>" + rs.getString("m_name")
						+ "</font><br><font size = 5>" + rs.getString("m_dir"));
				jl.setForeground(Color.white);
				jls[i].setLayout(new BorderLayout());
				jls[i].add(jl);
				jls[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_영화정보(mno), "D_영화정보");
					}
				});
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jls[i].setSize(panel_1.getSize());
			jls[i].setLocation(jls[i].getWidth() * i, 0);
			panel_1.add(jls[i]);
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
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (var jl : jls) {
						jl.setLocation(jl.getX()-1, 0);
						if(jl.getX()==-jl.getWidth()) {
							jl.setLocation(jl.getWidth()*4, 0);
							stop = true;
						}
					}
				}
			}
		}).start();
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(uno==0) {
				msgErr("로그인을 해주세요.");
				showPage(new B_로그인(), "B_로그인");
			}else {
				showPage(new I_키오스크(), "I_키오스크");
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			msgErr("해당하는 영화가 없습니다.");
		}
	}
}
