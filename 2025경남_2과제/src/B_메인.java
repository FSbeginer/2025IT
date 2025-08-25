import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
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
import java.awt.BorderLayout;

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
	public JPanel panel_3;
	public JPanel panel_4;
	public JLabel label_6;
	public JLabel label_7;
	public JPanel panel_5;
	public JLabel lblAd;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;
	private Timer timer;

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

	public B_메인() {
		setTitle("\uBA54\uC778");
		setBounds(100, 100, 670, 618);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new MainIcon();
		label.setBounds(0, 0, 119, 58);
		getContentPane().add(label);

		label_1 = new JLabel("\uC54C\uBC14\uCEA3");
		label_1.setForeground(orange);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_1.setBounds(118, 0, 135, 58);
		getContentPane().add(label_1);

		panel = new JPanel();
		panel.setBackground(orange);
		panel.setBounds(0, 56, 654, 67);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));

		label_2 = new JLabel("\uCC44\uC6A9");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		label_3 = new JLabel("\uBE0C\uB79C\uB4DC");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);

		label_4 = new JLabel("\uCC3E\uAE30");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);

		label_5 = new JLabel("\uB9C8\uC774\uD398\uC774\uC9C0");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(10, 133, 418, 118);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.control);
		panel_4.setBounds(12, 43, 394, 60);
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 5, 15, 0));

		label_8 = new JLabel("");
		label_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_8);

		label_9 = new JLabel("");
		label_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_9);

		label_10 = new JLabel("");
		label_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_10);

		label_11 = new JLabel("");
		label_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_11);

		label_12 = new JLabel("");
		label_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_12);

		label_6 = new JLabel("\uC778\uAE30 \uBE0C\uB79C\uB4DC TOP 5");
		label_6.setBounds(12, 10, 118, 15);
		panel_1.add(label_6);

		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.control);
		panel_2.setBounds(440, 133, 202, 118);
		getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		lblAd = new JLabel("");
		panel_2.add(lblAd, BorderLayout.CENTER);

		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(orange));
		panel_3.setBackground(SystemColor.control);
		panel_3.setBounds(12, 261, 630, 308);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		label_7 = new JLabel("\uCD94\uCC9C \uC54C\uBC14");
		label_7.setBounds(12, 10, 65, 15);
		panel_3.add(label_7);

		panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.control);
		panel_5.setBounds(36, 35, 558, 263);
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(2, 3, 15, 15));

		AreaB();
		AreaC();
		AreaD();
		JLabel[] jls = { label_8, label_9, label_10, label_11, label_12 };
	}

	@Override
	public void updateForm() {
		AreaB();
	}

	private void AreaB() {
		JLabel[] jls = { label_8, label_9, label_10, label_11, label_12 };
		try (var rs = res(
				"select bno,bname from apply ap join job j using(jno) join brand b using(bno) group by bno order by count(*) desc limit 5;")) {
			for (int i = 0; rs.next(); i++) {
				int bno = rs.getInt(1);
				jls[i].setIcon(getIcon("brand/" + bno + ".png", 65, 65));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setAd() {
		Random rand = new Random();
		int ano = rand.nextInt(200) + 1;
		String path = "advertise/" + ano + "-1.jpg";
		lblAd.setName(ano + "");
		lblAd.setIcon(getIcon(path, panel_2.getWidth(), panel_2.getHeight()));
		lblAd.repaint();
	}
	private void AreaC() {
		timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setAd();
			}
		});
		lblAd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer.stop();
				int ano = Integer.parseInt(lblAd.getName());
				var E = new E_광고정보(ano);
				E.setVisible(true);
				timer.start();
			}
		});
		timer.start();
		setAd();
	}

	private void AreaD() {
		panel_5.removeAll();
		try (var rs = res(
				"select cno as ucno from apply ap join job j using(jno) join brand using(bno) join category using(cno) where uno = "
						+ uno + " order by apdate desc limit 1")) {
			if (rs.next()) {
				int cno = rs.getInt(1);
				var top6 = res("select * from brand join job using(bno) join category using(cno) where cno = " + cno
						+ " order by rand() limit 6;");
				while (top6.next()) {
					String txt = "[주 " + top6.getInt("jday") + "일, " + top6.getInt("jtime") + "시간, "
							+ (top6.getBoolean("jwork") ? "계약직]" : "정규직]");
					JobPanel pp = new JobPanel(top6.getString("jname"), txt, top6.getInt("jmoney"));
					int jno = top6.getInt("jno");
					pp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							showPage(new C_알바정보(jno), "C_알바정보");
						}
					});
					panel_5.add(pp);
				}
			} else {
				var top6 = res(
						"select *, count(*) cnt from job join apply using(jno) group by bno order by cnt desc limit 6;");
				while (top6.next()) {
					String txt = "[주 " + top6.getInt("jday") + "일, " + top6.getInt("jtime") + "시간, "
							+ (top6.getBoolean("jwork") ? "계약직]" : "정규직]");
					JobPanel pp = new JobPanel(top6.getString("jname"), txt, top6.getInt("jmoney"));
					int jno = top6.getInt("jno");
					pp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							showPage(new C_알바정보(jno), "C_알바정보");
						}
					});
					panel_5.add(pp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_5.revalidate();
		panel_5.repaint();
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
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new D_채용(), "D_채용");
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new I_마이페이지(), "I_마이페이지");
		}
	}
}
