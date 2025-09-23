import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		addWindowListener(new ThisWindowListener());
		setTitle("\uCC3E\uAE30");
		setBounds(100, 100, 814, 593);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage img = getBuf();
				g.drawImage(img, 0, 0, null);
			}

		};
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.addMouseListener(new PanelMouseListener());
		panel.setBounds(0, 0, 600, 550);
		getContentPane().add(panel);
		
		label = new JLabel("\uC9C1\uC885 \uCE74\uD14C\uACE0\uB9AC");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label.setBounds(612, 10, 174, 28);
		getContentPane().add(label);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(612, 37, 174, 357);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(11, 1, 0, 0));
		
		label_1 = new MainLogo(179,86);
		label_1.setBounds(607, 430, 179, 86);
		getContentPane().add(label_1);

		addCate();
		selectCate(0);
	}
	Point cp;
	double scale = 1.0;
	AffineTransform af;
	private BufferedImage getBuf() {
		Image img = getIcon("지도.png", 600,550).getImage();
		BufferedImage bi = new BufferedImage(600, 550, 2);
		var g=  bi.createGraphics();
		
		if(cp!=null) {
			g.translate(-cp.x*scale+300, -cp.y*scale +275);
			g.scale(scale, scale);
		}
		
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.red);
		for (var p : selP) {
			g.fillOval(p.x-4,p.y-4, 8, 8);
		}
		
		af = g.getTransform();
		return bi;
	}

	private void addCate() {
		createCAte("전체", 0);
		try (var rs = res("select * from category")) {
			while(rs.next()) {
				createCAte(rs.getString(2),rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	List<List<Point>> plist = new ArrayList<List<Point>>();
	List<List<String>> names = new ArrayList<>();
	List<Point> selP = new ArrayList<>();
	List<JLabel> jls = new ArrayList<JLabel>();
	
	private void createCAte(String txt, int idx) {
		JLabel jl = new JLabel(txt);
		jl.setBorder(new LineBorder(Color.black));
		jl.setOpaque(true);
		jl.setBackground(Color.white);
		jl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectCate(idx);
				scale = 1;
				cp = null;
			}

		});
		String path = idx==0?"" :"where cno = "+idx;
		try (var rs = res("select * from brand "+path)) {
			List<Point> ps = new ArrayList<Point>();
			List<String> nn = new ArrayList<>();
			while(rs.next()) {
				ps.add(new Point(rs.getInt("bxx"), rs.getInt("byy")));
				nn.add(rs.getString("bname"));
			}
			names.add(nn);
			plist.add(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_1.add(jl);
		jls.add(jl);
	}
	List<String> selname = new ArrayList<>();
	
	private void selectCate(int idx) {
		var lbl = jls.get(idx);
		
		if(lbl.getBackground().equals(Color.white)) {
			lbl.setBackground(Color.red);
			lbl.setForeground(Color.white);
			selP.addAll(plist.get(idx));
			selname.addAll(names.get(idx));
		}
		else {
			lbl.setBackground(Color.white);
			lbl.setForeground(Color.black);
			for (var p : plist.get(idx)) {
				selP.remove(p);
			}
			for (var s : names) {
				selname.remove(s);
			}
		}
		panel.repaint();
	}

	private class PanelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var p = e.getPoint();
			repaint();
			try {
				Point2D inversep =af.inverseTransform(p, null);
				var imsi = selP.stream().filter(x->x.distance(inversep)<=4).findAny().orElse(null);
				if(imsi!=null) {
					if(scale==1.0) {
						cp = imsi;
						zoom();
					}
					if(2==e.getClickCount()) {
						try {
							var rs =res("select * from brand where bxx = "+imsi.x+" and byy ="+imsi.y);
							rs.next();
							showPage(new G_브랜드정보(rs.getInt("bno")), "G_브랜드정보");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			} catch (NoninvertibleTransformException e1) {
				e1.printStackTrace();
			}
		}

		private void zoom() {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int i = 0; i < 16; i++) {
						scale += 0.5;
						repaint();
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start(); 
		}
	}
	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			var p = e.getPoint();
			repaint();
			try {
				Point2D inversep =af.inverseTransform(p, null);
				var imsi = selP.stream().filter(x->x.distance(inversep)<=4).findAny().orElse(null);
				if(imsi!=null) {
					int idx = selP.indexOf(imsi);
					panel.setToolTipText(selname.get(idx));
				}
				else {
					panel.setToolTipText(null);
				}
			} catch (NoninvertibleTransformException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			showPage("B_메인");
		}
	}
}
