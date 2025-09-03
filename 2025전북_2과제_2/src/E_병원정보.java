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
import java.util.stream.Collectors;
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
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public E_병원정보(int hno) {
		setTitle("\uBCD1\uC6D0 \uC815\uBCF4");
		this.hno = hno;
		setBounds(100, 100, 569, 491);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				Image img = BF.getIcon("hospital/"+hno+".png", getWidth(), getHeight()).getImage();
				g.drawImage(img, 0, 0, null);
				
				if(flag) {
					int size = (int) (50 * zoom);
					int dx= cp.x-size/2;
					int dy= cp.y-size/2;
					
					g.setColor(Color.red);
					g.drawImage(img, dx, dy, dx+size, (dy+size), cp.x-25, cp.y-25, cp.x+25,cp.y+25, null);
					g.drawRect(dx, dy, size, size);
				}
			}

		};
		label.addMouseWheelListener(new LabelMouseWheelListener());
		label.addMouseMotionListener(new LabelMouseMotionListener());
		label.addMouseListener(new LabelMouseListener());
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 525, 159);
		getContentPane().add(label);
		
		label_1 = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = BF.getIcon("map.png", getWidth(), getHeight()).getImage();
				g.drawImage(img, 0, 0, null);
				
				g.setColor(Color.blue.darker());
				g.fillOval(p.x-4, p.y-4, 8, 8);
			}
		};
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(12, 317, 250, 125);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = BF.getIcon("map.png", getWidth(), getHeight()).getImage();
				g.drawImage(img, 0, 0, null);
				
				g.setColor(Color.blue.darker());
				for (Point p : ps) {
					g.fillOval(p.x-4, p.y-4, 8, 8);
				}
			}
		};
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setBounds(287, 317, 250, 125);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("굴림", Font.BOLD, 14));
		label_3.setBounds(12, 179, 406, 34);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setBounds(12, 223, 414, 22);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("\uC704\uCE58 \uC18C\uAC1C>");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setBounds(12, 255, 142, 15);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("\uC704\uCE58");
		label_6.setBounds(12, 292, 57, 15);
		getContentPane().add(label_6);

		load();
	}
	boolean flag = false;
	double zoom = 1.0;
	Point p = new Point();
	List<Point> ps = new ArrayList<Point>();
	
	private void load() {
		try (var rs = res("select * from hospital where hno = "+hno)) {
			rs.next();
			label_3.setText(rs.getString("name"));
			label_4.setText(rs.getString("time"));
			p = new Point(rs.getInt("x")/2, rs.getInt("y")/4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try (var rs = res("select * from pharmacy")) {
			while (rs.next()) {
				ps.add(new Point(rs.getInt("x")/2, rs.getInt("y") / 4));
			}
			ps = ps.stream().filter(x -> x.distance(p) <= 40).collect(Collectors.toList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	int cy;
	Point cp;
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			flag = true;
		}
		@Override
		public void mouseExited(MouseEvent e) {
			flag = false;
		}
		@Override
		public void mousePressed(MouseEvent e) {
			cy = e.getY();
		}
	}
	private class LabelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			cp = e.getPoint();
			repaint();
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			int dy= e.getY()-cy;
			zoom += dy>0? 0.1 : -0.1;
			repaint();
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_지도(new Point(p.x*2, p.y*4), label_3.getText()),"F_지도");
		}
	}
	private class LabelMouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			double dx = e.getWheelRotation()>0? 0.1 : -0.1;
			zoom += dx;
			repaint();
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new G_의사(hno),"G_의사");
		}
	}
}
