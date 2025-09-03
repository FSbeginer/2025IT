import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.awt.event.MouseWheelEvent;

public class C_마이홈 extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C_마이홈 frame = new C_마이홈();
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
	public C_마이홈() {
		setTitle("\uB9C8\uC774\uD648");
		setBounds(100, 100, 540, 521);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image map = getIcon("map.png", 500, 250).getImage();
				g.drawImage(map, 0, 0, null);

				g.drawString("현재위치", mypoint.x - 25, mypoint.y - 6);
				g.setColor(Color.blue.darker());
				g.fillOval(mypoint.x - 4, mypoint.y - 4, 8, 8);
				if (flag) {
					Image img = getBuff();
					g.drawImage(img, 0, 0, null);
				}
				System.out.println(1);
			}

		};
		panel.addMouseListener(new PanelMouseListener());
		panel.setBounds(12, 216, 500, 250);
		getContentPane().add(panel);

		panel_1 = new JPanel();
		panel_1.addMouseWheelListener(new Panel_1MouseWheelListener());
		panel_1.setBounds(12, 85, 500, 96);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		label = new JLabel("\uADFC\uCC98 \uBCD1\uC6D0");
		label.setBounds(12, 191, 57, 15);
		getContentPane().add(label);

		label_1 = new JLabel("\uC9C4\uB8CC \uAE30\uB85D");
		label_1.setBounds(12, 60, 57, 15);
		getContentPane().add(label_1);

		label_2 = new JLabel("\uAE40\uC9C0\uBBFC\uB2D8");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_2.setBounds(12, 10, 100, 33);
		getContentPane().add(label_2);

		label_3 = new JLabel("\uAC80\uC0AC \uACB0\uACFC");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setBounds(379, 10, 57, 15);
		getContentPane().add(label_3);

		label_4 = new JLabel("\uC608\uC57D \uB0B4\uC5ED");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setBounds(443, 10, 57, 15);
		getContentPane().add(label_4);

		load();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				flag = true;
				deg = 0;
				while (deg++ < 360) {
					panel.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	boolean flag;
	private int cx;
	private int deg;

	private Image getBuff() {
		BufferedImage bi = new BufferedImage(500, 250, 2);
		var g = bi.createGraphics();
		int r = 75;
		int bx = mypoint.x - r, by = mypoint.y - r;
		g.setColor(new Color(120, 120, 255, 30).brighter());
		g.fillOval(bx, by, r + r, r + r);
		int ang = 270;
		g.setColor(new Color(50,50,255,70).darker());
		g.fillArc(bx, by, r + r, r + r, ang, deg);
		ang += deg;
		g.setColor(Color.black);
		for (Point point : points) {
			g.fillOval(point.x - 4, point.y - 4, 8, 8);
		}
		return bi;
	}

	List<JPanel> pps = new ArrayList<JPanel>();

	private void load() {
		label_2.setText(uname + "님");
		try (var rs = res(
				"select hno, h.name hname, d.name dname, date  from record join doctor d using(dno) join hospital h using(hno) where uno ="
						+ uno)) {
			int w = (panel_1.getWidth() - 50) / 2;
			int h = panel_1.getHeight();
			int i = 0;
			while (rs.next()) {
				C_패널 pp = new C_패널(getIcon("hospital/" + rs.getInt(1) + ".png", w / 2 - 10, h - 10), rs.getString(2),
						rs.getString(3), rs.getString(4));
				pp.setSize(w, h);
				pp.setLocation((w + 10) * i, 0);
				int hno = rs.getInt(1);
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
				});
				pp.label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new E_병원정보(hno), "E_병원정보");
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
						if (pps.size() == 0 || pps.get(0).getX() + dx > 0
								|| pps.get(pps.size() - 1).getX() + dx < panel_1.getWidth() - w - 10)
							return;
						for (var b_패널 : pps) {
							b_패널.setLocation(b_패널.getX() + dx, 0);
						}
					}
				});
				pps.add(pp);
				panel_1.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from user where uno = " + uno)) {
			rs.next();
			mypoint = new Point(rs.getInt("x"), rs.getInt("y") / 2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from hospital")) {
			while (rs.next()) {
				points.add(new Point(rs.getInt("x"), rs.getInt("y") / 2));
				hnos.add(rs.getInt(1));
			}
			points = points.stream().filter(x -> x.distance(mypoint) <= 75).collect(Collectors.toList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Point mypoint;
	List<Point> points = new ArrayList<Point>();
	List<Integer> hnos = new ArrayList<>();

	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new K_검사결과(), "K_검사결과");
		}
	}

	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new L_예약내역(), "L_예약내역");
		}
	}

	private class Panel_1MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation() > 0 ? -10 : 10;
			System.out.println(e.getWheelRotation());
			if (pps.size() == 0 || pps.get(0).getX() + dx > 0
					|| pps.get(pps.size() - 1).getX() + dx < panel_1.getWidth() - pps.get(0).getWidth() - 10)
				return;
			for (var b_패널 : pps) {
				b_패널.setLocation(b_패널.getX() + dx, 0);
			}
		}
	}
	private class PanelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var p =points.stream().filter(x->x.distance(e.getPoint())<=4).findAny().orElse(null);
			if(p!=null) {
				int idx = points.indexOf(p);
				showPage(new E_병원정보(hnos.get(idx)),"E_병원정보");
			}
		}
	}
}
