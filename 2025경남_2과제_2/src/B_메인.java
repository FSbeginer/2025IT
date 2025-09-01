import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;

public class B_메인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JPanel panel;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label_6;
	public JPanel panel_3;
	public JLabel label_7;
	public JPanel panel_4;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;
	public JLabel label_13;
	public JPanel panel_5;
	private Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B_메인 frame = new B_메인();
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
	public B_메인() {
		setTitle("\uBA54\uC778");
		setBounds(100, 100, 661, 653);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new MainLogo(114,52);
		label.setBounds(0, 0, 114, 52);
		getContentPane().add(label);
		
		label_1 = new JLabel("\uC54C\uBC14\uCEA3");
		label_1.setForeground(new Color(255, 128, 0));
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(126, 10, 104, 42);
		getContentPane().add(label_1);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 128, 0));
		panel.setBounds(0, 62, 645, 60);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 4, 0, 0));
		
		label_2 = new JLabel("\uCC44\uC6A9");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);
		
		label_3 = new JLabel("\uBE0C\uB79C\uB4DC");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_3.setForeground(new Color(255, 255, 255));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		label_4 = new JLabel("\uCC3E\uAE30");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_4.setForeground(new Color(255, 255, 255));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);
		
		label_5 = new JLabel("\uB9C8\uC774\uD398\uC774\uC9C0");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_5.setForeground(new Color(255, 255, 255));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(10, 132, 382, 114);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		label_7 = new JLabel("\uC778\uAE30 \uBE0C\uB79C\uB4DC TOP 5");
		label_7.setBounds(12, 21, 164, 15);
		panel_1.add(label_7);
		
		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.control);
		panel_4.setBounds(12, 46, 358, 58);
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 5, 10, 0));
		
		label_8 = new JLabel("");
		label_8.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(label_8);
		
		label_9 = new JLabel("");
		label_9.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(label_9);
		
		label_10 = new JLabel("");
		label_10.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(label_10);
		
		label_11 = new JLabel("");
		label_11.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(label_11);
		
		label_12 = new JLabel("");
		label_12.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(label_12);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.control);
		panel_2.setBounds(404, 132, 229, 114);
		getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		label_6 = new JLabel("");
		panel_2.add(label_6, BorderLayout.CENTER);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(255, 128, 0)));
		panel_3.setBackground(SystemColor.control);
		panel_3.setBounds(12, 256, 621, 348);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		label_13 = new JLabel("\uCD94\uCC9C \uC54C\uBC14");
		label_13.setBounds(12, 10, 88, 15);
		panel_3.add(label_13);
		
		panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.control);
		panel_5.setBounds(43, 35, 540, 291);
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(2, 6, 20, 20));
		int ano = rand.nextInt(200)+1;
		label_6.setName(ano+"");
		label_6.setIcon(getIcon("advertise/"+ano+"-1.jpg",panel_2.getWidth(),panel_2.getHeight()));
		settimer();
		updateForm();
	}
	
	private void settimer() {
		timer = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ano = rand.nextInt(200)+1;
				label_6.setName(ano+"");
				label_6.setIcon(getIcon("advertise/"+ano+"-1.jpg",panel_2.getWidth(),panel_2.getHeight()));
			}
		});
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ano = Integer.parseInt(label_6.getName());
				var d = new E_광고정보(ano);
				timer.stop();
				d.setVisible(true);
				timer.start();
			}
		});
		timer.start();
	}

	Random rand = new Random();
	
	@Override
	public void updateForm() {
		loadB();
		loadD();
	}
	private void loadD() {
		panel_5.removeAll();
		try (var rs = res("with recently as (select cno from apply join job using(jno) join brand using(bno) where uno = "+uno+" order by apno desc limit 1)\r\n"
				+ "select * from job join brand using(bno) join recently using(cno) order by rand() limit 6;")) {
			int i = 0;
			while(rs.next()) {
				B_패널 pp =new B_패널(rs.getString("jname"), rs.getInt("jday"), rs.getInt("jtime"),rs.getInt("jwork"), rs.getInt("jmoney"));
				int jno = rs.getInt("jno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new C_알바정보(jno),"C_알바정보");
					}
				});
				panel_5.add(pp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(panel_5.getComponents().length==0) {
			try (var rs = res("select *, count(*) cnt from apply right join job using(jno) group by jno order by cnt desc, bno limit 6;")) {
				int i = 0;
				while(rs.next()) {
					B_패널 pp =new B_패널(rs.getString("jname"), rs.getInt("jday"), rs.getInt("jtime"),rs.getInt("jwork"), rs.getInt("jmoney"));
					int jno = rs.getInt("jno");
					pp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							showPage(new C_알바정보(jno),"C_알바정보");
						}
					});
					panel_5.add(pp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		panel_5.revalidate();
		panel_5.repaint();
	}	

	private void loadB() {
		try (var rs = res("select *, count(*) cnt from apply right join job using(jno) group by jno order by cnt desc, bno limit 5;")) {
			JLabel[] jls = {label_8, label_9, label_10, label_11, label_12};
			int i = 0;
			while(rs.next()) {
				jls[i].setIcon(getIcon("brand/"+rs.getInt("bno")+".png",60,60));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new D_채용(),"D_채용");
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new I_마이페이지(),"I_마이페이지");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new H_찾기(),"H_찾기");
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_브랜드(),"F_브랜드");
		}
	}
}
