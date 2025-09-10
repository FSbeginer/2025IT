
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorChooserUI;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class L_선생님메인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JButton button;
	Timer timer;
	String[] name = new String[5];
	int[] cnt = new int[5];
	int selIdx = -1;
	int prev = -1;
	Arc2D[] arcs = new Arc2D.Double[5];
	Color[] c = { Color.red, Color.yellow, Color.green, Color.blue, Color.magenta };
	int spin = 0, dotsize = 5, size = 100;
	Point2D cp;
	double zoom = 1.0;
	List<List<Point>> points;
	List<List<Point>> targetPoints = new ArrayList<List<Point>>();
	List<List<Color>> targetColors = new ArrayList<List<Color>>();
	AffineTransform af;
	public JButton button_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L_선생님메인 frame = new L_선생님메인();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public L_선생님메인() {
		setTitle("선생님 메인");
		setBounds(100, 100, 827, 702);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel(getIcon("icon/logo.png", 40, 40));
		label.setBounds(12, 10, 57, 42);
		getContentPane().add(label);

		label_1 = new JLabel("Skills Qualification Associaion");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(81, 10, 290, 42);
		getContentPane().add(label_1);

		label_2 = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage img = getDot();

				g.drawImage(img, 0, 0, null);
			}

//			@Override
//			public boolean contains(int x, int y) {
//				if (selIdx != -1)
//					return arcs[selIdx].contains(x, y);
//				return super.contains(x, y);
//			}
		};
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.addMouseMotionListener(new Label_2MouseMotionListener());
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(12, 62, 787, 551);
		getContentPane().add(label_2);

		button = new JButton("문의답변하기");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(Color.BLUE);
		button.setForeground(Color.WHITE);
		button.setBounds(265, 622, 284, 31);
		getContentPane().add(button);

		button_1 = new JButton("축소");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(519, 29, 97, 23);
		getContentPane().add(button_1);

		load();
		settimer();
	}

	private BufferedImage getDot() {
		BufferedImage img = getbuf();

		BufferedImage bufferedImage = new BufferedImage(label_2.getWidth(), label_2.getHeight(), 2);

		var g2 = bufferedImage.createGraphics();
		points = new ArrayList<List<Point>>();
		for (int i = 0; i < arcs.length; i++) {
			points.add(new ArrayList());
		}

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				int idx1 = i, idx2 = j;
				var selC = Arrays.stream(L_선생님메인.this.c).filter(x -> x.getRGB() == img.getRGB(idx1, idx2)).findAny()
						.orElse(null);
				if (selC != null) {
					int idx = Arrays.asList(c).indexOf(selC);
					points.get(idx).add(new Point(i * dotsize, j * dotsize));
				}
			}
		}

		if (zoom != 1.0) {
			g2.translate(-cp.getX() * zoom + label_2.getWidth() / 2, -cp.getY() * zoom + label_2.getHeight() / 2);
			g2.scale(zoom, zoom);
		}

		for (int i = 0; i < arcs.length; i++) {
			g2.setColor(c[i]);
			for (var p : points.get(i)) {
				if (targetPoints.get(i).contains(p)) {
					g2.setColor(targetColors.get(i).get(targetPoints.get(i).indexOf(p)));
				} else {
					g2.setColor(c[i]);
				}
				g2.fillOval(p.x, p.y, dotsize, dotsize);
			}
		}

		af = g2.getTransform();

		return bufferedImage;
	}

	private BufferedImage getbuf() {
		BufferedImage bi = new BufferedImage(label_2.getWidth(), label_2.getHeight(), 2);
		var g = bi.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int ang = 0;
		g.rotate(Math.toRadians(spin), label_2.getWidth() / (dotsize * 2), label_2.getHeight() / (dotsize * 2));
		int x = label_2.getWidth() / (dotsize * 2) - size / 2;
		int y = label_2.getHeight() / (dotsize * 2) - size / 2;

		for (int i = 0; i < arcs.length; i++) {
			int deg = (int) Math.round(((double) cnt[i] / Arrays.stream(cnt).sum() * 360));
			if (spin == 360 && selIdx == i) {
				arcs[i] = new Arc2D.Double(x + Math.cos(Math.toRadians(ang + deg / 2)) * 15,
						y - Math.sin(Math.toRadians(ang + deg / 2)) * 15, size, size, ang, deg, Arc2D.PIE);
			} else
				arcs[i] = new Arc2D.Double(x, y, size, size, ang, deg, Arc2D.PIE);
			g.setColor(c[i]);
			g.fill(arcs[i]);
			ang += deg;
		}

		return bi;
	}

	private void settimer() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (prev != -1) {
					selIdx = prev;
//					label_2.setToolTipText(name[selIdx] + ": "
//							+ String.format("%.1f%%", cnt[selIdx] * 1.0 / Arrays.stream(cnt).sum() * 100));
					new Thread(new Runnable() {
						@Override
						public void run() {
							while (spin < 360) {
								spin++;
								label_2.repaint();
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}).start();
					repaint();
				}
			}
		});
	}

	private void load() {
		try {
			var rs = res(
					"select concat(left(tname,1),'선생') name ,count(*) cnt from course_registration join certi using(cno) join teacher using(tno) group by tno order by cnt desc;");
			int i = 0;
			while (rs.next()) {
				name[i] = rs.getString(1);
				cnt[i] = rs.getInt(2);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			targetPoints.add(new ArrayList<>());
			targetColors.add(new ArrayList<>());
		}
	}

	private class Label_2MouseMotionListener extends MouseMotionAdapter {

		@Override
		public void mouseMoved(MouseEvent e) {
			int idx = -1;
			var img = getDot();
			for (int i = 0; i < 5; i++) {
				if (img.getRGB(e.getX(), e.getY()) == c[i].getRGB()) {
					idx = i;
					break;
				}
			}

			if (idx != -1 && idx != prev) {
				timer.stop();
				timer.start();
			}

			prev = idx;
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			showPage(new 후기작성폼(1), "후기작성폼");
		}
	}

	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var img = getDot();
			var selc = Arrays.stream(c).filter(x -> x.getRGB() == img.getRGB(e.getX(), e.getY())).findAny()
					.orElse(null);
			if (selc != null) {
				int imsi = Arrays.asList(c).indexOf(selc);
				if(imsi!=selIdx) return;
				try {
					repaint();
					cp = af.inverseTransform(e.getPoint(), null);

					if (zoom != 8.0) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								for (int i = 0; i < 16; i++) {
									zoom = Math.min(zoom + 0.5, 8);
									label_2.repaint();
									try {
										Thread.sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}

								}
							}

						}).start();
					} else {
						var jcc = new JColorChooser();
						var result = jcc.showDialog(null, "색상 선택", Color.gray);
						int idx = Arrays.asList(c).indexOf(selc);
						var target = points.get(idx).stream().filter(p -> p.distance(cp) <= 5).findAny()
								.orElse(null);
						targetPoints.get(idx).add(target);
						targetColors.get(idx).add(result);
						repaint();
					}
				} catch (NoninvertibleTransformException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			zoom = 1.0;
			label_2.repaint();
		}
	}
}
