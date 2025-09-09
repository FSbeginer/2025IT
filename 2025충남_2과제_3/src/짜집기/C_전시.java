package 짜집기;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class C_전시 extends BP {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JPanel panel_2;

	/**
	 * Create the panel.
	 */
	public C_전시() {
		setLayout(null);

		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GRAY));
		panel.setBounds(0, 0, 998, 46);
		add(panel);

		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(10, 56, 191, 261);
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		label = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int i = 0;
				for (Point point : plist) {
					if (selIdx == i && act) {
						g.drawImage(geticon().getImage(), point.x - 20, point.y - 20, point.x + 20, point.y + 20, 0, 0,
								30, 30, null);
					} else {
						g.drawImage(geticon().getImage(), point.x - 15, point.y - 15, null);
					}
					i++;
				}
				repaint();
			}
		};
		label.addMouseListener(new LabelMouseListener());
		label.addMouseMotionListener(new LabelMouseMotionListener());
		label.setBounds(285, 56, 680, 280);
		add(label);

		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.GRAY));
		panel_2.setBounds(129, 347, 737, 151);
		add(panel_2);
		panel_2.setLayout(null);

		addScience();
		bg.getElements().nextElement().setSelected(true);
		load(1);
		new javax.swing.Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				act = !act;
			}
		}).start();
	}

	boolean act = true;

	private void addScience() {
		try (var rs = res("select * from science;")) {
			while (rs.next()) {
				creatButton(rs.getString("name"), rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	ButtonGroup bg = new ButtonGroup();
	List<Point> plist = new ArrayList<Point>();

	private void creatButton(String txt, int sno) {
		JToggleButton btn = new JToggleButton(txt);
		btn.setContentAreaFilled(false);
		btn.setOpaque(true);
		btn.setFocusPainted(false);
		btn.setBorder(new LineBorder(BF.blue));
		btn.setBackground(Color.white);
		btn.setForeground(BF.blue);
		btn.setPreferredSize(new Dimension(140, 30));
		btn.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (btn.isSelected()) {
					btn.setBackground(BF.blue);
					btn.setForeground(Color.white);
				} else {
					btn.setBackground(Color.white);
					btn.setForeground(BF.blue);
				}
			}
		});
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				load(sno);
			}

		});
		bg.add(btn);
		panel.add(btn);
	}

	List<C_전시패널> pps = new ArrayList<C_전시패널>();
	List<JLabel> jls = new ArrayList<JLabel>();

	private void load(int sno) {
		pps.clear();
		panel_1.removeAll();
		panel_2.removeAll();
		plist.clear();
		label.setIcon(getIcon("내부/" + sno + ".png", 680, 280));
		try (var rs = res(
				"select * from (select *, curdate() between start_date and end_date r, row_number() over(partition by pno order by curdate() between start_date and end_date desc) and end_date from location join program using(pno) where sno = "
						+ sno + ") sub group by pno;")) {
			while (rs.next()) {
				JLabel jl = new JLabel(rs.getString("name"));
				jl.setForeground(Color.white);
				jl.setIcon(geticon());
				int pno = rs.getInt("pno"), lno = rs.getInt("lno");
				jls.add(jl);
				panel_1.add(jl);

				C_전시패널 pp = new C_전시패널(getIcon(rs.getBytes("p_img"), 203, 129), rs.getString("name"),
						rs.getString("explanation"));
				pp.button.setVisible(rs.getBoolean("r"));
				pp.button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((MainFrame) SwingUtilities.getWindowAncestor(pp)).showPage(new D_예매(sno, lno, pno), "예매");
					}
				});
				pp.setLocation(pps.size() * pp.getWidth(), 0);
				pps.add(pp);
				panel_2.add(pp);
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								int dir = pp.getX() > 0 ? -1 : 1;
								while (pp.getX() != 0) {
									for (C_전시패널 c_전시패널 : pps) {
										c_전시패널.setLocation(c_전시패널.getX() + dir, 0);
									}
									try {
										Thread.sleep(1);
									} catch (InterruptedException e) {
										break;
									}
								}
							}
						}).start();
					}

					int idx = jls.size()-1;
					@Override
					public void mouseEntered(MouseEvent e) {
						selIdx = idx;
						jl.setText("<html><u>" + jl.getText().replaceAll("<html><u>", ""));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						jl.setText(jl.getText().replaceAll("<html><u>", ""));
					}
				});
				plist.add(new Point(rs.getInt("x"), rs.getInt("y")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_1.revalidate();
		panel_1.repaint();
		panel_2.revalidate();
		panel_2.repaint();
	}

	int selIdx = -1;

	private ImageIcon geticon() {
		BufferedImage bi = new BufferedImage(30, 30, 2);
		var g = bi.createGraphics();
		g.setColor(Color.green);
		g.fillOval(0, 0, 30, 30);
		g.setColor(Color.white);
		g.fillOval(4, 4, 22, 22);
		return new ImageIcon(bi);
	}

	private class LabelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			var p = plist.stream().filter(x -> x.distance(e.getPoint()) <= 15).findAny().orElse(null);
			if (p != null) {
				int idx = plist.indexOf(p);
				selIdx = idx;
				jls.get(idx).setText("<html><u>" + jls.get(idx).getText().replaceAll("<html><u>", ""));
			} else {
				selIdx = -1;
				for (JLabel jl : jls) {
					jl.setText(jl.getText().replaceAll("<html><u>", ""));
				}
			}
		}
	}

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var p = plist.stream().filter(x -> x.distance(e.getPoint()) <= 15).findAny().orElse(null);
			if (p != null) {
				int idx = plist.indexOf(p);
				new Thread(new Runnable() {

					@Override
					public void run() {
						int dir = pps.get(idx).getX() > 0 ? -1 : 1;
						while (pps.get(idx).getX() != 0) {
							for (C_전시패널 c_전시패널 : pps) {
								c_전시패널.setLocation(c_전시패널.getX() + dir, 0);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								break;
							}
						}
					}
				}).start();
			}
		}
	}
}
