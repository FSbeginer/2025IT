import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class J_분석 extends BF {
	public JLabel label;
	public JPanel panel;
	public JComboBox comboBox;
	public JLabel label_1;
	public JPanel panel_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;

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
		setBounds(100, 100, 701, 563);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("\uBD84\uC11D");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 685, 68);
		getContentPane().add(label);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(getChart(), 0, 0, null);
			}

		};
		panel.setBounds(0, 91, 685, 354);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setVerticalTextPosition(SwingConstants.TOP);
		label_1.setBounds(481, 10, 192, 334);
		panel.add(label_1);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC9C4\uB8CC \uB9CE\uC740 \uC758\uC0AC 5", "\uC608\uC57D \uB9CE\uC740 \uC758\uC0AC 5"}));
		comboBox.setBounds(535, 50, 138, 32);
		getContentPane().add(comboBox);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 455, 685, 59);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
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
		
		label_6 = new JLabel("New label");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_6);
		
		getData("record");
	}

	List<data> datas = new ArrayList<data>();
	private void getData(String sql) {
		datas.clear();
		panel_1.removeAll();
		try (var rs = res("select dno,d.name dname, h.name hname, count(*) cnt from "+sql+" r join doctor d using(dno) join hospital h using(hno) group by dno order by cnt desc, dno limit 5;")) {
			while(rs.next()) {
				datas.add(new data(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), c[datas.size()]));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_1.revalidate();
		panel_1.repaint();
	}
	Color[] c = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue};
	public ImageIcon getIcon(Color c) {
		BufferedImage bi = new BufferedImage(35, 35, 2);
		var g = bi.createGraphics();
		g.setColor(c);
		g.fillOval(0, 0, 35, 35);
		return new ImageIcon(bi);
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0)
				getData("record");
			else
				getData("reservation");
			repaint();
		}
	}
	class data {
		int dno;
		String dname;
		String hname;
		int cnt;
		List<String> date = new ArrayList<String>();
		Color c;
		public data(int dno, String dname, String hname, int cnt, Color c) {
			this.dno = dno;
			this.dname = dname;
			this.hname = hname;
			this.cnt = cnt;
			this.c=c;
			String sql = comboBox.getSelectedIndex()==0?"record":"reservation";
			try {
				var rs = res("select * from "+sql+" where dno = "+dno);
				while(rs.next()) {
					date.add(rs.getString("date"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JLabel jl = new JLabel(getIcon(c));
			jl.setText(dname);
			panel_1.add(jl);
		}
	}
	private BufferedImage getChart() {
		BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), 2);
		Graphics2D g2 = bi.createGraphics();
		AffineTransform ori = g2.getTransform();
		
		int ang = 90;
		int x = panel.getWidth()/2-150;
		int sum = datas.stream().mapToInt(e->e.cnt).sum();
		
		for (int i = 0; i < 5; i++) {
			data d = datas.get(i);
			int deg = (int) ((double)d.cnt/sum * 360);
			
			g2.setColor(d.c);
			g2.fillArc(x, 0, 300, 300, ang, -deg);
			
			
			String str = String.format("%.1f", (double)d.cnt/sum*100);
			int w = g2.getFontMetrics().stringWidth(str)/2;
			
			g2.rotate(-Math.toRadians(ang-deg/2), x+150-w, 150);
			g2.rotate(Math.toRadians(ang-deg/2), x+150+150-w, 150);
			g2.setColor(Color.black);
			g2.drawString(str, x+300-w, 0);
			
			g2.setTransform(ori);
			
			ang -= deg;
		}
		
		g2.setColor(Color.white);
		g2.fillOval(x+50, 50, 200, 200);
		return bi;
	}
}
