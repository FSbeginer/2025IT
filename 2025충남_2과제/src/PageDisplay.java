import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PageDisplay extends BP {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JLabel label;
	public JPanel panel_4;
	List<Point> plist = new ArrayList<Point>();
	List<JPanel> palist = new ArrayList<JPanel>();
	private Image img;

	public PageDisplay() {
		setName("Àü½Ã");
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setPreferredSize(new Dimension(1, 170));
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(108, 10, 677, 145);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		label = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, null);
				for (Point p : plist) {
					g.drawImage(getCircleImage().getImage(), p.x, p.y, null);
				}
			}
		};
		label.addMouseListener(new LabelMouseListener());
		label.setBounds(200, 31, 680, 280);
		panel_2.add(label);

		panel_4 = new JPanel();
		panel_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		panel_4.setBackground(Color.GRAY);
		panel_4.setBounds(12, 31, 170, 270);
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		addButton();
	}

	private void addButton() {
		ButtonGroup bg = new ButtonGroup();
		try (var rs = res("select name, sno from science")) {
			while (rs.next()) {
				JToggleButton button = BF.createToggle(rs.getString(1));
				int sno = rs.getInt(2);
				button.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						addInfo(sno);
					}
				});
				button.setPreferredSize(new Dimension(130, 30));
				panel.add(button);
				bg.add(button);
			}
			bg.getElements().nextElement().setSelected(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ImageIcon getCircleImage() {
		BufferedImage bi = new BufferedImage(30, 30, 2);

		var g = bi.createGraphics();
		g.setColor(Color.green);
		g.fillOval(0, 0, 30, 30);
		g.setColor(Color.white);
		g.fillOval(2, 2, 22, 22);
		return new ImageIcon(bi);
	}

	private void addInfo(int sno) {
		panel_4.removeAll();
		panel_3.removeAll();
		plist.clear();
		palist.clear();
		try (var rs = res("select * from(select *, curdate() between start_date and end_date r, row_number() over(partition by pno order by (curdate() between start_date and end_date) desc) from location l join program p using(pno) where sno = "+sno+") sub group by pno;")) {
			int i = 0;
			while (rs.next()) {
				JLabel jl = new JLabel(rs.getString("name"));
				jl.setIcon(getCircleImage());
				jl.setForeground(Color.white);
				panel_4.add(jl);
				
				plist.add(new Point(rs.getInt("x"), rs.getInt("y")));
				
				PanelDisplay pp = new PanelDisplay(getIcon(rs.getBytes("p_img"), 200, 150), rs.getString("name"),
						rs.getString("explanation"));
				pp.button.setVisible(rs.getBoolean("r"));
				int pno = rs.getInt("pno"), lno = rs.getInt("lno");
				pp.button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						var main = ((MainFrame)SwingUtilities.getWindowAncestor(PageDisplay.this));
						prevPage.add(new Object[] {PageDisplay.this, main.label_1});
						main.label_2.setForeground(BF.blue);
						main.showPage(new PageReservation(sno, pno, lno));
					}
				});
				pp.setSize(panel_3.getSize());
				pp.setLocation(pp.getWidth() * i, 0);
				panel_3.add(pp);
				palist.add(pp);
				i++;
			}
			img = getIcon("³»ºÎ/" + sno + ".png", 680, 280).getImage();
			label.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_3.repaint();
		panel_3.revalidate();
		panel_4.repaint();
		panel_4.revalidate();
	}

	private class LabelMouseListener extends MouseAdapter {
		private Thread th;

		@Override
		public void mouseClicked(MouseEvent e) {
			var p = plist.stream().filter(x -> x.distance(e.getPoint()) <= 30).findFirst().orElse(null);
			if (p != null) {
				int idx = plist.indexOf(p);
				int dir = palist.get(idx).getX() < 0 ? 1 : palist.get(idx).getX() == 0 ? 0 : -1;
				
				if (th != null&&th.isAlive())
					th.interrupt();
				th = new Thread(new Runnable() {
					public void run() {
						while (palist.get(idx).getX() != 0) {
							try {
								for (var pa : palist) {
									pa.setLocation(pa.getX() + dir, 0);
								}
								Thread.sleep(1);
							} catch (InterruptedException e1) {
								break;
							}
						}
					}
				});
				th.start();
			}
		}
	}
}
