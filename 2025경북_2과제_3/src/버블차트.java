import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class 버블차트 extends JPanel {

	/**
	 * Create the panel.
	 */
	public 버블차트() {
		setSize(913, 482);
		setLayout(null);
		
		getdata();
		setBubble();
	}

	private void setBubble() {
		for (int i = 0; i < balls.size(); i++) {
			for (int j = 0; j<1000; j++) {
				boolean stop = true;
				var b1 = balls.get(i);
				double nx = r.nextInt((int) (getWidth()-b1.width));
				double ny = r.nextInt((int) (getHeight()-b1.width));
				double cx = nx+b1.width/2;
				double cy = ny+b1.width/2;
				for (int k = 0; k < i; k++) {
					var b2 = balls.get(k);
					double dist = Math.hypot(cx-b2.getCenterX(), cy-b2.getCenterY());
					double mindist =b1.width/2+b2.width/2;
					if(dist<mindist) {
						stop = false;
						break;
					}
				}
				if(stop) {
					b1.x = nx;
					b1.y = ny;
				}
			}
		}
	}

//	int[] cnt = new int[10];
	List<Boubble> balls = new ArrayList<버블차트.Boubble>();
 	private void getdata() {
		try (var rs = BF.res("select cnam, sum(o.quantity) cnt, rank() over(order by  sum(o.quantity) desc)  r from `order` o join product p using(pno) join category using(cno) group by cno;")) {
			int i = 0;
			int max = 0;
			while(rs.next()) {
//				cnt[i] = rs.getInt("cnt");
				max= Math.max(rs.getInt("cnt"),max);
				balls.add(new Boubble(0, 0, max*2/3*Math.pow(0.9, rs.getInt("r")-1), rs.getString("cnam"), Color.getHSBColor(r.nextFloat(), 0.6f, 0.9f)));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Random r = new Random();
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (Boubble b : balls) {
			g2.setColor(b.c);
			g2.fill(b);
			g2.setColor(Color.black);
			g2.draw(b);
			g2.drawString(b.name, (int)(b.getCenterX()-g2.getFontMetrics().stringWidth(b.name)/2), (int) (b.getCenterY()+7));
		}
		
		for (int i = 0; i < balls.size(); i++) {
			var b1 = balls.get(i);
			b1.moveBall();
			for (int j = i+1; j < balls.size(); j++) {
				var b2 = balls.get(j);
				double dx = b1.getCenterX()-b2.getCenterX();
				double dy = b1.getCenterY()-b2.getCenterY();
				double dist = Math.hypot(dx, dy);
				double mindist =b1.width/2+b2.width/2;
				if(dist<mindist) {
					double[] normal = {dx/dist,dy/dist};
					double[] normal2 = {-dx/dist,-dy/dist};
					double overlap = 0.5*(dist-mindist);
					b1.x -= overlap * normal[0];
					b1.y -= overlap * normal[1];
					b2.x -= overlap * normal2[0];
					b2.y -= overlap * normal2[1];
					
					b1.reflac(normal);
					b2.reflac(normal2);
				}
			}
			repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Boubble extends Ellipse2D.Double{
		double[] vel = {r.nextInt(2)+1, r.nextInt(2)+1};
		Color c;
		String name;
		public Boubble(double x, double y, double size,String name, Color c) {
			super(x, y, size,size);
			this.name=name;
			this.c= c;
		}
		public void reflac(double[] n) {
			double dot = vel[0] * n[0] + vel[1] * n[1];
			vel = new double[]{vel[0]-2*dot*n[0], vel[1]-2*dot*n[1]};
		}
		public void moveBall() {
			if(vel[0]+x<0||vel[0]+x>버블차트.this.getWidth()-width) {
				vel[0] *= -1;
			}
			if(vel[1]+y<0||vel[1]+y>버블차트.this.getHeight()-width) {
				vel[1] *= -1;
			}
			x += vel[0];
			y += vel[1];
		}
	}
	
}
