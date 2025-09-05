import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class L_선생님메인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L_선생님메인 frame = new L_선생님메인();
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
	public L_선생님메인() {
		setTitle("선생님 메인");
		setBounds(100, 100, 386, 382);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(getIcon("icon/logo.png",40,40));
		label.setBounds(12, 10, 57, 42);
		getContentPane().add(label);
		
		label_1 = new JLabel("Skills Qualification Associaion");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(81, 10, 290, 42);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = getbuf();
				g.drawImage(img, 0, 0, null);
			}
			@Override
			public boolean contains(int x, int y) {
				if(selIdx!=-1)
					return arcs[selIdx].contains(x,y);
				return super.contains(x, y);
			}
		};
		label_2.addMouseMotionListener(new Label_2MouseMotionListener());
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(12, 62, 346, 232);
		getContentPane().add(label_2);
		
		button = new RoundButton("문의답변하기");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(Color.BLUE);
		button.setForeground(Color.WHITE);
		button.setBounds(38, 304, 284, 31);
		getContentPane().add(button);
		
		load();
		settimer();
	}

	private void settimer() {
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(prev!=-1) {
					selIdx =prev;
					label_2.setToolTipText(name[selIdx]+": "+String.format("%.1f%%", cnt[selIdx]*1.0/Arrays.stream(cnt).sum()*100));
					new Thread(new Runnable() {
						@Override
						public void run() {
							while(spin<360) {
								spin++;
								label_2.repaint();
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}).start();
				}
			}
		});
	}

	Timer timer;
	String[] name = new String[5];
	int[] cnt = new int[5];
	int selIdx = -1;
	int prev = -1;
	Arc2D[] arcs = new Arc2D.Double[5];
	Color[] c = {Color.red, Color.yellow, Color.green, Color.blue, Color.magenta};
	
	private void load() {
		try {
			var rs =res("select concat(left(tname,1),'선생') name ,count(*) cnt from course_registration join certi using(cno) join teacher using(tno) group by tno order by cnt desc;");
			int i = 0;
			while(rs.next()) {
				name[i] =rs.getString(1);
				cnt[i] = rs.getInt(2);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	int spin = 0;
	private BufferedImage getbuf() {
		BufferedImage bi = new BufferedImage(label_2.getWidth(), label_2.getHeight(), 2);
		var g = bi.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int ang = 0;
		g.rotate(Math.toRadians(spin),label_2.getWidth()/2, label_2.getHeight()/2);
		int x = label_2.getWidth()/2-100;
		int y = label_2.getHeight()/2-100;
		for (int i = 0; i < arcs.length; i++) {
			int deg = (int)Math.round(((double)cnt[i] / Arrays.stream(cnt).sum() * 360));
			if(spin==360&&selIdx==i) {
				arcs[i] = new Arc2D.Double(x+Math.cos(Math.toRadians(ang+deg/2))*15, y-Math.sin(Math.toRadians(ang+deg/2))*15, 200, 200, ang, deg, Arc2D.PIE);
			}
			else
				arcs[i] = new Arc2D.Double(x, y, 200, 200, ang, deg, Arc2D.PIE);
			g.setColor(c[i]);
			g.fill(arcs[i]);
			g.setColor(Color.black);
			g.draw(arcs[i]);
			ang += deg;
		}
		
		return bi;
	}

	private class Label_2MouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			int idx = -1;
			var img = getbuf();
			for (int i = 0; i < 5; i++) {
				if(img.getRGB(e.getX(), e.getY())==c[i].getRGB()) {
					idx = i;
					break;
				}
			}
			
			if(idx!=-1&&idx!=prev) {
				timer.stop();
				timer.start();
			}
			
			prev = idx;
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new 후기작성폼(1),"후기작성폼");
		}
	}
}
