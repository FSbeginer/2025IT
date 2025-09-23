import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.awt.event.MouseWheelEvent;

public class C_마이홈 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JPanel panel;
	public JPanel panel_1;
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
		setBounds(100, 100, 539, 504);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(uname+"님");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label.setBounds(12, 10, 107, 26);
		getContentPane().add(label);
		
		label_1 = new JLabel("\uC9C4\uB8CC\uAE30\uB85D");
		label_1.setBounds(12, 46, 57, 15);
		getContentPane().add(label_1);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(getIcon("map.png",500,250).getImage(), 0, 0,null);
				g.setColor(Color.blue);
				g.fillOval(me.x-4, me.y-4, 8, 8);
				g.setColor(Color.black);
				g.drawString("현재위치", me.x-25, me.y-6);
				if(flag) {
					Image img = getbuf();
					g.drawImage(img, 0, 0, null);
				}
			}

		};
		panel.addMouseListener(new PanelMouseListener());
		panel.setBounds(12, 205, 500, 250);
		getContentPane().add(panel);
		
		panel_1 = new JPanel();
		panel_1.addMouseWheelListener(new Panel_1MouseWheelListener());
		panel_1.setBounds(12, 71, 500, 100);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		label_2 = new JLabel("\uADFC\uCC98 \uBCD1\uC6D0");
		label_2.setBounds(12, 180, 57, 15);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("\uAC80\uC0AC \uACB0\uACFC");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setBounds(366, 10, 57, 15);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("\uC608\uC57D \uB0B4\uC5ED");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setBounds(444, 10, 57, 15);
		getContentPane().add(label_4);

		load();
		new	Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				flag = true;
				while(deg++<360) {
					panel.repaint();
					System.out.println(1);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	boolean flag = false;
	private BufferedImage getbuf() {
		BufferedImage bi = new BufferedImage(500, 250, 2);
		var g = bi.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(new Color(120, 150, 255, 70).brighter());
		g.fillOval(me.x-75, me.y-75, 150, 150);
		
		int ang = 270;
		
		g.setColor(new Color(120,150,255,120).darker());
		g.fillArc(me.x-75, me.y-75, 150, 150, ang, deg);
		
		g.setColor(Color.black);
		for (Point p : plist) {
			g.fillOval(p.x-4, p.y-4, 8, 8);
		}
		
		return bi;
	}
	
	int cx;
	int deg = 0;
	List<JPanel> pps = new ArrayList<JPanel>();
	private void load() {
		try (var rs = res("select hno, dno, d.name dname, h.name hname, date from record join doctor d using(dno) join hospital h using(hno) where uno = "+uno+" order by date;")) {
			int w = (panel_1.getWidth()-50)/2;
			int h = panel_1.getHeight();
			int i =0;
			while(rs.next()) {
				C_패널 pp = new C_패널(getIcon("hospital/"+rs.getInt(1)+".png",90,80), rs.getString("hname"), rs.getString("dname"), rs.getString("date"));
				pp.setSize(w, h);
				pp.setLocation((w+10)*i, 0);
				int hno =rs.getInt(1);
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						cx =e.getX();
					}
				});
				pp.label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new E_병원정보(hno),"E_병원정보");
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX() - cx;
						if(pps.size()==0||pps.get(0).getX()+dx>0||pps.get(pps.size()-1).getX()+dx<panel_1.getWidth()-pps.get(0).getWidth()) return;
						for (var pp : pps) {
							pp.setLocation(pp.getX()+dx, pp.getY());
						}
					}
				});
				panel_1.add(pp);
				pps.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		try {
			var rs =res("select * from user where uno = "+uno);
			rs.next();
			me = new Point(rs.getInt("x"),rs.getInt("y")/2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from hospital;")) {
			while(rs.next()) {
				var p = new Point(rs.getInt("x"),rs.getInt("y")/2);
				if(p.distance(me)<=75) {
					plist.add(p);
					hnos.add(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	Point me = new Point();
	List<Point> plist = new ArrayList<Point>();
	List<Integer> hnos = new ArrayList<Integer>();

	private class Panel_1MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation()>0?-10:10;
			if(pps.size()==0||pps.get(0).getX()+dx>0||pps.get(pps.size()-1).getX()+dx<panel_1.getWidth()-pps.get(0).getWidth()) return;
			for (var pp : pps) {
				pp.setLocation(pp.getX()+dx, pp.getY());
			}
		}
	}
	private class PanelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var p = plist.stream().filter(x->x.distance(e.getPoint())<=4).findAny().orElse(null);
			if(p!=null) {
				showPage(new E_병원정보(hnos.get(plist.indexOf(p))), "E_병원정보");
			}
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new K_검사결과(),"K_검사결과");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new L_예약내역(),"L_예약내역");
		}
	}
}
