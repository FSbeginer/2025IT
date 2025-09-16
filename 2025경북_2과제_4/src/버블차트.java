import java.awt.Color;
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
		setSize(944, 395);
		setBoubble();
	}

	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (Boubble b : balls) {
			g2.setColor(b.c);
			g2.fill(b);
			g2.setColor(Color.black);
			g2.draw(b);
			g2.drawString(b.name, (int)b.getCenterX()-g.getFontMetrics().stringWidth(b.name)/2, (int) (b.getCenterY()+6));
		}
		for (int i = 0; i < balls.size(); i++) {
			var b1 = balls.get(i);
			b1.move();
			for (int j = i+1; j < balls.size(); j++) {
				var b2 = balls.get(j);
				double dx = b1.getCenterX()-b2.getCenterX();
				double dy = b1.getCenterY()-b2.getCenterY();
				double dist = Math.hypot(dx, dy);
				double minDist = b1.width/2+b2.width/2;
				if(dist<minDist) {
					double overlap = minDist -dist;
					double[] normal = {dx/dist, dy/dist};
					double[] normal2 = {-dx/dist, -dy/dist};
					b1.x += overlap * normal[0] * 0.5;
					b1.y += overlap * normal[1] * 0.5;
					b2.x += overlap * normal2[0] * 0.5;
					b2.y += overlap * normal2[1] * 0.5;
					
					b1.reflat(normal);
					b2.reflat(normal2);
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	};
	
	private void setBoubble() {
		try (var rs = BF.res("select cno,cnam, sum(`order`.quantity) cnt, rank() over(order by sum(`order`.quantity) desc,cno) r from `order` right join product p using(pno) join category using(cno) group by cno;")) {
			int max = 0;
			while(rs.next()) {
				max = Math.max(max, rs.getInt("cnt"));
				double size = max*2/3 *Math.pow(0.9,rs.getInt("r")-1);
				Boubble b = new Boubble(0, 0, size, rs.getString("cnam"), Color.getHSBColor(rand.nextFloat(), 0.6f, 0.9f));
				balls.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < balls.size(); i++) {
			boolean stop = true;
			for (int j = 0; j < 1000; j++) {
				stop = true;
				var b1 = balls.get(i);
				double nx = rand.nextInt((int) (getWidth()-b1.width));
				double ny = rand.nextInt((int) (getHeight()-b1.width));
				double cx = nx+b1.width/2;
				double cy = ny+b1.width/2;
				
				for (int k = 0; k < i; k++) {
					var b2 = balls.get(k);
					double dx = cx - b2.getCenterX();
					double dy = cy - b2.getCenterY();
					if(Math.hypot(dx, dy)<b1.width/2+b2.width/2) {
						stop =false;
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

	List<Boubble> balls = new ArrayList<버블차트.Boubble>();
	Random rand = new Random();
	class Boubble extends Ellipse2D.Double {
		double[] vel = {rand.nextInt(2)+1,rand.nextInt(2)+1};
		Color c;
		String name;
		public Boubble(double x, double y, double size,String name, Color c) {
			super(x,y,size,size);
			this.name = name;
			this.c= c;
		}
		
		public void reflat(double[] normal) {
			double dot  = vel[0] * normal[0] + vel[1]*normal[1];
			vel = new double[] {vel[0]-2*dot*normal[0] , vel[1]-2*dot*normal[1]};
		}

		public void move() {
			if(x+vel[0]<0 || x+vel[0]>버블차트.this.getWidth()-width) {
				vel[0] = -vel[0];
			}
			if(y+vel[1]<0||y+vel[1]>버블차트.this.getHeight()-width) {
				vel[1] = -vel[1];
			}
			x += vel[0];
			y += vel[1];
		}
	}
}
