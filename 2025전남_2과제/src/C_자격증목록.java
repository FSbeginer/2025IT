import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class C_자격증목록 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C_자격증목록 frame = new C_자격증목록(0, "'%%'");
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
	int cgno;
	String like = "";
	public JLabel label;
	public JTextField textField;
	public JLabel label_1;
	public JPanel panel;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JPanel panel_1;
	public JLabel label_7;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;
	public JLabel label_13;
	public JLabel label_14;
	public JLabel label_15;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	boolean list = true;
	String where = "";
	public JLabel label_16;
	
	public C_자격증목록(int cgno, String sql) {
		like = sql;
		setBounds(100, 100, 923, 695);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel();
		label.setText("  Skills Qualification Association");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label.setBounds(73, 10, 222, 53);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBorder(new RoundBorder(blue));
		textField.setBounds(320, 25, 261, 27);
		getContentPane().add(textField);
		
		label_1 = new JLabel(getIcon("icon/search.png",40,40));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(590, 17, 50, 43);
		getContentPane().add(label_1);
		
		panel = new JPanel();
		panel.setBounds(210, 73, 521, 27);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		label_2 = new JLabel("자격증 목록");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		panel.add(label_2);
		
		label_3 = new JLabel("시험 일정");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		panel.add(label_3);
		
		label_4 = new JLabel("고객센터");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		panel.add(label_4);
		
		label_5 = new JLabel("자격증발급");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		panel.add(label_5);
		
		label_6 = new JLabel("합격후기");
		label_6.addMouseListener(new Label_6MouseListener());
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_6);
		
		panel_1 = new JPanel();
		panel_1.setBounds(347, 126, 521, 43);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 6, 0, 0));
		
		label_7 = new JLabel("추천과정");
		label_7.setBackground(Color.BLUE);
		label_7.setOpaque(true);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_7);
		
		label_8 = new JLabel("IT");
		label_8.setBackground(Color.WHITE);
		label_8.setOpaque(true);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_8);
		
		label_9 = new JLabel("요리");
		label_9.setBackground(Color.WHITE);
		label_9.setOpaque(true);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_9);
		
		label_10 = new JLabel("봉사");
		label_10.setBackground(Color.WHITE);
		label_10.setOpaque(true);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_10);
		
		label_11 = new JLabel("항공");
		label_11.setBackground(Color.WHITE);
		label_11.setOpaque(true);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_11);
		
		label_12 = new JLabel("의학");
		label_12.setBackground(Color.WHITE);
		label_12.setOpaque(true);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_12);
		
		label_13 = new JLabel(getIcon("icon/imagelist.png",42,34));
		label_13.addMouseListener(new Label_13MouseListener());
		label_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_13.setBounds(733, 179, 42, 34);
		getContentPane().add(label_13);
		
		label_14 = new JLabel(getIcon("icon/list.png",42,34));
		label_14.addMouseListener(new Label_14MouseListener());
		label_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_14.setBounds(785, 179, 42, 34);
		getContentPane().add(label_14);
		
		label_15 = new JLabel(getIcon("icon/medel.png", 50, 50));
		label_15.setHorizontalAlignment(SwingConstants.LEFT);
		label_15.setText("<html>총 <font color = red>6개</font>의<br>추천과정이 있습니다.");
		label_15.setBounds(45, 179, 250, 53);
		getContentPane().add(label_15);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 248, 907, 408);
		getContentPane().add(scrollPane);
		
		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		label_16 = new JLabel(getIcon("icon/logo.png",50,50));
		label_16.addMouseListener(new LabelMouseListener());
		label_16.setBounds(12, 10, 49, 53);
		getContentPane().add(label_16);
		
		JLabel[] jls = {label_7, label_8, label_9,label_10,label_11, label_12};
		for (int i = 0; i < jls.length; i++) {
			int idx = i;
			jls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					like = "'%%'";
					selectCategory(idx);
				}

			});
		}
		selectCategory(cgno);
	}
	
	private void selectCategory(int idx) {
		int[] cgnos = {-1,5,2,1,6,3};
		int cgno = cgnos[idx];
		
		if(cgno==-1) 
			where = "";
		else
			where = "and cgno = "+cgno;
		
		JLabel[] jls = {label_7, label_8, label_9,label_10,label_11, label_12};
		for (JLabel jl : jls) {
			jl.setBackground(Color.white);
			jl.setForeground(Color.black);
		}
		jls[idx].setBackground(Color.blue);
		jls[idx].setForeground(Color.white);
		if(list) {
			load();
		}
		else {
			load2();
		}
	}
	
	
	private void load2() {
		panel_2.removeAll();
		try (var rs = res("select *, count(*) cnt from course_registration join certi using(cno) join teacher t using(tno) where true "+where+" and cname like "+like+" group by cno, ratring order by cnt desc,cno limit 6;")) {
			int w= 0,h=0;
			int i = 0;
			while(rs.next()) {
				C_ImageListPanel pp =new C_ImageListPanel(getIcon("certification/"+rs.getInt("cno")+".png"), rs.getString("cname"), rs.getString("tname"), rs.getString("type")+"/"+rs.getString("type1"), rs.getString("cmicorgan"), rs.getString("cciorgan"));
				w=pp.getWidth(); h = pp.getHeight();
				pp.setLocation((w+10)*(i%3), (h+10)*(i/3));
				int cno = rs.getInt("cno");
				pp.menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showPage(new H_자격증상세내용(cno), "H_자격증상세내용");
					}
				});
				pp.menuItem_1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showPage(new F_결제하기(cno),"F_결제하기");
					}
				});
				String cname = rs.getString("cname");
				pp.menuItem_2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Desktop.getDesktop().open(new File("./datafiles/question/"+cname+"/1.pdf"));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				panel_2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0,(h+01)*(i/3)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_2.revalidate();
		panel_2.repaint();
	}
	
	private void load() {
		panel_2.removeAll();
		try (var rs = res("select *, count(*) cnt from course_registration join certi using(cno) join teacher t using(tno) where true "+where+" and cname like "+like+"  group by cno, ratring order by cnt desc,cno limit 6;")) {
			int w= 0,h=0;
			int i = 0;
			while(rs.next()) {
				C_ListPanel pp =new C_ListPanel(getIcon("certification/"+rs.getInt("cno")+".png"), rs.getString("cname"), rs.getString("tname"), rs.getString("type")+"/"+rs.getString("type1"), rs.getString("cmicorgan"), rs.getString("cciorgan"));
				w=pp.getWidth(); h = pp.getHeight();
				pp.setLocation(0, (h+1)*i);
				int cno = rs.getInt("cno");
				pp.button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showPage(new H_자격증상세내용(cno), "H_자격증상세내용");
					}
				});
				pp.button_1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showPage(new F_결제하기(cno),"F_결제하기");
					}
				});
				String cname = rs.getString("cname");
				pp.button_2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Desktop.getDesktop().open(new File("./datafiles/question/"+cname+"/1.pdf"));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				panel_2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0,(h+1)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_2.revalidate();
		panel_2.repaint();
	}
	private class Label_14MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			list = true;
			load();
		}
	}
	private class Label_13MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			list = false;
			load2();
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage("A_메인");
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			like  = "'%"+ textField.getText()+"%'";
			if(list) {
				load();
			}
			else {
				load2();
			}
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new D_시험일정(), "D_시험일정");
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			setVisible(false);
			like = "'%%'";
			selectCategory(0);
			setVisible(true);
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new I_고객센터(), "I_고객센터");
		}
	}
	private class Label_6MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.err.println("없음.");
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new J_자격증(),"J_자격증");
		}
	}
}
