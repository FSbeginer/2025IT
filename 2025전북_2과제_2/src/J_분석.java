import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
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
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class J_분석 extends BF {
	public JLabel label;
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JComboBox comboBox;
	BufferedImage buf;

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
		setBounds(100, 100, 657, 523);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("\uBD84\uC11D");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 641, 62);
		getContentPane().add(label);

		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;

				buf = getbuf();
				g2.drawImage(buf, 0, 0, null);
			}
		};
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.setBounds(10, 53, 619, 345);
		getContentPane().add(panel);
		panel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC9C4\uB8CC \uB9CE\uC740 \uC758\uC0AC 5",
				"\uC608\uC57D \uB9CE\uC740 \uC758\uC0AC 5" }));
		comboBox.setBounds(468, 10, 139, 28);
		panel.add(comboBox);

		label_6 = new JLabel("");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setVerticalAlignment(SwingConstants.TOP);
		label_6.setBounds(361, 43, 246, 221);
		panel.add(label_6);

		panel_1 = new JPanel();
		panel_1.setBounds(20, 408, 590, 66);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

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

		getdata();
	}

	private BufferedImage getbuf() {
		BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), 2);
		var g = bi.createGraphics();

		int ang = 90;
		AffineTransform af = g.getTransform();
		JLabel[] jls = { label_1, label_2, label_3, label_4, label_5 };
		for (int i = 0; i < 5; i++) {
			jls[i].setText(name[i]);
			g.setColor(c[i]);
			int deg = (int) ((double) cnt[i] / Arrays.stream(cnt).sum() * 360);
			g.fillArc(getWidth() / 2 - 150, 0, 300, 300, ang, -deg);
			ang -= deg;
		}
		ang = 90;
		g.setColor(Color.black);
		for (int i = 0; i < 5; i++) {
			int deg = (int) ((double) cnt[i] / Arrays.stream(cnt).sum() * 360);
			String per = String.format("%.1f%%", (double) cnt[i] / Arrays.stream(cnt).sum() * 100);
			g.rotate(-Math.toRadians(ang - deg / 2), getWidth() / 2, 150);
			g.rotate(Math.toRadians(ang - deg / 2), (getWidth() / 2 + 150), 150);
			g.drawString(per, getWidth() / 2 + 150 - (g.getFontMetrics().stringWidth(per) / 2), 150);
			g.setTransform(af);
			ang -= deg;
		}
		g.setColor(Color.white);
		g.fillOval(getWidth() / 2 - 100, 50, 200, 200);
		return bi;
	}

	Color[] c = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue };
	int[] cnt = new int[5];
	String[] name = new String[5];
	int[] dno = new int[5];
	String group = "record";
	public JLabel label_6;

	private void getdata() {
		JLabel[] jls = { label_1, label_2, label_3, label_4, label_5 };
		try {
			var rs = res("select *, d.name dname,count(*) cnt from " + group
					+ " join doctor d using(dno) join hospital h using(hno) group by dno order by cnt desc, dno limit 5;");
			int i = 0;
			while (rs.next()) {
				cnt[i] = rs.getInt("cnt");
				name[i] = rs.getString("dname");
				dno[i] = rs.getInt("dno");
				jls[i].setIcon(geticon(c[i]));
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

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (comboBox.getSelectedIndex() == 0)
				group = "record";
			else
				group = "reservation";
			getdata();
			repaint();
		}
	}

	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			int rgb = buf.getRGB(e.getX(), e.getY());
			var imsi = Arrays.stream(c).filter(x -> x.getRGB() == rgb).findAny().orElse(null);
			if (imsi != null) {
				int idx = 0;
				for (int i = 0; i < c.length; i++) {
					if (imsi.equals(c[i]))
						idx = i;
				}
				String s = "<html>";
				try {
					var rs = res("select *,h.name hname, d.name dname from " + group
							+ " join doctor d using(dno) join hospital h using(hno)  where dno = " + dno[idx]);
					while(rs.next()) {
						s+= rs.getString("dname")+"/"+rs.getString("hname")+"/"+rs.getString("date")+"<br>";
					}
					label_6.setText(s);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else {
				label_6.setText("");
			}
		}
	}
}
