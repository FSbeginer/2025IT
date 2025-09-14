import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class L_선생님메인 extends BF {

	private JPanel contentPane;
	public JLabel label;
	public JLabel label_1;
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
		addWindowListener(new ThisWindowListener());
		setTitle("선생님 메인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel(getIcon("icon/logo.png",50,50));
		label.setBounds(12, 10, 64, 51);
		contentPane.add(label);
		
		label_1 = new JLabel();
		label_1.setText("Skills Qualification Association");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_1.setBounds(88, 10, 286, 46);
		contentPane.add(label_1);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage buf = getbuf();
				g.drawImage(buf, 0, 0, null);
			}
			@Override
			public boolean contains(int x, int y) {
				if(selidx!=-1)
					return arcs[selidx].contains(x,y);
				else
					return super.contains(x, y);
			}

		};
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(39, 90, 342, 269);
		contentPane.add(panel);
		
		button = new JButton("문의답변하기");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(new Color(0, 0, 255));
		button.setForeground(new Color(255, 255, 255));
		button.setBounds(69, 385, 277, 37);
		contentPane.add(button);
		getdata();
		setTiemr();
	}

	private void setTiemr() {
		timer = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(prev!=-1) {
					selidx = prev;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							while(spin++<360) {
								panel.repaint();
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}).start();
					panel.setToolTipText(names[selidx]+"선생: "+String.format("%.1f%%", (double)cnt[selidx]/Arrays.stream(cnt).sum()*100));
				}
			}
		});
	}
	int selidx=-1, prev, spin;
	Arc2D[] arcs = new Arc2D.Double[5];
	String[] names = new String[5];
	int[] cnt = new int[5];
	
	private void getdata() {
		try (var rs = res("select left(tname,1) ,count(*) cnt  from course_registration join certi using(cno) join teacher using(tno) group by cno order by cnt desc,tno limit 5;")) {
			int i = 0;
			while(rs.next()) {
				names[i] = rs.getString(1);
				cnt[i] = rs.getInt(2);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new I_고객센터(),"I_고객센터");
		}
	}
	Color[] c  =new Color[] {Color.red, Color.yellow, Color.green, Color.blue,Color.magenta};
	private BufferedImage getbuf() {
		BufferedImage buf = new BufferedImage(panel.getWidth(), panel.getHeight(), 2);
		var g = buf.createGraphics();
		g.rotate(Math.toRadians(spin),panel.getWidth()/2,panel.getHeight()/2);
		int bx = panel.getWidth()/2-125;
		int by = panel.getHeight()/2-125;
		int ang = 0;
		for (int i = 0; i < 5; i++) {
			int deg = (int) Math.round((double)cnt[i]/ Arrays.stream(cnt).sum() * 360);
			if(selidx==i&&spin==360) {
				arcs[i] = new Arc2D.Double(bx+ Math.cos(Math.toRadians(ang+deg/2))*15, by- Math.sin(Math.toRadians(ang+deg/2))*15, 250, 250, ang, deg, Arc2D.PIE);
			}
			else {
				arcs[i] = new Arc2D.Double(bx, by, 250, 250, ang, deg, Arc2D.PIE);
			}
			g.setColor(c[i]);
			g.fill(arcs[i]);
			g.setColor(Color.black);
			g.draw(arcs[i]);
			ang += deg;
		}
		
		return buf;
	}
	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			BufferedImage buf = getbuf();
			int idx = -1;
			for (int i = 0; i < 5; i++) {
				if(buf.getRGB(e.getX(), e.getY())==c[i].getRGB()) {
					idx = i;
					break;
				}
			}
			if(prev!=-1&&prev!=idx) {
				timer.stop();
				timer.start();
			}
			prev = idx;
		}
		
	}
	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			isAdmin =false;
			uno = 0;
		}
	}
}
