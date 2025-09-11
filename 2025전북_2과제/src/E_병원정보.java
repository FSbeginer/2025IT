import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class E_º´¿øÁ¤º¸ extends BF {

	
	int hno;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	Image map = getIcon("map.png", 250, 125).getImage();
	double scale = 2.0;
	int prev;
	Point mp;
	boolean isEnter = false;

	public E_º´¿øÁ¤º¸(int hno) {
		setTitle("\uBCD1\uC6D0 \uC815\uBCF4");
		this.hno = hno;
		setBounds(100, 100, 579, 554);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				var ori =g2.getTransform();
				Image img = BF.getIcon("hospital/"+hno+".png", getWidth(), getHeight()).getImage();
				g2.drawImage(img, 0, 0, null);
				if(isEnter) {
					g2.scale(scale, scale);
					int sx = (int) (mp.x/scale);
					int sy = (int) (mp.y/scale);
					g2.drawImage(img, sx-25, sy-25, sx+25, sy+25, mp.x-25, mp.y-25, mp.x+25, mp.y+25, null);
					g2.setColor(Color.red);
					g2.drawRect(sx-25, sy-25, 50, 50);
				}
				g2.setTransform(ori);
				repaint();
			}
		};
		label.addMouseWheelListener(new LabelMouseWheelListener());
		label.addMouseListener(new LabelMouseListener());
		label.addMouseMotionListener(new LabelMouseMotionListener());
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 529, 216);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("±¼¸²", Font.BOLD, 15));
		label_1.setBounds(12, 236, 405, 39);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("±¼¸²", Font.BOLD, 12));
		label_2.setBounds(12, 271, 405, 20);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("\uC758\uC0AC \uC18C\uAC1C>");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("±¼¸²", Font.BOLD, 12));
		label_3.setBounds(12, 301, 90, 20);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("\uC704\uCE58");
		label_4.setFont(new Font("±¼¸²", Font.PLAIN, 12));
		label_4.setBounds(12, 346, 78, 20);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(map, 0, 0, null);
				g.setColor(blue.darker());
				g.fillOval(hos.x-4, hos.y-4, 8, 8);
			}
		};
		label_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_5.setBounds(12, 373, 250, 125);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(map, 0, 0, null);
				g.setColor(blue.darker());
				for (Point p : points) {
					g.fillOval(p.x-4, p.y-4, 8, 8);
				}
			}
		};
		label_6.addMouseListener(new Label_6MouseListener());
		label_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_6.setBounds(291, 373, 250, 125);
		getContentPane().add(label_6);
		
		label_7 = new JLabel("\uC8FC\uBCC0 \uC57D\uAD6D");
		label_7.setFont(new Font("±¼¸²", Font.PLAIN, 12));
		label_7.setBounds(291, 346, 78, 20);
		getContentPane().add(label_7);

		load();
	}
	Point hos;
	List<Point> points = new ArrayList<Point>();
	private void load() {
		try (var rs = res("select * from hospital where hno = "+hno)) {
			if(rs.next()) {
				label_1.setText(rs.getString(2));
				label_2.setText(rs.getString("time"));
				hos = new Point(rs.getInt("x")/2,rs.getInt("y")/4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from pharmacy")) {
			while(rs.next()) {
				var p = new Point(rs.getInt("x")/2,rs.getInt("y")/4);
				if(hos.distance(p)<=40) {
					points.add(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class Label_6MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_Áöµµ(hno),"F_Áöµµ");
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new G_ÀÇ»ç(hno),"G_ÀÇ»ç");
		}
	}
	private class LabelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			scale = Math.max(0, scale+(e.getY()-prev>0?0.01:-0.01));
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			mp = e.getPoint();
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			prev = e.getY();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			isEnter= true;
		}
		@Override
		public void mouseExited(MouseEvent e) {
			isEnter = false;
		}
	}
	private class LabelMouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			scale = Math.max(0, scale+(e.getWheelRotation()>0?0.1:-0.1));
		}
	}
}
