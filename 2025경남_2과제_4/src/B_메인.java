import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.border.LineBorder;

public class B_∏ﬁ¿Œ extends BF {
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
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;
	public JLabel label_13;
	public JLabel label_14;
	public JPanel panel_4;
	private Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B_∏ﬁ¿Œ frame = new B_∏ﬁ¿Œ();
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
	public B_∏ﬁ¿Œ() {
		setTitle("\uBA54\uC778");
		setBounds(100, 100, 646, 597);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new MainLogo(88, 48);
		label.setBounds(10, 0, 88, 48);
		getContentPane().add(label);

		label_1 = new JLabel("\uC54C\uBC14\uCEA3");
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 18));
		label_1.setForeground(new Color(255, 128, 0));
		label_1.setBounds(103, 0, 146, 56);
		getContentPane().add(label_1);

		panel = new JPanel();
		panel.setBackground(new Color(255, 128, 0));
		panel.setBounds(0, 54, 630, 48);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		label_2 = new JLabel("\uCC44\uC6A9");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		label_3 = new JLabel("\uBE0C\uB79C\uB4DC");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		label_3.setForeground(new Color(255, 255, 255));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);

		label_4 = new JLabel("\uCC3E\uAE30");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		label_4.setForeground(new Color(255, 255, 255));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);

		label_5 = new JLabel("\uB9C8\uC774\uD398\uC774\uC9C0");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		label_5.setForeground(new Color(255, 255, 255));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);

		label_6 = new JLabel("\uD1B5\uACC4");
		label_6.addMouseListener(new Label_6MouseListener());
		label_6.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		label_6.setForeground(new Color(255, 255, 255));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_6);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(10, 112, 358, 105);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		label_8 = new JLabel("\uC778\uAE30 \uBE0C\uB79C\uB4DC TOP 5");
		label_8.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		label_8.setBounds(12, 10, 133, 15);
		panel_1.add(label_8);

		panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.control);
		panel_3.setBounds(12, 35, 334, 60);
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 5, 10, 0));

		label_10 = new JLabel("");
		label_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_10.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel_3.add(label_10);

		label_11 = new JLabel("");
		label_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_11.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel_3.add(label_11);

		label_12 = new JLabel("");
		label_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_12.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel_3.add(label_12);

		label_13 = new JLabel("");
		label_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_13.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel_3.add(label_13);

		label_14 = new JLabel("");
		label_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_14.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel_3.add(label_14);

		label_7 = new JLabel("");
		label_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(380, 112, 238, 105);
		getContentPane().add(label_7);

		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 128, 0)));
		panel_2.setBackground(SystemColor.control);
		panel_2.setBounds(10, 227, 608, 317);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		label_9 = new JLabel("\uCD94\uCC9C \uC54C\uBC14");
		label_9.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		label_9.setBounds(12, 10, 133, 15);
		panel_2.add(label_9);

		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.control);
		panel_4.setBounds(41, 35, 530, 270);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		updateForm();
		setD();
		setTimer();
	}

	@Override
	public void updateForm() {
		setB();
	}

	Random rand = new Random();

	private void setTimer() {
		timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ano = rand.nextInt(200) + 1;
				String path = "advertise/" + ano + "-1.jpg";
				label_7.setIcon(getIcon(path, label_7.getWidth(), label_7.getHeight()));
				label_7.setName(ano + "");
			}
		});
		int ano = rand.nextInt(200) + 1;
		String path = "advertise/" + ano + "-1.jpg";
		label_7.setIcon(getIcon(path, label_7.getWidth(), label_7.getHeight()));
		label_7.setName(ano + "");
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer.stop();
				int ano = Integer.parseInt(label_7.getName());
				var d = new E_±§∞Ì¡§∫∏(ano);
				d.setVisible(true);
				timer.start();
			}
		});
		timer.start();
	}

	private void setD() {
		panel_4.removeAll();
		int w = (panel_4.getWidth()-40)/3;
		int h = (panel_4.getHeight()-25)/2;
		try (var rs = res("with recent as(select cno from apply join job using(jno) join brand using(bno) where uno = "
				+ uno + " order by apno desc limit 1)\r\n"
				+ " select * from job join brand using(bno) join recent using(cno) order by rand() limit 6;")) {
			int i = 0;
			while (rs.next()) {
				JLabel jl = new JLabel(String.format("<html><b>%s</b><br><br>[¡÷ %d¿œ, %dΩ√∞£, %s]<br>Ω√±ﬁ: %dø¯", rs.getString("jname"),rs.getInt("jday"),rs.getInt("jtime"),rs.getInt("jwork")==1?"∞Ëæ‡¡˜":"¡§±‘¡˜",rs.getInt("jmoney")));
				jl.setBorder(new LineBorder(orange));
				jl.setSize(w,h);
				jl.setLocation((w+20)*(i%3), (h+25)*(i/3));
				int jno = rs.getInt("jno");
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new C_æÀπŸ¡§∫∏(jno),"C_æÀπŸ¡§∫∏");
					}
				});
				jl.setFont(new Font("∏º¿∫ ∞ÌµÒ", 0, 12));
				jl.setOpaque(true);
				jl.setBackground(Color.white);
				panel_4.add(jl);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (panel_4.getComponents().length == 0) {
			try {
				var rs =res("select *, count(*) cnt from  apply join job using(jno) group by jno order by cnt desc, jno limit 6;");
				int i = 0;
				while(rs.next()) {
					JLabel jl = new JLabel(String.format("<html><b>%s</b><br><br>[¡÷ %d¿œ, %dΩ√∞£, %s]<br>Ω√±ﬁ: %dø¯", rs.getString("jname"),rs.getInt("jday"),rs.getInt("jtime"),rs.getInt("jwork")==1?"∞Ëæ‡¡˜":"¡§±‘¡˜",rs.getInt("jmoney")));
					jl.setBorder(new LineBorder(orange));
					jl.setSize(w,h);
					jl.setLocation((w+20)*(i%3), (h+25)*(i/3));
					int jno = rs.getInt("jno");
					jl.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							showPage(new C_æÀπŸ¡§∫∏(jno),"C_æÀπŸ¡§∫∏");
						}
					});
					jl.setFont(new Font("∏º¿∫ ∞ÌµÒ", 0, 12));
					jl.setOpaque(true);
					jl.setBackground(Color.white);
					panel_4.add(jl);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		panel_4.revalidate();
		panel_4.repaint();
	}

	private void setB() {
		var jls = new JLabel[] { label_10, label_11, label_12, label_13, label_14 };
		try (var rs = res(
				"select *, count(*) cnt from  apply join job using(jno) right join brand using(bno) group by bno order by cnt desc, bno limit 5")) {
			int i = 0;
			while (rs.next()) {
				jls[i].setIcon(getIcon("brand/" + rs.getInt("bno") + ".png", 60, 60));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new D_√§øÎ(),"D_√§øÎ");
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_∫Í∑£µÂ(),"F_∫Í∑£µÂ");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new H_√£±‚(),"H_√£±‚");
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new I_∏∂¿Ã∆‰¿Ã¡ˆ(),"I_∏∂¿Ã∆‰¿Ã¡ˆ");
		}
	}
	private class Label_6MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new J_≈Î∞Ë(),"J_≈Î∞Ë");
		}
	}
}
