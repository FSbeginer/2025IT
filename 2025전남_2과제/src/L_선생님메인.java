import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class L_선생님메인 extends BF {
	public JLabel label;
	public JPanel panel;
	public JButton button;
	private Timer timer;

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
		setBounds(100, 100, 483, 519);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(getIcon("icon/logo.png",40,40));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		label.setText("Skills Qualification Association");
		label.setBounds(12, 10, 443, 59);
		getContentPane().add(label);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage buf = getbuf();
				g.drawImage(buf, 0, 0, null);
			}
			@Override
			public boolean contains(int x, int y) {
				if(selIdx!=-1) {
					return arc[selIdx].contains(x, y);
				}
				else
					return super.contains(x, y);
			}
		};
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(36, 93, 375, 302);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		button = new JButton("문의답변하기");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(Color.BLUE);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		button.setBounds(53, 419, 328, 35);
		getContentPane().add(button);
		
		getData();
		settimer();
	}

	int selIdx=-1;
	int before=-1;
	private void settimer() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(before!=-1) {
					selIdx = before;
					panel.setToolTipText(name.get(selIdx).substring(0,1)+"선생: "+String.format("%.1f%%", (double)cnt.get(selIdx)/sum*100));
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							while(spin<360) {
								spin++;
								panel.repaint();
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}).start();;
				}
			}
		});
	}

	List<Integer> cnt = new ArrayList<Integer>();
	List<String> name = new ArrayList<String>();
	int sum;
	
	private void getData() {
		try (var rs = res("select tname, count(*) cnt from teacher join certi using(tno) join course_registration using(cno) group by tno order by cnt desc;")) {
			while(rs.next()) {
				name.add(rs.getString(1));
				cnt.add(rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sum = cnt.stream().mapToInt(x->x).sum();
	}

	Arc2D[] arc = new Arc2D.Double[5];
	int spin = 0;
	Color[] c = {Color.red, Color.yellow, Color.green, Color.blue, Color.magenta};
	private BufferedImage getbuf() {
		BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), 2);
		Graphics2D g2 = bi.createGraphics();
		
		g2.rotate(Math.toRadians(spin), panel.getWidth()/2, panel.getHeight()/2);
		
		int ang = 0;
		int r = 125;
		int bx = panel.getWidth()/2-r;
		int by = panel.getHeight()/2-r;
		
		for (int i = 0; i < arc.length; i++) {
			int deg = (int) Math.round((double)cnt.get(i)/sum*360);
			
			if(spin==360&&selIdx==i) {
				arc[i] = new Arc2D.Double(bx+Math.cos(Math.toRadians(ang+deg/2))*20,by-Math.sin(Math.toRadians(ang+deg/2))*20,r*2,r*2,ang,deg, Arc2D.PIE);
			}
			else
				arc[i] = new Arc2D.Double(bx,by,r*2,r*2,ang,deg, Arc2D.PIE);
			
			g2.setColor(c[i]);
			g2.fill(arc[i]);
			g2.setColor(Color.black);
			g2.draw(arc[i]);
			ang += deg;
		}
		
		return bi;
	}
	
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new I_고객센터(), "I_고객센터");
		}
	}
	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			int idx = -1;
			for (int i = 0; i < arc.length; i++) {
				if(arc[i].contains(e.getPoint())) {
					idx = i;
					break;
				}
			}
			if(idx!=-1&&idx !=before) {
				timer.stop();
				timer.start();
			}
			before = idx;
		}
	}
}
