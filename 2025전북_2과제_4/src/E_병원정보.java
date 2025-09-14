import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class E_병원정보 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_병원정보 frame = new E_병원정보(1);
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
	int hno;
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	public JPanel panel_2;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public E_병원정보(int hno) {
		setTitle("\uBCD1\uC6D0 \uC815\uBCF4");
		this.hno = hno;
		setBounds(100, 100, 564, 501);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = getIcon("map.png",250,125).getImage();
				g.drawImage(img, 0, 0, null);
				g.setColor(blue.darker());
				g.fillOval(me.x-4, me.y-4, 8, 8);
			}
		};
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 327, 250, 125);
		getContentPane().add(panel);
		
		panel_1 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = getIcon("map.png",250,125).getImage();
				g.drawImage(img, 0, 0, null);
				g.setColor(blue.darker());
				for (Point p: ps) {
					System.out.println(1);
					g.fillOval(p.x-4, p.y-4, 8, 8);
				}
			}
		};
		panel_1.addMouseListener(new Panel_1MouseListener());
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(291, 327, 250, 125);
		getContentPane().add(panel_1);
		
		label = new JLabel("\uC704\uCE58");
		label.setBounds(12, 301, 57, 15);
		getContentPane().add(label);
		
		label_1 = new JLabel("\uC8FC\uBCC0 \uC57D\uAD6D");
		label_1.setBounds(291, 301, 57, 15);
		getContentPane().add(label_1);
		
		panel_2 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = getIcon("hospital/"+hno+".png",getWidth(),getHeight()).getImage();
				g.drawImage(img, 0, 0, null);
				
				if(enter) {
					int size = (int) (50 * zoom);
					g.drawImage(img, mx.x-size/2, mx.y-size/2, mx.x+size/2, mx.y+size/2, mx.x-25,mx.y-25, mx.x+25,mx.y+25, null);
					g.setColor(Color.red);
					g.drawRect(mx.x-size/2, mx.y-size/2, size, size);
				}
			}
		};
		panel_2.addMouseWheelListener(new Panel_2MouseWheelListener());
		panel_2.addMouseMotionListener(new Panel_2MouseMotionListener());
		panel_2.addMouseListener(new Panel_2MouseListener());
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(12, 10, 524, 171);
		getContentPane().add(panel_2);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("굴림", Font.BOLD, 14));
		label_2.setBounds(12, 187, 524, 40);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setBounds(12, 237, 293, 15);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("\uC758\uC0AC \uC18C\uAC1C>");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setBounds(12, 262, 83, 15);
		getContentPane().add(label_4);
		
		load();
	}
	Point me;
	boolean enter;
	double zoom = 1.0;
	Point mx ;
	String name;
	List<Point> ps = new ArrayList<>();
	private void load() {
		try (var rs = res("select * from hospital where hno = "+hno)) {
			rs.next();
			me = new Point(rs.getInt("x")/2,rs.getInt("y")/4);
			label_2.setText(rs.getString("name"));
			label_3.setText(rs.getString("time"));
			name = rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from pharmacy")) {
			while(rs.next()) {
				Point p = new Point(rs.getInt("x")/2, rs.getInt("y")/4);
				if(p.distance(me)<=40) {
					ps.add(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class Panel_2MouseListener extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			enter = true;
		}
		@Override
		public void mouseExited(MouseEvent e) {
			enter = false;
		}
		@Override
		public void mousePressed(MouseEvent e) {
			cy = e.getY();
		}
	}
	int cy; 
	private class Panel_2MouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			mx = e.getPoint();
			repaint();
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			int dx = e.getY() - cy;
			zoom += dx>0? 0.1 : -0.1;
			repaint();
		}
	}
	private class Panel_2MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			zoom += (e.getWheelRotation()>0 ? 0.1 : -0.1);
			repaint();
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new G_의사(hno),"G_의사");
		}
	}
	private class Panel_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_지도(new Point(me.x*2,me.y*4), name),"F_지도");
		}
	}
}
