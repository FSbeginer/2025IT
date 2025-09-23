import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Random;

public class B_메인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JPanel panel;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JPanel panel_1;
	public JLabel label_7;
	public JPanel panel_2;
	public JLabel label_8;
	public JLabel label_9;
	public JPanel panel_3;
	public JPanel panel_4;
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;
	public JLabel label_13;
	public JLabel label_14;

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
		setBounds(100, 100, 616, 593);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new MainLogo(107, 49);
		label.setBounds(12, 10, 107, 49);
		getContentPane().add(label);
		
		label_1 = new JLabel("\uC54C\uBC14\uCEA3");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setForeground(new Color(255, 128, 0));
		label_1.setBounds(140, 10, 114, 39);
		getContentPane().add(label_1);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 128, 0));
		panel.setBounds(0, 72, 600, 55);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		label_2 = new JLabel("\uCC44\uC6A9");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);
		
		label_3 = new JLabel("\uBE0C\uB79C\uB4DC");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_3.setForeground(new Color(255, 255, 255));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		label_4 = new JLabel("\uCC3E\uAE30");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_4.setForeground(new Color(255, 255, 255));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);
		
		label_5 = new JLabel("\uB9C8\uC774\uD398\uC774\uC9C0");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_5.setForeground(new Color(255, 255, 255));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);
		
		label_6 = new JLabel("\uD1B5\uACC4");
		label_6.addMouseListener(new Label_6MouseListener());
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_6.setForeground(new Color(255, 255, 255));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_6);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.control);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(12, 137, 350, 117);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		label_8 = new JLabel("\uC778\uAE30 \uBE0C\uB79C\uB4DC  TOP 5");
		label_8.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_8.setBounds(12, 10, 235, 23);
		panel_1.add(label_8);
		
		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.control);
		panel_4.setBounds(12, 43, 326, 52);
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 5, 10, 0));
		
		label_10 = new JLabel("");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(label_10);
		
		label_11 = new JLabel("");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(label_11);
		
		label_12 = new JLabel("");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(label_12);
		
		label_13 = new JLabel("");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(label_13);
		
		label_14 = new JLabel("");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(label_14);
		
		label_7 = new JLabel("");
		label_7.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_7.setBounds(374, 138, 214, 116);
		getContentPane().add(label_7);
		
		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.control);
		panel_2.setBorder(new LineBorder(new Color(255, 128, 0)));
		panel_2.setBounds(12, 264, 576, 280);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		label_9 = new JLabel("\uCD94\uCC9C \uC54C\uBC14");
		label_9.setBounds(12, 10, 70, 15);
		panel_2.add(label_9);
		
		panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.control);
		panel_3.setBounds(32, 35, 506, 235);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		int ano = rand.nextInt(200)+1;
		String path = "advertise/"+ano+"-1.jpg";
		label_7.setIcon(getIcon(path,label_7.getWidth(),label_7.getHeight()));
		label_7.setName(ano+"");
		
		
		settimer();
		loadB();
		loadD();
	}
	
	
	@Override
	public void updateForm() {
		loadB();
	}
	
	private void loadD() {
		panel_3.removeAll();
		int w = (panel_3.getWidth()-40)/3;
		int h = (panel_3.getHeight()-20)/2;
		try {
			var rs =res("with recently as (select cno from apply join job using(jno) join brand using(bno) join category using(cno) where uno = "+uno+" order by apdate desc limit 1)\r\n"
					+ "select * from job join  brand using(bno) join recently using(cno) order by rand() limit 6;");
			int i = 0;
			while(rs.next()) {
				B_패널 pp = new B_패널(rs.getString("jname"), rs.getInt("jday"), rs.getInt("jtime"), rs.getInt("jwork"), rs.getInt("jmoney"));
				pp.setSize(w, h);
				pp.setLocation((w+20)*(i%3), (h+20)*(i/3));
				int jno = rs.getInt("jno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new C_알바정보(jno),"C_알바정보");
					}
				});
				panel_3.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(panel_3.getComponents().length==0) {
			try {
				var rs =res("select *, count(*) cnt from apply join job using(jno) join brand using(bno) group by bno order by cnt desc, bno limit 6;");
				int i = 0;
				while(rs.next()) {
					B_패널 pp = new B_패널(rs.getString("jname"), rs.getInt("jday"), rs.getInt("jtime"), rs.getInt("jwork"), rs.getInt("jmoney"));
					pp.setSize(w, h);
					pp.setLocation((w+20)*(i%3), (h+20)*(i/3));
					int jno = rs.getInt("jno");
					pp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							showPage(new C_알바정보(jno),"C_알바정보");
						}
					});
					panel_3.add(pp);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		panel_3.revalidate();
		panel_3.repaint();
	}

	private void loadB() {
		var jls = new JLabel[] {label_10, label_11,label_12,label_13,label_14};
		try {
			var rs =res("select bno, count(*) cnt from apply join job using(jno) join brand using(bno) group by bno order by cnt desc, bno limit 5;");
			int i = 0;
			while(rs.next()) {
				jls[i].setIcon(getIcon("brand/"+rs.getInt(1)+".png",60,50));
				jls[i].setName(rs.getInt(1)+"");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	Random rand = new Random();
	private Timer timer;
	
	private void settimer() {
		timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ano = rand.nextInt(200)+1;
				String path = "advertise/"+ano+"-1.jpg";
				label_7.setIcon(getIcon(path,label_7.getWidth(),label_7.getHeight()));
				label_7.setName(ano+"");
			}
		});
		timer.start();
		
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ano = Integer.parseInt(label_7.getName());
				timer.stop();
				var d = new E_광고정보(ano);
				d.setVisible(true);
				timer.start();
			}
		});
	}
	
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new I_마이페이지(), "I_마이페이지");
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new D_채용(), "D_채용");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new H_찾기(), "H_찾기");
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_브랜드(), "F_브랜드");
		}
	}
	private class Label_6MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new J_통계(), "J_통계");
		}
	}
}
