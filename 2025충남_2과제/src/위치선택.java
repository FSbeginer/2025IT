import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class 위치선택 extends BF {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					위치선택 frame = new 위치선택(1, new Point(100,100));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int x, y;
	int dx, dy, sno;
	public JLabel label;
	Point now;
	Image img, pic;
	
	public 위치선택(int sno, Point now) {
		addKeyListener(new ThisKeyListener());
		this.now = now;
		this.sno = sno;
		setTitle("\uC704\uCE58\uC120\uD0DD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 695, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				var cir = getCircleImage();
				g.translate(x, y);
				g.drawImage(img, 0, 0, null);
				for (Point point : plist) {
					g.drawImage(cir, point.x, point.y, null);
				}
				g.drawImage(pic, now.x, now.y, null);
			}
		};
		label.setBounds(0, 0, 680, 280);
		contentPane.add(label);
		
		img = getIcon("내부/"+sno+".png",680,280).getImage();
		pic = getIcon("아이콘/위치.png", 30, 30).getImage();
		load();
	}
	
	List<Point> plist = new ArrayList<Point>();
	private void load() {
		try (var rs = res("select * from location where sno = "+sno+" group by pno")) {
			while(rs.next()) {
				var p =new Point(rs.getInt("x"), rs.getInt("y"));
				if(!p.equals(now))
					plist.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getCircleImage() {
		BufferedImage bi = new BufferedImage(30, 30, 2);

		var g = bi.createGraphics();
		g.setColor(Color.green);
		g.fillOval(0, 0, 30, 30);
		g.setColor(Color.white);
		g.fillOval(4, 4, 22, 22);
		return bi;
	}
	private class ThisKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==e.VK_UP) {
				y++;
			}
			if(e.getKeyCode()==e.VK_DOWN) {
				y--;
			}
			if(e.getKeyCode()==e.VK_LEFT) {
				x++;
			}
			if(e.getKeyCode()==e.VK_RIGHT) {
				x--;
			}
			repaint();
		}
	}
}
