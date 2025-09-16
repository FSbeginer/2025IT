import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseMotionAdapter;

public class H_찾기 extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	AffineTransform af;
	double zoom = 1.0;
	Point2D cp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					H_찾기 frame = new H_찾기();
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
	public H_찾기() {
		setTitle("\uCC3E\uAE30");
		setBounds(100, 100, 847, 589);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				if (zoom != 1.0) {
					g2.translate(-cp.getX() * zoom + getWidth() / 2, -cp.getY() * zoom + getHeight() / 2);
					g2.scale(zoom, zoom);
				}
				
				Image img = getIcon("지도.png", 600, 550).getImage();
				g.drawImage(img, 0, 0, null);
				g2.setColor(Color.red);
				for (var p : ps) {
					g2.fillOval(p.x - 4, p.y - 4, 8, 8);
				}

				af = g2.getTransform();
			}
		};
		panel.addMouseListener(new PanelMouseListener());
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.setBounds(0, 0, 600, 550);
		getContentPane().add(panel);

		panel_1 = new JPanel();
		panel_1.setBounds(612, 37, 202, 346);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(11, 0, 0, 0));

		label = new JLabel("\uC9C1\uC885 \uCE74\uD14C\uACE0\uB9AC");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label.setBounds(612, 12, 202, 30);
		getContentPane().add(label);

		label_1 = new MainLogo(200, 110);
		label_1.setBounds(612, 423, 207, 117);
		getContentPane().add(label_1);

		addCate();
		selectCate(0);
	}

	List<JLabel> cates = new ArrayList<JLabel>();
	List<Point> ps = new ArrayList<Point>();
	List<Integer> bs = new ArrayList<>();
	List<String> names = new ArrayList<>();
	List<List<Point>> allPoints = new ArrayList<List<Point>>();
	List<List<Integer>> allbnos = new ArrayList<List<Integer>>();
	List<List<String>> allname = new ArrayList<List<String>>();

	private void addCate() {
		createCate("전체", 0);
		try (var rs = res("select * from category")) {
			while (rs.next())
				createCate(rs.getString("cname"), rs.getInt("cno"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createCate(String name, int idx) {
		JLabel jl = new JLabel(name);
		jl.setBackground(Color.white);
		jl.setOpaque(true);
		jl.setBorder(new LineBorder(Color.black));
		jl.setFont(new Font("맑은 고딕", 0, 12));
		jl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				zoom = 1;
				selectCate(idx);
			}

		});
		String where = idx == 0 ? "" : "where cno =" + idx;
		try (var rs = res("select * from brand " + where)) {
			List<Point> list = new ArrayList<Point>();
			List<Integer> list2 = new ArrayList<Integer>();
			List<String> list3 = new ArrayList<>();
			while (rs.next()) {
				Point p = new Point(rs.getInt("bxx"), rs.getInt("byy"));
				list.add(p);
				list2.add(rs.getInt("bno"));
				list3.add(rs.getString("bname"));
			}
			allPoints.add(list);
			allbnos.add(list2);
			allname.add(list3);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		cates.add(jl);
		panel_1.add(jl);
	}

	private void selectCate(int idx) {
		var jl = cates.get(idx);
		if (jl.getBackground().equals(Color.red)) {
			jl.setBackground(Color.white);
			jl.setForeground(Color.black);
			for (var p : allPoints.get(idx)) {
				ps.remove(p);
			}
			for (var b : allbnos.get(idx)) {
				bs.remove(b);
			}
			for (var n : allname.get(idx)) {
				names.remove(n);
			}
		} else {
			jl.setBackground(Color.red);
			jl.setForeground(Color.white);
			for (var p : allPoints.get(idx)) {
				ps.add(p);
			}
			for (var b : allbnos.get(idx)) {
				bs.add(b);
			}
			for (var n : allname.get(idx)) {
				names.add(n);
			}
		}
		panel.repaint();
	}

	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			var imsi = e.getPoint();
			Point2D mp;
			try {
				repaint();
				mp = af.inverseTransform(imsi, null);
				var p = ps.stream().filter(x -> x.distance(mp) <= 5).findAny().orElse(null);
				if (p != null) {
					int idx = ps.indexOf(p);
					var n = names.get(idx);
					panel.setToolTipText(n);
				} else {
					panel.setToolTipText(null);
				}
			} catch (NoninvertibleTransformException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class PanelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var imsi = e.getPoint();
			Point2D mp;
			try {
				repaint();
				mp = af.inverseTransform(imsi, null);
				var p = ps.stream().filter(x -> x.distance(mp) <= 5).findAny().orElse(null);
				if (p != null) {
					cp = p;
					int idx = ps.indexOf(p);

					if (zoom == 1.0) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								while (zoom < 8) {
									zoom += 0.5;
									panel.repaint();
									try {
										Thread.sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}).start();
					}
					if (e.getClickCount() == 2) {
						showPage(new G_브랜드정보(bs.get(idx)), "G_브랜드정보");
					}
				}
			} catch (NoninvertibleTransformException e1) {
				e1.printStackTrace();
			}
		}
	}
}
