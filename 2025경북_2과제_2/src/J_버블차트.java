import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class J_버블차트 extends JPanel {

	/**
	 * Create the panel.
	 */
	Random rand = new Random();

	public J_버블차트() {
		setSize(852, 393);

		getData();
	}

	List<Boubble> balls = new ArrayList<J_버블차트.Boubble>();

	private void getData() {
		try (var rs = BF.res(
				"select cnam, sum(o.quantity), rank() over(order by sum(o.quantity) desc)-1 '순위' from `order` o join product p using(pno) join category c using(cno) group by cno;")) {
			int max = 0;
			while (rs.next()) {
				max = Math.max(rs.getInt(2), max);
				double size = max*2/3 * Math.pow(0.9, rs.getInt(3));
				Color c = Color.getHSBColor(rand.nextFloat(), 0.6f, 0.9f);
				Boubble b = new Boubble(0, 0, size, c, rs.getString(1));
				balls.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < balls.size(); i++) {
			while (true) {
				boolean stop = true;
				var b1 = balls.get(i);
				double x = rand.nextInt((int) (getWidth() - b1.width));
				double y = rand.nextInt((int) (getHeight() - b1.width));
				for (int j = 0; j < i; j++) {
					var b2 = balls.get(j);
					double dx = (x+b1.width/2) - b2.getCenterX();
					double dy = (y+b1.width/2) - b2.getCenterY();
					double dist = Math.hypot(dx, dy);
					if (dist < b2.width / 2 + b1.width / 2) {
						stop = false;
						
						break;
					}
				}
				if (stop) {
					b1.x = x;
					b1.y = y;
					break;
				}
			}
		}
	}

	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (Boubble boubble : balls) {
			g2.setColor(boubble.c);
			g2.fill(boubble);
			g2.setColor(Color.black);
			g2.draw(boubble);
			g2.drawString(boubble.name, (int)(boubble.getCenterX()-g2.getFontMetrics().stringWidth(boubble.name)/2), (int) boubble.getCenterY());
		}
		for (int i = 0; i < balls.size(); i++) {
			var b1 =balls.get(i);
			b1.moveBall();
			for (int j = i+1; j < balls.size(); j++) {
				var b2 = balls.get(j);
				double dx = b1.getCenterX()-b2.getCenterX();
				double dy = b1.getCenterY()-b2.getCenterY();
				double minDist = b1.width/2+b2.width/2;
				double dist = Math.hypot(dx, dy);
				if(dist<minDist) {
					double[] normal1 = {dx/dist, dy/dist};
					double[] normal2 = {-dx/dist, -dy/dist};
					
					double overlap = 0.5 * (minDist - dist);
					b1.x +=overlap * normal1[0];
					b1.y +=overlap* normal1[1];
					b2.x +=overlap* normal2[0];
					b2.y +=overlap* normal2[1];
					b1.reflac(normal1);
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
	};

	class Boubble extends Ellipse2D.Double {
		double[] vel = { rand.nextInt(2) + 1, rand.nextInt(2) + 1 };
		Color c;
		String name;

		public Boubble(double x, double y, double size, Color c, String name) {
			super(x, y, size, size);
			this.c = c;
			this.name = name;
		}

		public void reflac(double[] normal) {
			double dot = normal[0]*vel[0]+normal[1]*vel[1];
			vel = new double[]{ vel[0]-2*dot*normal[0], vel[1]-2*dot*normal[1]};
		}

		public void moveBall() {
			if(x+vel[0]<0||x+vel[0]>J_버블차트.this.getWidth()-width) {
				vel[0] = -vel[0];
			}
			if(y+vel[1]<0||y+vel[1]>J_버블차트.this.getHeight()-width) {
				vel[1] = -vel[1];
			}
			x +=vel[0];
			y +=vel[1];
		}
	}
}
