package 풀이본;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class A_메인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JTextField textField;
	public JLabel label_2;
	public JPanel panel;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JPanel panel_4;
	public JLabel label_7;
	public JLabel label_8;
	public JButton button;
	public RoundButton button_1;
	public RoundButton button_2;
	public RoundButton button_3;
	public JPanel panel_5;

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
		setTitle("자격증 메인 화면");
		setBounds(100, 100, 1023, 568);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new MainLogo();
		label.setBounds(12, 10, 63, 48);
		getContentPane().add(label);

		label_1 = new JLabel("Skills Qualification Association");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_1.setBounds(87, 10, 269, 57);
		getContentPane().add(label_1);

		textField = new JTextField();
		textField.setBorder(new RoundBorder(blue));
		textField.setBounds(368, 28, 310, 27);
		getContentPane().add(textField);
		textField.setColumns(10);

		label_2 = new JLabel(getIcon("icon/search.png", 43, 43));
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setBounds(690, 18, 43, 43);
		getContentPane().add(label_2);

		panel = new JPanel();
		panel.setBounds(22, 68, 960, 43);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));

		label_3 = new JLabel("자격증 목록");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);

		label_4 = new JLabel("시험 일정");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);

		label_5 = new JLabel("고객센터");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);

		label_6 = new JLabel("자격증발급");
		label_6.addMouseListener(new Label_6MouseListener());
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_6);

		panel_1 = new JPanel();
		panel_1.setBounds(32, 121, 434, 227);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBounds(478, 119, 220, 229);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		button_2 = new RoundButton("로그인");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setText("추천순");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(Color.BLUE);
		button_2.setBounds(12, 10, 97, 30);
		panel_2.add(button_2);

		button_3 = new RoundButton("로그인");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setText("별점순");
		button_3.setBorder(new RoundBorder(Color.BLACK));
		button_3.setBackground(new Color(255, 255, 255));
		button_3.setBounds(113, 10, 97, 30);
		panel_2.add(button_3);

		panel_5 = new JPanel();
		panel_5.setBounds(0, 42, 220, 187);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBounds(762, 121, 220, 227);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		label_8 = new JLabel(getIcon("icon/check.png", 45, 45));
		label_8.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_8.setBounds(0, 0, 220, 45);
		panel_3.add(label_8);

		button = new RoundButton("로그인");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(Color.blue);
		button.setForeground(Color.white);
		button.setBounds(10, 51, 97, 30);
		panel_3.add(button);

		button_1 = new RoundButton("로그인");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBorder(new RoundBorder(Color.BLACK));
		button_1.setText("내 정보");
		button_1.setBackground(SystemColor.control);
		button_1.setBounds(111, 51, 97, 30);
		panel_3.add(button_1);

		panel_6 = new JPanel();
		panel_6.setBounds(0, 84, 220, 143);
		panel_3.add(panel_6);
		panel_6.setLayout(new CardLayout(0, 0));

		label_9 = new JLabel(
				"<html><font color = red>로그인이 필요합니다.<br></font><b>1.</b> 유효한 사용자 정보를 입력하세요.<br><b>2. </b>인증 절차를 완료하세요.<br><b>3.</b> 로그인 후 이용 가능합니다.<br><b>4.</b> 오류가 지속되면 관리자에게 문의하세요.");
		label_9.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_9.setBorder(new RoundBorder(Color.black));
		panel_6.add(label_9, "name_35633010819300");

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_6.add(scrollPane, "name_35638039380400");

		panel_7 = new JPanel();
		panel_7.setBorder(new RoundBorder(Color.black));
		scrollPane.setViewportView(panel_7);

		panel_4 = new JPanel();
		panel_4.setBorder(new RoundBorder(Color.black));
		panel_4.setBounds(0, 428, 1007, 158);
		getContentPane().add(panel_4);

		label_7 = new JLabel(getIcon("icon/medel.png", 43, 43));
		label_7.setText("자격증을 선택해 주세요.");
		label_7.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label_7.setBounds(32, 369, 322, 43);
		getContentPane().add(label_7);

		addCate();
		loadAd();
		loadC();
		updateForm();
	}

	JLabel[] lbls = new JLabel[5];
	
	private void loadC() {
		int w = panel_5.getWidth();
		int h = panel_5.getHeight() / 5;
		for (int i = 0; i < 5; i++) {
			lbls[i] = new JLabel();
			lbls[i].setSize(w, h);
			int idx = i;
			lbls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int cno = Integer.parseInt(lbls[idx].getName());
					if (uno != 0) {
						showPage(new H_상세내용(cno), "H_상세내용");
					} else {
						msgErr("로그인을 해주세요.");
						var l = new B_로그인();
						l.addWindowListener(new WindowAdapter() {
							public void windowClosed(java.awt.event.WindowEvent e) {
								if (uno != 0 && !isAdmin) {
									l.setName("t");
									showPage(new H_상세내용(cno), "H_상세내용");
								}
							};
						});
						showPage(l, "B_로그인");
					}
				}
			});
			panel_5.add(lbls[i]);
		}
		getdata("course_registration");
		int[] idx = {2,3,4,0,1};
		for (int i = 0; i < 5; i++) {
			lbls[i].setLocation(0, lbls[i].getHeight()*idx[i]);
		}
		threading2();
	}

	private void threading2() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					for (int i = 0; i < jls.length; i++) {
						lbls[i].setLocation(0, lbls[i].getY()-lbls[i].getHeight());
						if(lbls[i].getY()<0) lbls[i].setLocation(0, lbls[i].getHeight()*4);
						if(lbls[i].getY()==lbls[i].getHeight()*2)lbls[i].setBorder(new RoundBorder(Color.blue));
						else lbls[i].setBorder(null);
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void getdata(String sql) {
		try (var rs = res(
				"select cno , cname,count(*) cnt from "+sql+" right join certi using(cno) group by cno order by cnt desc,cno limit 5;")) {
			int i = 0;
			while (rs.next()) {
				lbls[i].setText(rs.getString(2));
				lbls[i].setName(rs.getString(1));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateForm() {
		if (uno != 0) {
			button.setText("로그아웃");
			button_1.setVisible(true);
			try {
				var rs = res("select * from user where uno = " + uno);
				rs.next();
				label_8.setText(rs.getString(2) + "님, 환영합니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			loadUserPanel();
			((CardLayout) panel_6.getLayout()).last(panel_6);
		} else {
			button.setText("로그인");
			button_1.setVisible(false);
			label_8.setText("로그인이 필요합니다.");
			((CardLayout) panel_6.getLayout()).first(panel_6);
		}
	}

	private void loadUserPanel() {

	}

	JLabel[] jls = new JLabel[5];
	private Thread th;
	private int cx;
	public JPanel panel_6;
	public JLabel label_9;
	public JScrollPane scrollPane;
	public JPanel panel_7;

	private void loadAd() {
		int[] cno = { 10, 1, 14, -1, 5 };
		for (int i = 0; i < jls.length; i++) {
			jls[i] = new JLabel(getIcon("main/" + (i + 1) + ".png", panel_1.getWidth(), panel_1.getHeight()));
			jls[i].setSize(panel_1.getSize());
			int c = cno[i];
			jls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (uno != 0) {
						if (c == -1) {
							msgErr("해당하는 자격증이 없습니다.");
							return;
						} else {
							showPage(new H_상세내용(c), "H_상세내용");
						}
					} else {
						msgErr("로그인을 해주세요.");
						var l = new B_로그인();
						l.addWindowListener(new WindowAdapter() {
							public void windowClosed(java.awt.event.WindowEvent e) {
								if (uno != 0 && !isAdmin) {
									l.setName("t");
									showPage(new H_상세내용(c), "H_상세내용");
								}
							};
						});
						showPage(l, "B_로그인");
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					cx = e.getX();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					int dx = e.getX() - cx;
					if (dx >= 50) {
						moveRight();
					} else if (dx <= -50) {
						moveLeft();
					}
				}

				private void moveLeft() {
					for (JLabel jl : jls) {
						jl.setLocation(jl.getX() - jl.getWidth(), jl.getY());
						if (jl.getX() <= -jl.getWidth()) {
							jl.setLocation(jl.getWidth() * 5 + jl.getX(), jl.getY());
						} else if (jl.getX() >= jl.getWidth() * 4) {
							jl.setLocation(jl.getX() - jl.getWidth() * 5, jl.getY());
						}
					}
					repaint();
				}

				private void moveRight() {
					for (JLabel jl : jls) {
						jl.setLocation(jl.getX() + jl.getWidth(), jl.getY());
						if (jl.getX() <= -jl.getWidth()) {
							jl.setLocation(jl.getWidth() * 5 + jl.getX(), jl.getY());
						} else if (jl.getX() >= jl.getWidth() * 4) {
							jl.setLocation(jl.getX() - jl.getWidth() * 5, jl.getY());
						}
					}
					repaint();
				}
			});

			jls[i].setLocation(panel_1.getWidth() * i, 0);
			panel_1.add(jls[i]);
		}
		threading();
	}

	private void threading() {
		th = new Thread(new Runnable() {
			boolean stop = false;

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
						e.printStackTrace();
					}
					for (JLabel jl : jls) {
						jl.setLocation(jl.getX() - 1, jl.getY());

						if (jl.getX() == -jl.getWidth()) {
							jl.setLocation(jl.getWidth() * 4, jl.getY());
							stop = true;
						}
					}
				}
			}
		});
		th.start();
	}

	private void addCate() {
		int[] cgno = { 5, 2, 1, 6, 2 };
		String[] path = "it,cooking,volunteer,aviation,hospital".split(",");
		for (int i = 0; i < 5; i++) {
			MyLabel jl = new MyLabel(getIcon("icon/" + path[i] + ".png", 50, 50));
			jl.setPreferredSize(new Dimension(50, 50));
			panel_4.add(jl);
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (uno == 0) {
				showPage(new B_로그인(), "B_로그인");
			} else {
				uno = 0;
				isAdmin = false;
				updateForm();
			}
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getdata("course_registration");
			int[] idx = {2,3,4,0,1};
			for (int i = 0; i < 5; i++) {
				lbls[i].setLocation(0, lbls[i].getHeight()*idx[i]);
			}
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getdata("review");
			int[] idx = {2,3,4,0,1};
			for (int i = 0; i < 5; i++) {
				lbls[i].setLocation(0, lbls[i].getHeight()*idx[i]);
			}
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new G_나의과정(),"G_나의과정");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new D_시험일정(),"D_시험일정");
		}
	}
	private class Label_6MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new J_자격증(), "J_자격증");
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new I_고객센터(), "I_고객센터");
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new C_자격증목록(0, ""),"C_자격증목록");
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				var rs = res("select * from course_registration join certi using(cno) where cname like '%"+textField.getText()+"%';");
				if(rs.next())
					showPage(new C_자격증목록(0,textField.getText()),"C_자격증목록");
				else {
					msgErr("해당하는 자격증이 존재하지 않습니다.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}

class MyLabel extends JLabel {
	public MyLabel(ImageIcon img) {
		ImageIcon small = new ImageIcon(img.getImage().getScaledInstance(40, 40, 2));
		setIcon(small);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setIcon(img);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setIcon(small);
			}
		});
	}
}