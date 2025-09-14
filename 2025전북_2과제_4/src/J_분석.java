import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class J_분석 extends BF {
	public JLabel label;
	public JPanel panel;
	public JComboBox comboBox;
	public JPanel panel_1;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					J_분석 frame = new J_분석();
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
	public J_분석() {
		setTitle("\uBD84\uC11D");
		setBounds(100, 100, 659, 508);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("\uBD84\uC11D");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 619, 50);
		getContentPane().add(label);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = getbuf();
				g.drawImage(img, 0, 0, null);
			}

		};
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.setBounds(29, 86, 587, 310);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		label_6 = new JLabel("");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setVerticalAlignment(SwingConstants.TOP);
		label_6.setBounds(313, 10, 262, 233);
		panel.add(label_6);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC9C4\uB8CC \uB9CE\uC740 \uC758\uC0AC 5", "\uC608\uC57D \uB9CE\uC740 \uC758\uC0AC 5"}));
		comboBox.setBounds(476, 53, 140, 23);
		getContentPane().add(comboBox);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 419, 619, 40);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_4);
		
		label_5 = new JLabel("New label");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_5);

	
		getData();
	}
	private BufferedImage getbuf() {
		BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), 2);
		
		var g = bi.createGraphics();
		
		int ang = 90;
		int bx = panel.getWidth()/2-150, by = panel.getHeight()/2-150;
		for (int i = 0; i < 5; i++) {
			int deg = (int) ((double)cnt[i]/Arrays.stream(cnt).sum() * 360);
			g.setColor(c[i]);
			g.fillArc(bx, by, 300, 300, ang, -deg);
			ang -= deg;
		}
		ang = 90;
		var af = g.getTransform();
		for (int i = 0; i < 5; i++) {
			int deg = (int) ((double)cnt[i]/Arrays.stream(cnt).sum() * 360);
			g.rotate(-Math.toRadians(ang-deg/2), panel.getWidth()/2, panel.getHeight()/2);
			String s = java.lang.String.format("%.1f%%", (double)cnt[i]/Arrays.stream(cnt).sum() * 100);
			g.rotate(Math.toRadians(ang-deg/2),panel.getWidth()/2+150, panel.getHeight()/2);
			g.setColor(Color.black);
			g.drawString(s,panel.getWidth()/2+150-g.getFontMetrics().stringWidth(s)/2, panel.getHeight()/2);
			g.setTransform(af);
			ang -= deg;
		}
		g.setColor(Color.white);
		g.fillOval(bx+50, by+50, 200, 200);
		
		return bi;
	}
	
	int[] dnos = new int[5];
	private void getData() {
		String path = comboBox.getSelectedIndex()==0 ? "record" : "reservation";
		JLabel[] jls = new JLabel[] {label_1,label_2,label_3,label_4,label_5};
		try (var rs = res("select dno,d.name dn, h.name hn, date, count(*) cnt from doctor d  join hospital h using(hno) left join "+path+" using(dno) group by dno order by cnt desc, dno limit 5")) {
			
			System.out.println("select dno,d.name dn, h.name hn, date, count(*) cnt from doctor d  join hospital h using(hno) left join "+path+" using(dno) group by dno order by cnt desc, dno limit 5");
			int  i = 0;
			while(rs.next()) {
				cnt[i] = rs.getInt("cnt");
				dnos[i] =rs.getInt("dno");
				names[i] = rs.getString("dn");
				jls[i].setIcon(geticon(c[i]));
				jls[i].setText(names[i]);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Icon geticon(Color color) {
		BufferedImage bi = new BufferedImage(30, 30, 2);
		var g = bi.createGraphics();
		g.setColor(color);
		g.fillOval(0, 0, 30, 30);
		return new ImageIcon(bi);
	}

	int[] cnt = new int[5];
	String[] names = new String[5];
	Color[] c = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue};
	public JLabel label_6;
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getData();
			panel.repaint();
		}
	}
	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			BufferedImage bi = getbuf();
			var s = Arrays.stream(c).filter(x->x.getRGB()==bi.getRGB(e.getX(), e.getY())).findAny().orElse(null);
			if(s!=null) {
				int idx = Arrays.asList(c).indexOf(s);
				int dno = dnos[idx];
				String r = "<html>";
				try {
					String path = comboBox.getSelectedIndex()==0 ? "record" : "reservation";
					var rs =res("select d.name dn, h.name hn, date from doctor d  join hospital h using(hno) left join "+path+" using(dno) where dno = "+dno+" order by date");
					while(rs.next()) {
						r += rs.getString(1)+"/"+rs.getString(2)+"/"+rs.getString(3)+"<br>";
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				label_6.setText(r);
			}
			else {
				label_6.setText("");
			}
		}
	}
}
