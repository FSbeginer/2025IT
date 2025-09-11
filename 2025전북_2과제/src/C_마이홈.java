import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.awt.event.MouseWheelEvent;

public class C_마이홈 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JPanel panel;
	public JLabel label_4;
	public JLabel label_5;
	BufferedImage img = new BufferedImage(500, 250, 2);

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
		setBounds(100, 100, 544, 537);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("\uAE40\uC9C0\uBBFC\uB2D8");
		label.setFont(new Font("굴림", Font.BOLD, 15));
		label.setBounds(12, 10, 156, 35);
		getContentPane().add(label);

		label_1 = new JLabel("\uC9C4\uB8CC \uAE30\uB85D");
		label_1.setBounds(12, 55, 88, 35);
		getContentPane().add(label_1);

		label_2 = new JLabel("\uAC80\uC0AC \uACB0\uACFC");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("굴림", Font.PLAIN, 12));
		label_2.setBounds(386, 10, 57, 35);
		getContentPane().add(label_2);

		label_3 = new JLabel("\uC608\uC57D \uB0B4\uC5ED");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("굴림", Font.PLAIN, 12));
		label_3.setBounds(455, 10, 57, 35);
		getContentPane().add(label_3);

		panel = new JPanel();
		panel.addMouseWheelListener(new PanelMouseWheelListener());
		panel.setBounds(12, 100, 500, 86);
		getContentPane().add(panel);
		panel.setLayout(null);

		label_4 = new JLabel("\uADFC\uCC98 \uBCD1\uC6D0");
		label_4.setBounds(12, 206, 64, 21);
		getContentPane().add(label_4);

		label_5 = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				var map = (BF.getIcon("map.png", 500, 250).getImage());
				g2.drawImage(map, 0, 0, null);
				g2.drawString("현재위치", myPoint.x - 20, myPoint.y - 20);
				g2.setColor(new Color(120,150,255));
				g2.fillOval(myPoint.x - 4, myPoint.y - 4, 8, 8);
				g2.drawImage(img, 0, 0, null);
			}
		};
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setBounds(12, 237, 500, 250);
		getContentPane().add(label_5);

		addPanel();
		getData();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int ang = 0;
				while (ang<360) {
					img = new BufferedImage(500, 250, 2);
					Graphics2D g2 = img.createGraphics();
					g2.setColor(new Color(blue.getRed(), blue.getGreen(), blue.getBlue(), 70));
					g2.fillOval(myPoint.x - 75, myPoint.y - 75, 150, 150);
					g2.setColor(new Color(120, 150, 250, 120));
					g2.fillArc(myPoint.x-75, myPoint.y-75, 150, 150, 270, ++ang);
					g2.setColor(Color.black);
					for (Point point : points) {
						g2.fillOval(point.x-4, point.y-4, 8, 8);
					}
					repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	Point myPoint;
	List<Point> points = new ArrayList<Point>();
	List<Integer> hnos = new ArrayList<Integer>();
	private void getData() {
		try (var rs = res("select x,y,name from user where uno = " + uno)) {
			rs.next();
			myPoint = new Point(rs.getInt(1), rs.getInt(2) / 2);
			label.setText(rs.getString("name")+"님");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select x,y, hno from hospital")) {
			while (rs.next()) {
				var p =new Point(rs.getInt(1), rs.getInt(2) / 2);
				if(myPoint.distance(p)<=75) {
					points.add(p);
					hnos.add(rs.getInt(3));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	int cx;
	List<JPanel> pps = new ArrayList<JPanel>();

	private void addPanel() {
		try (var rs = res(
				"select hno, d.name dname, h.name hname, r.date from reservation r join doctor d using(dno) join hospital h using(hno) where uno = "
						+ uno)) {
			int w = panel.getWidth() / 3 + 50;
			int i = 0;
			while (rs.next()) {
				HomePanel pp = new HomePanel(getIcon("hospital/" + rs.getInt(1) + ".png", 80, 80), rs.getString(3),
						rs.getString(2), rs.getString(4));
				pp.setSize(w, panel.getHeight());
				pp.setLocation((w + 5) * i, 0);

				int hno = rs.getInt(1);
				pp.label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new E_병원정보(hno), "E_병원정보");
					}
				});
				pp.addMouseListener(new MouseAdapter() {
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
								|| pps.get(pps.size() - 1).getX() + dx < panel.getWidth() - pps.get(0).getWidth())
							return;
						for (JPanel pp : pps) {
							pp.setLocation(pp.getX() + dx, 0);
						}
					}
				});
				panel.add(pp);
				pps.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class PanelMouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation() > 0 ? -10 : 10;
			if (pps.size() == 0 || pps.get(0).getX() + dx > 0
					|| pps.get(pps.size() - 1).getX() + dx < panel.getWidth() - pps.get(0).getWidth())
				return;
			for (JPanel pp : pps) {
				pp.setLocation(pp.getX() + dx, 0);
			}
		}
	}

	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new K_검사결과(), "K_검사결과");
		}
	}

	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new L_예약내역(), "L_예약내역");
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var p = points.stream().filter(x->e.getPoint().distance(x)<=4).findFirst().orElse(null);
			if(p!=null) {
				int idx = points.indexOf(p);
				showPage(new E_병원정보(idx),"E_병원정보");
			}
		}
	}
}
