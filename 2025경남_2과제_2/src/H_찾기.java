import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class H_찾기 extends BF {
	public JPanel panel;
	public JLabel label;
	public JPanel panel_1;
	public JLabel label_1;

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
		setBounds(100, 100, 837, 589);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Image img = getIcon("지도.png", 600, 550).getImage();
				
				if (click != null) {
					double sx = click.x;
					double sy = click.y;
					double dx = sx * scale;
					double dy = sy * scale;
					g2.translate(-dx + getWidth()/2, -dy + getHeight()/2);
					g2.scale(scale, scale);
				}
				g2.drawImage(img, 0, 0, null);
				g2.setColor(Color.red);
				for (var p : selP) {
					g2.fillOval(p.x-4, p.y-4, 8, 8);
				}
				af = g2.getTransform();
			}
		};
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.addMouseListener(new PanelMouseListener());
		panel.setBounds(0, 0, 600, 550);
		getContentPane().add(panel);
		
		label = new JLabel("\uC9C1\uC885 \uCE74\uD14C\uACE0\uB9AC");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(611, 10, 196, 25);
		getContentPane().add(label);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(611, 33, 196, 357);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(11, 1, 0, 0));
		
		label_1 = new MainLogo(192,121);
		label_1.setBounds(615, 429, 192, 121);
		getContentPane().add(label_1);
		
		
		addCate();
		selectCate(jls.get(0));
	}

	private void addCate() {
		createCate("전체", 0);
		try (var rs = res("select * from category")) {
			while(rs.next()) {
				createCate(rs.getString("cname"), rs.getInt("cno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	List<JLabel> jls = new ArrayList<JLabel>();
	List<List<Point>> plist=  new ArrayList<List<Point>>();
	List<List<String>> slist=  new ArrayList<List<String>>();
	private void createCate(String string, int cno) {
		JLabel jl = new JLabel(string);
		jl.setOpaque(true);
		jl.setBackground(Color.white);
		jl.setBorder(new LineBorder(Color.black));
		jl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectCate(jl);
			}

		});
		String sql = cno==0?"":"where cno = "+cno;
		try (var rs = res("select * from brand "+sql)) {
			List<Point> list = new ArrayList<Point>();
			List<String> list2 = new ArrayList<>();
			while(rs.next()) {
				list.add(new Point(rs.getInt("bxx"),rs.getInt("byy")));
				list2.add(rs.getString("bname"));
			}
			plist.add(list);
			slist.add(list2);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panel_1.add(jl);
		jls.add(jl);
	}
	List<Point> selP =new ArrayList<Point>();
	List<String> selS =new ArrayList<>();
	double scale = 1;
	Point click;
	private void selectCate(JLabel jl) {
		int idx = jls.indexOf(jl);
		if(jl.getBackground()==Color.red) {
			jl.setBackground(Color.white);
			jl.setForeground(Color.black);
			for (var p : plist.get(idx)) {
				selP.remove(p);
			}
			for (var s : slist.get(idx)) {
				selS.remove(s);
			}
		}
		else {
			jl.setBackground(Color.red);
			jl.setForeground(Color.white);
			selP.addAll(plist.get(idx));
			selS.addAll(slist.get(idx));
		}
		scale = 0;
		click = null;
		panel.repaint();
	}

	private class PanelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var p = selP.stream().filter(x->x.distance(e.getPoint())<=4).findAny().orElse(null);
			if(p!=null) {
				click = p;
				zoom();
			}
		}

		private void zoom() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (scale<8) {
						scale+=0.5;
						repaint();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	AffineTransform af;
	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			Point2D cp= e.getPoint();
			if(click!=null) {
				try {
					cp = af.inverseTransform(cp, null);
				} catch (NoninvertibleTransformException e1) {
					e1.printStackTrace();
				}
			}
			
			var p = selP.stream().filter(x->x.distance(e.getPoint())<=4).findAny().orElse(null);
			if(p!=null) {
				int idx = selP.indexOf(p);
				panel.setToolTipText(selS.get(idx));
			}
			else {
				panel.setToolTipText(null);
			}
		}
	}
}
