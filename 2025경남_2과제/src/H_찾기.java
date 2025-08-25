import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.event.MouseMotionAdapter;

public class H_찾기 extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	List<JLabel> categorys = new ArrayList<JLabel>();
	List<Point> points = new ArrayList<Point>();
	List<String> names = new ArrayList<>();
	double scale = 1.0;
	private Thread th;
	
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
		setBounds(100, 100, 849, 602);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = getMap();
				if(cp!=null) {
					int fx = (int) (cp.x-25*(8/scale));
					int fy = (int) (cp.y-25*(8/scale));
					int dx = (int) (cp.x+25*(8/scale));
					int dy = (int) (cp.y+25*(8/scale));
					g.drawImage(img, 0, 0, getWidth(), getHeight(), fx,fy, dx, dy, null);
				}
				else
					g.drawImage(img, 0, 0, getWidth(), getHeight(), 0,0, 600, 550, null);
			}
		};
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.addMouseListener(new PanelMouseListener());
		panel.setBounds(0, 10, 600, 550);
		getContentPane().add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(612, 43, 209, 366);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(11, 0, 0, 0));
		
		label = new JLabel("\uC9C1\uC885 \uCE74\uD14C\uACE0\uB9AC");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(612, 10, 209, 40);
		getContentPane().add(label);
		
		label_1 = new MainIcon();
		label_1.setBounds(661, 436, 160, 117);
		getContentPane().add(label_1);

		addCate();
		selectCategory(categorys.get(0));
	}

	private void addCate() {
		createCate("전체",0);
		try {
			var rs = res("select * from category");
			while(rs.next()) {
				createCate(rs.getString(2),rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private void createCate(String string, int cno) {
		JLabel jl = new JLabel(string);
		jl.setOpaque(true);
		jl.setBackground(Color.white);
		jl.setBorder(new LineBorder(Color.black));
		jl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectCategory(jl);
			}
		});
		jl.setName(cno+"");
		categorys.add(jl);
		panel_1.add(jl);
	}
	
	private void selectCategory(JLabel jl) {
		cp = null;
		scale = 1.0;
		
		String cno = jl.getName();
		
		if(jl.getBackground().equals(Color.white)) {
			jl.setBackground(Color.red);
			jl.setForeground(Color.white);
		}
		else {
			jl.setBackground(Color.white);
			jl.setForeground(Color.black);
		}
		
		String sql = cno.equals("0")? "" : "where cno = "+cno;

		
		try {
			var rs = res("select * from brand join category using(cno) "+sql);
			while(rs.next()) {
				if(jl.getBackground().equals(Color.red)) {
					points.add(new Point(rs.getInt("bxx"),rs.getInt("byy")));
					names.add(rs.getString("bname"));
				}
				else {
					points.remove(new Point(rs.getInt("bxx"),rs.getInt("byy")));
					names.remove(rs.getString("bname"));
				}
			}
			panel.repaint();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private Image getMap() {
		BufferedImage bi = new BufferedImage(600, 550, 2);
		var g2 = bi.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Image map = getIcon("지도.png",600,550).getImage();
		g2.drawImage(map, 0, 0, null);
		g2.setColor(Color.red);
		for (Point p : points) {
			g2.fillOval(p.x-4, p.y-4, 8, 8);
		}
		return bi;
	}

	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			showPage("B_메인");
		}
	}
	
	private class PanelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Point p = points.stream().filter(x->x.distance(e.getPoint())<=4).findFirst().orElse(null);
			if(p!=null) {
				cp = p;
				zoom();
			}
			if(e.getClickCount()==2) {
				
			}
		}
	}
	Point cp;
	private void zoom() {
		if(th!=null && th.isAlive()) return;
		th = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 16; i++) {
					scale += 0.5;
					panel.repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}
	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			Point p = points.stream().filter(x->x.distance(e.getPoint())<=4).findFirst().orElse(null);
			if(p!=null) {
				panel.setToolTipText(names.get(points.indexOf(p)));
			}
			else
				panel.setToolTipText(null);
		}
	}
}
