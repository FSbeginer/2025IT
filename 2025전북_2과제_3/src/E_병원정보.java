import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class E_º´¿øÁ¤º¸ extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_º´¿øÁ¤º¸ frame = new E_º´¿øÁ¤º¸(1);
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
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public E_º´¿øÁ¤º¸(int hno) {
		setTitle("\uBCD1\uC6D0\uC815\uBCF4");
		this.hno = hno;
		setBounds(100, 100, 565, 498);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(BF.getIcon("map.png",250,125).getImage(), 0, 0, null);
				g.setColor(Color.blue);
				g.fillOval(me.x-4, me.y-4, 8, 8);
			}
		};
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(9, 322, 250, 125);
		getContentPane().add(label);
		
		label_1 = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(BF.getIcon("map.png",250,125).getImage(), 0, 0, null);
				g.setColor(Color.blue);
				for (Point point : plist) {
					g.fillOval(point.x-4, point.y-4, 8, 8);
				}
			}
		};
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(289, 321, 250, 125);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = BF.getIcon("hospital/"+hno+".png",getWidth(),getHeight()).getImage();
				g.drawImage(img, 0, 0, null);
				
				if(enter) {
					int sx = cp.x-25;
					int sy = cp.y-25;
					int size = (int) (scale * 50);
					g.drawImage(img, cp.x-size/2, cp.y-size/2, cp.x+size/2, cp.y+size/2, sx, sy, sx+50, sy+50, null);
					g.setColor(Color.red);
					g.drawRect(cp.x-size/2, cp.y-size/2, size, size);
				}
			}
		};
		label_2.addMouseWheelListener(new Label_2MouseWheelListener());
		label_2.addMouseMotionListener(new Label_2MouseMotionListener());
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setBounds(12, 13, 524, 165);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		label_3.setBounds(15, 190, 419, 39);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_4.setBounds(15, 242, 383, 15);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("\uC758\uC0AC \uC18C\uAC1C>");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_5.setBounds(16, 269, 72, 15);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("\uC704\uCE58");
		label_6.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_6.setBounds(10, 304, 56, 15);
		getContentPane().add(label_6);
		
		label_7 = new JLabel("\uC8FC\uBCC0 \uC57D\uAD6D");
		label_7.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_7.setBounds(288, 296, 56, 15);
		getContentPane().add(label_7);

		
		load();
	}
	Point me;
	List<Point > plist = new ArrayList<Point>();
	boolean enter;
	private void load() {
		try (var rs = res("select * from hospital where hno = "+hno)) {
			rs.next();
			me = new Point(rs.getInt("x")/2, rs.getInt("y")/4);
			label_3.setText(rs.getString(2));
			label_4.setText(rs.getString("time"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from pharmacy")) {
			while(rs.next()) {
				var p = new Point(rs.getInt("x")/2, rs.getInt("y")/4);
				if(me.distance(p)<40) {
					plist.add(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	int cx = 0;
	private class Label_2MouseListener extends MouseAdapter {
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
			cx = e.getY();
		}
	}
	double scale = 1.0;
	private class Label_2MouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			cp = e.getPoint();
			label_2.repaint();
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			int dx = e.getY() -cx;
			scale += dx>0? 0.01 : -0.01;
			label_2.repaint();
		}
	}
	Point cp;
	private class Label_2MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			scale += e.getWheelRotation()>0? 0.1 : -0.1;
			label_2.repaint();
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new G_ÀÇ»ç(hno),"G_ÀÇ»ç");
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				var rs =res("select * from hospital where hno = "+hno);
				rs.next();
				Point p = new Point(rs.getInt("x"),rs.getInt("y"));
				String name =rs.getString(2);
				showPage(new F_Áöµµ(p,name),"F_Áöµµ");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
