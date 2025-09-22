import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

public class A_메인 extends BF {
	public JLabel label;
	public JTextField textField;
	public JLabel label_1;
	public JPanel panel;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JLabel label_6;
	public JLabel label_7;
	public JPanel panel_4;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public A_메인() {
		setTitle("\uC790\uACA9\uC99D \uBA54\uC778 \uD654\uBA74");
		setBounds(100, 100, 1016, 621);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setText("  Skills Qualification Association");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label.setBounds(76, 10, 219, 53);
		getContentPane().add(label);

		textField = new JTextField();
		textField.setBorder(new RoundBorder(blue));
		textField.setBounds(320, 25, 261, 27);
		getContentPane().add(textField);
		textField.setColumns(10);

		label_1 = new JLabel(getIcon("icon/search.png", 40, 40));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(590, 17, 50, 43);
		getContentPane().add(label_1);

		panel = new JPanel();
		panel.setBounds(42, 73, 918, 43);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));

		label_2 = new JLabel("자격증 목록");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		label_3 = new JLabel("시험 일정");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);

		label_4 = new JLabel("고객센터");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);

		label_5 = new JLabel("자격증발급");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);

		panel_1 = new JPanel();
		panel_1.setBounds(42, 139, 415, 284);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBounds(467, 139, 231, 284);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		button_2 = new JButton("추천순");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBounds(0, 0, 116, 32);
		panel_2.add(button_2);
		
		button_3 = new JButton("별점순");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setBounds(128, 0, 103, 32);
		panel_2.add(button_3);
		
		panel_6 = new JPanel();
		panel_6.setBounds(0, 42, 231, 242);
		panel_2.add(panel_6);
		panel_6.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBounds(735, 242, 253, 183);
		getContentPane().add(panel_3);
		panel_3.setLayout(new CardLayout(0, 0));
		
		label_8 = new JLabel("<html><font color = red>로그인이 필요합니다.</font><br>1.유효한 사용자 정보를 입력하세요.<br>2.인증 절차를 완료하세요.<br>3.로그인 후 이용 가능합니다.<br>4.오류가 지속되면 관리자에게 문의하세요.");
		label_8.setBorder(new RoundBorder(Color.black));
		label_8.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_3.add(label_8, "name_10020020315900");
		
		scrollPane = new JScrollPane();
		panel_3.add(scrollPane, "name_10169880620700");
		
		panel_5 = new JPanel();
		panel_5.setBorder(new RoundBorder(Color.black));
		scrollPane.setViewportView(panel_5);
		panel_5.setLayout(null);

		label_6 = new JLabel(getIcon("icon/check.png", 40, 40));
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label_6.setText("로그인이 필요합니다.");
		label_6.setBounds(735, 139, 253, 43);
		getContentPane().add(label_6);

		label_7 = new JLabel(getIcon("icon/medel.png", 40, 40));
		label_7.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setText("자격증을 선택해 주세요.");
		label_7.setBounds(42, 443, 283, 43);
		getContentPane().add(label_7);

		panel_4 = new JPanel();
		panel_4.setBorder(new RoundBorder(Color.black,2));
		panel_4.setBounds(0, 491, 1000, 174);
		getContentPane().add(panel_4);
		
		button = new JButton("로그인");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(735, 192, 117, 32);
		getContentPane().add(button);
		
		button_1 = new JButton("내정보");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setVisible(false);
		button_1.setBounds(864, 192, 117, 32);
		getContentPane().add(button_1);
		
		label_9 = new JLabel(getIcon("icon/logo.png", 50, 50));
		label_9.setBounds(12, 10, 57, 53);
		getContentPane().add(label_9);

		AreaA();
		AreaC();
		addCategory();
		updateForm();
	}
	
	JLabel[] jls = new JLabel[5];
	private Timer timer;
	
	private void AreaC() {
		int w = panel_6.getWidth();
		int h= panel_6.getHeight()/5;
		for (int i = 0; i < 5; i++) {
			jls[i] = new JLabel();
			jls[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			jls[i].setSize(w,h);
			jls[i].setLocation(0, h*i);
			int idx = i;
			jls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(jls[idx].getBorder()!=null) {
						int cno = Integer.parseInt(jls[idx].getName());
						if(uno!=0)
							showPage(new H_자격증상세내용(cno), "H_자격증상세내용");
						else {
							var l = new B_로그인();
							l.addWindowListener(new WindowAdapter() {
								public void windowClosed(java.awt.event.WindowEvent e) {
									if(uno!=0) {
										SwingUtilities.invokeLater(()->setVisible(false));
										showPage(new H_자격증상세내용(cno), "H_자격증상세내용");
									}
								};
							});
							showPage(l, "B_로그인");
						}
					}
				}
			});
			panel_6.add(jls[i]);
		}
		getData("course_registration");
		threading2();
	}

	private void threading2() {
		timer = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				moveLabel();
			}
		});
		timer.start();
	}
	private void moveLabel() {
		for (JLabel jl : jls) {
			jl.setLocation(0, jl.getY()-jl.getHeight());
			if(jl.getY()<0) {
				jl.setLocation(0, jl.getHeight()*4);
			}
			jl.setBorder(null);
			if(jl.getY()==jl.getHeight()*2) {
				jl.setBorder(new RoundBorder(Color.BLUE,2));
			}
		}
	}

	private void getData(String sql) {
		try (var rs = res("select cno, cname, count(*) cnt from "+sql+" join certi using(cno) group by cno order by cnt desc, cno limit 5;")) {
			for (int i = 0; rs.next(); i++) {
				jls[i].setText(rs.getString(2));
				jls[i].setName(rs.getString("cno"));
			}
			resetLabel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void resetLabel() {
		int[] idx = {2,3,4,0,1};
		for (int i = 0; i < idx.length; i++) {
			jls[i].setLocation(0, jls[i].getHeight()*idx[i]);
		}
		for (JLabel jl : jls) {
			jl.setBorder(null);
			if(jl.getY()==jl.getHeight()*2) {
				jl.setBorder(new RoundBorder(Color.BLUE,2));
			}
		}
	}

	@Override
	public void updateForm() {
		if(uno==0) {
			label_6.setText("로그인이 필요합니다.");
			button.setText("로그인");
			button_1.setVisible(false);
			((CardLayout)panel_3.getLayout()).first(panel_3);
		}
		else {
			label_6.setText(uname+"님, 환영합니다.");
			button.setText("로그아웃");
			button_1.setVisible(true);
			((CardLayout)panel_3.getLayout()).last(panel_3);
		}
	}

	JLabel[] ads = new JLabel[5];
	private Thread th;
	boolean stop = true;
	public JLabel label_8;
	public JScrollPane scrollPane;
	public JPanel panel_5;
	public JButton button;
	public JButton button_1;
	public JButton button_2;
	public JButton button_3;
	public JPanel panel_6;
	public JLabel label_9;

	private void AreaA() {
		int[] cnos = {10,1 , 15, -1, 4} ;
		for (int i = 0; i < ads.length; i++) {
			ads[i] = new JLabel(getIcon("main/" + (i + 1) + ".png", panel_1.getWidth(), panel_1.getHeight()));
			ads[i].setSize(panel_1.getSize());
			ads[i].setLocation(ads[i].getWidth() * i, 0);
			int cno = cnos[i];
			ads[i].addMouseListener(new MouseAdapter() {
				int cx;
				@Override
				public void mouseClicked(MouseEvent e) {
					if(uno!=0) {
						if(cno==-1) {
							msgErr("해당하는 자격증이 없습니다.");
						}
						else
							showPage(new H_자격증상세내용(cno),"H_자격증상세내용");
					}
					else{
						msgErr("로그인을 해주세요.");
						var l = new B_로그인();
						l.addWindowListener(new WindowAdapter() {
							public void windowClosed(java.awt.event.WindowEvent e) {
								if(uno!=0) {
									if(cno==-1) {
										msgErr("해당하는 자격증이 없습니다.");
									}
									else {
										SwingUtilities.invokeLater(()->setVisible(false));
										showPage(new H_자격증상세내용(cno),"H_자격증상세내용");
									}
								}
							};
						});
						showPage(l,"B_로그인");
					}
				}
				@Override
				public void mousePressed(MouseEvent e) {
					cx = e.getX();
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					int dx = e.getX()-cx;
					if(dx>50) {
						prev();
					}
					else if(dx<-50) {
						next();
					}
				}
				private void next() {
				    // 모든 광고 패널을 왼쪽으로 한 장만큼 이동
				    for (JLabel jl : ads) {
				        jl.setLocation(jl.getX() - jl.getWidth(), 0);
				        if (jl.getX() <= -jl.getWidth()) {
				            // 맨 왼쪽으로 밀려난 건 오른쪽 끝으로 보내줌
				            jl.setLocation(jl.getX()+jl.getWidth() * 5, 0);
				        }
				    }
				}

				private void prev() {
				    // 모든 광고 패널을 오른쪽으로 한 장만큼 이동
				    for (JLabel jl : ads) {
				        jl.setLocation(jl.getX() + jl.getWidth(), 0);
				        if (jl.getX() >= jl.getWidth() * 4) {
				            // 맨 오른쪽으로 밀려난 건 맨 왼쪽으로 보내줌
				            jl.setLocation(jl.getX()-jl.getWidth()*5, 0);
				        }
				    }
				}
			});
			panel_1.add(ads[i]);
		}

		threading();
	}

	private void threading() {
		th = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						if (stop) {
							Thread.sleep(1000);
							stop = false;
						} else {
							Thread.sleep(1);
						}
					} catch (InterruptedException e) {
						break;
					}
					for (JLabel jl : ads) {
						jl.setLocation(jl.getX()-1, 0);
						if(jl.getX()==-jl.getWidth()) {
							stop= true;
							jl.setLocation(jl.getWidth()*4, 0);
						}
					}
				}
			}
		});
		th.start();
	}

	private void addCategory() {
		String[] path = "it cooking volunteer aviation hospital".split(" ");
		String[] name = "IT 요리 봉사 항공 의학".split(" ");
		for (int i = 0; i < 5; i++) {
			MyLabel lbl = new MyLabel(getIcon("icon/" + path[i] + ".png", 60, 60));
			lbl.setPreferredSize(new Dimension(60, 60));
			lbl.setToolTipText(name[i]);
			int idx = i+1;
			lbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showPage(new C_자격증목록(idx,"'%%'"), "C_자격증목록");
				}
			});
			panel_4.add(lbl);
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				var rs = res("select * from course_registration join certi using(cno) where cname like '%"+textField.getText()+"%';");
				if(rs.next())
					showPage(new C_자격증목록(0,"'%"+textField.getText()+"%'"),"C_자격증목록");
				else {
					msgErr("해당하는 자격증이 존재하지 않습니다.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인이 되어있지 않습니다.");
				showPage(new B_로그인(),"B_로그인");
			}
			showPage(new J_자격증(), "J_자격증");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인이 되어있지 않습니다.");
				showPage(new B_로그인(),"B_로그인");
			}
			else
				showPage(new I_고객센터(), "I_고객센터");
			
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인이 되어있지 않습니다.");
				showPage(new B_로그인(),"B_로그인");
			}
			showPage(new C_자격증목록(0, "'%%'"),"C_자격증목록");
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인이 되어있지 않습니다.");
				showPage(new B_로그인(),"B_로그인");
			}
			showPage(new D_시험일정(),"D_시험일정");
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(uno==0) {
				showPage(new B_로그인(),"B_로그인");
			}
			else {
				uno = 0;
				updateForm();
			}
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getData("course_registration");
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getData("review");
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new G_나의과정(true),"G_나의과정");
		}
	}
}

class MyLabel extends JLabel {
	public MyLabel(ImageIcon img) {
		setIcon(new ImageIcon(img.getImage().getScaledInstance(50, 50, 4)));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setIcon(img);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setIcon(new ImageIcon(img.getImage().getScaledInstance(50, 50, 4)));
			}
		});
	}
}
