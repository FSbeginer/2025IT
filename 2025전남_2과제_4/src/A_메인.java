import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

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
	public JPanel panel_4;
	public JPanel panel_5;
	public JLabel label_6;
	public JScrollPane scrollPane;
	public JPanel panel_6;
	public JButton button;
	public JButton button_1;
	public JPanel panel_7;
	public JLabel label_7;

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
		setTitle("자격증 메인 화면");
		setBounds(100, 100, 1008, 607);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(getIcon("icon/logo.png",45,45));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setText("Skills Qualification Association");
		label.setBounds(12, 10, 405, 46);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(391, 25, 293, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel(getIcon("icon/search.png",40,40));
		label_1.setBounds(695, 15, 57, 46);
		getContentPane().add(label_1);
		
		panel = new JPanel();
		panel.setBounds(22, 66, 942, 46);
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
		panel_1.setBounds(25, 144, 443, 240);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBounds(480, 144, 228, 240);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		button = new JButton("추천순");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 10, 97, 29);
		panel_2.add(button);
		
		button_1 = new JButton("별점순");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(118, 10, 97, 29);
		panel_2.add(button_1);
		
		panel_7 = new JPanel();
		panel_7.setBounds(0, 49, 228, 191);
		panel_2.add(panel_7);
		panel_7.setLayout(new GridLayout(5, 1, 0, 0));
		
		panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBounds(758, 122, 206, 268);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(0, 82, 206, 186);
		panel_3.add(panel_5);
		panel_5.setLayout(new CardLayout(0, 0));
		
		label_6 = new JLabel("<html><font color = red>로그인이 필요합니다.<br></font><b>1.</b> 유효한 사용자 정보를 입력하세요.<br><b>2. </b>인증 절차를 완료하세요.<br><b>3.</b> 로그인 후 이용 가능합니다.<br><b>4. </b>오류가 지속되면 관리자에게 문의하세요.");
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_5.add(label_6, "name_2566836590700");
		
		scrollPane = new JScrollPane();
		panel_5.add(scrollPane, "name_2568991884300");
		
		panel_6 = new JPanel();
		scrollPane.setViewportView(panel_6);
		panel_6.setLayout(null);
		
		button_2 = new JButton("로그인");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBounds(0, 49, 97, 29);
		panel_3.add(button_2);
		
		button_3 = new JButton("내 정보");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setBounds(106, 49, 97, 29);
		panel_3.add(button_3);
		
		label_8 = new JLabel("로그인이 필요합니다.");
		label_8.setIcon(getIcon("icon/check.png",30,30));
		label_8.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_8.setBounds(0, 10, 206, 30);
		panel_3.add(label_8);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(0, 458, 992, 177);
		getContentPane().add(panel_4);
		
		label_7 = new JLabel(getIcon("icon/medal.png",40,40));
		label_7.setText("자격증을 선택해 주세요.");
		label_7.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label_7.setBounds(12, 394, 405, 46);
		getContentPane().add(label_7);
		
		setA();
		setC();
		setCate();
		updateForm();
	}
	@Override
	public void updateForm() {
		if(uno==0) {
			((CardLayout)panel_5.getLayout()).first(panel_5);
			button_2.setText("로그인");
			button_3.setVisible(false);
			label_8.setText("로그인이 필요합니다.");
		}else {
			((CardLayout)panel_5.getLayout()).last(panel_5);
			button_2.setText("로그아웃");
			button_3.setVisible(true);
			label_8.setText(uname+"님, 환영합니다.");
		}
	}

	private void setCate() {
		String[] path = "it,cooking,volunteer,aviation,hospital".split(",");
		for (int i = 0; i < cgno.length; i++) {
			MyLabel jls = new MyLabel(getIcon("icon/"+path[i]+".png",60,60));
			jls.setPreferredSize(new Dimension(60, 60));
			try {
				var rs =res("select * from category where cgno = "+cgno[i]);
				rs.next();
				jls.setToolTipText(rs.getString(2));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			jls.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showPage(new C_자격증목록(),"C_자격증목록");
				}
			});
			panel_4.add(jls);
		}
	}

	JLabel[] jls = new JLabel[5];
	int[] cgno =  {5,2,1,6,3};
	int[] cno =  {10,1,14,-1,5};
	private void setA() {
		for (int i = 0; i < jls.length; i++) {
			jls[i] = new JLabel(getIcon("main/"+(i+1)+".png",panel_1.getWidth(),panel_1.getHeight()));
			jls[i].setSize(panel_1.getSize());
			jls[i].setLocation(jls[i].getWidth()*i, 0);
			int c = cno[i];
			jls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(uno==0) {
						msgErr("로그인을 해주세요.");
						var b = new B_로그인();
						b.addWindowListener(new WindowAdapter() {
							public void windowClosed(java.awt.event.WindowEvent e) {
								if(uno!=0) {
									b.setName("");
									if(c==-1) {
										msgErr("해당하는 자격증이 없습니다.");
										updateForm();
										setVisible(true);
										return;
									}
									else {
										showPage(new H_상세정보(c),"H_상세정보");
									}
								}
							};
						});
						showPage(b,"B_로그인");
					}
					else {
						if(c==-1) {
							msgErr("해당하는 자격증이 없습니다.");
							return;
						}
						else {
							showPage(new H_상세정보(c),"H_상세정보");
						}
					}
				}
			});
			panel_1.add(jls[i]);
		}
		
		threadingA();
	}

	private void threadingA() {
		new Thread(new Runnable() {
			boolean stop =true;
			@Override
			public void run() {
				while(true) {
					if(stop) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						stop =false;
					}
					else {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					for (var jl : jls) {
						jl.setLocation(jl.getX()-1, jl.getY());
						if(jl.getX()==-jl.getWidth()) {
							jl.setLocation(jl.getWidth()*4, 0);
							stop = true;
						}
					}
				}
			}
		}).start();
	}

	JLabel[] jls2 = new JLabel[5];
	private void setC() {
		for (int i = 0; i < cgno.length; i++) {
			jls2[i] = new JLabel();
			panel_7.add(jls2[i]);
		}
		jls2[2].setBorder(new LineBorder(Color.blue));
		jls2[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cno = (int) datas.peek()[0];
				showPage(new H_상세정보(cno),"H_상세정보");
			}
		});
		getData("cnt");
		lenderingC();
		threadingC();
	}

	private void getData(String order) {
		if(timer!=null&&timer.isRunning()) {
			timer.stop();
		}
		datas.clear();
		try {
			var rs =res("select cno,cname,count(*) cnt,avg(rstar) star from course_registration right join certi c using(cno) left join review using(cno) group by cno order by "+order+" desc,cno limit 5;");
			while(rs.next()) {
				datas.add(new Object[] {rs.getInt(1),rs.getString(2)});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(timer!=null&&timer.isRunning()) {
			timer.start();
		}
	}

	Queue<Object[]> datas = new LinkedList<Object[]>();
	private Timer timer; 
	public JButton button_2;
	public JButton button_3;
	public JLabel label_8;
	private void threadingC() {
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				var d = datas.poll();
				datas.add(d);
				lenderingC();
			}

		});
		timer.start();
	}
	private void lenderingC() {
		for (int i = 0; i < 5; i++) {
			var d = datas.poll();
			jls2[i].setText(d[1]+"");
			datas.add(d);
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getData("cnt");
			lenderingC();
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getData("star");
			lenderingC();
		}
	}
	private class Button_2ActionListener implements ActionListener {
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
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new G_나의과정(),"G_나의과정");
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인 되어있지 않습니다.");
				return;
			}
			showPage(new C_자격증목록(),"C_자격증목록");
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인 되어있지 않습니다.");
				return;
			}
			showPage(new J_자격증(),"J_자격증");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인 되어있지 않습니다.");
				return;
			}
			showPage(new I_고객센터(),"I_고객센터");
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인 되어있지 않습니다.");
				return;
			}
			showPage(new D_시험일정(),"D_시험일정");
		}
	}
}
class MyLabel extends JLabel {
	public MyLabel(ImageIcon img) {
		ImageIcon small = new ImageIcon(img.getImage().getScaledInstance(50, 50, 1));
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
