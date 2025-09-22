import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class A_메인 extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panelAd;
	public JPanel panel_3;
	public JPanel panel_4;
	public JButton button;
	public JButton button_1;
	public JPanel psize1;
	public JPanel psize2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A_메인 frame = new A_메인();
					frame.setName("A_메인");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public A_메인() {
		setTitle("메인");
		setBounds(100, 100, 660, 592);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 70));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		panelAd = new JPanel();
		panelAd.setBounds(12, 10, 620, 226);
		panel_1.add(panelAd);
		panelAd.setLayout(null);
		
		panel_3 = new JPanel();
		panel_3.setBounds(12, 246, 301, 227);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		button = new JButton("영화 전체보기");
		button.setFont(new Font("굴림", Font.PLAIN, 11));
		button.setBounds(12, 10, 116, 23);
		panel_3.add(button);
		
		button_1 = new JButton("먹거리 키오스크");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setFont(new Font("굴림", Font.PLAIN, 11));
		button_1.setBounds(140, 10, 116, 23);
		panel_3.add(button_1);
		
		psize1 = new JPanel();
		psize1.setVisible(false);
		psize1.setBounds(12, 43, 136, 174);
		panel_3.add(psize1);
		
		panel_4 = new JPanel();
		panel_4.setBounds(331, 246, 301, 227);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		psize2 = new JPanel();
		psize2.setVisible(false);
		psize2.setBounds(12, 10, 138, 207);
		panel_4.add(psize2);

		panel.add(up =new 유저패널());
		Ad();
		updateForm();
	}
	@Override
	public void updateForm() {
		AreaA();
		AreaB();
	}
	
	private void AreaB() {
		panel_4.removeAll();
		pps2.clear();
		try (var rs = res("select m_no, m_name, avg(re_star) star from movie m left join review re using(m_no) group by m_no order by star desc, m_no limit 5;")) {
			int i = 0;
			while(rs.next()) {
				A_별점패널 pp =new A_별점패널(getIcon("movies/"+rs.getInt(1)+".jpg",psize2.getWidth(),psize2.getHeight()-60), rs.getString(2), rs.getDouble(3));
				pp.setSize(psize2.getSize());
				pp.setLocation(psize2.getX()+(psize2.getWidth()+10)*i, psize2.getY());
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
						if(pps2.size()==0||pps2.get(0).getX()+dx>10||pps2.get(pps2.size()-1).getX()+dx<psize2.getWidth()-10) return;
						for (var pp : pps2) {
							pp.setLocation(pp.getX()+dx, pp.getY());
						}
					}
				});
				panel_4.add(pp);
				pps2.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_4.revalidate();
		panel_4.repaint();
	}

	List<JPanel> pps1 =new ArrayList<>();
	List<JPanel> pps2 =new ArrayList<>();
	private int cx;
	private void AreaA() {
		for (var comp : panel_3.getComponents()) {
			if(comp instanceof JPanel) {
				panel_3.remove(comp);
			}
		}
		pps1.clear();
		try (var rs = res("select * from movie m join reservation r using(m_no) group by m_no order by count(*) desc, m_no limit 10;")) {
			int w=0,h=0,i=0;
			while(rs.next()) {
				A_순위패널 pp = new A_순위패널(getIcon("movies/"+rs.getInt("m_no")+".jpg", psize1.getWidth(), psize1.getHeight()-15), rs.getString("m_name"), i+1);
				pp.setSize(psize1.getSize());
				pp.setLocation(psize1.getX()+(psize1.getWidth()+10)*i, psize1.getY());
				int mno = rs.getInt("m_no");
				pp.label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_영화정보(mno),"D_영화정보");
					}
					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
				});
				pp.label.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX()-cx;
						if(pps1.size()==0||pps1.get(0).getX()+dx>10||pps1.get(pps1.size()-1).getX()+dx < panel_3.getWidth()-psize1.getWidth()-10) return;
						for (var pp : pps1) {
							pp.setLocation(pp.getX()+dx, psize1.getY());
						}
					}
				});
				panel_3.add(pp);
				pps1.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		panel_3.revalidate();
		panel_3.repaint();
		
	}

	유저패널 up;
	int[] adnos = {6,2,32,9,18};
	JLabel[] jls = new JLabel[5];
	
	private void Ad() {
		for (int i = 0; i < 5; i++) {
			jls[i] = new JLabel(getIcon("advertising/"+(i+1)+".jpg",panelAd.getWidth(),panelAd.getHeight()));
			jls[i].setSize(panelAd.getSize());
			jls[i].setLocation(panelAd.getWidth()*i, 0);
			JLabel txt = new JLabel();
			jls[i].setLayout(new BorderLayout());
			try {
				var rs =res("select m_no,m_name, m_dir from movie where m_no = "+adnos[i]);
				rs.next();
				txt.setText("<html><br><font color = white><font size = 6>"+rs.getString(2)+"</font><br><font size = 5>"+rs.getString(3));
				jls[i].add(txt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			int mno = adnos[i];
			jls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showPage(new D_영화정보(mno),"D_영화정보");
				}
			});
			panelAd.add(jls[i]);
		}
		new Thread(new Runnable() {
			boolean stop = true;
			@Override
			public void run() {
				try {
					while (true) {
						if(stop) {
							Thread.sleep(2000);
							stop = false;
						}
						else {
							Thread.sleep(1);
						}
						for (var jl : jls) {
							jl.setLocation(jl.getX()-1, 0);
							if(jl.getX()==-jl.getWidth()) {
								stop = true;
								jl.setLocation(jl.getWidth()*4, 0);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new I_키오스크(),"I_키오스크");
		}
	}
}
